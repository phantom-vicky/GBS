(function () {
  "use strict";

  angular.module("gbmUltraApp").controller("BrokerageClaimController", BrokerageClaimController);

  BrokerageClaimController.$inject = [
    "$scope",
    "$filter",
    "$state",
    "$stateParams",
    "$location",
    "$rootScope",
    "$timeout",
    "BrokerageClaimService",
    "commonService",
  ];

  function BrokerageClaimController($scope, $filter, $state, $stateParams, $location, $rootScope, $timeout, BrokerageClaimService, commonService) {
    var vm = this;
    vm.getModuleName = getModuleName;
    vm.loginSolId = $rootScope.globals.currentUser.solId;
    vm.loginSolDesc = $rootScope.globals.currentUser.solDesc;
    vm.currentDate = new Date();
    vm.solDate = $filter("date")($rootScope.globals.currentUser.systemDate, "dd/MM/yyyy");
    vm.brokeragencyTorch = brokeragencyTorch;
    vm.getDetailspage = getDetailspage;

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
    vm.searchValue = "";
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
      vm.getDetailspage();
    };

    vm.pageNo = 1;
    vm.pageSize = 5;

    vm.goPreviousPage = function () {
      vm.pageNo = parseInt(vm.pageNo) - 1;
      vm.getDetailspage();
    };

    vm.goNextPage = function () {
      vm.pageNo = parseInt(vm.pageNo) + 1;
      vm.getDetailspage();
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

    vm.updateColumnHeaders = function () {
      if (vm.fromdate) {
        let fromDate = new Date(vm.fromdate);
        let monthYearFull = fromDate.toLocaleString("en-us", { month: "long", year: "numeric" });
        let monthYearShort = fromDate.toLocaleString("en-us", { month: "short", year: "2-digit" }).replace(" ", "-");

        if (vm.todate) {
          let toDate = new Date(vm.todate);
          let toMonthYearFull = toDate.toLocaleString("en-us", { month: "long", year: "numeric" });
          let toMonthYearShort = toDate.toLocaleString("en-us", { month: "short", year: "2-digit" }).replace(" ", "-");

          vm.brokerageClaimHeader = `Brokerage Claim for ${monthYearFull} - ${toMonthYearFull}`;
          vm.brokeragePaymentHeader = `Brokerage Payment for ${monthYearFull} - ${toMonthYearFull}`;
          vm.commissionAmountHeader = `Commission amount for ${monthYearShort} - ${toMonthYearShort}`;
          vm.brokeragetoBank = `Brokerage to Bank for ${monthYearShort} - ${toMonthYearShort}`;
          vm.brokerageAmount = `Brokerage amount for ${monthYearShort} - ${toMonthYearShort}`;
        } else {
          vm.brokerageClaimHeader = `Brokerage Claim for ${monthYearFull}`;
          vm.brokeragePaymentHeader = `Brokerage Payment for ${monthYearFull}`;
          vm.commissionAmountHeader = `Commission amount for ${monthYearShort}`;
          vm.brokeragetoBank = `Brokerage to Bank for ${monthYearShort}`;
          vm.brokerageAmount = `Brokerage amount for ${monthYearShort}`;
        }

        console.log("Updated headers:", vm.brokerageClaimHeader, vm.brokeragePaymentHeader);

        // Apply scope changes to update UI
        if (!$scope.$$phase) {
          $scope.$apply();
        }
      }
    };

    $scope.$watch("vm.fromdate", function (newValue) {
      if (newValue) {
        console.log("From Date changed:", newValue);
        vm.updateColumnHeaders();
      }
    });

    $scope.$watch("vm.todate", function (newValue) {
      if (newValue) {
        console.log("To Date changed:", newValue);
        vm.updateColumnHeaders();
      }
    });

    vm.downloadReport = function () {
      vm.loader = true;
      var requestData = {
        schemeCode: vm.schemeCode,
        fromDate: $filter("date")(vm.fromdate, "dd-MMM-yyyy"),
        toDate: $filter("date")(vm.todate, "dd-MMM-yyyy"),
        brokerAgencyCode: vm.brokerAgencyCode,
        flag: "Y",
        pageNo: 1,
        pageSize: vm.total,
        searchValue: vm.searchValue,
      };

       if (vm.downloadType === "excel") {
             BrokerageClaimService.detailsPageDownload(requestData).then(
                    function (response) {
                      vm.loader = false;
                      vm.DetailsList1 = response.data.result.list1;

                            if (vm.downloadType === "excel") {
//                              vm.downloadExcel();
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
                        $state.go("brokerage_claim_monthly_broker_wise_income&expense.inquiry");
                      }
                    }
                  );
       } else if(vm.downloadType!= "excel") {
                BrokerageClaimService.detailsPage(requestData).then(
                           function (response) {
                             vm.loader = false;

                             vm.DetailsList1 = response.data.result.list1;
                             BrokerageClaimService.getReferenceData({ referenceId: "MODE_OF_PAYMENT" }).then(function (response) {
                               var paymentModeDesc = {};
                               response.data.result.forEach(function (item) {
                                 paymentModeDesc[item.SUBCODE] = item.DESCRIPTION;
                               });

                               BrokerageClaimService.getReferenceData({ referenceId: "STATES OF INDIA" }).then(function (response) {
                                 var stateMap = {};
                                 response.data.result.forEach(function (item) {
                                   stateMap[item.SUBCODE] = item.DESCRIPTION;
                                 });
                                 BrokerageClaimService.getReferenceData({ referenceId: "STATES OF INDIA" }).then(function (response) {
                                   var stateMap1 = {};
                                   response.data.result.forEach(function (item) {
                                     stateMap1[item.SUBCODE] = item.DESCRIPTION;
                                   });

                                   vm.filteredRecord = vm.DetailsList1.map(function (record) {
                                     return {
                                       ...record,
                                       paymentMode: paymentModeDesc[record.paymentMode] || "",
                                       state: stateMap[record.state] || "",
                                       state1: stateMap[record.state1],
                                     };
                                   });
                                   if (vm.downloadType === "excel") {
                                     vm.downloadExcel();
                                   } else if (vm.downloadType === "csv") {
                                     vm.downloadCSV();
                                   } else if (vm.downloadType === "pdf") {
                                     vm.downloadPDF();
                                   }
                                   vm.downloadType = "";
                                   //
                                 });
                               });
                             });
                           },
                           function (error) {
                             vm.loader = false;
                             if (error.data.message == "No Data Found" || error.data.message == "NDF") {
                               vm.noDataFound = error.data.message;
                             } else {
                               handleErrorResponse(error);
                               $state.go("brokerage_claim_monthly_broker_wise_income&expense.inquiry");
                             }
                           }
                         );
       }

    };

    vm.downloadExcel = function () {
      const additionalDetails = [
        ["Brokerage Claim_Monthly_Broker Wise Income & Expense"],
        ["Bank Name: INSPIRISY BANK LTD"],
        ["Sol ID: 101 / BRANCH OF THE BANK"],
        ...(vm.brokerAgencyCode ? [["Broker Agency Code: " + vm.brokerAgencyCode]] : []),
        ["Name of the Bond Scheme: " + vm.schemeName],
        ["From Date: " + $filter("date")(vm.fromdate, "dd-MM-yyyy") + " To Date: " + $filter("date")(vm.todate, "dd-MM-yyyy")],
        [],
      ];

      const headers = [
        [
          "S.No.",
          "BLA_NO",
          "INVEST_AMT",
          "CAS_TRANSFER_DATE",
          "MATURITY_DATE",
          "SCHEME",
          "CFD",
          "BRANCH_REF_NO",
          "Pan Number",
          "Bank Name",
          "Advice_No",
          "APPLICANT_NAME",
          "ADDR1",
          "ADDR2",
          "CITY",
          "STATE",
          "PIN_CODE",
          "BRANCH_NAME",
          "RCPT_DATE",
          "GROUP_CODE",
          "HDFC_BROKER_CODE",
          "BROKER_NAME",
          "ADDR1",
          "ADDR2",
          "CITY",
          "STATE",
          "PIN_CODE",
          "PHONE",
          "PAYMENT_MODE",
          "ACCOUNT_TYPE_CODE",
          "BANK_ACCOUNT",
          "MICR_CODE",
          "APPL_NO",
          "Status",
          "DE_STATUS",
          "Broker_Amt",
          "Gross Brok",
          "Net Brok",
          "GST",
        ],
      ];

      var rows =
        vm.filteredRecord && vm.filteredRecord.length > 0
          ? vm.filteredRecord.map(function (row, index) {
              return [
                index + 1,
                row.blaNo || "",
                row.investAmt || "",
                row.casTransferDate || "",
                row.maturityDate || "",
                row.scheme || "",
                row.cfd || "",
                row.branchrefno || "",
                row.panNo || "",
                row.bankName || "",
                row.adviceNo || "",
                row.appName || "",
                row.addrs1 || "",
                row.addrs2 || "",
                row.city || "",
                row.state || "",
                row.pincode || "",
                row.branchName || "",
                row.rcptDate || "",
                row.groupDate || "",
                row.hdfcBrokerCode || "",
                row.brokerName || "",
                row.addr1 || "",
                row.addr2 || "",
                row.city1 || "",
                row.state1 || "",
                row.pincode1 || "",
                row.phone || "",
                row.paymentMode || "",
                row.accTypeCode || "",
                row.bankAcc || "",
                row.micrCode || "",
                row.appNo || "",
                row.status || "",
                row.deStatus || "",
                row.brokerAmt || "",
                row.grossBrok || "",
                row.netBrok || "",
                row.gst1 || "",
              ];
            })
          : [["No Data Available"]];

      const excelData3 = [...headers, ...rows];
      const ws3 = XLSX.utils.aoa_to_sheet(excelData3);

      // **First sheet (Header1)**
      const sheet1Headers = [[vm.brokerageClaimHeader || "Brokerage Claim", "Gross Brokerage", "Net Brokerage", "GST"]];

      // **Ensure `vm.header1` exists to avoid `undefined` error**
      const sheet1Rows =
        vm.header1 && vm.header1.length > 0
          ? vm.header1
              .map((row) => ["Brokerage to Bank (Direct Customers)", row.dirGrossAmt || "", row.dirNetAmt || "", row.dirGst || ""])
              .concat(
                vm.header1.map((row) => [
                  vm.brokeragePaymentHeader || "Brokerage Payment",
                  row.othersGrossAmt || "",
                  row.othersNetAmt || "",
                  row.othersGst || "",
                ])
              )
              .concat(vm.header1.map((row) => ["Commission", row.commissionGross || "", row.commissionNet || "", row.commissionGst || ""]))
              .concat([["100% Brokerage", vm.dirtotal || "", vm.othersTotal || "", vm.commissionTotal || ""]])
          : [["No Data Available"]]; // **Fallback to avoid `undefined`**

      const excelData1 = [...additionalDetails, ...sheet1Headers, ...sheet1Rows];
      const ws1 = XLSX.utils.aoa_to_sheet(excelData1);

      // **Second sheet (Header2)**
      const sheet2Headers = [["Code", "GL No", "GL Description", "Amount(Cr.)", "Narration to be captured"]];
      const sheet2Rows = [
        ["716", "190602412", "Dr", "1,509,648.31", "", "Brokerage to Bank for Apr-23"],
        ["542", "490204234", "Cr", "", "1,509,648.31", "Ser Tax code 518"],
        ["716", "190602412", "Dr", "1,400,723.75", "", "Commission amount for Apr-23"],
        ["542", "490204233", "Cr", "", "1,400,723.75", "Ser Tax code 517"],
        ["716", "190602412", "Dr", "7,986,783.90", "", "Brokerage amount for Apr-23"],
        ["542", "490204234", "Cr", "", "7,986,783.90", "Ser Tax code 518"],
      ];

      const excelData2 = [...sheet2Headers, ...sheet2Rows];
      const ws2 = XLSX.utils.aoa_to_sheet(excelData2);

      // **Create and write to Excel**
      const wb = XLSX.utils.book_new();
      XLSX.utils.book_append_sheet(wb, ws1, "Header1");
      XLSX.utils.book_append_sheet(wb, ws2, "Header2");
      XLSX.utils.book_append_sheet(wb, ws3, "Detailed Data");
      XLSX.writeFile(wb, "Brokerage_Claim_Monthly_Broker_Wise_Income_Expense.xlsx");
    };

    vm.downloadCSV = function () {
      // First table headers (original table)
      const table1Header = [
        { title: "S.No.", width: 10 },
        { title: "BLA_NO", width: 15 },
        { title: "INVEST_AMT", width: 15 },
        { title: "CAS_TRANSFER_DATE", width: 20 },
        { title: "MATURITY_DATE", width: 20 },
        { title: "SCHEME", width: 15 },
        { title: "CFD", width: 10 },
        { title: "BRANCH_REF_NO", width: 20 },
        { title: "Pan Number", width: 15 },
        { title: "Bank Name", width: 20 },
        { title: "Advice_No", width: 15 },
        { title: "APPLICANT_NAME", width: 25 },
        { title: "ADDR1", width: 30 },
        { title: "ADDR2", width: 30 },
        { title: "CITY", width: 15 },
        { title: "STATE", width: 15 },
        { title: "PIN_CODE", width: 10 },
        { title: "BRANCH_NAME", width: 20 },
        { title: "RCPT_DATE", width: 20 },
        { title: "GROUP_CODE", width: 15 },
        { title: "HDFC_BROKER_CODE", width: 20 },
        { title: "BROKER_NAME", width: 25 },
        { title: "ADDR1", width: 20 },
        { title: "ADDR2", width: 20 },
        { title: "CITY", width: 15 },
        { title: "STATE", width: 15 },
        { title: "PIN_CODE", width: 10 },
        { title: "PHONE", width: 15 },
        { title: "PAYMENT_MODE", width: 20 },
        { title: "ACCOUNT_TYPE_CODE", width: 20 },
        { title: "BANK_ACCOUNT", width: 20 },
        { title: "MICR_CODE", width: 15 },
        { title: "APPL_NO", width: 15 },
        { title: "Status", width: 15 },
        { title: "DE_STATUS", width: 15 },
        { title: "Broker_Amt", width: 20 },
        { title: "Gross Brok", width: 20 },
        { title: "Net Brok", width: 20 },
        { title: "GST", width: 10 },
      ];

      // Second table headers (Brokerage Summary)
      const table2Header = [
        { title: "Brokerage Claim for April 2023", width: 40 },
        { title: "Gross Brokerage", width: 20 },
        { title: "Net Brokerage", width: 20 },
        { title: "GST", width: 20 },
      ];

      // Third table headers (GL Details)
      const table3Header = [
        { title: "Code", width: 10 },
        { title: "GL No", width: 15 },
        { title: "GL Description", width: 20 },
        { title: "Amount(Dr.)", width: 20 },
        { title: "Amount(Cr.)", width: 20 },
        { title: "Narration to be captured", width: 30 },
      ];

      // Formatting headers for all tables

      const formattedTable2Header = table2Header.map((col) => col.title.padEnd(col.width, " ")).join("");
      const formattedTable3Header = table3Header.map((col) => col.title.padEnd(col.width, " ")).join("");
      const formattedTable1Header = table1Header.map((col) => col.title.padEnd(col.width, " ")).join("");

      // Data for first table (vm.filteredRecords holds the data)
      let table1Data = vm.filteredRecord.map((row, index) =>
        table1Header
          .map((col, colIndex) => {
            const value = [
              index + 1, // S.No.
              row.blaNo || "",
              row.investAmt || "",
              row.casTransferDate || "",
              row.maturityDate || "",
              row.scheme || "",
              row.cfd || "",
              row.branchrefno || "",
              row.panNo || "",
              row.bankName || "",
              row.adviceNo || "",
              row.appName || "",
              row.addrs1 || "",
              row.addrs2 || "",
              row.city || "",
              row.state || "",
              row.pincode || "",
              row.branchName || "",
              row.rcptDate || "",
              row.groupDate || "",
              row.hdfcBrokerCode || "",
              row.brokerName || "",
              row.addr1 || "",
              row.addr2 || "",
              row.city1 || "",
              row.state1 || "",
              row.pincode1 || "",
              row.phone || "",
              row.paymentMode || "",
              row.accTypeCode || "",
              row.bankAcc || "",
              row.micrCode || "",
              row.appNo || "",
              row.status || "",
              row.deStatus || "",
              row.brokerAmt || "",
              row.grossBrok || "",
              row.netBrok || "",
              row.gst1 || "",
            ][colIndex];
            return value.toString().padEnd(col.width, " ");
          })
          .join("")
      );

      if (table1Data.length === 0) {
        table1Data = ["No Data Available"];
      }

      const sheet1Data =
        vm.header1 && vm.header1.length > 0
          ? vm.header1
              .map((row) => ["Brokerage to Bank (Direct Customers)", row.dirGrossAmt || "", row.dirNetAmt || "", row.dirGst || ""])
              .concat(
                vm.header1.map((row) => [
                  vm.brokeragePaymentHeader || "Brokerage Payment",
                  row.othersGrossAmt || "",
                  row.othersNetAmt || "",
                  row.othersGst || "",
                ])
              )
              .concat(vm.header1.map((row) => ["Commission", row.commissionGross || "", row.commissionNet || "", row.commissionGst || ""]))
              .concat([["100% Brokerage", vm.dirtotal || "", vm.othersTotal || "", vm.commissionTotal || ""]])
          : [["No Data Available"]];

      const formattedSheet1Data = sheet1Data.map((row) => row.map((col, index) => col.toString().padEnd(table2Header[index].width, " ")).join(""));

      // Data for third table (hardcoded, as already defined)
      let table3Data = [
        ["716", "190602412", "Dr", "1,509,648.31", "", "Brokerage to Bank for Apr-23"],
        ["542", "490204234", "Cr", "", "1,509,648.31", "Ser Tax code 518"],
        ["716", "190602412", "Dr", "1,400,723.75", "", "Commission amount for Apr-23"],
        ["542", "490204233", "Cr", "", "1,400,723.75", "Ser Tax code 517"],
        ["716", "190602412", "Dr", "7,986,783.90", "", "Brokerage amount for Apr-23"],
        ["542", "490204234", "Cr", "", "7,986,783.90", "Ser Tax code 518"],
      ].map((row) => row.map((col, index) => col.padEnd(table3Header[index].width, " ")).join(""));

      if (table3Data.length === 0) {
        table3Data = ["No Data Available"];
      }
      // Final text data preparation
      const textData = [
        "Brokerage Claim_Monthly_Broker Wise Income & Expense",
        "Bank Name: INSPIRISY BANK LTD",
        "Sol ID: 101 / BRANCH OF THE BANK",
        ...(vm.brokerAgencyCode ? ["Broker Agency Code: " + vm.brokerAgencyCode] : []),
        "Name of the Bond Scheme: " + vm.schemeName,
        "From Date : " + $filter("date")(vm.fromdate, "dd-MM-yyyy") + " To Date : " + $filter("date")(vm.todate, "dd-MM-yyyy"),
        "",
        "Brokerage Summary",
        formattedTable2Header,
        "-".repeat(formattedTable2Header.length),
        ...formattedSheet1Data, // Replace table2Data with formattedSheet1Data
        "",
        "GL Details",
        formattedTable3Header,
        "-".repeat(formattedTable3Header.length),
        ...table3Data,
        "",
        "Detailed Brokerage Claim",
        formattedTable1Header,
        "-".repeat(formattedTable1Header.length),
        ...table1Data,
        "",
      ].join("\n");

      // Download logic
      const textContent = "data:text/plain;charset=utf-8," + encodeURIComponent(textData);
      const link = document.createElement("a");
      link.setAttribute("href", textContent);
      link.setAttribute("download", "Brokerage_Claim_Report.txt");
      document.body.appendChild(link);
      link.click();
      document.body.removeChild(link);
    };

    if ($state.current.name == "brokerage_claim_monthly_broker_wise_income&expense.inquiry_detail") {
      if ($stateParams.obj) {
        vm.schemeCode = $stateParams.obj.schemeCode;
        vm.schemeName = $stateParams.obj.schemeName;
        vm.schemeStatus = $stateParams.obj.schemeStatus;
        vm.fromdate = $stateParams.obj.fromDate;
        vm.todate = $stateParams.obj.toDate;
        vm.brokerAgencyCode = $stateParams.obj.brokerAgencyCode;
            getDetailspage();
      }
    }

    function getModuleName() {
      vm.loader = true;
      var requestData = {
        flag: "S",
      };
      BrokerageClaimService.detailsPage(requestData).then(
        function (response) {
          vm.loader = false;
          vm.schemeList = response.data.result.list;
          console.log("vm.schemeList", vm.schemeList);
          angular.element("#schemeModel").modal("toggle");
        },
        function (error) {
          handleErrorResponse(error);
          vm.loader = false;
        }
      );
    }

    vm.hasValidData = function (records) {
      return records.some((row) => Object.values(row).some((value) => value !== null && value !== undefined && value !== ""));
    };

    $scope.$applyAsync();

    function getDetailspage() {
      vm.loader = true;
      var requestData = {
        schemeCode: vm.schemeCode,
        fromDate: $filter("date")(vm.fromdate, "dd-MMM-yyyy"),
        toDate: $filter("date")(vm.todate, "dd-MMM-yyyy"),
        brokerAgencyCode: vm.brokerAgencyCode,
        flag: "Y",
        pageNo: vm.pageNo,
        pageSize: vm.pageSize,
        searchValue: vm.searchValue,
        sortOrder: vm.sortOrder,
        sortField: vm.sortField,
      };
      BrokerageClaimService.detailsPage(requestData).then(
        function (response) {
          vm.loader = false;
          vm.DetailsList = response.data.result.list1;
          vm.header1 = response.data.result.list;
                                    vm.dirtotal = response.data.result.total;
                                    vm.othersTotal = response.data.result.otherstotal;
                                    vm.commissionTotal = response.data.result.commissiontotal;
                                    vm.records = response.data.result.list1;
                                    //                           vm.totalAmount = vm.filteredRecords.reduce((sum, record) => sum + (record.amount ? parseFloat(record.amount) : 0), 0);
                                    vm.total = response.data.total;
                                    vm.maxPages = Math.ceil(vm.total / vm.pageSize);
                                    vm.paginationControls();
          //                           vm.filteredRecords = response.data.result.list1;
                          if (vm.DetailsList.length === 0) {
                            vm.noDataFoundInSearch = true;
                          } else {
                            vm.noDataFoundInSearch = false;
                          }

          BrokerageClaimService.getReferenceData({ referenceId: "MODE_OF_PAYMENT" }).then(function (response) {
            var paymentModeDesc = {};
            response.data.result.forEach(function (item) {
              paymentModeDesc[item.SUBCODE] = item.DESCRIPTION;
            });

            BrokerageClaimService.getReferenceData({ referenceId: "STATES OF INDIA" }).then(function (response) {
              var stateMap = {};
              response.data.result.forEach(function (item) {
                stateMap[item.SUBCODE] = item.DESCRIPTION;
              });
              BrokerageClaimService.getReferenceData({ referenceId: "STATES OF INDIA" }).then(function (response) {
                var stateMap1 = {};
                response.data.result.forEach(function (item) {
                  stateMap1[item.SUBCODE] = item.DESCRIPTION;
                });


                vm.filteredRecords = vm.DetailsList.map(function (record) {
                  return {
                    ...record,
                    paymentMode: paymentModeDesc[record.paymentMode] || "",
                    state: stateMap[record.state] || "",
                    state1: stateMap[record.state1],
                  };
                });
              });
            });
          });
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
            $state.go("brokerage_claim_monthly_broker_wise_income&expense.inquiry");
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
      if (!fromDate) return;

      var firstDayOfMonth = new Date(fromDate.getFullYear(), fromDate.getMonth(), 1);
      var lastDayOfMonth = new Date(fromDate.getFullYear(), fromDate.getMonth() + 1, 0);

      vm.toDateOptions = {
         minDate: fromDate,
         maxDate: new Date(),
//        showWeeks: false,
//        startingDay: 1, // Ensure the week starts correctly
//        initDate: fromDate, // Open the calendar at the selected fromDate's month
      };
    };


//     vm.toDateOptionss = {
//            minDate: vm.fromdate,
//            maxDate: new Date(),
//    //        showWeeks: false,
//    //        startingDay: 1, // Ensure the week starts correctly
//    //        initDate: fromDate, // Open the calendar at the selected fromDate's month
//          };

    vm.updateColumnHeaders = function () {
      if (vm.fromdate) {
        let selectedDate = moment(vm.fromdate, "DD/MM/YYYY").toDate();
        let monthNames = ["January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"];

        let month = monthNames[selectedDate.getMonth()];
        let year = selectedDate.getFullYear();

        vm.brokerageClaimHeader = `Brokerage Claim for ${month} ${year}`;
        vm.brokeragePaymentHeader = `Brokerage Payment for ${month} ${year}`;
        vm.brokeragetoBank = `Brokerage to Bank for ${month} ${year}`;
        vm.brokerageamt = `Brokerage amount for ${month} ${year}`;
        vm.commissionamt = `Commission amount for ${month} ${year}`;
      }
    };

    function brokeragencyTorch() {
      var requestData = {
        schemeCode: vm.schemeCode,
        brokerAgencyCode: vm.brokerAgencyCode,
        flag: "A",
      };
      BrokerageClaimService.detailsPage(requestData).then(
        function (response) {
          vm.loader = false;
          vm.brokeragency = response.data.result.list;
          console.log("vm.brokeragecy", vm.brokeragecy);
          angular.element("#brokerModel").modal("toggle");
        },
        function (error) {
          vm.loader = false;
          console.log("error", error);
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
  }
})();
