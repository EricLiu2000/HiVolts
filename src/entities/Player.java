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
	}

	public boolean update(Entity[][] x) {
		if(getAlive() == true) {
			if(Game.keyboard.key == "q") {
				move(Direction.NORTHWEST);
			}
			
			if(Game.keyboard.key == "w") {
				move(Direction.NORTH);
			}
			
			if(Game.keyboard.key == "e") {
				move(Direction.NORTHEAST);
			}
			
			if(Game.keyboard.key == "a") {
				move(Direction.WEST);
			}
			
			if(Game.keyboard.key == "s") {
				setLastX(getX());
				setLastY(getY());
				this.jump(x);
			}
			
			if(Game.keyboard.key == "d") {
				move(Direction.EAST);
			}
			
			if(Game.keyboard.key == "z") {
				move(Direction.SOUTHWEST);
			}
			
			if(Game.keyboard.key == "x") {
				move(Direction.SOUTH);
			}
			
			if(Game.keyboard.key == "c") {
				move(Direction.SOUTHEAST);
			}
			
			if(x[getX()][getY()] instanceof Mho || x[getX()][getY()] instanceof Fence ) {
				setAlive(false);
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
				setAlive(false);
				break;
			}
			else if(grid[validJumpX][validJumpY] instanceof Mho || grid[validJumpX][validJumpY] instanceof Player) {
				System.out.println("I hit a fence");
				continue;
			}
			else {
				setXPosition(validJumpX);
				setYPosition(validJumpY);
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
