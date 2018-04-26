package edu.ncsu.awbeggs.chess.model.piece;

import java.awt.image.BufferedImage;

import edu.ncsu.awbeggs.chess.model.board.Location;
import edu.ncsu.awbeggs.chess.ui.SpriteLookup;

public class King extends Piece{
	
	public King(Location location, PieceColor color) {
		super(location, color);
	}

	@Override
	public String toString() {
		switch(getColor()) {
		case WHITE:
			return "♔";
		case BLACK:
			return "♚";
		default:
			throw new IllegalArgumentException();
		}
	}

	@Override
	public BufferedImage getRepresentation() {
		return SpriteLookup.KING.lookup(getColor());
	}
}
