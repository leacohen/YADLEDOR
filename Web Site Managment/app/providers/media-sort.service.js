angular.module('orledor').service('mediaSort', function($rootScope, loggedUser) {

    var baseAge = new Date(loggedUser.getUser()._birthDate).getFullYear() + 18; // The base age is 18..
    var userLikes = loggedUser.getUser().likes;

    function sortSystemSuggest(medias) {
        var sortedMedia = [];

        sortedMedia = sortedMedia.concat(sortAlgoritem(_.filter(medias, function(media) {
        	return getUserLikeValue(media._name) === 1;
        })));

        sortedMedia = sortedMedia.concat(sortAlgoritem(_.filter(medias, function(media) {
        	return getUserLikeValue(media._name) === 0;
        })));

        sortedMedia = sortedMedia.concat(sortAlgoritem(_.filter(medias, function(media) {
        	return getUserLikeValue(media._name) === -1;
        })));


        return sortedMedia;
    }

    function sortFavorite(medias) {
    	var sortedMedias = [];

        sortedMedias = _.filter(medias, function(media) {
        	return media.userMedia;
        });

        sortedMedias = sortedMedias.concat(sortAlgoritem(_.filter(medias, function(media) {
        	return !media.userMedia;
        })));

        return sortedMedias;
    }

    function sortAlgoritem(medias) {
        var sortedMedias = [];
        var baseMedias = angular.copy(medias).filter(function (media) {
            return media._type !== 'Other';
        });
        
        var rangeIndex = 1;

        while (baseMedias.length) {
            sortedMedias = sortedMedias.concat(sortMediaInRange(getMediaRange(baseMedias, rangeIndex)));

            baseMedias = getMediasWithoutRange(baseMedias, rangeIndex)

            rangeIndex++;
        }

        return sortedMedias;

    }

    function sortMediaInRange(medias) {
        return _.sortBy(medias, function(media) {
            return media.likes;
        });
    }

    function getMediaRange(medias, rangeIndex) {
        var range = getYearRange(rangeIndex);

        return _.filter(medias, function(media) {
            return isMediaInRange(media, range);
        });
    }

    function getMediasWithoutRange(medias, rangeIndex) {
        var range = getYearRange(rangeIndex);

        return _.filter(medias, function(media) {
            return !isMediaInRange(media, range);
        });
    }

    function isMediaInRange(media, range) {
        var mediaPublishYear = new Date(media._publishDate).getFullYear();
        return (mediaPublishYear < range.top) && (mediaPublishYear > range.bottom);
    }

    function getYearRange(rangeIndex) {
        return {
            top: baseAge + (5 * rangeIndex),
            bottom: baseAge - (5 * rangeIndex)
        };
    }

    function getUserLikeValue(mediaName) {
        return userLikes[mediaName] || 0;
    }

    return {
        sortSystemSuggest: sortSystemSuggest,
        sortFavorite: sortFavorite
    };
})
