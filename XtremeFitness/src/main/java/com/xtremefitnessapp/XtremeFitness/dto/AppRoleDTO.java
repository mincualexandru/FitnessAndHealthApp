package com.xtremefitnessapp.XtremeFitness.dto;
 
public class AppRoleDTO {
     
    private Integer roleId;
    private String roleName;
 
    
    public AppRoleDTO() {
	}

    
	public AppRoleDTO(Integer roleId, String roleName) {
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
