package com.xtremefitnessapp.XtremeFitness.dao;


import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xtremefitnessapp.XtremeFitness.model.AppUser;
import com.xtremefitnessapp.XtremeFitness.model.Device;
import com.xtremefitnessapp.XtremeFitness.repository.DeviceRepository;


@Repository
@Transactional
@Service
public class DeviceDAO {
	
	@Autowired
    private EntityManager entityManager;
 
    public Device findUserDevice(String deviceName) {
        try {
            String sql = "Select e from " + Device.class.getName() + " e " //
                    + " Where e.deviceName = :deviceName ";
 
            Query query = entityManager.createQuery(sql, Device.class);
            query.setParameter("deviceName", deviceName);
 
            return (Device) query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

	@Autowired
	DeviceRepository deviceRepository;
	
	public Device save(Device std) {
		return deviceRepository.save(std);
	}
	
	public List<Device> findAll() {
		return (List<Device>) deviceRepository.findAll();
	}
	
	public List<Device> findAllNotAssociated() {
		return (List<Device>) deviceRepository.findAllNotAssociated();
	}
	
	public Device findById(Integer id) {
		Optional<Device> device = deviceRepository.findById(id);
		if(device.isPresent()) {
			return device.get();
		} else {
			return null;
		}
	}
	public void delete(Device std) {
		deviceRepository.delete(std);
	}

	public void deleteId(int id) {
		deviceRepository.deleteById(id);
		
	}
	
}
