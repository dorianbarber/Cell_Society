package simulations;

import java.util.List;

import javafx.scene.shape.Rectangle;

public class RPSGrid extends GridModel
{
	
	public RPSGrid(int gridSize)
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
	public RPSGrid()
	{
		this(150);
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
	}


	@Override
	public void getInputGlobal(List<Integer> s) {
		// TODO Auto-generated method stub
		
	}
	
	
}
	

	
	
