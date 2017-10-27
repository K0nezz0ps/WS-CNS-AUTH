package com.ingesup.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ingesup.controller.utils.ControllerUtils;
import com.ingesup.manager.UserManager;
import com.ingesup.model.User;

@Controller
public class AuthenticationControllerSpring {
	
	/**
	 * Display the login view or redirect if connected
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/login", method = RequestMethod.GET)
	public String login(Model model, HttpServletRequest request, HttpServletResponse response) {
		
		// 1. Validating the user
		if(ControllerUtils.validateUser(request)){
			ControllerUtils.redirect("/WS-MASTERE-IS/park", response);
			return null;
		}
        
        return "/authentication/login";
	}
	
	/**
	 * POST Method to attempt a connexion
	 * @param email
	 * @param password
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/login", method = RequestMethod.POST)
	public String login(@RequestParam("inputEmail") String email, @RequestParam("inputPassword") String password, Model model ,HttpServletRequest request, HttpServletResponse response) {
		
		// 1. In case of fail input data, display an error
		if(email == null || email.equals("") || password == null || password.equals(""))
			model.addAttribute("error", "Please enter an email and a password.");
		else{
			
			User user = new User();
			user.setMail(email);
			user.setPassword(password);
			
			// 2.1 Search for the requested user
			User currentUser = UserManager.get(user);
			
			// 2.2 If this user does'nt exist, then refuse the login
			if(currentUser == null)
				model.addAttribute("error", "This username does'nt exist or the password is not correct.");
			// 2.3 Else, store informations in the session, then display the monitor
			else {

				Cookie isConnected = new Cookie("isConnected", "true");
				isConnected.setPath("/");
				isConnected.setMaxAge(600);
				Cookie userEmail   = new Cookie("email", currentUser.getMail());
				userEmail.setPath("/");
				userEmail.setMaxAge(600);
				
				response.addCookie(isConnected);
				response.addCookie(userEmail);
				
				ControllerUtils.redirect("/WS-MASTERE-IS/park", response);

				return null;
			}

		}
		
		return "/authentication/login";
	}
	
	/**
	 * Logout / Destroy the current session
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/logout", method = RequestMethod.GET)
	public String login(HttpServletRequest request, HttpServletResponse response) {
		
		// 1. Destroy Session values
		if(request.getCookies() != null)
			for(Cookie currentCookie : request.getCookies()){
				currentCookie.setValue("");
				currentCookie.setMaxAge(0);
				currentCookie.setPath("/");
				response.addCookie(currentCookie);
			}
		
		// 2. Attempt to redirect on the monitor (to redirect on login page)
		ControllerUtils.redirect("/WS-MASTERE-IS/park", response);
		
		return "/authentication/login";
		
	}

}
