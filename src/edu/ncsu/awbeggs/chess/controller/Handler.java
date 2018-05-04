package edu.ncsu.awbeggs.chess.controller;

import javax.swing.SwingUtilities;

import edu.ncsu.awbeggs.chess.model.board.Board;
import edu.ncsu.awbeggs.chess.ui.ChessGUI;
import edu.ncsu.awbeggs.chess.ui.ChessboardDisplay;

/**
 * Overall handler for GUI runner.
 * @author Aidan Beggs
 */
public class Handler {
	
	/**
	 * Main class.
	 * @param args command line arguments passed to the program.
	 */
	public static void main(String[] args) {
		Board board = new Board(true);

		
		ChessboardDisplay c = new ChessboardDisplay(board);
		
		
		
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				ChessGUI g = new ChessGUI(c);
				g.setVisible(true);
			}
		});

	}
}
