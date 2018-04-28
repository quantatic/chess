package edu.ncsu.awbeggs.chess.ui;

import java.awt.FlowLayout;

import javax.swing.JFrame;

/**
 * The overall GUI window for displaying the {@link Board}.
 * @author Aidan Beggs
 */
public class ChessGUI extends JFrame{
	
	private static final long serialVersionUID = -4062699211127160865L;
	
	/**
	 * Creates a new ChessGUI from a {@link ChessboardDisplay}.
	 * @param c the {@link ChessboardDisplay} to create a GUI for.
	 */
	public ChessGUI(ChessboardDisplay c) {
		setLayout(new FlowLayout());
		
		add(c);
		
		pack();
		setMinimumSize(getSize());
		
		setTitle("Chess Game");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
	}
}
