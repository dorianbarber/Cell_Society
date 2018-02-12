package simulations;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import cellsociety_team09.StateNode;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class WatorCell extends CellModel {
	
	private static final Color[] colors= {Color.BLUE,Color.GREEN, Color.RED};
	private static final int EMPTYCELL=0;
	private static final int FISHCELL=1;
	private static final int SHARKCELL=2;
	private int hunger;
	private int repo;
	private int state;
	private int starverate;
	private int reporate1;
	private int reporate2;
	private ArrayList<WatorCell> neighbors = new ArrayList<WatorCell>();
	private boolean updated;
	private WatorCell next;
	
	//state 0 is state is repo state 3 is starve state
	public WatorCell(int s, int h, int r)
	{
		state=s;
		hunger=h;
		repo=r;
		color=colors[state];
	}
	
	@Override
	public void getClicked()
	{
		if(state==SHARKCELL)
		{
			state=0;
		}
		else {
			state++;
		}
		
		color = colors[state];
	}
	
	
	
	public WatorCell()
	{
		int t=(int)(Math.random()*8+1);
		if(t>7)
			state=SHARKCELL;
		else if(t<=7 && t>5)
			state=FISHCELL;
		else
			state=EMPTYCELL;
		repo=0;
		hunger=0;
		color=colors[state];
	}
	
	@Override
	public void addNeighbor(CellModel c) {
		neighbors.add((WatorCell) c);
	}
	
	
	public void getNextState(int d, int r1, int r2 )
	{
//		if(state==SHARKCELL)
//			System.out.println("type: " + state + " repo# " + repo + " hunger " + hunger);
		starverate=d;
		reporate1=r1;
		reporate2=r2;
		if(!updated) {
			if(state==SHARKCELL) {
				handleShark();
			}
			else if(state==FISHCELL) {
				handleFish();
			}
			else
				setNextState();
		}
	}
	
	public void setNextState()
	{
		next = new WatorCell(state, hunger, repo);
	}
	
	@Override
	public void getInput(List<Integer> states) {	
		state = states.get(0);
		hunger = states.get(1);
		repo = states.get(2);
		color = colors[state];
	}
	
	public void handleShark() 
	{
		if(hunger>=starverate)
		{
			state=0;
			hunger=0;
			repo=0;
			setNextState();
		}
		else if(!isEating())
			isMoving(SHARKCELL);
	}
	public void handleFish()
	{
		isMoving(FISHCELL);
	}
	public WatorCell getNext()
	{
		updated=false;

		return next;
	}

	private boolean isMoving(int cellstate)
	{
		Collections.shuffle(neighbors);
		for(int a=0; a<neighbors.size(); a++)
		{
				if(neighbors.get(a).getState()==EMPTYCELL){
					neighbors.get(a).setState(cellstate);
					neighbors.get(a).setHunger(hunger+1);
					neighbors.get(a).setRepo(repo+1);
					neighbors.get(a).setUpdated(true);
					if((repo>=reporate1 && cellstate==FISHCELL) || (repo>=reporate2 && cellstate==SHARKCELL)) {
						neighbors.get(a).setRepo(0);
						updated=true;
					}
					else 
						setState(EMPTYCELL);
					repo=0;
					neighbors.get(a).setNextState();
					setNextState();
					return true;
				}
		}
		repo++;
		hunger++;
		setNextState();
		return false;
	}
	public boolean isEating()
	{
		//System.out.println(neighbors.size());
		Collections.shuffle(neighbors);
		for(int a=0; a<neighbors.size(); a++)
		{
			if(neighbors.get(a).getState()==FISHCELL)
			{
				neighbors.get(a).setState(SHARKCELL);
				neighbors.get(a).setHunger(0);
				neighbors.get(a).setRepo(repo+1);
				neighbors.get(a).setUpdated(true);
				neighbors.get(a).setNextState();
				if(repo>=reporate2) {
					neighbors.get(a).setRepo(0);
					state=SHARKCELL;
				}
				else {
					state=EMPTYCELL;
				}
				repo=0;
				hunger=0;
				setNextState();
				return true;
			}
		
		}
		return false;
	}

	public void setRepo(int t)
	{
		repo=t;
	}
	public void setHunger(int t){
		hunger=t;
	}
	public int getState(){
		color=colors[state];
		return state;
	}
	private void setState(int t){
		state=t;
		color=colors[t];
	}
	private void setUpdated(boolean t) {
		updated=t;
	}

	@Override
	public String getXMLState() {
		return state + " " + hunger + " " + repo;
	}

}
