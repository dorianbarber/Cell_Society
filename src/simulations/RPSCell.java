package simulations;

import java.util.ArrayList;
import java.util.List;

import UnusedReferences.StateNode;
import javafx.scene.paint.Color;

public class RPSCell extends CellModel{

	private static Color[] colors= {Color.WHITE, Color.BLUE, Color.RED, Color.GREEN};
	private static final int EMPTYCELL=0;
	private static final int ROCKCELL=1;
	private static final int PAPERCELL=2;
	private static final int SCISSORCELL=3;
	private ArrayList<RPSCell> neighbors = new ArrayList<RPSCell>();
	private int state;
	private int power;
	private RPSCell next;
	private boolean updated=false;
	
	public RPSCell()
	{
		this(0,6);
	}
	 
	public RPSCell(int t, int p)
	{
		power=p;
		state=t;
		color=colors[state];

	}
	
	@Override
	public void addNeighbor(CellModel a)
	{
		neighbors.add((RPSCell)a);
	}
	
	@Override
	public void getClicked()
	{
		if(state==SCISSORCELL)
			state=EMPTYCELL;
		else
			state++;
		color = colors[state];
	}
	
	public void findNextState()
	{
		System.out.print(neighbors.size());
		if(state==ROCKCELL)
			for(int a=0; a<neighbors.size(); a++)
				if(neighbors.get(a).getState()==SCISSORCELL) {
					neighbors.get(a).setNextState(state, 6);
					neighbors.get(a).setUpdated(true);
				}
				
		if(state==PAPERCELL)
			for(int a=0; a<neighbors.size(); a++)
				if(neighbors.get(a).getState()==ROCKCELL) {
					neighbors.get(a).setNextState(state, 6);
					neighbors.get(a).setUpdated(true);
		}
			
		if(state==SCISSORCELL)
			for(int a=0; a<neighbors.size(); a++)
				if(neighbors.get(a).getState()==PAPERCELL) {
					neighbors.get(a).setNextState(state, 6);
					neighbors.get(a).setUpdated(true);
		}
		if(state!=EMPTYCELL)	
			for(int a=0; a<neighbors.size(); a++)
				if(neighbors.get(a).getState()==EMPTYCELL && power>0) {
					neighbors.get(a).setNextState(state, power-1);
					neighbors.get(a).setUpdated(true);
				}

		if(!updated)
			setNextState(state,power);
		}

	
	public void setUpdated(boolean t)
	{
		updated=t;
	}
	
	public int getState()
	{
		return state;
	}

	public RPSCell getNext()
	{
		next.setUpdated(false);
		return next;
	}
	
	@Override
	public void getInput(List<Integer> states) {
		state=states.get(0);
		power=6;
		color=colors[state];
	}

	
	protected void setNextState(int t, int p) {
		next= new RPSCell(t,p);
	}


	public String getXMLState() {
		String t="" +state;
		return t;
	}
	

}
