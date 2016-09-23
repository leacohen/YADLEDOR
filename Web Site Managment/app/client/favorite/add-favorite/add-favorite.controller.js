angular.module('orledor').controller('addFavoriteController', function($scope, $mdDialog, $q, $mdToast) {

    $scope.favorite = {};

    $scope.saveFavorite = function() {
        return validateFavorite()
            .then(function() {
                return $mdDialog.hide($scope.favorite);
            })
            .catch(function(error) {
                $mdToast.show($mdToast.simple().textContent(error));
                return $q.reject(error);
            });
    };

    function validateFavorite() {
        if (!$scope.favorite._name) {
            return $q.reject('שם הקישור הינו שדה חובה')
        }

        if (!$scope.favorite._link) {
            return $q.reject('קישור הינו שדה חובה')
        }

        if (!$scope.favorite._type) {
            return $q.reject('סוג היקשור הינו שדה חובה')
        }

        return $q.resolve();
    }
});
