package simulations;

import java.util.ArrayList;
import java.util.List;

import cellsociety_team09.StateNode;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class LifeCell extends CellModel {
	
	
	private static final int DEADSTATE=0;
	private static final int ALIVESTATE=1;
	private static final Color[] colors = {Color.WHITE, Color.GREEN};
	
	public LifeCell(int cellstate)
	{
		//shape = new Rectangle(1,1);
		color = colors[cellstate];
		int[] states= {cellstate};
		state = new StateNode(color,states);
		neighbors = new ArrayList<CellModel>();
		//int[] possiblestates = {0,1};
	}
	
	public LifeCell()
	{
		this(0);
	}

	public Color[] getPossibleStates(){
		return colors;
	}
	
	
	public int[] getStates()
	{
		return state.getStates();
	}
	public void addNeighbor(CellModel cell){
		neighbors.add(cell);
	}
	public void findNextState()
	{
		
		int alivecount=0;
		for(int a=0; a<neighbors.size(); a++){
			if(neighbors.get(a) != null && neighbors.get(a).getStates()[0]==1){
				alivecount++;
			}
		}
				
		if(getStates()[0]==ALIVESTATE && (alivecount==2) || alivecount==3){
			StateNode s = new StateNode(colors[ALIVESTATE],new int[]{ALIVESTATE});
			 state.setNextState(s);
		}
		else{
			StateNode s = new StateNode(colors[DEADSTATE],new int[]{DEADSTATE});
			 state.setNextState(s);
		}
		
	}
	
	public void moveForward() {
		state.moveForward();
	}
	
	public void setNextState(StateNode b)
	{
		state.setNextState(b);
	}
	
	public void getInput(List<Integer> states)
	{
		state.setState(colors[states.get(0)], new int[] {states.get(0)});

	}

	public StateNode getKind(){
		return state;
	}
<<<<<<< HEAD
	
	public void getNeighbors( int r, int c, List<List<CellModel>> grid)
	{
=======
>>>>>>> e6f8fe730e217ceccd2e6ea8e55de3a26ed2ef76

	@Override
	public void getNeighbors(int row, int col, ArrayList<ArrayList<CellModel>> grid) {
	}

}
