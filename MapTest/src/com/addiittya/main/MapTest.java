package com.addiittya.main;

import java.util.Collection;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
//import java.util.LinkedHashashmap;
import java.util.Set;

//import java.util.Hashashmap;
//import java.util.Map;

public class MapTest {

	public static void main(String[] args) {

//		LinkedHashashmap<String, Double> hashm = new LinkedHashashmap<>();
		HashMap<String, Double> hashm = new HashMap<>();
		Hashtable<String, String> hasht = new Hashtable<>();

//		put lines
		System.out.println(hashm.put("A", new Double(3434.34))+"\n");
		hashm.put(new String("B"), new Double(3434.34));  
		hashm.put("D", 123.22);
		System.out.println(hashm.put("A", new Double(1378.00))+"\n");
		hashm.put("C", new Double(99.22));
		hashm.put("E", -19.08);
//		hashm.put(null, null);
//		hashm.put("null", null);

//		hashtable put lines
		hasht.put("Aditya", "1");
		hasht.put("Adity", "1");
		hasht.put("Adit", "1");
		hasht.put("Adi", "1");
		hasht.put("Ad", "1");
		
		
		System.out.println(hashm+"\n");

//		hashm.put("null", new Double(919.22));

		System.out.println(hashm+"\n");

		double balance = hashm.get("A");

		System.out.println("case of replacement : "+hashm.put("A", balance + 1000)+"\n");  

		System.out.println(hashm+"\n");

		System.out.println("A's new balance:"+hashm.get("A")+"\n");

		Set<String> skey = hashm.keySet();

		System.out.println("\n\n");

		for(String cur_key : skey)
		{
			System.out.println(cur_key);
		}

		System.out.println("\n\n");

		Collection<Double> valu = hashm.values();

		for(Double cur_val : valu )
		{
			System.out.println( cur_val );
		}
		
		System.out.println("\n\n");
		
		Set < Map.Entry<String,Double> > meob = hashm.entrySet();

		Iterator < Map.Entry<String,Double> >itr = meob.iterator();

		while(itr.hasNext())
		{
			Map.Entry<String, Double> ee= itr.next();
			System.out.println(ee.getKey()+"    "+ee.getValue());
			ee.setValue( ee.getValue() +10000 );
		}
		
		System.out.println("\n");
		
		for(Map.Entry<String, Double> ev:meob)
		{
			System.out.println(ev.getKey()+" is mapped to "+ev.getValue());
		}
		
		System.out.println("\n");
		
		Enumeration<String> ehash = hasht.keys();
		while (ehash.hasMoreElements()) {
			String s = ehash.nextElement();
			System.out.println(s + "  $  " + hasht.get(s));
		}
		
		System.out.println("\n");
		
		Set<Map.Entry<String, String>> sethash = hasht.entrySet();
		Iterator<Map.Entry<String, String>> tableterate = sethash.iterator();
		while(tableterate.hasNext()){
			System.out.println(tableterate.next());
		}
			
	}
}