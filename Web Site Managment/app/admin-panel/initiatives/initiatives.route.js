angular.module('orledor').config(function($stateProvider) {
	$stateProvider.state('initiatives', {
		url: '/admin-panel/initiatives',
		templateUrl: 'app/admin-panel/initiatives/initiatives.html',
		controller: 'initiativesController',
		parent: 'adminShell'
	})
});