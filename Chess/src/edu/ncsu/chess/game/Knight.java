package edu.ncsu.chess.game;

import java.util.ArrayList;
import java.util.List;

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
	public List<Location> validMoves(ChessBoard b, Location l) {
		List<Location> result = new ArrayList<>();
		
		for(int directionY : new int[]{-2, -1, 1, 2}) {
			for(int directionX : new int[] {-2, -1, 1, 2}) {
				if(Math.abs(directionX) + Math.abs(directionY) == 3) { //algorithm for valid knight moves
					if(b.validLocation(l.getRow() + directionY, l.getCol() + directionX)) {
						Location thisLocation = b.getLocation(l.getRow() + directionY, l.getCol() + directionX);
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