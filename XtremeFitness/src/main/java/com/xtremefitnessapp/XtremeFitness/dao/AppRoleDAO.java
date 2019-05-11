package com.xtremefitnessapp.XtremeFitness.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.xtremefitnessapp.XtremeFitness.model.UserRole;
 
@Repository
@Transactional
public class AppRoleDAO {
 
    @Autowired
    private EntityManager entityManager;
 
    public List<String> getRoleNames(Integer userId) {
        String sql = "Select ur.appRole.roleName from " + UserRole.class.getName() + " ur " //
                + " where ur.appUser.userId = :userId ";
 
        Query query = this.entityManager.createQuery(sql, String.class);
        query.setParameter("userId", userId);
        return query.getResultList();
    }
 
}