(function () {
  "use strict";

  angular
    .module("gbmUltraApp")
    .controller("ModeOfHoldingController", ModeOfHoldingController);

  ModeOfHoldingController.$inject = [
    "$scope",
    "$filter",
    "$state",
    "$stateParams",
    "$location",
    "$rootScope",
    "ModeOfHoldingService",
    "commonService",
  ];

  function ModeOfHoldingController(
    $scope,
    $filter,
    $state,
    $stateParams,
    $location,
    $rootScope,
    ModeOfHoldingService,
    commonService
  ) {
    var vm = this;
    vm.itemsPerPage = "5";

    //Pagination integration
    vm.showNext = false;
    vm.showPrevious = false;
    $scope.sortReverse = false;
    $scope.sort = function (key) {
      $scope.sortKey = key;
      $scope.sortReverse =
        $scope.sortKey == key ? !$scope.sortReverse : $scope.sortReverse;
      $scope.sortReverse ? (vm.sortOrder = "DESC") : (vm.sortOrder = "ASC");
      vm.sortField = key;
      vm.getReportDetails();
    };
    vm.pageNo = 1;

    vm.textableDatePicker = textableDatePicker;
    vm.autoslashing = autoslashing;

    function textableDatePicker(id, valueDate) {
      commonService.textableDatePicker(id, valueDate);
    }
    function autoslashing(id) {
      commonService.autoslashing(id);
    }

    vm.filteredRecords = [];
    vm.pageSizeOptions = [10, 25, 50, 100];
    vm.pageSize = vm.pageSizeOptions[0];
    vm.currentPage = 1;
    vm.maxPagesToShow = 5;
    vm.searchText = "";
    vm.loading = true;
    vm.showExportOptions = false;
    vm.getDetails= getDetails;

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
//      vm.maxPages = parseInt(vm.pageDetails.maxPages);
//      for (var i = 1; i <= parseInt(vm.pageDetails.maxPages); i++) {
//        vm.pages.push(parseInt(i));
//      }


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

    vm.setPage = function (page) {
      if (page < 1) {
        vm.currentPage = 1;
      } else if (page > vm.totalPages) {
        vm.currentPage = vm.totalPages;
      } else {
        vm.currentPage = page;
      }
      vm.updatePagination();
    };

    vm.updatePagination = function () {
      try {
        var startPage, endPage;
        if (vm.totalPages <= vm.maxPagesToShow) {
          startPage = 1;
          endPage = vm.totalPages;
        } else {
          var maxPagesBeforeCurrentPage = Math.floor(vm.maxPagesToShow / 2);
          var maxPagesAfterCurrentPage = Math.ceil(vm.maxPagesToShow / 2) - 1;

          if (vm.currentPage <= maxPagesBeforeCurrentPage) {
            startPage = 1;
            endPage = vm.maxPagesToShow;
          } else if (
            vm.currentPage + maxPagesAfterCurrentPage >=
            vm.totalPages
          ) {
            startPage = vm.totalPages - vm.maxPagesToShow + 1;
            endPage = vm.totalPages;
          } else {
            startPage = vm.currentPage - maxPagesBeforeCurrentPage;
            endPage = vm.currentPage + maxPagesAfterCurrentPage;
          }
        }

        vm.pages = [];
        for (var i = startPage; i <= endPage; i++) {
          vm.pages.push(i);
        }
      } catch (error) {
        console.error("An error occurred in updatePagination:", error);
      }
    };

    vm.updatePageSize = function () {
      vm.totalPages = Math.ceil(vm.filteredRecords.length / vm.pageSize);
      vm.setPage(1);
    };

    vm.prevPage = function () {
      vm.setPage(vm.currentPage - 1);
    };

    vm.nextPage = function () {
      vm.setPage(vm.currentPage + 1);
    };

    vm.firstPage = function () {
      vm.setPage(1);
    };

    vm.lastPage = function () {
      vm.setPage(vm.totalPages);
    };

    vm.updateFilteredRecords = function () {
      const searchText = vm.searchText ? vm.searchText.toLowerCase() : "";
      vm.filteredRecords = vm.result.filter((record) => {
        return Object.values(record).some(
          (value) =>
            value !== null &&
            value !== undefined &&
            String(value).toLowerCase().includes(searchText)
        );
      });
      vm.totalPages = Math.ceil(vm.filteredRecords.length / vm.pageSize);
      vm.setPage(1);
    };
    vm.maxValue = function () {
      return Math.min(vm.currentPage * vm.pageSize, vm.filteredRecords.length);
    };
    vm.solDate = $rootScope.globals.currentUser.systemDate;
    vm.bankName = $rootScope.globals.currentUser.bankName;
    vm.branchOfTheBank = $rootScope.globals.currentUser.solDesc;
    vm.branchCode = $rootScope.globals.currentUser.solId;
    vm.formatDate = function (tempDate) {
      return $filter("date")(tempDate, "dd/MM/yyyy");
    };

    if (
      $state.current.name == "Mode_Of_Holding_Change_Report.inquiry_details"
    ) {
      vm.schemeCode = $stateParams.obj.schemeCode;
      vm.schemeName = $stateParams.obj.schemeName;
      vm.fromDate = $stateParams.obj.fromDate;
      vm.toDate = $stateParams.obj.toDate;
      getDetails();
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

    vm.downloadReport = function () {
      if (vm.downloadType === "excel") {
        vm.downloadExcel();
      }
      if (vm.downloadType === "csv") {
        vm.downloadTxt();
      }
      vm.downloadType = "";
    };

    vm.getDownload = getDownload;
    function getDownload() {
      vm.loader = true;
      var requestData = {
        schemeCode: vm.schemeCode,
        fromDate: $filter("date")(vm.fromDate, "dd-MMM-yyyy"),
        toDate: $filter("date")(vm.toDate, "dd-MMM-yyyy"),
        flag: "D",
        //
        pageNo: "1",
        pageSize: vm.total,
        searchValue: vm.searchValue,
        downloadType: vm.downloadType,
      };
      ModeOfHoldingService.getModeOfHoldingDownload(requestData).then(
        function (response) {
          vm.loader = false;
          vm.downloadfilteredRecords = response.data.result.list;

          if (vm.downloadType === "excel") {
//            vm.downloadExcel();
            if (response.data.result.fileName) {
                 vm.fileData = response.data.result.fileData;
                 vm.fileName = response.data.result.fileName;
                 saveAs(getBlobFromBase64String(vm.fileData), vm.fileName)
            }
          }
         else if (vm.downloadType === "csv") {
            vm.downloadTxt();

          }
          vm.downloadType = "";
        },
        function (error) {
          vm.loader = false;

          if (
            error.data.message == "No Data Found" ||
            error.data.message == "NDF" && vm.searchValue
          ) {
            vm.noDataFound = error.data.message;
            vm.noDataFoundInSearch = true;
          } else if (
            error.data.message == "No Data Found" ||
            error.data.message == "NDF"
          ) {
            vm.noDataFound = error.data.message;
          } else {
            handleErrorResponse(error);
            $state.go("callback_report.inquiry");
          }
        }
      );
    }

    // Function to Download Excel
    vm.downloadExcel = function () {
      const additionalHeaders = [
        ["Mode of Holding Change Report"],
        ["Sol ID: 101 / BRANCH OF THE BANK"],
        ["Name of the Bond Scheme: " + vm.schemeName],
        ["From Date: " + $filter("date")(vm.fromDate, "dd/MM/yyyy")],
        ["To Date: " + $filter("date")(vm.toDate, "dd/MM/yyyy")],
        [],
      ];

      const tableHeader = [
        "S.No.",
        "Holder Name",
        "PAN No",
        "Mode of Holding",
        "From BLA Account Number",
        "Active Subscription Id",
        "Active Subscription Count",
        "To BLA Account Number",
        "Date of Holding Change",
        "Remarks",
      ];

      const tableData = vm.downloadfilteredRecords.map((record, index) => [
        (vm.currentPage - 1) * vm.pageSize + index + 1,
        record.holderName,
        record.panNo,
        record.modeOfHolding,
        record.fromBlaAccountNumber,
        record.activeSubscriptionId,
        record.activeSubscriptionCount,
        record.toBlaAccountNumber,
        record.dateOfHoldingChange,
        record.remarks,
      ]);

      const wsData = [...additionalHeaders, tableHeader, ...tableData];
      const ws = XLSX.utils.aoa_to_sheet(wsData);
      const wb = XLSX.utils.book_new();
      XLSX.utils.book_append_sheet(wb, ws, "Transaction Details");

      XLSX.writeFile(wb, "Mode_Of_Holding_Change.xlsx");
    };

    vm.downloadTxt = function () {
      const additionalHeaders = [
        "Mode Of Holding Change Report",
        "Bank Name: INSPIRISY BANK LTD",
        "Sol ID: 101 / BRANCH OF THE BANK",
        "From Date: " + $filter("date")(vm.fromDate, "dd/MM/yyyy"),
        "To Date: " + $filter("date")(vm.toDate, "dd/MM/yyyy"),
        "", // Blank line for spacing
      ];

      const tableHeader = [
        "S. No.",
        "Holder Name",
        "PAN No",
        "Mode of Holding",
        "From BLA Account Number",
        "Active Subscription Id",
        "Active Subscription Count",
        "To BLA Account Number",
        "Date of Holding Change",
        "Remarks",
      ];

      function padString(str, width) {
        return str.toString().padEnd(width, " ");
      }

      const columnWidths = [10, 30, 25, 25, 25, 25, 30, 30, 25, 30];

      const formattedHeader = tableHeader
        .map((header, index) => padString(header, columnWidths[index]))
        .join("");

      const tableData = vm.downloadfilteredRecords.map((record, index) =>
        [
          padString(
            (vm.currentPage - 1) * vm.pageSize + index + 1,
            columnWidths[0]
          ),
          padString(record.holderName || "", columnWidths[1]),
          padString(record.panNo || "", columnWidths[2]),
          padString(record.modeOfHolding || "", columnWidths[3]),
          padString(record.fromBlaAccountNumber || "", columnWidths[4]),
          padString(record.activeSubscriptionId || "", columnWidths[5]),
          padString(record.activeSubscriptionCount || "", columnWidths[6]),
          padString(record.toBlaAccountNumber || "", columnWidths[7]),
          padString(record.dateOfHoldingChange || "", columnWidths[8]),
          padString(record.remarks || "", columnWidths[9]),
        ].join("")
      );

      const textData = [
        ...additionalHeaders,
        formattedHeader,
        ...tableData,
      ].join("\n");

      const textContent =
        "data:text/plain;charset=utf-8," + encodeURIComponent(textData);
      const link = document.createElement("a");
      link.setAttribute("href", textContent);
      link.setAttribute("download", "Mode_Of_Holding_Change_Report.txt");
      document.body.appendChild(link);
      link.click();
      document.body.removeChild(link);
    };

    function getDetails() {
      vm.loader = true;
if(null!=vm.searchValue){
var requestData = {
        schemeCode: vm.schemeCode,
        fromDate: $filter("date")(vm.fromDate, "dd-MMM-yyyy"),
        toDate: $filter("date")(vm.toDate, "dd-MMM-yyyy"),
        flag: "D",
        //
        pageNo: vm.pageNo,
        pageSize: vm.pageSize,
        searchValue: vm.searchValue,
        sortOrder: vm.sortOrder,
        sortField: vm.sortField,
      };
          }else{
var requestData = {
        schemeCode: vm.schemeCode,
        fromDate: $filter("date")(vm.fromDate, "dd-MMM-yyyy"),
        toDate: $filter("date")(vm.toDate, "dd-MMM-yyyy"),
        flag: "D",
        //
        pageNo: vm.pageNo,
        pageSize: vm.pageSize,
        searchValue: vm.searchValue,
        sortOrder: vm.sortOrder,
        sortField: vm.sortField,
      };
   }


      ModeOfHoldingService.getDetails(requestData).then(
        function (response) {
          vm.loader = false;

          vm.result = response.data.result.list;
          vm.filteredRecords = response.data.result.list;
          vm.totalPages = Math.ceil(vm.result.length / vm.pageSize);
          vm.pageDetails = response.data.result.list;
             vm.total = response.data.total;
                    vm.maxPages = Math.ceil(response.data.total / vm.pageSize);
          vm.updatePagination();
            vm.paginationControls();



          if (vm.filteredRecords.length === 0) {
            vm.noDataFoundInSearch = true;
          } else {
            vm.noDataFoundInSearch = false;
          }

          var holdingReferenceId = { referenceId: "MODE_OF_HOLDING" };

          ModeOfHoldingService.getReferenceData(holdingReferenceId).then(
            function (holdingMappingResponse) {
              var holdingMappings = holdingMappingResponse.data.result;
              var paymentMap = {};

              holdingMappings.forEach(function (item) {
                paymentMap[item.SUBCODE] = item.DESCRIPTION;
              });

              vm.filteredRecords.forEach(function (record) {
                record.modeOfHolding =
                  paymentMap[record.modeOfHolding] || "Unknown";
              });

              console.log(
                "Updated Records with Descriptions:",
                vm.filteredRecords
              );
            }
          );
        },
        function (error) {
          vm.loader = false;
          if (
            error.data.message == "No Data Found" ||
            error.data.message == "NDF" && vm.searchValue
          ) {


                    vm.pageNo ='0';
                     vm.maxPages ='0';
                     vm.total = '0';
                      vm.showNext = false;
            vm.noDataFoundInSearch = true;
            vm.noDataFound = error.data.message;
          } else if (
            error.data.message == "No Data Found" ||
            error.data.message == "NDF"
          ) {
            vm.noDataFound = error.data.message;
          } else {
            handleErrorResponse(error);
            $state.go("Mode_Of_Holding_Change_Report.inquiry");
          }
        }
      );
    }

    vm.getSchemeName = function () {
      vm.loader = true;
      var requestData = {
        flag: "S",
      };

      ModeOfHoldingService.getDetails(requestData).then(
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

    vm.printPage = function () {
      window.print();
    };
  }
})();
