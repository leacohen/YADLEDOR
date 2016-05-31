angular.module('orledor').controller('addLinkController', function($scope, $mdDialog, firebase) {
  $scope.saveLink = function() {
    // Temp...should talk about it..
    $scope.link._publishDate = new Date().toISOString();
    $scope.link._publishedCountry = ["Israel", "Russia"];

    firebase.child('multimedia').child($scope.link._name).set($scope.link);
    $mdDialog.hide();
  };
});