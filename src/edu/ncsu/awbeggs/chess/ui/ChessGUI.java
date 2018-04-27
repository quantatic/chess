package edu.ncsu.awbeggs.chess.ui;

import java.awt.FlowLayout;

import javax.swing.JFrame;

public class ChessGUI extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4062699211127160865L;
	
	private ChessboardDisplay c;
	
	public ChessGUI(ChessboardDisplay c) {
		setLayout(new FlowLayout());
		this.c = c;
		
		add(c);
		
		pack();
		setMinimumSize(getSize());
		
		setTitle("Chess Game");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
	}
}
