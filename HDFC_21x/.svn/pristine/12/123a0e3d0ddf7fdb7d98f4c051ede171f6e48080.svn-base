(function () {
  "use strict";

  angular.module("gbmUltraApp").controller("AIRController", AIRController);
  AIRController.$inject = ["$scope", "$filter", "$state", "$stateParams", "$location", "$rootScope", "AIRService", "commonService"];

  function AIRController($scope, $filter, $state, $stateParams, $location, $rootScope, AIRService, commonService) {
    var vm = this;

    vm.getSchemeName = function () {
      vm.loader = true;
      vm.modalSearch = '';
      var requestData = {
        flag: "S",
      };
      AIRService.getDetails(requestData).then(
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

    vm.getFinancialYear = function () {
      vm.loader = true;
      var requestData = {
        flag: "FY",
      };
      AIRService.getDetails(requestData).then(
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

    vm.getDetails = function () {
      vm.loader = true;

      var requestData = {
        schemeCode: vm.schemeCode,
        financialYear: vm.financialYear,
        flag: "T",
      };

      AIRService.getDetails(requestData).then(
        function (response) {
          vm.loader = false;
          vm.model = response.data.result.list;
          vm.totalPages = Math.ceil(vm.model.length / vm.pageSize);
          vm.pageDetails = response.data.result.list;
          AIRService.getReferenceData({ referenceId: "HOLDER_TYPE" }).then(function (holderTypeResult) {
            var holderTypeDesc = {};
            holderTypeResult.data.result.forEach(function (item) {
              holderTypeDesc[item.SUBCODE] = item.DESCRIPTION;
            });

            AIRService.getReferenceData({ referenceId: "STATES OF INDIA" }).then(function (stateRes) {
              var stateMap = {};
              stateRes.data.result.forEach(function (item) {
                stateMap[item.SUBCODE] = item.DESCRIPTION;
              });

              vm.filteredRecords = vm.model.map(function (record) {
                return {
                  ...record,
                  personType: holderTypeDesc[record.personType] || "",
                  state: stateMap[record.state] || "",
                };
              });

               vm.model = vm.model.map(function (record) {
                              return {
                                ...record,
                                personType: holderTypeDesc[record.personType] || "",
                                state: stateMap[record.state] || "",
                              };
                            });

              vm.updatePagination();
            });
          });
        },
        function (error) {
          vm.loader = false;
          if (error.data.message == "No Data Found" || error.data.message == "NDF") {
            vm.noDataFound = error.data.message;
          } else {
            handleErrorResponse(error);
            $state.go("AIR.inquiry_details");
          }
        }
      );
    };

    // Pagination integration
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
      vm.getDetails();
    };

    vm.goNextPage = function () {
      vm.pageNo = parseInt(vm.pageNo) + 1;
      vm.getDetails();
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

    if ($state.current.name == "AIR.inquiry_details") {
      vm.schemeCode = $stateParams.obj.schemeCode;
      vm.schemeName = $stateParams.obj.schemeName;
      vm.financialYear = $stateParams.obj.financialYear;
      vm.getDetails();
    }

    vm.totalPrincipalAmount = function () {
      return vm.filteredRecords.reduce((sum, record) => sum + (record.principalAmount ? parseFloat(record.principalAmount) : 0), 0);
    };
    vm.totalGrossAmount = function () {
      return vm.filteredRecords.reduce((sum, record) => sum + (record.grossAmount ? parseFloat(record.grossAmount) : 0), 0);
    };

    // Downloading the report
    vm.reports = {
      AIR: {
        filename: "AIR",
        tableHeaders: [
          { title: "Report Serial Number", width: 20, type: "numeric" },
          { title: "Original Report Serial Number", width: 20, type: "numeric" },
          { title: "Person Name", width: 20, type: "string" },
          { title: "Person Type", width: 20, type: "string" },
          { title: "Customer Identity", width: 20, type: "string" },
          { title: "Gender", width: 20, type: "string" },
          { title: "Father’s Name", width: 20, type: "string" },
          { title: "PAN", width: 20, type: "string" },
          { title: "Aadhaar Number", width: 20, type: "string" },
          { title: "Form 60 Acknowledgment", width: 20, type: "string" },
          { title: "Identification Type", width: 20, type: "string" },
          { title: "Identification Number", width: 20, type: "string" },
          { title: "Date of birth/Incorporation", width: 20, type: "string" },
          { title: "Nationality/Country of Incorporation", width: 20, type: "string" },
          { title: "Address Type", width: 20, type: "string" },
          { title: "Fix Address", width: 20, type: "string" },
          { title: "City/Town", width: 20, type: "string" },
          { title: "Postcode", width: 20, type: "string" },
          { title: "State", width: 20, type: "string" },
          { title: "Country Code", width: 20, type: "string" },
          { title: "Business or occupation", width: 20, type: "string" },
          { title: "Primary STD Code", width: 20, type: "string" },
          { title: "Primary Phone No", width: 20, type: "string" },
          { title: "Primary Mobile No", width: 20, type: "string" },
          { title: "Secondary STD Code", width: 20, type: "string" },
          { title: "Secondary Phone No", width: 20, type: "string" },
          { title: "Secondary Mobile No", width: 20, type: "string" },
          { title: "Email", width: 20, type: "string" },
          { title: "Remarks", width: 20, type: "string" },
          { title: "Product Type", width: 20, type: "string" },
          { title: "Aggregate gross amount received from the person", width: 20, type: "numeric" },
          { title: "Aggregate gross amount received from the person in cash", width: 20, type: "numeric" },
          { title: "Aggregate gross amount paid to the person", width: 20, type: "numeric" },
          { title: "Transaction Summary Remarks", width: 20, type: "string" },
          { title: "Product Identifier", width: 20, type: "string" },
          { title: "Last Date of Transaction", width: 20, type: "string" },
          { title: "Amount Received From Person", width: 20, type: "numeric" },
          { title: "Cash Amount Received From Person", width: 20, type: "numeric" },
          { title: "Amount Paid To Person", width: 20, type: "numeric" },
          { title: "Related Account Number", width: 20, type: "string" },
          { title: "Related Institute Name", width: 20, type: "string" },
          { title: "Related Institute Ref Number", width: 20, type: "string" },
          { title: "Financial Transaction Details Remarks", width: 20, type: "string" },
        ],
        get tableData() {
          return (vm.filteredRecords || []).map((record, index) => [
            (index + 1).toString(),
            record.originalReportSerialNumber || "",
            record.personName || "",
            record.personType || "",
            record.customerIdentity || "",
            record.gender || "",
            record.fatherName || "",
            record.panNumber || "",
            record.aadhaarNumber || "",
            record.form60Acknowledgment || "",
            record.identificationType || "",
            record.identificationNumber || "",
            record.dateOfBirth || "",
            record.nationality || "",
            record.addressType || "",
            record.fixAddress || "",
            record.city || "",
            record.postcode || "",
            record.state || "",
            record.countryCode || "",
            record.business || "",
            record.primarySTDCode || "",
            record.primaryPhoneNo || "",
            record.primaryMobileNo || "",
            record.secondarySTDCode || "",
            record.secondaryPhoneNo || "",
            record.secondaryMobileNo || "",
            record.email || "",
            record.remarks || "",
            record.productType || "",
            record.grossAmountReceivedPerson || "",
            record.grossAmountReceivedPersonInCash || "",
            record.grossAmountPaidToPerson || "",
            record.transactionSummaryRemarks || "",
            record.productIdentifier || "",
            record.lastDateOfTransaction || "",
            record.amountReceivedFromPerson || "",
            record.cashAmountReceivedFromPerson || "",
            record.amountPaidToPerson || "",
            record.relatedAccountNumber || "",
            record.relatedInstituteName || "",
            record.relatedInstituteRefNumber || "",
            record.financialTransactionDtlsRemarks || "",
          ]);
        },
      },
    };

    vm.downloadReport = function () {
      const currentReport = vm.reports["AIR"];
      if (vm.downloadType === "excel") {
        vm.downloadExcel(currentReport);
      } else if (vm.downloadType === "csv") {
        vm.downloadCSV(currentReport);
      }
      vm.downloadType = "";
    };

    vm.downloadCSV = function (currentReport) {
      const { filename, tableHeaders, tableData } = currentReport;

      const columnWidths = tableHeaders.map((header, colIndex) => {
        const headerLength = header.title.length;
        const maxDataLength = tableData.reduce((max, row) => {
          const val = row[colIndex] != null ? row[colIndex].toString() : "";
          return Math.max(max, val.length);
        }, 0);
        return Math.max(headerLength, maxDataLength, header.width || 15);
      });

      const formatRow = (values) =>
        values
          .map((value, i) => {
            const colWidth = columnWidths[i];
            const isNumeric = tableHeaders[i]?.type === "numeric";
            const val = value != null ? value.toString() : "";
            return isNumeric ? val.padStart(colWidth, " ") : val.padEnd(colWidth, " ");
          })
          .join(" | ");

      const separatorLine = "-".repeat(formatRow(tableHeaders.map(() => "-")).length);

      const headerText = [
        "Annual Information reporting of customers who have invested in RBI bonds for amount greater than equal to 10 lacs at PAN level for the FY",
        "",
        "Format of reporting",
        "",
        separatorLine,
        formatRow(tableHeaders.map((header) => header.title)),
        separatorLine,
      ];

      const formattedData = tableData.map((row) => formatRow(row));

      const textData = [...headerText, ...formattedData, separatorLine];
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

      const excelData = [["Annual Information reporting of customers who have invested in RBI bonds for amount greater than equal to 10 lacs at PAN level for the FY"], [""], ["Format of reporting"], [""], tableHeaders.map((header) => header.title)];

      tableData.forEach((record) => {
        excelData.push(record);
      });

      const ws = XLSX.utils.aoa_to_sheet(excelData);

      ws["!merges"] = [
        { s: { r: 0, c: 0 }, e: { r: 0, c: 10 } },
        { s: { r: 2, c: 0 }, e: { r: 2, c: 10 } },
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
      XLSX.utils.book_append_sheet(wb, ws, "AIR");
      XLSX.writeFile(wb, filename + ".xlsx");
    };

    vm.maxValue = function () {
      return Math.min(vm.currentPage * vm.pageSize, vm.filteredRecords.length);
    };
  }
})();
