package edu.ncsu.chess.game;

public class Location {
	
	private final int row, col;
	
	private Piece piece;
	
	/**
	 * Creates a new Location with a given x and y.
	 * @param x the x to set for this location.
	 * @param y the y to set for this location.
	 */
	public Location(int row, int col) {
		this.row = row;
		this.col = col;
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
	 * Sets the Piece at this Location.
	 * @param piece the Piece to set at this location, cannot be null.
	 * @throws IllegalArgumentException if the given Piece is null.
	 */
	public void setPiece(Piece piece) {
		if(piece == null) {
			throw new IllegalArgumentException("Cannot set location to null piece");
		}
		
		this.piece = piece;
	}
	
	/**
	 * Replaces the Piece at this Location with the given Piece.
	 * @param piece the Piece to set at this Location, cannot be null.
	 * @return the old Piece at this Location.
	 * @throws IllegalArgumentException if the given Piece is null.
	 * @throws IllegalStateException if this Location had no Piece at it to begin with.
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
	 * Gets the Piece at this Location.
	 * @return the Piece at this Location, throwing an exception if there is no Piece at this location.
	 * @throws IllegalStateException if there is not Piece at this Location.
	 */
	public Piece getPiece() {
		if(this.piece == null) {
			throw new IllegalStateException("Cannot get piece at empty location");
		}
		
		return this.piece;
	}
	
	/**
	 * Empties this location, returning the removed piece.
	 * @return the Piece that used to be at this Location.
	 * @throws IllegalStateException if there was no Piece at this Location when this method was called.
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
	 * Checks whether this Location is empty.
	 * @return whether this Location is empty.
	 */
	public boolean isEmpty() {
		return this.piece == null;
	}
}
