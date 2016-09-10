angular.module('orledor')
	.controller('loginController', function($scope, $state, $mdDialog, firebase, loggedUser) {
		// init user object
		$scope.user = {};

		$scope.login = function(ev) {
			return firebase.child("users").child($scope.user.userName).once("value")
				.then(function(user) {
					var userValue = user.val();

					if (!userValue || !userValue._password || "" + userValue._password !== $scope.user.password) {
						return $mdDialog.show(
							$mdDialog.alert()
							.clickOutsideToClose(true)
							.title('שגיאה בהתחברות')
							.textContent('שם המשתמש או הסיסמא לא נכונים')
							.ariaLabel('שגיאה בהתחברות')
							.ok('OK')
							.targetEvent(ev)
						);
					}

					loggedUser.setUser(userValue);
					routeUser(userValue);
				})
				.catch(function(err) {
					console.log(err);
				});
		}

		function routeUser(user) {
			var state = 'home';
			if (user._isAdmin) {
				state = 'manage-links';
			}

			$state.go(state);
		}
	});