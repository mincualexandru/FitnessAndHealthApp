package com.xtremefitnessapp.XtremeFitness.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.xtremefitnessapp.XtremeFitness.model.Measurements;

public interface MeasurementsRepository extends CrudRepository<Measurements, Integer> {

	@Query(value="DELETE FROM user_device WHERE user_device_id = ?1", nativeQuery= true)
	public void deleteMeasurementsForUserDeviceId(Integer user_device_id);

	
	@Query(value="SELECT * FROM  measurements WHERE user_device_id = ?1", nativeQuery= true)
	public List<Measurements> findAllForUserDeviceId(int selectedUserDeviceId);
	
	
//	@Query(value="SELECT value FROM  measurements WHERE user_device_id = ?1", nativeQuery= true)
//	public List<Measurements> findAllValuesForUserDeviceId(int selectedUserDeviceId);
}
