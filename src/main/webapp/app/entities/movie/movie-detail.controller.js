(function() {
    'use strict';

    angular
        .module('sandboxApp')
        .controller('MovieDetailController', MovieDetailController);

    MovieDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Movie', 'Movie_wishlist', 'Movie_watchlist'];

    function MovieDetailController($scope, $rootScope, $stateParams, previousState, entity, Movie, Movie_wishlist, Movie_watchlist) {
        var vm = this;

        vm.movie = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('sandboxApp:movieUpdate', function(event, result) {
            vm.movie = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
