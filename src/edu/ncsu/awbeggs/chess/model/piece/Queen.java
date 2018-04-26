package edu.ncsu.awbeggs.chess.model.piece;

import java.awt.image.BufferedImage;
import java.util.HashSet;
import java.util.Observable;
import java.util.Set;

import edu.ncsu.awbeggs.chess.model.board.Location;
import edu.ncsu.awbeggs.chess.ui.SpriteLookup;

public class Queen extends Piece {

	public Queen(Location location, PieceColor color) {
		super(location, color, SpriteLookup.QUEEN);
	}

	public Set<Location> getValidMoves() {
		Set<Location> validMoves = new HashSet<>();
		Location l = getLocation();
		
		for(int rowMod : new int[] {-1, 0, 1}) {
			for(int colMod : new int[] {-1, 0, 1}) {
				int tmpOffsetRow = rowMod;
				int tmpOffsetCol = colMod;
				Location tmpLocation = l.getNeighbor(tmpOffsetRow, tmpOffsetCol);
				while(tmpLocation != null && tmpLocation.isEmpty()) {
					validMoves.add(tmpLocation);
					tmpOffsetRow += rowMod;
					tmpOffsetCol += colMod;
					tmpLocation = l.getNeighbor(tmpOffsetRow, tmpOffsetCol);
				}
				
				if(tmpLocation != null && tmpLocation.getOccupant().getColor() != getColor()) {
					validMoves.add(tmpLocation);
				}
			}
		}
		
		return validMoves;
	}


}
