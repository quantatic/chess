package edu.ncsu.awbeggs.chess.model.piece;

import java.awt.image.BufferedImage;

import edu.ncsu.awbeggs.chess.model.board.Location;
import edu.ncsu.awbeggs.chess.ui.SpriteLookup;

public class Queen extends Piece {

	public Queen(Location location, PieceColor color) {
		super(location, color);
	}

	@Override
	public BufferedImage getRepresentation() {
		return SpriteLookup.QUEEN.lookup(getColor());
	}

	@Override
	public String toString() {
		switch(getColor()) {
		case WHITE:
			return "♕";
		case BLACK:
			return "♛";
		default:
			throw new IllegalArgumentException();
		}
	}


}
