package simulations;

import java.util.ArrayList;
import java.util.List;

import cellsociety_team09.StateNode;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class LifeCell extends CellModel {
	
	private int state;
	private static final int DEADCELL=0;
	private static final int ALIVECELL=1;
	private static final Color[] colors = {Color.WHITE, Color.GREEN};
	private ArrayList<LifeCell> neighbors = new ArrayList<LifeCell>();
	private LifeCell next;
	
	
	public LifeCell(int cellstate)
	{
		//shape = new Rectangle(1,1);
		state=cellstate;
		color = colors[cellstate];
	}
	
	public LifeCell()
	{
		this(0);
	}
	public int numColors(){
		return colors.length;
	}
	@Override
	public void getClicked()
	{
		if(state==0){
			state=1;
		}
		else{
			state=0;
		}
		color = colors[state];
	}
	
	public int getState()
	{
		return state;
	}
	public void addNeighbor(CellModel cell){
		neighbors.add((LifeCell) cell);
	}
	public LifeCell getNext()
	{
		return next;
	}
	public void findNextState()
	{
		System.out.print(neighbors.size());
		int alivecount=0;
		for(int a=0; a<neighbors.size(); a++){
			if(neighbors.get(a).getState()==ALIVECELL)
				alivecount++;
		}
				
		if((state==ALIVECELL && (alivecount==2)) || alivecount==3){
			setNextState(ALIVECELL);
		}
		else{
			setNextState(DEADCELL);
		}
		
	}
	
	private void setNextState(int t)
	{
		next = new LifeCell(t);
	}

	
	public void getInput(List<Integer> states)
	{
		state=states.get(0);
		color = colors[state];

	}

	@Override
	public String getXMLState() {
		return Integer.toString(getState());
	}	

}
