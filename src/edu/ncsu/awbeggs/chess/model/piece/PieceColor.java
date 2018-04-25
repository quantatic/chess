package edu.ncsu.awbeggs.chess.model.piece;

import edu.ncsu.awbeggs.chess.model.color.ANSIColor;

public enum PieceColor {
	
	BLACK(ANSIColor.BLACK),
	
	WHITE(ANSIColor.WHITE);
	
	private String code;
	
	private PieceColor(String code) {
		this.code = code;
	}
	
	public String getCode() {
		return this.code;
	}
}
