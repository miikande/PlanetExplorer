package org.unioulu.tol.sqat2015.planetExplorer.tests;

import static org.junit.Assert.*;

import org.junit.Test;
import org.unioulu.tol.sqat2015.planetExplorer.PlanetExplorer;
import org.unioulu.tol.sqat2015.planetExplorer.Coordinate;

public class TestCoordinate {
	
	private final int INITIAL_COORDINATE = 100;	

	@Test
	public void testXYAccessors() {
		Coordinate coordinate = new Coordinate(INITIAL_COORDINATE, INITIAL_COORDINATE);
		
		assertEquals(INITIAL_COORDINATE, coordinate.getX());
		assertEquals(INITIAL_COORDINATE, coordinate.getY());
		
		int newSize = 500;
		coordinate.setX(newSize);
		coordinate.setY(newSize);
		
		assertEquals(newSize, coordinate.getX());
		assertEquals(newSize, coordinate.getY());
	}

}
