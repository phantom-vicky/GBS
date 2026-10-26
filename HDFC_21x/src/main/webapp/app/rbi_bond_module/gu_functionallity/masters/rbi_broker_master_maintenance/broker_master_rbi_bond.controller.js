(function () {
  "use strict";

  angular.module("gbmUltraApp").controller("brokerMasterRbiBondController", brokerMasterRbiBondController);

  brokerMasterRbiBondController.$inject = [
    "$scope",
    "$filter",
    "$state",
    "$rootScope",
    "$stateParams",
    "$location",
    "brokerMasterRbiBondService",
    "commonService",
  ];

  function brokerMasterRbiBondController($scope, $filter, $state, $rootScope, $stateParams, $location, brokerMasterRbiBondService, commonService) {
    var vm = this;

    vm.commonYesNoMetaData = COMMON_YES_NO_METADATA;
    vm.showCancelConfirmation = showCancelConfirmation;
    vm.decimalConvert = decimalConvert;
    vm.dateValidation = dateValidation;
    vm.currentDate = new Date();
    vm.solDate = $rootScope.globals.currentUser.systemDate;
    vm.brokerName = "";
    // vm.checkAndValidateAccNum=checkAndValidateAccNum;
    vm.getState = getState;
    //Add Functions
    vm.submitAddForm = submitAddForm;
    vm.nonHdfcTorchM = nonHdfcTorchM;
    vm.addValidation = addValidation;
    vm.getModuleName = getModuleName;
    vm.convertLabelValue = convertLabelValue;
    vm.solDate = $rootScope.globals.currentUser.systemDate;
    //Verify and Delete Functions
    vm.submitVerifyForm = submitVerifyForm;
    vm.getVerifyDeleteSelect = getVerifyDeleteSelect;
    vm.getVerifyDeleteTorch = getVerifyDeleteTorch;
    vm.submitDeleteForm = submitDeleteForm;

    //Inquiry Functions
    vm.getInquiryTorch = getInquiryTorch;
    vm.getInquirySelect = getInquirySelect;
    vm.getPaymentMetaData = getPaymentMetaData;
    vm.getBrokerGroupMetaData = getBrokerGroupMetaData;
    //Modify Functions
    vm.modifySubmit = modifySubmit;
    vm.getOtherBankDetailsTorch = getOtherBankDetailsTorch;
    vm.modifySelectDetails = modifySelectDetails;
    vm.getModifyTorch = getModifyTorch;
    vm.getBranchCode = getBranchCode;
    vm.getEffectFromDate = getEffectFromDate;
    vm.fromToDateValidation = fromToDateValidation;
    vm.validateAccNumCbs = validateAccNumCbs;
    vm.validateAccNumCbsM = validateAccNumCbsM;
    vm.nonHdfcTorch = nonHdfcTorch;
    // Positions 1–4: letters; 5: 0; 6–11: A–Z / 0–9 (all letters, all digits, or mixed)
    vm.ifscPattern = /^[A-Za-z]{4}0[A-Za-z0-9]{6}$/;
    vm.ifscBranchSuffixPattern = /^[A-Za-z0-9]{6}$/;
    vm.sanitizeIfscCode = sanitizeIfscCode;
    vm.ifscKeypress = ifscKeypress;
    vm.applyIfscToModifyField = applyIfscToModifyField;
    vm.applyIfscToAddField = applyIfscToAddField;
    vm.panPattern = /[a-zA-Z]{5}[0-9]{4}[a-zA-Z]{1}/;
    vm.emailPattern = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.(com|in|co|co\.in)$/i;
    vm.removeChars = removeChars;
    //        vm.deleteUserConfirmation = deleteUserConfirmation;
    vm.getMetaData = getMetaData;
    //        vm.hdfcOtherBank = '';
    vm.operativeHdfcAccNo = "";
    vm.operativeAccNo = "";
    vm.cbsErrorMessage = null;
    vm.brokerageCreateDate = $rootScope.globals.currentUser.systemDate;

    //        if ($state.current.name == 'broker_master_rbi_bond.add_detail') {
    //            if ($stateParams.obj) {
    //                vm.moduleId = $stateParams.obj.moduleId;
    ////                vm.tdsParamCodeDesc = $stateParams.obj.tdsParamCodeDesc;
    ////                vm.tdsParamCode = $stateParams.obj.tdsParamCode;
    ////                vm.effFromDate = $stateParams.obj.effFromDate;
    //            } else {
    //                $state.go('broker_master_rbi_bond.add');
    //            }
    //        }
    if ($state.current.name == "broker_master_rbi_bond.modify_detail") {
      if ($stateParams.obj) {
        vm.brokerAgencyCode = $stateParams.obj.brokerAgencyCode;
        modifySelectDetails();
        getMetaData();
        getState();
        getPaymentMetaData();
        getBrokerGroupMetaData();
      } else {
        $state.go("broker_master_rbi_bond.modify");
      }
    } else if ($state.current.name == "broker_master_rbi_bond.verify_detail") {
      if ($stateParams.obj) {
        vm.brokerAgencyCode = $stateParams.obj.brokerAgencyCode;
        getVerifyDeleteSelect("V");
        getMetaData();
        getState();
        getPaymentMetaData();
        getBrokerGroupMetaData();
      } else {
        $state.go("broker_master_rbi_bond.verify");
      }
    } else if ($state.current.name == "broker_master_rbi_bond.delete_detail") {
      if ($stateParams.obj) {
        vm.brokerAgencyCode = $stateParams.obj.brokerAgencyCode;
        getVerifyDeleteSelect("D");
      } else {
        $state.go("broker_master_rbi_bond.delete");
      }
    } else if ($state.current.name == "broker_master_rbi_bond.inquiry_detail") {
      if ($stateParams.obj) {
        vm.brokerAgencyCode = $stateParams.obj.brokerAgencyCode;
        getInquirySelect();
        getMetaData();
        getState();
        getPaymentMetaData();
        getBrokerGroupMetaData();
      } else {
        $state.go("broker_master_rbi_bond.inquiry");
      }
    }
    //Get Module Name
    function getModuleName() {
      vm.referenceId = "RBI Bond";
      var requestData = {
        referenceId: vm.referenceId,
      };
      brokerMasterRbiBondService.getRbiBondModuleNameDetails(requestData).then(
        function (response) {
          vm.moduleNameList = response.data.result;
        },
        function (error) {
          handleErrorResponse(error);
        }
      );
    }
    vm.validateInput = validateInput;
    vm.minValueOne = minValueOne;
    vm.status = [
      { label: "Enable", value: "Y" },
      { label: "Disable", value: "N" },
    ];
    vm.stateMetaData = [];
$scope.allowAlphaNumericSlash = function(event) {
    var key = event.key;
    var regex = /^[a-zA-Z0-9\/-]$/;
    if (!regex.test(key)) {
        event.preventDefault(); // block input
    }
};
    function minValueOne(event, entity) {
      if (parseInt(event.target.value) == 0) {
        Swal.fire("Error!", "Minimum value is 1", "error");
        vm[entity] = "";
      }
    }
    vm.validateInputAlphaWithSpace = validateInputAlphaWithSpace;
    function validateInputAlphaWithSpace(event, id) {
      commonService.validateInputAlphaWithSpace(event, id);
    }

    function validateInput(event, id) {
      const textBox = document.getElementById(id);

      var charCode = event.which || event.keyCode;
      var charTyped = String.fromCharCode(charCode);

      if (textBox) {
        const cursorPosition = textBox.selectionStart;
        if (cursorPosition == 0 && charTyped === " ") {
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

    function isAllowedIfscCharAtPosition(ch, pos) {
      var upper = String(ch).toUpperCase();
      if (pos < 4) {
        return /[A-Z]/.test(upper);
      }
      if (pos === 4) {
        return upper === "0";
      }
      if (pos >= 5 && pos <= 10) {
        return /[A-Z0-9]/.test(upper);
      }
      return false;
    }

    function sanitizeIfscCode(value) {
      if (value == null || value === "") {
        return "";
      }

      var normalized = String(value).toUpperCase().replace(/\s+/g, "");
      var result = "";

      for (var idx = 0; idx < normalized.length && result.length < 11; idx++) {
        var ch = normalized.charAt(idx);
        var pos = result.length;

        if (isAllowedIfscCharAtPosition(ch, pos)) {
          result += ch.toUpperCase();
        }
      }

      return result;
    }

    function ifscKeypress(event) {
      var charCode = event.which || event.keyCode;
      if (charCode <= 31) {
        return;
      }

      var charTyped = String.fromCharCode(charCode);
      var input = event.target;
      if (!input) {
        return;
      }

      var value = input.value || "";
      var start = typeof input.selectionStart === "number" ? input.selectionStart : value.length;
      var end = typeof input.selectionEnd === "number" ? input.selectionEnd : value.length;
      var pos = start;

      if (value.length >= 11 && start === end && start >= 11) {
        event.preventDefault();
        return;
      }

      if (!isAllowedIfscCharAtPosition(charTyped, pos)) {
        event.preventDefault();
      }
    }

    function applyIfscToModifyField() {
      if (!vm.brokerMasterList || !vm.brokerMasterList.C026) {
        return;
      }
      var sanitized = sanitizeIfscCode(vm.brokerMasterList.C026.oldValue);
      if (sanitized !== vm.brokerMasterList.C026.oldValue) {
        vm.brokerMasterList.C026.oldValue = sanitized;
      }
    }

    function applyIfscToAddField() {
      var sanitized = sanitizeIfscCode(vm.instIfscCode);
      if (sanitized !== vm.instIfscCode) {
        vm.instIfscCode = sanitized;
      }
    }

    function getEffectFromDate() {
      vm.loader = true;
      var requestData = {
        table: "T226",
        columnList: ["C004"],
        conditionList: [
          {
            colName: COLUMN_SHORT_CODES.VERIFIED_STATUS,
            value: "Y",
            condition: CONDITIONS.EQUAL,
            order: 1,
          },
          {
            colName: COLUMN_SHORT_CODES.IS_DELETED,
            value: "N",
            condition: CONDITIONS.EQUAL,
            conjunction: CONJUNCTIONS.AND,
            order: 2,
          },
          {
            colName: "IS_ACT",
            value: "Y",
            condition: CONDITIONS.EQUAL,
            conjunction: CONJUNCTIONS.AND,
            order: 3,
          },
        ],
      };
      commonService.genericTableSelectRbiBond(requestData).then(
        function (response) {
          vm.loader = false;
          vm.effFromDate = new Date(response.data.result[0].C004);
          vm.effFromDateOld = new Date(response.data.result[0].C004);
          vm.effFromDateOptionsM = {
            minDate: vm.effFromDate,
            showWeeks: false,
          };
        },
        function (error) {
          vm.loader = false;
          handleErrorResponse(error);
        }
      );
    }

    vm.dateOptionsM = {
      minDate: new Date(),
      showWeeks: false,
    };

    function convertLabelValue(list) {
      for (var item in list) {
        list[item].value = list[item].SUBCODE;
        list[item].label = list[item].DESCRIPTION;
      }
      return list;
    }

    // Modify Select details
    function modifySelectDetails() {
      vm.loader = true;
      vm.searchSolId = "";

      var requestData = {
        brokerAgencyCode: vm.brokerAgencyCode,
      };

      brokerMasterRbiBondService.getModifySelect(requestData).then(
        function (response) {
          vm.loader = false;
          vm.remarks = response.data.result.remarks;
          vm.verifyDetailsList = response.data.result;
          vm.verificationDetails = response.data.result.prevOperType;
          vm.brokerMasterList = response.data.result.masterOldNewValueModel;

          vm.id = vm.verifyDetailsList.pkColumnValue;

          // Convert the date string to a JavaScript Date object
          if (vm.brokerMasterList && vm.brokerMasterList.C004 && vm.brokerMasterList.C004.oldValue) {
            var dateParts = vm.brokerMasterList.C004.oldValue.split("/");
            // Date constructor takes arguments in the order: year, month (0-indexed), day
            vm.brokerMasterList.C004.oldValue = new Date(dateParts[2], dateParts[1] - 1, dateParts[0]);
          }
          if (vm.brokerMasterList && vm.brokerMasterList.C021 && vm.brokerMasterList.C021.oldValue) {
            var dateParts = vm.brokerMasterList.C021.oldValue.split("/");
            // Date constructor takes arguments in the order: year, month (0-indexed), day
            vm.brokerMasterList.C021.oldValue = new Date(dateParts[2], dateParts[1] - 1, dateParts[0]);
          }
          if (vm.brokerMasterList) {
            if (!vm.brokerMasterList.C026) {
              vm.brokerMasterList.C026 = { oldValue: "", newValue: "" };
            } else if (vm.brokerMasterList.C026.oldValue != null && vm.brokerMasterList.C026.oldValue !== "") {
              vm.brokerMasterList.C026.oldValue = sanitizeIfscCode(vm.brokerMasterList.C026.oldValue);
            }
          }
        },
        function (error) {
          handleErrorResponse(error);
          $state.go("broker_master_rbi_bond.modify");
          vm.loader = false;
        }
      );
    }

    /* Verify/Delete Select Details */
    function getVerifyDeleteSelect(flg) {
      vm.loader = true;
      vm.searchSolId = "";
      var requestData = {
        brokerAgencyCode: vm.brokerAgencyCode,
      };
      brokerMasterRbiBondService.getVerifyDelSelectDetails(requestData).then(
        function (response) {
          vm.loader = false;
          vm.verifyDetailsList = response.data.result;
          vm.verificationDetails = response.data.result.prevOperType;
          vm.tdsParameterList = response.data.result.masterOldNewValueModel;
          vm.tdsParameterList.C004.oldValue = $filter("date")(vm.tdsParameterList.C004.oldValue, "dd-MMM-yyyy");
        },
        function (error) {
          handleErrorResponse(error);
          if (flg == "V") $state.go("broker_master_rbi_bond.verify");
          else $state.go("broker_master_rbi_bond.delete");
          vm.loader = false;
        }
      );
    }

    function getBranchCode() {
      vm.loader = true;
      var requestData = {
        moduleId: vm.moduleId,
      };
      brokerMasterRbiBondService.getBranches(requestData).then(
        function (responseOne) {
          vm.loader = false;
          vm.branchCodeList = responseOne.data.result.branchList;
          angular.element("#getBranchCode").modal("toggle");
        },
        function (errorOne) {
          vm.schemeCode = "";
          vm.loader = false;
          handleErrorResponse(errorOne);
        }
      );
    }

    function getMetaData() {
      vm.loader = true;
      vm.referenceId = "TYPE_OF_BROKER";
      var requestData = {
        referenceId: vm.referenceId,
      };
      brokerMasterRbiBondService.getMetaData(requestData).then(
        function (response) {
          vm.loader = false;
          vm.brokerTypeList = response.data.result;
        },
        function (error) {
          handleErrorResponse(error);
          vm.loader = false;
        }
      );
    }
    function getState() {
      vm.loader = true;
      vm.referenceId = "STATES OF INDIA";
      var requestData = {
        referenceId: vm.referenceId,
      };
      brokerMasterRbiBondService.getMetaData(requestData).then(
        function (response) {
          vm.loader = false;
          vm.stateList = response.data.result;
          vm.stateMetaData = response.data.result.map(function (item) {
                return {
                  value: item.SUBCODE,
                  label: item.DESCRIPTION
                }});
        },
        function (error) {
          handleErrorResponse(error);
          vm.loader = false;
        }
      );
    }
    function getPaymentMetaData() {
      vm.loader = true;
      vm.referenceId = "PAYMENT_MODE";
      var requestData = {
        referenceId: vm.referenceId,
      };
      brokerMasterRbiBondService.getMetaData(requestData).then(
        function (response) {
          vm.loader = false;
          vm.paymentTypeList = response.data.result;
        },
        function (error) {
          handleErrorResponse(error);
          vm.loader = false;
        }
      );
    }
    function getBrokerGroupMetaData() {
      vm.loader = true;
      vm.referenceId = "BROKER_GROUP";
      var requestData = {
        referenceId: vm.referenceId,
      };
      brokerMasterRbiBondService.getMetaData(requestData).then(
        function (response) {
          vm.loader = false;
          vm.brokerGroupList = response.data.result;
        },
        function (error) {
          handleErrorResponse(error);
          vm.loader = false;
        }
      );
    }

    /* Add Submit */
    function submitAddForm() {
//      Swal.fire(
//        {
//          title: "Are you sure?",
//          text: "Do you want to Really Add this record?",
//          type: "warning",
//          showCancelButton: true,
//          cancelButtonClass: "btn-cancel",
//          confirmButtonClass: "btn-primary",
//          confirmButtonText: "Yes, Add it!",
//          closeOnConfirm: false,
//          showLoaderOnConfirm: true,
//        },
//        function () {


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
            brokerAgencyCode: vm.brokerAgencyCode,
            brokerName: vm.brokerName,
            brokerageCreateDate: $filter("date")(vm.brokerageCreateDate, "dd-MMM-yyyy"),
            brokerGroup: vm.brokerGroup,
            brokerSortCode: vm.brokerSortCode,
            //                       'hdfcOtherBank':vm.hdfcOtherBank,
            bankName: vm.nonBankName,
            hdfcBranchCode: vm.branchCode,
            typeOfBroker: vm.brokerType,
            gst: vm.gstNo,
            pan: vm.panTan,
            ifsc: vm.instIfscCode,
            address1: vm.address1,
            address2: vm.address2,
            mobileNo: vm.mobileNumber,
            email: vm.emailId,
            remarks: vm.remarks,
            paymentMode: vm.paymentMode,
            ddAccNo: vm.ddAccNo,
            nonBankName: vm.nonBankName,
            operativeAccNo: vm.operativeAccNo,
            instIfscCode: vm.instIfscCode,
            micrCodeLoc: vm.micrCodeLoc,
            micrBranchCode: vm.micrBranchCode,
            micrBankCode: vm.micrBankCode,
            nameOfthePayee: vm.nameOfthePayee,
            bankCode: vm.bankCode,
            branchCode: vm.branchCode,
            pinCode: vm.pinCode,
            city: vm.city,
            state: vm.state,
            phoneNumber: vm.phoneNumber,
            fax: vm.fax,
          };
          brokerMasterRbiBondService.submitAddForm(requestData).then(
            function (response) {
              Swal.fire("Added!", "Broker Master Maintenance Added Successfully", "success");
              $state.go("broker_master_rbi_bond.inquiry");
            },
            function (error) {
             vm.loader = false;
           if(error.data.message == 'No Records Found' | error.data.message == 'NDF'){
             vm.noDataFound = error.data.message;
             Swal.fire("Warning!",vm.noDataFound,"warning");
           }else if(error.statusCode == '0' | error.status =='500'){
             Swal.fire("Warning!",error.message,"warning");
           }else{
             handleErrorResponse(error);
             $state.go("broker_master_rbi_bond.add");
             }
            }
          );
       } }
      );
    }

    function getOtherBankDetailsTorch(flag) {
      //                                                         vm.loader = true;
      //                                                             var requestData = {};
      //                                                            bondLedgerAccSubscriptionService.getOtherBankDetails(requestData).then(function(response){
      //                                                                 vm.loader = false;
      //                                                                 vm.otherBankDetailsTorchList = response.data.result.torchValue;
      //                                                                 angular.element('#otherBankDetailsModal').modal('toggle');
      //                                                             },function(error) {
      //                                                                  vm.loader = false;
      //                                                                handleErrorResponse(error);
      //                                                              });
      //
      //                                                     }
      if (flag == "torch") {
        vm.loader = true;
        vm.searchSolId = "";
        var requestData = {};
        brokerMasterRbiBondService.getNonHdfcTorch1(requestData).then(
          function (response) {
            vm.loader = false;
            vm.otherBankDetailsTorchList = response.data.result.torchValue;
            angular.element("#otherBankDetailsModal").modal("toggle");
          },
          function (error) {
            handleErrorResponse(error);
            vm.loader = false;
          }
        );
      } else {
        vm.loader = true;
        vm.searchSolId = "";
        var requestData = {
          bankCodeNonMicr: vm.bankCode,
        };
        brokerMasterRbiBondService.getNonHdfcBranchTorch(requestData).then(
          function (response) {
            vm.loader = false;
            vm.otherBankDetailsTorchList = response.data.result.torchValue;
            angular.element("#otherBankDetailsModal1").modal("toggle");
          },
          function (error) {
            handleErrorResponse(error);
            vm.loader = false;
          }
        );
      }
    }
    vm.rejectReason = rejectReason;
    function rejectReason() {
      angular.element("#permissionMappingDelete").modal("toggle");
    }
    function convertToColumnValuesArrayOldNew(objectToBeConvert) {
      if (typeof objectToBeConvert === "object") {
        let resultArray = [];
        for (let key of Object.keys(objectToBeConvert)) {
          if (objectToBeConvert[key])
            resultArray.push({
              colName: key,
              value: objectToBeConvert[key].oldValue,
            });
        }
        return resultArray;
      } else {
        throw "Only objects can be converted";
      }
    }

    /* Submit Modify*/
    function modifySubmit() {
//      Swal.fire(
//        {
//          title: "Are you sure?",
//          text: "Do you want to Really Modify this record?",
//          type: "warning",
//          showCancelButton: true,
//          cancelButtonClass: "btn-cancel",
//          confirmButtonClass: "btn-primary",
//          confirmButtonText: "Yes",
//          closeOnConfirm: false,
//        },
//        function () {


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

          vm.brokerMasterListModel = JSON.parse(JSON.stringify(vm.brokerMasterList));
          vm.brokerMasterListModel.C021.oldValue = $filter("date")(vm.brokerMasterListModel.C021.oldValue, "dd-MMM-yyyy");
          var requestData = {
            id: vm.id,
            columnValues: convertToColumnValuesArrayOldNew(vm.brokerMasterListModel),
          };
          brokerMasterRbiBondService.submitModifyForm(requestData).then(
            function (response) {
              vm.loader = false;
              Swal.fire("Modified!", "Broker Master Maintenance Modified Successfully", "success");
              $state.go("broker_master_rbi_bond.inquiry");
            },
            function (error) {
             vm.loader = false;
           if(error.data.message == 'No Records Found' | error.data.message == 'NDF'){
             vm.noDataFound = error.data.message;
             Swal.fire("Warning!",vm.noDataFound,"warning");
           }else if(error.statusCode == '0' | error.status =='500'){
             Swal.fire("Warning!",error.message,"warning");
           }else{
             handleErrorResponse(error);
             $state.go("broker_master_rbi_bond.modify");
             }
            }
          );
       } }
      );
    }

    /* Verify Submit */
    function submitVerifyForm() {
//      Swal.fire(
//        {
//          title: "Are you sure?",
//          text: "Do you want to Really Verify this record?",
//          type: "warning",
//          showCancelButton: true,
//          cancelButtonClass: "btn-cancel",
//          confirmButtonClass: "btn-primary",
//          confirmButtonText: "Yes",
//          closeOnConfirm: false,
//        },
//
//        function () {


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
            id: vm.verifyDetailsList.pkColumnValue,
            //                        'versionNo': vm.versionNo,
            //                        'effFromDate': $rootScope.formatDateToDDMMMYYYY(convertToDateObject(vm.tdsParameterList.C004.oldValue))
          };
          brokerMasterRbiBondService.submitVerifyForm(requestData).then(
            function (response) {
              vm.loader = false;
              Swal.fire("Verified!", "Broker Master Maintenance Verified Successfully", "success");
              $state.go("broker_master_rbi_bond.inquiry");
            },
            function (error) {
              vm.loader = false;
              handleErrorResponse(error);
              $state.go("broker_master_rbi_bond.verify");
            }
          );
      }  }
      );
    }

    /* Delete Submit */
    //      function submitDeleteForm() {
    //          Swal.fire({
    //              title: "Rejection Remarks",
    //              text: "",
    //              type: "input",
    //              inputPlaceholder: "Enter your remarks here",
    //              showCancelButton: true,
    //              cancelButtonClass: "btn-cancel",
    //              confirmButtonClass: "btn-primary",
    //              confirmButtonText: "Submit",
    //              animation: "slide-from-top",
    //              closeOnConfirm: false,
    //          },
    //          function(inputValue) {
    //              if (inputValue === false) return false;
    //
    //              if (inputValue === "") {
    //                  Swal.fire.showInputError("Please enter the remarks!");
    //                  return false;
    //              }
    //
    //
    //              vm.remarks = inputValue;
    //
    //
    //              var requestData = {
    //               'id': vm.verifyDetailsList.pkColumnValue,
    //                  remarks: vm.remarks
    //              };
    //
    //              brokerMasterRbiBondService.submitDeleteForm(requestData).then(function(response) {
    //                  if (response.data.message == 'SUCCESS') {
    //                      Swal.fire("Rejected!", "Broker Master Maintenance Rejected Successfully", "success");
    //                           $state.go('broker_master_rbi_bond.inquiry');
    //
    //                  } else {
    //                      vm.loader = false;
    //                      Swal.fire("WARNING!", response.data.message, "error");
    //
    //                  }
    //              }, handleErrorResponse);
    //          });
    //      }
    vm.getRemarksList = function () {
      vm.referenceId = "056";
      var requestData = { referenceId: vm.referenceId };

      brokerMasterRbiBondService.getRemarksList(requestData).then(
        function (response) {
          vm.remarksList = response.data.result || []; // Ensure it's an array
        },
        function (error) {
          handleErrorResponse(error);
        }
      );
    };

    function submitDeleteForm() {
      vm.loader = true;
      var requestData = {
        id: vm.verifyDetailsList.pkColumnValue,
        remarks: vm.remarks,
      };
      brokerMasterRbiBondService.submitDeleteForm(requestData).then(
        function (response) {
          if (response.data.message == "SUCCESS") {
            Swal.fire("Rejected!", "Broker Master Maintenance Rejected Successfully", "success");
            $state.go("broker_master_rbi_bond.inquiry");
          } else {
            Swal.fire("WARNING!", response.data.message, "error");
            $state.go("broker_master_rbi_bond.verify");
          }
          const backdrop = document.querySelector(".modal-backdrop");
          if (backdrop) {
            backdrop.parentNode.removeChild(backdrop);
            document.body.classList.remove("modal-open");
          }
        },
        function (error) {
          vm.loader = false;
          handleErrorResponse(error);
          const backdrop = document.querySelector(".modal-backdrop");
          if (backdrop) {
            backdrop.parentNode.removeChild(backdrop);
            document.body.classList.remove("modal-open");
          }
          location.href = "#!/broker_master_rbi_bond/action/inquiry";
        }
      );
    }

    // Cancel Confirmation
    function showCancelConfirmation(state) {
//      Swal.fire(
//        {
//          title: "Confirm Cancel",
//          text: "Do you really want to cancel?",
//          type: "warning",
//          showCancelButton: true,
//          cancelButtonClass: "btn-cancel",
//          confirmButtonClass: "btn-danger",
//          confirmButtonText: "Yes",
//          cancelButtonText: "No",
//          closeOnConfirm: true,
//        },
//        function () {



               Swal.fire({
                 title: "Are you sure?",
                 text: "Do you Really Want to cancel?",
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
     }   }
      );
    }

    //Get Inquiry Version No Tourch Details
    function getInquiryTorch() {
      vm.loader = true;
      vm.searchSolId = "";
      var requestData = {
        //                   'brokerAgencyCode': vm.brokerAgencyCode
      };
      brokerMasterRbiBondService.getInquirySelectDetails(requestData).then(
        function (response) {
          vm.loader = false;
          vm.inquiryTorchList = response.data.result.torchValue;
          angular.element("#rbiCodeModal").modal("toggle");
        },
        function (error) {
          handleErrorResponse(error);
          vm.loader = false;
        }
      );
    }

    function nonHdfcTorchM(flag) {
      vm.loader = true;
      vm.searchSolId = "";
      var requestData = {};
        if(flag == 'bankName'){

                 requestData.bankFlag = "B";
                }
                if(flag == 'ifsc'){


          requestData.bankCode = vm.brokerMasterBankCode;

              // requestData.instIfscCode = vm.brokerMasterList.C026.oldValue ? vm.brokerMasterList.C026.oldValue : '';

                }
                if(flag == 'micrCodeBank'){



                  requestData.bankCode = vm.brokerMasterBankCode;
                //   requestData.micrBankCode = vm.brokerMasterList.C028.oldValue ? vm.brokerMasterList.C028.oldValue : '';

                }
                if(flag == 'micrBranchCode'){

                   requestData.bankCode = vm.brokerMasterBankCode;
                 //    requestData.micrBranchCode = vm.brokerMasterList.C029.oldValue ? vm.brokerMasterList.C029.oldValue : '';

                }
      brokerMasterRbiBondService.getNonHdfcTorch(requestData).then(
        function (response) {
          vm.loader = false;
          vm.nonHdfcTorchList = response.data.result.torchValue;
          if(flag == 'bankName'){
          angular.element("#rbiCodeModal").modal("toggle");
          }else{
          angular.element("#rbiCodeModalIfsc").modal("toggle");
          }
        },
        function (error) {
          handleErrorResponse(error);
          vm.loader = false;
        }
      );
    }
    function nonHdfcTorch(flag) {
          vm.loader = true;
          vm.searchSolId = "";
          var requestData = {};
          if(flag == 'bankName'){
          requestData.nonBankName = vm.nonBankName;
           requestData.bankFlag = "B";
           //requestData.getBankFlagg ="B";
          }
          if(flag == 'ifsc'){
          requestData.bankCode = vm.bankCode;
          requestData.instIfscCode = vm.instIfscCode;


          }
          if(flag == 'micrCodeBank'){
          requestData.bankCode = vm.bankCode;
          requestData.micrBankCode = vm.micrBankCode;


          }
          if(flag == 'micrBranchCode'){
          requestData.bankCode = vm.bankCode;
          requestData.micrBranchCode = vm.micrBranchCode;


          }
          brokerMasterRbiBondService.getNonHdfcTorch(requestData).then(
            function (response) {




              vm.loader = false;
              vm.nonHdfcTorchList = response.data.result.torchValue;


//            if(flag == 'bankName'){
//              vm.nonHdfcTorchList = vm.nonHdfcTorchList.filter((value, index, self) =>
//                index === self.findIndex((t) => (
//                  t.bankName === value.bankName
//                ))
//              );
//              vm.nonHdfcTorchList = $filter('orderBy')(vm.nonHdfcTorchList, 'bankName');
//
//              }



              if(flag == 'bankName'){
              angular.element("#rbiCodeModal").modal("toggle");
              }else{
              angular.element("#rbiCodeModalIfsc").modal("toggle");
              }
            },
            function (error) {
              handleErrorResponse(error);
              vm.loader = false;
            }
          );
        }

    //Get Inquiry Select Details
    function getInquirySelect() {
      vm.loader = true;
      vm.searchSolId = "";
      var requestData = {
        brokerAgencyCode: vm.brokerAgencyCode,
      };
      brokerMasterRbiBondService.getInquirySelectDetails(requestData).then(
        function (response) {
          vm.loader = false;
          vm.verifyDetailsList = response.data.result;
          vm.verificationDetails = response.data.result.prevOperType;
          vm.tdsParameterList = response.data.result.masterOldNewValueModel;
          vm.remarks = response.data.result.remarks;
        },
        function (error) {
          handleErrorResponse(error);
          vm.loader = false;
        }
      );
    }

    //add validation function
    function addValidation() {
      vm.loader = true;
      var requestData = {
        brokerAgencyCode: vm.brokerAgencyCode,
      };
      //                        // Ensure input is numeric and pad with leading zeros if necessary
      //                                 if (isNaN(requestData.brokerAgencyCode)) {
      //                                     requestData.brokerAgencyCode = requestData.brokerAgencyCode.toString().padStart(6, '0');
      //                                 } else {
      //                                     vm.loader = false;
      //                                     vm.brokerAgencyCode = '';
      //                                     vm.brokerAgencyCodeErrorMessage = 'Broker Agency Code must be ';
      //                                     return;
      //                                 }
      brokerMasterRbiBondService.addValidation(requestData).then(
        function (response) {
          vm.loader = false;
          vm.brokerAgencyCodeErrorMessage = null;
        },
        function (error) {
          vm.loader = false;
          vm.brokerAgencyCode = "";
          vm.brokerAgencyCodeErrorMessage = error.message;
          //$state.go('broker_master_rbi_bond.inquiry');
          //handleErrorResponse(error);
        }
      );
    }

    function decimalConvert(flg, errorMsg, modifyFlg) {
      let decimal = parseFloat(flg);

      if (decimal < 100) {
        if (errorMsg == "P1") vm.tdsRatePanError = false;
        if (errorMsg == "P2") vm.tdsRateNoPanError = false;
        if (errorMsg == "P3") vm.educationCessError = false;
        if (errorMsg == "P4") vm.otherCessError = false;

        return true;
      } else {
        if (errorMsg == "P1") {
          vm.tdsRatePanError = "Min 2 Digit Decimal Number(" + decimal + ")";
          vm.tdsRatePan = "";
          if (modifyFlg) {
            if (vm.tdsParameterList.tdsRatePan) vm.tdsParameterList.tdsRatePan = "";
          }
          return false;
        }
        if (errorMsg == "P2") {
          vm.tdsRateNoPanError = "Min 2 Digit Decimal Number(" + decimal + ")";
          vm.tdsRateNoPan = "";
          if (modifyFlg) {
            if (vm.tdsParameterList.tdsRateNoPan) vm.tdsParameterList.tdsRateNoPan = "";
          }
          return false;
        }
        if (errorMsg == "P3") {
          vm.educationCessError = "Min 2 Digit Decimal Number(" + decimal + ")";
          vm.educationCess = "";
          if (modifyFlg) {
            if (vm.tdsParameterList.educationCess) vm.tdsParameterList.educationCess = "";
          }
          return false;
        }
        if (errorMsg == "P4") {
          vm.otherCessError = "Min 2 Digit Decimal Number(" + decimal + ")";
          vm.otherCess = "";
          if (modifyFlg) {
            if (vm.tdsParameterList.otherCess) vm.tdsParameterList.otherCess = "";
          }
          return false;
        }
      }
    }

    //verify and delete torch function
    function getVerifyDeleteTorch(flag) {
      vm.loader = true;
      vm.searchSolId = "";
      var requestData = {};
      if (flag == "torch") {
      } else {
        requestData.brokerAgencyCode = vm.brokerAgencyCode;
      }

      brokerMasterRbiBondService.getVerifyDelSelectDetails(requestData).then(
        function (response) {
          vm.loader = false;
          if (flag == "torch") {
            vm.rbiCodeList = response.data.result.torchValue;
            angular.element("#rbiCodeModal").modal("toggle");
          } else {
            vm.branchCodeList = response.data.result.torchValue;
            vm.versionNo = vm.branchCodeList[0].versionNo;
            vm.effectiveFromDate = vm.branchCodeList[0].effectiveFromDate;
          }
        },
        function (error) {
          handleErrorResponse(error);
          vm.loader = false;
        }
      );
    }

    /*Operative Acct Num*/
    //
    //              function checkAndValidateAccNum(acctNum) {
    //                            if (vm.hdfcOtherBank === 'Y') {
    //                                vm.validateAccNumCbs(acctNum);
    //                            }
    //                        };

    function validateAccNumCbs(acctNum) {
      if (acctNum) {
        vm.loader = true;
        commonService.getCustomAcctInqApi(
          acctNum.toUpperCase(),
          function (response) {
            if (response.success) {
              vm.accList = response.data;
              vm.loader = false;
              if (vm.accList.length > 0) {
                if (vm.accList[0].status == "S") {
                  if (vm.accList[0].accountFreezeStatus) {
                    vm.cbsErrorMessage = "Account is Friezed";
                    vm.branchId = null;
                    vm.operativeHdfcAccNo = null;
                    vm.operativeAccNo = null;
                  } else {
                    vm.cbsErrorMessage = null;
                    vm.branchId = vm.accList[0].solID;
                  }
                }
                if (vm.accList[0].status == "F") {
                  vm.cbsErrorMessage = "Enter valid account number";
                  vm.branchId = null;
                  vm.operativeHdfcAccNo = null;
                  vm.operativeAccNo = null;
                }
              } else {
                vm.cbsErrorMessage = "Enter valid account number";
                vm.branchId = null;
                vm.operativeHdfcAccNo = null;
                vm.operativeAccNo = null;
              }
            } else {
              vm.branchId = null;
              vm.operativeHdfcAccNo = null;
              vm.operativeAccNo = null;

              Swal.fire("Error!", response.message, "error");
              vm.loader = false;
            }
          },
          function (err) {
            vm.branchId = null;
            vm.operativeHdfcAccNo = null;
            vm.operativeAccNo = null;

            vm.loader = false;
            handleErrorResponse(err);
          }
        );
      }
    }

    // Watch for changes to the hdfcOtherBank model
    /* $scope.$watchGroup(['vm.hdfcOtherBank', 'vm.brokerMasterList.C007.oldValue'], function(newValues, oldValues) {
                          if (newValues !== oldValues) {
                              // Clear the error message when the radio button value changes
                              vm.cbsErrorMessage = null;
                          }
                      });*/

    function validateAccNumCbsM(acctNum) {
      if (acctNum) {
        vm.loader = true;
        commonService.getCustomAcctInqApi(
          acctNum.toUpperCase(),
          function (response) {
            if (response.success) {
              vm.accList = response.data;
              vm.loader = false;
              if (vm.accList.length > 0) {
                if (vm.accList[0].status == "S") {
                  if (vm.accList[0].accountFreezeStatus) {
                    vm.cbsErrorMessage = "Account is Friezed";
                    vm.brokerMasterList.C005.oldValue = null;
                  } else {
                    vm.cbsErrorMessage = null;
                    vm.brokerMasterList.C005.oldValue = vm.accList[0].solID;
                  }
                }
                if (vm.accList[0].status == "F") {
                  vm.cbsErrorMessage = vm.accList[0].errDesc;
                  vm.brokerMasterList.C005.oldValue = null;
                }
              } else {
                vm.cbsErrorMessage = "Invalid Account Number";
                vm.brokerMasterList.C005.oldValue = null;
              }
            } else {
              vm.brokerMasterList.C005.oldValue = null;
              Swal.fire("Error!", response.message, "error");
              vm.loader = false;
            }
          },
          function (err) {
            vm.brokerMasterList.C005.oldValue = null;
            vm.loader = false;
            handleErrorResponse(err);
          }
        );
      }
    }

    function getModifyTorch() {
      vm.loader = true;
      vm.searchSolId = "";
      var requestData = {
        //                    'brokerAgencyCode': vm.brokerAgencyCode,
      };
      brokerMasterRbiBondService.getModifySelect(requestData).then(
        function (response) {
          vm.loader = false;
          vm.rbiCodeList = response.data.result.torchValue;
          angular.element("#rbiCodeModal").modal("toggle");
        },
        function (error) {
          handleErrorResponse(error);
          vm.loader = false;
        }
      );
    }

    function dateValidation() {
      vm.dateOptions = {
        minDate: vm.currentDate,
      };
    }

    vm.dateOptionsM = {
      minDate: new Date(),
    };

    function removeChars(str) {
      vm.brokerName = str.replace("_", "");
    }
    function fromToDateValidation() {
      vm.toDate = "";
      vm.dobCalenderOptions = {
        minDate: vm.solDate,
        maxDate: vm.solDate,
      };
      vm.dateOfBirth = {
        minDate: vm.solDate,
      };
    }




     vm.textableDatePicker=textableDatePicker;
                        vm.autoslashing=autoslashing;
                        vm.futureNotAllowed=futureNotAllowed;


                function textableDatePicker(id,valueDate)
                {
                 commonService.textableDatePicker(id,valueDate);
                }


                function autoslashing(id)

                {
                 commonService.autoslashing(id);

                }



                function futureNotAllowed(id,valueDate)
                {
                 commonService.futureNotAllowed(id,valueDate);
                }







  }
})();
