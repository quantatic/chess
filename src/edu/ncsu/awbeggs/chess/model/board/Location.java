package edu.ncsu.awbeggs.chess.model.board;

import edu.ncsu.awbeggs.chess.model.piece.Piece;

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
		String result = "";
		if(occupant != null) {
			result += occupant.toString();
		} else {
			result += " ";
		}
		
		return result;
	}
}
