package entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import input.Keyboard;
import window.Game;
import window.Game.GameState;

public class Player extends Entity {
	private Game game;
	
	BufferedImage image = null;
	
	/**
	 * Creates a player and sets its position to the desired coordinates
	 * @param xPosition the desired x coordinate
	 * @param yPosition the desired y coordinate
	 * Author: Joseph Rumelhart
	 */
	public Player(int xPosition, int yPosition, Game game) {
		
		super(xPosition, yPosition);
		this.game = game;
		try{
			image = ImageIO.read(new File("images/player.png"));
		}
		catch(IOException e){
			System.out.println("IMAGE NOT WORKING");
		}
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

//		if(getAlive()) {
//			g.setColor(Color.GREEN);
//			g.fillRect(getX()*Game.SCALE, getY()*Game.SCALE + Game.WINDOWBAR, Game.SCALE, Game.SCALE);
//		}
//		
//		if(!getAlive()) {
//			g.setColor(Color.WHITE);
//			g.fillRect(getX()*Game.SCALE, getY()*Game.SCALE + Game.WINDOWBAR, Game.SCALE, Game.SCALE);
//		}
		
		//g.drawImage(image, getX()*Game.SCALE, getY()*Game.SCALE, null);
		g.drawImage(image, getX() * Game.SCALE, getY() * Game.SCALE + Game.WINDOWBAR, Game.SCALE, Game.SCALE, null);
		
		for(int i = 1; i <= 11; i++) {
			g.setColor(Color.BLACK);
			g.drawLine(i*Game.SCALE, 0 + Game.WINDOWBAR, i*Game.SCALE, 12*Game.SCALE + Game.WINDOWBAR);
			g.drawLine(0, i*Game.SCALE + Game.WINDOWBAR, 12*Game.SCALE, i*Game.SCALE + Game.WINDOWBAR);
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
				//sit and do nothing
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
			
			//Jumps the player to a random non-fence position
			if(Game.keyboard.key == "j") {
				grid[getX()][getY()] = null;
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
				game.endGame(GameState.JUMPED_ON_MHO);
				game.repaint();
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
				draw(game.getGraphics());
				break;
			}
		}
		return getAlive();
	}
}
