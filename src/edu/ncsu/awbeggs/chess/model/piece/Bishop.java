package edu.ncsu.awbeggs.chess.model.piece;

import java.util.HashSet;
import java.util.Set;

import edu.ncsu.awbeggs.chess.model.board.Board;
import edu.ncsu.awbeggs.chess.model.board.Location;
import edu.ncsu.awbeggs.chess.ui.SpriteLookup;

/**
 * Represents a Bishop, which extends {@links Piece}.
 * @author Aidan Beggs
 */
public class Bishop extends Piece{

	/** Full constructor for Bishop. Sets {@link Location}, {@link PieceColor}, 
	 * {@link SpriteLookup}, and {@link Board} for this Bishop.
	 * @param location the {@link Location} of this Bishop.
	 * @param color the {@link PieceColor} of this Bishop.
	 * @param board the {@link Board} this Bishop inhabits.
	 */
	public Bishop(Location location, PieceColor color, Board board) {
		super(location, color, SpriteLookup.BISHOP, board);
	}

	@Override
	public Set<Location> getValidMovesNoCheck() {
		Set<Location> validMoves = new HashSet<>();
		Location l = getLocation();
		
		for(int rowMod : new int[] {-1, 1}) {
			for(int colMod : new int[] {-1, 1}) {
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
