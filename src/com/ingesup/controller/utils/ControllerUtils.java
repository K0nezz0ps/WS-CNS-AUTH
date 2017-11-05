package com.ingesup.controller.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ingesup.hibernate.UserManager;

public class ControllerUtils {
	
	/**
	 * Redirection function
	 * @param uri
	 * @param response
	 */
	public static void redirect(String uri, HttpServletResponse response){
		
		try{
			response.sendRedirect(uri);
		} catch (Exception e){
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Return the boolean accorded to the active session status
	 * @param request
	 * @return
	 */
	public static boolean isValidUser(HttpServletRequest request) {
		
		// 1. Getting cookies
		Cookie[] cookieList = request.getCookies();
		
		// 2. Trying to find the "isConnected" cookie
		Cookie sessionCookie  = null;
		Cookie emailCookie    = null;
		
		if(cookieList != null)
			// ForEach cookie, trying to get the interested ones 
			for(Cookie currentCookie : cookieList){		
				switch(currentCookie.getName()){
					case "isConnected": sessionCookie = currentCookie;
										break;
					case "userEmail"  : emailCookie   = currentCookie;
										break;
					default: break;
				}
			}
		
		// 3. Checking that each previous wanted cookies are not null, then, checking if the user attached to the email & password exist
		if( sessionCookie == null 
				|| !sessionCookie.getValue().equals("true") 
				|| emailCookie == null 
				|| UserManager.getUser(emailCookie.getValue()) == null)
			return false;
		
		return true;
	}
	
	/**
	 * Return the cookie attached to the email in session
	 * @param req
	 * @return
	 */
	public static String getCookieEmail(HttpServletRequest req) {
		Cookie[] cookies = req.getCookies();
		Cookie emailCookie = null;
		if(cookies != null) {
			for(Cookie cookie : cookies) {
				if(cookie.getName().equals("email")) {
					emailCookie = cookie;
					break;
				}
			}
		}
		
		if(emailCookie != null)
			return emailCookie.getValue();
		
		return null;
	}

}