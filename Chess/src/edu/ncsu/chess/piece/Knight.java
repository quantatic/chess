package edu.ncsu.chess.piece;

import java.util.ArrayList;
import java.util.List;

import edu.ncsu.chess.game.ChessBoard;
import edu.ncsu.chess.game.Location;

/**
 * Creates a new knight, a chess piece.
 * @author Aidan Beggs
 */
public class Knight extends AbstractPiece {

	/**
	 * Creates a new knight with a given color.
	 * @param c the color for this knight.
	 */
	public Knight(PieceColor c) {
		super(c, PieceType.KNIGHT);
	}

	@Override
	public List<Location> validMoves(ChessBoard b, int startRow, int startCol) {
		List<Location> result = new ArrayList<>();
		
		for(int directionY : new int[]{-2, -1, 1, 2}) {
			for(int directionX : new int[] {-2, -1, 1, 2}) {
				if(Math.abs(directionX) + Math.abs(directionY) == 3) { //algorithm for valid knight moves
					if(b.validLocation(startRow + directionY, startCol + directionX)) {
						Location thisLocation = b.getLocation(startRow + directionY, startCol + directionX);
						if(thisLocation.isEmpty() || thisLocation.getPiece().getColor() != getColor()) {
							result.add(thisLocation);
						}
					}
				}
			}
		}
		return result;
	}

}