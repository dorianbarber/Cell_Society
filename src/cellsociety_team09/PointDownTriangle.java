package cellsociety_team09;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Shape;

public class PointDownTriangle{
	private Polygon shape;
	public PointDownTriangle(double x, double y, double size){
		shape = new Polygon();
		shape.getPoints().addAll(new Double[]{
				x + size / 2, y + size,
				x, y,
				x + size, y
		});
	}
	public Polygon getTriangle(){
		return shape;
	}
}
