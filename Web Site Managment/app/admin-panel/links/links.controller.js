angular.module('orledor').controller('linksController', function($scope, $mdDialog, firebase) {
	$scope.links = [];

	refreshMedia();

	$scope.createLink = function(ev) {
		$mdDialog.show({
			controller: 'addLinkController',
			templateUrl: 'app/admin-panel/links/add-link/add-link.html',
			targetEvent: ev,
			clickOutsideToClose: true,
		})
		.then(refreshMedia);
	};

	$scope.delete = function(mediaName) {
		firebase.child('multimedia').child(mediaName).remove();
		refreshMedia();
	};

	function refreshMedia() {
		$scope.links = [];
		firebase.child('multimedia').once('value')
			.then(function(mult) {
				mult.forEach(function(currentMultemdia) {
					$scope.links.push(currentMultemdia.val())
				});
				$scope.$apply();
			});
	}
});