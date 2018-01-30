
CELL SOCIETY TEAM 09
===================

### Introduction
The problem our team is trying to solve is writing a program that will animate any 2D grid cellular automata simulation. Cellular automata are simulation based on some model that consists of grid cells that are in one of a finite number of states. Each cell within the simulation will have its neighbors defined relative to the specific cell. Over time a fixed rule is applied to the simulation in which will determine new states for each relevant cell. Some examples of cellular automata are Schelling's model of segregation and Wa-Tor World model of predator-prey relationships. The goal of this project is to be able to animate any cellular automata simulation given. 

The primary design goal of the project is to develop a set of classes that can adapt to any different cellular automata simulation. The flexibility will come into play when the cellular automata simulation is defined. Once a certain simulation is defined the built classes will be able to adapt to its specifications: the number of states and what those states are, what is to be considered within a cell's neighborhood, and the rule that the simulation will follow. The user will be closed to modifying code that depends on the algorithm. The only available section of code that will be open to extension is the addition of a new model or new behaviors of existing modules. Through following the open-closed principle, the program will resist changes that result in a cascading effect to the any dependent modules. One effective approach that we will exercise as a team is abstraction. By following this principle of object oriented design, we can create abstractions that are fixed and represent a set of possible behaviors. Since the abstraction is fixed, child modules are closed for modification. The behavior, however, can be extended by creating new derivatives of the abstraction. Executing the open-closed principle primarily through abstraction and strategic closure our program will reach a state of reusability and maintainablilty. 

___
### Overview
This project will have a design hierarchy that maximizes its ability to adapt to new variations of Cell Automation Simulations. In order to do this, our plan is to have rigid classes that do not change based on different game rules and develop one abstraction that can be extended to add new game types. Maintaining the smallest number of dynamic classes minimizes the extra work we need to do to add new functionality. Our hierarchy will contain the following classes: Grid, Cell, menu, and Model. Each class will interact with each other class as follows:

+ The menu acts as the UI and allows user input. It contains a grid and 
    + Menu allows the user to change game speed load new types and interact with cells
    + Menu contains setUpGrid which will create a Grid with cells based on the rules determined by the active Model 
    + Menu will handle clicking and buttons with getInput method and will update the Grid and the active Model from this input
    + Menu will call the Grid to update with updateGrid()
	

+ The Grid class houses a collection of cell objects, and a model
	+ Grid will handle interactions between the Cells and Model with the method updateCells-- which will call the cells to update their state based on their own information and the rules and logic defined in Model
	
+ Model will contain the logic and methods accessed by cells which informs them on their state
	+ Cells will send information to the model and the model will return an updated state for each cell based on this information and the rules of the game defined in model. 
	+ For each game type a new model will be created and an abstraction will be used to ensure new models fit correctly in the framework 

+ Cells will contain their own past present and future states and a collection of the other cells around it
   + Cells will be able to send the information about their own state and surroundings to model, and will update their state based on the model
   + Cells will have access to all information about their neighbors with getNeighbor method 

___
### User Interface
The user interface will be handled and defined in the Menu class. It will be able to handle user input in the form of sliders, click buttons, and drop down menus. The basic plan for the layout is as follows: 
* The left side of the window will contain a central grid of cells whose states will be handled by the Grid class 
*  The right side will contain clickable buttons that will allow the user to:
	1. pause and play the simulation
	2. step forward or backward (when paused)
	3. define the step speed of the simulation (slider)
	4. allow users to select different game models (drop-down menu)

![](./Planning Images/UI.jpg)



### Design Details
**Menu** 
* The menu class will house instance variables, including, but not limited to:
```java
private scene myScene;
private root myRoot;
private int animationSpeed;
private int gridSize;
private Grid myGrid;
```
It will also include several methods that serve to initialize the stage and scene and allow the user to choose a simulation. 
```java
//Will return the beginning scene
//(i.e. with a blank grid of default size
private Scene setUp(int gridsize, int startspeed){}
//There will be several kinds of input which will
//all follow this general pattern
private void handleKeyInput(KeyCode code) {}
//Pauses the simulation
private void pause(){}
//Loads the next grid state when next button pushed
private void updateGrid(){}
//Loads the previous grid state
//reverse of updateGrid(){}
private void prevGrid(){}
//Set the animation speed
private void setSpeed(){}
//Loads a different model into the grid class
private void setModel(Grid g){}
```
**Grid**
The grid class will mainly include two instance variables, though more will likely have to be included :
```java
//our implementation is undecided at the moment, but it
//will be some form of two-d grid of Cell ojects
private Cell[][] grid;
private Model currentModel;
```
It will also include methods to update each 


+ The Grid class houses a collection of cell objects, and a model
	+ Grid will handle interactions between the Cells and Model with the method updateCells-- which will call the cells to update their state based on their own information and the rules and logic defined in Model

___
### Design Considerations
+ Conrad
    + An idea I was particularly interested in was the idea of creating a Neighborhood class which would be contained within Cell that would have access to the surrounding cells of the middle one. We initially went with this idea because it simplified the potentially complex task of gathering information about cell state and updating the cell by splitting it into two more manageable pieces. After refactoring the idea of a model class to handle all rules and cell updating we decided that the neighborhood would not be needed. Using a Model simplified new state algorithms so we decided against the neighborhood
    + An idea that I really liked was creating an abstraction of cell to handle the different geographic types of cell. We noticed that in Fire CA corner cells and edge cells had different qualifications for updating and in preparation for this, are designing cell so that it can easily interact with the other classes but still handle the complexities of different games. Potential setbacks of this are overcomplication with classes
    + An idea that originally had doubts about was the creation of a model class to handle the logic for each games cells' updating. It was difficult to see exactly how it would interact with Cell and Grid. Eventually we decided that the grid would contain model, but the cell updating being outsourced to this class means the cell has to exchange a lot of information with Grid which will make it hard to implement. However, the pro that finally sold me on this point was that this method will allow us to write only one additional class to add a new game type
+ Dorian
    + At the initial onset of the project we first had our grid class being both the user interface and the container for the all the cells in the simulation. As we continued to think about the broad aspects and responsibilities of each class, we realized that separating the GUI and the grid class was the direction to head towards. The reason we concluded this had been better is because we deemed that the grid itself had its own responsibilities outside of the user interface. They would work closely together; however, separating class based on responsibility is one aspect of programming that creates efficiency and understandable code. We thought that the two classes would have been so closely associated that there would have been a large exchange of information, but keeping in mind JavaFX implementation the grid can simply exist within the GUI and respond to inputs that way.
    + One idea that we wrestled with as a group was how the sub classes of the model class would work with the rest of the program. Initially we had each cell containing the proper model which outlined what the specific responsibilities of cell were in that simulation. As we began to develop a broader scope of the design overview we realized that the grid will need access to the model in order to interpret the changes that will persist throughout the grid of cells. As in the design details, we have that the grid class will have a model. The model class will be the one that was pass the proper state to the cell based on the cell's current state and surrounding states. This interaction will take place in the grid class. We deemed this would have been better structurally because it limits access to the model, which is the most important backend component to the program. Instead of having NxN cells have access to the model, only the grid class has access to the model. 

___
### Team Responsibilities 
The program was divided into three main components stemming from the design overview: The user interface, the grid and cell classes, and the model and specific CA simulations. Outlined are the primary and secondary team responsibilities:
- **Liam**
  1. User Interface: this will sum up to taking responsibilty of how the Menu class will function and look
  2. The flexibility, intuitiveness, and appearance of the UI in terms of the models that can be used and how to navigate it
- **Conrad**
  1. The Model class: responsible for creating an effective abstract model class that properly follows the principles of abstraction and the open-closed principle
  2. Creating the subclasses to model, at the moment this includes the implementation for:
      - Conway's Game of Life
      - Spreading of Fire
      - Wa-Tor Model of Predator-Prey relationships
      - Schelling's Model of Segregation
  3. Making sure the implementation of each model properly passes the correct information to the rest of the program
- **Dorian**
  1. The Grid and Cell classes: responsible for creating a grid class which works properly with the abstract cell class, whose subclasses will include the corner cell, side cell, and middle cell
  2. Making sure the model functioning can be passed through to the cells without the cells being able to edit the instance of the model class
  3. Creating an efficient implementation of the grid class so that updating will not require excessive runtime complexity

The higher level plan of how the project is going to be created can be divided into a series of steps. The user interface is expecting to be one of the more complicated, trial-and-error sections, and thus will be brought together toward the end of the code. Initially the grid/cell and model classes will start to develop independently. Once the first implementation of a model subclass is created, testing between the grid/cell and model class can commence. Once the UI reaches to the point where there is an effective location for a set grid, the grid and menu classes can begin testing how they will fit together efficiently. The grid class can begin testing far earlier than the menu class is completed. A pseudo-visual can be created with print statements.

