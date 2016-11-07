angular.module('orledor').config(function ($stateProvider) {
	$stateProvider.state('subcategories', {
		url: '/client/subcategories',
		templateUrl: 'app/client/subcategories/subcategories.html',
		controller: 'subcategoriesController',
		parent: 'clientShell'
	})
});