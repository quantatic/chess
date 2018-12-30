package edu.ncsu.chess.game;

public class Move {
	private final Location start;
	private final Location end;
	private final Piece moved;
	private final Piece taken;
	
	public Move(Location start, Location end, Piece moved, Piece taken) {
		this.start = start;
		this.end = end;
		this.moved = moved;
		this.taken = taken;
	}

	/**
	 * Gets the start.
	 * @return the start.
	 */
	public Location getStart() {
		return start;
	}

	/**
	 * Gets the end.
	 * @return the end.
	 */
	public Location getEnd() {
		return end;
	}

	/**
	 * Gets the moved.
	 * @return the moved.
	 */
	public Piece getMoved() {
		return moved;
	}

	/**
	 * Gets the taken.
	 * @return the taken.
	 */
	public Piece getTaken() {
		return taken;
	}
	
	
}
