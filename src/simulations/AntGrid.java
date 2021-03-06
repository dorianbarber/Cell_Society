package simulations;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.shape.Rectangle;

public class AntGrid extends GridModel{

	public AntGrid(int gridSize)
	{

		cellType = new AntsCell();
		gridCells = new ArrayList<List<CellModel>>();
		size = gridSize;
		for(int a=0; a < size; a++)
		{
			gridCells.add(new ArrayList<CellModel>());
			for(int b=0; b<size; b++)
			{
				AntsCell r = new AntsCell();
				r.setRC(a,b);
				gridCells.get(a).add(r);			
			}
		}
		ArrayList<Ant> as = new ArrayList<Ant>();
		for(int a=0; a<8; a++)
			as.add(new Ant(27,27));
		
		
		gridCells.get(27).set(27, new AntsCell(0,as,0,0,27,27));
		
		
		for(int a=0; a<8; a++)
			as.add(new Ant(29,29));
		
		
		gridCells.get(29).set(29, new AntsCell(0,as,0,0,29,29));
		

		NeighborFinder.getNeighbors(gridCells, currentshape, "standard", "standard");	
		
	}
	
	
	public AntGrid()
	{
		this(70);		
	}
	
	public int getKind(){
		return 4;
	}
	
	@Override
	public void update() {
		//System.out.println("yo");
		for(int r=0; r<size; r++)
			for(int c=0; c<size; c++) {
				AntsCell temp = (AntsCell) gridCells.get(r).get(c);
				temp.nextState();
			}
		for(int r=0; r<size; r++)
			for(int c=0; c<size; c++) {
				AntsCell temp = (AntsCell) gridCells.get(r).get(c);
				temp.getNextState();
			}	
	}

	@Override
	public void moveForward()
	{
		for(int r=0; r<size; r++)
			for(int c=0; c<size; c++)
			{
				AntsCell temp = (AntsCell) gridCells.get(r).get(c);
				gridCells.get(r).set(c, temp.getNext());
			}
		NeighborFinder.getNeighbors(gridCells,currentshape, "standard", "standard");	

	}

	@Override
	public void getInputGlobal(List<Integer> s) {
		// TODO Auto-generated method stub
		
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
				tempCells.get(a).add(new AntsCell());
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

}
