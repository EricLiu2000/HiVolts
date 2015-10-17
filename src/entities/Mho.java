package entities;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

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
			moveSimple(player, grid);
		}
		else {
			moveComplex(player,grid);
		}
		
		if(grid[getX()][getY()] instanceof Fence) {
			kill();
		}
	}
	
	/**
	 * Simple movement; activates when on a direct line to the player
	 * @param player
	 * @param grid
	 * Author: Joseph Rumelhart
	 */
	private void moveSimple(Player player, Entity[][] grid) {
		if(player.getX() == this.getX()) {
			if(player.getY() >= this.getY()){
				move(Direction.SOUTH);
			}
			else {
				move(Direction.NORTH);
			}
		}
		else if(player.getY() == this.getY()) {
			if(player.getX() >= this.getX()){
				move(Direction.EAST);
			}
			else {
				move(Direction.WEST);
			}
		}
		else {
			System.out.println("Error: Invalid call");
		}
	}
	
	/**
	 * Complex movement; activates when not on a direct line to the player
	 * @param player
	 * @param grid
	 * Author: Joseph Rumelhart
	 */
	private void moveComplex(Player player, Entity[][] grid) {
		ArrayList<Entity> obstacles = new ArrayList<Entity>(3);
		Direction d = null;
		//p1 finished
		if(player.getX() >= this.getX() && player.getY() >= this.getY()) {
			obstacles.set(0, grid[this.getX() + 1][this.getY()]);
			obstacles.set(1, grid[this.getX() + 1][this.getY() + 1]);
			obstacles.set(2, grid[this.getX()][this.getY() + 1]);
			d = Direction.SOUTHWEST;
		}
		//p2 finished
		else if(player.getX() >= this.getX() && player.getY() <= this.getY()) {
			obstacles.set(0, grid[this.getX()][this.getY() - 1 ]);
			obstacles.set(1, grid[this.getX() + 1][this.getY() - 1]);
			obstacles.set(2, grid[this.getX() + 1][this.getY()]);
			d = Direction.NORTHWEST;
		}
		//p3 finished
		else if(player.getX() <= this.getX() && player.getY() <= this.getY()) {
			obstacles.set(0, grid[this.getX()][this.getY() - 1 ]);
			obstacles.set(1, grid[this.getX() - 1][this.getY() - 1]);
			obstacles.set(2, grid[this.getX() - 1][this.getY()]);
			d = Direction.NORTHEAST;
		}
		//p4 finished
		else if(player.getX() <= this.getX() && player.getY() >= this.getY()) {
			obstacles.set(0, grid[this.getX() - 1][this.getY()]);
			obstacles.set(1, grid[this.getX() - 1][this.getY() + 1]);
			obstacles.set(2, grid[this.getX()][this.getY() + 1]);
			d = Direction.SOUTHEAST;
		}
		else {
			System.out.println("Error: Invalid call");
		}
		
		if(obstacles.get(1) == null) {
			move(d);
		}
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
