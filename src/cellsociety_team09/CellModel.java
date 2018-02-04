
package cellsociety_team09;

import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;
import java.util.*;
public abstract class CellModel {
	protected Shape shape;
	protected Color color;
	protected StateNode state;
	protected CellModel[] neighbors;
	
	
	public CellModel(){}
	
	abstract void getNeighbors(int row, int col, ArrayList<ArrayList<CellModel>> grid );
	
	abstract int[] getStates();
	
	abstract void findNextState();

	public abstract void moveForward(ArrayList<ArrayList<CellModel>> cellgrid);
	
	public abstract void getInput(ArrayList<Integer> states);

}

