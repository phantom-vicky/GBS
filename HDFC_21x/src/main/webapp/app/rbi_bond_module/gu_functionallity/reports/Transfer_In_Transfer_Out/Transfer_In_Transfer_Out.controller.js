(function () {
  "use strict";

  angular.module("gbmUltraApp").controller("TransferInOutController", TransferInOutController);

  TransferInOutController.$inject = ["$scope", "$filter", "$state", "$stateParams", "$location", "$rootScope", "TransferInOutService", "commonService"];

  function TransferInOutController($scope, $filter, $state, $stateParams, $location, $rootScope, TransferInOutService, commonService) {
    var vm = this;

    vm.textableDatePicker = textableDatePicker;
    vm.autoslashing = autoslashing;
     vm.downloadReport  =  downloadReport;
     vm.transferTypee ='';

    function textableDatePicker(id, valueDate) {
      commonService.textableDatePicker(id, valueDate);
    }
    function autoslashing(id) {
      commonService.autoslashing(id);
    }


    function formatDate(tempDate) {
      return $filter("date")(tempDate, "dd/MM/yyyy");
    }

    vm.getDetails = getDetails;
    function getDetails() {
      vm.loader = true;
      let requestData = {};
      if (vm.transferType === "TRANSFER IN") {
      vm.transferTypee = "N";
        requestData = {
          schemeCode: vm.schemeCode,
          fromDate: moment(vm.fromDate).format("DD-MMM-YYYY"),
          toDate: moment(vm.toDate).format("DD-MMM-YYYY"),
          transferType:  vm.transferTypee,
          flag: "D",
          //
         pageNo : vm.pageNo,
         pageSize:vm.pageSize,
         searchValue:vm.searchValue,
         sortOrder:vm.sortOrder,
         sortField:vm.sortField,
        };
      } else if (vm.transferType === "TRANSFER OUT") {
      vm.transferTypee = "Y";
        requestData = {
          schemeCode: vm.schemeCode,
          fromDate: moment(vm.fromDate).format("DD-MMM-YYYY"),
          toDate: moment(vm.toDate).format("DD-MMM-YYYY"),
          transferType: vm.transferTypee,
          flag: "D",
          //
          pageNo: vm.pageNo,
          pageSize: vm.pageSize,
          searchValue: vm.searchValue,
          sortOrder: vm.sortOrder,
          sortField: vm.sortField,
        };
      }

      TransferInOutService.getDetails(requestData).then(
        function (response) {
          vm.loader = false;
          vm.model = response.data.result.list;

           vm.totalAmountt = vm.model.reduce((sum, record) => sum + (record.subscriptionAmount ? parseFloat(record.subscriptionAmount) : 0), 0);
          vm.totalAmount = response.data.result.total;
          vm.totalPages = Math.ceil(vm.model.length / vm.pageSize);

          vm.pageDetails = response.data.result;
//                vm.updatePagination();
                vm.paginationControls();

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
          if ((error.data.message == "No Data Found" || error.data.message == "NDF") && vm.searchValue) {



                  vm.pageNo ='0';
                   vm.maxPages ='0';
                   vm.total ='0';
                    vm.showNext = false;
            vm.noDataFoundInSearch = true;
          } else if (error.data.message == "No Data Found" || error.data.message == "NDF") {
            vm.noDataFound = error.data.message;
          } else {
            handleErrorResponse(error);
            $state.go("Transfer_In_Transfer_Out.inquiry");
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

    vm.getSchemeName = function () {
      vm.loader = true;
      var requestData = {
        flag: "S",
      };

      TransferInOutService.getDetails(requestData).then(
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

    // Pagination integration
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
    vm.filteredRecords = [];
    vm.pageSizeOptions = [10, 25, 50, 100];
    vm.pageSize = vm.pageSizeOptions[0];
    vm.currentPage = 1;
    vm.maxPagesToShow = 5;
    vm.searchText = "";
    vm.loading = true;
    vm.showExportOptions = false;

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
    //  vm.maxPages = parseInt(vm.pageDetails.maxPages);

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

    vm.setPage = function (page) {
      if (page < 1) {
        vm.currentPage = 1;
      } else if (page > vm.totalPages) {
        vm.currentPage = vm.totalPages;
      } else {
        vm.currentPage = page;
      }
      vm.updatePagination();
    };

    vm.updatePagination = function () {
      try {
        var startPage, endPage;
        if (vm.totalPages <= vm.maxPagesToShow) {
          startPage = 1;
          endPage = vm.totalPages;
        } else {
          var maxPagesBeforeCurrentPage = Math.floor(vm.maxPagesToShow / 2);
          var maxPagesAfterCurrentPage = Math.ceil(vm.maxPagesToShow / 2) - 1;

          if (vm.currentPage <= maxPagesBeforeCurrentPage) {
            startPage = 1;
            endPage = vm.maxPagesToShow;
          } else if (vm.currentPage + maxPagesAfterCurrentPage >= vm.totalPages) {
            startPage = vm.totalPages - vm.maxPagesToShow + 1;
            endPage = vm.totalPages;
          } else {
            startPage = vm.currentPage - maxPagesBeforeCurrentPage;
            endPage = vm.currentPage + maxPagesAfterCurrentPage;
          }
        }

        vm.pages = [];
        for (var i = startPage; i <= endPage; i++) {
          vm.pages.push(i);
        }
      } catch (error) {
        console.error("An error occurred in updatePagination:", error);
      }
    };

    vm.updatePageSize = function () {
      vm.totalPages = Math.ceil(vm.filteredRecords.length / vm.pageSize);
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
      vm.filteredRecords = vm.model.filter((record) => {
        return Object.values(record).some((value) => value !== null && value !== undefined && String(value).toLowerCase().includes(searchText));
      });
      vm.totalPages = Math.ceil(vm.filteredRecords.length / vm.pageSize);
      vm.setPage(1);
    };

    vm.maxValue = function () {
      return Math.min(vm.currentPage * vm.pageSize, vm.filteredRecords.length);
    };

    if ($state.current.name == "Transfer_In_Transfer_Out.inquiry_details") {
      vm.schemeCode = $stateParams.obj.schemeCode;
      vm.schemeName = $stateParams.obj.schemeName;
      vm.fromDate = $stateParams.obj.fromDate;
      vm.toDate = $stateParams.obj.toDate;
      vm.transferType = $stateParams.obj.transferType;
      vm.getDetails();
    }

    // Downloading the report
    vm.reports = {
      TransferInOut: {
        filename: "Transfer In/Transfer Out Details",
        mainHeaders: ["Transfer In/Transfer Out Details", "Bank Name: INSPIRISY BANK LTD", "Sol ID: 101 / BRANCH OF THE BANK"],
        subHeaders: ["Name of the Bond Scheme: " + vm.schemeName, "Transfer Type: " + vm.transferType, "From Date: " + formatDate(vm.fromDate), "To Date: " + formatDate(vm.toDate)],
        tableHeaders: [
          { title: "S. No.", width: 8, type: "numeric" },
          { title: "BLA No.", width: 20, type: "string" },
          { title: "Holder Name", width: 25, type: "string" },
          { title: "Subscription ID", width: 20, type: "string" },
          { title: "Subscription Amount Rs.", width: 25, type: "numeric" },
          { title: `Transfer ${vm.transferType === "TRANSFER OUT" ? "Out" : "IN"} to Bank`, width: 25, type: "string" },
          { title: `Transfer ${vm.transferType === "TRANSFER OUT" ? "Out" : "IN"} to Bank Branch`, width: 25, type: "string" },
          { title: `${vm.transferType === "TRANSFER OUT" ? "Transferred Out Date" : "Transferred IN Date"}`, width: 20, type: "string" },
        ],

       get tableData() {
         return (vm.downloadfilteredRecords.list || []).map((x, index) => [

          (index + 1).toString(),
           x.blaNumber || "",
           x.holderName || "",
           x.subscriptionID || "",
           x.subscriptionAmount?.toString() || "",
           vm.transferType === "TRANSFER OUT" ? (x.transferOutBankName || "") : (x.transferInBankName || ""),
           vm.transferType === "TRANSFER OUT" ? (x.transferOutBranchName || "") : (x.transferInBranchName || ""),
           x.transferDate || "",
         ]);
       },
 },
    };







    vm.getDownload = getDownload;
    function getDownload() {
      vm.loader = true;
      var requestData = {
        schemeCode: vm.schemeCode,
        fromDate: moment(vm.fromDate).format("DD-MMM-YYYY"),
        toDate: moment(vm.toDate).format("DD-MMM-YYYY"),
        transferType: vm.transferTypee,
        flag: "D",
        //
        pageNo: vm.pageNo,
        pageSize: vm.pageSize,
        searchValue: vm.searchValue,
        sortOrder: vm.sortOrder,
        sortField: vm.sortField,
        downloadType : vm.downloadType,
      };

      TransferInOutService.getDetailsDownload(requestData).then(
        function (response) {
          vm.loader = false;
          vm.downloadfilteredRecords = response.data.result;
          const currentReport = vm.reports["TransferInOut"];
          if (vm.downloadType === "excel") {
//            vm.downloadExcel(currentReport);
            if (response.data.result.fileName) {
                 vm.fileData = response.data.result.fileData;
                 vm.fileName = response.data.result.fileName;
                 saveAs(getBlobFromBase64String(vm.fileData), vm.fileName)
            }
          } else if (vm.downloadType === "csv") {
            vm.downloadCSV(currentReport);
          } else if (vm.downloadType === "pdf") {
            vm.downloadPDF(currentReport);
          }
          vm.downloadType = "";
        }, function (error) {
          vm.loader = false;
          if ((error.data.message == "No Data Found") || (error.data.message == "NDF")) {
            vm.noDataFound = error.data.message;
          } else {
            handleErrorResponse(error);
            $state.go("callback_report.inquiry");
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

    function downloadReport() {
      const currentReport = vm.reports;
      if (vm.downloadType === "excel") {
        vm.downloadExcel(currentReport);
      } else if (vm.downloadType === "csv") {
        vm.downloadCSV(currentReport);
      } else if (vm.downloadType === "pdf") {
        vm.downloadPDF(currentReport);
      }
      vm.downloadType = "";
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
          .join(" | "); // Use " | " as a delimiter
      };
      const formattedHeader = formatRow(tableHeaders.map((col) => col.title));
      const formattedData = tableData.map((row) => formatRow(row));
      const textData = [mainHeaders.join("\n"), subHeaders.join("\n"), formattedHeader, "-".repeat(formattedHeader.length), ...formattedData].join("\n");
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

      const ws = XLSX.utils.aoa_to_sheet(excelData);
      const mergeRanges = [
        { s: { r: 0, c: 0 }, e: { r: 0, c: 10 } },
        { s: { r: 1, c: 0 }, e: { r: 1, c: 10 } },
        { s: { r: 2, c: 0 }, e: { r: 2, c: 10 } },
        { s: { r: 3, c: 0 }, e: { r: 3, c: 10 } },
        { s: { r: 4, c: 0 }, e: { r: 4, c: 10 } },
        { s: { r: 5, c: 0 }, e: { r: 5, c: 10 } },
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
  }
})();
