var authApp = angular.module("authApp", []);

authApp.controller("securityCenterController", function($scope, $rootScope, $http){
	
	$scope.userList     = null;
	$scope.parkList		= null;
	$scope.selectedUser = null;
	$scope.input = {};
	$scope.showCreateError = false;
	$scope.showCreateValidation = false;
	$scope.errorCreateMessage = "";
	$scope.validationCreateMessage = "";
	
	// Loading all the User List
	$http.get("/WS-CNS-AUTH/rest/User")
		.then(function(response){

			$scope.userList = response.data;
		
		});
	
	// Loading all the parks
	$http.get("WS-MASTERE-IS/rest/Park")
		.then(function(response){
			if(response.status == 200)
				$scope.parkList = response.data;
		});
	
	// Return the boolean accorded to the selected user
	$scope.displayAlert = function(user){
		if(confirm("Do you really want to delete the user : " + user.mail + " ?")){
			$http.post("/WS-CNS-AUTH/rest/UserDelete?userId=" + user.id)
				.then(function(response){
					if(response.status == 200)
						$scope.userList = response.data;
				});
		}
	}
	
	// Check input and do the request to create an User
	$scope.createUser = function() {
		
		$scope.showCreateError = false;
		$scope.showCreateValidation = false;
		
		// Wrong input
		if($scope.input.userEmail == undefined || $scope.input.userEmail.trim() == ""
				|| $scope.input.userPassword == undefined || $scope.input.userPassword.trim() == ""
					|| $scope.input.userPasswordConfirm == undefined || $scope.input.userPasswordConfirm.trim() == ""){
			$scope.errorCreateMessage = "Paramétres incorrects.";
			$scope.showCreateError = true;
			return;
		}
		
		// Different passwords
		if($scope.input.userPassword != $scope.input.userPasswordConfirm){
			$scope.errorCreateMessage = "Mots de passe différents.";
			$scope.showCreateError = true;
			return;
		}
		
		// Request to create the user
		$http.post("/WS-CNS-AUTH/rest/UserCreate?userEmail=" + $scope.input.userEmail + "&userPassword=" + $scope.input.userPassword)
			.then(function(response){
				if(response.status == 201){
					$scope.validationCreateMessage = "Successfully created.";
					$scope.showCreateValidation    = true;
					$scope.userList.push(reponse.data);
					$scope.input = {};
				} else {
					$scope.errorCreateMessage = response.data;
					$scope.showCreateError    = true;
				}
			});
	}
	
});