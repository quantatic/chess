package edu.ncsu.awbeggs.chess.model.piece;

import java.awt.image.BufferedImage;
import java.util.Map;
import java.util.Observer;
import java.util.Set;

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
	
	public Piece(Location location, PieceColor color, SpriteLookup lookup) {
		setLocation(location);
		this.color = color;
		this.lookup = lookup;
	}
	
	public boolean moveTo(Location l) {
		Set<Location> valid = getValidMoves();
		if(valid.contains(l)) {
			Location oldLocation = getLocation();
			setLocation(l);
			oldLocation.removeOccupant();
			return true;
		}
		
		return false;
	}
	
	public void setLocation(Location location) {
		if(location == null) {
			throw new IllegalArgumentException();
		}
		this.location = location;
		
		if(!(location.getOccupant() == this)) {
			location.setOccupant(this);
		}
	}
	
	public abstract Set<Location> getValidMoves();
	
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
}
