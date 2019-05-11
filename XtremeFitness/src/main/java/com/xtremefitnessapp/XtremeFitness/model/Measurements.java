package com.xtremefitnessapp.XtremeFitness.model;


import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name="measurements")
public class Measurements {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_device_id", nullable = false)
	private UserDevice userDevice;
	
	@Column(name = "value")
	private float value;
	
	
	@Enumerated(EnumType.STRING)
	@Column(name = "unit_of_measurement")
	private UnitOfMeasurement unitOfMeasurement;
	
	
	@Column(name = "date_of_measurement")
	@Temporal(TemporalType.TIMESTAMP)
    private Date dateOfMeasurement;
	
	public Measurements() {
	}

	public Measurements(int id, float value, UnitOfMeasurement unitOfMeasurement, Date dateOfMeasurement) {
		this.id = id;
		this.value = value;
		this.unitOfMeasurement = unitOfMeasurement;
		this.dateOfMeasurement = dateOfMeasurement;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public UserDevice getUserDevice() {
		return userDevice;
	}

	public void setUserDevice(UserDevice userDevice) {
		this.userDevice = userDevice;
	}

	public float getValue() {
		return value;
	}

	public void setValue(float value) {
		this.value = value;
	}

	public UnitOfMeasurement getUnitOfMeasurement() {
		return unitOfMeasurement;
	}

	public void setUnitOfMeasurement(UnitOfMeasurement unitOfMeasurement) {
		this.unitOfMeasurement = unitOfMeasurement;
	}

	public Date getDateOfMeasurement() {
		return dateOfMeasurement;
	}

	public void setDateOfMeasurement(Date dateOfMeasurement) {
		this.dateOfMeasurement = dateOfMeasurement;
	}
	
	
	
}
