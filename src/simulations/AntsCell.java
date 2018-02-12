package simulations;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import javafx.scene.paint.Color;

public class AntsCell extends CellModel implements Comparable<AntsCell>{
	
	private final int GROUNDCELL=0;
	private final int FOODCELL=1;
	private final int HOMECELL=2;
	
	private ArrayList<AntsCell> neighbors = new ArrayList<AntsCell>();
	private ArrayList<Ant> ants = new ArrayList<Ant>();

	private int state;
	private double foodpher;
	private double homepher;
	private int row;
	private int col;
	private AntsCell next;
	
	public AntsCell(int s, List<Ant> as, double fp, double hp, int r, int c)
	{
		state=s;
		foodpher=fp;
		homepher=hp;
		if(as!=null)
			for(int a=0; a<as.size(); a++)
				ants.add(as.get(a));
		row=r;
		col=c;
		color=colorCalc();
		
	}
	
	public AntsCell()
	{
		this(0,null,0,0,0,0);
	}
	
	public void setRC(int r, int c)
	{
		row=r;
		col=c;
	}
	
	public void setState(int t)
	{
		state=t;
		color=colorCalc();
	}
	
	public void addAnt(Ant a)
	{
		ants.add(a);
	}
	
	public AntsCell getNext()
	{
		
		return next;
	}
	
	@Override
	public void addNeighbor(CellModel c) {
		neighbors.add( (AntsCell) c);
		
	}

	
	
	public void nextState()
	{
		for(int a=0; a< ants.size(); a++)
		{
			if(ants.get(a).hasFood())
				foodpher=1;
			else
				homepher=1;
			if(ants.get(a).update(neighbors)) {
				ants.remove(a);
				a--;
			}
		}
		if(homepher>0)
			homepher-=.02;
		if(foodpher>0)
			foodpher-=.02;
	}
	
	public void getNextState()
	{
		for(int a=0; a<ants.size(); a++)
			ants.get(a).setMoved(false);
		next=new AntsCell(state, ants, foodpher, homepher, row, col);
	}
	
	public double getFoodPher()
	{
		return foodpher;
	}
	public double getHomePher()
	{
		return homepher;
	}
	public int getAnts()
	{
		return ants.size();
	}
	
	public Color colorCalc()
	{
		if(ants.size()>0)
			return Color.RED;
		else if(state==FOODCELL)
			return Color.BLUE;
		else if(state==HOMECELL)
			return Color.MAGENTA;
		else 		{
			if(homepher>0 || foodpher>0)
				if(homepher>foodpher)
					return Color.GREEN;
				else
					return Color.AQUA;
			else
				return Color.SADDLEBROWN;
		}
	}
	
	
	
	
	@Override
	public void getInput(List<Integer> list) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void getClicked()
	{
		if(state==GROUNDCELL || state==FOODCELL) {
			state=HOMECELL;
			ants.clear();
		}
		else if(state==HOMECELL)
			state=FOODCELL;
		color=colorCalc();

	}

	public int getState()
	{
		return state;
	}
	
	public int getRow()
	{
		return row;
	}
	public int getCol()
	{
		return col;
	}

	public static class HomePheremone implements Comparator<AntsCell> {
		public HomePheremone() {}
		public int compare(AntsCell v, AntsCell w) {
			double r =(v.getHomePher() - w.getHomePher());
			if(r<0)
				return (1);
			else if(r==0) 
				return 0;
			else
				return -1;
		}
	}

	public static class FoodPheremone implements Comparator<AntsCell> {
		public FoodPheremone() {}
		public int compare(AntsCell v, AntsCell w) {
			double r =(v.getFoodPher() - w.getFoodPher());
			if(r<0)
				return (1);
			else if(r==0) 
				return 0;
			else
				return -1;
		}
	}

	@Override
	public int compareTo(AntsCell o) {
		// TODO Auto-generated method stub
		return 0;
	}


	
	
	
	
	

}
