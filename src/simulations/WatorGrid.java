package simulations;

import java.util.ArrayList;
import java.util.List;

import cellsociety_team09.GridModel;
import javafx.scene.shape.Rectangle;

public class WatorGrid extends GridModel
{
	private int starverate = 6;
	private int reporate1 = 3;
	private int reporate2 = 8;
	
	public WatorGrid(int gridSize)
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
				gridCells.get(a).add(new WatorCell());
			}
		}
		NeighborFinder.getNeighbors(gridCells, new Rectangle(), "cross", "toroidal");
	}
	public WatorGrid()
	{
		this(40);
	}
	
	public int getKind(){
		return 3;
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
		NeighborFinder.getNeighbors(gridCells, new Rectangle(), "cross", "toroidal");

	}
	
	@Override
	public void update()
	{
		for(int r=0; r<size; r++)
			for(int c=0; c<size; c++) {
				WatorCell temp = (WatorCell) gridCells.get(r).get(c);
				temp.getNextState(starverate, reporate1, reporate2);
			}
	}


	@Override
	public void getInputGlobal(List<Integer> s) {
		starverate=s.get(0);
		reporate1=s.get(1);
		reporate2=s.get(2);		
	}
	
	
}
	

	
	
