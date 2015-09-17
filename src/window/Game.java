package window;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JFrame;

import entities.Fence;
import entities.Mho;
import entities.Player;

public class Game extends JFrame {
	
	//Default serialVersionUID
	private static final long serialVersionUID = 1L;

	private Player player;
	
	private ArrayList<Mho> mhos;
	
	private ArrayList<Fence> fences;
	
	private int[] [] grid;

	public boolean playerTurn;
	
	public Game() {
		setSize(500, 500);
		
		setBackground(Color.WHITE);
		
		grid = new int[11] [11];

		player = new Player(10, 10);
		
		playerTurn = true;
		
		for(int i = 1; i <= 12; i++) {
			mhos.add(new Mho((int) (10 * Math.random()) + 1, (int) (10 * Math.random()) + 1, player));
		}
		
		repaint();
	}
	
	public void paint(Graphics g) {
		
	}
	
	public void update() {
		while(true) {
			//update stuff here
		}
	}
	
	
}
