(function() {

    'use strict';

    angular
        .module('gbmUltraApp')
        .controller('activeUserController', activeUserController);

    activeUserController.$inject = ['$stateParams', '$state', 'activeUserService', '$scope','authService', 'base64Service'];

    function activeUserController($stateParams, $state, activeUserService ,$scope,authService,base64Service) {

        var vm = this;
        vm.action = $stateParams.action;

        vm.itemsPerPage = "5";
        vm.currentPage = 1;
        vm.loadComplete = false;

        vm.modifyUsers = modifyUsers;
        vm.showCancelConfirmation = showCancelConfirmation;
        vm.verifySubmit = verifySubmit;
        vm.deleteSubmit = deleteSubmit;
        vm.getUsers = getUsers;
        vm.CheckUncheckHeader = CheckUncheckHeader;
        vm.CheckUncheckAll = CheckUncheckAll;
        vm.validateSubmitCheckbox = validateSubmitCheckbox;
        vm.rejectReason = rejectReason;

       vm.getVerify = getVerify;


    vm.perms = [];


            if($state.current.name == 'active_user.list') {
                    decideRouting();

                    var isModifyContains = vm.perms.some(str => str.includes('modify'));
                    if(isModifyContains){
                        $state.go('active_user.modify');
                    }else{
                        $state.go('active_user.verify');
                    }
             }

         if($state.current.name == 'active_user.modify'){
              getUsers();
           }
         if($state.current.name == 'active_user.verify'){
                 getVerify();
        		    }

        		          function decideRouting(){
                                    var permissionList = JSON.parse(authService.getPerms());

                                        angular.forEach(permissionList, function(item){
                                            let itemDecodedValue = base64Service.decode(item);
                                            if(itemDecodedValue == 'USER_MGMT:ACTIVE_USER:MODIFY'){
                                                vm.perms.push('modify');
                                            }
                                            if(itemDecodedValue == 'USER_MGMT:ACTIVE_USER:VERIFY'){
                                                vm.perms.push('verify');
                                            }
                                        });
                                }



   function CheckUncheckHeader() {

            			vm.IsAllChecked = vm.users.every(function(users)
            					{
            							return users.Selected;
            					});
            	    };

            function CheckUncheckAll() {
                var toggleStatus = !vm.IsAllChecked;
                angular.forEach(vm.users, function(users) {
                    users.Selected = toggleStatus;
                });
            }


function getUsers() {
    vm.loader = true;
    activeUserService.getUsers()
        .then(function(response) {
            if (Array.isArray(response.data.result)) {
                vm.users = response.data.result;
            } else {
                vm.users = [];
            }
            vm.loader = false;
        })
        .catch(function(error) {
            handleErrorResponse(error);
            vm.loader = false;
        });
}

function getVerify() {
    vm.loader = true;
    activeUserService.getVerify()
        .then(function(response) {
            if (Array.isArray(response.data.result)) {
                vm.users = response.data.result;
            } else {
                vm.users = [];
            }
            vm.loader = false;
        })
        .catch(function(error) {
            handleErrorResponse(error);
            vm.loader = false;
//            $state.go('active_user.modify');
        });
}



//function modifyUsers() {
//    var userList = [];
//
//    angular.forEach(vm.users, function(user) {
//        if (user.checked === true) {
//            userList.push(user.id);
//        }
//    });
//
//    var requestData = {
//        'userListone': userList
//    };
//
//    Swal.fire({
//        title: "Are you sure?",
//        text: "Do you want to Really Modify this record?",
//        type: "warning",
//        showCancelButton: true,
//        cancelButtonClass: "btn-cancel",
//        confirmButtonClass: "btn-primary",
//        confirmButtonText: "Yes",
//        closeOnConfirm: false
//    }, function() {
//        vm.loader = true;
//
//        activeUserService.modifyUsers(requestData).then(function(response) {
//            vm.loader = false;
//            Swal.fire("Modified!", "Selected users moved to Authorization Successfully", "success");
//            $state.go('active_user');
//        }).catch(function(error) {
//            handleErrorResponse(error);
//            vm.loader = false;
//
//        });
//    });
//}

function modifyUsers() {
    var userList = [];


        angular.forEach(vm.users, function(user) {
            if (user.Selected === true) {
                userList.push(user.id);
            }
        });


    var requestData = {
        'userListone': userList
    };

//    Swal.fire({
//        title: "Are you sure?",
//        text: "Do you want to Really Modify this record?",
//        type: "warning",
//        showCancelButton: true,
//        cancelButtonClass: "btn-cancel",
//        confirmButtonClass: "btn-primary",
//        confirmButtonText: "Yes",
//        closeOnConfirm: false
//    }, function() {


            Swal.fire({
                               title: "Are you sure?",
                               text: "Do you want to Modify this record?",
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

        activeUserService.modifyUsers(requestData).then(function(response) {
            vm.loader = false;
            Swal.fire("Modified!", "Selected users moved to Authorization Successfully", "success");
            getUsers();
      location.href = "#!/";
        }).catch(function(error) {
            handleErrorResponse(error);
            vm.loader = false;
             location.href = "#!/";
        });
 }   });
}

function verifySubmit() {
    var userList = [];

//    if ($scope.selectAll) {
       //        angular.forEach(vm.users, function(user) {
       //            userList.push(user.id);
       //        });
       //    } else {
       //        angular.forEach(vm.users, function(user) {
       //            if (user.checked === true) {
       //                userList.push(user.id);
       //            }
       //        });
       //    }

       angular.forEach(vm.users, function(user) {
                 if (user.Selected === true) {
                     userList.push(user.id);
                 }
             });

    var requestData = {
        'userListone': userList
    };

//    Swal.fire({
//        title: "Are you sure?",
//        text: "Do you want to Really Verify this record?",
//        type: "warning",
//        showCancelButton: true,
//        cancelButtonClass: "btn-cancel",
//        confirmButtonClass: "btn-primary",
//        confirmButtonText: "Yes",
//        closeOnConfirm: false
//    }, function() {


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
        vm.loader = true;

        activeUserService.verifySubmit(requestData).then(function(response) {
            vm.loader = false;
            Swal.fire("Verified!","Dormant User Unlocked Successfully","success");
              location.href = "#!/";
        }).catch(function(error) {
            handleErrorResponse(error);
            vm.loader = false;
              location.href = "#!/";
        });
   } });
}



   function validateSubmitCheckbox(){
                var unCheckedList = vm.users.filter(i => i.Selected);
                if(unCheckedList.length <= 0){
                    return true;
                }else{
                    return false;
                }
            }
//
//function deleteSubmit() {
////    var userList = [];
//        //
//        //  if ($scope.selectAll) {
//        //         angular.forEach(vm.users, function(user) {
//        //             userList.push(user.id);
//        //         });
//        //     } else {
//        //         angular.forEach(vm.users, function(user) {
//        //             if (user.checked === true) {
//        //                 userList.push(user.id);
//        //             }
//        //         });
//        //     }
//
//            var userList = [];
//                                     angular.forEach(vm.users, function(user) {
//                                               if (user.Selected === true) {
//                                                   userList.push(user.id);
//                                               }
//                                           });
//    Swal.fire({
//        title: "Remarks",
//               text: "",
//               type: "input",
//               inputPlaceholder: "Enter your remarks here",
//               showCancelButton: true,
//               cancelButtonClass: "btn-cancel",
//               confirmButtonClass: "btn-primary",
//               confirmButtonText: "Submit",
//               animation: "slide-from-top",
//               closeOnConfirm: false,
//    }, function(inputValue) {
//
//           if (inputValue === false) return false;
//
//                if (inputValue === "") {
//                    Swal.fire.showInputError("Please enter the remarks!");
//                    return false;
//                }
//        vm.remarks = inputValue;
//         var requestData = {
//                'userListone': userList,
//                'remarks': vm.remarks
//            };
//
//
//        activeUserService.deleteSubmit(requestData).then(function(response) {
//            vm.loader = false;
//            Swal.fire("Rejected!", "Rejected Successfully", "success");
//            $state.go('active_user');
//        }).catch(function(error) {
//            handleErrorResponse(error);
//            vm.loader = false;
//            $state.go('active_user');
//        });
//    });
//}

//function deleteSubmit() {
//    function handleKeyPress(event) {
//        var key = event.key;
//        var input = event.target.value;
//
//        // Prevent entering more than one consecutive space
//        if (key === ' ' && input.slice(-1) === ' ') {
//            event.preventDefault();
//        }
//
//        // Allow only alphabetic characters, single spaces, and backspace
//        if (!key.match(/^[a-zA-Z\s]$/) && key !== 'Backspace' && key!=='Enter') {
//            event.preventDefault();
//        }
//    }
//
//    document.addEventListener('keypress', handleKeyPress);
//
//    var userList = [];
//    angular.forEach(vm.users, function(user) {
//        if (user.Selected === true) {
//            userList.push(user.id);
//        }
//    });
//
//    Swal.fire({
//        title: "Remarks",
//        text: "",
//        type: "input",
//        inputPlaceholder: "Enter your remarks here",
//        showCancelButton: true,
//        cancelButtonClass: "btn-cancel",
//        confirmButtonClass: "btn-primary",
//        confirmButtonText: "Submit",
//        animation: "slide-from-top",
//        closeOnConfirm: false,
//        inputValidator: function(value) {
//
//            if (/^[a-zA-Z\s]*$/.test(value.trim())) {
//
//                if (/\s\s/.test(value)) {
//                    return "Please enter only single spaces between words!";
//                }
//                document.removeEventListener('keypress', handleKeyPress);
//                return true;
//            } else {
//                return "Please enter only alphabetic characters!";
//            }
//        }
//    }, function(inputValue) {
//        if (inputValue === false) {
//            document.removeEventListener('keypress', handleKeyPress);
//            return false;
//        }
//
//        if (inputValue === "") {
//            Swal.fire.showInputError("Please enter the remarks!");
//            return false;
//        }
//
//        vm.remarks = inputValue;
//
//        var requestData = {
//            'userListone': userList,
//            'remarks': vm.remarks
//        };
//
//        activeUserService.deleteSubmit(requestData).then(function(response) {
//            vm.loader = false;
//            Swal.fire("Rejected!", "Rejected Successfully", "success");
//            location.href = "#!/";
//        }).catch(function(error) {
//            handleErrorResponse(error);
//            vm.loader = false;
//             location.href = "#!/";
//        });
//    });
//}





      function deleteSubmit() {
                    vm.loader = true;


                     var userList = [];
                        angular.forEach(vm.users, function(user) {
                            if (user.Selected === true) {
                                userList.push(user.id);
                            }
                        });

                      var requestData = {
                                'userListone': userList,
                                'remarks': vm.deleteRemark
                            };


                    activeUserService.deleteSubmit(requestData).then(function(response) {
                                vm.loader = false;
                                Swal.fire("Rejected!", "Rejected Successfully", "success");
                                location.href = "#!/";
                            }).catch(function(error) {
                                handleErrorResponse(error);
                                vm.loader = false;
                                 location.href = "#!/";
                            });
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
           //     function() {



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
                    location.href = "#!/";

              }  });
        }


        vm.getRemarksList = function () {
              vm.referenceId = "058";
              var requestData = { referenceId: vm.referenceId };

              activeUserService.getRemarksList(requestData).then(
                function (response) {
                  vm.remarksList = response.data.result || []; // Ensure it's an array
                },
                function (error) {
                  handleErrorResponse(error);
                }
              );
            }


            function rejectReason() {
              angular.element('#permissionMappingDelete').modal('toggle');
              }

    }

//$scope.selectAll = false;
//
//$scope.toggleAll = function() {
//    angular.forEach($scope.vm.users, function(user) {
//        user.checked = $scope.selectAll;
//    });
//};


})();