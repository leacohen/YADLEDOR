angular.module('orledor').config(function($stateProvider) {
	$stateProvider
	.state('register', {
		url: '/register',
		templateUrl: 'app/register/register.html',
		controller: 'registerController',
		parent: 'shell'
	})
});