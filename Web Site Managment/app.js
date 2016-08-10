angular.module('orledor', ['ui.router', 'ngMaterial']);

angular.module('orledor')
	.config(function($urlRouterProvider) {
    	$urlRouterProvider.otherwise('/login');
    });
