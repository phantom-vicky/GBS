(function() {

    'use strict';

    angular
        .module('gbmUltraApp')
        .controller('AdminController', AdminController);

    AdminController.$inject = ['$scope', '$state', '$stateParams', '$filter', '$timeout', '$rootScope', 'adminService', 'roleService'];

    function AdminController($scope, $state, $stateParams, $filter, $timeout, $rootScope, adminService, roleService) {
        var vm = this;
		vm.solDate = $rootScope.globals.currentUser.systemDate;
        vm.showMappingConfirmation = showMappingConfirmation;
        vm.showCancelConfirmation = showCancelConfirmation;
       // vm.editModifyUser = editModifyUser;
        vm.editModifySubmit = editModifySubmit;
		vm.commonYesNoMetaData = COMMON_YES_NO_METADATA;

        var leftValue = [];
        var rightValue = [];

        if ($state.current.name == 'user.roles') {
            getUser();
            getUserRoles();
            setDualListOptions();
        } else if ($state.current.name == 'user.modify') {
            getUser();
        } else if ($state.current.name == 'user.modify_edit') {
            getAllUsers();
            vm.disableDateOptions = {
                showWeeks: false,
                minDate: vm.solDate
            }
        }

        /*function editModifyUser() {
            vm.loader = true;
            adminService.getUser($stateParams.id)
                .then(
                    function(response) {
                        vm.loader = false;
                        vm.userDetails = response.data;
                        console.log(response);
                    }, handleErrorResponse
                );
        }*/


        function editModifySubmit() {

            var requestData = {
                'id':vm.userDetails.id,
                'solId':vm.userDetails.solId,
                'departmentCode':vm.userDetails.departmentCode,
                'locationCode':vm.userDetails.locationCode,
                'applicationName':vm.userDetails.applicationName,
                'supervisorCode':vm.userDetails.supervisorCode,
                'userType':vm.userDetails.userType,
                'userDomain':vm.userDetails.userDomain,
                'employeeCode':vm.userDetails.employeeCode,
                'lastName':vm.userDetails.lastName,
                'firstName':vm.userDetails.firstName,
                'username':vm.userDetails.username,
                'password':vm.password ,
                'userStatus':((vm.userDetails.userDisableStatus=='Y') ? ('N'):('Y')),
                'userDisabledTill':((vm.userDetails.userDisableStatus=='Y') ? (vm.userDetails.userDisabledTill) :(''))
            };

//            Swal.fire({
//                title: "Are you sure?",
//                text: "Do you want to Really Modify this record?",
//                type: "warning",
//                showCancelButton: true,
//                cancelButtonClass: "btn-cancel",
//                confirmButtonClass: "btn-primary",
//                confirmButtonText: "Yes!",
//                closeOnConfirm: true,
//
//            }, function() {


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
                adminService.editModifySubmit(requestData).then(function(response) {
                    vm.loader = false;
                    $timeout(function(){
//                        Swal.fire({
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
                                location.href = '#!/user/action/modify';
                             }
                        });
                    },300)
                }, function(error) {
                    vm.loader = false;
                    handleErrorResponse(error);
                    getUsers();
                });
          }  });
        }

        function getUser() {
            adminService.getUser($stateParams.id)
                .then(
                    function(response) {
                        vm.userDetails = response.data;
                    }, handleErrorResponse
                );
        }

        function getAllUsers() {
            vm.loader= true;
            let requestData = {
                id: $stateParams.id
            }
            adminService.getAllUsers(requestData)
                .then(
                    function(response) {
                        vm.loader= false;
                        vm.userDetails = response.data.result;
                        vm.userDetails.userDisableStatus = ((vm.userDetails.userStatus=='Y') ? ('N'):('Y'));
                        vm.userDetails.userDisableStatusNew = ((vm.userDetails.userStatusNew=='Y') ? ('N'):('Y'));
                        vm.password = response.data.result.password;
                        if(vm.userDetails.userDisabledTill){
                            vm.userDetails.userDisabledTill = Date.parse(vm.userDetails.userDisabledTill);
                            }
                    }, handleErrorResponse
                );
        }

        function getUserRoles() {
            adminService.getRoles($stateParams.id)
                .then(
                    function(response) {
                        vm.userRoles = response.data;

                        angular.forEach(vm.userRoles, function(value, key) {
                            value.name = value.name;
                        });
                        rightValue = angular.copy(vm.userRoles);
                        getRoles();
                    }, handleErrorResponse
                );
        }

        function getRoles() {
            roleService.getRoles()
                .then(
                    function(response) {
                        vm.roles = response.data;

                        vm.roles = vm.roles.filter(function(item1) {
                            for (var i in vm.userRoles) {
                                if (item1.name === vm.userRoles[i].name) {
                                    return false;
                                }
                            };
                            return true;
                        });

                        angular.forEach(vm.roles, function(value, key) {
                            value.name = value.name;
                        });
                        leftValue = angular.copy(vm.roles);


                    }, handleErrorResponse
                );
        }

        function showMappingConfirmation(id) {
//            Swal.fire({
//                    title: "Are you sure?",
//                    text: "Do you really want To map the selected roles? ",
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
                                   text: "Do you really want To map the selected roles? ",
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
                    saveUserRoleMapping();

            }    });
        }

        function saveUserRoleMapping() {
            adminService.saveUserRoleMapping($stateParams.id, getRoleIds())
                .then(
                    function(response) {
                        Swal.fire("Mapped!", response.data.message, "success");
                        //$scope.$emit('showAlert', { 'type': 'true', 'msg': response.data.message });
                    }, handleErrorResponse
                );
        }

        function getRoleIds() {
            var roleIds = [];
            angular.forEach(vm.userRoles, function(value, key) {
                roleIds.push(value.id);
            });
            return roleIds;
        }

        function handleErrorResponse(response) {
            var msg = response.data.message ? response.data.message : "Server Error";
            Swal.fire("Error!", msg, "error");
	        location.href = '#!/user/action/inquiry';
        }

        function setDualListOptions() {
            vm.options = {
                leftContainerScrollEnd: function() {

                },
                rightContainerScrollEnd: function() {

                },
                leftContainerSearch: function(text) {
                    vm.roles = $filter('filter')(leftValue, {
                        'name': text
                    })

                },
                rightContainerSearch: function(text) {
                    vm.userRoles = $filter('filter')(rightValue, {
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
                    title: "Are you sure?",
                    text: "Do you Really Want To cancel?",
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
                    location.href = '#!/user/action/modify';

              }  });
        }
    }

})();