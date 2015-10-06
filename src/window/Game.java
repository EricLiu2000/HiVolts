package window;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JFrame;

import entities.Entity;
import entities.Entity.Direction;
import entities.Fence;
import entities.Mho;
import entities.Player;
import input.Keyboard;

public class Game extends JFrame {
	
	//Default serialVersionUID
	private static final long serialVersionUID = 1L;
	
	private ArrayList<Entity> entities;
	
	private ArrayList<Fence> boundingFences;
	
	private ArrayList<Type> entityType;
	
	private Entity[][] grid;
	
	private double freeEntities;
	
	private Player player;
	
	private Keyboard keyboard;
	
	//Enum that represents the type of entity to be created
	public enum Type{
		FENCE,
		MHO,
		PLAYER
	}
	Type type;
	
	/**
	 * Game constructor
	 * Creates the entities
	 */
	public Game() {
		//The object used for keyboard input
		keyboard = new Keyboard();
		
		//ArrayList of entities on the board
		entities = new ArrayList<Entity>();
		
		//ArrayList of the outer fences
		boundingFences = new ArrayList<Fence>(44);
		
		setSize(500, 500);
		
		setBackground(Color.WHITE);
		//Sets the number of internal entities that still need to be created
		freeEntities = 33.0;
		
		//Creates the 2D array
		grid = new Entity[12][12];
		
		//Randomly creates the types of entities
		entityType = new ArrayList<Type>(33);
		entityType.add(0, Type.PLAYER);
		for(int i = 1; i < 13; i++) {
			entityType.add(i, Type.MHO);
			}
		for(int i = 13; i < 33; i++) {
			entityType.add(i, Type.FENCE);
			}
		Collections.shuffle(entityType);
		
		//Creates the internal entities
		createInternalEntities(entities, entityType);
		
		//Creates the bounding fences
		createBoundingFences(boundingFences);
		
		repaint();
	}
	/**
	 * Creates the entities on the board randomly
	 * @param entities arrayList of all entities on the inside of the board
	 * @param entityType arrayList storing the type of entity to be created at each position
	 */
	public void createInternalEntities(ArrayList<Entity> entities, ArrayList<Type> entityType) {
		for (int i = 1; i < 11 ; i++) {
			for (int j = 1; j < 11 ; j++) {
				double r = Math.random();
				//Creates threshold for creation of entity
				double threshold = (freeEntities /(100 - (i * 10) + j));
				//Determines if random number is within threshold
				if(r < threshold) {
					//Creates a player
					if(entityType.get((int) (33 - freeEntities)) == Type.PLAYER) {
						player = new Player(i, j);
						entities.add(player);
					}
					//creates a Mho
					else if(entityType.get((int) (33 - freeEntities)) == Type.MHO) {
						entities.add(new Mho(i, j));
					}
					//Creates a fence
					else {
						entities.add(new Fence(i, j));
					}
					freeEntities--;
					grid[i][j] = entities.get((int) (33-freeEntities));
				}
			}
			//If finished creating entities, break
			if(freeEntities <= 0) {
				break;
			}
		}
	}
	/**
	 * creates the bounding fences that appear in every game
	 * @param boundingFences an arrayList of fences
	 */
	public void createBoundingFences(ArrayList<Fence> boundingFences) {
		for(int i = 0; i < boundingFences.size(); i++) {
			
		}
	}
	
	/**
	 * Draws the current state of the game on the screen
	 */
	public void paint(Graphics g) {
		
	}
	
	/**
	 * Update method
	 */
	public void run() {
		while(true) {
			if(player.playerTurn == true) {
				//Sets wasTyped to false so that the game wait for player input
				keyboard.wasTyped = false;
				
				//This block is run when a key is typed
				if(keyboard.getKeyTyped()) {
					if(keyboard.key == "q") {
						player.move(Direction.NORTHWEST);
					}
					if(keyboard.key == "w") {
						player.move(Direction.NORTH);
					}
					if(keyboard.key == "e") {
						player.move(Direction.NORTHEAST);
					}
					if(keyboard.key == "a") {
						player.move(Direction.WEST);
					}
					if(keyboard.key == "s") {
						//jump
					}
					if(keyboard.key == "d") {
						player.move(Direction.EAST);
					}
					if(keyboard.key == "z") {
						player.move(Direction.SOUTHWEST);
					}
					if(keyboard.key == "x") {
						player.move(Direction.SOUTH);
					}
					if(keyboard.key == "c") {
						player.move(Direction.SOUTHEAST);
					}
					player.playerTurn = false;
				}
			}
			
			//If not the turn of the player
			else {
				for(Entity entity : entities) {
					if(!(entity instanceof Player)) {
						entity.update();
					}
					
					//Updates all entities that are not Players
				}
				player.playerTurn = true;
			}
		}
	}
	
	
}
