package com.api.platform.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.CascadeType;

@Entity
public class Student {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "student_id")
	private int studentId;
	private String name;
	private String surname;
	@Column(unique=true)
	private String email;
	private String password;
	@Column(unique=true, length=10)
	private String contact;
	
	 @OneToOne(mappedBy = "student")
	private StudentDetails studentDetails;

	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public StudentDetails getStudentDetails() {
		return studentDetails;
	}

	public void setStudentDetails(StudentDetails studentDetails) {
		this.studentDetails = studentDetails;
		
	}
	
	@Override 
	public boolean equals(Object o){
		if(o==this) {
			return true;
		}
		
		else if(!(o instanceof Student)) {
			return false;
		}
		
		Student other = (Student)o;
		
		if(other.contact.equals(this.contact) || other.email.equals(this.email)) {
			return true;
		}
		
		return false;
	}

}