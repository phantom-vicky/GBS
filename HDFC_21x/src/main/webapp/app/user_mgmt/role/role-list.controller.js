(function() {

    'use strict';

    angular
        .module('gbmUltraApp')
        .controller('RoleListController', RoleListController);

    RoleListController.$inject = ['$state', '$stateParams', 'roleService'];

    function RoleListController($state, $stateParams, roleService) {

        var vm = this;
        vm.commonYesNoMetaData = COMMON_YES_NO_METADATA;
        var modules = [];
        vm.showDeleteConfirmation = showDeleteConfirmation;
        vm.showVerifyConfirmation = showVerifyConfirmation;

        vm.action = $stateParams.action;
        vm.itemsPerPage = "10";


        if(vm.action == 'modify') {
            getModifiedRoles();
        }else if(vm.action == 'verify' || vm.action == 'delete') {
            getUnVerifiedRoles();
        }else if (vm.action == 'inquiry') {
            getRoles();
        }

        function getRoles() {
            vm.loader = true;
            let requestData = {
                id: $stateParams.id? $stateParams.id : ''
            }
            roleService.getRoles(requestData)
                .then(
                    function(response) {
                        vm.roles = response.data.result;
                        vm.loader = false;
                    }, handleErrorResponse
                );
        }

        function getUnVerifiedRoles() {
            vm.loader = true;
            let requestData = {
                id: $stateParams.id? $stateParams.id : ''
            }
            roleService.getUnVerifiedRoles(requestData)
                .then(
                    function(response) {
                        vm.roles = response.data.result;
                        vm.loader = false;
                    }, handleErrorResponse
                );
        }
        function getModifiedRoles() {
            vm.loader = true;
            let requestData = {
                id: $stateParams.id? $stateParams.id : ''
            }
            roleService.getModifiedRoles(requestData)
                .then(
                    function(response) {
                        vm.roles = response.data.result;
                        vm.loader = false;
                    }, handleErrorResponse
                );
        }

        function showVerifyConfirmation(id) {
//            Swal.fire({
//                    title: "Confirm Verify",
//                    text: "Do you really want to verify this role?",
//                    type: "warning",
//                    showCancelButton: true,
//                    cancelButtonClass: "btn-cancel",
//                    confirmButtonClass: "btn-danger",
//                    confirmButtonText: "Yes",
//                    cancelButtonText: "No",
//                    closeOnConfirm: false,
//                    showLoaderOnConfirm: true
//                },
//                function() {





              Swal.fire({
                                 title: "Are you sure?",
                                 text: "Do you Really Want To Verify This Role?",
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
                    verifyRole(id);

             }   });
        }

        function showDeleteConfirmation(id) {
//            Swal.fire({
//                    title: "Confirm Delete",
//                    text: "Do you really want to delete this role?",
//                    type: "warning",
//                    showCancelButton: true,
//                    cancelButtonClass: "btn-cancel",
//                    confirmButtonClass: "btn-danger",
//                    confirmButtonText: "Yes",
//                    cancelButtonText: "No",
//                    closeOnConfirm: false,
//                    showLoaderOnConfirm: true
//                },
//                function() {








                  Swal.fire({
                                     title: "Are you sure?",
                                     text: "Do you Really Want To Delete This Role?",
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
                    deleteRole(id);

               } });
        }

        function deleteRole(id) {
            vm.loader = true;
             let requestData = {
                id: id
            }
            roleService.deleteRole(requestData)
                .then(
                    function(response) {
                        vm.loader = false;
                        showSuccessAlert('DELETE');
	                    location.href = '#!/role/action/inquiry';
                    }, handleErrorResponse
                );
        }
        function verifyRole(id) {
            vm.loader = true;
             let requestData = {
                id: id
            }
            roleService.verifyRole(requestData)
                .then(
                    function(response) {
                        vm.loader = false;
                        showSuccessAlert('VERIFY');
	                    location.href = '#!/role/action/inquiry';
                    }, handleErrorResponse
                );
        }

        function handleErrorResponse(response) {
             vm.loader = false;
            var msg = response.data.message ? response.data.message : "Server Error";
            Swal.fire("Error!", msg, "error");
	        location.href = '#!/role/action/inquiry';
        }

    }

})();