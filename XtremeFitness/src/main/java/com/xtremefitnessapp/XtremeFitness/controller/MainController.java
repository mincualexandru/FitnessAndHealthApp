package com.xtremefitnessapp.XtremeFitness.controller;

import java.security.Principal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
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
import com.xtremefitnessapp.XtremeFitness.dao.MeasurementsDAO;
import com.xtremefitnessapp.XtremeFitness.dao.UserDeviceDAO;
import com.xtremefitnessapp.XtremeFitness.dto.AppUserDTO;
import com.xtremefitnessapp.XtremeFitness.dto.DeviceDTO;
import com.xtremefitnessapp.XtremeFitness.dto.MeasurementsDTO;
import com.xtremefitnessapp.XtremeFitness.model.AppUser;
import com.xtremefitnessapp.XtremeFitness.model.Device;
import com.xtremefitnessapp.XtremeFitness.model.Measurements;
import com.xtremefitnessapp.XtremeFitness.model.UnitOfMeasurement;
import com.xtremefitnessapp.XtremeFitness.model.UserDevice;
import com.xtremefitnessapp.XtremeFitness.repository.DeviceRepository;
import com.xtremefitnessapp.XtremeFitness.repository.MeasurementsRepository;
import com.xtremefitnessapp.XtremeFitness.repository.UserRepository;
import com.xtremefitnessapp.XtremeFitness.utils.WebUtils;
 
@Controller
public class MainController {
 	
	@Autowired
	private AppUserDAO userDAO;
	
	@Autowired
	private DeviceDAO deviceDAO;
	
	@Autowired
	private UserDeviceDAO userDeviceDAO;
	
	@Autowired 
	private MeasurementsDAO measurementsDAO;
	
	@Autowired 
	private MeasurementsRepository measurementsREP;
	
	@Autowired 
	private DeviceRepository deviceREP;
	
	@Autowired 
	private UserRepository userREP;
	
    @RequestMapping(value = { "/" }, method = RequestMethod.GET)
    public String welcomePage(Model model) {
        model.addAttribute("title", "Welcome");
        model.addAttribute("message", "This is welcome page!");
        return "welcomePage";
    }
    
    @RequestMapping(value = { "/welcome" }, method = RequestMethod.GET)
    public String welcome(Model model) {
        return "welcome";
    }
    
    @RequestMapping(value = { "/index" }, method = RequestMethod.GET)
    public String index(Model model) {
        return "index";
    }
    
    @RequestMapping(value = { "/about" }, method = RequestMethod.GET)
    public String aboutPage(Model model) {
        return "about";
    }
 
    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String adminPage(Model model, Principal principal) {
         
        User loginedUser = (User) ((Authentication) principal).getPrincipal();
 
        String userInfo = WebUtils.toString(loginedUser);
        model.addAttribute("userInfo", userInfo);
        
        String userName = principal.getName();
        AppUser user = userDAO.findUserAccount(userName);
        model.addAttribute("user", user);
        model.addAttribute("numberOfUsers", userREP.count());
        model.addAttribute("numberOfDevices", deviceREP.count());
        model.addAttribute("numberOfMeasurements", measurementsREP.count());
        
        return "adminPage";
    }
    
    @RequestMapping(value = { "/view" }, method = RequestMethod.GET)
	public ModelAndView view() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("view"); // resources/template/home.html
		return modelAndView;
	}
 
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage(Model model) {
 
        return "loginPage";
    }
 
    @RequestMapping(value = "/logoutSuccessful", method = RequestMethod.GET)
    public String logoutSuccessfulPage(Model model) {
        model.addAttribute("title", "Logout");
        return "logoutSuccessfulPage";
    }
 
    @RequestMapping(value = "/userInfo", method = RequestMethod.GET)
    public String userInfo(@RequestParam Integer userDeviceId, Model model, Principal principal) {
 
        // After user login successfully.
        String userName = principal.getName();
 
        System.out.println("User Name: " + userName);
 
        User loginedUser = (User) ((Authentication) principal).getPrincipal();
 
        String userInfo = WebUtils.toString(loginedUser);
        
        AppUser user = userDAO.findUserAccount(userName);
        
        if(userDeviceId != 0) {
        	int selectedUserDeviceId = userDeviceId;
        	System.out.println("ssss = " + selectedUserDeviceId);
        	List<Measurements> measurements = measurementsDAO.findAllForUserDeviceId(selectedUserDeviceId);
        	//List<Measurements> valuesOfMeasurements = measurementsDAO.findAllValuesForUserDeviceId(selectedUserDeviceId);
        	//model.addAttribute("valuesOfMeasurements", valuesOfMeasurements);
        	model.addAttribute("measurementsUD", measurements);
        	Map<Long, Float> surveyMap = new LinkedHashMap<>();
        	for (Measurements measure : measurements) {
        		Timestamp ts = new Timestamp(measure.getDateOfMeasurement().getTime());
        		System.out.println("daterrrrrrrrrrrrrr= " + ts.getTime());
        		surveyMap.put(ts.getTime(), measure.getValue());
    		}
        	model.addAttribute("surveyMap", surveyMap);
        }
        
//        for (iterable_type measure : measurements) {
//			
//		}
//    	Map<String, Float> surveyMap = new LinkedHashMap<>();
//    	surveyMap.put("10.02.2019", (float) 71);
//    	surveyMap.put("11.02.2019", (float) 71.2);
//    	surveyMap.put("12.02.2019", (float) 70.9);
//    	surveyMap.put("13.02.2019", (float) 71);
//    	surveyMap.put("14.02.2019", (float) 71.1);
//    	surveyMap.put("15.02.2019", (float) 71.5);
//    	surveyMap.put("16.02.2019", (float) 71.4);
//    	surveyMap.put("17.02.2019", (float) 71.6);
//    	surveyMap.put("19.02.2019", (float) 71.8);
//    	surveyMap.put("19.02.2019", (float) 71.7);
//    	surveyMap.put("20.02.2019", (float) 71.5);
//    	surveyMap.put("21.02.2019", (float) 71.9);
    	
    	
    	
    	//model.addAttribute("surveyMap", surveyMap);
        model.addAttribute("userInfo", userInfo);
        model.addAttribute("user", user);
        return "userInfoPage";
    }
    
    @RequestMapping(value="/deletemeasurement/{id}",method=RequestMethod.GET)
	public String deleteMeasurement(@PathVariable int id) {
		Measurements measurement = measurementsDAO.findById(id);
		measurementsDAO.delete(measurement);
		return "redirect:/userInfo?userDeviceId=0";
	}
    
    @RequestMapping(value="/editUserInfo")
	public String editUser (Model model, Principal principal) {
		
    	String userName = principal.getName();
    	AppUser user = userDAO.findUserAccount(userName);
    	AppUserDTO ud = new AppUserDTO();
    	ud.setUserId(user.getUserId());
		ud.setEmail(user.getEmail());
		ud.setMobile(user.getMobile());
		ud.setBorn_date(user.getBorn_date());
		ud.setGender(user.getGender());
		ud.setName(user.getName());
		model.addAttribute("userDto", ud);
		return "editUserInfoPage";		
		
	}
    
	@RequestMapping(value="/editSaveInfo",method=RequestMethod.POST)
	public String editSaveUser(@ModelAttribute("userDto") AppUserDTO p, Principal principal) {
		
		String userName = principal.getName();
		AppUser user=userDAO.findUserAccount(userName);
		user.setUserId(p.getUserId());
		user.setEmail(p.getEmail());
		user.setMobile(p.getMobile());
		user.setBorn_date(p.getBorn_date());
		user.setGender(p.getGender());
		user.setName(p.getName());
		userDAO.save(user);
		// ??
		return "redirect:/userInfo?userDeviceId=0";
	}
 
	
	@RequestMapping(value="/deleteMyAccount",method=RequestMethod.GET)
	public String deleteAccount(Principal principal) {
		String userName = principal.getName();
		AppUser user=userDAO.findUserAccount(userName);
		userDAO.delete(user);
		return "redirect:/login";
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
    	System.out.println("aaaa= " + deviceId);
    	UserDevice userDevice = userDeviceDAO.findByDevAndUserId(deviceId, user.getUserId());
    	System.out.println("ccc= " + userDevice.getId() );
    	if(userDevice != null) {
    		//measurementsDAO.deleteMeasurementsForUserDeviceId(userDevice.getId());
    		userDeviceDAO.delete(userDevice);
    	}
		if (user != null) {
			model.addAttribute("user", userDAO.findUserAccount(userName));
			model.addAttribute("devices", deviceDAO.findAll());
			return "redirect:/userInfo?userDeviceId=0";
		}

		return "redirect:/userInfo?userDeviceId=0";
	}
	
    @RequestMapping(value = "/403", method = RequestMethod.GET)
    public String accessDenied(Model model, Principal principal) {
    	
    	String userName = principal.getName();
 
        User loginedUser = (User) ((Authentication) principal).getPrincipal();
 
        String userInfo = WebUtils.toString(loginedUser);
        
        AppUser user = userDAO.findUserAccount(userName);
        
        model.addAttribute("user", user);
 
//        if (principal != null) {
//            User loginedUser = (User) ((Authentication) principal).getPrincipal();
// 
//            String userInfo = WebUtils.toString(loginedUser);
//            
//            AppUser user = userDAO.findUserAccount(userInfo);
// 
//            model.addAttribute("userInfo", userInfo);
// 
//            String message = "Hi " + principal.getName() //
//                    + "<br> You do not have permission to access this page!";
//            model.addAttribute("message", message);
//            model.addAttribute("user", user);
// 
//        }
 
        return "403Page";
    }
    @RequestMapping(value="/enroll",method=RequestMethod.GET)
	public String newRegistration(ModelMap model) {
		AppUser user = new AppUser();
		model.addAttribute("user",user);
		return "enroll";
	}
	
	@RequestMapping(value="/save",method=RequestMethod.POST)
	public String saveRegistration(@Valid AppUser user,BindingResult result,ModelMap model,RedirectAttributes redirectAttributes) {
		
		if(result.hasErrors()) {
			System.out.println("has errors");
			return "enroll";
		}
	
		userDAO.save(user);
		
		return "redirect:/userInfo";
	}
	
	@RequestMapping(value="/viewusers")
	public ModelAndView getAll() {
		
		List<AppUser> list=userDAO.findAll();
		return new ModelAndView("viewusers","list",list);
	}
	
	@RequestMapping(value = "information/{id}", method = RequestMethod.GET)
    public String addDevice(@PathVariable("id") Integer userId, Model model){
    	model.addAttribute("devices", deviceDAO.findAll());
		model.addAttribute("user", userDAO.findById(userId));
    	return "information";
    }
	
	
	@RequestMapping(value="/edituser/{id}")
	public String edit (@PathVariable int id,ModelMap model) {
		
		AppUser user=userDAO.findById(id);
		System.out.println("aaaaaaa= " + user.getUserId());
		AppUserDTO ud = new AppUserDTO();
		ud.setUserId(user.getUserId());
		ud.setName(user.getName());
		ud.setBorn_date(user.getBorn_date());
		ud.setEmail(user.getEmail());
		ud.setGender(user.getGender());
		ud.setMobile(user.getMobile());
		model.addAttribute("userDto", ud);
		return "edituser";		
	}

	@RequestMapping(value="/editsave",method=RequestMethod.POST)
	public ModelAndView editsave(@ModelAttribute("userDto") AppUserDTO p) {
		
		
		
		System.out.println("aaaaa=" + p.getUserId() + " name= " + p.getUserName() + " password = " + p.getEncrytedPassword());
		AppUser user=userDAO.findById(p.getUserId());
		user.setUserId(p.getUserId());
		user.setName(p.getName());
		user.setBorn_date(p.getBorn_date());
		user.setEmail(p.getEmail());
		user.setGender(p.getGender());
		user.setMobile(p.getMobile());		
		userDAO.save(user);
		return new ModelAndView("redirect:/viewusers");
		
		
	}
	
	@RequestMapping(value="/deleteuser/{id}",method=RequestMethod.GET)
	public ModelAndView delete(@PathVariable int id) {
		AppUser user = userDAO.findById(id);
		userDAO.delete(user);
		return new ModelAndView("redirect:/viewusers");
	}
 
	@RequestMapping(value="/enrolldevice",method=RequestMethod.GET)
	public String newDevice(ModelMap model) {
		DeviceDTO device = new DeviceDTO();
		model.addAttribute("device",device);
		return "enrolldevice";
	}
	
	@RequestMapping(value="/savedevice",method=RequestMethod.POST)
	public String saveRegistration(ModelMap model,RedirectAttributes redirectAttributes, @ModelAttribute("device") DeviceDTO pDevice ) {
		
		Device device = new Device();
		
		device.setCompanyName(pDevice.getCompanyName());
		device.setDeviceName(pDevice.getDeviceName());
		
		deviceDAO.save(device);
		
		return "redirect:/viewdevices";
	}
	@RequestMapping(value="/viewdevices")
	public ModelAndView getAllDevices() {
		
		List<Device> list=deviceDAO.findAll();
		return new ModelAndView("/viewdevices","list",list);
	}
	
	
	
	@RequestMapping(value="/deletedevice/{id}",method=RequestMethod.GET)
	public ModelAndView deleteDevice(@PathVariable int id) {
		Device device=deviceDAO.findById(id);
		deviceDAO.delete(device);
		return new ModelAndView("redirect:/viewdevices");
	}
    
    @RequestMapping(value = "/addDevice", method = RequestMethod.GET)
    public String addDevice(Model model, Principal principal){
		
		String userName = principal.getName();
    	model.addAttribute("devices", deviceDAO.findAllNotAssociated());
		model.addAttribute("user", userDAO.findUserAccount(userName));
    	return "addUserDevice";
    }
	
    
    @RequestMapping(value="/addUserDevice", method=RequestMethod.POST)
	public String addUserDeviceAccount(@RequestParam Integer deviceId , Model model, Principal principal) {
    	
    	String userName = principal.getName();
		AppUser user = userDAO.findUserAccount(userName);
		System.out.println("aaaa= " + deviceId );
		Device device;
		device = deviceDAO.findById(deviceId);
		UserDevice userDevice = new UserDevice();
		//device.setDeviceSerialNumber(p.getDeviceSerialNumber());
		//System.out.println("device = " + p.getDeviceSerialNumber());
		//deviceDAO.save(device);
		if (user != null) {
			userDevice.setDevice(device);
			userDevice.setUser(user);
			userDeviceDAO.save(userDevice);
			model.addAttribute("user", userDAO.findUserAccount(userName));
			model.addAttribute("devices", deviceDAO.findAll());
			return "redirect:/userInfo?userDeviceId=0";
		}
		return "redirect:/userInfo?userDeviceId=0";
	}
	
	@RequestMapping(value = "addUserDevice/{id}", method = RequestMethod.GET)
    public String addDevice(@PathVariable("id") Integer userId , @PathVariable("id") Integer deviceId, Model model){
    	//model.addAttribute("devices", deviceDAO.findAll());
    	model.addAttribute("devices", deviceDAO.findAllNotAssociated());
		model.addAttribute("user", userDAO.findById(userId));
		model.addAttribute("device", deviceDAO.findById(deviceId));
    	return "adminAddDeviceUser";
    }
	
    
    @RequestMapping(value="/user/{id}/devices", method=RequestMethod.POST)
	public String userAddDevice(@PathVariable Integer id, @RequestParam Integer deviceId, Model model, @ModelAttribute("device") DeviceDTO p) {
    	
    	AppUser user = userDAO.findById(id);
    	Device device;
    	device = deviceDAO.findById(deviceId);
    	UserDevice userDevice = new UserDevice();
		//Device device1 = deviceDAO.findById(deviceId);
		//System.out.println("aaaa= " + deviceId);
		//Device device = deviceDAO.findById(deviceId);
		//device.setDeviceSerialNumber(p.getDeviceSerialNumber());
		//System.out.println("bbbb= " + p.getDeviceSerialNumber());
		//System.out.println("name= " + device.getDeviceName());
		//deviceDAO.save(device);
		if (user != null) {
			userDevice.setDevice(device);
			userDevice.setUser(user);
			userDeviceDAO.save(userDevice);
			model.addAttribute("user", userDAO.findById(id));
			model.addAttribute("devices", deviceDAO.findAll());
			return "redirect:/viewusers";
		}

		return "redirect:/viewusers";
	}
    
    @RequestMapping(value = "/deleteUserDevice/{id}", method = RequestMethod.GET)
    public String deleteDeviceUserId(@PathVariable("id") Integer userId , @PathVariable("id") Integer deviceId, Model model){
    	model.addAttribute("devices", deviceDAO.findAll());
    	model.addAttribute("user", userDAO.findById(userId));
		model.addAttribute("device", deviceDAO.findById(deviceId));
    	return "adminDeleteUserDevice";
    }
    
    @RequestMapping(value="/deleteUserDeviceSave/{id}/devices", method=RequestMethod.POST)
	public String userDeleteDeviceId(@PathVariable Integer id, @RequestParam Integer deviceId, Model model, @ModelAttribute("device") DeviceDTO p) {
    	
    	UserDevice userDevice = userDeviceDAO.findByDevAndUserId(deviceId, id);
		if (userDevice != null) {
			userDeviceDAO.delete(userDevice);
			model.addAttribute("user", userDAO.findById(id));
			model.addAttribute("devices", deviceDAO.findAll());
			return "redirect:/viewusers";
		}

		return "redirect:/viewusers";
	}
    
    
    @RequestMapping(value="/editdevice/{id}")
	public String editDevice(@PathVariable int id,ModelMap model) {
		
		Device device=deviceDAO.findById(id);
		model.addAttribute("device", device);
		return "editdevice";
	}
	
	@RequestMapping(value="/editsavedevice",method=RequestMethod.POST)
	public ModelAndView editsaveDevice(@ModelAttribute("device") Device p) {
		
		Device device=deviceDAO.findById(p.getDevice_id());
		
		device.setDeviceName(p.getDeviceName());
		device.setCompanyName(p.getCompanyName());
		device.setDeviceDescription(p.getDeviceDescription());
		device.setDeviceSerialNumber(p.getDeviceSerialNumber());
		
		deviceDAO.save(device);
		return new ModelAndView("redirect:/viewdevices");
	}
	
	@RequestMapping(value="/editdeviceuser/{id}")
	public String editDeviceUser(@PathVariable int id,ModelMap model) {
		
		Device device=deviceDAO.findById(id);
		model.addAttribute("device", device);
		return "edituserdevice";
	}
	
	@RequestMapping(value="/editsavedeviceuser",method=RequestMethod.POST)
	public ModelAndView editsaveDeviceUser(@ModelAttribute("device") Device p) {
		
		Device device=deviceDAO.findById(p.getDevice_id());
		
		device.setDeviceName(p.getDeviceName());
		device.setCompanyName(p.getCompanyName());
		device.setDeviceDescription(p.getDeviceDescription());
		device.setDeviceSerialNumber(p.getDeviceSerialNumber());
		
		deviceDAO.save(device);
		return new ModelAndView("redirect:/userInfo?userDeviceId=0");
	}
	
    @RequestMapping(value="/enrollmeasurement/{id}")
	public String newMeasurement(@PathVariable("id") Integer deviceId, ModelMap model) {
    	MeasurementsDTO measurement = new MeasurementsDTO();
    	model.addAttribute("measurement", measurement);
    	model.addAttribute("unitsOfMeasurements", UnitOfMeasurement.values());
    	model.addAttribute("deviceId", deviceId);
    	return "enrollmeasurement";
	}
	
	@RequestMapping(value="/savemeasurement",method=RequestMethod.POST)
	public String saveMeasurement(@ModelAttribute("measurement") MeasurementsDTO pMeasurement, @RequestParam Integer deviceId) {
		
		Measurements measurement = new Measurements();
		
		System.out.println("ssss=" + deviceId);
		
		Optional<UserDevice> userDevice = userDeviceDAO.findUserDevice(deviceId);
		
		System.out.println("aaaaa = " + userDevice.get().getId());
		
		
		measurement.setUserDevice(userDevice.get());
		measurement.setValue(pMeasurement.getValue());
		measurement.setUnitOfMeasurement(pMeasurement.getUnitOfMeasurement());
		measurement.setDateOfMeasurement(pMeasurement.getDateOfMeasurement());
		
		measurementsDAO.save(measurement);
		return "redirect:/userInfo?userDeviceId=0";
	}
	@RequestMapping(value="/viewmeasurements")
	public ModelAndView getAllMeasurements() {
		
		List<Measurements> list=measurementsDAO.findAll();
		return new ModelAndView("/viewmeasurements","list",list);
	}
	
	@RequestMapping(value="/editmeasurement/{id}")
	public String editMeasurement(@PathVariable int id,ModelMap model) {
		
		Measurements measurement = measurementsDAO.findById(id);
		model.addAttribute("measurement", measurement);
		model.addAttribute("unitsOfMeasurements", UnitOfMeasurement.values());
		return "editmeasurement";
	}
	
	@RequestMapping(value="/editsavemeasurement",method=RequestMethod.POST)
	public ModelAndView editsaveMeasurement(@ModelAttribute("measurement") Measurements p) {
		
		Measurements measurement = measurementsDAO.findById(p.getId());
		
		measurement.setValue(p.getValue());
		measurement.setUnitOfMeasurement(p.getUnitOfMeasurement());
		measurement.setDateOfMeasurement(p.getDateOfMeasurement());
		
		measurementsDAO.save(measurement);
		return new ModelAndView("redirect:/viewmeasurements");
	}
	
	
	
	@RequestMapping(value = "/addUserDeviceMeasurement", method = RequestMethod.GET)
    public String addMeasurement(Model model, Principal principal){
		
		String userName = principal.getName();
    	model.addAttribute("devices", deviceDAO.findAllNotAssociated());
		model.addAttribute("user", userDAO.findUserAccount(userName));
    	return "addUserDeviceMeasurement";
    }
	
    
    @RequestMapping(value="/addUserDeviceMeasurementSave", method=RequestMethod.POST)
	public String measurementAccount(@RequestParam Integer deviceId , Model model, Principal principal) {
    	
    	String userName = principal.getName();
		AppUser user = userDAO.findUserAccount(userName);
		System.out.println("aaaa= " + deviceId );
		Device device;
		device = deviceDAO.findById(deviceId);
		UserDevice userDevice = new UserDevice();
		//device.setDeviceSerialNumber(p.getDeviceSerialNumber());
		//System.out.println("device = " + p.getDeviceSerialNumber());
		//deviceDAO.save(device);
		if (user != null) {
			userDevice.setDevice(device);
			userDevice.setUser(user);
			userDeviceDAO.save(userDevice);
			model.addAttribute("user", userDAO.findUserAccount(userName));
			model.addAttribute("devices", deviceDAO.findAll());
			return "redirect:/userInfo";
		}
		return "redirect:/userInfo";
	}
	
}
