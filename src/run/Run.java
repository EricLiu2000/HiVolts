package run;

import javax.swing.JFrame;

import window.Game;

//This class is used to run the program.
public class Run {
	/**
	 * 
	 * Author: Eric Liu
	 */
	public static void main(String[] args) {
		runGame();
	}
	
	public static void runGame() {
		Game game = new Game();
		game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		game.setVisible(true);
	}
}
