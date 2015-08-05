package com.addiittya;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CloneMain {

	public static void main(String[] args) throws CloneNotSupportedException, NumberFormatException, IOException {

		BufferedReader inp = new BufferedReader(new InputStreamReader(System.in));
		CloneTest c1 = new CloneTest("name", 10, 13);
		CloneTest c2 = c1.clone();
		System.out.println(c1+"      "+c2);
//		System.out.println();
		c1.getData();
		c2.getData();
		System.out.println("Do You want to change?");
		int choice = Integer.parseInt(inp.readLine());
		if(choice == 1){
			c2.changeDetails();
		}
		c1.getData();
		c2.getData();
	}

}
