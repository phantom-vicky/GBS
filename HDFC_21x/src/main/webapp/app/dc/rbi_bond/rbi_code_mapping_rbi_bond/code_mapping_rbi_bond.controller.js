(function() {

    'use strict';

    angular.module('gbmUltraApp').controller('codeMappingRbiBondController',
        codeMappingRbiBondController);

    codeMappingRbiBondController.$inject = ['$scope', '$filter', '$state', '$rootScope',
        '$stateParams', '$location', 'codeMappingRbiBondService', 'commonService'
    ];

    function codeMappingRbiBondController($scope, $filter, $state, $rootScope, $stateParams,
        $location, codeMappingRbiBondService, commonService) {

        var vm = this;


        vm.commonYesNoMetaData = COMMON_YES_NO_METADATA;
        vm.showCancelConfirmation = showCancelConfirmation;
        vm.decimalConvert = decimalConvert;
        vm.currentDate = new Date();
        vm.getRemarksList = getRemarksList;
        vm.rejectReason = rejectReason;

        //Add Functions
        vm.submitAddForm = submitAddForm;
        vm.addValidation = addValidation;
        vm.getModuleName = getModuleName;

        vm.currentDate = $rootScope.globals.currentUser.systemDate;

        //Verify and Delete Functions
        vm.submitVerifyForm = submitVerifyForm;
        vm.getVerifyDeleteSelect = getVerifyDeleteSelect;
        vm.getVerifyDeleteTorch = getVerifyDeleteTorch;
        vm.submitDeleteForm = submitDeleteForm;

        //Inquiry Functions
        vm.getInquiryTorch = getInquiryTorch;
        vm.getInquirySelect = getInquirySelect;

        //Modify Functions
        vm.modifySubmit = modifySubmit;
        vm.modifySelectDetails = modifySelectDetails;
        vm.getModifyTorch = getModifyTorch;
        vm.getEffectFromDate = getEffectFromDate;


        if ($state.current.name == 'code_mapping_rbi_bond.add') {
         addValidation();
        }
         if ($state.current.name == 'code_mapping_rbi_bond.modify_detail'){
            if ($stateParams.obj) {
                vm.pdoCode = $stateParams.obj.pdoCode;
                vm.effFromDateOld = $stateParams.obj.effFromDate
                modifySelectDetails();
            } else {
                $state.go('code_mapping_rbi_bond.modify');
            }
        }else if ($state.current.name == 'code_mapping_rbi_bond.verify_detail') {
            if ($stateParams.obj) {
                vm.pdoCode = $stateParams.obj.pdoCode;
                  getVerifyDeleteSelect('V');
            } else {
                $state.go('code_mapping_rbi_bond.verify');
            }
        } else if ($state.current.name == 'code_mapping_rbi_bond.delete_detail') {
            if ($stateParams.obj) {
                    vm.moduleId = $stateParams.obj.moduleId;
                    vm.rbiCode = $stateParams.obj.rbiCode;
                    getVerifyDeleteSelect('D');

            } else {
                $state.go('code_mapping_rbi_bond.delete');
            }
        } else if ($state.current.name == 'code_mapping_rbi_bond.inquiry_detail') {
            if ($stateParams.obj) {
               vm.pdoCode = $stateParams.obj.pdoCode;
               vm.versionNo = $stateParams.obj.versionNo;
               vm.versionNo = $stateParams.obj.versionNo;
                getInquirySelect();
            } else {
                $state.go('code_mapping_rbi_bond.inquiry');
            }
        }
        //Get Module Name
        function getModuleName() {
           vm.referenceId = 'RBI Bond';
           var requestData = {
                 'referenceId': vm.referenceId
             }
           codeMappingRbiBondService.getRbiBondModuleNameDetails(requestData).then(function(response){
              vm.moduleNameList = response.data.result;
            },function(error) {
               handleErrorResponse(error);
             });
        }

          function getRemarksList() {
                            vm.referenceId = '055';
                            var requestData = { 'referenceId': vm.referenceId };

                            codeMappingRbiBondService.getRemarksList(requestData).then(function(response) {
                                vm.remarksList = response.data.result || []; // Ensure it's an array
                            }, function(error) {
                                handleErrorResponse(error);
                            });
                        }

                         function rejectReason() {
                              angular.element("#permissionMappingDelete").modal("toggle");
                            }


        function getEffectFromDate() {
          vm.effFromDate = convertToDateObject($filter('date')(vm.effFromDate, 'dd/MM/yyyy'));
            console.log(vm.effFromDate);
                        vm.dateOptionsModify = {
                            minDate: vm.effFromDate,
//                            maxDate: $rootScope.globals.currentUser.systemDate,
                            showWeeks: false
                        }
        		}

                        vm.dateOptionsM = {
                            minDate: new Date(),
                            showWeeks: false
                        }
                                     vm.dateOptions = {
                                     showWeeks : false,
                                     minDate : $rootScope.globals.currentUser.systemDate
                                      }


       vm.textableDatePicker=textableDatePicker;
       vm.autoslashing=autoslashing;

       function textableDatePicker(id,valueDate)
       {
        commonService.textableDatePicker(id,valueDate);
       }
       function autoslashing(id)
       {
        commonService.autoslashing(id);
       }




        // Modify Select details
        function modifySelectDetails() {
             vm.loader = true;
                vm.searchSolId = '';
                   var requestData = {
                      pdoCode:  vm.pdoCode,
                   };
             codeMappingRbiBondService.getModifySelect(requestData).then(function(response){

                 vm.loader = false;
                 vm.verifyDetailsList = response.data.result;
                 vm.verificationDetails = response.data.result.prevOperType;
                 vm.tdsParameterList = response.data.result.masterOldNewValueModel;
                 vm.tdsDateObjOld = vm.tdsParameterList.C006.oldValue;
                 vm.effFromDateOld = $filter('date')(vm.effFromDateOld, 'dd/MM/yyyy');
                 vm.currentStr = $filter('date')(vm.tdsDateObjOld, 'dd/MM/yyyy');
                 vm.tdsParameterList.C006.oldValue = convertToDateObject(vm.effFromDateOld);
                 vm.id = vm.verifyDetailsList.pkColumnValue;

                 if (vm.currentStr == vm.effFromDateOld) {
//                                                              Swal.fire({
//                                                                  title: "Record already exists for the effective date",
//                                                                  text: "Do you wish to continue?",
//                                                                  type: "warning",
//                                                                  showCancelButton: true,
//                                                                  cancelButtonClass: "btn-cancel",
//                                                                  confirmButtonClass: "btn-danger",
//                                                                  confirmButtonText: "Yes",
//                                                                  cancelButtonText: "No",
//                                                                  closeOnConfirm: true
//                                                              }, function (inputValue) {
//                                                                  if (!inputValue) {

                              Swal.fire({
                                                 title: "Record already exists for the effective date",
                                                   text: "Do you wish to continue?",
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





                                                                  }
                                                                  else{
                                                                   $state.go('code_mapping_rbi_bond.modify');
                                                                  }
                                                              });
                                                          }

                 },function(error) {
                 handleErrorResponse(error);
                  $state.go('code_mapping_rbi_bond.inquiry');
                 vm.loader = false;
             });
        }

        /* Verify/Delete Select Details */
        function getVerifyDeleteSelect(flg) {
            vm.loader = true;
            vm.searchSolId = '';
               var requestData = {
                  'pdoCode': vm.pdoCode,
               };
             codeMappingRbiBondService.getVerifyDelSelectDetails(requestData).then(function(response){
                 vm.loader = false;
                 vm.verifyDetailsList = response.data.result;
                 vm.verificationDetails = response.data.result.prevOperType;
                 vm.tdsParameterList = response.data.result.masterOldNewValueModel;
                 },function(error) {
                 handleErrorResponse(error);
                 if(flg == 'V')
                    $state.go('code_mapping_rbi_bond.verify');
                 else
                    $state.go('code_mapping_rbi_bond.delete');
                 vm.loader = false;
             });
        }





        /* Add Submit */
        function submitAddForm() {
//            Swal.fire({
//                 title: "Are you sure?",
//                 text: "Do you want to add this record?",
//                 type: "warning",
//                 showCancelButton: true,
//                 cancelButtonClass: "btn-cancel",
//                 confirmButtonClass: "btn-primary",
//                 confirmButtonText: "Yes",
//                 closeOnConfirm: false,
//                 showLoaderOnConfirm: true
//           },
//               function(){


              Swal.fire({
                                 title: "Are you sure?",
                                 text: "Do you want to Really Add this record?",
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
                   var requestData = {
                       'rbiCode' : vm.rbiCode,
                       'effFromDate' : $rootScope.formatDateToDDMMMYYYY(vm.effFromDate),
                       'blaPrefix' : vm.blaPrefix,
                       'pdoCode' : vm.pdoCode,
                       'remark' : vm.remark
                   };
                   codeMappingRbiBondService.submitAddForm(requestData).then(function (response){
                        Swal.fire("Added!", "Added Successfully", "success");
                       $state.go('code_mapping_rbi_bond.inquiry');
                   },function(error){
                       handleErrorResponse(error);
                       $state.go('code_mapping_rbi_bond.add');
                   })
                 }  });
            }

            function convertToColumnValuesArrayOldNew(objectToBeConvert) {
                if (typeof (objectToBeConvert) === "object") {
                    let resultArray = [];
                    for (let key of Object.keys(objectToBeConvert)) {
                        if (objectToBeConvert[key])
                            resultArray.push({
                                colName: key,
                                value: objectToBeConvert[key].oldValue
                            })
                    }
                    return resultArray;
                } else {
                    throw "Only objects can be converted"
                }
            }

        /* Submit Modify*/
        function modifySubmit() {
//            Swal.fire({
//                    title: "Are you sure?",
//                    text: "Do you want to modify this record?",
//                    type: "warning",
//                    showCancelButton: true,
//                    cancelButtonClass: "btn-cancel",
//                    confirmButtonClass: "btn-danger",
//                    confirmButtonText: "Yes",
//                    cancelButtonText: "No",
//                    closeOnConfirm: false,
//                    showLoaderOnConfirm: true
//                            },
//            function () {




                Swal.fire({
                                   title: "Are you sure?",
                                   text: "Do you want to Really modify this record?",
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
                   vm.tdsParameterListModel = JSON.parse(JSON.stringify(vm.tdsParameterList));
                   vm.tdsParameterListModel.C006.oldValue = $filter('date')(vm.tdsParameterList.C006.oldValue, 'dd-MMM-yyyy');
                   var requestData = {
                       id: vm.id,
                       columnValues: convertToColumnValuesArrayOldNew(vm.tdsParameterListModel),
                   };
                  codeMappingRbiBondService.submitModifyForm(requestData).then(function (response){
                   vm.loader = false;
                  Swal.fire("Modified!", "Modified Successfully", "success");
                  $state.go('code_mapping_rbi_bond.inquiry');
                  },function(error){
                       vm.loader = false;
                       handleErrorResponse(error);
                       $state.go('code_mapping_rbi_bond.modify');
                  })


        }    });



}


        /* Verify Submit */
        function submitVerifyForm() {
//            Swal.fire({
//                    title: "Are you sure?",
//                    text: "Do you want to verify this record?",
//                    type: "warning",
//                    showCancelButton: true,
//                    cancelButtonClass: "btn-cancel",
//                    confirmButtonClass: "btn-primary",
//                    confirmButtonText: "Yes",
//                    closeOnConfirm: false
//                },
//
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
                    vm.loader = true;
                    var requestData = {
                        id: vm.verifyDetailsList.pkColumnValue
                    };
                      codeMappingRbiBondService.submitVerifyForm(requestData).then(function (response){
                          vm.loader = false;

                          Swal.fire("Verified!", "Verified Successfully", "success");
                          $state.go('code_mapping_rbi_bond.inquiry')
                      },function(error){
                          vm.loader = false;
                          handleErrorResponse(error);
                          $state.go('code_mapping_rbi_bond.verify');
                      })
                    }  });
                }


        /* Delete Submit */
function submitDeleteForm() {
//    function handleKeyPress(event) {
//        var key = event.key;
//        var input = event.target.value;
//        var cursorPosition = event.target.selectionStart; // Get the cursor position
//
//        // Prevent leading space at the beginning of the input
//        if (key === ' ' && cursorPosition === 0) {
//            event.preventDefault();
//            return;
//        }
//
//        // Prevent multiple spaces
//        if (key === ' ' && input.slice(-1) === ' ') {
//            event.preventDefault();
//            return;
//        }
//
//        // Allow only letters, space, backspace, and enter
//        if (!key.match(/^[a-zA-Z\s]$/) && key !== 'Backspace' && key !== 'Enter') {
//            event.preventDefault();
//        }
//    }

    // Add keypress event listener
//    document.addEventListener('keypress', handleKeyPress);

//    Swal.fire({
//        title: "Rejection Remarks",
//        text: "",
//        type: "input",
//        inputPlaceholder: "Enter your remarks here",
//        showCancelButton: true,
//        cancelButtonClass: "btn-cancel",
//        confirmButtonClass: "btn-primary",
//        confirmButtonText: "Submit",
//        animation: "slide-from-top",
//        closeOnConfirm: false,
//        showLoaderOnConfirm: true,
//        inputValidator: function(value) {
//            // Check for valid input: only alphabetic characters and single spaces
//            if (value.trim() === "") {
//                return "Please enter the remarks!";
//            }
//
//            // Check for consecutive spaces
//            if (/\s\s/.test(value)) {
//                return "Please enter only single spaces between words!";
//            }
//
//            // Check if the input is valid
//            if (/^[a-zA-Z\s]*$/.test(value)) {
//                // Remove keypress event listener
//                document.removeEventListener('keypress', handleKeyPress);
//                return true;
//            } else {
//                return "Please enter only alphabetic characters!";
//            }
//        }
//    }, function(inputValue) {
//        // Handle cancel button
//        if (inputValue === false) {
//            document.removeEventListener('keypress', handleKeyPress);
//            return false;
//        }
//
//        // Handle empty input
//        if (inputValue === "" || inputValue.trim() === "") {
//            Swal.fire.showInputError("Please enter the remarks!");
//            return false;
//        }

        vm.loader = true;
        var requestData = {
            id: vm.verifyDetailsList.pkColumnValue,
            rejectionRemark: vm.remarks
        };

        codeMappingRbiBondService.submitDeleteForm(requestData).then(function(response) {
            vm.loader = false;
            Swal.fire("Rejected!", "Rejected Successfully", "success");
            $state.go('code_mapping_rbi_bond.inquiry');
        }, function(error) {
            vm.loader = false;
            handleErrorResponse(error);
            $state.go('code_mapping_rbi_bond.inquiry');
        });

}



        // Cancel Confirmation
        function showCancelConfirmation(state) {
            $state.go(state);
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
//
//                });
        }

        //Get Inquiry Version No Tourch Details
        function getInquiryTorch() {
              vm.loader = true;
              vm.searchSolId = '';
              var requestData = {
              pdoCode: vm.pdoCode
              }

               codeMappingRbiBondService.getInquirySelectDetails(requestData).then(function(response){
                   vm.loader=false;
                   if(vm.pdoCode == null || vm.pdoCode == ''){
                   vm.inquiryTorchList = response.data.result.torchValue;
                   angular.element('#pdoCodeModal').modal('toggle');
                   }
                   else{
                     vm.inquiryTorchList = response.data.result.versionNoList;
                     angular.element('#versionListModal').modal('toggle');
                   }

               },function(error) {
                   handleErrorResponse(error);
                   vm.loader = false;
               });
        }



        //Get Inquiry Select Details
        function getInquirySelect() {
              vm.loader = true;
              vm.searchSolId = '';
                 var requestData = {
                    'pdoCode': vm.pdoCode,
                    'versionNo': vm.versionNo

                 }
                   codeMappingRbiBondService.getInquirySelectDetails(requestData).then(function(response){
                       vm.loader = false;
                       vm.verifyDetailsList = response.data.result;
                       vm.verificationDetails = response.data.result.prevOperType;
                       vm.tdsParameterList = response.data.result.masterOldNewValueModel;
                       },function(error) {
                       handleErrorResponse(error);
                       vm.loader = false;
                   });
        }

        //add validation function
         function addValidation(){
                vm.loader = true;
                codeMappingRbiBondService.addValidation().then(function(response){
                vm.loader = false;
                vm.effFromDate = convertToDateObject($filter('date')($rootScope.globals.currentUser.systemDate, 'dd/MM/yyyy'));
                },function(error){
                vm.loader = false;
                    $state.go('code_mapping_rbi_bond.inquiry');
                    handleErrorResponse(error);
                })

         }

            function decimalConvert(flg,errorMsg,modifyFlg){
         		let decimal = parseFloat(flg);

         		if(decimal<100){
         		    if(errorMsg=='P1')
         		        vm.tdsRatePanError = false;
         		    if(errorMsg=='P2')
         		        vm.tdsRateNoPanError = false;
         		    if(errorMsg=='P3')
         		        vm.educationCessError = false;
         		    if(errorMsg=='P4')
         		        vm.otherCessError = false;

         		 	return true;
         		}else{
                     if(errorMsg=='P1'){
                        vm.tdsRatePanError = 'Min 2 Digit Decimal Number(' +decimal+ ')';
                        vm.tdsRatePan='';
                         if(modifyFlg){
                        if(vm.tdsParameterList.tdsRatePan)
                             vm.tdsParameterList.tdsRatePan='';
                         }
                        return false;
                     }
                     if(errorMsg=='P2'){
                        vm.tdsRateNoPanError = 'Min 2 Digit Decimal Number(' +decimal+ ')';
                        vm.tdsRateNoPan='';
                         if(modifyFlg){
                        if(vm.tdsParameterList.tdsRateNoPan)
                             vm.tdsParameterList.tdsRateNoPan='';
                         }
                        return false;
                     }
                     if(errorMsg=='P3'){
                        vm.educationCessError = 'Min 2 Digit Decimal Number(' +decimal+ ')';
                        vm.educationCess='';
                         if(modifyFlg){
                        if(vm.tdsParameterList.educationCess)
                           vm.tdsParameterList.educationCess='';
                        }
                        return false;
                     }
                     if(errorMsg=='P4'){
                        vm.otherCessError = 'Min 2 Digit Decimal Number(' +decimal+ ')';
                        vm.otherCess='';
                        if(modifyFlg){
                             if(vm.tdsParameterList.otherCess)
                                 vm.tdsParameterList.otherCess='';
                        }
                        return false;
                     }
         		}
            }

            //verify and delete torch function
            function getVerifyDeleteTorch(flag){

                    vm.loader = true;
                      vm.searchSolId = '';
                      var requestData = {};
                      if(flag == 'torch'){
                       requestData.pdoCode = vm.pdoCode;
                      }

                   codeMappingRbiBondService.getVerifyDelSelectDetails(requestData).then(function(response){
                       vm.loader=false;
                       if(flag == 'torch'){
                            vm.pdoCodeList = response.data.result.torchValue;
                            angular.element('#pdoCodeModal').modal('toggle');
                       }else{
                            vm.branchCodeList = response.data.result.torchValue;
                           vm.versionNo= vm.branchCodeList[0].versionNo;
                           vm.effectiveFromDate = vm.branchCodeList[0].effectiveFromDate;
                       }

                   },function(error) {
                       handleErrorResponse(error);
                       vm.loader = false;
                   });


            }

            function getModifyTorch(){
               vm.loader = true;
               vm.searchSolId = '';
                 var requestData = {
                    pdoCode: vm.pdoCode
                 }
               codeMappingRbiBondService.getModifySelect(requestData).then(function(response){
                   vm.loader=false;
                   vm.pdoCodeList = response.data.result.torchValue;
                   angular.element('#pdoCodeModal').modal('toggle');
               },function(error) {
                   handleErrorResponse(error);
                   vm.loader = false;
               });
            }




    }
})();