package window;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JFrame;

import entities.Entity;
import entities.Fence;
import entities.Mho;
import entities.Player;

public class Game extends JFrame {
	
	//Default serialVersionUID
	private static final long serialVersionUID = 1L;

	private Player player;
	
	private ArrayList<Entity> entities;
	
	private ArrayList<Fence> boundingFences;
	
	private int[] [] grid;

	public boolean playerTurn;
	
	public Game() {
		entities = new ArrayList<Entity>();
		
		boundingFences = new ArrayList<Fence>();
		
		setSize(500, 500);
		
		setBackground(Color.WHITE);
		
		grid = new int[11] [11];

		player = new Player(10, 10);
		
		playerTurn = true;
		
		entities.add(player);
		for (int i = 0; i < 10 ; i++) {
			for (int j = 0; j < 10 ; j++) {
				
			}
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
