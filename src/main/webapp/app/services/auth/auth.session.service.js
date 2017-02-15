(function() {
    'use strict';

    angular
        .module('sandboxApp')
        .factory('AuthServerProvider', AuthServerProvider);

    AuthServerProvider.$inject = ['$http', '$localStorage' ];

    function AuthServerProvider ($http, $localStorage ) {
        var service = {
            getToken: getToken,
            hasValidToken: hasValidToken,
            login: login,
            logout: logout
        };

        return service;

        function getToken () {
            var token = $localStorage.authenticationToken;
            return token;
        }

        function hasValidToken () {
            var token = this.getToken();
            return !!token;
        }

        function login (credentials) {
            var data = 'j_username=' + encodeURIComponent(credentials.username) +
                '&j_password=' + encodeURIComponent(credentials.password) +
                '&remember-me=' + credentials.rememberMe + '&submit=Login';

            return $http.post('api/authentication', data, {
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded'
                }
            }).success(function (response) {
                return response;
            });
        }

        function logout () {
                   delete $localStorage.authenticationToken;
                   var form = document.createElement("form");
                   form.method = "POST";
                   form.action = "/logout";
                   document.body.appendChild(form);
                   form.submit();
               }
    }
})();
