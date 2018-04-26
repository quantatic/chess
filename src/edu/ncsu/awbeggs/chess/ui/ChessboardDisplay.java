package edu.ncsu.awbeggs.chess.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import edu.ncsu.awbeggs.chess.model.board.Board;
import edu.ncsu.awbeggs.chess.model.board.Location;

public class ChessboardDisplay extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7675290973133689692L;

	public static final int SQUARE_WIDTH = 100;
	
	public static final int SQUARE_HEIGHT = 100;
	
	private Board board;
	
	public ChessboardDisplay(Board board) {
		this.board = board;
		
		Dimension size = new Dimension(board.getHeight() * SQUARE_WIDTH,
				board.getWidth() * SQUARE_HEIGHT);
		
		setPreferredSize(size);
		setMinimumSize(size);
		setMaximumSize(size);
		setSize(size);
		setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));
		
		addMouseListener(new MouseAdapter() {
			/**
			 * @param e
			 */
			@Override
			public void mousePressed(MouseEvent e) {
				board.updateSelected(board.getLocation(8 - (e.getY() / SQUARE_HEIGHT),
						1 + (e.getX() / SQUARE_WIDTH)));
				ChessboardDisplay.this.repaint();
			}
		});
	}
	
	@Override
	public void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D)g;
		g2d.clearRect(0, 0, SQUARE_WIDTH * board.getWidth(), SQUARE_HEIGHT * board.getHeight());
		for(int renderCol = 1; renderCol <= board.getWidth(); renderCol++) {
			for(int renderRow = 1; renderRow <= board.getHeight(); renderRow++) {
				g2d.setColor((renderRow + renderCol) % 2 == 0 ? Color.WHITE : Color.decode("#CD853F"));
				g2d.fillRect((renderCol - 1) * SQUARE_WIDTH, (renderRow - 1) * SQUARE_HEIGHT, 
						SQUARE_WIDTH, SQUARE_HEIGHT);
				
				Location currentLocation = board.getLocation(9 - renderRow, renderCol);
				if(currentLocation.getOccupant() != null) {
					g2d.drawImage(currentLocation.getOccupant().getRepresentation(),
							(renderCol - 1) * SQUARE_WIDTH, (renderRow - 1) * SQUARE_HEIGHT, null);
				}

				if(board.getSelected() != null && board.getSelected().getOccupant() != null
						&& board.getSelected().getOccupant().getValidMoves().contains(currentLocation)) {
					g2d.setColor(new Color(0, 255, 0, 200));
					g2d.fillRect((renderCol - 1) * SQUARE_WIDTH, (renderRow - 1) * SQUARE_HEIGHT, 
							SQUARE_WIDTH, SQUARE_HEIGHT);
				}
			}
		}
	}
	
	public String toString() {
		return board.toString();
	}
	
	
	public static BufferedImage resizeToFit(BufferedImage in) {
		Image tmp = in.getScaledInstance(SQUARE_WIDTH, SQUARE_HEIGHT, Image.SCALE_DEFAULT);
		BufferedImage resized = new BufferedImage(SQUARE_WIDTH, SQUARE_HEIGHT, 
				BufferedImage.TYPE_INT_ARGB);
		
		Graphics2D g2d = resized.createGraphics();
		g2d.drawImage(tmp, 0, 0, null);
		g2d.dispose();
		return resized;
	}
}
