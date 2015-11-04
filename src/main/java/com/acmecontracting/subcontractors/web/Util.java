package com.acmecontracting.subcontractors.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.acmecontracting.subcontractors.Subcontractor;

public class Util {

	public static Subcontractor getLoggedInUser() {
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		HttpServletRequest request = attr.getRequest();
		HttpSession session =  request.getSession(true); // true == allow create
		Subcontractor loggedInUser = (Subcontractor)session.getAttribute(SessionConstants.USER);
		return loggedInUser;
	}
}
