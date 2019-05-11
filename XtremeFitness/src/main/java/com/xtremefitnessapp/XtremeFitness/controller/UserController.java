package com.xtremefitnessapp.XtremeFitness.controller;

import java.security.Principal;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.xtremefitnessapp.XtremeFitness.dao.AppUserDAO;
import com.xtremefitnessapp.XtremeFitness.dao.DeviceDAO;
import com.xtremefitnessapp.XtremeFitness.dto.AppUserDTO;
import com.xtremefitnessapp.XtremeFitness.dto.DeviceDTO;
import com.xtremefitnessapp.XtremeFitness.model.AppUser;
import com.xtremefitnessapp.XtremeFitness.model.Device;
import com.xtremefitnessapp.XtremeFitness.utils.WebUtils;

public class UserController {


	@Autowired
	private AppUserDAO userDAO;
	
	@Autowired
	private DeviceDAO deviceDAO;
 
    @RequestMapping(value = "/userInfo", method = RequestMethod.GET)
    public String userInfo(Model model, Principal principal) {
 
        // After user login successfully.
        String userName = principal.getName();
 
        System.out.println("User Name: " + userName);
 
        User loginedUser = (User) ((Authentication) principal).getPrincipal();
 
        String userInfo = WebUtils.toString(loginedUser);
       
        
        
        AppUser user = userDAO.findUserAccount(userName);
       // List<Device> devices = deviceDAO.findAll();
        
       // System.out.println("Log user: " + user.getUserId() + " fqsfgf " + user.getUserName());
        
       // AppUser user = userDAO.findById(id);
        
       // model.addAttribute("devices", devices);
        model.addAttribute("userInfo", userInfo);
        model.addAttribute("user", user);
 
        return "userInfoPage";
    }
    
    @RequestMapping(value="/editUserInfo")
	public String editUser (Model model, Principal principal) {
		
    	String userName = principal.getName();
    	AppUser user = userDAO.findUserAccount(userName);
    	AppUserDTO ud = new AppUserDTO();
    	ud.setUserId(user.getUserId());
		ud.setUserName(user.getUserName());
		ud.setEncrytedPassword(user.getEncrytedPassword());
		model.addAttribute("userDto", ud);
		return "editUserInfoPage";		
		
	}
    
	@RequestMapping(value="/editSaveInfo",method=RequestMethod.POST)
	public String editSaveUser(@ModelAttribute("userDto") AppUserDTO p, Principal principal) {
		
		String userName = principal.getName();
		AppUser user=userDAO.findUserAccount(userName);
		user.setUserId(p.getUserId());
		user.setUserName(p.getUserName());
		user.setEncrytedPassword(p.getEncrytedPassword());
		
		userDAO.save(user);
		return "redirect:/userInfo";
	}
 
	
	@RequestMapping(value="/deleteMyAccount",method=RequestMethod.GET)
	public String deleteAccount(Principal principal) {
		String userName = principal.getName();
		AppUser user=userDAO.findUserAccount(userName);
		userDAO.delete(user);
		return "redirect:/login";
	}
	
	@RequestMapping(value = "/addDevice", method = RequestMethod.GET)
    public String addDeviceUser(Model model, Principal principal){
		
		String userName = principal.getName();
    	model.addAttribute("devices", deviceDAO.findAllNotAssociated());
		model.addAttribute("user", userDAO.findUserAccount(userName));
    	return "addUserDevice";
    }
	
    
    @RequestMapping(value="/addUserDevice", method=RequestMethod.POST)
	public String userAddDeviceAccount(@RequestParam Integer deviceId , Model model, Principal principal) {
    	
    	String userName = principal.getName();
		AppUser user = userDAO.findUserAccount(userName);
		
		System.out.println("aaaa= " + deviceId );
		
		Device device;
		device = deviceDAO.findById(deviceId);
		
		//device.setDeviceSerialNumber(p.getDeviceSerialNumber());
		
		//System.out.println("device = " + p.getDeviceSerialNumber());
		//deviceDAO.save(device);
		if (user != null) {
			if (!user.hasDevice(device)) {
				user.getDevices().add(device);
			}
			userDAO.save(user);
			model.addAttribute("user", userDAO.findUserAccount(userName));
			model.addAttribute("devices", deviceDAO.findAll());
			return "redirect:/userInfo";
		}

		return "redirect:/userInfo";
	}
	
    @RequestMapping(value = "/deleteUserDevice", method = RequestMethod.GET)
    public String deleteDeviceUser(Model model, Principal principal){
    	String userName = principal.getName();
    	model.addAttribute("devices", deviceDAO.findAll());
		model.addAttribute("user", userDAO.findUserAccount(userName));
    	return "deleteUserDevice";
    }
    
    @RequestMapping(value="/deleteUserDeviceSave", method=RequestMethod.GET)
	public String userDeleteDevice(@RequestParam Integer deviceId , Model model, Principal principal) {
    	
    	String userName = principal.getName();
    	AppUser user = userDAO.findUserAccount(userName);
    	Device device = deviceDAO.findById(deviceId);
		if (user != null) {
			if (user.hasDevice(device)) {
				user.getDevices().remove(device);
			}
			userDAO.save(user);
			model.addAttribute("user", userDAO.findUserAccount(userName));
			model.addAttribute("devices", deviceDAO.findAll());
			return "redirect:/userInfo";
		}

		return "redirect:/userInfo";
	}
	
	@RequestMapping(value="/viewusers")
	public ModelAndView getAll() {
		
		List<AppUser> list=userDAO.findAll();
		return new ModelAndView("viewusers","list",list);
	}
	
	
	@RequestMapping(value="/edituser/{id}")
	public String edit (@PathVariable int id,ModelMap model) {
		
		AppUser user=userDAO.findById(id);
		System.out.println("aaaaaaa= " + user.getUserId());
		AppUserDTO ud = new AppUserDTO();
		ud.setUserId(user.getUserId());
		ud.setUserName(user.getUserName());
		ud.setEncrytedPassword(user.getEncrytedPassword());
		model.addAttribute("userDto", ud);
		return "edituser";		
	}

	@RequestMapping(value="/editsave",method=RequestMethod.POST)
	public ModelAndView editsave(@ModelAttribute("userDto") AppUserDTO p) {
		
		
		
		System.out.println("aaaaa=" + p.getUserId() + " name= " + p.getUserName() + " password = " + p.getEncrytedPassword());
		AppUser user=userDAO.findById(p.getUserId());
		user.setUserId(p.getUserId());
		user.setUserName(p.getUserName());
		user.setEncrytedPassword(p.getEncrytedPassword());
		
		userDAO.save(user);
		return new ModelAndView("redirect:/viewusers");
		
		
	}
	
	@RequestMapping(value="/deleteuser/{id}",method=RequestMethod.GET)
	public ModelAndView delete(@PathVariable int id) {
		AppUser user = userDAO.findById(id);
		userDAO.delete(user);
		return new ModelAndView("redirect:/viewusers");
	}
	
}
