package cellsociety_team09;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



/**
 * Manager for cell interactions, 
 * 
 * @author Dorian
 *
 */
public class Grid {
	private static ArrayList<ArrayList<CellModel>> gridCells; 
	private int gridSize;
	private int modelType;
//	private static final CellModel[] possibleModels= {
//			new LifeCell(),
//			new FireCell(),
//			new SegregationCell()
//	};
	
	
	private static final String[] xmlModel = {
			"GliderLifeCell.xml",
			"TestFireCell.xml",
			"TestSegregationCell.xml"
			//"BeaconLifeCell.xml"
	};
	
	Map<String, String> modelDescription =  new HashMap<>();
	
	public Grid(int size, int modelChoice) {
		modelType = modelChoice;
		gridSize = size;
		gridCells = new  ArrayList<ArrayList<CellModel>>();
		for(int i = 0; i < gridSize; i++) {
			for(int j = 0; j < gridSize; j++) {
				gridCells.add(new ArrayList<CellModel>());
				CellModel cell = getCell(modelChoice);
				gridCells.get(i).add(cell);
			}
		}
	}
	
	public Grid(int modelChoice) {
		modelType = modelChoice;
		ArrayList<ArrayList<Integer>> edits = this.getXMLFile(xmlModel[modelChoice]);
		gridSize = Integer.parseInt(modelDescription.get("Size"));
				
		gridCells = new  ArrayList<ArrayList<CellModel>>();
		for(int i = 0; i < gridSize; i++) {
			for(int j = 0; j < gridSize; j++) {
				gridCells.add(new ArrayList<CellModel>());
				CellModel cell = getCell(modelChoice);
				gridCells.get(i).add(cell);
			}
		}
		
		
		for(int i = 0; i < edits.size(); i++) {
			int row = edits.get(i).get(0);
			int col = edits.get(i).get(1);
			List<Integer> listOfCellEdits = edits.get(i).subList(2, edits.get(i).size());
			gridCells.get(row).get(col).getInput(listOfCellEdits);
		}
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
	
	/**
	 * Used for testing the grid class.
	 * Namely with the xml file.
	 * @param args
	 */
	public static void main(String[] args) {
		Grid tester = new Grid(2);
		
		tester.findCellNeighbors();
		System.out.println();
		for(int i = 0; i < 18; i++) {
			tester.printGrid();
			System.out.println();
			tester.moveSimulationForward();
		}
	}
	
	/**
	 * Used for testing the grid class.
	 * Prints the state of each cell.
	 */
	private void printGrid() {
		for(int i = 0; i < gridSize; i++) {
			for(int j = 0; j < gridSize; j++) {
				System.out.print(gridCells.get(i).get(j).getState() + " ");
				System.out.flush();  
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
		String filePath = String.format("data//%s", fileName);
	    File file = new File(filePath);
		modelDescription = xml.getModel(file);
		
		return xml.getEdits();
	}
	
	private CellModel getCell(int i) {
		if(i == 0) {
			return new LifeCell();
		} else if (i == 1) {
			return new FireCell();
		} else if(i == 2) {
			return new SegregationCell();
		} else {
			return null;
		}
		
	}
	
	public int getKind() {
		return modelType;
	}
}

