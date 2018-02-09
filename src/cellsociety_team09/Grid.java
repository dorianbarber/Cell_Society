package cellsociety_team09;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import simulations.CellModel;
import simulations.FireCell;
import simulations.LifeCell;
//import simulations.NullCell;
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
	private static List<List<CellModel>> gridCells; 
	private int gridSize;
	private int modelType;
	private String description = "";
	
	
	private static final String[] xmlModel = {
			"PulsarLifeCell.xml",
			"TestFireCell.xml",
			"TestSegregationCell.xml",
			"Wa-TorCell.xml"

	};
	
	Map<Integer, CellModel> possibleModels;
	
	Map<String, String> modelDescription;
	
	/**
	 * Constructs a grid with @param size and the specific @param modelChoice.
	 * Purpose behind this constructor is to give the user the ability to change
	 * the size of the grid. 
	 */
	public Grid(int size, int modelChoice) {
		setMapOfModels();
		modelType = modelChoice;
		gridSize = size;
		gridCells = new  ArrayList<List<CellModel>>();
		for(int i = 0; i < gridSize; i++) {
			gridCells.add(new ArrayList<CellModel>());
			for(int j = 0; j < gridSize; j++) {
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
		setMapOfModels();
		modelType = modelChoice;
		ArrayList<ArrayList<Integer>> edits = this.getXMLFile(xmlModel[modelChoice]);
		
		try {
			gridSize = Integer.parseInt(modelDescription.get("Size")) + 2;
		}
		catch(NumberFormatException e) {
			gridSize = 52;
		}
		
				
		gridCells = new  ArrayList<List<CellModel>>();
		for(int i = 0; i < gridSize; i++) {
			gridCells.add(new ArrayList<CellModel>());
			for(int j = 0; j < gridSize; j++) {
				CellModel cell;
				if(i == 0 || j == 0 || i == gridSize || j == gridSize) {
					cell = getCell(modelChoice);
				} else {
					cell = getCell(modelChoice);
				}
				gridCells.get(i).add(cell);
			}
		}
		
		//Applies the specific constraints from the XML file being read in
		for(int i = 0; i < edits.size(); i++) {
			int row = edits.get(i).get(0) + 1;
			int col = edits.get(i).get(1) + 1;
			List<Integer> listOfCellEdits = edits.get(i).subList(2, edits.get(i).size());
//REVISIT THIS TRY/CATCH
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
	public List<List<CellModel>> getCells() {
		return deepCopy(gridCells);
	}
	
	//returns the unmodifiable list
	private List<List<CellModel>> deepCopy (List<List<CellModel>> original){
		int size = original.size();
		
		List<List<CellModel>> shortened = original.subList(1, size - 1);

		System.out.println(shortened.get(1).size());
		System.out.println(size);
		for(int i = 0; i < size - 2; i++) {
			List<CellModel> row = shortened.get(i).subList(1, size);
			shortened.set(i, row);
		}
		
		return Collections.unmodifiableList(shortened);
	}
	
	//returns the dimension of the grid
	public int getGridSize() {
		return gridSize - 2;
	}
	
	
	public int getKind() {
		return modelType;
	}
	
	
	/**
	 * Loops through the cells to let them find their
	 * neighbors and also get their next state
	 */
	public void findCellNeighbors() {
		for(int i = 1; i < gridSize - 1; i++) {
			for(int j = 1; j < gridSize - 1; j++) {
				gridCells.get(i).get(j).getNeighbors(i, j, getCells());
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
				gridCells.get(i).get(j).moveForward(getCells());
				
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
	
	private void setMapOfModels() {
		possibleModels = new HashMap<Integer, CellModel>();
		possibleModels.put(0, new LifeCell());
		possibleModels.put(1, new FireCell());
		possibleModels.put(2, new SegregationCell());
		possibleModels.put(3, new WatorCell());
		
	}
	/**
	 * ASK TA ABOUT THIS CONCEPT
	 * ...does not seem like good Java OOP convention
	 * @return the new instance of the CellModel subclass
	 */
	private CellModel getCell(int i) {
		CellModel result = possibleModels.get(i);
		try {
			return result.getClass().newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			System.out.println("Error in picking the proper cell model. Defaulting to LifeCell.");
		}
		return new LifeCell();
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

