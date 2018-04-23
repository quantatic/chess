package edu.ncsu.awbeggs.chess.colors;

public enum PieceColor {
	BLACK(ANSIColor.BLACK),
	WHITE(ANSIColor.WHITE);
	
	private ANSIColor c;
	
	private PieceColor(ANSIColor c) {
		this.c = c;
	}
	
	public String colorize(String in) {
		return c.getCode() + in + ANSIColor.RESET;
	}
}
