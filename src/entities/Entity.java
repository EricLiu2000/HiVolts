package entities;

import java.awt.Graphics;

public abstract class Entity {
	
	//The values representing the current position of the Entity
	private int xPosition;
	private int yPosition;
	
	//The values representing the previous position of the Entity
	private int lastXPosition;
	private int lastYPosition;
	
	//Variable to represent if the entity is alive or not
	private boolean isAlive;

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
	 * Creates an Entity with the given coordinates
	 * 
	 * @param xPosition the x coordinate of the Entity
	 * @param yPosition the x coordinate of the Entity
	 * 
	 * Author: Eric Liu
	 */
	public Entity(int xPosition, int yPosition) {
		this.setXPosition(xPosition);
		this.setYPosition(yPosition);
		lastXPosition = xPosition;
		lastYPosition = yPosition;
		setAlive(true);
	}
	
	/**
	 * Draws the Entity. Meant to be overridden.
	 * 
	 * @param g the graphics object to be used
	 * 
	 * Author: Eric Liu
	 */
	public abstract void draw(Graphics g);
	
	/**
	 * Updates the Entity. Meant to be overridden.
	 * 
	 * @return if the Entity is alive
	 * 
	 * Author: Joseph Rumelhart
	 */
	public abstract boolean update(Entity[][] grid);
	
	/**
	 * Gets the x coordinate of the Entity.
	 * 
	 * @return the x coordinate of the entity
	 * 
	 * Author: Joseph Rumelhart
	 */
	public int getX() {
		return xPosition;
	}
	
	/**
	 * Gets the y coordinate of the Entity.
	 * 
	 * @return the y coordinate of the entity
	 * 
	 * Author: Joseph Rumelhart
	 */
	public int getY() {
		return yPosition;
	}
	
	/**
	 * Gets the last x coordinate of the Entity.
	 * 
	 * @return the last x coordinate of the entity
	 * 
	 * Author: Eric Liu
	 */
	public int getLastX() {
		return lastXPosition;
	}
	
	/**
	 * Gets the last y coordinate of the Entity
	 * 
	 * @return the last y coordinate of the entity
	 * 
	 * Author: Eric Liu
	 */
	public int getLastY() {
		return lastYPosition;
	}
	
	/**
	 * Sets the last x coordinate
	 * 
	 * @param lastX the last x coordinate
	 * 
	 * Author: Eric Liu
	 */
	public void setLastX(int lastX) {
		lastXPosition = lastX;
	}
	/**
	 * Sets the last y coordinate
	 * 
	 * @param lastY the last y coordinate 
	 * 
	 * Author: Eric Liu
	 */
	public void setLastY(int lastY) {
		lastYPosition = lastY;
	}
	
	/**
	 * Gets whether or not the Entity is alive or not
	 * 
	 * @return if the Entity is alive or not
	 * 
	 * Author: Joseph Rumelhart
	 */
	public boolean getAlive() {
		return isAlive();
	}
	
	/**
	 * Kills the entity
	 * 
	 * Author: Eric Liu
	 */
	public void kill() {
		setAlive(false);
	}
	
	/**
	 * Moves the entity in the specified direction
	 * 
	 * @param direction the direction to move in
	 * 
	 * Author: Joseph Rumelhart and Eric Liu
	 */
	public int[] move(Direction direction) {
		switch(direction) {
			case NORTH: 
				setLastX(getX());
				setLastY(getY());
				setYPosition(getY() - 1);
				break;
			case NORTHEAST:
				setLastX(getX());
				setLastY(getY());
				setYPosition(getY() - 1);
				setXPosition(getX() + 1);
				break;
			case EAST:
				setLastX(getX());
				setLastY(getY());
				setXPosition(getX() + 1);
				break;
			case SOUTHEAST:
				setLastX(getX());
				setLastY(getY());
				setYPosition(getY() + 1);
				setXPosition(getX() + 1);
				break;
			case SOUTH:
				setLastX(getX());
				setLastY(getY());
				setYPosition(getY() + 1);
				break;
			case SOUTHWEST:
				setLastX(getX());
				setLastY(getY());
				setYPosition(getY() + 1);
				setXPosition(getX() - 1);
				break;
			case WEST:
				lastXPosition = getX();
				lastYPosition = getY();
				setXPosition(getX() - 1);
				break;
			case NORTHWEST:
				setLastX(getX());
				setLastY(getY());
				setYPosition(getY() - 1);
				setXPosition(getX() - 1);
				break;
			default: 
				System.out.println("Error: Invalid direction");
				break;
		}
		
		//returns the current coordinates of the entity as an array
		int[] currentCoords = {getX(),getY()};
		return currentCoords;
	}

	/**
	 * Checks whether the entity is alive
	 * 
	 * @return entity alive or dead
	 * Author: Joseph Rumelhart
	 * 
	 */
	public boolean isAlive() {
		return isAlive;
	}

	/**
	 * Sets the entity to be alive or dead
	 * 
	 * @param isAlive whether the entity is alive
	 * 
	 * Author: Eric Liu
	 */
	public void setAlive(boolean isAlive) {
		this.isAlive = isAlive;
	}

	/**
	 * Sets the x coordinate to the desired x coordinate
	 * 
	 * @param xPosition the desired x coordinate
	 * 
	 * Author: Joseph Rumelhart
	 */
	public void setXPosition(int xPosition) {
		this.xPosition = xPosition;
	}
	
	/**
	 * Sets the y coordinate to the desired y coordinate
	 * 
	 * @param yPosition the desired y coordinate
	 * 
	 * Author: Joseph Rumelhart
	 */
	public void setYPosition(int yPosition) {
		this.yPosition = yPosition;
	}
}
