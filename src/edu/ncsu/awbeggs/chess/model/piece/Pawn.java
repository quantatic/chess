package edu.ncsu.awbeggs.chess.model.piece;

import java.util.HashSet;
import java.util.Observable;
import java.util.Set;

import edu.ncsu.awbeggs.chess.model.board.Location;
import edu.ncsu.awbeggs.chess.ui.SpriteLookup;

public class Pawn extends Piece{
	public Pawn(Location location, PieceColor color) {
		super(location, color, SpriteLookup.PAWN);
	}

	@Override
	public Set<Location> getValidMoves() {
		Set<Location> valid = new HashSet<>();
		int direction = getColor() == PieceColor.WHITE ? 1 : -1;
		Location l = getLocation();
		
		Location tmpNeighbor = l.getNeighbor(direction, 0);
		if(tmpNeighbor != null && tmpNeighbor.isEmpty()) {
			valid.add(tmpNeighbor);
			tmpNeighbor = l.getNeighbor(direction * 2, 0);
			if(tmpNeighbor != null 
					&& (direction == 1 ? (getLocation().getRow() == 2) : (getLocation().getRow() == 7))
					&& tmpNeighbor.isEmpty()) {
				valid.add(tmpNeighbor);
			}
		}
		
		for(int colOffset : new int[] {-1, 1}) {
			tmpNeighbor = l.getNeighbor(direction, colOffset);
			if(tmpNeighbor != null && tmpNeighbor.getOccupant() != null 
					&& tmpNeighbor.getOccupant().getColor() != getColor()) {
				valid.add(tmpNeighbor);
			}
		}
		return valid;
	}

}
