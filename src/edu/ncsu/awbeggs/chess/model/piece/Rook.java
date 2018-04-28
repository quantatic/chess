package edu.ncsu.awbeggs.chess.model.piece;

import java.util.HashSet;
import java.util.Set;

import edu.ncsu.awbeggs.chess.model.board.Board;
import edu.ncsu.awbeggs.chess.model.board.Location;
import edu.ncsu.awbeggs.chess.ui.SpriteLookup;

/**
 * Represents a rook {@link Piece}, which may exist on a {@link Board}.
 * @author Aidan Beggs
 */
public class Rook extends Piece {

	/** Full constructor for Rook. Sets {@link Location}, {@link PieceColor}, 
	 * {@link SpriteLookup}, and {@link Board} for this Rook.
	 * @param location the {@link Location} of this Rook.
	 * @param color the {@link PieceColor} of this Rook.
	 * @param board the {@link Board} this Rook inhabits.
	 */
	public Rook(Location location, PieceColor color, Board board) {
		super(location, color, SpriteLookup.ROOK, board);
	}

	@Override
	protected Set<Location> getValidMovesNoCheck() {
		Set<Location> validMoves = new HashSet<>();
		
		for(int rowMod : new int[] {-1, 0, 1}) {
			for(int colMod : new int[] {-1, 0, 1}) {
				if(Math.abs(rowMod + colMod) != 1) {
					continue;
				}
				
				int tmpOffsetRow = rowMod;
				int tmpOffsetCol = colMod;
				Location tmpLocation = getBoard().getLocation(getRow() + tmpOffsetRow, 
						getCol() + tmpOffsetCol);
				while(tmpLocation != null && tmpLocation.isEmpty()) {
					validMoves.add(tmpLocation);
					tmpOffsetRow += rowMod;
					tmpOffsetCol += colMod;
					tmpLocation = getBoard().getLocation(getRow() + tmpOffsetRow, 
							getCol() + tmpOffsetCol);
				}
				
				if(tmpLocation != null && tmpLocation.getOccupant().getColor() != getColor()) {
					validMoves.add(tmpLocation);
				}
			}
		}
		
		return validMoves;
	}
}
