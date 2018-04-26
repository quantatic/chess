package edu.ncsu.awbeggs.chess.model.board;

import java.util.Random;

import edu.ncsu.awbeggs.chess.model.piece.King;
import edu.ncsu.awbeggs.chess.model.piece.PieceColor;
import edu.ncsu.awbeggs.chess.model.piece.Queen;

/**
 * Represents a playing board.
 * @author Aidan Beggs
 */
public class Board {
	private Location[][] locations;
	
	/** Height of a playing board. */
	private static final int BOARD_HEIGHT = 8;
	
	/** Width of a playing board. */
	private static final int BOARD_WIDTH = 8;
	
	/**
	 * Creates a new board, and fills it with the correct number of empty locations.
	 */
	public Board() {
		locations = new Location[BOARD_HEIGHT][BOARD_WIDTH];
		
		for(int row = 0; row < BOARD_HEIGHT; row++) {
			for(int col = 0; col < BOARD_WIDTH; col++) {
				locations[row][col] = new Location(row, col);
				PieceColor c = new Random().nextInt(2) == 0 ? PieceColor.WHITE : PieceColor.BLACK;
				if(new Random().nextInt(2) == 1) {
					new King(locations[row][col], c);
				} else {
					new Queen(locations[row][col], c);
				}
				
			}
		}
	}
	
	public int getWidth() {
		return BOARD_WIDTH;
	}
	
	public int getHeight() {
		return BOARD_HEIGHT;
	}
	
	/**
	 * Prints a String representation of a playing board.
	 * @return a String representation of a playing board.
	 */
	public String toString() {
		String horizontal = " ---------------------------------\n";
		String result = horizontal;
		for(int row = 0; row < BOARD_HEIGHT; row++) {
			result += "| ";
			for(int col = 0; col < BOARD_WIDTH; col++) {
				result += locations[row][col].toString();
				result += "  | ";
			}
			result += "\n";
			result += horizontal;
		}
		
		return result;
	}
	
	public Location getLocation(int row, int col) {
		return locations[row][col];
	}
}
