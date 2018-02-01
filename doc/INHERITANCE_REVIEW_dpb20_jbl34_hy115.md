Inheritance Review Questions
=====
dpb20, jbl34, hy115

Part 1
=====
My design is encapsulating the data structures of cells from the interface class. 

My inheritance hierarchy is based around the cells, and the different kinds of cells that exist. They are based around the certain conditions related to what cells are neighbors and the position of cells on the board. 

Getting the neighboring states and the state of the cell will be opened. However, changing the states and the background creation will be closed. 

Some errors that can occur with the cells in the grid is checking for edge cases. My implementation should take care of this through the inheritance hierarchy of the specific cells. 

I think my design is a good design because the purpose is to be able to work for any simulation defined. The crux of the cell and grid classes are to be flexible for any simulation created. Therefore, when adding a new simulation all that needs to happen is the specific implementation of the simulation. The cell and grid classes will respond to those specifications inherently. 

Part 2
=====
This part interacts with the main user interface as well as the classes of simulations. This is crucial because the user interface shows the grid and the simulation determines the process of updating.

The grid class will create a Group and return that for the user interface to add to the scene. This is not dependent on the implementation of the user interface because the user interface simply needs to add the grid to the scene. This is not based on the implementation of the behavior of the other classes. 

Limiting the amount of information that is passed between the classes, and also encapsulating the other classes as well. This will make it so other classes are not dependent on the parameters of the other classes in the project. 

Looking at the abstract cell class and the corresponding edge, center, and side cells...
 
In common:
 - Have next state
 - Have previous state
 - Have neighbors
 - Can find next state
 
 Difference:
 - The neighbors
 - The corresponding algorithm to find the next state


Part 3
=====
*5 Use Cases*
1. The simulation runs entirely from start to finish.
The grid updates on a per time basis. All cells will go through and update depending on the neighbors. 
2. The user chooses to play back the simulation from a moment that is paused.
All cells remain in their state during the pause period. When the play back buttoned is pressed the grid loops through all the cells and reverts the cell to the previous state. 
3. The user begins the simulation.
The grid is initialized and the state of the cells are read from the xml file. 
4. The user chooses to play the simulation forward one time step. 
The grid loops through the cells and each cell goes to the next state. Only once all the cells have moved to the next state can the individual cells deduce their next state.
5. The simulation is switched.
The grid will update with entirely new cells that will have different direct implementations depending on the simulation chosen. The initial state is also read into the grid class to define those states. 



The feature I am most excited to work on is implementing the ability for the cells to go back and forth. Ideally, when the cells change back and forth between states the next state will not be altered by cells that have not reverted yet. This will involve dealing with when the cells take in information about their neighbors to determine their next state. 

Working with teammate is particularly worrying, especially making sure that my section of the code works nicely with the other team members. 
