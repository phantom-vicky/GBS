(function() {

    'use strict';

    angular
        .module('gbmUltraApp')
        .controller('ResetPasswordController', ResetPasswordController);

    ResetPasswordController.$inject = ['$scope', '$state', '$location', 'resetPasswordService', 'authService'];

    function ResetPasswordController($scope, $state, $location, resetPasswordService, authService) {
        var vm = this;
vm.passwordPattern = /^(?=.*[A-Z])(?=.*[a-z])(?=.*\d)(?=.*[!#$%&'()*+,\-./:;<=>?@[\]^_` {|}~])[A-Za-z\d !#$%&'()*+,\-./:;<=>?@[\]^_` {|}~]{6,25}$/;

        vm.updatePassword = updatePassword;

        function updatePassword() {
            vm.loader = true;
            resetPasswordService.updatePassword(vm.credentials)
                .then(
                    function(response) {
                        vm.loader = false;
                    	Swal.fire("updated!", response.data.message, "success");
                       /* $scope.$emit('showAlert', {
                            'type': 'true',
                            'msg': response.data.message
                        });*/
                        authService.logout(USER_LOGOUT_REASONS.NORMAL_TYPE)
                            .then(
                                function(response) {
                                    //$location.path('/login');
                                    $state.go('login');
                                }
                            );
                    }, handleErrorResponse
                );
        }

        function handleErrorResponse(response) {
             vm.loader = false;
        	 var msg = response.data.message ? response.data.message : "Server Error";
 			 Swal.fire("Error!", msg, "error");
        }

    }

})();