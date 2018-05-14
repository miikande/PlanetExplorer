package org.unioulu.tol.sqat2015.planetExplorer;

import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// Before submitting write your ID and finish time here. Your ID is written on project description sheets.
// ID: 159
// Finish time: 13:30
public class PlanetExplorer {
	private Coordinate planetSize;
	private List<Coordinate> obstacles;
	private Coordinate currentPosition;
	private Direction direction;
	
	public enum Direction {
		NORTH, EAST, SOUTH, WEST
	}
	
	public PlanetExplorer(int x, int y) {
		planetSize = new Coordinate(x, y);
		obstacles = new LinkedList<>();
		currentPosition = new Coordinate(0, 0);
		direction = Direction.NORTH;
	}
	
	public PlanetExplorer(int x, int y, String obstaclesString){
	/*	x and y represent the size of the grid.
	 *  Obstacles is a String formatted as follows: "(obs1_x,obs1_y)(obs2_x,obs2_y)...(obsN_x,obsN_y)" with no white spaces. 
	 *  
		Example use:
		PlanetExplorer explorer = new PlanetExplorer(100,100,"(5,5)(7,8)")  //A 100x100 grid with two obstacles at coordinates (5,5) and (7,8) 
	 */
		planetSize = new Coordinate(x, y);
		currentPosition = new Coordinate(0, 0);
		this.obstacles = parseObstacles(obstaclesString);
		direction = Direction.NORTH;
	}
	
	private List<Coordinate> parseObstacles(String obstaclesString) {
		List<Coordinate> obs = new LinkedList<>();
		String regexp = "((\\d*),(\\d*))";
		Pattern pattern = Pattern.compile(regexp);
		Matcher matcher = pattern.matcher(obstaclesString);
		
		while (matcher.find()) {
			String group = matcher.group(0);
			
			if (group != null && group.length() > 0) {
				String[] coords = group.split(",");
				
				if (coords.length > 1) {
					int x = Integer.parseInt(coords[0]);
					int y = Integer.parseInt(coords[1]);
					
					// Before actually creating a new obstacle, we should check its coordinates
					// are within the the planet
					if (x >= getSizeX() || y >= getSizeY()) {
						throw new IndexOutOfBoundsException("Given obstacle coordinates exceeds the size of planet!");
					}
					
					Coordinate c = new Coordinate(x,y);
					obs.add(c);
				}
			}
		}
		
		// Adding some nonsense empty lines for testing purposes...
		
		
		
		
		
		
		
		
		
		
		return obs;
	}

	public String executeCommand(String command){
		
		/* The command string is composed of "f" (forward), "b" (backward), "l" (left) and "r" (right)
		 * Example: 
		 * The explorer is on a 100x100 grid at location (0, 0) and facing NORTH. 
		 * The explorer is given the commands "ffrff" and should end up at (2, 2) facing East.
		 
		 * The return string is in the format: "(pos_x,pos_y,facing)(obs1_x,obs1_y)(obs2_x,obs2_y)..(obsN_x,obsN_y)" 
		 * Where pos_x and pos_y are the final coordinates, facing is the current direction the explorer is pointing to (N,S,W,E).
		 * The return string should also contain a list of coordinates of the encountered obstacles. No white spaces.
		 */
		
		return null;
	}

	public int getSizeX() {
		return planetSize.getX();
	}

	public void setSizeX(int sizeX) {
		planetSize.setX(sizeX);
	}

	public int getSizeY() {
		return planetSize.getY();
	}

	public void setSizeY(int sizeY) {
		planetSize.setY(sizeY);
	}

	public boolean hasObstacles() {
		return obstacles.size() > 0;
	}

	public Object getObstacleCount() {
		return obstacles.size();
	}
	
	public Coordinate getCurrentPosition() {
		return currentPosition;
	}
	
	public Direction getDirection() {
		return direction;
	}
	
	public void setDirection(Direction newDirection) {
		direction = newDirection;
	}

	public String getCurrentPositionWithDirection() {
		String heading = "";
		switch (direction) {
		case NORTH:
			heading = "N";
			break;
		case EAST:
			heading = "E";
			break;
		case SOUTH:
			heading = "S";
			break;
		case WEST:
			heading = "W";
			break;
		default:
			break;
		}
		
		String pos = "("+currentPosition.getX()+","+currentPosition.getY()+","+heading+")";
		return pos;
	}
	
	public void turnLeft() {
		switch (direction) {
		case NORTH:
			direction = Direction.EAST;
			break;
		case EAST:
			direction = Direction.NORTH;
			break;
		case SOUTH:
			direction = Direction.WEST;
			break;
		case WEST:
			direction = Direction.SOUTH;
			break;
		default:
			break;
		}
	}
	
	public void turnRight() {
		switch (direction) {
		case NORTH:
			direction = Direction.WEST;
			break;
		case EAST:
			direction = Direction.SOUTH;
			break;
		case SOUTH:
			direction = Direction.EAST;
			break;
		case WEST:
			direction = Direction.NORTH;
			break;
		default:
			break;
		}
	}
	
}
