package entities;

import java.awt.Color;
import java.awt.Graphics;

import input.Keyboard;
import window.Game;
import window.Game.Type;

public class Mho extends Entity {
	
	/**
	 * Creates a Mho and sets its position to the given coordinates
	 * @param xPosition the x coordinate of the Mho
	 * @param yPosition the y coordinate of the Mho
	 * Author: Joseph Rumelhart
	 */
	public Mho(int xPosition, int yPosition) {
		super(xPosition, yPosition);
	}
	
	/**
	 * Move the Mho towards the player
	 * Author: Joseph Rumelhart
	 */
	public void moveMho(Player player, Entity[][] grid) {
		if(player.getX() == this.getX() || player.getY() == this.getY()) {
			moveLinear(player, grid);
		}
		
		else {
			
		}
		
		if(grid[getX()][getY()] instanceof Fence) {
			kill();
		}
	}
	
	private void moveLinear(Player player, Entity[][] grid) {
		//Move in the correct direction on the line taking into account the object in the way
	}
	
	/**
	 * draws the mho on the game board
	 * Author: Eric Liu
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
	 * Author: Eric Liu
	 */
	public boolean update(Player player, Entity[][] grid) {
		moveMho(player, grid);
		return getAlive();
	}

	@Override
	public boolean update(Entity[][] grid) {
		// TODO Auto-generated method stub
		return false;
	}
}
