package edu.ncsu.awbeggs.chess.model.board;

import edu.ncsu.awbeggs.chess.model.piece.Piece;

/**
 * Represents a {@link Move} that a {@link Piece} can make from one {@link Location} to another
 * on a {@link Board}.
 * @author Aidan Beggs
 */
public class Move {
	private Location start;
	
	private Location end;
	
	private Piece moved;
	
	/**
	 * Creates a new {@link Move} Object, with a starting location, an ending location, and the
	 * {@link Piece} that was moved.
	 * @param start the starting {@link Location} of the move.
	 * @param end the ending {@link Location} of the move.
	 * @param moved the {@link Piece} that was moved during this move.
	 */
	public Move(Location start, Location end, Piece moved) {
		this.start = start;
		this.end = end;
		this.moved = moved;
	}

	/**
	 * Gets the starting {@link Location} of this move.
	 * @return the start of this move.
	 */
	public Location getStart() {
		return start;
	}

	/**
	 * Gets the ending {@link Location} of this move.
	 * @return the end of this move.
	 */
	public Location getEnd() {
		return end;
	}

	/**
	 * Gets the {@link Piece} moved during this move.
	 * @return the moved piece.
	 */
	public Piece getMoved() {
		return moved;
	}
	
	
}
