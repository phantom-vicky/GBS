(function () {
  "use strict";

  angular.module("gbmUltraApp").controller("bondPreClosureRepaymentController", bondPreClosureRepaymentController);

  bondPreClosureRepaymentController.$inject = ["$scope", "$filter", "$state", "$stateParams", "$location", "$rootScope", "bondPreClosureRepaymentService", "commonService"];

  function bondPreClosureRepaymentController($scope, $filter, $state, $stateParams, $location, $rootScope, bondPreClosureRepaymentService, commonService) {
    var vm = this;
    vm.solDate = $rootScope.globals.currentUser.solDate;
    vm.currentDate = new Date();
    vm.currentDate = $filter("date")(vm.currentDate, "dd/MM/yyyy");
    vm.getPreClosureRepayment = getPreClosureRepayment;
    vm.getSchemeName = getSchemeName;

    vm.textableDatePicker=textableDatePicker;
    vm.autoslashing=autoslashing;

    function textableDatePicker(id,valueDate)
    {
     commonService.textableDatePicker(id,valueDate);
    }
    function autoslashing(id)
    {
     commonService.autoslashing(id);
    }

    //Date Validation
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

//pagination
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
         vm.getPreClosureRepayment();
       };

       vm.pageNo = 1;
       vm.pageSize = 5;

       vm.goPreviousPage = function () {
         vm.pageNo = parseInt(vm.pageNo) - 1;
         vm.getPreClosureRepayment();
       };

       vm.goNextPage = function () {
         vm.pageNo = parseInt(vm.pageNo) + 1;
         vm.getPreClosureRepayment();
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

    vm.roundGrossAmount = function (amount) {
      return Math.round(amount); // Rounds to nearest integer
    };
 // Api call and details Screen

    if ($state.current.name == "rbibond_precloser_repayment_report.inquiry_details") {
      vm.schemeCode = $stateParams.obj.schemeCode;
      vm.schemeName = $stateParams.obj.schemeName;
      vm.fromDate = $stateParams.obj.fromDate;
      vm.toDate = $stateParams.obj.toDate;
         getPreClosureRepayment();
    }

    function getSchemeName() {
      vm.loader = true;
      var requestData = {
        schemeCode: "",
      };
      bondPreClosureRepaymentService.getDetails(requestData).then(
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

    function getPreClosureRepayment() {
      vm.loader = true;

      var requestData = {
        schemeCode: vm.schemeCode,
        fromDate: moment(vm.fromDate).format("DD-MMM-YYYY"),
        toDate: moment(vm.toDate).format("DD-MMM-YYYY"),
        flag: "D",
        pageNo : vm.pageNo,
        pageSize:vm.pageSize,
        searchValue:vm.searchValue,
        sortOrder:vm.sortOrder,
        sortField:vm.sortField
      };

      bondPreClosureRepaymentService.getDetails(requestData).then(
        function (response) {
          vm.loader = false;

          vm.model = response.data.result.list;
          vm.total = response.data.total;
          vm.filteredRecords = response.data.result.list;
          vm.maxPages = Math.ceil(response.data.total / vm.pageSize);
          vm.totalSubscriptionAmt = vm.filteredRecords.reduce((sum, record) => sum + (record.subscriptionAmt ? parseFloat(record.subscriptionAmt) : 0), 0);
          vm.totalGrossAmt = vm.filteredRecords.reduce((sum, record) => sum + (record.grossAmt ? parseFloat(record.grossAmt) : 0), 0);
          vm.totalRepayAmt = vm.filteredRecords.reduce((sum, record) => sum + (record.repayAmt ? parseFloat(record.repayAmt) : 0), 0);

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
           $state.go("rbibond_precloser_repayment_report.inquiry");
          }
        }
      );
    }

    //Downloading the report
    vm.downloadReport = function () {

        vm.loader = true;
        var requestData = {
            schemeCode: vm.schemeCode,
            fromDate: moment(vm.fromDate).format("DD-MMM-YYYY"),
            toDate: moment(vm.toDate).format("DD-MMM-YYYY"),
            flag: "D",
            pageNo : 1,
            pageSize: vm.total,
            searchValue:vm.searchValue
        };
        bondPreClosureRepaymentService.getDetailsDownload(requestData).then(
            function (response) {
                vm.loader = false;
                vm.filteredRecord = response.data.result;
    const currentReport = vm.reports["BondPreclosureRepayment"];
      if (vm.downloadType === "excel") {
//        vm.downloadExcel();
            if (response.data.result.fileName) {
                 vm.fileData = response.data.result.fileData;
                 vm.fileName = response.data.result.fileName;
                 saveAs(getBlobFromBase64String(vm.fileData), vm.fileName)
                 Swal.fire("Downloaded", response.data.message, "success");
            }
      } else if (vm.downloadType === "csv") {
        vm.downloadCSV(currentReport);
      }
      vm.downloadType = "";
                },function (error) {
               vm.loader = false;
               if ((error.data.message == "No Data Found") || (error.data.message == "NDF")) {
                 vm.noDataFound = error.data.message;
               }else {

                 handleErrorResponse(error);
                 $state.go("rbibond_precloser_repayment_report.inquiry");
               }
              });
    };

    vm.formatPercentage = function(value) {
      return (value !== null && value !== undefined) ? value + ' %' : '';
    };

vm.reports = {
      BondPreclosureRepayment: {
        filename: "Bond Pre-closure Repayment",
        mainHeaders: ["Bond Pre-closure Repayment", "Bank Name: INSPIRISY BANK LTD", "Sol ID: 101 / BRANCH OF THE BANK"],
        subHeaders: ["Name of the Bond Scheme :" + vm.schemeName,
                            "From Date: " + $filter("date")(vm.fromDate, "dd/MM/yyyy"),
                            "To Date: " + $filter("date")(vm.toDate, "dd/MM/yyyy")],
        tableHeaders: [
         { title: "S.NO", width: 10, type: "numeric" },
                 { title: "PAN No", width: 30, type: "string" },
                 { title: "Bond Ledger Account Number", width: 30, type: "string" },
                 { title: "Holder Name", width: 30, type: "string" },
                 { title: "Subscription ID", width: 30, type: "string" },
                 { title: "Subscription Date", width: 30, type: "string" },
                 { title: "Subscription Amount Rs", width: 30, type: "numeric" },
                 { title: "Due date of Repayment", width: 25, type: "string" },
                 { title: "Date of Pre-Closed Repayment", width: 33, type: "string" },
                 { title: "Gross Amount", width: 30, type: "numeric" },
                 { title: "TDS Percentage", width: 30, type: "string" },
                 { title: "TDS Amount", width: 30, type: "string" },
                 { title: "Amount of Repayment Rs", width: 30, type: "numeric" },
                 { title: "Credited to Bank Account No", width: 30, type: "string" },
        ],
        get tableData() {
          return (vm.filteredRecords || []).map((record, index) => [
            (index + 1).toString(),
            record.panNo || "",
                          record.blaNo || "",
                          record.holderName || "",
                          record.subscriptionId || "",
                          record.subscriptionDate || "",
                          record.subscriptionAmt || "",
                          record.dueDateRepay || "",
                          record.datePreclosRepay || "",
                          record.grossAmt ? Math.round(record.grossAmt) : "",
                          record.tdsPercentage || "",
                          record.tdsAmt || "",
                          record.repayAmt ? Math.round(record.repayAmt) : "",
                          record.creditAccNo || "",
          ]);
        },
      },
    };

vm.downloadExcel = function () {
 console.log("function gets called");
  var mainHeader = ["Bond Pre-closure Repayment Report"];
  var bankDetails = [
    ["Bank Name: INSPIRISY BANK LTD"],
    ["Sol ID: 101 / BRANCH OF THE BANK"],
    ["Name of the Bond Scheme: " + vm.schemeName],
    ["From Date: " + $filter("date")(vm.fromDate, "dd/MM/yyyy")],
    ["To Date: " + $filter("date")(vm.toDate, "dd/MM/yyyy")],
    [], // Empty row
  ];

  var headers = [
    "S.NO", "PAN No", "Bond Ledger Account Number", "Holder Name", "Subscription ID",
    "Subscription Date", "Subscription Amount Rs", "Due date of Repayment",
    "Date of Pre-Closed Repayment", "Gross Amount", "TDS Percentage",
    "TDS Amount", "Amount of Repayment Rs", "Credited to Bank Account No"
  ];

  var rows = vm.filteredRecords.map(function (record, index) {
    return [
      index + 1,
      record.panNo,
      record.blaNo,
      record.holderName,
      record.subscriptionId,
      record.subscriptionDate,
      record.subscriptionAmt,
      record.dueDateRepay,
      record.datePreclosRepay,
      record.grossAmt ? Math.round(record.grossAmt):"",
      record.tdsPercentage,
      record.tdsAmt,
      record.repayAmt ? Math.round(record.repayAmt):"",
      record.creditAccNo,
    ];
  });

  // Calculate totals
  var totalAmount = vm.filteredRecords.reduce((total, record) => total + parseFloat(record.subscriptionAmt || 0), 0);
  var grosstotal = vm.filteredRecords.reduce((total1, record) => total1 + parseFloat(record.grossAmt || 0), 0);
  var repaytotal = vm.filteredRecords.reduce((total2, record) => total2 + parseFloat(record.repayAmt || 0), 0);

  // Add total row
  rows.push([
    "", "", "", "", "", "Total",
    Math.round(totalAmount), "", "", Math.round(grosstotal),
    "", "", Math.round(repaytotal)
  ]);

  rows.unshift(headers);
  rows.unshift([]);  // Empty row
  bankDetails.reverse().forEach((detail) => rows.unshift(detail));
  rows.unshift(mainHeader);

  console.log(rows);  // For debugging - check the structure

  // Ensure rows is a 2D array before passing to aoa_to_sheet
  if (Array.isArray(rows) && Array.isArray(rows[0])) {
    var worksheet = XLSX.utils.aoa_to_sheet(rows);
    var workbook = XLSX.utils.book_new();
    XLSX.utils.book_append_sheet(workbook, worksheet, "Report");

    worksheet["A1"].s = {
      font: { bold: true, sz: 14 },
      alignment: { horizontal: "center", vertical: "center" },
    };
    worksheet["A2"].s = {
      font: { bold: true, sz: 12 },
      alignment: { horizontal: "center", vertical: "center" },
    };
    worksheet["A3"].s = {
      font: { bold: true, sz: 12 },
      alignment: { horizontal: "center", vertical: "center" },
    };

    worksheet["!merges"] = [
      { s: { r: 0, c: 0 }, e: { r: 0, c: headers.length - 1 } },
      { s: { r: 1, c: 0 }, e: { r: 1, c: headers.length - 1 } },
      { s: { r: 2, c: 0 }, e: { r: 2, c: headers.length - 1 } },
    ];

    for (var rowIndex = 4; rowIndex < rows.length; rowIndex++) {
      var cellAddress = { r: rowIndex, c: 4 };
      var cell = worksheet[XLSX.utils.encode_cell(cellAddress)];

      if (cell) {
        cell.s = {
          alignment: { horizontal: "right" },
        };
      }
    }

    var totalCellAddress = { r: rows.length - 1, c: 4 };
    var totalCell = worksheet[XLSX.utils.encode_cell(totalCellAddress)];

    if (totalCell) {
      totalCell.s = {
        alignment: { horizontal: "right" },
      };
    }
    XLSX.writeFile(workbook, "Bond pre-closure Repayment.xlsx");
  } else {
    console.error("Rows is not a 2D array:", rows);
  }
};

 vm.downloadCSV = function (currentReport) {
      const { filename, mainHeaders, subHeaders, tableHeaders, tableData } = currentReport;
      const formatRow = (values) => {
        return tableHeaders
          .map((col, i) => {
            const value = values[i] ? values[i].toString() : "";
            const alignment = col.type === "numeric" ? "right" : "left";
            if (alignment === "right") {
              return value.padStart(col.width, " ");
            } else {
              return value.padEnd(col.width, " ");
            }
          })
          .join("\t");
      };
      const formattedHeader = formatRow(tableHeaders.map((col) => col.title));
      const formattedData = tableData.map((row) => formatRow(row)).join("\n");

      const totalAmount = vm.filteredRecords.reduce((total, record) => total + parseFloat(record.subscriptionAmt || 0), 0);
      const totalAmountWithoutDecimal = Math.floor(totalAmount);

      const grosstotal = vm.filteredRecords.reduce((total1, record) => total1 + parseFloat(record.grossAmt || 0), 0);
      const grosstotalwithoutDecimal = Math.floor(grosstotal);

      const repaytotal = vm.filteredRecords.reduce((total1, record) => total1 + parseFloat(record.repayAmt || 0), 0);
      const repaytotalwithoutDecimal = Math.floor(repaytotal);

      const totalRow = formatRow(
        tableHeaders.map((col, i) => {
          if (i === tableHeaders.length - 9) return "Total";
          if (i === tableHeaders.length - 8) return totalAmountWithoutDecimal;
          if (i === tableHeaders.length - 5) return grosstotalwithoutDecimal;
          if (i === tableHeaders.length - 2) return repaytotalwithoutDecimal;
          return "";
        })
      );
      const textData = [mainHeaders.join("\n"), subHeaders.join("\n"), formattedHeader, formattedData, totalRow].join("\n");

      const textContent = "data:text/plain;charset=utf-8," + encodeURIComponent(textData);
      const link = document.createElement("a");
      link.setAttribute("href", textContent);
      link.setAttribute("download", filename + ".txt");
      document.body.appendChild(link);
      link.click();
      document.body.removeChild(link);
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
