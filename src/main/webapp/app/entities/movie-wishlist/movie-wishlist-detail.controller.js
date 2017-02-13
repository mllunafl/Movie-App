(function() {
    'use strict';

    angular
        .module('sandboxApp')
        .controller('Movie_wishlistDetailController', Movie_wishlistDetailController);

    Movie_wishlistDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Movie_wishlist', 'Movie'];

    function Movie_wishlistDetailController($scope, $rootScope, $stateParams, previousState, entity, Movie_wishlist, Movie) {
        var vm = this;

        vm.movie_wishlist = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('sandboxApp:movie_wishlistUpdate', function(event, result) {
            vm.movie_wishlist = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
