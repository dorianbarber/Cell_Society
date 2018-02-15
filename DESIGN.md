CELL SOCIETY TEAM 09
===================

### Introduction
The problem our team is trying to solve is writing a program that will animate any 2D grid cellular automata simulation. Cellular automata are simulation based on some model that consists of grid cells that are in one of a finite number of states. Each cell within the simulation will have its "neighbors" defined relative to the specific cell. The definition of neighbors is flexible according to the simulation (i.e. Spreading Fire includes only the cells which are above or below, or to the left or right of a cell, while Game of Life considers the eight cells surrounding a given cell, including diagonals). Over time a fixed rule is applied to the simulation which will determine new states for each cell. The cells are then updated -- importantly, they are dependent only on the states of their neighbors at the same time, so cells cannot be updated completely sequentially. Some examples of cellular automata are Schelling's model of segregation and the Wa-Tor World model of predator-prey relationships. The goal of this project is to be able to animate nearly any cellular automata simulation given according to a file with the specifications of that simulation. 

The primary design goal of the project is to develop a set of classes that can adapt to any different cellular automata simulation. The flexibility will come into play when the cellular automata simulation is defined. Once a certain simulation is defined the built classes will be able to adapt to its specifications: the number of states and what those states are, what is to be considered within a cell's neighborhood, and the set of rules that the simulation will follow. The user will be closed to modifying code that depends on the algorithm. The only available section of code that will be open to extension is the addition of a new model or new behaviors of existing modules. Through following the open-closed principle, the program will resist changes that result in a cascading effect to the any dependent modules. One effective approach that we will exercise as a team is abstraction. By following this principle of object oriented design, we can create abstractions that are fixed and represent a set of possible behaviors. Since the abstraction is fixed, child modules are closed for modification. The behavior, however, can be extended by creating new derivatives of the abstraction. Executing the open-closed principle primarily through abstraction and strategic closure our program will reach a state of easy reusability and maintenance. 

### Overview
This project will have a design hierarchy that maximizes its ability to adapt to new variations of Cell Automation Simulations. In order to do this, our plan is to have rigid classes that do not change based on different game rules and develop one abstraction that can be extended to add new game types. Maintaining the smallest number of dynamic classes minimizes the extra work we need to do to add new functionality. Our hierarchy will contain the following classes: Grid, Cell, Menu, and Model. Each class will interact with each other class as follows:

+ The menu acts as the UI and allows user input. It contains a grid and allows the user to change game speed, load new types, and interact with cells
+ contains setUpGrid which will create a Grid with cells based on the rules determined by the active Model 
+  will handle clicking and buttons with getInput method and will update the Grid and the active Model from this input
+  will call the Grid to update with updateGrid()
	

+ The GridModel class houses a collection of cell objects
	+ will handle interactions between the Cells and Model with the method updateCells-- which will call the cells to update their state based on their own information and the rules and logic defined in Model
	
+ NeighborFinder acts as a static helper class which finds the neighbors of different kinds of cells

+ CellModels will contain their own past, present, and future states and a collection of the other cells around it
   + CellModels are able to send the information about their own state and surroundings to the Neighborfinder, and will update their state based on their internal neighbors variable.
   + CellsModels can determine their next states and then update themselves
   
## Adding new features

To add a new simulation, a designer will have to add a new CellModel and GridModel subclass and modify the GetNeighbors method within NeighborFinder to accomodate the new simulation. The designer will also have to give the GridModel class an appropriate simulation number that can be used to identify the simulation in both the Menu class and also to keep the convention in the XML files. This also involves editing the instance variable POSSIBLEGRIDS in Menu to include this new simulation. The functionality of this simulation is entirely dependent to the designer and as long as it can also work with the implementation of the XML file builder and parser the project is easily extensible. 

New buttons can easily be added by simply following the same format as all the other buttons in the Menu class. The only concern would be positioning it relative to the other user interface aspects. The action performed by the button can be expanded quite significantly. Many more user interface feature adding can be done as long as the positioning of the components are properly dealt with. 

## Major Design Choices

We decided to abstract both the CellModel and the GridModel classes in order to simplify our algorithms. This made our class hierarchy more complex and makes our design less flexible. It does, however, make our code more readable and easy to interpret. Furthermore this spread out a significant amount of responsibility. This was helpful as each GridModel subclass acted as a container for the CellModel subclasses. Thus a lot of the behavior for the CellModel subclasses remained in an isolated location. 

Another strong design choices was how the XML related package played into the rest of the project. There are only two locations outside of the XML classes where these classes get instantiated. Both are contained effectively and no information is passed either as mutable variables or revealed to the menu class. Furthermore the XML files do not rely on anything besides a proper file input which is dealt with through try/catch statements and error handling. Alerts are posted to the user if an error such as incorrect file choosing occurs. 

## Assumptions

One major assumption when implementing the program with different shapes was the logic for the simulations were the same for all the different shapes. This was most notable in the Life 

