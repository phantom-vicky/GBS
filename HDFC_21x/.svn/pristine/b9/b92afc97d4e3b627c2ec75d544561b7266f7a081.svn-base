(function () {
  "use strict";
  angular.module("gbmUltraApp").controller("InterestNotCreditedController", InterestNotCreditedController);
  InterestNotCreditedController.$inject = ["$scope", "$filter", "$state", "$stateParams", "$location", "$rootScope", "InterestNotCreditedService", "commonService"];

  function InterestNotCreditedController($scope, $filter, $state, $stateParams, $location, $rootScope, InterestNotCreditedService, commonService) {
    var vm = this;
    vm.getBondInterestNotCreditedDetails = getBondInterestNotCreditedDetails;

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
    vm.getTorchDetails = getTorchDetails;
    $scope.sort = function (key) {
      $scope.sortKey = key;
      $scope.sortReverse = $scope.sortKey == key ? !$scope.sortReverse : $scope.sortReverse;
      $scope.sortReverse ? (vm.sortOrder = "DESC") : (vm.sortOrder = "ASC");
      vm.sortField = key;
      vm.getBondInterestNotCreditedDetails();
    };

    vm.pageNo = 1;
    vm.pageSize = 5;

    vm.goPreviousPage = function () {
      vm.pageNo = parseInt(vm.pageNo) - 1;
      vm.getBondInterestNotCreditedDetails();
    };

    vm.goNextPage = function () {
      vm.pageNo = parseInt(vm.pageNo) + 1;
      vm.getBondInterestNotCreditedDetails();
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

    if ($state.current.name === "rbiBond_bond_interest_not_credited_report.inquiry_detail") {
      vm.schemeCode = $stateParams.obj.schemeCode;
      vm.schemeName = $stateParams.obj.schemeName;
      vm.interestPaymentFor = $stateParams.obj.interestPaymentFor;
      vm.holderName = $stateParams.obj.holderName;
      vm.panNumber = $stateParams.obj.panNumber;
      vm.blaNumber = $stateParams.obj.blaNumber;
      vm.financialYear = $stateParams.obj.financialYear;
            vm.getBondInterestNotCreditedDetails();
    }

    vm.getSchemeName = function () {
      vm.loader = true;
      var requestData = {
        flag: "S",
      };
      InterestNotCreditedService.getDetails(requestData).then(
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

    vm.getPanNumber = function () {
      vm.loader = true;
      var requestData = {
        schemeCode: vm.schemeCode,
        holderName: vm.holderName,
        panNumber: vm.panNumber,
        flag: "P",
      };

      InterestNotCreditedService.getDetails(requestData).then(
        function (response) {
          vm.loader = false;
          vm.panList = response.data.result.list;
          angular.element("#panModel").modal("toggle");
        },
        function (error) {
          handleErrorResponse(error);
          vm.loader = false;
        }
      );
    };

    vm.getBlaNumber = function () {
      vm.loader = true;
      var requestData = {
        schemeCode: vm.schemeCode,
        holderName: vm.holderName,
        panNumber: vm.panNumber,
        blaNumber: vm.blaNumber,
        flag: "B",
      };
      InterestNotCreditedService.getDetails(requestData).then(
        function (response) {
          vm.loader = false;
          vm.blaList = response.data.result.list;
          angular.element("#blaModel").modal("toggle");
        },
        function (error) {
          handleErrorResponse(error);
          vm.loader = false;
        }
      );
    };

    vm.getFinancialYears = function () {
      vm.loader = true;
      var requestData = {
        flag: "FY",
      };
      InterestNotCreditedService.getDetails(requestData).then(
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

    function getBondInterestNotCreditedDetails() {
    vm.loader = true;
            var requestData = {
              schemeCode: vm.schemeCode,
              flag: "SB",
              interestPaymentFor: vm.interestPaymentFor,
              holderName: vm.holderName,
              panNumber: vm.panNumber,
              blaNumber: vm.blaNumber,
              financialYear: vm.financialYear,
              pageNo: vm.pageNo,
              pageSize: vm.pageSize,
              searchValue: vm.searchValue,
              sortOrder: vm.sortOrder,
              sortField: vm.sortField,
            };

            InterestNotCreditedService.getDetails(requestData).then(
            function (response) {
                              vm.loader = false;
                  vm.result = response.data.result.list;
                  vm.model = response.data.result.list;
                  vm.filteredRecords = response.data.result.list;
                  vm.totalInterest = vm.filteredRecords.reduce((sum, record) => sum + (record.totalInterest ? parseFloat(record.totalInterest) : 0), 0);
                  vm.totalTdsAmount = vm.filteredRecords.reduce((sum, record) => sum + (record.tdsAmount ? parseFloat(record.tdsAmount) : 0), 0);
                  vm.totalNetInterestAmount = vm.filteredRecords.reduce((sum, record) => sum + (record.netInterestAmount ? parseFloat(record.netInterestAmount) : 0), 0);
                  vm.total = response.data.total;
                  vm.maxPages = Math.ceil(response.data.total / vm.pageSize);
                  var holdingReferenceId = { referenceId: "MODE_OF_PAYMENT" };
                  InterestNotCreditedService.getReferenceData(holdingReferenceId).then(function (holdingMappingResponse) {
                    var holdingMappings = holdingMappingResponse.data.result;

                    var paymentMap = {};
                    holdingMappings.forEach(function (item) {
                      paymentMap[item.SUBCODE] = item.DESCRIPTION;
                    });

                    vm.filteredRecords.forEach(function (record) {
                      record.modeOfPaymentDescription = paymentMap[record.interestPaymentMode] || "Unknown";
                    });
                  });
                  vm.paginationControls();

                  if (vm.model.length === 0) {
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
                                $state.go("rbiBond_bond_interest_not_credited_report.inquiry");
                          }
            }
        );
    }

    function handleError(error) {
      if ((error.data.message == "No Data Found" || error.data.message == "NDF") && vm.searchValue) {
        vm.noDataFoundInSearch = true;
      } else if (error.data.message == "No Data Found" || error.data.message == "NDF") {
        vm.noDataFound = error.data.message;
      } else {
        handleErrorResponse(error);
        $state.go("rbiBond_bond_interest_not_credited_report.inquiry");
      }
    }

    vm.maxValue = function () {
      return Math.min(vm.currentPage * vm.pageSize, vm.filteredRecords.length);
    };

    //Downloading the report
    vm.reports = {
      BondInterestNotCredited: {
        filename: "Bond_Interest_Not_Credited_Report",
        mainHeaders: ["Bond Interest Not Credited Report", "Bank Name: INSPIRISY BANK LTD", "Sol ID: 101 / BRANCH OF THE BANK"],
        subHeaders: ["Name of the Bond Scheme: " + vm.schemeName],
        tableHeaders: [
          { title: "S. No.", width: 10, type: "numeric" },
          { title: "PAN No.", width: 20, type: "string" },
          { title: "BOND Ledger Account Number", width: 30, type: "string" },
          { title: "Customer Name", width: 35, type: "string" },
          { title: "BOND Subscription ID", width: 30, type: "string" },
          { title: "Subscription Date", width: 20, type: "string" },
          { title: "Subscription Amount", width: 20, type: "string" },
          { title: "Interest From Period", width: 20, type: "string" },
          { title: "Interest To Period", width: 20, type: "string" },
          { title: "Interest Payment Mode", width: 20, type: "string" },
          { title: "Credit Account Number", width: 30, type: "string" },
          { title: "Type Of Certificate", width: 20, type: "string" },
          { title: "Tax ExemptionLimit", width: 20, type: "string" },
          { title: "Low Tax Percentage", width: 20, type: "string" },
          { title: "PAN Status", width: 20, type: "string" },
          { title: "Total Interest Rs", width: 20, type: "string" },
          { title: "TDS Amount Rs", width: 20, type: "string" },
          { title: "Net Interest Amount Rs", width: 20, type: "string" },
          { title: "Reason", width: 40, type: "string" },
        ],
        get tableData() {
          return (vm.filteredRecord || []).map((record, index) => [
            (index + 1).toString(),
            record.panNumber || "",
            record.blaNumber || "",
            record.holderName || "",
            record.subscriptionID || "",
            record.subscriptionDate || "",
            record.subscriptionAmount || "",
            record.interestFromPeriod || "",
            record.interestToPeriod || "",
            record.modeOfPaymentDescription || "",
            record.creditAccountNumber || "",
            record.typeOfCertificate || "",
            record.taxExemptionLimit || "",
            record.lowTaxPercentage || "",
            record.panStatus || "",
            record.totalInterest || "",
            record.tdsAmount || "",
            record.netInterestAmount || "",
            record.reason || "",
          ]);
        },
      },
    };

    vm.downloadReport = function () {
      vm.loader = true;
      var requestData = {
        schemeCode: vm.schemeCode,
        flag: "SB",
        interestPaymentFor: vm.interestPaymentFor,
        holderName: vm.holderName,
        panNumber: vm.panNumber,
        blaNumber: vm.blaNumber,
        financialYear: vm.financialYear,
        pageNo: "1",
        pageSize: vm.total,
        searchValue: vm.searchValue,
      };
      InterestNotCreditedService.getDetails(requestData).then(
        function (response) {
          vm.loader = false;
          vm.filteredRecord = response.data.result.list;
          vm.totalInterestExcel = vm.filteredRecord.reduce((sum, record) => sum + (record.totalInterest ? parseFloat(record.totalInterest) : 0), 0);
          vm.totalTdsAmountExcel = vm.filteredRecord.reduce((sum, record) => sum + (record.tdsAmount ? parseFloat(record.tdsAmount) : 0), 0);
          vm.totalNetInterestAmountExcel = vm.filteredRecord.reduce((sum, record) => sum + (record.netInterestAmount ? parseFloat(record.netInterestAmount) : 0), 0);
          const currentReport = vm.reports["BondInterestNotCredited"];
          if (vm.downloadType === "excel") {
            vm.downloadExcel(currentReport);
          } else if (vm.downloadType === "csv") {
            vm.downloadCSV(currentReport);
          }
          vm.downloadType = "";
        },
        function (error) {
          vm.loader = false;
          if (error.data.message == "No Data Found" || error.data.message == "NDF") {
            vm.noDataFound = error.data.message;
          } else {
            handleErrorResponse(error);
            $state.go("rbiBond_bond_interest_not_credited_report.inquiry");
          }
        }
      );
    };

    vm.downloadCSV = function (currentReport) {
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
          .join(" | ");
      };
      const formattedHeader = formatRow(tableHeaders.map((col) => col.title));
      const formattedData = tableData.map((row) => formatRow(row));
      const totalRow = formatRow(
        tableHeaders.map((col, i) => {
          if (i === tableHeaders.length - 5) return "Total";
          if (i === tableHeaders.length - 4) return vm.totalInterestExcel.toString();
          if (i === tableHeaders.length - 3) return vm.totalTdsAmountExcel.toString();
          if (i === tableHeaders.length - 2) return vm.totalNetInterestAmountExcel.toString();
          return "";
        })
      );
      const textData = [mainHeaders.join("\n"), subHeaders.join("\n"), formattedHeader, "-".repeat(formattedHeader.length), ...formattedData, totalRow].join("\n");
      const textContent = "data:text/plain;charset=utf-8," + encodeURIComponent(textData);
      const link = document.createElement("a");
      link.setAttribute("href", textContent);
      link.setAttribute("download", filename + ".txt");
      document.body.appendChild(link);
      link.click();
      document.body.removeChild(link);
    };

    vm.downloadExcel = function (currentReport) {
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
      const totalOfInterestRow = ["", "", "", "", "", "", "", "", "", "", "", "", "", "", "Total", vm.totalInterestExcel, vm.totalTdsAmountExcel, vm.totalNetInterestAmountExcel, ""];
      excelData.push(totalOfInterestRow);
      const ws = XLSX.utils.aoa_to_sheet(excelData);
      const mergeRanges = [
        { s: { r: 0, c: 0 }, e: { r: 0, c: 10 } },
        { s: { r: 1, c: 0 }, e: { r: 1, c: 10 } },
        { s: { r: 2, c: 0 }, e: { r: 2, c: 10 } },
        { s: { r: 3, c: 0 }, e: { r: 3, c: 10 } },
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





                    function getTorchDetails(flag){
                                                        vm.loader = true;


                                                        var requestData = {
                                                            'schemeCode' : vm.schemeCode,
                                                            'interestForHalfYear' : vm.interestForHalfYear,
                                                            'operationType' : flag

                                                        };

                                                        InterestNotCreditedService.getTorchDetails(requestData).then(function (response) {
                                                            vm.loader = false;

                                                             if(flag == "interestHalfYear"){
                                                              vm.interestHalfYearList = response.data.result.interestHalfYearTorchOut;
                                                             }
                                                        }, function (error) {
                                                            handleErrorResponse(error);
                                                            vm.loader = false;
                                                        });
                                                       }
  }
})();
