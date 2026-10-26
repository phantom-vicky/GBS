(function () {
  "use strict";

  angular.module("gbmUltraApp").controller("bondActualInterestRunCreditedReportController", bondActualInterestRunCreditedReportController);

  bondActualInterestRunCreditedReportController.$inject = ["$scope", "$filter", "$state", "$stateParams", "$location", "$rootScope", "$timeout", "bondActualInterestRunCreditedReportService", "commonService"];

  function bondActualInterestRunCreditedReportController($scope, $filter, $state, $stateParams, $location, $rootScope, $timeout, bondActualInterestRunCreditedReportService, commonService) {
    var vm = this;
    vm.getModuleName = getModuleName;
    vm.solDate = $rootScope.globals.currentUser.systemDate;
    vm.solDate = $filter("date")($rootScope.globals.currentUser.systemDate, "dd/MM/yyyy");
    vm.moduleName = "RBI Bond";
    vm.getDetails = getDetails;

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

    vm.formatDate = function (tempDate) {
      return $filter("date")(tempDate, "dd/MM/yyyy");
    };

    if ($state.current.name == "bond_actual_interest_run_credited_report.inquiry_details") {
      if ($stateParams.obj) {
        vm.schemeCode = $stateParams.obj.schemeCode;
        vm.schemeName = $stateParams.obj.schemeName;
        vm.schemeStatus = $stateParams.obj.schemeStatus;
        vm.bodDate = $stateParams.obj.bodDate;
        vm.fromdate = $stateParams.obj.fromdate;
        vm.todate = $stateParams.obj.todate;
        vm.panNo = $stateParams.obj.panNo;
        vm.blaNo = $stateParams.obj.blaNo;
        vm.subscriptionId = $stateParams.obj.subscriptionId;
        getDetails();
      }
    }
    vm.reports = {
      BondActualInterestRunCreditedReport: {
        filename: "Bond Actual Interest Run Credited Report",
        mainHeaders: ["Bond Actual Interest Run Credited Report", "Bank Name: INSPIRISY BANK LTD", "Sol ID: 101 / BRANCH OF THE BANK"],
        subHeaders: ["Name of the Bond Scheme: " + vm.schemeName, "Interest Payment From Date : " + vm.formatDate(vm.fromdate), "Interest Payment To Date : " + vm.formatDate(vm.todate)],
        tableHeaders: [
          { title: "", width: 10 },
          { title: "Basic Details From BLA cum Subscription", width: 30 },
          { title: "", width: 30 },
          { title: "", width: 30 },
          { title: "", width: 30 },
          { title: "", width: 30 },
          { title: "", width: 30 },
          { title: "", width: 30 },
          { title: "", width: 40 },
          { title: "", width: 40 },
          { title: "", width: 40 },
          { title: "", width: 40 },
          { title: "", width: 30 },
          { title: "", width: 25 },
          { title: "", width: 30 },
          { title: "", width: 20 },
          { title: "", width: 20 },
          { title: "", width: 20 },
          { title: "", width: 20 },
          { title: "", width: 20 },
          { title: "", width: 20 },
          { title: "OTHER SCHEMES INTEREST RELATED INFO", width: 20 },
          { title: "", width: 20 },
          { title: "", width: 20 },
          { title: "", width: 20 },
          { title: "", width: 20 },
          { title: "PAN STATUS", width: 20 },
          { title: "", width: 20 },
          { title: "TDS EXEMPTION DETAILS", width: 20 },
          { title: "", width: 20 },
          { title: "", width: 20 },
          { title: "", width: 20 },
          { title: "", width: 20 },
          { title: "", width: 20 },
          { title: "", width: 20 },
          { title: "", width: 20 },
          { title: "", width: 20 },
          { title: "", width: 30 },
          { title: "", width: 30 },
          { title: "", width: 30 },
          { title: "", width: 20 },
          { title: "", width: 20 },
          { title: "", width: 20 },
          { title: "", width: 20 },
          { title: "", width: 20 },
          { title: "", width: 20 },
          { title: "FRSB INTEREST PAYOUT DETAILS", width: 20 },
          { title: "", width: 20 },
          { title: "", width: 20 },
          { title: "", width: 20 },
          { title: "", width: 20 },
          { title: "", width: 20 },
          { title: "", width: 20 },
          { title: "", width: 30 },
          { title: "", width: 20 },
          { title: "", width: 20 },
          { title: "", width: 30 },
          { title: "", width: 20 },
          { title: "", width: 20 },
          { title: "", width: 20 },
          { title: "", width: 20 },
          { title: "", width: 20 },
          { title: "", width: 20 },
          { title: "", width: 20 },
        ],
        tableSubHeaders: [
          { title: "S.No", width: 10 },
          { title: "Mode of Payment", width: 30 },
          { title: "Branch Ref No", width: 30 },
          { title: "BLA No", width: 30 },
          { title: "Ref No", width: 30 },
          { title: "App Name 1", width: 30 },
          { title: "App Name 2", width: 30 },
          { title: "App Name 3", width: 30 },
          { title: "Address 1", width: 40 },
          { title: "Address 2", width: 40 },
          { title: "Rec Name", width: 40 },
          { title: "Rec Parent Name", width: 40 },
          { title: "Pin Code", width: 30 },
          { title: "Phone No", width: 25 },
          { title: "Application Status", width: 30 },
          { title: "Investment Amount", width: 20 },
          { title: "CFD", width: 20 },
          { title: "Maturity Date", width: 20 },
          { title: "Account No", width: 20 },
          { title: "Subscription Count", width: 20 },
          { title: "Bank Sort Code", width: 20 },
          { title: "Bank Name", width: 20 },
          { title: "Pan No Name", width: 20 },
          { title: "8 % Interest Payout", width: 20 },
          { title: "Non Cumulative Int", width: 20 },
          { title: "Cumulative Int", width: 20 },
          { title: "7.75% interest payout", width: 20 },
          { title: "Non Cumulative Int 775", width: 20 },
          { title: "Cumulative Int 775", width: 20 },
          { title: "Total", width: 20 },
          { title: "In-Operative PAN", width: 20 },
          { title: "Non-Filers", width: 20 },
          { title: "Age", width: 20 },
          { title: "Taxable Limit", width: 20 },
          { title: "Waiver Document", width: 20 },
          { title: "Waiver Document Ref No", width: 20 },
          { title: "RRB-TDS rate", width: 20 },
          { title: "Threshold Exceed Amount", width: 30 },
          { title: "Threshold Exceed TDS Amount", width: 30 },
          { title: "Exemption Rate %", width: 30 },
          { title: "Exemption Amount", width: 20 },
          { title: "Exemption TDS Amount", width: 20 },
          { title: "Surcharge %", width: 20 },
          { title: "Edu Cess %", width: 20 },
          { title: "Base Rate Amount", width: 20 },
          { title: "Surcharge Amount", width: 20 },
          { title: "Edu cess Amount", width: 20 },
          { title: "TDS Amount", width: 20 },
          { title: "TDS Recovery Amount", width: 20 },
          { title: "TDS Refund Amount", width: 20 },
          { title: "Int Rate", width: 20 },
          { title: "Last Int Paid", width: 20 },
          { title: "Pending Interest", width: 20 },
          { title: "Current Half-Yearly Interest", width: 30 },
          { title: "Future Interest", width: 20 },
          { title: "Gross Interest Amount", width: 20 },
          { title: "Int Paid (Net Interest Amount)", width: 30 },
          { title: "Payment Date", width: 20 },
          { title: "Return Reason", width: 20 },
          { title: "Return Amount", width: 20 },
          { title: "ReInitiated Amount", width: 20 },
          { title: "ReInitiated Date", width: 20 },
          { title: "Payment Date", width: 20 },
          { title: "ReInitiate Payment Mode", width: 20 },
        ],
        get tableData() {
          return (vm.filteredRecordsDownload || []).map((record, index) => [
            (index + 1).toString(),
            record.modeOfPayment || "",
            record.branchRefNo || "",
            record.blaNo || "",
            record.refNo || "",
            record.appName1 || "",
            record.appName2 || "",
            record.appName3 || "",
            record.address1 || "",
            record.address2 || "",
            record.recName || "",
            record.recParentName || "",
            record.pinCode || "",
            record.phoneNo || "",
            record.appStatus || "",
            record.investmentAmount || "",
            record.cfd || "",
            record.maturityDate || "",
            record.accountNo || "",
            record.subscriptionCount || "",
            record.bankSortCode || "",
            record.bankName || "",
            record.panNoName || "",
            record.interestPayOut8Per || "",
            record.nonCumulativeInt || "",
            record.cumulativeInt || "",
            record.interestPayOut7Per || "",
            record.nonCumulativeIntFor7 || "",
            record.cumulativeIntFor7 || "",
            record.total || "",
            record.inOperativePAN || "",
            record.nonFilers || "",
            record.age || "",
            record.taxableLimit || "",
            record.waiverDocument || "",
            record.waiverDocumentRefNo || "",
            record.rrbTDSRate || "",
            record.thresholdExceedAmount || "",
            record.thresholdExceedTDSAmount || "",
            record.exemptionRate || "",
            record.exemptionAmount || "",
            record.exemptionTDSAmount || "",
            record.surcharge || "",
            record.eduCess || "",
            record.baseRateAmount || "",
            record.surchargeAmount || "",
            record.eduCessAmount || "",
            record.tdsAmount || "",
            record.tdsRecoveryAmount || "",
            record.tdsRefundAmount || "",
            record.intRate || "",
            record.lastIntPaid || "",
            record.pendingInterest || "",
            record.currentHalfYearlyInterest || "",
            record.futureInterest || "",
            record.grossInterestAmount || "",
            record.intPaid || "",
            record.paymentDate || "",
            record.returnReason || "",
            record.returnAmt || "",
            record.reInitiatedAmt || "",
            record.reInitiatedDate || "",
            record.paymentDateOfReturn || "",
            record.reInitiatedPaymentMode || "",
          ]);
        },
      },
    };

    vm.downloadReport = function () {
      vm.loader = true;
      var requestData = {
        schemeCode: vm.schemeCode,
        fromDate: moment(vm.fromdate).format("DD-MMM-YYYY"),
        toDate: moment(vm.todate).format("DD-MMM-YYYY"),
        blaNo: vm.blaNo,
        panNo: vm.panNo,
        subscriptionId: vm.subscriptionId,
        flag: vm.downloadType === "M" ? "M" : "UM",
        pageNo: "1",
        pageSize: vm.total,
        searchValue: vm.searchText,
        downloadType : vm.downloadType,
      };
      bondActualInterestRunCreditedReportService.getDetails(requestData).then(
        function (response) {
          vm.loader = false;
          vm.filteredRecordsDownload = response.data.result.list;



//           vm.investmentAmount = vm.filteredRecords.reduce((sum, record) => sum + (record.investmentAmount ? parseFloat(record.investmentAmount) : 0), 0);
//
//
//                      vm.thresholdExceedAmount = vm.filteredRecords.reduce((sum, record) => sum + (record.thresholdExceedAmount ? parseFloat(record.thresholdExceedAmount) : 0), 0);
//                       vm.thresholdExceedTDSAmount = vm.filteredRecords.reduce((sum, record) => sum + (record.thresholdExceedTDSAmount ? parseFloat(record.thresholdExceedTDSAmount) : 0), 0);
//                        vm.exemptionAmount = vm.filteredRecords.reduce((sum, record) => sum + (record.exemptionAmount ? parseFloat(record.exemptionAmount) : 0), 0);
//                         vm.exemptionTDSAmount = vm.filteredRecords.reduce((sum, record) => sum + (record.exemptionTDSAmount ? parseFloat(record.exemptionTDSAmount) : 0), 0);
//                          vm.baseRateAmount = vm.filteredRecords.reduce((sum, record) => sum + (record.baseRateAmount ? parseFloat(record.baseRateAmount) : 0), 0);
//                           vm.surchargeAmount = vm.filteredRecords.reduce((sum, record) => sum + (record.surchargeAmount ? parseFloat(record.surchargeAmount) : 0), 0);
//                           vm.eduCessAmount = vm.filteredRecords.reduce((sum, record) => sum + (record.eduCessAmount ? parseFloat(record.eduCessAmount) : 0), 0);
//                          vm.tdsAmount = vm.filteredRecords.reduce((sum, record) => sum + (record.tdsAmount ? parseFloat(record.tdsAmount) : 0), 0);
//                          vm.tdsRecoveryAmount = vm.filteredRecords.reduce((sum, record) => sum + (record.tdsRecoveryAmount ? parseFloat(record.tdsRecoveryAmount) : 0), 0);
//                           vm.tdsRefundAmount = vm.filteredRecords.reduce((sum, record) => sum + (record.tdsRefundAmount ? parseFloat(record.tdsRefundAmount) : 0), 0);
//
//
//
//                                            vm.grossInterestAmount = vm.filteredRecords.reduce((sum, record) => sum + (record.grossInterestAmount ? parseFloat(record.grossInterestAmount) : 0), 0);
//                                            vm.returnAmt = vm.filteredRecords.reduce((sum, record) => sum + (record.returnAmt ? parseFloat(record.returnAmt) : 0), 0);
//                                            vm.reInitiatedAmt = vm.filteredRecords.reduce((sum, record) => sum + (record.reInitiatedAmt ? parseFloat(record.reInitiatedAmt) : 0), 0);
//
//
//
//
//
//          const newEntryy = { investmentAmount: vm.investmentAmount, thresholdExceedAmount: vm.thresholdExceedAmount , thresholdExceedTDSAmount: vm.thresholdExceedTDSAmount, exemptionAmount: vm.exemptionAmount,exemptionTDSAmount: vm.exemptionTDSAmount, baseRateAmount: vm.baseRateAmount,surchargeAmount: vm.surchargeAmount, eduCessAmount: vm.eduCessAmount,tdsAmount: vm.tdsAmount, tdsRecoveryAmount: vm.tdsRecoveryAmount,tdsRefundAmount: vm.tdsRefundAmount, grossInterestAmount: vm.grossInterestAmount,returnAmt: vm.returnAmt, reInitiatedAmt: vm.reInitiatedAmt};
//
//                     vm.filteredRecordsDownload.push(newEntryy);
//
         const currentReport = vm.reports["BondActualInterestRunCreditedReport"];
           // vm.downloadExcel(currentReport);
            if (response.data.result.fileName) {
             vm.fileData = response.data.result.fileData;
             vm.fileName = response.data.result.fileName;
             saveAs(getBlobFromBase64String(vm.fileData), vm.fileName)
                        }
         //   vm.downloadCSV(currentReport);
          vm.downloadType = "";
        },
        function (error) {
          vm.loader = false;
          if (error.data.message == "No Data Found" || error.data.message == "NDF") {
            vm.noDataFound = error.data.message;
            vm.downloadType = "";
          } else {
            handleErrorResponse(error);
            vm.downloadType = "";
            $state.go("bond_actual_interest_run_credited_report.inquiry");
          }
        }
      );
    };

    vm.downloadCSV = function (currentReport) {
      const { filename, mainHeaders, subHeaders, tableHeaders, tableSubHeaders, tableData } = currentReport;
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
      const formattedSubHeaders = formatRow(tableSubHeaders.map((col) => col.title));
      const formattedData = tableData.map((row) => formatRow(row)).join("\n");

      //      const totalRow = formatRow(
      //        tableHeaders.map((col, i) => {
      //          if (i === tableHeaders.length - 2) return "Total Amount Rs.";
      //          if (i === tableHeaders.length - 1) return vm.totalAmount.toString();
      //          return "";
      //        })
      //      );
      const textData = [
        mainHeaders.join("\n"),
        subHeaders.join("\n"),
        "-".repeat(formattedHeader.length),
        formattedHeader,
        "-".repeat(formattedHeader.length),
        formattedSubHeaders,
        "-".repeat(formattedHeader.length),
        formattedData,
        "-".repeat(formattedHeader.length),
      ].join("\n");

      const textContent = "data:text/plain;charset=utf-8," + encodeURIComponent(textData);
      const link = document.createElement("a");
      link.setAttribute("href", textContent);
      link.setAttribute("download", filename + ".txt");
      document.body.appendChild(link);
      link.click();
      document.body.removeChild(link);
    };

    vm.downloadExcel = function (currentReport) {
      const { filename, mainHeaders, subHeaders, tableHeaders, tableSubHeaders, tableData } = currentReport;
      const excelData = [];

      mainHeaders.forEach((header) => {
        excelData.push([header, ...Array(tableHeaders.length - 1).fill("")]);
      });

      subHeaders.forEach((header) => {
        excelData.push([header, ...Array(tableHeaders.length - 1).fill("")]);
      });
      excelData.push([]);
      excelData.push(tableHeaders.map((header) => header.title));
      excelData.push(tableSubHeaders.map((header) => header.title));
      tableData.forEach((record) => {
        excelData.push(Object.values(record));
      });
      //      const totalRow = ["", "", "", "", "", "", "", "", "Total", vm.totalAmount, ""];
      //      excelData.push(totalRow);
      const ws = XLSX.utils.aoa_to_sheet(excelData);
      const mergeRanges = [
        { s: { r: 0, c: 0 }, e: { r: 0, c: tableHeaders.length - 1 } },
        { s: { r: 1, c: 0 }, e: { r: 1, c: tableHeaders.length - 1 } },
        { s: { r: 2, c: 0 }, e: { r: 2, c: tableHeaders.length - 1 } },
        { s: { r: 3, c: 0 }, e: { r: 3, c: tableHeaders.length - 1 } },
        { s: { r: 4, c: 0 }, e: { r: 4, c: tableHeaders.length - 1 } },
        { s: { r: 5, c: 0 }, e: { r: 5, c: tableHeaders.length - 1 } },
        { s: { r: 6, c: 0 }, e: { r: 6, c: tableHeaders.length - 1 } },
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

    function getModuleName() {
      vm.loader = true;

      var requestData = {
        flag: "S",
        schemeCode: "",
         downloadType : 'N',
      };
      bondActualInterestRunCreditedReportService.getDetails(requestData).then(
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
    }

    function getDetails() {
      vm.loader = true;

      var requestData = {
        schemeCode: vm.schemeCode,
        fromDate: moment(vm.fromdate).format("DD-MMM-YYYY"),
        toDate: moment(vm.todate).format("DD-MMM-YYYY"),
        blaNo: vm.blaNo,
        panNo: vm.panNo,
        subscriptionId: vm.subscriptionId,
        flag: "D",
        pageNo: vm.pageNo,
        pageSize: vm.pageSize,
        searchValue: vm.searchText,
        sortOrder: vm.sortOrder,
        sortField: vm.sortField,
        downloadType : 'N',
      };
      bondActualInterestRunCreditedReportService.getDetails(requestData).then(
        function (response) {
          vm.loader = false;
          vm.DetailsList = response.data.result.list;
          vm.filteredRecords = response.data.result.list;




           vm.investmentAmount = vm.filteredRecords.reduce((sum, record) => sum + (record.investmentAmount ? parseFloat(record.investmentAmount) : 0), 0);


            vm.thresholdExceedAmount = vm.filteredRecords.reduce((sum, record) => sum + (record.thresholdExceedAmount ? parseFloat(record.thresholdExceedAmount) : 0), 0);
             vm.thresholdExceedTDSAmount = vm.filteredRecords.reduce((sum, record) => sum + (record.thresholdExceedTDSAmount ? parseFloat(record.thresholdExceedTDSAmount) : 0), 0);
               vm.exemptionAmount = vm.filteredRecords.reduce((sum, record) => sum + (record.exemptionAmount ? parseFloat(record.exemptionAmount) : 0), 0);
                                       vm.exemptionTDSAmount = vm.filteredRecords.reduce((sum, record) => sum + (record.exemptionTDSAmount ? parseFloat(record.exemptionTDSAmount) : 0), 0);
                vm.baseRateAmount = vm.filteredRecords.reduce((sum, record) => sum + (record.baseRateAmount ? parseFloat(record.baseRateAmount) : 0), 0);
                 vm.surchargeAmount = vm.filteredRecords.reduce((sum, record) => sum + (record.surchargeAmount ? parseFloat(record.surchargeAmount) : 0), 0);
                 vm.eduCessAmount = vm.filteredRecords.reduce((sum, record) => sum + (record.eduCessAmount ? parseFloat(record.eduCessAmount) : 0), 0);
                vm.tdsAmount = vm.filteredRecords.reduce((sum, record) => sum + (record.tdsAmount ? parseFloat(record.tdsAmount) : 0), 0);
                vm.tdsRecoveryAmount = vm.filteredRecords.reduce((sum, record) => sum + (record.tdsRecoveryAmount ? parseFloat(record.tdsRecoveryAmount) : 0), 0);
                 vm.tdsRefundAmount = vm.filteredRecords.reduce((sum, record) => sum + (record.tdsRefundAmount ? parseFloat(record.tdsRefundAmount) : 0), 0);



                                  vm.grossInterestAmount = vm.filteredRecords.reduce((sum, record) => sum + (record.grossInterestAmount ? parseFloat(record.grossInterestAmount) : 0), 0);
                                  vm.returnAmt = vm.filteredRecords.reduce((sum, record) => sum + (record.returnAmt ? parseFloat(record.returnAmt) : 0), 0);
                                  vm.reInitiatedAmt = vm.filteredRecords.reduce((sum, record) => sum + (record.reInitiatedAmt ? parseFloat(record.reInitiatedAmt) : 0), 0);


















          vm.records = response.data.result.list;
          vm.total = response.data.total;
          vm.maxPages = Math.ceil(response.data.total / vm.pageSize);
          vm.paginationControls();
          if (vm.DetailsList.length === 0) {
            vm.noDataFoundInSearch = true;
          } else {
            vm.noDataFoundInSearch = false;
          }
        },
        function (error) {
          vm.loader = false;
          if ((error.data.message == "No Data Found" || error.data.message == "NDF") && vm.searchText) {



       vm.pageNo ='0';
        vm.maxPages ='0';
        vm.total = '0';
         vm.showNext = false;
            vm.noDataFoundInSearch = true;
          } else if (error.data.message == "No Data Found" || error.data.message == "NDF") {
            vm.noDataFound = error.data.message;
          } else {
            handleErrorResponse(error);
            $state.go("bond_actual_interest_run_credited_report.inquiry");
          }
        }
      );
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

    vm.getPanTorch = function () {
      var requestData = {
        schemeCode: vm.schemeCode,
        panNo: vm.panNo,
        holderName: vm.HolderName,
        flag: "P",
        downloadType : 'N',
      };
      bondActualInterestRunCreditedReportService.getDetails(requestData).then(
        function (response) {
          vm.loader = false;
          vm.PanTorch = response.data.result.list;
          angular.element("#panModel").modal("toggle");
        },
        function (error) {
          vm.loader = false;
          console.log("error", error);
          handleErrorResponse(error);
        }
      );
    };

    vm.getBlaNo = function () {
      vm.loader = true;
      var requestData = {
        schemeCode: vm.schemeCode,
        holderName: vm.HolderName,
        blaNo: vm.blaNo,
        panNo: vm.panNo,
        flag: "B",
        downloadType : 'N',
      };
      bondActualInterestRunCreditedReportService.getDetails(requestData).then(
        function (response) {
          vm.loader = false;
          vm.blaDataList = response.data.result.list;
          angular.element("#blaModel").modal("toggle");
        },
        function (error) {
          vm.loader = false;
          handleErrorResponse(error);
        }
      );
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

    vm.getSubscriptionId = function () {
      vm.loader = true;

      var requestData = {
        panNo: vm.panNo,
        blaNo: vm.blaNo,
        subscriptionId: vm.subscriptionId,
        flag: "BR",
        downloadType : 'N',

      };
      bondActualInterestRunCreditedReportService.getDetails(requestData).then(
        function (response) {
          vm.loader = false;
          vm.subscriptionList = response.data.result.list;
          angular.element("#subscriptionModel").modal("toggle");
        },
        function (error) {
          vm.loader = false;
          handleErrorResponse(error);
        }
      );
    };
  }
})();
