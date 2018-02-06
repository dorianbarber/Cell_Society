
package cellsociety_team09;

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
	
	
	public CellModel(){}
	
	abstract void getNeighbors(int row, int col, ArrayList<ArrayList<CellModel>> grid );
	
	abstract int[] getStates();
	
	abstract void findNextState();


	public abstract void moveForward(ArrayList<ArrayList<CellModel>> cellgrid);
	
	public abstract void getInput(ArrayList<Integer> states);

	public Color getColor(){
        return state.getColor();
	}
	
	public void moveBackward()
	{
		if(state.hasPrev())
			state.moveBackward();
	}
	
	protected abstract void setNextState(StateNode a);
	@Override
	public com.sun.javafx.geom.Shape impl_configShape() {
		// TODO Auto-generated method stub
		return null;
	}

	//@Conrad this is a method so that I can test whether the grid
	//class works well. I will delete this later. -Dorian
	public String getState() {
		return state.getStates()[0] + "";
	}

}

