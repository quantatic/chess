package edu.ncsu.chess.game;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;

import edu.ncsu.chess.ui.SpriteManager;

/**
 * Represents a Piece.
 * @author Aidan Beggs
 */
public class Piece {
	
	private final PieceType type;
	private final PieceColor color;
	private boolean moved;
	
	/**
	 * Creates a new Piece with the given type and color.
	 * @param type the type of the new piece.
	 * @param color the color of the new piece.
	 */
	public Piece(PieceType type, PieceColor color) {
		this.type = type;
		this.color = color;
		this.moved = false;
	}
	/**
	 * Gets the color of this piece.
	 * @return the color of this piece.
	 */
	public PieceColor getColor() {
		return this.color;
	}
	
	/**
	 * Gets the type of this piece.
	 * @return the type of this piece.
	 */
	public PieceType getType() {
		return this.type;
	}
	
	/**
	 * Gets whether or not this piece has moved.
	 * @return whether or not this piece has moved.
	 */
	public boolean hasMoved() {
		return this.moved;
	}
	
	/**
	 * Sets this piece to have moved.
	 * @param moved whether this piece has moved.
	 */
	public void setMoved(boolean moved) {
		this.moved = moved;
	}
	
	/** 
	 * Gets a buffered image representing this piece's sprite. 
	 * @return this piece's sprite.
	 */
	public BufferedImage getSprite() {
		String spritePath = "sprites/pieces/" + color.name().toLowerCase() + "/" + type.name().toLowerCase() + ".png";
		
		return SpriteManager.lookupSprite(spritePath);
	}
}
