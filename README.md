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

Quality Assurance: 
As part of QA on this project, Joseph implemented an unit testing structure known as JUnit. This enabled him to write and run tests within Eclipse on individual methods, or "units," to determine errors. For example, the GameTest enabled him to detect the player generation bug referenced in "Current Errors." This structure was overall sucessful, and provided good evidence 

Overview:


Challenges:
One of the primary challenges involved in creating this project was the movement logic for the Mhos. The list of instructions, while initially seeming simple to implement, required, due to various different decisions made early in the project, a large time and effort investment, especially in debugging. Other fields of issue included the grid data structure and the drawing of the entities on the screen.

Acknowledgements: 
Ben Cohen-Wang for aid on general program design and the entity generation algorithm.
Chris Kuszmaul for help on the generation algorithm and general guidance.
Jonathan Zwiebel for assistance on the image display.

Primary resources included the Java documentation files and the JUnit documentation.
