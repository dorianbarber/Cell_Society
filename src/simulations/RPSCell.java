package simulations;

import java.util.List;

import cellsociety_team09.StateNode;
import javafx.scene.paint.Color;

public class RPSCell extends CellModel{

	private static Color[] colors= {Color.WHITE, Color.BLUE, Color.RED, Color.GREEN};
	private static int ROCKCELL=1;
	private static int PAPERCELL=2;
	private static int SCISSORCELL=3;
	
	
	
	public RPSCell()
	{
		StateNode s = new StateNode(colors[0], new int[] {0});
		state=s;
	}
	
	
	
	
	@Override
	int[] getStates() {
		// TODO Auto-generated method stub
		return state.getStates();
	}

	@Override
	public void findNextState() {
		int cstate=getStates()[0]
			if(cstate==ROCKCELL)
				
		
		
		
		// TODO Auto-generated method stub
		
	}

	@Override
	public void moveForward(List<List<CellModel>> cellgrid) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void getInput(List<Integer> states) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void setNextState(StateNode a) {
		// TODO Auto-generated method stub
		
	}

}
