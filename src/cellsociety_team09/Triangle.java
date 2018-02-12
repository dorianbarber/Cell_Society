package cellsociety_team09;

import javafx.scene.shape.Polygon;

public class Triangle extends Polygon{
	Polygon shape;
	public Triangle(double x, double y, double size, boolean bool){
		if (bool){
			//shape = new Polygon();
			shape = new PointDownTriangle(x,y,size * 2).getTriangle();
		}
		else {
			//shape = new Polygon();
			shape = new PointUpTriangle(x,y,size * 2).getTriangle();
		}
	}
	public Polygon getTriangle(){
		return shape;
	}
}
