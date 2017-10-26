package com.ingesup.controller.utils;

import java.lang.reflect.Method;
import java.util.Arrays;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class HttpServletUtils extends HttpServlet {

	protected String action 				= "";
	protected String view 					= "";
	protected HttpServletRequest request 	= null;
	protected HttpServletResponse response 	= null; 
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
		
		this.action 	= request.getPathInfo();
		this.view		= this.action != null ? this.action.substring(1).toLowerCase() : null;
		this.request 	= request;
		this.response 	= response;
		
		if(this.view == null || this.view.length() == 0) {
			this.view = this.getClass().getMethods()[0].getName();
		}
			
		Method currentMethod = Arrays.asList(this.getClass().getMethods()).stream().filter(x -> x.getName().equals(this.view)).findFirst().orElse(null);

		try {
			currentMethod.invoke(this, null);
		} catch (Exception e3) {
			e3.printStackTrace();
			this.sendError(404, "Not implemented yet.");
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		
		this.action 	= request.getPathInfo();
		this.view		= this.action != null ? this.action.substring(1).toLowerCase() : null;
		this.request 	= request;
		this.response 	= response;
			
		Method currentMethod = Arrays.asList(this.getClass().getMethods()).stream().filter(x -> x.getName().equals(this.view)).findAny().orElse(null);

		try {
			currentMethod.invoke(this, null);
		} catch (Exception e) {
			e.printStackTrace();
			this.sendError(404, "Not implemented yet.");
		}
	}

	protected void displayView(final String viewName, String controllerName) {

		if(controllerName == null)
			controllerName = this.getClass().getSimpleName();
		
		controllerName = controllerName.substring(0, controllerName.lastIndexOf("Controller")).toLowerCase();

		final String path = "/WEB-INF/views/" + controllerName + "/" + viewName + ".jsp";
		
		try{
			this.request.getRequestDispatcher(path).forward(this.request, this.response);
		} catch(Exception e){
			e.printStackTrace();
		}
		 
		return;
		
	}
	
	protected void displayView(final String viewName) {
		displayView(viewName, null);
	}

	protected void displayView(Object model){
		this.request.setAttribute("model", model);
		displayView(this.view);
	}
	
	protected void displayView() {
		this.displayView(this.view);
	}

	protected String getParameter(final String paramName) {
		
		return this.request.getParameter(paramName) != null ? this.request.getParameter(paramName) : "";
		
	}

	protected Integer getParameterAsInteger(final String paramName) {
		
		final String param = getParameter(paramName);
		
		try{
			return Integer.parseInt(param);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	protected Float getParameterAsFloat(final String paramName) {
		
		final String param = getParameter(paramName);
		
		try{
			return Float.parseFloat(param);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}

	protected void redirect(String uri){
		
		try{
			this.response.sendRedirect(uri);
		} catch (Exception e){
			e.printStackTrace();
		}
		
	}

	protected void sendError(Integer code, String errorMessage){
		
		try{
			this.response.sendError(code, errorMessage);
		} catch (Exception e){
			e.printStackTrace();
		}
		
	}
}


