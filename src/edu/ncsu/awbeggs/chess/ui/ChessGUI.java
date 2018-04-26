package edu.ncsu.awbeggs.chess.ui;

import java.awt.FlowLayout;

import javax.swing.JFrame;

public class ChessGUI extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4062699211127160865L;
	
	ChessboardDisplay c;
	
	public ChessGUI() {
		
		
		setLayout(new FlowLayout());
		c = new ChessboardDisplay();
		
		System.out.println(c);
		
		setMinimumSize(c.getMinimumSize());
		add(c);
		
		pack();
		setTitle("Chess Game");
	}
}
