package simulations;

import java.util.List;
import java.util.Stack;

import javafx.scene.shape.Rectangle;

public class SegregationGrid extends GridModel
{
	private double percentsimilar=.7;
	private Stack<SegregationCell> unsatisfied = new Stack<SegregationCell>();
	
	public SegregationGrid(int gridSize)
	{
		size = gridSize;
		for(int a=0; a < size; a++)
		{
			for(int b=0; b<size; b++)
			{
				SegregationCell r = (SegregationCell) gridCells.get(a).get(b);
				gridCells.get(a).set(b,r);
			}
		}
		NeighborFinder.getNeighbors(gridCells, new Rectangle(), "standard", "standard");
	}
	

	@Override
	public void moveForward()
	{
		for(int r=0; r<size; r++)
			for(int c=0; c<size; c++)
			{
				SegregationCell temp = (SegregationCell) gridCells.get(r).get(c);
				gridCells.get(r).set(c, temp.getNext());
			}
	}
	
	@Override
	public void update()
	{
		int row=0;
		int col=0;
		SegregationCell moving=null;
		for(int r=0; r<size; r++)
			for(int c=0; c<size; c++) {
				SegregationCell temp = (SegregationCell) gridCells.get(r).get(c);
				SegregationCell t =temp.findNextState(percentsimilar);
				if(t!=null)
					unsatisfied.add(t);
			}
		while(!unsatisfied.isEmpty())
		{
			
			row=(int)(Math.random()*size);
			col=(int)(Math.random()*size);
			SegregationCell temp = (SegregationCell) gridCells.get(row).get(col);
			if(temp.getState()==0)
			{
				moving=unsatisfied.pop();
				temp.setState(moving.getState());
				temp.setNextState(moving.getState());
				moving.setState(0);
				moving.setNextState(0);
			}
		}
	}


	@Override
	public void getInputGlobal(List<Integer> s) {
		percentsimilar=s.get(0);	
	}
	
	
}
	

	
	