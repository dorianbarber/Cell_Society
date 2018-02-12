# cellsociety

CompSci 308 Cell Society Project

Collaborators: Dorian Barber, Liam Pulsifer, Conrad Mitchell
Start Date: 2/5/18
End Date: 2/12/18
Estimated Number of Hours Worked (each): 30

### Roles

- Liam
	- Designed and implemented Main GUI (Menu) and GridView classes
	- contributed substantially to GridModel classes.
	
- Dorian
	- Designed and implemented GridModels
	- created XML-reading and writing classes
	- designed XML file layouts
	
- Conrad
	- Designed and impmlemented back-end logic for simulations
	- Wrote helper methods and classes like NeighborFinder
	
### Resources Used

- All outside resources used in this project came directly from Prof. Duvall's website

### Notable Files

- To Start Project
	- Use Menu.Java

- To Test Project
	- Also use Menu.java
	- XMLTester.java
	- Check UnusedReferences package for developmental classes that provide insight into our thought process
	
## OverView

We started this project with a design using "CellModel" as our main logic engine and abstraction. As we started on the completed implementation, we realized that our CellModel class was too restrictive for some of the new simulations (it didn't allow easy enough access to the whole grid), and so we attempted a major refactor. This was in some ways successfull - we feel that our code became easier to add new pieces to as our refactoring went along. Unfortunately, it also added to the complexity of the code in ways that we weren't quite ready for, and so our project became huge and filled with bugs that were difficult to remove. To improve this project in the future, we would probably prefer to start again from the ground up. 
	
### Error-Handling

- We expect this project to be able to handle the kinds of errors causable by user input, i.e.
	- Choosing an incorrect file type when loading xml files
	- Choosing no file at all 
	- Deprecated file paths

- However, the data files for the project are stored in the data directory, which may not be immediately apparent in the file explorer. These data files are xml files formatted by Dorian. They contain the Simulation Name and Number, the Simulation author, the size of the grid to be used for that simulation, and then a list of grid points with the state they should be initialized in as an integer. This provides all the necessary information for our simulator.

### Assumptions and Simplifications

- At the moment, we treat all simulations of a given type as having the same rules, even if they have different grid cell shapes. This is a simplification because it changes the expected behavior of the simulations with different shapes. It's not really feasible to have a Game of Life simulation with triangles with 12 neighbors, but we chose to leave it be for lack of time.
- In Wa-Tor World, we chose to give cells access to the future states of their neighbors so that no cells would ever contain, for example, two sharks. The rules given for this simulation were vague, so we decided this gave the most interesting simulations.


### Bugs

Major Bugs: 

- The Ants simulation doesn't work entirely as intended. Sometimes Ants follow each other instead of the food source.
- The RPS simulation does not work at all, so we disconnected it from the display -- The user can still select the option from the drop-down menu, but it doesn't actually do anything except display a message. We judged this to be a better option than leaving in a bug-riddled simulation.
- The displays for various different shapes aren't entirely lined up within the Grid window, and for some gid sizes they look really strange
- The simulation can lag when too many changes are applied to it in quick succession

### Features

- The user can change the state of any cell at will by clicking on it. Each click cycles through a new state so that the user can design totally new simulations on his or her own
- Using the "Save to xml file", the user can write the current simulation state to an xml file and then load it later with the "load xml file" button. 
- The "toggle gridlines" button allows the user to turn gridlines on or off for a different view of the simulation

 
