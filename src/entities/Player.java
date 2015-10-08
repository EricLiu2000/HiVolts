package entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import entities.Entity.Direction;
import window.Game;

public class Player extends Entity {

	public boolean playerTurn;
	
	BufferedImage sprite = null;
	
	public Player(int xPosition, int yPosition) {
		super(xPosition, yPosition);
		playerTurn = true;
		
		//Accesses the image and displays an error message if it is not found
		try{
			sprite = ImageIO.read(new File("images/Player.jpg"));
		} 
		
		catch(IOException e) {
			System.out.println("Error: System not found");
		}
		
	}

	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		
	}

	public boolean update() {
		if(Game.keyboard.getKeyTyped()) {
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
