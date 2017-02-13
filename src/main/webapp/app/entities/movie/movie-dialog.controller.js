(function() {
    'use strict';

    angular
        .module('sandboxApp')
        .controller('MovieDialogController', MovieDialogController);

    MovieDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Movie', 'Movie_wishlist'];

    function MovieDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Movie, Movie_wishlist) {
        var vm = this;

        vm.movie = entity;
        vm.clear = clear;
        vm.save = save;
        vm.movie_wishlists = Movie_wishlist.query();

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.movie.id !== null) {
                Movie.update(vm.movie, onSaveSuccess, onSaveError);
            } else {
                Movie.save(vm.movie, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('sandboxApp:movieUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
