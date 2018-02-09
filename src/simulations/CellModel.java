
package simulations;

import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;
import java.util.List;
import cellsociety_team09.StateNode;

public abstract class CellModel{
	protected Shape shape;
	protected Color color;
	protected StateNode state;
	protected List<CellModel> neighbors;
	
	//For XMLFile identification purposes
	public static final String DATA_TYPE = "Model";
	
	
	public CellModel(){}

	abstract int[] getStates();
	
	public abstract void findNextState();
	
	public abstract void moveForward();
	
	public abstract void getInput(List<Integer> states);

	public Color getColor(){
        return state.getColor();
	}
	public StateNode getKind(){
		return state;
	}
	
	//Tentative
	public void moveBackward()
	{
		if(state.hasPrev())
			state.moveBackward();
	}
}

