package edu.ncsu.awbeggs.chess.piece;

import edu.ncsu.awbeggs.chess.board.Location;

/**
 * Represents an arbitrary Piece, which can be placed on a chessboard.
 * @author Aidan Beggs
 */
public abstract class Piece {
	private Location location;
	
	private PieceColor color;
	
	public Piece(Location location) {
		setLocation(location);
	}
	
	public void setLocation(Location location) {
		if(location == null) {
			throw new IllegalArgumentException();
		}
		this.location = location;
		
		if(!(location.getOccupant() == this)) {
			location.setOccupant(this);
		}
	}
	
	public Location getLocation() {
		return this.location;
	}
	
	public abstract String toString();
}
