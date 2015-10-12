package entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import images.*;
import entities.Entity.Direction;
import window.Game;

public class Player extends Entity {

	public boolean playerTurn;
	
	private File imageFile;
	
	BufferedImage sprite = null;
	
	public Player(int xPosition, int yPosition) {
		super(xPosition, yPosition);
		
		playerTurn = true;
		
		imageFile = new File("images/Player.jpg");
		
		//Accesses the image and displays an error message if it is not found
		try{
			sprite = ImageIO.read(imageFile);
		} 
		
		catch(IOException e) {
			System.out.println("Image not found");
		}
		
		sprite = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
		
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(Color.GREEN);
		g.fillRect(getX()*Entity.SCALE, getY()*Entity.SCALE + Game.WINDOWBAR, Entity.SCALE, Entity.SCALE);
	}

	public boolean update() {
		
		if(Game.keyboard.getKeyTyped()) {
			System.out.println("called");
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
				//jump
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
			playerTurn = false;
		}
		return getAlive();
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
