package simulations;

import java.util.ArrayList;
import java.util.List;

import cellsociety_team09.StateNode;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class FireCell extends CellModel {
	
	
	public static final int EMPTYCELL=0;
	public static final int BURNINGCELL=1;
	public static final int TREECELL=2;
	public static final Color[] colors = {Color.GREY, Color.RED, Color.GREEN};
	private int burnprb;
	
	public FireCell(int cellstate, int d)
	{
		burnprb=d;
		//shape = new Rectangle(1,1);
		color=colors[cellstate];
		int[] states= {cellstate,burnprb};
		state = new StateNode(color,states);
		neighbors = new FireCell[]{null};
	}
	
	public FireCell()
	{
		this(2,70);
	}
	
	

	@Override 
	public void getInput(List<Integer> states)
	{
		burnprb=states.get(1);
		int[] s = new int[]{states.get(0),burnprb};
		state.setState(colors[states.get(0)], s);
	}
	
	public void setNextState(StateNode b)
	{
		state.setNextState(b);
	}
	
	
	public int[] getStates()
	{
		return state.getStates();
	}
	
	public void findNextState()
	{
		int percentbrn=0;
		boolean burning=false;
		StateNode s;
		for(int a=0; a<neighbors.length; a++)
			if(neighbors[a]!=null && neighbors[a].getStates()[0]==BURNINGCELL)
			{
				percentbrn =neighbors[a].getStates()[1];
				double prb=((double)percentbrn)/100;
				burning=(Math.random()<prb);
			}
			
		if(getStates()[0]==TREECELL && burning)
			s = new StateNode(colors[BURNINGCELL], new int[] {BURNINGCELL,percentbrn});
		else if(getStates()[0]==BURNINGCELL)
			s = new StateNode(colors[EMPTYCELL], new int[] {EMPTYCELL, 0});
		else
			s = new StateNode(colors[getStates()[0]], getStates());
		state.setNextState(s);
	}
		
		
		
	
	public void moveForward(ArrayList<ArrayList<CellModel>> grid) {
		state.moveForward();
	}

	

	public void getNeighbors( int r, int c, ArrayList<ArrayList<CellModel>> grid)
	{
		
		int length=grid.get(0).size();
		int height=grid.get(0).size();
		if(c==0 && r==0){
			neighbors = new FireCell[] {null, null,(FireCell)grid.get(r).get(c+1), null, (FireCell)grid.get(r+1).get(c),
					null, null, null, null};
		}
		else if(c==(length-1) && r==0){
			neighbors= new FireCell[] {null, null, null, null, (FireCell)grid.get(r+1).get(c), null,
					(FireCell)grid.get(r).get(c-1), null};// 6 left
		}
		else if(r==(height-1) && c==0){
			neighbors= new FireCell[] {(FireCell)grid.get(r-1).get(c), null,(FireCell) grid.get(r).get(c+1), null, null,
					null, null, null};	
		}
		else if(r==(height-1) && c==(length-1)){
			neighbors= new FireCell[] {(FireCell)grid.get(r-1).get(c), null, null, null, null, null, (FireCell)grid.get(r).get(c-1), 
					null};
		}
		else if(r==0) { //top edge check
			neighbors = new FireCell[] {null, null, (FireCell) grid.get(r).get(c+1),  null,  (FireCell)grid.get(r+1).get(c), 
					null, (FireCell)grid.get(r).get(c-1), null}; 		
		}
		else if(r==(height-1)) { // bottom edge check
			neighbors = new FireCell[] {(FireCell)grid.get(r-1).get(c), null,(FireCell) grid.get(r).get(c+1),null,null, 
					null,(FireCell) grid.get(r).get(c-1) ,null};			
		}
		else if(c==0){ //left edge check
			neighbors = new FireCell[] {(FireCell)grid.get(r-1).get(c), null,(FireCell) grid.get(r).get(c+1),  null, 
					(FireCell)grid.get(r+1).get(c), null, null, null};  
		}
		else if( c==(length-1)) { // right edge check
			neighbors = new FireCell[] { (FireCell)grid.get(r-1).get(c) ,null,null,null,(FireCell) grid.get(r+1).get(c),null,  
					(FireCell)	grid.get(r).get(c-1), null};
		}
		else // checking for middle cell
		{
			neighbors = new FireCell[] {(FireCell)grid.get(r-1).get(c), //0 top 
										 null, //1 top right
										 (FireCell) grid.get(r).get(c+1), //2 right
										 null, // 3 bottom right
										 (FireCell) grid.get(r+1).get(c),  // 4 bottom 
										 null, // 5 bottom left
										 (FireCell) grid.get(r).get(c-1), // 6 left
										null}; // top left
		}
	}

	


}
