angular.module('orledor').config(function($stateProvider) {
	$stateProvider.state('shell', {
		url: '',
		templateUrl: 'app/shell/shell.html',
		controller: 'shellController',		
	})
});