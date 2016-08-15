angular.module('orledor').config(function($stateProvider) {
	$stateProvider.state('clientShell', {
		url: '/client',
		templateUrl: 'app/client/shell/shell.html',
		controller: 'clientShellController',		
		parent: 'shell'
	})
});