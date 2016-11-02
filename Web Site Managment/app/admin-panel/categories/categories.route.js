angular.module('orledor').config(function($stateProvider) {
	$stateProvider.state('categories', {
		url: '/admin-panel/categories',
		templateUrl: 'app/admin-panel/categories/categories.html',
		controller: 'categoriesController',
		parent: 'adminShell'
	})
});