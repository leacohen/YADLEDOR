angular.module('orledor').config(function($stateProvider) {
	$stateProvider
	.state('links', {
		url: '/links',
		templateUrl: 'links/links.html',
		controller: 'linksController',
		parent: 'shell'
	})
});