angular.module('orledor')
	.controller('systemSuggestionController', function($scope, firebase, loggedUser, $q) {

		var promise = $q.resolve();
		if (!loggedUser.getUser()) {
			// TODO: Temp!!! autologin to a@aa
			promise = firebase.child("users").child("leacohen").once("value")
				.then(function(user) {
					var userValue = user.val();
					loggedUser.setUser(userValue);
				})
		}

		promise.then(function() {
			$scope.user = loggedUser.getUser();
			$scope.user.likes = $scope.user.likes || {};

			refreshMedia();
		})

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

		$scope.like = function(mediaName) {
			if($scope.user.likes[mediaName] === 1) {
				return unvote(mediaName);
			}

			return vote(mediaName, 1);
		}

		$scope.dislike = function(mediaName) {
			if($scope.user.likes[mediaName] === -1) {
				return unvote(mediaName);
			}

			return vote(mediaName, -1);
		}

		function refreshMedia() {
			$scope.medias = [];
			$scope.isWaitingForMedia = true;
			return firebase.child('multimedia').once('value')
				.then(function(mult) {
					mult.forEach(function(currentMultemdia) {
						var newMedia = currentMultemdia.val();
						$scope.medias.push(newMedia);
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

		function vote(mediaName, voteValue) {
			$scope.user.likes[mediaName] = voteValue;

			return updateUser();
		}

		function unvote(mediaName) {
			delete $scope.user.likes[mediaName];

			return updateUser();
		}

		function updateUser() {
			return firebase.child('users')
				.child($scope.user._userName)
				.update($scope.user);
		}

		function getUserVote(mediaName) {
			var user = loggedUser.getUser();

			if (!user || !user.likes) {
				return 0;
			}

			var userVote = user.likes[mediaName];
			if (!userVote) {
				return 0;
			}

			return userVote;
		}

		function mediaSortAlgoritm(a, b) {
			var aLike = $scope.user.likes[a._name] || 0;
			var bLike = $scope.user.likes[b._name] || 0;

			if(aLike > bLike) {
				return -1;
			}

			if(aLike < bLike) {
				return 1;
			}

			return 0;
		}
	});