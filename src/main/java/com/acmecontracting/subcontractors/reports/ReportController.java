package com.acmecontracting.subcontractors.reports;

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
import com.acmecontracting.subcontractors.util.SessionStuff;

/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping(value = "rest/report")
public class ReportController {
	
	private static final Logger logger = LoggerFactory.getLogger(ReportController.class);
	@Autowired
	ReportService service;

	@RequestMapping(value="", method=RequestMethod.POST)
	public @ResponseBody Report create(@RequestBody Report report) {
		logger.info("Creating Report on Project: " + report.getProject().getName());
		Report result = service.create(report);
		return result;
	}
	
	@RequestMapping(value="myReports/{projectId}", method=RequestMethod.GET)
	public @ResponseBody List<Report> getReportsOnProject(@PathVariable Integer projectId){
		Subcontractor loggedInUser = SessionStuff.getLoggedInUser();
		logger.info("Reading my reports for project " + projectId + " and subcontractor "  + loggedInUser.getEmailAddress());
		List<Report> results = service.readReports(loggedInUser.getId(), projectId);
		return results;
		
	}
	@RequestMapping(value="{id}", method=RequestMethod.GET)
	public @ResponseBody Report read(@PathVariable Integer id) {
		logger.info("Reading report " + id);
		Report result = service.read(id);
		return result;
	}
	

	@RequestMapping(value="{id}", method=RequestMethod.PUT)
	public @ResponseBody Report update(@RequestBody Report report) {
		logger.info("Updating report: " + report.getId());
		Report result = service.update(report);
		return result;
	}
	
	@RequestMapping(value="{id}", method=RequestMethod.DELETE)
	public @ResponseBody boolean delete(@PathVariable Integer id) {
		logger.info("Deleting report: " + id);
		boolean result = service.delete(id);
		return result;
		
	}
}
