(function() {

    'use strict';

    angular
        .module('gbmUltraApp')
        .controller('LogoutController', LogoutController);

    LogoutController.$inject = ['$state', 'authService'];

    function LogoutController($state, authService) {

        logout();

        function logout() {
            authService.logout(USER_LOGOUT_REASONS.NORMAL_TYPE)
                .then(
                    function(response) {
                        $state.go('login');
                    });
        }


    }

})();