package com.api.platform.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class School {
	@Id
	private String schoolId;
	private String name;
	private String type;
	@Column(length=20)
	private String province;
	private String suburb;
	@Column(length=10)
	private String postalCode;
	@OneToOne(mappedBy = "school")
	private StudentDetails userDetails;
	public String getSchoolId() {
		return schoolId;
	}
	public void setSchoolId(String schoolId) {
		this.schoolId = schoolId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getSuburb() {
		return suburb;
	}
	public void setSuburb(String suburb) {
		this.suburb = suburb;
	}
	public String getPostalCode() {
		return postalCode;
	}
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
	public StudentDetails getUserDetails() {
		return userDetails;
	}
	public void setUserDetails(StudentDetails userDetails) {
		this.userDetails = userDetails;
	}

}
