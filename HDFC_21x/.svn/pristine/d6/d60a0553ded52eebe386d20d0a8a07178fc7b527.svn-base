(function () {
  "use strict";

  angular.module("gbmUltraApp").controller("tdsRemittanceFinconFileReportController", tdsRemittanceFinconFileReportController);

  tdsRemittanceFinconFileReportController.$inject = ["$scope", "$filter", "$state", "$stateParams", "$location", "$rootScope", "$timeout", "tdsRemittanceFinconFileReportService", "commonService"];

  function tdsRemittanceFinconFileReportController($scope, $filter, $state, $stateParams, $location, $rootScope, $timeout, tdsRemittanceFinconFileReportService, commonService) {
    var vm = this;
    vm.currentPage = 1;
    vm.itemsPerPage = "5";
    vm.getModuleName = getModuleName;
    vm.currentDate = new Date();
    vm.textableDatePicker = textableDatePicker;
    vm.autoslashing = autoslashing;

    function textableDatePicker(id, valueDate) {
      commonService.textableDatePicker(id, valueDate);
    }
    function autoslashing(id) {
      commonService.autoslashing(id);
    }
    vm.moduleName = "RBI Bond";
    vm.searchText = "";
    vm.getPanTorch = getPanTorch;
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

    //Download reports
    vm.downloadReport = function () {
      vm.loader = true;
      var requestData = {
        schemeCode: vm.schemeCode,
        holderName: vm.holderName,
        panNo: vm.panNo,
        flag: "D",
        fromDate: $filter("date")(vm.fromDate, "dd-MMM-yyyy"),
        toDate: $filter("date")(vm.toDate, "dd-MMM-yyyy"),
        pageNo: 1,
        pageSize: vm.total,
        searchValue: vm.searchText,
      };

                if (vm.downloadType === "excel") {
                    tdsRemittanceFinconFileReportService.getDetailsDownload(requestData).then(
                    function (response) {
                      vm.loader = false;
                      vm.downloadFilteredRecords = response.data.result.list;
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
                        $state.go("tds_remittance_fincon_file_report.inquiry");
                      }
                    }
                  );
                } else if(vm.downloadType != "excel") {
                    tdsRemittanceFinconFileReportService.getDetails(requestData).then(
                    function (response) {
                      vm.loader = false;
                      vm.downloadFilteredRecords = response.data.result.list;
                      if (vm.downloadType === "csv") {
                        vm.downloadCSV();
                      }
                      vm.downloadType = "";
                    },
                    function (error) {
                      vm.loader = false;
                      if (error.data.message == "No Data Found" || error.data.message == "NDF") {
                        vm.noDataFound = error.data.message;
                      } else {
                        handleErrorResponse(error);
                        $state.go("tds_remittance_fincon_file_report.inquiry");
                      }
                    }
                  );
                }
    };

    vm.downloadExcel = function () {
      const additionalDetails = [
        ["TDS Remittance Fincon File Report"],
        ["Bank Name: INSPIRISY BANK LTD"],
        ["Sol ID: 101 / BRANCH OF THE BANK"],
        ...(vm.schemeName ? [["Name of the Bond Scheme: " + vm.schemeName]] : []),
        ["TDS Deduction From Date : " + $filter("date")(vm.fromDate, "dd-MM-yyyy") + "     TDS Deduction To Date : " + $filter("date")(vm.toDate, "dd-MM-yyyy")],
        [],
      ];

      var headers = [
        [
          "S.No.",
          "Co Code",
          "Ref No",
          "BLA No",
          "Vendor Name",
          "Add 1",
          "Add 2",
          "City",
          "PIN",
          "State",
          "Country",
          "Section",
          "PAN No",
          "Gross Amount",
          "Tax Rate",
          "Total Tax Amount",
          "Payment Date",
          "Basic Tax",
          "Surcharge Amount",
          "Cess Amount",
          "Tax Dep Date",
          "Lower Deduction Status",
          "Category",
          "Interest Payout for the Year",
          "Waiver Document",
        ],
      ];
      var rows = vm.downloadFilteredRecords.map(function (row, index) {
        return [
          index + 1,
          row.coCode,
          row.refNo,
          row.bla,
          row.holderName,
          row.address1,
          row.address2,
          row.city,
          row.pin,
          row.state,
          row.country,
          row.section,
          row.panNo,
          row.grossAmount,
          row.taxRate,
          row.totalTaxAmount,
          row.paymentDate,
          row.basicTax,
          row.surchargeAmount,
          row.cessAmount,
          row.taxDepDate,
          row.lowerDeductionStatus,
          row.category,
          row.interestPayoutfortheYear,
          row.waiverDocument,
        ];
      });
      const excelData = [...additionalDetails, ...headers, ...rows];

      const ws = XLSX.utils.aoa_to_sheet(excelData);
      const mergeRanges = [
        { s: { r: 0, c: 0 }, e: { r: 0, c: 10 } },
        { s: { r: 1, c: 0 }, e: { r: 1, c: 10 } },
        { s: { r: 2, c: 0 }, e: { r: 2, c: 10 } },
        { s: { r: 3, c: 0 }, e: { r: 3, c: 10 } },
        { s: { r: 4, c: 0 }, e: { r: 4, c: 10 } },
      ];
      ws["!merges"] = mergeRanges;
      mergeRanges.forEach((merge) => {
        const cell = ws[XLSX.utils.encode_cell(merge.s)];
        if (cell) {
          cell.s = { alignment: { horizontal: "center", vertical: "center" } };
        }
      });
      const wb = XLSX.utils.book_new();
      XLSX.utils.book_append_sheet(wb, ws, "Data");
      XLSX.writeFile(wb, "TDS_remittance_fincon_file_report.xlsx");
    };

    vm.downloadCSV = function () {
      const tableHeader = [
        { title: "S.No.", width: 10 },
        { title: "Co Code", width: 10 },
        { title: "Ref No", width: 30 },
        { title: "BLA No", width: 30 },
        { title: "Vendor Name", width: 30 },
        { title: "Add 1", width: 40 },
        { title: "Add 2", width: 40 },
        { title: "City", width: 20 },
        { title: "PIN", width: 20 },
        { title: "State", width: 20 },
        { title: "Country", width: 20 },
        { title: "Section", width: 20 },
        { title: "PAN No", width: 30 },
        { title: "Gross Amount", width: 20 },
        { title: "Tax Rate", width: 20 },
        { title: "Total Tax Amount", width: 25 },
        { title: "Payment Date", width: 20 },
        { title: "Basic Tax", width: 20 },
        { title: "Surcharge Amount", width: 20 },
        { title: "Cess Amount", width: 20 },
        { title: "Tax Dep Date", width: 20 },
        { title: "Lower Deduction Status", width: 25 },
        { title: "Category", width: 20 },
        { title: "Interest Payout for the Year", width: 30 },
        { title: "Waiver Document", width: 20 },
      ];

      const formattedHeader = tableHeader.map((col) => col.title.padEnd(col.width, " ")).join("");

      const tableData = vm.downloadFilteredRecords.map((record, index) =>
        tableHeader
          .map((col, colIndex) => {
            const value = [
              index + 1, // S.No.
              record.coCode || "",
              record.refNo || "",
              record.bla || "",
              record.holderName || "",
              record.address1 || "",
              record.address2 || "",
              record.city || "",
              record.pin || "",
              record.state || "",
              record.country || "",
              record.section || "",
              record.panNo || "",
              record.grossAmount || "",
              record.taxRate || "",
              record.totalTaxAmount || "",
              record.paymentDate || "",
              record.basicTax || "",
              record.surchargeAmount || "",
              record.cessAmount || "",
              record.taxDepDate || "",
              record.lowerDeductionStatus || "",
              record.category || "",
              record.interestPayoutfortheYear || "",
              record.waiverDocument || "",
            ][colIndex];
            return value.toString().padEnd(col.width, " ");
          })
          .join("")
      );

      //  const totalRow = tableHeader
      //    .map((col, colIndex) => {
      //      if (colIndex === tableHeader.length - 2) {
      //        return "Total".padEnd(col.width, " ");
      //      }
      //      if (colIndex === tableHeader.length - 1) {
      //        const totalStr = totalAmountWithoutDecimal.toString();
      //        return totalStr.padStart(col.width, " ");
      //      }
      //      return "".padEnd(col.width, " ");
      //    })
      //    .join("");

      const textData = [
        "TDS Remittance Fincon File Report",
        "Bank Name: INSPIRISY BANK LTD",
        "Sol ID: 101 / BRANCH OF THE BANK",
        ...(vm.schemeName ? [["Name of the Bond Scheme: " + vm.schemeName]] : []),
        "TDS Deduction From Date : " + $filter("date")(vm.fromDate, "dd-MM-yyyy") + "     TDS Deduction To Date : " + $filter("date")(vm.toDate, "dd-MM-yyyy"),
        formattedHeader,
        "-".repeat(formattedHeader.length),
        ...tableData,
        "".padEnd(formattedHeader.length, " "), // Empty row
      ].join("\n");

      const textContent = "data:text/plain;charset=utf-8," + encodeURIComponent(textData);
      const link = document.createElement("a");
      link.setAttribute("href", textContent);
      link.setAttribute("download", "TDS_remittance_fincon_file_report.txt");
      document.body.appendChild(link);
      link.click();
      document.body.removeChild(link);
    };

    //        End of download report methods

    if ($state.current.name == "tds_remittance_fincon_file_report.inquiry_details") {
      if ($stateParams.obj) {
        vm.schemeCode = $stateParams.obj.schemeCode;
        vm.schemeName = $stateParams.obj.schemeName;
        vm.schemeStatus = $stateParams.obj.schemeStatus;
        vm.fromDate = $stateParams.obj.fromDate;
        vm.toDate = $stateParams.obj.toDate;
        vm.panNo = $stateParams.obj.panNo;
        vm.holderName = $stateParams.obj.holderName;
            getDetails();
      }
    }

    //Get Scheme name
    function getModuleName() {
      vm.loader = true;

      var requestData = {
        schemeCode: "",
      };
      tdsRemittanceFinconFileReportService.getDetails(requestData).then(
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
        holderName: vm.holderName,
        panNo: vm.panNo,
        flag: "D",
        fromDate: $filter("date")(vm.fromDate, "dd-MMM-yyyy"),
        toDate: $filter("date")(vm.toDate, "dd-MMM-yyyy"),
        pageNo: vm.pageNo,
        pageSize: vm.pageSize,
        searchValue: vm.searchText,
        sortOrder: vm.sortOrder,
        sortField: vm.sortField,
      };

      tdsRemittanceFinconFileReportService.getDetails(requestData).then(
        function (response) {
          vm.loader = false;
          vm.detailsList = response.data.result.list;
          vm.filteredRecords = response.data.result.list;
          vm.records = response.data.result.list;
          vm.total = response.data.total;
          vm.maxPages = Math.ceil(response.data.total / vm.pageSize);
          vm.paginationControls();
          if (vm.detailsList.length === 0) {
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
            $state.go("tds_remittance_fincon_file_report.inquiry");
          }
        }
      );
    }

    //Get Pan Number
    function getPanTorch() {
      var requestData = {
        schemeName: vm.schemeName,
        holderName: vm.holderName,
        panNo: vm.panNo,
        flag: "P",
      };
      tdsRemittanceFinconFileReportService.getDetails(requestData).then(
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
    }

    //Date validation
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
  }
})();
