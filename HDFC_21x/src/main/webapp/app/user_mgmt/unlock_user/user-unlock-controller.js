(function() {

    'use strict';

    angular
        .module('gbmUltraApp')
        .controller('unlockUsersController', unlockUsersController);

    unlockUsersController.$inject = ['$scope', '$state', '$stateParams', '$filter', '$timeout', '$rootScope', 'unlockUsersService', 'authService', 'base64Service'];

    function unlockUsersController($scope, $state, $stateParams, $filter, $timeout, $rootScope, unlockUsersService, authService, base64Service) {

            var vm = this;

            vm.commonYesNoMetaData = COMMON_YES_NO_METADATA;
            vm.itemsPerPage = "10";
            vm.currentPage = 1;
            vm.deleteSubmit = deleteSubmit;
            vm.verifySubmit = verifySubmit;
            vm.verifyConfirmation = verifyConfirmation;
            vm.deleteConfirmation = deleteConfirmation;
            vm.modifyConfirmation = modifyConfirmation;
            vm.CheckUncheckHeader =  CheckUncheckHeader;
            vm.CheckUncheckAll = CheckUncheckAll;
            vm.validateSubmitCheckbox = validateSubmitCheckbox;

            vm.perms = [];


            if($state.current.name == 'unlockUsers.list') {
                    decideRouting();

                    var isModifyContains = vm.perms.some(str => str.includes('modify'));
                    if(isModifyContains){
                        $state.go('unlockUsers.modify');
                    }else{
                        $state.go('unlockUsers.verify');
                    }
             }
            if($state.current.name == 'unlockUsers.verify') {
                    getDetailsVerifySelect();
            }
            if($state.current.name == 'unlockUsers.modify') {
                    getDetailsModifySelect();
            }

            function decideRouting(){
                var permissionList = JSON.parse(authService.getPerms());

                    angular.forEach(permissionList, function(item){
                        let itemDecodedValue = base64Service.decode(item);
                        if(itemDecodedValue == 'GBM_USER_MGMT:UNL_USR:MODIFY'){
                            vm.perms.push('modify');
                        }
                        if(itemDecodedValue == 'GBM_USER_MGMT:UNL_USR:VER/REJ'){
                            vm.perms.push('verify');
                        }
                    });
            }


            function CheckUncheckHeader() {

            			vm.IsAllChecked = vm.users.every(function(item)
            					{
            							return item.Selected;
            					});
            	    };

            function CheckUncheckAll() {
                var toggleStatus = !vm.IsAllChecked;
                angular.forEach(vm.users, function(item) {
                    item.Selected = toggleStatus;
                });
            }
vm.rejectReason=rejectReason;
function rejectReason() {
  angular.element('#permissionMappingDelete').modal('toggle');
  }


vm.getRemarksList = function () {
      vm.referenceId = "058";
      var requestData = { referenceId: vm.referenceId };

      unlockUsersService.getRemarksList(requestData).then(
        function (response) {
          vm.remarksList = response.data.result || []; // Ensure it's an array
        },
        function (error) {
          handleErrorResponse(error);
        }
      );
    };



//            function deleteSubmit( ) {
//                    vm.loader = true;
//                    let requestData = {};
//                    requestData.userId = vm.userId;
//                    requestData.remarks =vm.remarks;
//                    userCreationRoleMappingService.deleteService(requestData)
//                        .then(
//                            function(response) {
//                                vm.loader = false;
//                                Swal.fire("Rejected!","User Data Rejected Successfully","success");
//                                     const backdrop = document.querySelector('.modal-backdrop');
//                                                                                            if (backdrop) {
//                                                                                              backdrop.parentNode.removeChild(backdrop);
//                                                                                              document.body.classList.remove('modal-open');
//                                                                                            }
//                                $state.go('usrCrtRleMpm.list');
//                            }, function(error){
//                                  vm.loader = false;
//                                                          handleErrorResponse(error);
//                                                           const backdrop = document.querySelector('.modal-backdrop');
//                                                            if (backdrop) {
//                                                              backdrop.parentNode.removeChild(backdrop);
//                                                              document.body.classList.remove('modal-open');
//                                                            }
//                                $state.go('usrCrtRleMpm.verify');
//                            }
//                        );
//            }


            function getDetailsVerifySelect() {
                                    vm.loader = true;
                                    let requestData = {};
                                    unlockUsersService.verifySelectService(requestData)
                                        .then(
                                            function(response) {
                                                vm.resultSet = response.data.result;
                                                vm.users = vm.resultSet.userList;

                                                vm.IsAllChecked = true;
                                                angular.forEach(vm.users, function(item){
                                                   item.Selected = true;
                                                });

                                                vm.loader = false;
                                            }, function(error){
                                                handleErrorResponse(error);
                                                location.href = '#!/';
                                            }
                                        );
            }

            function getDetailsModifySelect() {
                                                vm.loader = true;
                                                //let requestData = {};
                                                //unlockUsersService.modifySelectService(requestData)
                                                unlockUsersService.modifySelectService()
                                                    .then(
                                                        function(response) {
                                                            vm.resultSet = response.data.result;
                                                            vm.users = vm.resultSet.userList;
                                                            vm.IsAllChecked = true;
                                                            angular.forEach(vm.users, function(item){
                                                               item.Selected = true;
                                                            });
                                                            vm.loader = false;
                                                        }, function(error){
                                                            handleErrorResponse(error);
                                                            $state.go('unlockUsers.verify');
                                                        }
                                                    );




                        }

            function validateSubmitCheckbox(){
                var unCheckedList = vm.users.filter(i => i.Selected);
                if(unCheckedList.length <= 0){
                    return true;
                }else{
                    return false;
                }
            }


            function handleErrorResponse(response) {
            			vm.loader = false;
            			var msg = response.data.message ? response.data.message
            					: "Server Error";
            			Swal.fire("Error!", msg, "error");
            }

            function deleteSubmit() {
                    vm.loader = true;
                    var tempPayload = [];
                    angular.forEach(vm.users, function(item){
                       tempPayload.push({userId: item.userId});
                    });
                    var requestData = {
                        'userList': tempPayload,
                        'remarks': vm.deleteRemark
                    };

                    unlockUsersService.deleteService(requestData)
                        .then(
                            function(response) {
                                vm.loader = false;
                                Swal.fire("Rejected!","Rejected Successfully","success");
                                location.href = '#!/';
                            }, function(error){
                                handleErrorResponse(error);
                                $state.go('unlockUsers.verify');
                            }
                        );
            }

            function verifySubmit() {
                    vm.loader = true;
                    var tempPayload = [];
                    angular.forEach(vm.users, function(item){
                       //tempPayload.push({userId: item.userId});
                       if(item.Selected === true){
                           tempPayload.push({userId: item.userId});
                       }
                    });
                    var requestData = {
                        'userList': tempPayload
                    };
                    unlockUsersService.verifyService(requestData)
                        .then(
                            function(response) {
                                vm.loader = false;
                                Swal.fire("Verified!","USER ID has been unlocked Successfully","success");
                                location.href = '#!/';
                            }, function(error){
                                handleErrorResponse(error);
                                $state.go('unlockUsers.verify');
                            }
                        );
            }

            function modifySubmit() {
                                vm.loader = true;
                                var tempPayload = [];
                                angular.forEach(vm.users, function(item){
                                   if(item.Selected === true){
                                        tempPayload.push({userId: item.userId});
                                   }
                                });
                                var requestData = {
                                    'userList': tempPayload
                                };
                                unlockUsersService.modifyService(requestData)
                                    .then(
                                        function(response) {
                                            vm.loader = false;
                                            Swal.fire("Modified!"," USER ID has been unlocked ‘Pending for approval’ ","success");
                                            location.href = '#!/';
                                        }, function(error){
                                            handleErrorResponse(error);
                                            $state.go('unlockUsers.modify');
                                        }
                                    );
                        }

            function verifyConfirmation() {
//                                    Swal.fire({
//                                            title: "Are you sure?",
//                                            text: "Do you want to verify this record ? ",
//                                            type: "warning",
//                                            showCancelButton: true,
//                                            cancelButtonClass: "btn-cancel",
//                                            confirmButtonClass: "btn-primary",
//                                            confirmButtonText: "Yes!",
//                                            closeOnConfirm: false,
//                                            showLoaderOnConfirm: true
//                                        },
//                                        function() {

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
                                            verifySubmit();
                                       } });
                                }
//
//            function deleteConfirmation(){
//
//                           Swal.fire({
//                               title: "Rejection Remarks",
//                               text: "",
//                               type: "input",
//                               inputPlaceholder: "Enter your remarks here",
//                               showCancelButton: true,
//                               cancelButtonClass: "btn-cancel",
//                               confirmButtonClass: "btn-primary",
//                               confirmButtonText: "Submit",
//                               animation: "slide-from-top",
//                               closeOnConfirm: false,
//                               showLoaderOnConfirm: true
//
//
//                           },
//                           function(inputValue) {
//                               if (inputValue === false) return false;
//
//                               if (inputValue === "") {
//                                   Swal.fire.showInputError("Please enter the remarks!");
//                                   return false;
//                               }
//
//                               deleteSubmit(inputValue);
//                           });
//
//                            }

            function deleteConfirmation() {
                function handleKeyPress(event) {
                    var key = event.key;
                    var input = event.target.value;

                    if (key === ' ' && input.slice(-1) === ' ') {
                        event.preventDefault();
                    }

                    if (!key.match(/^[a-zA-Z\s]$/) && key !== 'Backspace' && key!=='Enter') {
                        event.preventDefault();
                    }
                }

                document.addEventListener('keypress', handleKeyPress);

                Swal.fire({
                    title: "Rejection Remarks",
                    text: "",
                    type: "input",
                    inputPlaceholder: "Enter your remarks here",
                    showCancelButton: true,
                    cancelButtonClass: "btn-cancel",
                    confirmButtonClass: "btn-primary",
                    confirmButtonText: "Submit",
                    animation: "slide-from-top",
                    closeOnConfirm: false,
                    showLoaderOnConfirm: true,
                    inputValidator: function(value) {
                        if (/^[a-zA-Z\s]*$/.test(value.trim())) {
                            if (/\s\s/.test(value)) {
                                return "Please enter only single spaces between words!";
                            }
                            document.removeEventListener('keypress', handleKeyPress);
                            return true;
                        } else {
                            return "Please enter only alphabetic characters!";
                        }
                    }
                }, function(inputValue) {
                    if (inputValue === false) {
                        document.removeEventListener('keypress', handleKeyPress);
                        return false;
                    }

                    if (inputValue === "") {
                        Swal.fire.showInputError("Please enter the remarks!");
                        return false;
                    }

                    deleteSubmit(inputValue);
                });
            }


            function modifyConfirmation() {
//                                    Swal.fire({
//                                            title: "Are you sure?",
//                                            text: "Do you want to modify this record ? ",
//                                            type: "warning",
//                                            showCancelButton: true,
//                                            cancelButtonClass: "btn-cancel",
//                                            confirmButtonClass: "btn-primary",
//                                            confirmButtonText: "Yes!",
//                                            closeOnConfirm: false,
//                                            showLoaderOnConfirm: true
//                                        },
//                                        function() {

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
                                            modifySubmit();
                                      }  });
                                }


    }

})();