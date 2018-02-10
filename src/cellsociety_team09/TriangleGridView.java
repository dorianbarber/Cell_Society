package cellsociety_team09;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

public class TriangleGridView extends GridView {
	private double gridXPosition;
	private double gridYPosition;
	private double gridBlockSize;
	private double gridSize;
	
	public TriangleGridView(double x, double y, double blocksize, double gridSize){
		gridXPosition = x;
		gridYPosition = y;
		gridBlockSize = blocksize;
		this.gridSize = gridSize;
	}
	
	public Group drawGrid(Grid g, int screenwidth, int screenheight, double blocksize){
		Group retgroup = new Group();
		int x = 0, y = 0;
		boolean bool = false;
		blocksize *= 2;
		int counter = 0;
		for (double i = gridXPosition; i < gridXPosition + gridSize; i += blocksize){
			
			if (counter == 1){
				i -= blocksize;
			}
			else if (counter == 2){
				counter = 0;
			}
			for (double j = gridYPosition - 30; j < gridYPosition + gridSize - .5 - 30; j += blocksize){
				Polygon toAdd = new Triangle(i, j, blocksize, bool).getTriangle();
				
				//System.out.println("X: " + x + " Y: " + y);
				//System.out.println("I: " + i + " J: " + j);
				toAdd.setFill(g.getCells().get(x).get(y).getColor());
				//System.out.println(toAdd.getFill().toString());
				toAdd.setStroke(Color.BLACK);
				int xtemp = x;
				int ytemp = y;
				//toAdd.setOnMouseClicked(e -> handleClick(xtemp,ytemp,g, toAdd));
				retgroup.getChildren().add(toAdd);
				y++;
			}
			counter++;
			x++;
			y = 0;
			bool = !bool;
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
