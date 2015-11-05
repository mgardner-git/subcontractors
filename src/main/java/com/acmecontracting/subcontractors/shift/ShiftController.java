package com.acmecontracting.subcontractors.shift;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.acmecontracting.subcontractors.Subcontractor;
import com.acmecontracting.subcontractors.project.Project;
import com.acmecontracting.subcontractors.util.SessionStuff;

/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping(value = "rest/shift")
public class ShiftController {
	
	private static final Logger logger = LoggerFactory.getLogger(ShiftController.class);
	@Autowired
	ShiftService service;
	//TODO: Controller Advisor

	@RequestMapping(value="", method=RequestMethod.POST)
	public @ResponseBody Shift create(@RequestBody Shift shift) {
		logger.info("Creating Shift: " + shift.getDescription());
		Subcontractor loggedInUser = SessionStuff.getLoggedInUser();
		shift.setSubcontractorId(loggedInUser.getId());
		Shift result = service.create(shift);
		return result;
	}
	

	@RequestMapping(value="{id}", method=RequestMethod.GET)
	public @ResponseBody Shift read(@PathVariable Integer id){
		logger.info("Reading shift " + id);
		Shift result = service.read(id);
		return result;
	}
	
	
}