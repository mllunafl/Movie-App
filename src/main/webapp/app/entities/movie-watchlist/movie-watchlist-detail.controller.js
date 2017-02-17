(function() {
    'use strict';

    angular
        .module('sandboxApp')
        .controller('Movie_watchlistDetailController', Movie_watchlistDetailController);

    Movie_watchlistDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Movie_watchlist', 'User'];

    function Movie_watchlistDetailController($scope, $rootScope, $stateParams, previousState, entity, Movie_watchlist, User) {
        var vm = this;

        vm.movie_watchlist = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('sandboxApp:movie_watchlistUpdate', function(event, result) {
            vm.movie_watchlist = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
