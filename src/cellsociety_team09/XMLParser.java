package cellsociety_team09;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * Parses XML files to get exactly what is necessary
 * 
 * @author Dorian
 *
 */
public class XMLParser {
	
	public static final String ERROR_MESSAGE = "XML file does not represent %s";
    // name of root attribute that notes the type of file expecting to parse
    private final String TYPE_ATTRIBUTE;
    // keep only one documentBuilder because it is expensive to make and can reset it before parsing
    private final DocumentBuilder DOCUMENT_BUILDER;
    
    
    private NodeList children;
    private ArrayList<ArrayList<Integer>> xmlEdits = new ArrayList<ArrayList<Integer>>();
    
    private static final List<String> DATA_FIELDS = Arrays.asList(new String[] {
    		"Simulation",
    		"Author",
    		"Size",
    		"gridEdits"
    });
    
    
    /**
     * Creates a parser for the XML of a given type
     */
    public XMLParser(String type) {
    	DOCUMENT_BUILDER = getDocumentBuilder();
    	TYPE_ATTRIBUTE = type;
    }
    
    /**
     * This method gets the data in the XML file as an extension
     * @param dataFile
     * @return
     */
    //public CellModel getModel(File dataFile){
    public Map<String, String> getModel(File dataFile){
    	Element root = getRootElement(dataFile);
    	if(!isValidFile(root, CellModel.DATA_TYPE)) {
    		throw new XMLException(ERROR_MESSAGE, CellModel.DATA_TYPE);
    	}
    	Map<String, String> results = new HashMap<>();
    	for(String field : DATA_FIELDS) {
    		results.put(field, getTextValue(root, field));
    	}
    	
    	ArrayList<String> list = new ArrayList<>();
    	for(int i = 1; i < children.getLength(); i += 2) {
    		list.add(children.item(i).getTextContent());
    	}
    	for(int j = 0; j < list.size(); j++) {
    		xmlEdits.add(new ArrayList<Integer>());
    		String edit = list.get(j);
    		
    		String[] values = edit.split(" ");
    		for(int k = 0; k < values.length; k++) {
    			xmlEdits.get(j).add(Integer.parseInt(values[k]));
    		}
    	}
    	return results;
    }
    
    // Helper method to make a documentBuilder.
    private DocumentBuilder getDocumentBuilder () {
        try {
            return DocumentBuilderFactory.newInstance().newDocumentBuilder();
        }
        catch (ParserConfigurationException e) {
            throw new XMLException(e);
        }
    }
    
    
    // Get root element of an XML file
    private Element getRootElement (File xmlFile) {
        try {
            DOCUMENT_BUILDER.reset();
            Document xmlDocument = DOCUMENT_BUILDER.parse(xmlFile); 
            NodeList briefNode = xmlDocument.getElementsByTagName("gridEdits");
            System.out.println(briefNode.getLength());
            children = briefNode.item(0).getChildNodes();
            return xmlDocument.getDocumentElement();
        }
        catch (SAXException | IOException e) {
            throw new XMLException(e);
        }
    }
    
    // Get value of Element's attribute
    private String getAttribute (Element e, String attributeName) {
        return e.getAttribute(attributeName);
    }
    
    // Returns if this is a valid XML file for the specified object type
    private boolean isValidFile (Element root, String type) {
        return getAttribute(root, TYPE_ATTRIBUTE).equals(type);
    }
    
    

    
//FIX THE FIX ME!!!!!!!!!!!!!!!!!!!!!!!!!!!    
// Get value of Element's text
    private String getTextValue (Element e, String tagName) {
        NodeList nodeList = e.getElementsByTagName(tagName);
        if (nodeList != null && nodeList.getLength() > 0) {
            return nodeList.item(0).getTextContent();
        }
        else {
            // FIXME: empty string or null, is it an error to not find the text value?
            return "";
        }
    }
    
    
    
    
    public ArrayList<ArrayList<Integer>> getEdits(){
    	return xmlEdits;
    }
    
    
    

}
