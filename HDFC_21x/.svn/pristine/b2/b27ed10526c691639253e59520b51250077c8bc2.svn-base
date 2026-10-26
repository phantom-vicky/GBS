(function() {

    'use strict';

    angular
        .module('gbmUltraApp')
        .controller('LockedUsersController', LockedUsersController);

    LockedUsersController.$inject = ['$scope', '$state', '$stateParams', 'lockedUserService'];

    function LockedUsersController($scope, $state, $stateParams, lockedUserService) {
        var vm = this;

       // vm.lockedUsers = [];
        vm.action = $stateParams.action;
        vm.itemsPerPage = "10";

        vm.unlockUser = unlockUser;

        getLockedUsers();

        function getLockedUsers() {
        	vm.loader = true;
            lockedUserService.getLockedUsers()
                .then(
                    function(response) {
                        vm.lockedUsers = response.data;
                        vm.loader = false;
                    }, handleErrorResponse
                );
        }

        function unlockUser(id){
            lockedUserService.unlockUser(id)
                .then(
                    function(response) {
                        
                        Swal.fire("Unlocked!", response.data.message, "success");
                        getLockedUsers();
                    }, handleErrorResponse
                );
        }

        function handleErrorResponse(response) {
        	var msg = response.data.message ? response.data.message : "Server Error";
			Swal.fire("Error!", msg, "error");
        }

    }

})();