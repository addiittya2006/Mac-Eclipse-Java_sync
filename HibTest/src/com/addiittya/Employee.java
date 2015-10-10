package com.addiittya;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Set;


/**
 * The persistent class for the employees database table.
 * 
 */
@Entity
@Table(name="employees")
@NamedQuery(name="Employee.findAll", query="SELECT e FROM Employee e")
public class Employee implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	private String first;

	private String last;

	private String password;

	private String role;

	//bi-directional many-to-many association to Policy
	@ManyToMany
	@JoinTable(
		name="emp_poli"
		, joinColumns={
			@JoinColumn(name="empid")
			}
		, inverseJoinColumns={
			@JoinColumn(name="policyid")
			}
		)
	private Set<Policy> policies;

	public Employee() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirst() {
		return this.first;
	}

	public void setFirst(String first) {
		this.first = first;
	}

	public String getLast() {
		return this.last;
	}

	public void setLast(String last) {
		this.last = last;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return this.role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Set<Policy> getPolicies() {
		return this.policies;
	}

	public void setPolicies(Set<Policy> policies) {
		this.policies = policies;
	}

}