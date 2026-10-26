(function() {

    'use strict';

    angular
        .module('gbmUltraApp')
        .service('resetPasswordService', resetPasswordService);

    resetPasswordService.$inject = ['$http', 'base64Service', '$rootScope'];

    function resetPasswordService($http, base64Service, $rootScope) {

        var resetPasswordService = this;
        resetPasswordService.updatePassword = updatePassword;

        function updatePassword(credentials) {
            var encodedCredentials = {};
            //encodedCredentials.password = base64Service.encode(credentials.password);
            //encodedCredentials.confirmPassword = base64Service.encode(credentials.confirmPassword);
             encodedCredentials.password = $rootScope.toEncrypt(credentials.password);
             encodedCredentials.confirmPassword = $rootScope.toEncrypt(credentials.confirmPassword);
            return $http({
                method: 'POST',
                url: "gbmultra/user/password/reset",
                headers: {
                    'Content-Type': 'application/json'
                },
                data: encodedCredentials
            })
        }

    }

})();