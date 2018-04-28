package edu.ncsu.awbeggs.chess.model.piece;

import java.util.HashSet;
import java.util.Set;

import edu.ncsu.awbeggs.chess.model.board.Board;
import edu.ncsu.awbeggs.chess.model.board.Location;
import edu.ncsu.awbeggs.chess.ui.SpriteLookup;

/**
 * Represents a King {@link Piece} which may exist on a {@link Board}.
 * @author Aidan Beggs
 */
public class King extends Piece {
	
	private boolean hasMoved;
	
	/** Full constructor for King. Sets {@link Location}, {@link PieceColor}, 
	 * {@link SpriteLookup}, and {@link Board} for this King.
	 * @param location the {@link Location} of this King.
	 * @param color the {@link PieceColor} of this King.
	 * @param board the {@link Board} this King inhabits.
	 */
	public King(Location location, PieceColor color, Board board) {
		super(location, color, SpriteLookup.KING, board);
		hasMoved = false;
	}

	@Override
	protected Set<Location> getValidMovesNoCheck() {
		Set<Location> valid = new HashSet<>();
		
		for(int rowOffset : new int[] {-1, 0, 1}) {
			for(int colOffset : new int[] {-1, 0, 1}) {
				Location tmpLocation = getBoard().getLocation(getRow() + colOffset, 
						getCol() + rowOffset);
				
				if(tmpLocation != null && (tmpLocation.isEmpty() 
						|| tmpLocation.getOccupant().getColor() != getColor())) {
					valid.add(tmpLocation);
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
			if(p.getValidMovesNoCheck().contains(getLocation())) {
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
	public Set<Location> getValidMoves() {
		Set<Location> valid = super.getValidMoves();
		if(canCastle(true)) {
			valid.add(getBoard().getLocation(getRow(), 7));
		}
		
		if(canCastle(false)) {
			valid.add(getBoard().getLocation(getRow(), 3));
		}
		return valid;
	}

	/**
	 * Attempts to move this {@link King} to the passed {@link Location}, accounting for possible attempts
	 * to castle. Updates whether or not this {@link King} has moved.
	 * @param l the {@link Location} to attempt to move this {@link King} to.
	 * @return true if the move was successful, false otherwise.
	 */
	@Override
	public boolean attemptMove(Location l) {
		boolean movedThisTurn;
		if(l.getCol() == 7 && canCastle(true)) {
			getBoard().getLocation(getRow(), 8).getOccupant()
				.attemptMove(getBoard().getLocation(getRow(), 6));
			setLocation(getBoard().getLocation(getRow(), 7));
			movedThisTurn = true;
			
		} else if(l.getCol() == 3 && canCastle(false)) {
			getBoard().getLocation(getRow(), 1).getOccupant()
				.attemptMove(getBoard().getLocation(getRow(), 4));
			setLocation(getBoard().getLocation(getRow(), 3));
			movedThisTurn = true;
		} else {
			movedThisTurn = super.attemptMove(l);
		}
		
		
		if(!hasMoved) {
			hasMoved = movedThisTurn;
		}
		
		return movedThisTurn;
	}
	
	/**
	 * Finds out if this {@link King} can castle on a given side.
	 * @param side The side to check castling on, true to check King-side, false to check Queen-side.
	 * @return true if this {@link King} can castle on the given side, false otherwise.
	 */
	public boolean canCastle(boolean side) {
		if(hasMoved) {
			return false;
		}
		
		if(isInCheck()) {
			return false;
		}
		
		int rookLocation = side ? 8 : 1;
		
		Piece toCastleWith = getBoard().getLocation(getRow(), rookLocation).getOccupant();
		
		if(!(toCastleWith instanceof Rook && ((Rook)toCastleWith).canCastle())) {
			return false;
		}
		
		int[] colsToCheck = side ? new int[]{6, 7} : new int[]{4, 3};
		boolean canCastle = true;
		Location initLocation = getLocation();
		
		for(int col : colsToCheck) {
			Location toCheck = getBoard().getLocation(getRow(), col);
			if(!toCheck.isEmpty()) {
				canCastle = false;
				break;
			}
			
			setLocation(toCheck);
			
			if(isInCheck()) {
				canCastle = false;
				break;
			}
		}
		
		setLocation(initLocation);
		return canCastle;
	}

}
