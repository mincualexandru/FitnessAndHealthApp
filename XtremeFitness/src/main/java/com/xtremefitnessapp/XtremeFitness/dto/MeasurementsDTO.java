package com.xtremefitnessapp.XtremeFitness.dto;


import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;

import com.xtremefitnessapp.XtremeFitness.model.UnitOfMeasurement;
import com.xtremefitnessapp.XtremeFitness.model.UserDevice;

public class MeasurementsDTO {

	private int id;
	private UserDevice userDevice;
	private float value;
	private UnitOfMeasurement unitOfMeasurement;
	private Date dateOfMeasurement;
	
	public MeasurementsDTO() {
	}

	public MeasurementsDTO(int id, float value, UnitOfMeasurement unitOfMeasurement, Date dateOfMeasurement) {
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
