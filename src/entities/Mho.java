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
		if(getX() == player.getX() && getY() > player.getY()) {
			move(Direction.NORTH);
		}
		else if(getX() == player.getX() && getY() < player.getY()) {
			move(Direction.SOUTH);
		}
		else if(getY() == player.getY() && getX() > player.getX()) {
			move(Direction.WEST);
		}
		else if(getY() == player.getY() && getX() < player.getX()) {
			move(Direction.EAST);
		}
		else {
			moveDiagonal();
		}
	}
	
	private void moveDiagonal() {
		
	}
	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
	}
	
	public boolean update(Player player) {
		moveMho(player);
		return getAlive();
	}
}
