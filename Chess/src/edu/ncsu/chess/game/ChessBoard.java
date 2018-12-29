package edu.ncsu.chess.game;

import edu.ncsu.chess.piece.Bishop;
import edu.ncsu.chess.piece.King;
import edu.ncsu.chess.piece.Knight;
import edu.ncsu.chess.piece.Pawn;
import edu.ncsu.chess.piece.PieceColor;
import edu.ncsu.chess.piece.Queen;
import edu.ncsu.chess.piece.Rook;

/**
 * Represents a chess board.
 * @author Aidan Beggs
 */
public class ChessBoard {
	
	/** The width of a chess board. */
	public static final int WIDTH = 8;

	/** The height of a chess board. */
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
		
		for(int col = 1; col <= WIDTH; col++) {
			getLocation(2, col).setPiece(new Pawn(PieceColor.WHITE));
			getLocation(7, col).setPiece(new Pawn(PieceColor.BLACK));
		}
		
		for(int col : new int[] {1, 8}) {
			getLocation(1, col).setPiece(new Rook(PieceColor.WHITE));
			getLocation(8, col).setPiece(new Rook(PieceColor.BLACK));
		}
		
		for(int col : new int[] {2, 7}) {
			getLocation(1, col).setPiece(new Knight(PieceColor.WHITE));
			getLocation(8, col).setPiece(new Knight(PieceColor.BLACK));
		}
		
		for(int col : new int[] {3, 6}) {
			getLocation(1, col).setPiece(new Bishop(PieceColor.WHITE));
			getLocation(8, col).setPiece(new Bishop(PieceColor.BLACK));
		}
		
		getLocation(1, 4).setPiece(new Queen(PieceColor.WHITE));
		getLocation(8, 4).setPiece(new Queen(PieceColor.BLACK));
		
		getLocation(1, 5).setPiece(new King(PieceColor.WHITE));
		getLocation(8, 5).setPiece(new King(PieceColor.BLACK));
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
		
		return this.board[row - 1][col - 1];
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