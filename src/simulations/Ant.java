package simulations;

import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public class Ant {
	
	private final int GROUNDCELL=0;
	private final int FOODCELL=1;
	private final int HOMECELL=2;
	
	
	private int row;
	private int col;
	private boolean hasfood;
	private int direction;
	
	
	public boolean update(List<AntsCell> neighbors)
	{
		Collections.shuffle(neighbors);
		if(!hasfood)
		{
			if(!foundFood(neighbors))
			{
				if(isMoving(neighbors))
					return true;
				else
					return false;
			}
			else
				return false;
		}
		else
		{
			if(!foundHome(neighbors))
			{
				if(isMoving(neighbors))
					return true;
				else
					return false;

			}
			else
				return false;
		}
	}
	
	public boolean isMoving(List<AntsCell> neighbors)
	{
		PriorityQueue<AntsCell> bestlocs =null;
		if(hasfood) {
			bestlocs = new PriorityQueue<AntsCell>(new AntsCell.HomePheremone());
		}
		else {
			bestlocs = new PriorityQueue<AntsCell>(new AntsCell.FoodPheremone());
		}
		for(int b=0; b<2; b++) {
			for(int a=0; a<neighbors.size(); a++)
			{
				if(sameDirection(neighbors.get(a)) && neighbors.get(a).getAnts()<10)
					bestlocs.add(neighbors.get(a));
			}
			if(direction==3)
				direction=0;
			else
				direction++;
		}
		if(!bestlocs.isEmpty())
		{
			AntsCell temp = bestlocs.remove();
			row=temp.getRow();
			col=temp.getCol();
			temp.addAnt(this);
			return true;
		}
		return false;
	}
	
	public boolean foundFood(List<AntsCell> neighbors)
	{
		for(int a=0; a<neighbors.size(); a++)
		{
			if(neighbors.get(a).getState()==FOODCELL) 
			{
				neighbors.get(a).setState(GROUNDCELL);
				hasfood=true;
				direction = getDirection(Collections.max(neighbors, new AntsCell.HomePheremone()));
				return true;
			}
			
		}
		return false;
	}
	
	public boolean foundHome(List<AntsCell> neighbors)
	{
		for(int a=0; a<neighbors.size(); a++)
		{
			if(neighbors.get(a).getState()==HOMECELL) 
			{
				hasfood=false;
				direction = getDirection(Collections.max(neighbors, new AntsCell.FoodPheremone()));
				return true;
			}
			
		}
		return false;
	}
	
	public int getDirection(AntsCell a)
	{
		if(a.getRow()>row)
			return 2;
		else if(a.getRow()<row)
			return 0;
		else if(a.getCol()<col)
			return 1;
		else
			return 3;
	}
	
	public boolean sameDirection(AntsCell a)
	{
		
		if(direction == 0)
			return (row>a.getRow());
		else if(direction == 1)
			return (col<a.getCol());
		else if(direction == 2)
			return (row<a.getRow());
		else
			return (col>a.getCol());
	}

	public boolean hasFood()
	{
		return hasfood;
	}
}
