package simulations;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.shape.Rectangle;

public class RPSGrid extends GridModel
{
	
	public RPSGrid(int gridSize)
	{
		cellType = new RPSCell();
		gridCells = new ArrayList<List<CellModel>>();
		size = gridSize;
		currentshape="Square";

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
		NeighborFinder.getNeighbors(gridCells, currentshape, "standard", "standard");	
	}
	
	public void setSize(int t)
	{
	ArrayList<List<CellModel>>tempCells = new ArrayList<List<CellModel>>();
		
		for(int a=0; a < t; a++)
		{
			tempCells.add(new ArrayList<CellModel>());
			for(int b=0; b<t; b++)
			{
				tempCells.get(a).add(new RPSCell());
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
	public RPSGrid()
	{
		this(70);
	}
	
	public int getKind(){
		return 5;
	}

	@Override
	public void moveForward()
	{
		for(int r=0; r<size; r++){
			gridCells.add(new ArrayList<CellModel>());
			for(int c=0; c<size; c++)
			{
				RPSCell temp = (RPSCell) gridCells.get(r).get(c);
				gridCells.get(r).set(c, temp.getNext());
			}
		}
		NeighborFinder.getNeighbors(gridCells, currentshape, "standard", "standard");	
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
	

	
	
