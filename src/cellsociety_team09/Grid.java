package cellsociety_team09;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import simulations.CellModel;
import simulations.FireCell;
import simulations.LifeCell;
import simulations.SegregationCell;
import simulations.WatorCell;
import xml_related_package.XMLParser;



/**
 * Manager for cell interactions 
 * 
 * @author Dorian
 *
 */
public class Grid {
	private static ArrayList<ArrayList<CellModel>> gridCells; 
	private int gridSize;
	private int modelType;
	private String description = "";
//	private static final CellModel[] possibleModels= {
//			new LifeCell(),
//			new FireCell(),
//			new SegregationCell(),
//			new WatorCell()
//	};
	
	
	private static final String[] xmlModel = {
			"PulsarLifeCell.xml",
			"TestFireCell.xml",
			"TestSegregationCell.xml",
			"Wa-TorCell.xml"
			//"GliderLifeCell.xml",
			//"AcornLifeCell.xml",
	};
	
	Map<String, String> modelDescription =  new HashMap<>();
	
	/**
	 * Constructs a grid with @param size and the specific @param modelChoice.
	 * Purpose behind this constructor is to give the user the ability to change
	 * the size of the grid. 
	 */
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
	
	/**
	 * Standard grid constructor that will select the specific XML to be 
	 * read into the program. 
	 * @param modelChoice
	 */
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
		
		//Applies the specific constraints from the XML file being read in
		for(int i = 0; i < edits.size(); i++) {
			int row = edits.get(i).get(0);
			int col = edits.get(i).get(1);
			List<Integer> listOfCellEdits = edits.get(i).subList(2, edits.get(i).size());
			try {
				gridCells.get(row).get(col).getInput(listOfCellEdits);
			}
			catch(NullPointerException e) {
				System.out.println(i);
			}
		}
		this.findCellNeighbors();
	}
	
	
	public void setDescription(String s){
		description = s;
	}
	public String getDescription(){
		return description;
	}
	
	
	//return the set of cells for the menu class to use
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
	
	
	/**
	 * ASK TA ABOUT THIS CONCEPT
	 * ...does not seem like good Java OOP convention
	 * @return the new instance of the CellModel subclass
	 */
	private CellModel getCell(int i) {
		if(i == 0) {
			return new LifeCell();
		} else if (i == 1) {
			return new FireCell();
		} else if(i == 2) {
			return new SegregationCell();
		} else if(i == 3){
			return new WatorCell();
		} else {
			return null;
		}
		
	}
	
	public int getKind() {
		return modelType;
	}
	
	//NEXT METHODS ARE FOR TESTING THE GRID CLASS
	
	/**
	 * Used for testing the grid class.
	 * Namely with the xml file.
	 * @param args
	 */
	public static void main(String[] args) {
		//input can either be a 0,1,2,3
		Grid tester = new Grid(3);
		
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
	
	
}

