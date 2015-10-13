package entities;

import java.awt.Color;
import java.awt.Graphics;

import input.Keyboard;
import window.Game;

public class Mho extends Entity {
	
	/**
	 * Creates a Mho and sets its position to the given coordinates
	 * @param xPosition the x coordinate of the Mho
	 * @param yPosition the y coordinate of the Mho
	 */
	public Mho(int xPosition, int yPosition) {
		super(xPosition, yPosition);
	}
	
	/**
	 * Move the Mho towards the player
	 */
	public void moveMho(Player player, Entity[][] grid) {
		int xDiff = player.getX() - this.getX();
		int yDiff = player.getY() - this.getY();
	}
	
	@Override
	public void draw(Graphics g) {
		
		if(Keyboard.updateCycle > 0) {
			g.setColor(Color.WHITE);
			g.fillRect(getLastX()*Game.SCALE, getLastY()*Game.SCALE + Game.WINDOWBAR, Game.SCALE, Game.SCALE);
		}
		
		g.setColor(Color.RED);
		g.fillRect(getX()*Game.SCALE, getY()*Game.SCALE + Game.WINDOWBAR, Game.SCALE, Game.SCALE);
	}

	public boolean update() {
		return getAlive();
	}
}
