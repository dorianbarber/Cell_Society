package simulations;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.shape.Rectangle;

public class FireGrid extends GridModel{

	public FireGrid(int gridSize)
	{
		size = gridSize;
		for(int a=0; a < size; a++)
		{
			for(int b=0; b<size; b++)
			{
				RPSCell r = (RPSCell) gridCells.get(a).get(b);
				gridCells.get(a).set(b,r);
			}
		}
		NeighborFinder.getNeighbors(gridCells, new Rectangle(), "standard", "standard");
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
