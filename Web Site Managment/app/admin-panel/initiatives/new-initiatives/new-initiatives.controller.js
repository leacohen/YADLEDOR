angular.module('orledor').controller('newInitiativesController', function($scope, $mdDialog, $mdToast, $q, firebase) {    
    
    $scope.inv = {};

    $scope.save = function() {
        return validateInitiative()
            .then(function() {
                return $mdDialog.hide($scope.inv)
            })
            .catch(function(err) {
                $mdToast.show($mdToast.simple().textContent(err));
                return $q.reject(err);
            });
    }


    function validateInitiative() {
        if (!$scope.inv.name) {
            return $q.reject('השדה שם הינו חובה');
        }

        if (!$scope.inv.description) {
            return $q.reject('השדה תיאור הינו חובה');
        }

        return $q.resolve();
    }
});
