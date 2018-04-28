package edu.ncsu.awbeggs.chess.ui;

import java.awt.image.BufferedImage;

import edu.ncsu.awbeggs.chess.model.piece.Bishop;
import edu.ncsu.awbeggs.chess.model.piece.King;
import edu.ncsu.awbeggs.chess.model.piece.Knight;
import edu.ncsu.awbeggs.chess.model.piece.Pawn;
import edu.ncsu.awbeggs.chess.model.piece.Piece;
import edu.ncsu.awbeggs.chess.model.piece.PieceColor;
import edu.ncsu.awbeggs.chess.model.piece.Queen;
import edu.ncsu.awbeggs.chess.model.piece.Rook;

/**
 * Lookup class that stores and maintains ASCII and {@link BufferedImage} representations of 
 * various {@link Piece} objects.
 * @author Aidan Beggs
 */
public class SpriteLookup {
	
	/** A SpriteLookup object for a {@link King}. */
	public static final SpriteLookup KING = new SpriteLookup("king", "♔", "♚");
	
	/** A SpriteLookup object for a {@link Queen}. */
	public static final SpriteLookup QUEEN = new SpriteLookup("queen", "♕", "♛");
	
	/** A SpriteLookup object for a {@link Rook}. */
	public static final SpriteLookup ROOK = new SpriteLookup("rook", "♖", "♜");
	
	/** A SpriteLookup object for a {@link Knight}. */
	public static final SpriteLookup KNIGHT = new SpriteLookup("knight", "♘", "♞");
	
	/** A SpriteLookup object for a {@link Bishop}. */
	public static final SpriteLookup BISHOP = new SpriteLookup("bishop", "♗", "♝");
	
	/** A SpriteLookup object for a {@link Pawn}. */
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
	
	
	/**
	 * Looks up the ASCII representation of the {@link Piece} associated with this 
	 * {@link SpriteLookup} object, as a String.
	 * @param c the {@link PieceColor} of the {@link Piece} to look up.
	 * @return an ASCII representation of the {@link Piece} associated with this
	 * {@link SpriteLookup} object, of the passed {@link PieceColor}.
	 */
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
	
	/**
	 * Looks up the {@link BufferedImage} representation of the {@link Piece} 
	 * associated with this {@link SpriteLookup} object, as a String.
	 * @param c the {@link PieceColor} of the {@link Piece} to look up.
	 * @return an ASCII representation of the {@link Piece} associated with this
	 * {@link SpriteLookup} object, of the passed {@link PieceColor}. */
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
