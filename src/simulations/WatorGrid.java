package simulations;

import java.util.ArrayList;
import java.util.List;
import javafx.scene.shape.Rectangle;

public class WatorGrid extends GridModel
{
	private int starverate = 5;
	private int reporate1 = 3;
	private int reporate2 = 10;
	
	public WatorGrid(int gridSize)
	{
		cellType = new WatorCell();
		gridCells = new ArrayList<List<CellModel>>();
		size = gridSize;
		for(int a=0; a < size; a++)
		{
			gridCells.add(new ArrayList<CellModel>());
			for(int b=0; b<size; b++)
			{
//				WatorCell r = (WatorCell) gridCells.get(a).get(b);
//				gridCells.get(a).set(b,r);
				gridCells.get(a).add(new WatorCell());
			}
		}
		NeighborFinder.getNeighbors(gridCells, new Rectangle(), "cross", "standard");
	}
	public WatorGrid()
	{

		this(40);

	}
	
	public int getKind(){
		return 3;
	}
	
	@Override
	public void update()
	{
		for(int r=0; r<size; r++)
			for(int c=0; c<size; c++) {
				WatorCell temp = (WatorCell) gridCells.get(r).get(c);
				temp.getNextState(starverate, reporate1, reporate2);
			}
		NeighborFinder.getNeighbors(gridCells, new Rectangle(), "cross", "standard");

	}
	
	@Override
	public void moveForward()
	{
		for(int r=0; r<size; r++)
			for(int c=0; c<size; c++)
			{
				WatorCell temp = (WatorCell) gridCells.get(r).get(c);
				gridCells.get(r).set(c, temp.getNext());
			}
		NeighborFinder.getNeighbors(gridCells, new Rectangle(), "cross", "standard");

	}

	@Override
	public void getInputGlobal(List<Integer> s) {
		if(s.get(0)!=-1)
			reporate1=s.get(0);
		if(s.get(1)!=-1)
			reporate2=s.get(1);		
		if(s.get(2)!=-1)
			starverate=s.get(2);

	}
	@Override
	public void setSize(int t) {
		// TODO Auto-generated method stub
		
	}
	
	public void setSize(int t)
	{
	ArrayList<List<CellModel>>tempCells = new ArrayList<List<CellModel>>();
		
		for(int a=0; a < t; a++)
		{
			tempCells.add(new ArrayList<CellModel>());
			for(int b=0; b<t; b++)
			{
				tempCells.get(a).add(new WatorCell());
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
	

	
	
