
package cellsociety_team09;
import com.sun.javafx.geom.Shape;
import java.util.*;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import simulations.CellModel;
import simulations.FireCell;
import simulations.WatorCell;

public class ModelTester extends Application{
	
	private Shape[][] grid; 
	private Stage myStage;
	private Scene myScene;
    public static final int SIZE = 700;
    public static final int GRIDSIZE= 100;
    public static final int FRAMES_PER_SECOND = 60;
    public static final int MILLISECOND_DELAY = 300;
    public static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;
    public static final Paint BACKGROUND = Color.BLACK;
    private ArrayList<ArrayList<CellModel>> gridCells= new ArrayList<ArrayList<CellModel>>();
	private ArrayList<ArrayList<Rectangle>> shapes = new ArrayList<ArrayList<Rectangle>>();
	

	
	
	 	@Override
	    public void start(Stage stage) //called to create stages
	    {	
	    	myStage=stage;
	    	myScene = setupGame(SIZE, SIZE, BACKGROUND);
	        myScene.setOnMouseClicked(e -> handleMouseInput(e.getX(), e.getY()));

	    
	    		
	    	myStage.setScene(myScene);
	    	myStage.setTitle("Test");
	    	myStage.show();
	    	KeyFrame frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY),
	    									e -> step(SECOND_DELAY));
			Timeline animation = new Timeline();
			animation.setCycleCount(Timeline.INDEFINITE);
			animation.getKeyFrames().add(frame);
			animation.play();
	    }
		//Image endscreen = new Image(getClass().getClassLoader().getResourceAsStream("YouWin.png")); 
		//Image gameover = new Image(getClass().getClassLoader().getResourceAsStream("GameOver.png"));
	 	 public void step(double d)
		 {
	 		for(int i = 0; i < GRIDSIZE; i++) {
				for(int j = 0; j < GRIDSIZE; j++) {
					gridCells.get(i).get(j).findNextState();
				}
			}
    		for(int i = 0; i < GRIDSIZE; i++) {
				for(int j = 0; j < GRIDSIZE; j++) {
					gridCells.get(i).get(j).moveForward(gridCells);
				}
			}
    		int q=0; 
	    	int r=0;
	    	int w=0;
	    	int g=0;
	    	for(int a=0; a<GRIDSIZE; a++)
	    		for(int b=0; b<GRIDSIZE; b++)
	    		{
	    			if(gridCells.get(a).get(b).getStates()[0]==1)
	    				q++;
	    			if(gridCells.get(a).get(b).getStates()[0]==2)
	    				r++;
	    			if(gridCells.get(a).get(b).getStates()[0]==3)
	    				w++;
	    			if(gridCells.get(a).get(b).getStates()[0]==4)
	    				g++;
	    			
	    			shapes.get(a).get(b).setFill(gridCells.get(a).get(b).getColor());
	    		}
	    	System.out.println("red"+r+ " blue"+q+" redmoving"+w+" bluemoving"+g);
	    	
    		
    		
		 }
	    
	    private Scene setupGame(int width, int height, Paint background)
	    {
	    	
	    	int ssize=(SIZE/GRIDSIZE)-2;
	    	ImageView sc = new ImageView();
	    	Group root = new Group();
	    	Scene scene = new Scene(root, width, height, background);
	    	
			for(int i = 0; i < GRIDSIZE; i++) {
				for(int j =0 ; j < GRIDSIZE; j++) {
					shapes.add(new ArrayList<Rectangle>());
					shapes.get(i).add(new Rectangle((ssize+1)*i,(ssize+1)*j,ssize,ssize));
					gridCells.add(new ArrayList<CellModel>());
					gridCells.get(i).add(new WatorCell());
					if(Math.random()<.8)
					{
						gridCells.get(i).set(j,new WatorCell(0,2,8,5));
					}
				}
			}

			
			
		
					if(.8>Math.random())
							gridCells.get(i).set(j,new FireCell(2,70));
					
					else gridCells.get(i).set(j,new FireCell(0,70));
					
						
				}
			}

			for(int a=0; a<GRIDSIZE; a++)
	    		for(int b=0; b<GRIDSIZE; b++)
	    		{
	    			gridCells.get(a).get(b).getNeighbors(a, b, gridCells);
	    		}
			for(int a=0; a<GRIDSIZE; a++)
	    		for(int b=0; b<GRIDSIZE; b++)
	    		{
	    			shapes.get(a).get(b).setFill(gridCells.get(a).get(b).getColor());
	    		}
	    	
	    	for(int a=0; a<GRIDSIZE; a++)
	    		for(int b=0; b<GRIDSIZE; b++)
	    			root.getChildren().add(shapes.get(a).get(b));
	    	return scene;
	    }
	    
	   
	   
	    
	   
	    public static void main (String[] args) {
	        launch(args);
	    }
	    
	   
	   
	    
	    private void handleMouseInput(double x, double y) //handles navigating out of splash screen and cheat
	    {
	    	if(x<200) {
		    	
	    		for(int i = 0; i < GRIDSIZE; i++) {
					for(int j = 0; j < GRIDSIZE; j++) {
						gridCells.get(i).get(j).findNextState();
					}
				}
	    		for(int i = 0; i < GRIDSIZE; i++) {
					for(int j = 0; j < GRIDSIZE; j++) {
						gridCells.get(i).get(j).moveForward(gridCells);
					}
				}
		    	
				
	    	}
	    	else {
	    		for(int i = 0; i < GRIDSIZE; i++) {
					for(int j = 0; j < GRIDSIZE; j++) {
						gridCells.get(i).get(j).moveBackward();
					}
				}
	    	}
	    	int q=0; 
	    	int r=0;
	    	int w=0;
	    	int g=0;
	    	for(int a=0; a<GRIDSIZE; a++)
	    		for(int b=0; b<GRIDSIZE; b++)
	    		{
//	    			if(gridCells.get(a).get(b).getStates()[0]==1)
//	    				q++;
//	    			if(gridCells.get(a).get(b).getStates()[0]==2)
//	    				r++;
//	    			if(gridCells.get(a).get(b).getStates()[0]==3)
//	    				w++;
//	    			if(gridCells.get(a).get(b).getStates()[0]==4)
//	    				g++;
	    			
	    			shapes.get(a).get(b).setFill(gridCells.get(a).get(b).getColor());
	    		}
	    	System.out.println("red"+r+ " blue"+q+" redmoving"+w+" bluemoving"+g);
	    	
	    }
	   
	    
	    
	
	
	
	
	
	
	
	
	    
}

