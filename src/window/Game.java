package window;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JFrame;
import javax.swing.JPanel;

import entities.Entity;
import entities.Entity.Direction;
import entities.Fence;
import entities.Mho;
import entities.Player;
import input.Keyboard;

public class Game extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private ArrayList<Entity> entities;
	
	private ArrayList<Entity> boundingFences;
	
	private ArrayList<Type> entityType;
	
	private Entity[][] grid;
	
	private double freeEntities;
	
	private Player player;

	private int width;
	
	private int height;
	
	public static int WINDOWBAR = 22;
	
	public static int SCALE = 50;
	
	public static Keyboard keyboard;
	
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
		keyboard = new Keyboard();
		
		width = 12 * Game.SCALE;
		height = 12 * Game.SCALE;

		setSize(width, height + WINDOWBAR);
		setBackground(Color.WHITE);

		this.getContentPane().setFocusable(true);
		
		this.getContentPane().addKeyListener(keyboard);
		
		//ArrayList of entities on the board
		entities = new ArrayList<Entity>();
		
		//ArrayList of the outer fences
		boundingFences = new ArrayList<Entity>(44);
		
		//Sets the number of internal entities that still need to be created
		freeEntities = 33.0;
		
		//Creates the 2D array
		grid = new Entity[12][12];
		
		//Randomly creates the types of entities
		entityType = new ArrayList<Type>(33);
		entityType.add(0, Type.PLAYER);
		
		//Adds the Mhos
		for(int i = 1; i < 13; i++) {
			entityType.add(i, Type.MHO);
			}
		
		//Adds the fences
		for(int i = 13; i < 33; i++) {
			entityType.add(i, Type.FENCE);
			}
		
		//Shuffles the collection to prepare for random generation
		Collections.shuffle(entityType);
		
		//Creates the bounding fences
		createBoundingFences(boundingFences);
		
		//Creates the internal entities
		createInternalEntities(entities, entityType);
		
		repaint();

		//run();
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
	 * Creates the bounding fences on the edges
	 * @param boundingFences The arraylist of fences
	 */
	public void createBoundingFences(ArrayList<Entity> boundingFences) {
		//Creates top row
		for(int i = 0; i < 12; i++) {
			boundingFences.add(new Fence(i, 0));
		}
		
		//Creates bottom row
		for(int i = 0; i < 12; i++) {
			boundingFences.add(new Fence(i, 11));
		}
		
		//Creates left column
		for(int i = 1; i < 11; i++) {
			boundingFences.add(new Fence(0, i));
		}
		
		//Creates right column
		for(int i = 1; i < 11; i++) {
			boundingFences.add(new Fence(11, i));
		}
		
		//Adds these bounding fences to the list of entities
		entities.addAll(boundingFences);
	}
	
	/**
	 * Draws the current state of the game on the screen
	 */
	public void paint(Graphics g) {
		//Draws all entities
		for(Entity entity : entities) {
			entity.draw(g);
		}
		
		//Draws the lines 
		for(int i = 1; i <= 11; i++) {
			g.setColor(Color.BLACK);
			g.drawLine(i*Game.SCALE, 0 + Game.WINDOWBAR, i*Game.SCALE, 12*Game.SCALE + Game.WINDOWBAR);
			g.drawLine(0, i*Game.SCALE + Game.WINDOWBAR, 12*Game.SCALE, i*Game.SCALE + Game.WINDOWBAR);
		}
	}
	
	/**
	 * Runs the game
	 */
	public void run() {
		while(true) {
			//Player turn
			if(player.playerTurn == true) {
				player.update();
				//Sets wasTyped to false so that the game waits for player input
				keyboard.wasTyped = false;
			}
			
			//If not the turn of the player
			else {
				System.out.println("not player turn");
				for(Entity entity : entities) {
					//Updates all non-player entities
					if(!(entity instanceof Player)) {
						entity.update();
					}	
				}
				//After updating all non-player entities, the player takes their turn
				player.playerTurn = true;
			}
		}
	}	
}
