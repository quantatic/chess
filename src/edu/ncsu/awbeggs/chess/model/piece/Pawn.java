package edu.ncsu.awbeggs.chess.model.piece;

import java.util.HashSet;
import java.util.Set;

import edu.ncsu.awbeggs.chess.model.board.Board;
import edu.ncsu.awbeggs.chess.model.board.Location;
import edu.ncsu.awbeggs.chess.model.board.Move;
import edu.ncsu.awbeggs.chess.ui.SpriteLookup;

/**
 * Represents a {@link Pawn} piece which may inhabit a {@link Board}.
 * @author Aidan Beggs
 */
public class Pawn extends Piece {
	
	private boolean canBeTakenViaEnPassant;
	
	/** Full constructor for Pawn. Sets {@link Location}, {@link PieceColor}, 
	 * {@link SpriteLookup}, and {@link Board} for this Pawn.
	 * @param location the {@link Location} of this Pawn.
	 * @param color the {@link PieceColor} of this Pawn.
	 */
	public Pawn(Location location, PieceColor color) {
		super(location, color, SpriteLookup.PAWN);
	}

	@Override
	public Set<Move> getValidMovesNoCheck() {
		Set<Move> valid = new HashSet<>();
		int direction = getColor() == PieceColor.WHITE ? 1 : -1;
		
		Location checkLocation = getBoard().getLocation(getRow() + direction, getCol());
		if(checkLocation != null && checkLocation.isEmpty()) {
			valid.add(getMoveTo(checkLocation));
			checkLocation = getBoard().getLocation(getRow() + (direction * 2), getCol());
			if(checkLocation != null 
					&& (direction == 1 ? (getRow() == 2) : (getRow() == 7))
					&& checkLocation.isEmpty()) {
				valid.add(getMoveTo(checkLocation));
			}
		}
		
		for(int colOffset : new int[] {-1, 1}) {
			checkLocation = getBoard().getLocation(getRow() + direction, getCol() + colOffset);
			if(checkLocation != null && checkLocation.getOccupant() != null 
					&& checkLocation.getOccupant().getColor() != getColor()) {
				valid.add(getMoveTo(checkLocation));
			}
		}
		return valid;
	}
	
	
}
