package com.xtremefitnessapp.XtremeFitness.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.xtremefitnessapp.XtremeFitness.dao.DeviceDAO;
import com.xtremefitnessapp.XtremeFitness.model.Device;

public class DeviceController {
	
	@Autowired
	private DeviceDAO deviceDAO;
	
	@RequestMapping(value="/enrolldevice",method=RequestMethod.GET)
	public String newDevice(ModelMap model) {
		Device device = new Device();
		model.addAttribute("device",device);
		return "enrolldevice";
	}
	
	@RequestMapping(value="/savedevice",method=RequestMethod.POST)
	public String saveRegistration(@Valid Device device,BindingResult result,ModelMap model,RedirectAttributes redirectAttributes) {
		
		if(result.hasErrors()) {
			System.out.println("has errors");
			return "registerdevice";
		}
	
		deviceDAO.save(device);
		
		return "redirect:/viewdevices";
	}
	@RequestMapping(value="/viewdevices")
	public ModelAndView getAllDevices() {
		
		List<Device> list=deviceDAO.findAll();
		return new ModelAndView("/viewdevices","list",list);
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
	
	@RequestMapping(value="/deletedevice/{id}",method=RequestMethod.GET)
	public ModelAndView deleteDevice(@PathVariable int id) {
		Device device=deviceDAO.findById(id);
		deviceDAO.delete(device);
		return new ModelAndView("redirect:/viewdevices");
	}

}
