angular.module('orledor').config(function($stateProvider) {
	$stateProvider.state('statistics', {
		url: '/admin-panel/statistics',
		templateUrl: 'app/admin-panel/statistics/statistics.html',
		controller: 'statisticsController',
		parent: 'adminShell'
	})
});