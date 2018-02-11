package cellsociety_team09;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import simulations.GridModel;

public class HexGridView extends GridView {

		private double gridXPosition;
		private double gridYPosition;
		private double gridBlockSize;
		private double gridSize;
		private double gridHeight;
		
		public HexGridView(double x, double y, double blocksize, double GRIDSIZE){
			gridXPosition = x;
			gridYPosition = y;
			gridSize = GRIDSIZE;
			gridBlockSize = blocksize;
			gridHeight = Math.sqrt(3) * gridSize / 2;
		}
		
		public Group drawGrid(GridModel grid, int screenwidth, int screenheight, double width){
			Group retgroup = new Group();
			double hexwidth = 3/2 * width;
			double hexheight = Math.sqrt(3) * hexwidth / 2;
			boolean offset = false;
			int x = 0, y = 0;
			for (double i = gridXPosition; i < ((gridXPosition + gridSize - .5) * .75) * (3/2); i += .75 * hexwidth){
				for (double j = gridYPosition; j < (gridYPosition + gridHeight - .5) * (3/2); j += hexheight){
					Polygon toAdd = new Hexagon(i, j, hexwidth, offset).getShape();
					//System.out.println("X: " + x + " Y: " + y);
					//sSystem.out.println("I: " + i + " J: " + j);
					toAdd.setFill(grid.getCells().get(x).get(y).getColor());
					toAdd.setStroke(Color.BLACK);
					int xtemp = x;
					int ytemp = y;
					toAdd.setOnMouseClicked(e -> handleClick(xtemp,ytemp,grid, toAdd));
					retgroup.getChildren().add(toAdd);
					y++;
				}
				x++;
				y = 0;
				offset = !offset;
			}
			return retgroup;
		}
		
		private void handleClick(int x, int y, Grid g, Shape n) {
			List<Integer> list = new ArrayList<>();
			list.add(1);
			list.add(70);
			g.getCells().get(x).get(y).getInput(list);
			//n.setFill(g.getCells().get(x).get(y).getKind().getColor());
			g.findCellNeighbors();
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
