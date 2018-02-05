package cellsociety_team09;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.JFileChooser;

import javafx.scene.Node;


/**
 * Manager for cell interactions, 
 * 
 * @author Dorian
 *
 */
public class Grid {
	private static ArrayList<ArrayList<CellModel>> gridCells; 
	private int gridSize;
	private static final CellModel[] possibleModels= {
			new LifeCell(),
			new SegregationCell()
	};
	private static final String[] xmlModel = {
			"GliderLifeCell.xml"
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
		ArrayList<ArrayList<Integer>> edits = this.getXMLFile(xmlModel[0]);
		for(int i = 0; i < edits.size(); i++) {
			int row = edits.get(i).get(0);
			int col = edits.get(i).get(1);
			gridCells.get(row).set(col, new LifeCell(edits.get(i).get(2)));
		}
	}
	
	//supposed to return the set of cells for the menu class to use
	public ArrayList<ArrayList<CellModel>> getCellSet() {
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
	
	/**
	 * Used for testing the grid class.
	 * Namely with the xml file.
	 * @param args
	 */
	public static void main(String[] args) {
		Grid tester = new Grid(10, 0);
		//Algorithm for editing the grid 
		ArrayList<ArrayList<Integer>> edits = tester.getXMLFile(xmlModel[0]);
		for(int i = 0; i < edits.size(); i++) {
			int row = edits.get(i).get(0);
			int col = edits.get(i).get(1);
			gridCells.get(row).set(col, new LifeCell(edits.get(i).get(2)));
		}
		tester.printGrid();
	}
	
	/**
	 * Used for testing the grid class.
	 * Prints the state of each cell.
	 */
	private void printGrid() {
		for(int i = 0; i < gridSize; i++) {
			for(int j = 0; j < gridSize; j++) {
				System.out.print(gridCells.get(i).get(j).getState() + " ");
			}
			System.out.println();
		}
	}
	
	/**
	 * Parses the give xml file and returns the array of points
	 * that require the grid to edit. 
	 */
	public ArrayList<ArrayList<Integer>> getXMLFile(String fileName) {
		XMLParser xml = new XMLParser("type");
		String filePath = String.format("data\\%s", fileName);
	    File file = new File(filePath);
		Map<String, String> map = xml.getModel(file);
		
		return xml.getEdits();
	}
}

