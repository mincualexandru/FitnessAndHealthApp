package com.xtremefitnessapp.XtremeFitness.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Size;
 
public class AppUserDTO {
 
    private Integer userId;
    private String userName;
    private String encrytedPassword;
    private String email;
    private String mobile;
    private String gender;
    private String born_date;
    private String name;
    
    
    
	public AppUserDTO() {
	}

	
	
	public AppUserDTO(String userName, String encrytedPassword, String email, String mobile, String gender,
			String born_date, String name) {
		super();
		this.userName = userName;
		this.encrytedPassword = encrytedPassword;
		this.email = email;
		this.mobile = mobile;
		this.gender = gender;
		this.born_date = born_date;
		this.name = name;
	}



	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEncrytedPassword() {
		return encrytedPassword;
	}

	public void setEncrytedPassword(String encrytedPassword) {
		this.encrytedPassword = encrytedPassword;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}



	public String getGender() {
		return gender;
	}



	public void setGender(String gender) {
		this.gender = gender;
	}



	public String getBorn_date() {
		return born_date;
	}



	public void setBorn_date(String born_date) {
		this.born_date = born_date;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}
	
 
}
