package com.ingesup.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.ingesup.controller.utils.ControllerUtils;

@Controller
public class SecurityCenterControllerSpring {

	@RequestMapping(value="/securityCenter", method = RequestMethod.GET)
	public String userCenter(Model model, HttpServletRequest request, HttpServletResponse response){
		
		// 1. Validating user
		if(!ControllerUtils.isValidUser(request)){
			ControllerUtils.redirect("/WS-CNS-AUTH/login", response);
			return null;
		}
		
		// 2. Settings the default models
		model.addAttribute("pageTitle", "SecurityCenter");
		model.addAttribute("showLogout", true);
		
		return "securityCenter";
	}
	
}
