package com.acmecontracting.subcontractors.project;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping(value = "rest/project")
public class ProjectController {
	
	private static final Logger logger = LoggerFactory.getLogger(ProjectController.class);
	@Autowired
	ProjectService service;
	//TODO: Controller Advisor

	@RequestMapping(value="", method=RequestMethod.POST)
	public @ResponseBody Project create(@RequestBody Project project) {
		logger.info("Creating Project: " + project.getName());
		Project result = service.create(project);
		return result;
	}
	
	@RequestMapping(value="{id}", method=RequestMethod.GET)
	public @ResponseBody Project read(@PathVariable Integer id) {
		logger.info("Reading project " + id);
		Project result = service.read(id);
		return result;
	}

	@RequestMapping(value="{id}", method=RequestMethod.PUT)
	public @ResponseBody Project update(@RequestBody Project project) {
		logger.info("Updating project: " + project.getId());
		Project result = service.update(project);
		return result;
	}
	
	@RequestMapping(value="{id}", method=RequestMethod.DELETE)
	public @ResponseBody boolean delete(@PathVariable Integer id) {
		logger.info("Deleting project: " + id);
		boolean result = service.delete(id);
		return result;
		
	}
}
