package simulations;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.paint.Color;


public class LangstonCell extends CellModel {

	private static final int BLACKCELL = 0;
	private static final int BLUECELL = 1;
	private static final int REDCELL = 2;
	private static final int GREENCELL = 3;
	private static final int YELLOWCELL = 4;
	private static final int PINKCELL = 5;
	private static final int WHITECELL = 6;
	private static final int CYANCELL = 7;
	private int[] counts = {0,0,0,0,0,0,0,0};
	private boolean updated;
	private int state=0;
	private int direction;
	private List<LangstonCell> neighbors = new ArrayList<LangstonCell>();
	private boolean diverged=false;
	private Color[] colors= {Color.BLACK, Color.BLUE, Color.RED, Color.GREEN, Color.YELLOW,
			Color.PINK, Color.WHITE, Color.CYAN};
	private LangstonCell next;

	public LangstonCell(int t, int d) {
		state=t;
		color=colors[t];
		direction =d;
	}
	public LangstonCell(int t, int d, List<LangstonCell> s) {
		this(t,d);
		neighbors=s;
	}
	
	public LangstonCell getNext()
	{
		if(next==null)
			return this;
		return next;
		
	}
	
	public void setState(int t, int d)
	{
		state=t;
		color=colors[t];
		direction=d;
		
	}
	
	public void setNextState(int t, int d)
	{
		next = new LangstonCell(t, d, neighbors);
	}
	
	public int getState()
	{
		return state;
	}
	
	public boolean isUpdated()
	{
		return updated;
	}
	
	public int getDirection()
	{
		return direction;
	}
	
	@Override
	public void getClicked()
	{
		
	}
	
	public void getNextState()
	{
		if(state==CYANCELL)
			System.out.println(state);
		for(int a=0; a<neighbors.size(); a++)
		{
			counts[neighbors.get(a).getState()]++;
		}
		if(state==CYANCELL)
		for(int a=0; a<counts.length; a++)
			System.out.println(a+" "+counts[a]);
		if(state==CYANCELL)
		{
			if(counts[BLUECELL]==2) {}

//				if(!diverged) {
//					neighbors.get(directionCalc("counterclockwise")).setNextState(CYANCELL, setDirection("counterclockwise"));
//					neighbors.get(directionCalc("straight")).setNextState(CYANCELL,setDirection("straight"));
//					diverged=true;
//					setNextState(BLUECELL,directionCalc("straight"));
//				}
//				else
//				{
//					setNextState(BLACKCELL,direction);
//				}
//			}
			 if(neighbors.get(directionCalc("straight")).getState()==BLUECELL && neighbors.get(directionCalc("counterclockwise")).getState()!=BLUECELL)
			{
				System.out.print("moving forwarf");
				neighbors.get(directionCalc("straight")).setNextState(CYANCELL,direction);
			}
//			else if(counts[REDCELL]==5 && counts[BLUECELL]==0)
//				neighbors.get(directionCalc("straight")).setNextState(BLUECELL,setDirection("straight"));
			}
		
//		if(state==BLACKCELL)
//		{
//			for(int a=0; a<neighbors.size(); a++)
//				if(neighbors.get(a).getState()==CYANCELL || neighbors.get(a).getState()==YELLOWCELL)
//				{
//					neighbors.get(a).setState(BLACKCELL,neighbors.get(a).getDirection());
//					setNextState(BLUECELL,direction);
//				}
//		}
		
		counts= new int[] {0,0,0,0,0,0,0,0};
	}
	
	private int directionCalc(String dir)
	{	
		
		if(dir.equals("straight"))
		{
			if(direction == 0)
				return 1;
			else if(direction == 1)
				return 7;
			else if(direction == 2)
				return 4;
			else
				return 6;
			
			}
		else if(dir.equals("counterclockwise"))
		{
			int temp = direction;
			if(temp==0)
				return 6;
			else if(temp==1)
				return 1;
			else if(temp==2)
				return 7;
			else
				return 4;
		}
		else
		{
			int temp = direction;
			if(temp==0)
				return 7;
			else if(temp==1)
				return 4;
			else if(temp==2)
				return 6;
			else
				return 1;
		}
	}
	
	public int setDirection(String g)
	{
		if(g.equals("counterclockwise")){
			int d=direction;
			if(d==0)
				d=3;
			else
				d--;
			return d;
		}
		else
			return direction;
		
	}
	
	
	
	@Override
	public void addNeighbor(CellModel c) {
		neighbors.add((LangstonCell) c);
	}

	@Override
	public void getInput(List<Integer> list) {
		// TODO Auto-generated method stub
		
	}

	
	

}

	