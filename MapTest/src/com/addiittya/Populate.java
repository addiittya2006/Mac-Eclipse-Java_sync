package com.addiittya;

public class Populate {
	
	public String peopleName;
	public int ssn;
	
	public Populate(String peopleName, int ssn) {
		super();
		this.peopleName = peopleName;
		this.ssn = ssn;
	}
	
	public String getPeopleName() {
		return peopleName;
	}
	public void setPeopleName(String peopleName) {
		this.peopleName = peopleName;
	}
	public int getSsn() {
		return ssn;
	}
	public void setSsn(int ssn) {
		this.ssn = ssn;
	}

}