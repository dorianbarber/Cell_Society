CELL SOCIETY TEAM 09
===================

## Introduction
The problem our team is trying to solve is writing a program that will animate any 2D grid cellular automata simulation. Cellular automata are simulation based on some model that consists of grid cells that are in one of a finite number of states. Each cell within the simulation will have its neighbors defined relative to the specific cell. Over time a fixed rule is applied to the simulation in which will determine new states for each relevant cell. Some examples of cellular automata are Schelling's model of segregation and Wa-Tor World model of predator-prey relationships. The goal of this project is to be able to animate any cellular automata simulation given. 

The primary design goal of the project is to develop a set of classes that can adapt to any different cellular automata simulation. The flexibility will come into play when the cellular automata simulation is defined. Once a certain simulation is defined the built classes will be able to adapt to its specifications: the number of states and what those states are, what is to be considered within a cell's neighborhood, and the rule that the simulation will follow. The user will be closed to modifying code that depends on the algorithm. The only available section of code that will be open to extension is the addition of a new model or new behaviors of existing modules. Through following the open-closed principle, the program will resist changes that result in a cascading effect to the any dependent modules. One effective approach that we will exercise as a team is abstraction. By following this principle of object oriented design, we can create abstractions that are fixed and represent a set of possible behaviors. Since the abstraction is fixed, child modules are closed for modification. The behavior, however, can be extended by creating new derivatives of the abstraction. Executing the open-closed principle primarily through abstraction and strategic closure our program will reach a state of reusability and maintainablilty. 

=======
### Overview
This project will have a design heirarchy that maximizes its ability to adapt to new variations of Cell Automation Simulations. In order to do this, our plan is to have rigid classes that do not change based on different game rules and develop one abstraction that can be extended to add new game types. Maintaining the smallest number of dynamic classes minimizes the extra work we need to do to add new functionality. Our heirarchy will contain the following classes: Grid, Cell, menu, and Model. Each class will interact with each other class as follows:

+ The menu acts as the UI and allows user input. It contains a grid and 
	+ Menu allows the user to change game speed load new types and interact with cells
	+ Menu contains setUpGrid which will create a Grid with cells based on the rules determined by the active Model 
	+ Menu will handle clicking and buttons with getInput method and will update the Grid and the active Model from this input
	+ Menu will call the Grid to update with updateGrid()
	

+ The Grid class houses a collection of cell objects, and a model
	+ Grid will handle interactions between the Cells and Model with the method updateCells-- which will call the cells to update their state based on their own information and the rules and logic defined in Model
	
+ Model will contain the logic and methods accessed by cells which informs them on their state
	+ cells will send information to the model and the model will return an updated state for each cell based on this information and the rules of the game defined in model. 
	+ For each game type a new model will be created and an abstraction will be used to ensure new models fit correctly in the framework 

+ Cells will contain their own past present and future states and a collection of the other cells around it
   + Cells will be able to send the information about their own state and surroundings to model, and will update their state based on the model
   + Cells will have access to all information about their neighbors with getNeighbor method 
   
### User Interface
The user interface will be handled and defined in the Menu class. It will be able to handle user input in the form of sliders, click buttons, and drop down menus. The basic plan for the layout is as follows: one window will contain a central grid of cells which will be handled by the underlying class structure, clickable buttons that will allow the user to step forward backward pause and play the simulation, a sliding element to define the step speed of the simulation, and one drop down menu to allow users to select different game models.


### Design Considerations
+ Conrad
	+ An idea I was particularly interested in was the idea of creating a Neighborhood class which would be contained within Cell that would have access to the surrounding cells of the middle one. We initially went with this idea because it simplified the potentially complex task of gathering information about cell state and updating the cell by splitting it into two more manageable pieces. After refactoring the idea of a model class to handle all rules and cell updating we decided that the neighborhood would not be needed. Using a Model simplified new state algorithms so we decided against the neighborhood
	+An idea that I really liked was creating an abstraction of cell to handle the different geographic types of cell. We noticed that in Fire CA corner cells and edge cells had different qualifications for updating and in preparation for this, are designing cell so that it can easily interact with the other classes but still handle the complexities of different games. Potential setbacks of this are overcomplication with classes
	+An idea that originally had doubts about was the creation of a model class to handle the logic for each games cells' updating. It was difficult to see exactly how it would interact with Cell and Grid. Eventually we decided that the grid would contain model, but the cell updating being outsourced to this class means the cell has to exchange a lot of informatino with Grid which will make it hard to implement. However, the pro that finally sold me on this point was that this method will allow us to write only one additional class to add a new game type

