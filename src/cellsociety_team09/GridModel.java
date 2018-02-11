package cellsociety_team09;

import java.util.Collections;
import java.util.List;

import simulations.CellModel;

public abstract class GridModel {
	protected List<List<CellModel>> gridCells;
	protected int size;
	
	//To be overridden by each subclass
	public GridModel() {
	}
	
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
	
	public double getGridSize(){
		return (double) size;
	}

	public int getKind(){
		return 0;
	}
	
	
	public void xmlEdit(List<List<Integer>> xmlEdits) {
		for(List<Integer> list : xmlEdits) {
			int row = list.get(0);
			int col = list.get(1);
			List<Integer> listOfCellEdits = list.subList(2, list.size());
			gridCells.get(row).get(col).getInput(listOfCellEdits);
		}
	}
	
}
