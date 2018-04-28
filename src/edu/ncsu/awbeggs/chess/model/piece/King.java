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
	 * @return
	 */
	@Override
	public Set<Location> getValidMoves() {
		Set<Location> valid = super.getValidMoves();
		if(canCastleKingSide()) {
			valid.add(getBoard().getLocation(getRow(), 7));
		}
		
		if(canCastleQueenSide()) {
			valid.add(getBoard().getLocation(getRow(), 3));
		}
		return valid;
	}

	/**
	 * @param l
	 * @return
	 */
	@Override
	public boolean attemptMove(Location l) {
		boolean movedThisTurn;
		if(l.getCol() == 7 && canCastleKingSide()) {
			getBoard().getLocation(getRow(), 8).getOccupant().
				attemptMove(getBoard().getLocation(getRow(), 6));
			setLocation(getBoard().getLocation(getRow(), 7));
			movedThisTurn = true;
			
		} else if(l.getCol() == 3 && canCastleQueenSide()) {
			getBoard().getLocation(getRow(), 1).getOccupant().
				attemptMove(getBoard().getLocation(getRow(), 4));
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
	
	public boolean canCastleKingSide() {
		if(hasMoved) {
			return false;
		}
		
		if(isInCheck()) {
			return false;
		}
		
		Piece toCastleWith = getBoard().getLocation(getRow(), 8).getOccupant();
		
		if(!(toCastleWith instanceof Rook) && ((Rook)toCastleWith).canCastle()) {
			return false;
		}
		
		int[] colsToCheck = {6, 7};
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
	
	public boolean canCastleQueenSide() {
		if(hasMoved) {
			return false;
		}
		
		if(isInCheck()) {
			return false;
		}
		
		Piece toCastleWith = getBoard().getLocation(getRow(), 1).getOccupant();
		
		if(!(toCastleWith instanceof Rook) && ((Rook)toCastleWith).canCastle()) {
			return false;
		}
		
		int[] colsToCheck = {4, 3};
		boolean canCastle = true;
		Location initLocation = getLocation();
		
		for(int col : colsToCheck) {
			Location toCheck = getBoard().getLocation(getRow(), col);
			if(!toCheck.isEmpty()) {
				canCastle = false;
				return false;
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
