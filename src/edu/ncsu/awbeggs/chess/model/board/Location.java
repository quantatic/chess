package edu.ncsu.awbeggs.chess.model.board;

import edu.ncsu.awbeggs.chess.model.piece.Piece;

/**
 * Represents a location on the {@link Board} which may have a {@link Piece} at it.
 * @author Aidan Beggs
 */
public class Location {
	private Piece occupant;
	
	/** The row of this Location on the {@link Board}. */
	private int row;
	
	/** The column of this Location on the {@link Board}. */
	private int col;
	
	public Location(int row, int col) {
		this.row = row;
		this.col = col;
	}
	
	public void setOccupant(Piece piece) {
		if(piece == null) {
			throw new IllegalArgumentException();
		}
		
		this.occupant = piece;
		
		if(!(occupant.getLocation() == this)) {
			occupant.setLocation(this);
		}
	}
	
	public Piece getOccupant() {
		return this.occupant;
	}
	
	public String toString() {
		String result = "";
		if(occupant != null) {
			result += occupant.toString();
		} else {
			result += " ";
		}
		
		return result;
	}
}
