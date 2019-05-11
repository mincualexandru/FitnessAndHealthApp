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


@Entity
@Table(name="device")
public class Device {

	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "device_Id")
	private int device_id;
	
	@Column(name = "device_Name")
	private String deviceName;
	
	@Column(name = "company_Name")
	private String companyName;
	
	@Column(name = "device_Description")
	private String deviceDescription;
	
	@Column(name = "Serial_Number", unique = true)
	private String deviceSerialNumber;
	
	@OneToMany(mappedBy="device", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<UserDevice> deviceUsers = new HashSet<UserDevice>();
	
	public Device() {
		
	}

	public Device(String deviceName, String companyName, String deviceDescription, String deviceSerialNumber) {
		this.deviceName = deviceName;
		this.companyName = companyName;
		this.deviceDescription = deviceDescription;
		this.deviceSerialNumber = deviceSerialNumber;
	}
	public int getDevice_id() {
		return device_id;
	}


	public void setDevice_id(int device_id) {
		this.device_id = device_id;
	}


	public String getDeviceName() {
		return deviceName;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getDeviceDescription() {
		return deviceDescription;
	}

	public void setDeviceDescription(String deviceDescription) {
		this.deviceDescription = deviceDescription;
	}
	public String getDeviceSerialNumber() {
		return deviceSerialNumber;
	}

	public void setDeviceSerialNumber(String deviceSerialNumber) {
		this.deviceSerialNumber = deviceSerialNumber;
	}

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
}
