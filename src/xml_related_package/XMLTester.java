package xml_related_package;

import java.io.File;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;

/**
 * Tester class for the XMLParser.
 * 
 * @author Dorian
 *
 */
public class XMLTester extends Application{
	//Choosing the proper file
    public static final String DATA_FILE_EXTENSION = "*.xml";
    private FileChooser myChooser = makeChooser(DATA_FILE_EXTENSION);
	
	//Testing the XMLParser
    public static void main(String[] args) {
    	launch(args);
    }

    public void start (Stage primaryStage) throws Exception {
        File dataFile = myChooser.showOpenDialog(primaryStage);
        if (dataFile != null) {
            try {
                XMLParser xml = new XMLParser("type");
            	xml.getModel(dataFile);
            }
            catch (XMLException e) {
                Alert a = new Alert(AlertType.ERROR);
                a.setContentText(e.getMessage());
                a.showAndWait();
            }
            // silly trick to select data file multiple times for this demo
            start(primaryStage);
        }
        else {
            // nothing selected, so quit the application
            Platform.exit();
        }
    }

    private FileChooser makeChooser (String extensionAccepted) {
        FileChooser result = new FileChooser();
        result.setTitle("Open Data File");
        // pick a reasonable place to start searching for files
        result.setInitialDirectory(new File(System.getProperty("user.dir")));
        result.getExtensionFilters().setAll(new ExtensionFilter("Text Files", extensionAccepted));
        return result;
    }
}
