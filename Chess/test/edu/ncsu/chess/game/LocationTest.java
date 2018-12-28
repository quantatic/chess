package edu.ncsu.chess.game;

import static org.junit.Assert.*;

import org.junit.Test;

public class LocationTest {

	@Test
	public void testCreateLocation() {
		Location l = new Location(5, 6);
		assertEquals(5, l.getRow());
		assertEquals(6, l.getCol());
	}
	
	@Test
	public void testLocationContains() {
		Location l = new Location(0, 0);
		assertTrue(l.isEmpty());
	}
}
