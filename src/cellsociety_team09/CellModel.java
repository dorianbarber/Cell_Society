
package cellsociety_team09;

import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;
import java.util.ArrayList;

public abstract class CellModel extends Shape{
	protected Shape shape;
	protected Color color;
	protected StateNode state;
	protected CellModel[] neighbors;
	
	//For XMLFile identification purposes
	public static final String DATA_TYPE = "Model";
	
	
	public CellModel() {
		
	}
	
	abstract void getNeighbors(int row, int col, ArrayList<ArrayList<CellModel>> grid );
	
	abstract int[] getStates();
	
	abstract void findNextState();

	public void moveForward() {
		state.moveForward();
	}
	public Color getColor(){
		return color;
	}
	
	
	@Override
	public com.sun.javafx.geom.Shape impl_configShape() {
		// TODO Auto-generated method stub
		return null;
	}
	
}

