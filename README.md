# HiVolts
Project by Eric Liu and Joseph Rumelhart

Introduction: 
The HiVolts Project is an updated version of a old PLATO system video game by the same name.
It involves controlling a player as it moves around the screen, trying to avoid enemies called Mhos.
The player wins when all of the Mhos move themselves onto Fences, which are lethal for all characters.

Specifications:
The project specified that the game would be primarily copy of the original, with the same board layout of one player, 12 Mhos, and 20 internal Fences, with an outer border of Fences that always appear. The controls are also the same, with the keys around "S" being used for the cardinal directions, with "S" being sit in place and "J" being jump, which moves the player to a random position on the board that is not a fence. Finally, the Mhos must move towards the player according to a fixed set of rules, and the player must be able to quit or restart the game if they are killed or victorious. The main difference between the current project and the original is the graphics, with the updated version requiring much more advanced graphics. 

Our project meets these requirements in all ways. The graphics are far improved from the 1970's version, but all controls and logic remain the same. 

Current Errors:
While the project does meet the specification, there are several minor errors that are still unresolved. One observed bug has been the game starting without a player. However, under testing this has occurred only once out of a million times. 

Overview:


Challenges:
One of the primary challenges involved in creating this project was the movement logic for the Mhos. The list of instructions, while initially seeming simple to implement, required, due to various different decisions made early in the project, a large time and effort investment, especially in debugging. Other fields of issue included the grid data structure and the drawing of the entities on the screen.

Acknowledgements: 
Ben Cohen-Wang
Chris Kuszmaul
Jonathan Zwiebel

Primary resources included the Java documentation files and stackoverflow.com.
