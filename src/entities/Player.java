package entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import images.*;
import input.Keyboard;
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
		g.fillRect(getX()*Game.SCALE, getY()*Game.SCALE + Game.WINDOWBAR, Game.SCALE, Game.SCALE);
	}

	public boolean update(Entity[][] x) {
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
			playerTurn = false;
		
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

	public boolean jump(Entity[][] x) {
		while(true) {
			int foo = (int) (1 + ((int)(x.length - 2) * Math.random()));
			int bar = (int) (1 + ((int)(x[0].length - 2) * Math.random()));
			if(x[foo][bar] instanceof Fence) {
				setAlive(false);
				break;
			}
			else if(x[foo][bar] instanceof Mho || x[foo][bar] instanceof Player) {
				continue;
			}
			else {
				setxPosition(foo);
				setyPosition(bar);
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
