package com.addiittya;

@Descriptor(developer = "addiittya")
public class Annotata {
	public Annotata(){}
	
	@SuppressWarnings("unused")
	private Annotata(Integer a){
		System.out.println(" Private Constructor(Psst!): "+a);
	}
	
	public void display(){
		System.out.print(" \n Annotata display called ....."+"\n Developed by: ");
	}
}
