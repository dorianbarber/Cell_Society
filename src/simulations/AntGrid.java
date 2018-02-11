package simulations;

import java.util.List;

import javafx.scene.shape.Rectangle;

public class AntGrid extends GridModel{

	public AntGrid(int gridSize)
	{
		size = gridSize;
		for(int a=0; a < size; a++)
		{
			for(int b=0; b<size; b++)
			{
				AntsCell r = (AntsCell) gridCells.get(a).get(b);
				r.setRC(a,b);
				gridCells.get(a).set(b,r);
			
			}
		}
		NeighborFinder.getNeighbors(gridCells, new Rectangle(), "standard", "standard");
		
		
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
