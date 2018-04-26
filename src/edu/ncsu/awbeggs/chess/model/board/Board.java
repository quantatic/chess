package edu.ncsu.awbeggs.chess.model.board;

import edu.ncsu.awbeggs.chess.model.piece.Bishop;
import edu.ncsu.awbeggs.chess.model.piece.King;
import edu.ncsu.awbeggs.chess.model.piece.Knight;
import edu.ncsu.awbeggs.chess.model.piece.Pawn;
import edu.ncsu.awbeggs.chess.model.piece.PieceColor;
import edu.ncsu.awbeggs.chess.model.piece.Queen;
import edu.ncsu.awbeggs.chess.model.piece.Rook;

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
	
	private Location selectedSpace;
	
	private PieceColor currentTurn;
	
	/**
	 * Creates a new board, and fills it with the correct number of empty locations.
	 */
	public Board() {
		locations = new Location[BOARD_HEIGHT][BOARD_WIDTH];
		
		for(int row = 0; row < BOARD_HEIGHT; row++) {
			for(int col = 0; col < BOARD_WIDTH; col++) {
				locations[row][col] = new Location(row + 1, col + 1, this);
			}
		}
		for(int col = 1; col <= BOARD_WIDTH; col++) {
			new Pawn(getLocation(2, col), PieceColor.WHITE);
			new Pawn(getLocation(7, col), PieceColor.BLACK);
		}
		
		new Rook(getLocation(1, 1), PieceColor.WHITE);
		new Knight(getLocation(1, 2), PieceColor.WHITE);
		new Bishop(getLocation(1, 3), PieceColor.WHITE);
		new Queen(getLocation(1, 4), PieceColor.WHITE);
		new King(getLocation(1, 5), PieceColor.WHITE);
		new Bishop(getLocation(1, 6), PieceColor.WHITE);
		new Knight(getLocation(1, 7), PieceColor.WHITE);
		new Rook(getLocation(1, 8), PieceColor.WHITE);
		
		new Rook(getLocation(8, 1), PieceColor.BLACK);
		new Knight(getLocation(8, 2), PieceColor.BLACK);
		new Bishop(getLocation(8, 3), PieceColor.BLACK);
		new Queen(getLocation(8, 4), PieceColor.BLACK);
		new King(getLocation(8, 5), PieceColor.BLACK);
		new Bishop(getLocation(8, 6), PieceColor.BLACK);
		new Knight(getLocation(8, 7), PieceColor.BLACK);
		new Rook(getLocation(8, 8), PieceColor.BLACK);
		
		currentTurn = PieceColor.WHITE;
	}
	
	public Location getSelected() {
		return selectedSpace;
	}
	
	public void updateSelected(Location selected) {
		if(this.selectedSpace != null && this.selectedSpace.getOccupant() != null
				&& this.selectedSpace.getOccupant().moveTo(selected)) {
			this.selectedSpace = null;
			this.currentTurn = (this.currentTurn == PieceColor.WHITE) ? PieceColor.BLACK : PieceColor.WHITE;
		} else if(this.selectedSpace != selected 
				&& this.selectedSpace == null && selected.getOccupant() != null &&
				selected.getOccupant().getColor() == currentTurn) {
			this.selectedSpace = selected;
		} else {
			this.selectedSpace = null;
		}
	}
	
	public Location getLocation(int row, int col) {
		if(row > 0 && row <= BOARD_HEIGHT && col > 0 && col <= BOARD_WIDTH) {
			return locations[row - 1][col - 1];
		}
		
		return null;
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
		for(int row = 1; row <= BOARD_HEIGHT; row++) {
			result += "| ";
			for(int col = 1; col <= BOARD_WIDTH; col++) {
				result += getLocation(9 - row, col).toString();
				result += "  | ";
			}
			result += "\n";
			result += horizontal;
		}
		
		return result;
	}
}
