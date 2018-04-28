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
public abstract class Piece {

	/** The {@link Location} of this Piece. */
	private Location location;
	
	/** The {@link PieceColor} of this Piece. */
	private PieceColor color;
	
	/** A {@link SpriteLookup used to look up representations of this Piece. */
	private SpriteLookup lookup;
	
	/** The {@link Board} that this Piece inhabits. */
	private Board board;
	
	/** Full constructor for Piece. Sets {@link Location}, {@link PieceColor}, 
	 * {@link SpriteLookup}, and {@link Board} for this Piece.
	 * @param location the {@link Location} of this Piece.
	 * @param color the {@link PieceColor} of this Piece.
	 * @param lookup the {@link SpriteLookup} for this Piece.
	 * @param board the {@link Board} this Piece inhabits.
	 */
	public Piece(Location location, PieceColor color, SpriteLookup lookup, Board board) {
		setLocation(location);
		setColor(color);
		setSpriteLookup(lookup);
		setBoard(board);
	}
	
	/**
	 * Attempts to move this Piece to another {@link Location}.
	 * @param l the new {@link Location} to move to.
	 * @return true if a move to the passed {@link Location} is valid and the move was completed
	 * successfully.
	 */
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
	
	/**
	 * Gets a {@link Set} of any {@link Location} that is a valid move for this piece, disregarding check.
	 * @return a {@link Set} of any valid {@link Location} that is a valid move, disregarding check.
	 */
	protected abstract Set<Location> getValidMovesNoCheck();
	
	/**
	 * Gets a {@link Set} of any {@link Location} that is a valid move for this piece.
	 * @return a {@link Set} of any valid {@link Location} that is a valid move.
	 */
	public Set<Location> getValidMoves() {
		getValidMovesNoCheck();
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
	
	/**
	 * Gets the {@link Location} of this Piece.
 	 * @return the {@link Location} of this Piece.
	 */
	public Location getLocation() {
		return this.location;
	}
	
	/**
	 * Gets the row of the {@link Board} this Piece is on.
	 * @return the row of the {@link Board} this Piece is on.
	 */
	public int getRow() {
		return getLocation().getRow();
	}
	
	/**
	 * Gets the column of the {@link Board} this Piece is on.
	 * @return the column of the {@link Board} this Piece is on.
	 */
	public int getCol() {
		return getLocation().getCol();
	}
	
	/**
	 * Gets the {@link PieceColor} representing the color and side of this Piece.
	 * @return the {@link PieceColor} representing the color and side of this Piece.
	 */
	public PieceColor getColor() {
		return this.color;
	}
	
	/**
	 * Gets a String representation of this Piece.
	 * @return a String representation of this Piece.
	 */
	public String toString() {
		return lookup.lookupSpriteString(getColor());
	}
	
	/**
	 * Gets a {@link BufferedImage} representation of this Piece, that has been properly scaled
	 * to the size of the Board.
	 * @return a {@link BufferedImage} representing this Piece.
	 */
	public BufferedImage getRepresentation() {
		return lookup.lookupSpriteImage(getColor());
	}
	
	/**
	 * Gets the {@link Board} that this Piece inhabits.
	 * @return the {@link Board} that this Piece inhabits.
	 */
	protected Board getBoard() {
		return this.board;
	}
	
	private void setColor(PieceColor c) {
		this.color = c;
	}
	
	private void setSpriteLookup(SpriteLookup l) {
		this.lookup = l;
	}
	
	private void setBoard(Board b) {
		this.board = b;
	}
}
