package cellsociety_team09;
import java.util.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;



public class SegregationCell extends CellModel 
{
	public static final int EMPTYCELL = 0;
	public static final int TYPE1 = 1;
	public static final int TYPE2 = 2;
	public static final int MOVING = 3;
	public static final Color[] colors = {Color.WHITE, Color.AZURE, Color.RED};

	private double t;
	private boolean ismoving=false;
	
	public SegregationCell(int cellstates, double moveprb)
	{
		t=moveprb;
		shape = new Rectangle(1,1);
		color = colors[cellstates];
		int[] states= {cellstates};
		state = new StateNode(color,states);
		neighbors = new SegregationCell[]{null};
	}
	
	public SegregationCell()
	{
		this(0,.5);
	}
	
	
	
	
	public void getInput(int[] states)
	{
	}
	
	
	
	
	public void getNeighbors(int r, int c,ArrayList<ArrayList<CellModel>> cellgrid )
	{
	
	SegregationCell[][] grid= convertGrid(cellgrid);
		
		
		int length=grid[0].length;
		int height=grid.length;
		if(c==0 && r==0){
			neighbors = new SegregationCell[] {null, null,grid[r][c+1], grid[r+1][c+1], grid[r+1][c],
					null, null, null, null};
		}
		else if(c==(length-1) && r==0){
			neighbors= new SegregationCell[] {null, null, null, null, grid[r+1][c], grid[r+1][c-1],
					grid[r][c-1], null};// 6 left
		}
		else if(r==(height-1) && c==0){
			neighbors= new SegregationCell[] {grid[r-1][c], grid[r-1][c+1], grid[r][c+1], null, null,
					null, null, null};	
		}
		else if(r==(height-1) && c==(length-1)){
			neighbors= new SegregationCell[] {grid[r-1][c], null, null, null, null, null, grid[r][c-1], 
					 grid[r-1][c-1]};
		}
		else if(r==0) { //top edge check
			neighbors = new SegregationCell[] {null, null,  grid[r][c+1],  grid[r+1][c+1],  grid[r+1][c], 
					grid[r+1][c-1], grid[r][c-1], null}; 		
		}
		else if(r==(height-1)) { // bottom edge check
			neighbors = new SegregationCell[] {null, null, grid[r][c+1], grid[r+1][c+1],grid[r+1][c], 
					grid[r+1][c-1], grid[r][c-1],null};		
		}
		else if(c==0){ //left edge check
			neighbors = new SegregationCell[] {grid[r-1][c], grid[r-1][c+1], grid[r][c+1],  grid[r+1][c+1], 
					grid[r+1][c], null, null, null};  
		}
		else if( c==(length-1)) { // right edge check
			neighbors = new SegregationCell[] { grid[r-1][c] ,null,null,null, grid[r+1][c],grid[r+1][c-1],  
					grid[r][c-1],  grid[r-1][c-1]};
		}
		else // checking for middle cell
		{
			neighbors = new SegregationCell[] {grid[r-1][c], //0 top 
										 grid[r-1][c+1], //1 top right
										 grid[r][c+1], //2 right
										 grid[r+1][c+1], // 3 bottom right
										 grid[r+1][c],  // 4 bottom 
										 grid[r+1][c-1], // 5 bottom left
										 grid[r][c-1], // 6 left
										 grid[r-1][c-1]}; // top left
		}
	}
	private void findNextState(StateNode n)
	{
		state.setNextState(n);
	}
	
	
	@Override	
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
			if(nPercent( mecount,notmecount)<t)
			{
				StateNode s = new StateNode(colors[0],new int[] {0});
				state.setNextState(s);
				ismoving=true;
			}
			else
			{
				StateNode s = new StateNode(colors[me],new int[] {me});
				state.setNextState(s);
			}
		}
		else
		{
			StateNode s = new StateNode(colors[me],new int[] {me});
			state.setNextState(s);
		}
	}
		
	
	
	public void moveForward(ArrayList<ArrayList<CellModel>> cellgrid)
	{
		SegregationCell[][] grid =convertGrid(cellgrid);
		boolean[][] moved = new boolean[grid.length][grid[0].length];
		
		if(ismoving)	
			Outerloop:
			for(int r=0; r<grid.length; r++)
				for(int c=0; c<grid[0].length; c++)
				{
					if(grid[r][c].getStates()[0]==0 && !moved[r][c])
					{
						StateNode node=new StateNode(colors[getStates()[0]],getStates());
						grid[r][c].findNextState(node);
						moved[r][c]=true;
						ismoving=false;
						break Outerloop;
					}
				}
	}
		
	public int[] getStates()
	{
		return state.getStates();
	}


	private SegregationCell[][] convertGrid(ArrayList<ArrayList<CellModel>> cellgrid)
	{
		SegregationCell[][] grid= new SegregationCell[cellgrid.size()][cellgrid.get(0).size()];
		for(int i=0; i<cellgrid.size(); i++)
			for(int k=0; k<cellgrid.get(0).size(); k++)
			{
				SegregationCell cell=(SegregationCell)cellgrid.get(k).get(i);
				grid[k][i]=cell;
			}
		return grid;
	}
	
	private double nPercent(int mecount, int notmecount)
	{
		return((double)(mecount))/(notmecount+mecount);
	}
}
