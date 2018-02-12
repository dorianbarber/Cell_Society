package simulations;

import java.util.Collections;
import java.util.List;

import cellsociety_team09.Hexagon;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public abstract class GridModel {
	protected List<List<CellModel>> gridCells;
	protected int size;
	protected CellModel cellType;
	protected String currentshape = "Square";
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
		
		gridCells.get(row).get(col).getClicked();
	}
	
	public void xmlEdit(List<List<Integer>> xmlEdits) {
		for(List<Integer> list : xmlEdits) {
			int row = list.get(0);
			int col = list.get(1);
			List<Integer> listOfCellEdits = list.subList(2, list.size());
			gridCells.get(row).get(col).getInput(listOfCellEdits);
		}
	}
	public int getKind(){
		return 0;
	}
	public void clear(){
		for(int i = 0; i < size; i++) {
			for(int j = 0; j < size; j++) {
				try {
					gridCells.get(i).set(j, cellType.getClass().newInstance());
				} catch (InstantiationException | IllegalAccessException e) {
					System.out.println("Not a viable cell model");
				}
			}
		}
	}
	public abstract void setSize(int t);

	public void setCurrentShape(String currentshape) {
		currentshape = currentshape;
	}
	public String getCurrentShape() {
		return currentshape;
	}
}
