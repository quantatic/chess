package edu.ncsu.chess.piece;

import java.util.ArrayList;
import java.util.List;

import edu.ncsu.chess.game.ChessBoard;
import edu.ncsu.chess.game.Location;

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
		
		return result;
	}
}
