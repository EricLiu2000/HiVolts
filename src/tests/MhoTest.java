package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import entities.*;

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
		m1.moveMho(p1);
		assertEquals(2,m1.getX());
	}

	@Override
	public void testGetAlive() {
		Mho m1 = new Mho(1,1);
		assertTrue("Mho is alive", m1.getAlive());
	}
	
	@Test
	public void testGetAliveNeg() {
		Mho m1 = new Mho(1,1);
		m1.kill();
		assertTrue("Mho is alive", m1.getAlive());
	}
}
