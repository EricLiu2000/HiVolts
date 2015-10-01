package entities;

import java.awt.Graphics;

public class Mho extends Entity {
	public Mho(int xPosition, int yPosition) {
		super(xPosition, yPosition);
	}
	/**
	 * Move the Mho towards the player
	 */
	public void moveMho(Player player) {
		if(this.getX() == player.getX() || (this.getY() == player.getY())) {
			
		}
		else {
			
		}
	}
	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void update() {
		if(getAlive()) {
			//update stuff here
		}
		
	}
}
