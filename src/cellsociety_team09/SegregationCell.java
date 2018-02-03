package cellsociety_team09;
import java.util.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;



public class SegregationCell extends CellModel 
{
	public static final int EMPTYCELL=0;
	public static final int TYPE1=1;
	public static final int TYPE2=2;
	public static final int MOVING=3;

	private double t;
	private int ncount;
	
	public SegregationCell()
	{
		t=.5;
		shape = new Rectangle(1,1);
		color = Color.BLACK;
		int[] states= {1};
		state = new StateNode(color,states);
		neighbors = new SegregationCell[]{null};
	}
	
	
	
	
	
	public void getNeighbors(ArrayList<ArrayList<SegregationCell>> grid, int r, int c)
	{
		int length=grid.get(0).size();
		int height=grid.size();
		if(c==0 && r==0){
			neighbors = new SegregationCell[] {null, null,grid.get(r).get(c+1), grid.get(r+1).get(c+1), grid.get(r+1).get(c),
					null, null, null, null};
		}
		else if(c==(length-1) && r==0){
			neighbors= new SegregationCell[] {null, null, null, null, grid.get(r+1).get(c), grid.get(r+1).get(c-1),
					grid.get(r).get(c-1), null};// 6 left
		}
		else if(r==(height-1) && c==0){
			neighbors= new SegregationCell[] {grid.get(r-1).get(c), grid.get(r-1).get(c+1), grid.get(r).get(c+1), null, null,
					null, null, null};	
		}
		else if(r==(height-1) && c==(length-1)){
			neighbors= new SegregationCell[] {grid.get(r-1).get(c), null, null, null, null, null, grid.get(r).get(c-1), 
					 grid.get(r-1).get(c-1)};
		}
		else if(r==0) { //top edge check
			neighbors = new SegregationCell[] {null, null,  grid.get(r).get(c+1),  grid.get(r+1).get(c+1),  grid.get(r+1).get(c), 
					grid.get(r+1).get(c-1), grid.get(r).get(c-1), null}; 		
		}
		else if(r==(height-1)) { // bottom edge check
			neighbors = new SegregationCell[] {null, null, grid.get(r).get(c+1), grid.get(r+1).get(c+1),grid.get(r+1).get(c), 
					grid.get(r+1).get(c-1), grid.get(r).get(c-1),null};		
		}
		else if(c==0){ //left edge check
			neighbors = new SegregationCell[] {grid.get(r-1).get(c), grid.get(r-1).get(c+1), grid.get(r).get(c+1),  grid.get(r+1).get(c+1), 
					grid.get(r+1).get(c), null, null, null};  
		}
		else if( c==(length-1)) { // right edge check
			neighbors = new SegregationCell[] { grid.get(r-1).get(c) ,null,null,null, grid.get(r+1).get(c),grid.get(r+1).get(c-1),  
					grid.get(r).get(c-1),  grid.get(r-1).get(c-1)};
		}
		else // checking for middle cell
		{
			neighbors = new SegregationCell[] {grid.get(r-1).get(c), //0 top 
										 grid.get(r-1).get(c+1), //1 top right
										 grid.get(r).get(c+1), //2 right
										 grid.get(r+1).get(c+1), // 3 bottom right
										 grid.get(r+1).get(c),  // 4 bottom 
										 grid.get(r+1).get(c-1), // 5 bottom left
										 grid.get(r).get(c-1), // 6 left
										 grid.get(r-1).get(c-1)}; // top left
		}
	}
		
	public void findNextState()
	{
		int me = state.getStates()[0];
		if(me==1 || me==2)
		{
			int notme=3-me;
			int temp;
			int mecount=0; 
			int notmecount=0;
			for(int a=0; a< neighbors.length; a++)
			{
				if(neighbors[a]!=null)
				{
					temp=neighbors[a].getStates()[0];
					if(me==temp)
						mecount++;
					if(temp==notme)
						notmecount++;
				}
			}
			if(((double)(mecount))/(notmecount+mecount)<t)
			{
				StateNode s = new StateNode(Color.WHITE,new int[] {4});
				state.setNextState(s);
			}
		}
	}
		
	public void moveForward(ArrayList<ArrayList<Cell>> grid)
	{
		
		
	}
	public int[] getStates()
	{
		return state.getStates();
	}
	
	
}


	
	



