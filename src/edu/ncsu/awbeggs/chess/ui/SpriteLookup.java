package edu.ncsu.awbeggs.chess.ui;

import java.awt.image.BufferedImage;

import edu.ncsu.awbeggs.chess.model.piece.PieceColor;

public class SpriteLookup {
	
	public static final SpriteLookup KING = new SpriteLookup("king", "♔", "♚");
	
	public static final SpriteLookup QUEEN = new SpriteLookup("queen", "♕", "♛");
	
	public static final SpriteLookup ROOK = new SpriteLookup("rook", "♖", "♜");
	
	public static final SpriteLookup KNIGHT = new SpriteLookup("knight", "♘", "♞");
	
	public static final SpriteLookup BISHOP = new SpriteLookup("bishop", "♗", "♝");
	
	public static final SpriteLookup PAWN = new SpriteLookup("pawn", "♙", "♟");
	
	private BufferedImage blackImageSprite;
	
	private BufferedImage whiteImageSprite;
	
	private String whiteStringSprite;
	
	private String blackStringSprite;
	
	private SpriteLookup(String name, String whiteStringSprite, String blackStringSprite) {
		if(name == null || whiteStringSprite == null || blackStringSprite == null
				|| whiteStringSprite.isEmpty() || blackStringSprite.isEmpty()) {
			throw new IllegalArgumentException();
		}
		
		blackImageSprite = SpriteIO.readSpriteFromPath(name, PieceColor.BLACK);
		whiteImageSprite = SpriteIO.readSpriteFromPath(name, PieceColor.WHITE);
		this.whiteStringSprite = whiteStringSprite;
		this.blackStringSprite = blackStringSprite;
	}
	
	public String lookupSpriteString(PieceColor c) {
		switch(c) {
		case WHITE:
			return whiteStringSprite;
		case BLACK:
			return blackStringSprite;
		default:
			throw new IllegalArgumentException();
		}
	}
	
	public BufferedImage lookupSpriteImage(PieceColor c) {
		switch(c) {
		case WHITE:
			return whiteImageSprite;
		case BLACK:
			return blackImageSprite;
		default:
			throw new IllegalArgumentException();
		}
	}
}
