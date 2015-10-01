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
	
	private ArrayList<Integer> entityType;
	
	private double freeEntities;
	
	public Game() {
		//ArrayList of entities on the board
		entities = new ArrayList<Entity>();
		
		//ArrayList of the outer fences
		boundingFences = new ArrayList<Fence>();
		
		setSize(500, 500);
		
		setBackground(Color.WHITE);
		freeEntities = 33.0;
		
		entityType = new ArrayList<Integer>(33);
		entityType.add(0,0);
		for(int i = 1; i < 13; i++) {
			entityType.add(i,1);
			}
		for(int i = 13; i < 33; i++) {
			entityType.add(i,2);
			}
		Collections.shuffle(entityType);

		createInternalEntities(entities, entityType);
		
		
		repaint();
	}
	/**
	 * Creates the entities on the board randomly
	 * @param entities
	 * @param entityType
	 */
	public void createInternalEntities(ArrayList<Entity> entities, ArrayList<Integer> entityType) {
		for (int i = 0; i < 10 ; i++) {
			for (int j = 0; j < 10 ; j++) {
				double r = Math.random();
				//Creates threshold for creation of entity
				double threshold = (freeEntities /(100 - (i * 10) + j));
				//Determines if random number is within threshold
				if(r < threshold) {
					//Creates a player
					if(entityType.get((int) (33 - freeEntities)) == 0) {
						entities.add(new Player(i, j));
					}
					//creates a Mho
					else if(entityType.get((int) (33 - freeEntities)) == 1) {
						entities.add(new Mho(i, j));
					}
					//Creates a fence
					else {
						entities.add(new Fence(i, j));
					}
					freeEntities--;
				}
			}
			//If finished creating entities, break
			if(freeEntities <= 0) {
				break;
			}
		}
	}
	
	public void createBoundingFences() {
		
	}
	public void paint(Graphics g) {
		
	}
	
	public void update() {
		while(true) {
			
		}
	}
	
	
}
