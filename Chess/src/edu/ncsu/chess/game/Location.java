package edu.ncsu.chess.game;

import edu.ncsu.chess.piece.Piece;

/**
 * Represents a Location on a chess board, which may or may not have a Piece.
 * @author Aidan Beggs
 */
public class Location {
	
	private final int row, col;
	
	private Piece piece;
	
	/**
	 * Creates a new location with a given x and y.
	 * @param row the row to set for this location.
	 * @param  col the column to set for this location.
	 */
	public Location(int row, int col) {
		this.row = row;
		this.col = col;
	}
	
	/**
	 * Gets the row of this location.
	 * @return the row of this location.
	 */
	public int getRow() {
		return this.row;
	}
	
	/**
	 * Gets the column of this location.
	 * @return the column of this location.
	 */
	public int getCol() {
		return this.col;
	}
	
	/**
	 * Sets the piece at this location.
	 * @param piece the piece to set at this location, cannot be null.
	 * @throws IllegalArgumentException if the given piece is null.
	 */
	public void setPiece(Piece piece) {
		if(piece == null) {
			throw new IllegalArgumentException("Cannot set location to null piece");
		}
		
		this.piece = piece;
	}
	
	/**
	 * Replaces the piece at this location with the given piece.
	 * @param piece the piece to set at this location, cannot be null.
	 * @return the old piece at this location.
	 * @throws IllegalArgumentException if the given piece is null.
	 * @throws IllegalStateException if this location had no piece at it to begin with.
	 */
	public Piece replacePiece(Piece piece) {
		if(piece == null) {
			throw new IllegalArgumentException("Cannot set location to null piece");
		}
		
		if(this.piece == null) {
			throw new IllegalStateException("Cannot replace Piece at empty Location");
		}
		
		Piece oldPiece = this.piece;
		this.piece = piece;
		return oldPiece;
	}
	
	/**
	 * Gets the piece at this location.
	 * @return the piece at this Location, throwing an exception if there is no piece at this location.
	 * @throws IllegalStateException if there is not piece at this location.
	 */
	public Piece getPiece() {
		if(this.piece == null) {
			throw new IllegalStateException("Cannot get piece at empty location");
		}
		
		return this.piece;
	}
	
	/**
	 * Empties this location, returning the removed piece.
	 * @return the piece that used to be at this location.
	 * @throws IllegalStateException if there was no piece at this location when this method was called.
	 */
	public Piece emptyLocation() {
		if(this.piece == null) {
			throw new IllegalStateException("Cannot empty an already empty location");
		}
		
		Piece oldPiece = this.piece;
		this.piece = null;
		return oldPiece;
	}
	
	/**
	 * Checks whether this location is empty.
	 * @return whether this location is empty.
	 */
	public boolean isEmpty() {
		return this.piece == null;
	}
}
