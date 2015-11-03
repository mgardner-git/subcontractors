package com.acmecontracting.subcontractors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.acmecontracting.subcontractors.web.SessionConstants;

@Controller
public class LoginController {


	@Autowired
	SubcontractorService service;
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String performLogin(@RequestParam("emailAddress") String emailAddress, @RequestParam("password") String password) {
		Subcontractor result = service.login(emailAddress,password);
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		HttpServletRequest request = attr.getRequest();
		HttpSession session =  request.getSession(true); // true == allow create
		
		if (result == null){
			request.setAttribute(SessionConstants.LOGIN_ERROR, "User not found");
			return "forward:/login.jsp";
		}else{
			session.setAttribute(SessionConstants.USER, result);
			return "forward:/myProjects.jsp";
		}
	}
	
	@RequestMapping(value="/logout",method=RequestMethod.GET)
	public String performLogout() {
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		HttpServletRequest request = attr.getRequest();
		HttpSession session =  request.getSession(true); // true == allow create
		session.setAttribute(SessionConstants.USER, null);
		return "forward:/login.jsp";
	}
	
}
