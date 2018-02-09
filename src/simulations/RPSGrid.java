package simulations;

import com.sun.javafx.geom.Rectangle;

public class RPSGrid extends GridModel{

	private int size=0;
	
	
	public RPSGrid()
	{
		size=gridCells.get(0).size();
		for(int a=0; a<size.size(); a++)
		{
			for(int b=0; b<size.size(); b++)
			{
				RPSCell r = (RPSCell) gridCells.get(a).get(b);
				gridCells.get(a).set(b,r);
			}
		}
		NeighborFinder.getNeighbors(gridCells, new Rectangle(), 0);
	}
	
	
	@Override
	public void update()
	{
		for(int r=0; r<size; r++)
			for(int c=0; c<size; c++)
				gridCells.get(r).get(c).nextState();
	}
	
	@Override
	public void moveForward()
	{
		for(int r=0; r<size; r++)
			for(int c=0; c<size; c++)
				gridCells.get(r).get(c)= gridCells.get(r).get(c).getNext();
	}
	
	
	@Override
	public void getInput(List<Integer>)
	{
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
