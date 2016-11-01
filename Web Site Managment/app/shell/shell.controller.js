angular.module('orledor').controller('shellController', function($scope, $state, loggedUser) {	
	
	$scope.logout = loggedUser.logout;
	updateUserState();

	$scope.$on('user:logedin', updateUserState);
	$scope.$on('user:logedout', updateUserState);

	function updateUserState() {
		if(loggedUser.getUser()) {
			$scope.isUserLogged = true;
			$scope.userName = loggedUser.getUser()._firstName;
		}

		else {
			$scope.isUserLogged = false;
			$state.go('login');
		}
	}	
});