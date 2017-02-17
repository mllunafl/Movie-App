(function() {
    'use strict';

    angular
        .module('sandboxApp')
        .controller('Movie_watchlistDialogController', Movie_watchlistDialogController);

    Movie_watchlistDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', '$q', 'entity', 'Movie_watchlist', 'User', 'Movie'];

    function Movie_watchlistDialogController ($timeout, $scope, $stateParams, $uibModalInstance, $q, entity, Movie_watchlist, User, Movie) {
        var vm = this;

        vm.movie_watchlist = entity;
        vm.clear = clear;
        vm.save = save;
        vm.users = User.query();
        vm.movies = Movie.query();

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.movie_watchlist.id !== null) {
                Movie_watchlist.update(vm.movie_watchlist, onSaveSuccess, onSaveError);
            } else {
                Movie_watchlist.save(vm.movie_watchlist, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('sandboxApp:movie_watchlistUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
