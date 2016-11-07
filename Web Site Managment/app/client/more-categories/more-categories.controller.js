angular.module('orledor')
    .controller('myCategoriesController', function($scope, firebase) {

        $scope.categories = [];

        loadMedias()
            .then(function() { $scope.$apply(); });

        $scope.mediaTypeToMdi = function(type) {
            if (type === 'Song') {
                return 'mdi-music-note'
            }

            if (type === 'Movie') {
                return 'mdi-movie';
            }

            return 'mdi-account';
        }

        function loadMedias() {
            return firebase.child('multimedia').once('value')
                .then(function(mult) {
                    $scope.categories = _.map(_.groupBy(_.filter(_.values(mult.val()), function(media) {
                        return !!media._subcategory;
                    }), '_type'), function(medias, type) {
                        return {
                            name: type,
                            subcategories: _.map(
                                _.uniqBy(medias, '_subcategory'), function(media) {
                                    return media._subcategory;
                                })
                        };
                    })
                });
        }

    });