(function () {
  "use strict";

  angular.module("gbmUltraApp").controller("rbiBondFtiController", rbiBondFtiController);

  rbiBondFtiController.$inject = ["$scope", "$filter", "$state", "$stateParams", "$location", "$rootScope", "FTIService", "commonService"];

  function rbiBondFtiController($scope, $filter, $state, $stateParams, $location, $rootScope, FTIService, commonService) {
    var vm = this;
    vm.getDetails = getDetails;
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

    //    date validation
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

    vm.getSchemeName = function () {
      vm.loader = true;
      var requestData = {
        flag: "S",
        downloadType : "CSV",
      };

      FTIService.getDetails(requestData).then(
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

    vm.getSubscriptionID = function () {
      vm.loader = true;
      var requestData = {
        schemeCode: vm.schemeCode,
        subscriptionID: vm.subscriptionID,
        flag: "BR",
      };

      FTIService.getDetails(requestData).then(
        function (response) {
          vm.loader = false;
          vm.subscriptionIDList = response.data.result.list;
          angular.element("#subscriptionDetailsModal").modal("toggle");
        },
        function (error) {
          handleErrorResponse(error);
          vm.loader = false;
        }
      );
    };

    if ($state.current.name == "rbi_bond_fti_lc.inquiry_details") {
      if ($stateParams.obj) {
        vm.moduleName = $stateParams.obj.moduleName;
        vm.schemeCode = $stateParams.obj.schemeCode;
        vm.schemeName = $stateParams.obj.schemeName;
        vm.fromDate = $stateParams.obj.fromDate;
        vm.toDate = $stateParams.obj.toDate;
        vm.fromAmount = $stateParams.obj.fromAmount;
        vm.toAmount = $stateParams.obj.toAmount;
        vm.transaction = $stateParams.obj.transaction;
        vm.tranID = $stateParams.obj.tranID;
        vm.tranType = $stateParams.obj.tranType;
        vm.subscriptionID = $stateParams.obj.subscriptionID;
        vm.userID = $stateParams.obj.userID;
        vm.tranStatus = $stateParams.obj.tranStatus;
        vm.getDetails();
      }
    }

    vm.formatDate = function (tempDate) {
      return $filter("date")(tempDate, "dd/MM/yyyy");
    };

    //    Download report
//    vm.reports = {
//      FinancialTransactionInquiry: {
//        filename: "Financial Transaction Inquiry – DC",
//        mainHeaders: ["Financial Transaction Inquiry – DC", "Bank Name: INSPIRISY BANK LTD", "Sol ID: 101 / BRANCH OF THE BANK"],
//      subHeaders: ["Name of the Bond Scheme: " + vm.schemeName, "Tran Date From: " + vm.formatDate(vm.fromDate), "Tran Date To: " + vm.formatDate(vm.toDate), "Tran Type: " + vm.tranType],
////        tableHeaders: [
////          { title: "S. No.", width: 10, type: "numeric" },
////          { title: "Subscription ID", width: 20, type: "string" },
////          { title: "Bond Type", width: 20, type: "string" },
////          { title: "Tran Date", width: 20, type: "string" },
////          { title: "Tran Mode", width: 20, type: "string" },
////          { title: "GBM", width: 30, type: "string" },
////          { title: "", width: 30, type: "numeric" },
////          { title: "", width: 30, type: "string" },
////          { title: "", width: 30, type: "string" },
////          { title: "", width: 30, type: "string" },
////          { title: "", width: 30, type: "numeric" },
////          { title: "CBS", width: 30, type: "string" },
////          { title: "", width: 30, type: "string" },
////        ],
////        tableSubHeaders: [
//          { title: "", width: 10, type: "numeric" },
//          { title: "", width: 20, type: "string" },
//          { title: "", width: 20, type: "string" },
//          { title: "", width: 20, type: "string" },
//          { title: "", width: 20, type: "string" },
//          { title: "Tran ID", width: 30, type: "string" },
//          { title: "Account Number", width: 30, type: "string" },
//          { title: "Amount", width: 30, type: "string" },
//          { title: "Tran Status (N/R/Y)", width: 30, type: "string" },
//          { title: "Deleted (Y/N)", width: 30, type: "string" },
//          { title: "Tran Type (Debit or Credit)", width: 30, type: "string" },
//          { title: "Tran ID", width: 30, type: "string" },
//          { title: "Amount", width: 30, type: "string" },
//        ],
//        get tableData() {
//          return (vm.filteredRecordsDownload || []).map((record, index) => [
//            (index + 1).toString(),
//            record.subscriptionID || "",
//            record.bondType || "",
//            record.tranDate || "",
//            record.tranMode || "",
//            record.gbmTranID || "",
//            record.creditAccountNumber || record.debitAccountNumber || "",
//            record.gbmAmount || "",
//            record.tranStatus || "",
//            record.deleted == "Y" ? "YES" : "NO",
//            record.tranType,
//            record.cbsTranID || "",
//            record.cbsAmount || "",
//          ]);
//        },
//      },
//    };

    vm.downloadReport = function () {
    vm.loader = true;
          var requestData = {
            schemeCode: vm.schemeCode,
            subscriptionID: vm.subscriptionID,
            fromDate: moment(vm.fromDate).format("DD-MM-YYYY"),
            toDate: moment(vm.toDate).format("DD-MM-YYYY"),
            fromAmount: vm.fromAmount,
            toAmount: vm.toAmount,
            transaction: vm.transaction,
            tranType: vm.tranType,
            transactionId: vm.gbmTranIdFrom,
            userID: vm.userID,
            downloadType : vm.downloadType,
            flag: "D",
                    pageNo: "1",
                    pageSize: vm.total,
                    searchValue: vm.searchText,
          };

          FTIService.getDetails(requestData).then(
            function (response) {
              vm.loader = false;
              vm.filteredRecordsDownload = response.data.result;


          if (vm.downloadType === "excel") {


            vm.fileData = vm.filteredRecordsDownload.fileData;
               vm.fileName = vm.filteredRecordsDownload.fileName;
            saveAs(getBlobFromBase64String(vm.fileData), vm.fileName);
             Swal.fire("Success!", vm.fileName+"\n Downloaded Successfully", "success");
          }
            },
            function (error) {
              vm.loader = false;
              if (error.data.message == "No Data Found" || error.data.message == "NDF") {
                          vm.noDataFound = error.data.message;
                        } else {
                handleErrorResponse(error);
                $state.go("rbi_bond_fti_lc.inquiry");
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
        { s: { r: 7, c: 0 }, e: { r: 7, c: tableHeaders.length - 1 } },
        { s: { r: 8, c: 5 }, e: { r: 8, c: 10 } },
        { s: { r: 8, c: 11 }, e: { r: 8, c: 12 } },
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

    function getDetails() {
      vm.loader = true;
      var requestData = {
        schemeCode: vm.schemeCode,
        subscriptionID: vm.subscriptionID,
        fromDate: moment(vm.fromDate).format("DD-MM-YYYY"),
        toDate: moment(vm.toDate).format("DD-MM-YYYY"),
        fromAmount: vm.fromAmount,
        toAmount: vm.toAmount,
        transaction: vm.transaction,
        tranType: vm.tranType,
        transactionId: vm.gbmTranIdFrom,
        userID: vm.userID,
        downloadType : "CSV",
        flag: "D",

        pageNo: vm.pageNo,
                pageSize: vm.pageSize,
                searchValue: vm.searchText,
                sortOrder: vm.sortOrder,
                sortField: vm.sortField,
      };

      FTIService.getDetails(requestData).then(
        function (response) {
          vm.loader = false;
          vm.model = response.data.result.list;
          vm.filteredRecords = response.data.result.list;
vm.total = response.data.total;
          vm.maxPages = Math.ceil(response.data.total / vm.pageSize);
          vm.paginationControls();
          if (vm.model.length === 0) {
            vm.noDataFoundInSearch = true;
          } else {
            vm.noDataFoundInSearch = false;
          }
        },
        function (error) {
          vm.loader = false;
          if ((error.data.message == "No Data Found" || error.data.message == "NDF") && vm.searchText) {
                      vm.noDataFoundInSearch = true;
                    } else if (error.data.message == "No Data Found" || error.data.message == "NDF") {
                      vm.noDataFound = error.data.message;
                    } else {
            handleErrorResponse(error);
            $state.go("rbi_bond_fti_lc.inquiry");
          }
        }
      );
    }
  }
})();
