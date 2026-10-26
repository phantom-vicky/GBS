(function() {

    'use strict';

    angular.module('gbmUltraApp').controller('bondInwardOutwardController',
        bondInwardOutwardController);

    bondInwardOutwardController.$inject = ['$scope', '$filter', '$state', '$rootScope',
        '$stateParams', '$location', '$timeout', 'bondInwardOutwardService', 'commonService'
    ];

    function bondInwardOutwardController($scope, $filter, $state, $rootScope, $stateParams,
        $location, $timeout, bondInwardOutwardService, commonService) {


        var vm = this;
        //	    vm.panPattern = /[a-zA-Z]{5}[0-9]{4}[a-zA-Z]{1}/;
    vm.ftsDetailFetch=true;


        vm.validationMessages = '';
        vm.modeOfHolding = ''; // "Single", "Joint", "Jointly", or "HUF"
        vm.getScreenTypeMetaData = getScreenTypeMetaData;
        vm.getRequestCode = getRequestCode;
        //vm.getScssCommonMetaData = getScssCommonMetaData;
        vm.itemsPerPage = "5";
        vm.isIrnRequired = true;
        vm.isBlaNoRequired = true;
        vm.scssOtherBanksDetails = [{}];
        vm.customerMasterDetails = [];
        vm.customerMasterJointDetails = [];
        vm.customerMasterFormDetails = {};
        vm.getFtsDetails = getFtsDetails;
        vm.getStatusMetaData = getStatusMetaData;
        vm.addInward = addInward;
        vm.schemeTorch = schemeTorch;
        vm.checkFields = checkFields;
        vm.getAddInRefNo = getAddInRefNo;
        vm.addOutward = addOutward;
        vm.rejectReason=rejectReason;
        vm.deleteSubmit=deleteSubmit;
        vm.schemeTorchOut = schemeTorchOut;
        vm.getPanTorchDetails = getPanTorchDetails;
        vm.commonYesNoMetaData = COMMON_YES_NO_METADATA;
        vm.showCancelConfirmation = showCancelConfirmation;
        vm.panPattern = /[a-zA-Z]{5}[0-9]{4}[a-zA-Z]{1}/;
        vm.emailPattern = /[^@]+@[^@]+\.[a-zA-Z]{2,6}/;
        vm.optionToggled = optionToggled;
        vm.getAddDetailsBla = getAddDetailsBla;
        vm.getAddDetailsSubsId = getAddDetailsSubsId;
        vm.getRemarksList = getRemarksList;
        vm.inrRefNum = inrRefNum;

        vm.currentDate = new Date();
        //sub

        vm.errorMsg = [];
        vm.accountOpening = [];
        vm.nominationDetails = [{}];
        vm.accountNumberDetails = [];
        vm.accountOpeningMaster = [];
        vm.errorNomineeOperativeAcctNo = [];
        vm.loginSolId = $rootScope.globals.currentUser.solId;
        vm.userName = $rootScope.globals.currentUser.username;
        //        vm.systemDate = $rootScope.globals.currentUser.systemDate;
        //        vm.currentDate = $rootScope.globals.currentUser.systemDate;
        vm.solDate = $filter('date')(vm.systemDate, 'dd/MM/yyyy');

        vm.emailPattern = /[^@]+@[^@]+\.[a-zA-Z]{2,6}/;
        //        vm.inquiryBlaTorch = inquiryBlaTorch;



        //

        vm.loginSolDate = $rootScope.globals.currentUser.solDate;

        vm.genderListMetaData = [{
                label: "MALE",
                value: "M"
            },
            {
                label: "FEMALE",
                value: "F"
            },
            {
                label: "OTHERS",
                value: "O"
            },

        ];

        vm.requestTypeList = [{
                label: "Holder/Customer",
                value: "C"
            },
            {
                label: "Broker",
                value: "B"
            }
        ];


vm.getFtsFile=getFtsFile;
 function getFtsFile() {
      vm.loader = true;
      vm.ftsIndex = 0;
      bondInwardOutwardService.getFtsFileService(vm.ftsAppNum).then(
        function (response) {
          vm.loader = false;
          vm.ftsFile = response.data.body && response.data.body.imagesList;
          if (response.data.statusCodeValue != 200 || !vm.ftsFile || vm.ftsFile.length === 0) {
              vm.ftsDetailFetch = true;
              Swal.fire("Warning!", "Image is not available", "warning");
          } else {
              vm.ftsDetailFetch = false;
          }
        },
        function (error) {
          //handleErrorResponse(error);
//          vm.ftsDetailFetch=false;
          vm.loader = false;
             Swal.fire("Warning!","Image is not available", "warning");
        }
      );
    }
vm.ftsFunction=ftsFunction;
  vm.ftsIndex=0;
   function ftsFunction(flag) {
        if (flag == 'right') {
            if (vm.ftsFile.length - 2 >= vm.ftsIndex) {
                vm.ftsIndex++;
            } else {
                vm.ftsIndex = 0;
            }
        } else {
            if (vm.ftsIndex == 0) {
                vm.ftsIndex = vm.ftsFile.length - 1;
            } else {
                vm.ftsIndex--;
            }
        }

        // Reset image transformations on navigation
        vm.imageRotation = 0;
        vm.imageZoom = 1;
    }
    vm.imageRotation = 0;
    vm.imageZoom = 1;

    vm.rotateImage = function(deg) {
        vm.imageRotation = (vm.imageRotation + deg) % 360;
    };

    vm.zoomImage = function(factor) {
        vm.imageZoom *= factor;
        if (vm.imageZoom < 0.5) vm.imageZoom = 0.5;
        if (vm.imageZoom > 5) vm.imageZoom = 5;
    };
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

        vm.supportingDocumentsMetaData = [{
                label: "No",
                value: 'N'
            },
            {
                label: "Yes",
                value: 'Y'
            },
        ];
        vm.yesNo = [{
                label: "No",
                value: 'N'
            },
            {
                label: "Yes",
                value: 'Y'
            },
        ];
        // Initialize modeOfHoldingList with data

        if ($state.current.name == 'inward_and_outward.add_detail') {
            if ($stateParams.obj) {
                vm.screenType = $stateParams.obj.screenType;
                vm.requestType = $stateParams.obj.requestType; // added


//                		        addInward();

            }

        }

        vm.isBrokerInward = function () {
            return vm.requestType === 'B';
        };

        vm.getRequestTypeLabel = function (requestType) {
            if (requestType === 'B') {
                return 'Broker';
            }
            if (requestType === 'C') {
                return 'Holder/Customer';
            }
            return requestType || '';
        };

        function redirectToInputPage(state) {
            $state.go(state);
        }

        function convertLabelValue(list) {
            for (var item in list) {
                list[item].value = list[item].SUBCODE;
                list[item].label = list[item].DESCRIPTION;
            }
            return list;
        }
vm.fromToDateValidation=fromToDateValidation;
        function fromToDateValidation() {
            vm.toDate = '';
            vm.dateToOptions = {
                minDate: vm.fromDate,
                maxDate: vm.currentDate,
            };
            vm.dateOptions = {
                maxDate: vm.currentDate,
            }
        }



        function schemeTorch() {
            vm.loader = true;

            var requestData = {};
            bondInwardOutwardService.addInward(requestData).then(function(response) {
                vm.loader = false;
                vm.schemeNameList = response.data.result;
                angular.element('#schemeNameSearcherPopUp').modal('toggle');
            }, function(error) {
                handleErrorResponse(error);
                vm.loader = false;
            });
        }

 function checkFields() {
           // If either inRefNo or blaNo is filled, make the other optional
               if (vm.inRefNo && vm.inRefNo.trim() !== '') {
                   vm.isBlaNoRequired = false; // Remove asterisk from BLA No
               } else {
                   vm.isBlaNoRequired = true; // Keep BLA No required
               }

               if (vm.blaNo && vm.blaNo.trim() !== '') {
                   vm.isInRefRequired = false; // Remove asterisk from Inward Reference Number
               } else {
                   vm.isInRefRequired = true; // Keep Inward Reference Number required
               }
        }

         function schemeTorchOut() {
                    vm.loader = true;

                    var requestData = {};
                    bondInwardOutwardService.getAddInRefNo(requestData).then(function(response) {
                        vm.loader = false;
                        vm.schemeNameList = response.data.result;
                        angular.element('#schemeNameSearcherPopUp').modal('toggle');
                    }, function(error) {
                        handleErrorResponse(error);
                        vm.loader = false;
                    });
                }

        function getScreenTypeMetaData() {
            vm.loader = true;
            vm.referenceId = 'SCREEN_TYPE';
            var requestData = {
                'referenceId': vm.referenceId
            };
            bondInwardOutwardService.getMetaData(requestData).then(function(response) {
                vm.loader = false;
                vm.screenTypeList = response.data.result;
            }, function(error) {
                handleErrorResponse(error);
                vm.loader = false;
            });
        }
        function getStatusMetaData() {
                    vm.loader = true;
                    vm.referenceId = 'STATUS';
                    var requestData = {
                        'referenceId': vm.referenceId
                    };
                    bondInwardOutwardService.getMetaData(requestData).then(function(response) {
                        vm.loader = false;
                        vm.statusList = response.data.result;
                    }, function(error) {
                        handleErrorResponse(error);
                        vm.loader = false;
                    });
                }
                vm.optionToggledInward=optionToggledInward;
                function optionToggledInward(flag) {
                                                             vm.isAllSelected = [];

                                                             if(flag == 'ALL'){
                                                                 if(vm.selectedAllItem){
                                                                     angular.forEach(vm.blaRequestList, function(item) {
                                                                         item.selected = true;
                                                                         vm.isAllSelected.push(item.selected)
                                                                     });
                                                                 }else{
                                                                     angular.forEach(vm.blaRequestList, function(item) {
                                                                         item.selected = false;
                                                                         vm.isAllSelected.push(item.selected)
                                                                     });
                                                                 }
                                                             }
                                                             else{
                                                             vm.selectedCount = 0;
                                                                 angular.forEach(vm.blaRequestList, function(item) {
                                                                     vm.isAllSelected.push(item.selected)
                                                                         if(item.selected){
                                                                            vm.selectedCount = vm.selectedCount + 1;
                                                                         }
                                                                 });

                                                                 if(null!=vm.blaRequestList&&(vm.blaRequestList.length == vm.selectedCount)){
                                                                     vm.selectedAllItem = true;
                                                                 }
                                                                 else{
                                                                    vm.selectedAllItem = false;
                                                                 }
                                                             }
                                                         }
         function optionToggled(flag) {
                                             vm.isAllSelected = [];

                                             if(flag == 'ALL'){
                                                 if(vm.selectedAllItem){
                                                     angular.forEach(vm.outDetailList, function(item) {
                                                         item.selected = true;
                                                         vm.isAllSelected.push(item.selected)
                                                     });
                                                 }else{
                                                     angular.forEach(vm.outDetailList, function(item) {
                                                         item.selected = false;
                                                         vm.isAllSelected.push(item.selected)
                                                     });
                                                 }
                                             }
                                             else{
                                             vm.selectedCount = 0;
                                                 angular.forEach(vm.outDetailList, function(item) {
                                                     vm.isAllSelected.push(item.selected)
                                                         if(item.selected){
                                                            vm.selectedCount = vm.selectedCount + 1;
                                                         }
                                                 });

                                                 if(null!=vm.outDetailList&&(vm.outDetailList.length == vm.selectedCount)){
                                                     vm.selectedAllItem = true;
                                                 }
                                                 else{
                                                    vm.selectedAllItem = false;
                                                 }
                                             }
                                         }

        function getRequestCode() {
            vm.loader = true;
            vm.referenceId = 'REQUEST_CODE';
            var requestData = {
                'referenceId': vm.referenceId
            };
            bondInwardOutwardService.getMetaData(requestData).then(function(response) {
                vm.loader = false;
                vm.screenTypeList = response.data.result;
            }, function(error) {
                handleErrorResponse(error);
                vm.loader = false;
            });
        }

        function getFtsDetails() {
            vm.loader = true;
            var objRequest;

            // Define the request data object
            var requestData = {
                objRequest: {
                    "Cust_ID": "123456",
                    "Account_Number": "7890",
                    "Barcode": "ABC123456",
                    "Form_Status": "processed",
                    "Status": "Production",
                    "System_Type": "CRM",
                    "IsData": "Yes"
                }
            };

            var RequestBody = JSON.parse(JSON.stringify(requestData));
            bondInwardOutwardService.getFtsDetails(RequestBody).then(function(response) {
                vm.loader = false;
                if (response.data && response.data.result) {
                    vm.ftsDetails = response.data.result;
                }
            }, function(error) {
                handleErrorResponse(error);
                vm.loader = false;
            });
        }
vm.getPanTorchDetailsOutward=getPanTorchDetailsOutward;
function getPanTorchDetailsOutward() {
            vm.loader = true;
            var requestData = {
                'schemeCode': vm.schemeCode,
                'nameOfTheBondHolder': vm.nameOfTheBondHolder,
                'panNo': vm.panNo
            }
            bondInwardOutwardService.getPanTorchDetails(requestData).then(function(response) {
                vm.loader = false;
                vm.panDataList = response.data.result;
                angular.element('#pan2').modal('toggle');
            }, function(error) {
                handleErrorResponse(error);
                vm.loader = false;
            });
        }

        function getPanTorchDetails(flag) {
            vm.loader = true;

            var requestData = {
                'schemeCode': vm.schemeCode,
                'nameOfTheBondHolder': vm.nameOfTheBondHolder,
                'panNo': vm.panNo,
                'requestType': flag
            }

            bondInwardOutwardService.getPanTorchDetails(requestData)
            .then(function(response) {
                vm.loader = false;
                vm.panDataList = response.data.result;

                if(flag == 'C')
                    angular.element('#pan1').modal('toggle');
                else if(flag == 'B')
                    angular.element('#brokerDetailsModal').modal('toggle');
            }, function(error) {
                handleErrorResponse(error);
                vm.loader = false;
            });
        }

        vm.selectedDescriptions = [];

        /* Add Submit */
        function addInward() {
//            Swal.fire({
//                    title: "Are you sure?",
//                    text: "Do you want to Really Add this record?",
//                    type: "warning",
//                    showCancelButton: true,
//                    cancelButtonClass: "btn-cancel",
//                    confirmButtonClass: "btn-primary",
//                    confirmButtonText: "Yes, Add it!",
//                    closeOnConfirm: false,
//                    showLoaderOnConfirm: true
//                },
//                function() {






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
                                    vm.selectedDesc=[];
                                    angular.forEach(vm.blaRequestList, function(item){
                                 if (item.selected) {
                                 vm.selectedDesc.push({
                                     requestDesc: item.DESCRIPTION,
                                     requestCode: item.SUBCODE,
                                     schemeName: item.schemeName,
                                     schemeCode: item.schemeCode,
                                     uniqueRefNo: item.uniqueRefNo,
                                     blaNo: item.blaNo,
                                     brokerCode: item.brokerAgencyCode || vm.brokerCode
                                 });
                                 }
                            })
                    var requestData = {
                        'screenType': vm.screenType,
                        'requestType': vm.requestType, // added
                        'uniqueRefNo': vm.uniqueRefNo,
                        'ftsAppNum': vm.ftsAppNum,
                        'schemeCode': vm.schemeCode,
                        'nameOfTheBondHolder': vm.nameOfTheBondHolder,
                        'panNo': vm.panNo,
                        'inRefNo': vm.inRefNo,
                        'inwDate': $filter('date')(vm.inwDate, 'dd-MMM-yyyy'),
                        'inwDetailsList': vm.selectedDesc,
                        'inwardRemarks': vm.inwardRemarks,
                        'brokerCode': vm.brokerCode, // added
                        'brokerName': vm.brokerName // added
                    };
                    bondInwardOutwardService.addInward(requestData).then(function(response) {
                        Swal.fire("Added!","Inward No: " + vm.inRefNo + " Inward Added Successfully.","success");
                        $state.go('inward_and_outward.add');
                    }, function(error) {
                        vm.loader= false;
                        const errorMessage= error && error.data && error.data.message
                                            ? error.data.message
                                            : "An unexpected error occured.";
                        if(errorMessage!= null && errorMessage.startsWith("Invalid PAN"))  {
                            Swal.fire("Warning!", errorMessage, "warning");
                            $state.go("inward_and_outward.add");
                        }else if (errorMessage && errorMessage.includes('Given Request Is Open In Outward for BLA No')) {
                           Swal.fire("Warning!",errorMessage,"warning");
                         }else {
                            handleErrorResponse(error);
                            $state.go('inward_and_outward.add');
                        }
                    })
              }  });
        }

     /* Add Submit */
            function addOutward() {

            let anySelected = vm.outDetailList.some(item => item.selected);

            if (!anySelected) {
                    var msg = 'Please select a Outward to add';
                    Swal.fire('Warning!', msg, 'warning');
                } else {
                    vm.selectedOutWardList = [];
                    angular.forEach(vm.outDetailList, function(item) {
                        if (item.selected) {
                            vm.selectedOutWardList.push({
                                inRefNo: item.inRefNo,
                                requestCode: item.requestCode,
                                schemeCode: item.schemeCode,
                                blaNo: item.blaNo
                            });
                        }
                    });
//                Swal.fire({
//                        title: "Are you sure?",
//                        text: "Do you want to Really Add this record?",
//                        type: "warning",
//                        showCancelButton: true,
//                        cancelButtonClass: "btn-cancel",
//                        confirmButtonClass: "btn-primary",
//                        confirmButtonText: "Yes, Add it!",
//                        closeOnConfirm: false,
//                        showLoaderOnConfirm: true
//                    },
//                    function() {

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
                        vm.loader=true;
                        var requestData = {
                            'inwDetailsList': vm.selectedOutWardList,
                            'outwardRefNo': vm.outRefNo,
                            'inwDate': $filter('date')(vm.inwDate, 'dd-MMM-yyyy'),
                            'tranStatus':vm.tranStatus,
                            'remarks':vm.remarks,
                            'podConsignmentNumber':vm.podConsignmentNumber
                        };
                        bondInwardOutwardService.addOutward(requestData).then(function(response) {
                            vm.loader=false;
                            Swal.fire("Added!","Outward No: " + vm.outRefNo + " Outward Added Successfully.","success");
                            $state.go('inward_and_outward.add');
                        }, function(error) {
                            vm.loader=false;
                            handleErrorResponse(error);
                            $state.go('inward_and_outward.add');
                        })
                }    });
                    }
            }
function rejectReason() {
  angular.element('#permissionMappingDelete').modal('toggle');
  }
  function getRemarksList() {
      vm.referenceId = '056';
      var requestData = { 'referenceId': vm.referenceId };
      bondInwardOutwardService.getRemarksList(requestData).then(function(response) {
          vm.remarksList = response.data.result || []; // Ensure it's an array
      }, function(error) {
          handleErrorResponse(error);
      });
  }
   function deleteSubmit(){
            let anySelected = vm.outDetailList.some(item => item.selected);
            if (!anySelected) {
                    var msg = 'Please select a Outward to reject';
                    Swal.fire('Warning!', msg, 'warning');
                } else {
                    vm.selectedOutWardList = [];
                    angular.forEach(vm.outDetailList, function(item) {
                        if (item.selected) {
                            vm.selectedOutWardList.push({
                                inRefNo: item.inRefNo,
                                requestCode: item.requestCode,
                                schemeCode: item.schemeCode,
                                blaNo: item.blaNo
                            });
                        }
                    });
                      Swal.fire({
                        title: "Are you sure?",
                        text: "Do you want to Really Reject this record?",
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
                             vm.loader=true;
                        var requestData = {
                            'inwDetailsList': vm.selectedOutWardList,
                            'outwardRefNo': vm.outRefNo,
                            'inwDate': $filter('date')(vm.inwDate, 'dd-MMM-yyyy'),
                            'tranStatus':vm.tranStatus,
                            'remarks':vm.remarks
                        };
                           bondInwardOutwardService.deleteSubmit(requestData).then(
                                 (response) => {
                                   vm.loader = false;
                                   var message=response.data.message;
                                   Swal.fire("Rejected!","Outward No: " + vm.outRefNo + " Rejected successfully.","success");
                                   $state.go('inward_and_outward.add');
                                 },
                                 function(error) {
                                     vm.loader = false;
                                     handleErrorResponse(error);
                                 });
                               }
                               });
                          }
}
        /* Check box selection */
        function selectedChecklist() {
            angular.forEach(vm.serviceCodeDetailsList, function(item) {
                if (item.selected) {
                    vm.selectedCheckListArray.push(item.serCode);
                }
            });
            if (vm.selectedCheckListArray.length == '0') {
                Swal.fire('Please select atleast one request type')
            } else {
                getMonitorServiceReport();
            }
        }

        vm.displayRemarks = true;
        vm.reqField = true;




        // Cancel Confirmation
        function showCancelConfirmation(state) {
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
                 text: "Do you Really want to cancel?",
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




        function getAddDetailsBla() {
            vm.loader = true;
            var requestData = {
                'panNo': vm.panNo
            }
            bondInwardOutwardService.getAddDetailsBla(requestData).then(function(response) {
                vm.loader = false;
                vm.blaDataList = response.data.result;
                if (vm.blaDataList && vm.blaDataList.length > 0 && vm.blaDataList[0].schemeCode) {
                    vm.schemeCode = vm.blaDataList[0].schemeCode;
                    vm.schemeName = vm.blaDataList[0].schemeName;
                }
                vm.blaRequestList=[];
                //angular.element('#blaModel2').modal('toggle');
            }, function(error) {
                handleErrorResponse(error);
                vm.loader = false;
            });
        }

          function getAddInRefNo(flag) {
                    vm.loader = true;
                    if(flag == 'S'){
                    var requestData = {
                        'flag': flag
                    };
                    } else {
                    var requestData = {
                        'inRefNo': vm.inRefNo,
                        'flag': flag,
                        'requestType': vm.requestType
                        }
                    }
                    bondInwardOutwardService.getAddInRefNo(requestData).then(function(response) {
                        vm.loader = false;
                        if(flag == 'I'){
                       vm.inrRefNoDataList = response.data.result;
                       angular.element('#inrRefNo').modal('toggle');
                        }else if(flag == 'B'){
                        vm.blaDataList = response.data.result;
                        angular.element('#blaModel1').modal('toggle');
                        } else{
                        vm.outDetailList = response.data.result;
                        if (vm.outDetailList && vm.outDetailList.length > 0 && vm.outDetailList[0].requestType) {
                            vm.requestType = vm.outDetailList[0].requestType;
                        }
                        vm.requestCodeList=[];
                        vm.ftsAppNum =vm.outDetailList[0].ftsAppNum;
                          angular.forEach(vm.outDetailList, function(item){
                                     vm.requestCodeList.push({
                                      requestCode: item.requestCode
                                     });
                                })
                         vm.requestDescList=[];
                                     angular.forEach(vm.outDetailList, function(item){
                                     vm.requestDescList.push({
                                     requestDesc: item.requestDesc
                                     });
                                     })
                        }
                    }, function(error) {
                        handleErrorResponse(error);
                        vm.loader = false;
                    });
                }

        function getAddDetailsSubsId() {
            vm.loader = true;

            var requestData = {
                'schemeCode': vm.schemeCode,
                'nameOfTheBondHolder': vm.nameOfTheBondHolder,
                'panNo': vm.panNo,
                'blaNo': vm.blaNo,
                'boSubsId': vm.boSubsId,

            }
            bondInwardOutwardService.getAddDetailsSubsId(requestData).then(function(response) {
                vm.loader = false;
                vm.bondSubsList = response.data.result;
                angular.element('#bondSubsIdSearch').modal('toggle');
            }, function(error) {
                handleErrorResponse(error);
                vm.loader = false;
            });


        }
         function inrRefNum() {
                    vm.loader = true;

                    var requestData = {
                        'screenType': vm.screenType,
                        'requestType': vm.requestType, // added
                        'inwDate' : $filter('date')(vm.inwDate, 'dd-MMM-yyyy')
                    };
                    bondInwardOutwardService.inrRefNum(requestData).then(function(response) {
                        vm.loader = false;
                        if(vm.screenType == 'I001'){
                        vm.inRefNo = response.data.result;
                        } else if(vm.screenType == 'O001'){
                                                vm.outRefNo = response.data.result;
                        }
                    }, function(error) {
                        handleErrorResponse(error);
                        vm.loader = false;
                    });


                }
                vm.isAnySelected = isAnySelected;
function isAnySelected() {
    return vm.screenTypeList.some(function(item) {
        return item.selected;
    });
};
        // Array to store selected descriptions
        vm.updateDescriptions = updateDescriptions;
        // Function to update descriptions based on selected request codes
        function updateDescriptions() {
            vm.selectedDescriptions = vm.screenTypeList
                .filter(function(item) {
                    return item.selected;
                });
                var selectedList = vm.screenTypeList.filter(function(item) {
                        return item.selected;
                    });
               vm.inwardRemarks = selectedList
                       .map(function(item) {
                           return item.DESCRIPTION;
                       })
                       .join('\n');
                         vm.blaRequestList = [];

                         if (vm.requestType == 'B') { // added broker request table build
                             angular.forEach(selectedList, function (desc) {
                                 vm.blaRequestList.push({
                                     uniqueRefNo: vm.ftsAppNum || vm.uniqueRefNo,
                                     SUBCODE: desc.SUBCODE,
                                     DESCRIPTION: desc.DESCRIPTION,
                                     schemeCode: vm.schemeCode,
                                     schemeName: vm.schemeName,
                                     brokerAgencyCode: vm.brokerAgencyCode
                                 });
                             });
                         } else {
                             angular.forEach(vm.blaDataList, function (bla) {
                                 if (bla) {
                                     angular.forEach(vm.selectedDescriptions, function (desc) {
                                         vm.blaRequestList.push({
                                             schemeName: bla.schemeName,
                                             schemeCode: bla.schemeCode,
                                             panNo: bla.panNo,
                                             blaNo: bla.blaNo,
                                             uniqueRefNo: bla.uniqueRefNo,
                                             SUBCODE: desc.SUBCODE,
                                             DESCRIPTION: desc.DESCRIPTION
                                         });
                                     });
                                 }
                             });
                         }
        }
    }
})();