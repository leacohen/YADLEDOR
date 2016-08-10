angular.module('orledor').config(function($stateProvider) {
	$stateProvider.state('manage-links', {
		url: '/admin/links',
		templateUrl: 'app/admin-panel/links/links.html',
		controller: 'linksController',
		parent: 'shell'
	})
});