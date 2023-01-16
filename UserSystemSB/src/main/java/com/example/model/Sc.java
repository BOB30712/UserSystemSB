package com.example.model;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "sc")
public class Sc {

	@Id
	@Column(name = "SID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer SID;
	
	@ManyToOne()
	@JsonBackReference
	@JoinColumn(name="SNO")
	private Student student;
	
	@ManyToOne()
	@JsonBackReference
	@JoinColumn(name="CNO")
	private Course course;
	
	@Column(name = "SCORE")
	private int SCORE;

	public Integer getSID() {
		return SID;
	}

	public void setSID(Integer sID) {
		SID = sID;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public int getSCORE() {
		return SCORE;
	}

	public void setSCORE(int sCORE) {
		SCORE = sCORE;
	}

	
	
}
