package edu.ncsu.chess.piece;

import java.util.List;

import edu.ncsu.chess.game.Board;
import edu.ncsu.chess.game.Location;

public interface Piece {
	
	public PieceColor getColor();
	
	public boolean hasMoved();
	public void setMoved();
	
	public List<Location> validMoves(Board b, int startRow, int startCol);
}
