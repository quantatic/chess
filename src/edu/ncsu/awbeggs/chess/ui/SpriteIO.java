package edu.ncsu.awbeggs.chess.ui;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

import edu.ncsu.awbeggs.chess.model.piece.Piece;
import edu.ncsu.awbeggs.chess.model.piece.PieceColor;

/**
 * Helper class that handles the IO associated with reading in {@link Piece} sprites.
 * @author Aidan Beggs
 */
public class SpriteIO {
	
	/**
	 * Reads a {@link Piece} sprite from a path, depending on the name and {@link PieceColor}
	 * of the {@link Piece}, resized to fit a square on a {@link ChessboardDisplay}.
	 * @param name the name of the {@link Piece} to get the sprite of.
	 * @param color the {@link PieceColor} of the {@link Piece} to get the sprite of.
	 * @return a {@link BufferedImage} representing the sprite of a {@link Piece} of the passed
	 * name and {@link PieceColor}, resized to fit the {@link ChessboardDisplay}.
	 */
	public static BufferedImage readSpriteFromPath(String name, PieceColor color) {
		URL urlPath = SpriteIO.class.getResource("/pieces/" + (color == PieceColor.WHITE ? "white" : "black") + "/" + name + ".png");
		BufferedImage in;
		
		try {
			in = ImageIO.read(urlPath);
		} catch (IOException e) {
			throw new IllegalArgumentException();
		}
		
		return ChessboardDisplay.resizeToFit(in);
	}
}
