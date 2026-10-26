(function () {
  "use strict";

  angular.module("gbmUltraApp").controller("blaStatementBondHolderController", blaStatementBondHolderController);

  blaStatementBondHolderController.$inject = ["$scope", "$filter", "$state", "$stateParams", "$location", "$rootScope", "blaStatementBondHolderService", "commonService"];

  function blaStatementBondHolderController($scope, $filter, $state, $stateParams, $location, $rootScope, blaStatementBondHolderService, commonService) {
    var vm = this;
    vm.getDetails = getDetails;

    vm.textableDatePicker = textableDatePicker;
    vm.autoslashing = autoslashing;
    vm.indianNumberFormat=indianNumberFormat;

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
    vm.totalAmountInIndianFormat  = "";
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

    if ($state.current.name == "BLA_statement_for_Bondholders_report.inquiry_details") {
      vm.blaNumber = $stateParams.obj.blaNumber;
      vm.fromDate = $stateParams.obj.fromDate;
      vm.toDate = $stateParams.obj.toDate;
      vm.schemeCode = $stateParams.obj.schemeCode;
      vm.schemeName = $stateParams.obj.schemeName;
      vm.panNum = $stateParams.obj.panNum;
      vm.blaNo = $stateParams.obj.blaNo;
      vm.bondHolderName = $stateParams.obj.bondHolderName;
      getDetails();
    }

    vm.downloadReport = function () {
      vm.loader = true;
      var requestData = {
        panNo: vm.panNum,
        blaNumber: vm.blaNo,
        schemeCode: vm.schemeCode,
        schmeName : vm.schemeName,
        fromDate: $filter("date")(vm.fromDate, "dd-MMM-yyyy"),
        toDate: $filter("date")(vm.toDate, "dd-MMM-yyyy"),
        flag: "T",
        bondholdername: vm.bondHolderName,
        pageNo: "1",
        pageSize: vm.total,
        searchValue: vm.searchText,
        downloadType : vm.downloadType
      };
      blaStatementBondHolderService.getDetails(requestData).then(
        function (response) {
          vm.loader = false;
          vm.downloadFilteredRecords = response.data.result.list;

           if (response.data.result.fileName) {
                                  vm.fileData = response.data.result.fileData;
                                  vm.fileName = response.data.result.fileName;
                                  saveAs(getBlobFromBase64String(vm.fileData), vm.fileName)
                             }

//          if (vm.downloadType === "excel") {
//            vm.downloadExcel();
//}
           else if (vm.downloadType === "csv") {
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
            $state.go("BLA_statement_for_Bondholders_report.inquiry");
          }
        }
      );
    };

    vm.downloadExcel = function () {
      const additionalHeaders = [["BLA Statement For BondHolders Report"], ["Sol ID: 101 / BRANCH OF THE BANK"], ["Name of the Bond Scheme: " + vm.schemeName]];
      if (vm.fromDate) {
        additionalHeaders.push(["From Date: " + $filter("date")(vm.fromDate, "dd/MM/yyyy")]);
      }

      if (vm.toDate) {
        additionalHeaders.push(["To Date: " + $filter("date")(vm.toDate, "dd/MM/yyyy")]);
      }
      additionalHeaders.push([""]);
      let tableHeader = [];
      let tableData = [];
      let fileName = "BLA Statement For BondHolders.xlsx";
      let totalAmountColumnIndex = 0;

      if ((vm.schemeCode && vm.blaNo && !vm.panNum) || (vm.schemeCode && vm.blaNo && vm.panNum && !vm.fromDate && !vm.toDate)) {
        additionalHeaders.push(["BLA No: " + vm.blaNo], ["Holder Name: " + vm.bondHolderName], ["Address: " + vm.ListOfDtos.p_single_bond_hldr_add_out], [""]);
        tableHeader = ["S.No", "Date of Subscription", "Subscription ID", "Bond Type", "Maturity Date", "Subscription Amount Rs.", "Subscription Status"];
        totalAmountColumnIndex = 5;
        tableData = vm.downloadFilteredRecords.map((record, index) => [index + 1, record.dateOfSubscription, record.subscriptionId, record.bondtype, record.maturitydate, record.subcriptionamount, record.subsStatus]);

        fileName = "BLA_Report.xlsx";
      } else if ((vm.schemeCode && vm.fromDate && vm.toDate && !vm.panNum && !vm.blaNo) || (vm.schemeCode && vm.blaNo && (vm.panNum || vm.bondHolderName || vm.fromDate || vm.toDate))) {
        tableHeader = ["S.No", "Customer Name", "Address", "PAN No", "BLA No", "Date of Subscription", "Subscription ID", "Bond Type", "Maturity Date", "Subscription Amount Rs.", "Subscription Status"];
        totalAmountColumnIndex = 9;
        tableData = vm.downloadFilteredRecords.map((record, index) => [
          index + 1,
          record.bondholdername,
          record.address,
          record.panNo,
          record.blaNumber,
          record.dateOfSubscription,
          record.subscriptionId,
          record.bondtype,
          record.maturitydate,
          record.subcriptionamount,
          record.subsStatus,
        ]);

        fileName = "BLA Statement For BondHolders.xlsx";
      } else if (vm.schemeCode && vm.panNum && !vm.blaNo) {
        additionalHeaders.push(["PAN No: " + vm.panNum], ["Holder Name: " + vm.bondHolderName], ["Address: " + vm.ListOfDtos.p_single_bond_hldr_add_out], [""]);
        tableHeader = ["S.No", "BLA No", "Subscription ID", "Date of Subscription", "Bond Type", "Maturity Date", "Subscription Amount Rs.                    ", "Subscription Status"];
        totalAmountColumnIndex = 6;
        tableData = vm.downloadFilteredRecords.map((record, index) => [index + 1, record.blaNumber, record.subscriptionId, record.dateOfSubscription, record.bondtype, record.maturitydate, record.subcriptionamount, record.subsStatus]);

        fileName = "BLA Statement For BondHolders.xlsx";
      } else {
        alert("No table is currently displayed for download.");
        return;
      }

      const totalAmount = vm.downloadFilteredRecords.reduce((total, record) => total + parseFloat(record.subcriptionamount || 0), 0);

      vm.totalAmountFormat  =  indianNumberFormat(totalAmount);

      let totalRow = new Array(tableHeader.length).fill("");
      totalRow[totalAmountColumnIndex - 1] = "Grand Total (Rs)";
      totalRow[totalAmountColumnIndex] = vm.totalAmountFormat;

      tableData.push(totalRow);

      const wsData = [...additionalHeaders, tableHeader, ...tableData];
      const ws = XLSX.utils.aoa_to_sheet(wsData);
      const wb = XLSX.utils.book_new();
      XLSX.utils.book_append_sheet(wb, ws, "BLA Statement For Bondholders");
      XLSX.writeFile(wb, fileName);
    };

    vm.downloadCSV = function () {
      const additionalHeaders = ["BLA Statement For BondHolders Report", "Bank Name: INSPIRISY BANK LTD", "Sol ID: 101 / BRANCH OF THE BANK", "Name of the Bond Scheme: " + vm.schemeName];
      if (vm.fromDate) {
        additionalHeaders.push("From Date: " + $filter("date")(vm.fromDate, "dd/MM/yyyy"));
      }

      if (vm.toDate) {
        additionalHeaders.push("To Date: " + $filter("date")(vm.toDate, "dd/MM/yyyy"));
      }

      additionalHeaders.push("");
      let tableHeader = [];
      let columnWidths = [];
      let tableData = [];
      let fileName = "BLA_Statement_For_BondHolders.txt";

      if ((vm.schemeCode && vm.blaNo && !vm.panNum) || (vm.schemeCode && vm.blaNo && vm.panNum && !vm.fromDate && !vm.toDate)) {
        additionalHeaders.push("BLA No: " + vm.blaNo, "Holder Name: " + vm.bondHolderName, "Address: " + vm.ListOfDtos.p_single_bond_hldr_add_out, "");
        tableHeader = ["S.No", "Date of Subscription", "Subscription ID", "Bond Type", "Maturity Date", "Subscription Amount Rs.", "Subscription Status"];
        tableData = vm.downloadFilteredRecords.map((record, index) => [index + 1, record.dateOfSubscription, record.subscriptionId, record.bondtype, record.maturitydate, record.subcriptionamount, record.subsStatus]);
        tableData = [...tableData];
        fileName = "BLA_Report.txt";
        columnWidths = [10, 25, 25, 25, 25, 30, 25];
      } else if ((vm.schemeCode && vm.fromDate && vm.toDate && !vm.panNum && !vm.blaNo) || (vm.schemeCode && vm.blaNo && vm.panNum && vm.bondHolderName && vm.fromDate && vm.toDate)) {
        tableHeader = ["S.No", "Customer Name", "Address", "PAN No", "BLA No", "Date of Subscription", "Subscription ID", "Bond Type", "Maturity Date", "Subscription Amount Rs.", "Subscription Status"];
        tableData = vm.downloadFilteredRecords.map((record, index) => [
          index + 1,
          record.bondholdername,
          record.address,
          record.panNo,
          record.blaNumber,
          record.dateOfSubscription,
          record.subscriptionId,
          record.bondtype,
          record.maturitydate,
          record.subcriptionamount,
          record.subsStatus,
        ]);
        fileName = "BLA_Statement_For_BondHolders.txt";
        columnWidths = [10, 25, 70, 25, 25, 25, 25, 25, 25 ,25];
      } else if (vm.schemeCode && vm.panNum && !vm.blaNo) {
        additionalHeaders.push("PAN No: " + vm.panNum, "Holder Name: " + vm.bondHolderName, "Address: " + vm.ListOfDtos.p_single_bond_hldr_add_out, "");

        tableHeader = ["S.No", "BLA No", "Subscription ID", "Date of Subscription", "Bond Type", "Maturity Date", "Subscription Amount Rs.", "Subscription Status"];

        tableData = vm.downloadFilteredRecords.map((record, index) => [index + 1, record.blaNumber, record.subscriptionId, record.dateOfSubscription, record.bondtype, record.maturitydate, record.subcriptionamount, record.subsStatus]);
        tableData = [...tableData]; // Ensure table data does not include headers

        fileName = "BLA_Statement_For_BondHolders.txt";
        columnWidths = [10, 25, 30, 25, 25, 25, 30, 25];
      } else {
        alert("No table is currently displayed for download.");
        return;
      }

      function padString(str, width) {
        return str.toString().padEnd(width, " ");
      }

      const formattedHeader = tableHeader.map((header, index) => padString(header, columnWidths[index])).join("");

      const formattedTableData = tableData.map((row) => row.map((col, index) => padString(col || "", columnWidths[index])).join(""));

      const totalAmount = vm.downloadFilteredRecords.reduce((total, record) => total + parseFloat(record.subcriptionamount || 0), 0);
      const subscriptionAmountIndex = tableHeader.indexOf("Subscription Amount Rs.");

      formattedTableData.push(
        tableHeader
          .map((_, index) => {
            if (index === subscriptionAmountIndex - 1) {
              return padString("Grand Total (Rs)", columnWidths[index]);
            } else if (index === subscriptionAmountIndex) {
              return padString(totalAmount, columnWidths[index]);
            }
            return padString("", columnWidths[index]);
          })
          .join("")
      );
      //
      const textData = [additionalHeaders.join("\n"), formattedHeader, ...formattedTableData].join("\n");

      const textContent = "data:text/plain;charset=utf-8," + encodeURIComponent(textData);
      const link = document.createElement("a");
      link.setAttribute("href", textContent);
      link.setAttribute("download", fileName);
      document.body.appendChild(link);
      link.click();
      document.body.removeChild(link);
    };

    vm.getSchemeName = function () {
      vm.loader = true;
      var requestData = {
        schemeCode: "",
        flag: "S",
        detailsFlag :"D",
      };

      blaStatementBondHolderService.getDetails(requestData).then(
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
      var requestData = {
        schemeCode: vm.schemeCode,
        bondholdername: vm.bondHolderName,
        panNo: vm.panNum,
        flag: "P",
        detailsFlag :"D",
      };
      blaStatementBondHolderService.getDetails(requestData).then(
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
    };

    vm.getBLANumber = function () {
      vm.loader = true;
      var requestData = {
        schemeCode: vm.schemeCode,
        bondholdername: vm.bondHolderName,
        panNo: vm.panNum,
        blaNumber: vm.blaNo,
        flag: "C",
        detailsFlag :"D",
      };
      blaStatementBondHolderService.getDetails(requestData).then(
        function (response) {
          vm.loader = false;
          vm.blaAccNoList = response.data.result.list;
          angular.element("#blaModel").modal("toggle");
        },
        function (error) {
          vm.loader = false;
          handleErrorResponse(error);
        }
      );
    };

    function getDetails() {
      vm.loader = true;

      var requestData = {
        panNo: vm.panNum,
        blaNumber: vm.blaNo,
        schemeCode: vm.schemeCode,
        fromDate: $filter("date")(vm.fromDate, "dd-MMM-yyyy"),
        toDate: $filter("date")(vm.toDate, "dd-MMM-yyyy"),
        flag: "T",
        bondholdername: vm.bondHolderName,
        pageNo: vm.pageNo,
        pageSize: vm.pageSize,
        searchValue: vm.searchText,
        sortOrder: vm.sortOrder,
        sortField: vm.sortField,
        detailsFlag :"D"
      };

      blaStatementBondHolderService.getDetails(requestData).then(
        function (response) {
          vm.loader = false;
          vm.ListOfDtos = response.data.result.list;
          vm.filteredRecords = response.data.result.list;
          vm.totalAmount = vm.filteredRecords.reduce((total, record) => total + parseFloat(record.subcriptionamount || 0), 0);
          vm.total = response.data.total;
          vm.maxPages = Math.ceil(response.data.total / vm.pageSize);
          vm.paginationControls();
          vm.totalAmountInIndianFormat = vm.indianNumberFormat(vm.totalAmount);
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
                     vm.total= '0';
                      vm.showNext = false;
            vm.noDataFoundInSearch = true;
          } else if (error.data.message == "No Data Found" || error.data.message == "NDF") {
            vm.noDataFound = error.data.message;
          } else {
            handleErrorResponse(error);
            $state.go("BLA_statement_for_Bondholders_report.inquiry");
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
