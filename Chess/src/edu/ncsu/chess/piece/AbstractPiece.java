package edu.ncsu.chess.piece;

/**
 * Abstract implementation of some universal methods for pieces.
 * @author Aidan Beggs
 */
public abstract class AbstractPiece implements Piece {
	
	private final PieceColor c;
	
	private boolean hasMoved;
	
	/**
	 * Creates a new abstract piece implementation with a given color.
	 * @param c the color to create this piece with.
	 */
	public AbstractPiece(PieceColor c) {
		this.c = c;
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
}
