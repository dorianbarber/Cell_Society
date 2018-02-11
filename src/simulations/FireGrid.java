package simulations;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.shape.Rectangle;

public class FireGrid extends GridModel{

	public FireGrid(int gridSize)
	{
		gridCells = new ArrayList<List<CellModel>>();
		size = gridSize;
		for(int a=0; a < size; a++)
		{
			gridCells.add(new ArrayList<CellModel>());
			for(int b=0; b<size; b++)
			{
				gridCells.get(a).add(new FireCell(2,.7));
			}
		}
		NeighborFinder.getNeighbors(gridCells, new Rectangle(), "cross", "standard");
	}
	public FireGrid()
	{
		this(50);
	}
	
	@Override
	public void update() {
		for(List<CellModel> row : gridCells) {
			for(CellModel cell: row) {
				FireCell temp = (FireCell) cell;
				temp.findNextState();
			}
		}
	}
	
	public int getKind(){
		return 1;
	}
	
	@Override
	public void moveForward()
	{
		for(int r=0; r<size; r++)
			for(int c=0; c<size; c++)
			{
				FireCell temp = (FireCell) gridCells.get(r).get(c);
				gridCells.get(r).set(c, temp.getNext());
			}
		NeighborFinder.getNeighbors(gridCells, new Rectangle(), "cross", "standard");
	}

	@Override
	public void getInputGlobal(List<Integer> s) {
		int firechance=s.get(0);
		for(int r=0; r<size; r++)
			for(int c=0; c<size; c++)
			{
				FireCell temp = (FireCell) gridCells.get(r).get(c);
				temp.setFire(firechance);
			}	
	}
	
	
}
