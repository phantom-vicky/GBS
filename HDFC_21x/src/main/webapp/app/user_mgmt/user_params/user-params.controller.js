(function() {

    'use strict';

    angular
        .module('gbmUltraApp')
        .controller('UserParamController', UserParamController);

    UserParamController.$inject = ['$stateParams', '$state', 'userParamsService'];

    function UserParamController($stateParams, $state, userParamsService) {

        var vm = this;
        vm.action = $stateParams.action;
        vm.userParams = {};
        vm.loadComplete = false;

        vm.saveUserParamsConfirmation = saveUserParamsConfirmation;
        vm.showCancelConfirmation = showCancelConfirmation;
        vm.verifySubmit = verifySubmit;
        vm.deleteSubmit = deleteSubmit;
        vm.getModifiedParams = getModifiedParams;
        vm.getUserParams = getUserParams;
        vm.getverDelParam = getverDelParam;

if($state.current.name == 'user_params.list'){
            if(vm.action == 'modify') {
                getModifiedParams();
            }else if(vm.action == 'inquiry') {
            getUserParams();

            }
        }
         if($state.current.name == 'user_params.verify'){
                 getverDelParam();
        		    }



    function getUserParams() {
         vm.loader = true;
         let requestData = {};

         userParamsService.getUserParams()
             .then(function(response) {

                 vm.users = response.data.result[0];
                 vm.loader = false;
                  $state.go('user_params.list', {
                                         'action': 'inquiry'
                                     })

             })
             .catch(function(error) {

                 handleErrorResponse(error);
                 vm.loader = false;

             });
     }

function getModifiedParams() {
     vm.loader = true;
     let requestData = {};

     userParamsService.getModifiedParams()
         .then(function(response) {

             vm.users = response.data.result[0];
             vm.loader = false;


         })
         .catch(function(error) {

             handleErrorResponse(error);
             vm.loader = false;
getUserParams()
         });
 }


function getverDelParam() {
     vm.loader = true;
     let requestData = {};

     userParamsService.getverDelParam()
         .then(function(response) {

             vm.users = response.data.result[0];
             vm.loader = false;


         })
         .catch(function(error) {

             handleErrorResponse(error);
             vm.loader = false;
             getUserParams();

         });
 }


	function saveUserParamsConfirmation(){

		    var requestData = {
                'dormancyPeriod': vm.users.dormancyPeriod,
                'noOfRetries': vm.users.noOfRetries,
                'activationPeriod': vm.users.activationPeriod,
                'maxAccPeriod': vm.users.maxAccPeriod
		    }
//		    Swal.fire({
//                title : "Are you sure?",
//                text : "Do you want to Really Modify this record?",
//                type : "warning",
//                showCancelButton : true,
//                cancelButtonClass : "btn-cancel",
//                confirmButtonClass : "btn-primary",
//                confirmButtonText : "Yes",
//                closeOnConfirm : false
//            },
//
//            function() {


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
                vm.loader = true
                userParamsService.saveUserParams(requestData).then(function(response){
                    vm.loader = false;
//                    showSuccessAlert('MODIFY');
                 Swal.fire("Modified!","User Prams Modified Successfully","success");
                     getUserParams()
                },function(error){
                    handleErrorResponse(error);
                    vm.loader = false;
                     getUserParams()
                })
           } })
		}


//
//function verifySubmit() {
//    Swal.fire({
//        title: "Are you sure",
//        text: "Do you really want to verify This Record?",
//        type: "warning",
//        showCancelButton: true,
//        cancelButtonClass: "btn-cancel",
//        confirmButtonClass: "btn-primary",
//        confirmButtonText: "Yes",
//        cancelButtonText: "No",
//        closeOnConfirm: true
//    },
//    function(isConfirm) {
//        if (isConfirm) {
//            vm.loader = true;
//
//            userParamsService.verifySubmit()
//                .then(
//                    function(response) {
//                        vm.loader = false;
//                        // Ensure that the success message is displayed using Swal.fire
//                        Swal.fire("Success", "Record verified successfully.", "success");
//                    },
//                    function(error) {
//                        handleErrorResponse(error);
//                        vm.loader = false;
//                        getUserParams();
//                    }
//                );
//        }
//    });
//}

function verifySubmit() {
//        Swal.fire({
//            title : "Are you sure?",
//            text : "Do you want to Really Verify this record?",
//            type : "warning",
//            showCancelButton : true,
//            cancelButtonClass : "btn-cancel",
//            confirmButtonClass : "btn-primary",
//            confirmButtonText : "Yes",
//            closeOnConfirm : false
//        },() => {




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
        vm.loader= true;

        userParamsService.verifySubmit().then(function(response)
        {
            vm.loader=false;
            Swal.fire("Verified!","User Prams Verified Successfully","success");
            sleep(3000).then(() => {
             getUserParams();
             });
 document.querySelector(".modal-backdrop.in").remove();


        }, function(error){
              vm.loader = false;
              handleErrorResponse(error);
             getUserParams();
              document.querySelector(".modal-backdrop.in").remove();


          });
      } }) ;
    }

    function sleep(ms) {
             return new Promise(resolve => setTimeout(resolve, ms));
           }


//function deleteSubmit() {
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
//    },
//    function(inputValue) {
//        if (inputValue === false) return false;
//
//        if (inputValue === "") {
//            Swal.fire.showInputError("Please enter the remarks!");
//            return false;
//        }
//
//
//        vm.remarks = inputValue;
//
//
//        var requestData = {
//            remarks: vm.remarks
//        };
//
//        userParamsService.deleteSubmit(requestData).then(function(response) {
//            if (response.data.message == 'SUCCESS') {
//                Swal.fire("Rejected!", "User Prams Rejected Successfully", "success");
//              getUserParams();
//            } else {
//                vm.loader = false;
//                Swal.fire("WARNING!", response.data.message, "error");
//              getUserParams();
//            }
//        }, handleErrorResponse);
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
//            const trimmedValue = value.trim();
//            if (!trimmedValue || trimmedValue === "") {
//                    return "Please enter the remarks!";
//                }
//            if (trimmedValue.length > 0 &&/^[a-zA-Z\s]*$/.test(trimmedValue)) {
//                if (/\s\s/.test(trimmedValue)) {
//                    return "Please enter only single spaces between words!";
//                }
//                document.removeEventListener('keypress', handleKeyPress);
//                return true;
//            } else {
//                return "Please enter only alphabetic characters!";
//            }
//        }
//    },
//    function(inputValue) {
//        if (inputValue === false) {
//            document.removeEventListener('keypress', handleKeyPress);
//            return false;
//        }
//
//        if (inputValue.trim() === "") {
//            Swal.fire.showInputError("Please enter the remarks!");
//            return false;
//        }
//
//        vm.remarks = inputValue;
//
//        var requestData = {
//            remarks: vm.remarks
//        };
//
//        userParamsService.deleteSubmit(requestData).then(function(response) {
//            if (response.data.message == 'SUCCESS') {
//                Swal.fire("Rejected!", "User Params Rejected Successfully", "success");
//                getUserParams();
//            } else {
//                vm.loader = false;
//                Swal.fire("WARNING!", response.data.message, "error");
//                getUserParams();
//            }
//        }, handleErrorResponse);
//    });
//}






  function deleteSubmit() {
                    vm.loader = true;

                   var requestData = {
                              remarks: vm.deleteRemark
                          };

                          userParamsService.deleteSubmit(requestData).then(function(response) {
                              if (response.data.message == 'SUCCESS') {
                                  Swal.fire("Rejected!", "User Params Rejected Successfully", "success");
                                  getUserParams();
                                   document.querySelector(".modal-backdrop.in").remove();

                              } else {
                                  vm.loader = false;
                                  Swal.fire("WARNING!", response.data.message, "error");
                                  getUserParams();
                                   document.querySelector(".modal-backdrop.in").remove();

                              }
                          }, handleErrorResponse);
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
                    $state.go('user_params.list', {
                        'action': 'inquiry'
                    })

              }  });
        }


        vm.getRemarksList = function () {
              vm.referenceId = "058";
              var requestData = { referenceId: vm.referenceId };

              userParamsService.getRemarksList(requestData).then(
                function (response) {
                  vm.remarksList = response.data.result || []; // Ensure it's an array
                 document.querySelector(".modal-backdrop.in").remove();
                },
                function (error) {
                  handleErrorResponse(error);
                   document.querySelector(".modal-backdrop.in").remove();
                }
              );
            };
vm.rejectReason=rejectReason;
            function rejectReason() {
             angular.element("#permissionMappingDelete").modal("toggle");

              }



    }

})();