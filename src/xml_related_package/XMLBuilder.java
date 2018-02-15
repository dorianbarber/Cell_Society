package xml_related_package;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import simulations.CellModel;
import simulations.GridModel;
import simulations.LifeGrid;

/**
 * XML Builder to create xml files during runtime. 
 * Contains all behaviors related to building xml files. 
 * 
 * The only actual behavior comes from the setUpFile method
 * which actually creates the file. However, what came out of that method 
 * were a series of helper methods which refactored code away from that main
 * function. This refactoring makes this code significantly easier 
 * to read and also saved many lines of code. 
 * 
 * @author Dorian
 *
 */
public class XMLBuilder {
	
	private DocumentBuilderFactory docFactory;
	private DocumentBuilder docBuilder;
	private Document doc;
	
	//describes the specific tag names in each xml file
	private static final List<String> DATA_FIELDS = Arrays.asList(new String[] {
    		"Simulation",
    		"SimulationNumber",
    		"Author",
    		"Size",
    		"gridEdits"
    });
	
	
	/**
	 * Builds the xml file using xml and DOM packages.
	 * @param model
	 * @param fileName
	 */
	public void setUpFile(GridModel model, String fileName) {
		try {
			docFactory = DocumentBuilderFactory.newInstance();
			docBuilder = docFactory.newDocumentBuilder();

			doc = docBuilder.newDocument();
			
			//Creates the root element which signals the XMLParser that this
			//is a proper cell society XML file
			Element rootElement = doc.createElement("data");
			rootElement.setAttribute("type", "Model");
			doc.appendChild(rootElement);

			//Adds the information to the root node
			addContent(rootElement, model, fileName);
			writePoints(rootElement, model.getCells());

			//Creates the actual file
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			File fileToWrite = new File("./data\\" + fileName + ".xml");
			if(fileToWrite.createNewFile()) {
				StreamResult result = new StreamResult(fileToWrite);
				transformer.transform(source, result);
			}
		}
		catch(ParserConfigurationException | TransformerException | IOException e) {
			throw new XMLException(e);
		}
	}
	
	
	/**
	 * Adds the basic contents related to the DATA_FIELDS
	 */
	private void addContent(Element root, GridModel model, String fileName) {
		root.appendChild(createTag(DATA_FIELDS.get(0), fileName));
		root.appendChild(createTag(DATA_FIELDS.get(1), Integer.toString(model.getKind())));
		root.appendChild(createTag(DATA_FIELDS.get(2), "Conrad Mitchell"));
		root.appendChild(createTag(DATA_FIELDS.get(3), Integer.toString(model.getSize())));
	}
	
	
	//Refactored to make addContent more concise
	private Element createTag(String tagName, String content) {
		Element node = doc.createElement(tagName);
		node.appendChild(doc.createTextNode(content));
		return node;
	}
	
	
	/**
	 * Method for writing all of the data points related to 
	 * each specific change for the grid.
	 * 
	 * @param grid
	 * @param root
	 */
	private void writePoints(Element root, List<List<CellModel>> grid) {
		Element edits = doc.createElement(DATA_FIELDS.get(4));
		for(int i = 0; i < grid.size(); i++) {
			for(int j = 0; j < grid.size(); j++) {
				Element point = doc.createElement("point");
				String content = i + " " + j + " " + grid.get(i).get(j).getXMLState();
				point.appendChild(doc.createTextNode(content));
				edits.appendChild(point);
			}
		}
		root.appendChild(edits);
	}
	
	//For testing the XMLBuilder
	public static void main(String[] args) {
		GridModel grid = new LifeGrid();
		XMLBuilder builder = new XMLBuilder();
		builder.setUpFile(grid, "thirdTest");
	}
}
