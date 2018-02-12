package xml_related_package;

import java.io.File;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Manager for the XMLParser and Menu class.
 * Here the edits the special edits are returned. 
 * 
 * @author Dorian
 *
 */
public class XMLManager {
	private File configFile;
	private Map<String, String> modelDescription;
	private static final List<String> DATA_FIELDS = Arrays.asList(new String[] {
    		"Simulation",
    		"SimulationNumber",
    		"Author",
    		"Size",
    		"gridEdits"
    });
	
	public XMLManager(File XMLFile) {
		configFile = XMLFile;
	}
	
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
	
	public int getSimNumb() {
		return Integer.parseInt(modelDescription.get(DATA_FIELDS.get(1)));
	}
	
	public int getSize() {
		return Integer.parseInt(modelDescription.get(DATA_FIELDS.get(3)));
	}
	
	public String simTitle() {
		return modelDescription.get(DATA_FIELDS.get(0));
	}
	
	
}
