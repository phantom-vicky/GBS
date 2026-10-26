(function() {

    'use strict';

    angular
        .module('gbmUltraApp')
        .controller('PermissionListController', PermissionListController);

    PermissionListController.$inject = ['$scope', '$stateParams', 'permissionService'];

    function PermissionListController($scope, $stateParams, permissionService) {

        var vm = this;
        vm.showDeleteConfirmation = showDeleteConfirmation;

        vm.action = $stateParams.action;
       // vm.permissions = [];

        vm.itemsPerPage = "10";

        getPermissions();

        function getPermissions() {
        	vm.loader = true;
            permissionService.getAllPermissions()
                .then(
                    function(response) {
                        vm.permissions = response.data;
                    	vm.loader = false;
                    }, handleErrorResponse
                );
        }

        function showDeleteConfirmation(id) {
//            Swal.fire({
//                    title: "Confirm Delete",
//                    text: "Do you really want to delete this permission?",
//                    type: "warning",
//                    showCancelButton: true,
//                    cancelButtonClass: "btn-cancel",
//                    confirmButtonClass: "btn-danger",
//                    confirmButtonText: "Yes",
//                    cancelButtonText: "No",
//                    closeOnConfirm: true
//                },
//                function() {


 Swal.fire({
                    title: "Are you sure?",
                    text: "Do you want to Really Delete This Permission?",
                    icon: "warning", // use "icon", not "type"
                    showCancelButton: true,
                    confirmButtonText: "Yes",
                    cancelButtonText: "Cancel",
                    customClass: {
                      confirmButton: "btn-primary",
                      cancelButton: "btn-cancel"
                    },
                    buttonsStyling: false
                  }).then((result) => {

                         if (result.isConfirmed) {
                    deletePermission(id);

              }  });
        }

        function deletePermission(id) {
            permissionService.deletePermission(id)
                .then(
                    function(response) {
                    	 Swal.fire("Deleted!", response.data.message, "success");
                        getPermissions();
                    }, handleErrorResponse
                );
        }

        function handleErrorResponse(response) {
        	var msg = response.data.message ? response.data.message : "Server Error";
			Swal.fire("Error!", msg, "error");
        }

    }

})();