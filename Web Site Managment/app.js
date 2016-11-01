angular.module('orledor', ['ui.router', 'ngMaterial', 'chart.js']);

angular.module('orledor')
	.config(function($urlRouterProvider) {
		$urlRouterProvider.when('', '/login');
		$urlRouterProvider.when('/', '/login');    	
    });
