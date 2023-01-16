package com.example.model;

import java.util.LinkedHashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="course")
public class Course {

	@Id
	@Column(name="CNO")
	private String CNO;
	
	@Column(name="CNAME")
	private String CNAME;
		
	//@JsonIgnoreProperties("courses")
	//@JsonBackReference
	@ManyToOne()
	@JsonBackReference
	@JoinColumn(name="TNO")
	private Teacher teacher;

	@JsonManagedReference
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "course")
    private Set<Sc> scs=new LinkedHashSet<Sc>();
	
	
	
	public Set<Sc> getScs() {
		return scs;
	}

	public void setScs(Set<Sc> scs) {
		this.scs = scs;
	}

	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	public String getCNO() {
		return CNO;
	}

	public void setCNO(String cNO) {
		CNO = cNO;
	}

	public String getCNAME() {
		return CNAME;
	}

	public void setCNAME(String cNAME) {
		CNAME = cNAME;
	}
	
}
