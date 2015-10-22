package org.unioulu.tol.sqat2015.planetExplorer.tests;

import static org.junit.Assert.*;

import org.junit.Test;
import org.unioulu.tol.sqat2015.planetExplorer.Coordinate;
import org.unioulu.tol.sqat2015.planetExplorer.PlanetExplorer;

public class TestPlanetExplorer {

	private static final int INITIAL_SIZE_500 = 500;

	@Test
	public void testXYConstructor() {
		assertNotNull(getPlanetExplorer());
	}
	
	@Test
	public void testXYAccessors() {
		PlanetExplorer planet = getPlanetExplorer();
		
		assertEquals(INITIAL_SIZE_500, planet.getSizeX());
		assertEquals(INITIAL_SIZE_500, planet.getSizeY());
		
		int newSize = 100;
		planet.setSizeX(newSize);
		planet.setSizeY(newSize);
		
		assertEquals(newSize, planet.getSizeX());
		assertEquals(newSize, planet.getSizeY());
	}
	
	@Test
	public void testObstacleConstructor() {
		String obstacles = "(5,6)(10,11)";
		PlanetExplorer planet = new PlanetExplorer(INITIAL_SIZE_500, INITIAL_SIZE_500, obstacles);
		
		assertTrue(planet.hasObstacles());
	}
	
	@Test
	public void testObstacleCounter() {
		String obstacles = "(5,6)(10,11)(4,7)(19,21)"; // Four obstacles
		PlanetExplorer planet = new PlanetExplorer(INITIAL_SIZE_500, INITIAL_SIZE_500, obstacles);
		
		assertEquals(4, planet.getObstacleCount());
	}
	
	@Test(expected=IndexOutOfBoundsException.class)
	public void testObstacleXCoordinateIsWithinLimits() {
		String obstacles = "(500,499)"; // Let's go outside of planet boundaries
		PlanetExplorer planet = new PlanetExplorer(INITIAL_SIZE_500, INITIAL_SIZE_500, obstacles);
	}
	
	@Test(expected=IndexOutOfBoundsException.class)
	public void testObstacleYCoordinateIsWithinLimits() {
		String obstacles = "(499,500)"; // Let's go outside of planet boundaries
		PlanetExplorer planet = new PlanetExplorer(INITIAL_SIZE_500, INITIAL_SIZE_500, obstacles);
	}

	@Test
	public void testInitialPositionIs00() {
		PlanetExplorer pe = getPlanetExplorer();
		Coordinate c = pe.getCurrentPosition();
		
		assertEquals(0, c.getX());
		assertEquals(0, c.getY());
	}
	
	@Test
	public void testInitialDirectionIsNorth() {
		PlanetExplorer pe = getPlanetExplorer();
		assertEquals(PlanetExplorer.Direction.NORTH, pe.getDirection());
	}
	
	@Test
	public void testGetCurrentPositionWithDirection() {
		PlanetExplorer pe = getPlanetExplorer();
		String pos = pe.getCurrentPositionWithDirection();
		
		// Let's remove parenthesis
		pos = pos.substring(1, pos.length()-1);
		
		// And split the string into pieces
		String[] pieces = pos.split(",");
		
		assertEquals("0", pieces[0]);
		assertEquals("0", pieces[1]);
		assertEquals("N", pieces[2]);
	}
	
	@Test
	public void testGetCurrentPositionWithDirectionAfterTurningLeft() {
		PlanetExplorer pe = getPlanetExplorer();
		
		// Change the direction
		pe.turnLeft();
		
		String pos = pe.getCurrentPositionWithDirection();
		
		// Let's remove parenthesis
		pos = pos.substring(1, pos.length()-1);
		
		// And split the string into pieces
		String[] pieces = pos.split(",");
		
		assertEquals("0", pieces[0]);
		assertEquals("0", pieces[1]);
		assertEquals("E", pieces[2]);
	}
	
	@Test
	public void testGetCurrentPositionWithDirectionAfterTurningRight() {
		PlanetExplorer pe = getPlanetExplorer();
		
		// Change the direction
		pe.turnRight();
		
		String pos = pe.getCurrentPositionWithDirection();
		
		// Let's remove parenthesis
		pos = pos.substring(1, pos.length()-1);
		
		// And split the string into pieces
		String[] pieces = pos.split(",");
		
		assertEquals("0", pieces[0]);
		assertEquals("0", pieces[1]);
		assertEquals("W", pieces[2]);
	}
	
	private PlanetExplorer getPlanetExplorer() {
		PlanetExplorer planet = new PlanetExplorer(INITIAL_SIZE_500, INITIAL_SIZE_500);
		return planet;
	}
}
