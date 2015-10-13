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
	public void keyPressed(KeyEvent e) {
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
