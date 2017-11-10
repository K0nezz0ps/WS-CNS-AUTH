<jsp:include page="header.jsp"/>

	<body ng-controller="securityCenterController">
	
		<div class="park-title">
			<h3>Security Center</h3>
			<i>Create, Delete, Modify users.</i>
		</div>
		
		<div style="margin-top: 50px; margin-bottom: 100px; text-align: center;" class="container">
		
			<table style="margin-left: auto; margin-right: auto;" id="user-table">
			
				<thead>
					<th style="text-align: center; width: 20px;">N°</th>
					<th style="width: 50px;">E-Mail</th>
					<th style="width: 30px;">Park(s)</th>
					<th style="width: 20px;"></th>
				</thead>
			
				<tbody>
					<tr style="border-bottom: 1px solid #d0d0d0;" ng-repeat="user in userList">
						<td style="text-align: center;">{{user.id}}</td>
						<td>{{user.mail}}</td>
						
						<!-- Select park to manage -->
						<td>
							<select ng-model="selectedParkToAttribut">
								<option ng-repeat="park in parkList" ng-value="park">{{park.name}}</option>
							</select>
						</td>
						
						<td style="text-align: center;">
							<i style="color: red; cursor: pointer;" ng-click="displayAlert(user)" class="material-icons">cancel</i>
						</td>
					</tr>
				</tbody>
			</table>
			
		</div>
		
		<div id="user-creation-container" style="margin-top:20px; margin-bottom: 20px;" class="container">
		
			<div class="input-field" style="margin-left: auto; margin-right: auto; width: 450px;">
       			<input id="userEmail" placeholder="Email" ng-model="input.userEmail" type="text" class="validate" style="width: 200px; margin-right: 10px;">
       			<label for="userEmail"></label> <br>
       			
       			<input id="userPassword" placeholder="Password" ng-model="input.userPassword" type="password" class="validate" style="width: 200px; margin-right: 10px;">
       			<label for="userPassword"></label>
       			
       			<input id="userPasswordConfirm" placeholder="Password Confirm" ng-model="input.userPasswordConfirm" type="password" class="validate" style="width: 200px; margin-right: 10px;">
       			<label for="userPasswordConfirm"></label> <br>
       			
       			<a ng-click="createUser()" class="waves-effect waves-light btn">Create</a>
       			<span ng-if="showCreateError" style="color: red; font-size: 12px;">{{errorCreateMessage}}</span>
       			<span ng-if="showCreateValidation" style="color: green; font-size: 12px;">{{validationCreateMessage}}</span>
       		</div>
		
		</div>
	
	
<jsp:include page="footer.jsp"/>