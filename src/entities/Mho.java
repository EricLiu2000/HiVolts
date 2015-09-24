package entities;

import java.awt.Graphics;

public class Mho extends Entity {
	private Player player;
	
	private int xPosition;
	
	private int yPosition;
	
	public Mho(int xPosition, int yPosition, Player player) {
		super(xPosition, yPosition);
		this.player = player;
	}
	
	public void move() {
		//If the mho is in the same column as the player
		if(this.getX() == player.getX()) {
			if(this.getY() < player.getY()) {
				moveDistance(0, 1);
			}
			if(this.getY() > player.getY()) {
				moveDistance(0, -1);
			}
		}
		
		//If the mho is in the same row as the player
		if(this.getY() == player.getY()) {
			if(this.getX() < player.getX()) {
				moveDistance(0, 1);
			}
			if(this.getX() > player.getX()) {
				moveDistance(0, -1);
			}
		}
		
		
	}
	
	public void moveDistance(int xDistance, int yDistance) {
		xPosition += xDistance;
		yPosition += yDistance;
	}
	
}
