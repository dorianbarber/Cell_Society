# INHERITANCE_REVIEW_CMM134
By Conrad Mitchell, Brendon Cheng, Ben Auriemma, Kelly Croggs
1. Part 1
	1. The rules for the simulation will be hidden away in the model class. To get the answer for the question what is my new cell state based on my neighbors, a method in model must be called and it will return a state for the cell to be and handle any other updates that the grid will update as necessitated. 
	2. Inheritance heirarchies that will be added to the program are cell and model. Cell will be a super to  middle, edge, and corner pieces to easier handle corner cases, and model will be a super to the different game types
	3. We are trying to reduce the number of classes that change. We are hoping to do this with model and only change one class for each new game type hence, all other classes will be closed and model will be open
	4. we are throwing exceptions for importing xmls that change states to states not specified in games and for asking about rules that don't exist
	5. We think our design is good because it is very extendable without changing much

2. Part 2
	1. The back end is primarily linked with the command class that is Grid. Grid will handle all communication between the back end data and the displayed data. Model itself does not rely on much, but it must fit with the rigidness of the other classes
	2. These dependencies are based on the fact that we want model to control most everything
	3. I believe we have minimized these dependencies 
	4. The super sub class relationship I am interested in is model and specific game models. As of now model will have no useable methods and will act as an interface more or less for the game types. I believe some cellular simulations have rules that can be generalized and put in the parent classs to minimize duplication
3. Part 3 
	1. Use cases 
		* The user wants to upload an xml document for one game while playing another game
		* The user wants to change the amount of cells in the grid during runtime
		* The user wants to run two simulations at once
		* The user wants to change the cell shape during runtime
		* The user wants to see previous game states
	2. I am most excited about making an efficient rule handling system within model to quickly prescribe new states based on the cell in question
	3. I am least excited about handling the idea of different shapes in the grid because I do not know how the grid will get mapped to the visualization