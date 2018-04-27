package edu.ncsu.awbeggs.chess.model.piece;

import java.awt.image.BufferedImage;
import java.util.HashSet;
import java.util.Set;

import edu.ncsu.awbeggs.chess.model.board.Board;
import edu.ncsu.awbeggs.chess.model.board.Location;
import edu.ncsu.awbeggs.chess.ui.SpriteLookup;

/**
 * Represents an arbitrary Piece, which can be placed on a chessboard.
 * @author Aidan Beggs
 */
public abstract class Piece{

	private Location location;
	
	private PieceColor color;
	
	private SpriteLookup lookup;
	
	private Board board;
	
	public Piece(Location location, PieceColor color, SpriteLookup lookup, Board board) {
		setLocation(location);
		this.color = color;
		this.lookup = lookup;
		this.board = board;
	}
	
	public boolean attemptMove(Location l) {
		Set<Location> valid = getValidMoves();
		
		if(valid.contains(l)) {
			setLocation(l);
			return true;
		}
		
		return false;
	}
	
	/**
	 * Updates the {@link Location} of a Piece, removes the old Piece at the new {@link Location}.
	 * @param location the new {@link Location} of this Piece.
	 * @return the {@link Piece} that used to reside at the new Location.
	 */
	public Piece setLocation(Location location) {
		Piece oldPiece = null;
		
		if(this.location != null) {
			this.location.setOccupant(null);
		}
		
		this.location = location;
		
		if(location != null) {
			oldPiece = location.getOccupant();
			if(location.getOccupant() != this) {
				 location.setOccupant(this);
			}
		}
		
		return oldPiece;
	}
	
	protected abstract Set<Location> getValidMovesNoCheck();
	
	public Set<Location> getValidMoves() {
		Set<Location> noCheckValid = getValidMovesNoCheck();
		Set<Location> valid = new HashSet<>();
		King k = board.getKing(getColor());
		if(k == null) {
			return valid;
		}
		for(Location tmp : noCheckValid) {
			valid.add(tmp);
			Location oldLocation = getLocation();
			Piece oldPiece = setLocation(tmp);
			if(k.isInCheck()) {
				valid.remove(tmp);
			}
			
			setLocation(oldLocation);
			
			tmp.setOccupant(oldPiece);
		}
		
		return valid;
	}
	
	public Location getLocation() {
		return this.location;
	}
	
	public PieceColor getColor() {
		return this.color;
	}
	
	public String toString() {
		return lookup.lookupSpriteString(getColor());
	}
	
	public BufferedImage getRepresentation() {
		return lookup.lookupSpriteImage(getColor());
	}
	
	protected Board getBoard() {
		return this.board;
	}
}
