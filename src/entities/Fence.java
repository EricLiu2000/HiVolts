package entities;

import java.awt.Color;
import java.awt.Graphics;

import window.Game;

public class Fence extends Entity {

	public Fence(int xPosition, int yPosition) {
		super(xPosition, yPosition);
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(Color.DARK_GRAY);
		g.fillRect(getX()*Entity.SCALE, getY()*Entity.SCALE + Game.WINDOWBAR, Entity.SCALE, Entity.SCALE);
		
	}

	@Override
	public boolean update() {
		// TODO Auto-generated method stub
		return false;
	}
}
