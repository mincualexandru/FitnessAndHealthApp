package com.xtremefitnessapp.XtremeFitness.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "user_device", 
uniqueConstraints = { //
        @UniqueConstraint(name = "USER_DEVICE_UK", columnNames = { "device_Id", "User_Id" }) }
		)
public class UserDevice {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "user_device_id")
	private int id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "device_Id", nullable = false)
	private Device device;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "User_Id" , nullable = false)
	private AppUser user;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy="userDevice", orphanRemoval = true)
	private Set<Measurements> measurements = new HashSet<>();
	
	public UserDevice(int id, Device device, AppUser user) {
		this.id = id;
		this.device = device;
		this.user = user;
	}

	public UserDevice() {
	}
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Device getDevice() {
		return device;
	}

	public void setDevice(Device device) {
		this.device = device;
	}

	public AppUser getUser() {
		return user;
	}

	public void setUser(AppUser user) {
		this.user = user;
	}

	public Set<Measurements> getMeasurements() {
		return measurements;
	}

	public void setMeasurements(Set<Measurements> measurements) {
		this.measurements = measurements;
	}	
	
}
