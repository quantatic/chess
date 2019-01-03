package edu.ncsu.chess.game;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a king, a chess piece.
 * @author Aidan Beggs
 */
public class King extends AbstractPiece {

	/**
	 * Creates a new king with the given color.
	 * @param c the color for this king.
	 */
	public King(PieceColor c) {
		super(c, PieceType.KING);
	}
	
	@Override
	public List<Location> validMoves(ChessBoard b, Location l) {
		List<Location> result = new ArrayList<>();
		
		for(int directionX : new int[]{-1, 0, 1}) {
			for(int directionY : new int[]{-1, 0, 1}) {
				if(directionX != 0 || directionY != 0) {
					if(b.validLocation(l.getRow() + directionY, l.getCol() + directionX)) {
						Location tmpLoc = b.getLocation(l.getRow() + directionY, l.getCol() + directionX);
						if(tmpLoc.isEmpty() || tmpLoc.getPiece().getColor() != getColor()) {
							result.add(tmpLoc);
						}
					}
				}
			}
		}
		
		return result;
	}
}