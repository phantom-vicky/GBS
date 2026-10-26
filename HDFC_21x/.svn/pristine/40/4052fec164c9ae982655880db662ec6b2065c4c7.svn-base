(function () {
  "use strict";

  angular.module("gbmUltraApp").controller("bondRepaymentReportController", bondRepaymentReportController);

  bondRepaymentReportController.$inject = ["$scope", "$filter", "$state", "$stateParams", "$location", "$rootScope", "bondRepaymentReportService", "commonService"];

  function bondRepaymentReportController($scope, $filter, $state, $stateParams, $location, $rootScope, bondRepaymentReportService, commonService) {
    var vm = this;

    vm.currentDate = new Date();
    vm.currentDate = $filter("date")(vm.currentDate, "dd/MM/yyyy");
    vm.getModuleName = getModuleName;
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
    // Date validation
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

    if ($state.current.name == "bond_repayment_report.inquiry_details") {
      vm.schemeCode = $stateParams.obj.schemeCode;
      vm.schemeName = $stateParams.obj.schemeName;
      vm.fromDate = $stateParams.obj.fromDate;
      vm.toDate = $stateParams.obj.toDate;
        vm.getDetails();
    }

    vm.downloadReport = function () {
      vm.loader = true;
      var requestData = {
        schemeCode: vm.schemeCode,
        fromDate: $filter("date")(vm.fromDate, "dd-MMM-yyyy"),
        toDate: $filter("date")(vm.toDate, "dd-MMM-yyyy"),
        flag: "A",
        pageNo: 1,
        pageSize: vm.total,
        searchValue: vm.searchText,
      };

      if (vm.downloadType === "excel") {
            bondRepaymentReportService.getDetailsDownload(requestData).then(
                function (response) {
                  vm.loader = false;
                  vm.downloadModel = response.data.result.list;
                      if (vm.downloadType === "excel") {
//                        vm.downloadExcel();
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
                    $state.go("bond_repayment_report.inquiry");
                  }
                }
              );
      } else if(vm.downloadType!= "excel") {
    bondRepaymentReportService.getDetails(requestData).then(
            function (response) {
              vm.loader = false;
              vm.downloadModel = response.data.result.list;
              var holdingReferenceId = { referenceId: "MODE_OF_PAYMENT" };

              bondRepaymentReportService.getReferenceData(holdingReferenceId).then(
                function (holdingMappingResponse) {
                  var holdingMappings = holdingMappingResponse.data.result; // Response for mode of payment

                  var paymentMap = {};
                  holdingMappings.forEach(function (item) {
                    paymentMap[item.SUBCODE] = item.DESCRIPTION;
                  });

                  vm.downloadFilteredRecords = vm.downloadModel.map(function (record) {
                    return {
                      ...record,
                      principalRepayment: paymentMap[record.principalRepayment] || "",
                    };
                  });

                  if (vm.downloadType === "csv") {
                    vm.downloadCSV();
                  }
                  vm.downloadType = "";
                },
                function (error) {
                  console.error("Error fetching mode of payment descriptions:", error);
                }
              );
            },
            function (error) {
              vm.loader = false;
              if (error.data.message == "No Data Found" || error.data.message == "NDF") {
                vm.noDataFound = error.data.message;
              } else {
                handleErrorResponse(error);
                $state.go("bond_repayment_report.inquiry");
              }
            }
          );
      }


    };

    vm.downloadExcel = function () {
      const additionalHeaders = [
        ["Bond Repayment Report"],
        ["Sol ID: 101 / BRANCH OF THE BANK"],
        ["Name of the Bond Scheme: " + vm.schemeName],
        ["From Date: " + $filter("date")(vm.fromDate, "dd/MM/yyyy")], // From Date
        ["To Date: " + $filter("date")(vm.toDate, "dd/MM/yyyy")], // To Date
        [],
      ];

      const tableHeader = [
        "S.No",
        "Pan No",
        "BLA No.",
        "Holder Name",
        "Subscription ID",
        "Date of Subscription",
        "Subscription Amount (Rs)",
        "Gross Amount",
        "TDS Percentage",
        "TDS Amount",
        "Amount Repaid (Rs)",
        "Maturity Date",
        "Date of Repayment",
        "Mode of Interest Payment/Principal Repayment",
        "Operative Account Number",
        "DD/PO Account Number",
        "Bank Code",
        "Branch Code",
        "IFSC Code of the Credit Bank",
        "Name of the Payee",
        "Credit Account Number",
        "IFSC Code",
        "MICR Code",
      ];

      const tableData = vm.downloadFilteredRecords.map((record, index) => [
        index + 1,
        record.panNo,
        record.blaNo,
        record.holderName,
        record.subsId,
        record.dateOfSubscription,
        record.subsAmt,
        record.grossAmount,
        record.tdsPercentage,
        record.tdsAmount,
        record.amtRepaid,
        record.maturityDate,
        record.dateOfRepayment,
        record.principalRepayment,
        record.operativeAccountNumber,
        record.ddOrPoAccountNumber,
        record.bankCode,
        record.branchCode,
        record.ifscCodeOfCreditBank,
        record.nameOfPayee,
        record.creditAccountNumber,
        record.ifscCodeSecondary,
        record.micrCode,
      ]);

      const wsData = [...additionalHeaders, tableHeader, ...tableData];
      const ws = XLSX.utils.aoa_to_sheet(wsData);
      const wb = XLSX.utils.book_new();
      XLSX.utils.book_append_sheet(wb, ws, "Bond Repayment Report");
      XLSX.writeFile(wb, "Bond_Repayment_Report.xlsx");
    };

    vm.downloadCSV = function () {
      const mainHeader = "Bond Repayment Report";

      const additionalHeaders = [
        "Bank Name: INSPIRISY BANK LTD",
        "Sol ID: 101 / BRANCH OF THE BANK",
        "Name of the Bond Scheme: " + vm.schemeName,
        "From Date: " + $filter("date")(vm.fromDate, "dd/MM/yyyy"),
        "To Date: " + $filter("date")(vm.toDate, "dd/MM/yyyy"),
      ];

      const headers = [
        { title: "S.No", width: 10 },
        { title: "PAN No", width: 20 },
        { title: "BLA No.", width: 20 },
        { title: "Holder Name", width: 30 },
        { title: "Subscription ID", width: 20 },
        { title: "Date of Subscription", width: 25 },
        { title: "Subscription Amount (Rs)", width: 30 },
        { title: "Gross Amount", width: 50 },
        { title: "TDS Percentage", width: 30 },
        { title: "TDS Amount", width: 30 },
        { title: "Amount Repaid (Rs)", width: 30 },
        { title: "Maturity Date", width: 20 },
        { title: "Date of Repayment", width: 25 },
        { title: "Mode of Payment", width: 30 },
        { title: "Operative Account Number", width: 30 },
        { title: "DD/PO Account Number", width: 30 },
        { title: "Bank Code", width: 15 },
        { title: "Branch Code", width: 15 },
        { title: "IFSC Code of Credit Bank", width: 30 },
        { title: "Name of the Payee", width: 30 },
        { title: "Credit Account Number", width: 30 },
        { title: "IFSC Code", width: 20 },
        { title: "MICR Code", width: 20 },
        { title: "STATUS", width: 20 },
        { title: "RETURN DESCRIPTION", width: 20 }
      ];

      // Define column widths
      const columnWidths = headers.map((header) => header.width);

      function padString(str, width) {
        str = (str || "").toString();
        return str.length > width ? str.slice(0, width - 3) + "..." : str.padEnd(width, " ");
      }

      const formattedHeader = headers.map((header, index) => padString(header.title, columnWidths[index])).join("");

      const tableData = vm.downloadFilteredRecords.map((row, index) =>
        headers
          .map((col, colIndex) => {
            const value = [
              index + 1, // S.No
              row.panNo || "",
              row.blaNo || "",
              row.holderName || "",
              row.subsId || "",
              row.dateOfSubscription || "",
              row.subsAmt || "",
              row.grossAmount || "",
              row.tdsPercentage || "",
              row.tdsAmount || "",
              row.amtRepaid || "",
              row.maturityDate || "",
              row.dateOfRepayment || "",
              row.principalRepayment || "",
              row.operativeAccountNumber || "",
              row.ddOrPoAccountNumber || "",
              row.bankCode || "",
              row.branchCode || "",
              row.ifscCodeOfCreditBank || "",
              row.nameOfPayee || "",
              row.creditAccountNumber || "",
              row.ifscCodeSecondary || "",
              row.micrCode || "",
              row.status || "",
              row.returnDescription || ""
            ][colIndex];
            return value.toString().padEnd(col.width, " ");
          })
          .join("")
      );

      const totalsRow = [
        //                    padString("TOTAL", columnWidths[0]),
        padString("", columnWidths[1]),
        padString("", columnWidths[2]),
        padString("", columnWidths[3]),
        padString("", columnWidths[4]),
        padString("", columnWidths[5]),
        padString(vm.totalSubscriptionAmount, columnWidths[6]),
        padString(vm.totalGrossAmount, columnWidths[7]),
        padString("", columnWidths[8]),
        padString(vm.totalTdsAmount, columnWidths[9]),
        padString(vm.totalAmountRepaid, columnWidths[10]),
        padString("", columnWidths[11]),
        padString("", columnWidths[12]),
        padString("", columnWidths[13]),
        padString("", columnWidths[14]),
        padString("", columnWidths[15]),
        padString("", columnWidths[16]),
        padString("", columnWidths[17]),
        padString("", columnWidths[18]),
        padString("", columnWidths[19]),
        padString("", columnWidths[20]),
        padString("", columnWidths[21]),
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
      link.setAttribute("download", "Bond_Repayment_Report.txt");
      document.body.appendChild(link);
      link.click();
      document.body.removeChild(link);
    };

    function getModuleName() {
      vm.loader = true;
      var requestData = {
        schemeCode: "",
        flag: "S",
      };
      bondRepaymentReportService.getDetails(requestData).then(
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

      vm.totalInterestRupees = 0;
      vm.totalTdsAmount = 0;
      vm.totalNetInterestAmount = 0;

      var requestData = {
        schemeCode: vm.schemeCode,
        fromDate: $filter("date")(vm.fromDate, "dd-MMM-yyyy"),
        toDate: $filter("date")(vm.toDate, "dd-MMM-yyyy"),
        flag: "A",
        pageNo: vm.pageNo,
        pageSize: vm.pageSize,
        searchValue: vm.searchText,
        sortOrder: vm.sortOrder,
        sortField: vm.sortField,
      };

      bondRepaymentReportService.getDetails(requestData).then(
        function (response) {
          vm.loader = false;
          vm.model = response.data.result.list;
          vm.result = response.data.result.list;
          vm.filteredRecords = response.data.result.list;

          if (vm.model.length === 0) {
            vm.noDataFoundInSearch = true;
          } else {
            vm.noDataFoundInSearch = false;
          }
//          var holdingReferenceId = { referenceId: "MODE_OF_PAYMENT" };
//
//          bondRepaymentReportService.getReferenceData(holdingReferenceId).then(
//            function (holdingMappingResponse) {
//              var holdingMappings = holdingMappingResponse.data.result; // Response for mode of payment
//
//              var paymentMap = {};
//              holdingMappings.forEach(function (item) {
//                paymentMap[item.SUBCODE] = item.DESCRIPTION;
//              });
//
//              vm.filteredRecords = vm.model.map(function (record) {
//                return {
//                  ...record,
//                  principalRepayment: paymentMap[record.principalRepayment] || "",
//                };
//              });
//
//              vm.model = vm.model.map(function (record) {
//                return {
//                  ...record,
//                  principalRepayment: paymentMap[record.principalRepayment] || "",
//                };
//              });

              vm.total = response.data.total;
              vm.maxPages = Math.ceil(response.data.total / vm.pageSize);
              vm.paginationControls();
//            },
//            function (error) {
//              console.error("Error fetching mode of payment descriptions:", error);
//            }

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
            $state.go("bond_repayment_report.inquiry");
            vm.goPreviousState();
          }
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
  }
})();
