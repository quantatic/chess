package edu.ncsu.awbeggs.chess.model.board;

import edu.ncsu.awbeggs.chess.model.piece.King;
import edu.ncsu.awbeggs.chess.model.piece.Pawn;
import edu.ncsu.awbeggs.chess.model.piece.Piece;
import edu.ncsu.awbeggs.chess.model.piece.PieceColor;

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
	
	private boolean wasKingSideCastle;
	
	private boolean wasQueenSideCastle;
	
	private boolean isPromotion;
	
	/**
	 * Creates a new {@link Move} Object, with a starting location, an ending location, and the
	 * {@link Piece} that was moved.
	 * @param start the starting {@link Location} of the move.
	 * @param end the ending {@link Location} of the move.
	 */
	public Move(Location start, Location end) {
		this.start = start;
		this.end = end;
		this.moved = start.getOccupant();
		this.captured = end.getOccupant();
		
		if(moved instanceof King && start.getCol() == 5) {
			if(end.getCol() == 7) {
				wasKingSideCastle = true;
			} else if(end.getCol() == 3) {
				wasQueenSideCastle = true;
			}
		}
		
		this.isPromotion = (end.getRow() == 8 && moved.getColor() == PieceColor.WHITE)
				||  (end.getRow() == 1 && moved.getColor() == PieceColor.BLACK);
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
	
	public void undoMove() {
		if(wasKingSideCastle || wasQueenSideCastle) {
			((King)getMoved()).undoCastle(wasKingSideCastle);
		} else {
		
			getStart().setOccupant(getMoved());
			
			if(getCaptured() != null) {
				captured.getLocation().setOccupant(captured);
			}
			
			getMoved().decrementMovesMade();
		}
		
	}
	
	public boolean attemptMove() {
		if(getMoved() != null && getMoved().isValidMove(this)) {
			if(wasKingSideCastle || wasQueenSideCastle) {
				((King)getMoved()).doCastle(wasKingSideCastle);
			} else {
				getMoved().setLocation(getEnd());
				getMoved().incrementMovesMade();
			}
			
			return true;
		}
		
		return false;
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

	/**
	 * @return the wasKingSideCastle
	 */
	public boolean wasKingSideCastle() {
		return wasKingSideCastle;
	}

	/**
	 * @return the wasQueenSideCastle
	 */
	public boolean wasQueenSideCastle() {
		return wasQueenSideCastle;
	}

	/**
	 * @return
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((captured == null) ? 0 : captured.hashCode());
		result = prime * result + ((end == null) ? 0 : end.hashCode());
		result = prime * result + (isPromotion ? 1231 : 1237);
		result = prime * result + ((moved == null) ? 0 : moved.hashCode());
		result = prime * result + ((start == null) ? 0 : start.hashCode());
		result = prime * result + (wasKingSideCastle ? 1231 : 1237);
		result = prime * result + (wasQueenSideCastle ? 1231 : 1237);
		return result;
	}

	/**
	 * @param obj
	 * @return
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Move other = (Move) obj;
		if (captured == null) {
			if (other.captured != null) {
				return false;
			}
		} else if (!captured.equals(other.captured)) {
			return false;
		}
		if (end == null) {
			if (other.end != null) {
				return false;
			}
		} else if (!end.equals(other.end)) {
			return false;
		}
		if (isPromotion != other.isPromotion) {
			return false;
		}
		if (moved == null) {
			if (other.moved != null) {
				return false;
			}
		} else if (!moved.equals(other.moved)) {
			return false;
		}
		if (start == null) {
			if (other.start != null) {
				return false;
			}
		} else if (!start.equals(other.start)) {
			return false;
		}
		if (wasKingSideCastle != other.wasKingSideCastle) {
			return false;
		}
		if (wasQueenSideCastle != other.wasQueenSideCastle) {
			return false;
		}
		return true;
	}
	
}
