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
		
		try{
			sprite = ImageIO.read(new File("images/smily.jpg"));
		} 
		
		catch(IOException e) {
			
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

}
