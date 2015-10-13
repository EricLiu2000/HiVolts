package input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Keyboard implements KeyListener {

	public String key;
	
	public boolean wasTyped;
	
	public boolean getKeyTyped() {
		return wasTyped;
	}
	
	public Keyboard() {
		wasTyped = false;
	}

	//This method is called when a key is pressed
	@Override
	public void keyTyped(KeyEvent e) {
		System.out.println("key typed");
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
