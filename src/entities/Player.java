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
	
	//The game that the player is in
	private Game game;
	
	//The image of the player
	private BufferedImage image = null;
	
	/**
	 * Creates a player and sets its position to the desired coordinates.
	 * Sets the player's image to an angel
	 * 
	 * @param xPosition the desired x coordinate
	 * @param yPosition the desired y coordinate
	 * 
	 * Author: Joseph Rumelhart and Eric Liu
	 */
	public Player(int xPosition, int yPosition, Game game) {
		//Invokes superclass constructor
		super(xPosition, yPosition);
		
		//Sets the game that this player is in to the specified game
		this.game = game;
		
		//Creates the player image
		try{
			image = ImageIO.read(new File("images/player.jpg"));
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}

	/**
	 * Draws the player on the game board
	 * 
	 * @param g The graphics object to be used for drawing
	 * 
	 * Author: Eric Liu
	 */
	@Override
	public void draw(Graphics g) {
		
		//Erases the player's previous position
		if(Keyboard.updateCycle > 0) {
			g.setColor(Color.WHITE);
			g.fillRect(getLastX()*Game.CELL_SIZE, getLastY()*Game.CELL_SIZE + Game.WINDOW_BAR, Game.CELL_SIZE, Game.CELL_SIZE);
		}

		//Draws the player at its position
		g.drawImage(image, getX() * Game.CELL_SIZE, getY() * Game.CELL_SIZE + Game.WINDOW_BAR, Game.CELL_SIZE, Game.CELL_SIZE, null);
		
		//Redraws the lines of the game.
		//This is necessary for the lines to remain intact after the player jumps
		for(int i = 1; i <= 11; i++) {
			g.setColor(Color.BLACK);
			g.drawLine(i*Game.CELL_SIZE, 0 + Game.WINDOW_BAR, i*Game.CELL_SIZE, 12*Game.CELL_SIZE + Game.WINDOW_BAR);
			g.drawLine(0, i*Game.CELL_SIZE + Game.WINDOW_BAR, 12*Game.CELL_SIZE, i*Game.CELL_SIZE + Game.WINDOW_BAR);
		}
	}

	/**
	 * Moves the player according to keyboard input
	 * This method is called when a key is pressed
	 * 
	 * Author: Eric Liu
	 */
	@Override
	public boolean update(Entity[][] grid) {
		if(getAlive() == true) {
			//When the player moves, its previous position in the grid is set to null
			//Its new position is now accurately represented in the grid
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
			
			//The player passes turn by sitting
			if(Game.keyboard.key == "s") {
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
	 * Jumps the player to a random non-fence position
	 * 
	 * @param grid the array of game spaces
	 * @return whether or not the player is alive
	 * 
	 * Author: Joseph Rumelhart
	 */
	public boolean jump(Entity[][] grid) {
		//Before jumping, set last position
		setLastX(getX());
		setLastY(getY());
		
		//Keep cycling through until jump resolves
		while(true) {
			
			//The potential coordinates to jump to
			int jumpXCoordinate = (int) (1 + ((int)(grid.length - 2) * Math.random()));
			int jumpYCoordinate = (int) (1 + ((int)(grid[0].length - 2) * Math.random()));
			
			//When the jump coordinates are on top of a mho
			if(grid[jumpXCoordinate][jumpYCoordinate] instanceof Mho) {
				kill();
				game.endGame(GameState.JUMPED_ON_MHO);
				game.repaint();
				break;
			}
			
			//When the jump coordinates are invalid(on a fence)
			else if(grid[jumpXCoordinate][jumpYCoordinate] instanceof Fence || grid[jumpXCoordinate][jumpYCoordinate] instanceof Player) {
				continue;
			}
			
			//When the jump coordinates are valid
			else {
				setXPosition(jumpXCoordinate);
				setYPosition(jumpYCoordinate);
				grid[getX()][getY()] = this;
				draw(game.getGraphics());
				break;
			}
		}
		return getAlive();
	}
}
