package cellsociety_team09;

import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;

public abstract class CellModel {
	private Shape shape;
	private Color color;
	private StateNode state;
	private CellModel[] neighbors;
	
	
	public CellModel() {
		
	}
	
	abstract void getNeighbors();
	
	abstract void findNextState();

	public void moveForward() {
		state.moveForward();
	}
	
	
}
