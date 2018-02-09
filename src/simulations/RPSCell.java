package simulations;

import java.util.ArrayList;
import java.util.List;

import cellsociety_team09.StateNode;
import javafx.scene.paint.Color;

public class RPSCell extends CellModel{

	private static Color[] colors= {Color.WHITE, Color.BLUE, Color.RED, Color.GREEN};
	private static int EMPTYCELL;
	private static int ROCKCELL=1;
	private static int PAPERCELL=2;
	private static int SCISSORCELL=3;
	private ArrayList<RPSCell> neighbors = new ArrayList<RPSCell>();
	private int type;
	private int power;
	private RPSCell next;
	
	public RPSCell()
	{
		type=0;
		power=10;
	}
	 
	public RPSCell(int t, int p)
	{
		power=p;
		type=t;
	}
	
	@Override
	public void addNeighbor(CellModel a)
	{
		neighbors.add((RPSCell)a);
	}
	
	public void nextState()
	{
		if(type==ROCKCELL)
			for(int a=0; a<neighbors.size(); a++)
				if(neighbors.get(a).getState()==SCISSORCELL)
					neighbors.get(a).setNextState(type, 10);
				
		if(type==PAPERCELL)
			for(int a=0; a<neighbors.size(); a++)
				if(neighbors.get(a).getState()==ROCKCELL)
					neighbors.get(a).setNextState(type, 10);
			
		if(type==SCISSORCELL)
			for(int a=0; a<neighbors.size(); a++)
				if(neighbors.get(a).getState()==PAPERCELL)
					neighbors.get(a).setNextState(type, 10);
			
		for(int a=0; a<neighbors.size(); a++)
			if(neighbors.get(a).getState()==EMPTYCELL && power>0)
				neighbors.get(a).setNextState(type, power-1);
	}
	
	
	public int getState()
	{
		return type;
	}

	public RPSCell getNext()
	{
		return next;
	}
	@Override
	public void getInput(List<Integer> states) {
		// TODO Auto-generated method stub
		
	}

	
	protected void setNextState(int t, int p) {
		next= new RPSCell(t,p);
	}
	

}
