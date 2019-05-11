package com.xtremefitnessapp.XtremeFitness.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

 
@Entity
@Table(name = "App_User", //
        uniqueConstraints = { //
                @UniqueConstraint(name = "APP_USER_UK", columnNames = "User_Name") })
public class AppUser {
 
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "User_Id", nullable = false)
    private int userId;
 
    @Size(min=3, max=30)
    @Column(name = "User_Name")
    private String userName;
 
    @Size(min=3, max=128)
    @Column(name = "Encryted_Password")
    private String encrytedPassword;
    
    @Size(min=3, max=30)
    @Column(name = "real_name")
    private String name;
    
    @NotBlank
    @Column(name = "gender")
    private String gender;
    
    @Size(min=3, max=30)
    @Column(name = "born_date")
    private String born_date;
    
    @NotBlank
	@Column(name = "email")
	private String email;
    
	@NotBlank
	@Column(name = "mobile")
	private String mobile;

	@OneToMany(mappedBy="user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<UserDevice> deviceUsers = new HashSet<UserDevice>();
    
    public void addUser(UserDevice user) {
		this.deviceUsers.add(user);
	}
	public Set<UserDevice> getDeviceUsers() {
		return deviceUsers;
	}

	public void setDeviceUsers(Set<UserDevice> deviceUsers) {
		this.deviceUsers = deviceUsers;
	}

	public void addUserGroups(UserDevice userDevice) {
		this.deviceUsers.add(userDevice);
	}
    
    
    public AppUser() {
	}


	public AppUser(@Size(min = 3, max = 30) String userName, @Size(min = 3, max = 128) String encrytedPassword,
			@Size(min = 3, max = 30) String name, @NotBlank String gender, @Size(min = 3, max = 30) String born_date,
			@NotBlank String email, @NotBlank String mobile) {
		super();
		this.userName = userName;
		this.encrytedPassword = encrytedPassword;
		this.name = name;
		this.gender = gender;
		this.born_date = born_date;
		this.email = email;
		this.mobile = mobile;
	}
	
	
	public int getUserId() {
        return userId;
    }
 
    public void setUserId(int userId) {
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
    
	public boolean hasDevice(Device device) {
		for(UserDevice userDevice : getDeviceUsers()) {
			if(userDevice.getDevice().getDevice_id() == device.getDevice_id()) {
				return true;
			}
		}
		return false;
	}
 
}
