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
	public static final int MOVING1 = 3;
	public static final int MOVING2 = 4;
	public static final Color[] colors = {Color.WHITE, Color.BLUE, Color.RED, Color.WHITE, Color.WHITE};

	private double t;
	private boolean ismoving=false;
	private boolean moved=false;

	
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
		this((int)((Math.random()*2)+1),.75);
		
	}
	
	@Override	
	public void findNextState()
	{
		moved=false;
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
				StateNode s = new StateNode(colors[me+2],new int[] {me+2});
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
			StateNode s = new StateNode(colors[EMPTYCELL],new int[] {EMPTYCELL});
			state.setNextState(s);
		}
	}
		
	
	@Override
	public void moveForward(ArrayList<ArrayList<CellModel>> grid)
	{
		
		
			while(ismoving)
			{
					int r = ((int)(Math.random()*grid.get(0).size()));
					int c = ((int)(Math.random()*grid.get(0).size()));
					int cellstate=grid.get(r).get(c).getStates()[0];
					//System.out.println(r + " " + c + " "+ grid.get(r).get(c).getStates()[0]);
					
					if(cellstate==EMPTYCELL || cellstate==MOVING1 || cellstate==MOVING2)
					{
						SegregationCell temp = (SegregationCell)grid.get(r).get(c);
						StateNode n = new StateNode(colors[getStates()[0]],getStates());
						if(cellstate!=0)
						{
							temp.setState(colors[temp.getStates()[0]-2],new int[] {temp.getStates()[0]-2}); //for stepping backwards 

						}
						temp.findNextState(n);
						temp.state.moveForward();
						temp.moved=true;
						ismoving=false;
					}
			}
			if(!moved)
				state.moveForward();
	}

	@Override	
	public int[] getStates()
	{
		return state.getStates();
	}

	private double nPercent(int mecount, int notmecount)
	{
		return((double)(mecount))/(notmecount+mecount);
	}

	@Override
	public void getInput(List<Integer> states) {
			state.setState(colors[0], new int[] {0});
	}
	
	
	private void findNextState(StateNode n)
	{
		state.setNextState(n);
	}
	
	private void setState(Color n, int[] s)
	{
		state.setState(n, s);
	}
	
	@Override
	protected void setNextState(StateNode a) {
		state.setNextState(a);// TODO Auto-generated method stub
		
	}
	@Override
	public void getNeighbors( int r, int c, ArrayList<ArrayList<CellModel>> grid)
	{
		
		
		int length=grid.get(0).size();
		int height=grid.get(0).size();
		if(c==0 && r==0){
			neighbors = new SegregationCell[] {null, null,(SegregationCell)grid.get(r).get(c+1),(SegregationCell) grid.get(r+1).get(c+1), (SegregationCell)grid.get(r+1).get(c),
					null, null, null, null};
		}
		else if(c==(length-1) && r==0){
			neighbors= new SegregationCell[] {null, null, null, null, (SegregationCell)grid.get(r+1).get(c), (SegregationCell)grid.get(r+1).get(c-1),
					(SegregationCell)grid.get(r).get(c-1), null};// 6 left
		}
		else if(r==(height-1) && c==0){
			neighbors= new SegregationCell[] {(SegregationCell)grid.get(r-1).get(c), (SegregationCell)grid.get(r-1).get(c+1),(SegregationCell) grid.get(r).get(c+1), null, null,
					null, null, null};	
		}
		else if(r==(height-1) && c==(length-1)){
			neighbors= new SegregationCell[] {(SegregationCell)grid.get(r-1).get(c), null, null, null, null, null, (SegregationCell)grid.get(r).get(c-1), 
					(SegregationCell) grid.get(r-1).get(c-1)};
		}
		else if(r==0) { //top edge check
			neighbors = new SegregationCell[] {null, null, (SegregationCell) grid.get(r).get(c+1),  (SegregationCell)grid.get(r+1).get(c+1), (SegregationCell) grid.get(r+1).get(c), 
					(SegregationCell)grid.get(r+1).get(c-1), (SegregationCell)grid.get(r).get(c-1), null}; 		
		}
		else if(r==(height-1)) { // bottom edge check
			neighbors = new SegregationCell[] {(SegregationCell)grid.get(r-1).get(c), (SegregationCell)grid.get(r-1).get(c+1), (SegregationCell)grid.get(r).get(c+1),null,null, 
					null, (SegregationCell)grid.get(r).get(c-1) ,(SegregationCell) grid.get(r-1).get(c-1)};		
		}
		else if(c==0){ //left edge check
			neighbors = new SegregationCell[] {(SegregationCell)grid.get(r-1).get(c), (SegregationCell)grid.get(r-1).get(c+1),(SegregationCell) grid.get(r).get(c+1),
					(SegregationCell)  grid.get(r+1).get(c+1), (SegregationCell)grid.get(r+1).get(c), null, null, null};  
		}
		else if( c==(length-1)) { // right edge check
			neighbors = new SegregationCell[] { (SegregationCell)grid.get(r-1).get(c) ,null,null,null,(SegregationCell) grid.get(r+1).get(c),(SegregationCell)grid.get(r+1).get(c-1),  
					(SegregationCell)grid.get(r).get(c-1),  (SegregationCell)grid.get(r-1).get(c-1)};
		}
		else // checking for middle cell
		{
			neighbors = new SegregationCell[] {(SegregationCell)grid.get(r-1).get(c), //0 top 
					(SegregationCell) grid.get(r-1).get(c+1), //1 top right
					(SegregationCell) grid.get(r).get(c+1), //2 right
					(SegregationCell) grid.get(r+1).get(c+1), // 3 bottom right
		  		    (SegregationCell)grid.get(r+1).get(c),  // 4 bottom 
				    (SegregationCell) grid.get(r+1).get(c-1), // 5 bottom left
				    (SegregationCell) grid.get(r).get(c-1), // 6 left
					(SegregationCell) grid.get(r-1).get(c-1)}; // top left
		}
	}

}
