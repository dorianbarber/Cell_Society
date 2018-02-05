package cellsociety_team09;

import java.io.File;
import java.util.ArrayList;
import java.util.Map;

import javax.swing.JFileChooser;


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
				gridCells.get(i).add(possibleModels[modelChoice]);
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
				gridCells.get(i).get(j).moveForward(gridCells);
			}
		}
		this.findCellNeighbors();
	}
	
	public static void main(String[] args) {
		Grid tester = new Grid(10, 0);
		ArrayList<ArrayList<Integer>> edits = tester.getXMLFile();
		tester.printGrid();
	}
	
	private void printGrid() {
		for(int i = 0; i < gridSize; i++) {
			for(int j = 0; j < gridSize; j++) {
				System.out.print(gridCells.get(i).get(j).getState() + " ");
			}
			System.out.println();
		}
	}
	
	public ArrayList<ArrayList<CellModel>> getCellSet(){
		return gridCells;
	}
	
	public ArrayList<ArrayList<Integer>> getXMLFile() {
		XMLParser xml = new XMLParser("type");
	    File file = new File("data\\practiceXMLFile.xml");
		Map<String, String> map = xml.getModel(file);
		return xml.getEdits();
	}
}

