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
	public void moveMho(Player player) {
		int xDiff = player.getX() - this.getX();
		int yDiff = player.getY() - this.getY();
		
		if(xDiff == 0) {
			if(yDiff > 0) {
				move(Direction.WEST);
			}
			else {
				move(Direction.EAST);
			}
		}
		else if(yDiff == 0)  {
			if(xDiff > 0) {
				move(Direction.NORTH);
			}
			else {
				move(Direction.SOUTH);
			}
		}
		else if(xDiff > 0){
			
		}
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
