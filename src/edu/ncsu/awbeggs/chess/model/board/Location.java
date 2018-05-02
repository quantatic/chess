package edu.ncsu.awbeggs.chess.model.board;

import java.util.Observable;

import edu.ncsu.awbeggs.chess.model.piece.Piece;

/**
 * Represents a location on the {@link Board} which may have a {@link Piece} at it.
 * @author Aidan Beggs
 */
public class Location extends Observable {
	private Piece occupant;
	
	/** The row of this Location on the {@link Board}. */
	private int row;
	
	/** The column of this Location on the {@link Board}. */
	private int col;
	
	/**
	 * Full constructor for a Location, with a row and a column.
	 * @param row the row of this Location.
	 * @param col the column of this Location.
	 */
	public Location(int row, int col) {
		this.row = row;
		this.col = col;
	}
	
	/**
	 * Sets a new {@link Piece} to reside at this Location, and removes the old {@link Piece}
	 * that used to reside at this Location.
	 * @param piece the new {@link Piece} to reside at this Location.
	 * @return the old {@link Piece} that used to reside at this Location.
	 */
	public Piece setOccupant(Piece piece) {
		Piece oldOccupant = getOccupant();
		this.occupant = piece;
		
		if(occupant != null && occupant.getLocation() != this) {
			occupant.setLocation(this);
		}
		
		return oldOccupant;
	}
	
	/**
	 * Gets whether this Location is empty.
	 * @return true if this Location is empty.
	 */
	public boolean isEmpty() {
		return this.occupant == null;
	}
	
	/**
	 * Gets the row of this Location.
	 * @return the row of this Location.
	 */
	public int getRow() {
		return this.row;
	}
	
	/**
	 * Gets the column of this Location.
	 * @return the column of this Location.
	 */
	public int getCol() {
		return this.col;
	}
	
	/**
	 * Gets the {@link Piece} that occupies this Location.
	 * @return the {@link Piece} that occupies this Location.
	 */
	public Piece getOccupant() {
		return this.occupant;
	}
	
	/**
	 * Gets a String representation of this Location, including the {@link Piece} that occupies
	 * this Location, if any, and the row and column of this Location.
	 * @return a String representation of this Location.
	 */
	public String toString() {
		return "" + ((char)('a' + getCol() - 1)) + (getRow());
	}
	
}
