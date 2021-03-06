package simulations;
import java.util.*;

import UnusedReferences.StateNode;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;



public class SegregationCell extends CellModel 
{
	private static final int EMPTYCELL = 0;
	private static final int TYPE1 = 1;
	private static final int TYPE2 = 2;
	private static final Color[] colors = {Color.WHITE, Color.BLUE, Color.RED, Color.WHITE, Color.WHITE};
	private ArrayList<SegregationCell> neighbors = new ArrayList<SegregationCell>();
	
	private double t=.75;
	private int state;
	private SegregationCell next;

	
	public SegregationCell(int cellstates)
	{
		color = colors[cellstates];
		state=cellstates;
	}
	
	@Override
	public void addNeighbor(CellModel c)
	{
		neighbors.add((SegregationCell) c);
	}
	
	@Override 
	public void getClicked()
	{
		if(state==TYPE2)
			state=0;
		else
			state++;
		color = colors[state];
	}
	
	public SegregationCell()
	{
		int t= (int)(Math.random()*8);
		if(t >=5)
			state=1;
		else if(t>=2)
			state=2;
		else
			state=0;
		color = colors[state];
			
		
	}

	public int getState()
	{
		return state;
	}

	public SegregationCell findNextState(double s)
	{
		t=s;
		if(state==1 || state==2)
		{
			int notmecount=0;
			int mecount=0; 
			for(int a=0; a< neighbors.size(); a++)
			{
				if(neighbors.get(a).getState()==state)
				{
					mecount++;
				}
				else if(neighbors.get(a).getState()!=0)
				{
					notmecount++;
				}
			}
			if(nPercent(mecount, notmecount)<t)
			{
				return this;
			}
			else
			{
				setNextState(state);
				return null;
			}
		}
		else
		{
			setNextState(EMPTYCELL);
			return null;
		}
	}
		
	

	private double nPercent(int mecount, int notmecount)
	{
		return((double)(mecount))/(mecount+notmecount);
	}

	@Override
	public void getInput(List<Integer> states) {
		state = states.get(0);
		color = colors[state];
	}
	
	public void setNextState(int t)
	{
		next= new SegregationCell(t);
	}
	
	public SegregationCell getNext()
	{
		return next;
	}

	public void setState(int s)
	{
		state=s;
		color=colors[s];
	}

	@Override
	public String getXMLState() {
		return Integer.toString(state);
	}

	
}