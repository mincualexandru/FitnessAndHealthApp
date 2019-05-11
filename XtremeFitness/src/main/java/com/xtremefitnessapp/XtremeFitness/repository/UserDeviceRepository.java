package com.xtremefitnessapp.XtremeFitness.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.xtremefitnessapp.XtremeFitness.model.UserDevice;

public interface UserDeviceRepository extends CrudRepository<UserDevice, Integer> {

	@Query(value="SELECT * FROM user_device WHERE device_Id = ?1 AND User_Id = ?2", nativeQuery= true)
	Optional<UserDevice> findByDevAndUserId(Integer deviceId, Integer userId);

	@Query(value="SELECT * FROM user_device WHERE device_Id = ?1", nativeQuery= true)
	Optional<UserDevice> findUserDevice(Integer device_id);

}
