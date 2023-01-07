package com.example.model;

import java.sql.Timestamp;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;


@Entity
@Table(name = "user_jpa")
public class User {
	@Id
	@Column(name = "userno")
	private int userno;
	
	@Column(name = "username")
	private String username;
	
	@Column(name = "accountnumber")
	private String accountnumber;
	
	@Column(name = "password")
	private String password;
	
	@Column(name = "useremail")
	private String useremail;
	
	@Column(name = "memberlevel")
	private String memberlevel;
	
	@Column(name = "createddate")
	private Timestamp createddate;
	
	@Column(name = "updatedate")
	private Timestamp updatedate;

	public int getUserno() {
		return userno;
	}

	public void setUserno(int userno) {
		this.userno = userno;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getAccountnumber() {
		return accountnumber;
	}

	public void setAccountnumber(String accountnumber) {
		this.accountnumber = accountnumber;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUseremail() {
		return useremail;
	}

	public void setUseremail(String useremail) {
		this.useremail = useremail;
	}
	
	public String getMemberlevel() {
		return memberlevel;
	}

	public void setMemberlevel(String memberlevel) {
		this.memberlevel = memberlevel;
	}

	public Timestamp getCreateddate() {
		return createddate;
	}

	public void setCreateddate(Timestamp createddate) {
		this.createddate = createddate;
	}

	public Timestamp getUpdatedate() {
		return updatedate;
	}

	public void setUpdatedate(Timestamp updatedate) {
		this.updatedate = updatedate;
	}

	@Override
	public String toString() {
		return "User [userno=" + userno + ", username=" + username + ", accountnumber=" + accountnumber + ", password="
				+ password + ", useremail=" + useremail + ", memberlevel=" + memberlevel + ", createddate=" + createddate
				+ ", updatedate=" + updatedate + "]";
	}	
}
