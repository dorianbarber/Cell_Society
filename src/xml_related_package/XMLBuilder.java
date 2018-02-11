package xml_related_package;

import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import simulations.CellModel;
import simulations.GridModel;

public class XMLBuilder {
	
	
	
	
	public void setUpFile(GridModel model) {
		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		try {
			
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	
	
	public void writePoints(List<List<CellModel>> grid) {
		
	}
}
