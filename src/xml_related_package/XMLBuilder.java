package xml_related_package;

import java.util.Arrays;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import simulations.CellModel;
import simulations.GridModel;

public class XMLBuilder {
	
	private static final List<String> DATA_FIELDS = Arrays.asList(new String[] {
    		"Simulation",
    		"SimulationNumber",
    		"Author",
    		"Size",
    		"gridEdits"
    });
	
	
	public void setUpFile(GridModel model, String fileName) {
		
		try {
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			
			Document doc = docBuilder.newDocument();
			Element rootElement = doc.createElement("data");
			rootElement.setAttribute("type", "Model");
			
			Element sim = doc.createElement("Simulation");
			sim.appendChild(doc.createTextNode("fileName"));
			
			Element simNumb = doc.createElement("SimulationNumber");
			simNumb.appendChild(doc.createTextNode(Integer.toString(model.getKind())));
			
			Element author = doc.createElement("Author");
			author.appendChild(doc.createTextNode("Conrad Mitchell"));
			
			Element size = doc.createElement("Size");
			size.appendChild(doc.createTextNode(Integer.toString(model.getSize())));
			
			
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
		
	}
	
	
	
	
	public void writePoints(List<List<CellModel>> grid) {
		
	}
}
