package tests;

import static org.junit.Assert.*; 

import org.junit.Test;

import entities.*;
import window.Game;

public class PlayerTest extends EntityTest {

	Game g = new Game();
	@Override
	public void testUpdate() {
		Player p1 = new Player(1,1, g);
	//	p1.update();
	}

	@Override
	public void testGetAlive() {
		Player p1 = new Player(1,1, g);
		assertTrue("Mho is not alive", p1.getAlive());
	}
}
