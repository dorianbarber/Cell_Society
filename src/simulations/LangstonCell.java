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
	private int[] counts;
	private boolean updated;
	private int state=0;
	private int direction;
	private ArrayList<LangstonCell> neighbors = new ArrayList<LangstonCell>();
	private boolean diverged=false;
	private Color[] colors= {Color.BLACK, Color.BLUE, Color.RED, Color.GREEN, Color.YELLOW,
			Color.PINK, Color.WHITE, Color.CYAN};
	private LangstonCell next;

	public LangstonCell(int t) {
		state=t;
		color=colors[t];
	}
	
	public LangstonCell getNext()
	{
		return next;
	}
	
	public void setNextState(int t)
	{
		next = new LangstonCell(t);
	}
	
	public int getState()
	{
		return state;
	}
	
	public boolean isUpdated()
	{
		return updated;
	}
	
	public void getNextState()
	{
		for(int a=0; a<neighbors.size(); a++)
		{
			counts[neighbors.get(a).getState()]++;
		}
		if(state==CYANCELL)
		{
			if(counts[BLUECELL]==2) {
				if(!diverged) {
					neighbors.get(getDirection("counterclockwise")).setNextState(CYANCELL);
					diverged=true;
				}
				else
				{
					setNextState(BLACKCELL);
					neighbors.get(index)
				}
			}
			else if(counts[REDCELL]==5)
				neighbors.get(7).setNextState(BLUECELL);
		}
		
		
	}
	
	private int getDirection(String dir)
	{	
		if(dir.equals("straight"))
		{
			if(direction == 0)
				return 1;
			else if(direction == 1)
				return 8;
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
				return 8;
			else
				return 4;
		}
		else
		{
			int temp = direction;
			if(temp==0)
				return 8;
			else if(temp==1)
				return 4;
			else if(temp==2)
				return 6;
			else
				return 1;
		}
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

	