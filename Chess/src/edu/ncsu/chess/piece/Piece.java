package edu.ncsu.chess.piece;

import java.util.List;

import edu.ncsu.chess.game.ChessBoard;
import edu.ncsu.chess.game.Location;

public interface Piece {
	
	public PieceColor getColor();
	
	public boolean hasMoved();
	public void setMoved();
	
	public List<Location> validMoves(ChessBoard b, int startRow, int startCol);
}
