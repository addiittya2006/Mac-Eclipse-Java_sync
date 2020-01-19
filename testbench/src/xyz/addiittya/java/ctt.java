package xyz.addiittya.java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * under testbench
 * Created by addiittya on 10/09/16.
 */

public class ctt {
    public static void main(String args[]) {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        String entry1 = null, entry2 = null;
    	Integer[] n1 = new Integer[3];
    	Integer[] n2 = new Integer[3];
    	int alice = 0;
    	int bob = 0;
        try {
            entry1 = br.readLine();
            entry2 = br.readLine();
        } catch (IOException e) {
            System.out.println(e);
        }
        String[] a1 = entry1.split(" ");
        String[] a2 = entry2.split(" ");
        
//        n1[i] = Integer.parseInt(entry1.trim());
        
        for(int i=0;i<3;i++) {
        	n1[i] = Integer.parseInt(a1[i]);
        	n2[i] = Integer.parseInt(a2[i]);
        }
        
//        System.out.println(Arrays.toString(n2));
//        System.out.println(Arrays.toString(n1));
        
        for(int j=0;j<3;j++) {
        	if(n1[j]>n2[j]) {
        		alice++;
        	} else if(n1[j]<n2[j]) {
        		bob++;
        	}
        }
        
        System.out.println(alice+" "+bob);
    }
}