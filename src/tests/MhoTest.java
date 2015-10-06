package tests;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

import entities.Mho;
import entities.Player;

public class MhoTest {
	@Test
	public void moveTest1() {
		Player p1 = new Player(1,1);
		Mho m1 = new Mho(3,1);
		m1.moveMho(p1);
		assertEquals(2, m1.getX());
	}
}