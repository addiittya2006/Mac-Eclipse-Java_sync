package com.addiittya.students;

public class Student implements Comparable<Object>{
	
	public String studentName;
	public String batch;
	
	public Student(String studentName, String batch) {
		super();
		this.studentName = studentName;
		this.batch = batch;
	}
	
	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	
	public String getBatch() {
		return batch;
	}

	public void setBatch(String batch) {
		this.batch = batch;
	}

	@Override
	public String toString() {
		return "Student [studentName=" + studentName + ", batch=" + batch + "]";
	}
	
	@Override
	public int compareTo(Object obj){
		Student ob = (Student) obj;
//		String str1[] = ob.getBatch().split("$");
//		String str2[] = this.getBatch().split("$");
//		System.out.println(str1[0]+ "    " +str2[0]);
		int num1 = Integer.parseInt(this.getBatch().replaceAll("[\\D]", ""));
		int num2 = Integer.parseInt(ob.getBatch().replaceAll("[\\D]", ""));
		
		if(num1 != num2)
			if(num1 > num2)
				return 1;
			else
				return -1;
		else return 0;
//		return this.getBatch().compareTo(ob.getBatch());
	}

	
	@Override
	public boolean equals(Object obj){
		Student ob = (Student) obj;
		return ob.getStudentName().equals(this.getStudentName());
	}
	
	@Override
	public int hashCode(){
		return this.studentName.hashCode();
	}

}
