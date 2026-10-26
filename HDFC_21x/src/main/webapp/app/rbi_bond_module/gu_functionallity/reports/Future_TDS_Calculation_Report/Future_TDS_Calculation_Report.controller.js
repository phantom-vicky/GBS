(function () {
  "use strict";

  angular.module("gbmUltraApp").controller("FutureTdsController", FutureTdsController);
  FutureTdsController.$inject = [
    "$scope",
    "$filter",
    "$state",
    "$stateParams",
    "$location",
    "$rootScope",
    "FutureTdsService",
    "commonService",
  ];

  function FutureTdsController($scope, $filter, $state, $stateParams, $location, $rootScope, FutureTdsService, commonService) {
    var vm = this;

    vm.getTdsDetails = function () {
      vm.loader = true;

      var requestData = {
        schemeCode: vm.schemeCode,
        menu:vm.menu,
        flag: 'D',
        fromDate: $filter("date")(vm.fromDate, "dd-MMM-yyyy"),
        toDate: $filter("date")(vm.toDate, "dd-MMM-yyyy"),
      };
      FutureTdsService.getFutureTdsDetails(requestData).then(
        function (response) {
          vm.loader = false;
          //vm.downloadExcel();
            if (response.data.result.fileName) {
               vm.fileData = response.data.result.fileData;
               vm.fileName = response.data.result.fileName;
               saveAs(getBlobFromBase64String(vm.fileData), vm.fileName);
               vm.menu = '';
               Swal.fire("", "File Generated Successfully", "success");
          }
        },
        function (error) {
          vm.loader = false;
          if (error.data.message == "No Records Found" || error.data.message == "NDF") {
            vm.noDataFound = error.data.message;
             Swal.fire(" ", "No records for the given input date", "warning");
            vm.schemeName = '';
            vm.fromDate = '';
            vm.toDate = '';
            vm.menu = '';
          } else {
            $state.go("Future_TDS_Calculation_Report.inquiry");
          }
        }
      );
    };

    const holderTypeMap = {
      "HT001": "Power of Attorney",
      "HT002": "Karta of HUF",
      "HT003": "Individual"
    };

    vm.downloadExcel = function () {
        const exportData = vm.model.map(item => ({
            "Scheme ID": item.schemeId,
            "Branch Ref No": item.branchRefNo,
            "BLA No": item.blaNo,
            "Applicant Name with HUF": item.applNameWithHuf,
            "Applicant Name 1": item.applName1,
            "Applicant Name 2": item.applName2,
            "Applicant Name 3": item.applName3,
            "CAS Transfer Date": item.casTransferDate,
            "Applicant Status": holderTypeMap[item.applStatus] || item.applStatus,
            "Receipt Date": item.rcptDate,
            "Investment Amount": item.investAmt,
            "Scheme Name": item.scheme,
            "CFD": item.cfd,
            "Maturity Date": item.maturityDate,
            "Interest Rate": item.intRate,
            "Last Interest Paid Amount": item.lastIntPaidAmt,
            "PAN Number": item.panNo,
            "Future Interest Status": item.futureInterestStatus,
            "New 1": item.new1,
            "New 2": item.new2,
            "New 3": item.new3,
            "New 4": item.new4,
            "New 5": item.new5
        }));

        const worksheet = XLSX.utils.json_to_sheet(exportData);
        const workbook = XLSX.utils.book_new();
        XLSX.utils.book_append_sheet(workbook, worksheet, "FutureTDS");

        const wbout = XLSX.write(workbook, { bookType: "xlsx", type: "array" });
        const blob = new Blob([wbout], { type: "application/octet-stream" });
        saveAs(blob, "FutureTdsCalculationReport.xlsx");
    };

    vm.getSchemeName = function () {
      vm.loader = true;
      var requestData = {
        flag: "S",
      };
      FutureTdsService.getFutureTdsDetails(requestData).then(
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

    //Date Validation
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
  }
})();
