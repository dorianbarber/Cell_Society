package cellsociety_team09;

import java.util.ArrayList;

import javafx.scene.Group;
import simulations.GridModel;

/**
 * Abstract class for drawing the grid in many different shapes. 
 * 
 * @author Liam
 *
 */
public abstract class GridView {
	private double gridXPosition;
	private double gridYPosition;
	private int gridSize;
	public GridView(){
	}
	public Group drawBlankGrid(int screenwidth, int screenheight, int blocksize){
		return new Group();
	}
	public Group drawRandomGrid(int screenwidth, int screenheight, int blocksize){
		return new Group();
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
	public Group drawGrid(GridModel grid, int wIDTH, int hEIGHT, double blocksize) {
		// TODO Auto-generated method stub
		return null;
	}
	public ArrayList<Double> getProportions() {
		// TODO Auto-generated method stub
		return null;
	}
	public boolean getOutline() {
		// TODO Auto-generated method stub
		return false;
	}
	public void setOutline(boolean b) {
	}
}
