(function() {

    'use strict';

    angular
        .module('gbmUltraApp')
        .controller('UserListController', UserListController);

    UserListController.$inject = ['$state', '$stateParams', '$filter', 'adminService'];

    function UserListController($state, $stateParams,$filter,adminService) {

        var vm = this;

        vm.commonYesNoMetaData = COMMON_YES_NO_METADATA;
        vm.action = $stateParams.action;
        vm.showDeleteConfirmation = showDeleteConfirmation;
        vm.showVerifyConfirmation = showVerifyConfirmation;
        vm.showDisableConfirmation = showDisableConfirmation;
        vm.activeStatusChange=activeStatusChange;
        vm.activeUser=activeUser;

        vm.itemsPerPage = "10";

        vm.userTypeList = [
            {value: 'B', label: 'BANK'},
            {value: 'C', label: 'CONTRACT'}
        ]

        vm.dateOptions = {
            minDate: new Date(),
            showWeeks: false
        }

        if(vm.action == 'modify') {
            getAllUsers();
        }else if(((vm.action == 'verify') || (vm.action == 'verify' && $state.current.name == 'user.list_details')) ||
            ((vm.action == 'delete') || (vm.action == 'delete' && $state.current.name == 'user.list_details'))) {
            getUnVerifiedUsers();
        }else if ((vm.action == 'inquiry') || (vm.action == 'inquiry' && $state.current.name == 'user.list_details')) {
             getUsers();
        }

        function activeStatusChange(id)
        {
//            Swal.fire({
//                    title: "Confirm Active",
//                    text: "Do you wish to enable the User ID ?",
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
                    title: "Confirm Active?",
                    text: "Do you wish to enable the User ID ?",
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

                    activeUser(id);
               } });
        }

        function getUsers() {
            vm.loader = true;
            let requestData = {
                id: $stateParams.id? $stateParams.id : ''
            }
            adminService.getUsers(requestData)
                .then(
                    function(response) {
                        vm.users = response.data.result;
                        vm.users.userDisableStatus = ((vm.users.userStatus=='Y') ? ('N'):('Y'));
                        vm.users.userDisableStatusNew = ((vm.users.userStatusNew=='Y') ? ('N'):('Y'));
                        vm.loader = false;
                    }, handleErrorResponse
                );
        }
        function getAllUsers() {
            vm.loader = true;
            let requestData = {
                id: ''
            }
            adminService.getAllUsers(requestData)
                .then(
                    function(response) {
                        vm.users = response.data.result;
                        vm.loader = false;
                    }, handleErrorResponse
                );
        }
        function getUnVerifiedUsers() {
            vm.loader = true;
            let requestData = {
                id: $stateParams.id? $stateParams.id : ''
            }
            adminService.getUnVerifiedUsers(requestData)
                .then(
                    function(response) {
                        vm.users = response.data.result;
                        vm.users.userDisableStatus = ((vm.users.userStatus=='Y') ? ('N'):('Y'));
                        vm.users.userDisableStatusNew = ((vm.users.userStatusNew=='Y') ? ('N'):('Y'));
                        vm.loader = false;
                    }, handleErrorResponse
                );
        }

        function showDeleteConfirmation(id) {
//            Swal.fire({
//                    title: "Confirm Delete",
//                    text: "Do you really want to delete this user?",
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
                    text: "Do you want to Really Delete this record?",
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
                    deleteUser(id);

             }   });
        }
        function showVerifyConfirmation(id,password) {
//            Swal.fire({
//                    title: "Confirm Verify",
//                    text: "Do you really want to verify this user?",
//                    type: "warning",
//                    showCancelButton: true,
//                    cancelButtonClass: "btn-cancel",
//                    confirmButtonClass: "btn-primary",
//                    confirmButtonText: "Yes",
//                    cancelButtonText: "No",
//                    closeOnConfirm: true
//                },
//                function() {

     Swal.fire({
                    title: "Are you sure?",
                    text: "Do you want to Really Verify this record?",
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
                    verifyUser(id,password);

               } });
        }

        function showDisableConfirmation(id, disabledTill) {
            jQuery('#disableUserModal').modal('toggle');
//            Swal.fire({
//                    title: "Confirm Disable",
//                    text: "Do you really want to disable this user?",
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
                    title: "confirm Disable?",
                   text: "Do you really want to disable this user?",
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
                    disableUser(id, new Date(disabledTill).getTime());
             }   });


        }

        function deleteUser(id) {
            vm.loader = true;
             let requestData = {
                id: id
            }
            adminService.deleteUser(requestData)
                .then(
                    function(response) {
                        vm.loader = false;
                        showSuccessAlert('DELETE');
	                    location.href = '#!/user/action/inquiry';
                    }, handleErrorResponse
                );
        }

        function verifyUser(id,password) {
            vm.loader = true;
             let requestData = {
                id: id,
                password: password
            }
            adminService.verifyUser(requestData)
                .then(
                    function(response) {
                        vm.loader = false;
                        showSuccessAlert('VERIFY');
	                    location.href = '#!/user/action/inquiry';
                    }, handleErrorResponse
                );
        }

        function disableUser(id, disabledTill) {
          vm.loader = true;
        disabledTill=$filter('date')(disabledTill, "yyyy-MM-dd");
                    adminService.disableUser(id, disabledTill)
                .then(
                    function(response) {
                       vm.loader = false;
                        Swal.fire("Disabled !", response.data.message, "success");
                        getUsers();
                    }, handleErrorResponse
                );
        }

        function activeUser(id) {
            vm.loader = true;
            adminService.activeUser(id)
                .then(
                    function(response) {
                    vm.loader = false;
                        Swal.fire("Activated !","User ID is enabled successfully", "success");
                         getUsers();
                    }, handleErrorResponse
                );
        }

        function handleErrorResponse(response) {
            vm.loader = false;
            var msg = response.data.message ? response.data.message : "Server Error";
            Swal.fire("Error!", msg, "error");
	        location.href = '#!/user/action/inquiry';
        }

    }

})();