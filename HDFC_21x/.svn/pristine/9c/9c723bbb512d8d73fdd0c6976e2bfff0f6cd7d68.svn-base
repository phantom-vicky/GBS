(function () {
  "use strict";

  angular.module("gbmUltraApp").controller("disablingDeactivatingController", disablingDeactivatingController);

  disablingDeactivatingController.$inject = ["$scope", "$filter", "$state", "$stateParams", "$location", "$rootScope", "$timeout", "disablingDeactivatingService", "commonService"];

  function disablingDeactivatingController($scope, $filter, $state, $stateParams, $location, $rootScope, $timeout, disablingDeactivatingService, commonService) {
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
    vm.searchValue = "";
    vm.getBrokerCode = getBrokerCode;
    vm.getDetailsPage = getDetailsPage;

    // Pagination starts
    vm.goPreviousPage = goPreviousPage;
    vm.goNextPage = goNextPage;
    vm.paginationControls = paginationControls;
    vm.showNext = false;
    vm.showPrevious = false;
    $scope.sortReverse = false;
    $scope.sort = function (key) {
      $scope.sortKey = key;
      $scope.sortReverse = $scope.sortKey == key ? !$scope.sortReverse : $scope.sortReverse;
      $scope.sortReverse ? (vm.sortOrder = "DESC") : (vm.sortOrder = "ASC");
      vm.sortField = key;
      vm.getDetailsPage();
    };
    vm.pageNo = 1;
    vm.pageData = null;
    vm.records = [];
    vm.filteredRecords = [];
    vm.pageSizeOptions = [5, 10, 25, 50, 100];
    vm.pageSize = vm.pageSizeOptions[0];
    vm.currentPage = 1;
    vm.maxPagesToShow = 5;
    vm.searchText = "";
    vm.loading = true;
    vm.showExportOptions = false;
    vm.itemsPerPage = "10";

    vm.maxValue = function () {
      return Math.min(vm.currentPage * vm.pageSize, vm.filteredRecords.length);
    };

    function goPreviousPage() {
      vm.pageNo = parseInt(vm.pageNo) - 1;
      vm.getDetailsPage();
    }

    function goNextPage() {
      vm.pageNo = parseInt(vm.pageNo) + 1;
      vm.getDetailsPage();
    }

    function paginationControls() {
      vm.pages = [];
      // vm.maxPages = parseInt(vm.pageDetails.maxPages);

      for (var i = 1; i <= parseInt(vm.pageDetails.maxPages); i++) {
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
    }

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

    //----Pagination ends

    //Search Text
    vm.updateFilteredRecords = function () {
      const searchText = vm.searchText ? vm.searchText.toLowerCase() : "";
      vm.filteredRecords = vm.records.filter((record) => {
        return Object.values(record).some((value) => value !== null && value !== undefined && String(value).toLowerCase().includes(searchText));
      });
      vm.totalPages = Math.ceil(vm.filteredRecords.length / vm.pageSize);
      vm.setPage(1);
    };

    if ($state.current.name == "disabling_deactivating_of_brokers.inquiry_detail") {
      if ($stateParams.obj) {
        //        vm.schemeCode = $stateParams.obj.schemeCode;
        //        vm.schemeName= $stateParams.obj.schemeName;
        //        vm.schemeStatus = $stateParams.obj.schemeStatus;
        vm.fromDate = $stateParams.obj.fromDate;
        vm.toDate = $stateParams.obj.toDate;
        vm.status = $stateParams.obj.status;
        vm.brokerAgencyCode = $stateParams.obj.brokerAgencyCode;
        getDetailsPage();
      }
    }

    //Get Scheme name
    function getModuleName() {
      vm.modalSearch = "";
      vm.loader = true;

      var requestData = {
        flag: "S",
      };
      disablingDeactivatingService.getDetails(requestData).then(
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

    function getDetailsPage() {
      vm.loader = true;
      let requestData = {};

      if (vm.status == "Disable") {
        requestData = {
          //          schemeCode: vm.schemeCode,
          brokerStatus: vm.status,
          brokerAgencyCode: vm.brokerAgencyCode,
          flag: "T1",
          fromDate: $filter("date")(vm.fromDate, "dd-MMM-yyyy"),
          toDate: $filter("date")(vm.toDate, "dd-MMM-yyyy"),
          //
          pageNo: vm.pageNo,
          pageSize: vm.pageSize,
          searchValue: vm.searchValue,
          sortOrder: vm.sortOrder,
          sortField: vm.sortField,
        };
      } else if (vm.status == "De-Activated") {
        requestData = {
          //          schemeCode: vm.schemeCode,
          brokerStatus: vm.status,
          brokerAgencyCode: vm.brokerAgencyCode,
          flag: "T",
          fromDate: $filter("date")(vm.fromDate, "dd-MMM-yyyy"),
          toDate: $filter("date")(vm.toDate, "dd-MMM-yyyy"),
          //
          pageNo: vm.pageNo,
          pageSize: vm.pageSize,
          searchValue: vm.searchValue,
          sortOrder: vm.sortOrder,
          sortField: vm.sortField,
        };
      } else if (vm.status == 'Active') {
              requestData = {
                        //          schemeCode: vm.schemeCode,
                        brokerStatus: vm.status,
                        brokerAgencyCode: vm.brokerAgencyCode,
                        flag: "T3",
                        fromDate: $filter("date")(vm.fromDate, "dd-MMM-yyyy"),
                        toDate: $filter("date")(vm.toDate, "dd-MMM-yyyy"),
                        pageNo: vm.pageNo,
                        pageSize: vm.pageSize,
                        searchValue: vm.searchValue,
                        sortOrder: vm.sortOrder,
                        sortField: vm.sortField,
              };
      } else if (vm.status == "ALL") {
        requestData = {
          //          schemeCode: vm.schemeCode,
          brokerStatus: vm.status,
          brokerAgencyCode: vm.brokerAgencyCode,
          flag: "T2",
          fromDate: $filter("date")(vm.fromDate, "dd-MMM-yyyy"),
          toDate: $filter("date")(vm.toDate, "dd-MMM-yyyy"),
          //
          pageNo: vm.pageNo,
          pageSize: vm.pageSize,
          searchValue: vm.searchValue,
          sortOrder: vm.sortOrder,
          sortField: vm.sortField,
        };
      }

      disablingDeactivatingService.getDetails(requestData).then(
        function (response) {
          vm.loader = false;
          vm.detailsList = response.data.result.list;
          vm.filteredRecords = response.data.result.list;
          vm.model = response.data.result.list;
          vm.totalPages = Math.ceil(vm.records.length / vm.pageSize);
          vm.pageDetails = response.data.result.list;
          //          vm.updatePagination();
          //          angular.element("#schemeModel").modal("toggle");

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
          if ((error.data.message == "No Data Found") || (error.data.message == "NDF") && vm.searchValue) {
            vm.pageNo = '0';
            vm.maxPages = '0';
            vm.total = '0';
            vm.noDataFound = error.data.message;
            vm.showNext = false;

          } else {
            handleErrorResponse(error);
            $state.go("disabling_deactivating_of_brokers.inquiry");
          }
        }
      );
    }

    //Get Pan Number
    function getBrokerCode() {
      var requestData = {
        flag: "BA",
      };
      disablingDeactivatingService.getDetails(requestData).then(
        function (response) {
          vm.loader = false;
          vm.brokerCodeList = response.data.result.list;

          disablingDeactivatingService.getReferenceData({ referenceId: "STATES OF INDIA" }).then(function (states) {
            var statesList = {};
            states.data.result.forEach(function (item) {
              statesList[item.SUBCODE] = item.DESCRIPTION;
            });

            vm.brokerAgencyCodeList = vm.brokerCodeList.map(function (record) {
              return {
                ...record,
                branchId: statesList[record.branchId] || "",
              };
            });
            angular.element("#brokerModel").modal("toggle");

          });

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

    //        Download reports

    vm.getDownload = getDownload;
    function getDownload() {
      vm.loader = true;
      let requestData = {};

      if (vm.status === "Disable") {
        requestData = {
          //          schemeCode: vm.schemeCode,
          brokerStatus: vm.status,
          brokerAgencyCode: vm.brokerAgencyCode,
          flag: "T1",
          fromDate: $filter("date")(vm.fromDate, "dd-MMM-yyyy"),
          toDate: $filter("date")(vm.toDate, "dd-MMM-yyyy"),
          pageNo: vm.pageNo,
          pageSize: vm.total,
          searchValue: vm.searchValue,
          sortOrder: vm.sortOrder,
          sortField: vm.sortField,
          downloadType: vm.downloadType,
        };
      } else if (vm.status === "De-Activated") {
        requestData = {
          //          schemeCode: vm.schemeCode,
          brokerStatus: vm.status,
          brokerAgencyCode: vm.brokerAgencyCode,
          flag: "T",
          fromDate: $filter("date")(vm.fromDate, "dd-MMM-yyyy"),
          toDate: $filter("date")(vm.toDate, "dd-MMM-yyyy"),
          pageNo: vm.pageNo,
          pageSize: vm.total,
          searchValue: vm.searchValue,
          sortOrder: vm.sortOrder,
          sortField: vm.sortField,
          downloadType: vm.downloadType,
        };
      } else if (vm.status === "ALL") {
        requestData = {
          //          schemeCode: vm.schemeCode,
          brokerStatus: vm.status,
          brokerAgencyCode: vm.brokerAgencyCode,
          flag: "T2",
          fromDate: $filter("date")(vm.fromDate, "dd-MMM-yyyy"),
          toDate: $filter("date")(vm.toDate, "dd-MMM-yyyy"),
          pageNo: vm.pageNo,
          pageSize: vm.total,
          searchValue: vm.searchValue,
          sortOrder: vm.sortOrder,
          sortField: vm.sortField,
          downloadType: vm.downloadType,
        };
      } else if (vm.status === "Active") {
        requestData = {
          brokerStatus: vm.status,
          brokerAgencyCode: vm.brokerAgencyCode,
          flag: "T3",
          fromDate: $filter("date")(vm.fromDate, "dd-MMM-yyyy"),
          toDate: $filter("date")(vm.toDate, "dd-MMM-yyyy"),
          pageNo: vm.pageNo,
          pageSize: vm.total,
          searchValue: vm.searchValue,
          sortOrder: vm.sortOrder,
          sortField: vm.sortField,
          downloadType: vm.downloadType,
        };
      }
      disablingDeactivatingService.getDetailsDownload(requestData).then(
        function (response) {
          vm.loader = false;
          vm.downloadfilteredRecords = response.data.result;
          if (vm.downloadType === "excel") {
            //            vm.downloadExcel();
            if (response.data.result.fileName) {
              vm.fileData = response.data.result.fileData;
              vm.fileName = response.data.result.fileName;
              saveAs(getBlobFromBase64String(vm.fileData), vm.fileName);
            }
          } else if (vm.downloadType === "csv") {
            vm.downloadCSV();
          }
          vm.downloadType = "";
        }, function (error) {
          vm.loader = false;
          if ((error.data.message == "No Data Found") || (error.data.message == "NDF")) {
            vm.noDataFound = error.data.message;
            vm.downloadType = "";
          } else {
            handleErrorResponse(error);
            vm.downloadType = "";
            $state.go("disabling_deactivating_of_brokers.inquiry");
          }
        });
    }

    vm.downloadReport = function () {
      if (vm.downloadType === "excel") {
        vm.downloadExcel();
      } else if (vm.downloadType === "csv") {
        vm.downloadCSV();
      }
      vm.downloadType = "";
    };

    vm.downloadExcel = function () {
      const additionalDetails = [
        ["Disabling /Deactivating of brokers after 2 years of non-sourcing"],
        ["Bank Name: INSPIRISY BANK LTD"],
        ["Sol ID: 101 / BRANCH OF THE BANK"],
        //        ...(vm.schemeName ? [["Name of the Bond Scheme: " + vm.schemeName]] : []),
        ...(vm.brokerAgencyCode ? [["Broker Agency Code: " + vm.brokerAgencyCode]] : []),
        ...(vm.status ? [["Status: " + vm.status]] : []),
        ["From Date : " + $filter("date")(vm.fromDate, "dd-MM-yyyy") + " To Date : " + $filter("date")(vm.toDate, "dd-MM-yyyy")],

        [],
      ];

      var headers = [
        [
          "S.No.",
          "Name of the module",
          "Broker Agency Code",
          "Broker sort code",
          "Broker Name",
          "Broker having HDFC account",
          "Operative Account Number",
          "HDFC branch code",
          "IFSC Code",
          "GST No",
          "PAN",
          "Payment mode",
          "Broker Group",
          "Status",
          "The Broker is Registered?",
          "Address",
          "Mobile No",
          "Email ID",
          "Remarks",
        ],
      ];
      var rows = vm.detailsList.map(function (row, index) {
        return [
          index + 1,
          row.moduleName,
          row.brokerAgencyCode,
          row.brokerSortCode,
          row.brokerName,
          row.hdfcAcc,
          row.operativeAccNo,
          row.hdfcBranchCode,
          row.ifscCode,
          row.gstNo,
          row.panNo,
          row.paymentMode,
          row.brokerGrp,
          row.statusOut,
          row.regBroker,
          row.address,
          row.mobileNo,
          row.emailId,
          row.remarks,
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
      XLSX.writeFile(wb, "disabling_deactivating_of_brokers.xlsx");
    };

    vm.downloadCSV = function () {
      const tableHeader = [
        { title: "S.No.", width: 10 },
        { title: "Name of the module", width: 20 },
        { title: "Broker Agency Code", width: 20 },
        { title: "Broker sort code", width: 20 },
        { title: "Broker Name", width: 30 },
        { title: "Broker having HDFC account", width: 30 },
        { title: "Operative Account Number", width: 30 },
        { title: "HDFC branch code", width: 40 },
        { title: "IFSC Code", width: 40 },
        { title: "GST No", width: 30 },
        { title: "PAN", width: 30 },
        { title: "Payment mode", width: 30 },
        { title: "Broker Group", width: 30 },
        { title: "Status", width: 30 },
        { title: "The Broker is Registered?", width: 30 },
        { title: "Address", width: 30 },
        { title: "Mobile No", width: 30 },
        { title: "Email ID", width: 30 },
        { title: "Remarks", width: 30 },
      ];

      const formattedHeader = tableHeader.map((col) => col.title.padEnd(col.width, " ")).join("");

      const tableData = vm.downloadfilteredRecords.list.map((record, index) =>
        tableHeader
          .map((col, colIndex) => {
            const value = [
              index + 1, // S.No.
              record.moduleName || "",
              record.brokerAgencyCode || "",
              record.brokerSortCode || "",
              record.brokerName || "",
              record.hdfcAcc || "",
              record.operativeAccNo || "",
              record.hdfcBranchCode || "",
              record.ifscCode || "",
              record.gstNo || "",
              record.panNo || "",
              record.paymentMode || "",
              record.brokerGrp || "",
              record.statusOut || "",
              record.regBroker || "",
              record.address || "",
              record.mobileNo || "",
              record.emailId || "",
              record.remarks || "",
            ][colIndex];
            return value.toString().padEnd(col.width, " ");
          })
          .join("")
      );

      const textData = [
        "Disabling /Deactivating of brokers after 2 years of non-sourcing",
        "Bank Name: INSPIRISY BANK LTD",
        "Sol ID: 101 / BRANCH OF THE BANK",
        //        ...(vm.schemeName ? [["Name of the Bond Scheme: " + vm.schemeName]] : []),
        ...(vm.brokerAgencyCode ? [["Broker Agency Code: " + vm.brokerAgencyCode]] : []),
        ...(vm.status ? [["Status: " + vm.status]] : []),
        ["From Date : " + $filter("date")(vm.fromDate, "dd-MM-yyyy") + " To Date : " + $filter("date")(vm.toDate, "dd-MM-yyyy")],
        formattedHeader,
        "-".repeat(formattedHeader.length),
        ...tableData,
        "".padEnd(formattedHeader.length, " "), // Empty row
      ].join("\n");

      const textContent = "data:text/plain;charset=utf-8," + encodeURIComponent(textData);
      const link = document.createElement("a");
      link.setAttribute("href", textContent);
      link.setAttribute("download", "disabling_deactivating_of_brokers.txt");
      document.body.appendChild(link);
      link.click();
      document.body.removeChild(link);
    };
  }
})();
