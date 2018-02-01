package cellsociety_team09;

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
import javafx.stage.Stage;
import javafx.util.Duration;
import java.util.Random;

public class Menu extends Application{
	
	private Stage myStage;
	private Scene myScene;
	private Group myRoot;
	
	private final int WIDTH = 900;
	private final int HEIGHT = 600;
	private final Color BACKGROUND = Color.ANTIQUEWHITE;
	
	
	
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
		Scene scene = new Scene(root, screenwidth, screenheight, paint);
		
		return scene;
	}
}
