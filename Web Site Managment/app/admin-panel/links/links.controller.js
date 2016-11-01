angular.module('orledor').controller('linksController', function($scope, $mdDialog, firebase) {
	$scope.links = [];

	refreshMedia();

	$scope.createLink = function(ev) {
		return $mdDialog.show({
				controller: 'addLinkController',
				templateUrl: 'app/admin-panel/links/add-link/add-link.html',
				targetEvent: ev,
				clickOutsideToClose: true,
			})
			.then(refreshMedia);
	};

	$scope.delete = function(event, mediaName) {
		return warningDeleteAlert(event, mediaName)
			.then(function() {
				return firebase.child('multimedia').child(mediaName).remove();
			})
			.then(function() {
				return refreshMedia();
			})
	};

	function warningDeleteAlert(event, mediaName) {
		return $mdDialog.show(
			$mdDialog.confirm()
			.clickOutsideToClose(true)
			.textContent('האם אתה בטוח שברצונך למחוק את המדיה ' + mediaName + '?')
			.ok('המשך')
			.cancel('בטל')
			.targetEvent(event));
	}

	function refreshMedia() {
		$scope.links = [];
		return firebase.child('multimedia').once('value')
			.then(function(mult) {
				mult.forEach(function(currentMultemdia) {
					$scope.links.push(currentMultemdia.val())
				});
				$scope.$apply();
			});
	}
});