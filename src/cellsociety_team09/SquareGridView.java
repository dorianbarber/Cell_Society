package cellsociety_team09;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import simulations.GridModel;

/**
 * Tasked with specifically creating square grid cells. 
 * 
 * @author Liam
 *
 */
public class SquareGridView extends GridView{
	private double gridXPosition;
	private double gridYPosition;
	private double gridSize;
	private ArrayList<Double> proportions;
	private boolean outline = true;
	
	public SquareGridView(double x, double y, double blocksize, double GRIDSIZE){
		gridXPosition = x;
		gridYPosition = y;
		gridSize = GRIDSIZE;
		proportions = new ArrayList<Double>();
	}
	public Group drawBlankGrid(int screenwidth, int screenheight, double blocksize){
		Group retgroup = new Group();
		for (double i = gridXPosition; i < gridXPosition + gridSize; i += blocksize){
			for (double j = gridYPosition; j < gridYPosition + gridSize; j += blocksize){
				Rectangle toAdd = new Rectangle(i, j, blocksize, blocksize);
				toAdd.setFill(Color.ANTIQUEWHITE);
				toAdd.setStroke(Color.BLACK);
				retgroup.getChildren().add(toAdd);
			}
		}
		return retgroup;
	}
	public Group drawRandomGrid(int screenwidth, int screenheight, int blocksize){
		Group retgroup = new Group();
		for (double i = gridXPosition; i < gridXPosition + gridSize; i += blocksize){
			for (double j = gridYPosition; j < gridYPosition + gridSize; j += blocksize){
				Rectangle toAdd = new Rectangle(i, j, blocksize, blocksize);
				if (new Random().nextInt(3) == 0){
					toAdd.setFill(Color.BLUE);
				}
				else if (new Random().nextInt(3) == 1){
					toAdd.setFill(Color.RED);
				}
				else if (new Random().nextInt(3) == 2){
					toAdd.setFill(Color.GREEN);
				}
				else{
					toAdd.setFill(Color.YELLOWGREEN);
				}
				toAdd.setStroke(Color.BLACK);
				retgroup.getChildren().add(toAdd);
			}
		}
		return retgroup;
	}
	public Group drawGrid(GridModel grid, int screenwidth, int screenheight, double blocksize){
		
		HashMap<Paint,Integer> proportionmap = new HashMap<Paint,Integer>();
		Group retgroup = new Group();
		int x = 0, y = 0;
		for (double i = gridXPosition; i < gridXPosition + gridSize - .5; i += blocksize){
			for (double j = gridYPosition; j < gridYPosition + gridSize - .5; j += blocksize){
				Rectangle toAdd = new Rectangle(i, j, blocksize, blocksize);
				//System.out.println("X: " + x + " Y: " + y);
				//sSystem.out.println("I: " + i + " J: " + j);
				//System.out.println(grid.getCells().get(x).get(y).getColor().toString());
				toAdd.setFill(grid.getCells().get(x).get(y).getColor());
				if (!proportionmap.containsKey(toAdd.getFill())){
					proportionmap.put(toAdd.getFill(), 1);
				}
				else {
					proportionmap.put(toAdd.getFill(), proportionmap.get(toAdd.getFill()) + 1);
				}
				if (outline){
					toAdd.setStroke(Color.BLACK);
				}
				int xtemp = x;
				int ytemp = y;
				toAdd.setOnMouseClicked(e -> handleClick(xtemp,ytemp,grid, toAdd));
				retgroup.getChildren().add(toAdd);
				y++;
			}
			x++;
			y = 0;
		}
		int sum = 0;
		for (Paint key : proportionmap.keySet()){
			sum += proportionmap.get(key);
		}
		for (Paint key : proportionmap.keySet()){
			proportions.add((double) proportionmap.get(key) / sum);
		}
		return retgroup;
	}
	public ArrayList<Double> getProportions(){
		return proportions;
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
