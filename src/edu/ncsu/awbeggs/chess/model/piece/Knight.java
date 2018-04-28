package edu.ncsu.awbeggs.chess.model.piece;

import java.util.HashSet;
import java.util.Set;

import edu.ncsu.awbeggs.chess.model.board.Board;
import edu.ncsu.awbeggs.chess.model.board.Location;
import edu.ncsu.awbeggs.chess.ui.SpriteLookup;

/**
 * Represents a Knight {@link Piece} which may exist on a {@link Board}.
 * @author Aidan Beggs
 */
public class Knight extends Piece {
	
	/** Full constructor for Knight. Sets {@link Location}, {@link PieceColor}, 
	 * {@link SpriteLookup}, and {@link Board} for this Knight.
	 * @param location the {@link Location} of this Knight.
	 * @param color the {@link PieceColor} of this Knight.
	 * @param board the {@link Board} this Knight inhabits.
	 */
	public Knight(Location location, PieceColor color, Board board) {
		super(location, color, SpriteLookup.KNIGHT, board);
	}

	@Override
	public Set<Location> getValidMovesNoCheck() {
		Set<Location> validMoves = new HashSet<Location>();
		
		for(int rowMod : new int[] {-1, 1}) {
			for(int colMod : new int[] {-1, 1}) {
				for(int row : new int[] {1, 2}) {
					Location checkLocation = getBoard().getLocation(getRow() + (row * rowMod),
							getCol() + ((3 - row) * colMod));
					
					if(checkLocation != null 
							&& (checkLocation.isEmpty() 
									|| (checkLocation.getOccupant().getColor() != getColor()))) {
						validMoves.add(checkLocation);
					}
				}
			}
		}
		
		return validMoves;
	}
}
