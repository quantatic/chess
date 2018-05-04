package edu.ncsu.awbeggs.chess.model.piece;

import java.util.HashSet;
import java.util.Set;

import edu.ncsu.awbeggs.chess.model.board.Board;
import edu.ncsu.awbeggs.chess.model.board.Location;
import edu.ncsu.awbeggs.chess.model.board.Move;
import edu.ncsu.awbeggs.chess.ui.SpriteLookup;

/**
 * Represents a King {@link Piece} which may exist on a {@link Board}.
 * @author Aidan Beggs
 */
public class King extends Piece {
	

	
	/** Full constructor for King. Sets {@link Location}, {@link PieceColor}, 
	 * {@link SpriteLookup}, and {@link Board} for this King.
	 * @param location the {@link Location} of this King.
	 * @param color the {@link PieceColor} of this King.
	 */
	public King(Location location, PieceColor color) {
		super(location, color, SpriteLookup.KING);
	}

	@Override
	protected Set<Move> getValidMovesNoCheck() {
		Set<Move> valid = new HashSet<>();
		
		for(int rowOffset : new int[] {-1, 0, 1}) {
			for(int colOffset : new int[] {-1, 0, 1}) {
				Location tmpLocation = getBoard().getLocation(getRow() + colOffset, 
						getCol() + rowOffset);
				
				if(tmpLocation != null && (tmpLocation.isEmpty() 
						|| tmpLocation.getOccupant().getColor() != getColor())) {
					valid.add(getMoveTo(tmpLocation));
				}
			}
		}
		
		return valid;
	}
	
	/**
	 * Checks if this King is in check by any other {@link Piece}.
	 * @return true if this King is in check by any other {@link Piece}.
	 */
	public boolean isInCheck() {
		Set<Piece> otherPieces = getBoard().getPieces((getColor() == PieceColor.WHITE) ? PieceColor.BLACK : PieceColor.WHITE);
		for(Piece p : otherPieces) {
			if(p.getValidMovesNoCheck().contains(p.getMoveTo(getLocation()))) {
				return true;
			}
		}
		
		return false;
	}

	/**
	 * Gets a {@link Set} of {@link Location} objects for all valid moves for this {@link King}.
	 * @return a {@link Set} of {@link Location} objects for all valid moves for this {@link King}.
	 */
	@Override
	public Set<Move> getValidMoves() {
		Set<Move> valid = super.getValidMoves();
		if(canCastle(true)) {
			valid.add(getMoveTo(getBoard().getLocation(getRow(), 7)));
		}
		
		if(canCastle(false)) {
			valid.add(getMoveTo(getBoard().getLocation(getRow(), 3)));
		}
		return valid;
	}
	
	/**
	 * Finds out if this {@link King} can castle on a given side.
	 * @param kingSide The side to check castling on, true to check King-side, false to check Queen-side.
	 * @return true if this {@link King} can castle on the given side, false otherwise.
	 */
	private boolean canCastle(boolean kingSide) {
		if(hasMoved() || isInCheck()) { //if moved or in check, can't castle
			return false;
		}
		
		int rookLocation = kingSide ? 8 : 1; //get the location of the rook to check if castling is legal with
		
		Piece toCastleWith = getBoard().getLocation(getRow(), rookLocation).getOccupant();
		
		if(!(toCastleWith instanceof Rook)) { //if piece in wanted position is not rook
			return false;
		}
		
		Rook rookToCastleWith = (Rook)toCastleWith; //cast to a rook
		
		if(rookToCastleWith.getBoard() != getBoard() || rookToCastleWith.hasMoved()) {
			return false; //if the rook is on a different board or it has moved
		}
		
		int rookColTo = (rookToCastleWith.getCol() == 8) ? 6 : 4; //find where the rook wants to move to in castle
		
		if(!(rookToCastleWith.getValidMoves()
				.contains(rookToCastleWith.getMoveTo(getBoard().getLocation(rookToCastleWith.getRow(), rookColTo))))) {
			return false; //if move to wanted location is not valid
		}
		
		int[] colsToCheck = kingSide ? new int[]{6, 7} : new int[]{4, 3}; //find which locations king needs to move through
		boolean canCastle = true; //assume we can castle unless we find otherwise
		Location initLocation = getLocation(); //grab initial location so we can return later
		
		for(int col : colsToCheck) { //loop through columns of king to check
			Location toCheck = getBoard().getLocation(getRow(), col);
			if(!toCheck.isEmpty()) { //if the location we need to move to/through is not empty
				canCastle = false;
				break;
			}
			
			setLocation(toCheck); //"move" to location temporarily
			
			if(isInCheck()) { //make sure king is not in check at location -- cannot move through or into check
				canCastle = false;
				break;
			}
		}
		
		setLocation(initLocation); //resets location to initial location
		return canCastle; //returns whether or not the king can castle
	}
	
	public boolean doCastle(boolean kingSide) {
		if(!canCastle(kingSide)) {
			return false;
		}
		
		int rookAt = kingSide ? 8 : 1;
		int rookTo = kingSide ? 6 : 4; 
		int kingTo = kingSide ? 7 : 3;
		
		Rook toMove = (Rook)(getBoard().getLocation(getRow(), rookAt).getOccupant());
		toMove.setLocation(getBoard().getLocation(getRow(), rookTo));
		toMove.incrementMovesMade();
		
		setLocation(getBoard().getLocation(getRow(), kingTo));
		incrementMovesMade();
		
		return true;
	}
	
	
	public boolean undoCastle(boolean kingSide) {
		int rookAt = kingSide ? 6 : 4;
		int rookTo = kingSide ? 8 : 1; 
		int kingTo = 5;
		
		Rook toMove = (Rook)(getBoard().getLocation(getRow(), rookAt).getOccupant());
		toMove.setLocation(getBoard().getLocation(getRow(), rookTo));
		toMove.decrementMovesMade();
		
		setLocation(getBoard().getLocation(getRow(), kingTo));
		decrementMovesMade();
		
		return true;
	}
}
