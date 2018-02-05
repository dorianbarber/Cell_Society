package cellsociety_team09;

import java.util.ArrayList;


/**
 * Manager for cell interactions, 
 * 
 * @author Dorian
 *
 */
public class Grid {
	private ArrayList<ArrayList<CellModel>> gridCells; 
	private int gridSize;
	private static final CellModel[] possibleModels= {
			new LifeCell(),
			new SegregationCell()
	};
	
	public Grid(int size, int modelChoice) {
		gridSize = size;
		gridCells = new  ArrayList<ArrayList<CellModel>>();
		for(int i = 0; i < gridSize; i++) {
			for(int j = 0; j < gridSize; j++) {
				gridCells.add(new ArrayList<CellModel>());
				gridCells.get(i).add(new LifeCell());
			}
		}
		gridCells.get(3).set(3, new LifeCell(1));
		gridCells.get(3).set(4, new LifeCell(1));
		gridCells.get(3).set(5, new LifeCell(1));
		this.findCellNeighbors();
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
				gridCells.get(i).get(j).moveForward(gridCells);
				
			}
		}
		for(int i = 0; i < gridSize; i++) {
			for(int j = 0; j < gridSize; j++) {
				gridCells.get(i).get(j).findNextState();
			}
		}
		
	}
	
	public static void main(String[] args) {
		Grid tester = new Grid(10, 0);
		tester.printGrid();
	}
	
	private void printGrid() {
		for(int i = 0; i < gridSize; i++) {
			for(int j = 0; j < gridSize; j++) {
				System.out.print(gridCells.get(i).get(j).getState() + " ");
			}
			System.out.println();
		}
		Grid tester = new Grid(125, 0);
	}
}

