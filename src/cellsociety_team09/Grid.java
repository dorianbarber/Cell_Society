package cellsociety_team09;

public class Grid {
	private Cell[][] gridCells; //please change to arraylist for dynamic size changing 
	private int gridSize;
	private Model simulation;
	
	public Grid(int n) {
		gridSize = n;
		gridCells = new Cell[gridSize][gridSize];
		
		
		
	}
}
