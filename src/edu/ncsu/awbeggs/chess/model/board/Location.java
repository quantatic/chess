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
	
	public void setOccupant(Piece piece) {
		
		this.occupant = piece;
		
		if(!(occupant.getLocation() == this)) {
			occupant.setLocation(this);
		}
	}
	
	public void removeOccupant() {
		this.occupant = null;
	}
	
	public Location getNeighbor(int rowOffset, int colOffset) {
		return board.getLocation(row + rowOffset, col + colOffset);
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
			result += "";
		}
		
		return result;
	}
	
}
