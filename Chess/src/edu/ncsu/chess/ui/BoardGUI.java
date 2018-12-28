package edu.ncsu.chess.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import edu.ncsu.chess.game.Board;

/**
 * Represents a visual representation of the board.
 * @author Aidan Beggs
 */
public class BoardGUI extends JFrame{
	
	public BoardGUI(int scale) {
		super();
		setTitle("Chess Game");
		add(new BoardPanel(scale));
		pack();
		setLocationRelativeTo(null);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	private class BoardPanel extends JPanel {
		
		private final Board board;
		
		private final int scale;
		
		public BoardPanel(int scale) {
			this.board = new Board();
			this.scale = scale;
			setPreferredSize(new Dimension(scale * Board.WIDTH, scale * Board.HEIGHT));
		}
		
		@Override
		protected void paintComponent(Graphics g) {
			Graphics2D g2d = (Graphics2D)g;
			
			for(int row = 0; row < Board.HEIGHT; row++) {
				for(int col = 0; col < Board.WIDTH; col++) {
					g2d.setColor((row + col) % 2 == 0 ? new Color(139, 69, 19) : new Color(255, 228, 196));
					g2d.fillRect(col * this.scale, row * this.scale, this.scale, this.scale);
				}
			}
		}
	}
	
	public static void main(String[] args) {
		BoardGUI g = new BoardGUI(128);
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				g.setVisible(true);
			}
		});
	}
}
