(function () {
  "use strict";

  angular.module("gbmUltraApp").controller("StatewiseSgstMasterForBrokerageController", StatewiseSgstMasterForBrokerageController);

  StatewiseSgstMasterForBrokerageController.$inject = [
    "$scope",
    "$filter",
    "$state",
    "$stateParams",
    "$location",
    "StatewiseSgstMasterForBrokerage",
    "commonService",
  ];

  function StatewiseSgstMasterForBrokerageController(
    $scope,
    $filter,
    $state,
    $stateParams,
    $location,
    StatewiseSgstMasterForBrokerage,
    commonService
  ) {
    var vm = this;

    vm.commonYesNoMetaData = COMMON_YES_NO_METADATA;
    vm.addSubmitForm = addSubmitForm;
    vm.verifySubmit = verifySubmit;
    vm.modifySubmit = modifySubmit;
    vm.deleteSubmit = deleteSubmit;
    vm.inquirySubmit = inquirySubmit;
    vm.modifyDetails = modifyDetails;
    vm.verifyDetails = verifyDetails;
    vm.getInquiryDetails = getInquiryDetails;
    vm.getAddDetails = getAddDetails;
    vm.rejectReason = rejectReason;
    vm.currentPage = 1;
    vm.itemsPerPage = "5";
    vm.referenceId = "";
    if ($state.current.name == "state_wise_sgst_master_for_brokerage.inquiry_detail") {
      if ($stateParams.obj) {
        vm.stateCode = $stateParams.obj.stateCode;
        vm.schemeCode = $stateParams.obj.schemeCode;
        vm.schemeName = $stateParams.obj.schemeName;
        vm.stateName = $stateParams.obj.stateName;
        vm.sgstRate = $stateParams.obj.sgstRate;
        vm.inquirySubmit();
      }
    } else if ($state.current.name == "state_wise_sgst_master_for_brokerage.verify_detail") {
      if ($stateParams.obj) {
        vm.stateCode = $stateParams.obj.stateCode;
        vm.schemeCode = $stateParams.obj.schemeCode;
        vm.schemeName = $stateParams.obj.schemeName;
        vm.stateName = $stateParams.obj.stateName;
        vm.sgstRate = $stateParams.obj.sgstRate;
        vm.verifyDetails();
      }
    } else if ($state.current.name == "state_wise_sgst_master_for_brokerage.modify_detail") {
      if ($stateParams.obj) {
        vm.stateCode = $stateParams.obj.stateCode;
        vm.schemeCode = $stateParams.obj.schemeCode;
        vm.schemeName = $stateParams.obj.schemeName;
        vm.stateName = $stateParams.obj.stateName;
        vm.sgstRate = $stateParams.obj.sgstRate;
        vm.modifyDetails();
      }
    }

    function getInquiryDetails(flg) {
      vm.loader = true;
      if (flg === "schemeTorch") {
        vm.schemeCode = null;
        vm.stateCode = null;
        vm.stateName = null;
        vm.schemeName = null;
      } else if (flg === "stateTorch") {
        vm.stateCode = null;
      }
      var requestData = {
        schemeCode: vm.schemeCode,
        schemeName: vm.schemeName,
        stateCodeId: vm.stateCode,
        stateName: vm.stateName,
      };
      StatewiseSgstMasterForBrokerage.getInquiryDetails(requestData).then(
        function (response) {
          vm.loader = false;
          if (flg === "stateTorch") {
            vm.stateNameList = response.data.result.list;
            angular.element("#StateNameModal").modal("toggle");
          } else if (flg === "schemeTorch") {
            vm.schemeNameList = response.data.result.list;
            angular.element("#levelNameModal").modal("toggle");
          }
        },
        function (error) {
          vm.loader = false;
          if ((error.data.message == "No Records To Inquire") | (error.data.message == "NDF")) {
            vm.noDataFound = error.data.message;
            Swal.fire("Warning!", vm.noDataFound, "warning");
          } else {
            handleErrorResponse(error);
            $state.go("state_wise_sgst_master_for_brokerage.inquiry");
          }
        }
      );
    }

    vm.firstCharDot = firstCharDot;
    function firstCharDot(event) {
      if (event && event[0] === ".") {
        vm.sgstRate = "";
        return;
      }
    }
    vm.firstCharDotM = firstCharDotM;
    function firstCharDotM(event) {
      if (event && event[0] === ".") {
        vm.sgstRate.C003.oldValue = "";
        return;
      }
    }
    vm.formatPercentage = formatPercentage;
    function formatPercentage(value, mode) {
      // Ensure the value is a valid number
      let sanitizedValue = String(value).trim(); // Convert value to string if it's not already
      // Remove any non-digit characters except for the decimal point
      sanitizedValue = sanitizedValue.replace(/[^0-9.]/g, "");
      // If there is a decimal, separate it
      let [integerPart, decimalPart] = sanitizedValue.split(".");
      if (mode == "A") {
        // Limit integer part to two digits
        if (integerPart.length > 2 && integerPart !== "100") {
          integerPart = integerPart.substring(0, 2); // Take only the first two digits
          vm.sgstRate = integerPart;
          return;
        }
      } else if (mode == "M") {
        // Limit integer part to two digits
        if (integerPart.length > 2 && integerPart !== "100") {
          integerPart = integerPart.substring(0, 2); // Take only the first two digits
          vm.sgstRate.C003.oldValue = integerPart;
          return;
        }
      }
      return value; // Return the original value if it's invalid or empty
    }

    vm.percentageCheckNew = percentageCheckNew;
    function percentageCheckNew(event) {
      let inputValue = event.target.value;
      let key = event.key;
      // Combine the existing value with the new key
      let combinedValue = inputValue + key;
      // Regular expression to match allowed formats
      let regex = /^(100(\.0{1,2})?|[1-9]?\d(\.\d{0,2})?)$/;
      // Check if the combined value matches the regex pattern
      if (!regex.test(combinedValue)) {
        event.preventDefault();
      }
    }
    function addSubmitForm() {
//      Swal.fire(
//        {
//          title: "Are you sure?",
//          text: "Do you want to Really Add this record?",
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
            //'stateCodeId':vm.state_code,
            stateCodeId: vm.stateCode,
            schemeCode: vm.schemeCode,
            stateName: vm.stateName,
            sgstRate: parseFloat(vm.sgstRate),
          };
          StatewiseSgstMasterForBrokerage.addSubmitForm(requestData).then(
            function (response) {
              vm.loader = false;
              Swal.fire("Added!", "SGST (" + vm.stateName + ") added successfully", "success");
              $state.go("state_wise_sgst_master_for_brokerage.inquiry");
            },
            function (error) {
              vm.loader = false;
              const errorMessage = error && error.data && error.data.message
                      ? error.data.message
                      : "An unexpected error occurred.";
              if(null!=errorMessage&&errorMessage.startsWith("Invalid Percentage")){
              Swal.fire("Warning!", errorMessage , "warning");
              $state.go("state_wise_sgst_master_for_brokerage.add");
              }else{
              handleErrorResponse(error);
              $state.go("state_wise_sgst_master_for_brokerage.add");
              }
            }
          );
       } }
      );
    }
    function getAddDetails(flg) {
      vm.loader = true;
      if (flg === "schemeTorch") {
        vm.schemeCode = null;
        vm.stateCode = null;
        vm.stateName = null;
        vm.schemeName = null;
      } else if (flg === "stateTorch") {
        vm.stateCode = null;
      }
      var requestData = {
        stateCodeId: vm.stateCode,
        schemeCode: vm.schemeCode,
      };
      StatewiseSgstMasterForBrokerage.addSubmitForm(requestData).then(
        function (response) {
          vm.loader = false;
          if (flg === "schemeTorch") {
            vm.schemeNameList = response.data.result.list;
            angular.element("#levelNameModal").modal("toggle");
          } else if (flg === "stateTorch") {
            vm.stateNameList = response.data.result.list;
            angular.element("#StateNameModal").modal("toggle");
          }
        },
        function (error) {
          vm.loader = false;
          if ((error.data.message == "No Records Found") | (error.data.message == "NDF")) {
            vm.noDataFound = error.data.message;
            Swal.fire("Warning!", vm.noDataFound, "warning");
          } else {
            handleErrorResponse(error);
            $state.go("state_wise_sgst_master_for_brokerage.add");
          }
        }
      );
    }
    function modifyDetails(flg) {
      vm.loader = true;
      if (flg === "schemeTorch") {
        vm.schemeCode = null;
        vm.stateCode = null;
        vm.stateName = null;
        vm.schemeName = null;
      } else if (flg === "stateTorch") {
        vm.stateCode = null;
      }
      var requestData = {
        stateCodeId: vm.stateCode,
        schemeCode: vm.schemeCode,
      };
      StatewiseSgstMasterForBrokerage.modifyDetails(requestData).then(
        function (response) {
          vm.loader = false;
          if (flg === "stateTorch") {
            vm.stateNameList = response.data.result.list;
            angular.element("#StateNameModal").modal("toggle");
          } else if (flg === "schemeTorch") {
            vm.schemeNameList = response.data.result.list;
            angular.element("#levelNameModal").modal("toggle");
          } else if (vm.schemeCode && vm.stateCode) {
            vm.sgstRate = response.data.result.masterOldNewValueModel;
            vm.auditInfo = response.data.result;
            $state.go("state_wise_sgst_master_for_brokerage.modify_detail");
          }
        },
        function (error) {
          vm.loader = false;
          if ((error.data.message == "No Records To Modify") | (error.data.message == "NDF")) {
            vm.noDataFound = error.data.message;
            Swal.fire("Warning!", vm.noDataFound, "warning");
          } else {
            handleErrorResponse(error);
            $state.go("state_wise_sgst_master_for_brokerage.modify");
          }
        }
      );
    }

    function verifyDetails(flg) {
      vm.loader = true;
      if (flg === "schemeTorch") {
        vm.schemeCode = null;
        vm.stateCode = null;
        vm.stateName = null;
        vm.schemeName = null;
      } else if (flg === "stateTorch") {
        vm.stateCode = null;
      }
      var requestData = {
        stateCodeId: vm.stateCode,
        schemeCode: vm.schemeCode,
      };
      StatewiseSgstMasterForBrokerage.verifyDetails(requestData).then(
        function (response) {
          vm.loader = false;
          if (vm.schemeCode && vm.stateCode) {
            vm.sgstRateVal = response.data.result.masterOldNewValueModel;
            vm.auditInfo = response.data.result;
            $state.go("state_wise_sgst_master_for_brokerage.verify_detail");
          } else if (flg === "stateTorch") {
            vm.stateNameList = response.data.result.list;
            angular.element("#StateNameModal").modal("toggle");
          } else if (flg === "schemeTorch") {
            vm.schemeNameList = response.data.result.list;
            angular.element("#levelNameModal").modal("toggle");
          }
        },
        function (error) {
          vm.loader = false;
          if ((error.data.message == "No Records Found to Verify/Reject") | (error.data.message == "NDF")) {
            vm.noDataFound = error.data.message;
            Swal.fire("Warning!", vm.noDataFound, "warning");
          } else {
            handleErrorResponse(error);
            $state.go("state_wise_sgst_master_for_brokerage.verify");
          }
        }
      );
    }

    function verifySubmit() {
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
            primaryKeyvalue: vm.auditInfo.primaryKeyvalue,
          };
          StatewiseSgstMasterForBrokerage.verifySubmit(requestData).then(
            function (response) {
              vm.loader = false;
              Swal.fire("Verified!", "SGST for State (" + vm.stateName + ") Verified Successfully", "success");
              $state.go("state_wise_sgst_master_for_brokerage.inquiry");
            },
            function (error) {
              vm.loader = false;
              handleErrorResponse(error);
              $state.go("state_wise_sgst_master_for_brokerage.verify");
            }
          );
       } }
      );
    }
    function rejectReason() {
      angular.element("#permissionMappingDelete").modal("toggle");
    }
    vm.validateInput = validateInput;
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
    function deleteSubmit() {
      vm.loader = true;
      var requestData = {
        primaryKeyvalue: vm.auditInfo.primaryKeyvalue,
        deleteRemarks: vm.remarks,
        stateName: vm.stateName,
      };
      StatewiseSgstMasterForBrokerage.deleteSubmit(requestData).then(
        function (response) {
          if (response.data.message == "SUCCESS") {
            Swal.fire("Rejected!", "SGST (" + vm.stateName + ") Rejected Successfully", "success");
            $state.go("state_wise_sgst_master_for_brokerage.inquiry");
          } else {
            Swal.fire("WARNING!", response.data.message, "error");
            $state.go("state_wise_sgst_master_for_brokerage.verify");
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
          location.href = "#!/state_wise_sgst_master_for_brokerage/action/verify";
        }
      );
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
          var requestData = {
            primaryKeyvalue: vm.auditInfo.primaryKeyvalue,
            stateCodeId: vm.stateCode,
            stateName: vm.stateName,
            schemeCode: vm.schemeCode,
            columnValues: convertToColumnValuesArrayOldNew(vm.sgstRate),
          };
          StatewiseSgstMasterForBrokerage.modifySubmit(requestData).then(
            function (response) {
              vm.loader = false;
              //					vm.modelResult=  response.data.result.modelResult;
              Swal.fire("Modified!", "SGST (" + vm.stateName + ") Modified successfully", "success");
              $state.go("state_wise_sgst_master_for_brokerage.inquiry");
            },
            function (error) {
            vm.loader = false;
          const errorMessage = error && error.data && error.data.message
                  ? error.data.message
                  : "An unexpected error occurred.";
          if(null!=errorMessage&&errorMessage.startsWith("Invalid Percentage")){
          Swal.fire("Warning!", errorMessage , "warning");
          $state.go("state_wise_sgst_master_for_brokerage.modify");
          }else{
          handleErrorResponse(error);
          $state.go("state_wise_sgst_master_for_brokerage.modify");
          }
            }
          );
       } }
      );
    }
    function inquirySubmit() {
      vm.loader = true;
      var requestData = {
        stateCodeId: vm.stateCode,
        schemeCode: vm.schemeCode,
        stateName: vm.stateName,
        schemeName: vm.schemeName,
      };
      StatewiseSgstMasterForBrokerage.getInquiryDetails(requestData).then(
        function (response) {
          vm.loader = false;
          vm.sgstRateVal = response.data.result.masterOldNewValueModel;
          vm.auditInfo = response.data.result;
        },
        function (error) {
          vm.loader = false;
          if ((error.data.message == "No Data Found") | (error.data.message == "NDF")) {
            vm.noDataFound = error.data.message;
          } else {
            handleErrorResponse(error);
            $state.go("state_wise_sgst_master_for_brokerage.inquiry");
          }
        }
      );
    }

    vm.getRemarksList = function () {
      vm.referenceId = "056";
      var requestData = { referenceId: vm.referenceId };

      StatewiseSgstMasterForBrokerage.getRemarksList(requestData).then(
        function (response) {
          vm.remarksList = response.data.result || []; // Ensure it's an array
        },
        function (error) {
          handleErrorResponse(error);
        }
      );
    };
  }
})();
