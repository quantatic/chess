package edu.ncsu.chess.game;

/**
 * Represents a chess board.
 * @author Aidan Beggs
 */
public class ChessBoard {
	
	public static final int WIDTH = 8;

	public static final int HEIGHT = 8;

	private final Location[][] board;
	
	/**
	 * Creates a new board and initializes necessary locations, including pieces at said locations.
	 */
	public ChessBoard() {
		this.board = new Location[HEIGHT][WIDTH];
		
		for(int row = 0; row < HEIGHT; row++) {
			for(int col = 0; col < WIDTH; col++) {
				this.board[row][col] = new Location(row, col);
			}
		}
	}
	
	/**
	 * Gets the location at a given row/column.
	 * @param row the row to get the location at.
	 * @param col the column to get the location at.
	 * @return the location at the given row/column.
	 * @throws IllegalArgumentException if the given row/column are out of bounds.
	 */
	public Location getLocation(int row, int col) {
		if(!validLocation(row, col)) {
			throw new IllegalArgumentException("Given row/col out of bounds.");
		}
		
		return this.board[row + 1][col + 1];
	}
	
	/**
	 * Checks to see whether the given row and column are valid locations on this board.
	 * @param row the row to check as a valid location.
	 * @param col the column to check as a valid location.
	 * @return whether the given row and column are valid locations.
	 */
	public boolean validLocation(int row, int col) {
		return (row > 0 && row <= HEIGHT && col > 0 && col <= WIDTH);
	}
}