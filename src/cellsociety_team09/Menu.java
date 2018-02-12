package cellsociety_team09;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import simulations.AntGrid;
import simulations.FireGrid;
import simulations.GridModel;
import simulations.LifeGrid;
import simulations.RPSGrid;
import simulations.SegregationGrid;
import simulations.WatorGrid;
import xml_related_package.XMLBuilder;
import xml_related_package.XMLManager;

public class Menu extends Application{
	
	private Stage myStage;
	private Scene myScene;
	private Group myRoot;
	
	//private Rectangle test = new Rectangle(30,30);;
	
	public static final int FRAMES_PER_SECOND = 1;
	public static final int MILLISECOND_DELAY = 1000 / FRAMES_PER_SECOND;
	private static final String GOLDESCRIPTION = "resources/gameoflife.txt";
	private static final String FIREDESCRIPTION = "resources/fire.txt";
	private static final String SEGDESCRIPTION = "resources/segregation.txt";
	private static final String WATORDESCRIPTION = "resources/wator.txt";
	private static final String LABELFIELD = "resources/labelfield.txt";
	private static final  int GOLTYPE = 0;
    private static final int FIRETYPE = 1;
    private static final int SEGTYPE = 2;
    private static final int WATORTYPE = 3;
    private static final int ANTTYPE = 4;
    private static final int RPSTYPE = 5;
    private static final int MAXGRIDSIZE = 200;
    private static final int MINGRIDSIZE = 2;
    private static final int BUTTONSIZE = 30;
    private int gridsize = 20;
    LifeGrid a = new LifeGrid();
    private  final GridModel[] POSSIBLEGRIDS = {a, new FireGrid(), new SegregationGrid(), 
    		new WatorGrid(), new AntGrid(), new RPSGrid()}; 
	

	//public static final double SECOND_DELAY = 6.0 / FRAMES_PER_SECOND;
	private Timeline animation;
	
	private final int WIDTH = 700;
	private final int HEIGHT = 700;
	private final double GRIDSIZE = 500;
	private final int GRIDX = WIDTH / 20; 
	private final int GRIDY = HEIGHT / 20;
	private final Color BACKGROUND = Color.ANTIQUEWHITE;
	private final int SLIDEROFFSET = 6;
	private final double BUTTONVERTOFFSET = 25;
	private final double BUTTONHOROFFSET = 45;
	private final int DROPOFFSET = 15;
	private final int SLIDERSIZE = 300;
	private GridView myGrid;
	private double blocksize;
	private double stepincrement = FRAMES_PER_SECOND;
	private double sliderx;
	private Group gridgroup;
	private GridModel grid;
	private ComboBox<String> simBox;
	private ComboBox<String> gridShapeBox;
	private boolean happened = true;
	
	private boolean pushed = true;
	private String currentbox = "Game of Life";
	private String currentshape = "Square";
	private File currentfile;
	private LineChart<Number,Number> linechart; 
	private double time = 0;
	private XYChart.Series series = new XYChart.Series<>();
	
    /**
     * Start the program.
     */
    public static void main (String[] args) {
        launch(args); 
    }
	/* (non-Javadoc)
	 * @see javafx.application.Application#start(javafx.stage.Stage)
	 */
	@Override
	public void start(Stage stage) {
		
		try {
			myScene = initializeStart(WIDTH, HEIGHT, BACKGROUND, POSSIBLEGRIDS[0].getClass().newInstance(), getFile(GOLDESCRIPTION));
		} catch (InstantiationException | IllegalAccessException e) {
			System.out.println("Ya done messed up line 115");
		}	
		myStage = stage;
		myStage.setScene(myScene);
		myStage.show();
		
		animate();
	}
	
	  /**
     * Reads a file in its entirety and returns a string containing the file's contents
     * @param path is the path to the file to be read
     * @param encoding is how the chars should be read in -- Use Charset.DEFAULT
     * @return s new string with the full text of the file
     * @throws IOException
     */
    public String readFile(String path, Charset encoding) throws IOException {
		  byte[] encoded = Files.readAllBytes(Paths.get(path));
		  return new String(encoded, encoding);
    }
    
	/**
	 * Calls readFile to read in the whole text contents of a file
	 * @param filepath is the path to the file to be read
	 * @return is a new string with the contents of that text file
	 * 
	 */
	public String getFile(String filepath){
		String labelfield = "";
		try {
			labelfield = readFile(filepath, Charset.defaultCharset());
		} catch (IOException e) {
			labelfield = "Sorry, file not found";
		}
		return labelfield;
	}
	
	/**
	 *  Sets a new Timeline loop to show the simulation
	 */
	public void animate(){
		KeyFrame frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY), e -> step(stepincrement));
		animation = new Timeline();
		animation.setCycleCount(Timeline.INDEFINITE);
		animation.getKeyFrames().add(frame);
	}
	public Rectangle getShape(String shapename){
		if (shapename.equals("Square")){
			return new Rectangle();
		}
		
		return new Rectangle();
//		else if (shapename.equals("Triangle")){
//			return new Triangle(1,1,1,true);
//		}
//		else{
//			return new Hexagon(3,3,4,false);
//		}
	}
	/**
	 * Moves the simulation forward by updating and redrawing Grid
	 * @param elapsedTime how much time between each step
	 */
	private void step(double elapsedTime) {
		//NeighborFinder finder = new NeighborFinder();
		//NeighborFinder.getNeighbors(grid.getCells(), getShape(currentshape), "standard", "standard");
		grid.setCurrentShape(currentshape);
		grid.update();
		grid.moveForward();
		myRoot.getChildren().remove(gridgroup);
		gridgroup = myGrid.drawGrid(grid, WIDTH, HEIGHT, blocksize);
//		for (double d : myGrid.getProportions()){
//			
//			series.getData().add(new XYChart.Data(time,d));
//			linechart.getData().add(series);
//		}
		time += .1;
		myRoot.getChildren().add(gridgroup);
		
	}

	/**
	 * Resets the grid and the view to their original states
	 */
	private void reset(){
		//System.out.println(grid.getDescription());
		try {
			myScene = initializeStart(WIDTH, HEIGHT, BACKGROUND, POSSIBLEGRIDS[grid.getKind()].getClass().newInstance(), "BOO");
		} catch (InstantiationException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			System.out.println("Error in choosing CellModel type -- refer to Line 194");
		}
		myStage.setScene(myScene);
		myStage.show();
	}
	
	/**
	 * Initializes the simulation with default settings
	 * @param screenwidth = width of the desired screen window
	 * @param screenheight = height of the desired screen window
	 * @param paint = background color of the desired screen window
	 * @param g = The declared Grid 
	 * @param textbox = The description of the simulation
	 * @return = a new Scene containing all of the intialized scene components
	 */
	private Scene initializeStart(int screenwidth, int screenheight, Color paint, GridModel g, String textbox){
		grid = g;
		//grid.setDescription(textbox);
		blocksize = GRIDSIZE / grid.getSize();
		//System.out.println("blocksize = " + blocksize);
		Group root = new Group();
		myRoot = root;
		
		Scene scene = new Scene(root, screenwidth, screenheight, paint);
		//System.out.println(currentshape);
		if (root.getChildren().contains(gridShapeBox)){
			root.getChildren().remove(gridShapeBox);
		}
		gridShapeBox = getGridShape(currentshape);
		root.getChildren().add(gridShapeBox);
		//Also sets myGrid
		//System.out.println(currentshape);
		handleGridShapeInput("", currentshape);	
		//System.out.println(currentshape);
		//root.getChildren().add(getGridShape("Square"));
		root.getChildren().add(getAnimationSpeedSlider());
		root.getChildren().add(getPlayButton());
		root.getChildren().add(getPauseButton());
		root.getChildren().add(getStepForwardButton());
		simBox = getMenu(currentbox);
		root.getChildren().add(simBox);
		
		//root.getChildren().add(getText(grid.getDescription()));
		root.getChildren().add(getResetButton());
		root.getChildren().add(getSizeField());
		root.getChildren().add(getFileButton());
		root.getChildren().add(getOutlineButton());
		root.getChildren().add(getFileSaveButton());
//		NumberAxis xaxis = new NumberAxis();
//		NumberAxis yaxis = new NumberAxis();
//		linechart = new LineChart<Number,Number>(xaxis, yaxis);
//		linechart.setTitle("Proportions of different cell types over time");
//		linechart.setLayoutX(10);
//		linechart.setLayoutY(575);
//		linechart.setMaxHeight(80);
//		root.getChildren().add(linechart);
		return scene;
	}
	
	/**
	 * allows the user to change the grid size manually through a text field
	 * @return = a new VBox containing a text input field and a caption
	 * 
	 */
	private VBox getSizeField() {
		Label label1 = new Label(getFile(LABELFIELD));
		VBox hb = new VBox();
		TextField input = new TextField();
		label1.setWrapText(true);
        label1.setTextAlignment(TextAlignment.JUSTIFY);
		hb.getChildren().addAll(label1, input);
		//hb.setStyle("-fx-background: green;");
		hb.setSpacing(10);
		hb.setLayoutX(GRIDSIZE + GRIDX + DROPOFFSET / 2);
		hb.setLayoutY(420);
		hb.setMaxWidth(WIDTH - hb.getLayoutX() - 5);
		
		input.setText(Integer.toString(gridsize));
		input.textProperty().addListener((option, oldvalue, newvalue) -> {
			if (!newvalue.matches("\\d*")) {
	            input.setText(oldvalue);
	        }
			if (!newvalue.equals("")){
				gridsize = Integer.parseInt(input.getText());
			}
			if (gridsize > MAXGRIDSIZE){
				gridsize = MAXGRIDSIZE;
			}
			
			if (gridsize < MINGRIDSIZE){
				gridsize = MINGRIDSIZE;
			}
			
			
		});
		input.setOnKeyPressed((event) -> { if(event.getCode() == KeyCode.ENTER) { 
			//String text = input.getText();
			//System.out.println(text);
			//System.out.println(gridsize);
			grid.setSize((gridsize));
			blocksize = GRIDSIZE / gridsize;
			if (currentshape.equals("Square")) {
				myGrid = new SquareGridView(GRIDX, GRIDY, GRIDSIZE / grid.getSize(), GRIDSIZE);
			}
			else if (currentshape.equals("Triangle")){
				myGrid = new TriangleGridView(GRIDX, GRIDY, GRIDSIZE / grid.getSize(), GRIDSIZE);
			}
			else if (currentshape.equals("Hexagon")){
				myGrid = new HexGridView(GRIDX, GRIDY, GRIDSIZE / grid.getSize(), GRIDSIZE);
			}
			myRoot.getChildren().remove(gridgroup);
			gridgroup = myGrid.drawGrid(grid, WIDTH, HEIGHT, GRIDSIZE / grid.getSize());
			myRoot.getChildren().add(gridgroup);
			animation.stop();
			//pressed = false;
			
			//System.out.println(input.getText());
			
		}});
		input.setOnMouseClicked((event) -> {
			input.setText("");
		});
		
		return hb;
	}

	/**
	 * Gets a slider which changegs the animation speed
	 * @return a new Slider object
	 */
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

	/**
	 * Gets a button which resets the animation to the beginning
	 * @return a new Button
	 */
	private Button getResetButton(){
		
		Image play = new Image(getClass().getResourceAsStream("../stepbackward.png"), BUTTONSIZE, BUTTONSIZE, false, false);
		Button resetbutton = new Button("", new ImageView(play));
		resetbutton.setLayoutX(sliderx + 13.5);
		resetbutton.setLayoutY(myGrid.getY() + myGrid.getDimensions() + BUTTONVERTOFFSET);
		resetbutton.setOnAction(e -> getReset());
		return resetbutton;
	}
	
	private Button getOutlineButton(){
		
		//Image play = new Image(getClass().getResourceAsStream("../stepbackward.png"), BUTTONSIZE, BUTTONSIZE, false, false);
		Button resetbutton = new Button("Toggle Gridlines");
		resetbutton.setLayoutX(sliderx + 415);
		resetbutton.setLayoutY(5 * myGrid.getY() + 2 * BUTTONVERTOFFSET);
		resetbutton.setOnAction(e -> handleOutline());
		return resetbutton;
	}
	
	
	private void handleOutline() {
		myGrid.setOutline(!myGrid.getOutline());
		myRoot.getChildren().remove(gridgroup);
		gridgroup = myGrid.drawGrid(grid, WIDTH, HEIGHT, blocksize);
		myRoot.getChildren().add(gridgroup);
	}
	/**
	 * On click of Reset Button, resets the animation and then pauses it
	 * called only by the Reset Button
	 */
	private void getReset() {
		reset();
		animation.pause();
	}

	/**
	 * Gets a new button which on click plays the animation
	 * @return = a new Button object
	 */
	private Button getPlayButton(){
		Image play = new Image(getClass().getResourceAsStream("../playicon.png"), BUTTONSIZE, BUTTONSIZE, false, false);
		Button playbutton = new Button("", new ImageView(play));
		playbutton.setLayoutX(sliderx + BUTTONHOROFFSET + BUTTONSIZE + 13.5);
		playbutton.setLayoutY(myGrid.getY() + myGrid.getDimensions() + BUTTONVERTOFFSET);
		playbutton.setOnAction(e -> getPlayAction());
		return playbutton;
	}
	
	/**
	 * Gets a new button which on click pauses the animation
	 * @return = a new Button
	 */
	private Button getPauseButton(){
		
		Image play = new Image(getClass().getResourceAsStream("../pauseicon.png"), BUTTONSIZE, BUTTONSIZE, false, false);
		Button pausebutton = new Button("", new ImageView(play));
		pausebutton.setLayoutX(sliderx + 2 * BUTTONHOROFFSET + 2 * BUTTONSIZE + 13.5);
		pausebutton.setLayoutY(myGrid.getY() + myGrid.getDimensions() + BUTTONVERTOFFSET);
		pausebutton.setOnAction(e -> getPauseAction());
		return pausebutton;
	}
	
	/**
	 * Gets a new button which on click steps the animatiton forward once
	 * @return = a new Button
	 */
	private Button getStepForwardButton() {
		Image play = new Image(getClass().getResourceAsStream("../stepforward.png"), BUTTONSIZE, BUTTONSIZE, false, false);
		Button stepforwardbutton = new Button("", new ImageView(play));
		stepforwardbutton.setLayoutX(sliderx + 3 * BUTTONHOROFFSET + 3 * BUTTONSIZE + 13.5);
		stepforwardbutton.setLayoutY(myGrid.getY() + myGrid.getDimensions() + BUTTONVERTOFFSET);
		stepforwardbutton.setOnAction(e -> getStepForward());
		return stepforwardbutton;
	}

	private Button getFileButton(){
		Button retbutton = new Button();
		retbutton.setLayoutX(GRIDSIZE + GRIDX + DROPOFFSET);
		retbutton.setLayoutY(4 * GRIDY);
		retbutton.setText("Get XML File");
		retbutton.setOnAction(e -> getFile());
		return retbutton;
		
	}
	
	private void getFile(){
		//grid.clear();
		FileChooser chooser = new FileChooser();
		chooser.setInitialDirectory(new File(System.getProperty("user.dir")));
		currentfile = chooser.showOpenDialog(myStage);
		
		XMLManager manager = new XMLManager(currentfile);
		List<List<Integer>> editList = manager.getXMLFile();
		grid.setSize(manager.getSize());
		//grid.clear();
		blocksize = GRIDSIZE / grid.getSize();
		if (currentshape.equals("Square")) {
			myGrid = new SquareGridView(GRIDX, GRIDY, GRIDSIZE / grid.getSize(), GRIDSIZE);
		}
		else if (currentshape.equals("Triangle")){
			myGrid = new TriangleGridView(GRIDX, GRIDY, GRIDSIZE / grid.getSize(), GRIDSIZE);
		}
		else if (currentshape.equals("Hexagon")){
			myGrid = new HexGridView(GRIDX, GRIDY, GRIDSIZE / grid.getSize(), GRIDSIZE);
		}
		
		grid.xmlEdit(editList);
		
		//NeighborFinder.getNeighbors(grid.getCells(), new Rectangle(), "cross", "standard");
		gridgroup = myGrid.drawGrid(grid, WIDTH, HEIGHT, GRIDSIZE / grid.getSize());
		myRoot.getChildren().add(gridgroup);
		//initializeStart(WIDTH, HEIGHT, BACKGROUND, grid, getFile(GOLDESCRIPTION));
	}
	
	
	private Button getFileSaveButton(){
		Button retbutton = new Button();
		retbutton.setLayoutX(GRIDSIZE + GRIDX + DROPOFFSET);
		retbutton.setLayoutY(5 * GRIDY);
		retbutton.setText("Save to XML File");
		retbutton.setOnAction(e -> saveFile());
		return retbutton;
		
	}
	
	private void saveFile(){
		XMLBuilder builder = new XMLBuilder();
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		builder.setUpFile(grid, timeStamp);
	}
	
	/**
	 * Steps the animation forward once and pauses it
	 * For Step Forward button
	 */
	private void getStepForward() {
		animation.pause();
		step(stepincrement);
	}

	/**
	 * Gets a new ComboBox with a drop-down menu allowing the user to choose the simulation
	 * @param selected = a string indicating which option within the combo box is currently selected
	 * @return = a new ComboBox indicating the simulation menu
	 */
	private ComboBox<String> getMenu(String selected){
		ObservableList<String> options = 
			    FXCollections.observableArrayList(
			        "Game of Life",
			        "Spreading Fire",
			        "Segregation",
			        "Wa-Tor World",
			        "Ants",
			        "Rock Paper Scissors"
			    );
		ComboBox<String> combobox = new ComboBox<>(options);
		combobox.setLayoutX(GRIDSIZE + GRIDX + DROPOFFSET);
		combobox.setLayoutY(GRIDY);
		combobox.setValue(selected);
		combobox.valueProperty().addListener((option, oldvalue, newvalue) -> {
			handleBoxInput(oldvalue, newvalue);
		});
		
		return combobox;
	}
	
	/**
	 * Sets the simulation to match what a user selects in the member combo box
	 * @param oldvalue = the previous value (before a selection action) of the Menu combobox
	 * @param newvalue = the new value (after a selection action) of the Menu combobox
	 */
	private void handleBoxInput(String oldvalue, String newvalue) {
		if (happened) {
			if (oldvalue == null) {
				oldvalue = "";
			}
			if (oldvalue.equals(newvalue)) {
				return;
			}
			if (newvalue.equals("Game of Life")) {
				getGOL();
			}else if (newvalue.equals("Spreading Fire")) {
				getFire();
			}else if (newvalue.equals("Segregation")) {
				getSegregation();
			}else if (newvalue.equals("Wa-Tor World")){
				getWator();
			}else if (newvalue.equals("Ants")){
				getAnts();
			}else {
				getRPS();
			}
		}
		currentbox = simBox.getValue();
		happened = true;
	}
	
	/**
	 * @param 
	 * @return
	 */
	private ComboBox<String> getGridShape(String selected){
		ObservableList<String> options = 
			    FXCollections.observableArrayList(
			        "Square",
			        "Triangle",
			        "Hexagon"
			    );
		ComboBox<String> combobox = new ComboBox<>(options);
		combobox.setLayoutX(GRIDSIZE + GRIDX + DROPOFFSET);
		combobox.setLayoutY(2 * GRIDY);
		combobox.setValue(selected);
		combobox.valueProperty().addListener((option, oldvalue, newvalue) -> {
			handleGridShapeInput(oldvalue, newvalue);
		});
		
		return combobox;
	}
	private void handleGridShapeInput(String oldvalue, String newvalue) {
		if (pushed) {
			if (oldvalue == null) {
				oldvalue = "";
			}
			if (oldvalue.equals(newvalue)) {
				return;
			}
			if (newvalue.equals("Square")) {
				myRoot.getChildren().remove(gridgroup);
				myGrid = new SquareGridView(GRIDX, GRIDY, blocksize, GRIDSIZE);
				gridgroup = myGrid.drawGrid(grid, WIDTH, HEIGHT, blocksize);
				myRoot.getChildren().add(gridgroup);
				pushed = false;
				currentshape = "Square";
				gridShapeBox.setValue(currentshape);
			} 
			else if (newvalue.equals("Triangle")) {
				myRoot.getChildren().remove(gridgroup);
				myGrid = new TriangleGridView(GRIDX, GRIDY, blocksize, GRIDSIZE);
				gridgroup = myGrid.drawGrid(grid, WIDTH, HEIGHT, blocksize);
				myRoot.getChildren().add(gridgroup);
				pushed = false;
				currentshape = "Triangle";
				gridShapeBox.setValue(currentshape);
			} else {
				myRoot.getChildren().remove(gridgroup);
				myGrid = new HexGridView(GRIDX, GRIDY, blocksize, GRIDSIZE);
				gridgroup = myGrid.drawGrid(grid, WIDTH, HEIGHT, blocksize);
				myRoot.getChildren().add(gridgroup);
				pushed = false;
				currentshape = "Hexagon";
				gridShapeBox.setValue(currentshape);
			} 
		}
		pushed = true;
	}
	
	
	private void getWator() {
		try {
			myScene = initializeStart(WIDTH, HEIGHT, BACKGROUND, POSSIBLEGRIDS[WATORTYPE].getClass().newInstance(), getFile(WATORDESCRIPTION));
		} catch (InstantiationException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			System.out.println("559");
		}
		myStage.setScene(myScene);
		myStage.show();
		happened = false;
		simBox.setValue("Wa-Tor World");
		animation.pause();
	}
	private void getSegregation() {
		try {
			myScene = initializeStart(WIDTH, HEIGHT, BACKGROUND, POSSIBLEGRIDS[SEGTYPE].getClass().newInstance(), getFile(SEGDESCRIPTION));
		} catch (InstantiationException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			System.out.println("572");
		}
		myStage.setScene(myScene);
		myStage.show();
		happened = false;
		simBox.setValue("Segregation");
		animation.pause();
		//System.out.println(getFile(SEGDESCRIPTION));
	}
	private void getFire() {
		try {
			myScene = initializeStart(WIDTH, HEIGHT, BACKGROUND, POSSIBLEGRIDS[FIRETYPE].getClass().newInstance(), getFile(FIREDESCRIPTION));
		} catch (InstantiationException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			System.out.println("586");
		}
		myStage.setScene(myScene);
		myStage.show();
		happened = false;
		simBox.setValue("Spreading Fire");
		animation.pause();
	}
	private void getGOL() {
		try {
			myScene = initializeStart(WIDTH, HEIGHT, BACKGROUND, POSSIBLEGRIDS[GOLTYPE].getClass().newInstance(), getFile(GOLDESCRIPTION));
		} catch (InstantiationException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			System.out.println("599");
		}
		myStage.setScene(myScene);
		myStage.show();
		happened = false;
		simBox.setValue("Game of Life");
		animation.pause();
	}
	private void getAnts() {
		try {
			myScene = initializeStart(WIDTH, HEIGHT, BACKGROUND, POSSIBLEGRIDS[ANTTYPE].getClass().newInstance(), getFile(GOLDESCRIPTION));
		} catch (InstantiationException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			System.out.println("612");
		}
		myStage.setScene(myScene);
		myStage.show();
		happened = false;
		simBox.setValue("Ants");
		animation.pause();
	}
	private void getRPS() {
		try {
			myScene = initializeStart(WIDTH, HEIGHT, BACKGROUND, POSSIBLEGRIDS[RPSTYPE].getClass().newInstance(), getFile(GOLDESCRIPTION));
		} catch (InstantiationException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			System.out.println("625");
		}
		myStage.setScene(myScene);
		myStage.show();
		happened = false;
		simBox.setValue("Rock, Paper, Scissors");
		animation.pause();
	}
	private Group getText(String s){
		Text description = new Text();
		description.setLayoutX(GRIDSIZE + GRIDX + DROPOFFSET / 2);
		description.setLayoutY(3.8 * GRIDY);
		description.setText(s);
		description.setWrappingWidth(WIDTH - description.getLayoutX() - 5);
		description.setFill(Color.BLACK);
		description.setStyle("-fx-font-family: garamond; -fx-font-size: 14;");
		Rectangle background = new Rectangle(description.getLayoutX() - 3, description.getLayoutY() - 15, 158, 300);
		background.setStroke(Color.BLACK);
		background.setFill(Color.BURLYWOOD);
		background.toBack();
		Group retroot = new Group();
		retroot.getChildren().add(description);
		retroot.getChildren().add(background);
		description.toFront();
		
		return retroot;
	}
	private void getPlayAction(){
		animation.play();
	}
	private void getPauseAction(){
		animation.pause();
	}
}

