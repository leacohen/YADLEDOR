angular.module('orledor').config(function($stateProvider) {
	$stateProvider.state('favorite', {
		url: '/favorite',
		templateUrl: 'app/client/favorite/favorite.html',
		controller: 'favoriteController',
		parent: 'clientShell'
	})
});