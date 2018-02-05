package cellsociety_team09;

import java.util.Random;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class SquareGridView {
	private double gridXPosition;
	private double gridYPosition;
	private double gridBlockSize;
	private double gridSize;
	
	public SquareGridView(double x, double y, double blocksize, double GRIDSIZE){
		gridXPosition = x;
		gridYPosition = y;
		gridSize = GRIDSIZE;
		gridBlockSize = blocksize;
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
	public Group drawGrid(Grid g, int screenwidth, int screenheight, double blocksize){
		Group retgroup = new Group();
		int x = 0, y = 0;
		for (double i = gridXPosition; i < gridXPosition + gridSize - .5; i += blocksize){
			for (double j = gridYPosition; j < gridYPosition + gridSize - .5; j += blocksize){
				Rectangle toAdd = new Rectangle(i, j, blocksize, blocksize);
				//System.out.println("X: " + x + " Y: " + y);
				//sSystem.out.println("I: " + i + " J: " + j);
				toAdd.setFill(g.getCells().get(x).get(y).getColor());
				toAdd.setStroke(Color.BLACK);
				retgroup.getChildren().add(toAdd);
				y++;
			}
			x++;
			y = 0;
		}
		return retgroup;
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
