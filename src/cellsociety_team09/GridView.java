package cellsociety_team09;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class GridView {
	private double gridXPosition;
	private double gridYPosition;
	private int gridBlockSize;
	private int gridSize;
	
	public GridView(double x, double y, int blocksize, int gridsize){
		gridXPosition = x;
		gridYPosition = y;
		gridSize = gridsize;
		gridBlockSize = blocksize;
	}
	public Group drawBlankGrid(int screenwidth, int screenheight, int blocksize){
		Group retgroup = new Group();
		for (double i = gridXPosition; i < gridXPosition + gridSize; i += gridBlockSize){
			for (double j = gridYPosition; j < gridYPosition + gridSize; j += gridBlockSize){
				Rectangle toAdd = new Rectangle(i, j, blocksize, blocksize);
				toAdd.setFill(Color.ANTIQUEWHITE);
				toAdd.setStroke(Color.BLACK);
				retgroup.getChildren().add(toAdd);
			}
		}
		return retgroup;
	}
	public double getX(){
		return gridXPosition;
	}
	public double getY(){
		return gridYPosition;
	}
	public int getDimensions(){
		return gridSize;
	}
}
