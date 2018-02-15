package simulations;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.shape.Rectangle;

public class LifeGrid extends GridModel{


	public LifeGrid(int gridSize) {
		currentshape="Square";
		cellType = new LifeCell();
		gridCells = new ArrayList<List<CellModel>>();
		size = gridSize;
		for(int a=0; a < size; a++)
		{
			gridCells.add(new ArrayList<CellModel>());
			for(int b=0; b<size; b++)
			{
//				WatorCell r = (WatorCell) gridCells.get(a).get(b);
//				gridCells.get(a).set(b,r);
				gridCells.get(a).add(new LifeCell());
			}
		}
		NeighborFinder.getNeighbors(gridCells, currentshape, "standard", "standard");	
	}
	public LifeGrid() {
		this(20);
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
				tempCells.get(a).add(new LifeCell());
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
		for(List<CellModel> row : gridCells) {
			for(CellModel cell: row) {
				LifeCell temp = (LifeCell) cell;
//				temp.findNextState();
				temp.findNextState();
			}
		}
	}
	public void clear(){
		for(int i = 0; i < size; i++) {
			//gridCells.add(new ArrayList<CellModel>());
			for(int j = 0; j < size; j++) {
				gridCells.get(i).set(j, new LifeCell());
			}
		}
	}
	public int getKind(){
		return 0;
	}

	@Override
	public void moveForward()
	{
		for(int r=0; r<size; r++)
			for(int c=0; c<size; c++)
			{
				LifeCell temp = (LifeCell) gridCells.get(r).get(c);
				gridCells.get(r).set(c, temp.getNext());
			}
		NeighborFinder.getNeighbors(gridCells,currentshape, "standard", "standard");	

	}

	@Override
	public void getInputGlobal(List<Integer> s) {
		// TODO Auto-generated method stub
		
	}

}
