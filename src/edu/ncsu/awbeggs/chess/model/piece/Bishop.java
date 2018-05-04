package edu.ncsu.awbeggs.chess.model.piece;

import java.util.HashSet;
import java.util.Set;

import edu.ncsu.awbeggs.chess.model.board.Board;
import edu.ncsu.awbeggs.chess.model.board.Location;
import edu.ncsu.awbeggs.chess.model.board.Move;
import edu.ncsu.awbeggs.chess.ui.SpriteLookup;

/**
 * Represents a Bishop, which extends {@link Piece}.
 * @author Aidan Beggs
 */
public class Bishop extends Piece {

	/** Full constructor for Bishop. Sets {@link Location}, {@link PieceColor}, 
	 * {@link SpriteLookup}, and {@link Board} for this Bishop.
	 * @param location the {@link Location} of this Bishop.
	 * @param color the {@link PieceColor} of this Bishop.
	 */
	public Bishop(Location location, PieceColor color) {
		super(location, color, SpriteLookup.BISHOP);
	}

	@Override
	public Set<Move> getValidMovesNoCheck() {
		Set<Move> validMoves = new HashSet<>();
		
		for(int rowMod : new int[] {-1, 1}) {
			for(int colMod : new int[] {-1, 1}) {
				int tmpOffsetRow = rowMod;
				int tmpOffsetCol = colMod;
				Location tmpLocation = getBoard().getLocation(getRow() + tmpOffsetRow, 
						getCol() + tmpOffsetCol);
				
				while(tmpLocation != null && tmpLocation.isEmpty()) {
					validMoves.add(getMoveTo(tmpLocation));
					tmpOffsetRow += rowMod;
					tmpOffsetCol += colMod;
					tmpLocation = getBoard().getLocation(getRow() + tmpOffsetRow, 
							getCol() + tmpOffsetCol);
				}
				
				if(tmpLocation != null && tmpLocation.getOccupant().getColor() != getColor()) {
					validMoves.add(getMoveTo(tmpLocation));
				}
			}
		}
		
		return validMoves;
	}
}
