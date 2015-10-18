package entities;

import java.awt.Color;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import window.Game;

public class Fence extends Entity {

	private BufferedImage image = null;
	
	/**
	 * Creates a Fence with the given coordinates
	 * @param xPosition the x coordinate of the Fence
	 * @param yPosition the y coordinate of the Fence
	 * Author: Joseph Rumelhart
	 */
	public Fence(int xPosition, int yPosition) {
		super(xPosition, yPosition);
		
		try{
			image = ImageIO.read(new File("images/fence.jpg"));
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}

	/**
	 * Creates the visible fence on the game board
	 * Author: Eric Liu
	 */
	@Override
	public void draw(Graphics g) {
		g.drawImage(image, getX() * Game.SCALE, getY() * Game.SCALE + Game.WINDOWBAR, Game.SCALE, Game.SCALE, null);
	}

	/**
	 * Updates the fence
	 * Author: Eric Liu
	 */
	@Override
	public boolean update(Entity[][] grid) {
		grid[getX()][getY()] = this;
		return getAlive();
	}
}
