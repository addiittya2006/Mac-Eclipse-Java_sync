import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;


public class SaxParserApp extends DefaultHandler{
	private String preTag = null;	
	private HashMap<String,Integer> stocks;
	private String parsingStock = null;
	private long a = 1L;
	
	public SaxParserApp(){
		stocks = new HashMap<String,Integer>();
	}
	
	public void parseDocument(File xmlDoc) throws ParserConfigurationException, SAXException, IOException {
		SAXPrserFactory factory = SAXParserFactory.newInstance();  
        SAXParser parser = factory.newSAXParser();  
        SaxParserApp handler = new SaxParserApp();  
        parser.parse(xmlDoc, handler);
	}
	
	@Override
	public void startDocument() throws SAXException {
	}
	
	@Override
	public void endDocument() throws SAXException {
		
		Set<Entry<String, Integer>> stockSet = this.stocks.entrySet();
		Iterator<Entry<String, Integer>> i = stockSet.iterator();
		while (i.hasNext()) {
			Entry<String, Integer> stock = (Map.Entry<String, Integer>) i.next();
			System.out.println("股票代号=" + stock.getKey() + ";订购总数=" + stock.getValue());
		}
	}

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		preTag = qName;
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		
		preTag = null;
	}
	
	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		if(preTag!=null){
			String content = new String(ch,start,length);
			content = content.trim();
			
			if (this.preTag.equals("StockSymbol")) {
				this.parsingStock = content;
				if (!this.stocks.containsKey(parsingStock)) {
					this.stocks.put(parsingStock, 0);
				}
			} else if (this.preTag.equals("Quantity")) {
				if	(this.parsingStock == null)
					throw new SAXException("parsingStock is null.");
				Integer value = this.stocks.get(parsingStock);
				this.stocks.put(parsingStock, Integer.parseInt(content) + value.intValue());
				this.parsingStock = null;
			}
		}
	}
	
	public static void main(String args[]) {
		File xmlFile = new File("Orders.xml");
		SaxParserApp parser = new SaxParserApp();
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
	
}