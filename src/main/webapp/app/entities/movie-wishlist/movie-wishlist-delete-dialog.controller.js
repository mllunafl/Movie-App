(function() {
    'use strict';

    angular
        .module('sandboxApp')
        .controller('Movie_wishlistDeleteController',Movie_wishlistDeleteController);

    Movie_wishlistDeleteController.$inject = ['$uibModalInstance', 'entity', 'Movie_wishlist'];

    function Movie_wishlistDeleteController($uibModalInstance, entity, Movie_wishlist) {
        var vm = this;

        vm.movie_wishlist = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            Movie_wishlist.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
