(function () {
  "use strict";

  angular
    .module("gbmUltraApp")
    .controller("rbibondBondLedgeAccController", rbibondBondLedgeAccController);

  rbibondBondLedgeAccController.$inject = [
    "$scope",
    "$filter",
    "$state",
    "$stateParams",
    "$location",
    "$rootScope",
    "rbibondBondLedgeAccService",
    "commonService",
  ];

  function rbibondBondLedgeAccController(
    $scope,
    $filter,
    $state,
    $stateParams,
    $location,
    $rootScope,
    rbibondBondLedgeAccService,
    commonService
  ) {
    var vm = this;
    vm.itemsPerPage = "5";
    vm.loginSolId = $rootScope.globals.currentUser.solId;
    vm.loginSolDesc = $rootScope.globals.currentUser.solDesc;
    vm.solDate = $rootScope.globals.currentUser.systemDate;
    vm.currentDate = new Date();
    vm.currentDate = $filter("date")(vm.currentDate, "dd/MM/yyyy");
    vm.getRegBondLedgeAcc = getRegBondLedgeAcc;
    vm.getModuleName = getModuleName;
    vm.getBondType = getBondType;
    vm.getPantorch = getPantorch;
    vm.getBlaAccNo = getBlaAccNo;

    vm.fromDateVal = fromDateVal;
    vm.toDateVal = toDateVal;
    vm.pageNo = 1;
    vm.pageSizeOptions = [5, 10, 25, 50, 100];
    vm.pageSize = vm.pageSizeOptions[0];
    vm.currentPage = 1;
    if (
      $state.current.name ==
      "register_of_bond_ledger_accounts_single_holders.inquiry_details"
    ) {
      vm.schemeName = $stateParams.obj.schemeName;
      vm.bondType = $stateParams.obj.bondType;
      vm.holderName = $stateParams.obj.holderName;
      vm.panNo = $stateParams.obj.panNo;
      vm.blaNo = $stateParams.obj.blaNo;
      vm.fromDate = $stateParams.obj.fromDate;
      vm.toDate = $stateParams.obj.toDate;
      vm.moduleName = $stateParams.obj.schemeName;
      vm.subCode = $stateParams.obj.subCode;
        getRegBondLedgeAcc();
    }

    //    Pagination Integration
    vm.goPreviousPage = goPreviousPage;
    vm.goNextPage = goNextPage;
    vm.paginationControls = paginationControls;
    vm.showNext = false;
    vm.showPrevious = false;
    $scope.sortReverse = false;
    $scope.sort = function (key) {
      $scope.sortKey = key;
      $scope.sortReverse =
        $scope.sortKey == key ? !$scope.sortReverse : $scope.sortReverse;
      $scope.sortReverse ? (vm.sortOrder = "DESC") : (vm.sortOrder = "ASC");
      vm.sortField = key;
      vm.getRegBondLedgeAcc();
    };
    vm.pageData = null;
    vm.records = [];
    vm.filteredRecords = [];

    vm.maxPagesToShow = 5;
    vm.searchValue = vm.searchValue || "";
    vm.loading = true;
    vm.showExportOptions = false;
    vm.itemsPerPage = "10";

    function goPreviousPage() {
      vm.pageNo = parseInt(vm.pageNo) - 1;
      vm.getRegBondLedgeAcc();
    }

    function goNextPage() {
      vm.pageNo = parseInt(vm.pageNo) + 1;
      vm.getRegBondLedgeAcc();
    }

    function paginationControls () {
                     vm.pages = [];
                     for(var i=1; i<= parseInt(vm.maxPages); i++){
                        vm.pages.push(parseInt(i));
                      }
                     if(vm.pageNo != 1){
                         vm.showPrevious = true;
                     }
                     else{
                         vm.showPrevious = false;
                     }

                     if(vm.pageNo != vm.maxPages){
                          vm.showNext = true;
                     }
                     else{
                          vm.showNext = false;
                     }
                     }

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


    vm.setPage = function (page) {
      console.log("Page Number in setPage: " + page);
      if (page < 1) {
        vm.currentPage = 1;
      } else if (page > vm.totalPages) {
        vm.currentPage = vm.totalPages;
      } else {
        vm.currentPage = page;
      }
      console.log("vm.currentPage: " + vm.currentPage);
      vm.updatePagination();
    };

    vm.updatePagination = function () {
      try {
        var startPage, endPage;
        console.log("Inside updatePagination function...");

        if (vm.totalPages <= vm.maxPagesToShow) {
          startPage = 1;
          endPage = vm.totalPages;
        } else {
          var maxPagesBeforeCurrentPage = Math.floor(vm.maxPagesToShow / 2);
          var maxPagesAfterCurrentPage = Math.ceil(vm.maxPagesToShow / 2) - 1;

          if (vm.currentPage <= maxPagesBeforeCurrentPage) {
            startPage = 1;
            endPage = vm.maxPagesToShow;
          } else if (
            vm.currentPage + maxPagesAfterCurrentPage >=
            vm.totalPages
          ) {
            startPage = vm.totalPages - vm.maxPagesToShow + 1;
            endPage = vm.totalPages;
          } else {
            startPage = vm.currentPage - maxPagesBeforeCurrentPage;
            endPage = vm.currentPage + maxPagesAfterCurrentPage;
          }
        }

        console.log("vm.totalPages: " + vm.totalPages);
        console.log("startPage: " + startPage);
        console.log("endPage: " + endPage);

        vm.pages = [];
        for (var i = startPage; i <= endPage; i++) {
          vm.pages.push(i);
        }
        console.log("vm.pages: " + vm.pages);
      } catch (error) {
        console.error("An error occurred in updatePagination:", error);
      }
    };

    vm.updatePageSize = function () {
      vm.totalPages = Math.ceil(vm.filteredRecords.length / vm.pageSize);
      console.log("totalPages: " + vm.totalPages);
      vm.setPage(1);
    };

    vm.prevPage = function () {
      vm.setPage(vm.currentPage - 1);
    };

    vm.nextPage = function () {
      vm.setPage(vm.currentPage + 1);
    };

    vm.firstPage = function () {
      vm.setPage(1);
    };

    vm.lastPage = function () {
      vm.setPage(vm.totalPages);
    };

    vm.updateFilteredRecords = function () {
      const searchText = vm.searchText ? vm.searchText.toLowerCase() : "";
      vm.filteredRecords = vm.records.filter((record) => {
        return Object.values(record).some(
          (value) =>
            value !== null &&
            value !== undefined &&
            String(value).toLowerCase().includes(searchText)
        );
      });
      vm.totalPages = Math.ceil(vm.filteredRecords.length / vm.pageSize);
      vm.setPage(1);
    };

    vm.maxValue = function () {
      return Math.min(vm.currentPage * vm.pageSize, vm.filteredRecords.length);
    };

    var additionalHeaders = [
      "REGISTER OF BOND LEDGER ACCOUNTS - INDIVIDUAL ACCOUNT REPORT",
      "Bank Name : INSPIRISY BANK LTD",
      "Sol ID : 101 / BRANCH OF THE BANK",
      "Name of the Bond Scheme: " + vm.schemeName,
      "From Date : " +
        $filter("date")(vm.fromDate, "dd-MM-yyyy") +
        " To Date :" +
        $filter("date")(vm.toDate, "dd-MM-yyyy"),
      "",
    ];

    vm.downloadExcel = function () {
      var headers = [
        "S.No.",
        //    "Bla No.",
        "Address of the Single/First Holder",
        "Date of Transaction",
        "Investment Value",
        "Maturity Value",
        "CAS Advice No. & Date",
        "Initials",
        "Sale or Purchase @",
        "Investment Amount",
        "Maturity Value",
        "Due Date of Repayment",
        "Investment Amount",
        "Maturity Value",
        "Balance (Investment Amt)",
        "Remarks",
        "Initials",
      ];

      // Prepare additional headers as rows
      var additionalHeaderRows = additionalHeaders.map(function (header) {
        return [header];
      });

      // Prepare data rows
      var rows = vm.model.map(function (row, index) {
        return [
          index + 1,
          //      row.blaNo,
          row.addressOfTheHolder,
          row.dateOfTransaction,
          row.amtOfSubs,
          row.maturityVal,
          row.casAdviceNoDate,
          row.subsId,
          row.transferSaleBurchase,
          row.transferNominalValue,
          row.transferNominalAmt,
          row.dueDateOfRepayment,
          row.repaymntNominalAmt,
          row.repaymntMaturityValue,
          row.balance,
          row.remarks,
        ];
      });

      var finalRows = additionalHeaderRows.concat([headers], rows);

      var worksheet = XLSX.utils.aoa_to_sheet(finalRows);
      var workbook = XLSX.utils.book_new();

      worksheet["A1"].s = {
        font: { bold: true, sz: 14 }, // Set bold and font size 14
        alignment: { horizontal: "center", vertical: "center" },
      };
      worksheet["A2"].s = {
        font: { bold: true, sz: 14 }, // Set bold and font size 14
        alignment: { horizontal: "center", vertical: "center" },
      };

      worksheet["!merges"] = [
        { s: { r: 0, c: 0 }, e: { r: 0, c: headers.length - 1 } }, // Merge the first row for the main header
      ];

      XLSX.utils.book_append_sheet(workbook, worksheet, "Report");
      XLSX.writeFile(workbook, "REGISTER OF BOND LEDGER ACCOUNTS REPORT.xlsx");
    };

    var additionalHeaders = [
      "REGISTER OF BOND LEDGER ACCOUNTS - INDIVIDUAL ACCOUNT REPORT",
      "Bank Name : INSPIRISY BANK LTD",
      "Sol ID : 101 / BRANCH OF THE BANK",
      "Name of the Bond Scheme: " + vm.schemeName,
      "From Date : " +
        $filter("date")(vm.fromDate, "dd-MM-yyyy") +
        " To Date : " +
        $filter("date")(vm.toDate, "dd-MM-yyyy"),
      "",
    ];

    vm.downloadCsv = function () {
      var csvContent =
        "data:text/csv;charset=utf-8," +
        additionalHeaders.join("\n") +
        "\n" +
        "| Address of the Single/First Holder | Date of Transaction | Investment Value | Maturity Value | CAS Advice No. & Date           |   Initials    |Sale or Purchase @ | Investment Amount | Maturity Value | Due Date of Repayment| Investment Amount | Maturity Value | Balance(Investment Amt)| Remarks | Initials |\n" +
        vm.model
          .map(function (row) {
            return (
              "| " +
              //          (row.blaNo || "").padEnd(23) + " | " +
              (row.addressOfTheHolder || "").padEnd(34) +
              " | " +
              (row.dateOfTransaction || "").padEnd(19) +
              " | " +
              (row.amtOfSubs ? row.amtOfSubs.toString() : "").padEnd(13) +
              " | " +
              (row.maturityVal ? row.maturityVal.toString() : "").padEnd(14) +
              " | " +
              (row.casAdviceNoDate || "").padEnd(31) +
              " | " +
              (row.subsId || "").padEnd(8) +
              " | " +
              (row.transferSaleBurchase || "").padEnd(17) +
              " | " +
              (row.transferNominalValue
                ? row.transferNominalValue.toString()
                : ""
              ).padEnd(14) +
              " | " +
              (row.transferNominalAmt
                ? row.transferNominalAmt.toString()
                : ""
              ).padEnd(14) +
              " | " +
              (row.dueDateOfRepayment || "").padEnd(20) +
              " | " +
              (row.repaymntNominalAmt
                ? row.repaymntNominalAmt.toString()
                : ""
              ).padEnd(14) +
              " | " +
              (row.repaymntMaturityValue
                ? row.repaymntMaturityValue.toString()
                : ""
              ).padEnd(14) +
              " | " +
              (row.balance ? row.balance.toString() : "").padEnd(19) +
              " | " +
              (row.remarks || "").padEnd(7) +
              " | "
            );
          })
          .join("\n") +
        "\n";

      var encodedUri = encodeURI(csvContent);
      var link = document.createElement("a");
      link.setAttribute("href", encodedUri);
      link.setAttribute(
        "download",
        "REGISTER OF BOND LEDGER ACCOUNTS REPORT.txt"
      );
      document.body.appendChild(link);

      link.click();
      document.body.removeChild(link);
    };

    vm.handleDownloadOption = function () {
      if (vm.downloadType === "excel") {
//        vm.downloadExcel();
            if (response.data.result.fileName) {
                 vm.fileData = response.data.result.fileData;
                 vm.fileName = response.data.result.fileName;
                 saveAs(getBlobFromBase64String(vm.fileData), vm.fileName)
                 Swal.fire("Downloaded", response.data.message, "success");
            }
      } else if (vm.downloadType === "csv") {
        vm.downloadCsv();
      } else if (vm.downloadType == "pdf") {
        vm.downloadPDF();
      }
      vm.selectedOption = "";
    };

    var additionalHeaders = [
      "REGISTER OF BOND LEDGER ACCOUNTS - INDIVIDUAL ACCOUNT REPORT",
      "Bank Name : INSPIRISY BANK LTD",
      "Sol ID : 101 / BRANCH OF THE BANK",
      "Name of the Bond Scheme: " + vm.schemeName,
      "From Date : " +
        $filter("date")(vm.fromDate, "dd-MM-yyyy") +
        " To Date :" +
        $filter("date")(vm.toDate, "dd-MM-yyyy"),
      "",
    ];

    vm.downloadPDF = function () {
      const { jsPDF } = window.jspdf;
      const doc = new jsPDF();

      const rowsPerPage = 20; // Number of rows per page
      const columnsFirstSet = 8; // Number of columns in the first table

      const tableData = vm.filteredRecords.map((row, index) => [
        index + 1,
        //    row.blaNo,
        row.addressOfTheHolder,
        row.dateOfTransaction,
        row.amtOfSubs,
        row.maturityVal,
        row.casAdviceNoDate,
        row.subsId,
        row.transferSaleBurchase,
        row.transferNominalValue,
        row.transferNominalAmt,
        row.dueDateOfRepayment,
        row.repaymntNominalAmt,
        row.repaymntMaturityValue,
        row.balance,
        row.remarks,
      ]);

      const tableHeader = [
        "S.No.",
        //    "Bla No.",
        "Address of the Single/First Holder",
        "Date of Transaction",
        "Investment Value",
        "Maturity Value",
        "CAS Advice No. & Date",
        "Initials",
        "Sale or Purchase @",
        "Investment Amount",
        "Maturity Value",
        "Due Date of Repayment",
        "Investment Amount",
        "Maturity Value",
        "Balance (Investment Amt)",
        "Remarks",
      ];

      const tableHeader1 = tableHeader.slice(0, columnsFirstSet); // First set of columns
      const tableHeader2 = tableHeader.slice(columnsFirstSet); // Second set of columns

      // Helper function to paginate data
      const paginateData = (data, rowsPerPage) => {
        const pages = [];
        for (let i = 0; i < data.length; i += rowsPerPage) {
          pages.push(data.slice(i, i + rowsPerPage));
        }
        return pages;
      };

      // Paginate table data
      const paginatedData = paginateData(tableData, rowsPerPage);

      doc.setFontSize(10); // Set font size for additional headers
      additionalHeaders.forEach((header, index) => {
        doc.text(header, 8, 8 + index * 8); // Adjust vertical spacing
      });

      // Loop through each page and add data
      paginatedData.forEach((pageData, pageIndex) => {
        if (pageIndex > 0) doc.addPage(); // Add a new page for each set

        // First Table (First 8 columns)
        doc.autoTable({
          head: [tableHeader1],
          body: pageData.map((row) => row.slice(0, columnsFirstSet)),
          startY: 50,
          margin: { top: 10 },
          styles: {
            fontSize: 8,
            overflow: "linebreak",
            cellPadding: 2,
            lineWidth: 0.1,
            lineColor: [0, 0, 0],
          },
          columnStyles: {
            0: { cellWidth: 10 }, // Adjust width as needed
            2: { cellWidth: 50 },
          },
        });

        doc.addPage(); // Add a new page for the second table

        // Second Table (Remaining columns)
        doc.autoTable({
          head: [tableHeader2],
          body: pageData.map((row) => row.slice(columnsFirstSet)),
          startY: 50,
          margin: { top: 10 },
          styles: {
            fontSize: 8,
            overflow: "linebreak",
            cellPadding: 2,
            lineWidth: 0.1,
            lineColor: [0, 0, 0],
          },
          columnStyles: {
            0: { cellWidth: 10 }, // Adjust width as needed
            1: { cellWidth: 20 },
          },
        });
      });

      doc.save("REGISTER_OF_BOND_LEDGER_ACCOUNTS_REPORT.pdf");
    };

    function configDataTable() {
      vm.getTitleDescription = {
        title: "Bond Nomination Register",
        solId: $rootScope.globals.currentUser.solId,
        solDesc: $rootScope.globals.currentUser.solDesc,
      };
      vm.dataTableOption = configDataTableOptions(vm.getTitleDescription);
    }

    function getPantorch() {
      vm.loader = true;

      if (vm.bondType == "CUMULATIVE ") {
        vm.bondTypes = "C";
      } else if (vm.bondType == "NON-CUMULATIVE") {
        vm.bondTypes = "NC";
      }

      var requestData = {
        schemeCode: vm.subCode,
        panNo: vm.panNo,
        holderName: vm.holderName,
        bondType: vm.bondTypes,
        flag: "P",
      };

      rbibondBondLedgeAccService.getPantorch(requestData).then(
        function (response) {


          vm.loader = false;
          vm.panNoDataList = response.data.result.bondLedgerAccDtoList;


          angular.element("#panModel").modal("toggle");
        },
        function (error) {

          handleErrorResponse(error);
          vm.loader = false;
        }
      );
    }

    function getBlaAccNo() {
      vm.loader = true;

      var requestData = {
        holderName: vm.holderName,
        panNo: vm.panNo,
        blaNo: vm.blaNo,
        schemeCode: vm.subCode,
        flag: "C",
      };
      rbibondBondLedgeAccService.getBlaNum(requestData).then(
        function (response) {

          vm.loader = false;
          vm.blaDataList = response.data.result.bondLedgerAccDtoList;

          angular.element("#blaModel").modal("toggle");
        },
        function (error) {
          handleErrorResponse(error);
          vm.loader = false;
        }
      );
    }

    function getBondType() {
      vm.loader = true;
      var requestData = {
        schemeName: vm.schemeName,
//        blaflg: "Y",
        flag: "BT",
      };
      rbibondBondLedgeAccService.getBondDet(requestData).then(
        function (response) {
          vm.loader = false;
          vm.bondTypeList = response.data.result.bondLedgerAccDtoList;

          angular.element("#bondModel").modal("toggle");
        },
        function (error) {
          handleErrorResponse(error);
          vm.loader = false;
        }
      );
    }
    function getModuleName() {
      vm.loader = true;
      var requestData = {
        flag: "S",
      };
      rbibondBondLedgeAccService.getRbiBondModuleNameDetails(requestData).then(
        function (response) {
          vm.loader = false;
          vm.schemeList = response.data.result.bondLedgerAccDtoList;

          vm.moduleName = response.data.result.schemeName;

          angular.element("#schemeModel").modal("toggle");
        },
        function (error) {
          handleErrorResponse(error);
          vm.loader = false;
        }
      );
    }

    function getRegBondLedgeAcc() {
      vm.loader = true;

      if (vm.bondType == "CUMMULATIVE ") {
        vm.bondTypess = "C";
      } else if (vm.bondType == "NON CUMMULATIVE") {
        vm.bondTypess = "NC";
      }

      var requestData = {
        schemeCode: vm.subCode,
        bondType: vm.bondTypess || "",
        holderName: vm.holderName || "",
        panNo: vm.panNo || "",
        blaNo: vm.blaNo || "",
        fromDate: moment(vm.fromDate).format("DD-MMM-YYYY"),
        toDate: moment(vm.toDate).format("DD-MMM-YYYY"),
        flag: "D",

        pageNo    : vm.pageNo,
        pageSize  : vm.pageSize,
        searchValue :vm.searchValue,
        sortOrder:vm.sortOrder,
        sortField:vm.sortField,
      };

      rbibondBondLedgeAccService.getRegBondLedgeAcc(requestData).then(
        function (response) {
          vm.loader = false;

          vm.model = response.data.result.bondLedgerAccDtoList;
          vm.total= response.data.total;

          if (vm.model.length === 0) {
            vm.noDataFound = true;
            vm.noDataFoundInSearch = true;
          } else {
            vm.noDataFound = false;
            vm.noDataFoundInSearch = false;
          }

          vm.filteredRecords = response.data.result.bondLedgerAccDtoList;
          vm.records = response.data.result.bondLedgerAccDtoList;
          vm.totalPages = Math.ceil(vm.model.length / vm.pageSize);
          vm.pageDetails = response.data.result.bondLedgerAccDtoList;
          vm.maxPages =  Math.ceil(response.data.total/vm.pageSize);
            vm.paginationControls();
        },
        function (error) {
          vm.loader = false;
          if (vm.searchValue && (error.data.message ==  "No Data Found") || (error.data.message == "NDF")) {
//             vm.noDataFound = error.data.message;
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
            $state.go("register_of_bond_ledger_accounts_single_holders.inquiry");
          }
        }
      );
    }

    vm.goNextState = function (stateObj) {
      $state.go(vm.nextState, {
        obj: stateObj,
      });
    };
    vm.goPreviousState = function () {
      if (
        $state.current.name ==
        "register_of_bond_ledger_accounts_single_holders.inquiry_details"
      ) {
        $state.go("register_of_bond_ledger_accounts_single_holders.inquiry");
      }
    };

    vm.maxValue = function () {
      return Math.min(vm.currentPage * vm.pageSize, vm.filteredRecords.length);
    };
    function fromDateVal() {
      vm.fromDateOptions = {
        maxDate: vm.toDate ? new Date(vm.toDate) : new Date(),
        showWeeks: false,
      };
    }

    function toDateVal(fromDate) {
      vm.toDateOptions = {
        minDate: new Date(fromDate),
        maxDate: new Date(),
        showWeeks: false,
      };
    }

    vm.getDownload=getDownload;
    function getDownload() {
        vm.loader = true;
        var requestData = {

        schemeCode: vm.subCode,
                bondType: vm.bondTypess || "",
                holderName: vm.holderName || "",
                panNo: vm.panNo || "",
                blaNo: vm.blaNo || "",
                fromDate: moment(vm.fromDate).format("DD-MMM-YYYY"),
                toDate: moment(vm.toDate).format("DD-MMM-YYYY"),
                flag: "D",

                pageNo : 1,
                pageSize: vm.total,
                searchValue:vm.searchValue,
                sortOrder:vm.sortOrder,
                sortField:vm.sortField,
        };
        rbibondBondLedgeAccService.getRegBondLedgeAccDownload(requestData).then(
            function (response) {
                vm.loader = false;
                vm.filteredRecord = response.data.result;
                if (vm.downloadType === "excel") {
//                vm.downloadExcel();
                    if (response.data.result.fileName) {
                         vm.fileData = response.data.result.fileData;
                         vm.fileName = response.data.result.fileName;
                         saveAs(getBlobFromBase64String(vm.fileData), vm.fileName)
                         Swal.fire("Downloaded", response.data.message, "success");
                    }
              } else if (vm.downloadType === "csv") {
                vm.downloadCsv();
              } else if (vm.downloadType === "pdf") {
                vm.downloadPDF();
              }
              vm.downloadType = "";
                },function (error) {
               vm.loader = false;
               if ((error.data.message == "No Data Found") || (error.data.message == "NDF")) {
                 vm.noDataFound = error.data.message;
               }else {
                 handleErrorResponse(error);
                 $state.go("register_of_bond_ledger_accounts_single_holders.inquiry");
               }
              });
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