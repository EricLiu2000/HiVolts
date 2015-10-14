package entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import input.Keyboard;
import window.Game;

public class Player extends Entity {

	public Player(int xPosition, int yPosition) {
		super(xPosition, yPosition);
	}

	@Override
	public void draw(Graphics g) {
		
		if(Keyboard.updateCycle > 0) {
			g.setColor(Color.WHITE);
			g.fillRect(getLastX()*Game.SCALE, getLastY()*Game.SCALE + Game.WINDOWBAR, Game.SCALE, Game.SCALE);
		}

		if(getAlive()) {
			g.setColor(Color.GREEN);
			g.fillRect(getX()*Game.SCALE, getY()*Game.SCALE + Game.WINDOWBAR, Game.SCALE, Game.SCALE);
		}
		
		if(!getAlive()) {
			System.out.println("player dead");
		}
	}

	public boolean update(Entity[][] grid) {
		if(getAlive() == true) {
			if(Game.keyboard.key == "q") {
				grid[getX()][getY()] = null;
				move(Direction.NORTHWEST);
				grid[getX()][getY()] = this;
			}
			
			if(Game.keyboard.key == "w") {
				grid[getX()][getY()] = null;
				move(Direction.NORTH);
				grid[getX()][getY()] = this;
			}
			
			if(Game.keyboard.key == "e") {
				grid[getX()][getY()] = null;
				move(Direction.NORTHEAST);
				grid[getX()][getY()] = this;
			}
			
			if(Game.keyboard.key == "a") {
				grid[getX()][getY()] = null;
				move(Direction.WEST);
				grid[getX()][getY()] = this;
			}
			
			if(Game.keyboard.key == "s") {
				grid[getX()][getY()] = null;
				setLastX(getX());
				setLastY(getY());
				this.jump(grid);
			}
			
			if(Game.keyboard.key == "d") {
				grid[getX()][getY()] = null;
				move(Direction.EAST);
				grid[getX()][getY()] = this;
			}
			
			if(Game.keyboard.key == "z") {
				grid[getX()][getY()] = null;
				move(Direction.SOUTHWEST);
				grid[getX()][getY()] = this;
			}
			
			if(Game.keyboard.key == "x") {
				grid[getX()][getY()] = null;
				move(Direction.SOUTH);
				grid[getX()][getY()] = this;
			}
			
			if(Game.keyboard.key == "c") {
				grid[getX()][getY()] = null;
				move(Direction.SOUTHEAST);
				grid[getX()][getY()] = this;
			}
			
			if(grid[getX()][getY()] instanceof Mho || grid[getX()][getY()] instanceof Fence ) {
				kill();
			}
		}

		return getAlive();
	}
	

	/**
	 * Jumps the player to a random non-fence grid.
	 * @param grid the array of game spaces
	 * @return whether or not the player is alive
	 */
	public boolean jump(Entity[][] grid) {
		setLastX(getX());
		setLastY(getY());
		while(true) {
			int validJumpX = (int) (1 + ((int)(grid.length - 2) * Math.random()));
			int validJumpY = (int) (1 + ((int)(grid[0].length - 2) * Math.random()));
			if(grid[validJumpX][validJumpY] instanceof Fence) {
				System.out.println("You died");
				kill();
				break;
			}
			else if(grid[validJumpX][validJumpY] instanceof Mho || grid[validJumpX][validJumpY] instanceof Player) {
				System.out.println("I hit a fence");
				continue;
			}
			else {
				setXPosition(validJumpX);
				setYPosition(validJumpY);
				grid[getX()][getY()] = this;
				System.out.println("Successful jump");
				break;
			}
		}
		return getAlive();
	}
	
	/**
	 * unused update method
	 */
	@Override
	public boolean update() {
		// TODO Auto-generated method stub
		return false;
	}
}
