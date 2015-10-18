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
	private BufferedImage image = null;
	
	/**
	 * Creates a Mho and sets its position to the given coordinates
	 * @param xPosition the x coordinate of the Mho
	 * @param yPosition the y coordinate of the Mho
	 * Author: Joseph Rumelhart
	 */
	public Mho(int xPosition, int yPosition) {
		super(xPosition, yPosition);
		try{
			image = ImageIO.read(new File("images/mho.jpg"));
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}
	
	public int getDistance(Player player) {
		int xDistance = (player.getX() - getX());
		int yDistance = (player.getY() - getY());
		//no need to square root, we just want to sort by order of distance from player.
		int distance = xDistance * xDistance + yDistance * yDistance;
		return distance;
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
		grid[getLastX()][getLastY()] = null;
		grid[getX()][getY()] = this;
		
	}
	
	/**
	 * Simple movement; activates when on a direct line to the player
	 * @param player
	 * @param grid
	 * Author: Joseph Rumelhart
	 */
	private void moveSimple(Player player, Entity[][] grid) {
		if(player.getX() == this.getX() && player.getY() == this.getY()) {
			//sit
		}
		else if(player.getX() == this.getX()) {
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
	}
	
	/**
	 * Complex movement; activates when not on a direct line to the player
	 * @param player
	 * @param grid
	 * Author: Joseph Rumelhart
	 */
	private void moveComplex(Player player, Entity[][] grid) {
		ArrayList<Entity> obstacles = new ArrayList<Entity>(3);
		int numMhos = 0;
		int numEmpty = 0;
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
		
		//Moves directly on to a player if it is directly adjacent
		if(obstacles.get(0) instanceof Player) {
			move(dlat);
		}
		else if(obstacles.get(1) instanceof Player) {
			move(d);
		}
		else if(obstacles.get(2) instanceof Player) {
			move(dvert);
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
		//If surrounded by mhos
		if(numMhos == 3) {
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
				else{
					move(dvert);
				}
			}
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
			g.drawImage(image, getX() * Game.SCALE, getY() * Game.SCALE + Game.WINDOWBAR, Game.SCALE, Game.SCALE, null);
		}
		if(!getAlive()) {
			g.setColor(Color.WHITE);
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
