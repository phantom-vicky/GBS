(function() {

   'use strict';
    angular
        .module('gbmUltraApp')
        .controller('commonFlagController', commonFlagController);
    commonFlagController.$inject = ['$scope','$state', '$stateParams', '$filter', 'commonFlagService'];
    function commonFlagController($scope,$state, $stateParams, $filter, commonFlagService) {

        var vm = this;
        vm.commonYesNoMetaData = COMMON_ENABLE_DISABLE_METADATA;
        vm.isSelectionValid = false;
        vm.action = $stateParams.action;
        vm.itemsPerPage = "10";
        vm.remarks = '';
        vm.addPermissionSubmit = addPermissionSubmit;
        vm.submitModifyForm = submitModifyForm;
        vm.getInquiryRoles = getInquiryRoles;
        vm.verifySubmit = verifySubmit;
        vm.deleteSubmit = deleteSubmit;
        vm.deleteRemarks = deleteRemarks;
        vm.showCancelConfirmation = showCancelConfirmation;
        vm.getModifiedRoles = getModifiedRoles;
        vm.getUnVerifiedRoles =  getUnVerifiedRoles;
        vm.getLeftRightValue = getLeftRightValue;
        vm.getRemarksList = getRemarksList;
        setDualListOptions();
        var leftValue = [];
        var rightValue = [];

         vm.unmappedSol = [];
         vm.mappedSol = [];
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

//        if($state.current.name == 'common_flag.add') {
//            getAllRoles();
//        }else if($state.current.name == 'common_flag.list'){
//            if(vm.action == 'modify') {
//                getModifiedRoles();
//            }else if(vm.action == 'verify') {
//                getUnVerifiedRoles();
//            }else if (vm.action == 'inquiry') {
//                getRoles();
//            }
//        }

        if($state.current.name == 'common_flag.add_detail'){
            if($stateParams.obj){
                vm.roleId = $stateParams.obj.id;
                vm.roleName = $stateParams.obj.name;
                if(vm.roleId){
                     getAllRoles()
                }
		    }
        }else if($state.current.name == 'common_flag.modify_detail'){
            if($stateParams.obj){
                vm.schemeCode = $stateParams.obj.schemeCode;
                  vm.schemeName = $stateParams.obj.schemeName;

                if(vm.schemeCode){
                     getModifiedRoles()
                }
		    }
        }else if($state.current.name == 'common_flag.verify_detail'){


		      if($stateParams.obj){
                              vm.schemeCode = $stateParams.obj.schemeCode;
                               vm.schemeName = $stateParams.obj.schemeName;

                             // vm.roleName = $stateParams.obj.name;
                              if(vm.schemeCode){
                                   getUnVerifiedRoles()
                              }
              		    }
        }


        else if($state.current.name == 'common_flag.inquiry_detail'){


        		      if($stateParams.obj){
                                      vm.schemeCode = $stateParams.obj.schemeCode;
                                       vm.schemeName = $stateParams.obj.schemeName;

                                     // vm.roleName = $stateParams.obj.name;
                                      if(vm.schemeCode){
                                           getInquiryRoles()
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
                    vm.unmappedSol = $filter('filter')(leftValue, {
                     'codeDescription': text
                    })

                   },
                   rightContainerSearch: function(text) {
                    vm.mappedSol = $filter('filter')(rightValue, {
                     'codeDescription': text
                    })
                   },
                   leftContainerLabel: 'Non-Mandatory',
                   rightContainerLabel: 'Mandatory',
                   onMoveRight: function() {
                    getLeftRightValue();
                   },
                   onMoveLeft: function() {
                    getLeftRightValue();
                   }

                  };
                 }



         function getLeftRightValue() {
           leftValue = angular.copy(vm.unmappedSol);
           rightValue = angular.copy(vm.mappedSol);
          }

        function getAllRoles() {
            vm.loader = true;
            let requestData = {
                roleId: vm.roleId? vm.roleId : ''
            }
            commonFlagService.getAllRoles(requestData)
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

        function getModifiedRoles(flag) {
            vm.loader = true;


              var requestData;
                                    if (flag == 'Y') {
                                        requestData = {};

                                        commonFlagService.getModifiedRoles(requestData).then(function(response) {
                                            vm.loader = false;
                                            vm.schemeList = response.data.result.schemeList;
                                            angular.element('#schemeModel').modal('toggle');
                                        }, function(error) {
                                            handleErrorResponse(error);
                                            vm.loader = false;
                                            $state.go('common_flag.inquiry');
                                        });
                                    }

                                    else{
            let requestData = {
                schemeCode: vm.schemeCode
            }
            commonFlagService.getModifiedRoles(requestData)
                .then(
                    function(response) {
                        vm.roles = response.data.result;
                     vm.remarks = response.data.result.rejectionRemarks;
                         vm.unmappedSol = response.data.result.unmappedDocumentsList;
                                 if (response.data.result.mappedDocumentsList) {
                                  vm.mappedSol = response.data.result.mappedDocumentsList;
                                 }
                             getLeftRightValue();





//                        if(vm.roles.disableRole == 0){
//                                 vm.arrowEnableDisable = false;
//                                 }
//                                 else{
//                                 vm.arrowEnableDisable = true;
//                                 }
//                        if(vm.roles && vm.roles.unmappedPermissionList){
//                            vm.permissionsModify = vm.roles.unmappedPermissionList;
//                            angular.forEach(vm.permissionsModify, function(value, key) {
//                                value.name = value.permissionName;
//                            });
//                            console.log(vm.permissionsModify);
//                            leftValue = angular.copy(vm.permissions);
//                        }
//                        if(vm.roles && vm.roles.mappedPermissionList){
//                            vm.rolePermissionsModify = vm.roles.mappedPermissionList;
//                            angular.forEach(vm.rolePermissionsModify, function(value, key) {
//                                value.name = value.permissionName;
//                            });
//                            console.log(vm.rolePermissionsModify);
//                            rightValue = angular.copy(vm.rolePermissions);
//                        }
                        vm.loader = false;
                    }, handleErrorResponse
                );
        }
}




       function getInquiryRoles(flag) {
            vm.loader = true;


              var requestData;
                                    if (flag == 'Y') {
                                        requestData = {};

                                        commonFlagService.getInquiryRoles(requestData).then(function(response) {
                                            vm.loader = false;
                                            vm.schemeList = response.data.result.schemeList;
                                            angular.element('#schemeModel').modal('toggle');
                                        }, function(error) {
                                            handleErrorResponse(error);
                                            vm.loader = false;
                                            $state.go('common_flag.inquiry');
                                        });
                                    }

                                    else{
            let requestData = {
                schemeCode: vm.schemeCode
            }
            commonFlagService.getInquiryRoles(requestData)
                .then(
                    function(response) {
                        vm.mapUnmMppedDocuments = response.data.result;
                             vm.remarks = response.data.result.rejectionRemarks;




                        vm.loader = false;
                    }, handleErrorResponse
                );
        }
}


       function getUnVerifiedRoles(flag) {
            vm.loader = true;


              var requestData;
                                    if (flag == 'Y') {
                                        requestData = {};

                                        commonFlagService.getUnVerifiedRoles(requestData).then(function(response) {
                                            vm.loader = false;
                                            vm.schemeList = response.data.result.schemeList;
                                            angular.element('#schemeModel').modal('toggle');
                                        }, function(error) {
                                            handleErrorResponse(error);
                                            vm.loader = false;
                                            $state.go('common_flag.inquiry');
                                        });
                                    }

                                    else{
            let requestData = {
                schemeCode: vm.schemeCode
            }
            commonFlagService.getUnVerifiedRoles(requestData)
                .then(
                    function(response) {
                        vm.mapUnmMppedDocuments = response.data.result;
                           vm.remarks = response.data.result.rejectionRemarks;

                         vm.mappedDocumentsList = response.data.result.mappedDocumentsList;
                          vm.unmappedDocumentsList = response.data.result.unmappedDocumentsList;



                        vm.loader = false;
                    }, handleErrorResponse
                );
        }
}






        function submitModifyForm() {
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






                                       vm.mappedManList = [];
                                        vm.unMappedNonManList = [];

                                      angular.forEach(vm.mappedSol, function(value, key) {
                                       vm.mappedManList.push(value.code);
                                     });

                                    angular.forEach(vm.unmappedSol, function(value, key) {
                                             vm.unMappedNonManList.push(value.code);
                                     });



                       let requestData = {
                          roleId: vm.roleId,
                          modifiedManList : vm.mappedManList,
                          modifiedNonManList :  vm.unMappedNonManList,
                           schemeCode : vm.schemeCode
                      }





                    commonFlagService.submitModifyForm(requestData)
                    .then(function(response) {
                            Swal.fire("Modified!", " Common Flag Modified Successfully", "success");

                            location.href = '#!/common_flag/action/inquiry';
                        }, handleErrorResponse
                    );
                    }
                });
        }

        function getRoles() {
            vm.loader = true;
            let requestData = {
                roleId: vm.roleId? vm.roleId : ''
            }
            commonFlagService.getRoles(requestData)
                .then(
                    function(response) {
                        vm.roles = response.data.result;
                        vm.loader = false;
                    }, handleErrorResponse
                );
        }

//        function getUnVerifiedRoles() {
//            vm.loader = true;
//            let requestData = {
//                roleId: vm.roleId? vm.roleId : ''
//            }
//            commonFlagService.getUnVerifiedRoles(requestData)
//                .then(
//                    function(response) {
//                        vm.roles = response.data.result;
//                        vm.loader = false;
//                    }, handleErrorResponse
//                );
//        }

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


    function getRemarksList() {
        vm.referenceId = "055";
        var requestData = { referenceId: vm.referenceId };

        commonFlagService.getRemarksList(requestData).then(
          function (response) {
            vm.remarksList = response.data.result || []; // Ensure it's an array
          },
          function (error) {
            handleErrorResponse(error);
          }
        );
      }
function deleteSubmit() {




 vm.loader = true;


                                    vm.mappedList = [];
                                    vm.unMappedList = [];
                                    angular.forEach(vm.mappedDocumentsList, function(value, key) {
                                        vm.mappedList.push(value.code);
                                    });

                                      angular.forEach(vm.unmappedDocumentsList, function(value, key) {
                                                                            vm.unMappedList.push(value.code);
                                                                        });

         let combinedList = vm.mappedList.concat(vm.unMappedList);
        var requestData = {
            roleId: vm.roleId,
            schemeCode : vm.schemeCode,
            rejectionRemarks: vm.remarks,
            modifiedNonManList : combinedList
        };

        commonFlagService.deleteSubmit(requestData).then(function(response) {
            vm.loader = false;
            Swal.fire("Rejected!", "Common Flag Rejected Successfully", "success");
            location.href = '#!/common_flag/action/inquiry';
//                 const backdrop = document.querySelector('.modal-backdrop');
//                                                                        if (backdrop) {
//                                                                          backdrop.parentNode.removeChild(backdrop);
//                                                                          document.body.classList.remove('modal-open');
//                                                                        }
        }, function(error){
                           vm.loader = false;
                           handleErrorResponse(error);
                            const backdrop = document.querySelector('.modal-backdrop');
                             if (backdrop) {
                               backdrop.parentNode.removeChild(backdrop);
                               document.body.classList.remove('modal-open');
                             }
                              location.href = '#!/common_flag/action/inquiry';
                       });

}

   function addPermissionSubmit() {
            Swal.fire({
                    title: "Are you sure?",
                    text: "Do you really want to add and map the permissions for this role? ",
                    type: "warning",
                    showCancelButton: true,
                    cancelButtonClass: "btn-cancel",
                    confirmButtonClass: "btn-primary",
                    confirmButtonText: "Yes!",
                    closeOnConfirm: false,
                    showLoaderOnConfirm: true
                },
                function() {
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
                    commonFlagService.addPermissionSubmit(request)
                    .then(function(response) {
                            Swal.fire("Added!", " Role Added/ Mapped Successfully", "success");
                            location.href = '#!/role_permission_mapping/action/inquiry';
                        }, handleErrorResponse
                    );
                });
        }


           function verifySubmit() {
            Swal.fire({
                title: "Confirm Verify",
                text: "Do you really want to verify?",
                type: "warning",
                showCancelButton: true,
                confirmButtonText: "Yes",
                cancelButtonText: "Cancel",
                customClass: {
                  confirmButton: "btn-primary",
                  cancelButton: "btn-cancel"
                },
                buttonsStyling: false
              }).then((result) => {
                vm.loader = true;
                  vm.mappedList = [];
                   vm.unMappedList = [];
                                    angular.forEach(vm.mappedDocumentsList, function(value, key) {
                                        vm.mappedList.push(value.code);
                                    });

                                      angular.forEach(vm.unmappedDocumentsList, function(value, key) {
                                                                            vm.unMappedList.push(value.code);
                                                                        });
                 let requestData = {
                    roleId: vm.roleId,
                    modifiedManList : vm.mappedList,
                    modifiedNonManList :  vm.unMappedList,
                     schemeCode : vm.schemeCode
                }


                commonFlagService.verifySubmit(requestData)
                    .then(
                        function(response) {
                            vm.loader = false;
                            Swal.fire("Verified!", " Common Flag Verified Successfully", "success");
                            location.href = '#!/common_flag/action/inquiry';
                        }, handleErrorResponse
                    );
            });
            }


        function handleErrorResponse(response) {
            vm.loader = false;
            var msg = response.data.message ? response.data.message : "Server Error";
            Swal.fire("Error!", msg, "error");
            location.href = '#!/common_flag/action/inquiry';
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
                    closeOnConfirm: true,
                    showLoaderOnConfirm: true

                },
                function() {
                    if($state.current.name == 'common_flag.add_details'){
                        $state.go('common_flag.add')
                    }else{
                        $state.go('common_flag.list', {
                            'action': 'modify'
                        })
                    }
                });
        }

    }

})();