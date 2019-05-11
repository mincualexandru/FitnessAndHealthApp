package com.xtremefitnessapp.XtremeFitness.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.xtremefitnessapp.XtremeFitness.model.AppUser;
import com.xtremefitnessapp.XtremeFitness.model.Device;

public interface UserRepository extends CrudRepository<AppUser, Integer> {

	@Query(value="SELECT d.* FROM device d WHERE d.device_id not in (SELECT ud.device_id FROM user_device ud)", nativeQuery = true)
	public List<Device> findAllNotAssociated();
	
}
