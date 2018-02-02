package cellsociety_team09;

import javafx.scene.shape.Shape;

public abstract class Cell {
	private Shape cellShape;
	
	private int currentState;
	private int previousState;
	private int[] possibleStates;
	
	private int[] neighborStates;
	
	public Cell (int[] n) {
		possibleStates = n;
	}
	
	public void update() {
		previousState = currentState;
		currentState = Model.updateCell(possibleStates, neighborStates);
	}
}
