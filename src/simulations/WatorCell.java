package simulations;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import cellsociety_team09.StateNode;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class WatorCell extends CellModel {
	
	private static final Color[] colors= {Color.BLUE,Color.GREEN, Color.RED,Color.BLUE,Color.BLUE};
	private static final int EMPTYCELL=0;
	private static final int FISHCELL=1;
	private static final int SHARKCELL=2;
	private int hunger;
	private int repo;
	private int type;
	private int starverate;
	private int reporate1;
	private int reporate2;
	private boolean inheat;
	private ArrayList<WatorCell> neighbors = new ArrayList<WatorCell>();
	private boolean updated;
	private WatorCell next;
	
	//state 0 is type is repo type 3 is starve type
	public WatorCell(int s, int h, int r)
	{
		type=s;
		hunger=h;
		repo=r;
		color=colors[type];
	}
	
	
	
	public WatorCell()
	{
		int t=(int)(Math.random()*8+1);
		if(t>6)
			type=SHARKCELL;
		else if(t<=6)
			type=FISHCELL;
		else
			type=EMPTYCELL;
		repo=0;
		hunger=0;
		color=colors[type];
	}
	
	@Override
	public void addNeighbor(CellModel c) {
		neighbors.add((WatorCell) c);
	}
	
	
	public void getNextState(int d, int r1, int r2 )
	{
		starverate=d;
		reporate1=r1;
		reporate2=r2;
		if(!updated) 
			if(type==SHARKCELL) {
				setHunger(hunger+1);
				handleShark();
				setRepo(repo+1);
			}
			if(type==FISHCELL) {
				handleFish();
				setRepo(repo+1);
			}
	}
	
	public void setNextState()
	{
		next = new WatorCell(type, repo, hunger);
	}
	
	@Override
	public void getInput(List<Integer> states) {	
		type=states.get(0);
	}
	
	public void handleShark() 
	{
		if(gethunger()==starverate)
		{
			setState(EMPTYCELL);
			hunger=0;
			repo=0;
			inheat=false;
		}
		else
			if(!isEating())
				isMoving(SHARKCELL);
	}
	public void handleFish()
	{
		isMoving(FISHCELL);
	}
	public WatorCell getNext()
	{
		return next;
	}

	private boolean isMoving(int celltype)
	{
		Collections.shuffle(neighbors);
		for(int a=0; a<neighbors.size(); a++)
		{
				if(neighbors.get(a).getState()==EMPTYCELL){
					neighbors.get(a).setState(celltype);
					neighbors.get(a).setHunger(hunger);
					neighbors.get(a).setRepo(repo);
					neighbors.get(a).setUpdated(true);
					if(inheat) {
						setState(celltype);
						neighbors.get(a).setRepo(0);
					}
					else 
						setState(EMPTYCELL);
					repo=0;
					inheat=false;
					return true;
				}
		}
		return false;
	}
	public boolean isEating()
	{
		Collections.shuffle(neighbors);
		for(int a=0; a<neighbors.size(); a++)
		{
			if(neighbors.get(a).type==FISHCELL && !neighbors.get(a).updated)
			{
				neighbors.get(a).setState(SHARKCELL);
				neighbors.get(a).setHunger(0);
				neighbors.get(a).setRepo(repo);
				neighbors.get(a).setUpdated(true);
				if(inheat) {
					setState(SHARKCELL);
					repo=0;
					inheat=false;
					neighbors.get(a).setRepo(0);
				}
				else
					setState(EMPTYCELL);
				hunger=0;
				return true;
			}
		}
		return false;
	}
	public int gethunger(){
		return hunger;
	}
	public void setRepo(int t)
	{
		repo=t;
		if((type==SHARKCELL && repo>=reporate2) || (type==FISHCELL && repo>=reporate1)) {
			inheat=true;
			repo=0;
		}
	}
	public void setHunger(int t){
		hunger=t;
	}
	public int getState(){
		color=colors[type];
		return type;
	}
	private void setState(int t){
		type=t;
	}
	private void setUpdated(boolean t) {
		updated=t;
	}

}
