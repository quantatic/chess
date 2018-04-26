package edu.ncsu.awbeggs.chess.controller;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.SwingUtilities;

import edu.ncsu.awbeggs.chess.model.board.Board;
import edu.ncsu.awbeggs.chess.ui.ChessGUI;
import edu.ncsu.awbeggs.chess.ui.ChessboardDisplay;

public class Handler {
	public static void main(String[] args) {
		Board board = new Board();
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
