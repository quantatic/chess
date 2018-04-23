package edu.ncsu.awbeggs.chess.colors;

/**
 * Color wrapper for ANSI Color codes.
 * @author Aidan Beggs
 */
public enum ANSIColor {
	/** Represents the "Reset" ANSI code.*/
	RESET("\u001B[0m"),
	
	/** Represents the "Black Text" ANSI code.*/
	BLACK("\u001B[30m"),
	
	/** Represents the "Red Text" ANSI code.*/
	RED("\u001B[31m"),
	
	/** Represents the "Green Text" ANSI code.*/
	GREEN("\u001B[32m"),
	
	/** Represents the "Yellow Text" ANSI code.*/
	YELLOW("\u001B[33m"),
	
	/** Represents the "Blue Text" ANSI code. */
	BLUE("\u001B[34m"),
	
	/** Represents the "Purple Text" ANSI code. */
	PURPLE("\u001B[35m"),
	
	/** Represents the "Cyan Text" ANSI code. */
	CYAN("\u001B[36m"),
	
	/** Represents the "White Text" ANSI code. */
	WHITE("\u001B[37m");

	private String code;

	private ANSIColor(String code) {
		this.code = code;
	}
	
	/**
	 * Gets the ANSI code representing this ANSI code wrapper.
	 * @return the ANSI code representing this ANSI code wrapper.
	 */
	public String getCode() {
		return this.code;
	}
}
