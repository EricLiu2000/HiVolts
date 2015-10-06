package entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Player extends Entity {

	public boolean playerTurn;
	
	BufferedImage sprite = null;
	
	public Player(int xPosition, int yPosition) {
		super(xPosition, yPosition);
		playerTurn = true;
		
		//Accesses the image and displays an error message if it is not found
		try{
			sprite = ImageIO.read(new File("images/smily.jpg"));
		} 
		
		catch(IOException e) {
			System.out.println("Error: System not found");
		}
		
	}

	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update() {
		if(getAlive() == true) {
			//update stuff here
		}
		
		
	}
	
	/**
	 * Gets whether or not it is the player's turn
	 * 
	 * @return if it is the turn of the player
	 */
	public boolean getPlayerTurn() {
		return playerTurn;
	}

}
