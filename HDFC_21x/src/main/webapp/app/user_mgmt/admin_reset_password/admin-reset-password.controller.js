(function() {

    'use strict';

    angular
        .module('gbmUltraApp')
        .controller('AdminResetPasswordController', AdminResetPasswordController);

    AdminResetPasswordController.$inject = ['$scope', 'adminService', 'adminResetPasswordService'];

    function AdminResetPasswordController($scope, adminService, adminResetPasswordService) {
        var vm = this;
        vm.getUserByUsername = getUserByUsername;
        vm.updatePassword = updatePassword;
        vm.resetForm = resetForm;
        vm.showCancelConfirmation = showCancelConfirmation;

        function handleErrorResponse(response) {
            vm.loader = false;
            var msg = response.data.message ? response.data.message : "Server Error";
            Swal.fire("Error!", msg, "error");
        }

        function getUserByUsername() {
            vm.loader = true;
            adminService.getUserByUsername(vm.username)
                .then(
                    function(response) {
                        vm.loader = false;
                        vm.userDetails = response.data;
                    }, handleErrorResponse
                );
        }

        function resetForm(){
            $scope.resetPasswordForm.$setPristine();
            $scope.resetPasswordForm.$setUntouched();
        }

        function updatePassword() {
            vm.loader = true;
            adminResetPasswordService.updatePassword(vm.userDetails.id, vm.credentials)
                .then(
                    function(response) {
                        vm.loader = false;
                        vm.credentials = {};
                        angular.element('#resetPasswordModal').modal('toggle');
                        Swal.fire("Updated!", response.data.message, "success");

                    }, handleErrorResponse
                );
        }

        function showCancelConfirmation() {


            vm.userDetails = null;
            vm.credentials = {};
            vm.username = "";

        }

    }

})();