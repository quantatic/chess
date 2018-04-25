package edu.ncsu.awbeggs.chess.model.board;

import edu.ncsu.awbeggs.chess.model.Configs;

/**
 * Represents a playing board.
 * @author Aidan Beggs
 */
public class Board {
	private Location[][] locations;
	
	/**
	 * Creates a new board, and fills it with the correct number of empty locations.
	 */
	public Board() {
		locations = new Location[Configs.BOARD_HEIGHT][Configs.BOARD_WIDTH];
		
		for(int row = 0; row < Configs.BOARD_HEIGHT; row++) {
			for(int col = 0; col < Configs.BOARD_WIDTH; col++) {
				locations[row][col] = new Location(row, col);
			}
		}
	}
	
	/**
	 * Prints a String representation of a playing board.
	 * @return a String representation of a playing board.
	 */
	public String toString() {
		String horizontal = "---------------------------------\n";
		String result = horizontal;
		for(int row = 0; row < Configs.BOARD_HEIGHT; row++) {
			result += "| ";
			for(int col = 0; col < Configs.BOARD_WIDTH; col++) {
				result += locations[row][col].toString();
				result += " ";
				result += "| ";
			}
			result += "\n";
			result += horizontal;
		}
		
		return result;
	}
	
	public static void main(String[] args) {
		Board b = new Board();
		System.out.println(b);
	}
}
