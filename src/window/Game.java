package window;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import javax.swing.JButton;
import javax.swing.JFrame;
import entities.Entity;
import entities.Fence;
import entities.Mho;
import entities.Player;
import input.Keyboard;

public class Game extends JFrame {

	private static final long serialVersionUID = 1L;
	
	public ArrayList<Entity> entities;
	
	private ArrayList<Entity> boundingFences;
	
	private ArrayList<Type> entityType;
	
	//private ArrayList<Mho> sortedMhos;
	
	private Entity[][] grid;
	
	private double freeEntities;
	
	public Player player;

	private int width;
	
	private int height;
	
	public static int WINDOWBAR = 22;
	
	public static int SCALE = 50;
	
	public static Keyboard keyboard;
	
	JButton restart;
	
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
		keyboard = new Keyboard(this);
		
		//Creates a window to display the game
		width = 12 * Game.SCALE;
		height = 12 * Game.SCALE;
		setSize(width, height + WINDOWBAR);
		setBackground(Color.WHITE);

		restart = new JButton("Restart?");
		
		this.getContentPane().setFocusable(true);
		
		//Creates a keylistener
		this.getContentPane().addKeyListener(keyboard);
		
		//ArrayList of entities on the board
		entities = new ArrayList<Entity>();
		
		//ArrayList of the outer fences
		boundingFences = new ArrayList<Entity>(44);
		
		//sortedMhos = new ArrayList<Mho>();
		
		//Sets the number of internal entities that still need to be created
		freeEntities = 33.0;
		
		//Creates the 2D array
		setGrid(new Entity[12][12]);
		
		//Randomly creates the types of entities
		entityType = new ArrayList<Type>();
		entityType.add(Type.PLAYER);
		
		//Adds the Mhos
		for(int i = 1; i < 13; i++) {
			entityType.add(Type.MHO);
			}
		
		//Adds the fences
		for(int i = 13; i < 33; i++) {
			entityType.add(Type.FENCE);
			}
		
		//Shuffles the collection to prepare for random generation
		Collections.shuffle(entityType);
		
		//Creates the bounding fences
		createBoundingFences(boundingFences);
		
		//Creates the internal entities
		createInternalEntities(entities, entityType);
		
		//sortMhos();

		repaint();
	}
	/**
	 * Creates the entities on the board randomly
	 * @param entities arrayList of all entities on the inside of the board
	 * @param entityType arrayList storing the type of entity to be created at each position
	 * Author: Joseph Rumelhart
	 */
	private void createInternalEntities(ArrayList<Entity> entities, ArrayList<Type> entityType) {
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
						grid[i][j] = player;
					}
					//creates a Mho
					else if(entityType.get((int) (33 - freeEntities)) == Type.MHO) {
						Mho mho = new Mho(i, j);
						entities.add(mho);
						grid[i][j] = mho;
						
					}
					//Creates a fence
					else {
						Fence fence = new Fence(i, j);
						entities.add(fence);
						grid[i][j] = fence;
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
	
	/**
	 * Creates the bounding fences on the edges
	 * @param boundingFences The arraylist of fences
	 * Author: Eric Liu
	 */
	 private void createBoundingFences(ArrayList<Entity> boundingFences) {
		//Creates top row
		for(int i = 0; i < 12; i++) {
			Fence fence = new Fence(i, 0);
			boundingFences.add(fence);
			getGrid()[i][0] = fence;
		}
		
		//Creates bottom row
		for(int i = 0; i < 12; i++) {
			Fence fence = new Fence(i, 11);
			boundingFences.add(fence);
			getGrid()[i][11] = fence;
		}
		
		//Creates left column
		for(int i = 1; i < 11; i++) {
			Fence fence = new Fence(0, i);
			boundingFences.add(fence);
			getGrid()[0][i] = fence;
		}
		
		//Creates right column
		for(int i = 1; i < 11; i++) {
			Fence fence = new Fence(11, i);
			boundingFences.add(fence);
			getGrid()[11][i] = fence;
		}
		
		//Adds these bounding fences to the list of entities
		entities.addAll(boundingFences);
	}
	
	/**
	public void sortMhos() {
		int[] distances;
		distances = new int[12];
		int counter = 0;
		
		
		for(Entity entity : entities) {
			if(entity instanceof Mho) {
				//sets each spot in distances to the distances of the mhos
				distances[counter] = ((Mho) entity).getDistance(player);
				//adds empty spaces in sortedmhos so we can use the set() method without nullpointer errors
				sortedMhos.add(counter, null);
				counter ++;
			}
		}
		
		//Sorts distances in ascending order
		Arrays.sort(distances);
		
		for(Entity entity : entities) {
			if(entity instanceof Mho) {
				//Cycles thru the sorted array of distances and if there is a match, set the spot in sortedMhos 
				//to the spot in distances. 
				for(int counter1 = 0; counter1 < 11; counter1 ++) {
					if(((Mho) entity).getDistance(player) == distances[counter1]) {
						//this essentially takes it out of the array without removing, which would decrease array size
						distances[counter1] = 0;
						//puts the mho in the same place in sortedMhos as it is in the sorted array of distances
						sortedMhos.set(counter1, (Mho) entity);
						//breaks out of this inner for loop only?
						break;
					}
				}
				//problem line, concurrent modification
				//we want to remove old mhos from entites
				//entities.remove(entity);
			}
		}
		//merges this arraylist with entities
		entities.addAll(sortedMhos);
	}
	**/
	
	/**
	 * Draws the current state of the game on the screen
	 * Author: Eric Liu
	 */
	public void paint(Graphics g) {
	
		//Mhos have no priority
		for(Entity entity : entities) {
			if(entity instanceof Mho) {
				entity.draw(g);
			}
		}
		//Player has priority over Mhos
		for(Entity entity : entities) {
			if(entity instanceof Player) {
				entity.draw(g);
			}
		}
		//Fences have priority over everything
		for(Entity entity : entities) {
			if(entity instanceof Fence) {
				entity.draw(g);
			}
		}

		//Draws the lines 
		for(int i = 1; i <= 11; i++) {
			g.setColor(Color.BLACK);
			g.drawLine(i*Game.SCALE, 0 + Game.WINDOWBAR, i*Game.SCALE, 12*Game.SCALE + Game.WINDOWBAR);
			g.drawLine(0, i*Game.SCALE + Game.WINDOWBAR, 12*Game.SCALE, i*Game.SCALE + Game.WINDOWBAR);
		}
	}
	
	/**
	 * Updates the game every time a key is pressed
	 * Authors: Eric Liu and Joseph Rumelhart
	 */
	public void update() {
		//sortMhos();
		boolean pLive = player.update(grid);
		if(pLive == false) {
				//The paint method is called directly to ensure it is executed immediately
				paint(this.getGraphics());
				entities.remove(player);
				grid[player.getX()][player.getY()] = null;
				add(restart);
		}
		
//		for(Mho mho : sortedMhos) {
//			boolean alive = mho.update(player, grid);
//			if(alive == false) {
//				//The paint method is called directly to ensure it is executed immediately
//				paint(this.getGraphics());
//				grid[mho.getX()][mho.getY()] = null;
				add(restart);
//			}
//		}
		
		for(Entity entity : entities) {
			if(entity instanceof Mho) {
				//updates the Mho
				boolean alive = ((Mho) entity).update(player, grid);
				if(alive == false) {
					//The paint method is called directly to ensure it is executed immediately
					paint(this.getGraphics());
					grid[entity.getX()][entity.getY()] = null;
				}
			}
			
			//Updates the fence
			if(entity instanceof Fence) {
				((Fence) entity).update(grid);
			}
		}
		
		for(Entity entity : entities) {
			for(Entity entity2 : entities) {
				if(entity != entity2 && entity.getX() == entity2.getX() && entity.getY() == entity2.getY()) {
					
					if(entity instanceof Player && (entity2 instanceof Fence || entity2 instanceof Mho)) {
						entity.kill();
					}
					if(entity instanceof Mho && entity2 instanceof Fence) {
						entity.kill();
					}
				}
			}
		}
		//After all entities have updated, repaint the frame with the new results
		repaint();
	}
	
	public Entity[][] getGrid() {
		return grid;
	}
	
	public void setGrid(Entity[][] grid) {
		this.grid = grid;
	}
}
