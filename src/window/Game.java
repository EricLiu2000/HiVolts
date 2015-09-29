package window;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JFrame;

import entities.Entity;
import entities.Fence;
import entities.Mho;
import entities.Player;

public class Game extends JFrame {
	
	//Default serialVersionUID
	private static final long serialVersionUID = 1L;
	
	private ArrayList<Entity> entities;
	
	private ArrayList<Fence> boundingFences;
	
	private double freeEntities;
	
	public Game() {
		//ArrayList of entities on the board
		entities = new ArrayList<Entity>();
		
		//ArrayList of the outer fences
		boundingFences = new ArrayList<Fence>();
		
		setSize(500, 500);
		
		setBackground(Color.WHITE);
		
		//The number of entities still to be created
		freeEntities = 33.0;
		

		//Creates the entities on the board randomly
		for (int i = 0; i < 10 ; i++) {
			for (int j = 0; j < 10 ; j++) {
				double r = Math.random();
				//Creates threshold for creation of entity
				double threshold = (freeEntities /(100 - (i * 10) + j));
				//Determines if random number is within threshold
				if(r < threshold) {
					//Creates new entity
					entities.add(new Entity(i, j));
					freeEntities--;
				}
			}
			//If finished creating entities, break
			if(freeEntities <= 0) {
				break;
			}
		}
		
		repaint();
	}
	
	public void paint(Graphics g) {
		
	}
	
	public void update() {
		while(true) {
			
		}
	}
	
	
}
