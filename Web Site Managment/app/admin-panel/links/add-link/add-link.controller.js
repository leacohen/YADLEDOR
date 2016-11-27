angular.module('orledor').controller('addLinkController', function($scope, $mdDialog, $q, $mdToast, languages, firebase) {
	$scope.link = {};
	$scope.subcategories = {};

	loadCategories();

	languages
			.then(function (res) {
				$scope.languages = res;
			})
			.then(function () {
				$scope.$apply();
			});

	$scope.saveLink = function() {
		return ensureFields()
			.then(function() {
				if ($scope.link._type !== 'Other') {
					$scope.link._publishDate = $scope.publishDate.toISOString();
				}

				if(!$scope.link._subcategory) {
					delete $scope.link._subcategory;
				}

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

	$scope.publishDateReminder = function () {
		$mdToast.showSimple('אנא ביחרו את תאריך ההוצאה המדויק, אלגוריתם החיפוש משתמש בתאריכים אלו');
	};

	$scope.isAnySubcateogoryForType = function (type) {				
		return _.some($scope.subcategories, function (subcategory) {
			return subcategory.name == type && subcategory.subcategories && subcategory.subcategories.length;
		});	
	}	

	$scope.getSubcategories = function (type) {
		var category = _.find($scope.subcategories, { 'name': type });
		return category ? category.subcategories : [];
	}

	function loadCategories() {
        return firebase.child('categories').once('value')
            .then(function(mult) {
                $scope.subcategories = _.values(mult.val());
            });
    }

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