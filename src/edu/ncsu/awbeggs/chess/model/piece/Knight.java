package edu.ncsu.awbeggs.chess.model.piece;

import java.util.HashSet;
import java.util.Set;

import edu.ncsu.awbeggs.chess.model.board.Board;
import edu.ncsu.awbeggs.chess.model.board.Location;
import edu.ncsu.awbeggs.chess.ui.SpriteLookup;

public class Knight extends Piece{
	public Knight(Location location, PieceColor color, Board board) {
		super(location, color, SpriteLookup.KNIGHT, board);
	}

	@Override
	public Set<Location> getValidMovesNoCheck() {
		Set<Location> validMoves = new HashSet<Location>();
		
		for(int rowMod : new int[] {-1, 1}) {
			for(int colMod : new int[] {-1, 1}) {
				for(int row : new int[] {1, 2}) {
					Location checkLocation = getBoard().getLocation(getRow() + (row * rowMod),
							getCol() + ((3 - row) * colMod));
					
					if(checkLocation != null 
							&& (checkLocation.isEmpty() 
									|| (checkLocation.getOccupant().getColor() != getColor()))) {
						validMoves.add(checkLocation);
					}
				}
			}
		}
		
		return validMoves;
	}
}
