package simulations;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import javafx.scene.shape.Rectangle;

public class SegregationGrid extends GridModel
{
	private double percentsimilar=.75;
	private Stack<SegregationCell> unsatisfied = new Stack<SegregationCell>();
	
	public SegregationGrid(int gridSize)
	{
		cellType = new SegregationCell();
		gridCells = new ArrayList<List<CellModel>>();
		size = gridSize;
		for(int a=0; a < size; a++)
		{
			gridCells.add(new ArrayList<CellModel>());
			for(int b=0; b<size; b++)
			{
//				SegregationCell r = (SegregationCell) gridCells.get(a).get(b);
//				gridCells.get(a).set(b,r);
				gridCells.get(a).add(new SegregationCell());
			}
		}
		NeighborFinder.getNeighbors(gridCells, new Rectangle(), "standard", "standard");
	}
	
	@Override
	public void setSize(int t)
	{
	ArrayList<List<CellModel>>tempCells = new ArrayList<List<CellModel>>();
		
		for(int a=0; a < t; a++)
		{
			tempCells.add(new ArrayList<CellModel>());
			for(int b=0; b<t; b++)
			{
				tempCells.get(a).add(new SegregationCell());
			}
		}
		if(t>size)
		{
			int center=(t-size)/2;
			for(int r=0; r<size; r++)
				for(int c=0; c<size; c++)
					tempCells.get(r+center).set(center+c, gridCells.get(r).get(c));
		}
		else
		{
			if(t<size)
			{
				int center=(size-t)/2;
				for(int r=0; r<t; r++)
					for(int c=0; c<t; c++)
						tempCells.get(r).set(c, gridCells.get(r+center).get(c+center));
			}
		}
		gridCells=tempCells;
		size=t;
		
	}
	
	public SegregationGrid()
	{
		this(40);
	}
	
	public int getKind(){
		return 2;
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
		NeighborFinder.getNeighbors(gridCells, new Rectangle(), "standard", "standard");

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
	

	
	
