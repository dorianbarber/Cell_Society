package cellsociety_team09;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Shape;

public class PointUpTriangle{
	private Polygon shape;
	public PointUpTriangle(double x, double y, double size){
		shape = new Polygon();
		shape.getPoints().addAll(new Double[]{
				x,y,
				x - size / 2, y + size,
				x + size / 2, y + size
				
		});
	}
	public Polygon getTriangle(){
		return shape;
	}
}

