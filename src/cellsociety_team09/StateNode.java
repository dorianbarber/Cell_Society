package cellsociety_team09;

import javafx.scene.paint.Color;

public class StateNode {
	private Color stateColor;
	private int[] states;
	
	private StateNode nextState;
	private StateNode prevState;
	
	public StateNode(Color c, int[] currentStates) {
		stateColor = c;
		states = currentStates;
		nextState = null;
		prevState = null;
	}
	
	public Color getColor() {
		return stateColor;
	}
	
	public int[] getStates() {
		return states;
	}
	
	public void setNextState(StateNode next) {
		nextState = next;
	}
	
	public void moveForward() {
		StateNode pointer = this;
		this.nextState = this;
		this.prevState = pointer;
	}
}
