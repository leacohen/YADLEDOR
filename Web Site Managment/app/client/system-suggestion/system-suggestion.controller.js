angular.module('orledor')
	.controller('systemSuggestionController', function($scope, firebase) {

		refreshMedia();

		$scope.mediaTypeToMdi = function(type) {
			if (type === 'Song') {
				return 'mdi-music-note'
			}

			if (type === 'Movie') {
				return 'mdi-movie';
			}

			return 'mdi-account';
		}

		$scope.openLink = function(linkUrl) {
			window.open(linkUrl, '_blank').focus();
		}

		function refreshMedia() {
			$scope.medias = [];
			$scope.isWaitingForMedia = true;
			return firebase.child('multimedia').once('value')
				.then(function(mult) {
					mult.forEach(function(currentMultemdia) {
						$scope.medias.push(currentMultemdia.val())
					});
				})
				.then(function() {
					$scope.medias.sort(mediaSortAlgoritm);
				})
				.then(function() {
					$scope.isWaitingForMedia = false;
					$scope.$apply();
				});
		};

		function mediaSortAlgoritm(a, b) {
			if (a._publishDate > b._publishDate) {
				return -1;
			} else if (a._publishDate < b._publishDate) {
				return 1;
			}

			return 0;
		}
	});