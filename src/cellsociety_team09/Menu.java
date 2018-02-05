   package cellsociety_team09;

import java.util.Random;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Node;
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
import javafx.util.Duration;

public class Menu extends Application{
	
	private Stage myStage;
	private Scene myScene;
	private Group myRoot;
	
	private Rectangle test = new Rectangle(30,30);;
	
	public static final int FRAMES_PER_SECOND = 1;
	public static final int MILLISECOND_DELAY = 1000 / FRAMES_PER_SECOND;

	//public static final double SECOND_DELAY = 6.0 / FRAMES_PER_SECOND;
	private Timeline animation;
	
	private final int WIDTH = 700;
	private final int HEIGHT = 600;
	private final int GRIDSIZE = 500;
	private final int GRIDX = WIDTH / 20; 
	private final int GRIDY = HEIGHT / 20;
	private final Color BACKGROUND = Color.ANTIQUEWHITE;
	private final int SLIDEROFFSET = 6;
	private final double BUTTONVERTOFFSET = 25;
	private final double BUTTONHOROFFSET = 45;
	private final int DROPOFFSET = 15;
	private final int SLIDERSIZE = 300;
	private final int BUTTONSIZE = 30;
	private SquareGridView myGrid;
	private int blocksize;
	private double stepincrement = FRAMES_PER_SECOND;
	private double sliderx;
	private Group gridgroup;
	private Grid grid;
	private ComboBox<String> myBox;
	private boolean happened = true;
	
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
		
		animate();
	}
	public void animate(){
		// attach "game loop" to timeline to play it
		KeyFrame frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY), e -> step(stepincrement));
		animation = new Timeline();
		animation.setCycleCount(Timeline.INDEFINITE);
		animation.getKeyFrames().add(frame);
	}
	private void step(double elapsedTime) {
		
		myRoot.getChildren().remove(gridgroup);
		gridgroup = myGrid.drawGrid(grid, WIDTH, HEIGHT, blocksize);
		myRoot.getChildren().add(gridgroup);
		grid.moveSimulationForward();
	}
	
	private Scene initializeStart(int screenwidth, int screenheight, Color paint){
		blocksize = GRIDSIZE / 10;
		Group root = new Group();
		myRoot = root;
		myGrid = new SquareGridView(GRIDX, GRIDY, blocksize, GRIDSIZE);
		grid = new Grid(10,0);
		Scene scene = new Scene(root, screenwidth, screenheight, paint);
		gridgroup = myGrid.drawBlankGrid(screenwidth, screenheight, blocksize);
		root.getChildren().add(gridgroup);
		root.getChildren().add(getAnimationSpeedSlider());
		root.getChildren().add(getPlayButton());
		root.getChildren().add(getPauseButton());
		root.getChildren().add(getStepForwardButton());
		myBox = getMenu();
		root.getChildren().add(myBox);
		root.getChildren().add(getText());
		root.getChildren().add(getBackStepButton());
		return scene;
	}
	private Slider getAnimationSpeedSlider(){
		Slider speedtoggle = new Slider();
		speedtoggle.setLayoutY(myGrid.getY() + myGrid.getDimensions() + SLIDEROFFSET); 
		speedtoggle.setMinWidth(SLIDERSIZE);
		speedtoggle.setMaxWidth(SLIDERSIZE);
		speedtoggle.setMin(1);
		speedtoggle.setMax(10);
		speedtoggle.setLayoutX(myGrid.getX() + myGrid.getDimensions() / 2 - speedtoggle.getMaxWidth() / 2);
		sliderx = speedtoggle.getLayoutX();
		speedtoggle.valueProperty().addListener(new ChangeListener<Number>() {
		    @Override
		    public void changed(ObservableValue<? extends Number> observable,
		            Number oldValue, Number newValue) {
		    	stepincrement = (double) newValue;
		    	animation.setRate(stepincrement * FRAMES_PER_SECOND);
		    }
		});
		return speedtoggle;
	}

	private Button getBackStepButton(){
		
		Image play = new Image(getClass().getResourceAsStream("../pauseicon.png"), 30, 30, false, false);
		Button pausebutton = new Button("", new ImageView(play));
		pausebutton.setLayoutX(sliderx + 13.5);
		pausebutton.setLayoutY(myGrid.getY() + myGrid.getDimensions() + BUTTONVERTOFFSET);
		pausebutton.setOnAction(e -> getPauseAction());
		return pausebutton;
	}
//	private void getBackStep() {
//		animation.pause();
//		animation.setRate(-1 * animation.getRate());
//		animation.`	
//		animation.setRate(-1 * animation.getRate());
//	}

	private Button getPlayButton(){
		Image play = new Image(getClass().getResourceAsStream("../playicon.png"), BUTTONSIZE, BUTTONSIZE, false, false);
		Button playbutton = new Button("", new ImageView(play));
		playbutton.setLayoutX(sliderx + BUTTONHOROFFSET + BUTTONSIZE + 13.5);
		playbutton.setLayoutY(myGrid.getY() + myGrid.getDimensions() + BUTTONVERTOFFSET);
		playbutton.setOnAction(e -> getPlayAction());
		return playbutton;
	}
	private Button getPauseButton(){
		
		Image play = new Image(getClass().getResourceAsStream("../pauseicon.png"), 30, 30, false, false);
		Button pausebutton = new Button("", new ImageView(play));
		pausebutton.setLayoutX(sliderx + 2 * BUTTONHOROFFSET + 2 * BUTTONSIZE + 13.5);
		pausebutton.setLayoutY(myGrid.getY() + myGrid.getDimensions() + BUTTONVERTOFFSET);
		pausebutton.setOnAction(e -> getPauseAction());
		return pausebutton;
	}
	private Button getStepForwardButton() {
		Image play = new Image(getClass().getResourceAsStream("../playicon.png"), BUTTONSIZE, BUTTONSIZE, false, false);
		Button stepforwardbutton = new Button("", new ImageView(play));
		stepforwardbutton.setLayoutX(sliderx + 3 * BUTTONHOROFFSET + 3 * BUTTONSIZE + 13.5);
		stepforwardbutton.setLayoutY(myGrid.getY() + myGrid.getDimensions() + BUTTONVERTOFFSET);
		stepforwardbutton.setOnAction(e -> getStepForward());
		return stepforwardbutton;
	}

	private void getStepForward() {
		animation.pause();
		step(stepincrement);
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
		//combobox.setValue("Game of Life");
		combobox.valueProperty().addListener((option, oldvalue, newvalue) -> {
			handleBoxInput(oldvalue, newvalue);
		});
		
		return combobox;
	}
	private void handleBoxInput(String oldvalue, String newvalue) {
		if (happened) {
			if (oldvalue == null) {
				oldvalue = "";
			}
			if (oldvalue.equals(newvalue)) {
				return;
			}
			if (newvalue.equals("Game of Life")) {
				myScene = initializeStart(WIDTH, HEIGHT, BACKGROUND);
				myStage.setScene(myScene);
				myStage.show();
				happened = false;
				myBox.setValue("Game of Life");
			} else {
				myScene = initializeSegregation(WIDTH, HEIGHT, BACKGROUND);
				myStage.setScene(myScene);
				myStage.show();
			} 
		}
		happened = true;
	}

	private Scene initializeSegregation(int screenwidth, int screenheight, Color background) {
		blocksize = 10;
		Group root = new Group();
		myRoot = root;
		myGrid = new SquareGridView(GRIDX, GRIDY, blocksize, GRIDSIZE);
		grid = new Grid(125,0);
		Scene scene = new Scene(root, screenwidth, screenheight, background);
		gridgroup = myGrid.drawBlankGrid(screenwidth, screenheight, blocksize);
		root.getChildren().add(gridgroup);
		root.getChildren().add(getAnimationSpeedSlider());
		root.getChildren().add(getPlayButton());
		root.getChildren().add(getPauseButton());
		root.getChildren().add(getStepForwardButton());
		myBox = getMenu();
		root.getChildren().add(myBox);
		root.getChildren().add(getText());
		root.getChildren().add(getBackStepButton());
		
		return scene;
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
	private void getPlayAction(){
		animation.play();
	}
	private void getPauseAction(){
		animation.pause();
	}
}

