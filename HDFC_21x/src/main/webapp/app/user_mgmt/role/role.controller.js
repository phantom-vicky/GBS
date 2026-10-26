(function() {

    'use strict';

    angular
        .module('gbmUltraApp')
        .controller('RoleController', RoleController);

    RoleController.$inject = ['$filter', '$stateParams', '$state', 'roleService', 'permissionService'];

    function RoleController($filter, $stateParams, $state, roleService, permissionService) {

        var vm = this;
        vm.commonYesNoMetaData = COMMON_YES_NO_METADATA;

        if ($state.current.name == 'role.modify') {
            getModifiedRoles();
        } else if ($state.current.name == 'role.permissions') {
            getRole();
            setDualListOptions();
            getRolePermissions();
        }

        vm.addRoleConfirmation = addRoleConfirmation;
        vm.updateRoleConfirmation = updateRoleConfirmation;
        vm.saveUserPermissionMappingConfirmation = saveUserPermissionMappingConfirmation;
        vm.showCancelConfirmation = showCancelConfirmation;

        vm.role = {};
        vm.permissions = [];
        vm.rolePermissions = [];

        var leftValue = [];
        var rightValue = [];

        function getModifiedRoles() {
            vm.loader = true;
            let requestData = {
                id: $stateParams.id? $stateParams.id : ''
            }
            roleService.getModifiedRoles(requestData)
                .then(
                    function(response) {
                        vm.role = response.data.result;
                        vm.loader = false;
                    }, handleErrorResponse
                );
        }

        function addRoleConfirmation() {
//            Swal.fire({
//                    title: "Are you sure?",
//                    text: "Do You Really Want to Add This Role? ",
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
                    text: "Do you want to Really Add This Role?",
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
                    addRole();
              }  });
        }

        function addRole() {
            vm.loader = true;
            roleService.addRole(vm.role)
                .then(
                    function(response) {
                        vm.loader = false;
                        Swal.fire("Added!", response.data.message, "success");
                        vm.role = {};
	                    location.href = '#!/role/action/inquiry';
                    }, handleErrorResponse
                );
        }

        function updateRoleConfirmation() {
//            Swal.fire({
//                    title: "Are you sure?",
//                    text: "Do You Really Want to update This Role? ",
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
                       text: "Do you want to Really Update This Role?",
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
                    updateRole();
             }   });
        }

        function updateRole() {
            vm.loader = true;
            if(vm.role.disableRole){
                vm.role.disableRole = 'Y'
            }else{
                vm.role.disableRole = 'N'
            }
            roleService.updateRole(vm.role)
                .then(
                    function(response) {
                        vm.loader = false;
//                         Swal.fire({
//                             title : "Modified!",
//                             text : response.data.message,
//                             type : "success",
//                             showCancelButton : false,
//                             confirmButtonClass : "btn-primary",
//                             confirmButtonText : "Yes",
//                             closeOnConfirm : true
//                        }, function(inputValue) {
//                             if(inputValue){



                               Swal.fire({
                                                  title : "Modified!",
                                                 text : response.data.message,
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
                                location.href = '#!/role/action/modify';
                             }
                        });
                    }, handleErrorResponse
                );
        }

        function getPermissions() {
            permissionService.getAllPermissions()
                .then(
                    function(response) {
                        vm.permissions = response.data;

                        vm.permissions = vm.permissions.filter(function(item1) {
                            for (var i in vm.rolePermissions) {
                                if (item1.displayName === vm.rolePermissions[i].displayName) {
                                    return false;
                                }
                            };
                            return true;
                        });

                        angular.forEach(vm.permissions, function(value, key) {
                            value.name = value.displayName;
                        });
                        leftValue = angular.copy(vm.permissions);
                    }, handleErrorResponse
                );
        }

        function getRolePermissions() {
            roleService.getPermissions($stateParams.id)
                .then(
                    function(response) {
                        vm.rolePermissions = response.data;

                        angular.forEach(vm.rolePermissions, function(value, key) {
                            value.name = value.displayName;
                        });
                        rightValue = angular.copy(vm.rolePermissions);
                        getPermissions();
                    }, handleErrorResponse
                );
        }

        function saveUserPermissionMappingConfirmation() {
            Swal.fire({
                    title: "Are you sure?",
                    text: "Do you really want To map the selected permissions? ",
                    type: "warning",
                    showCancelButton: true,
                    cancelButtonClass: "btn-cancel",
                    confirmButtonClass: "btn-primary",
                    confirmButtonText: "Yes!",
                    closeOnConfirm: false,
                    showLoaderOnConfirm: true
                },
                function() {
                    saveUserPermissionMapping();
                });
        }

        function saveUserPermissionMapping() {
            roleService.saveUserPermissionMapping($stateParams.id, getPermissionIds())
                .then(
                    function(response) {
                        Swal.fire("Permited!", response.data.message, "success");
                    }, handleErrorResponse
                );
        }

        function getPermissionIds() {
            var permissionIds = [];
            angular.forEach(vm.rolePermissions, function(value, key) {
                permissionIds.push(value.id);
            });
            return permissionIds;
        }

        function handleErrorResponse(response) {
            vm.loader =  false;
            var msg = response.data.message ? response.data.message : "Server Error";
            Swal.fire("Error!", msg, "error");
	        location.href = '#!/role/action/inquiry';
        }

        function setDualListOptions() {
            vm.options = {
                leftContainerScrollEnd: function() {

                },
                rightContainerScrollEnd: function() {

                },
                leftContainerSearch: function(text) {
                    console.log(text)
                    vm.permissions = $filter('filter')(leftValue, {
                        'name': text
                    })

                },
                rightContainerSearch: function(text) {

                    vm.rolePermissions = $filter('filter')(rightValue, {
                        'name': text
                    })
                },
                leftContainerLabel: 'Available Permissions',
                rightContainerLabel: 'Selected Permissions',
                onMoveRight: function() {

                },
                onMoveLeft: function() {

                }

            };
        }

        function showCancelConfirmation() {
            Swal.fire({
                    title: "Confirm Cancel",
                    text: "Do you really want to cancel?",
                    type: "warning",
                    showCancelButton: true,
                    cancelButtonClass: "btn-cancel",
                    confirmButtonClass: "btn-danger",
                    confirmButtonText: "Yes",
                    cancelButtonText: "No",
                    closeOnConfirm: true
                },
                function() {
                    $state.go('role.list', {
                        'action': 'modify'
                    })

                });
        }

    }

})();