(function() {

    'use strict';

    angular
        .module('gbmUltraApp')
        .service('userService', userService);

    userService.$inject = ['$rootScope', '$http', '$window', 'base64Service'];

    function userService($rootScope, $http, $window, base64Service) {

        var userService = this;
        userService.updatePassword = updatePassword;
        userService.logout = logout;

        function updatePassword(credentials) {
            /*credentials.password = base64Service.encode(credentials.password);
            credentials.confirmPassword = base64Service.encode(credentials.confirmPassword);*/
            credentials.password = $rootScope.toEncrypt(credentials.password);
            credentials.confirmPassword = $rootScope.toEncrypt(credentials.confirmPassword);
            return $http({
                method: 'POST',
                url: "gbmultra/user/password/reset",
                headers: {
                    'Content-Type': 'application/json'
                },
                data: credentials
            })
        }

        function logout() {
            $window.sessionStorage.removeItem('globals');
            $rootScope.globals = {}
            return $http({
                method: "GET",
                url: "gbmultra/user/logout"
            });
        }

    }

})();