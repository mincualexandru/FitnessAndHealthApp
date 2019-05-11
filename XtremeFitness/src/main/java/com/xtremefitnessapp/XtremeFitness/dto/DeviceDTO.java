package com.xtremefitnessapp.XtremeFitness.dto;

import java.util.Set;

import com.xtremefitnessapp.XtremeFitness.model.AppUser;



public class DeviceDTO {

	private int device_id;

	private String deviceName;

	private String companyName;

	private String deviceDescription;
	
	private String deviceSerialNumber;
	
	
   
    public String getDeviceSerialNumber() {
		return deviceSerialNumber;
	}

	public void setDeviceSerialNumber(String deviceSerialNumber) {
		this.deviceSerialNumber = deviceSerialNumber;
	}

	private Set<AppUser> users;
	

	public Set<AppUser> getUsers() {
		return users;
	}

	public void setUsers(Set<AppUser> users) {
		this.users = users;
	}

	public DeviceDTO() {
	}
	
	public DeviceDTO(String deviceName, String companyName, String deviceDescription) {
		this.deviceName = deviceName;
		this.companyName = companyName;
		this.deviceDescription = deviceDescription;
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
}
