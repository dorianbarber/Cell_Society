package simulations;

import java.util.ArrayList;
import java.util.List;

public class LifeGrid extends GridModel{

	public LifeGrid(int gridSize) {
		size = gridSize;
		for(int i = 0; i < size; i++) {
			gridCells.add(new ArrayList<CellModel>());
			for(int j = 0; j < size; j++) {
				gridCells.get(i).add(new LifeCell());
			}
		}
	}
	
	@Override
	public void update() {
		for(List<CellModel> row : gridCells) {
			for(CellModel cell: row) {
				LifeCell temp = (LifeCell) cell;
				temp.findNextState();
			}
		}
	}

	@Override
	public void moveForward() {
		for(List<CellModel> row : gridCells) {
			for(CellModel cell: row) {
				LifeCell temp = (LifeCell) cell;
				temp = temp.getNext();
			}
		}
	}

	@Override
	public void getInputGlobal(List<Integer> s) {
		// TODO Auto-generated method stub
		
	}
}
