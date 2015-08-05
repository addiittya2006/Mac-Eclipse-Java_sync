package com.addiittya;

import java.util.Comparator;

import com.addiittya.students.Student;

public class AdiTest<T extends Student> implements Comparator<T> {

	@Override
	public int compare(T o1, T o2) {
		return o1.getStudentName().compareTo(o2.getStudentName());
	}
}