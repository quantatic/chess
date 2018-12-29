package edu.ncsu.chess.piece;

import java.util.ArrayList;
import java.util.List;

import edu.ncsu.chess.game.ChessBoard;
import edu.ncsu.chess.game.Location;

/**
 * Represents a bishop, a chess piece.
 * @author Aidan Beggs
 */
public class Bishop extends AbstractPiece{

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
		
		return result;
	}
}
