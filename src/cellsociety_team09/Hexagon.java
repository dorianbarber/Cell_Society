package cellsociety_team09;

import javafx.scene.shape.Polygon;

public class Hexagon extends Polygon{
	private double hexwidth;
	private double hexheight;
	private Polygon shape;
	public Hexagon(double x, double y, double width, boolean offset){
		hexwidth = width;
		hexheight = Math.sqrt(3) * width / 2;
		shape = new Polygon();
		if (!offset){
			shape.getPoints().addAll(new Double[]{
					x, y + .5 * hexheight,
					x + .25 * hexwidth, y,
					x + .75 * hexwidth, y,
					x + hexwidth, y + .5 * hexheight,
					x + .75 * hexwidth, y + hexheight,
					x + .25 * hexwidth, y + hexheight
					
			});
		}
		else {
			shape.getPoints().addAll(new Double[]{
					x, y + .5 * hexheight - .5 * hexheight,
					x + .25 * hexwidth, y - .5 * hexheight,
					x + .75 * hexwidth, y - .5 * hexheight,
					x + hexwidth, y + .5 * hexheight - .5 * hexheight,
					x + .75 * hexwidth, y + hexheight - .5 * hexheight,
					x + .25 * hexwidth, y + hexheight - .5 * hexheight
					
			});
		}
	}
	public Polygon getShape(){
		return shape;
	}
}
