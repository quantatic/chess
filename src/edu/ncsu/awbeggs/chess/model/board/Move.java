package edu.ncsu.awbeggs.chess.model.board;

import edu.ncsu.awbeggs.chess.model.piece.Pawn;
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
	
	private Piece captured;
	
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
		this.captured = end.getOccupant();
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
	
	/**
	 * Gets the {@link Piece} captured during this move, if any.
	 * @return the captured piece.
	 */
	public Piece getCaptured() {
		return captured;
	}

	/**
	 * Gets a String representation of this {@link Move}.
	 * @return a String representation of this {@link Move}.
	 */
	@Override
	public String toString() {
		String movedString = (moved instanceof Pawn) ? "" : moved.toString();
		String captureString = (captured == null) ? "" : "x";
		return movedString + captureString + end;
	}
}
