angular.module('orledor').controller('addLinkController', function($scope, $mdDialog, firebase) {
  $scope.saveLink = function() {
    // Temp...should talk about it..
    $scope.link._publishDate = $scope.publishDate.toISOString();
    $scope.link._language = "Hebrew";

    firebase.child('multimedia').child($scope.link._name).set($scope.link);
    $mdDialog.hide();
  };
});