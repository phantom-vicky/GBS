(function () {
  "use strict";

  angular.module("gbmUltraApp").controller("tdsExcemptionMasterController", tdsExcemptionMasterController);

  tdsExcemptionMasterController.$inject = [
    "$scope",
    "$filter",
    "$state",
    "$stateParams",
    "$location",
    "$rootScope",
    "tdsExcemptionMasterService",
    "commonService",
  ];

  function tdsExcemptionMasterController($scope, $filter, $state, $stateParams, $location, $rootScope, tdsExcemptionMasterService, commonService) {
    var vm = this;

    vm.currentPage = 1;
    vm.itemsPerPage = "5";
    vm.loginSolId = $rootScope.globals.currentUser.solId;
    vm.loginSolDesc = $rootScope.globals.currentUser.solDesc;
    vm.solDate = $rootScope.globals.currentUser.systemDate;
    vm.currentDate = new Date();
    vm.currentDate = $filter("date")(vm.currentDate, "dd/MM/yyyy");
    vm.getModuleName = getModuleName;
    vm.printpage = printpage;
    vm.residentialStatus='RES';
    vm.getSubscriptionId = getSubscriptionId;
//    vm.submitDeleteForm = submitDeleteForm;
    vm.getBlaAccNoTo = getBlaAccNoTo;
    vm.getAccTranSameNo = getAccTranSameNo;
    vm.convertNumberToWords = convertNumberToWords;
    vm.showDetailBtn = showDetailBtn;
    vm.showDetails = false;
    vm.commonYesNoMetaData = COMMON_YES_NO_METADATA;
    vm.getAddPanNumberDtls = getAddPanNumberDtls;
    vm.getVerDelPanNumberDtls = getVerDelPanNumberDtls;
    vm.getModPanNumberDtls = getModPanNumberDtls;
    vm.getInqPanNumberDtls = getInqPanNumberDtls;
    vm.getVersionNoDetails = getVersionNoDetails;
    vm.submitAddForm = submitAddForm;
    vm.submitModifyForm = submitModifyForm;
    vm.showCancelConfirmation = showCancelConfirmation;
    vm.getAddDetails = getAddDetails;
    vm.getModifyDetails = getModifyDetails;
    vm.getVerifyDetails = getVerifyDetails;
    vm.getVerifySubmit = getVerifySubmit;
    vm.getDeleteSubmit = getDeleteSubmit;
    vm.getInquiryDetails = getInquiryDetails;
    vm.getDeleteDetails = getDeleteDetails;
    vm.formatDate = formatDate;
    vm.dateValidationForModify = dateValidationForModify;
    vm.getEffectFromDate = getEffectFromDate;
    vm.getEffectFromDateVerify = getEffectFromDateVerify;
    //        vm.getFinancialYear=getFinancialYear;
    //        vm.populateDropdown=populateDropdown;
    vm.getcertAgeValidation = getcertAgeValidation;
    vm.getcertAgeModValidation = getcertAgeModValidation;
    vm.onCertificateTypeChange = onCertificateTypeChange;
    vm.getCertSeqNumber = getCertSeqNumber;
    vm.getEffectiveFromDate = getEffectiveFromDate;
    vm.isAgeBelowSixty = isAgeBelowSixty;
    vm.solDate = $rootScope.globals.currentUser.systemDate;
    vm.getModCertSeqNumber = getModCertSeqNumber;
    vm.dateValidation = dateValidation;
    //vm.declarationDateValidation = declarationDateValidation;
    vm.validateDeclarationReceivedDate = validateDeclarationReceivedDate;
    vm.validateLowTaxPercentage = validateLowTaxPercentage;
    vm.validateModifyDeclarationReceivedDate = validateModifyDeclarationReceivedDate;
    vm.dateModValidation = dateModValidation;
    vm.getEffectFromDateInquiry = getEffectFromDateInquiry;
    vm.panPattern = /[a-zA-Z]{5}[0-9]{4}[a-zA-Z]{1}/;
    vm.addList = [];
    vm.addResList = [];
    vm.modResList = [];
    // vm.inqResList= [];
    vm.modifyList = [];
    vm.delList = [];
    vm.inqList = [];
    vm.panNumberDetailList = [];
    vm.versionNumberList = [];
    vm.exemptionDocumentList = [];
    vm.resultAddArray = [];
    vm.taxExcemptionDtlsAdd = [];
    vm.taxExcemptionDtlsMod = [];
    vm.verDetails = {};
    vm.delDetails = {};
    vm.inqDetails = {};
    vm.auditDetails = {};
    vm.verSchemeDetails = [];
    vm.delSchemeDetails = [];
    vm.inqSchemeDetails = [];
    vm.preOprType = null;
    vm.blaCountSum = 0;
    vm.subScriptionCountSum = 0;
    vm.uniqueSchemeNames = [];
    vm.uniqueModeOfHoldings = [];
    vm.distinctSchemeNames = {};
    vm.distinctModeOfHoldings = {};
    vm.taxType = [
      { label: "15G", value: "TF001" },
      { label: "15H", value: "TF002" },
      { label: "TAX EXEMPTION", value: "TF003" },
      { label: "Form 121", value: "TF006" },
    ];

    vm.amountPattern = /^[0-9,]+(\.[0-9]{0,2})?$/;
    vm.lowTaxPercentagePattern = /^(?:\d|[1-9]\d)(?:\.\d{1,2})?$/;

vm.textableDatePicker=textableDatePicker;
vm.autoslashing=autoslashing;
vm.futureNotAllowed  =   futureNotAllowed;
vm.tableId= null;

function replaceStateCodeInAddress(schemeDetails) {
  if (!schemeDetails || !schemeDetails.length) {
    return;
  }
  schemeDetails.forEach(function (item) {
    if (item.communicationAddress) {
      item.communicationAddress = item.communicationAddress.replace(/S\d{3}/, function (code) {
        return stateMap[code] || code;
      });
    }
  });
}

const stateMap = {
    S001: "JAMMU AND KASHMIR",
    S002: "HIMACHAL PRADESH",
    S003: "PUNJAB",
    S004: "CHANDIGARH",
    S005: "UTTARAKHAND",
    S006: "HARYANA",
    S007: "DELHI",
    S008: "RAJASTHAN",
    S009: "UTTAR PRADESH",
    S010: "BIHAR",
    S011: "SIKKIM",
    S012: "ARUNACHAL PRADESH",
    S013: "NAGALAND",
    S014: "MANIPUR",
    S015: "MIZORAM",
    S016: "TRIPURA",
    S017: "MEGHALAYA",
    S018: "ASSAM",
    S019: "WEST BENGAL",
    S020: "JHARKHAND",
    S021: "ORISSA",
    S022: "CHHATTISGARH",
    S023: "MADHYA PRADESH",
    S024: "GUJARAT",
    S025: "DAMAN AND DIU",
    S026: "DADAR AND NAGAR HAVELI",
    S027: "MAHARASTRA",
    S029: "KARNATAKA",
    S030: "GOA",
    S031: "LAKSHADWEEP",
    S032: "KERALA",
    S033: "TAMIL NADU",
    S034: "PUDUCHERRY",
    S035: "ANDAMAN AND NICOBAR",
    S036: "TELANGANA",
    S037: "ANDHRA PRADESH",
    S096: "OTHER COUNTRY",
    S097: "OTHER TERRITORY"
};



function textableDatePicker(id,valueDate)
{
 commonService.textableDatePicker(id,valueDate);
}


function autoslashing(id)
{
 commonService.autoslashing(id);
}


function futureNotAllowed(id,valueDate)     {
                 commonService.futureNotAllowed(id,valueDate);
                }



    if ($state.current.name == "tds_exemption_master.add_detail") {
      vm.natureOfIncome = "Interest on Securities - Sec 393(1), 5(i)";
      vm.otherThanTaxYear = "Y";
      if ($stateParams.obj) {
        vm.panNumber = $stateParams.obj.panNumber;
//        vm.modeOfHolding = $stateParams.obj.modeOfHolding;
        vm.effFromDate= $stateParams.obj.effectiveFromDate

        //                     if (!vm.panPattern.test(vm.panNumber)) {
        //                         Swal.fire("Please enter a valid PAN number");
        //                         $state.go('tds_exemption_master.add');
        //                         vm.loader = false;
        //                       }
        getAddPanNumberDtls();
      }
    } else if ($state.current.name == "tds_exemption_master.modify_detail") {
      vm.natureOfIncome = vm.natureOfIncome || "Interest on Securities - Sec 393(1), 5(i)";
      if ($stateParams.obj)
      {
        vm.panNumber = $stateParams.obj.panNumber;
        vm.effFromDate = $stateParams.obj.effFromDate;
        vm.panId= $stateParams.obj.panId;
        vm.panEffFromDate = $stateParams.obj.panEffFromDate;
//        vm.pkColValOut = $stateParams.obj.pkColValOut;

//        vm.modeOfHolding = $stateParams.obj.modeOfHolding;
//        vm.rejectionRemarks = $stateParams.obj.rejectionRemarks;

        //            if (!vm.panPattern.test(vm.panNumber)) {
        //                Swal.fire("Please enter a valid PAN number");
        //                $state.go('tds_exemption_master.modify');
        //                vm.loader = false;
        //            }
        //            else if(vm.panPattern.test(vm.panNumber)) {
        getEffectiveFromDate();

        //                   }
      }
    } else if ($state.current.name == "tds_exemption_master.verify_detail") {
      if ($stateParams.obj) {
        vm.panNumber = $stateParams.obj.panNumber;
        vm.panId= $stateParams.obj.panId;
//        vm.pkColValOut = $stateParams.obj.pkColValOut;
//        vm.rejectionRemarks = $stateParams.obj.rejectionRemarks;
        getVerifyDetails();
        //getEffectFromDateVerify();
      }
    } else if ($state.current.name == "tds_exemption_master.inquiry_detail") {
      if ($stateParams.obj) {
        vm.panNumber = $stateParams.obj.panNumber;
        vm.tableId= $stateParams.obj.tableId;
//        vm.rejectionRemarks = $stateParams.obj.rejectionRemarks;
//        vm.versionNo = $stateParams.obj.versionNo;

        getInquiryDetails();
        //                        vm.getEffectFromDateInquiry();
      }
    } else if ($state.current.name == "tds_exemption_master.delete_detail") {
      if ($stateParams.obj) {
        vm.panNumber = $stateParams.obj.panNumber;
        vm.panId = $stateParams.obj.panId;
        if (!vm.panPattern.test(vm.panNumber)) {
          Swal.fire("Please enter a valid PAN number");
          $state.go("tds_exemption_master.delete");
          vm.loader = false;
        }
        getDeleteDetails();
      }
    }

    function configDataTable() {
      vm.getTitleDescription = {
        title: "Bond Tax Excemption Register",
        solId: $rootScope.globals.currentUser.solId,
        solDesc: $rootScope.globals.currentUser.solDesc,
      };
      vm.dataTableOption = configDataTableOptions(vm.getTitleDescription);
    }

    function showDetailBtn() {
      vm.showDetails = !vm.showDetails;
    }

    function getModuleName() {
      vm.loader = true;
      vm.referenceId = "TAX";
      var requestData = {
        referenceId: vm.referenceId,
      };
      tdsExcemptionMasterService.getRbiBondModuleNameDetails(requestData).then(
        function (response) {
          vm.loader = false;
          vm.schemeList = response.data.result;
          vm.moduleNameDetailsLabelValue = [];
          angular.forEach(vm.schemeList, function (item) {
            vm.moduleNameDetailsLabelValue.push({
              label: item.subCodeDescription,
              value: item.subCode,
            });
          });
        },
        function (error) {
          handleErrorResponse(error);
          vm.loader = false;
        }
      );
    }

    function printpage() {
      window.print();
    }

    function getAccTranSameNo() {
      vm.loader = true;

      var requestData = {
        schemeCode: vm.schemeCode,
      };
      rbibondTranSameBankService.getDetails(requestData).then(
        function (response) {
          vm.loader = false;
          vm.accTransList = response.data.result;
          angular.element("#branchIdModal").modal("toggle");
        },
        function (error) {
          handleErrorResponse(error);
          vm.loader = false;
        }
      );
    }
    function getAddPanNumberDtls(flag) {
      if (flag == "torch") {
        vm.loader = true;

        var requestData = {
          panNo: vm.panNumber,
          flag: "N",
        };
        tdsExcemptionMasterService.getAddPanNumberDtls(requestData).then(
          function (response) {
            vm.loader = false;
            vm.panNumberDetailList = response.data.result;
            angular.element("#panNoModal").modal("toggle");
          },
          function (error) {
            handleErrorResponse(error);
            vm.loader = false;
            $state.go("tds_exemption_master.inquiry");
          }
        );
      } else {
        vm.loader = true;
        var requestData = {
          panNo: vm.panNumber,
          flag: "Y",
        };

        tdsExcemptionMasterService.getAddPanNumberDtls(requestData).then(
          function (response) {
            vm.loader = false;
            vm.addList = response.data.result;

            replaceStateCodeInAddress(vm.addList);

            vm.modeOfHolding = response.data.result[0].modeOfHolding;
            vm.effFromDate = $filter("date")(vm.solDate, "dd/MM/yyyy");
            //                                                                                           vm.systemDate = new Date(); // Set this to your actual system date
            vm.setDefaultDedReceivedDate();
            vm.addResList = response.data.result;
            vm.addResList.forEach(function (item) {
              vm.blaCountSum += parseInt(item.blaCount);
              vm.subScriptionCountSum += parseInt(item.subScriptionCount);

              vm.uniqueSchemeNames[item.schemeName] = true;
              vm.uniqueModeOfHoldings[item.modeOfHolding] = true;
            });

            vm.distinctSchemeNames = Object.keys(vm.uniqueSchemeNames).join(" / ");
            vm.distinctModeOfHoldings = Object.keys(vm.uniqueModeOfHoldings).join(" / ");
            vm.getFinancialYear();
            vm.getDobValidationIndividualStatus();
            //vm.addList =vm.auditInfo.cursorOut[0];
            //
            //                                                                                   configDataTable();
          },
          function (error) {
            vm.loader = false;

            if (error.data.message == "No Data Found") {
              vm.noDataFound = error.data.message;
            } else {
              handleErrorResponse(error);
              $state.go("tds_exemption_master.inquiry");
            }
          }
        );
      }
    }

    vm.finYears = [];
    vm.finYearStartDate = null;
    vm.finYearEndDate = null;

    vm.getFinancialYear = function () {
      const currentYear = new Date().getFullYear();
      const currentMonth = new Date().getMonth(); // January is 0

      let currentFinYear, nextFinYear;

      if (currentMonth < 3) {
        // Before April
        currentFinYear = `${currentYear - 1}-${currentYear}`;
        nextFinYear = `${currentYear}-${currentYear + 1}`;
      } else {
        // April and after
        currentFinYear = `${currentYear}-${currentYear + 1}`;
        nextFinYear = `${currentYear + 1}-${currentYear + 2}`;
      }

      // Populate the financial years array (current FY and next FY)
      vm.finYears = [currentFinYear, nextFinYear];

      if (vm.modifyList?.C011?.oldValue) {
         vm.finYear = vm.modifyList.C011.oldValue;
         if (vm.finYears.indexOf(vm.finYear) === -1) {
           vm.finYears = [vm.finYear].concat(vm.finYears);
         }
          var dob = (vm.ModDetails && vm.ModDetails[0] && vm.ModDetails[0].dob)? vm.ModDetails[0].dob : null;
          if (dob) {
              var age = vm.calculateAgeParticular(dob, vm.finYear);
              setModifyC028Value(age >= 60 ? "Y" : "N");
          } else {
              setModifyC028Value("");
          }
          vm.onFinancialYearChange(); // Call to set dates based on the selected year
      } else {
          vm.finYear = currentFinYear;
          vm.onFinancialYearChange();
          vm.setDefaultDedReceivedDate();
          if (vm.addList?.[0]) {
            vm.getDobValidationIndividualStatus();
          } else if (vm.ModDetails?.[0]) {
            vm.getDobValidationIndividualModifyStatus();
          }
      }
    };

    vm.onFinancialYearChange = function () {
          if (vm.finYear === undefined || vm.finYear === null || vm.finYear === "") {
            return;
          }
          const yearParts = String(vm.finYear).split("-");
          if (yearParts.length === 2) {
            // Set the financial year start and end dates
            vm.finYearStartDate = new Date(yearParts[0], 3, 1); // Start: April 1
            vm.finYearEndDate = new Date(yearParts[1], 2, 31); // End: March 31
            var solDateObj = parseSolDateToDate(vm.solDate);

            // Update datepicker options
            vm.dateFromOptions.minDate = vm.finYearStartDate;
            vm.dateFromOptions.maxDate = vm.finYearEndDate;

            // Reset or auto-populate certificate dates based on certificate type
            if (vm.certificateType === "TF006") {
              vm.certificateValidFromDate = vm.finYearStartDate;
              vm.certificateValidToDate = vm.finYearEndDate;
              vm.dateToOptions.minDate = vm.finYearStartDate;
            } else {
              vm.certificateValidFromDate = null;
              vm.certificateValidToDate = null;
              vm.dateToOptions.minDate = null;
            }
            vm.dateToOptions.maxDate = vm.finYearEndDate; // Max is the end of the financial year

            // Update datePicker options for Declaration Received Date (within FY, not after sol date if sol is in FY)
            vm.dateToOption.minDate = vm.finYearStartDate;
            if (solDateObj && solDateObj >= vm.finYearStartDate && solDateObj <= vm.finYearEndDate) {
              vm.dateToOption.maxDate = solDateObj;
            } else {
              vm.dateToOption.maxDate = vm.finYearEndDate;
            }

            if ($state.current.name === "tds_exemption_master.modify_detail" && vm.modifyList && vm.modifyList.C011 && vm.finYear) {
              vm.modifyList.C011.oldValue = vm.finYear;
            }
          }
          if ($state.current.name === "tds_exemption_master.add_detail") {
            vm.initTwoTaxYearsListForAdd(true);
          } else if ($state.current.name === "tds_exemption_master.modify_detail") {
            vm.initTwoTaxYearsListForModify(false);
          }
        };

        function normalizeDateForComparison(inputDate) {

            if (!inputDate) {
                return null;
            }

            var parsedDate = inputDate;

            if (!(parsedDate instanceof Date)) {
                var parts = parsedDate.split('/');
                parsedDate = new Date(parts[2], parts[1] - 1, parts[0]);
            }

            return new Date(
                parsedDate.getFullYear(),
                parsedDate.getMonth(),
                parsedDate.getDate()
            );
        }
            function parseSolDateToDate(solDate) {
              if (!solDate) {
                return null;
              }

              if (solDate instanceof Date) {
                return normalizeDateForComparison(solDate);
              }

              if (typeof solDate === "string") {
                var parts = solDate.split("/");
                if (parts.length === 3) {
                  var dd = parseInt(parts[0], 10);
                  var mm = parseInt(parts[1], 10) - 1;
                  var yyyy = parseInt(parts[2], 10);
                  var parsed = new Date(yyyy, mm, dd);
                  if (!isNaN(parsed.getTime())) {
                    return normalizeDateForComparison(parsed);
                  }
                }
              }

              return normalizeDateForComparison(new Date(solDate));
            }

    vm.setDefaultDedReceivedDate = function () {
      if (!vm.dedReceivedDate) {
        vm.dedReceivedDate = parseSolDateToDate(vm.solDate);
      }
    };

            function validateDeclarationDateField(rawValue, clearFn) {
              if (!vm.finYearStartDate || !vm.finYearEndDate) {
                return true;
              }
              if (!rawValue) {
                return true;
              }

              var declarationDate = normalizeDateForComparison(rawValue);
              var fyStartDate = normalizeDateForComparison(vm.finYearStartDate);
              var fyEndDate = normalizeDateForComparison(vm.finYearEndDate);

              if (!fyStartDate || !fyEndDate) {
                return true;
              }

              if (!declarationDate) {
                Swal.fire("Warning!", "Please enter a valid Declaration Received Date.", "warning");
                clearFn();
                return false;
              }

              if (declarationDate < fyStartDate || declarationDate > fyEndDate) {
                Swal.fire("Warning!", "Declaration Received Date is not in Financial Year boundary (01-Apr to 31-Mar).", "warning");
                clearFn();
                return false;
              }

              return true;
            }

            function validateDeclarationReceivedDate() {
              return validateDeclarationDateField(vm.dedReceivedDate, function () {
                vm.dedReceivedDate = null;
              });
            }

            function validateLowTaxPercentage() {
              if (vm.lowTax !== "Y" || vm.noTax === "Y") {
                return true;
              }
              var value = vm.LowTaxPercentage;
              if (value === null || value === undefined || value === "") {
                return true;
              }
              var numValue = parseFloat(String(value).trim());
              if (isNaN(numValue) || numValue < 0 || numValue >= 100) {
                vm.LowTaxPercentage = "";
                Swal.fire("Warning!", "Low Tax Percentage should be between 0 and 99 only", "warning");
                return false;
              }
              return true;
            }

            function isLowTaxPercentageValid() {
              if (vm.certificateType !== "TF003" || vm.lowTax !== "Y") {
                return true;
              }
              var value = vm.LowTaxPercentage;
              if (value === null || value === undefined || value === "") {
                return false;
              }
              var numValue = parseFloat(String(value).trim());
              return !isNaN(numValue) && numValue >= 0 && numValue < 100 && vm.lowTaxPercentagePattern.test(String(value).trim());
            }

            function validateModifyDeclarationReceivedDate() {
              if (!vm.modifyList || !vm.modifyList.C017) {
                return true;
              }
              return validateDeclarationDateField(vm.modifyList.C017.oldValue, function () {
                vm.modifyList.C017.oldValue = null;
              });
            }

            function adjustModifyDeclarationDatePickerBounds() {
              if (!vm.modifyList?.C017?.oldValue || !vm.dateToOption) {
                return;
              }
              var declDate = normalizeDateForComparison(vm.modifyList.C017.oldValue);
              if (!declDate) {
                return;
              }
              var currentMin = vm.dateToOption.minDate ? normalizeDateForComparison(vm.dateToOption.minDate) : null;
              var currentMax = vm.dateToOption.maxDate ? normalizeDateForComparison(vm.dateToOption.maxDate) : null;
              if (currentMin && declDate < currentMin) {
                vm.dateToOption.minDate = declDate;
              }
              if (currentMax && declDate > currentMax) {
                vm.dateToOption.maxDate = declDate;
              }
            }

    vm.itrTaxYearOptions = [];

    vm.getItrTaxYearOptions = function () {
      var currentYear = new Date().getFullYear();
      var options = [];
      for (var i = 3; i >= 1; i--) {
        var fromYear = currentYear - i;
        options.push(fromYear + "-" + (fromYear + 1));
      }
      vm.itrTaxYearOptions = options;
      return options;
    };

    vm.initTwoTaxYearsListForAdd = function (resetExisting) {
      if ($state.current.name !== "tds_exemption_master.add_detail") {
        return;
      }
      vm.getItrTaxYearOptions();
      if (!resetExisting && vm.twoTaxYearsList && vm.twoTaxYearsList.length === 2) {
        return;
      }
      vm.twoTaxYearsList = [
        { assessmentYear: "", ackNumber: "", returnIncome: "" },
        { assessmentYear: "", ackNumber: "", returnIncome: "" },
      ];
    };

    vm.initTwoTaxYearsListForModify = function (preserveExisting) {
      if ($state.current.name !== "tds_exemption_master.modify_detail") {
        return;
      }
      vm.getItrTaxYearOptions();

      var row1 = { assessmentYear: "", ackNumber: "", returnIncome: "" };
      var row2 = { assessmentYear: "", ackNumber: "", returnIncome: "" };

      if (preserveExisting && vm.modifyList) {
        row1.assessmentYear = getModifySubmitValue("C020", "") || "";
        row1.ackNumber = getModifySubmitValue("C019", "") || "";
        row1.returnIncome = getModifySubmitValue("C021", "") || "";
        row2.assessmentYear = getModifySubmitValue("C023", "") || "";
        row2.ackNumber = getModifySubmitValue("C022", "") || "";
        row2.returnIncome = getModifySubmitValue("C024", "") || "";

        [row1.assessmentYear, row2.assessmentYear].forEach(function (year) {
          if (year && vm.itrTaxYearOptions.indexOf(year) === -1) {
            vm.itrTaxYearOptions.unshift(year);
          }
        });
      }

      vm.twoTaxYearsList = [row1, row2];
    };

    function syncModifyItrFieldsFromList() {
      if (!vm.modifyList || !vm.twoTaxYearsList || vm.twoTaxYearsList.length < 2) {
        return;
      }

      ["C019", "C020", "C021", "C022", "C023", "C024"].forEach(function (key) {
        vm.modifyList[key] = vm.modifyList[key] || { oldValue: null, newValue: null };
      });

      var mappings = [
        ["C020", vm.twoTaxYearsList[0].assessmentYear],
        ["C019", vm.twoTaxYearsList[0].ackNumber],
        ["C021", vm.twoTaxYearsList[0].returnIncome],
        ["C023", vm.twoTaxYearsList[1].assessmentYear],
        ["C022", vm.twoTaxYearsList[1].ackNumber],
        ["C024", vm.twoTaxYearsList[1].returnIncome],
      ];

      mappings.forEach(function (entry) {
        if (!isEmptyOrPlaceholder(entry[1])) {
          vm.modifyList[entry[0]].oldValue = entry[1];
        }
      });
    }

    var oldNewValueMapFieldKeys = [
      "C003", "C004", "C005", "C006", "C007", "C008", "C009", "C010", "C011", "C012",
      "C013", "C014", "C015", "C016", "C017", "C019", "C020", "C021", "C022", "C023", "C024",
      "C025", "C026", "C029", "C027", "C028"
    ];

    function initializeOldNewValueMapFields(detailsMap) {
      if (!detailsMap) {
        return;
      }
      oldNewValueMapFieldKeys.forEach(function (key) {
        if (!detailsMap[key] || typeof detailsMap[key] !== "object") {
          detailsMap[key] = { oldValue: null, newValue: null };
        } else {
          if (detailsMap[key].oldValue === undefined) {
            detailsMap[key].oldValue = null;
          }
          if (detailsMap[key].newValue === undefined) {
            detailsMap[key].newValue = null;
          }
        }
      });
    }

    function getOldNewFieldValue(field, preferNewFirst) {
      if (!field || typeof field !== "object") {
        return null;
      }
      var order = preferNewFirst ? ["newValue", "oldValue"] : ["oldValue", "newValue"];
      for (var i = 0; i < order.length; i++) {
        var val = field[order[i]];
        if (!isEmptyOrPlaceholder(val)) {
          return val;
        }
      }
      return null;
    }

    function normalizeDetailsField(detailsMap, key) {
      if (!detailsMap) {
        return;
      }
      detailsMap[key] = detailsMap[key] || { oldValue: null, newValue: null };
      var val = getOldNewFieldValue(detailsMap[key], false);
      if (val === null) {
        val = getOldNewFieldValue(detailsMap[key], true);
      }
      if (val !== null) {
        detailsMap[key].oldValue = val;
        if (isEmptyOrPlaceholder(detailsMap[key].newValue)) {
          detailsMap[key].newValue = val;
        }
      }
    }

    function normalizeDetailsItrFields(detailsMap) {
      ["C019", "C020", "C021", "C022", "C023", "C024"].forEach(function (key) {
        normalizeDetailsField(detailsMap, key);
      });
    }

    function normalizeInquiryIncomeFields() {
      ["C013", "C014", "C015", "C016", "C025", "C026", "C029"].forEach(function (key) {
        normalizeDetailsField(vm.inqDetails, key);
      });
    }

    function enrichInquiryDetails() {
      if (!vm.inqDetails) {
        return;
      }
      if (!getOldNewFieldValue(vm.inqDetails.C027, vm.preOprType === "M")) {
        vm.inqDetails.C027.oldValue = "RES";
      }
      if (!getOldNewFieldValue(vm.inqDetails.C028, vm.preOprType === "M") &&
          vm.inqSchemeDetails && vm.inqSchemeDetails[0]) {
        var finYear = getOldNewFieldValue(vm.inqDetails.C011, false);
        var dob = vm.inqSchemeDetails[0].dob;
        if (dob && finYear) {
          var age = calculateAgeParticular(dob, finYear);
          if (age !== null) {
            vm.inqDetails.C028.oldValue = age >= 60 ? "Y" : "N";
          }
        }
      }
      normalizeInquiryIncomeFields();
      if (vm.isInqTf006Certificate()) {
        normalizeDetailsItrFields(vm.inqDetails);
      }
    }

    vm.getInqFieldValue = function (key, defaultValue) {
      if (!vm.inqDetails || !vm.inqDetails[key]) {
        return defaultValue !== undefined && defaultValue !== null ? defaultValue : "";
      }
      var val = getOldNewFieldValue(vm.inqDetails[key], vm.preOprType === "M");
      if (val !== null) {
        return val;
      }
      return defaultValue !== undefined && defaultValue !== null ? defaultValue : "";
    };

    vm.getInqDisplayValue = function (key, defaultValue) {
      var val = vm.getInqFieldValue(key, defaultValue);
      return val !== "" ? val : "-";
    };

    vm.getInqNewDisplayValue = function (key) {
      if (vm.preOprType !== "M" || !vm.inqDetails || !vm.inqDetails[key]) {
        return "-";
      }
      var field = vm.inqDetails[key];
      if (isEmptyOrPlaceholder(field.newValue) || field.oldValue == field.newValue) {
        return "-";
      }
      return field.newValue;
    };

    vm.isInqTf006Certificate = function () {
      var certificateType = vm.getInqFieldValue("C003");
      return certificateType && String(certificateType).toUpperCase() === "TF006";
    };

    vm.getInqResidentIndividualLabel = function () {
      var value = vm.getInqFieldValue("C028");
      if (value === "Y") {
        return "Yes";
      }
      if (value === "N") {
        return "No";
      }
      return "";
    };

    function isEmptyOrPlaceholder(value) {
      return value === undefined || value === null || value === "" || value === "-";
    }

    function captureModifyOriginalSnapshot() {
      vm.modifyOriginalSnapshot = {};
      if (!vm.modifyList) {
        return;
      }
      Object.keys(vm.modifyList).forEach(function (key) {
        if (!vm.modifyList[key] || typeof vm.modifyList[key] !== "object") {
          return;
        }
        var val = getOldNewFieldValue(vm.modifyList[key], false);
        if (val === null) {
          val = getOldNewFieldValue(vm.modifyList[key], true);
        }
        if (val !== null) {
          vm.modifyOriginalSnapshot[key] = val;
        }
      });
    }

    function getModifySubmitValue(key, defaultValue) {
      if (!vm.modifyList) {
        return defaultValue || null;
      }
      vm.modifyList[key] = vm.modifyList[key] || { oldValue: null, newValue: null };
      var val = getOldNewFieldValue(vm.modifyList[key], false);
      if (val !== null) {
        return val;
      }
      if (vm.modifyOriginalSnapshot && vm.modifyOriginalSnapshot[key]) {
        return vm.modifyOriginalSnapshot[key];
      }
      return defaultValue || null;
    }

    function setModifySubmitItem(items, key, defaultValue) {
      var val = getModifySubmitValue(key, defaultValue);
      if (val !== null && val !== undefined && val !== "") {
        items[key] = val;
      }
    }

    function setModifySubmitDateItem(items, key) {
      var val = formatModifySubmitDate(key);
      if (val !== null && val !== undefined) {
        items[key] = val;
      }
    }

    function stripIndianAmountForModel(value) {
      if (value === null || value === undefined || value === "") {
        return value;
      }
      return String(value).replace(/,/g, "");
    }

    var modifyIndianAmountFields = ["C007", "C013", "C014", "C016", "C021", "C024", "C025"];

    function normalizeModifyIndianAmountField(key) {
      if (!vm.modifyList || !vm.modifyList[key]) {
        return;
      }
      var val = vm.modifyList[key].oldValue;
      if (val !== null && val !== undefined && val !== "") {
        vm.modifyList[key].oldValue = stripIndianAmountForModel(val);
      }
    }

    function normalizeModifyFieldOnLoad(key, defaultValue) {
      vm.modifyList[key] = vm.modifyList[key] || { oldValue: null, newValue: null };
      var val = getOldNewFieldValue(vm.modifyList[key], false);
      if (val === null) {
        val = getOldNewFieldValue(vm.modifyList[key], true);
      }
      if (val === null && defaultValue) {
        val = defaultValue;
      }
      if (val !== null) {
        vm.modifyList[key].oldValue = val;
      }
    }

    function normalizeModifyIncomeFieldsOnLoad() {
      vm.natureOfIncome = vm.natureOfIncome || "Interest on Securities - Sec 393(1), 5(i)";
      ["C013", "C014", "C015", "C016", "C025", "C026", "C029"].forEach(function (key) {
        var defaultValue = null;
        if (key === "C026") {
          defaultValue = vm.natureOfIncome;
        } else if (key === "C029") {
          defaultValue = "Y";
        }
        normalizeModifyFieldOnLoad(key, defaultValue);
      });
      modifyIndianAmountFields.forEach(normalizeModifyIndianAmountField);
    }

    function normalizeModifyItrFieldsOnLoad() {
      if (!vm.modifyList) {
        return;
      }
      ["C019", "C020", "C021", "C022", "C023", "C024"].forEach(function (key) {
        vm.modifyList[key] = vm.modifyList[key] || { oldValue: null, newValue: null };
        var val = getOldNewFieldValue(vm.modifyList[key], false);
        if (val !== null) {
          vm.modifyList[key].oldValue = val;
        }
      });
    }

    function getModifyDisplayValue(key, defaultValue) {
      return getModifySubmitValue(key, defaultValue);
    }

    function isModifyTf006Certificate() {
      var certType = getModifySubmitValue("C003", null);
      return certType && String(certType).toUpperCase() === "TF006";
    }

    function appendModifyItrFieldsToItems(items) {
      normalizeModifyItrFieldsOnLoad();
      syncModifyItrFieldsFromList();
      ["C019", "C020", "C021", "C022", "C023", "C024"].forEach(function (key) {
        setModifySubmitItem(items, key, null);
      });
    }

    function syncModifyFieldBeforeSubmit(key, defaultValue) {
      var val = getModifySubmitValue(key, defaultValue);
      if (modifyIndianAmountFields.indexOf(key) !== -1 && val !== null && val !== undefined && val !== "") {
        val = stripIndianAmountForModel(val);
      }
      if (val !== null) {
        vm.modifyList[key] = vm.modifyList[key] || { oldValue: null, newValue: null };
        vm.modifyList[key].oldValue = val;
        vm.modifyList[key].newValue = val;
      }
    }

    function formatModifySubmitDate(key) {
      var val = getModifySubmitValue(key, null);
      if (!val) {
        return null;
      }
      return moment(val).format("DD-MMM-YYYY");
    }

    function syncModifyListValuesBeforeSubmit() {
      if (!vm.modifyList) {
        return;
      }
      initializeOldNewValueMapFields(vm.modifyList);
      normalizeModifyIncomeFieldsOnLoad();

      syncModifyFieldBeforeSubmit("C027", "RES");
      syncModifyFieldBeforeSubmit("C028", null);
      [
        "C003", "C004", "C005", "C006", "C007", "C008", "C011", "C012",
        "C013", "C014", "C015", "C016", "C025", "C026", "C029"
      ].forEach(function (key) {
        syncModifyFieldBeforeSubmit(key, null);
      });

      if (isModifyTf006Certificate()) {
        syncModifyItrFieldsFromList();
        ["C019", "C020", "C021", "C022", "C023", "C024"].forEach(function (key) {
          syncModifyFieldBeforeSubmit(key, null);
        });
      }
    }

    function setModifyC028Value(value) {
      vm.modifyList.C028 = vm.modifyList.C028 || {};
      vm.modifyList.C028.newValue = value;
      vm.modifyList.C028.oldValue = value;
    }

    vm.onItrTaxYearChange = function (row) {
      if (!row || !row.assessmentYear) {
        if (row) {
          row.ackNumber = "";
          row.returnIncome = "";
        }
      }
    };

    vm.calculateAgeParticular = calculateAgeParticular;

    function calculateAgeParticular(dobStr, finYear) {

        if (!dobStr || !finYear) {
            return null;
        }

        // DOB format : dd/MM/yyyy
        var parts = dobStr.split("/");
        var dob = new Date(parts[2], parts[1] - 1, parts[0]);

        // Financial Year Example: 2026-2027
        var finYearParts = finYear.split("-");

        // Take ending year -> 2027
        var endYear = parseInt(finYearParts[1]);

        // Age should be calculated as on 31 March of ending FY
        var assessmentDate = new Date(endYear, 2, 31); // 31-Mar-2027

        var age = assessmentDate.getFullYear() - dob.getFullYear();

        // Check whether birthday completed before 31 March
        if (
            assessmentDate.getMonth() < dob.getMonth() ||
            (
                assessmentDate.getMonth() === dob.getMonth() &&
                assessmentDate.getDate() < dob.getDate()
            )
        ) {
            age--;
        }

        // 60+ logic
        var sixtiethBirthday = new Date(
            dob.getFullYear() + 60,
            dob.getMonth(),
            dob.getDate()
        );

        if (assessmentDate >= sixtiethBirthday) {
            age++;
        }

        return age;
    }
    vm.getDobValidationIndividualModifyStatus = function () {
                  var dob = (vm.ModDetails && vm.ModDetails[0] && vm.ModDetails[0].dob)? vm.ModDetails[0].dob : null;
                  if (dob) {
                      var age = vm.calculateAgeParticular(dob, vm.finYear);
                      setModifyC028Value(age >= 60 ? "Y" : "N");
                  } else {
                      setModifyC028Value("");
                  }
    };
    vm.getDobValidationIndividualStatus = function () {

      console.log("vm.addList?.[0]?.dob", vm.addList?.[0]?.dob);

        if (!vm.finYear || !vm.addList?.[0]?.dob) {
            vm.residentIndividual = "";
            return;
        }

        var age = vm.calculateAgeParticular(vm.addList[0].dob, vm.finYear);

        if (age >= 60) {
            vm.residentIndividual = "Y";
        } else {
            vm.residentIndividual = "N";
        }
    };

    // Update min date for To Date based on From Date
    vm.updateToDateMin = function () {
      if (vm.certificateValidFromDate) {
        vm.dateToOptions.minDate = new Date(vm.certificateValidFromDate);
      } else if (vm.modifyList.C009.oldValue) {
        vm.dateToOptions.minDate = new Date(vm.modifyList.C009.oldValue);
      } else {
        vm.dateToOptions.minDate = null; // Reset if From Date is not set
      }
    };

    $scope.checkForZero = function () {
      // Check if the value is '0' or starts with '0'
      if ($scope.vm.totalNoOfForm === "0" || /^0\d+/.test($scope.vm.totalNoOfForm)) {
        $scope.vm.totalNoOfForm = ""; // Clear the input or set to empty if invalid
      }
    };

    $scope.checkForZeroModify = function () {
      // Check if the value is '0' or starts with '0'
      if ($scope.vm.modifyList.C015.oldValue === "0" || /^0\d+/.test($scope.vm.modifyList.C015.oldValue)) {
        $scope.vm.modifyList.C015.oldValue = ""; // Clear the input or set to empty if invalid
      }
    };

    // Initialize datepicker options
    vm.dateFromOptions = {
      minDate: null,
      maxDate: null,
    };

    vm.dateToOptions = {
      minDate: null,
      maxDate: null,
    };

    vm.dateToOption = {
      minDate: null,
      maxDate: null,
    };

    // Set up the datepicker instances
    vm.fromDatepicker = {
      opened: false,
    };

    vm.toDatepicker = {
      opened: false,
    };

    vm.efffromDateOptions = {
      opened: false,
    };

    function getVerDelPanNumberDtls() {
      if (!vm.panNumber || vm.panNumber.trim() === "") {
        Swal.fire("Please enter PAN number");
        return false;
      } else {
        vm.loader = true;

        var requestData = {
          panNumber: vm.panNumber,
        };
        tdsExcemptionMasterService.getVerDelPanNumberDtls(requestData).then(
          function (response) {
            vm.loader = false;
            vm.panNumberDetailList = response.data.result;
            angular.element("#panNoModal").modal("toggle");
          },
          function (error) {
            handleErrorResponse(error);
            //vm.loader = false;
            $state.go("tds_exemption_master.inquiry");
          }
        );
      }
    }

    function getModPanNumberDtls() {
      if (!vm.panNumber || vm.panNumber.trim() === "") {
        Swal.fire("Please enter PAN number");
        return false;
      } else {
        vm.loader = true;

        var requestData = {
          panNumber: vm.panNumber,
        };
        tdsExcemptionMasterService.getModPanNumberDtls(requestData).then(
          function (response) {
            vm.loader = false;
            vm.panNumberDetailList = response.data.result;
            angular.element("#panNoModal").modal("toggle");
          },
          function (error) {
            handleErrorResponse(error);
            //  vm.loader = false;
            $state.go("tds_exemption_master.inquiry");
          }
        );
      }
    }

    function getInqPanNumberDtls() {
      if (!vm.panNumber || vm.panNumber.trim() === "") {
        Swal.fire("Please enter PAN number");
        return false;
      } else {
        vm.loader = true;

        var requestData = {
          panNumber: vm.panNumber,
        };
        tdsExcemptionMasterService.getInqPanNumberDtls(requestData).then(
          function (response) {
            vm.loader = false;
            vm.panNumberDetailList = response.data.result;
            angular.element("#panNoModal").modal("toggle");
          },
          function (error) {
            handleErrorResponse(error);
            vm.loader = false;
          }
        );
      }
    }

    function getSubscriptionId() {
      vm.loader = true;

      var requestData = {
        schemeCode: vm.schemeCode,
        fromBlaNo: vm.blaAccNo,
      };
      tdsExcemptionMasterService.getDetails(requestData).then(
        function (response) {
          vm.loader = false;
          vm.subscriptionIdList = response.data.result;
          //                       vm.tocSlabListEffctDate=response.data.result.effeFromDateList;

          angular.element("#subScriptionIdModal").modal("toggle");
        },
        function (error) {
          handleErrorResponse(error);
          vm.loader = false;
        }
      );
    }

    function getBlaAccNoTo() {
      vm.loader = true;

      var requestData = {
        schemeCode: vm.schemeCode,
        fromBlaNo: vm.blaAccNo,
        subscriptionId: vm.bondScbId,
      };
      tdsExcemptionMasterService.getDetails(requestData).then(
        function (response) {
          vm.loader = false;
          vm.blaAccNoToList = response.data.result;
          //                       vm.tocSlabListEffctDate=response.data.result.effeFromDateList;

          angular.element("#blaAccNoToModal").modal("toggle");
        },
        function (error) {
          handleErrorResponse(error);
          vm.loader = false;
        }
      );
    }

    //
    //function getFinancialYear(){
    //    		tdsExcemptionMasterService.getFinaincalYear(requestData).then(function(response)
    //    				vm.finYear = response.data;
    //    			}, handleErrorResponse
    //    		);
    //    	}
    vm.calculateAge=calculateAge;
function calculateAge(dobStr) {
  if (!dobStr) return null;

  var parts = dobStr.split("/"); // dd/MM/yyyy
  var dob = new Date(parts[2], parts[1] - 1, parts[0]);
  var today = new Date();

  // Calculate full-year difference
  var age = today.getFullYear() - dob.getFullYear();

  // Birthday in the current year
  var birthdayThisYear = new Date(today.getFullYear(), dob.getMonth(), dob.getDate());

  // Adjust age if birthday hasn't occurred yet this year
  if (today < birthdayThisYear) {
    age--;
  }
  // Dynamically handle 60+ logic
  var sixtiethBirthday = new Date(dob.getFullYear() + 60, dob.getMonth(), dob.getDate());

  if (today > sixtiethBirthday) {
    // Already 60 + any months/days → count as 61
    age++;
  }else if(today === sixtiethBirthday){
      age++;
  }
  return age;
}

    function setForm121CertificateDates() {
      if (vm.certificateType !== "TF006") {
        return;
      }
      if (!vm.finYearStartDate || !vm.finYearEndDate) {
        if (vm.finYear) {
          var yearParts = String(vm.finYear).split("-");
          if (yearParts.length === 2) {
            vm.finYearStartDate = new Date(yearParts[0], 3, 1);
            vm.finYearEndDate = new Date(yearParts[1], 2, 31);
          }
        }
      }
      if (vm.finYearStartDate && vm.finYearEndDate) {
        vm.certificateValidFromDate = vm.finYearStartDate;
        vm.certificateValidToDate = vm.finYearEndDate;
        if (vm.dateToOptions) {
          vm.dateToOptions.minDate = vm.finYearStartDate;
        }
      }
    }

    function onCertificateTypeChange() {
      if ($state.current.name !== "tds_exemption_master.add_detail") {
        return;
      }
      if (vm.certificateType === "TF006") {
        if (!vm.twoTaxYearsList || vm.twoTaxYearsList.length < 2) {
          vm.initTwoTaxYearsListForAdd();
        }
        setForm121CertificateDates();
      } else if (vm.certificateType === "TF003") {
        vm.certificateValidFromDate = null;
        vm.certificateValidToDate = null;
        if (vm.dateToOptions) {
          vm.dateToOptions.minDate = null;
        }
      }
    }

    function getcertAgeValidation(isPostSubmit, isAddSubmit) {
      if (isAddSubmit) {
        if (vm.certificateType === "TF006") {
          return getCertSeqNumber();
        }
        return Promise.resolve();
      }

      if (isPostSubmit) {
        if (vm.certificateType === "TF006") {
          return getCertSeqNumber();
        }
        return Promise.resolve();
      }

      if (vm.addList?.[0]?.modeOfHolding && vm.addList[0].modeOfHolding !== "HUF") {
        var age = calculateAge(vm.addList[0].dob);
        if (age === null) {
          Swal.fire("Error!", "Date of Birth is required", "error");
          return Promise.reject();
        }
        if (vm.certificateType == "TF001") {
          if (age >= 60) {
            Swal.fire("Error!", "Age of the holder is NOT BELOW 60 years", "error");
            vm.certificateType = "";
            return Promise.reject();
          }
          return getCertSeqNumber();
        } else if (vm.certificateType == "TF002") {
          if (age <= 60) {
            Swal.fire("Error!", "Age of the holder is BELOW 60 years", "error");
            vm.certificateType = "";
            return Promise.reject();
          }
          return getCertSeqNumber();
        } else if (vm.certificateType == "TF003") {
          vm.certificateValidFromDate = null;
          vm.certificateValidToDate = null;
          return Promise.resolve();
        } else if (vm.certificateType == "TF006") {
          if (!vm.twoTaxYearsList || vm.twoTaxYearsList.length < 2) {
            vm.initTwoTaxYearsListForAdd();
          }
          setForm121CertificateDates();
          return getCertSeqNumber();
        }
      } else {
        if (vm.certificateType == "TF006") {
          if (!vm.twoTaxYearsList || vm.twoTaxYearsList.length < 2) {
            vm.initTwoTaxYearsListForAdd();
          }
          setForm121CertificateDates();
        }
        return getCertSeqNumber();
      }
      return Promise.resolve();
    }

    function getcertAgeModValidation() {
      /*var parts = vm.ModDetails[0].dob.split("/");
      var year = parseInt(parts[2]);
      var cYear = parseInt($filter("date")(new Date(), "yyyy"));
      var age = cYear - year;*/
     if (vm.ModDetails?.[0]?.modeOfHolding && vm.ModDetails[0].modeOfHolding !== 'HUF') {
    var age = calculateAge(vm.ModDetails[0].dob);
    if (age === null) {
     Swal.fire("Error!", "Date of Birth is required", "error");
     return;
     }
      if (vm.modifyList.C003.oldValue === "TF001") {
        if (age >= 60) {
          Swal.fire("Error!", "Age of the holder is NOT BELOW 60 years", "error");
          vm.modifyList.C003.oldValue = "";
        } else {
          vm.getModCertSeqNumber();
        }
      } else if (vm.modifyList.C003.oldValue === "TF002") {
        if (age <= 60) {
          Swal.fire("Error!", "Age of the holder is BELOW 60 years", "error");
          vm.modifyList.C003.oldValue = "";
        } else {
          vm.getModCertSeqNumber();
        }
      } else if (vm.modifyList.C003.oldValue === "TF003") {
        vm.modifyList.C004.oldValue = "";
      }else if(vm.modifyList.C003.oldValue === "TF006"){
        vm.getModCertSeqNumber();
      }

      //                           else if(vm.modifyList.C003.oldValue === 'TF003') {
      //                                                   let str = vm.modifyList.certificateNumber;
      //                                                    let letterT = str.charAt(0);
      //
      //                                                     if((!(letterT === 'G')) && (!(letterT === 'H'))){
      //                                                     vm.modifyList.C003.oldValue=vm.modifyList.C003.oldValue;
      //                                                     }
      //                                                     else{
      //                                                   vm.modifyList.C004.oldValue='';
      //                                                   }
      //                                              }
      //                         else if (vm.modifyList.C003.oldValue == 'TF003'){
      //
      //                                                          vm.modifyList.C004.oldValue='';
      //                                                            }
      }else{
        vm.getModCertSeqNumber();
     }
    }

    function isAgeBelowSixty(dob) {
      var today = new Date();
      var birthDate = new Date(dob);
      var age = today.getFullYear() - birthDate.getFullYear();
      var m = today.getMonth() - birthDate.getMonth();
      if (m < 0 || (m === 0 && today.getDate() < birthDate.getDate())) {
        age--;
      }
      return age < 60;
    }

    function getCertSeqNumber() {
      vm.loader = true;
      var requestData = {
        certificateType: vm.certificateType,
        panNo: vm.panNumber || vm.addList.panNo,
        finYear: vm.finYear,
      };
      return tdsExcemptionMasterService.getcertSeqNumber(requestData).then(
        function (response) {
          vm.loader = false;
          vm.certificateNumber = response.data.result.certificateNumber;
          return response;
        },
        function (error) {
          handleErrorResponse(error);
          vm.loader = false;
          return Promise.reject(error);
        }
      );
    }

    function getModCertSeqNumber() {
      vm.loader = true;
      var certificate = vm.modifyList.C003.oldValue;
     /* vm.loader = true;
      if (vm.modifyList.C003.oldValue == "TF001") {
        certificate = "15G";
      }
      if (vm.modifyList.C003.oldValue == "TF002") {
        certificate = "15H";
      }*/
      var requestData = {
        certificateType: certificate,
        panNo: vm.modifyList.C001.oldValue,
        finYear: vm.modifyList.C011.oldValue,
      };

      tdsExcemptionMasterService.getcertSeqNumber(requestData).then(
        function (response) {
          vm.loader = false;
          vm.modifyList.C004.oldValue = response.data.result.certificateNumber;
        },
        function (error) {
          handleErrorResponse(error);
          vm.loader = false;
        }
      );
    }

    function getVersionNoDetails() {
      vm.loader = true;

      var requestData = {
        panNumber: vm.panNumber,
      };
      tdsExcemptionMasterService.getVersionNoDetails(requestData).then(
        function (response) {
          vm.loader = false;
          vm.versionNumberList = response.data.result;
          angular.element("#versionNumberModal").modal("toggle");
        },
        function (error) {
          handleErrorResponse(error);
          vm.loader = false;
        }
      );
    }

    function stripAmountCommas(value) {
      if (value === null || value === undefined || value === "") {
        return value;
      }
      return String(value).replace(/,/g, "").trim();
    }

    function buildTwoTaxYearsListForSubmit() {
      if (!vm.twoTaxYearsList) {
        return [];
      }
      return vm.twoTaxYearsList.map(function (row, index) {
        var inputEl = document.getElementById("returnIncome" + index);
        var returnIncome = inputEl && inputEl.value
          ? stripAmountCommas(inputEl.value)
          : stripAmountCommas(row.returnIncome);
        return {
          assessmentYear: row.assessmentYear,
          ackNumber: row.ackNumber,
          returnIncome: returnIncome,
        };
      });
    }

    function submitAddForm() {
      if (!isLowTaxPercentageValid()) {
        Swal.fire("Error!", "Low Tax Percentage should be between 0 and 99 only", "error");
        return;
      }

      if (vm.certificateType === "TF003") {
        if (!vm.certificateNumber || !String(vm.certificateNumber).trim()) {
          Swal.fire("Error!", "Tax Exemption Certificate No is required", "error");
          return;
        }
        if (vm.noTax !== "Y" && vm.lowTax !== "Y") {
          Swal.fire("Error!", "Please select No Tax or Low Tax", "error");
          return;
        }
      }

      function openConfirmSwal() {
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
            panNo: vm.panNumber,
            effFromDate: $rootScope.formatDateToDDMMMYYYY(convertToDateObject(vm.effFromDate)),
            certificateType: vm.certificateType,
            certificateNumber: vm.certificateNumber ? String(vm.certificateNumber).trim() : vm.certificateNumber,
            noTax: vm.noTax,
            lowTax: vm.lowTax,
            noTaxLimit: vm.noTaxLimit,
            lowTaxPercentage: vm.LowTaxPercentage,
            certificateValidFromDate: $filter("date")(vm.certificateValidFromDate, "dd-MMM-yyyy"),
            certificateValidToDate: $filter("date")(vm.certificateValidToDate, "dd-MMM-yyyy"),
            finYear: vm.finYear,
            incomeTaxAccount: vm.incomeTaxAccount,
            estimatedIncomeTaxDed: vm.estimatedIncomeTaxDed,
            estimatedTotalIncomeTaxDed: vm.estimatedTotalIncomeTaxDed,
            totalNoOfForm: vm.totalNoOfForm,
            aggAmtIncome: vm.aggAmtIncome,
            natureOfIncome: vm.natureOfIncome,
            residentialStatus: vm.residentialStatus,
            residentIndividual: vm.residentIndividual,
            otherThanTaxYear: vm.otherThanTaxYear,
            estimateTotalTaxYearAmt: vm.estimateTotalTaxYearAmt,
            dedReceivedDate: $filter("date")(vm.dedReceivedDate, "dd-MMM-yyyy"),
            twoTaxYearsList: buildTwoTaxYearsListForSubmit()
          };
          tdsExcemptionMasterService.getAddSubmit(requestData).then(
            (response) => {
              vm.loader = false;

              var successHtml = "TDS Exemption Certificate Details Added Successfully";
              if (vm.certificateType === "TF006" && vm.certificateNumber) {
                successHtml += "<br>UIN Number : " + vm.certificateNumber;
              }

              Swal.fire({
                title: "Added!",
                html: successHtml,
                icon: "success",
              }).then(function () {
                location.href = "#!/tds_exemption_master/action/inquiry";
              });
            },
            function (error) {
              vm.loader = false;
              handleErrorResponse(error);
            }
          );
      }  }
      );
      }

      if (vm.certificateType === "TF006") {
        getcertAgeValidation(false, true).then(
          function () {
            if (!vm.certificateNumber || !String(vm.certificateNumber).trim()) {
              Swal.fire("Error!", "Unable to generate UIN number", "error");
              return;
            }
            openConfirmSwal();
          },
          function () {}
        );
        return;
      }

      openConfirmSwal();
    }

    //     function submitModifyForm(){
    //     Swal.fire({
    //                      title: "Are you sure?",
    //                      text: "Do you want to Really Modify this record?",
    //                      type: "warning",
    //                      showCancelButton: true,
    //                      cancelButtonClass: "btn-cancel",
    //                      confirmButtonClass: "btn-primary",
    //                      confirmButtonText: "Yes",
    //                      closeOnConfirm: false,
    //                      showLoaderOnConfirm: true
    //                },
    //                    function(){
    //   vm.resultArray = [];
    //                        angular.forEach(vm.ModDetails, function(item){
    //
    //                             item.C009.oldValue = formatDate(convertToDateObject(item.C009.newValue));
    //                             item.C010.oldValue = formatDate(convertToDateObject(item.C010.newValue));
    //                             item.C017.oldValue = formatDate(convertToDateObject(item.C017.newValue));
    //                             vm.resultArray.push(convertToColumnValuesArrayList(item))
    //                        })
    ////                    for (let key in vm.modifyList) {
    ////                        if (vm.modifyList.hasOwnProperty(key)) {
    ////                            let item = vm.modifyList[key];
    ////                            let items = [];
    ////
    ////                             if(vm.modifyList.certificateName==='15G' || vm.modifyList.certificateName==='15H'){
    ////                                    items.C007 =null;
    ////                                    items.C008 =null;
    ////                                    items.C009 =null;
    ////                                    }
    ////                             else if(vm.modifyList.certificateName==='TAX'){
    ////                             if(vm.modifyList.noLowTax=='Y'){
    ////                             items.C007='low Tax';
    ////                             items.C008 = typeof vm.modifyList.lowTaxAmtLimit === "undefined" ? null : vm.modifyList.lowTaxAmtLimit;
    ////                             items.C009 = typeof vm.modifyList.lowTaxAmtPercent === "undefined" ? null : vm.modifyList.lowTaxAmtPercent;
    ////
    ////                             }
    ////                             else if(vm.modifyList.noLowTax=='N'){
    ////                             items.C007='no Tax';
    ////                             items.C008 = typeof vm.modifyList.lowTaxAmtLimit === "undefined" ? null : vm.modifyList.lowTaxAmtLimit;
    ////                             items.C009 = typeof vm.modifyList.lowTaxAmtPercent === "undefined" ? null : vm.modifyList.lowTaxAmtPercent;
    ////                             }
    ////                             }
    ////                            items.C002 = typeof vm.modifyList.effFromDate === "undefined" ? null : moment(convertToDateObject(vm.modifyList.effFromDate)).format("DD-MMM-YYYY");
    ////                            items.C003 = typeof vm.modifyList.panNo === "undefined" ? null : vm.modifyList.panNo;
    ////                            items.C004 = typeof vm.modifyList.bondHolderName === "undefined" ? null : vm.modifyList.bondHolderName;
    ////                            items.certificateType = typeof vm.modifyList.certificateName === "undefined" ? null : vm.modifyList.certificateName;
    ////
    ////                            items.C006 = typeof vm.modifyList.certificateNumber === "undefined" ? null : vm.modifyList.certificateNumber;
    ////                          //  items.C007 = typeof vm.modifyList.noLowTax === "undefined" ? null:vm.modifyList.noLowTax;
    ////                           // items.C008 = typeof vm.modifyList.lowTaxAmtLimit === "undefined" ? null : vm.modifyList.lowTaxAmtLimit;
    ////                           // items.C009 = typeof vm.modifyList.lowTaxAmtPercent === "undefined" ? null : vm.modifyList.lowTaxAmtPercent;
    ////                            items.C010 = typeof vm.modifyList.certFromDate === "undefined" ? null : moment(vm.modifyList.certFromDate).format("DD-MMM-YYYY");
    ////                            items.C011 = typeof vm.modifyList.certToDate === "undefined" ? null : moment(vm.modifyList.certToDate).format("DD-MMM-YYYY");
    ////                            items.C012 = $rootScope.formatDateToDDMMMYYYY(convertToDateObject($rootScope.globals.currentUser.solDate));
    ////                            items.C013 = typeof vm.modifyList.finYear === "undefined" ? null : vm.modifyList.finYear;
    ////                            items.C014 = typeof vm.modifyList.dob === "undefined" ? null : moment(convertToDateObject(vm.modifyList.dob)).format("DD-MMM-YYYY");
    ////                          vm.taxExcemptionDtlsMod = convertToColumnValuesArrayList(items);
    ////                            break;
    ////                        }
    //                    }
    //                      var requestData = {
    //                    'panId': vm.pkColValOut,
    //
    //                    'taxExcemptionModifyDtoList':vm.resultArray,
    //
    //                        };
    //                        tdsExcemptionMasterService.getModifySubmit(requestData).then(
    //                                                    (response) => {
    //                                                        vm.loader = false;
    //
    //                                                       //  vm.responseResult = response.data.message;
    //                                                        var message = "Exemption Certificate Details Modified Successfully";
    //                                                       Swal.fire("Modified!", message, "success");
    //                                                        location.href = '#!/tds_exemption_master/action/inquiry';
    //                                                    },
    //                                                    function(error) {
    //                                                        vm.loader = false;
    //                                                        handleErrorResponse(error);
    //
    //                                                    });
    //
    //                        });
    //     }

    function formatDate(tempDate) {
      return $filter("date")(tempDate, "dd-MMM-yyyy");
    }

    function formatSubmitDate(dateValue) {
      if (!dateValue) {
        return null;
      }
      if (typeof dateValue === "string") {
        var parsedString = moment(dateValue, ["DD-MMM-YYYY", "DD-MM-YYYY", "DD/MM/YYYY"], true);
        if (parsedString.isValid()) {
          return parsedString.format("DD-MMM-YYYY");
        }
      }
      if (moment(dateValue).isValid()) {
        return moment(dateValue).format("DD-MMM-YYYY");
      }
      return null;
    }
    function submitModifyForm() {
            Swal.fire({
              title: "Are you sure?",
              text: "Do you want to Really modify this record?",
              icon: "warning",
              showCancelButton: true,
              confirmButtonText: "Yes",
              cancelButtonText: "Cancel",
              customClass: {
                confirmButton: "btn-primary",
                cancelButton: "btn-cancel"
              },
              buttonsStyling: false
            }).then((result) => {

                   if (result.isConfirmed)
                   {
                      syncModifyListValuesBeforeSubmit();
                      for (let key in vm.modifyList) {
                        if (vm.modifyList.hasOwnProperty(key)) {
                          let item = vm.modifyList[key];
                          let items = [];

              //                             if(vm.modifyList.C003==='15G' || vm.modifyList.C003==='15H'){
              //                                    items.C007 =null;
              //                                    items.C008 =null;
              //                                    items.C009 =null;
              //                                    }
              //                             else if(vm.modifyList.C003==='TF003'){
              //                             if(vm.modifyList.C006=='Y'){
              //                             items.C007='low Tax';
              //                             items.C008 = typeof vm.modifyList.lowTaxAmtLimit === "undefined" ? null : vm.modifyList.lowTaxAmtLimit;
              //                             items.C009 = typeof vm.modifyList.lowTaxAmtPercent === "undefined" ? null : vm.modifyList.lowTaxAmtPercent;
              //
              //                             }
              //                             else if(vm.modifyList.C005=='N'){
              //                             items.C007='no Tax';
              //                             items.C008 = typeof vm.modifyList.lowTaxAmtLimit === "undefined" ? null : vm.modifyList.lowTaxAmtLimit;
              //                             items.C009 = typeof vm.modifyList.lowTaxAmtPercent === "undefined" ? null : vm.modifyList.lowTaxAmtPercent;
              //                             }
              //                             }

              setModifySubmitItem(items, "C001", null);
              //                            items.C002 = typeof vm.modifyList.C002 === "undefined" ? null : moment(convertToDateObject(vm.modifyList.C002.oldValue)).format("DD-MMM-YYYY");
              setModifySubmitItem(items, "C003", null);
              setModifySubmitItem(items, "C004", null);
              setModifySubmitItem(items, "C005", null);
              setModifySubmitItem(items, "C006", null);
              setModifySubmitItem(items, "C007", null);
              setModifySubmitItem(items, "C008", null);
              setModifySubmitItem(items, "C011", null);
              setModifySubmitItem(items, "C012", null);
              setModifySubmitItem(items, "C013", null);
              setModifySubmitItem(items, "C014", null);
              setModifySubmitItem(items, "C015", null);
              setModifySubmitItem(items, "C016", null);
              if (isModifyTf006Certificate()) {
                appendModifyItrFieldsToItems(items);
              }
              setModifySubmitItem(items, "C025", null);
              setModifySubmitItem(items, "C026", vm.natureOfIncome || "Interest on Securities - Sec 393(1), 5(i)");
              setModifySubmitItem(items, "C027", "RES");
              setModifySubmitItem(items, "C028", null);
              setModifySubmitItem(items, "C029", null);

              setModifySubmitDateItem(items, "C009");
              setModifySubmitDateItem(items, "C010");
              setModifySubmitDateItem(items, "C017");
              var c002Value = formatSubmitDate(vm.panEffFromDate)
                || formatSubmitDate(vm.effFromDate)
                || formatSubmitDate(vm.modifyList.C002 && vm.modifyList.C002.oldValue);
              if (c002Value) {
                items.C002 = c002Value;
              }

              //                            items.C012 = $rootScope.formatDateToDDMMMYYYY(convertToDateObject($rootScope.globals.currentUser.solDate));
              //                            items.C013 = typeof vm.modifyList.finYear === "undefined" ? null : vm.modifyList.finYear;
              //                            items.C017 = typeof vm.modifyList.C017 === "undefined" ? null : moment(convertToDateObject(vm.modifyList.C017)).format("DD-MMM-YYYY");
              vm.taxExcemptionDtlsMod = convertToColumnValuesArrayList(items);
              break;
            }
          }

          var requestData = {
            panId: vm.pkColValOut,
            taxExcemptionModifyDtoList: vm.taxExcemptionDtlsMod,
          };
           vm.loader = true;
          // Send the request
          tdsExcemptionMasterService.getModifySubmit(requestData).then(
            (response) => {
              vm.loader = false;
              var message = "TDS Exemption Certificate Details Modified Successfully";
              Swal.fire("Modified!", message, "success");
              location.href = "#!/tds_exemption_master/action/inquiry";
            },
            function (error) {
              vm.loader = false;
              handleErrorResponse(error);
            }
          );
      }  }
      );
    }

    function getVerifySubmit() {
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
//          showLoaderOnConfirm: true,
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
          var requestData = {
            panId: vm.pkColValOut,
          };
          tdsExcemptionMasterService.getVerifySubmit(requestData).then(
            (response) => {
              vm.loader = false;

              //  vm.responseResult = response.data.result.panNo;
              //  var message = "Tax Excemption Details Verified Successfully\n PAN Number: " +  vm.responseResult;
              var message = response.data.message;

              Swal.fire("Verified!", "TDS Exemption Certificate Details Verified Successfully", "success");
              location.href = "#!/tds_exemption_master/action/inquiry";
            },
            function (error) {
              vm.loader = false;
              handleErrorResponse(error);
            }
          );
       } }
      );
    }

    function getDeleteSubmit() {
//      Swal.fire(
//        {
//          title: "Are you sure?",
//          text: "Do you want to Really Delete this record?",
//          type: "warning",
//          showCancelButton: true,
//          cancelButtonClass: "btn-cancel",
//          confirmButtonClass: "btn-primary",
//          confirmButtonText: "Yes",
//          closeOnConfirm: false,
//          showLoaderOnConfirm: true,
//        },
//        function () {




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







          var requestData = {
            panId: vm.panId,
          };
          tdsExcemptionMasterService.getDeleteSubmit(requestData).then(
            (response) => {
              vm.loader = false;

              //  vm.responseResult = response.data.result.panNo;
              //  var message = "Tax Excemption Details Verified Successfully\n PAN Number: " +  vm.responseResult;
              var message = response.data.message;
              //Swal.fire("Success", message, "success");

              Swal.fire("Deleted!", "Exemption Certificate Details Deleted Successfully", "success");
              location.href = "#!/tds_exemption_master/action/inquiry";
            },
            function (error) {
              vm.loader = false;
              handleErrorResponse(error);
            }
          );
       } }
      );
    }

    // Cancel Confirmation
    function showCancelConfirmation(state) {
   //   Swal.fire(
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
              text: "Do you Really Want To Cancel?",
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
       } }
      );
    }

    function getAddDetails() {
      vm.loader = true;
      var requestData = {
        panNumber: vm.panNumber,
      };

      tdsExcemptionMasterService.getAdd(requestData).then(
        function (response) {
          vm.loader = false;
          vm.addList = response.data.result[0];
          vm.addResList = response.data.result;
          vm.addResList.forEach(function (item) {
            vm.blaCountSum += parseInt(item.blaCount);
            vm.subScriptionCountSum += parseInt(item.subScriptionCount);

            vm.uniqueSchemeNames[item.schemeName] = true;
            vm.uniqueModeOfHoldings[item.modeOfHolding] = true;
          });

          vm.distinctSchemeNames = Object.keys(vm.uniqueSchemeNames).join(" / ");
          vm.distinctModeOfHoldings = Object.keys(vm.uniqueModeOfHoldings).join(" / ");
          //vm.addList =vm.auditInfo.cursorOut[0];

          configDataTable();
        },
        function (error) {
          vm.loader = false;

          if (error.data.message == "No Data Found") {
            vm.noDataFound = error.data.message;
          } else {
            handleErrorResponse(error);
            $state.go("tds_exemption_master.inquiry");
          }
        }
      );
    }

    function getModifyDetails(flag) {
      if (flag == "torch") {
        vm.loader = true;

        var requestData = {
          panNo: vm.panNumber,
          flag: "N",
        };
        tdsExcemptionMasterService.getModify(requestData).then(
          function (response) {
            vm.loader = false;

            vm.panNumberDetailList = response.data.result.schemeDetailList;

            angular.element("#panNoModal").modal("toggle");
          },
          function (error) {
            handleErrorResponse(error);
            //  vm.loader = false;
            $state.go("tds_exemption_master.inquiry");
          }
        );
      } else {
        vm.loader = true;
        var requestData = {
          panNo: vm.panNumber,
          flag: "Y",
//          effFromDate: getFormattedEffFromDate(),
        };

        tdsExcemptionMasterService.getModify(requestData).then(
          function (response) {
            vm.loader = false;
            vm.ModDetails = response.data.result.schemeDetailMap || [];
            replaceStateCodeInAddress(vm.ModDetails);
            vm.modeOfHolding = vm.ModDetails[0] ? vm.ModDetails[0].modeOfHolding : "";
            vm.auditDetails = response.data.result;
            vm.rejectionRemarks = response.data.result.rejectionRemarks;

            vm.preOprType = vm.auditDetails.preOprType;
            vm.pkColValOut = response.data.result.pkColValOut;
            vm.modifyList = response.data.result.oldNewValueMap || {};
            initializeOldNewValueMapFields(vm.modifyList);
            captureModifyOriginalSnapshot();
            normalizeModifyIncomeFieldsOnLoad();
            vm.modifyList.C027 = vm.modifyList.C027 || {};
            if (!vm.modifyList.C027.oldValue) {
                vm.modifyList.C027.oldValue = 'RES';
            } else if (!vm.modifyList.C027.newValue) {
                vm.modifyList.C027.newValue = 'RES';
            }
            vm.modifyList.C012 = vm.modifyList.C012 || {};
            if (!vm.modifyList.C012.oldValue) {
                vm.modifyList.C012.oldValue = 'Y';
            }
            if (vm.modifyList && vm.modifyList.C009 && vm.modifyList.C009.oldValue) {
                vm.modifyList.C009.oldValue =convertToDateObject(vm.modifyList.C009.oldValue);
            }
            if (vm.modifyList && vm.modifyList.C010 && vm.modifyList.C010.oldValue) {
                vm.modifyList.C010.oldValue =convertToDateObject(vm.modifyList.C010.oldValue);
            }
            if (vm.modifyList && vm.modifyList.C017 && vm.modifyList.C017.oldValue) {
                vm.modifyList.C017.oldValue = convertToDateObject(vm.modifyList.C017.oldValue);
            }
            if (vm.modifyList?.C003?.oldValue === 'TF001' ||
                vm.modifyList?.C003?.oldValue === 'TF002') {
                vm.modifyList.C003.oldValue = null;
            }
            if (vm.modifyList && vm.modifyList.C011) {
              vm.getFinancialYear();
              adjustModifyDeclarationDatePickerBounds();
              vm.updateToDateMin();
            }
            if (vm.modifyList?.C003?.oldValue === "TF006") {
              normalizeModifyItrFieldsOnLoad();
              vm.initTwoTaxYearsListForModify(true);
            }
            //vm.modifyList.C017.oldValue = vm.solDate;
            //                                                           vm.modifyList.C017.oldValue = convertToDateObject(vm.modifyList.C017.oldValue);
           if (vm.modifyList?.certFromDate) {
               vm.modifyList.certFromDate = convertToDateObject(vm.modifyList.certFromDate);
           }
           if (vm.modifyList?.certToDate) {
               vm.modifyList.certToDate = convertToDateObject(vm.modifyList.certToDate);
           }
            if (vm.modifyList.noLowTax == "LOW TAX") {
              vm.modifyList.noLowTax = "Y";
            } else if (vm.modifyList.noLowTax == "NO TAX") {
              vm.modifyList.noLowTax = "N";
            }

            //                                                     vm.modResList = response.data.result;
            //                                                     vm.modResList.forEach(function(item) {
            //
            //                                                         vm.blaCountSum += parseInt(item.blaCount);
            //                                                         vm.subScriptionCountSum += parseInt(item.subScriptionCount);
            //
            //
            //                                                         vm.uniqueSchemeNames[item.schemeName] = true;
            //                                                         vm.uniqueModeOfHoldings[item.modeOfHolding] = true;
            //                                                     });
            //
            //
            //                                                     vm.distinctSchemeNames = Object.keys(vm.uniqueSchemeNames).join(" / ");
            //                                                     vm.distinctModeOfHoldings = Object.keys(vm.uniqueModeOfHoldings).join(" / ");
            //                                              configDataTable();
          },
          function (error) {
            vm.loader = false;

            if (error.data.message == "No Data Found") {
              vm.noDataFound = error.data.message;
            } else {
              handleErrorResponse(error);
              $state.go("tds_exemption_master.inquiry");
            }
          }
        );
      }
    }

    function getVerifyDetails(flag) {
      if (flag == "torch") {
        vm.loader = true;

        var requestData = {
//          panNo: vm.panNumber,
        };

        tdsExcemptionMasterService.getVerify(requestData).then(
          function (response) {
            vm.loader = false;
            vm.panNumberDetailList = response.data.result.schemeDetailList;
            angular.element("#panNoModal").modal("toggle");
          },
          function (error) {
            handleErrorResponse(error);
            vm.loader = false;
          }
        );
      } else {
        vm.loader = true;
        var requestData = {
          panNo: vm.panNumber,
          panId: vm.panId,
        };

        tdsExcemptionMasterService.getVerify(requestData).then(
          function (response) {
            vm.loader = false;
            vm.auditDetails = response.data.result;
            vm.rejectionRemarks = response.data.result.rejectionRemarks;
            vm.panId = response.data.result.panId;
            vm.preOprType = vm.auditDetails.preOprType;
            vm.pkColValOut = response.data.result.pkColValOut;
            vm.verDetails = response.data.result.oldNewValueMap || {};
            initializeOldNewValueMapFields(vm.verDetails);
            //                            if(vm.verDetails.certificateType.oldValue=='TAX'){
            //                           vm.verDetails.certificateType.oldValue='TAX Exemption Certificate';
            //                           }
            //                           else if(vm.verDetails.certificateType.newValue=='TAX'){
            //                           vm.verDetails.certificateType.newValue='TAX Exemption Certificate';
            //                           }
            vm.verSchemeDetails = response.data.result.schemeDetailMap;
            replaceStateCodeInAddress(vm.verSchemeDetails);
            //                            vm.verSchemeDetails.forEach(function(item) {
            //
            //                          vm.blaCountSum += parseInt(item.blaCount);
            //                          vm.subScriptionCountSum += parseInt(item.SubscriptionCount);
            //
            //
            //                          vm.uniqueSchemeNames[item.schemeName] = true;
            //                          vm.uniqueModeOfHoldings[item.modeOfHolding] = true;
            //                      });
            //
            //
            //                      vm.distinctSchemeNames = Object.keys(vm.uniqueSchemeNames).join(" / ");
            //                      vm.distinctModeOfHoldings = Object.keys(vm.uniqueModeOfHoldings).join(" / ");
            //
            //                    configDataTable();
          },
          function (error) {
            vm.loader = false;

            if (error.data.message == "No Data Found") {
              vm.noDataFound = error.data.message;
            } else {
              handleErrorResponse(error);
              $state.go("tds_exemption_master.inquiry");
            }
          }
        );
      }
    }

    vm.setInquiryExemptionDocumentType = function (item) {
      vm.exemptionDocumentTypeCode = item.exemptionDocumentType;
      vm.exemptionDocumentType = $filter("getLabelFromValuesArray")(item.exemptionDocumentType, vm.taxType);
    };

    function getInquiryDetails(flag) {
      if (flag == "torch") {
        vm.loader = true;

        var requestData = {
          panNo: vm.panNumber,
          flag: "N",
        };

        tdsExcemptionMasterService.getInquiry(requestData).then(
          function (response) {
            vm.loader = false;
            vm.panNumberDetailList = response.data.result.schemeDetailList;
            angular.element("#panNoModal").modal("toggle");
          },
          function (error) {
            handleErrorResponse(error);
            vm.loader = false;
          }
        );
      } else if (flag == "version") {
        vm.loader = true;

        var requestData = {
          panNo: vm.panNumber,
          flag: "",
        };

        tdsExcemptionMasterService.getInquiry(requestData).then(
          function (response) {
            vm.loader = false;
            vm.versionNumberList = response.data.result.schemeDetailList;
            angular.element("#exemptionDocumentTypeModal").modal("toggle");
            getEffectFromDateInquiry();
          },
          function (error) {
            handleErrorResponse(error);
            vm.loader = false;
          }
        );
      } else if (flag == "exemption") {
                vm.loader = true;

                var requestData = {
                  panNo: vm.panNumber,
                  flag: "S",
                };

                tdsExcemptionMasterService.getInquiry(requestData).then(
                  function (response) {
                    vm.loader = false;
                    console.log(requestData.data)
                    vm.exemptionDocumentList = response.data.result.schemeDetailList;
                    angular.element("#exemptionDocumentTypeModal").modal("toggle");
                  },
                  function (error) {
                    handleErrorResponse(error);
                    vm.loader = false;
                  }
                );
      } else {
                    vm.loader = true;

                    var requestData = {
                      panNo: vm.panNumber,
            //          versionNumber: vm.versionNo,
                      flag: "Y",
                      tableId: vm.tableId,
                    };

                    tdsExcemptionMasterService.getInquiry(requestData).then(
                      function (response) {
                        vm.loader = false;
                        var responseMessage = response.data && response.data.message;
                        if (responseMessage && responseMessage !== "SUCCESS") {
                          if (responseMessage === "No Data Found") {
                            vm.noDataFound = responseMessage;
                            Swal.fire("Warning!", responseMessage, "warning");
                          } else {
                            Swal.fire("Error!", responseMessage, "error");
                          }
                          $state.go("tds_exemption_master.inquiry");
                          return;
                        }
                        vm.auditDetails = response.data.result;
                        vm.rejectionRemarks = response.data.result.rejectionRemarks;
                        vm.preOprType = vm.auditDetails.preOprType;
                        vm.inqDetails = response.data.result.oldNewValueMap || {};
                        initializeOldNewValueMapFields(vm.inqDetails);

                        vm.inqSchemeDetails = response.data.result.schemeDetailMap;
                        if (!vm.inqSchemeDetails || !vm.inqSchemeDetails.length) {
                          Swal.fire("Warning!", "No Data Found", "warning");
                          $state.go("tds_exemption_master.inquiry");
                          return;
                        }
                        replaceStateCodeInAddress(vm.inqSchemeDetails);
                        enrichInquiryDetails();
                        vm.blaCountSum = 0; // Initialize sum
                        vm.subScriptionCountSum = 0; // Initialize sum
                        vm.uniqueSchemeNames = {};
                        vm.uniqueModeOfHoldings = {};

                        //            vm.inqSchemeDetails.forEach(function(item) {
                        //                vm.blaCountSum += parseInt(item.blaCount);
                        //                vm.subScriptionCountSum += parseInt(item.SubscriptionCount);
                        //                vm.uniqueSchemeNames[item.schemeName] = true;
                        //                vm.uniqueModeOfHoldings[item.modeOfHolding] = true;
                        //            });
                        //
                        //            vm.distinctSchemeNames = Object.keys(vm.uniqueSchemeNames).join(" / ");
                        //            vm.distinctModeOfHoldings = Object.keys(vm.uniqueModeOfHoldings).join(" / ");
                        //
                        //            configDataTable();
              },
                  function (error) {
                    vm.loader = false;

                    if (error.data.message == "No Data Found") {
                      vm.noDataFound = error.data.message;
                    } else {
                      handleErrorResponse(error);
                      $state.go("tds_exemption_master.inquiry");
                    }
              }
        );
      }
    }

    function getDeleteDetails() {
      vm.loader = true;
      var requestData = {
        panNumber: vm.panNumber,
        panId: vm.panId,
      };

      tdsExcemptionMasterService.getDelete(requestData).then(
        function (response) {
          vm.loader = false;
          vm.auditDetails = response.data.result;
          vm.panId = response.data.result.panId;
          vm.preOprType = vm.auditDetails.preOprType;
          vm.delDetails = response.data.result.oldNewValueMap;
          if (vm.delDetails.certificateType.oldValue == "TAX") {
            vm.delDetails.certificateType.oldValue = "TAX Exemption Certificate";
          } else if (vm.delDetails.certificateType.newValue == "TAX") {
            vm.delDetails.certificateType.newValue = "TAX Exemption Certificate";
          }
          vm.delSchemeDetails = response.data.result.schemeDetailMap;
          vm.delSchemeDetails.forEach(function (item) {
            vm.blaCountSum += parseInt(item.blaCount);
            vm.subScriptionCountSum += parseInt(item.SubscriptionCount);

            vm.uniqueSchemeNames[item.schemeName] = true;
            vm.uniqueModeOfHoldings[item.modeOfHolding] = true;
          });

          vm.distinctSchemeNames = Object.keys(vm.uniqueSchemeNames).join(" / ");
          vm.distinctModeOfHoldings = Object.keys(vm.uniqueModeOfHoldings).join(" / ");

          configDataTable();
        },
        function (error) {
          vm.loader = false;

          if (error.data.message == "No Data Found") {
            vm.noDataFound = error.data.message;
          } else {
            handleErrorResponse(error);
            $state.go("tds_exemption_master.inquiry");
          }
        }
      );
    }

    vm.rejectReason = function () {
      angular.element("#permissionMappingDelete").modal("toggle");
    };

    vm.getRemarksList = function () {
      vm.referenceId = "057";
      var requestData = { referenceId: vm.referenceId };

      tdsExcemptionMasterService.getRemarksList(requestData).then(
        function (response) {
          vm.remarksList = response.data.result || []; // Ensure it's an array
        },
        function (error) {
          handleErrorResponse(error);
        }
      );
    };

    vm.deleteSubmit = function () {
          vm.loader = true;
          let requestData = {
                  panId: vm.pkColValOut,
                  rejectionRemarks: vm.remarks,
                };
          tdsExcemptionMasterService.getDeleteSubmit(requestData).then(
            function (response) {
              if (response.data.message == "SUCCESS") {
                         var message = "Rejected Successfully";
                         Swal.fire("Success", message, "success");
                         location.href = "#!/tds_exemption_master/action/inquiry";
              } else {
                Swal.fire("WARNING!", response.data.message, "error");
                location.href = "#!/tds_exemption_master/action/verify/detail";
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
              location.href = "#!/tds_exemption_master/action/verify/detail";
            }
          );
        };


    //function getEffectFromDate() {
    //    console.log("Selected Pan No:", vm.panNumber);
    //    vm.loader = true;
    //    var requestData = {};
    //    tdsExcemptionMasterService.getModify(requestData).then(function(response) {
    //        vm.loader = false;
    //        vm.effFromDate = null;
    //        vm.panNumberDetailList.forEach(function(item) {
    //            if (item.panNo === vm.panNumber) {
    //                vm.effFromDate = convertToDateObject(item.effFromDate);
    //                vm.versionNo = item.versionNumber;
    //                console.log("Effective from date for selected scheme:", vm.effFromDate);
    //            }
    //        });
    //
    //        vm.effFromDateOptions = {
    //            minDate: vm.effFromDate,
    //            showWeeks: false
    //        };
    //
    //        $scope.$apply();
    //    }, function (error) {
    //        vm.loader = false;
    //        handleErrorResponse(error);
    //    });
    //}

    //function getEffectFromDate() {
    //    console.log("Selected PAN No:", vm.panNumber);
    //    vm.loader = true;
    //
    //    // Find the selected PAN number details from the list
    //    var selectedItem = vm.panNumberDetailList.find(function(item) {
    //        return item.panNo === vm.panNumber;
    //    });
    //
    //    if (selectedItem) {
    //        // Set effective date and version number from the selected item
    //        vm.effFromDate = convertToDateObject(selectedItem.effFromDate);
    //        vm.versionNo = selectedItem.versionNumber;  // Set the version number
    //        console.log("Effective from date for selected PAN:", vm.effFromDate);
    //    } else {
    //        // Handle the case where no matching PAN number is found
    //        vm.effFromDate = null;
    //        vm.versionNo = null;  // Reset version number if no matching PAN is found
    //        console.log("No details found for the selected PAN number.");
    //    }
    //
    //    // Set effective date options
    //    if (vm.effFromDate) {
    //        vm.effFromDateOptions = {
    //            minDate: vm.effFromDate,
    //            showWeeks: false
    //        };
    //    } else {
    //        // Optionally reset date options if no effective date is found
    //        vm.effFromDateOptions = {
    //            minDate: null,
    //            showWeeks: false
    //        };
    //    }
    //
    //    vm.loader = false;
    //    // No need for $scope.$apply() if this function is called within AngularJS context
    //}

    function getEffectFromDateVerify() {
      console.log("Selected Pan No:", vm.panNumber);
      vm.loader = true;
      var requestData = {};
      tdsExcemptionMasterService.getVerify(requestData).then(
        function (response) {
          vm.loader = false;
          vm.effFromDate = null;
          vm.panNumberDetailList.forEach(function (item) {
            if (item.panNo === vm.panNumber) {
              vm.effFromDate = convertToDateObject(item.effFromDate);
              vm.versionNo = item.versionNumber;
              console.log("Effective from date for selected scheme:", vm.effFromDate);
            }
          });

          vm.effFromDateOptions = {
            minDate: vm.effFromDate,
            showWeeks: false,
          };

         // $scope.$apply();
        },
        function (error) {
          vm.loader = false;
          handleErrorResponse(error);
        }
      );
    }
    function getEffectFromDateInquiry() {
      console.log("Selected Version No:", vm.versionNo);

      // Ensure the loader is shown
      vm.loader = true;
      vm.effFromDate = null;

      // Check if versionNumberList is populated
      if (vm.versionNumberList) {
        vm.versionNumberList.forEach(function (item) {
          if (item.versionNumber === vm.versionNo) {
            vm.effFromDate = convertToDateObject(item.effFromDate);
            console.log("Effective from date for selected version:", vm.effFromDate);
          }
        });

        // Set effective date options
        vm.effFromDateOptions = {
          minDate: vm.effFromDate,
          showWeeks: false,
        };
      } else {
        console.log("No versionNumberList available");
      }

      // Hide the loader after processing
      vm.loader = false;
    }
    function getEffectFromDate() {
      console.log("Selected PAN No:", vm.panNumber);

      vm.loader = true;

      vm.panNumberDetailList.forEach(function (item) {
        if (item.panNo === vm.panNumber) {
          var dateStr = item.effFromDate || item.declarationReceivedDate;
          if (dateStr) {
            var parsedDate = moment(dateStr, ["DD-MMM-YYYY", "DD-MM-YYYY", "DD/MM/YYYY"], true);
            vm.effFromDate = parsedDate.isValid() ? parsedDate.toDate() : convertToDateObject(dateStr);
            vm.panEffFromDate = dateStr;
          }
          vm.versionNo = item.versionNumber;
          console.log("Effective from date for selected version:", vm.effFromDate);
        }
      });

      if (!vm.effFromDate && vm.panEffFromDate) {
        var fallbackDate = moment(vm.panEffFromDate, ["DD-MMM-YYYY", "DD-MM-YYYY", "DD/MM/YYYY"], true);
        if (fallbackDate.isValid()) {
          vm.effFromDate = fallbackDate.toDate();
        }
      }

      vm.effFromDateOptions = {
        minDate: vm.effFromDate,
        showWeeks: false,
      };

      vm.loader = false;
    }

    function dateValidationForModify() {
      commonService.getSolDate().then(function (response) {
        vm.solDate = response.data.solDate;
        vm.effFromDateOptions = {
          minDate: vm.solDate,
        };
      }, handleErrorResponse);
    }

    function dateValidation() {
      vm.dateToOptions = {
        minDate: vm.certificateValidFromDate,
      };
      vm.certificateValidToDate = "";
    }

   /* function declarationDateValidation() {
      vm.dateToOption = {
        minDate: vm.solDate,
        maxDate: vm.solDate,
      };
      //                                      vm.certificateValidToDate='';
    }*/

    function dateModValidation() {
      vm.dateToOptions = {
        minDate: vm.modifyList.certFromDate,
      };
      vm.modifyList.certToDate = "";
    }

    /*function validatePercentage(flag){
         var msg='Percentage should be less than or equal to 100';
         if(flag=='add'){
         if(parseInt(vm.C009)>100)
         Swal.fire('Warning!', msg, 'warning');
         vm.C009='';
         }
          if(flag=='mod'){
                  if(parseInt(vm.modifyList.lowTaxAmtPercent)>100)
                  Swal.fire('Warning!', msg, 'warning');
                  vm.modifyList.lowTaxAmtPercent='';
                  }
         }*/

    function getFormattedEffFromDate() {
      return formatSubmitDate(vm.panEffFromDate)
        || formatSubmitDate(vm.effFromDate)
        || formatSubmitDate(vm.modifyList && vm.modifyList.C002 && vm.modifyList.C002.oldValue);
    }

    function getEffectiveFromDate() {
        vm.getModifyDetails();

//      const effFromDateFormatted = getFormattedEffFromDate();
//      const panEffFromDateFormatted = moment(vm.panEffFromDate, ["DD-MMM-YYYY", "DD-MM-YYYY", "DD/MM/YYYY"], true).isValid()
//        ? moment(vm.panEffFromDate, ["DD-MMM-YYYY", "DD-MM-YYYY", "DD/MM/YYYY"], true).format("DD-MMM-YYYY")
//        : effFromDateFormatted;
//
//      const areEqual = effFromDateFormatted === panEffFromDateFormatted;
//
//      if (areEqual) {
//                  Swal.fire({
//                  title: "Record already exists for the effective date",
//                   text: "Do you wish to continue?",
//                    icon: "warning", // use "icon", not "type"
//                    showCancelButton: true,
//                    confirmButtonText: "Yes",
//                    cancelButtonText: "Cancel",
//                    customClass: {
//                      confirmButton: "btn-primary",
//                      cancelButton: "btn-cancel"
//                    },
//                    buttonsStyling: false
//                  }).then((result) => {
//
//            if (result.isConfirmed) {
//              vm.getModifyDetails();
//            } else {
//              // Reset the effective date to the original value
//              vm.effFromDate = vm.panEffFromDate;
//              $state.go("tds_exemption_master.modify");
//              vm.loader = false;
//            }
//          }
//        );
//      } else {
//        vm.getModifyDetails();
//      }
    }

    const convertToColumnValuesArrayList = (objectToBeConvert) => {
      if (typeof objectToBeConvert === "object") {
        let resultArrayOther = [];
        for (let key of Object.keys(objectToBeConvert)) {
          // Check for null or undefined, not just truthiness
          if (objectToBeConvert[key] !== undefined && objectToBeConvert[key] !== null) {
            resultArrayOther.push({ colName: key, value: objectToBeConvert[key] });
          }
        }
        return resultArrayOther;
      } else {
        throw "Only objects can be converted";
      }
    };

    function convertNumberToWords(subscriptionAmt) {
      var words = new Array();
      words[0] = "Zero";
      words[1] = "One";
      words[2] = "Two";
      words[3] = "Three";
      words[4] = "Four";
      words[5] = "Five";
      words[6] = "Six";
      words[7] = "Seven";
      words[8] = "Eight";
      words[9] = "Nine";
      words[10] = "Ten";
      words[11] = "Eleven";
      words[12] = "Twelve";
      words[13] = "Thirteen";
      words[14] = "Fourteen";
      words[15] = "Fifteen";
      words[16] = "Sixteen";
      words[17] = "Seventeen";
      words[18] = "Eighteen";
      words[19] = "Nineteen";
      words[20] = "Twenty";
      words[30] = "Thirty";
      words[40] = "Forty";
      words[50] = "Fifty";
      words[60] = "Sixty";
      words[70] = "Seventy";
      words[80] = "Eighty";
      words[90] = "Ninety";
      subscriptionAmt = subscriptionAmt.toString();
      var atemp = subscriptionAmt.split(".");
      var number = atemp[0].split(",").join("");
      var n_length = number.length;
      var words_string = "";
      var paisa_string = "";
      if (atemp.length > 1) {
        var paisa = atemp[1].split(",").join("");
        var paisa_length = paisa.length;
      } else {
        paisa_string += words[0] + " ";
      }

      if (n_length <= 9) {
        var n_array = new Array(0, 0, 0, 0, 0, 0, 0, 0, 0);
        var received_n_array = new Array();
        for (var i = 0; i < n_length; i++) {
          received_n_array[i] = number.substr(i, 1);
        }
        for (var i = 9 - n_length, j = 0; i < 9; i++, j++) {
          n_array[i] = received_n_array[j];
        }
        for (var i = 0, j = 1; i < 9; i++, j++) {
          if (i == 0 || i == 2 || i == 4 || i == 7) {
            if (n_array[i] == 1) {
              n_array[j] = 10 + parseInt(n_array[j]);
              n_array[i] = 0;
            }
          }
        }
        let value = "";
        for (var i = 0; i < 9; i++) {
          if (i == 0 || i == 2 || i == 4 || i == 7) {
            value = n_array[i] * 10;
          } else {
            value = n_array[i];
          }
          if (value != 0) {
            words_string += words[value] + " ";
          }
          if (value == "0" && i == 8) {
            words_string += words[value] + " ";
          }
          if ((i == 1 && value != 0) || (i == 0 && value != 0 && n_array[i + 1] == 0)) {
            words_string += "Crores ";
          }
          if ((i == 3 && value != 0) || (i == 2 && value != 0 && n_array[i + 1] == 0)) {
            words_string += "Lakhs ";
          }
          if ((i == 5 && value != 0) || (i == 4 && value != 0 && n_array[i + 1] == 0)) {
            words_string += "Thousand ";
          }
          if (i == 6 && value != 0 && n_array[i + 1] != 0 && n_array[i + 2] != 0) {
            words_string += "Hundred and ";
          } else if (i == 6 && value != 0) {
            words_string += "Hundred ";
          }
        }
        words_string = words_string.split("  ").join(" ");
      }
      if (paisa_length < 3) {
        var n_array = new Array(0, 0, 0, 0, 0, 0, 0, 0, 0);
        var received_n_array = new Array();
        for (var i = 0; i < paisa_length; i++) {
          received_n_array[i] = paisa.substr(i, 1);
        }
        for (var i = 9 - paisa_length, j = 0; i < 9; i++, j++) {
          n_array[i] = received_n_array[j];
        }
        for (var i = 0, j = 1; i < 9; i++, j++) {
          if (i == 0 || i == 2 || i == 4 || i == 7) {
            if (n_array[i] == 1) {
              n_array[j] = 10 + parseInt(n_array[j]);
              n_array[i] = 0;
            }
          }
        }
        let value = "";
        for (var i = 0; i < 9; i++) {
          if (i == 0 || i == 2 || i == 4 || i == 7) {
            value = n_array[i] * 10;
          } else {
            value = n_array[i];
          }
          if (value != 0) {
            paisa_string += words[value] + " ";
          } else {
            paisa_string += words[value] + " ";
          }
        }
        paisa_string = paisa_string.split("  ").join(" ");
      }
      vm.netPaymentInWords = "Rupees  " + words_string + " ONLY";
      return vm.netPaymentInWords;
    }
  }
})();

(function () {
  "use strict";

  function parseIndianAmountValue(value) {
    if (value === null || value === undefined || value === "") {
      return value;
    }
    return String(value).replace(/,/g, "");
  }

  function formatIndianAmountDisplay(value) {
    if (value === null || value === undefined || value === "") {
      return "";
    }
    var normalized = String(value).replace(/,/g, "").trim();
    if (!normalized || normalized === ".") {
      return normalized;
    }
    if (!/^\d*\.?\d*$/.test(normalized)) {
      return String(value);
    }
    var parts = normalized.split(".");
    var integerPart = parts[0];
    var decimalPart = parts.length > 1 ? parts[1] : null;
    if (!integerPart && decimalPart !== null) {
      integerPart = "0";
    }
    if (!integerPart) {
      return normalized;
    }
    var formattedInteger = Number(integerPart).toLocaleString("en-IN");
    if (decimalPart !== null) {
      return formattedInteger + "." + decimalPart;
    }
    return formattedInteger;
  }

  function sanitizeAmountInput(value) {
    if (value === null || value === undefined || value === "") {
      return "";
    }
    var cleaned = String(value).replace(/,/g, "").replace(/[^0-9.]/g, "");
    var dotIndex = cleaned.indexOf(".");
    if (dotIndex !== -1) {
      cleaned =
        cleaned.substring(0, dotIndex + 1) +
        cleaned.substring(dotIndex + 1).replace(/\./g, "");
    }
    var parts = cleaned.split(".");
    if (parts[0] && parts[0].length > 15) {
      parts[0] = parts[0].substring(0, 15);
    }
    if (parts.length > 1 && parts[1] && parts[1].length > 2) {
      parts[1] = parts[1].substring(0, 2);
    }
    return parts.length > 1 ? parts[0] + "." + parts[1] : parts[0];
  }

  function getNewCaretPosition(oldDisplay, newDisplay, oldCaret) {
    var significantBefore = 0;
    var i;
    for (i = 0; i < oldCaret && i < oldDisplay.length; i++) {
      if (/[\d.]/.test(oldDisplay[i])) {
        significantBefore++;
      }
    }
    if (significantBefore === 0) {
      return 0;
    }
    var significantSeen = 0;
    for (i = 0; i < newDisplay.length; i++) {
      if (/[\d.]/.test(newDisplay[i])) {
        significantSeen++;
        if (significantSeen === significantBefore) {
          return i + 1;
        }
      }
    }
    return newDisplay.length;
  }

  angular.module("gbmUltraApp").filter("indianAmount", function () {
    return function (value) {
      return formatIndianAmountDisplay(value);
    };
  });

  angular.module("gbmUltraApp").directive("indianAmountInput", function ($timeout) {
    return {
      restrict: "A",
      require: "ngModel",
      link: function (scope, element, attrs, ngModel) {
        var input = element[0];
        var formatting = false;

        ngModel.$parsers.push(function (viewValue) {
          if (viewValue === null || viewValue === undefined || viewValue === "") {
            return viewValue;
          }
          var parsed = parseIndianAmountValue(viewValue);
          return parsed === null || parsed === undefined ? parsed : String(parsed);
        });

        ngModel.$formatters.push(function (modelValue) {
          if (modelValue === null || modelValue === undefined || modelValue === "") {
            return modelValue;
          }
          return formatIndianAmountDisplay(modelValue);
        });

        scope.$watch(function () {
          return ngModel.$modelValue;
        }, function (newValue) {
          if (formatting || document.activeElement === input || newValue === null || newValue === undefined || newValue === "") {
            return;
          }
          var formatted = formatIndianAmountDisplay(newValue);
          if (formatted !== input.value) {
            formatting = true;
            ngModel.$setViewValue(formatted);
            input.value = formatted;
            $timeout(function () {
              formatting = false;
            }, 0);
          }
        });

        element.on("input", function () {
          if (formatting) {
            return;
          }
          formatting = true;

          var oldValue = input.value;
          var caret = input.selectionStart || 0;
          var raw = sanitizeAmountInput(oldValue);
          var formatted = raw === "" ? "" : formatIndianAmountDisplay(raw);
          var newCaret = getNewCaretPosition(oldValue, formatted, caret);

          input.value = formatted;

          scope.$applyAsync(function () {
            ngModel.$setViewValue(formatted);
          });

          $timeout(function () {
            input.setSelectionRange(newCaret, newCaret);
            formatting = false;
          }, 0);
        });
      },
    };
  });
})();
