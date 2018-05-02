package edu.ncsu.awbeggs.chess.model.board;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

import edu.ncsu.awbeggs.chess.model.piece.Bishop;
import edu.ncsu.awbeggs.chess.model.piece.King;
import edu.ncsu.awbeggs.chess.model.piece.Knight;
import edu.ncsu.awbeggs.chess.model.piece.Pawn;
import edu.ncsu.awbeggs.chess.model.piece.Piece;
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
	
	private Stack<Move> moves;
	
	/**
	 * Creates a new board, and fills it with the correct number of empty locations.
	 */
	public Board() {
		locations = new Location[BOARD_HEIGHT][BOARD_WIDTH];
		moves = new Stack<>();
		
		for(int row = 0; row < BOARD_HEIGHT; row++) {
			for(int col = 0; col < BOARD_WIDTH; col++) {
				locations[row][col] = new Location(row + 1, col + 1);
			}
		}
		for(int col = 1; col <= BOARD_WIDTH; col++) {
			new Pawn(getLocation(2, col), PieceColor.WHITE, this);
			new Pawn(getLocation(7, col), PieceColor.BLACK, this);
		}
		
		new Rook(getLocation(1, 1), PieceColor.WHITE, this);
		new Knight(getLocation(1, 2), PieceColor.WHITE, this);
		new Bishop(getLocation(1, 3), PieceColor.WHITE, this);
		new Queen(getLocation(1, 4), PieceColor.WHITE, this);
		new King(getLocation(1, 5), PieceColor.WHITE, this);
		new Bishop(getLocation(1, 6), PieceColor.WHITE, this);
		new Knight(getLocation(1, 7), PieceColor.WHITE, this);
		new Rook(getLocation(1, 8), PieceColor.WHITE, this);
		
		new Rook(getLocation(8, 1), PieceColor.BLACK, this);
		new Knight(getLocation(8, 2), PieceColor.BLACK, this);
		new Bishop(getLocation(8, 3), PieceColor.BLACK, this);
		new Queen(getLocation(8, 4), PieceColor.BLACK, this);
		new King(getLocation(8, 5), PieceColor.BLACK, this);
		new Bishop(getLocation(8, 6), PieceColor.BLACK, this);
		new Knight(getLocation(8, 7), PieceColor.BLACK, this);
		new Rook(getLocation(8, 8), PieceColor.BLACK, this);
		
		currentTurn = PieceColor.WHITE;
	}
	
	/**
	 * Gets the {@link Location} of the selected {@link Piece}.
	 * @return the {@link Location} of the selected {@link Piece}.
	 */
	public Location getSelected() {
		return selectedSpace;
	}
	
	/**
	 * Attempts to make a move on this {@link Board} from the starting {@link Location} to
	 * the ending {@link Location}.
	 * @param start the starting {@link Location} for this {@link Move}.
	 * @param end the ending {@link Location} for this {@link Move}.
	 * @return true if making this {@link Move} in the current state of the {@link Board}
	 * is legal, and the {@link Move} was made successfully.
	 */
	public boolean attemptMove(Location start, Location end) {
		if(start == null || start.getOccupant() == null) {
			return false;
		}
		
		Piece toMove = start.getOccupant();
		if(toMove.attemptMove(end)) {
			moves.push(new Move(start, end, toMove));
			toMove.incrementMovesMade();
			return true;
		}
		
		return false;
	}
	
	/**
	 * Updates the selected {@link Location} of this Board, performing necessary actions if
	 * a {@link Piece} has been attempted to be moved.
	 * @param selected the new {@link Location} that has been attempted to be selected.
	 */
	public void updateSelected(Location selected) {
		if(this.selectedSpace != null && this.selectedSpace.getOccupant() != null
				&& this.selectedSpace.getOccupant().attemptMove(selected)) {
			this.selectedSpace = null;
			this.currentTurn = (this.currentTurn == PieceColor.WHITE) ? PieceColor.BLACK : PieceColor.WHITE;
		} else if(this.selectedSpace != selected 
				&& this.selectedSpace == null && selected.getOccupant() != null 
				&& selected.getOccupant().getColor() == currentTurn 
				&& selected.getOccupant().getValidMoves().size() > 0) {
			this.selectedSpace = selected;
		} else {
			this.selectedSpace = null;
		}
	}
	
	/**
	 * Gets the {@link King} of this Board of a given {@link PieceColor}.
	 * @param c the {@link PieceColor} of the {@link PieceColor to look for}.
	 * @return the found {@link King} of the passed {@link PieceColor}, or null if no 
	 * {@link King} of the given {@link PieceColor} is found.
	 */
	public King getKing(PieceColor c){
		for(int row = 1; row <= BOARD_HEIGHT; row++) {
			for(int col = 1; col <= BOARD_WIDTH; col++) {
				Location tmpLocation = getLocation(row, col);
				if(tmpLocation != null && (tmpLocation.getOccupant() instanceof King)
						&& (tmpLocation.getOccupant().getColor() == c)) {
					return (King) tmpLocation.getOccupant();
				}
			}
		}
		
		return null;
	}
	
	/**
	 * Gets a {@link Set} of any {@link Piece} matching a given {@link PieceColor}.
	 * @param c the {@link PieceColor} to look for any {@link Piece} matching.
	 * @return a {@link Set} of any {@link Piece} matching a given {@link PieceColor}.
	 */
	public Set<Piece> getPieces(PieceColor c){
		Set<Piece> pieces = new HashSet<>();
		for(int row = 1; row <= BOARD_HEIGHT; row++) {
			for(int col = 1; col <= BOARD_WIDTH; col++) {
				Location tmpLocation = getLocation(row, col);
				if(tmpLocation != null && tmpLocation.getOccupant() != null 
						&& tmpLocation.getOccupant().getColor() == c) {
					pieces.add(tmpLocation.getOccupant());
				}
			}
		}
		
		return pieces;
	}
	
	/**
	 * Checks if the {@link King} of a given {@link PieceColor} on this {@link Board} is in 
	 * checkmate.
	 * @param c the {@link PieceColor} to check if the given {@link King} is in checkmate.
	 * @return true if the {@link King} matching the given {@link PieceColor} on this Board
	 * is in checkmate.
	 */
	public boolean isInCheckmate(PieceColor c) {
		Set<Piece> pieces = getPieces(c);
		
		for(Piece p : pieces) {
			if(!p.getValidMoves().isEmpty()) {
				return false;
			}
		}
		
		return true;
	}
	
	/**
	 * Gets the {@link Location} at this board at a given row and column.
	 * @param row the row to look for a {@link Location} at.
	 * @param col the column to look for a {@link Location} at.
	 * @return the {@link Location} found at the given row and column if found, otherwise null.
	 */
	public Location getLocation(int row, int col) {
		if(row > 0 && row <= BOARD_HEIGHT && col > 0 && col <= BOARD_WIDTH) {
			return locations[row - 1][col - 1];
		}
		
		return null;
	}
	
	/**
	 * Gets the width of this board.
	 * @return the width of this board.
	 */
	public int getWidth() {
		return BOARD_WIDTH;
	}
	
	/**
	 * Gets the height of this Board.
	 * @return the height of this Board.
	 */
	public int getHeight() {
		return BOARD_HEIGHT;
	}
	
	/**
	 * Gets the {@link PieceColor} associated with the player who's current turn it is.
	 * @return the {@link PieceColor} associated with the player who's current turn it is.
	 */
	public PieceColor getCurrentTurn() {
		return this.currentTurn;
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
