package edu.ncsu.chess.piece;

import java.util.ArrayList;
import java.util.List;

import edu.ncsu.chess.game.Board;
import edu.ncsu.chess.game.Location;

public class Pawn extends AbstractPiece{
	
	public Pawn(PieceColor c) {
		super(c);
	}

	@Override
	public List<Location> validMoves(Board b, int startRow, int startCol) {
		ArrayList<Location> validMoves = new ArrayList<>();
		
	}
}
