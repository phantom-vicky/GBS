(function () {
  "use strict";
  angular.module("gbmUltraApp").controller("ReportOnPanAadharController", ReportOnPanAadharController);
  ReportOnPanAadharController.$inject = [
    "$scope",
    "$filter",
    "$state",
    "$stateParams",
    "$location",
    "$rootScope",
    "ReportOnPanAadharService",
    "commonService",
  ];

  function ReportOnPanAadharController($scope, $filter, $state, $stateParams, $location, $rootScope, ReportOnPanAadharService, commonService) {
    var vm = this;
    vm.getPanAadharDetails = getPanAadharDetails;
    vm.solDate = $rootScope.globals.currentUser.systemDate;

   // Pagination
    vm.showNext = false;
    vm.showPrevious = false;
    $scope.sortReverse = false;
    $scope.sort = function (key) {
      $scope.sortKey = key;
      $scope.sortReverse = $scope.sortKey == key ? !$scope.sortReverse : $scope.sortReverse;
      $scope.sortReverse ? (vm.sortOrder = "DESC") : (vm.sortOrder = "ASC");
      vm.sortField = key;
      vm.getPanAadharDetails();
    };
    vm.pageNo = 1;
    vm.filteredRecords = [];
    vm.pageSizeOptions = [10, 25, 50, 100];
    vm.pageSize = vm.pageSizeOptions[0];
    vm.currentPage = 1;
    vm.maxPagesToShow = 5;
    vm.searchText = "";
    vm.loading = true;
    vm.showExportOptions = false;

    vm.itemsPerPage = "5";

    vm.goPreviousPage = function () {
      vm.pageNo = parseInt(vm.pageNo) - 1;
      vm.getPanAadharDetails();
    };

    vm.goNextPage = function () {
      vm.pageNo = parseInt(vm.pageNo) + 1;
      vm.getPanAadharDetails();
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
          } else if (vm.currentPage + maxPagesAfterCurrentPage >= vm.totalPages) {
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
      vm.filteredRecords = vm.records.filter((record) => {
        return Object.values(record).some((value) => value !== null && value !== undefined && String(value).toLowerCase().includes(searchText));
      });
      vm.totalPages = Math.ceil(vm.filteredRecords.length / vm.pageSize);
      vm.setPage(1);
    };
    vm.maxValue = function () {
      return Math.min(vm.currentPage * vm.pageSize, vm.filteredRecords.length);
    };

    if ($state.current.name === "report_on_pan_aadhar.inquiry_details") {
      vm.financialYear = $stateParams.obj.financialYear;
      vm.getPanAadharDetails();
    }

    vm.getFinancialYears = function () {
      vm.loader = true;
      var requestData = {
        flag: "FY",
      };
      ReportOnPanAadharService.getDetails(requestData).then(
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
    function getPanAadharDetails() {
      vm.loader = true;
      if (null != vm.searchValue) {
        var requestData = {
          flag: "D",
          financialYear: vm.financialYear,
          //
          pageNo: vm.pageNo,
          pageSize: vm.pageSize,
          searchValue: vm.searchValue,
          sortOrder: vm.sortOrder,
          sortField: vm.sortField,
        };
      } else {
        var requestData = {
          flag: "D",
          financialYear: vm.financialYear,
          //
          pageNo: vm.pageNo,
          pageSize: vm.pageSize,
          searchValue: vm.searchValue,
          sortOrder: vm.sortOrder,
          sortField: vm.sortField,
        };
      }

      ReportOnPanAadharService.getDetails(requestData).then(
        function (response) {
          vm.loader = false;

          vm.filteredRecords = response.data.result.list;

          vm.records = response.data.result.list;
          vm.total = response.data.total;
          vm.totalPages = Math.ceil(vm.records.length / vm.pageSize);
          vm.pageDetails = response.data.result.list;

          vm.updatePagination();
          vm.total = response.data.total;
          vm.maxPages = Math.ceil(response.data.total / vm.pageSize);
           vm.paginationControls();

          if (vm.filteredRecords.length === 0) {
            vm.noDataFoundInSearch = true;
          } else {
            vm.noDataFoundInSearch = false;
          }
        },
        function (error) {
          vm.loader = false;
          if ((error.data.message == "No Data Found" || error.data.message == "NDF") && vm.searchValue) {


                      vm.pageNo ='0';
                      vm.maxPages ='0';
                      vm.total ='O';
                       vm.showNext = false;
            vm.noDataFoundInSearch = true;
          } else if (error.data.message == "No Data Found" || error.data.message == "NDF") {
            vm.noDataFound = error.data.message;
          } else {
            handleErrorResponse(error);
            $state.go("report_on_pan_aadhar.inquiry");
          }
        }
      );
    }


    // Export Options
    vm.downloadReport = function () {
      if (vm.downloadType === "excel") {
        vm.downloadExcel();
      } else if (vm.downloadType === "csv") {
        vm.downloadCSV();
      } else if (vm.downloadType === "pdf") {
        vm.downloadPDF();
      }
      vm.downloadType = "";
    };

    vm.downloadExcel = function () {
      const additionalHeaders = [
        ["Report on PAN-Aadhaar non-linkage/Invalid PAN"],
        ["Sol ID: 101 / BRANCH OF THE BANK"],
        [],]

      const tableHeader = [
        "S.No.",
        "PAN No",
        "Holder Name",
        "PAN Status",
        "Last Status Date",
      ];

      const tableData = vm.downloadFilteredRecords.map((record, index) => [
        (vm.currentPage - 1) * vm.pageSize + index + 1,
        record.panNo,
        record.holderName,
        record.panStatus,
        record.lastStatusDate,

      ]);

      const totalAmount = vm.downloadFilteredRecords.reduce((total, record) => total + parseFloat(record.currentBalance || 0), 0);

      const wsData = [...additionalHeaders, tableHeader, ...tableData];
      const ws = XLSX.utils.aoa_to_sheet(wsData);
      const wb = XLSX.utils.book_new();
      XLSX.utils.book_append_sheet(wb, ws, "Report on PAN-Aadhaar");
      XLSX.writeFile(wb, "Report on PAN-Aadhaar.xlsx");
    };

    vm.downloadCSV = function () {
      const additionalHeaders = [
        "Report on PAN-Aadhaar non-linkage/Invalid PAN",
        "Bank Name: INSPIRISY BANK LTD",
        "Sol ID: 101 / BRANCH OF THE BANK",
        "",
      ];

      const tableHeader = [
        "S.No.",
        "PAN No",
        "Holder Name",
        "PAN Status",
        "Last Status Date",
      ];

      function padString(str, width) {
        return str.toString().padEnd(width, " ");
      }


      const columnWidths = [10, 25, 25, 25, 25]; // Adjust as necessary for proper alignment

      const formattedHeader = tableHeader
        .map((header, index) => padString(header, columnWidths[index]))
        .join("");

      const tableData = vm.downloadFilteredRecords.map((record, index) =>
        [
          padString((vm.currentPage - 1) * vm.pageSize + index + 1, columnWidths[0]),
          padString(record.panNo || "", columnWidths[1]),
          padString(record.holderName || "", columnWidths[2]),
          padString(record.panStatus || "", columnWidths[3]),
          padString(record.lastStatusDate || "", columnWidths[4]),
        ].join("")
      );

      const totalAmount = vm.downloadFilteredRecords.reduce(
        (total, record) => total + parseFloat(record.currentBalance || 0),
        0
      );


      tableData.push(
        [
          padString("", columnWidths[0]),
          padString("", columnWidths[1]),
          padString("", columnWidths[2]),
          padString("", columnWidths[3]),
          padString("", columnWidths[4]),

        ].join("")
      );


      const textData = [
        ...additionalHeaders,
        formattedHeader,
        ...tableData,
      ].join("\n"); // Use newline to separate rows

      // Create and download the text file
      const textContent = "data:text/plain;charset=utf-8," + encodeURIComponent(textData);
      const link = document.createElement("a");
      link.setAttribute("href", textContent);
      link.setAttribute("download", "Report On Pan-Aadhaar.txt");
      document.body.appendChild(link);
      link.click();
      document.body.removeChild(link);
    };

    vm.downloadPDF = function () {
      // Load jsPDF from the global namespace (for CDN usage)
      const { jsPDF } = window.jspdf;
      const doc = new jsPDF();

      // Add title and branch details
      doc.setFontSize(14);
      doc.text("Report on PAN-Aadhaar non-linkage/Invalid PAN", 14, 10);
      doc.text("Sol ID: 101 / BRANCH OF THE BANK", 14, 20);

      // Table headers
      const tableHeader = [
        ["S.No.", "PAN No", "Holder Name", "PAN Status", "Last Status Date"]
      ];

      //Table data
      const tableData = vm.downloadFilteredRecords.map((record, index) => [
        (vm.currentPage - 1) * vm.pageSize + index + 1,
        record.panNo,
        record.holderName,
        record.panStatus,
        record.lastStatusDate
      ]);

      //Use autoTable to add the table
      doc.autoTable({
        startY: 30, // Start position after headers
        head: tableHeader,
        body: tableData,
        theme: "grid",
        styles: { fontSize: 10, cellPadding: 3 }
      });

      //Save the PDF
      doc.save("Report_on_PAN-Aadhaar.pdf");
    };

    vm.getDownload = getDownload;
    function getDownload() {
      vm.loader = true;
      var requestData = {
        flag: "D",
        financialYear: vm.financialYear,
        pageNo: '1',
        pageSize: vm.total,
          downloadType :vm.downloadType,
      };
      ReportOnPanAadharService.getDetailsDownload(requestData).then(
        function (response) {
          vm.loader = false;
          vm.downloadFilteredRecords = response.data.result.list;

          if (vm.downloadType === "excel") {
//            vm.downloadExcel();
            if (response.data.result.fileName) {
                 vm.fileData = response.data.result.fileData;
                 vm.fileName = response.data.result.fileName;
                 saveAs(getBlobFromBase64String(vm.fileData), vm.fileName)
            }
          } else if (vm.downloadType === "csv") {
            vm.downloadCSV();
          }
          //       else if (vm.selectedOption == "pdf") {
          //        vm.downloadPDF();
          //      }
          vm.downloadType = "";
        }, function (error) {
          vm.loader = false;
          if ((error.data.message == "No Data Found") || (error.data.message == "NDF")) {
            vm.noDataFound = error.data.message;
          } else {
            handleErrorResponse(error);
            $state.go("report_on_pan_aadhar.inquiry");
          }
        });
    }
  }
})();
