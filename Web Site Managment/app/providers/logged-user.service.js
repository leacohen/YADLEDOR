angular.module('orledor').service('loggedUser', function($rootScope) {
	var loggedUser;

	function setUser(user) {
		loggedUser = user;
		$rootScope.$broadcast('user:logedin');
	}

	function logout() {
		loggedUser = null;
		$rootScope.$broadcast('user:logedout');
	}

	function getUser() {
		return loggedUser;
	}

	return {
		setUser: setUser,
		logout: logout,
		getUser: getUser
	};
})