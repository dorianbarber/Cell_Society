package simulations;

import java.util.ArrayList;
import java.util.List;

import cellsociety_team09.StateNode;

/**
 * The purpose of this class is to support a n+2 by n+2 grid size. 
 * The cells on the edges of the grid will be made up of instances
 * of the NullCell class. The specific purpose is to make the 
 * getNeighbors method more abstractable since the cells no longer
 * needs to identify if it is an edge/corner/middle cell. 
 * 
 * @author Dorian
 *
 */
public class NullCell extends CellModel{

	//Does nothing because these cells do not have neighbors
	@Override
	public void getNeighbors(int row, int col, ArrayList<ArrayList<CellModel>> grid) {}

	//This {-1} will be the identifier for what a NullCell is considered
	//this is important when the other cells are looking for their neighbors
	@Override
	int[] getStates() {
		return new int[] {-1};
	}

	//Does nothing because these cells do not have a next state
	@Override
	public void findNextState() {}

	//Does nothing because these cells do not have a next state
	@Override
	public void moveForward(ArrayList<ArrayList<CellModel>> cellgrid) {}

	//Does nothing because these cells do not have a next state 
	@Override
	public void getInput(List<Integer> states) {}

	//Does nothing because these cells do not have a next state
	@Override
	protected void setNextState(StateNode a) {}

}
