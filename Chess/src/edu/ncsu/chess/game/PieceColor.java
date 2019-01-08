package edu.ncsu.chess.game;

/**
 * Represents possible colors for chess pieces.
 * @author Aidan Beggs
 */
public enum PieceColor {
	
	/** A black chess piece. */
	BLACK,
	
	/** A white chess piece. */
	WHITE;
	
	public PieceColor other() {
		return (this == WHITE) ? BLACK : WHITE;
	}
}
