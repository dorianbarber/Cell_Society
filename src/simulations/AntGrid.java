package simulations;

import java.util.ArrayList;
import java.util.List;

import cellsociety_team09.GridModel;
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
		NeighborFinder.getNeighbors(gridCells, new Rectangle(), "standard", "standard");		
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
		for(int r=0; r<size; r++)
			for(int c=0; c<size; c++) {
				AntsCell temp = (AntsCell) gridCells.get(r).get(c);
				temp.getNextState();
			}
	}

	@Override
	public void moveForward() {
		for(int r=0; r<size; r++)
			for(int c=0; c<size; c++) {
				AntsCell temp = (AntsCell) gridCells.get(r).get(c);
				temp=temp.getNext();
			}		
	}

	@Override
	public void getInputGlobal(List<Integer> s) {
		// TODO Auto-generated method stub
		
	}

}
