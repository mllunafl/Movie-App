(function() {
    'use strict';

    angular
        .module('sandboxApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('movie-wishlist', {
            parent: 'entity',
            url: '/movie-wishlist',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'sandboxApp.movie_wishlist.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/movie-wishlist/movie-wishlists.html',
                    controller: 'Movie_wishlistController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('movie_wishlist');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('movie-wishlist-detail', {
            parent: 'movie-wishlist',
            url: '/movie-wishlist/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'sandboxApp.movie_wishlist.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/movie-wishlist/movie-wishlist-detail.html',
                    controller: 'Movie_wishlistDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('movie_wishlist');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'Movie_wishlist', function($stateParams, Movie_wishlist) {
                    return Movie_wishlist.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'movie-wishlist',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('movie-wishlist-detail.edit', {
            parent: 'movie-wishlist-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/movie-wishlist/movie-wishlist-dialog.html',
                    controller: 'Movie_wishlistDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Movie_wishlist', function(Movie_wishlist) {
                            return Movie_wishlist.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('movie-wishlist.new', {
            parent: 'movie-wishlist',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/movie-wishlist/movie-wishlist-dialog.html',
                    controller: 'Movie_wishlistDialogController',
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
                    $state.go('movie-wishlist', null, { reload: 'movie-wishlist' });
                }, function() {
                    $state.go('movie-wishlist');
                });
            }]
        })
        .state('movie-wishlist.edit', {
            parent: 'movie-wishlist',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/movie-wishlist/movie-wishlist-dialog.html',
                    controller: 'Movie_wishlistDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Movie_wishlist', function(Movie_wishlist) {
                            return Movie_wishlist.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('movie-wishlist', null, { reload: 'movie-wishlist' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('movie-wishlist.delete', {
            parent: 'movie-wishlist',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/movie-wishlist/movie-wishlist-delete-dialog.html',
                    controller: 'Movie_wishlistDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Movie_wishlist', function(Movie_wishlist) {
                            return Movie_wishlist.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('movie-wishlist', null, { reload: 'movie-wishlist' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
