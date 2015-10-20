package window;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

import entities.Entity;
import entities.Fence;
import entities.Mho;
import entities.Player;
import input.Keyboard;
import run.Run;

public class Game extends JFrame implements ActionListener {

	//Default serialVersionUID
	private static final long serialVersionUID = 1L;
	
	//The arraylist of entities in the game
	public ArrayList<Entity> entities;
	
	//The arraylist of surrounding fences
	private ArrayList<Entity> boundingFences;

	//An arraylist for the types of all the entities
	private ArrayList<Type> entityType;

	//The entity at position (x, y) is represented in grid[x][y]
	private Entity[][] grid;
	
	//The entities still to be generated
	private double freeEntities;
	
	//The player of this game
	public Player player;

	//The width of this game
	private int width;
	
	//The height of this game
	private int height;
	
	//A constant representing the height of the window bar
	public static int WINDOW_BAR = 22;
	
	//A constant representing the length of each cell
	public static int CELL_SIZE = 50;
	
	//The keyboard used to control this game
	public static Keyboard keyboard;
	
	//The buttons used to restart and quit the game
	private JButton restart;
	private JButton quit;
	
	//The text field used to tell you what happened
	public JTextField test;

	//Enum that represents the type of entity to be created
	public enum Type{
		FENCE,
		MHO,
		PLAYER
	}
	Type type;
	
	//Enum that represents the current state of the game. Starts as ongoing.
	public enum GameState{
		KILLED_BY_MHO,
		JUMPED_ON_MHO,
		RAN_INTO_WALL,
		MHOS_DEAD,
		ONGOING
	}
	GameState state;
	
	/**
	 * Creates everything needed for the game.
	 * 
	 * Author: Eric Liu and Joseph Rumelhart
	 */
	public Game() {
		//Creates the keyboard
		keyboard = new Keyboard(this);
		
		//Sets the state to ongoing
		state = GameState.ONGOING;
		
		//Creates a window to display the game and sets the background color to white
		width = 12 * Game.CELL_SIZE;
		height = 12 * Game.CELL_SIZE;
		setSize(width, height + WINDOW_BAR);
		setBackground(Color.WHITE);

		//Makes this game focusable
		this.getContentPane().setFocusable(true);
		
		//Adds a keylistener
		this.getContentPane().addKeyListener(keyboard);
		
		//Creates the arraylist of entities
		entities = new ArrayList<Entity>();
		
		//Creates the arraylist of surrounding fences
		boundingFences = new ArrayList<Entity>(44);

		//Sets the number of internal entities that still need to be created to 33
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
		
		//Creates the surrounding fences
		createBoundingFences(boundingFences);
		
		//Creates the internal entities
		createInternalEntities(entities, entityType);

		//Paints the JFrame
		repaint();
	}
	/**
	 * Creates the entities on the board randomly
	 * 
	 * @param entities arrayList of all entities on the inside of the board
	 * @param entityType arrayList storing the type of entity to be created at each position
	 * 
	 * Author: Joseph Rumelhart
	 */
	private void createInternalEntities(ArrayList<Entity> entities, ArrayList<Type> entityType) {
		for (int x = 1; x < 11 ; x++) {
			for (int y = 1; y < 11 ; y++) {
				//Creates a random value used in generation
				double r = Math.random();
				
				//Creates threshold for creation of entity
				double threshold = (freeEntities /(100 - (x * 10) + y));
				
				//Determines if random number is within threshold
				if(r < threshold) {
					
					//Creates a player
					if(entityType.get((int) (33 - freeEntities)) == Type.PLAYER) {
						player = new Player(x, y, this);
						entities.add(player);
						grid[x][y] = player;
					}
					
					//Creates a mho
					else if(entityType.get((int) (33 - freeEntities)) == Type.MHO) {
						Mho mho = new Mho(x, y);
						entities.add(mho);
						grid[x][y] = mho;
					}
					
					//Creates a fence
					else {
						Fence fence = new Fence(x, y);
						entities.add(fence);
						grid[x][y] = fence;
					}
					
					//Subtracts one from the number of entities that still need to be created
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
	 * 
	 * @param boundingFences The arraylist of surrounding fences
	 * 
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
	 * 
	 * @param g the graphics object to be used
	 * 
	 * Author: Eric Liu
	 */
	public void paint(Graphics g) {
		
		//Draws different things for different game states
		switch(state) {
			//If the game is in progress
			case ONGOING: 
				//Player has lowest priority for drawing
				for(Entity entity : entities) {
					if(entity instanceof Player) {
						entity.draw(g);
					}
				}
				
				//Mhos have priority over player
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
					g.drawLine(i*Game.CELL_SIZE, 0 + Game.WINDOW_BAR, i*Game.CELL_SIZE, 12*Game.CELL_SIZE + Game.WINDOW_BAR);
					g.drawLine(0, i*Game.CELL_SIZE + Game.WINDOW_BAR, 12*Game.CELL_SIZE, i*Game.CELL_SIZE + Game.WINDOW_BAR);
				}
				
				break;
				
			//If you died by running into a fence
			case RAN_INTO_WALL:
				//Sets the text to indicate what killed you
				JTextField text = new JTextField("You Ran Into a Fire");
				
				//Creates the buttons
				JButton restart = new JButton("Play Again");
				JButton quit = new JButton("Quit");
				
				//Adds action listeners to the buttons
				restart.addActionListener(this);
				quit.addActionListener(this);
				
				//Sets the sizes of the buttons and text field
				restart.setBounds(50, 500, 100, 50);
				quit.setBounds(450, 500, 100, 50);
				text.setBounds(225, 25, 135, 25);
				
				//You can't edit or click the text box
				text.setFocusable(false);
				
				//Adds the buttons and text field to the game
				this.getContentPane().add(restart);
				this.getContentPane().add(quit);
				this.getContentPane().add(text);
				
				//Sets the background color
				this.getContentPane().setBackground(Color.BLACK);
				
				//Repaints the game
				this.getContentPane().repaint();
				
				break;
				
			//If you were killed by a mho
			case KILLED_BY_MHO:
				//Sets the text to indicate what killed you
				text = new JTextField("A Mho Ate You");
				
				//Creates the buttons
				restart = new JButton("Play Again");
				quit = new JButton("Quit");
				
				//Adds action listeners to the buttons
				restart.addActionListener(this);
				quit.addActionListener(this);
				
				//Sets the sizes of the buttons and text field
				restart.setBounds(50, 500, 100, 50);
				quit.setBounds(450, 500, 100, 50);
				text.setBounds(225, 25, 130, 25);
				
				//You can't edit or click the text box
				text.setFocusable(false);
				
				//Adds the buttons and text field to the game
				this.getContentPane().add(restart);
				this.getContentPane().add(quit);
				this.getContentPane().add(text);
				
				//Sets the background color
				this.getContentPane().setBackground(Color.BLACK);
				
				//Repaints the game
				this.getContentPane().repaint();
				
				break;
				
			//If you win by killing all the mhos
			case MHOS_DEAD: 
				//Sets the text to indicate your victory
				text = new JTextField("You Win!");
				
				//Creates the buttons
				restart = new JButton("Play Again");
				quit = new JButton("Quit");
				
				//Adds action listeners to the buttons
				restart.addActionListener(this);
				quit.addActionListener(this);
				
				//Sets the sizes of the buttons and text field
				restart.setBounds(50, 500, 100, 50);
				quit.setBounds(450, 500, 100, 50);
				text.setBounds(260, 25, 90, 25);
				
				//You can't edit or click the text box
				text.setFocusable(false);
				
				//Adds the buttons and text field to the game
				this.getContentPane().add(restart);
				this.getContentPane().add(quit);
				this.getContentPane().add(text);
				
				//Sets the background color
				this.getContentPane().setBackground(Color.BLUE);
				
				//Repaints the game
				this.getContentPane().repaint();
				
				break;
				
			//If you died by jumping on a mho
			case JUMPED_ON_MHO:
				//Sets the text to indicate what killed you
				text = new JTextField("You Jumped On A Mho");
				
				//Creates the buttons
				restart = new JButton("Play Again");
				quit = new JButton("Quit");
				
				//Adds action listeners to the buttons
				restart.addActionListener(this);
				quit.addActionListener(this);
				
				//Sets the sizes of the buttons and text field
				restart.setBounds(50, 500, 100, 50);
				quit.setBounds(450, 500, 100, 50);
				text.setBounds(200, 25, 160, 25);
				
				//You can't edit or click the text box
				text.setFocusable(false);
				
				//Adds the buttons and text field to the game
				this.getContentPane().add(restart);
				this.getContentPane().add(quit);
				this.getContentPane().add(text);
				
				//Sets the background color
				this.getContentPane().setBackground(Color.BLACK);
				
				//Repaints the game
				this.getContentPane().repaint();
				
				break;
		}
	}
	
	/**
	 * Gets whether or not the game is over
	 * 
	 * @return whether or not the gamem is over
	 * 
	 * Author: Eric Liu
	 */
	public boolean gameOver() {
		if(state == GameState.ONGOING) {
			return false;
		}
		else return true;
	}
	
	/**
	 * Updates the game every time a key is pressed
	 * 
	 * Authors: Eric Liu and Joseph Rumelhart
	 */
	public void update() {
		//The number of mhos left in the game. Used to determine when you have won.
		int mhoCount = 0;
		
		//Updates the player first
		player.update(grid);
		
		//Updates the rest of the entities
		for(Entity entity : entities) {
			//Updates the mhos
			if(entity instanceof Mho) {
				((Mho) entity).update(player, grid);
			}
			
			//Updates the fences
			if(entity instanceof Fence) {
				((Fence) entity).update(grid);
			}
		}
		
		//Handles death
		for(Entity entity : entities) {
			for(Entity entity2 : entities) {
				//If the two entities are not the same but share a position
				if(entity != entity2 && entity.getX() == entity2.getX() && entity.getY() == entity2.getY()) {
					//Player dies if it runs into a fence
					if(entity instanceof Player && entity2 instanceof Fence) {
						endGame(GameState.RAN_INTO_WALL);
					}
					//Player dies if it is killed by a mho
					if(entity instanceof Player && entity2 instanceof Mho) {
						endGame(GameState.KILLED_BY_MHO);
					}
					//Mhos die if they walk over a fence
					if(entity instanceof Mho && entity2 instanceof Fence) {
						entity.kill();
					}
					if(entity instanceof Mho && entity2 instanceof Mho) {
						System.out.println("x: " + entity.getX());
						System.out.println("y: " + entity.getY());
					}
				}
			}
		}
		
		//Iterates through entities and removes the dead ones
		for(Iterator<Entity> iterator = entities.iterator(); iterator.hasNext();) {
		    Entity entity = iterator.next();
		    if (!entity.getAlive()) {
		        // Draws the dead entity to erase it and removes it from entities and the grid
		    	entity.draw(getGraphics());
		    	grid[entity.getX()][entity.getX()] = null;
		        iterator.remove();
		    }
		}
		
		//Sets mhoCount to the number of live mhos in the game
		for(Entity entity : entities) {
			if(entity instanceof Mho) {
				mhoCount ++;
			}
		}
		
		//If there are no live mhos, the player wins
		if(mhoCount == 0) {
			endGame(GameState.MHOS_DEAD);
		}
		
		//After all entities have updated, repaint the frame with the new results
		repaint();
	}
	
	/**
	 * Gets the grid of this game
	 * 
	 * @return the grid of this game
	 * 
	 * Author: Joseph Rumelhart
	 */
	public Entity[][] getGrid() {
		return grid;
	}
	
	/**
	 * Sets the game grid to the desired grid
	 * 
	 * @param grid the grid of this game
	 * 
	 * Author: Joseph Rumelhart
	 */
	public void setGrid(Entity[][] grid) {
		this.grid = grid;
	}
	
	/**
	 * Ends the game at the given state
	 * 
	 * @param end the state of the ended game
	 * 
	 * Author: Eric Liu
	 */
	public void endGame(GameState end) {
		state = end;
	}
	
	/**
	 * Based on which button is pressed, either start a new game or quit.
	 * 
	 * @param e the event that is fired when a button is pressed
	 * 
	 * Author: Eric Liu
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if(((JButton)e.getSource()).getText() == "Play Again") {
			//Create a new game and dispose of the old one
			Run.runGame();
			this.dispose();
		}
		
		if(((JButton)e.getSource()).getText() == "Quit") {
			//Exit the game
			System.exit(DISPOSE_ON_CLOSE);
		}
	}
}
