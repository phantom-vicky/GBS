(function () {
  "use strict";

  angular.module("gbmUltraApp").controller("sftController", sftController);
  sftController.$inject = ["$scope", "$filter", "$state", "$stateParams", "$location", "$rootScope", "sftService", "commonService"];

  function sftController($scope, $filter, $state, $stateParams, $location, $rootScope, sftService, commonService) {
    var vm = this;
    vm.getDetail=getDetail;

    // Pagination integration
    vm.showNext = false;
    vm.showPrevious = false;
    $scope.sortReverse = false;
    $scope.sort = function (key) {
      $scope.sortKey = key;
      $scope.sortReverse = $scope.sortKey == key ? !$scope.sortReverse : $scope.sortReverse;
      $scope.sortReverse ? (vm.sortOrder = "DESC") : (vm.sortOrder = "ASC");
      vm.sortField = key;
      vm.getDetail();
    };
    vm.pageNo = 1;
    vm.filteredRecords = [];
    vm.pageSizeOptions = [5, 10, 25, 50, 100];
    vm.pageSize = vm.pageSizeOptions[0];
    vm.currentPage = 1;
    vm.maxPagesToShow = 5;
    vm.searchText = "";
    vm.loading = true;
    vm.showExportOptions = false;

    vm.goPreviousPage = function () {
      vm.pageNo = parseInt(vm.pageNo) - 1;
      vm.getDetail();
    };

    vm.goNextPage = function () {
      vm.pageNo = parseInt(vm.pageNo) + 1;
      vm.getDetail();
    };

    vm.paginationControls = function () {
      vm.pages = [];
      vm.maxPages = parseInt(vm.pageDetails.maxPages);
      for (var i = 1; i <= parseInt(vm.pageDetails.maxPages); i++) {
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
      vm.filteredRecords = vm.model.filter((record) => {
        return Object.values(record).some((value) => value !== null && value !== undefined && String(value).toLowerCase().includes(searchText));
      });
      vm.totalPages = Math.ceil(vm.filteredRecords.length / vm.pageSize);
      vm.setPage(1);
    };

    vm.getSchemeName = function () {
      vm.loader = true;
      var requestData = {
        schemeCode: "",
        flag: "S",
      };
      sftService.getDetails(requestData).then(
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

  vm.getFinYear = function () {
        vm.loader = true;
        var requestData = {
          flag: "FY",
        };
        sftService.getDetails(requestData).then(
          function (response) {
            vm.loader = false;
            vm.finYearList= response.data.result.list;
          },
          function (error) {
            handleErrorResponse(error);
            vm.loader = false;
          }
        );
      };

    function getDetail() {
      vm.loader = true;

      var requestData = {
        schemeCode: vm.schemeCode,
        financialYear : vm.financialYear,
        flag: "D",
      };

      sftService.getDetails(requestData).then(
        function (response) {
          vm.loader = false;
          vm.model = response.data.result.list;
          vm.filteredRecords = response.data.result.list;
          vm.totalPages = Math.ceil(vm.model.length / vm.pageSize);
          vm.pageDetails = response.data.result.list;
          vm.updatePagination();
        },
        function (error) {
          vm.loader = false;
          if (error.data.message == "No Data Found" || error.data.message == "NDF") {
            vm.noDataFound = error.data.message;
          } else {
            handleErrorResponse(error);
            $state.go("sft_scroll.inquiry_detail");
          }
        }
      );
    };


    if ($state.current.name == "sft_scroll.inquiry_detail") {
      vm.schemeCode = $stateParams.obj.schemeCode;
      vm.financialYear =$stateParams.obj.financialYear;
      vm.schemeName = $stateParams.obj.schemeName;
      vm.getDetail();
    }

    vm.totalPrincipalAmount = function () {
      return vm.filteredRecords.reduce((sum, record) => sum + (record.principalAmount ? parseFloat(record.principalAmount) : 0), 0);
    };
    vm.totalGrossAmount = function () {
      return vm.filteredRecords.reduce((sum, record) => sum + (record.grossAmount ? parseFloat(record.grossAmount) : 0), 0);
    };

    // Downloading the report
    vm.reports = {
      Sft: {
        filename: "SFT",
        tableHeaders: [
          { title: "S. No.", width: 10, type: "numeric" },
          { title: "Person Name", width: 30, type: "string" },
          { title: "PAN", width: 20, type: "String" },
          { title: "Aadhaar", width: 35, type: "numeric" },
          { title: "Mobile Number", width: 20, type: "numeric" },
          { title: "Email Id", width: 20, type: "numeric" },
          { title: "Account Number", width: 30, type: "numeric" },
          { title: "Type of Account", width: 30, type: "string" },
          { title: "Total Interest paid/ credited", width: 20, type: "string" },
          { title: "Bank Indicator", width: 20, type: "numeric" },
          { title: "BLA ref", width: 20, type: "string" },
          { title: "Remarks", width: 20, type: "string" },
        ],
        get tableData() {
          return (vm.filteredRecords || []).map((record, index) => [
            (index + 1).toString(),
            record.personName || "",
            record.panNo || "",
            record.aadhar || "",
            record.mobileNo || "",
            record.emailId || "",
            record.accNo || "",
            record.accType || "",
            record.totalIntPaid || "",
            record.bankIndicator || "",
            record.blaRef || "",
            record.remarks || "",
          ]);
        },
      },
    };

    vm.downloadReport = function () {
      const currentReport = vm.reports["Sft"];
      if (vm.downloadType === "excel") {
        vm.downloadExcel(currentReport);
      } else if (vm.downloadType === "csv") {
        vm.downloadCSV(currentReport);
      }
      vm.downloadType = '';
    };

    vm.downloadCSV = function (currentReport) {
      const { filename, tableHeaders, tableData } = currentReport;

      const columnWidths = tableHeaders.map((header) => Math.max(header.title.length, header.width || 15));

      const formatRow = (values) => {
        return values
          .map((value, i) => {
            const colWidth = columnWidths[i];
            const isNumeric = tableHeaders[i].type === "numeric";
            return isNumeric ? value.toString().padStart(colWidth, " ") : value.toString().padEnd(colWidth, " ");
          })
          .join(" | ");
      };
      const separatorLine = "-".repeat(formatRow(tableHeaders.map(header => "-")).length);
      const headerText = [
        "Statement of financial transactions for interest (both HY and redemption) paid during the year at BLA -transaction level".padStart(100, " "),
        "",
        "Format as below",
        "",
//        `Name of the Bank:         HDFC Bank Ltd`,
//        `Scheme Name:              ${vm.schemeName}`,
//        `FinancialYear            ${vm.financialYear}`,
//        "",
        "-".repeat(formatRow(tableHeaders.map((header) => "-")).length),
        formatRow(tableHeaders.map((header) => header.title)),
        "-".repeat(formatRow(tableHeaders.map((header) => "-")).length),
      ];

      const formattedData = tableData.map((row) => formatRow(row));

//      const totalRow = formatRow(
//        tableHeaders.map((col, i) => {
//          if (i === 1) return "Total";
//          if (i === 2) return vm.totalPrincipalAmount().toString();
//          if (i === 4) return vm.totalGrossAmount().toString();
//          return "";
//        })
//      );

      const textData = [...headerText, ...formattedData, separatorLine, separatorLine];

      const textContent = "data:text/plain;charset=utf-8," + encodeURIComponent(textData.join("\n"));
      const link = document.createElement("a");
      link.setAttribute("href", textContent);
      link.setAttribute("download", filename + ".txt");
      document.body.appendChild(link);
      link.click();
      document.body.removeChild(link);
    };

    vm.downloadExcel = function (currentReport) {
      const { filename, tableHeaders, tableData } = currentReport;

      const excelData = [
        ["Statement of financial transactions for interest (both HY and redemption) paid during the year at BLA -transaction level"],
        [""],
        ["Format as below"],
        [""],

        tableHeaders.map((header) => header.title),
      ];

      tableData.forEach((record) => {
        excelData.push(record);
      });

//      const totalRow = ["", "Total", vm.totalPrincipalAmount(), "", vm.totalGrossAmount(), "", "", "", "", "", ""];
//      excelData.push(totalRow);

      const ws = XLSX.utils.aoa_to_sheet(excelData);

      ws["!merges"] = [
        { s: { r: 0, c: 0 }, e: { r: 0, c: 10 } },
        { s: { r: 2, c: 0 }, e: { r: 2, c: 10 } },
        { s: { r: 4, c: 1 }, e: { r: 4, c: 3 } },
        { s: { r: 5, c: 1 }, e: { r: 5, c: 3 } },
        { s: { r: 6, c: 1 }, e: { r: 6, c: 3 } },
        { s: { r: 7, c: 1 }, e: { r: 7, c: 3 } },
        { s: { r: 9, c: 0 }, e: { r: 9, c: 10 } },
        { s: { r: 10, c: 0 }, e: { r: 10, c: 10 } },
      ];

      const cellStyles = {
        alignment: { horizontal: "center", vertical: "center" },
        font: { bold: true },
      };

      const mergedCells = ["A1", "A3", "A10", "A11"];
      mergedCells.forEach((cell) => {
        if (ws[cell]) {
          ws[cell].s = cellStyles;
        }
      });

      const wb = XLSX.utils.book_new();
      XLSX.utils.book_append_sheet(wb, ws, "Interest Scroll");
      XLSX.writeFile(wb, filename + ".xlsx");
    };

    vm.maxValue = function () {
      return Math.min(vm.currentPage * vm.pageSize, vm.filteredRecords.length);
    };
  }
})();
