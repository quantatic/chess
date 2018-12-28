package edu.ncsu.chess.piece;

import java.util.ArrayList;
import java.util.List;

import edu.ncsu.chess.game.Board;
import edu.ncsu.chess.game.Location;

/**
 * Represents a chess pawn.
 * @author Aidan Beggs
 */
public class Pawn extends AbstractPiece {
	
	/**
	 * Creates a new pawn with a given color.
	 * @param c the color for this pawn to have.
	 */
	public Pawn(PieceColor c) {
		super(c);
	}

	@Override
	public List<Location> validMoves(Board b, int startRow, int startCol) {
		ArrayList<Location> validMoves = new ArrayList<>();
		
		int directionY = getColor() == PieceColor.WHITE ? 1 : -1; //1 if going up board (white), -1 if down (black)
		if(b.validLocation(startRow + directionY, startCol)) {
			validMoves.add(b.getLocation(startRow + directionY, startCol));
		}
		
		//Check both possible directions for taking a piece
		for(int directionX : new int[]{-1, 1}) {
			if(b.validLocation(startRow + directionY, startCol + directionX)) {
				Location thisLoc = b.getLocation(startRow + directionY, startCol + directionX);

				if(!thisLoc.isEmpty() && thisLoc.getPiece().getColor() != getColor()) {
					validMoves.add(thisLoc);
				}
			}
		}
		
		return validMoves;
	}
	
	@Override
	public String toString() {
		return getColor() == PieceColor.WHITE ? "p" : "P";
	}
}