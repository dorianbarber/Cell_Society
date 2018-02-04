package cellsociety_team09;

import java.util.Random;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class SquareGridView {
	private double gridXPosition;
	private double gridYPosition;
	private int gridBlockSize;
	private int gridSize;
	
	public SquareGridView(double x, double y, int blocksize, int gridsize){
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
	public Group drawRandomGrid(int screenwidth, int screenheight, int blocksize){
		Group retgroup = new Group();
		for (double i = gridXPosition; i < gridXPosition + gridSize; i += gridBlockSize){
			for (double j = gridYPosition; j < gridYPosition + gridSize; j += gridBlockSize){
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
