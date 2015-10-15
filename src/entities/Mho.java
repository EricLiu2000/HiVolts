package entities;

import java.awt.Color;
import java.awt.Graphics;

import input.Keyboard;
import window.Game;

public class Mho extends Entity {
	
	/**
	 * Creates a Mho and sets its position to the given coordinates
	 * @param xPosition the x coordinate of the Mho
	 * @param yPosition the y coordinate of the Mho
	 */
	public Mho(int xPosition, int yPosition) {
		super(xPosition, yPosition);
	}
	
	/**
	 * Move the Mho towards the player
	 */
	public void moveMho(Player player, Entity[][] grid) {
		int xDiff = player.getX() - this.getX();
		int yDiff = player.getY() - this.getY();
		
		if(xDiff == 0 && yDiff > 0) {
			grid[getX()][getY()] = null;
			move(Direction.SOUTH);
			grid[getX()][getY()] = this;
		}
		else if(xDiff == 0 && yDiff < 0) {
			grid[getX()][getY()] = null;
			move(Direction.NORTH);
			grid[getX()][getY()] = this;
		}
		else if(yDiff == 0 && xDiff > 0) {
			grid[getX()][getY()] = null;
			move(Direction.SOUTH);
			grid[getX()][getY()] = this;
		}
		else if(yDiff == 0 && xDiff < 0) {
			grid[getX()][getY()] = null;
			move(Direction.NORTH);
			grid[getX()][getY()] = this;
		}
		else {
			moreMove(player, grid);
		}
		if(grid[getX()][getY()] instanceof Fence) {
			kill();
		}
	}
	
	private void moreMove(Player player, Entity[][] grid) {
		
	}
	
	/**
	 * draws the mho on the game board
	 */
	@Override
	public void draw(Graphics g) {
		if(Keyboard.updateCycle > 0) {
			g.setColor(Color.WHITE);
			g.fillRect(getLastX()*Game.SCALE, getLastY()*Game.SCALE + Game.WINDOWBAR, Game.SCALE, Game.SCALE);
		}
		if(getAlive()) {
			g.setColor(Color.RED);
			g.fillRect(getX()*Game.SCALE, getY()*Game.SCALE + Game.WINDOWBAR, Game.SCALE, Game.SCALE);
		}
	}

	/**
	 * Updates the mho to the current state of the board
	 * @param player the current player
	 * @param grid the game board
	 * @return whether the Mho is dead or alive
	 */
	public boolean update(Player player, Entity[][] grid) {
		//moveMho(player, grid);
		return getAlive();
	}

	@Override
	public boolean update(Entity[][] grid) {
		// TODO Auto-generated method stub
		return false;
	}
}
