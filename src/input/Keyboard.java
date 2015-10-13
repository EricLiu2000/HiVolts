package input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import window.Game;

public class Keyboard implements KeyListener {

	public String key;
	
	public boolean wasTyped;
	
	public Game game;
	
	public Keyboard(Game game) {
		wasTyped = false;
		this.game = game;
	}
	public boolean getKeyTyped() {
		return wasTyped;
	}
	
	

	//This method is called when a key is pressed
	@Override
	public void keyTyped(KeyEvent e) {
		wasTyped = true;
		
		if(e.getKeyCode() == KeyEvent.VK_Q) {
			key = "q";
		}
		if(e.getKeyCode() == KeyEvent.VK_W) {
			key = "w";
		}
		if(e.getKeyCode() == KeyEvent.VK_E) {
			key = "e";
		}
		if(e.getKeyCode() == KeyEvent.VK_A) {
			key = "a";
		}
		if(e.getKeyCode() == KeyEvent.VK_S) {
			key = "s";
		}
		if(e.getKeyCode() == KeyEvent.VK_D) {
			key = "d";
		}
		if(e.getKeyCode() == KeyEvent.VK_Z) {
			key = "z";
		}
		if(e.getKeyCode() == KeyEvent.VK_X) {
			key = "x";
		}
		if(e.getKeyCode() == KeyEvent.VK_C) {
			key = "c";
		}
		game.update();
		wasTyped = false;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
