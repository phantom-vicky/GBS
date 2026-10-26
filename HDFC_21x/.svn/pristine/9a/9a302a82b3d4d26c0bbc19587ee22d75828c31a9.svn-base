(function() {

    'use strict';

    angular
        .module('gbmUltraApp')
        .service('adminResetPasswordService', adminResetPasswordService);

    adminResetPasswordService.$inject = ['$http', 'base64Service', '$rootScope'];

    function adminResetPasswordService($http, base64Service, $rootScope) {

        var adminResetPasswordService = this;
        adminResetPasswordService.updatePassword = updatePassword;


        function updatePassword(userId, credentials) {
            var encodedCredentials = {};
            /*encodedCredentials.password = base64Service.encode(credentials.password);
            encodedCredentials.confirmPassword = base64Service.encode(credentials.confirmPassword);*/

            encodedCredentials.password = $rootScope.toEncrypt(credentials.password);
            encodedCredentials.confirmPassword = $rootScope.toEncrypt(credentials.confirmPassword);
            encodedCredentials.userId = userId;
            return $http({
                method: 'POST',
                url: "gbmultra/admin/user/password/reset",
                headers: {
                    'Content-Type': 'application/json'
                },
                data: encodedCredentials
            })
        }
    }

})();