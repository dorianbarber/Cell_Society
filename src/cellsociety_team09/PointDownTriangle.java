package cellsociety_team09;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Shape;

public class PointDownTriangle{
	private Polygon shape;
	public PointDownTriangle(double x, double y, double size){
		shape = new Polygon();
		shape.getPoints().addAll(new Double[]{
				x, y + size/2,
				x + size/2, y - size/2,
				x - size/2, y - size/2
		});
	}
	public Polygon getTriangle(){
		return shape;
	}
}
