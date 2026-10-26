(function () {
  "use strict";

  angular.module("gbmUltraApp").controller("InterestScrollReportController", InterestScrollReportController);
  InterestScrollReportController.$inject = ["$scope", "$filter", "$state", "$stateParams", "$location", "$rootScope", "InterestScrollReportService", "commonService"];

  function InterestScrollReportController($scope, $filter, $state, $stateParams, $location, $rootScope, InterestScrollReportService, commonService) {
    var vm = this;
//    vm.formatIndianCurrency = formatIndianCurrency;

//vm.formatIndianCurrency = function(amount) {
//    if (amount === undefined || amount === null) {
//        return '';
//    }
//
//    const numStr = amount.toString();
//    const lastThree = numStr.substring(numStr.length - 3);
//    const otherNumbers = numStr.substring(0, numStr.length - 3);
//    const result = otherNumbers.replace(/\B(?=(\d{2})+(?!\d))/g, ",") + (otherNumbers ? "," : "") + lastThree;
//    return result;
//};




vm.formatIndianCurrency = function(amount) {
             if (amount === undefined) {
                 return null;
             }

                    return new Intl.NumberFormat('en-IN', {
                                                                minimumFractionDigits: 2,
                                                                maximumFractionDigits: 2
                                                            }).format(amount);
                }

 vm.formatDate = function (tempDate) {
    if (!tempDate) return '';

    // Handle dd/MM/yyyy format
    if (typeof tempDate === 'string' && tempDate.includes('/')) {
        const parts = tempDate.split('/');
        if (parts.length === 3) {
            const day = parseInt(parts[0], 10);
            const month = parseInt(parts[1], 10) - 1; // JavaScript months are 0-based
            const year = parseInt(parts[2], 10);

            const validDate = new Date(year, month, day);

            // Check if the date is valid
            if (!isNaN(validDate.getTime())) {
                return $filter('date')(validDate, 'dd-MMM-yyyy');
            }
        }
    }

    // If it's already a valid date string or timestamp, process it
    const parsedDate = new Date(tempDate);
    if (!isNaN(parsedDate.getTime())) {
        return $filter('date')(parsedDate, 'dd-MMM-yyyy');
    }

    return ''; // Return empty if invalid date
};


                                  vm.textableDatePicker = textableDatePicker;
                                  vm.autoslashing = autoslashing;
                                  vm.textableDatePickerTo = textableDatePickerTo;

               function textableDatePickerTo(id, valueDate,valueDateFrom) {
                                                   if (valueDate != null && valueDateFrom != null) {

                                                         let lastDayOfMonth = new Date(valueDateFrom.getFullYear(), valueDateFrom.getMonth() + 1, 0);

                                                         // Validation
                                                         if (valueDate < valueDateFrom) {

                                                             Swal.fire("warning","To Date should be greater than or equal to From Date", "warning");
                                                             vm.toDate="";
                                                             return;
                                                         }

//                                                         if (valueDate > lastDayOfMonth) {
//
//                                                             Swal.fire("warning","To Date should not exceed month end of From Date", "warning");
//                                                              vm.toDate="";
//                                                             return;
//                                                         }


                                                     }
                                                      commonService.textableDatePicker(id, valueDate);
                                                    }


                                 function textableDatePicker(id, valueDate) {
                                   commonService.textableDatePicker(id, valueDate);
                                 }
                                 function autoslashing(id) {
                                   commonService.autoslashing(id);
                                 }



    vm.getSchemeName = function () {
      vm.loader = true;
      var requestData = {
        schemeCode: "",
        flag: "S",
      };
      InterestScrollReportService.getDetails(requestData).then(
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


    // Convert to Date object (if fromDate is string)
           var from = new Date(fromDate);

           // Last day of the same month as fromDate
//           var lastDayOfMonth = new Date(
//               from.getFullYear(),
//               from.getMonth() + 1,
//               0
//           );

           vm.toDateOptions = {
               minDate: from,
               maxDate: new Date(),
               showWeeks: false
           };


    };

    vm.getDetails = function () {
      vm.loader = true;

      var requestData = {
        schemeCode: vm.schemeCode,
        fromDate: $filter("date")(vm.fromDate, "dd-MMM-yyyy"),
        toDate: $filter("date")(vm.toDate, "dd-MMM-yyyy"),
        flag: "D",
      };

      InterestScrollReportService.getDetails(requestData).then(
        function (response) {
          vm.loader = false;
          vm.model = response.data.result.list;
          vm.filteredRecords = response.data.result.list;
          vm.totalPages = Math.ceil(vm.model.length / vm.pageSize);
          vm.pageDetails = response.data.result.list;
//          var holdingReferenceId = { referenceId: "MODE_OF_PAYMENT" };
//          InterestScrollReportService.getReferenceData(holdingReferenceId).then(function (holdingMappingResponse) {
//            var holdingMappings = holdingMappingResponse.data.result; // Response for mode of payment
//
//            var paymentMap = {};
//            holdingMappings.forEach(function (item) {
//              paymentMap[item.SUBCODE] = item.DESCRIPTION;
//            });
//
//            vm.filteredRecords = vm.filteredRecords.map((record) => ({
//              ...record,
//              amountPaid: paymentMap[record.amountPaid] || "Unknown",
//            }));
//          });

          vm.updatePagination();
        },
        function (error) {
          vm.loader = false;
          if (error.data.message == "No Data Found" || error.data.message == "NDF") {
            vm.noDataFound = error.data.message;
          } else {
            handleErrorResponse(error);
            $state.go("Interest_Scroll_report_5106.inquiry_details");
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

    if ($state.current.name == "Interest_Scroll_report_5106.inquiry_details") {
      vm.schemeCode = $stateParams.obj.schemeCode;
      vm.fromDate = $stateParams.obj.fromDate;
      vm.toDate = $stateParams.obj.toDate;
      vm.schemeName = $stateParams.obj.schemeName;
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
      InterestScrollReport5106: {
        filename: "Interest Scrollreport 5106 Mar 24 - Appendix XI",
        tableHeaders: [
          { title: "Serial No", width: 10, type: "numeric" },
          { title: "Bond Ledger Account Number", width: 20, type: "string" },
          { title: "Principal Amount", width: 20, type: "numeric" },
          { title: "Due date of Repayment", width: 35, type: "string" },
          { title: "Gross Amount of Interest Paid", width: 20, type: "numeric" },
          { title: "Interest paid for last broken Period", width: 20, type: "numeric" },
          { title: "Interest paid for the first Broken period", width: 30, type: "numeric" },
          { title: "Credited to Bank Account No.  Cheque / Payment Order No", width: 30, type: "string" },
          { title: "Amount paid in case of Cheque / Payment Order", width: 20, type: "string" },
          { title: "Remarks (C/NC)", width: 20, type: "numeric" },
          { title: "Initials", width: 20, type: "string" },
        ],
        get tableData() {
          return (vm.filteredRecords || []).map((record, index) => [
            (index + 1).toString(),
            record.blaNumber || "",
            vm.formatIndianCurrency(record.principalAmount || ""),
            vm.formatDate(record.dueDate || ""),
            vm.formatIndianCurrency(record.grossAmount || ""),
            record.interestPaidForFirstBrokenPeriod || "",
            record.interestPaidForLastBrokenPeriod || "",
            record.creditedToBankAccount || "",
            record.amountPaid || "",
            record.remarks || "",
            record.initials || "",
          ]);
        },
      },

    };


    vm.downloadReport = function () {
      const currentReport = vm.reports["InterestScrollReport5106"];
      if (vm.downloadType === "C" || vm.downloadType === "NC" ) {
        vm.downloadExcel(currentReport);
      } else if (vm.downloadType === "csv") {
        vm.downloadCSV(currentReport);
      }
      vm.downloadType = "";
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
        "For Principal and Interest Non Cumulative cases paid on maturity date and Cumulative Cases paid after maturity date".padStart(100, " "),
        "",
        "Appendix XI",
        "",
        `Name of the Bank:         HDFC Bank Ltd`,
        `Scheme Name:              ${vm.schemeName}`,
        `Name of Branch & Code No: Mumbai, 5106`,
        `Name of Month:            Mar-24`,
        "",
        "Monthly Interest Payment Scroll",
        "Relief / Saving Bond",
        "",
        "-".repeat(formatRow(tableHeaders.map((header) => "-")).length),
        formatRow(tableHeaders.map((header) => header.title)),
        "-".repeat(formatRow(tableHeaders.map((header) => "-")).length),
      ];

      const formattedData = tableData.map((row) => formatRow(row));

      const totalRow = formatRow(
        tableHeaders.map((col, i) => {
          if (i === 1) return "Total";
          if (i === 2) return vm.formatIndianCurrency(vm.totalPrincipalAmount()).toString();
          if (i === 4) return vm.formatIndianCurrency(vm.totalGrossAmount()).toString();
          return "";
        })
      );

      const textData = [...headerText, ...formattedData, separatorLine, totalRow, separatorLine];

      const textContent = "data:text/plain;charset=utf-8," + encodeURIComponent(textData.join("\n"));
      const link = document.createElement("a");
      link.setAttribute("href", textContent);
      link.setAttribute("download", filename + ".txt");
      document.body.appendChild(link);
      link.click();
      document.body.removeChild(link);
    };

    var toDate = moment(vm.toDate).format("MMM-YYYY");
    vm.downloadExcel = function (currentReport) {
      const { filename, tableHeaders } = currentReport;

      const isCumulative = vm.downloadType === "C";
      const headerText = isCumulative
        ? "For Principal and Interest on Cumulative cases paid on maturity date"
        : "For Principal and Interest Non Cumulative cases paid on maturity date and Cumulative Cases paid after maturity date";

      const outputFilename =
        "Interest_Scrollreport_5106_"+moment(vm.toDate).format("MMM_YYYY")+"_Appendix_XI" +
        (isCumulative ? " - Cumulative" : " - Non Cumulative");

      const excelData = [
        [headerText],
        [""],
        ["Appendix XI"],
        [""],
        ["Name of the Bank:", "HDFC Bank Ltd", "", "", "", "", "", "", "", "", ""],
        ["Scheme Name:", vm.schemeName, "", "", "", "", "", "", "", "", ""],
        ["Name of Branch & Code No:", "Mumbai, 5106", "", "", "", "", "", "", "", "", ""],
        ["Name of Month:", toDate, "", "", "", "", "", "", "", "", ""],
        [""],
        ["Monthly Interest Payment Scroll"],
        ["Relief / Saving Bond"],
        [""],

        tableHeaders.map((header) => header.title),
      ];

      // Build rows after filtering by remarks so Serial No starts from 1 for both C and NC.
      const filteredRecordsForDownload =
        isCumulative
          ? (vm.filteredRecords || []).filter((r) => r.remarks === "C")
          : (vm.filteredRecords || []).filter((r) => r.remarks !== "C");

      filteredRecordsForDownload.forEach((record, index) => {
        excelData.push([
          (index + 1).toString(),
          record.blaNumber || "",
          vm.formatIndianCurrency(record.principalAmount || ""),
          vm.formatDate(record.dueDate || ""),
          vm.formatIndianCurrency(record.grossAmount || ""),
          record.interestPaidForFirstBrokenPeriod || "",
          record.interestPaidForLastBrokenPeriod || "",
          record.creditedToBankAccount || "",
          record.amountPaid || "",
          record.remarks || "",
          record.initials || "",
        ]);
      });
      const totalRow = ["", "Total", vm.formatIndianCurrency(vm.filteredRecords.reduce((sum, record) => {

                                                                 const condition = vm.downloadType === 'C'
                                                                     ? record.remarks === 'C'
                                                                     : record.remarks !== 'C';

                                                                 return sum + (
                                                                     condition && record.principalAmount
                                                                         ? parseFloat(record.principalAmount)
                                                                         : 0
                                                                 );

                                                             }, 0)), "", vm.formatIndianCurrency(vm.filteredRecords.reduce((sum, record) => {

                                                                                                     const condition = vm.downloadType === 'C'
                                                                                                         ? record.remarks === 'C'
                                                                                                         : record.remarks !== 'C';

                                                                                                     return sum + (
                                                                                                         condition && record.grossAmount
                                                                                                             ? parseFloat(record.grossAmount)
                                                                                                             : 0
                                                                                                     );

                                                                                                 }, 0)), "", "", "", "", "", ""];
      excelData.push(totalRow);

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

      // Apply consistent font + borders to the whole sheet range.
      // Note: styling support depends on the XLSX build used in this project.
      const colCount = tableHeaders.length; // 0..10
      const rowCount = excelData.length; // includes header rows + data rows + total row

      // Expand columns based on content length (Excel export can't do true "autofit").
      // We compute widths only for the actual table area: header + data rows + total row.
      const safeString = (v) => (v === null || v === undefined ? "" : String(v));
      // Include info/header label rows too (Name of the Bank / Scheme Name / Month)
      // so those don't wrap.
      const tableHeaderRowIndexForWidth = 4;
      const totalRowIndexForWidth = rowCount - 1;
      if (rowCount > tableHeaderRowIndexForWidth) {
        const widthFromLengths = (maxLen, colIndex) => {
          // wch is roughly character width for the default font.
          // Keep widths reasonable to avoid extreme columns.
          const wch = maxLen + 3;
          // Column A contains long "Name of ..." labels; give it enough width to avoid truncation.
          // Other columns should remain capped to prevent giant headers.
          const colCap = colIndex === 0 ? 32 : 45;
          return Math.max(8, Math.min(colCap, wch));
        };

        const colWidths = new Array(colCount).fill(0).map((_, c) => {
          let maxLen = 0;
          // Use label rows only for column A, and table-only for other columns.
          const startRow = c === 0 ? 4 : tableHeaderRowIndexForWidth;
          for (let r = startRow; r <= totalRowIndexForWidth; r++) {
            const value = excelData[r] && excelData[r][c] !== undefined ? excelData[r][c] : "";
            maxLen = Math.max(maxLen, safeString(value).length);
          }
          return widthFromLengths(maxLen, c);
        });

        ws["!cols"] = colWidths.map((wch) => ({ wch }));
      }

      // SheetJS styling expects ARGB hex (e.g. FF000000) for colors.
      const thinBlack = { style: "thin", color: { rgb: "FF000000" } };
      const borderStyle = {
        top: thinBlack,
        bottom: thinBlack,
        left: thinBlack,
        right: thinBlack,
      };

      const defaultFont = { name: "Times New Roman", sz: 10, color: { rgb: "FF000000" } };
      // Prevent wrapping for all non-header cells; long text should remain on one line.
      const defaultAlignment = { horizontal: "center", vertical: "center", wrapText: false };

      const ensureCell = (r, c) => {
        const addr = XLSX.utils.encode_cell({ r, c });
        if (!ws[addr]) ws[addr] = { t: "s", v: "" }; // required to render borders for empty cells
        return ws[addr];
      };

      // Base styling
      for (let r = 0; r < rowCount; r++) {
        for (let c = 0; c < colCount; c++) {
          const cell = ensureCell(r, c);
          cell.s = {
            font: defaultFont,
            alignment: defaultAlignment,
            border: borderStyle,
          };
        }
      }

      // Title / section headers
      const titleRows = [0, 2, 9, 10];
      titleRows.forEach((r) => {
        for (let c = 0; c < colCount; c++) {
          const cell = ensureCell(r, c);
          cell.s.font = { ...defaultFont, bold: true, sz: r === 0 ? 12 : 11 };
          // Request: main header ("For Principal and Interest...") should be left aligned.
          cell.s.alignment = { horizontal: r === 0 ? "left" : "center", vertical: "center", wrapText: true };

          // Shade the secondary section headers (as per screenshot).
          // Excel styles require ARGB colors.
          if (r === 2 || r === 9 || r === 10) {
            cell.s.fill = {
              patternType: "solid",
              fgColor: { rgb: "FFE6E6E6" },
              bgColor: { rgb: "FFE6E6E6" },
            };
          }
        }
      });

      // Ensure merged header text (A1) is bold as requested.
      // (Some XLSX builds only render style from the top-left cell of a merged range.)
      // Also set it for the entire merged width (A1:K1) to be safe.
      for (let c = 0; c <= 10; c++) {
        const cell = ensureCell(0, c);
        cell.s.font = { ...defaultFont, bold: true, sz: 12 };
        cell.s.alignment = { horizontal: "left", vertical: "center", wrapText: true };
      }

      // Info rows: keep label column bold + left aligned, and value part left aligned.
      [4, 5, 6, 7].forEach((r) => {
        for (let c = 0; c < colCount; c++) {
          const cell = ensureCell(r, c);
          if (c === 0) {
            cell.s.font = { ...defaultFont, bold: true };
            cell.s.alignment = { horizontal: "left", vertical: "center", wrapText: false };
          } else if (c >= 1) {
            cell.s.alignment = { horizontal: "left", vertical: "center", wrapText: false };
          }
        }
      });

      // Table header row
      const tableHeaderRowIndex = 12;
      if (rowCount > tableHeaderRowIndex) {
        for (let c = 0; c < colCount; c++) {
          const cell = ensureCell(tableHeaderRowIndex, c);
          cell.s.font = { ...defaultFont, bold: true, sz: 9 };
          cell.s.alignment = { horizontal: "center", vertical: "center", wrapText: false };
          cell.s.fill = {
            patternType: "solid",
            fgColor: { rgb: "FFE6E6E6" },
            bgColor: { rgb: "FFE6E6E6" },
          };
        }
      }

      // Total row
      const totalRowIndex = rowCount - 1;

      // Data rows: align based on column type and keep single-line content.
      for (let r = tableHeaderRowIndex + 1; r < totalRowIndex; r++) {
        for (let c = 0; c < colCount; c++) {
          const cell = ensureCell(r, c);
          const isNumeric = tableHeaders[c].type === "numeric";

          // Excel-like alignment similar to your screenshot.
          let horizontal = "left";
          if (c === 0) horizontal = "center"; // S.No
          else if (c === 3) horizontal = "center"; // Due date
          else if (c === 9) horizontal = "center"; // Remarks (C/NC)
          else if (c === 10) horizontal = "center"; // Initials
          else if (c === 1) horizontal = "left"; // Account number
          else horizontal = isNumeric ? "right" : "left";

          cell.s.font = { ...defaultFont, bold: false };
          cell.s.alignment = { horizontal, vertical: "center", wrapText: false };
        }
      }

      for (let c = 0; c < colCount; c++) {
        const cell = ensureCell(totalRowIndex, c);
        const boldInTotal = c === 1 || c === 2 || c === 4;
        cell.s.font = { ...defaultFont, bold: boldInTotal };

        let horizontal = "center";
        if (c === 2 || c === 4) horizontal = "right"; // totals
        else if (c === 1) horizontal = "center"; // "Total" label

        cell.s.alignment = { horizontal, vertical: "center", wrapText: false };
      }

      const wb = XLSX.utils.book_new();
      XLSX.utils.book_append_sheet(wb, ws, "Interest Scroll");
      XLSX.writeFile(wb, outputFilename + ".xlsx");
    };

    vm.maxValue = function () {
      return Math.min(vm.currentPage * vm.pageSize, vm.filteredRecords.length);
    };
  }
})();
