angular.module('orledor').config(function($stateProvider) {
	$stateProvider.state('home', {
		url: '/home',
		templateUrl: 'app/client/home/home.html',
		controller: 'homeController',
		parent: 'shell'
	})
});