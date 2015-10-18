package window;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JFrame;
import entities.Entity;
import entities.Fence;
import entities.Mho;
import entities.Player;
import input.Keyboard;

public class Game extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	
	public ArrayList<Entity> entities;
	
	private ArrayList<Entity> boundingFences;
	
	private ArrayList<Type> entityType;

	private Entity[][] grid;
	
	private double freeEntities;
	
	public Player player;

	private int width;
	
	private int height;
	
	public static int WINDOWBAR = 22;
	
	public static int SCALE = 50;
	
	public static Keyboard keyboard;
	
	public JButton restart;
	
	public JButton quit;

	//Enum that represents the type of entity to be created
	public enum Type{
		FENCE,
		MHO,
		PLAYER
	}
	Type type;
	
	public enum GameState{
		PLAYER_DEAD,
		MHOS_DEAD,
		ONGOING
	}
	GameState state = GameState.ONGOING;
	
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

		this.getContentPane().setFocusable(true);
		
		//Creates a keylistener
		this.getContentPane().addKeyListener(keyboard);
		
		//ArrayList of entities on the board
		entities = new ArrayList<Entity>();
		
		//ArrayList of the outer fences
		boundingFences = new ArrayList<Entity>(44);

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
		
		//Shuffles the collection to prepare for random generation and prevents player generation bug
		while(true) {
			Collections.shuffle(entityType);
			if(!(entityType.get(32) == Type.PLAYER || entityType.get(31) == Type.PLAYER || entityType.get(30) == Type.PLAYER)) {
				break;
			}
		}
		
		//Creates the bounding fences
		createBoundingFences(boundingFences);
		
		//Creates the internal entities
		createInternalEntities(entities, entityType);

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
						player = new Player(i, j, this);
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
	 * Draws the current state of the game on the screen
	 * Author: Eric Liu
	 */
	public void paint(Graphics g) {
		if(state == GameState.ONGOING) {
			
			//Player has least priority for drawing
			for(Entity entity : entities) {
				if(entity instanceof Player) {
					entity.draw(g);
				}
			}
			
			//Mhos have priotity over player
			for(Entity entity : entities) {
				if(entity instanceof Mho) {
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
		
		if(state == GameState.PLAYER_DEAD) {
			g.setColor(Color.BLACK);
			g.fillRect(0, Game.WINDOWBAR, 12*Game.SCALE, 12*Game.SCALE);
			
			JButton restart = new JButton("Play again");
			JButton quit = new JButton("Quit");
			
			restart.setVisible(true);
			quit.setVisible(true);
			
			restart.addActionListener(this);
			quit.addActionListener(this);
		}
		
		if(state == GameState.MHOS_DEAD) {
			g.setColor(Color.BLACK);
			g.fillRect(0, Game.WINDOWBAR, 12*Game.SCALE, 12*Game.SCALE);
			
			JButton restart = new JButton("Play again");
			JButton quit = new JButton("Quit");
			
			restart.addActionListener(this);
			quit.addActionListener(this);
			
			restart.setVisible(true);
			quit.setVisible(true);
		}
	}
	
	/**
	 * Updates the game every time a key is pressed
	 * Authors: Eric Liu and Joseph Rumelhart
	 */
	public void update() {
		int playerCount = 0;
		int mhoCount = 0;
		
		//sortMhos();
		boolean pLive = player.update(grid);
		if(pLive == false) {
				//The paint method is called directly to ensure it is executed immediately
				paint(this.getGraphics());
				entities.remove(player);
				grid[player.getX()][player.getY()] = null;
		}
		
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
				if(entity2 instanceof Player) {
					playerCount ++;
				}
				if(entity2 instanceof Mho) {
					mhoCount ++;
				}
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
		
		//Iterates through entities and removes the dead ones
		for (Iterator<Entity> iterator = entities.iterator(); iterator.hasNext();) {
		    Entity entity = iterator.next();
		    if (!entity.getAlive()) {
		        // Remove the current element from the iterator and the list.
		    	grid[entity.getX()][entity.getX()] = null;
		        iterator.remove();
		    }
		}
		
		if(mhoCount == 0) {
			endGame(GameState.MHOS_DEAD);
		}
			
		if(playerCount == 0) {
			System.out.println("player dead");
			endGame(GameState.PLAYER_DEAD);
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
	
	private void endGame(GameState end) {
		if(end == GameState.PLAYER_DEAD) {
			state = GameState.PLAYER_DEAD;
		}
		
		if(end == GameState.MHOS_DEAD) {
			state = GameState.MHOS_DEAD;
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object button = e.getSource();
		
	}
}
