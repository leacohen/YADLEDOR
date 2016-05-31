angular.module('orledor').service('loggedUser', function(){
	var loggedUser;

	function setUser(user) {
		loggedUser = user;
	}

	function logout() {
		loggedUser = null;
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