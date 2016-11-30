angular.module('orledor').controller('statisticsController', function ($scope, $mdDialog, firebase, loggedUser) {

    var medias = [];
    var users = [];

    // init all the loading flags
    $scope.mediaTypeLoaded = false;
    $scope.spokenLanguagesLoaded = false;
    $scope.mediaLanguagesLoaded = false;
    $scope.top10mediasLoaded = false;

    loadMedias()
        .then(loadUsers)
        .then(loadUserLikesPerMediaType)
        .then(function () { $scope.$apply(); })
        .then(loadSpokenLanguages)
        .then(function () { $scope.$apply(); })
        .then(loadMediasLangs)
        .then(function () { $scope.$apply(); })
        .then(loadTop10Medias)
        .then(function () { $scope.$apply(); });

    function loadUserLikesPerMediaType() {
        var mediaType = [];
        medias.forEach(function (media) {
            if (!mediaType[media._type]) {
                mediaType[media._type] = 0;
            }

            mediaType[media._type]++;
        });

        $scope.mediaType_labels = _.keys(mediaType);
        $scope.mediaType_data = _.values(mediaType);

        $scope.mediaTypeLoaded = true;
    }

    function loadSpokenLanguages() {
        var spokenLan = [];
        _.map(users, '_language').forEach(function (langs) {
            langs.forEach(function (lan) {
                if (!spokenLan[lan]) {
                    spokenLan[lan] = 0;
                }
                spokenLan[lan]++;
            })
        });

        $scope.spokenLanguages_labels = _.keys(spokenLan);
        $scope.spokenLanguages_data = _.values(spokenLan);

        $scope.spokenLanguagesLoaded = true;
    }

    function loadMediasLangs() {
        var mediasLan = [];
        medias.forEach(function (media) {
            if (!mediasLan[media._language]) {
                mediasLan[media._language] = 0;
            }
            mediasLan[media._language]++;
        });

        $scope.mediaLanguages_labels = _.keys(mediasLan);
        $scope.mediaLanguages_data = _.values(mediasLan);

        $scope.mediaLanguagesLoaded = true;
    }

    function loadTop10Medias() {
        var topMedias = _.slice(_.reverse(_.sortBy(medias, function (media) {
            return media.likes;
        })), 0, 10);

        $scope.top10medias_labels = _.map(topMedias, '_name');
        $scope.top10medias_data = _.map(topMedias, 'likes');

        debugger;
        $scope.top10mediasLoaded = true;
    }
    function loadMedias() {
        return firebase.child('multimedia').once('value')
            .then(function (mult) {
                medias = _.values(mult.val());
            });
    }

    function loadUsers() {
        return firebase.child('users').once('value')
            .then(function (fusers) {
                users = _.values(fusers.val());
            });
    }
});
