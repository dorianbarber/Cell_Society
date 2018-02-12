package simulations;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.shape.Rectangle;

public class LangstonGrid extends GridModel
{
	private int[] rows  = {0,0,0,0,0,0,0,0,
			 1,1,1,1,1,1,1,1,1,1,
			 2,2,2,2,2,2,2,2,2,2,
			 3,3,3,3,3,3,
			 4,4,4,4,4,4,
			 5,5,5,5,5,5,
			 6,6,6,6,6,6,
			 7,7,7,7,7,7,7,7,7,7,7,7,7,7,
			 8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,
			 9,9,9,9,9,9,9,9,9,9,9,9,9};
private int[] cols = {1,2,3,4,5,6,7,8,
			  0,1,2,3,4,5,6,7,8,9,
			  0,1,2,3,4,5,6,7,8,9,
			  0,1,2,7,8,9,
			  0,1,2,7,8,9,
			  0,1,2,7,8,9,
			  0,1,2,7,8,9,
			  0,1,2,3,4,5,6,7,8,9,10,11,12,13,
			  0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,
			  1,2,3,4,5,6,7,8,9,10,11,12,13};
private int[] colors = {2,2,2,2,2,2,2,2,
				2,1,7,0,1,4,0,1,4,2,
				2,0,2,2,2,2,2,2,0,2,
				2,7,2,2,1,2,
				2,1,2,2,1,2,
				2,0,2,2,1,2,
				2,7,2,2,1,2,
				2,1,2,2,2,2,2,2,1,2,2,2,2,2,
				2,0,7,1,0,7,1,0,7,1,1,1,1,1,2,
				2,2,2,2,2,2,2,2,2,2,2,2,2};
private int[] direct= {3,3,3,3,3,2,0,2,2,2,2,1,1,1,1,1};

	public LangstonGrid(int gridSize)
	{
		cellType = new LangstonCell(0, 0);
		gridCells = new ArrayList<List<CellModel>>();
		size = gridSize;
		for(int a=0; a < size; a++)
		{
			gridCells.add(new ArrayList<CellModel>());
			for(int b=0; b<size; b++)
			{
				gridCells.get(a).add(new LangstonCell(0,0));
			
			}
		}
		NeighborFinder.getNeighbors(gridCells, new Rectangle(), "cross", "toroidal");
		
		int b=0;
    	for(int a=0; a<colors.length; a++)
    	{
    		LangstonCell temp =  (LangstonCell) gridCells.get(20+rows[a]).get(20+cols[a]);
    		if(colors[a] == 2 || colors[a]==1)
    			temp.setState(colors[a],0);
    		else {
    			temp.setState(colors[a],direct[b]);
    			b++;
    		}
    	}
	}
	
	public void setSize(int t)
	{
	ArrayList<List<CellModel>>tempCells = new ArrayList<List<CellModel>>();
		
		for(int a=0; a < t; a++)
		{
			tempCells.add(new ArrayList<CellModel>());
			for(int b=0; b<t; b++)
			{
				tempCells.get(a).add(new LangstonCell(0,0));
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
	
	@Override
	public void update() {

		for(int r=0; r<size; r++)
			for(int c=0; c<size; c++) {
				LangstonCell t = (LangstonCell) gridCells.get(r).get(c);
				//System.out.println(t.getState());
				t.getNextState();
			}
	}

	@Override
	public void moveForward() {
		for(int r=0; r<size; r++)
			for(int c=0; c<size; c++) {
				LangstonCell t = (LangstonCell) gridCells.get(r).get(c);
				gridCells.get(r).set(c,  t.getNext());

		
			}
		System.out.println();

		NeighborFinder.getNeighbors(gridCells, new Rectangle(), "cross", "toroidal");

	}

	@Override
	public void getInputGlobal(List<Integer> s) {
		// TODO Auto-generated method stub
		
	}

}
