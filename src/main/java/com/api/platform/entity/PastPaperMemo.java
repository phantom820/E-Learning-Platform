package com.api.platform.entity;

import java.awt.print.Paper;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@ Table (schema = "resource")
public class PastPaperMemo {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "memo_id")
	private int memoId;
	private short grade;
	private String subject;
	private short paperNo;
	private String type;
	private String province;
	private Date date;
	private String memoName;
	private String memoUrl;
	
	@OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "paper_id")
    private PastPaper paper;

	public int getMemoId() {
		return memoId;
	}

	public void setMemoId(int memoId) {
		this.memoId = memoId;
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

	public String getMemoName() {
		return memoName;
	}

	public void setMemoName(String memoName) {
		this.memoName = memoName;
	}

	public PastPaper getPaper() {
		return paper;
	}

	public void setPaper(PastPaper paper) {
		this.paper = paper;
	}

	public String getMemoUrl() {
		return memoUrl;
	}

	public void setMemoUrl(String memoUrl) {
		this.memoUrl = memoUrl;
	}


	
}
