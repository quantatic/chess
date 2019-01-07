package edu.ncsu.chess.game;

/**
 * Represents a Location on a chess board, which may or may not have a Piece.
 * @author Aidan Beggs
 */
public class Location {
	
	private final int x, y;
	
	private Piece piece;
	
	/**
	 * Creates a new location with a given x and y.
	 * @param x the x coordinate to set for this location.
	 * @param  y the y coordinate to set for this location.
	 */
	public Location(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Gets the x coordinate of this location.
	 * @return the x coordinate of this location.
	 */
	public int getX() {
		return this.x;
	}
	
	/**
	 * Gets the y coordinate of this location.
	 * @return the y coordinate of this location.
	 */
	public int getY() {
		return this.y;
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
	 * @throws IllegalStateException if there was no piece at the location this method was called on.
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
	
	@Override
	public String toString() {
		return "Location at (" + this.x + ", " + this.y + ") with piece: " + this.piece;
	}
}