package com.xtremefitnessapp.XtremeFitness.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xtremefitnessapp.XtremeFitness.model.Device;
import com.xtremefitnessapp.XtremeFitness.model.UserDevice;
import com.xtremefitnessapp.XtremeFitness.repository.DeviceRepository;
import com.xtremefitnessapp.XtremeFitness.repository.UserDeviceRepository;


@Repository
@Transactional
@Service
public class UserDeviceDAO {

	@Autowired
	UserDeviceRepository userDeviceRepository;
	
	public UserDevice save(UserDevice std) {
		return userDeviceRepository.save(std);
	}
	
	public List<UserDevice> findAll() {
		return (List<UserDevice>) userDeviceRepository.findAll();
	}
	
	public UserDevice findById(Integer id) {
		Optional<UserDevice> device = userDeviceRepository.findById(id);
		if(device.isPresent()) {
			return device.get();
		} else {
			return null;
		}
	}
	
	public UserDevice findByDevAndUserId(Integer deviceId, Integer userId) {
		Optional<UserDevice> device = userDeviceRepository.findByDevAndUserId(deviceId, userId);
		if(device.isPresent()) {
			return device.get();
		} else {
			return null;
		}
	}
	
	public Optional<UserDevice> findUserDevice(Integer device_id) {
		Optional<UserDevice> userDevice = userDeviceRepository.findUserDevice(device_id);
		return userDevice;
	}
	
	public void delete(UserDevice std) {
		userDeviceRepository.delete(std);
	}

	public void deleteId(int id) {
		userDeviceRepository.deleteById(id);
		
	}
	
}
