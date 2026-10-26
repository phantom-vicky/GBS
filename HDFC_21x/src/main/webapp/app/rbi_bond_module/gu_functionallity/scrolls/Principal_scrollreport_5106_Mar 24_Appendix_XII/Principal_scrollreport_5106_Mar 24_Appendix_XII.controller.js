(function() {

	'use strict';

	angular.module('gbmUltraApp').controller(
			'principalScrollreportAppendixXIIController',
			principalScrollreportAppendixXIIController);

	principalScrollreportAppendixXIIController.$inject = [ '$scope', '$filter',
			'$state', '$stateParams', '$location', '$rootScope','principalScrollreportAppendixXIIService', 'commonService' ];

	function principalScrollreportAppendixXIIController($scope, $filter, $state,
			$stateParams, $location, $rootScope, principalScrollreportAppendixXIIService,
			commonService) {

		var vm = this;

//		vm.formatIndianCurrency = function(amount) {
//            if (amount === undefined || amount === null) {
//                return '';
//            }
//
//            const numStr = amount.toString();
//            const lastThree = numStr.substring(numStr.length - 3);
//            const otherNumbers = numStr.substring(0, numStr.length - 3);
//            const result = otherNumbers.replace(/\B(?=(\d{2})+(?!\d))/g, ",") + (otherNumbers ? "," : "") + lastThree;
//            return result;
//        };



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

        vm.currentPage = 1;
        vm.itemsPerPage = '5';
        vm.loginSolId = $rootScope.globals.currentUser.solId;
        vm.loginSolDesc = $rootScope.globals.currentUser.solDesc;
         vm.bankName = $rootScope.globals.currentUser.bankName;
        vm.solDate = $rootScope.globals.currentUser.systemDate;
        vm.currentDate = new Date();
        vm.currentDate = $filter('date')(vm.currentDate, 'dd/MM/yyyy');
        vm.getAppendixVIIBrokHand = getAppendixVIIBrokHand;
        vm.getModuleName=getModuleName;
        vm.dateValidation=dateValidation;


    // Pagination integration
    vm.showNext = false;
    vm.showPrevious = false;
    $scope.sortReverse = false;
    $scope.sort = function (key) {
      $scope.sortKey = key;
      $scope.sortReverse = $scope.sortKey == key ? !$scope.sortReverse : $scope.sortReverse;
      $scope.sortReverse ? (vm.sortOrder = "DESC") : (vm.sortOrder = "ASC");
      vm.sortField = key;
      vm.getAppendixVIIBrokHand();
    };
    vm.pageNo = 1;
    vm.filteredRecords = [];
    vm.pageSizeOptions = [5,10, 25, 50, 100];
    vm.pageSize = vm.pageSizeOptions[0];
    vm.currentPage = 1;
    vm.maxPagesToShow = 5;
    vm.searchText = "";
    vm.loading = true;
    vm.showExportOptions = false;

    vm.goPreviousPage = function () {
      vm.pageNo = parseInt(vm.pageNo) - 1;
      vm.getAppendixVIIBrokHand();
    };

    vm.goNextPage = function () {
      vm.pageNo = parseInt(vm.pageNo) + 1;
      vm.getAppendixVIIBrokHand();
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

        if ($state.current.name == 'Principal_scrollreport_5106_Mar_24_Appendix_XII.inquiry_details')
        {
        vm.schemeCode = $stateParams.obj.schemeCode;
        vm.schemeName = $stateParams.obj.schemeName;
        vm.fromDate = $stateParams.obj.fromDate;
        vm.toDate = $stateParams.obj.toDate;
         getAppendixVIIBrokHand();

        }



         vm.downloadReport = function () {
              console.log("The download Type ", vm.downloadType);
              if (vm.downloadType === "NC" || vm.downloadType === "C") {
                vm.downloadExcel();
              } else if (vm.downloadType === "csv") {
                vm.downloadTXT();
              }
              vm.downloadType = "";
            };


    var toDate = moment(vm.toDate).format("MMM-YYYY");


        vm.downloadExcel = function () {
            var isCumulative = vm.downloadType === 'C';
            var headerText = isCumulative
                ? 'For Principal and Interest on Cumulative cases paid on maturity date'
                : 'For Principal and Interest Non Cumulative cases paid on maturity date and Cumulative Cases paid after maturity date';
            var wb = XLSX.utils.book_new();
            var wsData = [];
            wsData.push([headerText]);
            wsData.push([]);
            // Header Information (Before Table)
            wsData.push(["Appendix XII"]);  // Title Row
            wsData.push(["Name of the Bank :", "HDFC Bank Ltd"]);
            wsData.push(["Name of the Bond Scheme :", vm.schemeName]);
            wsData.push(["Name of Branch & Code No :", "Mumbai, 5106"]);
            wsData.push(["Name of Month :", toDate]);
            wsData.push([]);  // Empty row for spacing

            // Table Titles

            wsData.push(["Repayment Scroll"]);
            wsData.push(["Relief / Saving Bond"]);
            wsData.push([]);

            //Main Headers (First Row)
            wsData.push([
                "Serial No", "Bond Ledger Account Number", "Due Date of Repayment",
                "Amount Of Repayment", "", "Credited to Bank Account No. Cheque / Payment Order No",
                "Amount paid in case of Cheque / Payment Order", "", "Remarks", "Initials"
            ]);

            //Sub-Headers (Second Row)
            wsData.push([
                "", "", "",
                "Nominal Value", "Maturity Value", "",
                "Clearing","Transfer","", ""
            ]);
            if (vm.downloadType === 'C') {

                // Data Rows (Only remarks === 'C')
                var serial = 1;
                vm.filteredRecords.forEach(function (row) {
                    if (row.remarks === 'C') {
                        wsData.push([
                            serial,
                            row.bla,
                            vm.formatDate(row.dueDateRepay),
                            vm.formatIndianCurrency(row.amountOfRepayment),
                            vm.formatIndianCurrency(row.nomineeValue),
                            row.creditAccNo,
                            "",
                            "",
                            row.remarks,
                            row.initials
                        ]);
                        serial++;
                    }
                });

            } else {

                // Data Rows (Only remarks !== 'C')
                var serial = 1;
                vm.filteredRecords.forEach(function (row) {
                    if (row.remarks !== 'C') {
                        wsData.push([
                            serial,
                            row.bla,
                            vm.formatDate(row.dueDateRepay),
                            vm.formatIndianCurrency(row.amountOfRepayment),
                            vm.formatIndianCurrency(row.nomineeValue),
                            row.creditAccNo,
                            "",
                            "",
                            row.remarks,
                            row.initials
                        ]);
                        serial++;
                    }
                });

            }

            // Footer Row
            wsData.push(["", "", "Total Rs.", vm.formatIndianCurrency(vm.filteredRecords.reduce((sum, record) => {

                                                                          const condition = vm.downloadType === 'C'
                                                                              ? record.remarks === 'C'
                                                                              : record.remarks !== 'C';

                                                                          return sum + (
                                                                              condition && record.amountOfRepayment
                                                                                  ? parseFloat(record.amountOfRepayment)
                                                                                  : 0
                                                                          );

                                                                      }, 0)), vm.formatIndianCurrency(vm.filteredRecords.reduce((sum, record) => {

                                                                                                          const condition = vm.downloadType === 'C'
                                                                                                              ? record.remarks === 'C'
                                                                                                              : record.remarks !== 'C';

                                                                                                          return sum + (
                                                                                                              condition && record.nomineeValue
                                                                                                                  ? parseFloat(record.nomineeValue)
                                                                                                                  : 0
                                                                                                          );

                                                                                                      }, 0)), "", "", "", "", ""]);

            // Create Worksheet
            var ws = XLSX.utils.aoa_to_sheet(wsData);

            // Merge Cells for Main Headers
            ws["!merges"] = [
                { s: { r: 0, c: 0 }, e: { r: 0, c: 9 } }, // Main heading
                { s: { r: 2, c: 0 }, e: { r: 2, c: 9 } }, // Appendix title
                { s: { r: 8, c: 0 }, e: { r: 8, c: 9 } }, // Repayment Scroll title
                { s: { r: 9, c: 0 }, e: { r: 9, c: 9 } }, // Relief / Saving Bond title
                { s: { r: 11, c: 3 }, e: { r: 11, c: 4 } }, // Amount Of Repayment Merge
                { s: { r: 11, c: 6 }, e: { r: 11, c: 7 } }, // Amount Paid In Case Of Cheque Merge
            ];

            // Styling (font, borders, no-wrap) + column width expansion.
            // Note: styling depends on using the style-capable SheetJS build (xlsx-js-style).
            var colCount = 10;
            var rowCount = wsData.length;

            var safeString = function (v) { return v === null || v === undefined ? "" : String(v); };
            var thinBlack = { style: "thin", color: { rgb: "FF000000" } };
            var borderStyle = { top: thinBlack, bottom: thinBlack, left: thinBlack, right: thinBlack };
            var defaultFont = { name: "Times New Roman", sz: 10, color: { rgb: "FF000000" } };
            var defaultAlignment = { horizontal: "center", vertical: "center", wrapText: false };
            var headingFill = {
                patternType: "solid",
                fgColor: { rgb: "FFE6E6E6" },
                bgColor: { rgb: "FFE6E6E6" }
            };

            // Approximate column autofit based on content length.
            var tableHeaderRowIndexForWidth = 3; // include "Name of ..." label rows
            var totalRowIndexForWidth = rowCount - 1;
            var widthFromLengths = function (maxLen, colIndex) {
                var wch = maxLen + 3;
                var colCap = colIndex === 0 ? 32 : (colIndex === 5 ? 45 : 35);
                return Math.max(8, Math.min(colCap, wch));
            };

            var colWidths = new Array(colCount).fill(0).map(function (_, c) {
                var maxLen = 0;
                for (var r = tableHeaderRowIndexForWidth; r <= totalRowIndexForWidth; r++) {
                    var value = (wsData[r] && wsData[r][c] !== undefined) ? wsData[r][c] : "";
                    maxLen = Math.max(maxLen, safeString(value).length);
                }
                return widthFromLengths(maxLen, c);
            });
            ws["!cols"] = colWidths.map(function (wch) { return { wch: wch }; });

            var ensureCell = function (r, c) {
                var addr = XLSX.utils.encode_cell({ r: r, c: c });
                if (!ws[addr]) ws[addr] = { t: "s", v: "" };
                return ws[addr];
            };

            // Base styling
            for (var r = 0; r < rowCount; r++) {
                for (var c = 0; c < colCount; c++) {
                    var cell = ensureCell(r, c);
                    cell.s = { font: defaultFont, alignment: defaultAlignment, border: borderStyle };
                }
            }

            // Titles (no wrap)
            [0, 2, 8, 9].forEach(function (r) {
                for (var c = 0; c < colCount; c++) {
                    var cell = ensureCell(r, c);
                    cell.s.font = { ...defaultFont, bold: true, sz: r === 0 ? 12 : 11 };
                    cell.s.alignment = { horizontal: r === 0 ? "left" : "center", vertical: "center", wrapText: false };
                    // Background for specific headings (Appendix XII / Repayment Scroll / Relief / Saving Bond)
                    if (r === 2 || r === 8 || r === 9) {
                        cell.s.fill = headingFill;
                    }
                }
            });

            // Table headers (main + sub)
            [11, 12].forEach(function (r) {
                for (var c = 0; c < colCount; c++) {
                    var cell = ensureCell(r, c);
                    cell.s.font = { ...defaultFont, bold: true, sz: 9 };
                    cell.s.alignment = { horizontal: "center", vertical: "center", wrapText: false };
                }
            });

            // Info rows: rows 3..6 (label in col 0, value in col 1)
            [3, 4, 5, 6].forEach(function (r) {
                for (var c = 0; c < colCount; c++) {
                    var cell = ensureCell(r, c);
                    if (c === 0) {
                        cell.s.font = { ...defaultFont, bold: true };
                        cell.s.alignment = { horizontal: "left", vertical: "center", wrapText: false };
                    } else if (c === 1) {
                        cell.s.alignment = { horizontal: "left", vertical: "center", wrapText: false };
                    }
                }
            });

            // Remove inner divider between label/value columns in info rows.
            var noneBorder = { style: "none", color: { rgb: "FF000000" } };
            [3, 4, 5, 6].forEach(function (r) {
                var labelCell = ensureCell(r, 0);
                var valueCell = ensureCell(r, 1);
                if (labelCell.s && labelCell.s.border) labelCell.s.border.right = noneBorder;
                if (valueCell.s && valueCell.s.border) valueCell.s.border.left = noneBorder;
            });

            // Data alignment (rows 13..totalRowIndex-1)
            var firstDataRowIndex = 13;
            var totalRowIndex = rowCount - 1;
            for (var r = firstDataRowIndex; r < totalRowIndex; r++) {
                for (var c = 0; c < colCount; c++) {
                    var cell = ensureCell(r, c);
                    var isNumeric = (c === 3 || c === 4);
                    var horizontal = "left";

                    if (c === 0 || c === 8 || c === 9) horizontal = "center";
                    else if (c === 2) horizontal = "center";
                    else if (isNumeric) horizontal = "right";

                    cell.s.font = { ...defaultFont, bold: false };
                    cell.s.alignment = { horizontal: horizontal, vertical: "center", wrapText: false };
                }
            }

            // Total row styling
            for (var c = 0; c < colCount; c++) {
                var cell = ensureCell(totalRowIndex, c);
                var bold = (c === 2 || c === 3 || c === 4);
                cell.s.font = { ...defaultFont, bold: bold };
                if (c === 3 || c === 4) cell.s.alignment = { horizontal: "right", vertical: "center", wrapText: false };
                else cell.s.alignment = { horizontal: "center", vertical: "center", wrapText: false };
            }

            // Create and Download Excel File
            XLSX.utils.book_append_sheet(wb, ws, "Repayment Scroll");
            XLSX.writeFile(wb, "Principal_scrollreport_5106_"+moment(vm.toDate).format("MMM_YYYY")+"_Appendix_XII " + (isCumulative ? "Cumulative" : "Non Cumulative") + ".xlsx");
        };


vm.downloadTXT = function () {
    var txtContent = "";

    // Define column widths (adjust as needed)
    var colWidths = [5, 30, 20, 20, 20, 30, 20, 20, 15, 10];

    // Function to format a value to a fixed width
    function formatValue(value, width) {
        value = value ? value.toString() : ""; // Ensure it's a string
        return value.padEnd(width, " "); // Add spaces to align columns
    }

      // Add Additional Headings
        txtContent += "============================================================\n";
        txtContent += "  For Principal and Interest Non-Cumulative cases paid on\n";
        txtContent += "  maturity date and Cumulative Cases paid after maturity date\n";
        txtContent += "============================================================\n\n";
        txtContent += "                            Appendix XII\n\n";

        // Add Bank and Scheme Details
        txtContent += "Name of the Bank: HDFC Bank Ltd\n";
        txtContent += "Name of the Bond Scheme: " + (vm.schemeName || "N/A") + "\n";
        txtContent += "Name of Branch & Code No: Mumbai, 5106\n";
        txtContent += "Name of Month:"+fromDate+"\n\n";

        // Add Title
        txtContent += "=======================\n";
        txtContent += "    Repayment Scroll\n";
        txtContent += "    Relief / Saving Bond\n";
        txtContent += "=======================\n\n";

    // Main Column Headers (First Row)
    txtContent += formatValue("S.No", colWidths[0]) +
        formatValue("Bond Ledger Account No", colWidths[1]) +
        formatValue("Due Date", colWidths[2]) +
        formatValue("Amount Of Repayment", colWidths[3] + colWidths[4]) + // Merging two columns
        formatValue("Credited to Bank Account No. Cheque / Payment Order No", colWidths[5]) +
        formatValue("Amount Paid In Case Of Cheque / Payment Order", colWidths[6] + colWidths[7]) + // Merging two columns
        formatValue("Remarks", colWidths[8]) +
        formatValue("Initials", colWidths[9]) + "\n";

    // Sub-Headers (Second Row)
    txtContent += formatValue("", colWidths[0]) + // Empty for S.No
        formatValue("", colWidths[1]) + // Empty for Bond Ledger Account No
        formatValue("", colWidths[2]) + // Empty for Due Date
        formatValue("Nominal Value", colWidths[3]) +
        formatValue("Maturity Value", colWidths[4]) +
        formatValue("", colWidths[5]) + // Empty for Credited to Bank Account No
        formatValue("Clearing", colWidths[6]) +
        formatValue("Transfer", colWidths[7]) +
        formatValue("", colWidths[8]) + // Empty for Remarks
        formatValue("", colWidths[9]) + "\n";

    // Add Separator Line
    txtContent += "-".repeat(colWidths.reduce((a, b) => a + b, 0)) + "\n";

    // Add Data Rows
    vm.filteredRecords.forEach(function (row, index) {
        txtContent += formatValue(index + 1, colWidths[0]) +
            formatValue(row.bla, colWidths[1]) +
            formatValue(vm.formatDate(row.dueDateRepay), colWidths[2]) +
            formatValue(vm.formatIndianCurrency(row.amountOfRepayment), colWidths[3]) + // Nominal Value
            formatValue(vm.formatIndianCurrency(row.nomineeValue), colWidths[4]) + // Maturity Value
            formatValue(row.creditAccNo, colWidths[5]) +
            formatValue(row.clearing, colWidths[6]) +
            formatValue(row.transfer, colWidths[7]) +
            formatValue(row.remarks, colWidths[8]) +
            formatValue(row.initials, colWidths[9]) + "\n";
    });

    // Add Footer Row for Totals
    txtContent += "-".repeat(colWidths.reduce((a, b) => a + b, 0)) + "\n";
    txtContent += formatValue("", colWidths[0]) +
        formatValue("Total Rs.:", colWidths[1] + colWidths[2]) +
        formatValue(vm.formatIndianCurrency(vm.totalPrincipalAmount()), colWidths[3]) +
        formatValue(vm.formatIndianCurrency(vm.nominalAmount()), colWidths[4]) + "\n";

    // Create and Download TXT File
    var blob = new Blob([txtContent], { type: "text/plain;charset=utf-8;" });
    var link = document.createElement("a");
    link.href = URL.createObjectURL(blob);
    link.download = "Repayment_Scroll.txt";
    link.click();
};

     function getModuleName() {
            vm.loader = true;
                 var requestData = {
                   schemeCode: "",
                   flag: "S",
                 };
                 principalScrollreportAppendixXIIService.getDetails(requestData).then(
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
              }
            function getAppendixVIIBrokHand(){
                          vm.loader = true;
                          var requestData = {
                              'schemeCode':vm.schemeCode,
                              'flag':'D',
                              'fromDate' :moment(vm.fromDate).format("DD/MMM/YYYY"),
                              'toDate'  :moment(vm.toDate).format("DD/MMM/YYYY"),
                          }

                          principalScrollreportAppendixXIIService.getDetails(requestData).then(function(response) {
                              vm.loader = false;

                               vm.model = response.data.result;
                               vm.filteredRecords =  response.data.result;
                               vm.totalPages         = Math.ceil(vm.model.length / vm.pageSize);
                               vm.pageDetails        = response.data.result;
                               vm.updatePagination();

//                      configDataTable();
                 }, function(error){

                            vm.loader = false;
                            if (error.data.message == 'No Data Found') {
                                vm.noDataFound = error.data.message;
                            } else {
                                handleErrorResponse(error);
                                $state.go('Principal_scrollreport_5106_Mar_24_Appendix_XII.inquiry')
                            }
                        })
             }

             vm.maxValue = function () {
             return Math.min(vm.currentPage * vm.pageSize, vm.filteredRecords.length);
             };

              function dateValidation() {
                              vm.toDate = '';


                                // Convert to Date object (if fromDate is string)
                                     var from = new Date(vm.fromDate);

                                     // Last day of the same month as fromDate
//                                     var lastDayOfMonth = new Date(
//                                         from.getFullYear(),
//                                         from.getMonth() + 1,
//                                         0
//                                     );



                              vm.dateToOptions = {
                                  minDate: from,
                                  maxDate: convertToDateObject(vm.currentDate),
                                  showWeeks: false
                              };
                              vm.dateOptions = {
                               maxDate:convertToDateObject(vm.currentDate)
                              }


                          }

            vm.totalPrincipalAmount = function () {
            return vm.filteredRecords.reduce((sum, record) => sum + (record.amountOfRepayment ? parseFloat(record.amountOfRepayment) : 0), 0);
            };

                vm.nominalAmount = function () {
                        return vm.filteredRecords.reduce((sum, record) => sum + (record.nomineeValue ? parseFloat(record.nomineeValue) : 0), 0);
                        };




            vm.textableDatePicker = textableDatePicker;
            vm.textableDatePickerTo =textableDatePickerTo;
           vm.autoslashing = autoslashing;
                                             function textableDatePickerTo(id, valueDate,valueDateFrom) {
                                            if (valueDate != null && valueDateFrom != null) {

                                                  let lastDayOfMonth = new Date(valueDateFrom.getFullYear(), valueDateFrom.getMonth() + 1, 0);

                                                  // Validation
                                                  if (valueDate < valueDateFrom) {

                                                      Swal.fire("warning","To Date should be greater than or equal to From Date", "warning");
                                                      vm.toDate="";
                                                      return;
                                                  }

//                                                  if (valueDate > lastDayOfMonth) {
//
//                                                      Swal.fire("warning","To Date should not exceed month end of From Date", "warning");
//                                                       vm.toDate="";
//                                                      return;
//                                                  }


                                              }
                                               commonService.textableDatePicker(id, valueDate);
                                             }

                                              function textableDatePicker(id, valueDate) {
                                                                     commonService.textableDatePicker(id, valueDate);
                                                                }
                                             function autoslashing(id) {
                                               commonService.autoslashing(id);
                                             }

	}
})();