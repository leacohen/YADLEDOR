angular.module('orledor').config(function($stateProvider) {
	$stateProvider
	.state('shell', {
		url: '',
		templateUrl: 'shell/shell.html',
		controller: 'shellController',		
	})
});