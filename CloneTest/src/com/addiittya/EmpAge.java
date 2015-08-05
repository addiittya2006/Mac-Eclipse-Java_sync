package com.addiittya;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class EmpAge implements Cloneable{

	int age;
	BufferedReader inp = new BufferedReader(new InputStreamReader(System.in));

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int changeAge() throws IOException{
		System.out.print("Enter new age: ");
		return Integer.parseInt(inp.readLine());
	}

	public EmpAge clone() throws CloneNotSupportedException{
		return (EmpAge)super.clone();
	}
}
