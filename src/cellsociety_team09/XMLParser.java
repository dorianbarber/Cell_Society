package cellsociety_team09;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class XMLParser {
	public static final String ERROR_MESSAGE = "XML file does not represent %s";
    // name of root attribute that notes the type of file expecting to parse
    private final String TYPE_ATTRIBUTE;
    // keep only one documentBuilder because it is expensive to make and can reset it before parsing
    private final DocumentBuilder DOCUMENT_BUILDER;
    
    public XMLParser(String type) {
    	DOCUMENT_BUILDER = getDocumentBuilder();
    	TYPE_ATTRIBUTE = type;
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


}
