# HiVolts
Project by Eric Liu and Joseph Rumelhart.
This README was written by Joseph Rumelhart.

Introduction: 
The HiVolts Project is an updated version of a old PLATO system video game by the same name, originally provided to Mr. Kuszmaul by Dirk Pellet.It involves controlling a player as it moves around the screen, trying to avoid enemies called Mhos. The player wins when all of the Mhos move themselves onto Fences, which are lethal for all characters.

Specifications:
The project specified that the game would be primarily copy of the original, with the same board layout of one player, 12 Mhos, and 20 internal Fences, with an outer border of Fences that always appear. The controls are also the same, with the keys around "S" being used for the cardinal directions, with "S" being sit in place and "J" being jump, which moves the player to a random position on the board that is not a fence. Finally, the Mhos must move towards the player according to a fixed set of rules, and the player must be able to quit or restart the game if they are killed or victorious. The main difference between the current project and the original is the graphics, with the updated version requiring much more advanced graphics. 

Our project meets these requirements in nearly all ways. The graphics are far improved from the 1970's version, but all controls and logic remain the same. For example, when the jump method is called, it provides for the option to land on a Mho, but not a Fence, and die due to this. A video has been included to demonstrate the different states of the program, and the opportunities for victory and loss. The primary problems lie in the movement logic for the Mhos, as detailed in the Current Errors section.

Current Errors:
While the project does meet the specification, there are several minor errors that are still unresolved. One observed bug has been the game starting without a player. However, under testing this has occurred only once out of a million times. In addition, the Mho behavior has problems when interacting with other Mhos, occasionally exhibiting behavior not outlined in the rules.

Quality Assurance: 
As part of QA on this project, Joseph implemented an unit testing structure known as JUnit. This enabled him to write and run tests within Eclipse on individual methods, or "units," to determine errors. (These are stored in the "tests" package if you wish to run them.) For example, the GameTest enabled him to detect the player generation bug referenced in "Current Errors." This structure was overall successful, although the naming conventions are not correct, and provides good evidence for the full completion of the specifications.

Overview:
The game is divided into four main packages: Entities, Input, Run, and Window, with the Tests package housing the unit tests (see QA). 

Entities contains the base class Entity, from which the game entities Fence, Mho, and Player all inherit their primary methods. Fence contains very little code, as it does not require movement or death logic. In contrast, Player and Mho are large objects, whose primary difference is in their means of movement. While Player requires input from a human, Mho may move on its own towards the Player. The largest method in Entity is the movement method, which takes a Direction enum of the cardinal directions and moves the entity to that direction.

Fence is a simple inheritance from Entity, and does nothing more than exist and update.

Player's primary method is its update, which responds to the key input from Keyboard and moves in the direction indicated. The other primary method within Player is jump, which moves it to a random empty space or Mho on the board. This represents a gamble on the player's part to get out of a risky situation. Finally, the last method used within Player is the draw method, which draws its avatar on the screen.

Mho is very similar to Player, having both move and draw methods. However, the moveMho method requires the use of two subsidary methods, moveSimple and moveComplex. Each of these methods takes a Player object and the game grid, a representation of the game stored in a 2D array. moveSimple operates when the Mho is on a direct line with the Player, and moveComplex operates in the other case. The draw method is very similar to that of Player, only requiring a differed sprite.

The Input package contains Keyboard, which is responsible for taking input from a human and translating it into instructions usable by the Player. The primary method within Keyboard is keyTyped, which recieves input from the physical keyboard stores them in the String "key," which is public and thus available to all classes and methods. 

Run contains the Run class, which is responsible for instantiating and running the Game, and contains the Main method. The Main method calls runGame(), which creates a new game and governs some of its properties. This method is also called from Game when it needs to restart.

Finally, Game creates and instantiates all of the Entities used in the game and displays them on the screen. It is also responsible for determining if the game is over, and allowing the game to quit or restart as needed. The constructor in Game calls two separate methods, createBoundingFences, which creates the fences on the outer edge that appear in every game, and createInternalEntities, which handles the random creation of the Player, Mhos, and more Fences. A variable that is heavily used within createInternalEntities is freeEntities, which stores the number of entities that are yet to be created. This is used to determine the threshold at which an object is created. If a randomly generated number exceeds the threshold, an entity is created, be it a Player, Mho, or Fence, and freeEntities is decreased by one. Otherwise, the loop moves on to the next square of the grid and repeats the process.

Challenges:
One of the primary challenges involved in creating this project was the movement logic for the Mhos. The list of instructions, while initially seeming simple to implement, required, due to various different decisions made early in the project, a large time and effort investment, especially in debugging. Other fields of issue included the grid data structure and the drawing of the entities on the screen. The decision was made early on to use an ArrayList to store the data, as that was simplest to generate in. However, when Joseph started to implement movement, he discovered that a 2D array would be much simpler for that purpose. Eric initially used colored blocks to represent the entities on the board, as importing and using images was very difficult. Thanks to Jonathan, this issue was resolved, and Eric implemented images for the entities.

Acknowledgements: 
Ben Cohen-Wang for aid on general program design and the entity generation algorithm.
Chris Kuszmaul for help on the generation algorithm and general guidance.
Jonathan Zwiebel for assistance on the image display.

Primary resources included the Java and JUnit documentation files.
