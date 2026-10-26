(function () {
  "use strict";

  angular.module("gbmUltraApp").controller("ReportTransactionDetailsController", ReportTransactionDetailsController);

  ReportTransactionDetailsController.$inject = ["$scope", "$filter", "$state", "$stateParams", "$location", "$rootScope", "ReportTransactionDetailsService", "commonService"];

  function ReportTransactionDetailsController($scope, $filter, $state, $stateParams, $location, $rootScope, ReportTransactionDetailsService, commonService) {
    var vm = this;
    vm.solDate = $rootScope.globals.currentUser.solDate;

     //Pagination
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
          vm.getReportOnTransactionDetails();
        };

        vm.pageNo = 1;
        vm.pageSize = 5;

        vm.goPreviousPage = function () {
          vm.pageNo = parseInt(vm.pageNo) - 1;
          vm.getReportOnTransactionDetails();
        };

        vm.goNextPage = function () {
          vm.pageNo = parseInt(vm.pageNo) + 1;
          vm.getReportOnTransactionDetails();
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
        flag:'SC'
      };

      ReportTransactionDetailsService.getReportOnTransactionDetails(requestData).then(
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
        schemeCode  : vm.schemeCode,
        holderName  : vm.bondHolderName,
        panNumber   : vm.panNum,
        flag        : "P",
      };
      ReportTransactionDetailsService.getReportOnTransactionDetails(requestData).then(
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
      var requestData = {
        schemeCode  : vm.schemeCode,
        holderName  : vm.bondHolderName,
        panNumber   : vm.panNum,
        blaNumber   : vm.blaNo,
        flag        : "B",
      };
      ReportTransactionDetailsService.getReportOnTransactionDetails(requestData).then(
        function (response) {
          vm.loader = false;
          vm.blaDataList = response.data.result.list;
          angular.element("#blaModel").modal("toggle");
        },
        function (error) {
          vm.loader = false;
          handleErrorResponse(error);
        }
      );
    };

    vm.getSubscriptionID = function () {
      vm.loader = true;

      var requestData = {
        schemeCode      : vm.schemeCode,
        holderName      : vm.bondHolderName,
        panNumber       : vm.panNum,
        blaNumber       : vm.blaNo,
        flag            : "S",
        subscriptionID  : vm.subscriptionID,
      };
      ReportTransactionDetailsService.getReportOnTransactionDetails(requestData).then(
        function (response) {
          vm.loader = false;
          vm.subscriptionIdList = response.data.result.list;
          angular.element("#subscriptionIDModal").modal("toggle");
        },
        function (error) {
          handleErrorResponse(error);
          vm.loader = false;
        }
      );
    };
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

    vm.getReportOnTransactionDetails = function () {
      vm.loader = true;
      var requestData = {
        schemeCode      : vm.schemeCode,
        holderName      : vm.bondHolderName,
        panNumber       : vm.panNo,
        blaNumber       : vm.blaNo,
        fromDate        : moment(vm.fromDate).format("DD-MMM-YYYY"),
        toDate          : moment(vm.toDate).format("DD-MMM-YYYY"),
        subscriptionID  : vm.subscriptionID,
        flag: "D",
        pageSize: vm.pageSize,
        pageNo: vm.pageNo,
        searchValue: vm.searchValue,
        sortOrder: vm.sortOrder,
        sortField: vm.sortField,
      };

      ReportTransactionDetailsService.getReportOnTransactionDetails(requestData).then(
        function (response) {
          vm.loader             = false;
          vm.model              = response.data.result.list;
          vm.filteredRecords    = response.data.result.list;
          vm.subscriptionTotalAmount = vm.filteredRecords.reduce((sum, record) => sum + (record.subscriptionAmount ? parseFloat(record.subscriptionAmount) : 0), 0);
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
          if (vm.searchValue && (error.data.message == "No Data Found" || error.data.message == "NDF")) {
             vm.noDataFoundInSearch = true;
             vm.total= error.data.total;
             vm.pageNo= 0;
             vm.maxPages = Math.ceil(error.data.total / vm.pageSize);
             vm.paginationControls();
             vm.showPrevious= false;
          } else if (error.data.message == "No Data Found" || error.data.message == "NDF") {
                     vm.noDataFound = error.data.message;
          }  else {
                    handleErrorResponse(error);
                    $state.go("Report_on_Transaction_Details_for_Bond.inquiry");
          }
        }
      );
    };


    if ($state.current.name == "Report_on_Transaction_Details_for_Bond.inquiry_details") {
      vm.schemeCode = $stateParams.obj.schemeCode;
      vm.fromDate = $stateParams.obj.fromDate;
      vm.toDate = $stateParams.obj.toDate;
      vm.subscriptionID = $stateParams.obj.subscriptionID;
      vm.schemeName = $stateParams.obj.schemeName;
      vm.panNo = $stateParams.obj.panNumber;
      vm.bondHolderName = $stateParams.obj.holderName;
      vm.blaNo = $stateParams.obj.blaNumber;
        vm.getReportOnTransactionDetails();
    }

    // Downloading the report
    vm.reports = {
      ReportOnTransactionDetailsForBond: {
        filename: "Report On Transaction Details For Bond",
        mainHeaders: ["Report On Transaction Details For Bond", "Bank Name: INSPIRISY BANK LTD", "Sol ID: 101 / BRANCH OF THE BANK"],
        subHeaders: ["Name of the Bond Scheme: " + vm.schemeName, "From Transaction Date: " + formatDate(vm.fromDate), "To Transaction Date " + formatDate(vm.toDate)],
        tableHeaders: [
          { title: "S. No.", width: 10, type: "numeric" },
          { title: "PAN No.", width: 20, type: "string" },
          { title: "Bond BLA No.", width: 20, type: "string" },
          { title: "Holder Name", width: 35, type: "string" },
          { title: "Subscription ID", width: 20, type: "string" },
          { title: "Broker Code", width: 20, type: "string" },
          { title: "Broker Name", width: 30, type: "string" },
          { title: "Broker Group", width: 30, type: "string" },
          { title: "Mode of Transaction", width: 20, type: "string" },
          { title: "Subscription Amount Rs.", width: 20, type: "numeric" },
          { title: "Transaction Date", width: 20, type: "string" },
        ],
        get tableData() {
          return (vm.filteredRecord || []).map((record, index) => [
            (index + 1).toString(),
            record.panNumber || "",
            record.blaNumber || "",
            record.holderName || "",
            record.subscriptionID || "",
            record.brokerCode || "",
            record.brokerName || "",
            record.brokerType || "",
            record.modeOfTransaction || "",
            record.subscriptionAmount || "",
            record.transactionDate || "",
          ]);
        },
      },
    };

    vm.downloadReport = function () {
      vm.loader = true;
            var requestData = {
              schemeCode      : vm.schemeCode,
              holderName      : vm.bondHolderName,
              panNumber       : vm.panNo,
              blaNumber       : vm.blaNo,
              fromDate        : moment(vm.fromDate).format("DD-MMM-YYYY"),
              toDate          : moment(vm.toDate).format("DD-MMM-YYYY"),
              subscriptionID  : vm.subscriptionID,
              flag: "D",
              pageNo: 1,
              pageSize: vm.total,
              searchValue: vm.searchValue,
            };

             if (vm.downloadType === "excel") {
              ReportTransactionDetailsService.getReportOnTransactionDetailsDownload(requestData).then(
                      function (response) {
                        vm.loader = false;
                        vm.filteredRecord = response.data.result.list;

                                    if (vm.downloadType === "excel") {
        //                                vm.downloadExcel(vm.reports.ReportOnTransactionDetailsForBond);
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
                          $state.go("Report_on_Transaction_Details_for_Bond.inquiry");
                        }
                      }
            );
             } else if (vm.downloadType === "csv") {
                ReportTransactionDetailsService.getReportOnTransactionDetails(requestData).then(
                      function (response) {
                        vm.loader = false;
                        vm.filteredRecord = response.data.result.list;
                        vm.subscriptionTotalAmountDownload = vm.filteredRecord.reduce((sum, record) => sum + (record.subscriptionAmount ? parseFloat(record.subscriptionAmount) : 0), 0);
                                   if (vm.downloadType === "csv") {
                                       vm.downloadCSV(vm.reports.ReportOnTransactionDetailsForBond);
                                   }
                                       vm.downloadType = "";

                      },
                      function (error) {
                        vm.loader = false;
                        if (error.data.message == "No Data Found" || error.data.message == "NDF") {
                          vm.noDataFound = error.data.message;
                        } else {
                          handleErrorResponse(error);
                          $state.go("Report_on_Transaction_Details_for_Bond.inquiry");
                        }
                      }
            );
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

      const totalRow = formatRow(
        tableHeaders.map((col, i) => {
          if (i === tableHeaders.length - 2) return "Total Amount Rs.";
          if (i === tableHeaders.length - 1) return vm.subscriptionTotalAmountDownload.toString();
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
      const totalRow = ["", "", "", "", "", "", "", "", "Total", vm.subscriptionTotalAmountDownload, ""];
      excelData.push(totalRow);
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

//    vm.downloadPDF = function (currentReport) {
//      const { filename, mainHeaders, subHeaders, tableHeaders, tableData } = currentReport;
//      const { jsPDF } = window.jspdf;
//      const doc = new jsPDF({ orientation: "landscape", unit: "mm", format: "a4" });
//
//      doc.setFont("Times", "bold");
//      if (Array.isArray(mainHeaders)) {
//        let yPosition = 10;
//
//        mainHeaders.forEach((header) => {
//          if (header) {
//            const pageWidth = doc.internal.pageSize.width;
//            doc.setFontSize(13);
//            doc.text(header, pageWidth / 2, yPosition, { align: "center" });
//            yPosition += 10;
//          } else {
//            console.error("Invalid header text:", header);
//          }
//        });
//      }
//      const formattedSubHeaders = subHeaders.map((header) => [header]);
//      tableData.push(["", "", "", "", "", "", "", "", "Total", Math.round(vm.subscriptionTotalAmountDownload)]);
//
//      doc.autoTable({
//        body: formattedSubHeaders,
//        startY: 40,
//        margin: { left: 20, right: 20 },
//        theme: "grid",
//        styles: { fontSize: 10, overflow: "linebreak", cellPadding: 2 },
//        tableWidth: "auto",
//        columnStyles: {
//          0: { fontStyle: "bold" },
//          1: {},
//          2: { fontStyle: "bold" },
//          3: {},
//        },
//      });
//
//      const afterFirstTableY = doc.lastAutoTable.finalY + 10;
//      doc.autoTable({
//        head: [tableHeaders],
//        body: tableData,
//        startY: afterFirstTableY,
//        theme: "grid",
//        margin: { left: 20, right: 20 },
//        styles: { fontSize: 10, overflow: "linebreak", cellPadding: 2 },
//        pageBreak: "auto",
//        tableWidth: "auto",
//        rowPageBreak: "avoid",
//        showHead: "firstPage",
//      });
//
//      doc.save(filename + ".pdf");
//    };

    vm.maxValue = function () {
      return Math.min(vm.currentPage * vm.pageSize, vm.filteredRecords.length);
    };

    function formatDate(tempDate) {
      return $filter("date")(tempDate, "dd/MM/yyyy");
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
