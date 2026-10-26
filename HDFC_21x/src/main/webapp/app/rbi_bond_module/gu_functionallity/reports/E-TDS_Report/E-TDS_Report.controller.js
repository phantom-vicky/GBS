(function () {
  "use strict";

  angular.module("gbmUltraApp").controller("eTDSReportController", eTDSReportController);

  eTDSReportController.$inject = ["$scope", "$filter", "$state", "$stateParams", "$location", "$rootScope", "eTDSReportService", "commonService"];

  function eTDSReportController($scope, $filter, $state, $stateParams, $location, $rootScope, eTDSReportService, commonService) {
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

 vm.textableDatePicker = textableDatePicker;
    vm.autoslashing = autoslashing;

    function textableDatePicker(id, valueDate) {
      commonService.textableDatePicker(id, valueDate);
    }
    function autoslashing(id) {
      commonService.autoslashing(id);
    }
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

    vm.getFinancialYear = function () {
      vm.loader = true;
      var requestData = {
        flag: "FY",
      };

      eTDSReportService.getDetails(requestData).then(
        function (response) {
          vm.loader = false;
          vm.financialYears = response.data.result.list;
        },
        function (error) {
          handleErrorResponse(error);
          vm.loader = false;
        }
      );
    };


    vm.setFinancialYear = function (item) {
      for (let i = 0; i < vm.financialYears.length; i++) {
        if (vm.financialYears[i].financialYear == item) {
          vm.financialYear = vm.financialYears[i].financialYear;
        }
      }
    };

    vm.getMonth = function () {
      vm.loader = true;
      var requestData = {
        flag: "M",
      };

      eTDSReportService.getDetails(requestData).then(
        function (response) {
          vm.loader = false;
          vm.monthIns = response.data.result.list;
        },
        function (error) {
          handleErrorResponse(error);
          vm.loader = false;
        }
      );
    };

    vm.setMonthIn = function (item) {
      for (let i = 0; i < vm.monthIns.length; i++) {
        if (vm.monthIns[i].monthIn == item) {
          vm.monthIn = vm.monthIns[i].monthIn;
        }
      }
    };

    function getDetails() {
      vm.loader = true;
      var requestData = {
       fromDate: $filter("date")(vm.fromDate, "dd-MMM-yyyy"),
                     toDate: $filter("date")(vm.toDate, "dd-MMM-yyyy"),
        flag: "D",
        pageNo: vm.pageNo,
        pageSize: vm.pageSize,
        searchValue: vm.searchText,
        sortOrder: vm.sortOrder,
        sortField: vm.sortField,
      };

      eTDSReportService.getDetails(requestData).then(
        function (response) {
          vm.loader = false;
          vm.model = response.data.result.list;
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
          if (vm.searchText && (error.data.message == "No Data Found" || error.data.message == "NDF")) {
             vm.noDataFoundInSearch = true;
             vm.total= error.data.total;
             vm.pageNo= 0;
             vm.maxPages = Math.ceil(error.data.total / vm.pageSize);
             vm.paginationControls();
             vm.showPrevious= false;
          } else if (error.data.message == "No Data Found" || error.data.message == "NDF") {
                    vm.noDataFound = error.data.message;
          } else {
                handleErrorResponse(error);
                $state.go("e-tds_report.inquiry");
          }
        }
      );
    }

    if ($state.current.name == "e-tds_report.inquiry_details") {
      vm.fromDate = $stateParams.obj.fromDate;
      vm.toDate = $stateParams.obj.toDate;
        getDetails();
    }

    //Download Report
    vm.downloadReport = function () {
      vm.loader = true;
      var requestData = {
       fromDate: $filter("date")(vm.fromDate, "dd-MMM-yyyy"),
              toDate: $filter("date")(vm.toDate, "dd-MMM-yyyy"),
        flag: "D",
        pageNo: 1,
        pageSize: vm.total,
        searchValue: vm.searchText,
      };

      eTDSReportService.getDetailsDownload(requestData).then(
        function (response) {
          vm.loader = false;
          vm.filteredRecordsDownload = response.data.result.list;

          if (vm.downloadType === "excel") {
//            vm.downloadExcel();
            if (response.data.result.fileName) {
                 vm.fileData = response.data.result.fileData;
                 vm.fileName = response.data.result.fileName;
                 saveAs(getBlobFromBase64String(vm.fileData), vm.fileName)
                 Swal.fire("Downloaded", response.data.message, "success");
            }
          } else if (vm.downloadType === "csv") {
            vm.downloadCSV();
          }
          vm.downloadType = "";
        },
        function (error) {
          vm.loader = false;
          if (error.data.message == "No Data Found" || error.data.message == "NDF") {
            vm.noDataFound = error.data.message;
          } else {
            handleErrorResponse(error);
            $state.go("e-tds_report.inquiry");
          }
        }
      );
    };

    vm.downloadCSV = function () {
      const tableHeader = [
        { title: "S.NO", width: 10 },
        { title: "Challan Serial No", width: 30 },
        { title: "Section Code", width: 30 },
        { title: "Type of Rent", width: 30 },
        { title: "Deductee Identification No", width: 40 },
        { title: "Deductee Code (414)", width: 30 },
        { title: "Permanent Account Number (PAN) of deductee (415)", width: 30 },
        { title: "Deductee reference No(In case of Invalid PAN)(413)", width: 45 },
        { title: "Name of Deductee (416)", width: 45 },
        { title: "Date on which Amount paid / credited (418)", width: 45 },
        { title: "Date on which tax deducted (422)", width: 40 },
        { title: "Amount of Payment (Rs.)(419)", width: 40 },
        { title: "Rate at which tax deducted (423)", width: 30 },
        { title: "Amount of tax deducted", width: 30 },
        { title: "Total Tax Deposited (421)", width: 45 },
        { title: "Reason for Non-deduction / Lower Deduction, if any (424)", width: 40 },
        { title: "Certificate No .u/s 197 issued by the AO for non-deduction/ lower deduction (425)", width: 40 },
      ];

      const formattedHeader = tableHeader.map((col) => col.title.padEnd(col.width, " ")).join("\t");

      const tableData = vm.filteredRecordsDownload.map((record, index) =>
        tableHeader
          .map((col, colIndex) => {
            const value = [
              index + 1,
              record.chSlNo || "",
              record.sectionCode || "",
              record.typeOfRent || "",
              record.deducteeIdentificationNo || "",
              record.deducteeCode || "",
              record.panNo || "",
              record.deducteeReferenceNo || "",
              record.deducteeName || "",
              record.amountPaidDate || "",
              record.taxDeductedDate || "",
              record.amountOfPayment || "",
              record.rateAtTaxDeducted || "",
              record.amountOfTaxDeducted || "",
              record.totalTaxDeposited || "",
              record.deductionReason || "",
              record.certificateNo || "",
            ][colIndex];
            return value.toString().padEnd(col.width, " ");
          })
          .join("\t")
      );

      const textData = [
        "E-TDS Report",
        "Bank Name: INSPIRISY BANK LTD",
        "Sol ID: 101 / BRANCH OF THE BANK",
        "Name of the Module " + " " + "RBI Bond",
        "Financial Year : " + vm.financialYear,
        "Month : " + vm.monthIn,
        formattedHeader,
        "-".repeat(formattedHeader.length),
        ...tableData,
      ].join("\n");

      const textContent = "data:text/plain;charset=utf-8," + encodeURIComponent(textData);
      const link = document.createElement("a");
      link.setAttribute("href", textContent);
      link.setAttribute("download", "E-TDS Report.txt");
      document.body.appendChild(link);
      link.click();
      document.body.removeChild(link);
    };

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
  }
})();
