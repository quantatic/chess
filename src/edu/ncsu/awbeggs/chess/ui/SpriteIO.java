package edu.ncsu.awbeggs.chess.ui;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import edu.ncsu.awbeggs.chess.model.piece.PieceColor;

public class SpriteIO {
	
	public static BufferedImage readSpriteFromPath(String name, PieceColor color) {
		String path = "sprites/" + (color == PieceColor.WHITE ? "white" : "black") + "/" + name + ".png";
		
		BufferedImage in;
		
		try {
			in = ImageIO.read(new File(path));
		} catch (IOException e) {
			throw new IllegalArgumentException();
		}
		
		return ChessboardDisplay.resizeToFit(in);
	}
}
