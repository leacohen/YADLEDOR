angular.module('orledor').controller('accountsController', function($scope, $mdDialog, firebase) {

    loadAllUsers();

    $scope.editAccount = function(account, ev) {
        return $mdDialog.show({
            controller: 'editAccountController',
            templateUrl: 'app/admin-panel/accounts/edit-account/edit-account.html',
            targetEvent: ev,
            clickOutsideToClose: true,
            locals: {
            	account: account
            }
        })
        .then(function (user) {
        	return firebase.child('users')
				.child(user._userName)
				.update(user);
        })
        .then(function () {
        	return loadAllUsers();
        });
    };

    function loadAllUsers() {
        $scope.allUsers = [];

        return firebase.child('users').once('value')
            .then(function(users) {
                $scope.allUsers = _.values(users.val());
            })
            .then(function() {
                $scope.$apply();
            });
    };
});
