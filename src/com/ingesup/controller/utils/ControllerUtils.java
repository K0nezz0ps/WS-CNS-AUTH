package com.ingesup.controller.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
	public static boolean validateUser(HttpServletRequest request) {
		
		// 1. Getting cookies
		Cookie[] cookieList = request.getCookies();
		
		// 2. Trying to find the "isConnected" cookie
		Cookie requestCookie = null;
		if(cookieList != null)
			for(Cookie currentCookie : cookieList){
				if(currentCookie.getName().equals("isConnected")){
					requestCookie = currentCookie;
					break;
				}
			}
		
		// 3. Checking that the cookie value is set to true
		if( requestCookie == null || !requestCookie.getValue().equals("true") )
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