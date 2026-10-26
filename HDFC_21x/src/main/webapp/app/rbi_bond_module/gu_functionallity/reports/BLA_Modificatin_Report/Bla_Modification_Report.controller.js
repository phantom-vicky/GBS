(function () {
  "use strict";

  angular.module("gbmUltraApp").controller("blaModificationController", blaModificationController);

  blaModificationController.$inject = ["$scope", "$filter", "$state", "$rootScope", "$stateParams", "$location", "$timeout", "blaModificationService", "commonService"];

  function blaModificationController($scope, $filter, $state, $rootScope, $stateParams, $location, $timeout, blaModificationService, commonService) {
    var vm = this;
    vm.getSchemeName = getSchemeName;
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

    function getSchemeName() {
      vm.loader = true;
      var requestData = {
        schemeCode: "",
      };

      blaModificationService.getDetails(requestData).then(
        function (response) {
          vm.loader = false;
          vm.schemeList = response.data.result.list;
          angular.element("#schemeModel").modal("toggle");
        },
        function (error) {
          vm.loader = false;
          handleErrorResponse(error);
        }
      );
    }

    vm.textableDatePicker = textableDatePicker;
    vm.autoslashing = autoslashing;

    function textableDatePicker(id, valueDate) {
      commonService.textableDatePicker(id, valueDate);
    }
    function autoslashing(id) {
      commonService.autoslashing(id);
    }

    function getDetails() {
      vm.loader = true;
      var requestData = {
        schemeCode: vm.schemeCode,
        flag: "D",
        fromDate: moment(vm.fromDate).format("DD-MMM-YYYY") || "",
        toDate: moment(vm.toDate).format("DD-MMM-YYYY") || "",
        pageNo: vm.pageNo,
        pageSize: vm.pageSize,
        searchValue: vm.searchText,
        sortOrder: vm.sortOrder,
        sortField: vm.sortField,
      };

      blaModificationService.getDetails(requestData).then(
        function (response) {
          vm.loader = false;
          vm.model = response.data.result.list;
          vm.total = response.data.total;
          /*blaModificationService.getReferenceData({ referenceId: "MODE_OF_HOLDING" }).then(function (modeOfHolding) {
            var modeOfHoldingDesc = {};
            modeOfHolding.data.result.forEach(function (item) {
              modeOfHoldingDesc[item.SUBCODE] = item.DESCRIPTION;
            });

            blaModificationService.getReferenceData({ referenceId: "MODE_OF_PAYMENT" }).then(function (modeOfPayment) {
              var modeOfPaymentDesc = {};
              modeOfPayment.data.result.forEach(function (item) {
                modeOfPaymentDesc[item.SUBCODE] = item.DESCRIPTION;
              });

              blaModificationService.getReferenceData({ referenceId: "HOLDER_TYPE" }).then(function (holderType) {
                var holderTypeDesc = {};
                holderType.data.result.forEach(function (item) {
                  holderTypeDesc[item.SUBCODE] = item.DESCRIPTION;
                });

                vm.filteredRecords = vm.model.map(function (record) {
                  return {
                    ...record,
                    modeOfHolding: modeOfHoldingDesc[record.modeOfHolding] || "",
                    oldModeOfHolding: modeOfHoldingDesc[record.oldModeOfHolding] || "",
                    modeOfPayment: modeOfPaymentDesc[record.modeOfPayment] || "",
                    oldModeOfPayment: modeOfPaymentDesc[record.oldModeOfPayment] || "",
                    applStatus: holderTypeDesc[record.applStatus] || "",
                    oldApplStatus: holderTypeDesc[record.oldApplStatus] || "",
                  };
                });

                vm.model = vm.model.map(function (record) {
                  return {
                    ...record,
                    modeOfHolding: modeOfHoldingDesc[record.modeOfHolding] || "",
                    oldModeOfHolding: modeOfHoldingDesc[record.oldModeOfHolding] || "",
                    modeOfPayment: modeOfPaymentDesc[record.modeOfPayment] || "",
                    oldModeOfPayment: modeOfPaymentDesc[record.oldModeOfPayment] || "",
                    applStatus: holderTypeDesc[record.applStatus] || "",
                    oldApplStatus: holderTypeDesc[record.oldApplStatus] || "",
                  };
                });


              });
            });
          });*/
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
            $state.go("bla_modification_report.inquiry");
          }
        }
      );
    }

    if ($state.current.name == "bla_modification_report.inquiry_details") {
      if ($stateParams.obj) {
        vm.loader = true;
        vm.schemeName = $stateParams.obj.schemeName;
        vm.schemeCode = $stateParams.obj.schemeCode;
        vm.fromDate = $stateParams.obj.fromDate;
        vm.toDate = $stateParams.obj.toDate;
            vm.getDetails();
      }
    }

    // Date validation
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

    // Downloading the report
    vm.reports = {
      BLAModificationReport: {
        filename: "BLA Modification Report",
        mainHeaders: ["BLA Modification Report", "Bank Name: INSPIRISY BANK LTD", "Sol ID: 101 / BRANCH OF THE BANK"],
        subHeaders: ["Name of the Bond Scheme: " + vm.schemeName, "From Date: " + moment(vm.fromDate).format("DD/MM/YYYY"), "To Date: " + moment(vm.toDate).format("DD/MM/YYYY")],
        tableHeaders: [
          { title: "Scheme ID", width: 10, type: "numeric" },
          { title: "Scheme Name", width: 50, type: "string" },
          { title: "Inward Ref No", width: 20, type: "string" },
          { title: "Inward Ref Date", width: 20, type: "string" },
          { title: "BLA No", width: 20, type: "string" },
          { title: "First Applicant", width: 20, type: "string" },
          { title: "Old Value", width: 20, type: "string" },
          { title: "Second Applicant", width: 20, type: "string" },
          { title: "Old Second Applicant", width: 20, type: "string" },
          { title: "Third Applicant", width: 20, type: "string" },
          { title: "Old Third Applicant", width: 20, type: "string" },
          { title: "Address", width: 70, type: "string" },
          { title: "Old Address", width: 70, type: "string" },
          { title: "Mode of Holding", width: 20, type: "string" },
          { title: "Old Mode of Holding", width: 20, type: "string" },
          { title: "DOB", width: 15, type: "string" },
          { title: "Old DOB", width: 15, type: "string" },
          { title: "Mode of Payment", width: 20, type: "string" },
          { title: "Old Mode of Payment", width: 20, type: "string" },
          { title: "Account Type", width: 20, type: "string" },
          { title: "Old Account Type", width: 20, type: "string" },
          { title: "Account No", width: 20, type: "string" },
          { title: "Old Account No", width: 20, type: "string" },
          { title: "MICR", width: 20, type: "string" },
          { title: "Old MICR", width: 20, type: "string" },
          { title: "Application Status", width: 20, type: "string" },
          { title: "Old Application Status", width: 20, type: "string" },
          { title: "PAN No", width: 20, type: "string" },
          { title: "Old PAN No", width: 20, type: "string" },
          { title: "Maker ID", width: 20, type: "string" },
          { title: "Maker Date", width: 20, type: "string" },
          { title: "Checker ID", width: 20, type: "string" },
          { title: "Checker Date", width: 20, type: "string" },
          { title: "Authorized Status", width: 20, type: "string" },
          { title: "HDFC Branch Code", width: 20, type: "string" },
          { title: "Old HDFC Branch Code", width: 20, type: "string" },
          { title: "Remarks", width: 20, type: "string" },
        ],
        get tableData() {
          return (vm.downloadFilteredRecords || []).map((row, index) => [
            row.schemeCode,
            row.schemeName,
            row.inwardRefNo || " ",
            row.inwardRefDate,
            row.blaNo,
            row.firstApplicant,
            row.oldValue,
            row.secondApplicant,
            row.oldSecondApplicant,
            row.thirdApplicant,
            row.oldThirdApplicant,
            row.address,
            row.oldAddress,
            row.modeOfHolding,
            row.oldModeOfHolding,
            row.dob,
            row.oldDob,
            row.modeOfPayment,
            row.oldModeOfPayment,
            row.accountType,
            row.oldAccountType,
            row.accountNo,
            row.oldAccountNo,
            row.micr,
            row.oldMicr,
            row.applStatus,
            row.oldApplStatus,
            row.panNo,
            row.oldPanNo,
            row.makerId,
            row.makerDate,
            row.checkerId,
            row.checkerDate,
            row.authorizedStatus,
            row.hdfcBranchCode,
            row.oldHdfcBranchCode,
            row.remarks,
          ]);
        },
      },
    };

    vm.downloadReport = function () {
      vm.loader = true;
      var requestData = {
        schemeCode: vm.schemeCode,
        flag: "D",
        fromDate: moment(vm.fromDate).format("DD-MMM-YYYY") || "",
        toDate: moment(vm.toDate).format("DD-MMM-YYYY") || "",
        pageNo: 1,
        pageSize: vm.total,
        searchValue: vm.searchText,
      };

        if(vm.downloadType == "excel") {
          blaModificationService.getDetailsDownload(requestData).then(
            function (response) {
              vm.loader = false;
               if (vm.downloadType === "excel") {
//                    vm.downloadExcel(currentReport);
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
            $state.go("bla_modification_report.inquiry");
          }
        }
      );
        } else if(vm.downloadType != "excel") {
          blaModificationService.getDetails(requestData).then(
            function (response) {
              vm.loader = false;
              vm.model = response.data.result.list;
              vm.total = response.data.total;
              blaModificationService.getReferenceData({ referenceId: "MODE_OF_HOLDING" }).then(function (modeOfHolding) {
                var modeOfHoldingDesc = {};
                modeOfHolding.data.result.forEach(function (item) {
                  modeOfHoldingDesc[item.SUBCODE] = item.DESCRIPTION;
                });

                blaModificationService.getReferenceData({ referenceId: "MODE_OF_PAYMENT" }).then(function (modeOfPayment) {
                  var modeOfPaymentDesc = {};
                  modeOfPayment.data.result.forEach(function (item) {
                    modeOfPaymentDesc[item.SUBCODE] = item.DESCRIPTION;
                  });

                  blaModificationService.getReferenceData({ referenceId: "HOLDER_TYPE" }).then(function (holderType) {
                    var holderTypeDesc = {};
                    holderType.data.result.forEach(function (item) {
                      holderTypeDesc[item.SUBCODE] = item.DESCRIPTION;
                    });

                    vm.downloadFilteredRecords = vm.model.map(function (record) {
                      return {
                        ...record,
                        modeOfHolding: modeOfHoldingDesc[record.modeOfHolding] || "",
                        oldModeOfHolding: modeOfHoldingDesc[record.oldModeOfHolding] || "",
                        modeOfPayment: modeOfPaymentDesc[record.modeOfPayment] || "",
                        oldModeOfPayment: modeOfPaymentDesc[record.oldModeOfPayment] || "",
                        applStatus: holderTypeDesc[record.applStatus] || "",
                        oldApplStatus: holderTypeDesc[record.oldApplStatus] || "",
                      };
                    });

                    const currentReport = vm.reports["BLAModificationReport"];
                    if (vm.downloadType === "csv") {
                      vm.downloadCSV(currentReport);
                    }
                    vm.downloadType = "";
                  });
                });
              });
        },
        function (error) {
          vm.loader = false;
          if (error.data.message == "No Data Found" || error.data.message == "NDF") {
            vm.noDataFound = error.data.message;
          } else {
            handleErrorResponse(error);
            $state.go("bla_modification_report.inquiry");
          }
        }
      );
        }
    };

vm.dateFormat = dateFormat;
    function dateFormat(date){

     vm.date= moment(date).format("DD/MM/YYYY")
     return vm.date;

    }

    vm.downloadCSV = function (currentReport) {
      const { filename, mainHeaders, subHeaders, tableHeaders, tableData } = currentReport;
      const formatRow = (values) => {
        return tableHeaders
          .map((col, i) => {
            const value = values[i] ? values[i].toString() : "";
            return value.padEnd(col.width, "  ");
          })
          .join("\t\t");
      };
      const formattedHeader = formatRow(tableHeaders.map((col) => col.title));
      const formattedData = tableData.map((row) => formatRow(row)).join("\n");
      const textData = [mainHeaders.join("\n"), subHeaders.join("\n"), formattedHeader, formattedData].join("\n");

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

    $scope.formatDate = function (dateString) {
        if (!dateString) return ''; // Handle null or undefined date
        // Parse the input date in the format DD-MMM-YYYY
        const months = {
            JAN: '01', FEB: '02', MAR: '03', APR: '04', MAY: '05', JUN: '06',
            JUL: '07', AUG: '08', SEP: '09', OCT: '10', NOV: '11', DEC: '12'
        };

        const parts = dateString.split('-'); // Split into [day, month, year]
        if (parts.length !== 3) return ''; // Invalid date format

        const day = parts[0].padStart(2, '0'); // Ensure day is two digits
        const month = months[parts[1].toUpperCase()]; // Convert month to MM format
        const year = parts[2];

        if (!month) return ''; // Invalid month

        // Return the formatted date
        return `${day}/${month}/${year}`;
    };


  }
})();
