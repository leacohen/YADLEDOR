angular.module('orledor')
	.controller('systemSuggestionController', function($scope, firebase) {

		refreshMedia();

		function refreshMedia() {
			$scope.medias = [];
			return firebase.child('multimedia').once('value')
				.then(function(mult) {
					mult.forEach(function(currentMultemdia) {
						$scope.medias.push(currentMultemdia.val())
					});
					$scope.$apply();
				})
		};

	});