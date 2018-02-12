package UnusedReferences;

import javafx.scene.paint.Color;

/**
 * StateNode class to keep track of the history of 
 * states that each cell has been in 
 * 
 * Has no dependencies
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
	
	//Creates a fresh StateNode with no set pointers
	public StateNode(Color c, int[] currentStates) {
		stateColor = c;
		states = currentStates;
	}
	
	//Creates a StateNode with purpose to act as a pointer
	public StateNode(StateNode pointee) {
		setNode(pointee);
	}
	
	//gets the color of the StateNode
	public Color getColor() {
		return stateColor;
	}
	
	//gets the array of states of the StateNode
	public int[] getStates() {
		return states;
	}
	
	public void setState(Color c, int[] currentstates)
	{
		stateColor=c;
		states=currentstates; 
	}
	
	public void setNextState(StateNode next) {
		nextState = next;
	}
	
	public boolean hasNext(){
		return (nextState!=null);
	}
	
	public boolean hasPrev(){
		return (prevState!=null);
	}
	/**
	 * Makes this StateNode the next state node
	 * and points the new prevState to the state
	 * the StateNode was just in
	 */
	public void moveForward() {
		StateNode pointer = new StateNode(this);
		if (nextState != null) {
			this.setNode(nextState);
		}
		else{
		}
		this.prevState = pointer;
	}
	
	public void moveBackward() {
		StateNode pointer = new StateNode(this);
		this.setNode(prevState);
		this.nextState = pointer;
	}
	
	
	public StateNode getNextState()
	{
		return nextState;
	}
	private void setNode(StateNode target) {
		stateColor = target.stateColor;
		states = target.states;
		prevState = target.prevState;
		nextState = target.nextState;
	}
}
