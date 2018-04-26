package edu.ncsu.awbeggs.chess.ui;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

import edu.ncsu.awbeggs.chess.model.piece.PieceColor;

public class SpriteIO {
	
	public static BufferedImage readSpriteFromPath(String name, PieceColor color) {
		URL urlPath = SpriteIO.class.getResource("/" + (color == PieceColor.WHITE ? "white" : "black") + "/" + name + ".png");
		System.out.println(urlPath);
		BufferedImage in;
		
		try {
			in = ImageIO.read(urlPath);
		} catch (IOException e) {
			throw new IllegalArgumentException();
		}
		
		return ChessboardDisplay.resizeToFit(in);
	}
}
