package edu.ncsu.chess.game;

import java.util.List;

public interface Piece {
	
	public Color getColor();
	
	public List<Location> validMoves();
}
