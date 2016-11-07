angular.module('orledor').controller('subcategoriesController', function($scope, $stateParams, firebase, mediaTypeToMdi) {
    $scope.type = $stateParams.type;
    $scope.category = $stateParams.category;    

    $scope.isWaitingForMedia = true;

    $scope.openLink = function(linkUrl) {
            window.open(linkUrl, '_blank').focus();
        }

    $scope.mediaTypeToMdi = mediaTypeToMdi.mediaTypeToMdi;

    loadMedias()
        .then(function () {
            $scope.isWaitingForMedia = false;
        })
        .then(function () {
            $scope.$apply();
        });

    function loadMedias() {
        return firebase.child('multimedia').once('value')
            .then(function (mult) {
                $scope.medias = _.filter(_.values(mult.val()), function (media) {
                    return media._type === $scope.type && media._subcategory === $scope.category;
                });
            });
    }
});