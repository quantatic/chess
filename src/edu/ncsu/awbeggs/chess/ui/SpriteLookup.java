package edu.ncsu.awbeggs.chess.ui;

import java.awt.image.BufferedImage;

import edu.ncsu.awbeggs.chess.model.piece.PieceColor;

public class SpriteLookup {
	
	public static final SpriteLookup KING = new SpriteLookup("king");
	
	public static final SpriteLookup QUEEN = new SpriteLookup("queen");
	
	public static final SpriteLookup ROOK = new SpriteLookup("rook");
	
	public static final SpriteLookup KNIGHT = new SpriteLookup("knight");
	
	public static final SpriteLookup BISHOP = new SpriteLookup("bishop");
	
	public static final SpriteLookup PAWN = new SpriteLookup("pawn");
	
	private BufferedImage black;
	
	private BufferedImage white;
	
	private SpriteLookup(String name) {
		black = SpriteIO.readSpriteFromPath(name, PieceColor.BLACK);
		white = SpriteIO.readSpriteFromPath(name, PieceColor.WHITE);
	}
	
	public BufferedImage lookup(PieceColor c) {
		switch(c) {
		case WHITE:
			return white;
		case BLACK:
			return black;
		default:
			throw new IllegalArgumentException();
		}
	}
}
