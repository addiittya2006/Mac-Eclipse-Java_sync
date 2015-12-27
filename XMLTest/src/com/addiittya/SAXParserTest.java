package com.addiittya;
import java.io.File;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class SAXParserTest {
   public static void main(String[] args){

      try {	
         File inputFile = new File("student.xml");
         SAXParserFactory factory = SAXParserFactory.newInstance();
         SAXParser saxParser = factory.newSAXParser();
         UserHandler userhandler = new UserHandler();
         saxParser.parse(inputFile, userhandler);     
      } catch (Exception e) {
         e.printStackTrace();
      }
   }   
}

class UserHandler extends DefaultHandler {

   boolean fFirstName = false;
   boolean fLastName = false;
   boolean fNickName = false;
   boolean fMarks = false;

   @Override
   public void startElement(String uri, 
      String localName, String qName, Attributes attributes)
         throws SAXException {
      if (qName.equalsIgnoreCase("student")) {
         String rollNo = attributes.getValue("rollno");
         System.out.println("Roll No : " + rollNo);
      } else if (qName.equalsIgnoreCase("firstname")) {
         fFirstName = true;
      } else if (qName.equalsIgnoreCase("lastname")) {
         fLastName = true;
      } else if (qName.equalsIgnoreCase("nickname")) {
         fNickName = true;
      }
      else if (qName.equalsIgnoreCase("marks")) {
         fMarks = true;
      }
   }

   @Override
   public void endElement(String uri, 
      String localName, String qName) throws SAXException {
      if (qName.equalsIgnoreCase("student")) {
         System.out.println("End Element :" + qName);
      }
   }

   @Override
   public void characters(char ch[], 
      int start, int length) throws SAXException {
      if (fFirstName) {
         System.out.println("First Name: " 
         + new String(ch, start, length));
         fFirstName = false;
      } else if (fLastName) {
         System.out.println("Last Name: " 
         + new String(ch, start, length));
         fLastName = false;
      } else if (fNickName) {
         System.out.println("Nick Name: " 
         + new String(ch, start, length));
         fNickName = false;
      } else if (fMarks) {
         System.out.println("Marks: " 
         + new String(ch, start, length));
         fMarks = false;
      }
   }
}