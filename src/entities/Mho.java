package entities;

import java.awt.Color;
import java.awt.Graphics;

import window.Game;

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
		g.setColor(Color.RED);
		g.fillRect(getX()*Entity.SCALE, getY()*Entity.SCALE + Game.WINDOWBAR, Entity.SCALE, Entity.SCALE);
	}

	@Override
	public boolean update() {
		// TODO Auto-generated method stub
		return getAlive();
	}
}
