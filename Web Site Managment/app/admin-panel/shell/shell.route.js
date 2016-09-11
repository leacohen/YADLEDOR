angular.module('orledor').config(function($stateProvider) {
	$stateProvider.state('adminShell', {
		url: '/admin-panel',
		templateUrl: 'app/admin-panel/shell/shell.html',
		controller: 'adminShellController',		
		parent: 'shell'
	})
});