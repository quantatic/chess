package edu.ncsu.awbeggs.chess.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import edu.ncsu.awbeggs.chess.model.board.Board;

public class ChessboardDisplay extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7675290973133689692L;

	public static final int SQUARE_WIDTH = 128;
	
	public static final int SQUARE_HEIGHT = 128;
	
	private Board board;
	
	public ChessboardDisplay() {
		board = new Board();
		
		Dimension size = new Dimension(board.getHeight() * SQUARE_WIDTH,
				board.getWidth() * SQUARE_HEIGHT);
		
		setPreferredSize(size);
		setMinimumSize(size);
		setMaximumSize(size);
		setSize(size);
		setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));
	}
	
	@Override
	public void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D)g;
		for(int col = 0; col < board.getWidth(); col++) {
			for(int row = 0; row < board.getHeight(); row++) {
				g2d.setColor((row + col) % 2 == 0 ? Color.WHITE : Color.decode("#CD853F"));
				g2d.fillRect(col * SQUARE_WIDTH, row * SQUARE_HEIGHT, 
						SQUARE_WIDTH, SQUARE_HEIGHT);
				g2d.drawImage(board.getLocation(row, col).getOccupant().getRepresentation(), col * SQUARE_WIDTH, row * SQUARE_HEIGHT, null);
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
