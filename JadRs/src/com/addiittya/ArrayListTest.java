package com.addiittya;

import java.util.ArrayList;
//import java.util.ConcurrentModificationException;
//import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.ListIterator;
//import java.util.NoSuchElementException;

import java.util.TreeSet;

import com.addiittya.students.Student;

public class ArrayListTest {

	public static void main(String[] args){

		ArrayList<Student> stulist = new ArrayList<>();
		HashSet<Student> stuhash = new HashSet<>();
//		TreeSet<Student> stutree = new TreeSet<>();
		TreeSet<Student> stutree = new TreeSet<>(new AdiTest<>());

		Student ob1 = new Student("Aditya", "B7");
		Student ob2 = new Student("Aya", "B8");
		Student ob3 = new Student("Adit", "B9");
		Student ob4 = new Student("Adity", "B4");
		Student ob5 = new Student("Adity", "B4");
		Student ob6 = new Student("Adity", "B4");

		stulist.add(ob1);
		stulist.add(ob2);
		stulist.add(ob3);
		stulist.add(ob4);
		stulist.add(ob5);
		System.out.println(stulist.add(ob6)+"\t");
		System.out.println(stulist.add(ob4)+"\n");

		stuhash.add(ob1);
		stuhash.add(ob2);
		stuhash.add(ob3);
		stuhash.add(ob4);
		System.out.println(stuhash.add(ob5));
		System.out.println(stuhash.add(ob6)+"\t");
		System.out.println(stuhash.add(ob4)+"\n");

		stutree.add(ob3);
		stutree.add(ob2);
		stutree.add(ob1);
		stutree.add(ob4);

		Iterator<Student> stuterate = stuhash.iterator();
		ListIterator<Student> stuterat = stulist.listIterator();
		Iterator<Student> stutera = stutree.iterator();

		//		for (Student student : stutree) {
		//			System.out.println(student.getStudentName());
		//		}
		while (stutera.hasNext()) {
			//			Student student = (Student) stutera.next();
			System.out.println(stutera.next().toString());
		}

		System.out.println("\n\n");

		//		for (Student student : stuhash) {
		//			System.out.println("Student [studentName=" + student.studentName + ", batch=" + student.batch + "]");
		//		}

		while (stuterat.hasNext()) {
			System.out.println(stuterat.next().toString());
		}

		System.out.println("\n\n");

		while(stuterate.hasNext()){
			System.out.println(stuterate.next().toString());
		}

		//		for (Student student : stulist) {
		//			System.out.println("Student [studentName=" + student.studentName + ", batch=" + student.batch + "]");
		//		}

		
		System.out.println("\n");
		
		System.out.println(" whole list  =  " + stutree );
		System.out.println(" first   =   " + stutree.first());
		System.out.println(" last  =  " + stutree.last());
		System.out.println(" head set  =  " + stutree.headSet(ob3));
		System.out.println(" tail set  =  " + stutree.tailSet(ob3));
//		System.out.println(" sub set  =  " + stutree.subSet(ob1, ob3));

	}


}
