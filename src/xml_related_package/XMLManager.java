package xml_related_package;

import java.io.File;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Manager for the XMLParser and Menu class.
 * Here the special edits are returned if they are valid
 * otherwise an XMLException is thrown. 
 * 
 * This class only returns immutable objects and its only
 * dependency is the XMLParser class. 
 * 
 * @author Dorian
 *
 */
public class XMLManager {
	//Holds the XML file
	private File configFile;
	
	//Holds the logistical information relevant to the simulation
	private Map<String, String> modelDescription;
	
	//Acts as a key to all the available tags to choose from
	private static final List<String> DATA_FIELDS = Arrays.asList(new String[] {
    		"Simulation",
    		"SimulationNumber",
    		"Author",
    		"Size",
    		"gridEdits"
    });
	
	/**
	 * Constructs an XMLManager object with a certain file
	 * which is hopefully an XML file...
	 */
	public XMLManager(File XMLFile) {
		configFile = XMLFile;
	}
	
	/**
	 * Builds the XMLParser which checks to see if the configFile matches
	 * the simulation identification number
	 * @return an unmodifiable list of all the xml edits which determine
	 * the configuration of the simulation
	 */
	public List<List<Integer>> getXMLFile(int gridModelType) {
		XMLParser xml = new XMLParser("type");
		try {
			modelDescription = xml.getModel(configFile, gridModelType);
			return Collections.unmodifiableList(xml.getEdits());
		}
		catch(XMLException e) {
			throw new XMLException("Edits not reached");
		}
	}
	
	/**
	 * @return the model identification number
	 */
	public int getSimNumb() {
		return Integer.parseInt(modelDescription.get(DATA_FIELDS.get(1)));
	}
	
	/**
	 * @return the length of one side of the simulation
	 */
	public int getSize() {
		return Integer.parseInt(modelDescription.get(DATA_FIELDS.get(3)));
	}
	
	/**
	 * @return the title of the simulation being run
	 */
	public String simTitle() {
		return modelDescription.get(DATA_FIELDS.get(0));
	}
}
