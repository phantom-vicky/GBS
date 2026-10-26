(function () {
  "use strict";

  angular.module("gbmUltraApp").controller("estimationController", estimationController);
  estimationController.$inject = ["$scope", "$filter", "$state", "$stateParams", "$location", "$rootScope", "estimationService", "commonService"];

  function estimationController($scope, $filter, $state, $stateParams, $location, $rootScope, estimationService, commonService) {
    var vm = this;

    vm.getSchemeName = function () {
      vm.loader = true;
      var requestData = {
//        schemeCode: "",
//        flag: "S",
    referenceId : 'TAX',
      };
      estimationService.getRbiBondModuleNameDetails(requestData).then(
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


    vm.getDetail = function () {
      vm.loader = true;

      var requestData = {
//        schemeCode: vm.schemeCode,
//        fromDate: $filter("date")(vm.fromDate, "dd-MMM-yyyy"),
//        toDate: $filter("date")(vm.toDate, "dd-MMM-yyyy"),
        flag: "D",
      };

      estimationService.getDetails(requestData).then(
        function (response) {
          vm.loader = false;
          vm.model = response.data.result.list;
          vm.filteredRecords = response.data.result.list;
          vm.totalPages = Math.ceil(vm.model.length / vm.pageSize);
          vm.pageDetails = response.data.result.list;
          console.log("vm.model",vm.model);
          vm.updatePagination();
        },
        function (error) {
          vm.loader = false;
          if (error.data.message == "No Data Found" || error.data.message == "NDF") {
            vm.noDataFound = error.data.message;
          } else {
            handleErrorResponse(error);
            $state.go("estimation_of_expenses_scroll.inquiry_detail");
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

    if ($state.current.name == "estimation_of_expenses_scroll.inquiry_detail") {
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

    vm.reports = {
      estimation: {
        receipts: {
          filename: "receipts",
          title: "Budget / Revised Estimates of Receipts, Repayment of Principal and payment of interest on Relief / Savings Bonds",
          sectionLabel: "I - Receipts (New Subscriptions)",
          headers: [
            [
              { title: "S. No.", rowspan: 2 },
              { title: "Scheme", rowspan: 2 },
              { title: "Revised Estimates (2017-18)", colspan: 3, align: "center" },
              { title: "Budget Estimates (2018-19)", rowspan: 2 }
            ],
            [
              { title: "Actual Receipts (April 17 - Sep 2017)" },
              { title: "Estimated Receipts (Oct 2017 - Mar 2018)" },
              { title: "Total" }
            ]
          ],

          get tableData() {
            return (vm.filteredRecords || []).map((record, index) => [
              (index + 1).toString(),
              record.schemeName || "",
              record.actualRecipt || "",
              record.estimatedRecipt || "",
              record.total || "",
              record.budgetEstimates || "",
            ]);
          },
          notes: [
            "1. The estimated receipts for the period Oct 17 to March 18 are arrived based on the monthly average of actual receipts up to Sep 17.",
            "2. The Budgeted estimates for the period 2018-19 are arrived at based on the average on the total receipts for the period 2017-18."
          ]
        },

        repayment: {
          filename: "repayment",
          sectionLabel: "II - Repayment of Principal",
//          title: "Budget / Revised Estimates of Receipts, Repayment of Principal and payment of interest on Relief / Savings Bonds",
          headers: [
            [
              { title: "S. No.", rowspan: 2 },
              { title: "Scheme", rowspan: 2 },
//              { title: "Revised Estimates (2017-18)", colspan: 3, align: "center" },
              { title: "Revised Estimates (2017-18)", colspan: 3 },
              { title: "Budget Estimates (2018-19)", rowspan: 2 }
            ],
            [
              { title: "Actual Repayments (Apr - Sep 2017)" },
              { title: "Estimated Repayments (Oct 2017 - Mar 2018)" },
              { title: "Total" }
            ]
          ],
          get tableData() {
            return (vm.filteredRecords || []).map((record, index) => [
              (index + 1).toString(),
              record.schemeName || "",
              record.actualRepayment || "",
              record.estimatedRepayment || "",
              record.total1 || "",
              record.budgetEstimates1 || "",
            ]);
          },
          notes: [
            "1. Except 8% Savings (Taxable) Bonds, 2003, all other Schemes were completely matured...",
            "2. For 8% Savings Bonds 2003, matured investments on hold are added under Estimated Repayments.",
            "3. Budget Estimates 2018-19 show actual maturity values less premature cases.",
            "4. Premature Encashment is estimated based on age and investment date."
          ]
        },

        interest: {
          filename: "interest",
          sectionLabel: "II - Repayment of Principal",
//          title: "Budget / Revised Estimates of Receipts, Repayment of Principal and payment of interest on Relief / Savings Bonds",
          headers: [
            [
              { title: "S. No.", rowspan: 2 },
              { title: "Scheme", rowspan: 2 },
              { title: "Revised Estimates (2017-18)", colspan: 3, align: "center" },
              { title: "Budget Estimates (2018-19)", rowspan: 2 }
            ],
            [
              { title: "Actual Interest Payments (Apr - Sep 2017)" },
              { title: "Estimated Interest Payments (Oct 2017 - Mar 2018)" },
              { title: "Total" }
            ]
          ],
          get tableData() {
            return (vm.filteredRecords || []).map((record, index) => [
              (index + 1).toString(),
              record.schemeName || "",
              record.actualPaymentInterest || "",
              record.estimatedPaymentInterest || "",
              record.total2 || "",
              record.budgetEstimates2 || "",
            ]);
          },
          notes: [
            "1. Except 8% Savings Bonds, 2003, all other Schemes were matured...",
            "2. For 8% Bonds 2003, outstanding interest and broken interest are included in estimates.",
            "3. Budget Estimates 2018-19 reflect actual interest values.",
            "4. Estimations based on subscriptions received till Sep 2017 and projected monthly average.",
            "5. Premature Encashment Interest is estimated using age and investment data."
          ]
        }
      }
    };

vm.downloadReport = function () {
  const allReports = vm.reports.estimation;

  if (vm.downloadType === "excel") {
    vm.downloadCombinedExcel(allReports);
  } else if (vm.downloadType === "csv") {
    vm.downloadCombinedCSV(allReports);
  }

  vm.downloadType = '';
};

   vm.downloadCombinedCSV = function (reports) {
     const allSections = [];

     // Padding helper
     function pad(str, width) {
       str = str ? str.toString() : "";
       return str.length >= width ? str : str + " ".repeat(width - str.length);
     }

     // Define fixed column widths
     const columnWidths = [6, 12, 35, 35, 20, 25]; // S.No, Scheme, Actual, Estimated, Total, Budget

Object.keys(reports).forEach((key) => {
  const report = reports[key];

  // Section Title
  if (report.title) {
    allSections.push("");
    allSections.push(`${report.title.toUpperCase()}`);
    allSections.push("");
  }

  // Section label
  if (report.sectionLabel) {
    allSections.push(report.sectionLabel.toUpperCase());
    allSections.push("");
  }

       // Add grouped column header manually if needed
       const firstHeaderRow = report.headers[0];
       const secondHeaderRow = report.headers[1];

       if (firstHeaderRow && secondHeaderRow) {
         // First header row (e.g., with colspan information)
         const headerRow = [];

         firstHeaderRow.forEach((h, i) => {
           if (h.colspan) {
             const totalSpanWidth = columnWidths.slice(i, i + h.colspan).reduce((a, b) => a + b + 3, -3); // +3 for ' | '
             const titlePadded = h.title.padStart(((totalSpanWidth + h.title.length) / 2), ' ');
             headerRow.push(titlePadded.padEnd(totalSpanWidth, ' '));
           } else {
             headerRow.push(pad(h.title, columnWidths[i] || 15));
           }
         });

         allSections.push(headerRow.join(" | "));

         // Second row (Actual, Estimated, Total)
         const secondHeader = secondHeaderRow.map((h, i) => pad(h.title, columnWidths[i + 2] || 15)); // +2 skips S.No and Scheme
         allSections.push(pad("", columnWidths[0]) + " | " + pad("", columnWidths[1]) + " | " + secondHeader.join(" | "));
       }

       // Divider
       allSections.push("-".repeat(120));

       // Table data
       const dataRows = typeof report.tableData === "function" ? report.tableData() : report.tableData;
       dataRows.forEach((row) => {
         const line = row.map((cell, i) => pad(cell, columnWidths[i] || 15)).join(" | ");
         allSections.push(line);
       });

       // Notes
       if (report.notes && report.notes.length > 0) {
         allSections.push("");
         allSections.push("Notes:");
         report.notes.forEach((note) => {
           allSections.push("- " + note);
         });
       }

       allSections.push("\n");
     });

     // Trigger download
     const textContent = "data:text/plain;charset=utf-8," + encodeURIComponent(allSections.join("\n"));
     const link = document.createElement("a");
     link.setAttribute("href", textContent);
     link.setAttribute("download", "Combined_Report.txt");
     document.body.appendChild(link);
     link.click();
     document.body.removeChild(link);
   };


 vm.downloadCombinedExcel = function(reports) {
   const wb = XLSX.utils.book_new();
   const ws_data = [];
   const merges = [];
   let currentRow = 0;

   Object.keys(reports).forEach(key => {
     const report = reports[key];
     const tableData = report.tableData || [];
     const headers = report.headers || [];

     // Calculate max columns
     let maxColumns = 0;
     tableData.forEach(row => {
       if (row.length > maxColumns) maxColumns = row.length;
     });
     if (maxColumns === 0) maxColumns = 4; // Default to 4 columns if no data

     // Add title if exists
     if (report.title) {
       ws_data.push([report.title]);
       merges.push({ s: { r: currentRow, c: 0 }, e: { r: currentRow, c: maxColumns - 1 } });
       currentRow++;
     }

     // Add section label
     if (report.sectionLabel) {
       ws_data.push([report.sectionLabel]);
       merges.push({ s: { r: currentRow, c: 0 }, e: { r: currentRow, c: maxColumns - 1 } });
       currentRow++;
     }

     // Add empty row
     ws_data.push([]);
     currentRow++;

     // Process headers
     headers.forEach((headerRow, rowIndex) => {
       const row = new Array(maxColumns).fill("");
       let colIndex = 0;

       headerRow.forEach(cell => {
         // Skip already filled cells (from rowspan)
         while (row[colIndex] !== "") {
           colIndex++;
         }

         const colspan = cell.colspan || 1;
         const rowspan = cell.rowspan || 1;

         row[colIndex] = cell.title;

         if (colspan > 1 || rowspan > 1) {
           merges.push({
             s: { r: currentRow + rowIndex, c: colIndex },
             e: { r: currentRow + rowIndex + rowspan - 1, c: colIndex + colspan - 1 }
           });
         }

         // Fill spanned columns with empty strings to maintain structure
         for (let i = 1; i < colspan; i++) {
           row[colIndex + i] = "";
         }

         colIndex += colspan;
       });

       ws_data.push(row);
     });

     currentRow += headers.length;

     // Add table data
     tableData.forEach(row => {
       const rowData = new Array(maxColumns).fill("");
       for (let i = 0; i < row.length; i++) {
         rowData[i] = row[i];
       }
       ws_data.push(rowData);
       currentRow++;
     });

     // Add empty row after section
     ws_data.push([]);
     currentRow++;
   });

   // Create worksheet
   const ws = XLSX.utils.aoa_to_sheet(ws_data);
   ws["!merges"] = merges;

   // Set column widths
   ws["!cols"] = [
     { wch: 8 },   // S.No.
     { wch: 10 },  // Number
     { wch: 15 },  // Scheme
     { wch: 15 }   // Total/Budget Estimates
   ];

   XLSX.utils.book_append_sheet(wb, ws, "Receipts_Report");
   XLSX.writeFile(wb, "Receipts_Report.xlsx");
 };

    vm.maxValue = function () {
      return Math.min(vm.currentPage * vm.pageSize, vm.filteredRecords.length);
    };
  }
})();
