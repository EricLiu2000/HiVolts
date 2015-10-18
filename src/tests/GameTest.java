package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import entities.Player;
import window.Game;

public class GameTest {

	@Test
	public void test() {
		boolean hasPlayer = false;
		int runLength = 5000;
		for(int m = 0; m < runLength; m++) {
			Game g1 = new Game();
			hasPlayer = false;
			for(int i = 0; i < 11; i++) {
				for(int j = 0; j < 11; j++) {
					if(g1.getGrid()[i][j] instanceof Player) {
						hasPlayer = true;
					}
				}
			}
			if(!(hasPlayer)) {
				break;
			}
			
		}
		assertTrue("Does not have player", hasPlayer);
	}

}
