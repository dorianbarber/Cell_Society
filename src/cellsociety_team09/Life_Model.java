package cellsociety_team09;



public class Life_Model extends Model 
{
	
	private final int EMPTY_STATE=0; //useful naming convention... dont know if these 
	private final int ALIVE_STATE=1; // would be helpful to have in cell class or how to get them to you
	private final int DEAD_STATE=2;
	
	
	public Life_Model(){}
	
	public int updateCell(int[] state, int[] neighborstates) //considering throwing exceptions for unexpected state values obviously
	{														 //redundant with LIFE but could be useful for more complex CAs
		//if(state[0]!=ALIVE_STATE || state[0]!=DEAD_STATE ||state.length>1)
			//throw an exception 
		if(state[0]==EMPTY_STATE)
			return EMPTY_STATE;
		int alive=0;
		for(int a=0; a<neighborstates.length; a++)
		{
			if(neighborstates[a]==ALIVE_STATE)
				alive++;
		}
		if((state[0]==ALIVE_STATE && (alive==2)) || alive==3)
			return ALIVE_STATE;
		else
			return DEAD_STATE;
	}
	
	public Cell createCell(int[] state) //any thoughts on creating new cells
	{
		Cell c = new Cell(state)//need a constructor for cell when possible 
		
		
		
	}
	
	
	
	
}

