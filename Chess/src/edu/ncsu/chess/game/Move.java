package edu.ncsu.chess.game;

/**
 * Represents a single move in a chess game.
 * @author Aidan Beggs
 */
public class Move {
	private final Location start;
	private final Location end;
	private final Piece moved;
	private final Piece taken;
	private final boolean hadMoved; //whether the moved piece had moved before this move.
	
	/**
	 * Creates a new move from a given start location, end location, keeps track of the piece moved,
	 * and the potential piece taken.
	 * @param start the starting location for this move.
	 * @param end the ending location for this move.
	 */
	public Move(Location start, Location end) {
		this.start = start;
		this.end = end;
		if(start.isEmpty()) {
			throw new IllegalStateException("starting location cannot be empty");
		}
		
		this.moved = start.getPiece();
		this.hadMoved = this.moved.hasMoved();
		
		if(end.isEmpty()) {
			this.taken = null;
		} else {
			this.taken = end.getPiece();
		}
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
	 * Gets whether or not there was any piece taken during this move.
	 * @return whether there was any piece taken during this move.
	 */
	public boolean pieceWasTaken() {
		return this.taken != null;
	}

	/**
	 * Gets the taken.
	 * @return the taken.
	 */
	public Piece getTaken() {
		if(this.taken == null) {
			throw new IllegalStateException("no piece taken for this move");
		}
		
		return taken;
	}
	
	/**
	 * Whether the moved piece had moved before this move.
	 * @return whether the moved piece had moved before this move.
	 */
	public boolean getHadMoved() {
		return this.hadMoved;
	}
	
	@Override
	public String toString() {
		return "move with " + moved.getType() + " from " + start.toString() + " to " + end.toString();
	}
}