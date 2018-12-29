package edu.ncsu.chess.ui;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

/**
 * Static helper class for lookup of sprites.
 * @author Aidan Beggs
 */
public class SpriteManager {
	
	/** Persistent map to map paths to already read in sprites. */
	private static final Map<String, BufferedImage> SPRITE_MAP = new HashMap<>();
	
	/**
	 * Looks up the sprite associated with the given path.
	 * @param path the path to look up the sprite on.
	 * @return the sprite associated with the given path.
	 * @throws IllegalArgumentException if no sprite could be found at the given path.
	 */
	public static BufferedImage lookupSprite(String path) {
		if(!SPRITE_MAP.containsKey(path)) {
			try {
				BufferedImage result = ImageIO.read(new File(path));
				SPRITE_MAP.put(path, result);
			} catch (IOException e) {
				throw new IllegalArgumentException("Cannot find sprite file at given path: " + path);
			}
		}
		
		return SPRITE_MAP.get(path);
	}
}
