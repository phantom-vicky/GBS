(function () {
  "use strict";

  angular.module("gbmUltraApp").controller("BLASubsNominationController", BLASubsNominationController);

  BLASubsNominationController.$inject = ["$scope", "$filter", "$state", "$stateParams", "$location", "$rootScope", "BLASubsNominationService", "commonService"];

  function BLASubsNominationController($scope, $filter, $state, $stateParams, $location, $rootScope, BLASubsNominationService, commonService) {
    var vm = this;
    vm.getDetails = getDetails;

    // Pagination
    vm.filteredRecords = [];
    vm.currentPage = 1;
    vm.maxPagesToShow = 5;
    vm.searchText = "";
    vm.loading = true;
    vm.showExportOptions = false;
    vm.itemsPerPage = "5";
    vm.showNext = false;
    vm.showPrevious = false;
    $scope.sortReverse = false;
    $scope.sort = function (key) {
      $scope.sortKey = key;
      $scope.sortReverse = $scope.sortKey == key ? !$scope.sortReverse : $scope.sortReverse;
      $scope.sortReverse ? (vm.sortOrder = "DESC") : (vm.sortOrder = "ASC");
      vm.sortField = key;
      vm.getDetails();
    };

    vm.pageNo = 1;
    vm.pageSize = 5;

    vm.goPreviousPage = function () {
      vm.pageNo = parseInt(vm.pageNo) - 1;
      vm.getDetails();
    };

    vm.goNextPage = function () {
      vm.pageNo = parseInt(vm.pageNo) + 1;
      vm.getDetails();
    };

    vm.paginationControls = function () {
      vm.pages = [];
      for (var i = 1; i <= parseInt(vm.maxPages); i++) {
        vm.pages.push(parseInt(i));
      }
      if (vm.pageNo != 1) {
        vm.showPrevious = true;
      } else {
        vm.showPrevious = false;
      }
      if (vm.pageNo != vm.maxPages) {
        vm.showNext = true;
      } else {
        vm.showNext = false;
      }
    };

    vm.getState = function () {
      vm.listType = "STATES OF INDIA";

      var requestData = {
        referenceId: vm.listType,
      };

      BLASubsNominationService.getReferenceData(requestData).then(
        function (response) {
          vm.stateListValues = response.data.result;
          vm.stateList = [];
          angular.forEach(vm.stateListValues, function (item) {
            vm.stateList.push({
              subCodeDescription: item.DESCRIPTION,
              subCode: item.SUBCODE,
            });
          });
        },
        function (error) {
          handleErrorResponse(error);
        }
      );
    };

    vm.textableDatePicker = textableDatePicker;
    vm.autoslashing = autoslashing;

    function textableDatePicker(id, valueDate) {
      commonService.textableDatePicker(id, valueDate);
    }
    function autoslashing(id) {
      commonService.autoslashing(id);
    }

    vm.getModeOfHolding = function () {
      vm.listType = "MODE_OF_HOLDING";

      var requestData = {
        referenceId: vm.listType,
      };

      BLASubsNominationService.getReferenceData(requestData).then(
        function (response) {
          vm.modeOfHoldingListValues = response.data.result;
          vm.modeOfHoldingList = [];
          angular.forEach(vm.modeOfHoldingListValues, function (item) {
            vm.modeOfHoldingList.push({
              subCodeDescription: item.DESCRIPTION,
              subCode: item.SUBCODE,
            });
          });
        },
        function (error) {
          handleErrorResponse(error);
        }
      );
    };

    vm.getRelation = function () {
      vm.listType = "RELATION_WITH_MINOR";

      var requestData = {
        referenceId: vm.listType,
      };

      BLASubsNominationService.getReferenceData(requestData).then(
        function (response) {
          vm.relationListValues = response.data.result;
          vm.relationList = [];
          angular.forEach(vm.relationListValues, function (item) {
            vm.relationList.push({
              subCodeDescription: item.DESCRIPTION,
              subCode: item.SUBCODE,
            });
          });
        },
        function (error) {
          handleErrorResponse(error);
        }
      );
    };

    vm.getHolderType = function () {
      vm.listType = "HOLDER_TYPE";

      var requestData = {
        referenceId: vm.listType,
      };

      BLASubsNominationService.getReferenceData(requestData).then(
        function (response) {
          vm.holderTypeListValues = response.data.result;
          vm.holderTypeList = [];
          angular.forEach(vm.holderTypeListValues, function (item) {
            vm.holderTypeList.push({
              subCodeDescription: item.DESCRIPTION,
              subCode: item.SUBCODE,
            });
          });
        },
        function (error) {
          handleErrorResponse(error);
        }
      );
    };

    vm.getModeOfPayment = function () {
      vm.listType = "MODE_OF_PAYMENT";

      var requestData = {
        referenceId: vm.listType,
      };

      BLASubsNominationService.getReferenceData(requestData).then(
        function (response) {
          vm.modeOfInterestValues = response.data.result;
          vm.modeOfInterestList = [];
          angular.forEach(vm.modeOfInterestListValues, function (item) {
            vm.modeOfInterestList.push({
              subCodeDescription: item.DESCRIPTION,
              subCode: item.SUBCODE,
            });
          });
        },
        function (error) {
          handleErrorResponse(error);
        }
      );
    };

    vm.convertLabelValue = function (list) {
      for (var item in list) {
        list[item].value = list[item].SUBCODE;
        list[item].label = list[item].DESCRIPTION;
      }
      return list;
    };

    vm.getSchemeName = function () {
      vm.loader = true;
      var requestData = {
        flag: "S",
      };

      BLASubsNominationService.getDetails(requestData).then(
        function (response) {
          vm.loader = false;
          vm.schemeList = response.data.result.list;
          angular.element("#schemeModel").modal("toggle");
        },
        function (error) {
          handleErrorResponse(error);
          vm.loader = false;
        }
      );
    };

    vm.getPanNumber = function () {
      var requestData = {
        schemeCode: vm.schemeCode,
        holderName: vm.holderName,
        panNumber: vm.panNumber,
        flag: "P",
      };
      BLASubsNominationService.getDetails(requestData).then(
        function (response) {
          vm.loader = false;
          vm.panNumberList = response.data.result.list;
          angular.element("#panModel").modal("toggle");
        },
        function (error) {
          vm.loader = false;
          handleErrorResponse(error);
        }
      );
    };

    vm.getBLANumber = function () {
      var requestData = {
        schemeCode: vm.schemeCode,
        holderName: vm.holderName,
        panNumber: vm.panNumber,
        blaNumber: vm.blaNumber,
        flag: "B",
      };
      BLASubsNominationService.getDetails(requestData).then(
        function (response) {
          vm.loader = false;
          vm.blaNumberList = response.data.result.list;
          angular.element("#blaModel").modal("toggle");
        },
        function (error) {
          vm.loader = false;
          handleErrorResponse(error);
        }
      );
    };

    vm.getSubscriptionID = function () {
      vm.loader = true;

      var requestData = {
        schemeCode: vm.schemeCode,
        holderName: vm.holderName,
        panNumber: vm.panNumber,
        blaNumber: vm.blaNumber,
        flag: "BR",
        subscriptionID: vm.subscriptionID,
      };
      BLASubsNominationService.getDetails(requestData).then(
        function (response) {
          vm.loader = false;
          vm.subscriptionIDList = response.data.result.list;
          angular.element("#subscriptionIDModal").modal("toggle");
        },
        function (error) {
          handleErrorResponse(error);
          vm.loader = false;
        }
      );
    };

    function getDetails() {
      vm.loader = true;
      var requestData = {
        schemeCode: vm.schemeCode,
        holderName: vm.HolderName,
        panNumber: vm.panNumber,
        blaNumber: vm.blaNumber,
        subscriptionID: vm.subscriptionID,
        fromDate: $filter("date")(vm.fromDate, "dd-MMM-yyyy"),
        toDate: $filter("date")(vm.toDate, "dd-MMM-yyyy"),
        flag: "D",
        pageNo: vm.pageNo,
        pageSize: vm.pageSize,
        searchValue: vm.searchText,
        sortOrder: vm.sortOrder,
        sortField: vm.sortField,
      };
      BLASubsNominationService.getDetails(requestData).then(
        function (response) {
          vm.loader = false;
          vm.model = response.data.result.list;
          vm.totalAmount = response.data.result.total;
          vm.filteredRecords = response.data.result.list;
          vm.total = response.data.total;
          vm.maxPages = Math.ceil(response.data.total / vm.pageSize);
          vm.paginationControls();
          if (vm.model.length === 0) {
            vm.noDataFoundInSearch = true;
          } else {
            vm.noDataFoundInSearch = false;
          }
        },
        function (error) {
          vm.loader = false;
          if ((error.data.message == "No Data Found" || error.data.message == "NDF") && vm.searchText) {


                    vm.pageNo ='0';
                    vm.maxPages ='0';
                    vm.total = '0';
                     vm.showNext = false;
            vm.noDataFoundInSearch = true;
          } else if (error.data.message == "No Data Found" || error.data.message == "NDF") {
            vm.noDataFound = error.data.message;
          } else {
            handleErrorResponse(error);
            $state.go("BLA_Subscription_Nomination_Detailed_Report.inquiry");
          }
        }
      );
    }

    if ($state.current.name == "BLA_Subscription_Nomination_Detailed_Report.inquiry_details") {
      vm.schemeCode = $stateParams.obj.schemeCode;
      vm.schemeName = $stateParams.obj.schemeName;
      vm.holderName = $stateParams.obj.holderName;
      vm.panNumber = $stateParams.obj.panNumber;
      vm.blaNumber = $stateParams.obj.blaNumber;
      vm.subscriptionID = $stateParams.obj.subscriptionID;
      vm.fromDate = $stateParams.obj.fromDate;
      vm.toDate = $stateParams.obj.toDate;
      getDetails();
    }

    vm.formatDate = function (tempDate) {
      return $filter("date")(tempDate, "dd/MM/yyyy");
    };

    vm.fromDateVal = function () {
      vm.fromDateOptions = {
        maxDate: new Date(),
        showWeeks: false,
      };
    };
    vm.toDateVal = function (fromDate) {
      vm.toDateOptions = {
        minDate: fromDate,
        maxDate: new Date(),
        showWeeks: false,
      };
    };

    vm.getLabelFromValuesArray = function (value, list) {
      var filter = $filter("getLabelFromValuesArray");
      return filter(value, vm.convertLabelValue(list));
    };

    //    Download report
    vm.reports = {
      BLASubscriptionNomination: {
        filename: "BLA/Subscription/Nomination Detailed Report",
        mainHeaders: ["BLA/Subscription/Nomination Detailed Report", "Bank Name: INSPIRISY BANK LTD", "Sol ID: 101 / BRANCH OF THE BANK"],
        subHeaders: [
          "Name of the Bond Scheme: " + vm.schemeName,
          vm.holderName ? "Name: " + vm.holderName : null,
          vm.panNumber ? "PAN No: " + vm.panNumber : null,
          vm.blaNumber ? "BLA No: " + vm.blaNumber : null,
          vm.subscriptionID ? "Subscription ID: " + vm.subscriptionID : null,
          vm.fromDate ? "From Date: " + vm.formatDate(vm.fromDate) : null,
          vm.toDate ? "To Date: " + vm.formatDate(vm.toDate) : null,
        ].filter(Boolean),
        tableHeaders: [
          { title: "S. No.", width: 10, type: "numeric" },
          { title: "Name of the Bond Scheme", width: 30, type: "string" },
          { title: "Mode of Holding", width: 20, type: "string" },
          { title: "FIRST JOINT BOND HOLDER DETAILS", width: 30, type: "string" },
          { title: "", width: 30, type: "string" },
          { title: "", width: 30, type: "string" },
          { title: "", width: 30, type: "string" },
          { title: "", width: 30, type: "string" },
          { title: "", width: 30, type: "string" },
          { title: "", width: 30, type: "string" },
          { title: "", width: 30, type: "string" },
          { title: "", width: 30, type: "string" },
          { title: "", width: 30, type: "string" },
          { title: "", width: 30, type: "string" },
          { title: "", width: 30, type: "string" },
          { title: "", width: 30, type: "string" },
          { title: "", width: 30, type: "string" },
          { title: "", width: 30, type: "string" },
          { title: "", width: 30, type: "string" },
          { title: "", width: 30, type: "string" },
          { title: "", width: 30, type: "string" },
          { title: "", width: 30, type: "string" },
          { title: "", width: 30, type: "string" },
          { title: "", width: 30, type: "string" },
          { title: "", width: 30, type: "string" },
          { title: "", width: 30, type: "string" },
          { title: "Coparceners Details of Karta", width: 30, type: "string" },
          { title: "", width: 30, type: "string" },
          { title: "", width: 30, type: "string" },
          { title: "Guardian Details", width: 30, type: "string" },
          { title: "", width: 30, type: "string" },
          { title: "", width: 30, type: "string" },
          { title: "", width: 30, type: "string" },
          { title: "", width: 30, type: "string" },
          { title: "", width: 30, type: "string" },
          { title: "", width: 30, type: "string" },
          { title: "", width: 30, type: "string" },
          { title: "", width: 30, type: "string" },
          { title: "", width: 30, type: "string" },
          { title: "", width: 30, type: "string" },
          { title: "", width: 30, type: "string" },
          { title: "", width: 30, type: "string" },
          { title: "Other Details", width: 30, type: "string" },
          { title: "", width: 30, type: "string" },
          { title: "", width: 30, type: "string" },
          { title: "", width: 30, type: "string" },
          { title: "", width: 30, type: "string" },
          { title: "", width: 30, type: "string" },
          { title: "", width: 30, type: "string" },
          { title: "", width: 30, type: "string" },
          { title: "", width: 30, type: "string" },
          { title: "", width: 30, type: "string" },
          { title: "", width: 30, type: "string" },
          { title: "", width: 30, type: "string" },
          { title: "Witness Details", width: 30, type: "string" },
          { title: "", width: 30, type: "string" },
          { title: "", width: 30, type: "string" },
          { title: "", width: 30, type: "string" },
          { title: "Supporting Documents", width: 30, type: "string" },
          { title: "", width: 30, type: "string" },
          { title: "", width: 30, type: "string" },
          { title: "", width: 30, type: "string" },
          { title: "", width: 30, type: "string" },
          { title: "", width: 30, type: "string" },
          { title: "", width: 30, type: "string" },
          { title: "", width: 30, type: "string" },
          { title: "", width: 30, type: "string" },
          { title: "", width: 30, type: "string" },
          { title: "SECOND JOINT BOND HOLDER DETAILS", width: 30, type: "string" },
          { title: "", width: 30, type: "string" },
          { title: "", width: 30, type: "string" },
          { title: "", width: 30, type: "string" },
          { title: "", width: 30, type: "string" },
          { title: "", width: 30, type: "string" },
          { title: "", width: 30, type: "string" },
          { title: "", width: 30, type: "string" },
          { title: "", width: 30, type: "string" },
          { title: "", width: 30, type: "string" },
          { title: "", width: 30, type: "string" },
          { title: "", width: 30, type: "string" },
          { title: "", width: 30, type: "string" },
          { title: "", width: 30, type: "string" },
          { title: "", width: 30, type: "string" },
          { title: "", width: 30, type: "string" },
          { title: "", width: 30, type: "string" },
          { title: "", width: 30, type: "string" },
          { title: "", width: 30, type: "string" },
          { title: "", width: 30, type: "string" },
          { title: "", width: 30, type: "string" },
          { title: "", width: 30, type: "string" },
          { title: "Guardian Details", width: 30, type: "string" },
          { title: "", width: 30, type: "string" },
          { title: "", width: 30, type: "string" },
          { title: "", width: 30, type: "string" },
          { title: "", width: 30, type: "string" },
          { title: "", width: 30, type: "string" },
          { title: "", width: 30, type: "string" },
          { title: "", width: 30, type: "string" },
          { title: "", width: 30, type: "string" },
          { title: "", width: 30, type: "string" },
          { title: "", width: 30, type: "string" },
          { title: "", width: 30, type: "string" },
          { title: "", width: 30, type: "string" },
          { title: "Other Details", width: 30, type: "string" },
          { title: "", width: 30, type: "string" },
          { title: "", width: 30, type: "string" },
          { title: "", width: 30, type: "string" },
          { title: "", width: 30, type: "string" },
          { title: "Witness Details", width: 30, type: "string" },
          { title: "", width: 30, type: "string" },
          { title: "", width: 30, type: "string" },
          { title: "", width: 30, type: "string" },
          { title: "Supporting Documents", width: 30, type: "string" },
          { title: "", width: 30, type: "string" },
          { title: "", width: 30, type: "string" },
          { title: "", width: 30, type: "string" },
          { title: "", width: 30, type: "string" },
          { title: "", width: 30, type: "string" },
          { title: "", width: 30, type: "string" },
          { title: "", width: 30, type: "string" },
          { title: "", width: 30, type: "string" },
          { title: "", width: 30, type: "string" },
          { title: "THIRD JOINT BOND HOLDER DETAILS", width: 30, type: "string" },
          { title: "", width: 30, type: "string" },
          { title: "", width: 30, type: "string" },
          { title: "", width: 30, type: "string" },
          { title: "", width: 30, type: "string" },
          { title: "", width: 30, type: "string" },
          { title: "", width: 30, type: "string" },
          { title: "", width: 30, type: "string" },
          { title: "", width: 30, type: "string" },
          { title: "", width: 30, type: "string" },
          { title: "", width: 30, type: "string" },
          { title: "", width: 30, type: "string" },
          { title: "", width: 30, type: "string" },
          { title: "", width: 30, type: "string" },
          { title: "", width: 30, type: "string" },
          { title: "", width: 30, type: "string" },
          { title: "", width: 30, type: "string" },
          { title: "", width: 30, type: "string" },
          { title: "", width: 30, type: "string" },
          { title: "", width: 30, type: "string" },
          { title: "", width: 30, type: "string" },
          { title: "", width: 30, type: "string" },
          { title: "Guardian Details", width: 30, type: "string" },
          { title: "", width: 30, type: "string" },
          { title: "", width: 30, type: "string" },
          { title: "", width: 30, type: "string" },
          { title: "", width: 30, type: "string" },
          { title: "", width: 30, type: "string" },
          { title: "", width: 30, type: "string" },
          { title: "", width: 30, type: "string" },
          { title: "", width: 30, type: "string" },
          { title: "", width: 30, type: "string" },
          { title: "", width: 30, type: "string" },
          { title: "", width: 30, type: "string" },
          { title: "", width: 30, type: "string" },
          { title: "Other Details", width: 30, type: "string" },
          { title: "", width: 30, type: "string" },
          { title: "", width: 30, type: "string" },
          { title: "", width: 30, type: "string" },
          { title: "", width: 30, type: "string" },
          { title: "Witness Details", width: 30, type: "string" },
          { title: "", width: 30, type: "string" },
          { title: "", width: 30, type: "string" },
          { title: "", width: 30, type: "string" },
          { title: "Supporting Documents", width: 30, type: "string" },
          { title: "", width: 30, type: "string" },
          { title: "", width: 30, type: "string" },
          { title: "", width: 30, type: "string" },
          { title: "", width: 30, type: "string" },
          { title: "", width: 30, type: "string" },
          { title: "", width: 30, type: "string" },
          { title: "", width: 30, type: "string" },
          { title: "", width: 30, type: "string" },
          { title: "", width: 30, type: "string" },
        ],
        tableSubHeaders: [
          { title: "", width: 10, type: "numeric" },
          { title: "", width: 30, type: "string" },
          { title: "", width: 20, type: "string" },
          { title: "FTS Application Number", width: 30, type: "string" },
          { title: "Bond Ledger Account Number", width: 30, type: "string" },
          { title: "Bond Holder First name", width: 40, type: "string" },
          { title: "Bond Holder Middle Name", width: 40, type: "string" },
          { title: "Bond Holder Last name", width: 40, type: "string" },
          { title: "Holder Type", width: 20, type: "string" },
          { title: "Father Name/Spouse Name", width: 40, type: "string" },
          { title: "Mother Name", width: 40, type: "string" },
          { title: "Gender", width: 10, type: "string" },
          { title: "Bond Holder Date of Birth (DD/MM/YYYY)", width: 40, type: "string" },
          { title: "Is Minor (Y/N)?", width: 10, type: "string" },
          { title: "Details of Birth certificate submitted as per norms", width: 40, type: "string" },
          { title: "Communication Address 1", width: 40, type: "string" },
          { title: "Address 2", width: 35, type: "string" },
          { title: "Address 3", width: 35, type: "string" },
          { title: "City", width: 30, type: "string" },
          { title: "State", width: 30, type: "string" },
          { title: "Pin Code", width: 15, type: "string" },
          { title: "Phone Number", width: 15, type: "string" },
          { title: "Mobile Number", width: 15, type: "string" },
          { title: "Email id", width: 30, type: "string" },
          { title: "HUF registration / Power of Attorney number", width: 40, type: "string" },
          { title: "Power of Attorney/Karta Name", width: 40, type: "string" },
          { title: "Coparceners Name", width: 40, type: "string" },
          { title: "Relationship with Karta", width: 40, type: "string" },
          { title: "DOB", width: 40, type: "string" },
          { title: "Guardian ID", width: 30, type: "string" },
          { title: "Guardian First Name", width: 30, type: "string" },
          { title: "Guardian Middle Name", width: 30, type: "string" },
          { title: "Guardian Last Name", width: 30, type: "string" },
          { title: "Relation with Minor", width: 30, type: "string" },
          { title: "Date of Birth", width: 30, type: "string" },
          { title: "Communication Address", width: 30, type: "string" },
          { title: "Mobile No.", width: 30, type: "string" },
          { title: "Email ID", width: 30, type: "string" },
          { title: "Date of Death of Guardian  (DD/MM/YYYY)", width: 30, type: "string" },
          { title: "Date of Receipt of Death Certificate (DD/MM/YYYY)", width: 30, type: "string" },
          { title: "If Request from Current Guardian (Y/N)", width: 30, type: "string" },
          { title: "Remarks", width: 30, type: "string" },
          { title: "Whether the customer belongs to HDFC?", width: 30, type: "string" },
          { title: "Operative Account Number", width: 30, type: "string" },
          { title: "Mode of Interest Payment/ Principal Repayment", width: 30, type: "string" },
          { title: "Account Type code", width: 30, type: "string" },
          { title: "DD/PO Account Number", width: 30, type: "string" },
          { title: "Bank Code", width: 30, type: "string" },
          { title: "Branch Code", width: 30, type: "string" },
          { title: "IFSC Code of the Credit Bank", width: 30, type: "string" },
          { title: "Name of the Payee", width: 30, type: "string" },
          { title: "Other Bank Name", width: 30, type: "string" },
          { title: "Other Branch Details", width: 30, type: "string" },
          { title: "IFC Code", width: 30, type: "string" },
          { title: "MICR Code", width: 30, type: "string" },
          { title: "PAN No", width: 30, type: "string" },
          { title: "Aadhaar", width: 30, type: "string" },
          { title: "WitnessName1", width: 30, type: "string" },
          { title: "Address", width: 30, type: "string" },
          { title: "WitnessName2", width: 30, type: "string" },
          { title: "Address", width: 30, type: "string" },
          { title: "Birth Certificate", width: 30, type: "string" },
          { title: "Passport", width: 30, type: "string" },
          { title: "Senior Citizen Card", width: 30, type: "string" },
          { title: "Voter ID Card", width: 30, type: "string" },
          { title: "PAN Card", width: 30, type: "string" },
          { title: "Driving Licence", width: 30, type: "string" },
          { title: "Ration Card", width: 30, type: "string" },
          { title: "Others", width: 30, type: "string" },
          { title: "Detail for Others", width: 30, type: "string" },
          { title: "Remarks", width: 30, type: "string" },
          { title: "Bond Ledger Account Number", width: 30, type: "string" },
          { title: "Bond Holder First name", width: 30, type: "string" },
          { title: "Bond Holder Middle Name", width: 30, type: "string" },
          { title: "Bond Holder Last name", width: 30, type: "string" },
          { title: "Holder Type", width: 30, type: "string" },
          { title: "Father Name/Spouse Name", width: 30, type: "string" },
          { title: "Mother Name", width: 30, type: "string" },
          { title: "Gender", width: 30, type: "string" },
          { title: "Bond Holder Date of Birth (DD/MM/YYYY)", width: 30, type: "string" },
          { title: "Is Minor (Y/N)?", width: 30, type: "string" },
          { title: "Details of Birth certificate submitted as per norms", width: 30, type: "string" },
          { title: "Communication Address 1", width: 30, type: "string" },
          { title: "Address 2", width: 30, type: "string" },
          { title: "Address 3", width: 30, type: "string" },
          { title: "City", width: 30, type: "string" },
          { title: "State", width: 30, type: "string" },
          { title: "Pin Code", width: 30, type: "string" },
          { title: "Phone Number", width: 30, type: "string" },
          { title: "Mobile Number", width: 30, type: "string" },
          { title: "Email id", width: 30, type: "string" },
          { title: "HUF registration / Power of Attorney number", width: 30, type: "string" },
          { title: "Power of Attorney/Karta Name", width: 30, type: "string" },
          { title: "Guardian ID", width: 30, type: "string" },
          { title: "Guardian First Name", width: 30, type: "string" },
          { title: "Guardian Middle Name", width: 30, type: "string" },
          { title: "Guardian Last Name", width: 30, type: "string" },
          { title: "Relation with Minor", width: 30, type: "string" },
          { title: "Date of Birth", width: 30, type: "string" },
          { title: "Communication Address", width: 30, type: "string" },
          { title: "Mobile No.", width: 30, type: "string" },
          { title: "Email ID", width: 30, type: "string" },
          { title: "Date of Death of Guardian (DD/MM/YYYY)", width: 30, type: "string" },
          { title: "Date of Receipt of Death Certificate (DD/MM/YYYY)", width: 30, type: "string" },
          { title: "If Request from Current Guardian (Y/N)", width: 30, type: "string" },
          { title: "Remarks", width: 30, type: "string" },
          { title: "Whether the customer belongs to HDFC?", width: 30, type: "string" },
          { title: "Operative Account Number", width: 30, type: "string" },
          { title: "IFSC Code", width: 30, type: "string" },
          { title: "PAN No", width: 30, type: "string" },
          { title: "Aadhaar", width: 30, type: "string" },
          { title: "WitnessName1", width: 30, type: "string" },
          { title: "Address", width: 30, type: "string" },
          { title: "WitnessName2", width: 30, type: "string" },
          { title: "Address", width: 30, type: "string" },
          { title: "Birth Certificate", width: 30, type: "string" },
          { title: "Passport", width: 30, type: "string" },
          { title: "Senior Citizen Card", width: 30, type: "string" },
          { title: "Voter ID Card", width: 30, type: "string" },
          { title: "PAN Card", width: 30, type: "string" },
          { title: "Driving Licence", width: 30, type: "string" },
          { title: "Ration Card", width: 30, type: "string" },
          { title: "Others", width: 30, type: "string" },
          { title: "Detail for Others", width: 30, type: "string" },
          { title: "Remarks", width: 30, type: "string" },
          { title: "Bond Ledger Account Number", width: 30, type: "string" },
          { title: "Bond Holder First name", width: 30, type: "string" },
          { title: "Bond Holder Middle Name", width: 30, type: "string" },
          { title: "Bond Holder Last name", width: 30, type: "string" },
          { title: "Holder Type", width: 30, type: "string" },
          { title: "Father Name/Spouse Name", width: 30, type: "string" },
          { title: "Mother Name", width: 30, type: "string" },
          { title: "Gender", width: 30, type: "string" },
          { title: "Bond Holder Date of Birth (DD/MM/YYYY)", width: 30, type: "string" },
          { title: "Is Minor (Y/N)?", width: 30, type: "string" },
          { title: "Details of Birth certificate submitted as per norms", width: 30, type: "string" },
          { title: "Communication Address 1", width: 30, type: "string" },
          { title: "Address 2", width: 30, type: "string" },
          { title: "Address 3", width: 30, type: "string" },
          { title: "City", width: 30, type: "string" },
          { title: "State", width: 30, type: "string" },
          { title: "Pin Code", width: 30, type: "string" },
          { title: "Phone Number", width: 30, type: "string" },
          { title: "Mobile Number", width: 30, type: "string" },
          { title: "Email id", width: 30, type: "string" },
          { title: "HUF registration / Power of Attorney number", width: 30, type: "string" },
          { title: "Power of Attorney/Karta Name", width: 30, type: "string" },
          { title: "Guardian ID", width: 30, type: "string" },
          { title: "Guardian First Name", width: 30, type: "string" },
          { title: "Guardian Middle Name", width: 30, type: "string" },
          { title: "Guardian Last Name", width: 30, type: "string" },
          { title: "Relation with Minor", width: 30, type: "string" },
          { title: "Date of Birth", width: 30, type: "string" },
          { title: "Communication Address", width: 30, type: "string" },
          { title: "Mobile No.", width: 30, type: "string" },
          { title: "Email ID", width: 30, type: "string" },
          { title: "Date of Death of Guardian (DD/MM/YYYY)", width: 30, type: "string" },
          { title: "Date of Receipt of Death Certificate (DD/MM/YYYY)", width: 30, type: "string" },
          { title: "If Request from Current Guardian (Y/N)", width: 30, type: "string" },
          { title: "Remarks", width: 30, type: "string" },
          { title: "Whether the customer belongs to HDFC?", width: 30, type: "string" },
          { title: "Operative Account Number", width: 30, type: "string" },
          { title: "IFSC Code", width: 30, type: "string" },
          { title: "PAN No", width: 30, type: "string" },
          { title: "Aadhaar", width: 30, type: "string" },
          { title: "WitnessName1", width: 30, type: "string" },
          { title: "Address", width: 30, type: "string" },
          { title: "WitnessName2", width: 30, type: "string" },
          { title: "Address", width: 30, type: "string" },
          { title: "Birth Certificate", width: 30, type: "string" },
          { title: "Passport", width: 30, type: "string" },
          { title: "Senior Citizen Card", width: 30, type: "string" },
          { title: "Voter ID Card", width: 30, type: "string" },
          { title: "PAN Card", width: 30, type: "string" },
          { title: "Driving Licence", width: 30, type: "string" },
          { title: "Ration Card", width: 30, type: "string" },
          { title: "Others", width: 30, type: "string" },
          { title: "Detail for Others", width: 30, type: "string" },
          { title: "Remarks", width: 30, type: "string" },
        ],
        get tableData() {
          return (vm.downloadFilteredRecords || []).map((record, index) => [
            (index + 1).toString(),
            record.schemeName || "",
            record.modeOfHolding ? vm.getLabelFromValuesArray(record.modeOfHolding, vm.modeOfHoldingListValues) : "",
            record.bondHolderFTSApplicationNumber || "",
            record.bondHolderBlaNumber || "",
            record.bondHolderFirstName || "",
            record.bondHolderMiddleName || "",
            record.bondHolderLastName || "",
            record.bondHolderHolderType ? vm.getLabelFromValuesArray(record.bondHolderHolderType, vm.holderTypeListValues) : "",
            record.bondHolderFatherName || "",
            record.bondHolderMotherName || "",
            record.bondHolderGender || "",
            record.bondHolderDateOfBirth || "",
            record.bondHolderIsMinor || "",
            record.bondHolderDetailsOfBirthCertificate || "",
            record.bondHolderCommunicationAddress1 || "",
            record.bondHolderAddress2 || "",
            record.bondHolderAddress3 || "",
            record.bondHolderCity || "",
            record.bondHolderState ? vm.getLabelFromValuesArray(record.bondHolderState, vm.stateListValues) : "",
            record.bondHolderPinCode || "",
            record.bondHolderPhoneNumber || "",
            record.bondHolderMobileNumber || "",
            record.bondHolderEmailID || "",
            record.bondHolderHUF || "",
            record.bondHolderPowerOfAttorney || "",

            record.bondHolderCoName || "",
            record.bondHolderCoRelationshipWithKarta || "",
            record.bondHolderCoDOB || "",

            record.bondHolderGID || "",
            record.bondHolderGFirstName || "",
            record.bondHolderGMiddleName || "",
            record.bondHolderGLastName || "",
            record.bondHolderGRelationWithMinor ? vm.getLabelFromValuesArray(record.bondHolderGRelationWithMinor, vm.relationListValues) : "",

            record.bondHolderGDateOfBirth || "",
            record.bondHolderGAddress || "",
            record.bondHolderGMobile || "",
            record.bondHolderGEmailID || "",
            record.bondHolderGDateOfDeath || "",
            record.bondHolderGDateOfReceipt || "",
            record.bondHolderGIfRequestFromCurrentGuardian || "",
            record.bondHolderGRemarks || "",

            record.bondHolderWhetherHDFCCustomer || "",
            record.bondHolderOperativeAccountNumber || "",
            record.bondHolderModeOfInterestPayment ? vm.getLabelFromValuesArray(record.bondHolderModeOfInterestPayment, vm.modeOfInterestValues) : "",
            record.bondHolderAccountTypeCode || "",
            record.bondHolderDDPOAccountNumber || "",
            record.bondHolderBankCode || "",
            record.bondHolderBranchCode || "",
            record.bondHolderIFSCCodeOfCreditBank || "",
            record.bondHolderNameOfPayee || "",
            record.bondHolderOtherBankName || "",
            record.bondHolderOtherBranchDetails || "",
            record.bondHolderIFSCCode || "",
            record.bondHolderMICR || "",
            record.bondHolderPANNo || "",
            record.bondHolderAadhaar || "",

            record.bondHolderWitnessName1 || "",
            record.bondHolderWitnessAddress1 || "",
            record.bondHolderWitnessName2 || "",
            record.bondHolderWitnessAddress2 || "",

            record.bondHolderBirthCertificate || "",
            record.bondHolderPassport || "",
            record.bondHolderSeniorCitizenCard || "",
            record.bondHolderVoterIDCard || "",
            record.bondHolderPANCard || "",
            record.bondHolderDrivingLicence || "",
            record.bondHolderRationCard || "",
            record.bondHolderOthers || "",
            record.bondHolderDetailForOthers || "",
            record.bondHolderRemarks || "",

            record.secondBlaNumber || "",
            record.secondFirstName || "",
            record.secondMiddleName || "",
            record.secondLastName || "",
            record.secondHolderType ? vm.getLabelFromValuesArray(record.secondHolderType, vm.holderTypeListValues) : "",

            record.secondFatherName || "",
            record.secondMotherName || "",
            record.secondGender || "",
            record.secondDateOfBirth || "",
            record.secondIsMinor || "",
            record.secondDetailsOfBirthCertificate || "",
            record.secondAddress1 || "",
            record.secondAddress2 || "",
            record.secondAddress3 || "",
            record.secondCity || "",
            record.secondState ? vm.getLabelFromValuesArray(record.secondState, vm.stateListValues) : "",

            record.secondPinCode || "",
            record.secondPhoneNumber || "",
            record.secondMobileNumber || "",
            record.secondEmailID || "",
            record.secondHUF || "",
            record.secondPowerOfAttorney || "",

            record.secondGID || "",
            record.secondGFirstName || "",
            record.secondGMiddleName || "",
            record.secondGLastName || "",
            record.secondGRelation ? vm.getLabelFromValuesArray(record.secondGRelation, vm.relationListValues) : "",

            record.secondGDateOfBirth || "",
            record.secondGAddress || "",
            record.secondGMobile || "",
            record.secondGEmailID || "",
            record.secondGDeathDateOfGuardian || "",
            record.secondGDateOfReceipt || "",
            record.secondGIfRequestFromGuardian || "",
            record.secondGRemarks || "",

            record.secondWhetherHDFCCustomer || "",
            record.secondOperativeAccountNumber || "",
            record.secondIFSCCode || "",
            record.secondPANNo || "",
            record.secondAadhaar || "",

            record.secondWitnessName1 || "",
            record.secondWitnessAddress1 || "",
            record.secondWitnessName2 || "",
            record.secondWitnessAddress2 || "",

            record.secondBirthCertificate || "",
            record.secondPassport || "",
            record.secondSeniorCitizenCard || "",
            record.secondVoterIDCard || "",
            record.secondPANCard || "",
            record.secondDrivingLicence || "",
            record.secondRationCard || "",
            record.secondOthers || "",
            record.secondDetailForOthers || "",
            record.secondRemarks || "",

            record.thirdBlaNumber || "",
            record.thirdFirstName || "",
            record.thirdMiddleName || "",
            record.thirdLastName || "",
            record.thirdHolderType ? vm.getLabelFromValuesArray(record.thirdHolderType, vm.holderTypeListValues) : "",

            record.thirdFatherName || "",
            record.thirdMotherName || "",
            record.thirdGender || "",
            record.thirdDateOfBirth || "",
            record.thirdIsMinor || "",
            record.thirdDetailsOfBirthCertificate || "",
            record.thirdAddress1 || "",
            record.thirdAddress2 || "",
            record.thirdAddress3 || "",
            record.thirdCity || "",
            record.thirdState ? vm.getLabelFromValuesArray(record.thirdState, vm.stateListValues) : "",
            record.thirdPinCode || "",
            record.thirdPhoneNumber || "",
            record.thirdMobileNumber || "",
            record.thirdEmailID || "",
            record.thirdHUF || "",
            record.thirdPowerOfAttorney || "",

            record.thirdGID || "",
            record.thirdGFirstName || "",
            record.thirdGMiddleName || "",
            record.thirdGLastName || "",
            record.thirdGRelationWithMinor ? vm.getLabelFromValuesArray(record.thirdGRelationWithMinor, vm.relationListValues) : "",
            record.thirdGDateOfBirth || "",
            record.thirdGAddress || "",
            record.thirdGMobile || "",
            record.thirdGEmailID || "",
            record.thirdGDeathDateOfGuardian || "",
            record.thirdGDateOfReceipt || "",
            record.thirdGIfRequestFromGuardian || "",
            record.thirdGRemarks || "",

            record.thirdWhetherHDFCCustomer || "",
            record.thirdOperativeAccountNumber || "",
            record.thirdIFSCCode || "",
            record.thirdPANNo || "",
            record.thirdAadhaar || "",

            record.thirdWitnessName1 || "",
            record.thirdWitnessAddress1 || "",
            record.thirdWitnessName2 || "",
            record.thirdWitnessAddress2 || "",

            record.thirdBirthCertificate || "",
            record.thirdPassport || "",
            record.thirdSeniorCitizenCard || "",
            record.thirdVoterIDCard || "",
            record.thirdPANCard || "",
            record.thirdDrivingLicence || "",
            record.thirdRationCard || "",
            record.thirdOthers || "",
            record.thirdDetailForOthers || "",
            record.thirdRemarks || "",
          ]);
        },
        tableHeaders2: [
          { title: "S. No.", width: 10, type: "numeric" },
          { title: "BOND HOLDER DETAILS", width: 30, type: "string" }, //scheme name
          { title: "", width: 30, type: "string" }, //mode of holding
          { title: "", width: 30, type: "string" }, //first bla
          { title: "", width: 30, type: "string" }, //first holder name
          { title: "", width: 50, type: "string" }, //second bla
          { title: "", width: 50, type: "string" }, //second holder name
          { title: "", width: 30, type: "string" }, //third bla
          { title: "", width: 30, type: "string" }, //third holdername
          { title: "", width: 30, type: "string" }, //subscription id
          { title: "", width: 30, type: "string" }, //application no
          { title: "BOND SUBSCRIPTION DETAILS", width: 30, type: "string" }, //broker id
          { title: "", width: 30, type: "string" }, //subs amt
          { title: "", width: 30, type: "string" }, //amt in words
          { title: "", width: 30, type: "string" }, //segment
          { title: "", width: 30, type: "string" }, //bond type
          { title: "", width: 30, type: "string" }, //account open date
          { title: "", width: 30, type: "string" }, //value date
          { title: "", width: 30, type: "string" }, //period of deposits
          { title: "", width: 30, type: "string" }, //maturity date
          { title: "", width: 30, type: "string" }, //rate of interest
          { title: "", width: 30, type: "string" }, //branch code
          { title: "", width: 30, type: "string" }, //branch name
          { title: "", width: 30, type: "string" }, //branch name
          { title: "TRANSACTION DETAILS", width: 30, type: "string" }, //trans mode
          { title: "", width: 30, type: "string" }, //debit account number
          { title: "", width: 30, type: "string" }, //branch id
          { title: "", width: 30, type: "string" }, //instrument type
          { title: "", width: 30, type: "string" }, //instrument number
          { title: "", width: 30, type: "string" }, //date
          { title: "", width: 30, type: "string" }, //amt
          { title: "", width: 30, type: "string" }, //tran code
          { title: "", width: 30, type: "string" }, //zone code
          { title: "", width: 30, type: "string" }, //zone date
          { title: "", width: 30, type: "string" }, //bank code
          { title: "", width: 30, type: "string" }, //branch code
          { title: "", width: 30, type: "string" }, //sort code
          { title: "", width: 30, type: "string" }, //remarks
          { title: "", width: 30, type: "string" }, //do you want to add nominee
          { title: "", width: 30, type: "string" }, //do you want to add nominee
          { title: "NOMINATION DETAILS", width: 30, type: "string" }, //trans mode
          { title: "", width: 30, type: "string" }, //debit account number
          { title: "", width: 30, type: "string" }, //branch id
          { title: "", width: 30, type: "string" }, //instrument type
          { title: "", width: 30, type: "string" }, //instrument number
          { title: "", width: 30, type: "string" }, //date
          { title: "", width: 30, type: "string" }, //amt
          { title: "", width: 30, type: "string" }, //tran code
          { title: "", width: 30, type: "string" }, //zone code
          { title: "", width: 30, type: "string" }, //zone date
          { title: "", width: 30, type: "string" }, //bank code
          { title: "", width: 30, type: "string" }, //branch code
          { title: "", width: 30, type: "string" }, //sort code
          { title: "", width: 30, type: "string" }, //remarks
          { title: "GUARDIAN DETAILS", width: 30, type: "string" }, //trans mode
          { title: "", width: 30, type: "string" }, //debit account number
          { title: "", width: 30, type: "string" }, //branch id
          { title: "", width: 30, type: "string" }, //instrument type
          { title: "", width: 30, type: "string" }, //instrument number
          { title: "", width: 30, type: "string" }, //date
          { title: "", width: 30, type: "string" }, //amt
          { title: "", width: 30, type: "string" }, //tran code
          { title: "", width: 30, type: "string" }, //zone code
          { title: "", width: 30, type: "string" }, //zone date
          { title: "", width: 30, type: "string" }, //bank code
          { title: "", width: 30, type: "string" }, //branch code
        ],
        tableSubHeaders2: [
          { title: "", width: 10, type: "numeric" },
          { title: "Name of the Bond Scheme", width: 30, type: "string" },
          { title: "Mode Of Holding", width: 30, type: "string" },
          { title: "First Joint Bond Holder BLA No.", width: 30, type: "string" },
          { title: "First Joint Bond Holder Name", width: 30, type: "string" },
          { title: "Second Joint Bond Holder BLA No.", width: 50, type: "string" },
          { title: "Second Joint Bond Holder Name", width: 50, type: "string" },
          { title: "Third Joint Bond Holder BLA No.", width: 30, type: "string" },
          { title: "Third Joint Bond Holder Name", width: 30, type: "string" },
          { title: "Subscription Id", width: 30, type: "string" },
          { title: "Application No", width: 30, type: "string" },
          { title: "Broker ID", width: 30, type: "string" },
          { title: "Subscription Amount", width: 30, type: "string" },
          { title: "Amount in Words", width: 30, type: "string" },
          { title: "Segment", width: 30, type: "string" },
          { title: "Bond Type", width: 30, type: "string" },
          { title: "Account Open Date (dd/mm/yyyy)", width: 30, type: "string" },
          { title: "Value Date (dd/mm/yyyy)", width: 30, type: "string" },
          { title: "Period Of Deposit (months)", width: 30, type: "string" },
          { title: "Maturity Date (dd/mm/yyyy)", width: 30, type: "string" },
          { title: "Rate of Interest (%)", width: 30, type: "string" },
          { title: "Maturity Amount", width: 30, type: "string" },
          { title: "Branch code- WBO", width: 30, type: "string" },
          { title: "Branch Name- WBO", width: 30, type: "string" },
          { title: "Transaction Mode", width: 30, type: "string" },
          { title: "Debit Account Number", width: 30, type: "string" },
          { title: "Branch ID", width: 30, type: "string" },
          { title: "Instrument Type", width: 30, type: "string" },
          { title: "Instrument Number", width: 30, type: "string" },
          { title: "Instrument Date (dd/mm/yyyy)", width: 30, type: "string" },
          { title: "Instrument Amount", width: 30, type: "string" },
          { title: "Tran Code", width: 30, type: "string" },
          { title: "Zone Code", width: 30, type: "string" },
          { title: "Zone Date (dd/mm/yyyy)", width: 30, type: "string" },
          { title: "Bank Code (Non-MICR)", width: 30, type: "string" },
          { title: "Branch Code (Non-MICR)", width: 30, type: "string" },
          { title: "Sort Code", width: 30, type: "string" },
          { title: "Remarks", width: 30, type: "string" },
          { title: "Do You want to add Nominee?", width: 30, type: "string" },
          { title: "Rejection Remarks", width: 30, type: "string" },
          { title: "Nominee First Name", width: 30, type: "string" },
          { title: "Nominee Middle Name", width: 30, type: "string" },
          { title: "Nominee Last Name", width: 30, type: "string" },
          { title: "Address1", width: 30, type: "string" },
          { title: "Address2", width: 30, type: "string" },
          { title: "Pin Code", width: 30, type: "string" },
          { title: "State", width: 30, type: "string" },
          { title: "City", width: 30, type: "string" },
          { title: "Phone No ", width: 30, type: "string" },
          { title: "Share Percentage", width: 30, type: "string" },
          { title: "Operative Account No", width: 30, type: "string" },
          { title: "Is Nominee a Minor", width: 30, type: "string" },
          { title: "Relation with Holder", width: 30, type: "string" },
          { title: "Date of Birth Of Minor", width: 30, type: "string" },
          { title: "Guardian First Name", width: 30, type: "string" },
          { title: "Guardian Middle Name", width: 30, type: "string" },
          { title: "Guardian Last Name ", width: 30, type: "string" },
          { title: "Gender", width: 30, type: "string" },
          { title: "Relation with Minor", width: 30, type: "string" },
          { title: "Address1", width: 30, type: "string" },
          { title: "Address2", width: 30, type: "string" },
          { title: "PIN Code", width: 30, type: "string" },
          { title: "State", width: 30, type: "string" },
          { title: "City", width: 30, type: "string" },
          { title: "Mobile No.", width: 30, type: "string" },
          { title: "Email ID", width: 30, type: "string" },
        ],
        get tableData2() {
          return (vm.downloadFilteredRecords || []).map((record, index) => [
            (index + 1).toString(),
            record.schemeName || "",
            record.modeOfHolding ? vm.getLabelFromValuesArray(record.modeOfHolding, vm.modeOfHoldingListValues) : "",
            record.firstJointBlaNumber || "",
            record.firstJointHolderName || "",
            record.secondJointBlaNumber || "",
            record.secondJointHolderName || "",
            record.thirdJointBlaNumber || "",
            record.thirdJointHolderName || "",
            record.subscriptionID || "",
            record.applicationNo || "",
            record.brokerID || "",
            record.subscriptionAmount || "",
            record.amountInWords || "",
            record.segment || "",
            record.bondType || "",
            record.accountOpenDate || "",
            record.valueDate || "",
            record.periodOfDeposit || "",
            record.maturityDate || "",
            record.rateOfInterest || "",
            record.maturityAmount || "",
            record.branchCode || "",
            record.branchName || "",
            record.transactionMode || "",
            record.debitAccountNumber || "",
            record.branchID || "",
            record.instrumentType || "",
            record.instrumentNumber || "",
            record.instrumentDate || "",
            record.instrumentAmount || "",
            record.tranCode || "",
            record.zoneCode || "",
            record.zoneDate || "",
            record.bankCode || "",
            record.branchCodeOfTransactionDetails || "",
            record.sortCode || "",
            record.remarks || "",
            record.doYouWantToAddNominee || "",
            record.rejectionRemarks || "",
            record.nomineeFirstName || "",
            record.nomineeMiddleName || "",
            record.nomineeLastName || "",
            record.nomineeAddress1 || "",
            record.nomineeAddress2 || "",
            record.nomineePinCode || "",
            record.nomineeState ? vm.getLabelFromValuesArray(record.nomineeState, vm.stateListValues) : "",
            record.nomineeCity || "",
            record.nomineePhoneNo || "",
            record.nomineeSharePercentage || "",
            record.nomineeOperativeAccountNumber || "",
            record.nomineeIsNomineeMinor || "",
            record.nomineeRelationWithHolder ? vm.getLabelFromValuesArray(record.nomineeRelationWithHolder, vm.relationListValues) : "",
            record.nomineeDateOfBirth || "",
            record.nomineeGFirstName || "",
            record.nomineeGMiddleName || "",
            record.nomineeGLastName || "",
            record.nomineeGender || "",
            record.nomineeGRelationWithMinor ? vm.getLabelFromValuesArray(record.nomineeGRelationWithMinor, vm.relationListValues) : "",
            record.nomineeGAddress1 || "",
            record.nomineeGAddress2 || "",
            record.nomineeGPINCode || "",
            record.nomineeGState ? vm.getLabelFromValuesArray(record.nomineeGState, vm.stateListValues) : "",
            record.nomineeGCity || "",
            record.nomineeGMobileNo || "",
            record.nomineeGEmailID || "",
          ]);
        },
      },
    };

    vm.downloadReport = function () {
      vm.loader = true;
      var requestData = {
        schemeCode: vm.schemeCode,
        holderName: vm.HolderName,
        panNumber: vm.panNumber,
        blaNumber: vm.blaNumber,
        subscriptionID: vm.subscriptionID,
        fromDate: $filter("date")(vm.fromDate, "dd-MMM-yyyy"),
        toDate: $filter("date")(vm.toDate, "dd-MMM-yyyy"),
        flag: "D",
        pageNo: "1",
        pageSize: vm.total,
        searchValue: vm.searchText,
      };
      BLASubsNominationService.getDetails(requestData).then(
        function (response) {
          vm.loader = false;
          vm.downloadFilteredRecords = response.data.result.list;
          const currentReport = vm.reports["BLASubscriptionNomination"];
          if (vm.downloadType === "excel") {
            vm.downloadExcel(currentReport);
          } else if (vm.downloadType === "csv") {
            vm.downloadCSV(currentReport);
          }
          vm.downloadType = "";
        },
        function (error) {
          vm.loader = false;
          if (error.data.message == "No Data Found" || error.data.message == "NDF") {
            vm.noDataFound = error.data.message;
          } else {
            handleErrorResponse(error);
            $state.go("BLA_Subscription_Nomination_Detailed_Report.inquiry");
          }
        }
      );
    };

    vm.downloadCSV = function (currentReport) {
      const { filename, mainHeaders, subHeaders, tableHeaders, tableSubHeaders, tableData, tableHeaders2, tableSubHeaders2, tableData2 } = currentReport;

      const columnWidths = tableHeaders.map((col, index) => {
        const headerLength = col.title.length;
        const subHeaderLength = tableSubHeaders[index] ? tableSubHeaders[index].title.length : 0;
        const maxDataLength = Math.max(headerLength, subHeaderLength, ...tableData.map((row) => (row[index] ? row[index].toString().length : 0)));

        return Math.max(col.width || 0, maxDataLength);
      });

      const columnWidths2 = tableHeaders2.map((col, index) => {
        const headerLength = col.title.length;
        const subHeaderLength = tableSubHeaders2[index] ? tableSubHeaders2[index].title.length : 0;
        const maxDataLength = Math.max(headerLength, subHeaderLength, ...tableData2.map((row) => (row[index] ? row[index].toString().length : 0)));

        return Math.max(col.width || 0, maxDataLength);
      });

      const formatRow = (values, columnWid) => {
        return values
          .map((value, i) => {
            const width = columnWid[i];
            const stringValue = value ? value.toString() : "";
            return stringValue.padEnd(width, " ");
          })
          .join(" | ");
      };

      const formattedHeader = formatRow(
        tableHeaders.map((col) => col.title),
        columnWidths
      );
      const formattedSubHeaders = formatRow(
        tableSubHeaders.map((col) => col.title),
        columnWidths
      );
      const formattedData = tableData.map((row) => formatRow(row, columnWidths)).join("\n");

      const formattedHeader2 = formatRow(
        tableHeaders2.map((col) => col.title),
        columnWidths2
      );
      const formattedSubHeaders2 = formatRow(
        tableSubHeaders2.map((col) => col.title),
        columnWidths2
      );
      const formattedData2 = tableData2.map((row) => formatRow(row, columnWidths2)).join("\n");

      const textData = [
        mainHeaders.join("\n"),
        subHeaders.join("\n"),
        "-".repeat(formattedHeader.length),
        formattedHeader,
        "-".repeat(formattedHeader.length),
        formattedSubHeaders,
        "-".repeat(formattedHeader.length),
        formattedData,
        "-".repeat(formattedHeader.length),
        formattedHeader2,
        "-".repeat(formattedHeader2.length),
        formattedSubHeaders2,
        "-".repeat(formattedHeader2.length),
        formattedData2,
        "-".repeat(formattedHeader2.length),
      ]
        .filter(Boolean)
        .join("\n");

      const textContent = "data:text/plain;charset=utf-8," + encodeURIComponent(textData);
      const link = document.createElement("a");
      link.setAttribute("href", textContent);
      link.setAttribute("download", filename + ".txt");
      document.body.appendChild(link);
      link.click();
      document.body.removeChild(link);
    };

    vm.downloadExcel = function (currentReport) {
      const { filename, mainHeaders, subHeaders, tableHeaders, tableSubHeaders, tableData, tableHeaders2, tableSubHeaders2, tableData2 } = currentReport;
      const excelData = [];

      mainHeaders.forEach((header) => {
        excelData.push([header, ...Array(tableHeaders.length - 1).fill("")]);
      });

      subHeaders.forEach((header) => {
        excelData.push([header, ...Array(tableHeaders.length - 1).fill("")]);
      });
      excelData.push([]);
      excelData.push(tableHeaders.map((header) => header.title));
      excelData.push(tableSubHeaders.map((header) => header.title));
      tableData.forEach((record) => {
        excelData.push(Object.values(record));
      });
      excelData.push(tableHeaders2.map((header) => header.title));
      excelData.push(tableSubHeaders2.map((header) => header.title));
      tableData2.forEach((record) => {
        excelData.push(Object.values(record));
      });
      const ws = XLSX.utils.aoa_to_sheet(excelData);
      const wb = XLSX.utils.book_new();
      XLSX.utils.book_append_sheet(wb, ws, "Data");
      XLSX.writeFile(wb, filename + ".xlsx");
    };
  }
})();
