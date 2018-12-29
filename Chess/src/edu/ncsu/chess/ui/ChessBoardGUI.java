package edu.ncsu.chess.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseMotionListener;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import edu.ncsu.chess.game.ChessBoard;
import edu.ncsu.chess.game.Location;
import edu.ncsu.chess.piece.Piece;

/**
 * Represents a visual representation of the board.
 * @author Aidan Beggs
 */
public class ChessBoardGUI extends JFrame {
	
	private static final long serialVersionUID = 6548192522996001684L;

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
		
		private Location selectedLocation;
		
		
		public BoardPanel(int scale) {
			this.board = new ChessBoard();
			this.scale = scale;
			this.selectedLocation = board.getLocation(1, 1);
			
			setPreferredSize(new Dimension(scale * ChessBoard.WIDTH, scale * ChessBoard.HEIGHT));
			addMouseMotionListener(new MouseMotionAdapter() {

				@Override
				public void mouseMoved(MouseEvent e) {
					int boardRow = Math.min(
							ChessBoard.HEIGHT + 1 - (int)Math.ceil((double)e.getY() / BoardPanel.this.scale),
							ChessBoard.HEIGHT);
					int boardCol = (int)Math.ceil((double)e.getX() / BoardPanel.this.scale);
					BoardPanel.this.selectedLocation = BoardPanel.this.board.getLocation(boardRow, boardCol);
					repaint();
				}
			});
		}
		
		@Override
		protected void paintComponent(Graphics g) {
			Graphics2D g2d = (Graphics2D)g;
			
			for(int row = 1; row <= ChessBoard.HEIGHT; row++) {
				for(int col = 1; col <= ChessBoard.WIDTH; col++) {
					g2d.setColor((row + col) % 2 == 0 ? WHITE_SPACE : BLACK_SPACE);
					g2d.fillRect((col - 1) * this.scale, (ChessBoard.HEIGHT - row) * this.scale, this.scale, this.scale);
					
					if(!this.board.getLocation(row, col).isEmpty()) {
						g2d.drawImage(this.board.getLocation(row, col).getPiece().getSprite(), 
								(col - 1) * this.scale, (ChessBoard.HEIGHT - row) * this.scale, this.scale, this.scale, null);
					}
				}
			}
			
			g2d.setColor(new Color(0, 255, 0, 127));
			g2d.fillRect((selectedLocation.getCol() - 1) * this.scale, 
					(ChessBoard.HEIGHT - selectedLocation.getRow()) * this.scale, this.scale, this.scale);
			
			if(!selectedLocation.isEmpty()) {
				Piece selectedPiece = selectedLocation.getPiece();
				List<Location> validMoves = selectedPiece.validMoves(board, selectedLocation.getRow(), 
						selectedLocation.getCol());
				
				System.out.println(validMoves);
				g2d.setColor(new Color(0, 0, 255));
				for(Location validMove : validMoves) {
					g2d.fillRect((validMove.getCol() - 1) * this.scale, 
							(ChessBoard.HEIGHT - validMove.getRow()) * this.scale, this.scale, this.scale);
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
