    package cellsociety_team09;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Menu extends Application{
	
	private Stage myStage;
	private Scene myScene;
	private Group myRoot;
	
	private final int WIDTH = 700;
	private final int HEIGHT = 600;
	private final int GRIDSIZE = 500;
	private final int GRIDX = WIDTH / 20; 
	private final int GRIDY = HEIGHT / 20;
	private final Color BACKGROUND = Color.ANTIQUEWHITE;
	private final int SLIDEROFFSET = 10;
	private final double BUTTONOFFSET = 2.5 * SLIDEROFFSET;
	private final int DROPOFFSET = 15;
	private GridView myGrid;
	private int blocksize;
	
	
    /**
     * Start the program.
     */
    public static void main (String[] args) {
        launch(args); 
    }

	@Override
	public void start(Stage stage) {
		myScene = initializeStart (WIDTH, HEIGHT, BACKGROUND);	
		myStage = stage;
		myStage.setScene(myScene);
		myStage.show();
	}
	
	private Scene initializeStart(int screenwidth, int screenheight, Color paint){
		blocksize = 10;
		Group root = new Group();
		myRoot = root;
		myGrid = new GridView(GRIDX, GRIDY, blocksize, GRIDSIZE);
		Scene scene = new Scene(root, screenwidth, screenheight, paint);
		root.getChildren().add(myGrid.drawBlankGrid(screenwidth, screenheight, blocksize));
		root.getChildren().add(getAnimationSpeedSlider());
		root.getChildren().add(getPlayButton());
		root.getChildren().add(getPauseButton());
		root.getChildren().add(getMenu());
		root.getChildren().add(getText());
		
		return scene;
	}
	private Slider getAnimationSpeedSlider(){
		Slider speedtoggle = new Slider();
		speedtoggle.setLayoutY(myGrid.getY() + myGrid.getDimensions() + SLIDEROFFSET); 
		speedtoggle.setMinWidth(300);
		speedtoggle.setMaxWidth(300);
		speedtoggle.setLayoutX(myGrid.getX() + myGrid.getDimensions() / 2 - speedtoggle.getMaxWidth() / 2);
		return speedtoggle;
	}
	private Button getPlayButton(){
		Image play = new Image(getClass().getResourceAsStream("../playicon.png"), 30, 30, false, false);
		Button playbutton = new Button("", new ImageView(play));
		playbutton.setLayoutX(myGrid.getX() + myGrid.getDimensions() / 2 - 3 * BUTTONOFFSET);
		playbutton.setLayoutY(myGrid.getY() + myGrid.getDimensions() + BUTTONOFFSET);
		return playbutton;
	}
	private Button getPauseButton(){
		
		Image play = new Image(getClass().getResourceAsStream("../pauseicon.png"), 30, 30, false, false);
		Button playbutton = new Button("", new ImageView(play));
		playbutton.setLayoutX(myGrid.getX() + myGrid.getDimensions() / 2 + BUTTONOFFSET);
		playbutton.setLayoutY(myGrid.getY() + myGrid.getDimensions() + BUTTONOFFSET);
		return playbutton;
	}
	private ComboBox<String> getMenu(){
		ObservableList<String> options = 
			    FXCollections.observableArrayList(
			        "Game of Life",
			        "Spreading Fire",
			        "Segregation",
			        "Wa-Tor World"
			    );
		ComboBox<String> combobox = new ComboBox<String>(options);
		combobox.setLayoutX(GRIDSIZE + GRIDX + DROPOFFSET);
		combobox.setLayoutY(2 * GRIDY);
		return combobox;
	}
	private Group getText(){
		Text description = new Text();
		description.setLayoutX(GRIDSIZE + GRIDX + DROPOFFSET / 2);
		description.setLayoutY(3.8 * GRIDY);
		description.setText("Testing, testing, one two three"
				+ "Still testing keep it up great job everyone here we will "
				+ "store the incredible description of our game");
		description.setWrappingWidth(WIDTH - description.getLayoutX() - 5);
		description.setFill(Color.BLACK);
		Rectangle background = new Rectangle(description.getLayoutX() - 3, description.getLayoutY() - 15, 158, 300);
		background.setStroke(Color.BLACK);
		background.setFill(Color.TRANSPARENT);
		Group retroot = new Group();
		retroot.getChildren().add(description);
		retroot.getChildren().add(background);
		
		return retroot;
	}
}

