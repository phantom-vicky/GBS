(function () {
  "use strict";

  angular.module("gbmUltraApp").controller("bondInterestTrialRunController", bondInterestTrialRunController);

  bondInterestTrialRunController.$inject = ["$scope", "$filter", "$state", "$stateParams", "$location", "$rootScope", "bondInterestTrialRunService", "commonService"];

  function bondInterestTrialRunController($scope, $filter, $state, $stateParams, $location, $rootScope, bondInterestTrialRunService, commonService) {
    var vm = this;

    vm.year = new Date().getFullYear();

    vm.solDate = $rootScope.globals.currentUser.systemDate;
    vm.getDetails = getDetails;


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

    if ($state.current.name == "bond_interest_trial_run_eligible_accounts_report.inquiry_details") {
      vm.schemeCode = $stateParams.obj.schemeCode;
      vm.schemeName = $stateParams.obj.schemeName;
      vm.moduleName = $stateParams.obj.moduleName;
      vm.panNo = $stateParams.obj.panNo;
      vm.blaNum = $stateParams.obj.blaNum;
      vm.subscriptionId = $stateParams.obj.subscriptionId;
      vm.HolderName = $stateParams.obj.HolderName;
      vm.intMonth = $stateParams.obj.interestHalfYear;
      vm.year = $stateParams.obj.year;
      getDetails();
    }

    vm.downloadReport = function () {
          vm.loader = true;
          if(vm.downloadType===''){
          vm.loader = false;
          return false;
          }
if(null!=vm.downloadType && vm.downloadType === "MERGED"){
var requestData = {
            schemeCode: vm.schemeCode,
            name: vm.HolderName,
            panNo: vm.panNo,
            interestHalfYear: vm.intMonth,
            year: "2025",
            flag: "M",
            pageNo: "1",
            pageSize: vm.total,
            searchValue: vm.searchText,
          };
}else{
          var requestData = {
            schemeCode: vm.schemeCode,
            name: vm.HolderName,
            panNo: vm.panNo,
            interestHalfYear: vm.intMonth,
            year: "2025",
            flag: "T",
            pageNo: "1",
            pageSize: vm.total,
            searchValue: vm.searchText,
          };
}
          bondInterestTrialRunService.getDownload(requestData).then(
            function (response) {
              vm.loader = false;
              if(response.data.message=='SUCCESS') {
              vm.fileData = response.data.result.fileData;
               vm.fileName=response.data.result.fileName;
              saveAs(getBlobFromBase64String(vm.fileData), vm.fileName)
              swal("File Downloaded Successfully!", response.data.message, "success");
              }
             /* vm.downloadFilteredRecords = response.data.result.list;



               vm.investmentAmt = vm.downloadFilteredRecords.reduce((sum, record) => sum + (record.investmentAmt ? parseFloat(record.investmentAmt) : 0), 0);
                         vm.thresholdExceedAmount = vm.downloadFilteredRecords.reduce((sum, record) => sum + (record.thresholdExceedAmount ? parseFloat(record.thresholdExceedAmount) : 0), 0);
                          vm.thresholdExceedTdsAmount = vm.downloadFilteredRecords.reduce((sum, record) => sum + (record.thresholdExceedTdsAmount ? parseFloat(record.thresholdExceedTdsAmount) : 0), 0);
                           vm.exemptionAmount = vm.downloadFilteredRecords.reduce((sum, record) => sum + (record.exemptionAmount ? parseFloat(record.exemptionAmount) : 0), 0);
                            vm.exemptionTdsAmount = vm.downloadFilteredRecords.reduce((sum, record) => sum + (record.exemptionTdsAmount ? parseFloat(record.exemptionTdsAmount) : 0), 0);


                                        vm.baseRateAmt = vm.downloadFilteredRecords.reduce((sum, record) => sum + (record.baseRateAmt ? parseFloat(record.baseRateAmt) : 0), 0);
                                         vm.surchargeAmt = vm.downloadFilteredRecords.reduce((sum, record) => sum + (record.surchargeAmt ? parseFloat(record.surchargeAmt) : 0), 0);
                                          vm.eduCessAmt = vm.downloadFilteredRecords.reduce((sum, record) => sum + (record.eduCessAmt ? parseFloat(record.eduCessAmt) : 0), 0);

                                           vm.tdsAmount = vm.downloadFilteredRecords.reduce((sum, record) => sum + (record.tdsAmount ? parseFloat(record.tdsAmount) : 0), 0);
                             vm.tdsRecoveryAmount = vm.downloadFilteredRecords.reduce((sum, record) => sum + (record.tdsRecoveryAmount ? parseFloat(record.tdsRecoveryAmount) : 0), 0);
                             vm.tdsRefundAmount = vm.downloadFilteredRecords.reduce((sum, record) => sum + (record.tdsRefundAmount ? parseFloat(record.tdsRefundAmount) : 0), 0);
                              vm.grossInterestAmount = vm.downloadFilteredRecords.reduce((sum, record) => sum + (record.grossInterestAmount ? parseFloat(record.grossInterestAmount) : 0), 0);
                               vm.intPaidAmt = vm.downloadFilteredRecords.reduce((sum, record) => sum + (record.intPaidAmt ? parseFloat(record.intPaidAmt) : 0), 0);


                   if (vm.downloadType === "UNMERGED") {
                     vm.downloadExcel();
                   }if (vm.downloadType === "MERGED") {
                     vm.downloadExcelMerged();
                   }
                   if (vm.downloadType === "csv") {
                     vm.downloadCSV();
                   }*/
                   vm.downloadType = "";
            },
            function (error) {
              vm.loader = false;
              vm.downloadType = "";
              if ((error.data.message == "No Data Found" || error.data.message == "NDF") && vm.searchText) {
                  swal("Warning!",error.data.message, "warning");
                } else if (error.data.message == "No Data Found" || error.data.message == "NDF") {
                  swal("Warning!",error.data.message, "warning");
                }else {
                handleErrorResponse(error);
                $state.go("bond_interest_trial_run_eligible_accounts_report.inquiry");
              }
            }
          );
    };

    vm.downloadExcel = function () {
      vm.loader = false;
      vm.message = "Report generated successfully"; // Set an appropriate message
      vm.checkResult = vm.ListOfDtos; // Assume this is the response data

      if (vm.checkResult && vm.checkResult.length > 0) {
        // Create a new workbook and worksheet
        let workbook = XLSX.utils.book_new();
        let sheetData = [];

        // Create the title row
        sheetData.push([]);
        sheetData[0][6] = "Bond Interest Trial Run-Eligible Accounts Report";

        sheetData.push([]);
        sheetData[1][1] = "Bank Name:";
        sheetData[1][2] = "INSPIRISY BANK LTD";

        sheetData.push([]);
        sheetData[2][1] = "Sol ID:";
        sheetData[2][2] = "101 / BRANCH OF THE BANK";

        sheetData.push([]);
        sheetData[3] = [];
        sheetData[3][1] = "Name of the Bond Scheme:";
        sheetData[3][2] = vm.schemeName;

        let mainHeaders = [
          "",
          "Basic Details From BLA cum Subscription",
          "",
          "",
          "",
          "",
          "",
          "",
          "",
          "",
          "",
          "",
          "",
          "",
          "",
          "",
          "",
          "",
          "",
          "",
          "",
          "",
          "OTHER SCHEMES INTEREST RELATED INFO",
          "",
          "",
          "",
          "",
          "",
          "",
          "",
          "PAN STATUS",
          "",
          "",
          "TDS EXEMPTION DETAILS",
          "",
          "",
          "",
          "",
          "",
          "",
          "FRSB INTEREST PAYOUT DETAILS",
          "",
        ];
        sheetData.push(mainHeaders);

        // Add sub-header row
        var subHeaders = [
          "S.No",
          "Mode of Payment",
          "Branch Ref No",
          "Bla_no",
          "Ref No",
          "Appl Name 1",
          "Appl Name 2",
          "Appl Name 3",
          "Address 1",
          "Address 2",
          "Recipient Name",
          "Recipient Parent Name",
          "Pin Code",
          "Phone No",
          "Application Status",
          "Investment Amount",
          "CFD",
          "Mat Date",
          "Account No",
          "Subscription Count",
          "Bank Sort Code",
          "Bank Name",
          "PAN No",
          "8% Interest Payout",
          "Non Cumulative Interest",
          "Cumulative Interest",
          "7.75% Interest Payout",
          "Non Cumulative Interest 775",
          "Cumulative Interest 775",
          "Total",
          "In-operative PAN",
          "Non-filers",
          "Age",
          "Taxable Limit",
          "Waiver Document",
          "Waiver Document Ref No",
          "RRB-TDS Rate",
          "Threshold Exceed Amount",
          "Threshold Exceed TDS Amount",
          "Exemption Rate %",
          "Exemption Amount",
          "Exemption TDS Amount",
          "Surcharge %",
          "Edu Cess %",
          "Base Rate Amount",
          "Surcharge Amount",
          "Edu Cess Amount",
          "TDS Amount",
          "TDS Recovery Amount",
          "TDS Refund Amount",
          "Interest Rate",
          "Last Interest Paid",
          "Pending Interest",
          "Current Half-Yearly Interest",
          "Future Interest",
          "Gross Interest Amount",
          "Net Interest Paid Amount",
        ];

        sheetData.push(subHeaders);

        // Populate data rows
        vm.downloadFilteredRecords.forEach((item, index) => {
          let row = [
            index + 1,
            item.modeOfPayment,
            item.branchRefNo,
            item.blaNo,
            item.refNo,
            item.applName1,
            item.applName2,
            item.applName3,
            item.addr1,
            item.addr2,
            item.recName,
            item.recParentName,
            item.pinCode,
            item.phoneNo,
            item.applStatus,
            item.investmentAmt,
            item.cfd,
            item.matDate,
            item.accountNo,
            item.subscriptionCount,
            item.bankSortCode,
            item.bankName,
            item.panNo,
            item.interestPayout8,
            item.nonCumulativeInt8,
            item.cumulatinveInt8,
            item.interestPayout7,
            item.nonCumulativeInt7,
            item.cumulativeInt7,
            item.total,
            item.inOperativePan,
            item.nonFilers,
            item.age,
            item.taxableLimit,
            item.waiverDocument,
            item.waiverDocumentRefNo,
            item.rrbTdsRate,
            item.thresholdExceedAmount,
            item.thresholdExceedTdsAmount,
            item.exemptionRatePercent,
            item.exemptionAmount,
            item.exemptionTdsAmount,
            item.surchargePercent,
            item.eduCessPercent,
            item.baseRateAmt,
            item.surchargeAmt,
            item.eduCessAmt,
            item.tdsAmount,
            item.tdsRecoveryAmount,
            item.tdsRefundAmount,
            item.intRate,
            item.lastIntPaid,
            item.pendingInterest,
            item.currentHalfYearlyInterest,
            item.futureInterest,
            item.grossInterestAmount,
            item.intPaidAmt,
          ];
          sheetData.push(row);
        });







        var subFooter = [


                 "",
                 "",
                 "",
                 "",
                 "",
                 "",
                 "",
                 "",
                 "",
                 "",
                 "",
                 "",
                 "",
                 "",
                 "",
                 [vm.investmentAmt],
                 "",
                 "",
                 "",
                 "",
                 "",
                 "",
                 "",
                 "",
                 "",
                 "",
                 "",
                 "",
                 "",
                 "",
                 "",
                 "",
                 "",
                 "",
                  "",
                  "",
                   "",

                 [vm.thresholdExceedAmount],
                 [vm.thresholdExceedTdsAmount],
                 "",
                 [vm.exemptionAmount],
                 [vm.exemptionTdsAmount],
                 "",
                 "",
                 [vm.baseRateAmt],
                 [vm.surchargeAmt],
                 [vm.eduCessAmt],
                 [vm.tdsAmount],
                 [vm.tdsRecoveryAmount],
                 [vm.tdsRefundAmount],
                 "",
                 "",
                 "",
                 "",
                 "",
                 [vm.grossInterestAmount],
                 [vm.intPaidAmt],
               ];

               sheetData.push(subFooter);
        // Create and append sheet to workbook
        let worksheet = XLSX.utils.aoa_to_sheet(sheetData);
        XLSX.utils.book_append_sheet(workbook, worksheet, "Bond Report");

        // Write the workbook and trigger download
        XLSX.writeFile(workbook, "Bond_Interest_Trial_Run-Eligible Accounts Report.xlsx");
      }
    };
vm.downloadExcelMerged = function () {
      vm.loader = false;
      vm.message = "Report generated successfully"; // Set an appropriate message
      vm.checkResult = vm.ListOfDtos; // Assume this is the response data

      if (vm.checkResult && vm.checkResult.length > 0) {
        // Create a new workbook and worksheet
        let workbook = XLSX.utils.book_new();
        let sheetData = [];

        // Create the title row
        sheetData.push([]);
        sheetData[0][6] = "Bond Interest Trial Run-Eligible Accounts Report";

        sheetData.push([]);
        sheetData[1][1] = "Bank Name:";
        sheetData[1][2] = "INSPIRISY BANK LTD";

        sheetData.push([]);
        sheetData[2][1] = "Sol ID:";
        sheetData[2][2] = "101 / BRANCH OF THE BANK";

        sheetData.push([]);
        sheetData[3] = [];
        sheetData[3][1] = "Name of the Bond Scheme:";
        sheetData[3][2] = vm.schemeName;

        let mainHeaders = [
          "",
          "Basic Details From BLA cum Subscription",
          "",
          "",
          "",
          "",
          "",
          "",
          "",
          "",
          "",
          "",
          "",
          "",
          "",
          "",
          "",
          "",
          "",
          "",
          "",
          "OTHER SCHEMES INTEREST RELATED INFO",
          "",
          "",
          "",
          "",
          "",
          "",
          "",
          "PAN STATUS",
          "",
          "",
          "TDS EXEMPTION DETAILS",
          "",
          "",
          "",
          "",
          "",
          "",
          "FRSB INTEREST PAYOUT DETAILS",
          "",
        ];
        sheetData.push(mainHeaders);

        // Add sub-header row
        var subHeaders = [
          "S.No",
          "Mode of Payment",
          "Bla_no",
          "Ref No",
          "Appl Name 1",
          "Appl Name 2",
          "Appl Name 3",
          "Address 1",
          "Address 2",
          "Recipient Name",
          "Recipient Parent Name",
          "Pin Code",
          "Phone No",
          "Application Status",
          "Investment Amount",
          "CFD",
          "Mat Date",
          "Account No",
          "Subscription Count",
          "Bank Sort Code",
          "Bank Name",
          "PAN No",
          "8% Interest Payout",
          "Non Cumulative Interest",
          "Cumulative Interest",
          "7.75% Interest Payout",
          "Non Cumulative Interest 775",
          "Cumulative Interest 775",
          "Total",
          "In-operative PAN",
          "Non-filers",
          "Age",
          "Taxable Limit",
          "Waiver Document",
          "Waiver Document Ref No",
          "RRB-TDS Rate",
          "Threshold Exceed Amount",
          "Threshold Exceed TDS Amount",
          "Exemption Rate %",
          "Exemption Amount",
          "Exemption TDS Amount",
          "Surcharge %",
          "Edu Cess %",
          "Base Rate Amount",
          "Surcharge Amount",
          "Edu Cess Amount",
          "TDS Amount",
          "TDS Recovery Amount",
          "TDS Refund Amount",
          "Interest Rate",
          "Last Interest Paid",
          "Pending Interest",
          "Current Half-Yearly Interest",
          "Future Interest",
          "Gross Interest Amount",
          "Net Interest Paid Amount",
        ];

        sheetData.push(subHeaders);

        // Populate data rows
        vm.downloadFilteredRecords.forEach((item, index) => {
          let row = [
            index + 1,
            item.modeOfPayment,
            item.blaNo,
            item.refNo,
            item.applName1,
            item.applName2,
            item.applName3,
            item.addr1,
            item.addr2,
            item.recName,
            item.recParentName,
            item.pinCode,
            item.phoneNo,
            item.applStatus,
            item.investmentAmt,
            item.cfd,
            item.matDate,
            item.accountNo,
            item.subscriptionCount,
            item.bankSortCode,
            item.bankName,
            item.panNo,
            item.interestPayout8,
            item.nonCumulativeInt8,
            item.cumulatinveInt8,
            item.interestPayout7,
            item.nonCumulativeInt7,
            item.cumulativeInt7,
            item.total,
            item.inOperativePan,
            item.nonFilers,
            item.age,
            item.taxableLimit,
            item.waiverDocument,
            item.waiverDocumentRefNo,
            item.rrbTdsRate,
            item.thresholdExceedAmount,
            item.thresholdExceedTdsAmount,
            item.exemptionRatePercent,
            item.exemptionAmount,
            item.exemptionTdsAmount,
            item.surchargePercent,
            item.eduCessPercent,
            item.baseRateAmt,
            item.surchargeAmt,
            item.eduCessAmt,
            item.tdsAmount,
            item.tdsRecoveryAmount,
            item.tdsRefundAmount,
            item.intRate,
            item.lastIntPaid,
            item.pendingInterest,
            item.currentHalfYearlyInterest,
            item.futureInterest,
            item.grossInterestAmount,
            item.intPaidAmt,
          ];
          sheetData.push(row);
        });







        var subFooter = [


                 "",
                 "",
                 "",
                 "",
                 "",
                 "",
                 "",
                 "",
                 "",
                 "",
                 "",
                 "",
                 "",
                 "",
                 "",
                 [vm.investmentAmt],
                 "",
                 "",
                 "",
                 "",
                 "",
                 "",
                 "",
                 "",
                 "",
                 "",
                 "",
                 "",
                 "",
                 "",
                 "",
                 "",
                 "",
                 "",
                  "",
                  "",
                   "",

                 [vm.thresholdExceedAmount],
                 [vm.thresholdExceedTdsAmount],
                 "",
                 [vm.exemptionAmount],
                 [vm.exemptionTdsAmount],
                 "",
                 "",
                 [vm.baseRateAmt],
                 [vm.surchargeAmt],
                 [vm.eduCessAmt],
                 [vm.tdsAmount],
                 [vm.tdsRecoveryAmount],
                 [vm.tdsRefundAmount],
                 "",
                 "",
                 "",
                 "",
                 "",
                 [vm.grossInterestAmount],
                 [vm.intPaidAmt],
               ];

               sheetData.push(subFooter);
        // Create and append sheet to workbook
        let worksheet = XLSX.utils.aoa_to_sheet(sheetData);
        XLSX.utils.book_append_sheet(workbook, worksheet, "Bond Report");

        // Write the workbook and trigger download
        XLSX.writeFile(workbook, "Bond_Interest_Trial_Run-Eligible Accounts Report.xlsx");
      }
    };
    vm.downloadCSV = function () {
      const additionalHeaders = [
        "Bond Interest Trial Run - Eligible Accounts Report",
        "Bank Name: INSPIRISY BANK LTD",
        "Sol ID : 101 / BRANCH OF THE BANK",
        "Name of the Bond Scheme: " + vm.schemeName,

        "",
      ];

      const mainHeaders = [
        "Basic Details From BLA cum Subscription".padEnd(22 * 10, " "),
        "OTHER SCHEMES INTEREST RELATED INFO".padEnd(8 * 10, " "),
        "PAN STATUS".padEnd(3 * 10, " "),
        "TDS EXEMPTION DETAILS".padEnd(10 * 10, " "),
        "FRSB INTEREST PAYOUT DETAILS".padEnd(5 * 10, " "),
      ];

      const subHeaders = [
        "S.No",
        "Mode of Payment",
        "Branch Ref No",
        "BLA No",
        "Ref No",
        "Appl Name 1",
        "Appl Name 2",
        "Appl Name 3",
        "Address 1",
        "Address 2",
        "Recipient Name",
        "Recipient Parent Name",
        "Pin Code",
        "Phone No",
        "Application Status",
        "Investment Amount",
        "CFD",
        "Mat Date",
        "Account No",
        "Subscription Count",
        "Bank Sort Code",
        "Bank Name",
        "PAN No",
        "8% Interest Payout",
        "Non Cumulative Interest",
        "Cumulative Interest",
        "7.75% Interest Payout",
        "Non Cumulative Interest 775",
        "Cumulative Interest 775",
        "Total",
        "In-operative PAN",
        "Non-filers",
        "Age",
        "Taxable Limit",
        "Waiver Document",
        "Waiver Document Ref No",
        "RRB-TDS Rate",
        "Threshold Exceed Amount",
        "Threshold Exceed TDS Amount",
        "Exemption Rate %",
        "Exemption Amount",
        "Exemption TDS Amount",
        "Surcharge %",
        "Edu Cess %",
        "Base Rate Amount",
        "Surcharge Amount",
        "Edu Cess Amount",
        "TDS Amount",
        "TDS Recovery Amount",
        "TDS Refund Amount",
        "Interest Rate",
        "Last Interest Paid",
        "Pending Interest",
        "Current Half-Yearly Interest",
        "Future Interest",
        "Gross Interest Amount",
        "Net Interest Paid Amount",
      ];

      function padString(text, width) {
        return String(text).padEnd(width, " ");
      }

      const columnWidths = [10, 20, 20, 20, 20, 20, 20, 20, 40, 50, 30, 30, 30, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 30, 30, 30, 30, 30, 30, 20, 20, 20, 20, 20, 20, 30, 30, 30, 30, 30, 30, 30, 20, 20, 20, 20, 20, 20, 20, 30, 30, 40, 40, 40, 40];

      const formattedHeader = subHeaders.map((header, index) => padString(header, columnWidths[index])).join("");

      const tableData = vm.downloadFilteredRecords.map((record, index) =>
        [
          padString(index + 1, columnWidths[0]),
          padString(record.modeOfPayment || "", columnWidths[1]),
          padString(record.branchRefNo || "", columnWidths[2]),
          padString(record.blaNo || "", columnWidths[3]),
          padString(record.refNo || "", columnWidths[4]),
          padString(record.applName1 || "", columnWidths[5]),
          padString(record.applName2 || "", columnWidths[6]),
          padString(record.applName3 || "", columnWidths[7]),
          padString(record.addr1 || "", columnWidths[8]),
          padString(record.addr2 || "", columnWidths[9]),
          padString(record.recName || "", columnWidths[10]),
          padString(record.recParentName || "", columnWidths[11]),
          padString(record.pinCode || "", columnWidths[12]),
          padString(record.phoneNo || "", columnWidths[13]),
          padString(record.applStatus || "", columnWidths[14]),
          padString(record.investmentAmt || "", columnWidths[15]),
          padString(record.cfd || "", columnWidths[16]),
          padString(record.matDate || "", columnWidths[17]),
          padString(record.accountNo || "", columnWidths[18]),
          padString(record.subscriptionCount || "", columnWidths[19]),
          padString(record.bankSortCode || "", columnWidths[20]),
          padString(record.bankName || "", columnWidths[21]),
          padString(record.panNo || "", columnWidths[22]),
          padString(record.interestPayout8 || "", columnWidths[23]),
          padString(record.nonCumulativeInt8 || "", columnWidths[24]),
          padString(record.cumulatinveInt8 || "", columnWidths[25]),
          padString(record.interestPayout7 || "", columnWidths[26]),
          padString(record.nonCumulativeInt7 || "", columnWidths[27]),
          padString(record.cumulativeInt7 || "", columnWidths[28]),
          padString(record.total || "", columnWidths[29]),
          padString(record.inOperativePan || "", columnWidths[30]),
          padString(record.nonFilers || "", columnWidths[31]),
          padString(record.age || "", columnWidths[32]),
          padString(record.taxableLimit || "", columnWidths[33]),
          padString(record.waiverDocument || "", columnWidths[34]),
          padString(record.waiverDocumentRefNo || "", columnWidths[35]),
          padString(record.rrbTdsRate || "", columnWidths[36]),
          padString(record.thresholdExceedAmount || "", columnWidths[37]),
          padString(record.thresholdExceedTdsAmount || "", columnWidths[38]),
          padString(record.exemptionRatePercent || "", columnWidths[39]),
          padString(record.exemptionAmount || "", columnWidths[40]),
          padString(record.exemptionTdsAmount || "", columnWidths[41]),
          padString(record.surchargePercent || "", columnWidths[42]),
          padString(record.eduCessPercent || "", columnWidths[43]),
          padString(record.baseRateAmt || "", columnWidths[44]),
          padString(record.surchargeAmt || "", columnWidths[45]),
          padString(record.eduCessAmt || "", columnWidths[46]),
          padString(record.tdsAmount || "", columnWidths[47]),
          padString(record.tdsRecoveryAmount || "", columnWidths[48]),
          padString(record.tdsRefundAmount || "", columnWidths[49]),
          padString(record.intRate || "", columnWidths[50]),
          padString(record.lastIntPaid || "", columnWidths[51]),
          padString(record.pendingInterest || "", columnWidths[52]),
          padString(record.currentHalfYearlyInterest || "", columnWidths[53]),
          padString(record.futureInterest || "", columnWidths[54]),
          padString(record.grossInterestAmount || "", columnWidths[55]),
          padString(record.intPaidAmt || "", columnWidths[56]),
        ].join("")
      );

      const textData = [...additionalHeaders, mainHeaders, formattedHeader, ...tableData].join("\n");
      const textContent = "data:text/plain;charset=utf-8," + encodeURIComponent(textData);
      const link = document.createElement("a");
      link.setAttribute("href", textContent);
      link.setAttribute("download", "Bond_Interest_Trial_Run_Report.txt");
      document.body.appendChild(link);
      link.click();
      document.body.removeChild(link);
    };

    vm.getModuleName = function () {
      vm.loader = true;

      var requestData = {
        schemeCode: "",
        flag: "S",
      };
      bondInterestTrialRunService.getDetails(requestData).then(
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

    vm.getPanTorch = function () {
      var requestData = {
        schemeCode: vm.schemeCode,
        panNo: vm.panNo,
        name: vm.HolderName,
        flag: "P",
      };
      bondInterestTrialRunService.getDetails(requestData).then(
        function (response) {
          vm.loader = false;
          vm.PanTorch = response.data.result.list;
          angular.element("#panModel").modal("toggle");
        },
        function (error) {
          vm.loader = false;
          handleErrorResponse(error);
        }
      );
    }




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

    function getDetails() {
      vm.loader = true;

      var requestData = {
        schemeCode: vm.schemeCode,
        name: vm.HolderName,
        panNo: vm.panNo,
        interestHalfYear: vm.intMonth,
        year: "2025",
        flag: "T",
         pageNo: vm.pageNo,
                pageSize: vm.pageSize,
                searchValue: vm.searchText,
                sortOrder: vm.sortOrder,
                sortField: vm.sortField,
      };

      bondInterestTrialRunService.getDetails(requestData).then(
        function (response) {
          vm.loader = false;

          vm.ListOfDtos = response.data.result.list;
          vm.filteredRecords = response.data.result.list;

          vm.investmentAmt = vm.filteredRecords.reduce((sum, record) => sum + (record.investmentAmt ? parseFloat(record.investmentAmt) : 0), 0);
           vm.thresholdExceedAmount = vm.filteredRecords.reduce((sum, record) => sum + (record.thresholdExceedAmount ? parseFloat(record.thresholdExceedAmount) : 0), 0);
            vm.thresholdExceedTdsAmount = vm.filteredRecords.reduce((sum, record) => sum + (record.thresholdExceedTdsAmount ? parseFloat(record.thresholdExceedTdsAmount) : 0), 0);
             vm.exemptionAmount = vm.filteredRecords.reduce((sum, record) => sum + (record.exemptionAmount ? parseFloat(record.exemptionAmount) : 0), 0);
              vm.exemptionTdsAmount = vm.filteredRecords.reduce((sum, record) => sum + (record.exemptionTdsAmount ? parseFloat(record.exemptionTdsAmount) : 0), 0);


                          vm.baseRateAmt = vm.filteredRecords.reduce((sum, record) => sum + (record.baseRateAmt ? parseFloat(record.baseRateAmt) : 0), 0);
                           vm.surchargeAmt = vm.filteredRecords.reduce((sum, record) => sum + (record.surchargeAmt ? parseFloat(record.surchargeAmt) : 0), 0);
                            vm.eduCessAmt = vm.filteredRecords.reduce((sum, record) => sum + (record.eduCessAmt ? parseFloat(record.eduCessAmt) : 0), 0);

                             vm.tdsAmount = vm.filteredRecords.reduce((sum, record) => sum + (record.tdsAmount ? parseFloat(record.tdsAmount) : 0), 0);
               vm.tdsRecoveryAmount = vm.filteredRecords.reduce((sum, record) => sum + (record.tdsRecoveryAmount ? parseFloat(record.tdsRecoveryAmount) : 0), 0);
               vm.tdsRefundAmount = vm.filteredRecords.reduce((sum, record) => sum + (record.tdsRefundAmount ? parseFloat(record.tdsRefundAmount) : 0), 0);
                vm.grossInterestAmount = vm.filteredRecords.reduce((sum, record) => sum + (record.grossInterestAmount ? parseFloat(record.grossInterestAmount) : 0), 0);
                 vm.intPaidAmt = vm.filteredRecords.reduce((sum, record) => sum + (record.intPaidAmt ? parseFloat(record.intPaidAmt) : 0), 0);
















          vm.total = response.data.total;
                    vm.maxPages = Math.ceil(response.data.total / vm.pageSize);
                    vm.paginationControls();
                    if (vm.ListOfDtos.length === 0) {
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
                    }else {
            handleErrorResponse(error);
            $state.go("bond_interest_trial_run_eligible_accounts_report.inquiry");
          }
        }
      );
    }

    vm.fromDateVal = function () {
      vm.fromDateOptions = {
        maxDate: convertToDateObject(),
        showWeeks: false,
      };
    }

    vm.toDateVal=function (fromDate) {
      vm.toDateOptions = {
        minDate: fromDate,
        maxDate: convertToDateObject(),
        showWeeks: false,
      };
    }
  }
})();
