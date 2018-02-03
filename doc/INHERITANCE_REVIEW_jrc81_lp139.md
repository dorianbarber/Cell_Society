### Inheritance Review - Front End

#### Part 1

1. What is an implementation decision that your design is encapsulating (i.e., hiding) for other areas of the program?

jrc81: Only the Visualizer classes are aware of how objects are visually represented - the objects themselves do not know how they appear on the screen.

lp139: The Grid class is responsible for interpreting the states of the cells and returning their displays - the cells themselves do not know how they appear on the screen. The main class does not know about the cells, just how they appear. 
	
2. What inheritance hierarchies are you intending to build within your area and what behavior are they based around?

jrc81: I have a Visualizer interface and classes that implement the interface depending on the shape of the grid. For this basic implementation, I just have a SquareVisualizer class that initializes the display of a square grid. 

lp139: In the UI, I am not planning on having any inheritance hierarchies, but it will not deal with any simulation logic, just displaying things. 

3. What parts within your area are you trying to make closed and what parts open to take advantage of this polymorphism you are creating?

jrc81: The Visualizer class is open to extension but closed to modification - in order to add a new representation of a Grid, one only needs to add a new subclass and the original interface or other subclasses do not need to be edited. 

lp139: The Grid class is open to extension but closed to modification - the Grid class will have different subclasses for different shapes of Grids and each will have its own visualization. Adding a new visualization would only require that a new subclass of Grid be created.

4. What exceptions (error cases) might occur in your area and how will you handle them (or not, by throwing)?

jrc81: If the file doesn't exist/ is corrupted/ is the wrong kind of file, I should probably throw and IllegalArgumentException. 

lp139: If the file doesn't exist/ is corrupted/ is the wrong kind of file, I should probably throw and IllegalArgumentException. 

5. Why do you think your design is good (also define what your measure of good is)?

jrc81: I think my design is good because it separates the visualization from the logic of the simulation. I also think it is pretty easily extendable; we have a lot of interfaces that make it easy to add new features and simulations. 

lp139: I think my design is good because it is not dependent upon the other classes. It just takes what the other classes give it and display it; it doesn't have to decide what to display.

#### Part 2

1. How is your area linked to/dependent on other areas of the project?

jrc81: My display is dependent upon the Reader class - the Reader class needs to determine which simulation and what type of Grid needs to be displayed.

lp139: My display is very detached from the other classes. The menu class will display whatever Grid is passed to it and the display does not need to decide what to display

2. Are these dependencies based on the other class's behavior or implementation?

jrc81: If the Reader class throws an error for some reason and cannot pass a proper Grid to the Visualizer, then nothing will get visualized. 

lp139: Whatever the Grid class returns is what will be displayed - the display is completely dependent upon this return object.

3. How can you minimize these dependencies?

jrc81: Because the display doesn't have to do with any of the logic of the other classes, I think that's a good start to minimizing dependency. My Visualizer will just visualize whatever it is given.

lp139: Anything that doesn't require other class' input will be displayed by default and all other aspects will have a default display that will be displayed if there is an error on the Back End.

4. Go over one pair of super/sub classes in detail to see if there is room for improvement.

jrc81: In my SquareViewer class, I am going to add outlines to my rectangles instead of also drawing lines. This way I won't need to draw as many objects. 

lp139: 
 
5. Focus on what things they have in common (these go in the superclass) and what about them varies (these go in the subclass).

jrc81:

lp139: 