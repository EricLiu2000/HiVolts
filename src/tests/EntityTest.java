package tests;

import static org.junit.Assert.*;

import org.junit.Test;

public abstract class EntityTest {
	
	@Test
	public abstract void testUpdate();
	
	@Test
	public abstract void testGetAlive();
}
