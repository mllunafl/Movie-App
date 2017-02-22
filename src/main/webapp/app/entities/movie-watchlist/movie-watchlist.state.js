(function() {
    'use strict';

    angular
        .module('sandboxApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('movie-watchlist', {
            parent: 'entity',
            url: '/movie-watchlist',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'sandboxApp.movie_watchlist.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/movie-watchlist/movie-watchlists.html',
                    controller: 'Movie_watchlistController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('movie_watchlist');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('movie-watchlist-detail', {
            parent: 'movie-watchlist',
            url: '/movie-watchlist/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'sandboxApp.movie_watchlist.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/movie-watchlist/movie-watchlist-detail.html',
                    controller: 'Movie_watchlistDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('movie_watchlist');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'Movie_watchlist', function($stateParams, Movie_watchlist) {
                    return Movie_watchlist.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'movie-watchlist',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('movie-watchlist-detail.edit', {
            parent: 'movie-watchlist-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/movie-watchlist/movie-watchlist-dialog.html',
                    controller: 'Movie_watchlistDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Movie_watchlist', function(Movie_watchlist) {
                            return Movie_watchlist.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('movie-watchlist.new', {
            parent: 'movie-watchlist',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/movie-watchlist/movie-watchlist-dialog.html',
                    controller: 'Movie_watchlistDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                dbmovie_id: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('movie-watchlist', null, { reload: 'movie-watchlist' });
                }, function() {
                    $state.go('movie-watchlist');
                });
            }]
        })
        .state('movie-watchlist.edit', {
            parent: 'movie-watchlist',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/movie-watchlist/movie-watchlist-dialog.html',
                    controller: 'Movie_watchlistDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Movie_watchlist', function(Movie_watchlist) {
                            return Movie_watchlist.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('movie-watchlist', null, { reload: 'movie-watchlist' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('movie-watchlist.delete', {
            parent: 'movie-watchlist',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/movie-watchlist/movie-watchlist-delete-dialog.html',
                    controller: 'Movie_watchlistDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Movie_watchlist', function(Movie_watchlist) {
                            return Movie_watchlist.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('movie-watchlist', null, { reload: 'movie-watchlist' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
