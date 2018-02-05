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
		color = colors[cellstate];
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
		
		int alivecount=0;
		for(int a=0; a<neighbors.length; a++)
			if(neighbors[a]!=null && neighbors[a].getStates()[0]==1)
				alivecount++;
		if(getStates()[0]==ALIVESTATE && (alivecount==2) || alivecount==3)
		{
			StateNode s = new StateNode(colors[ALIVESTATE],new int[]{ALIVESTATE});
			 state.setNextState(s);
		}
		else
		{
			StateNode s = new StateNode(colors[DEADSTATE],new int[]{DEADSTATE});
			 state.setNextState(s);
		}
		
	}
	
	public void moveForward(ArrayList<ArrayList<CellModel>> grid) {
		state.moveForward();
	}
	
	public void getInput(ArrayList<Integer> states)
	{
		
	}


	public void getNeighbors( int r, int c, ArrayList<ArrayList<CellModel>> grid)
	{
		(LifeCell)
		int length=grid.get(0).size();
		int height=grid.get(0).size();
		if(c==0 && r==0){
			neighbors = new LifeCell[] {null, null,(LifeCell)grid.get(r).get(c+1), (LifeCell)grid.get(r+1).get(c+1), (LifeCell)grid.get(r+1).get(c),
					null, null, null, null};
		}
		else if(c==(length-1) && r==0){
			neighbors= new LifeCell[] {null, null, null, null, (LifeCell)grid.get(r+1).get(c), (LifeCell)grid.get(r+1).get(c-1),
					(LifeCell)grid.get(r).get(c-1), null};// 6 left
		}
		else if(r==(height-1) && c==0){
			neighbors= new LifeCell[] {(LifeCell)grid.get(r-1).get(c), (LifeCell)grid.get(r-1).get(c+1), (LifeCell)grid.get(r).get(c+1), null, null,
					null, null, null};	
		}
		else if(r==(height-1) && c==(length-1)){
			neighbors= new LifeCell[] {(LifeCell)grid.get(r-1).get(c), null, null, null, null, null, (LifeCell)grid.get(r).get(c-1), 
					(LifeCell)grid.get(r-1).get(c-1)};
		}
		else if(r==0) { //top edge check
			neighbors = new LifeCell[] {null, null,  (LifeCell)grid.get(r).get(c+1),  (LifeCell)grid.get(r+1).get(c+1), (LifeCell) grid.get(r+1).get(c), 
					(LifeCell)grid.get(r+1).get(c-1),(LifeCell) grid.get(r).get(c-1), null}; 		
		}
		else if(r==(height-1)) { // bottom edge check
			neighbors = new LifeCell[] {(LifeCell)grid.get(r-1).get(c), (LifeCell)grid.get(r-1).get(c+1),(LifeCell) grid.get(r).get(c+1),null,null, 
					null, (LifeCell)grid.get(r).get(c-1) , (LifeCell)grid.get(r-1).get(c-1)};		
		}
		else if(c==0){ //left edge check
			neighbors = new LifeCell[] {(LifeCell)grid.get(r-1).get(c), (LifeCell)grid.get(r-1).get(c+1),(LifeCell) grid.get(r).get(c+1),  (LifeCell)grid.get(r+1).get(c+1), 
					(LifeCell)grid.get(r+1).get(c), null, null, null};  
		}
		else if( c==(length-1)) { // right edge check
			neighbors = new LifeCell[] { (LifeCell)grid.get(r-1).get(c) ,null,null,null,(LifeCell) grid.get(r+1).get(c),(LifeCell)grid.get(r+1).get(c-1),  
					(LifeCell)grid.get(r).get(c-1), (LifeCell) grid.get(r-1).get(c-1)};
		}
		else // checking for middle cell
		{
			neighbors = new LifeCell[] { (LifeCell)grid.get(r-1).get(c), //0 top 
					(LifeCell) grid.get(r-1).get(c+1), //1 top right
					(LifeCell) grid.get(r).get(c+1), //2 right
					(LifeCell) grid.get(r+1).get(c+1), // 3 bottom right
					(LifeCell) grid.get(r+1).get(c),  // 4 bottom 
					(LifeCell) grid.get(r+1).get(c-1), // 5 bottom left
					(LifeCell) grid.get(r).get(c-1), // 6 left
					(LifeCell) grid.get(r-1).get(c-1)}; // top left
		}
	}

}
