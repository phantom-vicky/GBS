(function() {

    'use strict';

    angular
        .module('gbmUltraApp')
        .controller('userRoleMappingController', userRoleMappingController);

    userRoleMappingController.$inject = ['$state', '$stateParams', '$filter', '$timeout', 'userRoleMappingService'];

    function userRoleMappingController($state, $stateParams, $filter, $timeout, userRoleMappingService) {

        var vm = this;
        vm.commonYesNoMetaData = COMMON_YES_NO_METADATA;

        vm.action = $stateParams.action;
        vm.itemsPerPage = "10";

        vm.addRoleSubmit = addRoleSubmit;
        vm.modifyRoleSubmit = modifyRoleSubmit;
        vm.verifySubmit = verifySubmit;
        vm.showCancelConfirmation = showCancelConfirmation;

        setDualListOptions();
        var leftValue = [];
        var rightValue = [];

        if($state.current.name == 'user_role_mapping.add') {
            getAllRoles();
        }else if($state.current.name == 'user_role_mapping.list'){
            if(vm.action == 'modify') {
                getModifiedRoles();
            }else if(vm.action == 'verify') {
                getUnVerifiedRoles();
            }else if (vm.action == 'inquiry') {
                getRoles();
            }
        }

        if($state.current.name == 'user_role_mapping.add_details'){
            if($stateParams.obj){
                vm.userId = $stateParams.obj.id;
                vm.userName = $stateParams.obj.name;
                if(vm.userId){
                     getAllRoles()
                }
		    }
        }else if($state.current.name == 'user_role_mapping.modify_details'){
            if($stateParams.obj){
                vm.userId = $stateParams.obj.id;
                vm.userName = $stateParams.obj.name;
                if(vm.userId){
                     getModifiedRoles()
                }
		    }
        }else  if($state.current.name == 'user_role_mapping.list_details'){
              if($stateParams){
                  vm.userId = $stateParams.id;
                  vm.userName = $stateParams.name;
                  if(vm.userId){
                        getRoles();
                  }
              }
       }

        function setDualListOptions() {
            vm.options = {
                leftContainerScrollEnd: function() {

                },
                rightContainerScrollEnd: function() {

                },
                leftContainerSearch: function(text) {
                    vm.userRolesUnmap = $filter('filter')(leftValue, {
                        'name': text
                    })

                },
                rightContainerSearch: function(text) {

                    vm.userRolesMap = $filter('filter')(rightValue, {
                        'name': text
                    })
                },
                leftContainerLabel: 'Available Roles',
                rightContainerLabel: 'Selected Roles',
                onMoveRight: function() {

                },
                onMoveLeft: function() {

                }

            };
        }

        function getAllRoles() {
            vm.loader = true;
            let requestData = {
                userId: vm.userId? vm.userId : ''
            }
            userRoleMappingService.getAllRoles(requestData)
                .then(
                    function(response) {
                        vm.users = response.data.result;
                        if(vm.users && vm.users.unmappedUserRoleList){
                            vm.userRolesUnmap = vm.users.unmappedUserRoleList;
                            angular.forEach(vm.userRolesUnmap, function(value, key) {
                                value.name = value.name;
                            });
                            leftValue = angular.copy(vm.userRolesUnmap);
                            vm.userRolesMap = [];
                            rightValue = angular.copy(vm.userRolesMap);
                        }
                        vm.loader = false;

                    }, handleErrorResponse
                );
        }

        function getModifiedRoles() {
            vm.loader = true;
            let requestData = {
                userId: vm.userId? vm.userId : ''
            }
            userRoleMappingService.getModifiedRoles(requestData)
                .then(
                    function(response) {
                        vm.users = response.data.result;
                        if(vm.users && vm.users.unmappedUserRoleList){
                            vm.userRolesUnmap = vm.users.unmappedUserRoleList;
                            angular.forEach(vm.userRolesUnmap, function(value, key) {
                                value.name = value.name;
                            });
                            leftValue = angular.copy(vm.permissions);
                        }
                        if(vm.users && vm.users.mappedUserRoleList){
                            vm.userRolesMap = vm.users.mappedUserRoleList;
                            angular.forEach(vm.userRolesMap, function(value, key) {
                                value.name = value.name;
                            });
                            rightValue = angular.copy(vm.userRolesMap);
                        }
                        vm.loader = false;
                    }, handleErrorResponse
                );
        }

        function addRoleSubmit() {
//            Swal.fire({
//                    title: "Are you sure?",
//                    text: "Do you really want to map the selected roles? ",
//                    type: "warning",
//                    showCancelButton: true,
//                    cancelButtonClass: "btn-cancel",
//                    confirmButtonClass: "btn-primary",
//                    confirmButtonText: "Yes!",
//                    closeOnConfirm: false,
//                    showLoaderOnConfirm: true
//                },
//                function() {



                      Swal.fire({
                                         title: "Are you sure?",
                                            text: "Do you really want to map the selected roles? ",
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
                    vm.loader = true;
                    vm.roleList = [];
                    angular.forEach(vm.userRolesMap, function(value, key) {
                        vm.roleList.push(value.id);
                    });
                    let request = {
                        userId: vm.userId,
                        roleList: vm.roleList
                    }
                    userRoleMappingService.addRoleSubmit(request)
                    .then(function(response) {
                            Swal.fire("Roles Mapped Successfully. \n 'Pending for Approval'",response.data.message,"success");
                            location.href = '#!/user_role_mapping/action/inquiry';
                        }, handleErrorResponse
                    );
               } });
        }

        function modifyRoleSubmit() {
//            Swal.fire({
//                    title: "Are you sure?",
//                    text: "Do you really want to modify the selected roles? ",
//                    type: "warning",
//                    showCancelButton: true,
//                    cancelButtonClass: "btn-cancel",
//                    confirmButtonClass: "btn-primary",
//                    confirmButtonText: "Yes!",
//                    closeOnConfirm: false,
//                    showLoaderOnConfirm: true
//                },
//                function() {



               Swal.fire({
                                  title: "Are you sure?",
                                  text: "Do you want to Really Modify this record?",
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
                    vm.loader = true;
                    vm.roleList = [];
                    angular.forEach(vm.userRolesMap, function(value, key) {
                        vm.roleList.push(value.id);
                    });
                    let request = {
                        userId: vm.userId,
                        roleList: vm.roleList
                    }
                    userRoleMappingService.modifyRoleSubmit(request)
                    .then(function(response) {
                            Swal.fire("Roles Modified Successfully. \n 'Pending for Approval'",response.data.message,"success");
                            location.href = '#!/user_role_mapping/action/inquiry';
                        }, handleErrorResponse
                    );
               } });
        }

        function getRoles() {
            vm.loader = true;
            let requestData = {
                userId: vm.userId? vm.userId : ''
            }
            userRoleMappingService.getRoles(requestData)
                .then(
                    function(response) {
                        vm.users = response.data.result;
                        vm.loader = false;
                    }, handleErrorResponse
                );
        }

        function getUnVerifiedRoles() {
            vm.loader = true;
            let requestData = {
                userId: vm.userId? vm.userId : ''
            }
            userRoleMappingService.getUnVerifiedRoles(requestData)
                .then(
                    function(response) {
                        vm.users = response.data.result;
                        vm.loader = false;
                    }, handleErrorResponse
                );
        }


        function verifySubmit() {
            Swal.fire({
                title: "Confirm Verify",
                text: "Do you really want to verify this roles?",
                type: "warning",
                showCancelButton: true,
                cancelButtonClass: "btn-cancel",
                confirmButtonClass: "btn-primary",
                confirmButtonText: "Yes",
                cancelButtonText: "No",
                closeOnConfirm: true
            },
            function() {
                vm.loader = true;
                 let requestData = {
                    userId: vm.userId
                }
                userRoleMappingService.verifySubmit(requestData)
                    .then(
                        function(response) {
                            vm.loader = false;
                            showSuccessAlert('VERIFY');
                            location.href = '#!/user_role_mapping/action/inquiry';
                        }, handleErrorResponse
                    );
            });
        }


        function handleErrorResponse(response) {
            vm.loader = false;
            var msg = response.data.message ? response.data.message : "Server Error";
            Swal.fire("Error!", msg, "error");

            $timeout(function(){
                location.href = '#!/user_role_mapping/action/inquiry';
            },300)
        }


        function showCancelConfirmation() {
//            Swal.fire({
//                    title: "Confirm Cancel",
//                    text: "Do you really want to cancel?",
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
                                   title: "Confirm Cancel?",
                                   text: "Do you Really Want To Cancel?",
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
                    $state.go('user_role_mapping.list', {
                        'action': 'modify'
                    })

              }  });
        }


    }

})();