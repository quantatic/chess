package edu.ncsu.chess.piece;

/**
 * Abstract implementation of some universal methods for pieces.
 * @author Aidan Beggs
 */
public abstract class AbstractPiece implements Piece {
	
	private final PieceColor c;
	
	/**
	 * Creates a new abstract piece implementation with a given color.
	 * @param c the color to create this piece with.
	 */
	public AbstractPiece(PieceColor c) {
		this.c = c;
	}
	
	@Override
	public PieceColor getColor() {
		return this.c;
	}
}
