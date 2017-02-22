(function() {
    'use strict';

    angular
        .module('sandboxApp')
        .controller('Movie_wishlistController', Movie_wishlistController);

    Movie_wishlistController.$inject = ['$scope', '$state', 'Movie_wishlist'];

    function Movie_wishlistController ($scope, $state, Movie_wishlist) {
        var vm = this;

        vm.movie_wishlists = [];

        loadAll();

        function loadAll() {
            Movie_wishlist.query(function(result) {
                vm.movie_wishlists = result;
                vm.searchQuery = null;
            });
        }
    }
})();
