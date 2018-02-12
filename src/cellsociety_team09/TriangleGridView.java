package cellsociety_team09;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Shape;
import simulations.GridModel;

/**
 * Tasked with creating triangle shaped grids. 
 * 
 * @author Liam
 *
 */
public class TriangleGridView extends GridView {
	private double gridXPosition;
	private double gridYPosition;
	private double gridSize;
	private boolean outline = true;
	
	public TriangleGridView(double x, double y, double blocksize, double gridSize){
		gridXPosition = x;
		gridYPosition = y;
		this.gridSize = gridSize;
	}
	
	public Group drawGrid(GridModel grid, int screenwidth, int screenheight, double blocksize){
		Group retgroup = new Group();
		int x = 0, y = 0;
		boolean bool = true;
		for (double i = gridXPosition; i < (gridXPosition + gridSize - 2 * blocksize); i += 2 * blocksize){
			bool = true;
			for (double j = gridYPosition; j < gridYPosition + gridSize; j += blocksize){
				Polygon toAdd = new Triangle(i, j, blocksize, bool).getTriangle();
				
				//System.out.println("X: " + x + " Y: " + y);
				//System.out.println("I: " + i + " J: " + j);
				toAdd.setFill(grid.getCells().get(x).get(y).getColor());
				//System.out.println(toAdd.getFill().toString());
				if (outline){
					toAdd.setStroke(Color.BLACK);
				}
				int xtemp = x;
				int ytemp = y;
				toAdd.setOnMouseClicked(e -> handleClick(xtemp,ytemp, grid , toAdd));
				retgroup.getChildren().add(toAdd);
				y++;
				bool = !bool;
			}
			x++;
			y = 0;
		}
		return retgroup;
	}
	public void setOutline(boolean outline){
		this.outline = outline;
	}
	public boolean getOutline(){
		return outline;
	}
	private void handleClick(int x, int y, GridModel g, Shape n) {
		List<Integer> list = new ArrayList<>();
		list.add(x);
		list.add(y);
		//g.getCells().get(x).get(y).getInput(list);
		//System.out.println(g.getCells().get(x).get(y).getState());
		g.getUserInput(list);
		//System.out.println(g.getCells().get(x).get(y).getState());
		n.setFill(g.getCells().get(x).get(y).getColor());
		//System.out.println("Clicked!");
		
	}
	
	public double getX(){
		return gridXPosition;
	}
	public double getY(){
		return gridYPosition;
	}
	public double getDimensions(){
		return gridSize;
	}
}
