package com.addiittya;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CloneTest implements Cloneable{

	EmpAge c = new EmpAge();
	String name;
	int roll;
	int age;
	BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

	public CloneTest(String name, int age, int roll){
//		EmpAge c = new EmpAge();
		this.name = name;
		try {
			c.setAge(age);
			this.age= c.getAge();
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.roll = roll;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getRoll() {
		return roll;
	}
	public void setRoll(int roll) {
		this.roll = roll;
	}
	public CloneTest clone() throws CloneNotSupportedException{
		CloneTest temp = (CloneTest)super.clone();
		EmpAge c4 = c.clone();
		temp.age = c4.getAge();
		return temp;
	}
	public void getData(){
		System.out.println("Name: "+name+"\nAge: "+age+"\nRoll: "+roll);
	}
	public void changeDetails() throws IOException{
		System.out.print("what doe you want ot chane?\n1. Age\n2.Name\n>>");
		int ch = Integer.parseInt(input.readLine());
		if(ch ==1 )
			this.age = c.changeAge();
	}

}
