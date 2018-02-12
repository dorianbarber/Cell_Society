
package simulations;
import javafx.scene.paint.Color;
import java.util.List;

/**
 * Abstract class for all cell types. Cells have states
 * colors and specific behaviors when updating their
 * state depending on the number of neighbors. 
 * 
 * Contains the logic behind each simulation. 
 * 
 * @author Dorian and Conrad
 *
 */
public abstract class CellModel{
	
	protected Color color;

	
	//For XMLFile identification purposes
	public static final String DATA_TYPE = "Model";

	public CellModel(){}	
	
	public abstract void addNeighbor(CellModel c);

	public abstract void getInput(List<Integer> list);
	
	public abstract void getClicked();
	
	public Color getColor(){
        return color;
	}
	
	//returns the state
	public abstract int getState();

	public abstract CellModel getNext();

	//returns the specific format for writing to xmlfiles
	public abstract String getXMLState();

}
