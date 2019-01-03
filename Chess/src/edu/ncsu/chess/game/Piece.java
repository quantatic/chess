package edu.ncsu.chess.game;

import java.awt.image.BufferedImage;
import java.util.List;

/**
 * Represents a Piece.
 * @author Aidan Beggs
 */
public interface Piece {
	
	/**
	 * Gets the color of this piece.
	 * @return the color of this piece.
	 */
	public PieceColor getColor();
	
	/**
	 * Gets the type of this piece.
	 * @return the type of this piece.
	 */
	public PieceType getType();
	
	/**
	 * Gets whether or not this piece has moved.
	 * @return whether or not this piece has moved.
	 */
	public boolean hasMoved();
	
	/**
	 * Sets this piece to have moved.
	 * @param hasMoved whether this piece has moved.
	 */
	public void setMoved(boolean hasMoved);
	
	/**
	 * Gets a list of all valid moves for this piece, given the board on which it resides,
	 * and its starting row/column.
	 * @param b the board which this piece resides on.
	 * @param l the location at which this piece resides.
	 * @return a list of all valid moves for this piece.
	 */
	public List<Location> validMoves(ChessBoard b, Location l);
	
	/** 
	 * Gets a buffered image representing this piece's sprite. 
	 * @return this piece's sprite.
	 */
	public BufferedImage getSprite();
}
