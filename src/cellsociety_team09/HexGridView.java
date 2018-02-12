package cellsociety_team09;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Shape;
import simulations.GridModel;

/**
 * Class specifically tasked with creating hexagonal grids. 
 * 
 * @author Liam
 *
 */
public class HexGridView extends GridView {

		private double gridXPosition;
		private double gridYPosition;
		private double gridSize;
		private double gridHeight;
		private boolean outline = true;
		public HexGridView(double x, double y, double blocksize, double GRIDSIZE){
			gridXPosition = x;
			gridYPosition = y;
			gridSize = GRIDSIZE;
			gridHeight = Math.sqrt(3) * gridSize / 2;
		}
		
		public Group drawGrid(GridModel grid, int screenwidth, int screenheight, double width){
			Group retgroup = new Group();
			double hexwidth =  1.4 * width;
			double hexheight = Math.sqrt(3) * hexwidth / 2;
			boolean offset = false;
			int x = 0, y = 0;
			for (double i = gridXPosition - 15; i < ((gridXPosition + gridSize - .5) * .9); i += .75 * hexwidth){
				for (double j = gridYPosition; j < (gridYPosition + gridHeight - .5); j += hexheight){
					Polygon toAdd = new Hexagon(i, j, hexwidth, offset).getShape();
					System.out.println("X: " + x + " Y: " + y);
					//sSystem.out.println("I: " + i + " J: " + j);
					toAdd.setFill(grid.getCells().get(x).get(y).getColor());
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
				offset = !offset;
			}
			return retgroup;
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
		public boolean getOutline(){
			return outline;
		}
		public void setOutline(boolean outline){
			this.outline = outline;
		}
		public double getY(){
			return gridYPosition;
		}
		public double getDimensions(){
			return gridSize;
		}

}
