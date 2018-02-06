package simulations;

import java.util.ArrayList;
import java.util.Collections;

import cellsociety_team09.StateNode;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class WatorCell extends CellModel {
	
	private static final Color[] colors= {Color.BLUE,Color.GREEN, Color.RED,Color.BLUE,Color.BLUE};
	private static final int EMPTYCELL=0;
	private static final int FISHCELL=1;
	private static final int SHARKCELL=2;
	private static final int FISHLEAVE=3;
	private static final int SHARKLEAVE=4;
	private int starverate;
	private int reporate;
	private int type;
	private boolean inheat =false;
	private boolean moved=false;
	private boolean isalive=true;
	
	//state 0 is type is repo type 3 is starve type
	public WatorCell(int[] states)
	{
		shape = new Rectangle(1,1);
		starverate=states[2];
		reporate=states[1];
		type=states[0];
		StateNode n = new StateNode(colors[type],new int[] {type,0,0});
		state=n;
		neighbors = new WatorCell[] {null};
	}
	
	public void getNeighbors( int r, int c, ArrayList<ArrayList<CellModel>> grid)
	{
		
		
		int length=grid.get(0).size();
		int height=grid.get(0).size();
		if(c==0 && r==0){
			neighbors = new WatorCell[] {(WatorCell)grid.get(height-1).get(c), null,(WatorCell)grid.get(r).get(c+1), null, (WatorCell)grid.get(r+1).get(c),
					null, (WatorCell) grid.get(r).get(length-1), null};
		}
		else if(c==(length-1) && r==0){
			neighbors= new WatorCell[] {(WatorCell) grid.get(height-1).get(c), null, (WatorCell) grid.get(r).get(0), null, (WatorCell)grid.get(r+1).get(c), null,
					(WatorCell)grid.get(r).get(c-1), null};// 6 left
		}
		else if(r==(height-1) && c==0){
			neighbors= new WatorCell[] {(WatorCell)grid.get(r-1).get(c), null,(WatorCell) grid.get(r).get(c+1), null, (WatorCell) grid.get(0).get(c),
					null, (WatorCell) grid.get(r).get(length-1), null};	
		}
		else if(r==(height-1) && c==(length-1)){
			neighbors= new WatorCell[] {(WatorCell)grid.get(r-1).get(c), null, (WatorCell) grid.get(r).get(0), null, (WatorCell) grid.get(0).get(length-1), null, (WatorCell)grid.get(r).get(c-1), 
					null};
		}
		else if(r==0) { //top edge check
			neighbors = new WatorCell[] {(WatorCell) grid.get(height-1).get(c), null, (WatorCell) grid.get(r).get(c+1),  null,  (WatorCell)grid.get(r+1).get(c), 
					null, (WatorCell)grid.get(r).get(c-1), null}; 		
		}
		else if(r==(height-1)) { // bottom edge check
			neighbors = new WatorCell[] {(WatorCell)grid.get(r-1).get(c), null,(WatorCell) grid.get(r).get(c+1),null,(WatorCell) grid.get(0).get(c), 
					null,(WatorCell) grid.get(r).get(c-1) ,null};			
		}
		else if(c==0){ //left edge check
			neighbors = new WatorCell[] {(WatorCell)grid.get(r-1).get(c), null,(WatorCell) grid.get(r).get(c+1),  null, 
					(WatorCell)grid.get(r+1).get(c), null, (WatorCell) grid.get(r).get(length-1), null};  
		}
		else if( c==(length-1)) { // right edge check
			neighbors = new WatorCell[] { (WatorCell)grid.get(r-1).get(c) ,null,(WatorCell) grid.get(r).get(0),null,(WatorCell) grid.get(r+1).get(c),null,  
					(WatorCell)	grid.get(r).get(c-1), null};
		}
		else // checking for middle cell
		{
			neighbors = new WatorCell[] {(WatorCell)grid.get(r-1).get(c), //0 top 
										 null, //1 top right
										 (WatorCell) grid.get(r).get(c+1), //2 right
										 null, // 3 bottom right
										 (WatorCell) grid.get(r+1).get(c),  // 4 bottom 
										 null, // 5 bottom left
										 (WatorCell) grid.get(r).get(c-1), // 6 left
										null}; // top left
		}
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
	public void findNextState()
	{
		boolean die=false;
		int next=-1;
		StateNode thisnode=null;
		StateNode movenode=null;
		int[] states = getStates();
		int[] nebs={0,1,2,3};
		
		if(states[0]==SHARKCELL)
		{
			nebs=shuffle(nebs);
			for(int a=0; a<nebs.length; a++)
			{
				WatorCell c = (WatorCell)neighbors[nebs[a]];
				if(c.getStates()[0]==FISHCELL && c.isAlive())
				{
					
				}
			}
		}
		
		
		
		
		
		
		public boolean isAlive()
		{
			return isalive;
		}
		
		
		if(states[0]==FISHCELL)
		{
			nebs=shuffle(nebs);
			if(states[1]==reporate)
			{
				inheat=true;
			}
			for(int a=0; a<neighbors.length; a++)
			{
				if(neighbors[nebs[a]].getStates()[0]==SHARKCELL)
					die=true;
				if(neighbors[nebs[a]].getStates()[0]==EMPTYCELL)
					next=nebs[a];
			}
			if(!die && next!=-1)
			{
				thisnode = new StateNode(colors[FISHLEAVE], new int[] {FISHLEAVE,0,0});
				movenode = new StateNode(colors[FISHCELL],new int[] {FISHCELL,getStates()[1]+1,0});
				moved=true;
				neighbors[next].setNextState(movenode);
			}
			else if(die)
			{
				 thisnode = new StateNode(colors[EMPTYCELL], new int[] {EMPTYCELL,0,0});
			}
			else if(next==-1)
			{
				 thisnode = new StateNode(colors[FISHCELL], new int[] {FISHCELL,getStates()[1]+1,0});
			}
			state.setNextState(thisnode);

			
				
		}
		
	}
	
	public static int[] shuffle(int[] ar)
	{
		int a=0;
		while(a<10)
		{
			int b=(int)(Math.random()*ar.length);
			int c=(int)(Math.random()*ar.length);
			int temp=ar[b];
			ar[b]=ar[c];
			ar[c]=temp;
			a++;
		}
		return ar;
	}
	
	
	public void moveForward(ArrayList<ArrayList<CellModel>> grid)
	{
		
//		
//			while(ismoving)
//			{
//					int r = ((int)(Math.random()*grid.get(0).size()));
//					int c = ((int)(Math.random()*grid.get(0).size()));
//					int cellstate=grid.get(r).get(c).getStates()[0];
//					//System.out.println(r + " " + c + " "+ grid.get(r).get(c).getStates()[0]);
//					
//					if(cellstate==EMPTYCELL || cellstate==MOVING1 || cellstate==MOVING2)
//					{
//						SegregationCell temp = (SegregationCell)grid.get(r).get(c);
//						StateNode n = new StateNode(colors[getStates()[0]],getStates());
//						if(cellstate!=0)
//						{
//							temp.setState(colors[temp.getStates()[0]-2],new int[] {temp.getStates()[0]-2}); //for stepping backwards 
//
//						}
//						temp.findNextState(n);
//						temp.state.moveForward();
//						temp.moved=true;
//						ismoving=false;
//					}
//			}
//			if(!moved)
//				state.moveForward();
	}

		
	public int[] getStates()
	{
		return state.getStates();
	}


	
	
	private double nPercent(int mecount, int notmecount)
	{
		return((double)(mecount))/(notmecount+mecount);
	}

	@Override
	public void getInput(ArrayList<Integer> states) {
		// TODO Auto-generated method stub
		
	}
	

}
