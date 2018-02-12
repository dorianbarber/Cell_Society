package simulations;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.shape.Rectangle;

public class RPSGrid extends GridModel
{
	
	public RPSGrid(int gridSize)
	{
		gridCells = new ArrayList<List<CellModel>>();
		size = gridSize;
		for(int a=0; a < size; a++)
		{
			gridCells.add(new ArrayList<CellModel>());
			for(int b=0; b<size; b++)
			{
//				WatorCell r = (WatorCell) gridCells.get(a).get(b);
//				gridCells.get(a).set(b,r);
				gridCells.get(a).add(new RPSCell());
			}
		}
		NeighborFinder.getNeighbors(gridCells, new Rectangle(), "cross", "toroidal");
	}
	public RPSGrid()
	{
		this(50);
	}
	
	public int getKind(){
		return 5;
	}

	@Override
	public void moveForward()
	{
		for(int r=0; r<size; r++)
			for(int c=0; c<size; c++)
			{
				RPSCell temp = (RPSCell) gridCells.get(r).get(c);
				gridCells.get(r).set(c, temp.getNext());
			}
	}
	
	@Override
	public void update()
	{
		for(int r=0; r<size; r++)
			for(int c=0; c<size; c++) {
				RPSCell temp = (RPSCell) gridCells.get(r).get(c);
				temp.nextState();
			}
		NeighborFinder.getNeighbors(gridCells, new Rectangle(), "standard", "standard");

	}


	@Override
	public void getInputGlobal(List<Integer> s) {
		// TODO Auto-generated method stub
		
	}
	
	
}
	

	
	
