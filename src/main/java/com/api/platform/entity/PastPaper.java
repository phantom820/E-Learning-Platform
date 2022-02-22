package com.api.platform.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table ( schema = "resource")
public class PastPaper {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "paper_id")
	private int paperId;
	private short grade;
	private String subject;
	private short paperNo;
	private String type;
	private String province;
	private Date date;
	private String paperName;
	private String paperUrl;
	
	@OneToOne(mappedBy = "paper")
	private PastPaperMemo memo;

	public int getPaperId() {
		return paperId;
	}

	public void setPaperId(int paperId) {
		this.paperId = paperId;
	}

	public short getGrade() {
		return grade;
	}

	public void setGrade(short grade) {
		this.grade = grade;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public short getPaperNo() {
		return paperNo;
	}

	public void setPaperNo(short paperNo) {
		this.paperNo = paperNo;
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

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getPaperName() {
		return paperName;
	}

	public void setPaperName(String paperName) {
		this.paperName = paperName;
	}

	public PastPaperMemo getMemo() {
		return memo;
	}

	public void setMemo(PastPaperMemo memo) {
		this.memo = memo;
	}

	public String getPaperUrl() {
		return paperUrl;
	}

	public void setPaperUrl(String paperUrl) {
		this.paperUrl = paperUrl;
	}

	
}
