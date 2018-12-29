package edu.ncsu.chess.piece;

import java.util.ArrayList;
import java.util.List;

import edu.ncsu.chess.game.ChessBoard;
import edu.ncsu.chess.game.Location;

/**
 * Creates a new rook, a chess piece.
 * @author Aidan Beggs
 */
public class Rook extends AbstractPiece {

	/**
	 * Creates a new rook with a given color.
	 * @param c the color for this rook.
	 */
	public Rook(PieceColor c) {
		super(c, PieceType.ROOK);
	}

	@Override
	public List<Location> validMoves(ChessBoard b, int startRow, int startCol) {
		List<Location> result = new ArrayList<>();
		
		return result;
	}
	
	
}
