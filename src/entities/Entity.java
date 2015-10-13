package entities;

import java.awt.Graphics;

public abstract class Entity {
	
	//The values representing the position of the Entity
	private int xPosition;
	private int yPosition;
	
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
	 * @param xPosition the x coordinate of the Entity
	 * @param yPosition the x coordinate of the Entity
	 */
	public Entity(int xPosition, int yPosition) {
		this.setxPosition(xPosition);
		this.setyPosition(yPosition);
		setAlive(true);
	}
	
	/**
	 * Draws the Entity. Meant to be overridden.
	 * @param g the graphics object to be used
	 */
	public abstract void draw(Graphics g);
	
	/**
	 * Updates the Entity. Meant to be overridden.
	 * @return if the Entity is alive
	 */
	public abstract boolean update();
	
	/**
	 * Gets the x coordinate of the Entity.
	 * @return the x coordinate of the entity
	 */
	public int getX() {
		return getxPosition();
	}
	
	/**
	 * Gets the y coordinate of the Entity.
	 * @return the y coordinate of the entity
	 */
	public int getY() {
		return getyPosition();
	}
	
	/**
	 * Gets whether or not the Entity is alive or not
	 * 
	 * @return if the Entity is alive or not
	 */
	public boolean getAlive() {
		return isAlive();
	}
	
	/**
	 * Kills the entity
	 */
	public void kill() {
		setAlive(false);
	}
	
	/**
	 * Moves the entity in the specified direction
	 * @param direction the direction to move in
	 */
	public void move(Direction direction) {
		switch(direction) {
			case NORTH: 
				setyPosition(getyPosition() - 1);
				break;
			case NORTHEAST:
				setyPosition(getyPosition() - 1);
				setxPosition(getxPosition() + 1);
				break;
			case EAST:
				setxPosition(getxPosition() + 1);
				break;
			case SOUTHEAST:
				setyPosition(getyPosition() + 1);
				setxPosition(getxPosition() + 1);
				break;
			case SOUTH:
				setyPosition(getyPosition() + 1);
				break;
			case SOUTHWEST:
				setyPosition(getyPosition() + 1);
				setxPosition(getxPosition() - 1);
				break;
			case WEST:
				setxPosition(getxPosition() - 1);
				break;
			case NORTHWEST:
				setyPosition(getyPosition() - 1);
				setxPosition(getxPosition() - 1);
				break;
			default: 
				System.out.println("Error: Invalid direction");
				break;
		}
	}

	public boolean isAlive() {
		return isAlive;
	}

	public void setAlive(boolean isAlive) {
		this.isAlive = isAlive;
	}

	public int getxPosition() {
		return xPosition;
	}

	public void setxPosition(int xPosition) {
		this.xPosition = xPosition;
	}

	public int getyPosition() {
		return yPosition;
	}

	public void setyPosition(int yPosition) {
		this.yPosition = yPosition;
	}
}
