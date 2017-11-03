<jsp:include page="header.jsp"/>
	
	<body>
	
	<div class="park-title">
		<h3>Dashboard Authentication</h3>
		<i>Connect yourself to access on the administration pages</i>
	</div>
	
		<div style="margin-top: 50px; margin-bottom: 150px; width: 400px; text-align: center;" class="container">
		
			<form method="POST" action="/WS-CNS-AUTH/login">
				<div><label style="float: left;">E-mail :</label> <input type="text" placeholder="Your username" name="inputEmail" /></div>
				<div><label style="float: left;">Password :</label> <input type="password" placeholder="Your password" name="inputPassword" /></div>
				
				<button style="margin-top: 20px;" class="btn waves-effect waves-light" type="submit" name="action">Connect<i class="material-icons right">verified_user</i></button>
			</form>
			
			<p style="color: red;">${error}</p>
			<p style="color: green;">${validation}</p>
			
		</div>

<jsp:include page="footer.jsp"/>