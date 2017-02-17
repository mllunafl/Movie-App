(function() {
    'use strict';

    angular
        .module('sandboxApp')
        .controller('Movie_watchlistController', Movie_watchlistController);

    Movie_watchlistController.$inject = ['$scope', '$state', 'Movie_watchlist'];

    function Movie_watchlistController ($scope, $state, Movie_watchlist) {
        var vm = this;

        vm.movie_watchlists = [];

        loadAll();

        function loadAll() {
            Movie_watchlist.query(function(result) {
                vm.movie_watchlists = result;
                vm.searchQuery = null;
            });
        }
    }
})();
