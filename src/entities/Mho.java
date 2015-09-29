package entities;

import java.awt.Graphics;

public class Mho extends Entity {
	private Player player;
	
	public Mho(int xPosition, int yPosition, Player player) {
		super(xPosition, yPosition);
		this.player = player;
	}
	/**
	 * Move the Mho towards the player
	 */
	public void moveMho() {
		//If the mho is in the same column as the player
		if(this.getX() == player.getX()) {
			if(this.getY() < player.getY()) {
				move(Direction.SOUTH);
			}
			if(this.getY() > player.getY()) {
				move(Direction.NORTH);
			}
		}
		
		//If the mho is in the same row as the player
		if(this.getY() == player.getY()) {
			if(this.getX() < player.getX()) {
				move(Direction.EAST);
			}
			if(this.getX() > player.getX()) {
				move(Direction.WEST);
			}
		}
		
		
	}
	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}
}
