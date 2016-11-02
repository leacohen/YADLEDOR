angular.module('orledor').controller('categoriesController', function($scope, $mdDialog, firebase, loggedUser) {
    loadCategories()
        .then(initMissingCategories)
        .then(function() {
            $scope.$apply();
        });

    $scope.delete = function(ev, category, subcategory) {
        return warningDeleteAlert(ev, subcategory)
            .then(function () {
                _.pull(category.subcategories, subcategory);
                return firebase.child('categories').child(category.name).set(angular.copy(category));
            });
    }

    $scope.addSubcateogty = function(ev, category) {
        return $mdDialog.show({
                controller: 'addCategoryController',
                templateUrl: 'app/admin-panel/categories/add-category/add-category.html',
                targetEvent: ev,
                clickOutsideToClose: true,
                locals: {
                    category: category
                }
            })
            .then(function(newSubcategoryName) {
                category.subcategories.push(newSubcategoryName);                
                return firebase.child('categories').child(category.name).set(angular.copy(category));
            });
    }

    $scope.mediaTypeToMdi = function(type) {
            if (type === 'Song') {
                return 'mdi-music-note'
            }

            if (type === 'Movie') {
                return 'mdi-movie';
            }

            return 'mdi-account';
        }

    function warningDeleteAlert(event, mediaName) {
        return $mdDialog.show(
            $mdDialog.confirm()
            .clickOutsideToClose(true)
            .textContent('האם אתה בטוח שברצונך למחוק את התת קטגוריה ' + mediaName + '?')
            .ok('המשך')
            .cancel('בטל')
            .targetEvent(event));
    }

    function loadCategories() {
        return firebase.child('categories').once('value')
            .then(function(mult) {
                $scope.categories = _.values(mult.val());
            });
    }

    function initMissingCategories() {
        _.forEach(
            ['Song', 'Movie', 'Other'],
            function(categoryTypeName) {
                if (!_.some($scope.categories, function(category) {
                        return category.name === categoryTypeName;
                    })) {
                    $scope.categories.push({
                        name: categoryTypeName,
                        subcategories: []
                    });
                }
            });
    }
});