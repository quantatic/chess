package edu.ncsu.chess.game;

import java.util.ArrayList;
import java.util.List;

/**
 * Creates a new queen, a chess piece.
 * @author Aidan Beggs
 */
public class Queen extends AbstractPiece {

	/**
	 * Creates a new queen with a given color.
	 * @param c the color for this queen.
	 */
	public Queen(PieceColor c) {
		super(c, PieceType.QUEEN);
	}
	
	@Override
	public List<Location> validMoves(ChessBoard b, int startRow, int startCol) {
		List<Location> result = new ArrayList<>();
		
		for(int directionX : new int[] {-1, 0, 1}) {
			for(int directionY : new int[] {-1, 0, 1}) {
				if(directionX != 0 || directionY != 0) { //if not in no direction
					int tmpRow = startRow + directionY;
					int tmpCol = startCol + directionX;
					
					while(b.validLocation(tmpRow, tmpCol)
							&& b.getLocation(tmpRow, tmpCol).isEmpty()) { //while on board and haven't hit a piece, add it
						result.add(b.getLocation(tmpRow, tmpCol));
						tmpRow += directionY;
						tmpCol += directionX;
					}
					
					if(b.validLocation(tmpRow, tmpCol) //if the place where we couldn't move anymore was a piece of the opposite color
							&& b.getLocation(tmpRow, tmpCol).getPiece().getColor() != getColor()) {
						result.add(b.getLocation(tmpRow, tmpCol));
					}
				}
			}
		}
		
		return result;
	}
}
