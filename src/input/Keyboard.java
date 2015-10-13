package input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import window.Game;

public class Keyboard implements KeyListener {

	public String key;

	public Game game;
	
	public static int updateCycle;
	
	public Keyboard(Game game) {
		this.game = game;
		updateCycle = 0;
	}

	//This method is called when a key is pressed
	@Override
	public void keyTyped(KeyEvent e) {

		updateCycle ++;
		
		if(e.getKeyChar() == 'q') {
			key = "q";
		}
		if(e.getKeyChar() == 'w') {
			key = "w";
		}
		if(e.getKeyChar() == 'e') {
			key = "e";
		}
		if(e.getKeyChar() == 'a') {
			key = "a";
		}
		if(e.getKeyChar() == 's') {
			key = "s";
		}
		if(e.getKeyChar() == 'd') {
			key = "d";
		}
		if(e.getKeyChar() == 'z') {
			key = "z";
		}
		if(e.getKeyChar() == 'x') {
			key = "x";
		}
		if(e.getKeyChar() == 'c') {
			key = "c";
		}
		game.update();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
