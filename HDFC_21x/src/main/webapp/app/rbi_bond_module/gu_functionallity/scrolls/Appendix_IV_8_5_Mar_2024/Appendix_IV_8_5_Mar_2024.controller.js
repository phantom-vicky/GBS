(function () {
  "use strict";

  angular.module("gbmUltraApp").controller("AppendixIVController", AppendixIVController);

  AppendixIVController.$inject = ["$scope", "$filter", "$state", "$stateParams", "$location", "$rootScope", "AppendixIVService", "commonService"];

  function AppendixIVController($scope, $filter, $state, $stateParams, $location, $rootScope, AppendixIVService, commonService) {
    var vm = this;

    vm.textableDatePicker = textableDatePicker;
    vm.autoslashing = autoslashing;
    vm.textableDatePickerTo =  textableDatePickerTo;


       function textableDatePickerTo(id, valueDate,valueDateFrom) {
                                                       if (valueDate != null && valueDateFrom != null) {

                                                             let lastDayOfMonth = new Date(valueDateFrom.getFullYear(), valueDateFrom.getMonth() + 1, 0);

                                                             // Validation
                                                             if (valueDate < valueDateFrom) {

                                                                 Swal.fire("warning","To Date should be greater than or equal to From Date", "warning");
                                                                 vm.toDate="";
                                                                 return;
                                                             }

                                                             if (valueDate > lastDayOfMonth) {

                                                                 Swal.fire("warning","To Date should not exceed month end of From Date", "warning");
                                                                  vm.toDate="";
                                                                 return;
                                                             }


                                                         }
                                                          commonService.textableDatePicker(id, valueDate);
                                                        }

    function textableDatePicker(id, valueDate) {
      commonService.textableDatePicker(id, valueDate);
    }
    function autoslashing(id) {
      commonService.autoslashing(id);
    }

    vm.getAppendixIVDetails = getAppendixIVDetails;
    vm.getModuleName = getModuleName;

    if ($state.current.name == "Appendix_IV_8_5_Mar_2024.inquiry_details") {
      vm.schemeCode = $stateParams.obj.schemeCode;
      vm.schemeName = $stateParams.obj.schemeName;
      vm.fromDate = $stateParams.obj.fromDate;
      vm.toDate = $stateParams.obj.toDate;
      getAppendixIVDetails();
    }

    vm.downloadReport = function () {
      console.log("The download Type ", vm.downloadType);
      if (vm.downloadType === "excel") {
        vm.downloadExcel();
      }
      if (vm.downloadType === "csv") {
        vm.downloadTXT();
      }
      vm.downloadType = "";
    };

    vm.downloadExcel = function () {
      var table = document.getElementById("myTable");
      var workbook = XLSX.utils.table_to_book(table, { sheet: "Sheet1" });
      XLSX.writeFile(workbook, "Appendix_8_5_scroll.xlsx");
    };

    vm.downloadTXT = function () {
      var table = document.getElementById("myTable");
      var rows = table.querySelectorAll("tr");
      var data = Array.from(rows).map((row) => Array.from(row.querySelectorAll("th, td")).map((cell) => cell.innerText.trim()));

      var colWidths = [];
      data.forEach((row) => {
        row.forEach((cell, i) => {
          colWidths[i] = Math.max(colWidths[i] || 0, cell.length);
        });
      });

      var txt = "";
      data.forEach((row) => {
        row.forEach((cell, i) => {
          txt += cell.padEnd(colWidths[i] + 2, " ");
        });
        txt += "\n";
      });

      var blob = new Blob([txt], { type: "text/plain" });
      var link = document.createElement("a");
      link.download = "Bond_Balance_Report.txt";
      link.href = window.URL.createObjectURL(blob);
      link.click();
    };

    function getModuleName() {
      vm.loader = true;
      var requestData = {
        flag: "S",
      };
      AppendixIVService.getDetails(requestData).then(
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
    }
    function getAppendixIVDetails() {
      vm.loader = true;
      var requestData = {
        schemeCode: vm.schemeCode,
        flag: "D",
        fromDate: $filter("date")(vm.fromDate, "dd-MMM-yyyy"),
        toDate: $filter("date")(vm.toDate, "dd-MMM-yyyy"),
      };

      AppendixIVService.getDetails(requestData).then(
        function (response) {
          vm.loader = false;
          //        partA
          vm.partACumList = response.data.result.partACumList;
          vm.partANonCumList = response.data.result.partANonCumList;
          //       partB
          vm.partBCumList = response.data.result.partBCumList;
          vm.partBNonCumList = response.data.result.partBNonCumList;
          //        partC
          vm.partCCumList = response.data.result.partCCumList;
          vm.partCNonCumList = response.data.result.partCNonCumList;
          //          partD
          vm.partDCumList = response.data.result.partDCumList;
          vm.partDNonCumList = response.data.result.partDNonCumList;

          vm.noDataFound =
            vm.partACumList.length === 0 &&
            vm.partANonCumList.length === 0 &&
            vm.partBCumList.length === 0 &&
            vm.partBNonCumList.length === 0 &&
            vm.partCCumList.length === 0 &&
            vm.partCNonCumList.length === 0 &&
            vm.partDCumList.length === 0 &&
            vm.partDNonCumList.length === 0;

          vm.partANonCumulativeTotalAmount = vm.partANonCumList.reduce((sum, record) => sum + (record.amountNonCumA ? parseFloat(record.amountNonCumA) : 0), 0);
          vm.partACumulativeTotalAmount = vm.partACumList.reduce((sum, record) => sum + (record.amountCumA ? parseFloat(record.amountCumA) : 0), 0);
          vm.partBNonCumulativeTotalAmount = vm.partBNonCumList.reduce((sum, record) => sum + (record.amountNonCumB ? parseFloat(record.amountNonCumB) : 0), 0);
          vm.partBCumulativeTotalAmount = vm.partBCumList.reduce((sum, record) => sum + (record.amountCumB ? parseFloat(record.amountCumB) : 0), 0);
          vm.partCNonCumulativeTotalAmount = vm.partCNonCumList.reduce((sum, record) => sum + (record.amountNonCumC ? parseFloat(record.amountNonCumC) : 0), 0);
          vm.partCCumulativeTotalAmount = vm.partCCumList.reduce((sum, record) => sum + (record.amountCumC ? parseFloat(record.amountCumC) : 0), 0);
          vm.partDNonCumulativeTotalAmount = vm.partDNonCumList.reduce((sum, record) => sum + (record.amountNonCumD ? parseFloat(record.amountNonCumD) : 0), 0);
          vm.partDCumulativeTotalAmount = vm.partDCumList.reduce((sum, record) => sum + (record.amountCumD ? parseFloat(record.amountCumD) : 0), 0);

          vm.nonCumulativeNetTotal = vm.partANonCumulativeTotalAmount + vm.partBNonCumulativeTotalAmount - (vm.partCNonCumulativeTotalAmount + vm.partDNonCumulativeTotalAmount);
          vm.cumulativeNetTotal = vm.partACumulativeTotalAmount + vm.partBCumulativeTotalAmount - (vm.partCCumulativeTotalAmount + vm.partDCumulativeTotalAmount);

          //     partA mergelist
          vm.mergedPartAList = [];
          var maxLength = Math.max(vm.partANonCumList.length, vm.partACumList.length);
          for (var i = 0; i < maxLength; i++) {
            vm.mergedPartAList.push({ nonCumulative: vm.partANonCumList[i] || {}, cumulative: vm.partACumList[i] || {} });
          }

          //   partB mergelist
          vm.mergedPartBList = [];

          var maxLength = Math.max(vm.partBNonCumList.length, vm.partBCumList.length);
          for (var i = 0; i < maxLength; i++) {
            vm.mergedPartBList.push({ nonCumulative: vm.partBNonCumList[i] || {}, cumulative: vm.partBCumList[i] || {} });
          }

          //   partC mergelist
          vm.mergedPartCList = [];

          var maxLength = Math.max(vm.partCNonCumList.length, vm.partCCumList.length);
          for (var i = 0; i < maxLength; i++) {
            vm.mergedPartCList.push({ nonCumulative: vm.partCNonCumList[i] || {}, cumulative: vm.partCCumList[i] || {} });
          }

          //           partD mergelist
          vm.mergedPartDList = [];

          var maxLength = Math.max(vm.partDNonCumList.length, vm.partDCumList.length);
          for (var i = 0; i < maxLength; i++) {
            vm.mergedPartDList.push({ nonCumulative: vm.partDNonCumList[i] || {}, cumulative: vm.partDCumList[i] || {} });
          }
        },

        function (error) {
          vm.loader = false;

          if (error.data.message == "No Data Found") {
            vm.noDataFound = error.data.message;
          } else {
            handleErrorResponse(error);
            $state.go("Appendix_IV_8_5_Mar_2024.inquiry");
          }
        }
      );
    }
    // Date Validation
    vm.fromDateVal = function () {
      vm.fromDateOptions = {
        maxDate: new Date(),
        showWeeks: false,
      };
    };
    vm.toDateVal = function (fromDate) {
     // Convert to Date object (if fromDate is string)
                var from = new Date(fromDate);

                // Last day of the same month as fromDate
                var lastDayOfMonth = new Date(
                    from.getFullYear(),
                    from.getMonth() + 1,
                    0
                );


           vm.toDateOptions = {
             minDate: from,
             maxDate: lastDayOfMonth,
             showWeeks: false,
           };
    };
  }
})();
