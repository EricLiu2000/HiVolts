package entities;

import java.awt.Color;
import java.awt.Graphics;
import input.Keyboard;
import window.Game;

public class Player extends Entity {

	/**
	 * Creates a player and sets its position to the desired coordinates
	 * @param xPosition the desired x coordinate
	 * @param yPosition the desired y coordinate
	 * Author: Joseph Rumelhart
	 */
	public Player(int xPosition, int yPosition) {
		super(xPosition, yPosition);
	}

	/**
	 * Draws the player on the game board
	 * Author: Eric Liu
	 */
	@Override
	public void draw(Graphics g) {
		//Redraws the previous square to white
		if(Keyboard.updateCycle > 0) {
			g.setColor(Color.WHITE);
			g.fillRect(getLastX()*Game.SCALE, getLastY()*Game.SCALE + Game.WINDOWBAR, Game.SCALE, Game.SCALE);
		}

		if(getAlive()) {
			g.setColor(Color.GREEN);
			g.fillRect(getX()*Game.SCALE, getY()*Game.SCALE + Game.WINDOWBAR, Game.SCALE, Game.SCALE);
		}
		
		if(!getAlive()) {
			g.setColor(Color.WHITE);
			g.fillRect(getX()*Game.SCALE, getY()*Game.SCALE + Game.WINDOWBAR, Game.SCALE, Game.SCALE);
			System.out.println("player dead");
		}
	}

	/**
	 * Moves the player according to keyboard input
	 * This method is called when a key is pressed
	 * Author: Eric Liu
	 */
	@Override
	public boolean update(Entity[][] grid) {
		if(getAlive() == true) {
			if(Game.keyboard.key == "q") {
				grid[getX()][getY()] = null;
				int futureX = getFuturePosition(Direction.NORTHWEST)[0];
				int futureY = getFuturePosition(Direction.NORTHWEST)[1];
				if(grid[futureX][futureY] instanceof Fence) {
					kill();
				}
				else {
					move(Direction.NORTHWEST);
					grid[getX()][getY()] = this;
				}
			}
			
			if(Game.keyboard.key == "w") {
				grid[getX()][getY()] = null;
				int futureX = getFuturePosition(Direction.NORTH)[0];
				int futureY = getFuturePosition(Direction.NORTH)[1];
				if(grid[futureX][futureY] instanceof Fence) {
					kill();
				}
				else {
					move(Direction.NORTH);
					grid[getX()][getY()] = this;
				}
			}
			
			if(Game.keyboard.key == "e") {
				grid[getX()][getY()] = null;
				int futureX = getFuturePosition(Direction.NORTHEAST)[0];
				int futureY = getFuturePosition(Direction.NORTHEAST)[1];
				if(grid[futureX][futureY] instanceof Fence) {
					kill();
				}
				else {
					move(Direction.NORTHEAST);
					grid[getX()][getY()] = this;
				}
			}
			
			if(Game.keyboard.key == "a") {
				grid[getX()][getY()] = null;
				int futureX = getFuturePosition(Direction.WEST)[0];
				int futureY = getFuturePosition(Direction.WEST)[1];
				if(grid[futureX][futureY] instanceof Fence) {
					kill();
				}
				else {
					move(Direction.WEST);
					grid[getX()][getY()] = this;
				}
			}
			
			//Jump key, moves the player to a random position that is not a fence
			if(Game.keyboard.key == "s") {
				//sit and do nothing
			}
			
			if(Game.keyboard.key == "d") {
				grid[getX()][getY()] = null;
				int futureX = getFuturePosition(Direction.EAST)[0];
				int futureY = getFuturePosition(Direction.EAST)[1];
				if(grid[futureX][futureY] instanceof Fence) {
					kill();
				}
				else {
					move(Direction.EAST);
					grid[getX()][getY()] = this;
				}
			}
			
			if(Game.keyboard.key == "z") {
				grid[getX()][getY()] = null;
				int futureX = getFuturePosition(Direction.SOUTHWEST)[0];
				int futureY = getFuturePosition(Direction.SOUTHWEST)[1];
				if(grid[futureX][futureY] instanceof Fence) {
					kill();
				}
				else {
					move(Direction.SOUTHWEST);
					grid[getX()][getY()] = this;
				}
			}
			
			if(Game.keyboard.key == "x") {
				grid[getX()][getY()] = null;
				int futureX = getFuturePosition(Direction.SOUTH)[0];
				int futureY = getFuturePosition(Direction.SOUTH)[1];
				if(grid[futureX][futureY] instanceof Fence) {
					kill();
				}
				else {
					move(Direction.SOUTH);
					grid[getX()][getY()] = this;
				}
			}
			
			if(Game.keyboard.key == "c") {
				grid[getX()][getY()] = null;
				int futureX = getFuturePosition(Direction.SOUTHEAST)[0];
				int futureY = getFuturePosition(Direction.SOUTHEAST)[1];
				if(grid[futureX][futureY] instanceof Fence) {
					kill();
				}
				else {
					move(Direction.SOUTHEAST);
					grid[getX()][getY()] = this;
				}
			}
			
			//Jumps the player to a random non-fence position
			if(Game.keyboard.key == "j") {
				grid[getX()][getY()] = null;
				setLastX(getX());
				setLastY(getY());
				this.jump(grid);
			}
		}

		return getAlive();
	}
	

	/**
	 * Jumps the player to a random non-fence grid.
	 * @param grid the array of game spaces
	 * @return whether or not the player is alive
	 * Author: Joseph Rumelhart
	 */
	public boolean jump(Entity[][] grid) {
		setLastX(getX());
		setLastY(getY());
		while(true) {
			int validJumpX = (int) (1 + ((int)(grid.length - 2) * Math.random()));
			int validJumpY = (int) (1 + ((int)(grid[0].length - 2) * Math.random()));
			//Occurs when the random position selects a killing position
			if(grid[validJumpX][validJumpY] instanceof Mho) {
				kill();
				break;
			}
			//Occurs when the random position selects an invalid position
			else if(grid[validJumpX][validJumpY] instanceof Fence || grid[validJumpX][validJumpY] instanceof Player) {
				continue;
			}
			//Occurs when the random position selects a valid position
			else {
				setXPosition(validJumpX);
				setYPosition(validJumpY);
				grid[getX()][getY()] = this;
				break;
			}
		}
		return getAlive();
	}
}
