package edu.ncsu.chess.game;

import java.util.List;

import edu.ncsu.chess.piece.Piece;
import edu.ncsu.chess.piece.PieceColor;

/**
 * Wrapper to manager an entire game of chess.
 * @author Aidan Beggs
 */
public class ChessManager {
	
	private ChessBoard board;
	private PieceColor currentTurn;
	
	/**
	 * Creates a new ChessManager, with a given starting player.
	 * @param startingPlayer the player who will make the first move for the chess game.
	 */
	public ChessManager(PieceColor startingPlayer) {
		this.board = new ChessBoard();
		this.currentTurn = startingPlayer;
	}
	
	/**
	 * Gets the color of the player who is responsible for making the next move.
	 * @return the color of the player who is responsible for making the next move.
	 */
	public PieceColor getCurrentTurn() {
		return this.currentTurn;
	}
	
	/**
	 * Gets a list of all valid moves from a starting location.
	 * @param start the location of the piece to get all valid moves from.
	 * @return a list of all valid moves from the given location.
	 * @throws IllegalArgumentException if the given location has no piece.
	 */
	public List<Location> getMoves(Location start) {
		if(start.isEmpty()) {
			throw new IllegalArgumentException("starting location to get moves from cannot be empty");
		}
		
		return start.getPiece().validMoves(this.board, start.getRow(), start.getCol());
	}
	
	/**
	 * Moves a piece from one location to another, replacing a potential piece in the ending location.
	 * @param startLocation the starting location to move the piece from.
	 * @param endLocation the ending location to move the piece to, potentially with another piece in it.
	 * @throws IllegalArgumentException if the starting location has no piece in it initially.
	 */
	public void movePiece(Location startLocation, Location endLocation) {
		if(startLocation.isEmpty()) {
			throw new IllegalArgumentException("starting location must have piece in it");
		}
		
		Piece movedPiece = startLocation.emptyLocation();
		
		if(endLocation.isEmpty()) {
			endLocation.setPiece(movedPiece);
		} else {
			endLocation.replacePiece(movedPiece);
		}
		
		movedPiece.setMoved();
		this.currentTurn = (this.currentTurn == PieceColor.WHITE) ? PieceColor.BLACK : PieceColor.WHITE;
	}
	
	/**
	 * Gets the location at a given row/column.
	 * @param row the row to get the location at.
	 * @param col the column to get the location at.
	 * @return the location at the given row/column.
	 * @throws IllegalArgumentException if the given row/column are out of bounds.
	 */
	public Location getLocation(int row, int col) {
		if(!board.validLocation(row, col)) {
			throw new IllegalArgumentException("Given row/col out of bounds.");
		}
		
		return board.getLocation(row, col);
	}
}
