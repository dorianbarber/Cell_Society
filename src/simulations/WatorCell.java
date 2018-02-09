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
	private int starverate;
	private int reporate1;
	private int reporate2;

	private int type;
	private boolean isalive=true;

	private boolean inheat =false;
	private boolean moved=false;
	private boolean moving=false;
	
	//state 0 is type is repo type 3 is starve type
	public WatorCell(int s, int repo1, int repo2, int death)
	{
		if(s==0)
			type=0;
		else if(s<6)
			type=1;
		else
			type=2;
		shape = new Rectangle(1,1);
		starverate=6;
		reporate1=3;
		reporate2=8;
		StateNode n = new StateNode(colors[type],new int[] {type,0,0});
		state=n;
		neighbors = null;
	}
	
	public WatorCell()
	{
		this((int)(Math.random()*8+1),3,8,6);
	}
	
	
	private void findNextState(StateNode n)
	{
		state.setNextState(n);
	}
	
	private void setState(Color n, int[] s)
	{
		state.setState(n, s);
	}
	
	
	@Override	
	public void findNextState()
	{

		int[] states = getStates();
		
		
		if(states[0]==SHARKCELL)
		{
			handleShark();
		}
		if(states[0]==FISHCELL)
		{
			handleFish();
		}
		if(states[0]==EMPTYCELL && !moved)
		{
			setNextState(new StateNode(colors[EMPTYCELL],new int[] {EMPTYCELL,0,0}));
		}
	}
		
		

		
		
	
	public boolean isAlive()
	{
		return isalive;
	}
	
	public void setAlive(boolean b)
	{
		isalive=b;
	}
	
	public static int[] shuffle(int[] ar)
	{
		int a=0;
		while(a<10)
		{
			int b=(int)(Math.random()*ar.length);
			int c=(int)(Math.random()*ar.length);
			int temp=ar[b];
			ar[b]=ar[c];
			ar[c]=temp;
			a++;
		}
		return ar;
	}
	
	
	public void moveForward(List<List<CellModel>> grid)
	{
		this.moved=false;
		this.moving=false;
		this.inheat=false;
		setAlive(true);
		state.moveForward();
	}

		
	public int[] getStates()
	{
		return state.getStates();
	}


	@Override
	public void getInput(List<Integer> states) {
		state.setState(colors[EMPTYCELL], new int[] {0,0,0});
	}
	
	public void handleShark() 
	{
		int reproduce=0;
		StateNode baby=null;
		StateNode empty= new StateNode(colors[EMPTYCELL],new int[] {EMPTYCELL,0,0});
		if(!(getStates()[2]>=starverate))
		{
			int hunger=getStates()[2]+1;
			
			if(getStates()[1]>=reporate2){
				inheat=true;
				reproduce=0;
				baby = new StateNode(colors[SHARKCELL], new int[] {SHARKCELL,0,0});
			}
			else
				reproduce=getStates()[1]+1;
			
			if(isEating(reproduce)) {
				if(inheat) {
					setNextState(baby);
					moved=true;
				}
			
				else
					setNextState(empty);
			}
			else if(isMoving(reproduce, hunger, SHARKCELL)) {
				if(inheat) {
					setNextState(baby);
					moved=true;
				}
				else
					setNextState(empty);
			}
			
			else {
				if(inheat)
					reproduce=800;
				setNextState(new StateNode(colors[SHARKCELL],new int[] {SHARKCELL,reproduce,hunger}));
			}
		}
		else {
			setNextState(new StateNode(colors[EMPTYCELL],new int[] {EMPTYCELL,0,0}));
			setAlive(false);
		}
	}
	
	
	public boolean isEating(int reproduce)
	{
		Collections.shuffle(neighbors);
		for(int a=0; a<neighbors.size(); a++){
			if(neighbors.get(a).getStates()[0]!=-1)
			{
				WatorCell c = (WatorCell)neighbors.get(a);
				if(c.getStates()[0]==FISHCELL && c.isAlive() && (!c.moving ||(c.inHeat() && c.getStates()[0]==FISHCELL))){
					c.setNextState(new StateNode(colors[SHARKCELL], new int[] {SHARKCELL,reproduce+1,0}));
					c.setAlive(false);
					c.moved=true;
					moving=true;
					return true;
				}
				
			}
			
		}
		return false;
	}
	public void handleFish()
	{
		int reproduce=0;
		StateNode baby = new StateNode(colors[FISHCELL], new int[] {FISHCELL,0,0});
		StateNode empty= new StateNode(colors[EMPTYCELL],new int[] {EMPTYCELL,0,0});
		if(isAlive())
		{
			if(getStates()[1]>=reporate1){
				setHeat(true);
				reproduce=0;
			}
			else
				reproduce=getStates()[1]+1;
			if(isMoving(reproduce, 0, FISHCELL)){
				if(inHeat())
					setNextState(baby);
				else
					setNextState(empty);
			}
			if(!moving){			
				setNextState(new StateNode(getColor(),new int[] {FISHCELL,reproduce,0}));
			}
		}
	}
	
	private boolean isMoving(int r, int h, int type)
	{
		Collections.shuffle(neighbors);
		for(int a=0; a<neighbors.size(); a++){
			if(neighbors.get(a).getStates()[0]!=-1)
			{
				WatorCell c=(WatorCell)neighbors.get(a);
				if((c.moving||c.getStates()[0]==EMPTYCELL) && !c.moved){
					c.setNextState(new StateNode(colors[type], new int[] {type,r+1,h+1}));
					moving=true;
					c.moved=true;
					return true;
				}
			}
		}
		return false;
		
	}
	
	private void setHeat(boolean t)
	{
		inheat=t;
	}
	private boolean inHeat()
	{
		return inheat;
	}
	
	

	
	
//	public void getNeighbors( int r, int c, ArrayList<ArrayList<CellModel>> grid)
//	{
//		
//		
//		int length=grid.get(0).size();
//		int height=grid.get(0).size();
//		if(c==0 && r==0){
//			neighbors = new WatorCell[] {(WatorCell)grid.get(height-1).get(c), null,(WatorCell)grid.get(r).get(c+1), null, (WatorCell)grid.get(r+1).get(c),
//					null, (WatorCell) grid.get(r).get(length-1), null};
//		}
//		else if(c==(length-1) && r==0){
//			neighbors= new WatorCell[] {(WatorCell) grid.get(height-1).get(c), null, (WatorCell) grid.get(r).get(0), null, (WatorCell)grid.get(r+1).get(c), null,
//					(WatorCell)grid.get(r).get(c-1), null};// 6 left
//		}
//		else if(r==(height-1) && c==0){
//			neighbors= new WatorCell[] {(WatorCell)grid.get(r-1).get(c), null,(WatorCell) grid.get(r).get(c+1), null, (WatorCell) grid.get(0).get(c),
//					null, (WatorCell) grid.get(r).get(length-1), null};	
//		}
//		else if(r==(height-1) && c==(length-1)){
//			neighbors= new WatorCell[] {(WatorCell)grid.get(r-1).get(c), null, (WatorCell) grid.get(r).get(0), null, (WatorCell) grid.get(0).get(length-1), null, (WatorCell)grid.get(r).get(c-1), 
//					null};
//		}
//		else if(r==0) { //top edge check
//			neighbors = new WatorCell[] {(WatorCell) grid.get(height-1).get(c), null, (WatorCell) grid.get(r).get(c+1),  null,  (WatorCell)grid.get(r+1).get(c), 
//					null, (WatorCell)grid.get(r).get(c-1), null}; 		
//		}
//		else if(r==(height-1)) { // bottom edge check
//			neighbors = new WatorCell[] {(WatorCell)grid.get(r-1).get(c), null,(WatorCell) grid.get(r).get(c+1),null,(WatorCell) grid.get(0).get(c), 
//					null,(WatorCell) grid.get(r).get(c-1) ,null};			
//		}
//		else if(c==0){ //left edge check
//			neighbors = new WatorCell[] {(WatorCell)grid.get(r-1).get(c), null,(WatorCell) grid.get(r).get(c+1),  null, 
//					(WatorCell)grid.get(r+1).get(c), null, (WatorCell) grid.get(r).get(length-1), null};  
//		}
//		else if( c==(length-1)) { // right edge check
//			neighbors = new WatorCell[] { (WatorCell)grid.get(r-1).get(c) ,null,(WatorCell) grid.get(r).get(0),null,(WatorCell) grid.get(r+1).get(c),null,  
//					(WatorCell)	grid.get(r).get(c-1), null};
//		}
//		else // checking for middle cell
//		{
//			neighbors = new WatorCell[] {(WatorCell)grid.get(r-1).get(c), //0 top 
//										 null, //1 top right
//										 (WatorCell) grid.get(r).get(c+1), //2 right
//										 null, // 3 bottom right
//										 (WatorCell) grid.get(r+1).get(c),  // 4 bottom 
//										 null, // 5 bottom left
//										 (WatorCell) grid.get(r).get(c-1), // 6 left
//										null}; // top left
//		}
//	}

	@Override
	protected void setNextState(StateNode a) {
		state.setNextState(a);// TODO Auto-generated method stub
		
	}

	

}
