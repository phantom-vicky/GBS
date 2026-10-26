(function() {

    'use strict';

    angular
        .module('gbmUltraApp')
        .controller('UserController', UserController);

    UserController.$inject = ['$scope', '$state', 'userService'];

    function UserController($scope, $state, userService) {
        var vm = this;
        vm.passwordPattern = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[$@$!%*#?&])[A-Za-z\d$@$!%*#?&]{8,}$/;
        vm.updatePassword = updatePassword;

        function updatePassword() {
            userService.updatePassword(vm.credentials)
                .then(
                    function(response) {
                    	Swal.fire("Updated!", response.data.message, "success");
                        //$scope.$emit('showAlert', { 'type': 'true', 'msg': response.data.message });
                        logout();
                    }, handleErrorResponse
                );
        }

        function logout() {
            userService.logout()
                .then(
                    function(response) {
                        $state.go('login');
                    }, handleErrorResponse
                );
        }

        function handleErrorResponse(response) {
        	var msg = response.data.message ? response.data.message : "Server Error";
            
			Swal.fire("Error!", msg, "error");
        }

    }

})();