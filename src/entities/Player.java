package entities;

import java.awt.Graphics;

public class Player extends Entity {

	public boolean playerTurn;
	
	public Player(int xPosition, int yPosition) {
		super(xPosition, yPosition);
		playerTurn = true;
	}

	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update() {
		if(getAlive() == true) {
			//update stuff here
		}
		
	}

}
