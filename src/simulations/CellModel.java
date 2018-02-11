
package simulations;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;
import java.util.ArrayList;
import java.util.List;
import cellsociety_team09.StateNode;

public abstract class CellModel{
	
	protected Color color;

	
	//For XMLFile identification purposes
	public static final String DATA_TYPE = "Model";

	public CellModel(){}	
//	public abstract void findNextState();
	public abstract void addNeighbor(CellModel c);

	public abstract void getInput(List<Integer> list);
	
	public abstract void getClicked();
//	public abstract void moveForward(List<List<CellModel>> cellgrid);
	public Color getColor(){
        return color;
	}
	public int getState() {
		// TODO Auto-generated method stub
		return 0;
	}
	public void findNextState() {
		// TODO Auto-generated method stub
		
	}
	public CellModel getNext() {
		// TODO Auto-generated method stub
		return null;
	}
}
