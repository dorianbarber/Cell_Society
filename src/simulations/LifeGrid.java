package simulations;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.shape.Rectangle;

public class LifeGrid extends GridModel{

	public LifeGrid(int gridSize) {
		size = gridSize;
		gridCells = new ArrayList<List<CellModel>>();
		for(int i = 0; i < size; i++) {
			gridCells.add(new ArrayList<CellModel>());
			for(int j = 0; j < size; j++) {
				gridCells.get(i).add(new LifeCell());
			}
		}
	}
	public LifeGrid() {
		this(50);
	}
	
	@Override
	public void update() {
		for(List<CellModel> row : gridCells) {
			for(CellModel cell: row) {
//				LifeCell temp = (LifeCell) cell;
//				temp.findNextState();
				cell.findNextState();
			}
		}
	}
	public void clear(){
		for(int i = 0; i < size; i++) {
			//gridCells.add(new ArrayList<CellModel>());
			for(int j = 0; j < size; j++) {
				gridCells.get(i).set(j, new LifeCell());
			}
		}
	}
	public int getKind(){
		return 0;
	}

	@Override
	public void moveForward() {
		for(List<CellModel> row : gridCells) {
			for(CellModel cell: row) {
//				LifeCell temp = (LifeCell) cell;
//				temp = temp.getNext();
				cell = cell.getNext();
			}
		}

		NeighborFinder.getNeighbors(this.getCells(), new Rectangle(), "standard", "standard");
	}

	@Override
	public void getInputGlobal(List<Integer> s) {
		// TODO Auto-generated method stub
		
	}
}
