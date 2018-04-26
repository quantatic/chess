package edu.ncsu.awbeggs.chess.model.piece;

import java.awt.image.BufferedImage;

import edu.ncsu.awbeggs.chess.model.board.Location;

/**
 * Represents an arbitrary Piece, which can be placed on a chessboard.
 * @author Aidan Beggs
 */
public abstract class Piece {

	private Location location;
	
	private PieceColor color;
	
	public Piece(Location location, PieceColor color) {
		setLocation(location);
		this.color = color;
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
	
	public PieceColor getColor() {
		return this.color;
	}
	
	public abstract String toString();
	
	
	public abstract BufferedImage getRepresentation();
}
