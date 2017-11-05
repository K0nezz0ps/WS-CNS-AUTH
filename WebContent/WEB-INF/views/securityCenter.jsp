<jsp:include page="header.jsp"/>

	<body ng-controller="securityCenterController">
	
		<div class="park-title">
			<h3>Security Center</h3>
			<i>Create, Delete, Modify users.</i>
		</div>
		
		<div style="margin-top: 50px; margin-bottom: 150px; width: 400px; text-align: center;" class="container">
		
			<table id="user-table">
			
				<thead><th>N°</th><th>E-Mail</th><th></th></thead>
			
				<tbody>
					<tr ng-repeat="user in userList"><td>{{user.id}}</td><td>{{user.email}}</td></tr>
				</tbody>
			</table>
			
		</div>
	
	
<jsp:include page="footer.jsp"/>