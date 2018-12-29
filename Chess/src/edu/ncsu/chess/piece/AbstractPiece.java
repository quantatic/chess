package edu.ncsu.chess.piece;

import java.awt.image.BufferedImage;

import edu.ncsu.chess.ui.SpriteManager;

/**
 * Abstract implementation of some universal methods for pieces.
 * @author Aidan Beggs
 */
public abstract class AbstractPiece implements Piece {
	
	private final PieceColor c;
	
	private final String spritePath;
	
	private boolean hasMoved;
	
	/**
	 * Creates a new abstract piece implementation with a given color.
	 * @param c the color to create this piece with.
	 * @param name the name of this piece, used to construct the path.
	 */
	public AbstractPiece(PieceColor c, String name) {
		this.c = c;
		this.spritePath = "sprites/pieces/" + c.name().toLowerCase() + "/" + name + ".png";
		this.hasMoved = false;
	}
	
	@Override
	public PieceColor getColor() {
		return this.c;
	}
	
	@Override
	public boolean hasMoved() {
		return this.hasMoved;
	}
	
	@Override
	public void setMoved() {
		this.hasMoved = true;
	}
	
	@Override
	public String toString() {
		throw new UnsupportedOperationException("Please implement toString() for" + this.getClass());
	}
	
	@Override
	public BufferedImage getSprite() {
		return SpriteManager.lookupSprite(this.spritePath);
	}
}
