(function() {

   'use strict';
    angular
        .module('gbmUltraApp')
        .controller('rolePermissionMappingController', rolePermissionMappingController);
    rolePermissionMappingController.$inject = ['$scope','$state', '$stateParams', '$filter', 'rolePermissionMappingService'];
    function rolePermissionMappingController($scope,$state, $stateParams, $filter, rolePermissionMappingService) {

        var vm = this;
        vm.commonYesNoMetaData = COMMON_ENABLE_DISABLE_METADATA;
        vm.isSelectionValid = false;
        vm.action = $stateParams.action;
        vm.itemsPerPage = "10";
        vm.remarks = '';
        vm.addPermissionSubmit = addPermissionSubmit;
        vm.modifyPermissionSubmit = modifyPermissionSubmit;
        vm.verifySubmit = verifySubmit;
        vm.deleteSubmit = deleteSubmit;
        vm.deleteRemarks = deleteRemarks;
        vm.showCancelConfirmation = showCancelConfirmation;
        setDualListOptions();
        var leftValue = [];
        var rightValue = [];
        vm.validateInput=validateInput;
        vm.arrowEnableDisable = false;
        vm.isEnableDisable = isEnableDisable;
        // Function to update selection validity
        function updateSelectionValidity() {
            vm.isSelectionValid = vm.rolePermissions && vm.rolePermissions.length > 0;
        }

        // Watch for changes in right scope (rolePermissions)
        $scope.$watchCollection('vm.rolePermissions', function() {
            updateSelectionValidity();
        });

        if($state.current.name == 'role_permission_mapping.add') {
            getAllRoles();
        }else if($state.current.name == 'role_permission_mapping.list'){
            if(vm.action == 'modify') {
                getModifiedRoles();
            }else if(vm.action == 'verify') {
                getUnVerifiedRoles();
            }else if (vm.action == 'inquiry') {
                getRoles();
            }
        }

        if($state.current.name == 'role_permission_mapping.add_details'){
            if($stateParams.obj){
                vm.roleId = $stateParams.obj.id;
                vm.roleName = $stateParams.obj.name;
                if(vm.roleId){
                     getAllRoles()
                }
		    }
        }else if($state.current.name == 'role_permission_mapping.modify_details'){
            if($stateParams.obj){
                vm.roleId = $stateParams.obj.id;
                vm.roleName = $stateParams.obj.name;
                if(vm.roleId){
                     getModifiedRoles()
                }
		    }
        }else if($state.current.name == 'role_permission_mapping.list_details'){
            if($stateParams){
                vm.roleId = $stateParams.id;
                vm.roleName = $stateParams.name;
                if(vm.roleId){
                     getRoles()
                }
		    }
        }

        function isEnableDisable(){
        console.log(vm.roles.disableRole);
         if(vm.roles.disableRole == 0){
         vm.arrowEnableDisable = false;
         }
         else{
         vm.arrowEnableDisable = true;
         }


        }
        function setDualListOptions() {
            vm.options = {
                leftContainerScrollEnd: function() {

                },
                rightContainerScrollEnd: function() {

                },
                leftContainerSearch: function(text) {
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

        function getAllRoles() {
            vm.loader = true;
            let requestData = {
                roleId: vm.roleId? vm.roleId : ''
            }
            rolePermissionMappingService.getAllRoles(requestData)
                .then(
                    function(response) {
                        vm.roles = response.data.result;


                        if(vm.roles && vm.roles.unmappedPermissionList){
                            vm.permissions = vm.roles.unmappedPermissionList;
                            angular.forEach(vm.permissions, function(value, key) {
                                value.name = value.permissionName;
                            });
                            leftValue = angular.copy(vm.permissions);
                            vm.rolePermissions = [];
                            rightValue = angular.copy(vm.rolePermissions);
                        }
                        vm.loader = false;

                    }, handleErrorResponse
                );
        }

        function getModifiedRoles() {
            vm.loader = true;
            let requestData = {
                roleId: vm.roleId? vm.roleId : ''
            }
            rolePermissionMappingService.getModifiedRoles(requestData)
                .then(
                    function(response) {
                        vm.roles = response.data.result;
                        if(vm.roles.disableRole == 0){
                                 vm.arrowEnableDisable = false;
                                 }
                                 else{
                                 vm.arrowEnableDisable = true;
                                 }
                        if(vm.roles && vm.roles.unmappedPermissionList){
                            vm.permissionsModify = vm.roles.unmappedPermissionList;
                            angular.forEach(vm.permissionsModify, function(value, key) {
                                value.name = value.permissionName;
                            });
                            console.log(vm.permissionsModify);
                            leftValue = angular.copy(vm.permissions);
                        }
                        if(vm.roles && vm.roles.mappedPermissionList){
                            vm.rolePermissionsModify = vm.roles.mappedPermissionList;
                            angular.forEach(vm.rolePermissionsModify, function(value, key) {
                                value.name = value.permissionName;
                            });
                            console.log(vm.rolePermissionsModify);
                            rightValue = angular.copy(vm.rolePermissions);
                        }
                        vm.loader = false;
                    }, handleErrorResponse
                );
        }

        function addPermissionSubmit() {
//            Swal.fire({
//                    title: "Are you sure?",
//                    text: "Do you really want to add and map the permissions for this role? ",
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
                          text: "Do you really want to add and map the permissions for this role? ",
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
                    vm.permissionList = [];
                    angular.forEach(vm.rolePermissions, function(value, key) {
                        vm.permissionList.push(value.permissionId);
                    });
                    let request = {
                        roleName: vm.role.name,
                        roleDescription: vm.role.description,
                        permissionList: vm.permissionList
                    }
                    rolePermissionMappingService.addPermissionSubmit(request)
                    .then(function(response) {
                            Swal.fire("Added!", " Role Added/ Mapped Successfully", "success");
                            location.href = '#!/role_permission_mapping/action/inquiry';
                        }, handleErrorResponse
                    );
             }   });
        }

        function modifyPermissionSubmit() {
//            Swal.fire({
//                    title: "Are you sure?",
//                    text: "Do you really want to modify? ",
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
                    text: "Do you want to Really Modify?",
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
                    vm.permissionList = [];
                    angular.forEach(vm.rolePermissionsModify, function(value, key) {
                        vm.permissionList.push(value.permissionId);
                    });
                    let request = {
                        roleId: vm.roleId,
                        roleName: vm.roles.roleName,
                        roleDescription: vm.roles.roleDescription,
                        disableRole: vm.roles.disableRole,
                        permissionList: vm.permissionList
                    }
                    rolePermissionMappingService.modifyPermissionSubmit(request)
                    .then(function(response) {
                            Swal.fire("Modified!", " Role Modified/ Mapped Successfully \n Pending for Authorization", "success");

                            location.href = '#!/role_permission_mapping/action/inquiry';
                        }, handleErrorResponse
                    );
              }  });
        }

        function getRoles() {
            vm.loader = true;
            let requestData = {
                roleId: vm.roleId? vm.roleId : ''
            }
            rolePermissionMappingService.getRoles(requestData)
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
                roleId: vm.roleId? vm.roleId : ''
            }
            rolePermissionMappingService.getUnVerifiedRoles(requestData)
                .then(
                    function(response) {
                        vm.roles = response.data.result;
                        vm.loader = false;
                    }, handleErrorResponse
                );
        }

      function deleteRemarks(){
          angular.element('#permissionMappingDelete').modal('toggle');

         }
     function validateInput(event,id){

        const textBox = document.getElementById(id);

          var charCode = event.which || event.keyCode;
            var charTyped = String.fromCharCode(charCode);

    if(textBox)
    {
     const cursorPosition = textBox.selectionStart;
        if(cursorPosition == 0 && charTyped === ' ')
        {
                event.preventDefault();
                return;

        }
        }
          var currentValue = event.target.value + charTyped;
            var pattern = /^[a-zA-Z\s]*'?([a-zA-Z\s]*'?)*$/;

    //        // Check if more than one apostrophe is present
    //        if ((currentValue.match(/'/g) || []').length > 1) {
    //            event.preventDefault();
    //            return;
    //        }

            if (!pattern.test(currentValue)) {
                event.preventDefault();
            }
        }


vm.rejectReason=rejectReason;
function rejectReason() {
  angular.element('#permissionMappingDelete').modal('toggle');
  }


  vm.getRemarksList = function () {
                vm.referenceId = "058";
                var requestData = { referenceId: vm.referenceId };

                rolePermissionMappingService.getRemarksList(requestData).then(
                  function (response) {
                    vm.remarksList = response.data.result || []; // Ensure it's an array
                  },
                  function (error) {
                    handleErrorResponse(error);
                  }
                );
              };
function deleteSubmit() {




 vm.loader = true;
        var requestData = {
            roleId: vm.roleId,
            remarks: vm.deleteRemark
        };

        rolePermissionMappingService.deleteSubmit(requestData).then(function(response) {
            vm.loader = false;
            Swal.fire("Rejected!", "Rejected Successfully", "success");
            location.href = '#!/role_permission_mapping/action/inquiry';
                 const backdrop = document.querySelector('.modal-backdrop');
                                                                        if (backdrop) {
                                                                          backdrop.parentNode.removeChild(backdrop);
                                                                          document.body.classList.remove('modal-open');
                                                                        }
        }, function(error){
                           vm.loader = false;
                           handleErrorResponse(error);
                            const backdrop = document.querySelector('.modal-backdrop');
                             if (backdrop) {
                               backdrop.parentNode.removeChild(backdrop);
                               document.body.classList.remove('modal-open');
                             }
                              location.href = '#!/role_permission_mapping/action/inquiry';
                       });

}

//           function deleteSubmit(){
//
//               Swal.fire({
//                   title: "Rejection Remarks",
//                   text: "",
//                   type: "input",
//                   inputPlaceholder: "Enter your remarks here",
//                   showCancelButton: true,
//                   cancelButtonClass: "btn-cancel",
//                   confirmButtonClass: "btn-primary",
//                   confirmButtonText: "Submit",
//                   animation: "slide-from-top",
//                   closeOnConfirm: false,
//                   showLoaderOnConfirm: true
//
//
//               },
//               function(inputValue) {
//                   if (inputValue === false) return false;
//
//                   if (inputValue === "") {
//                       Swal.fire.showInputError("Please enter the remarks!");
//                       return false;
//                   }
//                   vm.loader = true;
//                   var requestData = {
//                       roleId: vm.roleId,
//                       remarks: inputValue
//                   };
//
//                   rolePermissionMappingService.deleteSubmit(requestData).then(function(response) {
//                            vm.loader = false;
//                            Swal.fire("Rejected!", "Rejected Successfully", "success");
//                            location.href = '#!/role_permission_mapping/action/inquiry';
//                        }, handleErrorResponse
//                    );
//               });
//
//                }

           function verifySubmit() {
//            Swal.fire({
//                title: "Confirm Verify",
//                text: "Do you really want to verify this permission?",
//                type: "warning",
//                showCancelButton: true,
//                cancelButtonClass: "btn-cancel",
//                confirmButtonClass: "btn-primary",
//                confirmButtonText: "Yes",
//                cancelButtonText: "No",
//                closeOnConfirm: false,
//                showLoaderOnConfirm: true
//
//            },
//            function() {











               Swal.fire({
                                   title: "Are you sure?",
                                   text: "Do you want to Really Verify this Permission?",
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
                 let requestData = {
                    roleId: vm.roleId
                }
                rolePermissionMappingService.verifySubmit(requestData)
                    .then(
                        function(response) {
                            vm.loader = false;
                            Swal.fire("Verified!", " Role Verified/ Mapped Successfully", "success");
                            location.href = '#!/role_permission_mapping/action/inquiry';
                        }, handleErrorResponse
                    );
          }  });
            }


        function handleErrorResponse(response) {
            vm.loader = false;
            var msg = response.data.message ? response.data.message : "Server Error";
            Swal.fire("Error!", msg, "error");
            location.href = '#!/role_permission_mapping/action/inquiry';
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
//                    closeOnConfirm: true,
//                    showLoaderOnConfirm: true
//
//                },
//                function() {


             Swal.fire({
                                 title: "Are you sure?",
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
                    if($state.current.name == 'role_permission_mapping.add_details'){
                        $state.go('role_permission_mapping.add')
                    }else{
                        $state.go('role_permission_mapping.list', {
                            'action': 'modify'
                        })
                    }
              }  });
        }

    }

})();