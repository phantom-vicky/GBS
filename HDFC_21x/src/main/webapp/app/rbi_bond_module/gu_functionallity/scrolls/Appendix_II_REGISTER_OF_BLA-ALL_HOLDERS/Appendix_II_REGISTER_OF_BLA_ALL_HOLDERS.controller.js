(function () {
  "use strict";

  angular.module("gbmUltraApp").controller("AppendixIIRegisterOfBlaController", AppendixIIRegisterOfBlaController);

  AppendixIIRegisterOfBlaController.$inject = [
    "$scope",
    "$filter",
    "$state",
    "$stateParams",
    "$location",
    "$rootScope",
    "AppendixIIRegisterOfBlaService",
    "commonService",
  ];

  function AppendixIIRegisterOfBlaController(
    $scope,
    $filter,
    $state,
    $stateParams,
    $location,
    $rootScope,
    AppendixIIRegisterOfBlaService,
    commonService
  ) {
    var vm = this;

    vm.init = function () {
      vm.branchId = ($rootScope.globals && $rootScope.globals.currentUser && $rootScope.globals.currentUser.solId) || $rootScope.solId || "";
      vm.branchName = ($rootScope.globals && $rootScope.globals.currentUser && $rootScope.globals.currentUser.solDesc) || $rootScope.solDesc || "";
      vm.bankName = ($rootScope.globals && $rootScope.globals.currentUser && $rootScope.globals.currentUser.bankName) || "";
      vm.moduleName = "RBI Bond";
      vm.schemeList = [];
      vm.model = null;
      vm.noDataFound = false;
      vm.currentDate = $filter("date")(new Date(), "dd/MM/yyyy");
    };
    vm.init();

    if ($state.current.name == "appendix_ii_register_of_bla_all_holders.inquiry_details") {
      if ($stateParams.obj) {
        vm.schemeName = $stateParams.obj.schemeName;
        vm.fromDate = $stateParams.obj.fromDate;
        vm.toDate = $stateParams.obj.toDate;
      }
      getRegisterLedger();
    }

    function configDataTable() {
      vm.getTitleDescription = {
        title: "Appendix II - Register of Bond Ledger Accounts - All Holders",
        solId: vm.branchId,
        solDesc: vm.branchName,
      };
      if (typeof configDataTableOptions === "function") {
        vm.dataTableOption = configDataTableOptions(vm.getTitleDescription);
      }
    }

    function getRegisterLedger() {
      vm.loader = true;
      // Payload: Name of Scheme, From Date, To Date
      var requestData = {
        schemeName: vm.schemeName,
        fromDate: moment(vm.fromDate).format("DD-MMM-YYYY"),
        toDate: moment(vm.toDate).format("DD-MMM-YYYY"),
      };

      AppendixIIRegisterOfBlaService.getDetails(requestData).then(
        function (response) {
          vm.loader = false;
          if (response.data && response.data.result) {
            vm.model = response.data.result;
            vm.noDataFound = false;
            configDataTable();
          } else {
            vm.model = null;
            vm.noDataFound = true;
            Swal.fire(" ", "No records for the given input date", "warning");
          }
        },
        function (error) {
          vm.loader = false;
          vm.model = null;
          if (error.data && (error.data.message == "No Data Found" || error.data.message == "NDF" || error.data.message == "No Records Found")) {
            vm.noDataFound = true;
            Swal.fire(" ", "No records for the given input date", "warning");
          } else {
            handleErrorResponse(error);
            $state.go("appendix_ii_register_of_bla_all_holders.inquiry");
          }
        }
      );
    }

    vm.getSchemeName = function () {
      vm.loader = true;
      var requestData = {
        referenceId: "TAX",
      };
      AppendixIIRegisterOfBlaService.getRbiBondModuleNameDetails(requestData).then(
        function (response) {
          vm.loader = false;
          var list = (response.data && response.data.result) || [];
          // Normalize for torch display (scheme name + status)
          vm.schemeList = list.map(function (item) {
            return {
              schemeCode: item.schemeCode || item.subCode,
              schemeName: item.schemeName || item.subCodeDescription,
              schemeStatus: item.schemeStatus || item.status || "",
              subCode: item.subCode,
              subCodeDescription: item.subCodeDescription,
            };
          });
          angular.element("#schemeModel").modal("toggle");
        },
        function (error) {
          vm.loader = false;
          Swal.fire(" ", "Error fetching scheme list", "error");
        }
      );
    };

    vm.selectScheme = function (x) {
      vm.schemeCode = x.schemeCode || x.subCode;
      vm.schemeName = x.schemeName || x.subCodeDescription;
      vm.schemeStatus = x.schemeStatus || x.status || "";
      vm.fromDate = "";
      vm.toDate = "";
    };

    vm.printpage = function () {
      window.print();
    };

    vm.dateValidation = function () {
      vm.toDate = "";
      vm.dateOptions = {
        maxDate: typeof convertToDateObject === "function" ? convertToDateObject(vm.currentDate) : new Date(),
        showWeeks: false,
      };
      vm.dateToOptions = {
        minDate: vm.fromDate,
        maxDate: typeof convertToDateObject === "function" ? convertToDateObject(vm.currentDate) : new Date(),
        showWeeks: false,
      };
    };

    vm.effFromDatePicker = { opened: false };
    vm.effToDatePicker = { opened: false };
  }
})();
