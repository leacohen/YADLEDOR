angular.module('orledor').config(function($stateProvider) {
	$stateProvider.state('systemSuggestion', {
		url: '/systemSuggestion',
		templateUrl: 'app/client/system-suggestion/system-suggestion.html',
		controller: 'systemSuggestionController',
		parent: 'clientShell'
	})
});