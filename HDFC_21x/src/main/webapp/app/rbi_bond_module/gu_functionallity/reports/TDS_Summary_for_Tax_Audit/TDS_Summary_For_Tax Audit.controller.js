(function () {
  "use strict";

  angular
    .module("gbmUltraApp")
    .controller(
      "tdsSummaryTaxAuditController",
      tdsSummaryTaxAuditController
    );

  tdsSummaryTaxAuditController.$inject = [
    "$scope",
    "$filter",
    "$state",
    "$stateParams",
    "$location",
    "$rootScope",
    "tdsSummaryTaxAuditService",
    "commonService",

  ];

  function tdsSummaryTaxAuditController(
    $scope,
    $filter,
    $state,
    $stateParams,
    $location,
    $rootScope,
    tdsSummaryTaxAuditService,
    commonService
  ) {
    var vm = this;

//    vm.currentPage = 1;
//    vm.itemsPerPage = "5";
    vm.loginSolId = $rootScope.globals.currentUser.solId;
    vm.loginSolDesc = $rootScope.globals.currentUser.solDesc;
    vm.solDate = $rootScope.globals.currentUser.systemDate;
    vm.gettdsSummaryTaxAuditReport = gettdsSummaryTaxAuditReport;
    vm.printpage = printpage;
    vm.solDate = $filter("date")($rootScope.globals.currentUser.systemDate, "dd/MM/yyyy");
    vm.loginSolDate = $rootScope.globals.currentUser.solDate;

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
         vm.getDetails();
       };

       vm.pageNo = 1;
       vm.pageSize = 5;

       vm.goPreviousPage = function () {
         vm.pageNo = parseInt(vm.pageNo) - 1;
         vm.gettdsSummaryTaxAuditReport();
       };

       vm.goNextPage = function () {
         vm.pageNo = parseInt(vm.pageNo) + 1;
         vm.gettdsSummaryTaxAuditReport();
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


    if ($state.current.name == "tds_summary_for_tax_audit.inquiry_details") {
          vm.financialYear = $stateParams.obj.financialYear;
          vm.month=$stateParams.obj.month;
              gettdsSummaryTaxAuditReport();
    }

    vm.downloadReport = function () {
          vm.loader = true;
                var requestData = {
                  financialYear: vm.financialYear,
                  month: vm.month,
                  flag:"D",
                  pageNo: 1,
                  pageSize: vm.total,
                  searchValue: vm.searchValue,
                };
                tdsSummaryTaxAuditService.getReportDetailsDownload(requestData).then(
                  function (response) {
                    vm.loader = false;
                    vm.filteredRecord = response.data.result.tdsSummaryForTaxAuditDTOList;
//                    vm.totalAmountPaidDownload = vm.filteredRecord.reduce((sum, record) => sum + (record.totalAmountPaid ? parseFloat(record.totalAmountPaid) : 0), 0);
//                    vm.totalTaxDownload = vm.filteredRecord.reduce((sum, record) => sum + (record.totalTaxDeducted ? parseFloat(record.totalTaxDeducted) : 0), 0);
//                    vm.totalChallanDownload= vm.filteredRecord.reduce((sum, record) => sum + (record.totalChallanAmount ? parseFloat(record.totalChallanAmount) : 0), 0);
                     if (vm.downloadType === "excel") {
//                                 vm.downloadExcel();
            if (response.data.result.fileName) {
                 vm.fileData = response.data.result.fileData;
                 vm.fileName = response.data.result.fileName;
                 saveAs(getBlobFromBase64String(vm.fileData), vm.fileName)
                 Swal.fire("Downloaded", response.data.message, "success");
            }
                               } else if (vm.downloadType === "csv") {
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
                      $state.go("tds_summary_for_tax_audit.inquiry");
                    }
                  }
                );
        };

vm.downloadCSV = function () {
    const tableHeader = [
        { title: "S.No.", width: 10, type: "numeric" },
        { title: "Challan Serial No", width: 30, type: "string" },
        { title: "For The Month Of", width: 25, type: "string" },
        { title: "Quarter", width: 15, type: "string" },
        { title: "Date Of Payment", width: 20, type: "string" },
        { title: "Nature Of Payment", width: 25, type: "string" },
        { title: "Quarterly Form", width: 20, type: "string" },
        { title: "Total Amount Paid", width: 25, type: "numeric" },
        { title: "Total Tax Deducted", width: 35, type: "numeric" },
        { title: "Tax Deposited(Total Challan Amount)", width: 35, type: "numeric" },
        { title: "Team", width: 30, type: "string" },
        { title: "Difference [CTT][J-I]", width: 30, type: "string" },
        { title: "Teams Revert For Difference", width: 30, type: "string" }
    ];

    const formatRow = (values) => {
        return tableHeader
            .map((col, i) => {
                const value = values[i] ? values[i].toString() : "";
                const alignment = col.type === "numeric" ? "right" : "center"; // Align numeric values right, others centered
                // Ensure padding for columns with consistent width and center-align text
                return value.padStart(Math.floor((col.width + value.length) / 2), " ").padEnd(col.width, " ");
            })
            .join("") + "\n"; // Add newline for vertical alignment
    };

    const formattedHeader = formatRow(tableHeader.map((col) => col.title));

    if (!vm.filteredRecord || vm.filteredRecord.length === 0) {
        alert("No records to download.");
        return;
    }

    const tableData = vm.filteredRecord.map((record, index) =>
        formatRow([
            (index + 1).toString(),
            record.challanSerialNo || "",
            record.forTheMonthOf || "",
            record.quater || "",
            record.dateOfPayment || "",
            record.natureOfPayment || "",
            record.quaterlyForm || "",
            record.totalAmountPaid || "",
            record.totalTaxDeducted || "",
            record.totalChallanAmount || "",
            record.team || "",
            record.difference || "",
            record.teamsRevertForDifference || ""
        ])
    );

    // Add summary row
    const summaryRow = formatRow([
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        vm.totalAmountPaidDownload || 0,
        vm.totalTaxDownload|| 0,
        vm.totalChallanDownload || 0,
        "",
        "",
        ""
    ]);

    const textData = [
        "TDS Summary For Tax Audit",
        "Bank Name: HDFC",
        "Branch Name: BRANCH OF THE BANK",
        "Branch Id: 101",
        formattedHeader,
        ...tableData,
        summaryRow // Add summary row to the CSV
    ].join("\n");

    const textContent = "data:text/plain;charset=utf-8," + encodeURIComponent(textData);
    const link = document.createElement("a");
    link.setAttribute("href", textContent);
    link.setAttribute("download", "TDS_Summary_For_Tax_Audit.txt");
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

vm.downloadExcel = function () {
    if (!vm.filteredRecord || vm.filteredRecord.length === 0) {
        alert("No records to download.");
        return;
    }

    var title = [["TDS Summary For Tax Audit"]];
    var additionalDetails = [
        ["Bank Name : INSPIRISY BANK LTD"],
        ["Sol ID : 101 / BRANCH OF THE BANK"]
    ];
    var headers = [
        [
            "S.No.",
            "Challan Serial No",
            "For The Month Of",
            "Quarter",
            "Date Of Payment",
            "Nature Of Payment",
            "Quarterly Form",
            "Total Amount Paid",
            "Total Tax Deducted",
            "Tax Deposited(Total Challan Amount)",
            "Team",
            "Difference[CTT][J-I]",
            "Team's Revert For Difference"
        ]
    ];

    var rows = vm.filteredRecord.map(function (record, index) {
        return [
            index + 1,
            record.challanSerialNo || "",
            record.forTheMonthOf || "",
            record.quater || "",
            record.dateOfPayment || "",
            record.natureOfPayment || "",
            record.quaterlyForm || "",
            record.totalAmountPaid || "",
            record.totalTaxDeducted || "",
            record.totalChallanAmount || "",
            record.team || "",
            record.difference || "",
            record.teamsRevertForDifference || ""
        ];
    });

    // Add summary row
    var summaryRow = [
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        vm.totalAmountPaidDownload || 0,
        vm.totalTaxDownload || 0,
        vm.totalChallanDownload || 0,
        "",
        "",
        ""
    ];

    // Combine data for Excel
    var excelData = [].concat(title, additionalDetails, headers, rows, [summaryRow]);

    var wb = XLSX.utils.book_new();
    var ws = XLSX.utils.aoa_to_sheet(excelData);

    XLSX.utils.book_append_sheet(wb, ws, "TDS Summary for Tax Audit");

    XLSX.writeFile(wb, `TDS_Summary_Tax_Audit_${new Date().toLocaleDateString()}.xlsx`);
};


    function printpage() {
      window.print();
    }

    vm.maxValue = function () {
          return Math.min(vm.currentPage * vm.pageSize, vm.filteredRecords.length);
        }

    vm.getFinancialYears = function () {
          vm.loader = true;
          var requestData = {
            flag: "FY",
          };
          tdsSummaryTaxAuditService.getReportDetails(requestData).then(
            function (response) {
              vm.loader = false;
              vm.financialYears = response.data.result.tdsSummaryForTaxAuditDTOList;
            },
            function (error) {
              handleErrorResponse(error);
              vm.loader = false;
            }
          );
        };

    function gettdsSummaryTaxAuditReport() {

      var requestData = {
        financialYear: vm.financialYear,
        flag:'D',
        month: vm.month,
        pageNo: vm.pageNo,
        pageSize: vm.pageSize,
        searchValue: vm.searchValue,
        sortOrder: vm.sortOrder,
        sortField: vm.sortField,
      };

      tdsSummaryTaxAuditService.getReportDetails(requestData).then(
        function (response) {
          vm.loader = false;
          vm.ListOfDtos = response.data.result.tdsSummaryForTaxAuditDTOList;
          vm.filteredRecords = response.data.result.tdsSummaryForTaxAuditDTOList;
          vm.pageDetails = response.data.result.tdsSummaryForTaxAuditDTOList;
          vm.totalAmountPaid = vm.filteredRecords.reduce((sum, record) => sum + (record.totalAmountPaid ? parseFloat(record.totalAmountPaid) : 0), 0);
          vm.totalTax = vm.filteredRecords.reduce((sum, record) => sum + (record.totalTaxDeducted ? parseFloat(record.totalTaxDeducted) : 0), 0);
          vm.totalChallan = vm.filteredRecords.reduce((sum, record) => sum + (record.taxDeposited ? parseFloat(record.taxDeposited) : 0), 0);
          vm.records = response.data.result.tdsSummaryForTaxAuditDTOList;
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
                $state.go("tds_summary_for_tax_audit.inquiry");
          }
        }
      );
    }
  }
})();
