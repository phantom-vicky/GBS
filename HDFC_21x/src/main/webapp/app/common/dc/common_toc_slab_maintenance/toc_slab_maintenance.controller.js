(function() {

    'use strict';

    angular.module('gbmUltraApp').controller('commonTocSlabMaintenanceController',
        commonTocSlabMaintenanceController);

    commonTocSlabMaintenanceController.$inject = ['$scope', '$filter', '$state',
        '$stateParams', '$location', 'commonTocSlabMaintenanceService', 'commonService'
    ];

    function commonTocSlabMaintenanceController($scope, $filter, $state, $stateParams,
        $location, commonTocSlabMaintenanceService, commonService) {

        var vm = this;
        const TABLE_NAME = "T022";

        vm.commonYesNoMetaData = COMMON_YES_NO_METADATA;
        vm.showCancelConfirmation = showCancelConfirmation;

        vm.tocSlabList = [{}];
        vm.disableDelete = false;
        vm.addRange = addRange;
        vm.removeRange = removeRange;
        vm.getDcId = getDcId;
        vm.getVersionNum = getVersionNum;
        vm.submitAddForm = submitAddForm;
        vm.submitVerifyForm = submitVerifyForm;
        vm.submitDeleteForm = submitDeleteForm;
        vm.fetchModuleDetails = fetchModuleDetails;
        vm.getVersionNumModify = getVersionNumModify;
        vm.getModuleName = getModuleName;
        vm.checkRecordExisted = checkRecordExisted;
        vm.getPenionTocList = getPenionTocList;
        vm.getVerifyDeleteSelect = getVerifyDeleteSelect;
        vm.getInquiryVersionTourch = getInquiryVersionTourch;
        vm.getInquirySelect = getInquirySelect;
        vm.modifySelectDetails = modifySelectDetails;
        vm.modifySubmit = modifySubmit;
        vm.getRoundFactList = getRoundFactList;
        getPenionTocList();
        getRoundFactList();

        if ($state.current.name == 'common_toc_slab_maintenance.add_detail') {
            if ($stateParams.obj) {
                vm.moduleId = $stateParams.obj.moduleId;
                vm.versionNo = $stateParams.obj.versionNo;
                vm.effFromDate = $stateParams.obj.effFromDate;

                getLevelRow(2);
                if (vm.error == 1) {
                    $state.go('common_toc_slab_maintenance.add');
                }

            } else {
                $state.go('common_toc_slab_maintenance.add');
            }
        }else if ($state.current.name == 'common_toc_slab_maintenance.modify_detail'){
            if ($stateParams.obj) {
                vm.moduleId = $stateParams.obj.moduleId;
                vm.effFromDate = $stateParams.obj.effFromDate;
                modifySelectDetails();
                //getLevelRow(3);
            } else {
                $state.go('common_toc_slab_maintenance.modify');
            }
        }else if ($state.current.name == 'common_toc_slab_maintenance.verify_detail') {
            if ($stateParams.obj) {
                vm.moduleId = $stateParams.obj.moduleId;

                  getVerifyDeleteSelect();
            } else {
                $state.go('common_toc_slab_maintenance.verify');
            }
        } else if ($state.current.name == 'common_toc_slab_maintenance.delete_detail') {
            if ($stateParams.obj) {
                vm.moduleId = $stateParams.obj.moduleId;

                getVerifyDeleteSelect();
            } else {
                $state.go('common_toc_slab_maintenance.delete');
            }
        } else if ($state.current.name == 'common_toc_slab_maintenance.list_detail') {
            if ($stateParams.obj) {
                vm.moduleId = $stateParams.obj.moduleId;
                vm.versionNo = $stateParams.obj.versionNo;
                vm.effFromDate = $stateParams.obj.effFromDate;

                getInquirySelect();
            } else {
                $state.go('common_toc_slab_maintenance.list');
            }
        }
        function getLevelRow(countRow) {

            if(countRow && countRow <= 2)
            {
                vm.tocSlabList = [];
                for(var i=1; i<=countRow; i++){
                    vm.tocSlabList.push({});
                }
                return vm.tocSlabList;
            }else{
                vm.tocSlabList = '';
            }

        }
        vm.makeOptionsInVisible = function(indexInHierarchy) {
                 for(let i=0;i<vm.tocSlabList.length;i++) {
                    if(indexInHierarchy==i)
                         continue;

                    for(let item of vm.pensionTocList) {
                       if(vm.tocSlabList[i].paymentNature != item.value && vm.tocSlabList[indexInHierarchy].paymentNature == item.value) {
                           angular.element("#" + item.value + i).hide();
                       } else {
                           let flag  = true;
                           for(let levelItem of vm.tocSlabList) {
                             if(item.value == levelItem.paymentNature) {
                               flag = false;
                               break;
                             }
                           }

                           if(flag)
                                angular.element("#" + item.value + i).show();
                       }
                    }
                 }
            }
        //Get Module Name
        function getModuleName() {
           vm.referenceId = 'MOD_FOR_INT';
           var requestData = {
                'referenceId': vm.referenceId
           }
           commonService.getModuleNameDetails(requestData).then(function(response){
              vm.moduleNameList = response.data.result;
                vm.moduleNameDetailsLabelValue = [];
                  angular.forEach(vm.moduleNameList, function(item){
                       vm.moduleNameDetailsLabelValue.push({label: item.subCodeDescription, value: item.subCode})
                  })

            },function(error) {
               handleErrorResponse(error);
             });
        }
        //Get Rounding Factor
        function getRoundFactList(){
                 vm.referenceId = 'ROUNDING_FACTOR';
                 var requestData = {
                      'referenceId': vm.referenceId
                 }
                 commonService.getModuleNameDetails(requestData).then(function(response){
                    vm.roudingFactorList = response.data.result;
                      vm.roundOff = [];
                        angular.forEach(vm.roudingFactorList, function(item){
                             vm.roundOff.push({label: item.subCodeDescription, value: item.subCode})
                        })
                  },function(error) {
                     handleErrorResponse(error);
                   });

            }
        //Check Record already is there or not
        function checkRecordExisted(){
            var requestData = {
                'schemeCode' : vm.moduleId
            };
            commonTocSlabMaintenanceService.checkRecordExisted(requestData).then(function(response){
                if(response.data.message=='SUCCESS')
                 {
                    Swal.fire("Warning!", "Record already added, Do Changes in modify", "warning");
                     $state.go('common_toc_slab_maintenance.modify');
                 }
            },function(error){
                if(error.data.message=='NDF') {
                    $state.go('common_toc_slab_maintenance.add');
                 }else{
                         handleErrorResponse(error);
                 }
            })
        }
        //Get pension TOC Details
        function getPenionTocList(){
              vm.referenceId = 'NATURE_OF_TOC';
              var requestData = {
                   'referenceId': vm.referenceId
              }
              commonService.getModuleNameDetails(requestData).then(function(response){
                 vm.natureOftocList = response.data.result;
                   vm.pensionTocList = [];
                     angular.forEach(vm.natureOftocList, function(item){
                          vm.pensionTocList.push({label: item.subCodeDescription, value: item.subCode})
                     })
                 var countRow = vm.pensionTocList.length;
               },function(error) {
                  handleErrorResponse(error);
                });
        }



        function addRange() {
            if (vm.tocSlabList.length <= 2) {
                vm.tocSlabList.push({});
                vm.disableDelete = true;
            }
        }

        function removeRange() {
            if (vm.tocSlabList.length > 1) {
                vm.lastRow = vm.tocSlabList.length - 1;
                vm.tocSlabList.splice(vm.lastRow, 1);
                if (vm.lastRow == 1) {
                    vm.disableDelete = false;
                }
            } else {
                vm.disableDelete = false;
            }
        }


        function getDcId() {
            vm.searchDcId = '';
            vm.loader = true;
            var requestData = {
                table: 'T004',
                columnList: ["C001", "C002"]
            }

            commonService.genericTableSelect(requestData).then(
                (response) => {
                    vm.loader = false;
                    vm.dcIdList = response.data.result;
                    angular.element('#dcIDModal').modal('toggle');

                }, function(error) {
                   	vm.loader = false;
                   	handleErrorResponse(error);
                });
        }



        function getVersionNumModify() {
            commonTocSlabMaintenanceService.modifyVerifyTocSlab(vm.moduleId, vm.treasuryId).then(
                (response) => {
                    vm.loader = false;
                    vm.versionNo = response.data.result.versionNo;
                }, function(error) {
                   	vm.loader = false;
                   	handleErrorResponse(error);
                });
        }

        function getVersionNum() {
            commonTocSlabMaintenanceService.getVersionNum(vm.module, vm.treasuryId).then(
                (response) => {
                    vm.loader = false;
                    vm.versionNo = response.data.result.versionNo;
                }, function(error) {
                   	vm.loader = false;
                   	handleErrorResponse(error);
                });
        }

        // Modify Select details
        function modifySelectDetails() {
                    vm.loader = true;
                    var requestData = {
                        'schemeCode' : vm.moduleId
                    };
                    commonTocSlabMaintenanceService.modifySelect(requestData).then(
                        (response) => {
                            vm.loader = false;
                                vm.tocSlabList = response.data.result.oldNewValueList;
                                vm.effFromDate= convertToDateObject(vm.tocSlabList[0].C003.oldValue);
                                vm.effFromDates = vm.effFromDate;
                                vm.versionNo=vm.tocSlabList[0].C002.oldValue;

                                angular.forEach(vm.moduleNameList, function(item){
                                     if(vm.moduleId == item.subCode){
                                        vm.tocSlabListEffctDate = response.data.result.oldNewValueList[0].C003.oldValue;
                                    }else{
                                        vm.tocSlabListEffctDate = '';
                                    }
                                })
                                vm.auditInfo = response.data.result;
                                if (vm.tocSlabList.length > 1) {
                                    vm.disableDelete = true;
                                }

                        }, function(error) {
                           	vm.loader = false;
                           	handleErrorResponse(error);
                           	$state.go('common_toc_slab_maintenance.modify');
                           	vm.moduleId ='';
                        });
                }
        /* Verify/Delete Select Details */
        function getVerifyDeleteSelect() {
            vm.loader = true;
            var requestData = {
                'schemeCode' : vm.moduleId
            };
            commonTocSlabMaintenanceService.VerifyDeleteTocSlab(requestData).then(
                (response) => {
                    vm.loader = false;
                    vm.auditInfo = response.data.result;
                    vm.tocSlabList = response.data.result.oldNewValueList;
                    vm.effDate=vm.tocSlabList[0].C003.oldValue;
                    vm.versionNo=vm.tocSlabList[0].C002.oldValue;

                    vm.verifySubmitList = response.data.result.oldNewValueList[0];

                },function(error) {
                      vm.loader = false;
                      handleErrorResponse(error);
                      $state.go('common_toc_slab_maintenance.list');
                  });
        }

        /* Add Submit */
        function submitAddForm() {
            if (vm.tocSlabList.length == 2 || vm.submitAction == 'true') {
                Swal.fire({
                        title: "Are you sure?",
                        text: "Do you want to Really Add this record?",
                        type: "warning",
                        showCancelButton: true,
                        cancelButtonClass: "btn-cancel",
                        confirmButtonClass: "btn-primary",
                        confirmButtonText: "Yes",
                        closeOnConfirm: false
                    },

                    function() {

                            vm.loader = true;
                            let requestData = {
                                'schemeCode': vm.moduleId,
                                'versionNo': vm.versionNo,
                                'effeFromDate': formatDate(vm.effFromDate),
                                'slabSubDetailsDTOList': vm.tocSlabList
                                //$filter('date')(vm.effFromDate, "dd-MMM-yyyy")
                            }
                            commonTocSlabMaintenanceService.submitAddForm(requestData).then(
                                (response) => {
                                    vm.loader = false;
                                    Swal.fire("Added!","Details Added Successfully","success");
                                    location.href = '#!/common_toc_slab_maintenance/action/inquiry';
                                }, handleErrorResponse);

                    }, function(error) {
                        vm.loader = false;
                        handleErrorResponse(error);
                    });
            }else{
                Swal.fire("Warning!","Should be Allow 3 Records","warning");
            }
        }

        /* Submit Modify*/
        function modifySubmit() {
            if (vm.tocSlabList.length == 2 || vm.submitAction == 'true') {
                if(vm.effFromDates.getTime() == vm.effFromDate.getTime()){
                    var titleString = "Record already exists for the effective date";
                    var textString = "Do you wish to continue?Yes/No";
                }else{
                    var titleString = "Are you sure?";
                    var textString = "Do you want to Really Modify this record?";
                }
                Swal.fire({
                        title: titleString,
                        text: textString,
                        type: "warning",
                        showCancelButton: true,
                        cancelButtonClass: "btn-cancel",
                        confirmButtonClass: "btn-primary",
                        confirmButtonText: "Yes",
                        closeOnConfirm: false
                    },

                    function(yesFlg) {

                        if(yesFlg){
                                vm.loader = true;
                                vm.resultArray = [];
                                angular.forEach(vm.tocSlabList, function(item){
                                     item.C003.oldValue = formatDate(vm.effFromDate);
                                     item.C004.oldValue = formatDate(convertToDateObject(item.C004.newValue));
                                     if(item.C005.oldValue == 'Y')
                                     {
                                     if(item.C011){
                                        item.C011.oldValue='';
                                     }

                                     }
                                     item.C001.oldValue= vm.moduleId;
                                     vm.resultArray.push(convertToColumnValuesArrayList(item))
                                })
                                    let requestData = {
                                        'schemeCode': vm.moduleId,
                                        'versionNo': vm.versionNo,
                                        'effeFromDate': formatDate(vm.effFromDate),
                                        'columnValueDetailsList': vm.resultArray
                                    }
                                    commonTocSlabMaintenanceService.submitModifyForm(requestData).then(
                                        (response) => {
                                            vm.loader = false;
                                            Swal.fire("Modified!","Details Modified Successfully","success");
                                            location.href = '#!/common_toc_slab_maintenance/action/inquiry';
                                        }, function(error) {
                                             vm.loader = false;
                                             handleErrorResponse(error);
                                         });
                        }else{
                            $state.go('common_toc_slab_maintenance.modify');
                        }

                    });
            }
        }

const convertToColumnValuesArrayList = (objectToBeConvert) => {
  if(typeof(objectToBeConvert) === "object") {
     let resultArray = [];
     for(let key of Object.keys(objectToBeConvert)) {
        if(objectToBeConvert[key])
            resultArray.push({colName : key,value : objectToBeConvert[key].oldValue})
     }

     return resultArray;
  } else {
    throw "Only objects can be converted"
  }
}

        /* Verify Submit */
        function submitVerifyForm() {
            Swal.fire({
                    title: "Are you sure?",
                    text: "Do you want to Really Verify this record?",
                    type: "warning",
                    showCancelButton: true,
                    cancelButtonClass: "btn-cancel",
                    confirmButtonClass: "btn-primary",
                    confirmButtonText: "Yes",
                    closeOnConfirm: false
                },

                function() {
                    vm.loader = true;
                    let requestData = {
                        'schemeCode': vm.moduleId,
                        'versionNo': vm.versionNo,
                        'effeFromDate': formatDate(convertToDateObject(vm.effDate))
                    }
                    commonTocSlabMaintenanceService.submitVerifyForm(requestData).then(
                        (response) => {
                            vm.loader = false;
                            Swal.fire("Verified!","Details Verified Successfully","success");
                            location.href = '#!/common_toc_slab_maintenance/action/inquiry';
                        }, handleErrorResponse);
                }, function(error) {
                   	vm.loader = false;
                   	handleErrorResponse(error);
                });

        }

        /* Delete Submit */
        function submitDeleteForm() {
            Swal.fire({
                    title: "Are you sure?",
                    text: "Do you want to Really Delete this record?",
                    type: "warning",
                    showCancelButton: true,
                    cancelButtonClass: "btn-cancel",
                    confirmButtonClass: "btn-primary",
                    confirmButtonText: "Yes",
                    closeOnConfirm: false
                },

                function() {
                    vm.loader = true;
                    let requestData = {
                        'schemeCode': vm.moduleId,
                        'versionNo': vm.versionNo,
                        'effeFromDate': formatDate(convertToDateObject(vm.effDate))
                    }
                    commonTocSlabMaintenanceService.submitDeleteForm(requestData).then(
                        (response) => {
                            vm.loader = false;
                            Swal.fire("Deleted!","Details Deleted Successfully","success");
                            location.href = '#!/common_toc_slab_maintenance/action/inquiry';
                        }, handleErrorResponse);
                }, function(error) {
                   	vm.loader = false;
                   	handleErrorResponse(error);
                });

        }

        // Cancel Confirmation
        function showCancelConfirmation(state) {
            Swal.fire({
                    title: "Confirm Cancel",
                    text: "Do you really want to cancel?",
                    type: "warning",
                    showCancelButton: true,
                    cancelButtonClass: "btn-cancel",
                    confirmButtonClass: "btn-danger",
                    confirmButtonText: "Yes",
                    cancelButtonText: "No",
                    closeOnConfirm: true
                },
                function() {
                    $state.go(state);
                });
        }



        // Handle error response
        function handleErrorResponse(response) {
            vm.loader = false;
            var msg = response.data.message ? response.data.message :
                "Server Error";
            Swal.fire("Error!", msg, "error");
        }
        /* Table Sorting */
        vm.column = 'value';
        // sort ordering (Ascending or Descending). Set true for desending
        vm.reverse = false;

        // called on header click
        vm.sortColumn = function(col) {
            vm.column = col;
            if (vm.reverse) {
                vm.reverse = false;
                vm.reverseclass = 'arrow-up';
            } else {
                vm.reverse = true;
                vm.reverseclass = 'arrow-down';
            }
        };

        // remove and change class
        vm.sortClass = function(col) {
            if (vm.column == col) {
                if (vm.reverse) {
                    return 'arrow-down';
                } else {
                    return 'arrow-up';
                }
            } else {
                return '';
            }
        }

        //Get Level Name
        function fetchModuleDetails() {
            vm.loader = true;

            var requestData = {
                'referenceId': 'PENSION'
            }

            commonService.getModuleDetails(requestData).then(
                function(response) {
                    vm.loader = false;
                    vm.moduleList = response.data.result;
                }
            );
        }
        //Get Inquiry Version No Tourch Details
        function getInquiryVersionTourch() {
              vm.loader = true;
              vm.searchSolId = '';
                 var requestData = {
                    'schemeCode': vm.moduleId,
                    'versionNo': "",
                    'effeFromDate': ""
                 }
                   commonTocSlabMaintenanceService.getInquirySelectDetails(requestData).then(function(response){
                       vm.loader=false;
                       vm.versionNoList = response.data.result.effeFromDateList;
                       angular.forEach(vm.versionNoList, function (item) {
                            if(item.verifiedStatus=='Y'){
                                item.verifiedStatus = item.verifiedStatus = 'Yes';
                            }else{
                                item.verifiedStatus = item.verifiedStatus = 'No';
                            }

                       });
                       angular.element('#versionNoModal').modal('toggle');
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
                    'schemeCode': vm.moduleId,
                    'versionNo': vm.versionNo,
                    'effeFromDate': vm.effeFromDate
                 }
                   commonTocSlabMaintenanceService.getInquirySelectDetails(requestData).then(function(response){
                       vm.loader = false;
                       vm.auditInfo = response.data.result;
                       vm.tocSlabList = response.data.result.oldNewValueList;
                       vm.verionNoDetails = response.data.result.oldNewValueList[0];
                   },function(error) {
                       handleErrorResponse(error);
                       vm.loader = false;
                   });
        }
        // format date from dd/MM/yyyy to yyyy-MM-dd
         function formatDate(tempDate){
                 return $filter('date')(tempDate, "dd-MMM-yyyy");
         }

     vm.dateValidationForModify = function(){
        commonService.getSolDate()
        .then(
            function(response) {
                vm.solDate = response.data.solDate;
                 vm.dateOptions = {
                          minDate : vm.solDate
                          }
            }, handleErrorResponse
        );

     }


    }
})();