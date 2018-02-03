package cellsociety_team09;

import java.util.ArrayList;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Slider;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Menu extends Application{
	
	private Stage myStage;
	private Scene myScene;
	private Group myRoot;
	
	private final int WIDTH = 900;
	private final int HEIGHT = 600;
	private final Color BACKGROUND = Color.ANTIQUEWHITE;
	private Grid myGrid;
	
	
    /**
     * Start the program.
     */
    public static void main (String[] args) {
        launch(args);
    }

	@Override
	public void start(Stage stage) {
		myScene = initializeStart(WIDTH, HEIGHT, BACKGROUND);	
		myStage = stage;
		myStage.setScene(myScene);
		myStage.show();
	}
	
	private Scene initializeStart(int screenwidth, int screenheight, Color paint){
		Group root = new Group();
		myRoot = root;
		myGrid = new Grid(20, .05 * screenheight, 10, 500);
		Scene scene = new Scene(root, screenwidth, screenheight, paint);
		root.getChildren().add(myGrid.drawBlankGrid(screenwidth, screenheight));
		Slider speedtoggle = new Slider();
		speedtoggle.setLayoutY(myGrid.getY() + myGrid.getDimensions() + 5); 
		speedtoggle.setMinWidth(300);
		speedtoggle.setMaxWidth(300);
		speedtoggle.setLayoutX(myGrid.getX() + myGrid.getDimensions() / 2 - speedtoggle.getWidth());
		root.getChildren().add(speedtoggle);
		
		return scene;
	}
	
}
