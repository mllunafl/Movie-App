(function() {
    'use strict';
    angular
        .module('sandboxApp')
        .factory('Movie_wishlist', Movie_wishlist);

    Movie_wishlist.$inject = ['$resource'];

    function Movie_wishlist ($resource) {
        var resourceUrl =  'api/movie-wishlists/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    if (data) {
                        data = angular.fromJson(data);
                    }
                    return data;
                }
            },
            'update': { method:'PUT' }
        });
    }
})();
