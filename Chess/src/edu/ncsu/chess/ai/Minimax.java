package edu.ncsu.chess.ai;

import java.util.Collections;
import java.util.List;

import edu.ncsu.chess.game.ChessBoard;
import edu.ncsu.chess.game.Location;
import edu.ncsu.chess.game.Move;
import edu.ncsu.chess.game.Piece;
import edu.ncsu.chess.game.PieceColor;
import edu.ncsu.chess.game.PieceType;

/**
 * Helper class which contains static methods to implement the minimax algorithm
 * for a chess board.
 * @author Aidan Beggs
 */
public class Minimax {
	
	public static int depth = 0;
	
	public static Move findBestMove(ChessBoard board, PieceColor currentPlayer, int maxDepth) {
		int bestMoveValue = Integer.MIN_VALUE;
		Move bestMove = null;
		
		for(int y = 1; y <= ChessBoard.HEIGHT; y++) {
			for(int x = 1; x <= ChessBoard.WIDTH; x++) {
				Location loc = board.getLocation(x, y);
				if(!loc.isEmpty() && loc.getPiece().getColor() == currentPlayer) {
					List<Move> movesThisPiece = board.getMoves(loc);
					Collections.shuffle(movesThisPiece);
					for(Move m : movesThisPiece) {
						board.makeMove(m);
						
						int currentMoveValue = minimaxHelper(board, currentPlayer, currentPlayer.other(), 
								0, maxDepth, Integer.MIN_VALUE, Integer.MAX_VALUE);
						
						board.undoLastMove();
						
						if(currentMoveValue > bestMoveValue) {
							bestMoveValue = currentMoveValue;
							bestMove = m;
						}
					}
				}
			}
		}
		
		System.out.println("Found best move: " + bestMove + " with score: " + bestMoveValue);
		
		System.out.println("depth: " + depth);
		depth = 0;
		return bestMove;
	}
	
	private static int minimaxHelper(ChessBoard board, PieceColor optimizingFor,
										PieceColor currentPlayer, 
										int currentDepth, int maxDepth, int alpha, int beta) {
		depth++;
		
		if(currentDepth >= maxDepth) {
			int boardValue = evaluateBoard(board, optimizingFor);
			return boardValue;
		}
		
		int bestMoveValue = (optimizingFor == currentPlayer) ? Integer.MIN_VALUE : Integer.MAX_VALUE;
		
		for(int y = 1; y <= ChessBoard.HEIGHT; y++) {
			for(int x = 1; x <= ChessBoard.WIDTH; x++) {
				Location loc = board.getLocation(x, y);
				if(!loc.isEmpty() && loc.getPiece().getColor() == currentPlayer) {
					List<Move> movesThisPiece = board.getMoves(loc);
					Collections.shuffle(movesThisPiece);
					for(Move m : movesThisPiece) {
						board.makeMove(m);
						
						int currentMoveValue = minimaxHelper(board, optimizingFor, 
								currentPlayer.other(), 
								currentDepth + 1, maxDepth, alpha, beta);
						
						board.undoLastMove();
						
						if(optimizingFor == currentPlayer) {
							bestMoveValue = Math.max(bestMoveValue, currentMoveValue);
							alpha = Math.max(alpha, bestMoveValue);
						} else {
							bestMoveValue = Math.min(bestMoveValue, currentMoveValue);
							beta = Math.min(beta, bestMoveValue);
						}
						
						if(alpha >= beta) {
							break;
						}
					}
				}
			}
		}

		return bestMoveValue;
	}
	
	private static int evaluateBoard(ChessBoard board, PieceColor currentPlayer) {
		int score = 0;
		int kingBalance = 0;
		
		for(int y = 1; y <= ChessBoard.HEIGHT; y++) {
			for(int x = 1; x <= ChessBoard.WIDTH; x++) {
				Location loc = board.getLocation(x, y);
				if(!loc.isEmpty()) {
					Piece pieceAtLoc = loc.getPiece();
					int valueModifier = pieceAtLoc.getColor() == currentPlayer ? 1 : -1;
					
					if(pieceAtLoc.getType() == PieceType.KING) {
						if(board.isInCheck(loc)) {
							score -= valueModifier;
						}
						
						if(!board.isInCheck(loc) || !board.getMoves(loc).isEmpty()) {
							kingBalance += valueModifier;
						}
					} else {
						score += (valueModifier * pieceAtLoc.getType().getValue());
					}
				}
			}
		}
		
		if(kingBalance != 0) {
			return kingBalance > 0 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
		}
		
		return score;
	}
}