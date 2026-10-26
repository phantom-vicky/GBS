(function () {
  "use strict";

  angular.module("gbmUltraApp").controller("FreezeUnfreezeController", FreezeUnfreezeController);

  FreezeUnfreezeController.$inject = ["$scope", "$filter", "$state", "$stateParams", "$location", "$rootScope", "FreezeUnfreezeService", "commonService"];

  function FreezeUnfreezeController($scope, $filter, $state, $stateParams, $location, $rootScope, FreezeUnfreezeService, commonService) {
    var vm = this;
    vm.solDate = $rootScope.globals.currentUser.solDate;
    vm.getDetails = getDetails;

    function formatDate(tempDate) {
      return $filter("date")(tempDate, "dd/MM/yyyy");
    }

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
        detailsFlag :"D"
      };

      FreezeUnfreezeService.getDetails(requestData).then(
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
        holderName: vm.holderName,
        panNumber: vm.panNumber,
        flag: "P",
          detailsFlag :"D",
      };
      FreezeUnfreezeService.getDetails(requestData).then(
        function (response) {
          vm.loader = false;
          vm.panList = response.data.result.list;
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
        schemeCode: vm.schemeCode,
        holderName: vm.holderName,
        panNumber: vm.panNumber,
        blaNumber: vm.blaNumber,
        flag: "B",
          detailsFlag :"D",
      };
      FreezeUnfreezeService.getDetails(requestData).then(
        function (response) {
          vm.loader = false;
          vm.blaList = response.data.result.list;
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
        schemeCode: vm.schemeCode,
        holderName: vm.holderName,
        panNumber: vm.panNumber,
        blaNumber: vm.blaNumber,
        fromDate: moment(vm.fromDate).format("DD-MMM-YYYY"),
        toDate: moment(vm.toDate).format("DD-MMM-YYYY"),
        flag: "D",
        pageNo: vm.pageNo,
        pageSize: vm.pageSize,
        searchValue: vm.searchText,
        sortOrder: vm.sortOrder,
        sortField: vm.sortField,
          detailsFlag :"D",
      };

      FreezeUnfreezeService.getDetails(requestData)
        .then(function (response) {
          vm.model = response.data.result.list || [];
          vm.total = response.data.total || 0;

          return FreezeUnfreezeService.getReferenceData({ referenceId: "MODE_OF_HOLDING" });
        })
        .then(function (modeOfHolding) {
          var modeOfHoldingDesc = {};
          (modeOfHolding.data.result || []).forEach(function (item) {
            modeOfHoldingDesc[item.SUBCODE] = item.DESCRIPTION;
          });

          vm.model = vm.model.map(function (record) {
            return {
              ...record,
              modeOfHolding: modeOfHoldingDesc[record.modeOfHolding] || "",
            };
          });

          vm.filteredRecords = angular.copy(vm.model);
          vm.maxPages = Math.ceil(vm.total / vm.pageSize);
          vm.paginationControls();
          vm.noDataFoundInSearch = vm.model.length === 0;
        })
        .catch(function (error) {
          if ((error.data?.message === "No Data Found" || error.data?.message === "NDF") && vm.searchText) {



                      vm.pageNo ='0';
                       vm.maxPages ='0';
                       vm.total = '0';
                        vm.showNext = false;
            vm.noDataFoundInSearch = true;
          } else if (error.data?.message === "No Data Found" || error.data?.message === "NDF") {
            vm.noDataFound = error.data.message;
          } else {
            handleErrorResponse(error);
            $state.go("Freeze_Un_Freeze_Report.inquiry");
          }
        })
        .finally(function () {
          vm.loader = false;
        });
    }

    if ($state.current.name == "Freeze_Un_Freeze_Report.inquiry_details") {
      vm.schemeCode = $stateParams.obj.schemeCode;
      vm.fromDate = $stateParams.obj.fromDate;
      vm.toDate = $stateParams.obj.toDate;
      vm.subscriptionID = $stateParams.obj.subscriptionID;
      vm.schemeName = $stateParams.obj.schemeName;
      vm.panNumber = $stateParams.obj.panNumber;
      vm.holderName = $stateParams.obj.holderName;
      vm.blaNumber = $stateParams.obj.blaNumber;
      vm.getDetails();
    }

    // Downloading the report
    vm.reports = {
      FreezeUnFreezeReport: {
        filename: "Freeze / Un-Freeze Report",
        mainHeaders: ["Freeze / Un-Freeze Report", "Bank Name: INSPIRISY BANK LTD", "Sol ID: 101 / BRANCH OF THE BANK"],
        subHeaders: ["Name of the Bond Scheme: " + vm.schemeName, "From Date: " + formatDate(vm.fromDate), "To Date " + formatDate(vm.toDate)],
        tableHeaders: [
          { title: "S. No.", width: 20, type: "numeric" },
          { title: "Holder Name", width: 20, type: "string" },
          { title: "PAN No", width: 20, type: "string" },
          { title: "BLA No", width: 20, type: "string" },
          { title: "Freeze", width: 20, type: "string" },
          { title: "Date", width: 20, type: "string" },
          { title: "Un Freeze", width: 20, type: "string" },
          { title: "Date", width: 20, type: "string" },
          { title: "Mode of Holding", width: 20, type: "string" },
          { title: "Does the principal amount need to be frozen?", width: 20, type: "string" },
          { title: "Does the Interest Payment need to be frozen?", width: 20, type: "string" },
          { title: "Is transferring out from another bank allowed?", width: 20, type: "string" },
          { title: "Reason from Maker", width: 20, type: "string" },
          { title: "Reason from Checker", width: 20, type: "string" },
        ],
        get tableData() {
          return (vm.filteredRecordsDownload || []).map((record, index) => [
            (index + 1).toString(),
            record.holderName || "",
            record.panNumber || "",
            record.blaNumber || "",
            record.freeze || "",
            record.freezeDate || "",
            record.unFreeze || "",
            record.unFreezeDate || "",
            record.modeOfHolding || "",
            record.principalAmountFrozen || "",
            record.interestPaymentFrozen || "",
            record.isTransferringOutAllowed || "",
            record.reasonFromMaker || "",
            record.reasonFromChecker || "",
          ]);
        },
      },
    };

   vm.downloadReport = function () {
     vm.loader = true;
     vm.noDataFound = "";

     var requestData = {
       schemeCode: vm.schemeCode,
       holderName: vm.holderName,
       panNumber: vm.panNumber,
       blaNumber: vm.blaNumber,
       fromDate: vm.fromDate ? moment(vm.fromDate).format("DD-MMM-YYYY") : "",
       toDate: vm.toDate ? moment(vm.toDate).format("DD-MMM-YYYY") : "",
       flag: "D",
       pageNo: "1",
       pageSize: vm.total,
       searchValue: vm.searchText,
       downloadType: vm.downloadType,
       schemeName: vm.schemeName,
     };

     FreezeUnfreezeService.getDetails(requestData).then(
       function (response) {
         vm.loader = false;
         if (
           response.data &&
           response.data.result &&
           response.data.result.fileName &&
           response.data.result.fileData
         ) {
           var fileData = response.data.result.fileData;
           var fileName = response.data.result.fileName;

           saveAs(getBlobFromBase64String(fileData), fileName);

           Swal.fire(
             "Downloaded",
             response.data.message || "File downloaded successfully",
             "success"
           );

           vm.downloadType = "";
           return;
         }

         vm.model = response.data.result.list;
         vm.total = response.data.total;

         FreezeUnfreezeService.getReferenceData({ referenceId: "MODE_OF_HOLDING" })
           .then(function (modeOfHolding) {

             var modeOfHoldingDesc = {};
             modeOfHolding.data.result.forEach(function (item) {
               modeOfHoldingDesc[item.SUBCODE] = item.DESCRIPTION;
             });

             vm.model = vm.model.map(function (record) {
               return {
                 ...record,
                 modeOfHolding: modeOfHoldingDesc[record.modeOfHolding] || "",
               };
             });

             const currentReport = vm.reports["FreezeUnFreezeReport"];

             if (vm.downloadType === "csv") {
               vm.downloadCSV(currentReport);
             } else if (vm.downloadType === "pdf") {
               vm.downloadPDF(currentReport);
             }

             vm.downloadType = "";
           });
       },

       function (error) {
         vm.loader = false;

         if (
           error.data &&
           (error.data.message === "No Data Found" ||
            error.data.message === "NDF")
         ) {
           vm.noDataFound = error.data.message;
         } else {
           handleErrorResponse(error);


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
