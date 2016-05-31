angular.module('orledor').controller('shellController', function($state, loggedUser) {
	if (!loggedUser.getUser()) {
		//$state.go('login');
	}
});