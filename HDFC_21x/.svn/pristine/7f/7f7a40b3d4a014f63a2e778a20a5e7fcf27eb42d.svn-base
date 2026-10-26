(function() {

    'use strict';

    angular.module('gbmUltraApp').controller('rbibondtocSharingMaintenanceController',
        rbibondtocSharingMaintenanceController);

    rbibondtocSharingMaintenanceController.$inject = ['$scope', '$filter', '$state',
        '$stateParams', '$location', 'rbibondtocSharingMaintenanceService', 'commonService'
    ];

    function rbibondtocSharingMaintenanceController($scope, $filter, $state, $stateParams,
        $location, rbibondtocSharingMaintenanceService, commonService) {

        var vm = this;

        vm.commonYesNoMetaData = COMMON_YES_NO_METADATA;


        vm.errorMsg = [];
        vm.levelDetailsTocSharingDto = [];

        vm.decimalConvert = decimalConvert;
        vm.getModuleName = getModuleName;
        vm.calculateSum = calculateSum;
        vm.submitAddForm = submitAddForm;
        vm.showCancelConfirmation = showCancelConfirmation;
        vm.validateAddSelect = validateAddSelect;
        vm.getInquiryVersionTorch = getInquiryVersionTorch;
        vm.getInquirySelect = getInquirySelect;
        vm.getRoundFactList = getRoundFactList;
        vm.checkRecordExisted = checkRecordExisted;
        vm.getVerifyDeleteSelect = getVerifyDeleteSelect;
        vm.getVersionEffFromDate = getVersionEffFromDate;
        vm.submitDeleteForm = submitDeleteForm;
        vm.submitVerifyForm = submitVerifyForm;
        vm.getModifySelect = getModifySelect;
        vm.getVersionEffFromDateForModify = getVersionEffFromDateForModify;
        vm.dateValidationForModify = dateValidationForModify;
        vm.calculateSumForModify = calculateSumForModify;
        vm.decimalConvertForModify = decimalConvertForModify;
        vm.modifySubmit = modifySubmit;
        vm.formatDate = formatDate;
        /* vm.getSubCodeList = getSubCodeList;

         getSubCodeList();*/
        getModuleName();
        getRoundFactList();




        if ($state.current.name == 'rbi_bond_toc_share_maintanance.add_detail') {
            if ($stateParams.obj) {
                vm.schemeCode = $stateParams.obj.schemeCode;
                vm.versionNo = $stateParams.obj.versionNo;
                vm.effFromDate = $stateParams.obj.effFromDate;
                if (vm.schemeCode && vm.effFromDate) {
                    validateAddSelect();
                } else {
                    $state.go('rbi_bond_toc_share_maintanance.add');
                }

            }
        } else if ($state.current.name == 'rbi_bond_toc_share_maintanance.modify_detail') {
            if ($stateParams.obj) {
                vm.schemeCode = $stateParams.obj.schemeCode;
                vm.versionNo = $stateParams.obj.versionNo;
                vm.effFromDate = $stateParams.obj.effFromDate;
                getModifySelect();
            } else {
                $state.go('rbi_bond_toc_share_maintanance.modify');
            }
        } else if ($state.current.name == 'rbi_bond_toc_share_maintanance.verify_detail') {
            if ($stateParams.obj) {
                vm.schemeCode = $stateParams.obj.schemeCode;
                vm.versionNo = $stateParams.obj.versionNo;
                vm.effFromDate = $stateParams.obj.effFromDate;
                getVerifyDeleteSelect();
            } else {
                $state.go('rbi_bond_toc_share_maintanance.verify');
            }
        } else if ($state.current.name == 'rbi_bond_toc_share_maintanance.delete_detail') {
            if ($stateParams.obj) {
                vm.schemeCode = $stateParams.obj.schemeCode;
                vm.versionNo = $stateParams.obj.versionNo;
                vm.effFromDate = $stateParams.obj.effFromDate;

                getVerifyDeleteSelect();
            } else {
                $state.go('rbi_bond_toc_share_maintanance.delete');
            }
        } else if ($state.current.name == 'rbi_bond_toc_share_maintanance.inquiry_detail') {
            if ($stateParams.obj) {
                vm.schemeCode = $stateParams.obj.schemeCode;
                vm.versionNo = $stateParams.obj.versionNo;
                vm.effFromDate = $stateParams.obj.effFromDate;


                getInquirySelect();
            } else {
                $state.go('rbi_bond_toc_share_maintanance.inquiry');
            }
        }


        vm.roundingFactorMetaData = [{
                label: "Ceil",
                value: "RF002"
            },
            {
                label: "Floor",
                value: "RF003"
            },
            {
                label: "Round",
                value: "RF004"
            },
            {
                label: "Actual",
                value: "RF001"
            },
        ];

        //Get Module Name
        function getModuleName() {
            vm.loader = true;
            vm.referenceId = 'TAX';
            var requestData = {
                'referenceId': vm.referenceId
            };
            rbibondtocSharingMaintenanceService.getRbiBondModuleNameDetails(requestData).then(function(response) {
                    vm.loader = false;
                    vm.schemeList = response.data.result;
                    vm.moduleNameDetailsLabelValue = [];
                    angular.forEach(vm.schemeList, function(item) {
                        vm.moduleNameDetailsLabelValue.push({
                            label: item.subCodeDescription,
                            value: item.subCode
                        })
                    })
                },
                function(error) {
                    handleErrorResponse(error);
                    vm.loader = false;
                });
        }

        function decimalConvert(value, index, event) {

            let decimal = parseFloat(value);

            vm.index = index;
            if (decimal == undefined || decimal == null || decimal == NaN) {
                if (event.target.value == "") {
                    vm.errorMsg[vm.index] = false;
                } else if (event.target.id == vm.index) {

                    if (event.target.value <= 100) {
                        vm.errorMsg[vm.index] = false;
                    } else {
                        vm.errorMsg[vm.index] = true;

                        for (var i = 0; i < vm.levelDetailsTocSharingDto.length; i++) {
                            if (vm.index == i) {
                                vm.levelDetailsTocSharingDto[vm.index].sharingPercentage = '';
                                break;
                            }
                        }
                    }
                }
            } else {
                let strValue = value.toString();
                let strValueLen = strValue.length;


                if ((strValue[1] == "." && strValue[2] == ".") || (strValue[1] == "." && strValue[3] == ".") || (strValue[1] == "." && strValue[4] == ".") || (strValue[2] == "." && strValue[3] == ".") || (strValue[2] == "." && strValue[4] == ".") || (strValue[3] == "." && strValue[4] == ".") || (strValue == "NaN") || (strValue[0] == '.') || (strValue[1] == '.' && strValueLen == 5) || (strValue[2] == '.' && strValueLen == 6)) {
                    vm.errorMsg[vm.index] = true;
                    vm.levelDetailsTocSharingDto[vm.index].sharingPercentage = '';
                } else if (decimal >= 100) {
                    vm.errorMsg[vm.index] = true;
                    vm.levelDetailsTocSharingDto[vm.index].sharingPercentage = '';

                } else {
                    vm.errorMsg[vm.index] = false;

                }
            }
        }

        function decimalConvertForModify(value, index, event) {

            let decimal = parseFloat(value);

            vm.index = index;
            if (decimal == undefined || decimal == null || decimal == NaN) {
                if (event.target.value == "") {
                    vm.errorMsg[vm.index] = false;
                } else if (event.target.id == vm.index) {

                    if (event.target.value <= 100) {
                        vm.errorMsg[vm.index] = false;
                    } else {
                        vm.errorMsg[vm.index] = true;

                        for (var i = 0; i < vm.levelDetailsTocSharingDto.length; i++) {
                            if (vm.index == i) {
                                vm.levelDetailsTocSharingDto[vm.index].C007.oldValue = '';
                                break;
                            }
                        }
                    }
                }
            } else {
                let strValue = value.toString();
                let strValueLen = strValue.length;


                if ((strValue[1] == "." && strValue[2] == ".") || (strValue[1] == "." && strValue[3] == ".") || (strValue[1] == "." && strValue[4] == ".") || (strValue[2] == "." && strValue[3] == ".") || (strValue[2] == "." && strValue[4] == ".") || (strValue[3] == "." && strValue[4] == ".") || (strValue == "NaN") || (strValue[0] == '.') || (strValue[1] == '.' && strValueLen == 5) || (strValue[2] == '.' && strValueLen == 6)) {
                    vm.errorMsg[vm.index] = true;
                    vm.levelDetailsTocSharingDto[vm.index].C007.oldValue = '';
                } else if (decimal >= 100) {
                    vm.errorMsg[vm.index] = true;
                    vm.levelDetailsTocSharingDto[vm.index].C007.oldValue = '';

                } else {
                    vm.errorMsg[vm.index] = false;

                }
            }
        }

        function calculateSum() {
            vm.total = 0;
            for (var i = 0; i < vm.levelDetailsTocSharingDto.length; i++) {
                let lastRow = vm.levelDetailsTocSharingDto.length - 1;
                if (lastRow) {
                    vm.sum = parseFloat(vm.levelDetailsTocSharingDto[i].sharingPercentage);
                    vm.total += vm.sum;
                }
            }
        }

        function calculateSumForModify() {
            vm.total = 0;
            for (var i = 0; i < vm.levelDetailsTocSharingDto.length; i++) {
                let lastRow = vm.levelDetailsTocSharingDto.length - 1;
                if (lastRow) {
                    vm.sum = parseFloat(vm.levelDetailsTocSharingDto[i].C007.oldValue);
                    vm.total += vm.sum;
                }
            }
        }

        function submitAddForm() {
            calculateSum();
            if (vm.total != 100) {
                var msg = 'Sum of sharing percentage should be equal to 100';
                Swal.fire('Warning!', msg, 'warning');
                /*                for(var i = 0; i < vm.levelDetailsTocSharingDto.length; i++){
                                   vm.levelDetailsTocSharingDto[i].sharingPercentage = '';
                                   vm.total = '';
                                }*/
            } else {
//                Swal.fire({
//                        title: "Are you sure?",
//                        text: "Do you want to Really Add this record?",
//                        type: "warning",
//                        showCancelButton: true,
//                        cancelButtonClass: "btn-cancel",
//                        confirmButtonClass: "btn-primary",
//                        confirmButtonText: "Yes",
//                        closeOnConfirm: false
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
                        vm.loader = true;
                        var requestData = {
                            "schemeCode": vm.schemeCode,
                            "versionNo": vm.versionNo,
                            "effeFromDate": $filter('date')(vm.effFromDate, 'dd-MMM-yyyy'),
                            "roundOff": vm.roundOffValue,
                            "levelDetailsTocSharingDto": vm.levelDetailsTocSharingDto
                        };
                        rbibondtocSharingMaintenanceService.submitAddForm(requestData).then(
                            (response) => {
                                vm.loader = false;
                                Swal.fire("Record Added!", "TOC Sharing Maintenance Created Successfully", "success");
                                location.href = '#!/rbi_bond_toc_share_maintanance/action/inquiry';
                            },
                            function(error) {
                                vm.loader = false;
                                handleErrorResponse(error);

                            });
                  }  });
            }
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
                                     text: "Do you Really want to cancel ?",
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

        //Get Inquiry Version No Tourch Details
        function getInquiryVersionTorch() {
            vm.loader = true;
            vm.searchSolId = '';
            var requestData = {
                'schemeCode': vm.schemeCode,
                'versionNo': "",
                'effeFromDate': ""
            }
            rbibondtocSharingMaintenanceService.getInquirySelectDetails(requestData).then(function(response) {
                vm.loader = false;
                vm.versionNoList = response.data.result.versionOut;
                angular.forEach(vm.versionNoList, function(item) {
                    if (item.verifiedStatus == 'Y') {
                        item.verifiedStatus = 'Yes';
                    } else {
                        item.verifiedStatus = 'No';
                    }

                });
                angular.element('#versionNoModal').modal('toggle');
            }, function(error) {
                handleErrorResponse(error);
                vm.loader = false;
            });
        }

        /*        //Check Record already is there or not
                function checkRecordExisted(){
                    var requestData = {};
                    tocSharingMaintenanceService.checkRecordExisted(requestData).then(function(response){
                        if(response.data.message=='SUCCESS')
                         {
                            Swal.fire("Warning!", "Record already added, Do Changes in modify", "warning");
                                         $state.go('common_toc_sharing_maintenance.modify');
                         }
                    },function(error){
                        if(error.data.message=='NDF') {
                            $state.go('common_toc_sharing_maintenance.add');
                         }else{
                                 handleErrorResponse(error);
                         }
                    })
                }*/

        function dateValidationForModify() {
                         vm.effFromDateOptions = {
                            minDate: vm.effFromDate[3]+vm.effFromDate[4]+vm.effFromDate[2]+vm.effFromDate[0]+vm.effFromDate[1]+vm.effFromDate[5]+vm.effFromDate[6]+vm.effFromDate[7]+vm.effFromDate[8]+vm.effFromDate[9]
                        }

        }

        function validateAddSelect() {
            vm.loader = true;
            var requestData = {
                'schemeCode': vm.schemeCode
            }
            rbibondtocSharingMaintenanceService.validateAddSelect(requestData).then(function(response) {
                vm.loader = false;
                vm.levelDetailsTocSharingDto = response.data.result.levelsOut;
            }, function(error) {
                handleErrorResponse(error);
                vm.loader = false;
                $state.go('rbi_bond_toc_share_maintanance.add');
            });
        }

        function checkRecordExisted() {
            var requestData = {
                'schemeCode': vm.schemeCode
            }
            vm.versionNo = '00001';
            rbibondtocSharingMaintenanceService.validateAddSelect(requestData).then(function(response) {

            }, function(error) {
                handleErrorResponse(error);
                $state.go('rbi_bond_toc_share_maintanance.inquiry');
            });
        }

        //Get Inquiry Select Details
        function getInquirySelect() {
            vm.loader = true;
            vm.searchSolId = '';
            var requestData = {
                'schemeCode': vm.schemeCode,
                'versionNo': vm.versionNo,
                'effeFromDate': $filter('date')(vm.effFromDate, 'dd-MMM-yyyy')
            }
            rbibondtocSharingMaintenanceService.getInquirySelectDetails(requestData).then(function(response) {
                vm.loader = false;
                vm.auditInfo = response.data.result;
                vm.tocSharingList = response.data.result.oldNewValueList;
                vm.versionEffDateDetails = response.data.result.oldNewValueList[0];
                vm.roundOffValue = response.data.result.oldNewValueList[0].C008;
            }, function(error) {
                handleErrorResponse(error);
                vm.loader = false;

            });
        }

        //Get Rounding Factor
        function getRoundFactList() {
            vm.referenceId = 'ROUNDING_FACTOR';
            var requestData = {
                'referenceId': vm.referenceId
            }
            rbibondtocSharingMaintenanceService.getRbiBondRoundFactor(requestData).then(function(response) {
                vm.roudingFactorList = response.data.result;
                vm.roundOff = [];
                angular.forEach(vm.roudingFactorList, function(item) {
                    vm.roundOff.push({
                        label: item.subCodeDescription,
                        value: item.subCode
                    })
                })
            }, function(error) {
                handleErrorResponse(error);
            });
        }

        /* Verify/Delete Select Details */
        function getVerifyDeleteSelect() {
            vm.loader = true;
            var requestData = {
                'schemeCode': vm.schemeCode,
                'versionNo': vm.versionNo,
                'effeFromDate': $filter('date')(vm.effFromDate, 'dd-MMM-yyyy')
            }
            rbibondtocSharingMaintenanceService.VerifyDeleteTocSharing(requestData).then(
                (response) => {
                    vm.loader = false;
                    vm.auditInfo = response.data.result;
                    vm.tocSharingList = response.data.result.oldNewValueList;
                    vm.versionEffDateDetails = response.data.result.oldNewValueList[0];
                    vm.roundOffValue = response.data.result.oldNewValueList[0].C008;

                },
                function(error) {
                    vm.loader = false;
                    handleErrorResponse(error);

                });
        }

        /* Version/EffFromDate  Details */
        function getVersionEffFromDate() {
            vm.loader = true;
            var requestData = {
                'schemeCode': vm.schemeCode
            }
            rbibondtocSharingMaintenanceService.VerifyDeleteTocSharing(requestData).then(
                (response) => {
                    vm.loader = false;
                    vm.versionNo = response.data.result.versionOut[0].versionNo;
                    vm.effFromDate = response.data.result.versionOut[0].effectiveFromDate;

                },
                function(error) {
                    vm.loader = false;
                    handleErrorResponse(error);
                });
        }


        /* Delete Submit */
        function submitDeleteForm() {
//            Swal.fire({
//                    title: "Are you sure?",
//                    text: "Do you want to Really Delete this record?",
//                    type: "warning",
//                    showCancelButton: true,
//                    cancelButtonClass: "btn-cancel",
//                    confirmButtonClass: "btn-primary",
//                    confirmButtonText: "Yes",
//                    closeOnConfirm: false
//                },

      //          function() {



              Swal.fire({
                             title: "Are you sure?",
                             text: "Do you want to Really Delete this record?",
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
                        'schemeCode': vm.schemeCode,
                        'versionNo': vm.versionNo,
                        'effeFromDate': $filter('date')(vm.effFromDate, 'dd-MMM-yyyy')
                    }
                    rbibondtocSharingMaintenanceService.submitDeleteForm(requestData).then(
                        (response) => {
                            vm.loader = false;
                            Swal.fire("Record Deleted!", "TOC Sharing Maintenance Deleted Successfully", "success");
                            location.href = '#!/rbi_bond_toc_share_maintanance/action/inquiry';
                        },
                        function(error) {
                            vm.loader = false;
                            handleErrorResponse(error);

                        });
              }  });

        }

        /* Verify Submit */
        function submitVerifyForm() {
//            Swal.fire({
//                    title: "Are you sure?",
//                    text: "Do you want to Really Verify this record?",
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
                    let requestData = {
                        'schemeCode': vm.schemeCode,
                        'versionNo': vm.versionNo,
                        'effeFromDate': $filter('date')(vm.effFromDate, 'dd-MMM-yyyy')
                    }
                    rbibondtocSharingMaintenanceService.submitVerifyForm(requestData).then(
                        (response) => {
                            vm.loader = false;
                            Swal.fire("Record Verified!", "TOC Sharing Maintenance Verified Successfully", "success");
                            location.href = '#!/rbi_bond_toc_share_maintanance/action/inquiry';
                        },
                        function(error) {
                            vm.loader = false;
                            handleErrorResponse(error)

                        });
               } });

        }

        function getVersionEffFromDateForModify() {
            vm.loader = true;
            var requestData = {
                'schemeCode': vm.schemeCode
            }
            rbibondtocSharingMaintenanceService.getModifySelectSharing(requestData).then(
                (response) => {
                    vm.loader = false;
                    vm.versionNo = response.data.result.versionOut[0].versionNo;
                    vm.effFromDate = new Date(response.data.result.versionOut[0].effectiveFromDate);
                    vm.effFromDate = $filter('date')(vm.effFromDate, 'dd/MM/yyyy');

                },
                function(error) {
                    vm.loader = false;
                    handleErrorResponse(error);
                });
        }

        function getModifySelect() {
            vm.loader = true;
                    getRoundFactList();
            var requestData = {
                'schemeCode': vm.schemeCode,
                'versionNo': vm.versionNo,
                'effeFromDate': $filter('date')(vm.effFromDate, 'dd-MMM-yyyy')
            }
            vm.loader = true;
            rbibondtocSharingMaintenanceService.getModifySelectSharing(requestData).then(
                (response) => {
                    vm.loader = false;
                    vm.all = response.data.result;
                    vm.levelDetailsTocSharingDto = response.data.result.oldNewValueList;
                    //vm.roundOff = vm.levelDetailsTocSharingDto[0].C008.oldValue;

                    if (vm.levelDetailsTocSharingDto)
                    {
                        vm.effFromDate = convertToDateObject(vm.levelDetailsTocSharingDto[0].C004.oldValue);
                    vm.effeFromDates = vm.effFromDate;
                    vm.roundOff = vm.levelDetailsTocSharingDto[0].C008.oldValue;
//                    vm.verifiedStatus = vm.all.verifiedStatus;
                    calculateSumForModify();


                                        				}

                },
                function(error) {
                    vm.loader = false;
                    handleErrorResponse(error);
                    $state.go('rbi_bond_toc_share_maintanance.modify');
                }
            );
        }
function modifySubmit() {
            if ( vm.levelDetailsTocSharingDto != null || vm.submitAction == 'true') {
              var effFromDate = $filter('date')(vm.effFromDate, 'dd-MMM-yyyy');
              var dbStr = $filter('date')(vm.effeFromDates, 'dd-MMM-yyyy');
               if (effFromDate == dbStr) {
                    var titleString = "Record already exists for the effective date";
                    var textString = "Do you wish to continue?Yes/No";
                } else {
                    var titleString = "Are you sure?";
                    var textString = "Do you want to Really Modify this record?";
                }
//                Swal.fire({
//                        title: titleString,
//                        text: textString,
//                        type: "warning",
//                        showCancelButton: true,
//                        cancelButtonClass: "btn-cancel",
//                        confirmButtonClass: "btn-primary",
//                        confirmButtonText: "Yes",
//                        closeOnConfirm: false
//                    },
//
//          function(yesFlg) {
//
//               if(yesFlg){




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
                                                 vm.resultArray = [];
                                                             angular.forEach(vm.levelDetailsTocSharingDto, function(item) {
                                                                 vm.resultArray.push({
                                                                     "id": item.ID.oldValue,
                                                                     "levelId": item.C006.oldValue,
                                                                     "levelName": item.C003.oldValue,
                                                                     "sharingPercentage": item.C007.oldValue
                                                                 });
                                                             })

                                                             let requestData = {
                                                                 'schemeCode': vm.schemeCode,
                                                                 'roundOff': vm.roundOff,
                                                                 'effeFromDate': $filter('date')(vm.effFromDate, 'dd-MMM-yyyy'),
                                                                 'levelDetailsTocSharingDto': vm.resultArray
                                                             }
                                                               calculateSumForModify();
                                                                         if (vm.total != 100) {
                                                                             var msg = 'Sum of sharing percentage should be equal to 100';
                                                                             Swal.fire('Warning!', msg, 'warning');

                                                                         }

                                              rbibondtocSharingMaintenanceService.submitModifyForm(requestData).then(
                                                                 (response) => {
                                                                                                                                  vm.loader = false;
                                                                                                                           						Swal.fire("Record Modified!", " TOC Sharing Maintenance Modified Successfully", "success");

                                                                                                                                                                    location.href = '#!/rbi_bond_toc_share_maintanance/action/inquiry';


                                                                                                                              },
                                                                                                                              function(error) {
                                                                                                                                  vm.loader = false;



                                                                  handleErrorResponse(error);
                                                              });
                                             }else{
                 $state.go('rbi_bond_toc_share_maintanance.modify_detail');
                         }

                    });
            }
        }






        function formatDate(tempDate) {
            return $filter('date')(tempDate, 'dd/MMM/yyyy');
        }


    }
})();