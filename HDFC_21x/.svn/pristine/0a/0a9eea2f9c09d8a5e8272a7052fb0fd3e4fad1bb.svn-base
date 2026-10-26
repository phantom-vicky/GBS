(function () {
  "use strict";

  angular.module("gbmUltraApp").controller("SchemeWiseOutstandingBalanceController", SchemeWiseOutstandingBalanceController);
  SchemeWiseOutstandingBalanceController.$inject = [
    "$scope",
    "$filter",
    "$state",
    "$stateParams",
    "$location",
    "$rootScope",
    "SchemeWiseOutstandingBalanceService",
  ];

  function SchemeWiseOutstandingBalanceController(
    $scope,
    $filter,
    $state,
    $stateParams,
    $location,
    $rootScope,
    SchemeWiseOutstandingBalanceService
  ) {
    var vm = this;

    // Initialize Branch details from logged in user
    vm.init = function () {
      vm.branchId = $rootScope.solId || "";
      vm.branchName = $rootScope.solDesc || "";
      vm.moduleName = "RBI Bond";
      vm.showDetails = false;
      vm.noDataFound = false;
    };
    vm.init();

    if ($state.current.name == "scheme_wise_outstanding_balance_report.inquiry_details") {
      vm.toDate = $stateParams.obj.toDate;
      vm.toDateDisplay = $filter("date")(vm.toDate, "dd/MM/yyyy");
      vm.balanceAsOnDateLabel = formatBalanceAsOnDate(vm.toDate);
      vm.loader = true;

      // Only To Date is sent — backend returns outstanding balance from scheme start up to this date
      var requestData = {
        toDate: $filter("date")(vm.toDate, "dd-MMM-yyyy"),
      };

      SchemeWiseOutstandingBalanceService.getSchemeWiseOutstandingBalanceDetails(requestData).then(
        function (response) {
          vm.loader = false;
          if (response.data && response.data.result) {
            var result = response.data.result;
            vm.outstandingBalanceData = Array.isArray(result) ? result : result.list || result.details || [];
            vm.showDetails = true;
            vm.noDataFound = false;
          } else {
            vm.showDetails = false;
            vm.noDataFound = true;
            Swal.fire(" ", "No records for the given input date", "warning");
          }
        },
        function (error) {
          vm.loader = false;
          vm.showDetails = false;
          if (error.data && (error.data.message == "No Records Found" || error.data.message == "NDF")) {
            vm.noDataFound = true;
            Swal.fire(" ", "No records for the given input date", "warning");
          } else {
            Swal.fire(" ", "Error occurred while fetching data", "error");
          }
        }
      );
    }

    function formatBalanceAsOnDate(dateValue) {
      if (!dateValue) {
        return "";
      }
      var dateObj = dateValue instanceof Date ? dateValue : new Date(dateValue);
      if (isNaN(dateObj.getTime())) {
        return $filter("date")(dateValue, "dd MMMM yyyy");
      }
      var day = dateObj.getDate();
      var ordinal = "th";
      if (day % 10 === 1 && day !== 11) {
        ordinal = "st";
      } else if (day % 10 === 2 && day !== 12) {
        ordinal = "nd";
      } else if (day % 10 === 3 && day !== 13) {
        ordinal = "rd";
      }
      var monthYear = $filter("date")(dateObj, "MMMM yyyy");
      return day + ordinal + " " + monthYear;
    }

    vm.printpage = function () {
      window.print();
    };

    vm.getOutstandingBalanceDetails = function () {
      $state.go("scheme_wise_outstanding_balance_report.inquiry_details", {
        obj: {
          toDate: vm.toDate,
        },
      });
    };

    vm.downloadOutstandingBalanceReport = function () {
      vm.loader = true;
      var requestData = {
        toDate: $filter("date")(vm.toDate, "dd-MMM-yyyy"),
        flag: "R",
      };

      SchemeWiseOutstandingBalanceService.getSchemeWiseOutstandingBalanceDetails(requestData).then(
        function (response) {
          vm.loader = false;
          if (response.data && response.data.result) {
            var dataList = response.data.result.map(function (item) {
              return item.number || item;
            });
            var textContent = dataList.join("\n");
            downloadFile(textContent, "Scheme Wise Outstanding Balance Report.txt", "text/plain");
            Swal.fire("Success", "Scheme Wise Outstanding Balance Report.txt Generated Successfully", "success");
          } else {
            Swal.fire("Warning", "No data available for download", "warning");
          }
        },
        function (error) {
          vm.loader = false;
          handleErrorResponse(error);
        }
      );
    };

    function downloadFile(content, fileName, contentType) {
      var blob = new Blob([content], { type: contentType });
      var link = document.createElement("a");
      link.href = window.URL.createObjectURL(blob);
      link.download = fileName;
      document.body.appendChild(link);
      link.click();
      document.body.removeChild(link);
    }

    // To Date only — outstanding balance is as on this date (from scheme start)
    vm.dateValidation = function () {
      vm.dateToOptions = {
        maxDate: new Date(),
        showWeeks: false,
      };
    };

    vm.effToDatePicker = { opened: false };
  }
})();
