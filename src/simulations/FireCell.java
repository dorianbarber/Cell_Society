package simulations;

import java.util.ArrayList;
import java.util.List;

import cellsociety_team09.StateNode;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class FireCell extends CellModel {
	
	
	public static final int EMPTYCELL=0;
	public static final int BURNINGCELL=1;
	public static final int TREECELL=2;
	public static final Color[] colors = {Color.GREY, Color.RED, Color.GREEN};
	private int burnprb;
	
	public FireCell(int cellstate, int d)
	{
		burnprb=d;
		//shape = new Rectangle(1,1);
		color=colors[cellstate];
		int[] states= {cellstate,burnprb};
		state = new StateNode(color,states);
		List<FireCell> neighbors = new ArrayList<FireCell>();
	}
	
	public FireCell()
	{
		this(2,70);
	}
	

	@Override 
	public void getInput(List<Integer> states)
	{
		burnprb=states.get(1);
		int[] s = new int[]{states.get(0),burnprb};
		state.setState(colors[states.get(0)], s);
	}
	
	public void setNextState(StateNode b)
	{
		state.setNextState(b);
	}
	
	
	public int[] getStates()
	{
		return state.getStates();
	}

	public void findNextState()
	{
		int percentbrn=0;
		boolean burning=false;
		StateNode s;
		for(int a=0; a<neighbors.length; a++)
			if(neighbors[a]!=null && neighbors[a].getStates()[0]==BURNINGCELL)
			{
				percentbrn =neighbors[a].getStates()[1];
				double prb=((double)percentbrn)/100;
				burning=(Math.random()<prb);
			}
			
		if(getStates()[0]==TREECELL && burning)
			s = new StateNode(colors[BURNINGCELL], new int[] {BURNINGCELL,percentbrn});
		else if(getStates()[0]==BURNINGCELL)
			s = new StateNode(colors[EMPTYCELL], new int[] {EMPTYCELL, 0});
		else
			s = new StateNode(colors[getStates()[0]], getStates());
		state.setNextState(s);
	}
		
		
		
	
	public void moveForward(ArrayList<ArrayList<CellModel>> grid) {
		state.moveForward();
	}
	
	

	


}
