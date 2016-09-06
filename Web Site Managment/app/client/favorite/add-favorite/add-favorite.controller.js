angular.module('orledor').controller('addFavoriteController', function($scope, $mdDialog) {
  $scope.saveFavorite = function() {
    return $mdDialog.hide($scope.favorite);
  };
});