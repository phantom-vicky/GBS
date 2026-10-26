(function () {

    'use strict';

    angular.module('gbmUltraApp').controller('rejectionReasonMasterController',
        rejectionReasonMasterController);

    rejectionReasonMasterController.$inject = ['$scope', '$filter', '$state',
        '$stateParams', '$location', '$rootScope', '$timeout', 'rejectionReasonMasterService', 'commonService'
    ];

    function rejectionReasonMasterController($scope, $filter, $state, $stateParams,
        $location, $rootScope, $timeout, rejectionReasonMasterService, commonService) {

        var vm = this;
        vm.getRemarksList = getRemarksList;
        vm.rejectReason = rejectReason;
        vm.getModuleNameAdd = getModuleNameAdd;
        vm.dropDownList = dropDownList;
        vm.dropDownListElements = [];
        vm.addRange = addRange;
        vm.removeRange = removeRange;
        vm.addSubmit = addSubmit;
        vm.modifySubmit = modifySubmit;
        vm.verifySubmit = verifySubmit;
        vm.validateInput = validateInput;
        vm.rejectDetails = rejectDetails;
        vm.getInquiryDetails = getInquiryDetails;
        vm.getVerifyDetails = getVerifyDetails;
        vm.getModifyDetails = getModifyDetails;
        vm.modifyRange = modifyRange;
        vm.removeModRange = removeModRange;

        if ($state.current.name == 'rejection_reason_master.add_detail') {
            if ($stateParams.obj) {
                vm.fieldName = $stateParams.obj.fieldName;
                vm.fieldCode = $stateParams.obj.fieldCode;
                vm.schemeStatus = $stateParams.obj.schemeStatus;
                vm.dropDownType = $stateParams.obj.dropDownType;
                //                    vm.getGuardianDetailsMetaData();
            }
        } else if ($state.current.name == 'rejection_reason_master.inquiry_detail') {
            if ($stateParams.obj) {
                vm.schemeCode = $stateParams.obj.schemeCode;
                vm.schemeName = $stateParams.obj.schemeName;
                vm.schemeStatus = $stateParams.obj.schemeStatus;
                vm.dropDownType = $stateParams.obj.dropDownType;
                vm.getInquiryDetails();
            }
        } else if ($state.current.name == 'rejection_reason_master.verify_detail') {
            if ($stateParams.obj) {
                vm.schemeCode = $stateParams.obj.schemeCode;
                vm.schemeName = $stateParams.obj.schemeName;
                vm.schemeStatus = $stateParams.obj.schemeStatus;
                vm.dropDownType = $stateParams.obj.dropDownType;
                vm.getVerifyDetails();
            }
        } else if ($state.current.name == 'rejection_reason_master.modify_detail') {
            if ($stateParams.obj) {
                vm.schemeCode = $stateParams.obj.schemeCode;
                vm.schemeName = $stateParams.obj.schemeName;
                vm.schemeStatus = $stateParams.obj.schemeStatus;
                vm.dropDownType = $stateParams.obj.dropDownType;
                vm.getModifyDetails();
            }
        }


        function getRemarksList() {
            vm.referenceId = '002';
            var requestData = { 'referenceId': vm.referenceId };

            rejectionReasonMasterService.getRemarksList(requestData).then(function (response) {
                vm.remarksList = response.data.result || []; // Ensure it's an array
            }, function (error) {
                handleErrorResponse(error);
            });
        }

        function rejectReason() {
            angular.element("#permissionMappingDelete").modal("toggle");
        }

        function getModuleNameAdd() {
            vm.loader = true;
            //            vm.referenceId = 'ACTSCH';
            //            var requestData = {
            //                'referenceId': vm.referenceId
            //            };
            //            rejectionReasonMasterService.getRbiBondModuleNameDetails(requestData).then(function (response) {
            //                vm.loader = false;ō
            //                vm.schemeList = response.data.result;
            //            }, function (error) {
            //                handleErrorResponse(error);
            //                $state.go('rejection_reason_master.inquiry');
            //                vm.loader = false;
            //            });

            vm.schemeList = [
                {
                    "schemeCode": "0012",
                    "schemeName": "FLOATING RATE SAVINGS TAXABLE BONDS, 2020",
                    "schemeStatus": "Current"
                },
                {
                    "schemeCode": "0021",
                    "schemeName": "8% SAVINGS TAXABLE BONDS, 2006",
                    "schemeStatus": "Active"
                },
                {
                    "schemeCode": "0017",
                    "schemeName": "8% SAVINGS TAXABLE BONDS, 2005",
                    "schemeStatus": "Active"
                },
                {
                    "schemeCode": "0020",
                    "schemeName": "FLOATING RATE SAVINGS TAXABLE BONDS, 2023",
                    "schemeStatus": "Active"
                }
            ];
            vm.loader = false;
            angular.element('#schemeModel').modal('toggle');
        }

        function dropDownList() {
            vm.loader = true;

//            vm.dropDownList = [
//                {
//                    fieldName: 'APPL STATUS', fieldCode: '060'
//                },
//                {
//                    fieldName: 'BANK ACCT TYPE', fieldCode: '061'
//                },
//                {
//                    fieldName: 'FREEZE AUTHORITY', fieldCode: '062'
//                },
//                {
//                    fieldName: 'DOCUMENT', fieldCode: '063'
//                },
//                {
//                    fieldName: 'PROOF', fieldCode: '064'
//                }
//            ]

    vm.dropDownList = [
//    { fieldName: "EXCEPTION", fieldCode: "051" },
    { fieldName: "REJECT REASON FOR INTIMATION", fieldCode: "052" },
    { fieldName: "REJECT REASON ON EXEMPT", fieldCode: "053" },
    { fieldName: "RETURN CASE", fieldCode: "054" },
    { fieldName: "DC", fieldCode: "055" },
    { fieldName: "GU", fieldCode: "056" },
    { fieldName: "Tax Exemption", fieldCode: "057" },
    { fieldName: "Admin", fieldCode: "058" },
]
            vm.loader = false;

        }




        function addRange() {
            vm.loader = true;
            vm.dropDownListElements.push({});
            vm.loader = false;
            if (vm.dropDownListElements.length == 0) {
                vm.enableDelete = false;
                vm.enableSubmit = false;
            } else
                vm.enableDelete = true;
            vm.enableSubmit = true;
        }


        function modifyRange() {
            vm.loader = true;
            vm.dropDownListElements.push({});
            vm.loader = false;
            vm.enableDelete = true;
        }


        function removeRange(index) {
            if (!vm.deletedRows) {
                vm.deletedRows = [];
            }
            const removedItem = vm.dropDownListElements.splice(index, 1)[0];


            if (vm.dropDownListElements.length === 0) {
                vm.disableDelete = true;
                vm.enableSubmit = false;
            }
        }

        function removeModRange(index) {
            // Ensure deletedRows is initialized
            if (!vm.deletedRows) {
                vm.deletedRows = []; // Initialize if it doesn't exist
            }

            // Ensure resultDelArray is initialized
            if (!vm.resultDelArray) {
                vm.resultDelArray = []; // Initialize if it doesn't exist
            }

            // Remove the item and capture the removed item
            const removedItem = vm.dropAddDownListElements.splice(index, 1)[0];

            // Create deletion data object
            let deletionData = {};
            if (removedItem) {
                deletionData['fieldCode'] = removedItem.fieldCode; // Capture the field name
                // Add any other necessary data, e.g., ID if applicable
            }

            // Push deletion data to deletedRows
            vm.deletedRows.push(deletionData);
            //    vm.resultDelArray.push(deletionData]);

            // Check if we need to disable delete button
            if (vm.dropAddDownListElements.length === 0) {
                vm.disableDelete = true;
            }
        }



        function addSubmit() {
//            Swal.fire({
//                title: "Are you sure?",
//                text: "Do you want to Really Add this record?",
//                type: "warning",
//                showCancelButton: true,
//                cancelButtonClass: "btn-cancel",
//                confirmButtonClass: "btn-primary",
//                confirmButtonText: "Yes",
//                closeOnConfirm: false
//            }, function () {


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



         vm.resultAddArray1 = [];  // Initialize the array

         // Create a Set to track unique field names
         let rejectionReasonSet = new Set();
         let duplicateFound = false;  // Flag to track if duplicates are found

                angular.forEach(vm.dropDownListElements, function (item) {
                    if (item.rejectionReason) { // Check if fieldName exists

                    if (rejectionReasonSet.has(item.rejectionReason)) {
                                         // If the fieldName already exists in the set, it's a duplicate
                                         duplicateFound = true;
                                     } else {
                                         // Add the fieldName to the set (will handle uniqueness automatically)
                                         rejectionReasonSet.add(item.rejectionReason);
                                     }
                                 }
                });
// If duplicates are found, show the warning SweetAlert
      if (duplicateFound) {
          Swal.fire("Warning!", "Duplicate records in the list.", "warning");
          return;
      } else {
          // If no duplicates are found, just add all unique values to resultAddArray1
          vm.resultAddArray1 = Array.from(rejectionReasonSet);
      }
                vm.loader = true;

                var dropDownType = (typeof vm.dropDownType === 'string') ? JSON.parse(vm.dropDownType) : vm.dropDownType;

                var requestData = {
                    'values': vm.resultAddArray1,
                    'fieldName': dropDownType.fieldName,
                    'fieldCode': dropDownType.fieldCode
                };

                rejectionReasonMasterService.addDetails(requestData).then(
                    (response) => {
                        vm.loader = false;


                        var message = response.data.message;

                        //                               Swal.fire("Added!", `Field Name for ${vm.dropDownType} added successfully.`, "success");
                        Swal.fire("Added!", `Field Name for  ${dropDownType.fieldName}  added successfully.`, "success");



                        location.href = '#!/rejection_reason_master/action/inquiry';
                    },
                    function (error) {
                        vm.loader = false;
                        handleErrorResponse(error);

                    });

          }  });
        }





        function validateInput(event, id) {
            const textBox = document.getElementById(id);
            var charCode = event.which || event.keyCode;
            var charTyped = String.fromCharCode(charCode);

            if (textBox) {
                const cursorPosition = textBox.selectionStart;
                if (cursorPosition == 0 && charTyped === ' ') {
                    event.preventDefault();
                    return;
                }
            }
            var currentValue = event.target.value + charTyped;
            var pattern = /^[a-zA-Z\s]*'?([a-zA-Z\s]*'?)*$/;
            if (!pattern.test(currentValue)) {
                event.preventDefault();
            }
        }


        function rejectDetails() {
            //    function handleKeyPress(event) {
            //        var key = event.key;
            //        var input = event.target.value;
            //
            //        if (key === ' ' && (input.length === 0 || input.slice(-1) === ' ')) {
            //            event.preventDefault();
            //        }
            //
            //        if (!key.match(/^[a-zA-Z\s]$/) && key !== 'Backspace' && key !== 'Enter') {
            //            event.preventDefault();
            //        }
            //    }

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
            //        inputValidator: function(value) {
            //            if (!value || value.trim() === "") {
            //                return "Please enter the remarks!";
            //            }
            //
            //            const trimmedValue = value.trim();
            //
            //            if (trimmedValue.length > 0 && /^[a-zA-Z\s]*$/.test(trimmedValue)) {
            //                if (/\s\s/.test(trimmedValue)) {
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
            //        // Check if input is empty after trimming
            //        const trimmedValue = inputValue.trim();
            //        if (trimmedValue === "") {
            //            Swal.fire.showInputError("Please enter the remarks!");
            //            return false;
            //        }
            //
            //                    vm.rejectionRemarks = trimmedValue;

            vm.resultAddArray = [];
            vm.resultDelArray = []; // Reset resultDelArray

            angular.forEach(vm.AddedList, function (item) {
                vm.resultAddArray.push(item.fieldCode);
            });
            //
            angular.forEach(vm.deleteddList, function (item) {
                vm.resultDelArray.push(item.fieldCode); // Correct usage of push with parentheses
            });
            //
            var dropDownType = (typeof vm.dropDownType === 'string') ? JSON.parse(vm.dropDownType) : vm.dropDownType;

            var requestData = {
                'fieldCode': dropDownType.fieldCode,
                'addedList': vm.resultAddArray,
                'deletedList': vm.resultDelArray,
                'rejectionRemarks': vm.remarks
            };

            rejectionReasonMasterService.reject(requestData).then(
                (response) => {
                    vm.loader = false;
                    Swal.fire("Success!", "Rejected Successfully", "success");
                    location.href = '#!/rejection_reason_master/action/inquiry';
                    const backdrop = document.querySelector('.modal-backdrop');
                    if (backdrop) {
                      backdrop.parentNode.removeChild(backdrop);
                      document.body.classList.remove('modal-open');
                    }
                }, function (error) {
                    vm.loader = false;
                    handleErrorResponse(error);
                    const backdrop = document.querySelector('.modal-backdrop');
                    if (backdrop) {
                      backdrop.parentNode.removeChild(backdrop);
                      document.body.classList.remove('modal-open');
                    }
                   location.href = '#!/rejection_reason_master/action/verify';
                }
            );
        }
        function getModifyDetails() {

            vm.loader = true;


            var dropDownType = (typeof vm.dropDownType === 'string') ? JSON.parse(vm.dropDownType) : vm.dropDownType;

            var requestData = {

                'fieldCode': dropDownType.fieldCode,
            };

            rejectionReasonMasterService.modifyDetails(requestData).then(function (response) {
                vm.loader = false;
                vm.dropAddDownListElements = response.data.result.schemeDetailList;
                vm.AddedList = response.data.result.addedDetailList;
                vm.deleteddList = response.data.result.deleteDetailList;
                vm.audit = response.data.result;
                vm.rejectionRemarks = response.data.result.rejectionRemarks;
            }, function (error) {
                handleErrorResponse(error);
                vm.loader = false;
                $state.go('rejection_reason_master.inquiry');
            });




        }


        function modifySubmit() {
//            Swal.fire({
//                title: "Are you sure?",
//                text: "Do you want to Really Modify this record?",
//                type: "warning",
//                showCancelButton: true,
//                cancelButtonClass: "btn-cancel",
//                confirmButtonClass: "btn-primary",
//                confirmButtonText: "Yes",
//                closeOnConfirm: false
//            },
//                function (yesFlg) {
//                    if (yesFlg) {


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

                        vm.resultAddArray = [];
                        vm.resultDelArray = []; // Reset resultDelArray

                        angular.forEach(vm.dropDownListElements, function (item) {
                            if (item.fieldName) { // Check if fieldName exists
                                vm.resultAddArray.push(item.fieldName);
                            }
                        });

                        // Construct deletion list
                        angular.forEach(vm.deletedRows, function (deletedItem) {
                            angular.forEach(deletedItem, function (value) {
                                vm.resultDelArray.push(value); // Push each value into resultDelArray
                            });
                        });

                        var dropDownType = (typeof vm.dropDownType === 'string') ? JSON.parse(vm.dropDownType) : vm.dropDownType;

                        var requestData = {

                            'fieldCode': dropDownType.fieldCode,
                            'fieldName': dropDownType.fieldName,
                            'addedList': vm.resultAddArray,
                            'deletedList': vm.resultDelArray
                        };

                        rejectionReasonMasterService.modifySubmit(requestData).then(function (response) {

                            vm.loader = false;
                            //                                                   Swal.fire("Modified!", "Field Name For ${dropDownType.fieldName} Modified Successfully", "success");
                            Swal.fire("Modified!", `Field Name for  ${dropDownType.fieldName}  Modified successfully.`, "success");
                            location.href = '#!/rejection_reason_master/action/inquiry';
                        },
                            function (error) {
                                vm.loader = false;
                                handleErrorResponse(error);
                                $state.go('rejection_reason_master.inquiry');
                            }
                        );
                    }
                    else {
                        $state.go('rejection_reason_master.inquiry');
                    }
                });
        }
        function getVerifyDetails() {

            vm.loader = true;
            var dropDownType = (typeof vm.dropDownType === 'string') ? JSON.parse(vm.dropDownType) : vm.dropDownType;

            var requestData = {

                'fieldCode': dropDownType.fieldCode
            };

            rejectionReasonMasterService.verifyDetails(requestData).then(function (response) {
                vm.loader = false;
                vm.modifiedList = response.data.result.schemeDetailList;
                vm.AddedList = response.data.result.addedDetailList;
                vm.deleteddList = response.data.result.deleteDetailList;
                vm.audit = response.data.result;
                vm.rejectionRemarks = response.data.result.rejectionRemarks;
            }, function (error) {
                handleErrorResponse(error);
                vm.loader = false;
                $state.go('rejection_reason_master.inquiry');
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
//                function (yesFlg) {
//                    if (yesFlg) {

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

                        vm.resultAddArray = [];
                        vm.resultDelArray = []; // Reset resultDelArray

                        angular.forEach(vm.AddedList, function (item) {
                            if (item.fieldCode) { // Ensure fieldCode exists
                                vm.resultAddArray.push(item.fieldCode);
                            }
                        });

                        angular.forEach(vm.deleteddList, function (item) {

                            if (item.fieldCode) {
                                vm.resultDelArray.push(item.fieldCode);
                            }
                        });

                        var dropDownType = (typeof vm.dropDownType === 'string') ? JSON.parse(vm.dropDownType) : vm.dropDownType;

                        var requestData = {

                            'fieldCode': dropDownType.fieldCode,

                            'addedList': vm.resultAddArray,
                            'deletedList': vm.resultDelArray
                        };

                        rejectionReasonMasterService.verifySubmit(requestData).then(
                            (response) => {
                                vm.loader = false;
                                //                    Swal.fire("Verified!","Field Name for ${vm.dropDownType} Verified Successfully","success");
                                //                    Swal.fire("Verified!","Field Name for ${dropDownType.fieldName} Verified Successfully","success");
                                Swal.fire("Verified!", `Field Name for  ${dropDownType.fieldName}  Verified successfully.`, "success");

                                location.href = '#!/rejection_reason_master/action/inquiry';
                            },
                            function (error) {
                                vm.loader = false;
                                handleErrorResponse(error);
                                $state.go('rejection_reason_master.inquiry');
                            }
                        );
                    } else {
                        $state.go('rejection_reason_master.inquiry');
                    }
                });
        }
        function getInquiryDetails() {

            vm.loader = true;
            var dropDownType = (typeof vm.dropDownType === 'string') ? JSON.parse(vm.dropDownType) : vm.dropDownType;

            var requestData = {

                'fieldCode': dropDownType.fieldCode
            };


            rejectionReasonMasterService.inquiry(requestData).then(function (response) {
                vm.loader = false;
                vm.modifiedList = response.data.result.schemeDetailList;
                vm.AddedList = response.data.result.addedDetailList;
                vm.deleteddList = response.data.result.deleteDetailList;
                vm.audit = response.data.result;
                vm.rejectionRemarks = response.data.result.rejectionRemarks;
            }, function (error) {
                vm.loader = false;
                handleErrorResponse(error);

                $state.go('rejection_reason_master.inquiry');
            });




        }


        const convertToColumnValuesArrayList = (objectToBeConvert) => {
            if (typeof (objectToBeConvert) === "object") {
                let resultArray = [];
                for (let key of Object.keys(objectToBeConvert)) {
                    if (objectToBeConvert[key])
                        resultArray.push({ colName: key, value: objectToBeConvert[key].oldValue })
                }

                return resultArray;
            } else {
                throw "Only objects can be converted"
            }
        }
    }
})();
