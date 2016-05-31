angular.module('orledor').config(function($stateProvider) {
	$stateProvider
	.state('login', {
		url: '/login',
		templateUrl: 'login/login.html',
		controller: 'loginController',
		parent: 'shell'
	})
});