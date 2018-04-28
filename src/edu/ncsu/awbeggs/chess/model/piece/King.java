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
	
	/** Full constructor for King. Sets {@link Location}, {@link PieceColor}, 
	 * {@link SpriteLookup}, and {@link Board} for this King.
	 * @param location the {@link Location} of this King.
	 * @param color the {@link PieceColor} of this King.
	 * @param board the {@link Board} this King inhabits.
	 */
	public King(Location location, PieceColor color, Board board) {
		super(location, color, SpriteLookup.KING, board);
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
}
