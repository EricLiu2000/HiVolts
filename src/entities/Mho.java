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
			
		}
		
		//If the mho is in the same row as the player
		if(this.getY() == player.getY()) {
			
		}
	}
	
	public void moveUp(int distance) {
		
	}
	
}
