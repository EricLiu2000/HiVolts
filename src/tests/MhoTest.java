package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import entities.*;
import window.Game;

public class MhoTest extends EntityTest {

	@Test
	public void testUpdate() {
		Mho m1 = new Mho(1,1);
		m1.update();
		fail("Not yet implemented");
	}

	@Test
	public void testMoveMho() {
		Mho m1 = new Mho(1,1);
		Player p1 = new Player(3,1);
		Game g1 = new Game();
		m1.moveMho(p1,g1.getGrid());
		assertEquals(2,m1.getX());
	}

	@Override
	public void testGetAlive() {
		Mho m1 = new Mho(1,1);
		assertTrue("Mho is alive", m1.getAlive());
	}
}
