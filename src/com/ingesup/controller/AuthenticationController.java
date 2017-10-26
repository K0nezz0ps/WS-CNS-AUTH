package com.ingesup.controller;

import java.util.Map;

import javax.servlet.http.Cookie;

import com.ingesup.controller.utils.HttpServletUtils;
import com.ingesup.manager.UserManager;
import com.ingesup.model.User;

public class AuthenticationController extends HttpServletUtils {

	public void login(){
		
		// 1. GET Method to display the login view form
		if(this.request.getMethod().toUpperCase().equals("GET")){
			
			boolean isConnected 	= this.request.getSession().getAttribute("isConnected") != null ? (boolean) this.request.getSession().getAttribute("isConnected") : false;
			String currentEmail  = (String)  this.request.getSession().getAttribute("email");
			
			if(isConnected && currentEmail != null){
				this.redirect("/WS-MASTERE-IS/monitor");
				return;
			}
			
			this.displayView();
			return;
		}
		
		// 2. POST Method to attempt a connection
		if(this.request.getMethod().toUpperCase().equals("POST")){
			this.loginPost(this.request.getParameterMap());
			return;
		}
		
		this.sendError(404, "Not implemented yet.");
	}
	
	/**
	 * POST Method for the Login
	 * Verify that the given email & password permit to login
	 * @param params
	 */
	public void loginPost(Map<String, String[]> params){
		
		// 1. Getting both parameters required to login
		String email = params.get("inputEmail") != null ? params.get("inputEmail")[0].trim() : null;
		String password = params.get("inputPassword") != null ? params.get("inputPassword")[0].trim() : null;
		
		// 2. In case of fail input data, display an error
		if(email == null || email.equals("") || password == null || password.equals(""))
			this.request.setAttribute("error", "Please enter an email and a password.");
		
		// 2b. Else
		else{
			
			User user = new User();
			user.setMail(email);
			user.setPassword(password);
			
			// 2b.1 Search for the requested user
			User currentUser = UserManager.get(user);
			
			// 2b.2 If this user does'nt exist, then refuse the login
			if(currentUser == null){
				this.request.setAttribute("error", "This username does'nt exist or the password is not correct.");
			}
			
			// 2b.3 Else, store informations in the session, then display the monitor
			else {

				Cookie isConnected = new Cookie("isConnected", "true");
				isConnected.setPath("/");
				Cookie userEmail   = new Cookie("email", currentUser.getMail());
				userEmail.setPath("/");
				
				this.response.addCookie(isConnected);
				this.response.addCookie(userEmail);
				
				this.redirect("/WS-MASTERE-IS/monitor");
				
				return;
			}

		}
		
		this.displayView("login");
		
	}
	
	public void logout() {
		
		// 1. Destroy Session values
		if(this.request.getCookies() != null)
			for(Cookie currentCookie : this.request.getCookies()){
				currentCookie.setValue("");
				currentCookie.setMaxAge(0);
				currentCookie.setPath("/");
				this.response.addCookie(currentCookie);
			}
		
		// 2. Attempt to redirect on the monitor (to redirect on login page)
		this.redirect("/WS-MASTERE-IS/monitor");
		
	}
}
