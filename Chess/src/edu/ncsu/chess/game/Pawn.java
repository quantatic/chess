package edu.ncsu.chess.game;

import java.util.ArrayList;
import java.util.List;

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
		super(c, PieceType.PAWN);
	}

	@Override
	public List<Location> validMoves(ChessBoard b, Location l) {
		ArrayList<Location> validMoves = new ArrayList<>();
		
		int directionY = getColor() == PieceColor.WHITE ? 1 : -1; //1 if going up board (white), -1 if down (black)
		if(b.validLocation(l.getRow() + directionY, l.getCol()) 
				&& b.getLocation(l.getRow() + directionY, l.getCol()).isEmpty()) {
			validMoves.add(b.getLocation(l.getRow() + directionY, l.getCol()));
		}
		
		if(!hasMoved() && b.validLocation(l.getRow() + 2 * directionY, l.getCol())
				&& b.getLocation(l.getRow() + directionY, l.getCol()).isEmpty()
				&& b.getLocation(l.getRow() + 2 * directionY, l.getCol()).isEmpty()) {
			validMoves.add(b.getLocation(l.getRow() + 2 * directionY, l.getCol()));
		}
		
		//Check both possible directions for taking a piece
		for(int directionX : new int[]{-1, 1}) {
			if(b.validLocation(l.getRow() + directionY, l.getCol() + directionX)) {
				Location thisLoc = b.getLocation(l.getRow() + directionY, l.getCol() + directionX);

				if(!thisLoc.isEmpty() && thisLoc.getPiece().getColor() != getColor()) {
					validMoves.add(thisLoc);
				}
			}
		}
		
		return validMoves;
	}
}
