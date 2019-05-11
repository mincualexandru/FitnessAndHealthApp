package com.xtremefitnessapp.XtremeFitness.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
 
@Entity
@Table(name = "App_Role", //
        uniqueConstraints = { //
                @UniqueConstraint(name = "APP_ROLE_UK", columnNames = "Role_Name") })
public class AppRole {
     
    @Id
    @GeneratedValue
    @Column(name = "Role_Id", nullable = false)
    private Integer roleId;
 
    @Column(name = "Role_Name", length = 30, nullable = false)
    private String roleName;
 
    public AppRole() {
	}

    
	public AppRole(Integer roleId, String roleName) {
		this.roleId = roleId;
		this.roleName = roleName;
	}
    
    public Integer getRoleId() {
        return roleId;
    }
 
    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }
 
    public String getRoleName() {
        return roleName;
    }
 
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
     
}
