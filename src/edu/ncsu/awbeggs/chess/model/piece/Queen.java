package edu.ncsu.awbeggs.chess.model.piece;

import java.util.HashSet;
import java.util.Set;

import edu.ncsu.awbeggs.chess.model.board.Board;
import edu.ncsu.awbeggs.chess.model.board.Location;
import edu.ncsu.awbeggs.chess.model.board.Move;
import edu.ncsu.awbeggs.chess.ui.SpriteLookup;

/**
 * Represents a Queen {@link Piece}, which may exist on a {@link Board}.
 * @author Aidan Beggs
 */
public class Queen extends Piece {

	/** Full constructor for Queen. Sets {@link Location}, {@link PieceColor}, 
	 * {@link SpriteLookup}, and {@link Board} for this Queen.
	 * @param location the {@link Location} of this Queen.
	 * @param color the {@link PieceColor} of this Queen.
	 */
	public Queen(Location location, PieceColor color) {
		super(location, color, SpriteLookup.QUEEN);
	}
	
	@Override
	protected Set<Move> getValidMovesNoCheck() {
		Set<Move> validMoves = new HashSet<>();
		
		for(int rowMod : new int[] {-1, 0, 1}) {
			for(int colMod : new int[] {-1, 0, 1}) {
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
