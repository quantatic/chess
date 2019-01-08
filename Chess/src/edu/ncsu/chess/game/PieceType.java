package edu.ncsu.chess.game;

/**
 * Represents the different types of chess pieces.
 * @author Aidan Beggs
 */
public enum PieceType {
	
	/** Represents a pawn chess piece. */
	PAWN(1),
	
	/** Represents a rook chess piece. */
	ROOK(5),
	
	/** Represents a knight chess piece. */
	KNIGHT(3),
	
	/** Represents a bishop chess piece. */
	BISHOP(3),
	
	/** Represents a queen chess piece. */
	QUEEN(8),
	
	/** Represents a king chess piece. */
	KING(Integer.MAX_VALUE);
	
	private int value;
	
	private PieceType(int value) {
		this.value = value;
	}
	
	/**
	 * Gets the value of this kind of piece.
	 * @return the value of this kind of piece.
	 */
	public int getValue() {
		return this.value;
	}
}
