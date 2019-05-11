package com.xtremefitnessapp.XtremeFitness.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xtremefitnessapp.XtremeFitness.dto.MeasurementsDTO;
import com.xtremefitnessapp.XtremeFitness.model.Measurements;
import com.xtremefitnessapp.XtremeFitness.model.UserDevice;
import com.xtremefitnessapp.XtremeFitness.repository.MeasurementsRepository;


@Repository
@Transactional
@Service
public class MeasurementsDAO {

	@Autowired
	MeasurementsRepository measurementsRepository;
	
	@Autowired
	UserDeviceDAO userDeviceDAO;
	
	public Measurements save(Measurements std) {
		return measurementsRepository.save(std);
	}
	
	public List<Measurements> findAll() {
		return (List<Measurements>) measurementsRepository.findAll();
	}
	
	public void deleteMeasurementsForUserDeviceId(Integer device_id) {
		System.out.println("dddd = " + device_id);
		UserDevice user_device = userDeviceDAO.findUserDevice(device_id).get();
		System.out.println("ssss = " + user_device.getId());
		if(user_device != null) {
			//measurementsRepository.deleteMeasurementsForUserDeviceId(user_device.getId());
		}
	}
	
	public Measurements findById(Integer id) {
		Optional<Measurements> device = measurementsRepository.findById(id);
		if(device.isPresent()) {
			return device.get();
		} else {
			return null;
		}
	}
	public void delete(Measurements std) {
		measurementsRepository.delete(std);
	}

	public void deleteId(int id) {
		measurementsRepository.deleteById(id);
		
	}

	public List<Measurements> findAllForUserDeviceId(int selectedUserDeviceId) {
		return (List<Measurements>) measurementsRepository.findAllForUserDeviceId(selectedUserDeviceId);
	}
	
//	public List<Measurements> findAllValuesForUserDeviceId(int selectedUserDeviceId) {
//		return (List<Measurements>) measurementsRepository.findAllValuesForUserDeviceId(selectedUserDeviceId);
//	}
	
}
