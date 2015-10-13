package entities;

import java.awt.Color;
import java.awt.Graphics;

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
		g.setColor(Color.RED);
		g.fillRect(getX()*Game.SCALE, getY()*Game.SCALE + Game.WINDOWBAR, Game.SCALE, Game.SCALE);
	}

	@Override
	public boolean update() {
		// TODO Auto-generated method stub
		return getAlive();
	}
}
