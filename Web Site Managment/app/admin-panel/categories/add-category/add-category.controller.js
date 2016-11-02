angular.module('orledor').controller('addCategoryController', function($scope, $mdDialog, $mdToast, $q, category) {
    $scope.category = angular.copy(category);    

    $scope.save = function() {
        return validateSubcategory()
            .then(function() {
                return $mdDialog.hide($scope.subcategoryName)
            })
            .catch(function(err) {
                $mdToast.show($mdToast.simple().textContent(err));
                return $q.reject(err);
            });
    }

    function validateSubcategory() {        
        if (!$scope.subcategoryName || $scope.subcategoryName.length < 5) {
            return $q.reject('אורך תת הקטגוריה חייב להיות לפחות 5 תווים');
        }

        return $q.resolve();
    }
});
