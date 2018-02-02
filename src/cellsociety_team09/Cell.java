package cellsociety_team09;

import javafx.scene.shape.Shape;

public abstract class Cell {
	//the shape of the cell in the simulation
	private Shape cellShape;
	
	private int currentState;
	private int previousState;
	private int[] possibleStates;
	
	//requires an instance of Model to update the cell class
	private Model simulation;
	
	private int[] neighborStates;
	
	public Cell (int[] n, Model sim) {
		simulation = sim;
		possibleStates = n;
	}
	
	public void update() {
		previousState = currentState;
		currentState = simulation.updateCell(possibleStates, neighborStates);
	}
}
