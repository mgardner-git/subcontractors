package com.acmecontracting.subcontractors.shift;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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

	

	@RequestMapping(value="{id}", method=RequestMethod.GET)
	public @ResponseBody Shift read(@PathVariable Integer id){
		logger.info("Reading shift " + id);
		Shift result = service.read(id);
		return result;
	}
}