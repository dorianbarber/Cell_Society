package cellsociety_team09;

import java.util.ArrayList;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class LifeCell extends CellModel {
	
	
	public static final int DEADSTATE=0;
	public static final int ALIVESTATE=1;
	public static final Color[] colors = {Color.WHITE, Color.BLACK};
	
	public LifeCell(int cellstate)
	{
		shape = new Rectangle(1,1);
		color = Color.WHITE;
		int[] states= {cellstate};
		state = new StateNode(color,states);
		neighbors = new LifeCell[]{null};
	}
	
	public LifeCell()
	{
		this(0);
	}
	
	
	
	
	public int[] getStates()
	{
		return state.getStates();
	}
	
	public void findNextState()
	{
		StateNode s;
		int alivecount=0;
		for(int a=0; a<neighbors.length; a++)
			if(neighbors[a]!=null && neighbors[a].getStates()[0]==1)
				alivecount++;
		if(getStates()[0]==ALIVESTATE && (alivecount==2) || alivecount==3)
		{
			 s = new StateNode(colors[ALIVESTATE], new int[]{ALIVESTATE});
		}
		else
		{
			 s = new StateNode(colors[DEADSTATE], new int[]{DEADSTATE});
		}
		state.setNextState(s);
	}
	
	public void moveForward(ArrayList<ArrayList<CellModel>> grid) {
		state.moveForward();
	}
	
	public void getInput(ArrayList<Integer> states)
	{
		
	}

	public void getNeighbors( int r, int c, ArrayList<ArrayList<CellModel>> cellgrid)
	{
		ArrayList<ArrayList<LifeCell>> grid= new ArrayList<ArrayList<LifeCell>>();
		for(int i=0; i<cellgrid.get(0).size(); i++)
			for(int k=0; k<cellgrid.size(); k++)
			{
				LifeCell cell=(LifeCell)cellgrid.get(k).get(i);
				grid.get(k).set(i, cell);
			}
		
		int length=grid.get(0).size();
		int height=grid.size();
		if(c==0 && r==0){
			neighbors = new LifeCell[] {null, null,grid.get(r).get(c+1), grid.get(r+1).get(c+1), grid.get(r+1).get(c),
					null, null, null, null};
		}
		else if(c==(length-1) && r==0){
			neighbors= new LifeCell[] {null, null, null, null, grid.get(r+1).get(c), grid.get(r+1).get(c-1),
					grid.get(r).get(c-1), null};// 6 left
		}
		else if(r==(height-1) && c==0){
			neighbors= new LifeCell[] {grid.get(r-1).get(c), grid.get(r-1).get(c+1), grid.get(r).get(c+1), null, null,
					null, null, null};	
		}
		else if(r==(height-1) && c==(length-1)){
			neighbors= new LifeCell[] {grid.get(r-1).get(c), null, null, null, null, null, grid.get(r).get(c-1), 
					 grid.get(r-1).get(c-1)};
		}
		else if(r==0) { //top edge check
			neighbors = new LifeCell[] {null, null,  grid.get(r).get(c+1),  grid.get(r+1).get(c+1),  grid.get(r+1).get(c), 
					grid.get(r+1).get(c-1), grid.get(r).get(c-1), null}; 		
		}
		else if(r==(height-1)) { // bottom edge check
			neighbors = new LifeCell[] {null, null, grid.get(r).get(c+1), grid.get(r+1).get(c+1),grid.get(r+1).get(c), 
					grid.get(r+1).get(c-1), grid.get(r).get(c-1),null};		
		}
		else if(c==0){ //left edge check
			neighbors = new LifeCell[] {grid.get(r-1).get(c), grid.get(r-1).get(c+1), grid.get(r).get(c+1),  grid.get(r+1).get(c+1), 
					grid.get(r+1).get(c), null, null, null};  
		}
		else if( c==(length-1)) { // right edge check
			neighbors = new LifeCell[] { grid.get(r-1).get(c) ,null,null,null, grid.get(r+1).get(c),grid.get(r+1).get(c-1),  
					grid.get(r).get(c-1),  grid.get(r-1).get(c-1)};
		}
		else // checking for middle cell
		{
			neighbors = new LifeCell[] {grid.get(r-1).get(c), //0 top 
										 grid.get(r-1).get(c+1), //1 top right
										 grid.get(r).get(c+1), //2 right
										 grid.get(r+1).get(c+1), // 3 bottom right
										 grid.get(r+1).get(c),  // 4 bottom 
										 grid.get(r+1).get(c-1), // 5 bottom left
										 grid.get(r).get(c-1), // 6 left
										 grid.get(r-1).get(c-1)}; // top left
		}
	}

}
