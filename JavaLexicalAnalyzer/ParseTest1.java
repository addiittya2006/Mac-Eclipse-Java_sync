import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import java.util.Set;


public static void main(String args[]) {
//	File xmlFile = new File("Orders.xml");
//	SaxParserApp parser = new SaxParserApp();
	try {
		parser.parseDocument(xmlFile);
	} catch (ParserConfigurationException e) {
		e.printStackTrace();
	} catch (SAXException e) {
		e.printStackTrace();
	} catch (IOException e) {
		e.printStackTrace();
	}
}