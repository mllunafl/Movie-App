(function() {
    'use strict';

    angular
        .module('sandboxApp')
        .controller('Movie_wishlistDialogController', Movie_wishlistDialogController);

    Movie_wishlistDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Movie_wishlist', 'User'];

    function Movie_wishlistDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Movie_wishlist, User) {
        var vm = this;

        vm.movie_wishlist = entity;
        vm.clear = clear;
        vm.save = save;
        vm.users = User.query();

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.movie_wishlist.id !== null) {
                Movie_wishlist.update(vm.movie_wishlist, onSaveSuccess, onSaveError);
            } else {
                Movie_wishlist.save(vm.movie_wishlist, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('sandboxApp:movie_wishlistUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
