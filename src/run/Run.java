package run;

import javax.swing.JFrame;

import window.Game;

public class Run {

	public static void main(String[] args) {
		Game game = new Game();
		game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		game.setVisible(true);
	}

}
