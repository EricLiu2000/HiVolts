package run;

import javax.swing.JFrame;

import window.Game;

//This class is used to run the program.
public class Run {
	//The main method
	public static void main(String[] args) {
		//Constructs a new Game, which runs it.
		Game game = new Game();
		game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		game.setVisible(true);
	}

}
