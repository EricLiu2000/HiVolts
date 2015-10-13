package entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import input.Keyboard;
import window.Game;

public class Player extends Entity {

	public boolean playerTurn;

	BufferedImage sprite = null;
	
	public Player(int xPosition, int yPosition) {
		super(xPosition, yPosition);
	
		playerTurn = true;
	}

	@Override
	public void draw(Graphics g) {
		
		if(Keyboard.updateCycle > 0) {
			g.setColor(Color.WHITE);
			g.fillRect(getLastX()*Game.SCALE, getLastY()*Game.SCALE + Game.WINDOWBAR, Game.SCALE, Game.SCALE);
		}
		
		g.setColor(Color.GREEN);
		g.fillRect(getX()*Game.SCALE, getY()*Game.SCALE + Game.WINDOWBAR, Game.SCALE, Game.SCALE);
	}

	public boolean update(Entity[][] x) {
		if(getAlive() == true) {
			if(Game.keyboard.key == "q") {
				move(Direction.NORTHWEST);
				playerTurn = false;
			}
			if(Game.keyboard.key == "w") {
				move(Direction.NORTH);
				playerTurn = false;
			}
			if(Game.keyboard.key == "e") {
				move(Direction.NORTHEAST);
				playerTurn = false;
			}
			if(Game.keyboard.key == "a") {
				move(Direction.WEST);
				playerTurn = false;
			}
			if(Game.keyboard.key == "s") {
				setLastX(getX());
				setLastY(getY());
				this.jump(x);
				playerTurn = true;
			}
			if(Game.keyboard.key == "d") {
				move(Direction.EAST);
				playerTurn = false;
			}
			if(Game.keyboard.key == "z") {
				move(Direction.SOUTHWEST);
				playerTurn = false;
			}
			if(Game.keyboard.key == "x") {
				move(Direction.SOUTH);
				playerTurn = false;
			}
			if(Game.keyboard.key == "c") {
				move(Direction.SOUTHEAST);
				playerTurn = false;
			}
			
			System.out.println("X Position: " + getX());
			System.out.println("Y Position: " + getY());
			
			System.out.println("Last X Position: " + getLastX());
			System.out.println("Last Y Position: " + getLastY());
		}
		
		return getAlive();
	}
	
	/**
	 * Gets whether or not it is the player's turn
	 * @return if it is the turn of the player
	 */
	public boolean getPlayerTurn() {
		return playerTurn;
	}

	/**
	 * jumps the player to a random empty space or mho on the board
	 * @param x the array of game spaces
	 * @return is alive
	 */
	public boolean jump(Entity[][] x) {
		setLastX(getX());
		setLastY(getY());
		while(true) {
			int validJumpX = (int) (1 + ((int)(x.length - 2) * Math.random()));
			int validJumpY = (int) (1 + ((int)(x[0].length - 2) * Math.random()));
			if(x[validJumpX][validJumpY] instanceof Fence) {
				setAlive(false);
				break;
			}
			else if(x[validJumpX][validJumpY] instanceof Mho || x[validJumpX][validJumpY] instanceof Player) {
				continue;
			}
			else {
				setXPosition(validJumpX);
				setYPosition(validJumpY);
				break;
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
