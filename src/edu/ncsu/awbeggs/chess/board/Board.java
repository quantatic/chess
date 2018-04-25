package edu.ncsu.awbeggs.chess.board;

import edu.ncsu.awbeggs.chess.Configs;

public class Board {
	private Location[][] locations;
	
	public Board() {
		locations = new Location[Configs.BOARD_HEIGHT][Configs.BOARD_WIDTH];
		
		for(int row = 0; row < Configs.BOARD_HEIGHT; row++) {
			for(int col = 0; col < Configs.BOARD_WIDTH; col++) {
				locations[row][col] = new Location(row, col);
			}
		}
	}
	
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
