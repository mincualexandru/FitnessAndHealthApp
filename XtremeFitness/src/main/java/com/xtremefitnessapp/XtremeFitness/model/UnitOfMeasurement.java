package com.xtremefitnessapp.XtremeFitness.model;

public enum UnitOfMeasurement {
	
	KG ("KG"),
	CM ("CM"),
	GRADE ("GRADE"),
	BMI ("BMI"),
	mmHg(" mmHg"),
	kCal("kCal");
	
	private final String name;
	
	private UnitOfMeasurement(String name) {
		this.name=name;
	}

	public String getName() {
		return name;
	}
	
}
