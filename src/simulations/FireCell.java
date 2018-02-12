package simulations;

import java.util.ArrayList;
import java.util.List;

import cellsociety_team09.StateNode;
import javafx.scene.paint.Color;

public class FireCell extends CellModel {
	
	
	private static final int EMPTYCELL=0;
	private static final int BURNINGCELL=1;
	private static final int TREECELL=2;
	private static final Color[] colors = {Color.GREY, Color.RED, Color.GREEN};
	private ArrayList<FireCell> neighbors = new ArrayList<FireCell>();
	private int type;
	private double burnprb;
	private FireCell next;
	private int regrow;
	
	public FireCell()
	{
		this(2,.2);
	}
	
	public FireCell(int ty, double bp)
	{
		type=ty;
		burnprb=bp;
		color=colors[type];
		regrow = 23;
	}

	@Override 
	public void getInput(List<Integer> states)
	{
		type = states.get(0);
		burnprb=((double)states.get(1))/100;
		color = colors[type];
	}
	
	@Override
	public void getClicked()
	{
		if(type==2)
			type=0;
		else
			type++;
		color = colors[type];
	}
	
	public void setNextState(int t, double p)
	{
		next = new FireCell(t,p);
	}
	
	public int getState()
	{
		return type;
	}
	
	
	
	public void findNextState()
	{
		double bp=0;
		boolean burning=false;
		for(int a=0; a<neighbors.size(); a++){
			if(neighbors.get(a).getState()==BURNINGCELL)
			{
			    bp=neighbors.get(a).getBurnPrb();
				double rand = Math.random();
				burning=(rand<bp);
				break;
			}
		}	
		
		if(type==TREECELL && burning){
			setNextState(BURNINGCELL, bp);
		}
		else if(type==BURNINGCELL){
			setNextState(EMPTYCELL, burnprb);
		}
		else if (type == EMPTYCELL){
			if (regrow == 0){
				regrow = 3;
				setNextState(TREECELL, burnprb);
			}
			else {
				setNextState(EMPTYCELL, burnprb);
				regrow -= 1;
				next.setRegrow(regrow);
				
			}
		}
		else {
			setNextState(TREECELL, burnprb);
		}
	}
	public void setRegrow(int r){
		regrow = r;
	}
	public void setFire(int f)
	{
		burnprb=((double)f)/100;
	}
	
	public double getBurnPrb()
	{
		return burnprb;
	}
	
	@Override
	public void addNeighbor(CellModel c) {
		neighbors.add((FireCell) c);
	}
	
	public FireCell getNext()
	{
		return next;
	}
}
