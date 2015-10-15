package entities;

import java.awt.Color;

import java.awt.Graphics;

import window.Game;

public class Fence extends Entity {

	/**
	 * Creates a Fence with the given coordinates
	 * @param xPosition the x coordinate of the Fence
	 * @param yPosition the y coordinate of the Fence
	 */
	public Fence(int xPosition, int yPosition) {
		super(xPosition, yPosition);
	}

	/**
	 * Creates the visible fence on the game board
	 */
	@Override
	public void draw(Graphics g) {
		g.setColor(Color.DARK_GRAY);
		g.fillRect(getX()*Game.SCALE, getY()*Game.SCALE + Game.WINDOWBAR, Game.SCALE, Game.SCALE);
	}

	@Override
	public boolean update(Entity[][] grid) {
		grid[getX()][getY()] = this;
		return getAlive();
	}
}
