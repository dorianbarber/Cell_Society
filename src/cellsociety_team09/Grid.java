package cellsociety_team09;

import java.util.ArrayList;

import javafx.scene.Group;

public class Grid {
	private ArrayList<ArrayList<CellModel>> gridCells; //please change to arraylist for dynamic size changing 
	private int gridSize;
	private Group cellSet;
	private static final CellModel[] possibleModels= {
			
	};
	
	public Grid(int size, int modelChoice) {
		gridSize = size;
		gridCells = new  ArrayList<ArrayList<CellModel>>();
		for(int i = 0; i < gridSize; i++) {
			for(int j = 0; j < gridSize; j++) {
				gridCells.get(i).set(j, possibleModels[modelChoice]);
				//cellSet.getChildren().add(gridCells.get(i).get(j));
			}
		}
	}
	
	//supposed to return the set of cells for the menu class to use
	public Group getCells() {
		return null;
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
}
