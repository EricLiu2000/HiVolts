package input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import window.Game;

public class Keyboard implements KeyListener {

	public String key;

	public Game game;
	
	//The number of updates that occured this game
	public static int updateCycle;
	
	/**
	 * 
	 * @param game the game that this keyboard is controlling
	 * Author: Eric Liu
	 */
	public Keyboard(Game game) {
		this.game = game;
		updateCycle = 0;
	}

	/**
	 * Called when a key is typed
	 * calls game update method
	 * Author: Eric Liu
	 */
	@Override
	public void keyTyped(KeyEvent e) {

		updateCycle ++;
		
		//Gets input from the keyboard
		if(e.getKeyChar() == 'q') {
			key = "q";
			game.update();
		}
		if(e.getKeyChar() == 'w') {
			key = "w";
			game.update();
		}
		if(e.getKeyChar() == 'e') {
			key = "e";
			game.update();
		}
		if(e.getKeyChar() == 'a') {
			key = "a";
			game.update();
		}
		if(e.getKeyChar() == 's') {
			key = "s";
			game.player.update(game.getGrid());
			game.repaint();
		}
		if(e.getKeyChar() == 'd') {
			key = "d";
			game.update();
		}
		if(e.getKeyChar() == 'z') {
			key = "z";
			game.update();
		}
		if(e.getKeyChar() == 'x') {
			key = "x";
			game.update();
		}
		if(e.getKeyChar() == 'c') {
			key = "c";
			game.update();
		}
		
	}

	@Override
	public void keyPressed(KeyEvent e) {}

	@Override
	public void keyReleased(KeyEvent e) {}

}
