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
	
	private DocumentBuilderFactory docFactory;
	private DocumentBuilder docBuilder;
	private Document doc;
	
	private static final List<String> DATA_FIELDS = Arrays.asList(new String[] {
    		"Simulation",
    		"SimulationNumber",
    		"Author",
    		"Size",
    		"gridEdits"
    });
	
	
	public void setUpFile(GridModel model, String fileName) {
		
		try {
			docFactory = DocumentBuilderFactory.newInstance();
			docBuilder = docFactory.newDocumentBuilder();
			
			doc = docBuilder.newDocument();
			
			Element rootElement = doc.createElement("data");
			rootElement.setAttribute("type", "Model");
			
			Element sim = doc.createElement(DATA_FIELDS.get(0));
			sim.appendChild(doc.createTextNode("fileName"));
			rootElement.appendChild(sim);
			
			Element simNumb = doc.createElement(DATA_FIELDS.get(1));
			simNumb.appendChild(doc.createTextNode(Integer.toString(model.getKind())));
			rootElement.appendChild(simNumb);
			
			Element author = doc.createElement(DATA_FIELDS.get(2));
			author.appendChild(doc.createTextNode("Conrad Mitchell"));
			rootElement.appendChild(author);
			
			Element size = doc.createElement(DATA_FIELDS.get(3));
			size.appendChild(doc.createTextNode(Integer.toString(model.getSize())));
			rootElement.appendChild(size);
			
			writePoints(model.getCells(), rootElement);
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
		
	}
	
	
	
	
	public void writePoints(List<List<CellModel>> grid, Element root) {
		Element edits = doc.createElement(DATA_FIELDS.get(4));
		for(int i = 0; i < grid.size(); i++) {
			for(int j = 0; j < grid.size(); j++) {
				Element point = doc.createElement("point");
				String content = i + " " + j + " " + grid.get(i).get(j).getState();
				point.appendChild(doc.createTextNode(content));
				edits.appendChild(point);
			}
		}
		root.appendChild(edits);
	}
}
