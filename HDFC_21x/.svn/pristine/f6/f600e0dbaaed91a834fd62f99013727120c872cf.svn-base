(function () {
  "use strict";

  angular.module("gbmUltraApp").controller("MonthlyBrokerageClaimController", MonthlyBrokerageClaimController);
  MonthlyBrokerageClaimController.$inject = [
    "$scope",
    "$filter",
    "$state",
    "$stateParams",
    "$location",
    "$rootScope",
    "MonthlyBrokerageClaimService",
    "commonService",
  ];

  function MonthlyBrokerageClaimController($scope, $filter, $state, $stateParams, $location, $rootScope, MonthlyBrokerageClaimService, commonService) {
    var vm = this;

    vm.getSubsAccDetails = function () {
      console.log("hi");
      vm.loader = true;

      var requestData = {
        schemeCode: vm.schemeCode,
        fromDate: $filter("date")(vm.fromDate, "dd-MMM-yyyy"),
        toDate: $filter("date")(vm.toDate, "dd-MMM-yyyy"),
        flag: "D",
      };

      MonthlyBrokerageClaimService.getMonthBrokerageDetails(requestData).then(
        function (response) {
          vm.loader = false;
          if (response.data && response.data.result) {
            vm.monthBrokerageList = response.data.result.map((item) => item.number);
          } else {
            vm.monthBrokerageList = [];
          }
          vm.downloadTxtFile();
          Swal.fire("", "File Generated Successfully", "success");
        },
        function (error) {
          vm.loader = false;
          if (error.data.message == "No Records Found" || error.data.message == "NDF") {
            vm.noDataFound = error.data.message;
            Swal.fire(" ", "No records for the given input date", "warning");
            vm.schemeName=""; vm.fromDate=""; vm.toDate="";
          } else {
            $state.go("monthly_brokerage_claim_scroll.inquiry");
          }
        }
      );
    };

    vm.downloadTxtFile = function () {
      //        if (!Array.isArray(vm.subsAccDetailList) || vm.subsAccDetailList.length === 0) {
      //            alert("No data available to download.");
      //            return;
      //        }
      var textContent = vm.monthBrokerageList.join("\n");
      var blob = new Blob([textContent], { type: "text/plain" });
      var link = document.createElement("a");

      link.href = window.URL.createObjectURL(blob);
      link.download = "Monthly_Brokerage_Claim_Scroll.txt";
      document.body.appendChild(link);
      link.click();
      document.body.removeChild(link);
    };

    vm.getSchemeName = function () {
      vm.loader = true;
      var requestData = {
        schemeCode: "",
        flag: "S",
      };
      MonthlyBrokerageClaimService.getMonthBrokerageDetails(requestData).then(
        function (response) {
          vm.loader = false;
          vm.schemeList = response.data.result;
          angular.element("#schemeModel").modal("toggle");
        },
        function (error) {
          handleErrorResponse(error);
          vm.loader = false;
        }
      );
    };

    // Date Validation
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

      vm.textableDatePicker = textableDatePicker;
                                                        vm.autoslashing = autoslashing;
                                                        function textableDatePicker(id, valueDate) {
                                                          commonService.textableDatePicker(id, valueDate);
                                                        }
                                                        function autoslashing(id) {
                                                          commonService.autoslashing(id);
                                                        }
  }
})();
