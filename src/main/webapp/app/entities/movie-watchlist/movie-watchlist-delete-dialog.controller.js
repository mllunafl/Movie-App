(function() {
    'use strict';

    angular
        .module('sandboxApp')
        .controller('Movie_watchlistDeleteController',Movie_watchlistDeleteController);

    Movie_watchlistDeleteController.$inject = ['$uibModalInstance', 'entity', 'Movie_watchlist'];

    function Movie_watchlistDeleteController($uibModalInstance, entity, Movie_watchlist) {
        var vm = this;

        vm.movie_watchlist = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            Movie_watchlist.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
