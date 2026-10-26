(function () {
  "use strict";

  angular.module("gbmUltraApp").controller("interestpaymentReportController", interestpaymentReportController);

  interestpaymentReportController.$inject = [
    "$scope",
    "$filter",
    "$state",
    "$rootScope",
    "$stateParams",
    "$location",
    "$timeout",
    "interestpaymentReportService",
    "commonService",
  ];

  function interestpaymentReportController(
    $scope,
    $filter,
    $state,
    $rootScope,
    $stateParams,
    $location,
    $timeout,
    interestpaymentReportService,
    commonService
  ) {
    var vm = this;
    vm.moduleName = "RBI Bond";

    vm.getModuleName = getModuleName;
    vm.getReportDetails = getReportDetails;

    vm.solDate = $rootScope.globals.currentUser.systemDate;
    vm.subsDate = moment(vm.solDate).format("DD-MMM-YYYY");
    vm.subsDate1 = moment(vm.solDate).format("DD-MM-YYYY");
    vm.solDate1 = $rootScope.globals.currentUser.solDate;

    vm.textableDatePicker = textableDatePicker;
    vm.autoslashing = autoslashing;

    function textableDatePicker(id, valueDate) {
      commonService.textableDatePicker(id, valueDate);
    }
    function autoslashing(id) {
      commonService.autoslashing(id);
    }

    vm.loginSolDesc = $rootScope.globals.currentUser.solDesc;
    vm.today = new Date();
    vm.loginSolDate = $rootScope.globals.currentUser.solDate;
    vm.loginSolId = $rootScope.globals.currentUser.solId;
    //            vm.commonYesNoMetaData = COMMON_YES_NO_METADATA;
    //            vm.fromToDateValidation = fromToDateValidation;
    //            vm.getAddDetails =  getAddDetails;
    //            vm.printpage = printpage;
    //            vm    .alert=alert;

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

        vm.pageNo = 1;
        vm.pageSize = 5;

        vm.goPreviousPage = function () {
          vm.pageNo = parseInt(vm.pageNo) - 1;
          vm.getReportDetails();
        };

        vm.goNextPage = function () {
          vm.pageNo = parseInt(vm.pageNo) + 1;
          vm.getReportDetails();
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


    if ($state.current.name == "interest_payment_to_other_bank_report.inquiry_details") {
      if ($stateParams.obj) {
        vm.loader = true;
        vm.dataList = [];
        vm.schemeName = $stateParams.obj.schemeName;
        vm.schemeCode = $stateParams.obj.schemeCode;
        vm.fromDate = $stateParams.obj.fromDate;
        vm.toDate = $stateParams.obj.toDate;
            vm.getReportDetails();
      }
    }


    vm.downloadExcel = function () {
      var mainHeader = ["Interest Payment To Other Bank Report(Non-HDFC)"];

      var additionalHeaders = [
        ["Bank Name: INSPIRISY BANK LTD"],
        ["Sol ID: 101 / BRANCH OF THE BANK"],
        ["Name of the Bond Scheme: " + vm.schemeName],
        ["From Date: " + $filter("date")(vm.fromDate, "dd/MM/yyyy")],
        ["To Date: " + $filter("date")(vm.toDate, "dd/MM/yyyy")],
        [""], // Blank row for spacing
      ];

      var headers = [
        "S.No",
        "Customer Name",
        "PAN No",
        "Bond Ledger Account Number",
        "Bond Subscription ID",
        "Subscription Date",
        "Subscription Amount",
        "Interest Paid Date",
        "Interest From Date",
        "Interest To Date",
        "Interest Payment Mode",
        "Bank Name",
        "IFC Code",
        "MICR Code",
        "Credit Account Number",
        "TDS Certificate",
        "TDS Exemption Limit",
        "Low Tax Percentage",
        "PAN Status",
        "ITR Status",
        "Gross Interest",
        "TDS Amount",
        "Net Amount",
      ];

      var totalSubscriptionAmount = 0;
      var totalGrossInterest = 0;
      var totalTdsAmount = 0;

      var rows = vm.filteredRecord.map(function (row, index) {
        totalSubscriptionAmount += parseFloat(row.subscriptionAmount || 0);
        totalGrossInterest += parseFloat(row.grossInterest || 0);
        totalTdsAmount += parseFloat(row.tdsAmount || 0);

        return [
          index + 1,
          row.customerName,
          row.panNo,
          row.bondLedgerAccountNumber,
          row.bondSubscriptionId,
          row.subscriptionDate,
          row.subscriptionAmount,
          row.interestPaidDate,
          row.interestFromDate,
          row.interestToDate,
          row.modeOfPaymentDescription,
          row.bankName,
          row.ifcCode,
          row.micrCode,
          row.creditAccountNumber,
          row.tdsCertificate,
          row.tdsExemptionLimit,
          row.lowTaxPercentage,
          row.panStatus,
          row.itrStatus,
          row.grossInterest,
          row.tdsAmount,
          row.netAmount,
        ];
      });

      var totalsRow = Array(headers.length).fill("");
      totalsRow[0] = "TOTAL";
      totalsRow[6] = totalSubscriptionAmount.toFixed(2);
      totalsRow[20] = totalGrossInterest.toFixed(2);
      totalsRow[21] = totalTdsAmount.toFixed(2);

      // Combine all rows
      var combinedRows = [
        ...mainHeader.map((header) => [header]), // Main header as a single row
        ...additionalHeaders, // Additional headers
        headers, // Table headers
        ...rows, // Data rows
        totalsRow, // Totals row
      ];

      // Create worksheet from combinedRows
      var worksheet = XLSX.utils.aoa_to_sheet(combinedRows);
      var workbook = XLSX.utils.book_new();
      XLSX.utils.book_append_sheet(workbook, worksheet, "Report");

      // Merging main header row
      worksheet["!merges"] = [
        { s: { r: 0, c: 0 }, e: { r: 0, c: headers.length - 1 } }, // Merge the first row
      ];

      // Write the workbook to a file
      XLSX.writeFile(workbook, "Interest_Payment_To_Other_Bank_Report.xlsx");
    };

    vm.downloadCSV = function () {
      const mainHeader = "Interest Payment To Other Bank Report(NON-HDFC)";

      const additionalHeaders = [
        "Bank Name: INSPIRISY BANK LTD",
        "Sol ID: 101 / BRANCH OF THE BANK",
        "Name of the Bond Scheme: " + vm.schemeName,
        "From Date: " + $filter("date")(vm.fromDate, "dd/MM/yyyy"),
        "To Date: " + $filter("date")(vm.toDate, "dd/MM/yyyy"),
      ];

      const headers = [
        { title: "S.No.", width: 10 },
        { title: "Customer Name", width: 25 },
        { title: "PAN No", width: 25 },
        { title: "Bond Ledger Account Number", width: 35 },
        { title: "Bond Subscription ID", width: 25 },
        { title: "Subscription Date", width: 25 },
        { title: "Subscription Amount", width: 25 },
        { title: "Interest Paid Date", width: 25 },
        { title: "Interest From Date", width: 25 },
        { title: "Interest To Date", width: 25 },
        { title: "Interest Payment Mode", width: 25 },
        { title: "Bank Name", width: 25 },
        { title: "IFSC Code", width: 25 },
        { title: "MICR Code", width: 25 },
        { title: "Credit Account Number", width: 25 },
        { title: "TDS Certificate", width: 25 },
        { title: "TDS Exemption Limit", width: 25 },
        { title: "Low Tax Percentage", width: 25 },
        { title: "PAN Status", width: 25 },
        { title: "ITR Status", width: 25 },
        { title: "Gross Interest", width: 25 },
        { title: "TDS Amount", width: 25 },
        { title: "Net Amount", width: 25 },
      ];

      // Define columnWidths based on headers
      const columnWidths = headers.map((header) => header.width);

      function padString(str, width) {
        return (str || "").toString().padEnd(width, " ");
      }

      const formattedHeader = headers.map((header, index) => padString(header.title, columnWidths[index])).join("");

      const tableData = vm.filteredRecord.map((row, index) =>
        headers // Use `headers` instead of `tableHeader`
          .map((col, colIndex) => {
            const value = [
              index + 1, // S.No.
              row.customerName || "",
              row.panNo || "",
              row.bondLedgerAccountNumber || "",
              row.bondSubscriptionId || "",
              row.subscriptionDate || "",
              row.subscriptionAmount || "",
              row.interestPaidDate || "",
              row.interestFromDate || "",
              row.interestToDate || "",
              row.modeOfPaymentDescription || "",
              row.bankName || "",
              row.ifcCode || "",
              row.micrCode || "",
              row.creditAccountNumber || "",
              row.tdsCertificate || "",
              row.tdsExemptionLimit || "",
              row.lowTaxPercentage || "",
              row.panStatus || "",
              row.itrStatus || "",
              row.grossInterest || "",
              row.tdsAmount || "",
              row.netAmount || "",
            ][colIndex];
            return value.toString().padEnd(col.width, " ");
          })
          .join("")
      );

      const totalsRow = [
        padString("TOTAL", columnWidths[0]),
        padString("", columnWidths[1]),
        padString("", columnWidths[2]),
        padString("", columnWidths[3]),
        padString("", columnWidths[4]),
        padString("", columnWidths[5]),
        padString(vm.totalSubscriptionAmountDownload, columnWidths[6]),
        padString("", columnWidths[7]),
        padString("", columnWidths[8]),
        padString("", columnWidths[9]),
        padString("", columnWidths[10]),
        padString("", columnWidths[11]),
        padString("", columnWidths[12]),
        padString("", columnWidths[13]),
        padString("", columnWidths[14]),
        padString("", columnWidths[15]),
        padString("", columnWidths[16]),
        padString("", columnWidths[17]),
        padString("", columnWidths[18]),
        padString("", columnWidths[19]),
        padString(vm.totalGrossInterestDownload, columnWidths[20]),
        padString(vm.totalTdsAmountDownload !== undefined && vm.totalTdsAmountDownload !== null ? vm.totalTdsAmountDownload.toString() : "0", columnWidths[21]),

        padString("", columnWidths[22]),
      ].join("");

      const separatorLine = "-".repeat(formattedHeader.length);

      const textContent = [
        padString(mainHeader, formattedHeader.length),
        separatorLine,
        ...additionalHeaders.map((header) => padString(header, formattedHeader.length)),
        separatorLine,
        formattedHeader,
        separatorLine,
        ...tableData,
        separatorLine,
        totalsRow,
        separatorLine,
      ].join("\n");

      const textBlob = new Blob([textContent], { type: "text/plain;charset=utf-8;" });
      const link = document.createElement("a");
      const url = URL.createObjectURL(textBlob);

      link.setAttribute("href", url);
      link.setAttribute("download", "Interest_Payment_To_Other_Bank_Report.txt");
      document.body.appendChild(link);
      link.click();
      document.body.removeChild(link);
    };

    function getModuleName() {
      console.log("get module details");
      vm.loader = true;
      var requestData = {
        schemeCode: "",
      };

      interestpaymentReportService.getinterestPaymentScheme(requestData).then(
        function (response) {
          vm.loader = false;
          vm.schemeList = response.data.result.list;
          angular.element("#schemeModel").modal("toggle");
        },
        function (error) {
          vm.loader = false;
          handleErrorResponse(error);
        }
      );
    }

    function getReportDetails() {
      vm.loader = true;
      var requestData = {
        schemeCode: vm.schemeCode,
        fromDate: moment(vm.fromDate).format("DD-MMM-YYYY"),
        toDate: moment(vm.toDate).format("DD-MMM-YYYY"),
        pageNo: vm.pageNo,
        pageSize: vm.pageSize,
        searchValue: vm.searchValue,
        sortOrder: vm.sortOrder,
        sortField: vm.sortField,
      };

      interestpaymentReportService.getinterestPaymentDetails(requestData).then(
        function (response) {
          vm.loader = false;
          vm.report = response.data.result.list;
          vm.filteredRecords = response.data.result.list;
          vm.records = response.data.result.list;
          vm.total = response.data.total;
          vm.totalSubscriptionAmount = vm.filteredRecords.reduce((sum, record) => sum + (record.subscriptionAmount ? parseFloat(record.subscriptionAmount) : 0), 0);
          vm.totalGrossInterest = vm.filteredRecords.reduce((sum, record) => sum + (record.grossInterest ? parseFloat(record.grossInterest) : 0), 0);
          vm.totalTdsAmount = vm.filteredRecords.reduce((sum, record) => sum + (record.tdsAmount ? parseFloat(record.tdsAmount) : 0), 0);
          vm.maxPages = Math.ceil(response.data.total / vm.pageSize);
               vm.paginationControls();
                    if (vm.report.length === 0) {
                      vm.noDataFoundInSearch = true;
                    } else {
                      vm.noDataFoundInSearch = false;
                    }
        },
        function (error) {
          vm.loader = false;
          if (vm.searchValue && (error.data.message == "No Data Found" || error.data.message == "NDF")) {
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
               $state.go("interest_payment_to_other_bank_report.inquiry_details");
          }
        }
      );
    }

        vm.downloadReport = function () {
             vm.loader = true;
                var requestData = {
                  schemeCode: vm.schemeCode,
                  fromDate: moment(vm.fromDate).format("DD-MMM-YYYY"),
                  toDate: moment(vm.toDate).format("DD-MMM-YYYY"),
                  pageNo: 1,
                  pageSize: vm.total,
                  searchValue: vm.searchValue,
                };

                interestpaymentReportService.getinterestPaymentDetailsDownload(requestData).then(
                  function (response) {
                    vm.loader = false;
                    vm.filteredRecord = response.data.result.list;

                    if (vm.downloadType === "excel") {
                        if (response.data.result.fileName) {
                             vm.fileData = response.data.result.fileData;
                             vm.fileName = response.data.result.fileName;
                             saveAs(getBlobFromBase64String(vm.fileData), vm.fileName)
                             Swal.fire("Downloaded", response.data.message, "success");
                        }
                    }
                    vm.downloadType = "";
                  },
                  function (error) {
                      vm.loader = false;
                        if (error.data.message == "No Data Found" || error.data.message == "NDF") {
                          vm.noDataFound = error.data.message;
                        } else {
                          handleErrorResponse(error);
                          $state.go("interest_payment_to_other_bank_report.inquiry");
                        }
                  }
                );
        };

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
