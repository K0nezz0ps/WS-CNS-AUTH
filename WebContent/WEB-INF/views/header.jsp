<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html ng-app="authApp">
	<head>
		<!-- UTF-8 -->
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<!-- Import CSS -->
		<link rel="stylesheet" type="text/css" href="/WS-MASTERE-IS/assets/styles/style.css"/>
		<!-- Compiled and minified CSS -->
 		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.100.2/css/materialize.min.css">
	    <!-- Compiled and minified JavaScript -->
	    <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.100.2/js/materialize.min.js"></script>
	   	<!-- Material icons -->
	    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
		<!-- Title -->
		<title>IngéSup - ${pageTitle}</title>
		<link rel="icon" type="image/png" href="/WS-MASTERE-IS/assets/images/favicon.png" />
		<!-- Authentication JAVASCRIPT -->
		<script src="/WS-MASTERE-IS/assets/scripts/auth.js"></script>

	</head>
	
	<header class="header-container">
		<a href="/WS-MASTERE-IS/park"><img src="/WS-MASTERE-IS/assets/images/ingesup_header_img.png" style="margin: 7px 0 7px 7px; height: 35px;"/></a>
		<c:if test="${showLogout == true}"><a class="shortcut" href="/WS-CNS-AUTH/logout"><i class="material-icons">power_settings_new</i></a></c:if>
	</header>