angular.module('orledor').controller('editAccountController', function($scope, $mdDialog, $mdToast, $q, firebase, account) {
    $scope.account = angular.copy(account);

    $scope.$watch('shouldShowPassword', function() {
        if ($scope.shouldShowPassword) {
            $scope.passwordInputType = 'text';
        } else {
            $scope.passwordInputType = 'password';
        }
    });

    var initIsAdminWatch = false;
    $scope.$watch('account._isAdmin', function () {
        if(initIsAdminWatch) {
            $mdToast.show($mdToast.simple().textContent('שים לב! הפיכת משתמש למנהל תיתן לאותו המשתש הרשאות על המערכת!'));
        } 
        initIsAdminWatch = true;       
    });

    $scope.save = function() {
        return validateUser()
            .then(function() {
                return $mdDialog.hide($scope.account)
            })
            .catch(function(err) {
                $mdToast.show($mdToast.simple().textContent(err));
                return $q.reject(err);
            });
    }


    function validateUser() {
        if (!$scope.account._userName) {
            return $q.reject('השדה שם התחברות הינו חובה');
        }

        if (!$scope.account._firstName) {
            return $q.reject('השדה שם הינו חובה');
        }

        if (!$scope.account._password) {
            return $q.reject('השדה סיסמא הינו חובה');
        }

        return $q.resolve();
    }
});
