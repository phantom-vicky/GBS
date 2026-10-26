(function() {
    "use strict";

    angular.module("gbmUltraApp").controller("cancellationSubscriptionCatCbtController", cancellationSubscriptionCatCbtController);

    cancellationSubscriptionCatCbtController.$inject = ["$scope", "$filter", "$state", "$rootScope", "$stateParams", "$location", "$timeout", "cancellationSubscriptionCatCbtService", "commonService"];

    function cancellationSubscriptionCatCbtController($scope, $filter, $state, $rootScope, $stateParams, $location, $timeout, cancellationSubscriptionCatCbtService, commonService) {
        var vm = this;
        vm.validationMessages = "";
 vm.textableDatePicker=textableDatePicker;
         vm.autoslashing=autoslashing;
         vm.futureNotAllowed=futureNotAllowed;



function textableDatePicker(id,valueDate)
{
 commonService.textableDatePicker(id,valueDate);
}
function futureNotAllowed(id,valueDate)
{
 commonService.futureNotAllowed(id,valueDate);
}
vm.indianNumberFormat=indianNumberFormat;
                function indianNumberFormat(value) {
                  if (isNaN(value)|| null == value) return value;
                  let numStr = value.toString();
                  let [whole, decimal] = numStr.split('.');
                  if (whole.length <= 3) {
                      return decimal ? whole + '.' + decimal : whole + '.00';
                    }
                  let lastThree = whole.slice(-3);  // last three digits
                  let otherNumbers = whole.slice(0, whole.length - 3);
                  if (otherNumbers !== '') {
                    lastThree = ',' + lastThree;
                  }
                  let formattedWhole = otherNumbers.replace(/\B(?=(\d{2})+(?!\d))/g, ',') + lastThree;
                if (decimal) {
                    return formattedWhole + '.' + decimal;
                  } else {
                    return formattedWhole+'.00';
                  }
                }
function autoslashing(id)
{
 commonService.autoslashing(id);
}
        vm.tabReferenceAdd = '';

        vm.tabReferenceVerify = '';

        vm.tabReferenceInquiry = '';

        vm.modeOfHolding = ""; // "Single", "Joint", "Jointly", or "HUF"
$scope.restrictInput = function(event) {
    var charCode = event.which || event.keyCode;
    var charStr = String.fromCharCode(charCode).toLowerCase();
    if (!/^[a-z\s]*$/.test(charStr) && charCode !== 8 && charCode !== 32 && charCode !== 20) {
        event.preventDefault(); // Prevent entering the character if it’s not a valid input
    }
};
        vm.commonYesNoMetaData = COMMON_YES_NO_METADATA;
        vm.panPattern = /[a-zA-Z]{5}[0-9]{4}[a-zA-Z]{1}/;
        vm.emailPattern = /[^@]+@[^@]+\.[a-zA-Z]{2,6}/;
        vm.nextStep = nextStep;
        vm.prevStep = prevStep;
        vm.nextTab = nextTab;
        vm.prevTab = prevTab;
        vm.addcustomerMaster = addcustomerMaster;

        vm.getVerifySelectDetails = getVerifySelectDetails;
        vm.getInquirySelectDetails = getInquirySelectDetails;

        vm.getModuleNameAdd = getModuleNameAdd;
        vm.getModuleNameInq = getModuleNameInq;
        vm.getModuleNameVer = getModuleNameVer;
        vm.getPantorchAdd = getPantorchAdd;
        vm.getPantorchInq = getPantorchInq;
        vm.getblaNoAdd = getblaNoAdd;
        vm.getblaNoInq = getblaNoInq;
        vm.getblaNoVer = getblaNoVer;
        vm.getsubscriptionIdAdd = getsubscriptionIdAdd;
        vm.getsubscriptionIdInq = getsubscriptionIdInq;
        vm.getsubscriptionIdVer = getsubscriptionIdVer;
        vm.getTransactionModes = getTransactionModes;
        vm.getGuardianDetailsMetaData = getGuardianDetailsMetaData;
        vm.convertLabelValue = convertLabelValue;

        vm.currentDate = new Date();
        vm.loginSolId = $rootScope.globals.currentUser.solId;
        vm.systemDate = $rootScope.globals.currentUser.systemDate;
        vm.solDate = $filter("date")(vm.systemDate, "dd/MM/yyyy");

        vm.verifyCustomerMaster = verifyCustomerMaster;

        vm.loginSolDate = $rootScope.globals.currentUser.solDate;
        vm.valueDateOptions = {
            minDate: null,
            maxDate: null,
        };
        if ($state.current.name == "cancellation_subscription_cat_cbt.add_detail") {
            if ($stateParams.obj) {
                vm.schemeCode = $stateParams.obj.schemeCode;
                vm.schemeName = $stateParams.obj.schemeName;
                vm.closerType = $stateParams.obj.closerType;
                vm.bondHolderName = $stateParams.obj.bondHolderName;
                vm.panNum = $stateParams.obj.panNum;
                vm.blaNo = $stateParams.obj.blaNo;
                vm.subscriptionId = $stateParams.obj.subscriptionId;
                vm.valueDateOptions.maxDate = vm.systemDate;
                getSubscriptionDetailsAdd();
                getTransactionModes();
                getGuardianDetailsMetaData();
            }
        } else if ($state.current.name == "cancellation_subscription_cat_cbt.verify_detail") {
            if ($stateParams.obj) {
                vm.schemeCode = $stateParams.obj.schemeCode;
                vm.blaNo = $stateParams.obj.blaNo;
                vm.schemeName = $stateParams.obj.schemeName;
                vm.closerType = $stateParams.obj.closerType;
                vm.subscriptionId = $stateParams.obj.subscriptionId;
                getVerifySelectDetails();
                getTransactionModes();
                getGuardianDetailsMetaData();
            }
        } else if ($state.current.name == "cancellation_subscription_cat_cbt.inquiry_detail") {
            if ($stateParams.obj) {
                vm.blaNo = $stateParams.obj.blaNo;
                vm.schemeCode = $stateParams.obj.schemeCode;
                vm.schemeName = $stateParams.obj.schemeName;
                vm.closerType = $stateParams.obj.closerType;
                vm.panNum = $stateParams.obj.panNum;
                vm.subscriptionId = $stateParams.obj.subscriptionId;
                vm.bondHolderName = $stateParams.obj.bondHolderName;
                getInquirySelectDetails();
                getTransactionModes();
                getGuardianDetailsMetaData();
            }
        }

        function getTransactionModes() {
            vm.referenceId = "TRANSACTION_TYPE";
            var requestData = {
                referenceId: "RBI_TRAN_MODES",
            };
            cancellationSubscriptionCatCbtService.getReferenceData(requestData).then(
                function(response) {
                    vm.transactionModeList = response.data.result;
                },
                function(error) {
                    handleErrorResponse(error);
                }
            );
        }

        function getGuardianDetailsMetaData() {
            var requestData = {
                referenceId: "RELATION_WITH_MINOR",
            };
            cancellationSubscriptionCatCbtService.getReferenceData(requestData).then(
                function(response) {
                    vm.guardianDetailsListMetaData = response.data.result;
                },
                function(error) {
                    handleErrorResponse(error);
                }
            );
        }

        function convertLabelValue(list) {
            for (var item in list) {
                list[item].value = list[item].SUBCODE;
                list[item].label = list[item].DESCRIPTION;
            }
            return list;
        }

        ///////////////
        function getModuleNameAdd() {
            vm.loader = true;
            var requestData = {
                flag: null,
            };
            cancellationSubscriptionCatCbtService.addCloserSubCatCbt(requestData).then(
                function(response) {
                    vm.loader = false;
                    vm.schemeList = response.data.result;
                    angular.element("#schemeModel").modal("toggle");
                },
                function(error) {
                    handleErrorResponse(error);
                    vm.loader = false;
                }
            );
        }

        function getPantorchAdd() {
            var requestData = {
                schemeCode: vm.schemeCode,
                bondHolderName: vm.bondHolderName,
                panNum: vm.panNum,
                closerType: vm.closerType,
                flag: "P",
            };
            cancellationSubscriptionCatCbtService.addCloserSubCatCbt(requestData).then(
                function(response) {
                    vm.loader = false;
                    vm.PanTorch = response.data.result;
                    angular.element("#panModel").modal("toggle");
                },
                function(error) {
                    vm.loader = false;
                    handleErrorResponse(error);
                }
            );
        }

        function getblaNoAdd() {
            vm.loader = true;
            var requestData = {
                schemeCode: vm.schemeCode,
                schemeName: vm.schemeName,
                closerType: vm.closerType,
                panNum: vm.panNum,
                bondHolderName: vm.bondHolderName,
                blaNo: vm.blaNo,
                flag: "B",
            };
            cancellationSubscriptionCatCbtService.addCloserSubCatCbt(requestData).then(
                function(response) {
                    vm.loader = false;
                    vm.blaDataList = response.data.result;
                    angular.element("#blaModel").modal("toggle");
                },
                function(error) {
                    vm.loader = false;
                    handleErrorResponse(error);
                }
            );
        }
        vm.defaultSelectOption = defaultSelectOption;

        function defaultSelectOption() {
            vm.closerType = 'CAT';
        }

        function getsubscriptionIdAdd() {
            vm.loader = true;
            var requestData = {
                schemeCode: vm.schemeCode,
                schemeName: vm.schemeName,
                closerType: vm.closerType,
                panNum: vm.panNum,
                bondHolderName: vm.bondHolderName,
                blaNo: vm.blaNo,
                subscriptionId: vm.subscriptionId,
                flag: "S"
            };
            cancellationSubscriptionCatCbtService.addCloserSubCatCbt(requestData).then(
                function(response) {
                    vm.loader = false;
                    vm.subscriptionList = response.data.result;
                    angular.element("#subscriptionModel").modal("toggle");
                },
                function(error) {
                    vm.loader = false;
                    handleErrorResponse(error);
                }
            );
        }

        function getSubscriptionDetailsAdd() {
            vm.loader = true;
            var requestData = {
                schemeCode: vm.schemeCode,
                schemeName: vm.schemeName,
                closerType: vm.closerType,
                panNum: vm.panNum,
                bondHolderName: vm.bondHolderName,
                blaNo: vm.blaNo,
                subscriptionId: vm.subscriptionId,
                flag: 'D'
            };
            cancellationSubscriptionCatCbtService.addCloserSubCatCbt(requestData).then(
                function(response) {
                    vm.loader = false;
                    vm.subscriptionList = response.data.result.reduce((acc, item) => {
                        acc[item.colName] = item.value;
                        return acc;
                    }, {});
                },
                function(error) {
                    vm.loader = false;
                    handleErrorResponse(error);
                    $state.go("cancellation_subscription_cat_cbt.inquiry");
                }
            );
        }
        /*Add submit*/
        function addcustomerMaster() {
//            Swal.fire({
//                    title: "Are you sure?",
//                    text: "Do you really want to add this record?",
//                    type: "warning",
//                    showCancelButton: true,
//                    cancelButtonClass: "btn-cancel",
//                    confirmButtonClass: "btn-primary",
//                    confirmButtonText: "Yes",
//                    closeOnConfirm: false,
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
                    vm.loader = true;
                    var formattedDate = null;
                    if (vm.courtOrderDate) {
                        const date = new Date(vm.courtOrderDate);
                        formattedDate = `${date.getDate().toString().padStart(2, "0")}-${date.toLocaleString("en-US", { month: "short" }).toUpperCase()}-${date.getFullYear()}`;
                    }
                    var requestData = {
                        schemeCode: vm.schemeCode,
                        schemeName: vm.schemeName,
                        closerType: vm.closerType,
                        panNum: vm.panNum,
                        blaNo: vm.blaNo,
                        subscriptionId: vm.subscriptionId,
                        courtOrder: vm.courtOrder,
                        courtOrderDate: formattedDate,
                        catCbtRemarks: vm.catCbtRemarks,
                    };
                    cancellationSubscriptionCatCbtService.addSubCloserSubCatCbt(requestData).then(function(response) {
                        vm.loader = false;
                        if (response.data.message == "SUCCESS") {
                            Swal.fire("Added!", "Added Successfully", "success");
                            location.href = "#!/cancellation_subscription_cat_cbt/action/inquiry";
                        } else {
                            Swal.fire("Warning!", response.data.message, "warning");
                            location.href = "#!/cancellation_subscription_cat_cbt/action/add";
                        }
                    }, function(error) {
                        vm.loader = false;
                        handleErrorResponse(error);
                    });
                    }
                });
        }

        //inquiry

        function getModuleNameInq() {
            vm.loader = true;
            var requestData = {
                flag: null
            };
            cancellationSubscriptionCatCbtService.inquiryCloserSubCatCbt(requestData).then(
                function(response) {
                    vm.loader = false;
                    vm.schemeList = response.data.result;
                    angular.element("#schemeModel").modal("toggle");
                },
                function(error) {
                    handleErrorResponse(error);
                    vm.loader = false;
                }
            );
        }

        function getPantorchInq() {
            var requestData = {
                schemeCode: vm.schemeCode,
                bondHolderName: vm.bondHolderName,
                panNum: vm.panNum,
                closerType: vm.closerType,
                flag: "P"
            };
            cancellationSubscriptionCatCbtService.inquiryCloserSubCatCbt(requestData).then(
                function(response) {
                    vm.loader = false;
                    vm.PanTorch = response.data.result;
                    angular.element("#panModel").modal("toggle");
                },
                function(error) {
                    vm.loader = false;
                    handleErrorResponse(error);
                }
            );
        }

        function getblaNoInq() {
            vm.loader = true;
            var requestData = {
                schemeCode: vm.schemeCode,
                schemeName: vm.schemeName,
                closerType: vm.closerType,
                panNum: vm.panNum,
                bondHolderName: vm.bondHolderName,
                blaNo: vm.blaNo,
                flag: "B",
            };
            cancellationSubscriptionCatCbtService.inquiryCloserSubCatCbt(requestData).then(
                function(response) {
                    vm.loader = false;
                    vm.blaDataList = response.data.result;
                    angular.element("#blaModel").modal("toggle");
                },
                function(error) {
                    vm.loader = false;
                    handleErrorResponse(error);
                }
            );
        }

        function getsubscriptionIdInq() {
            vm.loader = true;
            var requestData = {
                schemeCode: vm.schemeCode,
                schemeName: vm.schemeName,
                closerType: vm.closerType,
                panNum: vm.panNum,
                bondHolderName: vm.bondHolderName,
                blaNo: vm.blaNo,
                subscriptionId: vm.subscriptionId,
                flag: "S"
            };
            cancellationSubscriptionCatCbtService.inquiryCloserSubCatCbt(requestData).then(
                function(response) {
                    vm.loader = false;
                    vm.subscriptionList = response.data.result;

                    angular.element("#subscriptionModel").modal("toggle");
                },
                function(error) {
                    vm.loader = false;
                    handleErrorResponse(error);
                }
            );
        }

        function getSubscriptionDetailsInq() {
            vm.loader = true;
            var requestData = {
                schemeCode: vm.schemeCode,
                schemeName: vm.schemeName,
                closerType: vm.closerType,
                panNum: vm.panNum,
                bondHolderName: vm.bondHolderName,
                blaNo: vm.blaNo,
                subscriptionId: vm.subscriptionId,
                flag: "S"
            };
            cancellationSubscriptionCatCbtService.inquiryCloserSubCatCbt(requestData).then(
                function(response) {
                    vm.loader = false;
                    vm.subscriptionList = response.data.result;
                    angular.element("#subscriptionModel").modal("toggle");
                },
                function(error) {
                    vm.loader = false;
                    handleErrorResponse(error);
                }
            );
        }

        function getInquirySelectDetails() {
            vm.loader = true;
            var requestData = {
                schemeCode: vm.schemeCode,
                schemeName: vm.schemeName,
                closerType: vm.closerType,
                panNum: vm.panNum,
                bondHolderName: vm.bondHolderName,
                blaNo: vm.blaNo,
                subscriptionId: vm.subscriptionId,
                flag: 'D'
            };
            cancellationSubscriptionCatCbtService.inquiryCloserSubCatCbt(requestData).then(
                function(response) {
                    vm.loader = false;
                    vm.subscriptionList = response.data.result.reduce((acc, item) => {
                        acc[item.colName] = item.value;
                        return acc;
                    }, {});
                    vm.auditInfo = response.data.result1;
                },
                function(error) {
                    vm.loader = false;
                    handleErrorResponse(error);
                    $state.go("cancellation_subscription_cat_cbt.inquiry");
                }
            );
        }

        /*verify*/
        function getModuleNameVer() {
            vm.loader = true;
            var requestData = {
                torch: "scheme",
            };
            cancellationSubscriptionCatCbtService.verifyRejectCloserSubCatCbt(requestData).then(
                function(response) {
                    vm.loader = false;
                    vm.schemeList = response.data.result;
                    angular.element("#schemeModel").modal("toggle");
                },
                function(error) {
                    handleErrorResponse(error);
                    vm.loader = false;
                }
            );
        }
        /*
                            function getPantorchVer() {

                                var requestData = {
                                    'schemeCode': vm.schemeCode,
                                    'bondHolderName': vm.bondHolderName,
                                    'panNum': vm.panNum,
                                    'torch': "pan"
                                };
                                cancellationSubscriptionCatCbtService.verifyRejectCloserSubCatCbt(requestData)
                                    .then(function(response) {
                                        vm.loader = false;
                                        vm.PanTorch = response.data.result;
                                        angular.element('#panModel')
                                            .modal('toggle');
                                    }, function(error) {
                                        vm.loader = false;
                                        handleErrorResponse(error);
                                    });
                            }*/

        function getblaNoVer() {
            vm.loader = true;
            var requestData = {
                schemeCode: vm.schemeCode,
                schemeName: vm.schemeName,
                closerType: vm.closerType,
                /*'panNum': vm.panNum,
                                        'bondHolderName': vm.bondHolderName,*/
                blaNo: vm.blaNo,
                torch: "bla",
            };
            cancellationSubscriptionCatCbtService.verifyRejectCloserSubCatCbt(requestData).then(
                function(response) {
                    vm.loader = false;
                    vm.blaDataList = response.data.result;
                    angular.element("#blaModel").modal("toggle");
                },
                function(error) {
                    vm.loader = false;
                    handleErrorResponse(error);
                }
            );
        }

        function getsubscriptionIdVer() {
            vm.loader = true;
            var requestData = {
                schemeCode: vm.schemeCode,
                schemeName: vm.schemeName,
                closerType: vm.closerType,
                /* 'panNum': vm.panNum,
                                        'bondHolderName': vm.bondHolderName,*/
                blaNo: vm.blaNo,
                subscriptionId: vm.subscriptionId,
                torch: "sub",
            };
            cancellationSubscriptionCatCbtService.verifyRejectCloserSubCatCbt(requestData).then(
                function(response) {
                    vm.loader = false;
                    vm.subscriptionList = response.data.result;

                    angular.element("#subscriptionModel").modal("toggle");
                },
                function(error) {
                    vm.loader = false;
                    handleErrorResponse(error);
                }
            );
        }

        /* function getSubscriptionDetailsVer() {
                                vm.loader = true;
                                var requestData = {
                                    'schemeCode': vm.schemeCode,
                                    'schemeName': vm.schemeName,
                                    'closerType': vm.closerType,
                                    'blaNo': vm.blaNo,
                                    'subscriptionId': vm.subscriptionId,
                                    'torch': "sub"
                                };
                                cancellationSubscriptionCatCbtService.verifyRejectCloserSubCatCbt(requestData)
                                    .then(function(response) {
                                        vm.loader = false;
                                        vm.subscriptionList = response.data.result;

                                        angular.element('#subscriptionModel')
                                            .modal('toggle');
                                    }, function(error) {
                                        vm.loader = false;
                                        handleErrorResponse(error);
                                    });

                            }*/
        function getVerifySelectDetails() {
            vm.loader = true;
            var requestData = {
                schemeCode: vm.schemeCode,
                schemeName: vm.schemeName,
                closerType: vm.closerType,
                blaNo: vm.blaNo,
                subscriptionId: vm.subscriptionId,
            };
            cancellationSubscriptionCatCbtService.verifyRejectCloserSubCatCbt(requestData).then(
                function(response) {
                    vm.loader = false;
                    vm.subscriptionList = response.data.result.reduce((acc, item) => {
                        acc[item.colName] = item.value;
                        return acc;
                    }, {});
                    vm.auditInfo = response.data.result1;
                },
                function(error) {
                    vm.loader = false;
                    handleErrorResponse(error);
                    $state.go("cancellation_subscription_cat_cbt.inquiry");
                }
            );
        }
//
//        function verifyCustomerMaster() {
//            function handleKeyPress(event) {
//                var key = event.key;
//                var input = event.target.value;
//
//                // Prevent entering more than one consecutive space
//                if (key === " " && (input.length === 0 || input.slice(-1) === " ")) {
//                    event.preventDefault();
//                }
//
//                // Allow only alphabetic characters, single spaces, and backspace
//                if (!key.match(/^[a-zA-Z\s]$/) && key !== "Backspace" && key !== "Enter") {
//                    event.preventDefault();
//                }
//            }
//
//            document.addEventListener("keypress", handleKeyPress);
//
//            Swal.fire({
//                    title: "Verification Remarks",
//                    text: "",
//                    type: "input",
//                    inputPlaceholder: "Enter your remarks here",
//                    showCancelButton: true,
//                    cancelButtonClass: "btn-cancel",
//                    confirmButtonClass: "btn-primary",
//                    confirmButtonText: "Submit",
//                    animation: "slide-from-top",
//                    closeOnConfirm: false,
//                    inputValidator: function(value) {
//                        if (!value || value.trim() === "") {
//                            return "Please enter the remarks!";
//                        }
//
//                        // Trim spaces and check for leading or trailing spaces
//                        const trimmedValue = value.trim();
//
//                        // Ensure no leading/trailing spaces and valid content
//                        if (trimmedValue.length > 0 && /^[a-zA-Z\s]*$/.test(trimmedValue)) {
//                            if (/\s\s/.test(trimmedValue)) {
//                                return "Please enter only single spaces between words!";
//                            }
//                            document.removeEventListener("keypress", handleKeyPress);
//                            return true;
//                        } else {
//                            return "Please enter only alphabetic characters!";
//                        }
//                    },
//                },
//                function(inputValue) {
//                    if (inputValue === false) {
//                        document.removeEventListener("keypress", handleKeyPress);
//                        return false;
//                    }
//
//                    // Check if input is empty after trimming
//                    const trimmedValue = inputValue.trim();
//                    if (trimmedValue === "") {
//                        Swal.fire.showInputError("Please enter the remarks!");
//                        return false;
//                    }
//
//                    vm.verifyRemarks = trimmedValue;
//
//                    var requestData = {
//                        schemeCode: vm.schemeCode,
//                        closerType: vm.closerType,
//                        blaNo: vm.blaNo,
//                        subscriptionId: vm.subscriptionId,
//                        rejectionRemarks: vm.verifyRemarks,
//                    };
//                    cancellationSubscriptionCatCbtService.verifyCloserSubCatCbt(requestData).then(
//                        (response) => {
//                            vm.loader = false;
//                            vm.responseResult = response.data.result;
//                            Swal.fire("Verified!", "Verified Successfully", "success");
//                            location.href = "#!/cancellation_subscription_cat_cbt/action/inquiry";
//                        },
//                        function(error) {
//                            vm.loader = false;
//                            handleErrorResponse(error);
//                            location.href = "#!/cancellation_subscription_cat_cbt/action/inquiry";
//                        }
//                    );
//                },
//                function(error) {
//                    vm.loader = false;
//                    handleErrorResponse(error);
//                }
//            );
//        }



function verifyCustomerMaster() {
    function handleKeyPress(event) {
        const key = event.key;
        const input = event.target.value;

        // Prevent entering more than one consecutive space
        if (key === " " && (input.length === 0 || input.slice(-1) === " ")) {
            event.preventDefault();
        }

        // Allow only alphabetic characters, single spaces, and backspace
        if (!/^[a-zA-Z\s]$/.test(key) && key !== "Backspace" && key !== "Enter") {
            event.preventDefault();
        }
    }

    document.addEventListener("keypress", handleKeyPress);

    Swal.fire({
        title: "Verification Remarks",
        input: "text",
        inputPlaceholder: "Enter your remarks here",
        showCancelButton: true,
        confirmButtonText: "Submit",
        cancelButtonText: "Cancel",
        customClass: {
            confirmButton: "btn btn-primary",
            cancelButton: "btn btn-cancel"
        },
        inputValidator: (value) => {
            const trimmedValue = value.trim();

            if (!trimmedValue) {
                return "Please enter the remarks!";
            }

            if (!/^[a-zA-Z\s]+$/.test(trimmedValue)) {
                return "Please enter only alphabetic characters!";
            }

            if (/\s\s/.test(trimmedValue)) {
                return "Please enter only single spaces between words!";
            }

            return null; // Valid input
        },
        preConfirm: (inputValue) => {
            const trimmedValue = inputValue.trim();

            if (trimmedValue === "") {
                Swal.showValidationMessage("Please enter the remarks!");
                return false;
            }

            vm.verifyRemarks = trimmedValue;

            vm.loader = true;

            const requestData = {
                schemeCode: vm.schemeCode,
                closerType: vm.closerType,
                blaNo: vm.blaNo,
                subscriptionId: vm.subscriptionId,
                rejectionRemarks: vm.verifyRemarks,
            };

            // Return the promise to Swal so it can manage loader automatically
            return cancellationSubscriptionCatCbtService.verifyCloserSubCatCbt(requestData)
                .then(response => {
                    vm.loader = false;
                    vm.responseResult = response.data.result;
                    return true;
                })
                .catch(error => {
                    vm.loader = false;
                    handleErrorResponse(error);
                    // Return a rejection to show an error
                    throw new Error("Verification failed. Please try again.");
                });


        },
        allowOutsideClick: () => !Swal.isLoading(),
        didClose: () => {
            document.removeEventListener("keypress", handleKeyPress);
        },
        showLoaderOnConfirm: true,
    }).then((result) => {
        if (result.isConfirmed) {
            Swal.fire("Verified!", "Verified Successfully", "success").then(() => {
                location.href = "#!/cancellation_subscription_cat_cbt/action/inquiry";
            });
        }
    });
}
        vm.getRemarksList = function() {
            vm.referenceId = "056";
            var requestData = {
                referenceId: vm.referenceId
            };

            cancellationSubscriptionCatCbtService.getRemarksList(requestData).then(
                function(response) {
                    vm.remarksList = response.data.result || []; // Ensure it's an array
                },
                function(error) {
                    handleErrorResponse(error);
                }
            );
        };

        vm.rejectReason = function() {
            angular.element("#permissionMappingDelete").modal("toggle");
        };

        vm.deleteSubmit = function() {
            vm.loader = true;
            var requestData = {
                schemeCode: vm.schemeCode,
                closerType: vm.closerType,
                blaNo: vm.blaNo,
                subscriptionId: vm.subscriptionId,
                rejectionRemarks: vm.rejectionRemarks,
            };
            cancellationSubscriptionCatCbtService.rejectCloserSubCatCbt(requestData).then(
                function(response) {
                    if (response.data.message == "SUCCESS") {

                        Swal.fire("Rejected!", "Rejected Successfully", "success");
                        location.href = "#!/cancellation_subscription_cat_cbt/action/inquiry";
                    } else {
                        Swal.fire("WARNING!", response.data.message, "error");
                        location.href = "#!/cancellation_subscription_cat_cbt/action/verify/detail";
                    }
                    const backdrop = document.querySelector(".modal-backdrop");
                    if (backdrop) {
                        backdrop.parentNode.removeChild(backdrop);
                        document.body.classList.remove("modal-open");
                    }
                },
                function(error) {
                    vm.loader = false;
                    handleErrorResponse(error);
                    const backdrop = document.querySelector(".modal-backdrop");
                    if (backdrop) {
                        backdrop.parentNode.removeChild(backdrop);
                        document.body.classList.remove("modal-open");
                    }
                    location.href = "#!/cancellation_subscription_cat_cbt/action/verify/detail";
                }
            );
        };

        function nextStep() {
            var $active = $(".from_wrapper .nav-tabs li.active");
            nextTab($active);
        }

        function nextTab(elem) {
            $(elem).next().find('a[data-bs-toggle="tab"]').click();
        }

        function prevStep() {
            var $active = $(".from_wrapper .nav-tabs li.active");
            prevTab($active);
        }

        function prevTab(elem) {
            $(elem).prev().find('a[data-bs-toggle="tab"]').click();
        }
    }
})();