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
	
	public abstract void getInputGlobal(List<Integer> s);
	
	public void getInput(List<List<Integer>> edits) {
		for(int i = 0; i < edits.size(); i++) {
			int row = edits.get(i).get(0);
			int col = edits.get(i).get(1);
			List<Integer> listOfCellEdits = edits.get(i).subList(2, edits.get(i).size());
			gridCells.get(row).get(col).getInput(listOfCellEdits);
		}
	}
	
	public void getUserInput(List<Integer> update)
	{
		int row = update.get(0);
		int col = update.get(1); 
		
		gridCells.get(row).get(col).getClicked());
	}
	
}
