package edu.ncsu.chess.game;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Represents a chess board.
 * 
 * @author Aidan Beggs
 */
public class ChessBoard {

	/** The width of a chess board. */
	public static final int WIDTH = 8;

	/** The height of a chess board. */
	public static final int HEIGHT = 8;

	private final Location[][] board;
	
	private final Stack<Move> moves;

	/**
	 * Creates a new board and initializes necessary locations, including pieces at
	 * said locations.
	 */
	public ChessBoard() {
		this.board = new Location[HEIGHT][WIDTH];
		this.moves = new Stack<>();

		for (int y = 1; y <= HEIGHT; y++) {
			for (int x = 1; x <= WIDTH; x++) {
				this.board[y - 1][x - 1] = new Location(x, y);
			}
		}

		for (int x = 1; x <= WIDTH; x++) {
			getLocation(x, 2).setPiece(new Piece(PieceType.PAWN, PieceColor.WHITE));
			getLocation(x, 7).setPiece(new Piece(PieceType.PAWN, PieceColor.BLACK));
		}

		for (int x : new int[] { 1, 8 }) {
			getLocation(x, 1).setPiece(new Piece(PieceType.ROOK, PieceColor.WHITE));
			getLocation(x, 8).setPiece(new Piece(PieceType.ROOK, PieceColor.BLACK));
		}

		for (int x : new int[] { 2, 7 }) {
			getLocation(x, 1).setPiece(new Piece(PieceType.KNIGHT, PieceColor.WHITE));
			getLocation(x, 8).setPiece(new Piece(PieceType.KNIGHT, PieceColor.BLACK));
		}

		for (int x : new int[] { 3, 6 }) {
			getLocation(x, 1).setPiece(new Piece(PieceType.BISHOP, PieceColor.WHITE));
			getLocation(x, 8).setPiece(new Piece(PieceType.BISHOP, PieceColor.BLACK));
		}

		getLocation(4, 1).setPiece(new Piece(PieceType.QUEEN, PieceColor.WHITE));
		getLocation(4, 8).setPiece(new Piece(PieceType.QUEEN, PieceColor.BLACK));


		getLocation(5, 1).setPiece(new Piece(PieceType.KING, PieceColor.WHITE));
		getLocation(5, 8).setPiece(new Piece(PieceType.KING, PieceColor.BLACK));
	}

	/**
	 * Gets the location at a given row/column.
	 * 
	 * @param x the row to get the location at.
	 * @param y the column to get the location at.
	 * @return the location at the given row/column.
	 * @throws IllegalArgumentException if the given row/column are out of bounds.
	 */
	public Location getLocation(int x, int y) {
		if (!validLocation(x, y)) {
			throw new IllegalArgumentException("Given row/col out of bounds.");
		}

		return this.board[y - 1][x - 1];
	}

	/**
	 * Checks to see whether the given row and column are valid locations on this
	 * board.
	 * 
	 * @param x the row to check as a valid location.
	 * @param y the column to check as a valid location.
	 * @return whether the given row and column are valid locations.
	 */
	public boolean validLocation(int x, int y) {
		return (x > 0 && x <= WIDTH && y > 0 && y <= HEIGHT);
	}

	/**
	 * Makes the given move on the board.
	 * 
	 * @param m the move to make.
	 * @throws IllegalArgumentException if the given move is not valid for the current board state.
	 */
	public void makeMove(Move m) {
		if (m.getStart().isEmpty() || m.getStart().getPiece() != m.getMoved()) {
			throw new IllegalArgumentException();
		}

		m.getStart().emptyLocation();
		if (m.getEnd().isEmpty()) {
			m.getEnd().setPiece(m.getMoved());
		} else {
			m.getEnd().replacePiece(m.getMoved());
		}

		m.getMoved().setMoved(true);
		
		moves.push(m);
	}
	
	/**
	 * Undoes the last move performed upon this board. Performing a move on this board and then calling
	 * this function is exactly the same as if the move had never been performed in the first place.
	 */
	public void undoLastMove() {
		if(moves.isEmpty()) {
			throw new IllegalStateException();
		}
		
		Move lastMove = moves.pop();
		
		
		if(lastMove.pieceWasTaken()) {
			lastMove.getEnd().replacePiece(lastMove.getTaken());
		} else {
			lastMove.getEnd().emptyLocation();
		}
		
		lastMove.getStart().setPiece(lastMove.getMoved());
		lastMove.getMoved().setMoved(lastMove.getHadMoved());
	}

	/**
	 * Gets all pieces on this board.
	 * 
	 * @return all pieces on this board.
	 */
	public List<Piece> getPieces() {
		List<Piece> result = new ArrayList<>();

		for (int row = 1; row <= HEIGHT; row++) {
			for (int col = 1; col <= WIDTH; col++) {
				Location loc = getLocation(row, col);
				if (!loc.isEmpty()) {
					result.add(loc.getPiece());
				}
			}
		}

		return result;
	}

	/**
	 * Gets all valid moves starting at the given location.
	 * 
	 * @param start the location to find all valid moves starting from.
	 * @return a list of all valid moves that start from the given starting location.
	 */
	public List<Move> getMoves(Location start) {
		List<Move> naiveMoves = getNaiveMoves(start);
		
		List<Move> validMoves = new ArrayList<>();
		
		for(Move m : naiveMoves) {
			makeMove(m);
			boolean validMove = true;
			for(int y = 1; y <= HEIGHT; y++) {
				for(int x = 1; x <= WIDTH; x++) {
					Location loc = getLocation(x, y);
					if(!loc.isEmpty() && loc.getPiece().getType() == PieceType.KING
							&& loc.getPiece().getColor() == m.getMoved().getColor()
							&& isInCheck(loc)) {
						validMove = false;
					}
				}
			}
			
			if(validMove) {
				validMoves.add(m);
			}
			
			undoLastMove();
		}
		
		return validMoves;
	}
	
	/**
	 * Checks to see whether a given location is in check. A location is in check if 
	 * the piece at that location is in direct danger of being taken by a piece of the other color.
	 * 
	 * @param l the location to test for check at.
	 * @return whether the piece at the given location is in check.
	 * @throws IllegalArgumentException if the given location is empty.
	 */
	public boolean isInCheck(Location l) {
		if(l.isEmpty()) {
			throw new IllegalArgumentException();
		}
		
		for(int y = 1; y <= HEIGHT; y++) {
			for(int x = 1; x <= WIDTH; x++) {
				Location thisLoc = getLocation(x, y);
				if(!thisLoc.isEmpty() && thisLoc.getPiece().getColor() != l.getPiece().getColor()) {
					List<Move> tmpMoves = getNaiveMoves(thisLoc);
					for(Move m : tmpMoves) {
						if(m.getEnd() == l) {
							return true;
						}
					}
				}
			}
		}
		
		return false;
	}

	private List<Move> getNaiveMoves(Location start) {
		switch (start.getPiece().getType()) {
		case PAWN:
			return pawnMoveHelper(start);
		case ROOK:
			return rookMoveHelper(start);
		case KNIGHT:
			return knightMoveHelper(start);
		case BISHOP:
			return bishopMoveHelper(start);
		case QUEEN:
			return queenMoveHelper(start);
		case KING:
			return kingMoveHelper(start);
		default:
			throw new IllegalStateException("unimplemented piece type " + start.getPiece().getType());
		}
	}

	private List<Move> pawnMoveHelper(Location start) {

		List<Move> result = new ArrayList<>();

		int startX = start.getX();
		int startY = start.getY();
		int directionY = start.getPiece().getColor() == PieceColor.WHITE ? 1 : -1;

		if (validLocation(startX, startY + directionY) && getLocation(startX, startY + directionY).isEmpty()) {
			result.add(new Move(start, getLocation(startX, startY + directionY)));

			if (!start.getPiece().hasMoved() && validLocation(startX, startY + directionY * 2)
					&& getLocation(startX, startY + directionY * 2).isEmpty()) {
				result.add(new Move(start, getLocation(startX, startY + directionY * 2)));
			}
		}

		for (int directionX : new int[] { -1, 1 }) {
			if (validLocation(startX + directionX, startY + directionY)
					&& !getLocation(startX + directionX, startY + directionY).isEmpty()
					&& getLocation(startX + directionX, startY + directionY).getPiece().getColor() != start.getPiece()
							.getColor()) {
				result.add(new Move(start, getLocation(startX + directionX, startY + directionY)));
			}
		}

		return result;
	}

	private List<Move> rookMoveHelper(Location start) {

		List<Move> result = new ArrayList<>();

		for (int directionX : new int[] { -1, 0, 1 }) {
			for (int directionY : new int[] { -1, 0, 1 }) {
				if (Math.abs(directionX) + Math.abs(directionY) == 1) {
					int currentX = start.getX() + directionX;
					int currentY = start.getY() + directionY;

					while (validLocation(currentX, currentY) && getLocation(currentX, currentY).isEmpty()) {
						result.add(new Move(start, getLocation(currentX, currentY)));
						currentX += directionX;
						currentY += directionY;
					}

					if (validLocation(currentX, currentY)
							&& getLocation(currentX, currentY).getPiece().getColor() != start.getPiece().getColor()) {
						result.add(new Move(start, getLocation(currentX, currentY)));
					}
				}
			}
		}

		return result;
	}
	
	private List<Move> knightMoveHelper(Location start) {
		List<Move> result = new ArrayList<>();
		
		int startX = start.getX();
		int startY = start.getY();
		
		for(int directionX : new int[] {-2, -1, 1, 2}) {
			for(int directionY : new int[] {-2, -1, 1, 2}) {
				if(Math.abs(directionX) != Math.abs(directionY)) {
					if(validLocation(startX + directionX, startY + directionY)) {
						Location loc = getLocation(startX + directionX, startY + directionY);
						if(loc.isEmpty() || loc.getPiece().getColor() != start.getPiece().getColor()) {
							result.add(new Move(start, loc));
						}
					}
				}
			}
		}
		
		return result;
	}

	private List<Move> bishopMoveHelper(Location start) {

		List<Move> result = new ArrayList<>();

		for (int directionX : new int[] { -1, 1 }) {
			for (int directionY : new int[] { -1, 1 }) {
				int currentX = start.getX() + directionX;
				int currentY = start.getY() + directionY;

				while (validLocation(currentX, currentY) && getLocation(currentX, currentY).isEmpty()) {
					result.add(new Move(start, getLocation(currentX, currentY)));
					currentX += directionX;
					currentY += directionY;
				}

				if (validLocation(currentX, currentY)
						&& getLocation(currentX, currentY).getPiece().getColor() != start.getPiece().getColor()) {
					result.add(new Move(start, getLocation(currentX, currentY)));
				}
			}
		}

		return result;
	}

	private List<Move> queenMoveHelper(Location start) {

		List<Move> result = new ArrayList<>();

		for (int directionX : new int[] { -1, 0, 1 }) {
			for (int directionY : new int[] { -1, 0, 1 }) {
				if (!(directionX == 0 && directionY == 0)) {
					int currentX = start.getX() + directionX;
					int currentY = start.getY() + directionY;

					while (validLocation(currentX, currentY) && getLocation(currentX, currentY).isEmpty()) {
						result.add(new Move(start, getLocation(currentX, currentY)));
						currentX += directionX;
						currentY += directionY;
					}

					if (validLocation(currentX, currentY)
							&& getLocation(currentX, currentY).getPiece().getColor() != start.getPiece().getColor()) {
						result.add(new Move(start, getLocation(currentX, currentY)));
					}
				}
			}
		}

		return result;
	}

	private List<Move> kingMoveHelper(Location start) {

		List<Move> result = new ArrayList<>();

		int startX = start.getX();
		int startY = start.getY();

		for (int directionX : new int[] { -1, 0, 1 }) {
			for (int directionY : new int[] { -1, 0, 1 }) {
				if (!(directionX == 0 && directionY == 0)) {
					if (validLocation(startX + directionX, startY + directionY)) {
						Location loc = getLocation(startX + directionX, startY + directionY);
						if (loc.isEmpty() || loc.getPiece().getColor() != start.getPiece().getColor()) {
							result.add(new Move(start, loc));
						}
					}
				}
			}
		}

		return result;
	}
}