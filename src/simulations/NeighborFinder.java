package simulations;

import java.util.ArrayList;
import java.util.List;

import com.sun.javafx.geom.Shape;

import UnusedReferences.Grid;
import cellsociety_team09.Hexagon;
import cellsociety_team09.Triangle;
import javafx.scene.shape.Rectangle;

public class NeighborFinder {
	
	private static int[] rs= {};
	private static int[] cs= {};
	private static int[] rowindexu = {};
	private static int[] rowindexd = {};
	private static int[] colindexu = {};
	private static int[] colindexd = {};
	
	
	private static final int[] triangle_down_row_full = {-1,-1,-1,-1,-1, 0, 0, 0, 0, 0, 1, 1, 1};
	private static final int[] triangle_up_row_full = {-1,-1,-1, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1};									  
	private static final int[] triangle_down_col_full  = {-2,-1, 0, 1, 2,-2,-1, 0, 1, 2,-1, 0, 1};
	private static final int[] triangle_up_col_full = {-1, 0, 1,-2,-1, 0, 1, 2,-2,-1, 0, 1, 2};

	private static final int[] triangle_down_row_pyr = {-1, 0, 0};
	private static final int[] triangle_up_row_pyr = {0, 1, 1};									  
	private static final int[] triangle_down_col_pyr  = {0,-1, 1};
	private static final int[] triangle_up_col_pyr = {-1, 1, 0};
	
	private static final int[] hexagon_up_row = {-1,-1,-1, 0, 0, 0, 1};
	private static final int[] hexagon_down_row = {-1, 0, 0, 0, 1, 1, 1};
	private static final int[] hexago_col = {-1, 0, 1, -1, 0, 1, 0};
	
	private static final int[] rectangle_row_standard = {-1,-1,-1, 1, 1, 1, 0, 0, 0};
	private static final int[] rectangle_col_standard = {-1, 0, 1,-1, 0, 1,-1, 0, 1};
	
	private static final int[] rectangle_row_cross = {-1, 0, 0, 1};
	private static final int[] rectangle_col_cross = { 0,-1, 1, 0};
	
	
	public static void getNeighbors(List<List<CellModel>> grid, String s, String nebtype, String gridtype)
	{

		if(s.equals("Triangle"))
			getNeighbors(grid,new Triangle(0,0,0,false), nebtype,gridtype);
		if(s.equals("Square"))
			getNeighbors(grid,new Rectangle(), nebtype,gridtype);
		if(s.equals("Hexagon"))
			getNeighbors(grid,new Hexagon(0, 0, 0, false), nebtype,gridtype);
		

	}
	//Triangle main 
	public static void getNeighbors(List<List<CellModel>> grid, Triangle t, String nebtype, String gridtype)	
	{			
		decideNebs(nebtype, t);
		if(gridtype.equals("toroidal"))
			getNeighborsToroidal(grid, t);
		else
			getNeighborsRegular(grid,t);
	}
	//sub method for toroidal grid																									
	public static void getNeighborsToroidal(List<List<CellModel>> grid, Triangle t)
	{
		int size=grid.get(0).size();
		for(int r=0; r<grid.get(0).size(); r++)
			for(int c=0; c<grid.get(0).size(); c++)
			{
				if(upside(r,c)){
					rs=rowindexu;
					cs=colindexu;
				}
				else{
					rs=rowindexd;
					cs=colindexd;
				}
				for(int a=0; a<rs.length; a++)
						if(rs[a]!=0||cs[a]!=0)
							try{
								grid.get(r).get(c).addNeighbor(grid.get((r+rs[a])).get(c+cs[a]));
							}
							catch(IndexOutOfBoundsException obobrow) {
								try {
									grid.get(r).get(c).addNeighbor(grid.get(size-Math.abs(r+rs[a])).get(c+cs[a]));
								}
								catch(IndexOutOfBoundsException obobcol) {								
									try {
										grid.get(r).get(c).addNeighbor(grid.get((r+rs[a])).get(size-Math.abs(c+cs[a])));
									}
										catch(IndexOutOfBoundsException obobcolrow) {
											grid.get(r).get(c).addNeighbor(grid.get(size-Math.abs(r+rs[a])).get(size-Math.abs(c+cs[a])));
										}
								}
							}
			}
	}
	//sub method for standard grid
	public static void getNeighborsRegular(List<List<CellModel>> grid, Triangle t)
	{
		int size=grid.get(0).size();
		for(int r=0; r<size; r++)
			for(int c=0; c<size; c++)
			{
				if(upside(r,c)){
					rs=rowindexu;
					cs=colindexu;
				}
				else{
					rs=rowindexd;
					cs=colindexd;
				}
				for(int a=0; a<rs.length; a++)
						if(rs[a]!=0||cs[a]!=0)
							try{
								grid.get(r).get(c).addNeighbor(grid.get((r+rs[a])).get(c+cs[a]));
							}
							catch(IndexOutOfBoundsException obobrowcol) {
								//what do i do here we are just supposed to not add the neighbor
							}
			}
	}
	//sub method for deciding between pyramid and standard neighbors
	private static void decideNebs(String nebtype, Triangle t)
	{

		if(nebtype.equals("standard")) {
			 rowindexu = triangle_up_row_full;
			 rowindexd = triangle_down_row_full;
			 colindexu = triangle_up_col_full;
			 colindexd = triangle_down_col_full;
		}
		else {
			 rowindexu = triangle_up_row_pyr;
			 rowindexd = triangle_down_row_pyr;
			 colindexu = triangle_up_col_pyr;
			 colindexd = triangle_down_col_pyr;
		}
	}
	//sub method for assessing orientation
	private static boolean upside(int r, int c)
	{
		return ((r%2 == 0 && c%2 == 0) || (r%2 != 0 && c%2 != 0));
	}
		
	//Rectangle main
	public static void getNeighbors(List<List<CellModel>> grid, Rectangle r, String nebtype, String gridtype)
	{
		decideNebs(nebtype, r);
		if(gridtype.equals("toroidal"))
			getNeighborsToroidal(grid, r);
		else
			getNeighborsRegular(grid, r);
	}
	// sub method for toroidal
	public static void getNeighborsToroidal(List<List<CellModel>> grid, Rectangle rec)
	{
		int size=grid.get(0).size();
		for(int r=0; r<grid.get(0).size(); r++)
			for(int c=0; c<grid.get(0).size(); c++)
				for(int a=0; a<rs.length; a++)
						if(rs[a]!=0||cs[a]!=0) {
							try{
								grid.get(r).get(c).addNeighbor(grid.get((r+rs[a])).get(c+cs[a]));
							}
							catch(IndexOutOfBoundsException obobrow) {
								try {
									grid.get(r).get(c).addNeighbor(grid.get(size-Math.abs(r+rs[a])).get(c+cs[a]));
								}
								catch(IndexOutOfBoundsException obobcol) {								
									try {
										grid.get(r).get(c).addNeighbor(grid.get((r+rs[a])).get(size-Math.abs(c+cs[a])));
									}
									catch(IndexOutOfBoundsException obobcolrow) {
											grid.get(r).get(c).addNeighbor(grid.get(size-Math.abs(r+rs[a])).get(size-Math.abs(c+cs[a])));
									}
								}
							}
						}
	}
	// sub method for standard
	public static void getNeighborsRegular(List<List<CellModel>> grid, Rectangle rec)
	{
		//System.out.print("HELLO");
		int size=grid.get(0).size();
		for(int r=0; r<size; r++)
			for(int c=0; c<size; c++)
				for(int a=0; a<rs.length; a++)
						if(rs[a]!=0||cs[a]!=0)
							try{
								grid.get(r).get(c).addNeighbor(grid.get((r+rs[a])).get(c+cs[a]));
							}
							catch(IndexOutOfBoundsException obobrowcol) {
								//System.out.print("out of bounds");//what do i do here we are just supposed to not add the neighbor
							}
	}
	// sub method to decide between cross and standard orientations
	private static void decideNebs(String nebtype, Rectangle rec)
	{

		if(nebtype.equals("standard")) {
			 rs = rectangle_row_standard;
			 cs = rectangle_col_standard;
		}
		else {
			rs = rectangle_row_cross;
			cs = rectangle_col_cross;
		}
	}
	
	//hexagon main
	public static void getNeighbors(List<List<CellModel>> grid, Hexagon h, String nebtype, String gridtype)
	{
		cs = hexago_col;
		rowindexu = hexagon_up_row;
		rowindexd = hexagon_down_row;
		if(gridtype.equals("toroidal"))
			getNeighborsToroidal(grid, h);
		else
			getNeighborsRegular(grid, h);
	}
	// sub method for toroidal
	public static void getNeighborsToroidal(List<List<CellModel>> grid, Hexagon h)
	{
		int size=grid.get(0).size();
		for(int r=0; r<grid.get(0).size(); r++)
			for(int c=0; c<grid.get(0).size(); c++)
			{
				if(topCell(r,c))
					rs=rowindexu;
				else
					rs=rowindexd;
				for(int a=0; a<rs.length; a++)
						if(rs[a]!=0||cs[a]!=0)
							try{
								grid.get(r).get(c).addNeighbor(grid.get((r+rs[a])).get(c+cs[a]));
							}
							catch(IndexOutOfBoundsException obobrow) {
								try {
									grid.get(r).get(c).addNeighbor(grid.get(size-Math.abs(r+rs[a])).get(c+cs[a]));
								}
								catch(IndexOutOfBoundsException obobcol) {								
									try {
										grid.get(r).get(c).addNeighbor(grid.get((r+rs[a])).get(size-Math.abs(c+cs[a])));
									}
										catch(IndexOutOfBoundsException obobcolrow) {
											grid.get(r).get(c).addNeighbor(grid.get(size-Math.abs(r+rs[a])).get(size-Math.abs(c+cs[a])));
										}
								}
							}
			}
	}
	// sub method for standard
	public static void getNeighborsRegular(List<List<CellModel>> grid, Hexagon h)
	{
		int size=grid.get(0).size();
		for(int r=0; r<grid.get(0).size(); r++)
			for(int c=0; c<grid.get(0).size(); c++)
			{
				if(topCell(r,c))
					rs=rowindexu;
				else
					rs=rowindexd;
				
				for(int a=0; a<rs.length; a++)
						if(rs[a]!=0||cs[a]!=0)
							try{
								grid.get(r).get(c).addNeighbor(grid.get((r+rs[a])).get(c+cs[a]));
							}
							catch(IndexOutOfBoundsException obobrowcol) {
								//what do i do here we are just supposed to not add the neighbor
							}
			}
	}
	// sub method to decide between cross and standard orientations
	public static boolean topCell(int r, int c)
	{
		return (c%2 == 0);
	}
	
}//eof