angular.module('orledor').service('mediaTypeToMdi', function () {
    function mediaTypeToMdi (type) {
        if (type === 'Song') {
            return 'mdi-music-note'
        }

        if (type === 'Movie') {
            return 'mdi-movie';
        }

        return 'mdi-account';
    };

    return {
        mediaTypeToMdi: mediaTypeToMdi
    };
});