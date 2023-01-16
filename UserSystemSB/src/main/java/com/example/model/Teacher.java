package com.example.model;

import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "teacher")
public class Teacher {
	
	@Id
	@Column(name = "TNO")
	private String TNO;
	
	@Column(name = "TNAME")
	private String TNAME;
	
	@Column(name = "TBDAY")
	private Date TBDAY;
	
	@Column(name = "TSEX")
	private int TSEX;
	
	@Column(name = "TMAIL")
	private String TMAIL;
	
	@Column(name = "TPWD")
	private String TPWD;
	
	//@JsonIgnoreProperties("teacher")
	//@JsonManagedReference
	@JsonManagedReference
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "teacher")
    private Set<Course> courses=new LinkedHashSet<Course>();
	
	public Set<Course> getCourses() {
		return courses;
	}
	public void setCourses(Set<Course> courses) {
		courses = courses;
	}
	public String getTNO() {
		return TNO;
	}
	public void setTNO(String tNO) {
		TNO = tNO;
	}
	public String getTNAME() {
		return TNAME;
	}
	public void setTNAME(String tNAME) {
		TNAME = tNAME;
	}
	public Date getTBDAY() {
		return TBDAY;
	}
	public void setTBDAY(Date tBDAY) {
		TBDAY = tBDAY;
	}
	public int getTSEX() {
		return TSEX;
	}
	public void setTSEX(int tSEX) {
		TSEX = tSEX;
	}
	public String getTMAIL() {
		return TMAIL;
	}
	public void setTMAIL(String tMAIL) {
		TMAIL = tMAIL;
	}
	public String getTPWD() {
		return TPWD;
	}
	public void setTPWD(String tPWD) {
		TPWD = tPWD;
	}

	
}
