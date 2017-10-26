<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
	
		<!-- UTF-8 -->
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		
		<!-- Import for JQuery -->
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
		
		<!-- Title -->
		<title>IngéSup - ${title}</title>
	</head>

	<header>
		JEE Mastère IngéSup Header
	</header>

	<body>
	
		<form method="POST" action="/WS-CNS-AUTH/auth/login">
			<div><label>E-mail :</label> <input type="text" placeholder="Your username" name="inputEmail" /></div>
			<div><label>Password :</label> <input type="password" placeholder="Your password" name="inputPassword" /></div>
			<div><input type="submit" value="Connect" /></div>
		</form>
		
		<p style="color: red;">${error}</p>
		<p style="color: green;">${validation}</p>

	</body>

	<footer>
		JEE Mastère Footer IngéSup
	</footer>

</html>