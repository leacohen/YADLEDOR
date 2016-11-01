angular.module('orledor').controller('addLinkController', function($scope, $mdDialog, $q, $mdToast, firebase) {
	$scope.link = {};

	$scope.saveLink = function() {
		return ensureFields()
			.then(function() {
				if ($scope.link._type !== 'Other') {
					$scope.link._publishDate = $scope.publishDate.toISOString();
				}

				// TODO: shold impl langs..
				$scope.link._language = "Hebrew";

				return firebase.child('multimedia').child($scope.link._name).set($scope.link);
			})
			.then($mdDialog.hide)
			.catch(function(error) {
				if (error) {
					$mdToast.showSimple(error);
				}

				return $q.reject(error);
			});

	};

	function ensureFields() {
		if (!$scope.link._name) {
			return $q.reject('מדיה חייבת להכיל שם');
		}

		if (!$scope.publishDate && $scope.link._type !== 'Other') {
			return $q.reject('מדיה חייבת להכיל תאריך הוצאה');
		}

		if (!$scope.link._link) {
			return $q.reject('מדיה חייבת להכיל קישור');
		}

		return $q.resolve();
	}


});