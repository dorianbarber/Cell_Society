package cellsociety_team09;

import java.util.ArrayList;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class FireCell extends CellModel {
	
	
	public static final int EMPTYCELL=0;
	public static final int BURNINGCELL=1;
	public static final int TREECELL=2;
	public static final Color[] colors = {Color.YELLOW, Color.RED, Color.GREEN};
	private double burnprb;
	
	public FireCell(int cellstate, double bprb)
	{
		burnprb=bprb;
		shape = new Rectangle(1,1);
		color=colors[cellstate];
		int[] states= {cellstate};
		state = new StateNode(color,states);
		neighbors = new FireCell[]{null};
	}
	
	public FireCell()
	{
		this(0,.5);
	}
	
	
	
	public void getInput(ArrayList<Integer> states)
	{
		burnprb=states.get(1);
		int[] s = new int[]{states.get(0)};
		state.setState(color,s);
	}
	
	
	public int[] getStates()
	{
		return state.getStates();
	}
	
	public void findNextState()
	{
		boolean burning=false;
		StateNode s;
		for(int a=0; a<neighbors.length; a++)
			if(neighbors[a]!=null && neighbors[a].getStates()[0]==BURNINGCELL)
				burning=(Math.random()<burnprb);
			
		if(getStates()[0]==TREECELL && burning)
			s = new StateNode(colors[BURNINGCELL], new int[] {BURNINGCELL});
		else if(getStates()[0]==BURNINGCELL)
			s = new StateNode(colors[EMPTYCELL], new int[] {EMPTYCELL});
		else
			s = new StateNode(colors[getStates()[0]], getStates());
		state.setNextState(s);
	}
		
		
		
	
	public void moveForward(ArrayList<ArrayList<CellModel>> grid) {
		state.moveForward();
	}

	

	public void getNeighbors( int r, int c, ArrayList<ArrayList<CellModel>> cellgrid)
	{
		ArrayList<ArrayList<FireCell>> grid= new ArrayList<ArrayList<FireCell>>();
		for(int i=0; i<cellgrid.get(0).size(); i++)
			for(int k=0; k<cellgrid.size(); k++)
			{
				FireCell cell=(FireCell)cellgrid.get(k).get(i);
				grid.get(k).set(i, cell);
			}
		
		int length=grid.get(0).size();
		int height=grid.size();
		if(c==0 && r==0){
			neighbors = new FireCell[] {null, null,grid.get(r).get(c+1), null, grid.get(r+1).get(c),
					null, null, null, null};
		}
		else if(c==(length-1) && r==0){
			neighbors= new FireCell[] {null, null, null, null, grid.get(r+1).get(c), null,
					grid.get(r).get(c-1), null};// 6 left
		}
		else if(r==(height-1) && c==0){
			neighbors= new FireCell[] {grid.get(r-1).get(c), null, grid.get(r).get(c+1), null, null,
					null, null, null};	
		}
		else if(r==(height-1) && c==(length-1)){
			neighbors= new FireCell[] {grid.get(r-1).get(c), null, null, null, null, null, grid.get(r).get(c-1), 
					null};
		}
		else if(r==0) { //top edge check
			neighbors = new FireCell[] {null, null,  grid.get(r).get(c+1),  null,  grid.get(r+1).get(c), 
					null, grid.get(r).get(c-1), null}; 		
		}
		else if(r==(height-1)) { // bottom edge check
			neighbors = new FireCell[] {null, null, grid.get(r).get(c+1), null,grid.get(r+1).get(c), 
					null, grid.get(r).get(c-1),null};		
		}
		else if(c==0){ //left edge check
			neighbors = new FireCell[] {grid.get(r-1).get(c), null, grid.get(r).get(c+1),  null, 
					grid.get(r+1).get(c), null, null, null};  
		}
		else if( c==(length-1)) { // right edge check
			neighbors = new FireCell[] { grid.get(r-1).get(c) ,null,null,null, grid.get(r+1).get(c),null,  
					grid.get(r).get(c-1), null};
		}
		else // checking for middle cell
		{
			neighbors = new FireCell[] {grid.get(r-1).get(c), //0 top 
										 null, //1 top right
										 grid.get(r).get(c+1), //2 right
										 null, // 3 bottom right
										 grid.get(r+1).get(c),  // 4 bottom 
										 null, // 5 bottom left
										 grid.get(r).get(c-1), // 6 left
										null}; // top left
		}
	}


}
