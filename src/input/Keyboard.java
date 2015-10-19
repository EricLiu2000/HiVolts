package input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import window.Game;

public class Keyboard implements KeyListener {

	//The key that was pressed
	public String key;

	//The game that this keyboard belongs to
	public Game game;
	
	//The number of updates that occurred this game
	public static int updateCycle;
	
	/**
	 * Creates a keyboard with its number of updates set to 0
	 * 
	 * @param game the game that this keyboard is controlling
	 * Author: Eric Liu
	 */
	public Keyboard(Game game) {
		this.game = game;
		updateCycle = 0;
	}

	/**
	 * This method is called when a key is typed
	 * It calls the update method of game
	 * 
	 * @param e the event that is fired when a key is typed
	 * 
	 * Author: Eric Liu
	 */
	@Override
	public void keyTyped(KeyEvent e) {
		
		//Increases the number of updates by one
		updateCycle ++;
		
		//Only updates if the player is alive
		if(game.player.getAlive()) {
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
			if(e.getKeyChar() == 'j') {
				key = "j";
				//In order to maintain the player turn, only the player updates
				game.player.update(game.getGrid());
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
			if(e.getKeyChar() == 's') {
				key = "s";
				game.update();
			}
		}
	}

	//Unused KeyListener method
	@Override
	public void keyPressed(KeyEvent e) {}

	//Unused KeyListener method
	@Override
	public void keyReleased(KeyEvent e) {}

}
