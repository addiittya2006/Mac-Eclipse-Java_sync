package com.addiittya;
 
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStreamReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class XMLEntry {
 
    public static void main(String[] args) {
        DocumentBuilderFactory icFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder icBuilder;
        InputStreamReader r=new InputStreamReader(System.in);  
        Integer i;
        BufferedReader br=new BufferedReader(r);  

		File file = new File("test.xml");
        try {
            icBuilder = icFactory.newDocumentBuilder();
            Document doc = icBuilder.newDocument();
            Element mainRootElement1 = doc.createElement("Companies1");
            doc.appendChild(mainRootElement1);
            
//            Element mainRootElement2 = doc.createElement("Companies2");
//            doc.appendChild(mainRootElement2);
            
//            Element mainRootElement3 = doc.createElement("Companies2");
//            doc.appendChild(mainRootElement3);

            for(i=1;i<4;i++){
            System.out.println("Enter Name");
            String s1 = br.readLine();
            
            System.out.println("Enter Type");
            String s2 = br.readLine();
            
            System.out.println("Enter Emplyees");
            String s3 = br.readLine();
            
            mainRootElement1.appendChild(getCompany(doc, i.toString(), s1, s2, s3));
            
            }
            
			if (!file.exists()) {
				file.createNewFile();
			}else {
				file.delete();
				file.createNewFile();
			}

			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);

            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes"); 
            DOMSource source = new DOMSource(doc);
            StreamResult console = new StreamResult(bw);
            transformer.transform(source, console);

			bw.close();

            System.out.println("\nXML DOM Created Successfully..");
 
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
 
    private static Node getCompany(Document doc, String id, String name, String age, String role) {
        Element company = doc.createElement("Company");
        company.setAttribute("id", id);
        company.appendChild(getCompanyElements(doc, company, "Name", name));
        company.appendChild(getCompanyElements(doc, company, "Type", age));
        company.appendChild(getCompanyElements(doc, company, "Employees", role));
        return company;
    }

    private static Node getCompanyElements(Document doc, Element element, String name, String value) {
        Element node = doc.createElement(name);
        node.appendChild(doc.createTextNode(value));
        return node;
    }
}