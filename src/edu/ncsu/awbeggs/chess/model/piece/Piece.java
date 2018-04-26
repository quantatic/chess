package edu.ncsu.awbeggs.chess.model.piece;

import java.awt.image.BufferedImage;
import java.util.HashSet;
import java.util.Map;
import java.util.Observer;
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
			moveTo(l);
			return true;
		}
		
		return false;
	}
	
	private Piece moveTo(Location l) {
		Piece oldOccupant = l.removeOccupant();
		getLocation().removeOccupant();
		setLocation(l);
		return oldOccupant;
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
	
	protected abstract Set<Location> getValidMovesNoCheck();
	
	public Set<Location> getValidMoves() {
		Set<Location> noCheckValid = getValidMovesNoCheck();
		Set<Location> valid = new HashSet<>();
		for(Location tmp : noCheckValid) {
			valid.add(tmp);
			Location oldLocation = getLocation();
			Piece oldPiece = moveTo(tmp);
			King k = board.getKing(getColor());
			Set<Piece> otherPieces = board.getPieces((getColor() == PieceColor.WHITE) ? PieceColor.BLACK : PieceColor.WHITE);
			for(Piece p : otherPieces) {
				if(p.getValidMovesNoCheck().contains(k.getLocation())) {
					valid.remove(tmp);
					break;
				}
			}
			moveTo(oldLocation);
			
			if(oldPiece != null) {
				oldPiece.setLocation(tmp);
			} else {
				tmp.removeOccupant();
			}
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
}
