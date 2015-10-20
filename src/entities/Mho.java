package entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import input.Keyboard;
import window.Game;

public class Mho extends Entity {
	
	//The image of the mho
	private BufferedImage image = null;
	
	/**
	 * Creates a Mho and sets its position to the given coordinates
	 * Sets the Mho's image to a devil
	 * 
	 * @param xPosition the x coordinate of the Mho
	 * @param yPosition the y coordinate of the Mho
	 * 
	 * Author: Joseph Rumelhart and Eric Liu
	 */
	public Mho(int xPosition, int yPosition) {
		super(xPosition, yPosition);
		//Looks for the image to display
		try{
			image = ImageIO.read(new File("images/mho.jpg"));
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}
	
	/**
	 * Move the Mho towards the player
	 * 
	 * @param player the player of the game
	 * @param grid the grid of the game
	 * 
	 * Author: Joseph Rumelhart
	 */
	public void moveMho(Player player, Entity[][] grid) {
		if(player.getX() == this.getX() || player.getY() == this.getY()) {
			moveSimple(player, grid);
		}
		else {
			moveComplex(player,grid);
		}
		grid[getLastX()][getLastY()] = null;
		grid[getX()][getY()] = this;
		
	}
	
	/**
	 * Simple movement; activates when on a direct line to the player
	 * 
	 * @param player the player of the game
	 * @param grid the grid of the game
	 * 
	 * Author: Joseph Rumelhart
	 */
	private void moveSimple(Player player, Entity[][] grid) {
		//Occurs when the mho is on top of the player
		if(player.getX() == this.getX() && player.getY() == this.getY()) {
			//sit
		}
		//On a vertical line to player
		else if(player.getX() == this.getX()) {
			if(player.getY() >= this.getY()){
				move(Direction.SOUTH);
			}
			else {
				move(Direction.NORTH);
			}
		}
		//Horizontal line to player
		else if(player.getY() == this.getY()) {
			if(player.getX() >= this.getX()){
				move(Direction.EAST);
			}
			else {
				move(Direction.WEST);
			}
		}
	}
	
	/**
	 * Complex movement; activates when not on a direct line to the player
	 * 
	 * @param player the player of the game
	 * @param grid the grid of the game
	 * Author: Joseph Rumelhart
	 * 
	 */
	private void moveComplex(Player player, Entity[][] grid) {
		//ArrayList of the entities between the mho and the player
		ArrayList<Entity> obstacles = new ArrayList<Entity>(3);
		int numMhos = 0;
		int numEmpty = 0;
		//The possible directions the mho could go in
		Direction d = null;
		Direction dlat = null;
		Direction dvert = null;
		
		//Determines the relevant squares and directions
		if(player.getX() >= this.getX() && player.getY() >= this.getY()) {
			obstacles.add(0, grid[this.getX() + 1][this.getY()]);
			obstacles.add(1, grid[this.getX() + 1][this.getY() + 1]);
			obstacles.add(2, grid[this.getX()][this.getY() + 1]);
			d = Direction.SOUTHEAST;
			dlat = Direction.EAST;
			dvert = Direction.SOUTH;
		}  
		else if(player.getX() >= this.getX() && player.getY() <= this.getY()) {
			obstacles.add(0, grid[this.getX()][this.getY() - 1 ]);
			obstacles.add(1, grid[this.getX() + 1][this.getY() - 1]);
			obstacles.add(2, grid[this.getX() + 1][this.getY()]);
			d = Direction.NORTHEAST;
			dlat = Direction.EAST;
			dvert = Direction.NORTH;
		}
		else if(player.getX() <= this.getX() && player.getY() <= this.getY()) {
			obstacles.add(0, grid[this.getX()][this.getY() - 1 ]);
			obstacles.add(1, grid[this.getX() - 1][this.getY() - 1]);
			obstacles.add(2, grid[this.getX() - 1][this.getY()]);
			d = Direction.NORTHWEST;
			dlat = Direction.WEST;
			dvert = Direction.NORTH;
		}
		else if(player.getX() <= this.getX() && player.getY() >= this.getY()) {
			obstacles.add(0, grid[this.getX() - 1][this.getY()]);
			obstacles.add(1, grid[this.getX() - 1][this.getY() + 1]);
			obstacles.add(2, grid[this.getX()][this.getY() + 1]);
			d = Direction.SOUTHWEST;
			dlat = Direction.WEST;
			dvert = Direction.SOUTH;
		}

		//Determines the content of each of the relevant squares
		for(int i = 0; i < 3; i++) {
			if(obstacles.get(i) instanceof Mho) {
				numMhos++;
			}
			else if(obstacles.get(i) == null) {
				numEmpty++;
			}
		}
		
		//Moves in the decided direction
		if(obstacles.get(0) instanceof Player) {
			move(dlat);
		}
		else if(obstacles.get(1) instanceof Player) {
			move(d);
		}
		else if(obstacles.get(2) instanceof Player) {
			move(dvert);
		}
		//If surrounded by mhos
		else if(numMhos >= 3) {
			//sit
		}
		//Moves to an empty space 
		else if (numEmpty > 0) {
			//Moves diagonally
			if(obstacles.get(1) == null) {
				move(d);
			}
			//Moves horizontally or vertically
			else {
				if(Math.abs(player.getX() - this.getX()) >= Math.abs(player.getY() - this.getY())) {
					move(dlat);
				}
				else {
					move(dvert);
				}
			}
		}
		else {
			kill();
		}
	}
	
	/**
	 * Draws the mho on the game board
	 * 
	 * @param g the graphics object to be used
	 * 
	 * Author: Eric Liu
	 */
	@Override
	public void draw(Graphics g) {
		
		//Erase the last position of the mho
		if(Keyboard.updateCycle > 0) {
			g.setColor(Color.WHITE);
			g.fillRect(getLastX()*Game.CELL_SIZE, getLastY()*Game.CELL_SIZE + Game.WINDOW_BAR, Game.CELL_SIZE, Game.CELL_SIZE);
		}
		
		//Draw the mho if it is alive
		if(getAlive()) {
			g.drawImage(image, getX() * Game.CELL_SIZE, getY() * Game.CELL_SIZE + Game.WINDOW_BAR, Game.CELL_SIZE, Game.CELL_SIZE, null);
		}
		
		//If dead, erase this mho
		if(!getAlive()) {
			g.setColor(Color.WHITE);
			g.fillRect(getX()*Game.CELL_SIZE, getY()*Game.CELL_SIZE + Game.WINDOW_BAR, Game.CELL_SIZE, Game.CELL_SIZE);
		}
	}

	/**
	 * Updates the mho to the current state of the board
	 * 
	 * @param player the current player
	 * @param grid the game board
	 * @return whether or not the mho is alive
	 * 
	 * Author: Eric Liu
	 */
	public boolean update(Player player, Entity[][] grid) {
		moveMho(player, grid);
		return getAlive();
	}

	//Unused update method inherited from Entity
	@Override
	public boolean update(Entity[][] grid) {
		// TODO Auto-generated method stub
		return false;
	}
}
