package entities;

import java.awt.Graphics;

public class Entity {
	
	private int xPosition;
	private int yPosition;
	
	public enum Direction{
		NORTH,
		NORTHEAST,
		EAST,
		SOUTHEAST,
		SOUTH,
		SOUTHWEST,
		WEST,
		NORTHWEST
	}
	Direction direction;
	
	public Entity(int xPosition, int yPosition) {
		this.xPosition = xPosition;
		this.yPosition = yPosition;
	}
	
	public void draw(Graphics g){}
	
	public int getX() {
		return xPosition;
	}
	
	public int getY() {
		return yPosition;
	}
	/**
	 * Moves the entity in the specified direction
	 * @param direction the direction to move in
	 */
	public void move(Direction direction) {
		switch(direction) {
		case NORTH: 
			yPosition --;
			break;
		case NORTHEAST:
			yPosition --;
			xPosition ++;
			break;
		case EAST:
			xPosition ++;
			break;
		case SOUTHEAST:
			yPosition ++;
			xPosition ++;
			break;
		case SOUTH:
			yPosition ++;
			break;
		case SOUTHWEST:
			yPosition ++;
			xPosition --;
			break;
		case WEST:
			xPosition --;
			break;
		case NORTHWEST:
			yPosition --;
			xPosition --;
			break;
		default: 
			System.out.println("Error: Invalid direction");
			break;
		
		}
	}
}
