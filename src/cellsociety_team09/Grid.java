package cellsociety_team09;

import java.util.ArrayList;

//import javafx.scene.Group;

/**
 * Manager for cell interactions, 
 * 
 * @author Dorian
 *
 */
public class Grid {
	private ArrayList<ArrayList<CellModel>> gridCells; 
	private int gridSize;
	//private Group cellSet;
	private static final CellModel[] possibleModels= {
			new SegregationCell()
	};
	
	public Grid(int size, int modelChoice) {
		gridSize = size;
		gridCells = new  ArrayList<ArrayList<CellModel>>();
		for(int i = 0; i < gridSize; i++) {
			for(int j = 0; j < gridSize; j++) {
				gridCells.add(new ArrayList<CellModel>());
				gridCells.get(i).add(possibleModels[modelChoice]);
				//cellSet.getChildren().add(gridCells.get(i).get(j));
			}
		}
	}
	
	//supposed to return the set of cells for the menu class to use
	public ArrayList<ArrayList<CellModel>> getCells() {
		return gridCells;
	}
	
	//returns the dimension of the grid
	public int getGridSize() {
		return gridSize;
	}
	
	/**
	 * Loops through the cells to let them find their
	 * neighbors and also get their next state
	 */
	public void findCellNeighbors() {
		for(int i = 0; i < gridSize; i++) {
			for(int j = 0; j < gridSize; j++) {
				gridCells.get(i).get(j).getNeighbors(i, j, gridCells);
				gridCells.get(i).get(j).findNextState();
			}
		}
	}
	
	/**
	 * Loops through cells to move each one to their next state
	 */
	public void moveSimulationForward() {
		for(int i = 0; i < gridSize; i++) {
			for(int j = 0; j < gridSize; j++) {
				gridCells.get(i).get(j).moveForward();
			}
		}
		this.findCellNeighbors();
	}
	
	public static void main(String[] args) {
		Grid tester = new Grid(125, 0);
	}
}
