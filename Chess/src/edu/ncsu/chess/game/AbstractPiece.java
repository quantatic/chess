package edu.ncsu.chess.game;

import java.awt.image.BufferedImage;

import edu.ncsu.chess.ui.SpriteManager;

/**
 * Abstract implementation of some universal methods for pieces.
 * @author Aidan Beggs
 */
public abstract class AbstractPiece implements Piece {
	
	private final PieceColor color;
	private final PieceType type;
	
	private boolean hasMoved;
	
	/**
	 * Creates a new abstract piece implementation with a given color.
	 * @param color the color to create this piece with.
	 * @param type the type of this piece.
	 */
	public AbstractPiece(PieceColor color, PieceType type) {
		this.color = color;
		this.type = type;
		this.hasMoved = false;
	}
	
	@Override
	public PieceColor getColor() {
		return this.color;
	}
	
	@Override
	public PieceType getType() {
		return this.type;
	}
	
	@Override
	public boolean hasMoved() {
		return this.hasMoved;
	}
	
	@Override
	public void setMoved(boolean hasMoved) {
		this.hasMoved = hasMoved;
	}
	
	@Override
	public BufferedImage getSprite() {
		return SpriteManager.lookupSprite("sprites/pieces/" 
											+ getColor().name().toLowerCase() 
											+ "/" 
											+ getType().name().toLowerCase() 
											+ ".png");
	}
}
