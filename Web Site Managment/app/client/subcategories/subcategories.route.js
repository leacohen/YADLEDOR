angular.module('orledor').config(function ($stateProvider) {
	$stateProvider.state('subcategories', {
		url: '/client/subcategories/:type/:category',
		templateUrl: 'app/client/subcategories/subcategories.html',
		controller: 'subcategoriesController',
		parent: 'clientShell'
	})
});