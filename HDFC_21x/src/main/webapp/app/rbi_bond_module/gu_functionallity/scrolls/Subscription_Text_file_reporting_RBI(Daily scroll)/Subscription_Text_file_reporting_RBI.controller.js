(function () {
  "use strict";

  angular.module("gbmUltraApp").controller("SubscriptionTextFileController", SubscriptionTextFileController);
  SubscriptionTextFileController.$inject = [
    "$scope",
    "$filter",
    "$state",
    "$stateParams",
    "$location",
    "$rootScope",
    "SubscriptionTextFileService",
    "commonService",
  ];

  function SubscriptionTextFileController($scope, $filter, $state, $stateParams, $location, $rootScope, SubscriptionTextFileService, commonService) {
    var vm = this;

    vm.getSubsAccDetails = function () {
      vm.loader = true;

      var requestData = {
        schemeCode: vm.schemeCode,
        fromDate: $filter("date")(vm.fromDate, "dd-MMM-yyyy"),
        toDate: $filter("date")(vm.toDate, "dd-MMM-yyyy"),
        flag: "D",
      };

      SubscriptionTextFileService.getSubsAccDetails(requestData).then(
        function (response) {
          vm.loader = false;
          if (response.data && response.data.result) {
            vm.subsAccDetailList = response.data.result.map((item) => item.number);
          } else {
            vm.subsAccDetailList = [];
            console.log("No data found!");
          }
          vm.downloadTxtFile();
          Swal.fire("", "File Generated Successfully", "success");
        },
        function (error) {
          vm.loader = false;
          if (error.data.message == "No Records Found" || error.data.message == "NDF") {
            vm.noDataFound = error.data.message;
            Swal.fire(" ", "No records for the given input date", "warning");
          } else {
            $state.go("subscription_text_file_reporting_rbi.inquiry");
          }
        }
      );
    };

    vm.downloadTxtFile = function () {
      //        if (!Array.isArray(vm.subsAccDetailList) || vm.subsAccDetailList.length === 0) {
      //            alert("No data available to download.");
      //            return;
      //        }

      var textContent = vm.subsAccDetailList.join("\n");
      var blob = new Blob([textContent], { type: "text/plain" });
      var link = document.createElement("a");

      link.href = window.URL.createObjectURL(blob);
      link.download = "Subscription Text file reporting RBI (Daily scroll).txt";
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
      SubscriptionTextFileService.getSubsAccDetails(requestData).then(
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
