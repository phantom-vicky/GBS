(function () {
  "use strict";

  angular.module("gbmUltraApp").controller("StatementOfBrokerageController", StatementOfBrokerageController);

  StatementOfBrokerageController.$inject = ["$scope", "$filter", "$state", "$stateParams", "$location", "$rootScope", "StatementOfBrokerageService", "commonService"];

  function StatementOfBrokerageController($scope, $filter, $state, $stateParams, $location, $rootScope, StatementOfBrokerageService, commonService) {
    var vm = this;
    vm.getDetails = getDetails;
    vm.solDate = $rootScope.globals.currentUser.solDate;
    vm.textableDatePicker = textableDatePicker;
    vm.autoslashing = autoslashing;

    function textableDatePicker(id, valueDate) {
      commonService.textableDatePicker(id, valueDate);
    }
    function autoslashing(id) {
      commonService.autoslashing(id);
    }

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

    vm.getSchemeName = function () {
      vm.loader = true;
      var requestData = {
        flag: "S",
      };
      StatementOfBrokerageService.getDetails(requestData).then(
        function (response) {
          vm.loader = false;
          vm.schemeList = response.data.result.list;
          angular.element("#schemeModal").modal("toggle");
        },
        function (error) {
          handleErrorResponse(error);
          vm.loader = false;
        }
      );
    };

    function getDetails() {
      vm.loader = true;
      var requestData = {
        schemeCode: vm.schemeCode,
        reportType: vm.reportType,
        status: vm.status,
        fromDate: moment(vm.fromDate).format("DD-MMM-YYYY"),
        toDate: moment(vm.toDate).format("DD-MMM-YYYY"),
        flag: "D",
        pageNo: vm.pageNo,
        pageSize: vm.pageSize,
        searchValue: vm.searchText,
        sortOrder: vm.sortOrder,
        sortField: vm.sortField,
      };

      StatementOfBrokerageService.getDetails(requestData).then(
        function (response) {
          vm.loader = false;
          vm.model = response.data.result.list;
          vm.filteredRecords = response.data.result.list;
          vm.totalCumulative = vm.filteredRecords.reduce((sum, record) => sum + (record.cumulative ? parseFloat(record.cumulative) : 0), 0);
          vm.totalNonCumulative = vm.filteredRecords.reduce((sum, record) => sum + (record.nonCumulative ? parseFloat(record.nonCumulative) : 0), 0);
          vm.totalNetBrokerage = vm.filteredRecords.reduce((sum, record) => sum + (record.netBrokeragePaid ? parseFloat(record.netBrokeragePaid) : 0), 0);
          vm.totalNetBrokeragee = parseFloat(vm.totalNetBrokerage.toFixed(2));

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
            $state.go("Statement_of_Brokerage_Paid_report.inquiry");
          }
        }
      );
    }
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

    //    Details screen state change
    vm.getReportType = function (reportType) {
      const descriptions = {
        IW: "Investment Wise",
        BW: "Broker Wise",
      };
      return descriptions[reportType] || "";
    };

    vm.getStatus = function (status) {
      const descriptions = {
        P: "Paid",
        UP: "Unpaid",
        ALL: "All",
      };
      return descriptions[status] || "";
    };

    if ($state.current.name == "Statement_of_Brokerage_Paid_report.inquiry_details") {
      vm.schemeCode = $stateParams.obj.schemeCode;
      vm.schemeName = $stateParams.obj.schemeName;
      vm.reportType = $stateParams.obj.reportType;
      vm.reportDesc = $stateParams.obj.reportDesc;
      vm.status = $stateParams.obj.status;
      vm.statusDesc = $stateParams.obj.statusDesc;
      vm.fromDate = $stateParams.obj.fromDate;
      vm.toDate = $stateParams.obj.toDate;
        getDetails();
    }

    //    Download report
    vm.reports = {
      IW: {
        filename: "Statement of Brokerage Paid & Unpaid",
        mainHeaders: ["Statement of Brokerage Paid & Unpaid", "Bank Name: INSPIRISY BANK LTD", "Sol ID: 101 / BRANCH OF THE BANK"],
        subHeaders: [
          "Date: " + vm.solDate,
          "Scheme Name: " + vm.schemeName,
          "Type of Report: " + vm.reportDesc,
          "Status: " + vm.status,
          "From Date: " + $filter("date")(vm.fromDate, "dd/MM/yyyy"),
          "To Date: " + $filter("date")(vm.toDate, "dd/MM/yyyy"),
        ],
        tableHeaders: [
          { title: "S. No.", width: 10, type: "numeric" },
          { title: "BLA No.", width: 20, type: "string" },
          { title: "Customer Name", width: 30, type: "string" },
          { title: "Subscription ID", width: 20, type: "string" },
          { title: "Subscription amount Rs. (Cumulative)", width: 20, type: "numeric" },
          { title: "Subscription amount Rs. (Non-Cumulative)", width: 20, type: "numeric" },
          { title: "Status", width: 20, type: "string" },
          { title: "Invoice Number", width: 20, type: "string" },
          { title: "Invoice Date", width: 20, type: "string" },
          { title: "Broker ID", width: 20, type: "string" },
          { title: "Name of the Broker", width: 20, type: "string" },
          { title: "Broker PAN", width: 20, type: "string" },
          { title: "Broker Email ID", width: 20, type: "string" },
          { title: "Gross Brokerage", width: 20, type: "numeric" },
          { title: "GST", width: 20, type: "numeric" },
          { title: "Net Brokerage Paid", width: 20, type: "numeric" },
          { title: "Brokerage Paid Date", width: 20, type: "string" },
          { title: "Brokerage Transaction initiated date for Unpaid", width: 20, type: "string" },
          { title: "Reason for Unpaid", width: 20, type: "string" },
        ],
        get tableData() {
          return (vm.downloadFilteredRecords || []).map((record, index) => [
            (index + 1).toString(),
            record.blaNumber || "",
            record.customerName || "",
            record.subscriptionID || "",
            record.cumulative || "",
            record.nonCumulative || "",
            record.customerStatus || "",
            record.invoiceNumber || "",
            record.invoiceDate || "",
            record.brokerID || "",
            record.brokerName || "",
            record.brokerPAN || "",
            record.brokerEmailId || "",
            record.grossBrokerage || "",
            record.gst || "",
            record.netBrokeragePaid || "",
            record.brokeragePaidDate || "",
            record.brokerageTransactionInitiatedDate || "",
            record.reasonForUnpaid || "",
          ]);
        },
      },
      BW: {
        filename: "Statement of Brokerage Paid & Unpaid",
        mainHeaders: ["Statement of Brokerage Paid & Unpaid", "Bank Name: INSPIRISY BANK LTD", "Sol ID: 101 / BRANCH OF THE BANK"],
        subHeaders: [
          "Date: " + vm.solDate,
          "Scheme Name: " + vm.schemeName,
          "Type of Report: " + vm.reportDesc,
          "Status: " + vm.status,
          "From Date: " + $filter("date")(vm.fromDate, "dd/MM/yyyy"),
          "To Date: " + $filter("date")(vm.toDate, "dd/MM/yyyy"),
        ],
        tableHeaders: [
          { title: "S. No.", width: 10, type: "numeric" },
          { title: "Invoice Number", width: 20, type: "string" },
          { title: "Invoice Date", width: 20, type: "string" },
          { title: "Broker Name", width: 20, type: "string" },
          { title: "Broker Code", width: 20, type: "string" },
          { title: "Broker PAN", width: 20, type: "string" },
          { title: "Broker Email ID", width: 20, type: "string" },
          { title: "Gross Brokerage", width: 20, type: "numeric" },
          { title: "GST %", width: 20, type: "numeric" },
          { title: "GST Amount", width: 20, type: "numeric" },
          { title: "Net Brokerage", width: 20, type: "numeric" },
          { title: "Brokerage Paid Date", width: 20, type: "string" },
          { title: "Status", width: 20, type: "string" },
          { title: "Remarks", width: 20, type: "string" },
        ],
        get tableData() {
          return (vm.downloadFilteredRecords || []).map((record, index) => [
            (index + 1).toString(),
            record.invoiceNumber || "",
            record.invoiceDate || "",
            record.brokerName || "",
            record.brokerCode || "",
            record.brokerPAN || "",
            record.brokerEmailId || "",
            record.grossBrokerage || "",
            record.gstPercent || "",
            record.gstAmount || "",
            record.netBrokeragePaid || "",
            record.brokeragePaidDate || "",
            record.customerStatus || "",
            record.remarks || "",
          ]);
        },
      },
    };

    vm.downloadReport = function (reportType) {
      vm.loader = true;
      var requestData = {
        schemeCode: vm.schemeCode,
        reportType: vm.reportType,
        status: vm.status,
        fromDate: moment(vm.fromDate).format("DD-MMM-YYYY"),
        toDate: moment(vm.toDate).format("DD-MMM-YYYY"),
        flag: "D",
        pageNo: "1",
        pageSize: vm.total,
        searchValue: vm.searchText,
      };
      StatementOfBrokerageService.getDetails(requestData).then(
        function (response) {
          vm.loader = false;
          vm.downloadFilteredRecords = response.data.result.list;
          vm.totalCumulativeDownload = vm.downloadFilteredRecords.reduce((sum, record) => sum + (record.cumulative ? parseFloat(record.cumulative) : 0), 0);
          vm.totalNonCumulativeDownload = vm.downloadFilteredRecords.reduce((sum, record) => sum + (record.nonCumulative ? parseFloat(record.nonCumulative) : 0), 0);
          vm.totalNetBrokerageDownload = vm.downloadFilteredRecords.reduce((sum, record) => sum + (record.netBrokeragePaid ? parseFloat(record.netBrokeragePaid) : 0), 0);
          const currentReport = vm.reports[reportType];
          if (vm.downloadType === "excel") {
            vm.downloadExcel(currentReport, reportType);
          } else if (vm.downloadType === "csv") {
            vm.downloadCSV(currentReport, reportType);
          }
          vm.downloadType = "";
        },
        function (error) {
          vm.loader = false;
          if (error.data.message == "No Data Found" || error.data.message == "NDF") {
            vm.noDataFound = error.data.message;
          } else {
            handleErrorResponse(error);
            $state.go("Statement_of_Brokerage_Paid_report.inquiry");
          }
        }
      );
    };

    vm.downloadCSV = function (currentReport, reportType) {
      const { filename, mainHeaders, subHeaders, tableHeaders, tableData } = currentReport;
      const columnWidths = tableHeaders.map((col, index) => {
        const headerLength = col.title.length;
        const maxDataLength = Math.max(headerLength, ...tableData.map((row) => (row[index] ? row[index].toString().length : 0)));
        return Math.max(col.width || 0, maxDataLength);
      });
      const formatRow = (values) => {
        return values
          .map((value, i) => {
            const col = tableHeaders[i];
            const width = columnWidths[i];
            const alignment = col.type === "numeric" ? "right" : "left";
            const stringValue = value ? value.toString() : "";

            if (alignment === "right") {
              return stringValue.padStart(width, " ");
            } else {
              return stringValue.padEnd(width, " ");
            }
          })
          .join(" | "); // Use " | " as a delimiter
      };
      const formattedHeader = formatRow(tableHeaders.map((col) => col.title));
      const formattedData = tableData.map((row) => formatRow(row));
      const totalRow = formatRow(
        tableHeaders.map((col, i) => {
          if (i === tableHeaders.length - 16) return "Total";
          if (i === tableHeaders.length - 15) return vm.totalCumulativeDownload.toString();
          if (i === tableHeaders.length - 14) return vm.totalNonCumulativeDownload.toString();
          if (i === tableHeaders.length - 4) return vm.totalNonCumulativeDownload.toString();
          return "";
        })
      );
      let textData = [];
      if (reportType == "IW") {
        textData = [
          mainHeaders.join("\n"),
          subHeaders.join("\n"),
          "-".repeat(formattedHeader.length),
          formattedHeader,
          "-".repeat(formattedHeader.length),
          ...formattedData,
          "-".repeat(formattedHeader.length),
          totalRow,
          "-".repeat(formattedHeader.length),
        ].join("\n");
      } else {
        textData = [mainHeaders.join("\n"), subHeaders.join("\n"), "-".repeat(formattedHeader.length), formattedHeader, "-".repeat(formattedHeader.length), ...formattedData, "-".repeat(formattedHeader.length)].join("\n");
      }

      const textContent = "data:text/plain;charset=utf-8," + encodeURIComponent(textData);
      const link = document.createElement("a");
      link.setAttribute("href", textContent);
      link.setAttribute("download", filename + ".txt");
      document.body.appendChild(link);
      link.click();
      document.body.removeChild(link);
    };

    vm.downloadExcel = function (currentReport, reportType) {
      const { filename, mainHeaders, subHeaders, tableHeaders, tableData } = currentReport;
      const excelData = [];
      mainHeaders.forEach((header) => {
        excelData.push([header, ...Array(tableHeaders.length - 1).fill("")]);
      });
      subHeaders.forEach((header) => {
        excelData.push([header, ...Array(tableHeaders.length - 1).fill("")]);
      });
      excelData.push([]);
      excelData.push(tableHeaders.map((header) => header.title));
      tableData.forEach((record) => {
        excelData.push(Object.values(record));
      });
      let totalRow = [];
      if (reportType === "IW") {
        totalRow = ["", "", "", "Total", vm.totalCumulativeDownload, vm.totalNonCumulativeDownload, "", "", "", "", "", "", "", "", "", vm.totalNetBrokerageDownload, "", "", ""];
        excelData.push(totalRow);
      }
      const ws = XLSX.utils.aoa_to_sheet(excelData);
      const mergeRanges = [
        { s: { r: 0, c: 0 }, e: { r: 0, c: 10 } },
        { s: { r: 1, c: 0 }, e: { r: 1, c: 10 } },
        { s: { r: 2, c: 0 }, e: { r: 2, c: 10 } },
        { s: { r: 3, c: 0 }, e: { r: 3, c: 10 } },
        { s: { r: 4, c: 0 }, e: { r: 4, c: 10 } },
        { s: { r: 5, c: 0 }, e: { r: 5, c: 10 } },
        { s: { r: 6, c: 0 }, e: { r: 6, c: 10 } },
        { s: { r: 7, c: 0 }, e: { r: 7, c: 10 } },
        { s: { r: 8, c: 0 }, e: { r: 8, c: 10 } },
      ];
      ws["!merges"] = mergeRanges;
      mergeRanges.forEach((merge) => {
        const cell = ws[XLSX.utils.encode_cell(merge.s)];
        if (cell) {
          cell.s = { alignment: { horizontal: "center", vertical: "center" }, font: { bold: true } };
        }
      });
      const wb = XLSX.utils.book_new();
      XLSX.utils.book_append_sheet(wb, ws, "Data");
      XLSX.writeFile(wb, filename + ".xlsx");
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
