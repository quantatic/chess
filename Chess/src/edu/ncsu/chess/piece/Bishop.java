package edu.ncsu.chess.piece;

import java.util.ArrayList;
import java.util.List;

import edu.ncsu.chess.game.ChessBoard;
import edu.ncsu.chess.game.Location;

/**
 * Represents a bishop, a chess piece.
 * @author Aidan Beggs
 */
public class Bishop extends AbstractPiece {

	/**
	 * Creates a new bishop with the given color.
	 * @param c the color of this bishop.
	 */
	public Bishop(PieceColor c) {
		super(c, PieceType.BISHOP);
	}

	@Override
	public List<Location> validMoves(ChessBoard b, int startRow, int startCol) {
		List<Location> result = new ArrayList<>();
		
		for(int directionX : new int[] {-1, 1}) {
			for(int directionY : new int[] {-1, 1}) {
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
		
		return result;
	}
}
