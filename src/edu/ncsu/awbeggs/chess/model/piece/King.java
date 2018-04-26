package edu.ncsu.awbeggs.chess.model.piece;

import java.util.HashSet;
import java.util.Set;

import edu.ncsu.awbeggs.chess.model.board.Location;
import edu.ncsu.awbeggs.chess.ui.SpriteLookup;

public class King extends Piece{
	
	public King(Location location, PieceColor color) {
		super(location, color, SpriteLookup.KING);
	}

	@Override
	public Set<Location> getValidMoves() {
		Set<Location> valid = new HashSet<>();
		Location l = getLocation();
		
		for(int rowOffset : new int[] {-1, 0, 1}) {
			for(int colOffset : new int[] {-1, 0, 1}) {
				Location tmpLocation = l.getNeighbor(rowOffset, colOffset);
				if(tmpLocation != null && (tmpLocation.isEmpty() 
						|| tmpLocation.getOccupant().getColor() != getColor())) {
					valid.add(tmpLocation);
				}
			}
		}
		
		return valid;
	}
}
