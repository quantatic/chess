package edu.ncsu.chess.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import edu.ncsu.chess.game.ChessBoard;
import edu.ncsu.chess.game.ChessManager;
import edu.ncsu.chess.game.Location;
import edu.ncsu.chess.piece.PieceColor;

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

		private final ChessManager manager;
		
		private final int scale;
		
		private final Color whiteSquareColor = new Color(139, 69, 19);
		private final Color blackSquareColor = new Color(255, 228, 196);
		
		private Location selectedLocation;
		
		
		public BoardPanel(int scale) {
			this.manager = new ChessManager(PieceColor.WHITE);
			this.scale = scale;
			
			setPreferredSize(new Dimension(scale * ChessBoard.WIDTH, scale * ChessBoard.HEIGHT));
			addMouseListener(new MouseAdapter() {

				
				@Override
				public void mouseClicked(MouseEvent e) {
					int boardRow = Math.min(
							ChessBoard.HEIGHT + 1 - (int)Math.ceil((double)e.getY() / scale),
							ChessBoard.HEIGHT);
					int boardCol = (int)Math.ceil((double)e.getX() / BoardPanel.this.scale);
					Location clickedLocation = manager.getLocation(boardRow, boardCol);
					
					if(selectedLocation == null) { //if no selected location
						if(!clickedLocation.isEmpty()
								&& clickedLocation.getPiece().getColor() == manager.getCurrentTurn()) { //if also, we clicked on a loc with a piece in it and of the right color
							selectedLocation = clickedLocation;
						}
					} else { //if previously selected location
						List<Location> validMoves = manager.getMoves(selectedLocation);
						
						if(validMoves.contains(clickedLocation)) { //make sure clicking on a valid location
							manager.movePiece(selectedLocation, clickedLocation);
						}
						
						BoardPanel.this.selectedLocation = null;
					}
					
					repaint();
				}
			});
		}
		
		@Override
		protected void paintComponent(Graphics g) {
			setTitle(manager.getCurrentTurn().toString() + " TURN"); //set the title whenever we repaint for whatever reason
			
			Graphics2D g2d = (Graphics2D)g;
			
			for(int row = 1; row <= ChessBoard.HEIGHT; row++) {
				for(int col = 1; col <= ChessBoard.WIDTH; col++) {
					g2d.setColor((row + col) % 2 == 0 ? whiteSquareColor : blackSquareColor);
					g2d.fillRect((col - 1) * this.scale, (ChessBoard.HEIGHT - row) * this.scale, this.scale, this.scale);
					
					if(!manager.getLocation(row, col).isEmpty()) {
						g2d.drawImage(manager.getLocation(row, col).getPiece().getSprite(), 
								(col - 1) * this.scale, (ChessBoard.HEIGHT - row) * this.scale, this.scale, this.scale, null);
					}
				}
			}
			
			if(selectedLocation != null) {
				g2d.setColor(new Color(0, 255, 0, 127));
				g2d.fillRect((selectedLocation.getCol() - 1) * this.scale, 
						(ChessBoard.HEIGHT - selectedLocation.getRow()) * this.scale, this.scale, this.scale);

			
				if(!selectedLocation.isEmpty()) {
					List<Location> validMoves = manager.getMoves(selectedLocation);
	
					g2d.setColor(new Color(0, 0, 255, 127));
					for(Location validMove : validMoves) {
						g2d.fillRect((validMove.getCol() - 1) * this.scale, 
								(ChessBoard.HEIGHT - validMove.getRow()) * this.scale, this.scale, this.scale);
					}
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
