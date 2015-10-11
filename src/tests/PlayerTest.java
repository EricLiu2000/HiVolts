package tests;

import static org.junit.Assert.*; 

import org.junit.Test;

import entities.*;
import window.Game;

public class PlayerTest extends EntityTest {

	@Override
	public void testUpdate() {
		Player p1 = new Player(1,1);
		p1.update();
	}

	@Override
	public void testGetAlive() {
		Game g = new Game();
		Player p1 = new Player(1,1);
		assertTrue("Mho is not alive", p1.getAlive());
	}
}
