CELL SOCIETY TEAM 09 
====================
2. Overview
This project will have a design heirarchy that maximizes its ability to adapt to new variations of Cell Automation Simulations. In order to do this, our plan is to have rigid classes that do not change based on different game rules and develop one abstraction that can be extended to add new game types. Maintaining the smallest number of dynamic classes minimizes the extra work we need to do to add new functionality. Our heirarchy will contain the following classes: Grid, Cell, Neighborhood, menu, and Model. Each class will interact with each other class as follows:

+ The Grid class houses a collection of cell objects, and a model
    + The grid with use of the menu class which allows user interaction with the        cell game, will handle the front end UI and updating of the screen It will also 
    be responsible for importing new models of games and handling interactions          between cells and these models. 
    + Methods of the Grid class--
        +  updateCells: which will call cells to collect their information and send it to
        model for further direction on how to change
        +   updateGrid: which will take input from the menu as users input information and update itself as defined by the model it contains
        +   
+ Model will contain the logic and methods accessed by cells which informs them on    their state
    + cells will send information to the model and the model will return an updated state for each cell based on this information and the rules of the game defined in model. 
    + For each game type a new model will be created and an abstraction will be used to ensure new models fit correctly in the framework 
+ Cells will contain their own past present and future states and reference to their Neighborhood 
    + Cells will be able to send the information about their own state and surroundings to model, and will update their state based on the model
    + Cells will have access to all infomration about their neighbors
+ Neighborhood will be have infomraiton on the collection of cells defined by model as ones that have influence over the middle cell 
    + Neighborhood will contain methods that collect infomration about the cell states it contains and send it to the owner cell of the neighborhood 
+ Menu will be responsible for collecting user input and influencing how quickly grid updates cells and imports new game states 
    + Menu will be able to take direction from model on how to change the game state based on user input, and it will tell the Grid how to react to these inputs. 