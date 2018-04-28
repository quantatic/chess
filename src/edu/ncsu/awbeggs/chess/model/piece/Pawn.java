package edu.ncsu.awbeggs.chess.model.piece;

import java.util.HashSet;
import java.util.Set;

import edu.ncsu.awbeggs.chess.model.board.Board;
import edu.ncsu.awbeggs.chess.model.board.Location;
import edu.ncsu.awbeggs.chess.ui.SpriteLookup;

public class Pawn extends Piece{
	public Pawn(Location location, PieceColor color, Board board) {
		super(location, color, SpriteLookup.PAWN, board);
	}

	@Override
	public Set<Location> getValidMovesNoCheck() {
		Set<Location> valid = new HashSet<>();
		int direction = getColor() == PieceColor.WHITE ? 1 : -1;
		
		Location checkLocation = getBoard().getLocation(getRow() + direction, getCol());
		if(checkLocation != null && checkLocation.isEmpty()) {
			valid.add(checkLocation);
			checkLocation = getBoard().getLocation(getRow() + (direction * 2), getCol());
			if(checkLocation != null 
					&& (direction == 1 ? (getRow() == 2) : (getRow() == 7))
					&& checkLocation.isEmpty()) {
				valid.add(checkLocation);
			}
		}
		
		for(int colOffset : new int[] {-1, 1}) {
			checkLocation = getBoard().getLocation(getRow() + direction, getCol() + colOffset);
			if(checkLocation != null && checkLocation.getOccupant() != null 
					&& checkLocation.getOccupant().getColor() != getColor()) {
				valid.add(checkLocation);
			}
		}
		return valid;
	}

}
