package entities;

import java.awt.Graphics;

public abstract class Entity {
	
	//The values representing the position of the Entity
	private int xPosition;
	private int yPosition;
	
	//An enum for the direction of movement
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
	
	/**
	 * The constructor for Entity. Sets the position of the Entity to the given values.
	 * 
	 * @param xPosition the desired x-position of the Entity
	 * @param yPosition the desired y-position of the Entity
	 */
	public Entity(int xPosition, int yPosition) {
		this.xPosition = xPosition;
		this.yPosition = yPosition;
	}
	
	/**
	 * Draws the Entity
	 * 
	 * @param g the graphics object to be used
	 */
	public abstract void draw(Graphics g);
	
	/**
	 * 
	 * @return the x-position of the entity
	 */
	public int getX() {
		return xPosition;
	}
	
	/**
	 * Gets the 
	 * 
	 * @return the y-position of the entity
	 */
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
