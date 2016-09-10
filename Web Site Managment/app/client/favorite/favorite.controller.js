angular.module('orledor')
    .controller('favoriteController', function($scope, firebase, loggedUser, $q, $mdDialog) {

    	$scope.isWaitingForMedia = true;
        initMediaTypes();

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

        $scope.addFavorite = function(ev) {
            return $mdDialog.show({
                    controller: 'addFavoriteController',
                    templateUrl: 'app/client/favorite/add-favorite/add-favorite.template.html',
                    targetEvent: ev,
                    clickOutsideToClose: true,
                })
                .then(function(favorite) {
                    if (!$scope.user._userMultimedia) {
                        $scope.user._userMultimedia = [];
                    }

                    $scope.user._userMultimedia.push(favorite);
                })
                .then(refreshMedia)
                .then(updateUser);
        };

        function refreshMedia() {
            $scope.medias = [];
            $scope.isWaitingForMedia = true;
            return firebase.child('multimedia').once('value')
                .then(function(mult) {
                    mult.forEach(function(currentMultemdia) {
                        var newMedia = currentMultemdia.val();
                        if ($scope.user.likes[newMedia._name] === 1) {
                            $scope.medias.push(newMedia);
                        }
                    });
                })
                .then(function() {
                    if ($scope.user._userMultimedia) {
                        $scope.user._userMultimedia.forEach(function(userMedia) {
                            $scope.medias.push(angular.copy(userMedia));
                        });
                    }
                })
                .then(function() {
                    $scope.medias.sort(mediaSortAlgoritm);
                })
                .then(function() {
                    $scope.isWaitingForMedia = false;
                    $scope.$apply();
                });
        };

        function updateUser() {
            return firebase.child('users')
                .child($scope.user._userName)
                .update($scope.user);
        }

        function mediaSortAlgoritm(a, b) {

            return 0;
        }

        function initMediaTypes() {
            $scope.mediaTypes = [{
                name: '',
                displayName: 'הצג הכול'
            }, {
                name: 'Song',
                displayName: 'מוזיקה'
            }, {
                name: 'Movie',
                displayName: 'סרטים'
            }];
        }
    });
