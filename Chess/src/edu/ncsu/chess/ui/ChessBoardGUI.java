package edu.ncsu.chess.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import edu.ncsu.chess.game.ChessBoard;
import edu.ncsu.chess.piece.PieceColor;

/**
 * Represents a visual representation of the board.
 * @author Aidan Beggs
 */
public class ChessBoardGUI extends JFrame{
	
	/**
	 * Creates a new chess board GUI with a given scale.
	 * @param scale the scale to use when rendering the board.
	 */
	public ChessBoardGUI(int scale) {
		super();
		setTitle("Chess Game");
		add(new BoardPanel(scale));
		pack();
		setLocationRelativeTo(null);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	/**
	 * Inner class to represent the board actually rendered within the main jframe.
	 * @author Aidan Beggs
	 */
	private class BoardPanel extends JPanel {
		
		/**
		 * The serial ID for this panel.
		 */
		private static final long serialVersionUID = 5556046639390553516L;

		private final ChessBoard board;
		
		private final int scale;
		
		private final Color WHITE_SPACE = new Color(139, 69, 19);
		private final Color BLACK_SPACE = new Color(255, 228, 196);
		
		
		public BoardPanel(int scale) {
			this.board = new ChessBoard();
			this.scale = scale;
			setPreferredSize(new Dimension(scale * ChessBoard.WIDTH, scale * ChessBoard.HEIGHT));
		}
		
		@Override
		protected void paintComponent(Graphics g) {
			Graphics2D g2d = (Graphics2D)g;
			
			for(int row = 1; row <= ChessBoard.HEIGHT; row++) {
				for(int col = 1; col <= ChessBoard.WIDTH; col++) {
					if(!this.board.getLocation(row, col).isEmpty()) {
						if(this.board.getLocation(row, col).getPiece().getColor() == PieceColor.WHITE) {
							g2d.setColor(Color.WHITE);
						} else {
							g2d.setColor(Color.BLACK);
						}
					} else {
						g2d.setColor((row + col) % 2 == 0 ? WHITE_SPACE : BLACK_SPACE);
					}
					
					g2d.fillRect((col - 1) * this.scale, (ChessBoard.HEIGHT - row) * this.scale, this.scale, this.scale);
				}
			}
		}
	}
	
	/**
	 * Creates a new ChessBoardGUI and runs it, threaded properly.
	 * @param args command line arguments passed to this program.
	 */
	public static void main(String[] args) {
		ChessBoardGUI g = new ChessBoardGUI(128);
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				g.setVisible(true);
			}
		});
	}
}
