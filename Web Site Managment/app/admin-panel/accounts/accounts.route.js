angular.module('orledor').config(function($stateProvider) {
	$stateProvider.state('manage-accounts', {
		url: '/admin-panel/accounts',
		templateUrl: 'app/admin-panel/accounts/accounts.html',
		controller: 'accountsController',
		parent: 'adminShell'
	})
});