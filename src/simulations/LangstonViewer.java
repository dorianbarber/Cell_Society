package simulations;

import java.util.ArrayList;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.util.*;

public class LangstonViewer extends Application {
	

	
	
		
	private final int SIZE=700;
	private final int gridblocks=70;
	private final int gridsize=SIZE/gridblocks;
	private Stage myStage;
	private Scene myScene;
	public static final int FRAMES_PER_SECOND = 60;
	public static final int MILLISECOND_DELAY = 5000;
    public static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;

	
	ArrayList<ArrayList<Rectangle>> model = new ArrayList<ArrayList<Rectangle>>();
	LangstonGrid theg = new LangstonGrid(gridblocks);
	
	public void step()
	{
		theg.update();
		theg.moveForward();
		updateColors();
	}
	
	
	
    @Override
    public void start(Stage stage) //called to create stages
    {	
    	myStage=stage;
    	myScene = setupGame(SIZE, SIZE, Color.WHITE);
    	
    		
    	myStage.setScene(myScene);
    	myStage.setTitle("lView");
    	myStage.show();
    	KeyFrame frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY),
    									e -> step());
		Timeline animation = new Timeline();
		animation.setCycleCount(Timeline.INDEFINITE);
		animation.getKeyFrames().add(frame);
		animation.play();
    }
    
    private Scene setupGame(int width, int height, Paint background)
    {
    	for(int i = 0; i < gridblocks; i++) {
			model.add(new ArrayList<Rectangle>());
			for(int j = 0; j < gridblocks; j++) {
				model.get(i).add(new Rectangle());
			}
		}
    	
    	
    	Group root = new Group();
    	Scene scene = new Scene(root, width, height, background);
    	List<List<CellModel>> grid = theg.getCells();
    	for(int r=0; r<gridblocks; r++)
    	{
    		for(int c=0; c<gridblocks; c++)
    		{    		
    			model.get(r).get(c).setX(c*(gridsize+.5));
    			model.get(r).get(c).setY(r*(gridsize+.5));
    			model.get(r).get(c).setHeight(gridsize);
    			model.get(r).get(c).setWidth(gridsize);
    			model.get(r).get(c).setFill(grid.get(r).get(c).getColor());
    			root.getChildren().add(model.get(r).get(c));
    			
    		}
    	}
    	
    	return scene;
    }
    
    public void updateColors()
    {
    	List<List<CellModel>> grid = theg.getCells();
    	for(int r=0; r<gridblocks; r++)
    	{
    		for(int c=0; c<gridblocks; c++)
    		{    		
    			model.get(r).get(c).setFill(grid.get(r).get(c).getColor());    			
    		}
    	}
    	
    }
    
    public static void main (String[] args) {
        launch(args);
    }
}
