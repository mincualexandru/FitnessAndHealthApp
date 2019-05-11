package com.xtremefitnessapp.XtremeFitness.dao;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.xtremefitnessapp.XtremeFitness.model.AppUser;
import com.xtremefitnessapp.XtremeFitness.repository.UserRepository;
 
@Repository
@Transactional
public class AppUserDAO {
 
    @Autowired
    private EntityManager entityManager;
 
    public AppUser findUserAccount(String userName) {
        try {
            String sql = "Select e from " + AppUser.class.getName() + " e " //
                    + " Where e.userName = :userName ";
 
            Query query = entityManager.createQuery(sql, AppUser.class);
            query.setParameter("userName", userName);
 
            return (AppUser) query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
 
    @Autowired
	UserRepository userRepository;
    
    public AppUser save(AppUser std) {
		return userRepository.save(std);
	}
    public List<AppUser> findAll() {
		return (List<AppUser>) userRepository.findAll();
	}
	public AppUser findById(int id) {
		Optional<AppUser> user = userRepository.findById(id);
		if(user.isPresent()) {
			return user.get();
		} else {
			return null;
		}
	}
	public void delete(AppUser std) {
		userRepository.delete(std);
	}
	
    
}