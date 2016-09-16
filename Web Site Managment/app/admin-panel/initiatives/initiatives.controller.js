angular.module('orledor').controller('initiativesController', function($scope, $mdDialog, firebase, loggedUser) {

    loadUsersNames()
        .then(loadAllInitiatives);

    $scope.createInitiative = function(ev) {
        return $mdDialog.show({
                controller: 'newInitiativesController',
                templateUrl: 'app/admin-panel/initiatives/new-initiatives/new-initiatives.html',
                targetEvent: ev,
                clickOutsideToClose: true,
            })
            .then(function(inv) {
                addAdditionalInformation(inv);
                return inv;
            })
            .then(function(inv) {
                return firebase.child('initiatives').push(inv);
            })
            .then(function() {
                return loadAllInitiatives();
            });
    };

    $scope.getReporterName = function(userName) {
        return $scope.usersNames[userName] || 'לא ידוע';
    }

    function loadAllInitiatives() {
        $scope.allUsers = [];

        return firebase.child('initiatives').once('value')
            .then(function(inv) {
                $scope.initiatives = _.values(inv.val());
            })
            .then(function() {
                $scope.$apply();
            });
    };

    function loadUsersNames() {
        return firebase.child("users").once("value")
            .then(function(users) {
                var usersVal = users.val();

                $scope.usersNames = _.zipObject(
                    _.map(usersVal, function(user) {
                        return user._userName;
                    }),
                    _.map(usersVal, function(user) {
                        return user._firstName;
                    }));
            })
            .catch(function(err) {
                console.log(err);
            });
    };

    function addAdditionalInformation(inv) {
        inv.reporter = loggedUser.getUser()._userName || '';
    };
});
