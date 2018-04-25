package edu.ncsu.awbeggs.chess.piece;

import edu.ncsu.awbeggs.chess.color.ANSIColor;

/**
 * Represents the Color of a playing piece.
 * @author Aidan Beggs
 */
public enum PieceColor {
	/** Represents a black playing piece. */
	BLACK(ANSIColor.BLACK),
	
	/** Represents a white playing piece. */
	WHITE(ANSIColor.WHITE);
	
	/** The ANSIColor wrapper representing the color of this piece. */
	private ANSIColor c;
	
	private PieceColor(ANSIColor c) {
		this.c = c;
	}
	
	/**
	 * Returns an ANSI color coded string representation of some text, with this Piece's Color.
	 * @param in the String to colorize with this PieceColor.
	 * @return the colorized String.
	 */
	public String colorize(String in) {
		return c.getCode() + in + ANSIColor.RESET;
	}
}
