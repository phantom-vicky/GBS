(function () {

   'use strict';

   angular.module('gbmUltraApp').controller('sackUserController',
      sackUserController);


   sackUserController.$inject = ['$scope', '$filter', '$state',
      '$stateParams', '$location', '$timeout', 'sackUserService', 'commonService'
   ];

   function sackUserController($scope, $filter, $state, $stateParams,
      $location, $timeout, sackUserService, commonService) {

      var vm = this;
      vm.sackUser = {};
      vm.showForm = true;
      vm.selectedUsers = [];
      vm.isAllSelected = false;
      vm.getSolID = getSolID;
      vm.getSackUsers = getSackUsers;
      vm.CheckUncheckHeader = CheckUncheckHeader;
      vm.CheckUncheckAll = CheckUncheckAll;
      vm.submitForm = submitForm;
      vm.selectAll = selectAll;
      vm.formSubmitValidation = formSubmitValidation;
      vm.getBranchIdDetails = getBranchIdDetails;
      vm.getInputPage = getInputPage;
      vm.getOutputPage = getOutputPage;


      //vm.getSackUsers();
      vm.getInputPage();

      vm.showCancelConfirmation = showCancelConfirmation;

      /*  if ($state.current.name == 'sack_user.add') {
            vm.formSubmitValidationFlg = true;
        }*/

      function getSolID() {
         commonService.getSolId(function (response) {
            if (response.success) {
               vm.solId = response.data[0].solId;
               vm.solDescription = response.data[0].solDescription;
            } else {
               Swal.fire("Error!", response.message, "error");
            }
         });
      }

      function getSackUsers() {
         var data = [];
         var tempData = {
            'branchId': vm.branchId,
         };
         data.push(tempData);
         vm.sackUsers = null;
         sackUserService.getSackUsers(tempData,
            function (response) {
               if (response.success) {
                  vm.sackUsers = response.data;
                  /*angular.forEach(vm.sackUsers, function (item) {
			            item.Selected = true;
					});*/
                  //	vm.getSolID();
                  //$scope.IsAllChecked = true;
                  //vm.CheckUncheckHeader();
                  vm.showForm = true;
               }

            }, handleErrorResponse);
      }

      function getInputPage() {
         vm.inputDetails = true;
         vm.outputDetails = false;
         vm.branchId = "";
      }

      function getOutputPage() {
         vm.inputDetails = false;
         vm.outputDetails = true;
      }

      function getBranchIdDetails() {
         vm.loader = true;
         vm.searchBranchId = '';
         vm.branchIdList = null;
         var tempData = {};
         sackUserService.getSackUsers(tempData,
            function (response) {
               vm.loader = false;
               vm.branchIdList = response.data;
               angular.element('#treasurySearcher').modal('toggle');
            },
            function (error) {
               vm.loader = false;
               console.log('error in java');
               handleErrorResponse(error);
            });


      }


      function CheckUncheckHeader($event) {

         /*vm.IsAllChecked = true;

	        angular.forEach(vm.sackUsers, function (item) {
	        	if(!item.Selected){
	        		item.Selected = false;
	        		vm.IsAllChecked = false;
	        	}
	        }, handleErrorResponse);*/

         vm.IsAllChecked = vm.sackUsers.every(function (item) {
            //vm.checked = false;
            return item.Selected;
         });
         formSubmitValidation();

      };

      function formSubmitValidation() {
         for (var i = 0; i < vm.sackUsers.length; i++) {
            if (vm.sackUsers[i].Selected == true) {
               vm.formSubmitValidationFlg = false;
               break;
            } else {
               vm.formSubmitValidationFlg = true;
            }
         }
      }

      function CheckUncheckAll($event) {
         if (vm.IsAllChecked == true) {
            vm.IsAllChecked = false;
         } else {
            vm.IsAllChecked = true;
         }
         angular.forEach(vm.sackUsers, function (item) {

            item.Selected = vm.IsAllChecked;
            //console.log(item.Selected);
         }, handleErrorResponse);
      }


      function selectAll() {
         vm.checkAll = true;
         if (vm.IsAllChecked == true) {
            vm.IsAllChecked = false;
            vm.formSubmitValidationFlg = true;
         } else {
            vm.IsAllChecked = true;
            vm.formSubmitValidationFlg = false;
         }
         var toggleStatus = vm.IsAllChecked;
         angular.forEach(vm.sackUsers, function (item) {
            item.Selected = toggleStatus;
         });
      }


      function submitForm() {
         var data = [];
         angular.forEach(vm.sackUsers, function (item) {
            if (item.Selected == true) {
               var tempData = {
                  "username": item.username,
                  "userId": item.userId,
                  "sessionId": item.sessionId,
                  //"inTime": item.inTime
               };
               data.push(tempData);
            }
         });

         sackUserService.updateSackUser(data,
            function (response) {
               vm.sackUsers = {};
               Swal.fire("Success!", response.data.message, "success");
               $timeout(getSackUsers(), 5000);
               vm.formSubmitValidationFlg = true;
               vm.getInputPage();
            }, handleErrorResponse);

      }
      // Cancel Confirmation
      function showCancelConfirmation(state) {
//         Swal.fire({
//               title: "Confirm Cancel",
//               text: "Do you really want to cancel?",
//               type: "warning",
//               showCancelButton: true,
//               cancelButtonClass: "btn-cancel",
//               confirmButtonClass: "btn-danger",
//               confirmButtonText: "Yes",
//               cancelButtonText: "No",
//               closeOnConfirm: true
//            },
//            function () {


              Swal.fire({
                                  title: "Are you sure?",
                                  text: "Do you Really Want TO Cancel?",
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
               $state.go(state);

          }  });
      }

      // Handle error response
      function handleErrorResponse(response) {
         vm.loader = false;
         var msg = response.data.message ? response.data.message :
            "Server Error";
         Swal.fire("Error!", msg, "error");
      }


   }
})();
