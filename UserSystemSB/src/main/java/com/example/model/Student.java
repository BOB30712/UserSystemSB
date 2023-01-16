package com.example.model;

import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "student")
public class Student {

	@Id
	@Column(name = "SNO")
	private String SNO;
	
	@Column(name = "SNAME")
	private String SNAME;
	
	@Column(name = "SDATE")
	private Date SDATE;
	
	@Column(name = "SSEX")
	private int SSEX;
	
	@Column(name = "SEMAIL")
	private String SEMAIL;
	
	@Column(name = "SPWD")
	private String SPWD;
	
	@JsonManagedReference
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "student")
    private Set<Sc> scs=new LinkedHashSet<Sc>();

	public String getSNO() {
		return SNO;
	}

	public void setSNO(String sNO) {
		SNO = sNO;
	}

	public String getSNAME() {
		return SNAME;
	}

	public void setSNAME(String sNAME) {
		SNAME = sNAME;
	}

	public Date getSDATE() {
		return SDATE;
	}

	public void setSDATE(Date sDATE) {
		SDATE = sDATE;
	}

	public int getSSEX() {
		return SSEX;
	}

	public void setSSEX(int sSEX) {
		SSEX = sSEX;
	}

	public String getSEMAIL() {
		return SEMAIL;
	}

	public void setSEMAIL(String sEMAIL) {
		SEMAIL = sEMAIL;
	}

	public String getSPWD() {
		return SPWD;
	}

	public void setSPWD(String sPWD) {
		SPWD = sPWD;
	}

	public Set<Sc> getScs() {
		return scs;
	}

	public void setScs(Set<Sc> scs) {
		this.scs = scs;
	}
	
	
}
