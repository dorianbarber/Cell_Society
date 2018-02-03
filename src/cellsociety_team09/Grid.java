package cellsociety_team09;

import javafx.scene.Group;

public class Grid {
	private CellModel[][] gridCells;
	private int gridSize;
	private Group cellSet;
	private static final CellModel[] possibleModels= {
			new Life_Model()
	};
	
	public Grid(int n) {
		gridSize = n;
		gridCells = new CellModel[gridSize][gridSize];
		for(int i = 0; i < gridSize; i++) {
			for(int j = 0; j < gridSize; j++) {
				gridCells[i][j] = new CellModel();
			}
		}
	}
	
	
	public Group getCells() {
		
	}
	
	/**
	 * Loops through the cells to let them find their
	 * neighbors and also get their next state
	 */
	public void findCellNeighbors() {
		for(int i = 0; i < gridSize; i++) {
			for(int j = 0; j < gridSize; j++) {
				gridCells[i][j].getNeighbors();
				gridCells[i][j].findNextState();
			}
		}
	}
	
	/**
	 * Loops through cells to move each one to their next state
	 */
	public void moveSimulationForward() {
		for(int i = 0; i < gridSize; i++) {
			for(int j = 0; j < gridSize; j++) {
				gridCells[i][j].moveForward();
			}
		}
		this.findCellNeighbors();
	}
}
