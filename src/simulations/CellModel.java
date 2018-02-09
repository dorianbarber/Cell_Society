
package simulations;

import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;
import java.util.ArrayList;
import java.util.List;
import cellsociety_team09.StateNode;

public abstract class CellModel extends Shape{
	protected Shape shape;
	protected Color color;
	protected StateNode state;
	protected List<CellModel> neighbors;
	
	//For XMLFile identification purposes
	public static final String DATA_TYPE = "Model";
	
	
	public CellModel(){}
	
<<<<<<< HEAD
	public abstract void getNeighbors(int row, int col, List<List<CellModel>> gridCells );
=======
	//public abstract void getNeighbors(int row, int col, ArrayList<ArrayList<CellModel>> grid );
>>>>>>> e6f8fe730e217ceccd2e6ea8e55de3a26ed2ef76

	
	abstract int[] getStates();
	
	public abstract void findNextState();


<<<<<<< HEAD
	public abstract void moveForward(List<List<CellModel>> gridCells);
=======
	public abstract void moveForward(List<List<CellModel>> cellgrid);
>>>>>>> e6f8fe730e217ceccd2e6ea8e55de3a26ed2ef76
	
	public abstract void getInput(List<Integer> states);

	public Color getColor(){
        return state.getColor();
	}
	public StateNode getKind(){
		return state;
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

	public void addNeighbor(CellModel cellModel) {
		this.neighbors.add(cellModel);
	}
}

