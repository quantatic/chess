package edu.ncsu.chess.piece;

import java.util.ArrayList;
import java.util.List;

import edu.ncsu.chess.game.ChessBoard;
import edu.ncsu.chess.game.Location;

/**
 * Represents a king, a chess piece.
 * @author Aidan Beggs
 */
public class King extends AbstractPiece{

	/**
	 * Creates a new king with the given color.
	 * @param c the color for this king.
	 */
	public King(PieceColor c) {
		super(c, PieceType.KING);
	}
	
	@Override
	public List<Location> validMoves(ChessBoard b, int startRow, int startCol) {
		List<Location> result = new ArrayList<>();
		
		return result;
	}
}