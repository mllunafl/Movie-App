(function() {
    'use strict';
    angular
        .module('sandboxApp')
        .factory('Movie_watchlist', Movie_watchlist);

    Movie_watchlist.$inject = ['$resource'];

    function Movie_watchlist ($resource) {
        var resourceUrl =  'api/movie-watchlists/:id';

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
