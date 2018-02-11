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
	
	public Group drawGrid(GridModel grid, int screenwidth, int screenheight, double blocksize){
		Group retgroup = new Group();
		int x = 0, y = 0;
		boolean bool = true;
		for (double i = gridXPosition; i < (gridXPosition + gridSize - 2 * blocksize); i += 2 * blocksize){
			bool = i % 0 != 0;
			for (double j = gridYPosition; j < gridYPosition + gridSize - .5; j += blocksize){
				Polygon toAdd = new Triangle(i, j, blocksize, bool).getTriangle();
				
				//System.out.println("X: " + x + " Y: " + y);
				//System.out.println("I: " + i + " J: " + j);
				toAdd.setFill(grid.getCells().get(x).get(y).getColor());
				//System.out.println(toAdd.getFill().toString());
				toAdd.setStroke(Color.BLACK);
				int xtemp = x;
				int ytemp = y;
				//toAdd.setOnMouseClicked(e -> handleClick(xtemp,ytemp,g, toAdd));
				retgroup.getChildren().add(toAdd);
				y++;
				bool = !bool;
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
