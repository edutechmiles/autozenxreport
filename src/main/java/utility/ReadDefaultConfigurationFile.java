package utility;

/**
 * @author Nitin Singh
 */

import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.report.ZenXReport;
import com.report.ReportConstants;

public class ReadDefaultConfigurationFile {

	public Map<String, String> configurationMap = new HashMap<String, String>(); 

	
	/**
	 * 
	 * @return
	 */
	
	public Map<String, String> readConfigurationFile() {
		
		 String resourceFile = ZenXReport.class.getPackage().getName().replace(".", "/")+ "/resources/"+ ReportConstants.CONFIG_FILE;
		  ///System.getProperty("user.dir")+"\\"+ReportConstants.CONFIG_FILE
		  URL url = getClass().getClassLoader().getResource(resourceFile);
		   

		  if(url != null) {
			 try { 
			    DocumentBuilderFactory dbFactory = null;
				DocumentBuilder dBuilder = null;
				Document doc = null;
			
				InputStream ioStream = url.openStream();
				
				
				dbFactory = DocumentBuilderFactory.newInstance();
				dBuilder = dbFactory.newDocumentBuilder();
				doc = dBuilder.parse(ioStream);
				doc.getDocumentElement().normalize();
				
			
				NodeList nodeList = doc.getElementsByTagName("configuration").item(0).getChildNodes();
				
				for (int i = 0; i < nodeList.getLength(); i++) {
					Node nNode = nodeList.item(i);
					
					if (nNode.getNodeType() == Node.ELEMENT_NODE) {
						configurationMap.put(nNode.getNodeName(), nNode.getTextContent());
					}
				}
			 }catch(Exception e) {
				  return configurationMap;
			 }
		  }else {
			  return configurationMap;
		  }
		 return configurationMap;
	}
	
}
