package cellsociety_team09;

import javafx.scene.paint.Color;

/**
 * StateNode class to keep track of the history of 
 * states that each cell has been in 
 * 
 * @author Dorian
 *
 */
public class StateNode {
	//Information specific to the actual cell state
	private Color stateColor;
	private int[] states;
	
	//Pointers to the next state and the previous state
	private StateNode nextState;
	private StateNode prevState;
	
	public StateNode(Color c, int[] currentStates) {
		stateColor = c;
		states = currentStates;
	}
	
	public StateNode(StateNode pointee) {
		setNode(pointee);
	}
	
	public Color getColor() {
		return stateColor;
	}
	
	public int[] getStates() {
		return states;
	}
	
	public void setNextState(StateNode next) {
		this.nextState = next;
	}
	
	//Moves this StateNode to the nextState
	public void moveForward() {
		StateNode pointer = new StateNode(this);
		this.setNode(nextState);
		this.prevState = pointer;
	}
	
	
	private void setNode(StateNode target) {
		stateColor = target.stateColor;
		states = target.states;
		prevState = target.prevState;
		nextState = target.nextState;
	}
}
