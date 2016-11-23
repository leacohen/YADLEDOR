angular.module('orledor').service('languages' , function($http) {	
	return $http.get('resources/language.json')
			.then(function (respone) {
				return respone.data.map(function (lan) {
                    return _.capitalize(lan.name);
                });
			});
});