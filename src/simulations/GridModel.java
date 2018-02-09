package simulations;

import java.util.Collections;
import java.util.List;

public abstract class GridModel {
	protected List<List<CellModel>> gridCells;
	protected int size;
	
	//To be overridden by each subclass
	public GridModel() {}
	
	public List<List<CellModel>> getCells() {
		return Collections.unmodifiableList(gridCells);
	}
	
	public int getSize() {
		return size;
	}
	
	public abstract void update();
	
	public abstract void moveForward();
	
	public abstract void getInput();
}
