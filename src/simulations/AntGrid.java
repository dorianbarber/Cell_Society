package simulations;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.shape.Rectangle;

public class AntGrid extends GridModel{

	public AntGrid(int gridSize)
	{
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
			as.add(new Ant(5,8));
		
		gridCells.get(5).set(8, new AntsCell(0,as,0,0,5,8));
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
		System.out.print("yo");
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
