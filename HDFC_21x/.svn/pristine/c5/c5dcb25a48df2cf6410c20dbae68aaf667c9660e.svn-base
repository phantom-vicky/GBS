(function () {
  "use strict";

  angular.module("gbmUltraApp").controller("rbibondTocSlabMaintenanceController", rbibondTocSlabMaintenanceController);

  rbibondTocSlabMaintenanceController.$inject = [
    "$scope",
    "$filter",
    "$state",
    "$stateParams",
    "$location",
    "rbibondTocSlabMaintenanceService",
    "commonService",
  ];

  function rbibondTocSlabMaintenanceController($scope, $filter, $state, $stateParams, $location, rbibondTocSlabMaintenanceService, commonService) {
    var vm = this;
    const TABLE_NAME = "T022";

    vm.commonYesNoMetaData = COMMON_YES_NO_METADATA;
    vm.showCancelConfirmation = showCancelConfirmation;
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


    vm.tocSlabList = [{}];
    vm.schemeCode = "";
    vm.rejectReason = rejectReason;
    vm.disableDelete = false;
    vm.addRange = addRange;
    vm.removeRange = removeRange;
    vm.getDcId = getDcId;
    vm.getVersionNum = getVersionNum;
    vm.submitAddForm = submitAddForm;
    vm.submitVerifyForm = submitVerifyForm;
    vm.getRemarksList = getRemarksList;
    //        vm.submitDeleteForm = submitDeleteForm;
    //        vm.fetchModuleDetails = fetchModuleDetails;
    vm.getVersionNumModify = getVersionNumModify;
    //        vm.getModuleName = getModuleName;
    vm.checkRecordExistedVerify = checkRecordExistedVerify;
    vm.getPenionTocList = getPenionTocList;

    vm.updateFilteredNatureOfTocLists = updateFilteredNatureOfTocLists;
    vm.getVerifyDeleteSelect = getVerifyDeleteSelect;
    vm.getInquiryVersionTourch = getInquiryVersionTourch;
    vm.getVerifyVersionTourch = getVerifyVersionTourch;
    vm.getInquirySelect = getInquirySelect;
    vm.modifySelectDetails = modifySelectDetails;
    vm.modifySubmit = modifySubmit;
    vm.getRoundFactList = getRoundFactList;
    vm.updateRowOptions = updateRowOptions;

    vm.getNatureOfTocList = getNatureOfTocList;
    vm.getFilteredOptions = getFilteredOptions;
    vm.initializeWatchers = initializeWatchers;

    vm.deleteSubmit = deleteSubmit;
    vm.getschemeName = getschemeName;
    vm.getEffectFromDate = getEffectFromDate;
    vm.minValueOne = minValueOne;

    vm.newPercentageCheck = newPercentageCheck;
    vm.firstCharDotSgst = firstCharDotSgst;
    vm.firstCharDotCgst = firstCharDotCgst;

    function firstCharDotCgst(cgst) {
      // Check if the input is in an invalid format after changes (e.g. ".11")
      let dotIndex = cgst.indexOf(".");

      // If the input starts with a decimal or only contains the decimal with no leading digits
      if (dotIndex === 0 || (dotIndex === 1 && cgst.length <= 4 && cgst[0] === "0")) {
        vm.cgst = ""; // Clear the decimal and any values after it
      }
    }

    function firstCharDotSgst(sgst) {
      // Check if the input is in an invalid format after changes (e.g. ".11" or "0.11")
      let dotIndex = sgst.indexOf(".");

      // If the input starts with a decimal or only contains the decimal with no leading digits
      if (dotIndex === 0 || (dotIndex === 1 && sgst.length <= 4 && sgst[0] === "0")) {
        vm.sgst = ""; // Clear the decimal and any values after it
      }
    }

    function newPercentageCheck(event) {
      let decimal = event.target.value + event.key;
      let strValueLen = decimal.length;
      let dot = decimal.lastIndexOf(".");
      let dotCount = decimal.split(".").length - 1;
      let numericValue = parseFloat(decimal);

      // Allow only values between 0 and 100, with up to two decimal places
      if (
        numericValue > 100 ||
        dotCount > 1 ||
        (dot == -1 && strValueLen > 3) || // Allow 100 without decimal
        (dot == 1 && strValueLen > 5)
      ) {
        // Allow up to 2 decimal places
        event.preventDefault();
      }
    }

    function minValueOne(event, entity) {
      if (parseInt(event.target.value) == 0) {
        Swal.fire("Error!", "Minimum value is 1", "error");
        vm[entity] = "";
      }
    }
    vm.minValueOneWithIndex = minValueOneWithIndex;
    function minValueOneWithIndex(event, entity, index) {
      if (parseInt(event.target.value) == 0) {
        Swal.fire("Error!", "Minimum value is 1", "error");
        vm.tocSlabList[index][entity] = "";
      }
    }

    vm.challanTypeMetaData = [
      { label: "Receipts", value: "NP001" },
      { label: "Interest Payments", value: "NP002" },
      { label: "Redemption", value: "NP003" },
      { label: "Other Payments", value: "NP004" },
      //                    {label : "Other Payments",value : "NP003"},
    ];

    vm.ChallanTypes = [
      { label: "Physical Challans", value: "P" },
      { label: "E-Receipt Challans", value: "E" },
    ];

    vm.roundingFactorMetaData = [
      { label: "Ceil", value: "RF002" },
      { label: "Floor", value: "RF003" },
      { label: "Round", value: "RF004" },
      { label: "Actual", value: "RF001" },
    ];

    if ($state.current.name == "rbi_bond_toc_slab_maintenance.add_detail") {
      if ($stateParams.obj) {
        console.log("Received parameters:", $stateParams.obj);

        vm.schemeCode = $stateParams.obj.schemeCode;
        vm.versionNo = $stateParams.obj.versionNo;
        vm.effFromDate = $stateParams.obj.effFromDate;
        vm.schemeName = $stateParams.obj.schemeName;
        console.log($stateParams.obj);
        getNatureOfTocList();
        getPenionTocList();
        initializeWatchers();
        getFilteredOptions();
        getLevelRow(1);
        if (vm.error == 1) {
          $state.go("rbi_bond_toc_slab_maintenance.add");
        }
      } else {
        $state.go("rbi_bond_toc_slab_maintenance.add");
      }
    } else if ($state.current.name == "rbi_bond_toc_slab_maintenance.modify_detail") {
      if ($stateParams.obj) {
        vm.schemeCode = $stateParams.obj.schemeCode;
        vm.effeFromDate = $stateParams.obj.effeFromDate;
        vm.schemeName = $stateParams.obj.schemeName;

        modifySelectDetails();
        //getLevelRow(3);
      } else {
        $state.go("rbi_bond_toc_slab_maintenance.modify");
      }
    } else if ($state.current.name == "rbi_bond_toc_slab_maintenance.verify_detail") {
      if ($stateParams.obj) {
        vm.schemeCode = $stateParams.obj.schemeCode;
        vm.versionNo = $stateParams.obj.versionNo;
        vm.effeFromDate = $stateParams.obj.effeFromDate;
        vm.schemeName = $stateParams.obj.schemeName;

        getVerifyDeleteSelect();
      } else {
        $state.go("rbi_bond_toc_slab_maintenance.verify");
      }
    } else if ($state.current.name == "rbi_bond_toc_slab_maintenance.delete_detail") {
      if ($stateParams.obj) {
        vm.schemeCode = $stateParams.obj.schemeCode;
        vm.versionNo = $stateParams.obj.versionNo;
        vm.effeFromDate = $stateParams.obj.effeFromDate;

        getVerifyDeleteSelect();
      } else {
        $state.go("rbi_bond_toc_slab_maintenance.delete");
      }
    } else if ($state.current.name == "rbi_bond_toc_slab_maintenance.inquiry_detail") {
      if ($stateParams.obj) {
        vm.schemeCode = $stateParams.obj.schemeCode;
        vm.versionNo = $stateParams.obj.versionNo;
        vm.effFromDate = $stateParams.obj.effFromDate;
        vm.schemeName = $stateParams.obj.schemeName;

        getInquirySelect();
      } else {
        $state.go("rbi_bond_toc_slab_maintenance.inquiry");
      }
    }

    function getLevelRow(countRow) {
      if (countRow && countRow <= 50) {
        vm.tocSlabList = [];
        for (var i = 1; i <= countRow; i++) {
          vm.tocSlabList.push({});
        }
        return vm.tocSlabList;
      } else {
        vm.tocSlabList = "";
      }
    }
    //        vm.makeOptionsInVisible = function(indexInHierarchy) {
    //            for (let i = 0; i < vm.tocSlabList.length; i++) {
    //                if (indexInHierarchy == i)
    //                    continue;
    //
    //                for (let item of vm.pensionTocList) {
    //                    if (vm.tocSlabList[i].paymentNature != item.value && vm.tocSlabList[indexInHierarchy].paymentNature == item.value) {
    //                        angular.element("#" + item.value + i).hide();
    //                    } else {
    //                        let flag = true;
    //                        for (let levelItem of vm.tocSlabList) {
    //                            if (item.value == levelItem.paymentNature) {
    //                                flag = false;
    //                                break;
    //                            }
    //                        }
    //
    //                        if (flag)
    //                            angular.element("#" + item.value + i).show();
    //                    }
    //                }
    //            }
    //        }

    //
    //        function getModuleName() {
    //            vm.loader = true;
    //            vm.referenceId = 'TAX';
    //            var requestData = {
    //                'referenceId': vm.referenceId
    //            };
    //            rbibondTocSlabMaintenanceService.getRbiBondModuleNameDetails(requestData).then(function(response) {
    //                    vm.loader = false;
    //                    vm.schemeList = response.data.result;
    //                    vm.moduleNameDetailsLabelValue = [];
    //                    angular.forEach(vm.schemeList, function(item) {
    //                        vm.moduleNameDetailsLabelValue.push({
    //                            label: item.subCodeDescription,
    //                            value: item.subCode
    //                        })
    //                    })
    //                },
    //                function(error) {
    //                    handleErrorResponse(error);
    //                    vm.loader = false;
    //                });
    //        }
    //        //Get Rounding Factor

    vm.roudingFactorList = [];

    // Fetch Rounding Factor options
    function getRoundFactList() {
      vm.referenceId = "ROUNDING_FACTOR";
      var requestData = { referenceId: vm.referenceId };

      rbibondTocSlabMaintenanceService.getRbiBondRoundFactor(requestData).then(
        function (response) {
          vm.roudingFactorList = response.data.result || [];

          // Ensure each row has a filtered list of available options
          updateRowOptions();
        },
        function (error) {
          handleErrorResponse(error);
        }
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

    // Update options for each row
    function updateRowOptions() {
      // Collect all selected values from the table, excluding empty selections
      var selectedValues = vm.tocSlabList.map((row) => row.roundOff).filter((value) => value); // Ensure to exclude empty or falsy values

      vm.tocSlabList.forEach(function (item, index) {
        // Filter out already selected values and keep current row's selected value
        item.availableRoundOffOptions = vm.roudingFactorList
          .filter(function (factor) {
            return !selectedValues.includes(factor.SUBCODE) || factor.SUBCODE === item.roundOff;
          })
          .map(function (factor) {
            return {
              label: factor.DESCRIPTION,
              value: factor.SUBCODE,
            };
          });

        // Ensure the current value is always available
        if (item.roundOff && !item.availableRoundOffOptions.some((option) => option.value === item.roundOff)) {
          item.availableRoundOffOptions.push({
            label: vm.roudingFactorList.find((factor) => factor.SUBCODE === item.roundOff).DESCRIPTION,
            value: item.roundOff,
          });
        }
      });
    }

    // Initialize data and watchers
    getRoundFactList();
    $scope.$watch(
      "vm.tocSlabList",
      function (newValue, oldValue) {
        if (newValue !== oldValue) {
          updateRowOptions(); // Update options when tocSlabList changes
        }
      },
      true
    ); // Set deep watch to monitor changes inside array

    //        Check Record already is there or not
    function checkRecordExistedVerify(schemeCode) {
      ////         	alert(vm.schemeCode);
      vm.loader = false;
      var reqDt = {
        schemeCode: schemeCode,
      };

      rbibondTocSlabMaintenanceService.checkRecordExisted(reqDt).then(
        function (response) {
          vm.loader = false;

          vm.versionNo = response.data.result.versionNo;
          vm.effFromDate = response.data.result.effeFromDate;
          if (response.data.result.message == "No Records Found to Delete or Verify") {
            Swal.fire("Warning!", response.data.result.message, "warning");
          }
        },
        function (error) {
          vm.loader = false;
          handleErrorResponse(error);
          //                           	 Swal.fire("Warning!",error.data.message,"warning");
          $state.go("rbi_bond_toc_slab_maintenance.inquiry");
        }
      );
    }

    vm.natureOfTocList = [];
    vm.tocSlabList = [];

    // Fetch Nature of TOC options
    function getNatureOfTocList() {
      vm.referenceId = "NATURE_OF_PAYMENT";
      var requestData = { referenceId: vm.referenceId };

      rbibondTocSlabMaintenanceService.getRbiBondRoundFactor(requestData).then(
        function (response) {
          vm.natureOfTocList = response.data.result || []; // Ensure it's an array
          updateFilteredNatureOfTocLists();
        },
        function (error) {
          handleErrorResponse(error);
        }
      );
    }

    // Fetch Nature of TOC options
    function getRemarksList() {
      vm.referenceId = "055";
      var requestData = { referenceId: vm.referenceId };

      rbibondTocSlabMaintenanceService.getRemarksList(requestData).then(
        function (response) {
          vm.remarksList = response.data.result || []; // Ensure it's an array
        },
        function (error) {
          handleErrorResponse(error);
        }
      );
    }

    // Function to get filtered options for a specific row
    function getFilteredOptions(rowIndex) {
      // Check if natureOfTocList and tocSlabList are defined
      if (!Array.isArray(vm.natureOfTocList) || !Array.isArray(vm.tocSlabList)) {
        return [];
      }

      // Collect all selected values from the table
      var selectedValues = vm.tocSlabList
        .map(function (item) {
          return item.paymentNature;
        })
        .filter(Boolean); // Exclude empty selections

      // Check if the current row index is valid
      if (rowIndex < 0 || rowIndex >= vm.tocSlabList.length) {
        return [];
      }

      // Filter out already selected values for the current row
      return vm.natureOfTocList.filter(function (option) {
        return !selectedValues.includes(option.SUBCODE) || option.SUBCODE === vm.tocSlabList[rowIndex].paymentNature;
      });
    }

    // Update filtered options for all rows
    function updateFilteredNatureOfTocLists() {
      if (!Array.isArray(vm.tocSlabList)) {
        return;
      }

      vm.tocSlabList.forEach(function (item, index) {
        item.filteredOptions = getFilteredOptions(index);
      });
    }

    // Watch for changes in the tocSlabList
    function initializeWatchers() {
      $scope.$watch(
        function () {
          return vm.tocSlabList;
        },
        function (newValue, oldValue) {
          if (newValue !== oldValue) {
            updateFilteredNatureOfTocLists(); // Update options whenever tocSlabList changes
          }
        },
        true
      ); // Set deep watch to monitor changes inside array
    }

    //Get pension TOC Details
    function getPenionTocList() {
      vm.referenceId = "CHALLAN_TYPES";
      var requestData = {
        referenceId: vm.referenceId,
      };
      rbibondTocSlabMaintenanceService.getRbiBondRoundFactor(requestData).then(
        function (response) {
          vm.pensionTocList = response.data.result;
          var countRow = vm.pensionTocList.length;
          //                    console.log("vm.pensionTocList");
          vm.roundOff = vm.pensionTocList;
        },
        function (error) {
          handleErrorResponse(error);
        }
      );
    }
    vm.changeNOT = changeNOT;
    function changeNOT(value1, value2, value3) {
      console.log(value1);
      console.log(value2);
      console.log(value3);
    }
    //       function getNatureOfTocList(){
    //             vm.referenceId = 'NATURE_OF_PAYMENT';
    //             var requestData = {
    //                   'referenceId': vm.referenceId
    //             }
    //             rbibondTocSlabMaintenanceService.getRbiBondRoundFactor(requestData).then(function(response){
    //                    vm.natureOfTocList = response.data.result;
    //                var countRow = vm.natureOfTocList.length;
    //                console.log("vm.natureOfTocList");
    //                vm.roundOff = vm.natureOfTocList;
    //                },
    //                function(error){
    //                handleErrorResponse(error);
    //                }
    //
    //             )
    //       };

    //
    function addRange() {
      if (vm.tocSlabList.length <= 3) {
        vm.tocSlabList.push({});
        vm.disableDelete = true;
      }
    }
    //function addRange() {
    //
    //    if (vm.tocSlabList.length <= 3) {
    //        vm.tocSlabList.push({});
    //        vm.tocSlabList=[];
    //        vm.disableDelete = true;
    //   vm.resultArray1 = [];
    //    }
    //}
    //        function removeRange() {
    //            if (vm.tocSlabList.length > 1) {
    //                vm.lastRow = vm.tocSlabList.length - 1;
    //                vm.tocSlabList.splice(vm.lastRow, 1);
    //                if (vm.lastRow == 1) {
    //                    vm.disableDelete = false;
    //                }
    //            } else {
    //                vm.disableDelete = false;
    //            }
    //        }

    function removeRange(ID, index) {
      if (!vm.deletedRows) {
        vm.deletedRows = [];
      }

      vm.item = vm.tocSlabList.splice(index, 1)[0];

      if (vm.tocSlabList.length === 0) {
        vm.disableDelete = true;
      }

      let deletionData = {};

      if (ID) {
        deletionData["ID"] = vm.item.ID.oldValue;

        deletionData["C008"] = "Y";

        vm.deletedRows.push(deletionData);
        vm.resultDelArray.push([deletionData]);
      }
    }

    function getDcId() {
      vm.searchDcId = "";
      vm.loader = true;
      var requestData = {
        table: "T004",
        columnList: ["C001", "C002"],
      };

      commonService.genericTableSelect(requestData).then(
        (response) => {
          vm.loader = false;
          vm.dcIdList = response.data.result;
          angular.element("#dcIDModal").modal("toggle");
        },
        function (error) {
          vm.loader = false;
          handleErrorResponse(error);
        }
      );
    }

    function getVersionNumModify() {
      rbibondTocSlabMaintenanceService.modifyVerifyTocSlab(vm.schemeCode, vm.treasuryId).then(
        (response) => {
          vm.loader = false;
          vm.versionNo = response.data.result.versionNo;
        },
        function (error) {
          vm.loader = false;
          handleErrorResponse(error);
        }
      );
    }

    function getVersionNum() {
      rbibondTocSlabMaintenanceService.getVersionNum(vm.module, vm.treasuryId).then(
        (response) => {
          vm.loader = false;
          vm.versionNo = response.data.result.versionNo;
        },
        function (error) {
          vm.loader = false;
          handleErrorResponse(error);
        }
      );
    }

    // Modify Select details
    //        function modifySelectDetails() {
    //            vm.loader = true;
    //            var requestData = {
    //
    //
    //
    //
    //                'schemeCode': vm.schemeCode,
    //                'effeFromDate': $filter('date')(vm.effFromDate, 'dd-MMM-yyyy')
    //            };
    //
    //
    //            rbibondTocSlabMaintenanceService.modifySelect(requestData).then(
    //                (response) => {
    //
    //                    vm.loader = false;
    //                    vm.model = response.data.result;
    //                    vm.effFromDate = vm.model.effeFromDate;
    //                    vm.tocSlabList = response.data.result.oldNewValueList;
    //                    vm.versionNo = vm.tocSlabList[0].C002.oldValue;
    //                    vm.effFromDate = convertToDateObject(vm.tocSlabList[0].C003.oldValue);
    //                    //                                      vm.bankGstTinNo = vm.tocSlabList[0].C015.oldValue;
    //                    vm.cgst = vm.tocSlabList[0].C015.oldValue;
    //                    vm.sgst = vm.tocSlabList[0].C016.oldValue;
    //                    vm.effFromDates = vm.effFromDate;
    //
    //                    angular.forEach(vm.schemeList, function(item) {
    //                        if (vm.schemeCode == item.subCode) {
    //                            vm.tocSlabListEffctDate = response.data.result.oldNewValueList[0].C003.oldValue;
    //
    //                        } else {
    //                            vm.tocSlabListEffctDate = '';
    //                        }
    //                    })
    //                    vm.auditInfo = response.data.result;
    //                    if (vm.tocSlabList.length > 1) {
    //                        vm.disableDelete = true;
    //                    }
    //
    //                                  var currentStr = $filter('date')(vm.effFromDate , 'dd-MMM-yyyy');
    //                                                        var dbStr = $filter('date')(vm.effFromDateOld , 'dd-MMM-yyyy');
    //                                                        if (currentStr !== dbStr) {
    //                                                            vm.showAddButton = true;
    //                                                            } else {
    //                                                            vm.showAddButton = false;
    //                                                            }
    //                                        				if (currentStr == dbStr) {
    //                                        					Swal.fire({
    //                                        						title: "Record already exists for the effective date",
    //                                        						text: "Do you wish to continue?",
    //                                        						type: "warning",
    //                                        						showCancelButton: true,
    //                                        						cancelButtonClass: "btn-cancel",
    //                                        						confirmButtonClass: "btn-danger",
    //                                        						confirmButtonText: "Yes",
    //                                        						cancelButtonText: "No",
    //                                        						closeOnConfirm: true
    //                                        					}, function (inputValue) {
    //                                        						if (!inputValue) {
    //                                        							$state.go('rbibond_age_based.modify');
    //                                        						}
    //                                        					});
    //                                        				}
    //
    //                },
    //                function(error) {
    //                    vm.loader = false;
    //                    handleErrorResponse(error);
    //                    $state.go('rbi_bond_toc_slab_maintenance.modify');
    //                    vm.schemeCode = '';
    //                });
    //        }

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
    function modifySelectDetails() {
      vm.loader = true;
      var requestData = {
        schemeCode: vm.schemeCode,
        schemeName: vm.schemeName,
        effeFromDate: $filter("date")(vm.effeFromDate, "dd-MMM-yyyy"),
      };

      rbibondTocSlabMaintenanceService.modifySelect(requestData).then(
        (response) => {
          vm.loader = false;
          vm.model = response.data.result;
          vm.rejectionRemarks = response.data.result.rejectionRemarks;
          //            vm.effFromDate = vm.model.effeFromDate;
          vm.tocSlabList = response.data.result.oldNewValueList;
          vm.versionNo = vm.tocSlabList[0].C002.oldValue;
          if (vm.effFromDate) {
            convertToDateObject(vm.tocSlabList[0].C003.oldValue);
          }
          if (vm.tocSlabList.length > 0) {
            vm.cgst = vm.tocSlabList[0].C015.oldValue;
            vm.sgst = vm.tocSlabList[0].C016.oldValue;
          }

          vm.effFromDates = convertToDateObject(vm.tocSlabList[0].C003.oldValue);

          angular.forEach(vm.schemeList, function (item) {
            if (vm.schemeCode == item.subCode) {
              vm.tocSlabListEffctDate = response.data.result.oldNewValueList[0].C003.oldValue;
            } else {
              vm.tocSlabListEffctDate = "";
            }
          });

          vm.auditInfo = response.data.result;
          if (vm.tocSlabList.length > 1) {
            vm.disableDelete = true;
          }

          var currentStr = $filter("date")(vm.effeFromDate, "dd-MMM-yyyy");
          var dbStr = $filter("date")(vm.effFromDates, "dd-MMM-yyyy");
          if (currentStr !== dbStr) {
            vm.showAddButton = true;
          } else {
            vm.showAddButton = false;
          }

          if (currentStr == dbStr) {
            // Display Swal.fire dialog if the dates are the same
//            Swal.fire(
//              {
//                title: "Record already exists for the effective date",
//                text: "Do you wish to continue?",
//                type: "warning",
//                showCancelButton: true,
//                cancelButtonClass: "btn-cancel",
//                confirmButtonClass: "btn-danger",
//                confirmButtonText: "Yes",
//                cancelButtonText: "No",
//                closeOnConfirm: true,
//              },
//              function (inputValue) {
//                if (!inputValue) {


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

                                       if (!result.isConfirmed) {
                  $state.go("rbi_bond_toc_slab_maintenance.modify");
                }
              }
            );
          }
        },
        function (error) {
          vm.loader = false;
          handleErrorResponse(error);
          $state.go("rbi_bond_toc_slab_maintenance.modify");
          vm.schemeCode = "";
        }
      );
    }

    /* Verify/Delete Select Details */
    function getVerifyDeleteSelect() {
      vm.loader = true;
      var requestData = {
        schemeCode: vm.schemeCode,
        versionNo: vm.versionNo,
        effeFromDate: vm.effeFromDate,
      };
      rbibondTocSlabMaintenanceService.VerifyDeleteTocSlab(requestData).then(
        (response) => {
          vm.loader = false;
          vm.auditInfo = response.data.result;
          vm.rejectionRemarks = response.data.result.rejectionRemarks;
          vm.tocSlabList = response.data.result.oldNewValueList;
          vm.effeFromDate = vm.tocSlabList[0].C003.oldValue;
          vm.versionNo = vm.tocSlabList[0].C002.oldValue;
          //                            vm.bankGstTinNo=vm.tocSlabList[0].C015.oldValue;
          //                    vm.cgst = vm.tocSlabList.C015.oldValue;
          //                    vm.sgst = vm.tocSlabList.C016.oldValue;
          vm.cgst = vm.tocSlabList[0].C015.oldValue;
          vm.sgst = vm.tocSlabList[0].C016.oldValue;
          vm.verifySubmitList = response.data.result.oldNewValueList;
        },
        function (error) {
          vm.loader = false;
          handleErrorResponse(error);
          $state.go("rbi_bond_toc_slab_maintenance.inquiry");
        }
      );
    }

    /* Add Submit */
    //        function submitAddForm() {
    //            if (vm.tocSlabList.length >= 1 || vm.submitAction == 'true') {
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
    //
    //                    function() {
    //
    //                        vm.loader = true;
    //                        let requestData = {
    //                            'schemeCode': vm.schemeCode,
    //                            'versionNo': vm.versionNo,
    //                            'effeFromDate': formatDate(vm.effFromDate),
    //
    //                            'cgst': vm.cgst,
    //                            'sgst': vm.sgst,
    //                            'slabSubDetailsDTOList': vm.tocSlabList
    //                            //$filter('date')(vm.effFromDate, "dd-MMM-yyyy")
    //                        };
    //                        rbibondTocSlabMaintenanceService.submitAddForm(requestData).then(
    //                            (response) => {
    //                                vm.loader = false;
    //                                Swal.fire("Record Added!", "TOC parameter created Successfully", "success");
    //                                location.href = '#!/rbi_bond_toc_slab_maintenance/action/inquiry';
    //                            }, handleErrorResponse);
    //
    //                    },
    //                    function(error) {
    //                        vm.loader = false;
    //                        handleErrorResponse(error);
    //                    });
    //            }
    ////             else {
    ////                Swal.fire("Warning!", "Should be Allow 3 Records", "warning");
    ////            }
    //        }
    function submitAddForm() {
      if (vm.tocSlabList.length >= 1 || vm.submitAction == "true") {
//        Swal.fire(
//          {
//            title: "Are you sure?",
//            text: "Do you want to Really Add this record?",
//            type: "warning",
//            showCancelButton: true,
//            cancelButtonClass: "btn-cancel",
//            confirmButtonClass: "btn-primary",
//            confirmButtonText: "Yes",
//            closeOnConfirm: false,
//          },
//
//          function () {


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
            // Clean up slabSubDetailsDTOList by removing unwanted fields
            let cleanedSlabList = vm.tocSlabList.map((item) => {
              // Create a new object by destructuring and excluding unwanted fields
              let cleanedItem = { ...item };

              // Remove unwanted fields like $$hashKey, filteredOptions, and availableRoundOffOptions
              delete cleanedItem.$$hashKey;
              delete cleanedItem.filteredOptions;
              delete cleanedItem.availableRoundOffOptions;

              // If you need to remove any other fields, add them here
              return cleanedItem;
            });

            vm.loader = true;
            let requestData = {
              schemeCode: vm.schemeCode,
              versionNo: vm.versionNo,
              effeFromDate: formatDate(vm.effFromDate),
              cgst: vm.cgst,
              sgst: vm.sgst,
              slabSubDetailsDTOList: cleanedSlabList, // Use cleaned slab list
            };

            rbibondTocSlabMaintenanceService.submitAddForm(requestData).then((response) => {
              vm.loader = false;
              Swal.fire("Record Added!", "TOC parameter created Successfully", "success");
              location.href = "#!/rbi_bond_toc_slab_maintenance/action/inquiry";
            }, handleErrorResponse);
         } },
          function (error) {
            vm.loader = false;
            handleErrorResponse(error);
          }
        );
      }
    }

    function getEffectFromDate() {
      console.log("Selected scheme name:", vm.schemeName);
      vm.loader = true;
      var requestData = {};
      rbibondTocSlabMaintenanceService.modifySelect(requestData).then(
        function (response) {
          vm.loader = false;
          vm.effeFromDate = null;
          vm.schemeNameList.forEach(function (item) {
            if (item.schemeName === vm.schemeName) {
              vm.effeFromDate = convertToDateObject(item.effFromDate);
              console.log("Effective from date for selected scheme:", vm.effeFromDate);
            }
          });
          vm.effFromDateOptionsM = {
            minDate: vm.effeFromDate,
            showWeeks: false,
          };
          //                $scope.$apply();
        },
        function (error) {
          vm.loader = false;
          handleErrorResponse(error);
        }
      );
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
//        function (yesFlg) {
//          if (yesFlg) {



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
            vm.resultAddArray = [];
            vm.resultDelArray = [];

            angular.forEach(vm.tocSlabList, function (item) {
              if (item.C003) {
                item.C003.oldValue = formatDate(vm.effeFromDate);
              }
              if (item.C004) {
                item.C004.oldValue = formatDate(convertToDateObject(item.C004.oldValue));
              }
              if (item.C005.oldValue == "Y" && item.C011 != undefined) {
                item.C011.oldValue = "0";
              }
              if (item.C001) {
                item.C001.oldValue = vm.schemeCode;
              }
              if (item.C015) {
                item.C015.oldValue = vm.cgst;
              }

              if (item.C016) {
                item.C016.oldValue = vm.sgst;
              }

              // Check if item should be added to resultArray or resultAddArray
              if (item.ID == null || item.ID == undefined) {
                let newItem = Object.assign({}, item);
                newItem.colName = "$$HASHKEY";

                // Ensure C015 and C016 are initialized if they exist
                if (!newItem.C015) {
                  newItem.C015 = {};
                }
                if (!newItem.C016) {
                  newItem.C016 = {};
                }

                // Mutate C015 and C016 in newItem
                newItem.C015.oldValue = vm.cgst; // Assign vm.cgst to C015.oldValue
                newItem.C016.oldValue = vm.sgst; // Assign vm.sgst to C016.oldValue

                // Push newItem to resultAddArray
                vm.resultAddArray.push(convertToColumnValuesArrayList(newItem));
              } else {
                // Push item to resultArray
                vm.resultArray.push(convertToColumnValuesArrayList(item));
              }
            });

            angular.forEach(vm.deletedRows, function (deletedItem) {
              let deletionData = [];
              angular.forEach(deletedItem, function (value, key) {
                deletionData.push({ colName: key, value: value });
              });
              vm.resultDelArray.push(deletionData);
            });

            let requestData = {
              schemeCode: vm.schemeCode,
              versionNo: vm.versionNo,
              effeFromDate: formatDate(vm.effeFromDate),
              cgst: vm.cgst, // Include vm.cgst in requestData if needed
              sgst: vm.sgst, // Include vm.sgst in requestData if needed
              columnValueDetailsList: vm.resultArray,
              columnValueDetailsAddList: vm.resultAddArray,
              columnValueDetailsDelList: vm.resultDelArray,
            };

            rbibondTocSlabMaintenanceService.submitModifyForm(requestData).then(
              (response) => {
                vm.loader = false;
                Swal.fire("Record Modified!", "TOC Slab Master Maintenance Modified Successfully", "success");
                location.href = "#!/rbi_bond_toc_slab_maintenance/action/inquiry";
              },
              function (error) {
                vm.loader = false;
                handleErrorResponse(error);
                $state.go("rbi_bond_toc_slab_maintenance.inquiry");
              }
            );
          } else {
            $state.go("rbi_bond_toc_slab_maintenance.modify_detail");
          }
        }
      );
    }

    const convertToColumnValuesArrayList = (objectToBeConvert) => {
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
    };

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
          let requestData = {
            schemeCode: vm.schemeCode,
            versionNo: vm.versionNo,
            effeFromDate: formatDate(convertToDateObject(vm.effeFromDate)),
          };
          rbibondTocSlabMaintenanceService.submitVerifyForm(requestData).then((response) => {
            vm.loader = false;
            Swal.fire("Record Verified!", "TOC Slab Master Maintenance Verified Successfully’", "success");
            location.href = "#!/rbi_bond_toc_slab_maintenance/action/inquiry";
          }, handleErrorResponse);
     }   },
        function (error) {
          vm.loader = false;
          handleErrorResponse(error);
        }
      );
    }

    function deleteSubmit() {
      vm.loader = true;

      var requestData = {
        schemeCode: vm.schemeCode,
        versionNo: vm.versionNo,
        effeFromDate: formatDate(convertToDateObject(vm.effFromDate)),
        rejectionRemarks: vm.remarks,
      };

      rbibondTocSlabMaintenanceService.submitDeleteForm(requestData).then(
        function (response) {
          if (response.data.message === "SUCCESS") {
            Swal.fire("Rejected!", "TOC Slab Master Maintenance Rejected Successfully", "success");
            location.href = "#!/rbi_bond_toc_slab_maintenance/action/inquiry";
          } else {
            vm.loader = false;
            Swal.fire("WARNING!", response.data.message, "error");
            location.href = "#!/rbi_bond_toc_slab_maintenance/action/inquiry";
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
          location.href = "#!/rbi_bond_toc_slab_maintenance/action/inquiry";
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
                       text: "Do you Really want to Cancel",
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
      }  }
      );
    }

    // Handle error response
    function handleErrorResponse(response) {
      vm.loader = false;
      var msg = response.data.message ? response.data.message : "Server Error";
      Swal.fire("Error!", msg, "error");
    }
    /* Table Sorting */
    vm.column = "value";
    // sort ordering (Ascending or Descending). Set true for desending
    vm.reverse = false;

    // called on header click
    vm.sortColumn = function (col) {
      vm.column = col;
      if (vm.reverse) {
        vm.reverse = false;
        vm.reverseclass = "arrow-up";
      } else {
        vm.reverse = true;
        vm.reverseclass = "arrow-down";
      }
    };

    // remove and change class
    vm.sortClass = function (col) {
      if (vm.column == col) {
        if (vm.reverse) {
          return "arrow-down";
        } else {
          return "arrow-up";
        }
      } else {
        return "";
      }
    };

    //Get Level Name
    //        function fetchModuleDetails() {
    //            vm.loader = true;
    //
    //            var requestData = {
    //                'referenceId': 'PENSION'
    //            }
    //
    //            commonService.getModuleDetails(requestData).then(
    //                function(response) {
    //                    vm.loader = false;
    //                    vm.moduleList = response.data.result;
    //                }
    //            );
    //        }
    //Get Inquiry Version No Tourch Details
    function getInquiryVersionTourch() {
      vm.loader = true;
      vm.searchSolId = "";
      var requestData = {
        schemeCode: vm.schemeCode,
        versionNo: "",
        effeFromDate: "",
      };
      rbibondTocSlabMaintenanceService.getInquirySelectDetails(requestData).then(
        function (response) {
          vm.loader = false;
          vm.versionNoList = response.data.result.effeFromDateList;
          //                       vm.tocSlabListEffctDate=response.data.result.effeFromDateList;
          angular.forEach(vm.versionNoList, function (item) {
            if (item.verifiedStatus == "Y") {
              item.verifiedStatus = item.verifiedStatus = "Yes";
            } else {
              item.verifiedStatus = item.verifiedStatus = "No";
            }
          });
          angular.element("#versionNoModal").modal("toggle");
        },
        function (error) {
          handleErrorResponse(error);
          vm.loader = false;
        }
      );
    }

    function getVerifyVersionTourch() {
      vm.loader = true;
      var requestData = {
        schemeCode: vm.schemeCode,
        versionNo: null,
        effeFromDate: null,
      };
      rbibondTocSlabMaintenanceService.VerifyDeleteTocSlab(requestData).then(
        (response) => {
          vm.loader = false;

          vm.versionNoList = response.data.result.versionOut;

          angular.element("#versionNoModal").modal("toggle");
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
        schemeCode: vm.schemeCode,
        versionNo: vm.versionNo,
        effeFromDate: vm.effFromDate,
      };
      rbibondTocSlabMaintenanceService.getInquirySelectDetails(requestData).then(
        function (response) {
          vm.loader = false;
          vm.auditInfo = response.data.result;
          vm.rejectionRemarks = response.data.result.rejectionRemarks;
          vm.tocSlabList = response.data.result.oldNewValueList;
          vm.verionNoDetails = response.data.result.oldNewValueList[0];
        },
        function (error) {
          vm.loader = false;
          handleErrorResponse(error);
          $state.go("rbi_bond_toc_slab_maintenance.inquiry");
        }
      );
    }
    // format date from dd/MM/yyyy to yyyy-MM-dd
    function formatDate(tempDate) {
      return $filter("date")(tempDate, "dd-MMM-yyyy");
    }

    function getschemeName(flag) {
      if (flag == "Verify") {
        vm.loader = true;
        var requestData = {
          //                                              'schemeCode': "",
          //                                              'versionNo': "",
          //                                              'effeFromDate': ""
        };
        rbibondTocSlabMaintenanceService.VerifyDeleteTocSlab(requestData).then(
          function (response) {
            vm.loader = false;
            vm.schemeNameList = response.data.result.schemeTorchList;
            angular.element("#schemeNameSearcherPopUp").modal("toggle");
          },
          function (error) {
            handleErrorResponse(error);
            vm.loader = false;
          }
        );
      } else if (flag == "Inquiry") {
        vm.loader = true;
        vm.searchSolId = "";
        var requestData = {
          schemeCode: "",
          versionNo: "",
          effeFromDate: "",
        };
        rbibondTocSlabMaintenanceService.getInquirySelectDetails(requestData).then(
          function (response) {
            vm.loader = false;
            vm.schemeNameList = response.data.result.schemeTorchList;
            angular.element("#schemeNameSearcherPopUp").modal("toggle");
          },
          function (error) {
            handleErrorResponse(error);
            vm.loader = false;
          }
        );
      } else if (flag == "Add") {
        vm.loader = true;
        let requestData = {
          schemeCode: "",
          versionNo: "",
          effeFromDate: "",

          cgst: "",
          sgst: "",
          slabSubDetailsDTOList: null,
          //$filter('date')(vm.effFromDate, "dd-MMM-yyyy")
        };
        rbibondTocSlabMaintenanceService.getAddDetails(requestData).then(
          function (response) {
            vm.loader = false;
            vm.schemeNameList = response.data.result.schemeTorchList;
            angular.element("#schemeNameSearcherPopUp").modal("toggle");
          },
          function (error) {
            handleErrorResponse(error);
            vm.loader = false;
          }
        );
      } else if (flag == "Modify") {
        vm.loader = true;
        var requestData = {
          schemeCode: "",
          effeFromDate: "",
        };

        rbibondTocSlabMaintenanceService.modifySelect(requestData).then(
          function (response) {
            vm.loader = false;
            vm.schemeNameList = response.data.result.schemeTorchList;
            angular.element("#schemeNameSearcherPopUp").modal("toggle");
          },
          function (error) {
            handleErrorResponse(error);
            vm.loader = false;
          }
        );
      }
    }

    vm.dateValidationForModify = function () {
      vm.dateOptions = {
        minDate:
          vm.effFromDate[3] +
          vm.effFromDate[4] +
          vm.effFromDate[2] +
          vm.effFromDate[0] +
          vm.effFromDate[1] +
          vm.effFromDate[5] +
          vm.effFromDate[6] +
          vm.effFromDate[7] +
          vm.effFromDate[8] +
          vm.effFromDate[9],
      };
    };
  }
})();
