package edu.ncsu.awbeggs.chess.board;

import edu.ncsu.awbeggs.chess.piece.Piece;

public class Location {
	private Piece occupant;
	
	private int row;
	
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
		if(occupant != null) {
			return occupant.toString();
		}
		
		return " ";
	}
}
