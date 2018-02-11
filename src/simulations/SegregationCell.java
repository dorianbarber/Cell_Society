package simulations;
import java.util.*;

import cellsociety_team09.StateNode;
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
	
	public SegregationCell()
	{
		this((int)((Math.random()*2)+1));
		
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
			
			int mecount=0; 
			for(int a=0; a< neighbors.size(); a++)
			{
				if(neighbors.get(a).getState()==state)
				{
					mecount++;
				}
			}
			if(nPercent( mecount, neighbors.size())<t)
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
		
	

	private double nPercent(int mecount, int total)
	{
		return((double)(mecount))/(total);
	}

	@Override
	public void getInput(List<Integer> states) {
		if(state==2)
			state=0;
		else
			state++;
		color=colors[state];
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

}