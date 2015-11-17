package com.acmecontracting.subcontractors.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.acmecontracting.subcontractors.Subcontractor;

public class SessionStuff {

	public static final String LOGIN_ERROR = "LOGIN_ERROR";
	public static final String USER = "USER";
	
	public static Subcontractor getLoggedInUser() {	
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		HttpServletRequest request = attr.getRequest();
		HttpSession session =  request.getSession(true); // true == allow create
		Subcontractor user = (Subcontractor)session.getAttribute(USER);
		return user;

	}
}

