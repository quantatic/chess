package edu.ncsu.awbeggs.chess.model.board;

import java.util.Observable;

import edu.ncsu.awbeggs.chess.model.piece.Piece;

/**
 * Represents a location on the {@link Board} which may have a {@link Piece} at it.
 * @author Aidan Beggs
 */
public class Location extends Observable {
	private Piece occupant;
	
	private Board board;
	
	/** The row of this Location on the {@link Board}. */
	private int row;
	
	/** The column of this Location on the {@link Board}. */
	private int col;
	
	public Location(int row, int col, Board board) {
		this.row = row;
		this.col = col;
		this.board = board;
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
	
	public boolean isEmpty() {
		return this.occupant == null;
	}
	
	public int getRow() {
		return this.row;
	}
	
	public int getCol() {
		return this.col;
	}
	
	public Piece getOccupant() {
		return this.occupant;
	}
	
	public String toString() {
		String result = "";
		if(occupant != null) {
			result += occupant.toString();
		} else {
			result += "Location";
		}
		
		result += " at row " + getRow() + ", col " + getCol();
		
		return result;
	}
	
}
