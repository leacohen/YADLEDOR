angular.module('orledor').config(function($stateProvider) {
	$stateProvider.state('moreCategories', {
		url: '/more-categories',
		templateUrl: 'app/client/more-categories/more-categories.html',
		controller: 'myCategoriesController',
		parent: 'clientShell'
	})
});