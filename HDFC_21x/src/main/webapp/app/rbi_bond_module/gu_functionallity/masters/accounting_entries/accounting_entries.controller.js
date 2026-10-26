(function () {
    "use strict";

    angular.module("gbmUltraApp").controller("accountingEntriesController", accountingEntriesController);

    accountingEntriesController.$inject = [
        "$scope",
        "$filter",
        "$state",
        "$rootScope",
        "$stateParams",
        "$location",
        "$timeout",
        "accountingEntriesService",
        "commonService",
    ];

    function accountingEntriesController($scope, $filter, $state, $rootScope, $stateParams, $location, $timeout,
        accountingEntriesService, commonService) {
        var vm = this;
// MyCode
        vm.modifySubmit = modifySubmit;
        vm.deleteSubmit = deleteSubmit;
        vm.rejectReason = rejectReason;
        vm.getRemarksList = getRemarksList;

        vm.getModuleName = getModuleName;
        vm.getInquiryModuleName = getInquiryModuleName;
        vm.getInquiryVersionNo = getInquiryVersionNo;
        vm.getModifyModuleName = getModifyModuleName;
        vm.getVerifyModuleName = getVerifyModuleName;

        vm.getInquiryDetails = getInquiryDetails;
        vm.getVerifyDetails = getVerifyDetails;
        vm.getModifyDetails = getModifyDetails;

        vm.addSubmit = addSubmit;
        vm.verifySubmit = verifySubmit;
        vm.fromDateVal = fromDateVal;
        vm.toDateVal = toDateVal;
        vm.formatDate= formatDate;



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


        if ($state.current.name == "accounting_entries.modify_detail") {
            if ($stateParams.obj) {
                vm.schemeCode = $stateParams.obj.schemeCode;
                vm.schemeName = $stateParams.obj.schemeName;
                vm.effectiveFromDate = $stateParams.obj.effectiveFromDate;

                getModifyDetails();
            }
        } else if ($state.current.name == "accounting_entries.verify_detail") {
            if ($stateParams.obj) {
                vm.schemeCode = $stateParams.obj.schemeCode;
                vm.schemeName = $stateParams.obj.schemeName;
                vm.effectiveFromDate = $stateParams.obj.effectiveFromDate;
                vm.pkColumnValues = $stateParams.obj.pkColumnValues;

                getVerifyDetails();
            }
        } else if ($state.current.name == "accounting_entries.inquiry_detail") {
            if ($stateParams.obj) {
                vm.schemeCode = $stateParams.obj.schemeCode;
                vm.schemeName = $stateParams.obj.schemeName;
                vm.effectiveFromDate = $stateParams.obj.effectiveFromDate;
                vm.versionNo = $stateParams.obj.versionNo;

                getInquiryDetails();
            }
        }

        function getModuleName() {
            vm.loader = true;
            var requestData = {};
            accountingEntriesService.getModuleName(requestData).then(
                function (response) {
                    vm.loader = false;
                    vm.schemeList = response.data.result;
                    angular.element('#schemeModel').modal('toggle');
                }, function (error) {
                    vm.loader = false;
                    handleErrorResponse(error);
                });
        }

        function getInquiryModuleName() {
            vm.loader = true;
            var requestData = {};
            accountingEntriesService.getModuleName(requestData).then(
                function (response) {
                    vm.loader = false;
                    vm.schemeList = response.data.result;
                    angular.element('#schemeModel').modal('toggle');
                }, function (error) {
                    vm.loader = false;
                    handleErrorResponse(error);
                });
        }

        function getInquiryVersionNo() {
            vm.loader = true;
            var requestData = {
                "schemeCode": vm.schemeCode,
            };
            accountingEntriesService.getInquiryVersionNo(requestData).then(
                function (response) {
                    vm.loader = false;
                    vm.versionNoList = response.data.result;
                    angular.element('#versionModel').modal('toggle');
                }, function (error) {
                    vm.loader = false;
                    handleErrorResponse(error);
                });
        }

        function getModifyModuleName() {
            vm.loader = true;
            var requestData = {};
            accountingEntriesService.getModifyModuleName(requestData).then(
                function (response) {
                    vm.loader = false;
                    vm.schemeList = response.data.result;
                    angular.element('#schemeModel').modal('toggle');
                }, function (error) {
                    vm.loader = false;
                    handleErrorResponse(error);
                });
        }

        function getVerifyModuleName() {
            vm.loader = true;
            var requestData = {};
            accountingEntriesService.getVerifyModuleName(requestData).then(
                function (response) {
                    vm.loader = false;
                    vm.schemeList = response.data.result;
                    vm.effectiveFromDate = response.data.result.effectiveFromDate;
                    angular.element('#schemeModel').modal('toggle');
                }, function (error) {
                    vm.loader = false;
                    handleErrorResponse(error);
                });
        }

        function getInquiryDetails(flag) {
            vm.loader = true;
            var requestData = {
                "schemeCode": vm.schemeCode,
                "versionNo": vm.versionNo,
            };
            accountingEntriesService.inquiryDetails(requestData).then(function (response) {
                vm.loader = false;
                vm.accountingEntries = response.data.result.accountingEntriesOldNewValueModel;
                vm.audit = response.data.result;
                vm.previousOperationType = response.data.result.previousOperationType;
                console.log('previousOperationType', vm.previousOperationType);

                vm.getOldValue = function (code) {
                    return vm.accountingEntries?.[code]?.oldValue || '';
                };
                vm.getNewValue = function (code) {
                    return vm.accountingEntries?.[code]?.newValue || '';
                };

            }, function (error) {
                vm.loader = false;
                handleErrorResponse(error);
                $state.go('accounting_entries.inquiry');
            });
        }

        function getModifyDetails() {
            vm.loader = true;
            var requestData = {
                "moduleId": vm.schemeCode,
            };
            accountingEntriesService.getModifyDetails(requestData).then(function (response) {
                vm.loader = false;
                var recordStatus = response.data.message;
                vm.record = response.data;
                vm.pkColumnValues = response.data.result.pkColumnValues;
                console.log('pkcoliu', vm.pkColumnValues);

                vm.accountingEntries = response.data.result;
                console.log('vm.accountingEntries', vm.accountingEntries);

                vm.getOldValue = function (code) {
                    return vm.accountingEntries?.accountingEntriesOldNewValueModel?.[code]?.oldValue || '';
                };
                vm.getNewValue = function (code) {
                    return vm.accountingEntries?.accountingEntriesOldNewValueModel?.[code]?.newValue || '';
                };

                if (recordStatus == 'SUCCESS') {
                    $state.go("accounting_entries.modify_detail");
                    vm.suspAccCmsDbt = vm.getOldValue('C010');
                    vm.suspAccInetDbt = vm.getOldValue('C011');
                    vm.vndBrkrAccCdt = vm.getOldValue('C122');
                    vm.bondSchemeEffectiveFromDate = vm.getOldValue('C003');
                    vm.bondSchemeEffectiveToDate = vm.getOldValue('C004');
                    vm.parameterEffectiveFromDate = vm.getOldValue('C005');
                    vm.suspAccCmsDbt = vm.getOldValue('C010');
                    vm.suspAccInetDbt = vm.getOldValue('C011');
                    vm.rrbPayCr = vm.getOldValue('C012');
                    vm.rrbPayDbt = vm.getOldValue('C014');
                    vm.rbiClgNostroRbi = vm.getOldValue('C016');
                    vm.rrbRbiNagpBrkgReceDbt1 = vm.getOldValue('C018');
                    vm.remitSuspAccCrd1 = vm.getOldValue('C020');
                    vm.rrbRbiNagpBrkgReceDbt2 = vm.getOldValue('C022');
                    vm.remitSuspAccCrd2 = vm.getOldValue('C024');
                    vm.rrbRbiNagpBrkgReceDbt3 = vm.getOldValue('C026');
                    vm.remitSuspAccCrd3 = vm.getOldValue('C028');
                    vm.brkgIncAccRemSuspAccDbt1 = vm.getOldValue('C030');
                    vm.brkgErnRbiBondCrd1 = vm.getOldValue('C032');
                    vm.brkgIncAccRemSuspAccDbt2 = vm.getOldValue('C034');
                    vm.brkgErnRbiBondCrd2 = vm.getOldValue('C036');
                    vm.gstLiabRemSuspAccDbt = vm.getOldValue('C038');
                    vm.rspctvGstAccCrd = vm.getOldValue('C040');
                    vm.gstLiabRrbRbiNagpReceAccDbt = vm.getOldValue('C042');
                    vm.gstLiabRemSuspAccCdt = vm.getOldValue('C044');
                    vm.hndCmmRemSusAccDbt = vm.getOldValue('C046');
                    vm.cmmEarnCdt = vm.getOldValue('C048');
                    vm.gstLiabRemeSuspAccDbt2 = vm.getOldValue('C050');
                    vm.gstLiabRspGSTAccCrd1 = vm.getOldValue('C052');
                    vm.gstLiabRrbRbiNgpBrkgAccDbt = vm.getOldValue('C054');
                    vm.gstLiabRemeSuspAccCdt2 = vm.getOldValue('C056');
                    vm.rvrsRbiClgDbt2 = vm.getOldValue('C058');
                    vm.rvrsRrrbRbiNgpBrkgReceAccCrd = vm.getOldValue('C060');
                    vm.glRbiClgDbt = vm.getOldValue('C062');
                    vm.glRrbRbiNgpBrkgAccCdt = vm.getOldValue('C064');
                    vm.intRcvAccDbt = vm.getOldValue('C066');
                    vm.minorDiffRndDbtCdt = vm.getOldValue('C068');
                    vm.tdsIntScsCdt = vm.getOldValue('C070');
                    vm.rbiBondsTrnstAccCdt1 = vm.getOldValue('C072');
                    vm.ecsSttlAccCdt = vm.getOldValue('C074');
                    vm.rbiBondsTrnstAccCdt2 = vm.getOldValue('C076');
                    vm.cmsDisbCtrlCltFndCrd = vm.getOldValue('C078');
                    vm.rbiBdsTrnstAccDbt = vm.getOldValue('C080');
                    vm.ecsSettlAccDbt = vm.getOldValue('C082');
                    vm.rbiBondsTrnstAccDbt3 = vm.getOldValue('C084');
                    vm.cmsDisbCtrlCltFndDbt = vm.getOldValue('C086');
                    vm.custAccCdt = vm.getOldValue('C088');
                    vm.rvrsRbiClgDbt3 = vm.getOldValue('C090');
                    vm.intRcvbAccCdt = vm.getOldValue('C092');
                    vm.prpRcvbAccDbt = vm.getOldValue('C094');
                    vm.intRcvbAccDbt = vm.getOldValue('C096');
                    vm.cmsDisbCtrlCliFndCdt = vm.getOldValue('C098');
                    vm.rbiBdsTrnstAccCdt = vm.getOldValue('C100');
                    vm.tdsSec193Cdt = vm.getOldValue('C102');
                    vm.cusCASAAccCdt = vm.getOldValue('C104');
                    vm.rbiTrstAccDbt = vm.getOldValue('C106');
                    vm.cmsDisbCtrlFndDbt = vm.getOldValue('C108');
                    vm.cusAccCdt = vm.getOldValue('C110');
                    vm.rbiClgDbt4 = vm.getOldValue('C112');
                    vm.prpRcvbAccCdt = vm.getOldValue('C114');
                    vm.intRcvbAccCdt2 = vm.getOldValue('C116');
                    vm.brkgExpRbiBdsRrbDbt = vm.getOldValue('C118');
                    vm.brkgExpRbiBdsRcm = vm.getOldValue('C120');
                    vm.vndBrkrAccCdt = vm.getOldValue('C122');
                    // vm.effectiveFromDate = vm.getOldValue('C123');
                }
                else {
//                    Swal.fire(
//                        {
//                            title: "Record already exists for the effective date",
//                            text: "Do you wish to continue?",
//                            type: "warning",
//                            showCancelButton: true,
//                            cancelButtonClass: "btn-cancel",
//                            confirmButtonClass: "btn-danger",
//                            confirmButtonText: "Yes",
//                            cancelButtonText: "No",
//                            closeOnConfirm: true,
//                        },
//                        function () {
//                            vm.loader = true;
//                            location.href = "#!/accounting_entries/action/modify/detail";
//                        },
//                        function (error) {
//                            vm.loader = false;
//                            handleErrorResponse(error);
//                            $state.go("accounting_entries.modify");
//                        }
//                    );



                    Swal.fire({
                        title: "Record already exists for the effective date",
                        text: "Do you wish to continue?",
                        icon: "warning",
                        showCancelButton: true,
                        confirmButtonText: "Yes",
                        cancelButtonText: "No",
                        customClass: {
                            confirmButton: "btn-danger",
                            cancelButton: "btn-cancel"
                        },
                        buttonsStyling: false
                    }).then((result) => {
                        if (result.isConfirmed) {
                            vm.loader = true;
                            location.href = "#!/accounting_entries/action/modify/detail";
                        } else if (result.dismiss === Swal.DismissReason.cancel) {
                            vm.loader = false;
                            $state.go("accounting_entries.modify");
                        }
                    })








                }
            },
                function (error) {
                    vm.loader = false;
                    handleErrorResponse(error);
                    $state.go("accounting_entries.modify");
                }
            );
        }

        function getVerifyDetails() {
            vm.loader = true;
            var requestData = {
                "moduleId": vm.schemeCode,
            };
            accountingEntriesService.getVerifyDetails(requestData).then((response) => {
                vm.loader = false;

                vm.verifyDetailsResponse = response.data.message;
                vm.verifyDetailsList = response.data.result;
                vm.pkColumnValues = response.data.result.pkColumnValues;
                console.log('pkColumnValues', vm.pkColumnValues)

                vm.accountingEntries = response.data.result.accountingEntriesOldNewValueModel;
                console.log('accountingEntries', vm.accountingEntries)

                vm.getDetails = response.data.result;
                vm.previousOperationType = response.data.result.previousOperationType;
                console.log('previousOperationType', vm.previousOperationType)

                vm.getOldValue = function (code) {
                    return vm.accountingEntries?.[code]?.oldValue || '';
                };
                vm.getNewValue = function (code) {
                    return vm.accountingEntries?.[code]?.newValue || '';
                };
            }, function (error) {
                vm.loader = false;
                handleErrorResponse(error);
                $state.go('accounting_entries.verify');
            });
        }

        function addSubmit() {
//            Swal.fire({
//                title: "Are you sure?",
//                text: "Do you want to add this record?",
//                type: "warning",
//                showCancelButton: true,
//                cancelButtonClass: "btn-cancel",
//                confirmButtonClass: "btn-primary",
//                confirmButtonText: "Yes",
//                cancelButtonText: "Cancel",
//                closeOnConfirm: false
//            },



                    Swal.fire({
                      title: "Are you sure?",
                      text: "Do you want to add this record?",
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



               // function () {
                    vm.loader = true;
                    var requestData = {
                        pageType: 'add',
                        schemeCode: vm.schemeCode,
                        accountingEntriesListModel: {}
                    };
                    var keyMappings = {
                        "C003": "bondSchemeEffectiveFromDate",
                        "C004": "bondSchemeEffectiveToDate",
                        "C005": "parameterEffectiveFromDate",
                        "C010": "suspAccCmsDbt",
                        "C011": "suspAccInetDbt",
                        "C012": "rrbPayCr",
                        "C014": "rrbPayDbt",
                        "C016": "rbiClgNostroRbi",
                        "C018": "rrbRbiNagpBrkgReceDbt1",
                        "C020": "remitSuspAccCrd1",
                        "C022": "rrbRbiNagpBrkgReceDbt2",
                        "C024": "remitSuspAccCrd2",
                        "C026": "rrbRbiNagpBrkgReceDbt3",
                        "C028": "remitSuspAccCrd3",
                        "C030": "brkgIncAccRemSuspAccDbt1",
                        "C032": "brkgErnRbiBondCrd1",
                        "C034": "brkgIncAccRemSuspAccDbt2",
                        "C036": "brkgErnRbiBondCrd2",
                        "C038": "gstLiabRemSuspAccDbt",
                        "C040": "rspctvGstAccCrd",
                        "C042": "gstLiabRrbRbiNagpReceAccDbt",
                        "C044": "gstLiabRemSuspAccCdt",
                        "C046": "hndCmmRemSusAccDbt",
                        "C048": "cmmEarnCdt",
                        "C050": "gstLiabRemeSuspAccDbt2",
                        "C052": "gstLiabRspGSTAccCrd1",
                        "C054": "gstLiabRrbRbiNgpBrkgAccDbt",
                        "C056": "gstLiabRemeSuspAccCdt2",
                        "C058": "rvrsRbiClgDbt2",
                        "C060": "rvrsRrrbRbiNgpBrkgReceAccCrd",
                        "C062": "glRbiClgDbt",
                        "C064": "glRrbRbiNgpBrkgAccCdt",
                        "C066": "intRcvAccDbt",
                        "C068": "minorDiffRndDbtCdt",
                        "C070": "tdsIntScsCdt",
                        "C072": "rbiBondsTrnstAccCdt1",
                        "C074": "ecsSttlAccCdt",
                        "C076": "rbiBondsTrnstAccCdt2",
                        "C078": "cmsDisbCtrlCltFndCrd",
                        "C080": "rbiBdsTrnstAccDbt",
                        "C082": "ecsSettlAccDbt",
                        "C084": "rbiBondsTrnstAccDbt3",
                        "C086": "cmsDisbCtrlCltFndDbt",
                        "C088": "custAccCdt",
                        "C090": "rvrsRbiClgDbt3",
                        "C092": "intRcvbAccCdt",
                        "C094": "prpRcvbAccDbt",
                        "C096": "intRcvbAccDbt",
                        "C098": "cmsDisbCtrlCliFndCdt",
                        "C100": "rbiBdsTrnstAccCdt",
                        "C102": "tdsSec193Cdt",
                        "C104": "cusCASAAccCdt",
                        "C106": "rbiTrstAccDbt",
                        "C108": "cmsDisbCtrlFndDbt",
                        "C110": "cusAccCdt",
                        "C112": "rbiClgDbt4",
                        "C114": "prpRcvbAccCdt",
                        "C116": "intRcvbAccCdt2",
                        "C118": "brkgExpRbiBdsRrbDbt",
                        "C120": "brkgExpRbiBdsRcm",
                        "C122": "vndBrkrAccCdt",
                        "C123": "effectiveFromDate",
                    };

                    Object.keys(keyMappings).forEach(key => {
                        let fieldName = keyMappings[key];
                        let value = vm[fieldName];
                        if (value !== undefined && value !== '') {
                            requestData.accountingEntriesListModel[key] = value;

                            requestData.accountingEntriesListModel[key] = (key === "C123" && value instanceof Date)
                                        ? vm.formatDate(value)
                                        : value;
                        }
                    });
                    console.log("Final Request Data:", requestData);

                    accountingEntriesService.addAccountEntriesValues(requestData).then((response) => {
                        vm.loader = false;
                        vm.addResultResponse = response.data.message;
                        if (vm.addResultResponse == 'SUCCESS') {
                            console.log('in service call if')
                            Swal.fire("Success!", "Office Accounts Added Successfully ", "success");
                            location.href = '#!/accounting_entries/action/inquiry';
                        }
                    }, function (error) {
                        vm.loader = false;
                        handleErrorResponse(error);
                        $state.go('accounting_entries.inquiry');
                    });
                    }
                });
        }

function formatDate(date) {
    if (!date) return '';

        const day = String(date.getDate()).padStart(2, '0');
        const month = date.toLocaleString('en-GB', { month: 'short' }).toUpperCase(); // Get month in uppercase
        const year = date.getFullYear();

        return `${day}-${month}-${year}`;
}

        function modifySubmit() {
//            Swal.fire({
//                title: "Are you sure?",
//                text: "Do you want to modify this record?",
//                type: "warning",
//                showCancelButton: true,
//                cancelButtonClass: "btn-cancel",
//                confirmButtonClass: "btn-primary",
//                confirmButtonText: "Yes",
//                cancelButtonText: "Cancel",
//                closeOnConfirm: false
//            },
//                function () {

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
                    var requestData = {
                        pkColumnValues: vm.pkColumnValues,
                        accountingEntriesListModel: {}
                    };
                    var keyMappings = {
                        "C003": "bondSchemeEffectiveFromDate",
                        "C004": "bondSchemeEffectiveToDate",
                        "C005": "parameterEffectiveFromDate",
                        "C010": "suspAccCmsDbt",
                        "C011": "suspAccInetDbt",
                        "C012": "rrbPayCr",
                        "C014": "rrbPayDbt",
                        "C016": "rbiClgNostroRbi",
                        "C018": "rrbRbiNagpBrkgReceDbt1",
                        "C020": "remitSuspAccCrd1",
                        "C022": "rrbRbiNagpBrkgReceDbt2",
                        "C024": "remitSuspAccCrd2",
                        "C026": "rrbRbiNagpBrkgReceDbt3",
                        "C028": "remitSuspAccCrd3",
                        "C030": "brkgIncAccRemSuspAccDbt1",
                        "C032": "brkgErnRbiBondCrd1",
                        "C034": "brkgIncAccRemSuspAccDbt2",
                        "C036": "brkgErnRbiBondCrd2",
                        "C038": "gstLiabRemSuspAccDbt",
                        "C040": "rspctvGstAccCrd",
                        "C042": "gstLiabRrbRbiNagpReceAccDbt",
                        "C044": "gstLiabRemSuspAccCdt",
                        "C046": "hndCmmRemSusAccDbt",
                        "C048": "cmmEarnCdt",
                        "C050": "gstLiabRemeSuspAccDbt2",
                        "C052": "gstLiabRspGSTAccCrd1",
                        "C054": "gstLiabRrbRbiNgpBrkgAccDbt",
                        "C056": "gstLiabRemeSuspAccCdt2",
                        "C058": "rvrsRbiClgDbt2",
                        "C060": "rvrsRrrbRbiNgpBrkgReceAccCrd",
                        "C062": "glRbiClgDbt",
                        "C064": "glRrbRbiNgpBrkgAccCdt",
                        "C066": "intRcvAccDbt",
                        "C068": "minorDiffRndDbtCdt",
                        "C070": "tdsIntScsCdt",
                        "C072": "rbiBondsTrnstAccCdt1",
                        "C074": "ecsSttlAccCdt",
                        "C076": "rbiBondsTrnstAccCdt2",
                        "C078": "cmsDisbCtrlCltFndCrd",
                        "C080": "rbiBdsTrnstAccDbt",
                        "C082": "ecsSettlAccDbt",
                        "C084": "rbiBondsTrnstAccDbt3",
                        "C086": "cmsDisbCtrlCltFndDbt",
                        "C088": "custAccCdt",
                        "C090": "rvrsRbiClgDbt3",
                        "C092": "intRcvbAccCdt",
                        "C094": "prpRcvbAccDbt",
                        "C096": "intRcvbAccDbt",
                        "C098": "cmsDisbCtrlCliFndCdt",
                        "C100": "rbiBdsTrnstAccCdt",
                        "C102": "tdsSec193Cdt",
                        "C104": "cusCASAAccCdt",
                        "C106": "rbiTrstAccDbt",
                        "C108": "cmsDisbCtrlFndDbt",
                        "C110": "cusAccCdt",
                        "C112": "rbiClgDbt4",
                        "C114": "prpRcvbAccCdt",
                        "C116": "intRcvbAccCdt2",
                        "C118": "brkgExpRbiBdsRrbDbt",
                        "C120": "brkgExpRbiBdsRcm",
                        "C122": "vndBrkrAccCdt",
                        "C123": "effectiveFromDate",
                    };

                    Object.keys(keyMappings).forEach(key => {
                        let fieldName = keyMappings[key];
                        let value = vm[fieldName];
                        console.log('key', fieldName);
                        console.log('value', value);
                        if (value !== undefined && value !== '') {
                            requestData.accountingEntriesListModel[key] = value;
                        }
                    });
                    console.log("Final Request Data:", requestData);

                    accountingEntriesService.modifySubmit(requestData).then((response) => {
                        vm.loader = false;
                        vm.modifyResultResponse = response.data.message;
                        if (vm.modifyResultResponse == 'SUCCESS') {
                            Swal.fire("Success!", "Office Accounts Modified Successfully ", "success");
                            location.href = '#!/accounting_entries/action/inquiry';
                        }
                    }, function (error) {
                        vm.loader = false;
                        handleErrorResponse(error);
                        $state.go('accounting_entries.inquiry');
                    });

                    }
                });
        }

        function verifySubmit() {
//            Swal.fire({
//                title: "Are you sure?",
//                text: "Do you want to Really Verify this record?",
//                type: "warning",
//                showCancelButton: true,
//                cancelButtonClass: "btn-cancel",
//                confirmButtonClass: "btn-primary",
//                confirmButtonText: "Yes",
//                closeOnConfirm: false
//            },
//                function () {



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
                        "pkColumnValues": vm.pkColumnValues
                    };
                    accountingEntriesService.verifySubmit(requestData).then(function (response) {
                        vm.loader = false;

                        vm.responseResult = response.data.result;
                        var message = "Accounting Entries Verified Successfully";
                        Swal.fire("Success", message, "success");

                        $state.go('accounting_entries.inquiry');

                    }, function (error) {
                        vm.loader = false;
                        handleErrorResponse(error);
                        $state.go('accounting_entries.inquiry');
                    });
                    }
                });
        }

        function deleteSubmit(rejectionRemarks) {
//            Swal.fire({
//                title: "Are you sure?",
//                text: "Do you want to Really Reject this record?",
//                type: "warning",
//                showCancelButton: true,
//                cancelButtonClass: "btn-cancel",
//                confirmButtonClass: "btn-primary",
//                confirmButtonText: "Yes",
//                closeOnConfirm: false
//            }, function () {




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
                vm.loader = true;
                var requestData = {
                    "pkColumnValues": vm.pkColumnValues,
                    "rejectionRemarks": vm.rejectionRemarks
                };

                accountingEntriesService.deleteSubmit(requestData).then(function (response) {
                    vm.loader = false;
                    console.log('pkColumnValues', vm.pkColumnValues);
                    console.log('rejectionRemarks', vm.rejectionRemarks);
                    Swal.fire("Success", "Rejected Successfully", "success");
                    $state.go('accounting_entries.inquiry');
                }, function (error) {
                    vm.loader = false;
                    handleErrorResponse(error);
                    $state.go('accounting_entries.inquiry');
                });

                }
            });
        }

        function rejectReason() {
            angular.element('#permissionMappingDelete').modal('toggle');
        }

        function getRemarksList() {
            vm.referenceId = '056';
            var requestData = { 'referenceId': vm.referenceId };
            accountingEntriesService.getRemarksList(requestData).then(function (response) {
                vm.remarksList = response.data.result || []; // Ensure it's an array
            }, function (error) {
                handleErrorResponse(error);
            });
        }

        function fromDateVal() {
            vm.fromDateOptions = {
                maxDate: vm.toDate ? new Date(vm.toDate) : new Date(),
                showWeeks: false,
            };
        }

        function toDateVal(effectiveFromDate) {
            vm.toDateOptions = {
                minDate: new Date(effectiveFromDate),
                maxDate: new Date(),
                showWeeks: false,
            };
        }
    }
})();