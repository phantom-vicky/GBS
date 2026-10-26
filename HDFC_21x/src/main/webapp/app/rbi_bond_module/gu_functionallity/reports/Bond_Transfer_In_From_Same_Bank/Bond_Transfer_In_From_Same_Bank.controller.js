(function () {
  "use strict";

  angular.module("gbmUltraApp").controller("bondTransferInController", bondTransferInController);

  bondTransferInController.$inject = ["$scope", "$filter", "$state", "$stateParams", "$location", "$rootScope", "$timeout", "bondTransferInService", "commonService"];

  function bondTransferInController($scope, $filter, $state, $stateParams, $location, $rootScope, $timeout, bondTransferInService, commonService) {
    var vm = this;

    vm.getDetails = getDetails;

    vm.getSchemeName = function () {
      vm.loader = true;

      var requestData = {
        flag: "S",
      };
      bondTransferInService.getDetails(requestData).then(
        function (response) {
          vm.loader = false;
          vm.schemeList = response.data.result.list;
          angular.element("#schemeModal").modal("toggle");
        },
        function (error) {
          handleErrorResponse(error);
          vm.loader = false;
        }
      );
    };

    vm.getAccTranSameNo = function () {
      vm.loader = true;

      var requestData = {
        flag: "T",
        schemeCode: vm.schemeCode,
      };
      bondTransferInService.getDetails(requestData).then(
        function (response) {
          vm.loader = false;
          vm.accTransList = response.data.result.list;
          angular.element("#branchIdModal").modal("toggle");
        },
        function (error) {
          handleErrorResponse(error);
          vm.loader = false;
        }
      );
    };

    vm.getPanNo = function () {
      vm.loader = true;

      var requestData = {
        flag: "P",
        schemeCode: vm.schemeCode,
        holderName: vm.holderName,
        panNo: vm.panNo,
      };

      bondTransferInService.getDetails(requestData).then(
        function (response) {
          vm.loader = false;
          vm.panNoList = response.data.result.list;

          angular.element("#panNoModal").modal("toggle");
        },
        function (error) {
          handleErrorResponse(error);
          vm.loader = false;
        }
      );
    };

    vm.getBlaAccNo = function () {
      vm.loader = true;

      var requestData = {
        flag: "B",
        schemeCode: vm.schemeCode,
        panNo: vm.panNo,
        holderName: vm.holderName,
        blaNo: vm.blaNo,
      };
      bondTransferInService.getDetails(requestData).then(
        function (response) {
          vm.loader = false;
          vm.blaAccNoList = response.data.result.list;
          angular.element("#blaAccNoModal").modal("toggle");
        },
        function (error) {
          handleErrorResponse(error);
          vm.loader = false;
        }
      );
    };

    vm.getSubscriptionId = function () {
      vm.loader = true;

      var requestData = {
        flag: "BR",
        schemeCode: vm.schemeCode,
        panNo: vm.panNo,
        holderName: vm.holderName,
        blaNo: vm.blaNo,
        subsId: vm.subsId,
      };
      bondTransferInService.getDetails(requestData).then(
        function (response) {
          vm.loader = false;
          vm.subscriptionIdList = response.data.result.list;

          angular.element("#subScriptionIdModal").modal("toggle");
        },
        function (error) {
          handleErrorResponse(error);
          vm.loader = false;
        }
      );
    };

    function getDetails() {
      vm.loader = true;
      var requestData = {
        flag: "D",
        schemeCode: vm.schemeCode,
        accTranFrom: vm.accTranFrom,
        panNo: vm.panNo,
        holderName: vm.holderName,
        blaNo: vm.blaNo,
        subsId: vm.subsId,
      };

      bondTransferInService.getDetails(requestData).then(
        function (response) {
          vm.loader = false;
          vm.detailsList = response.data.result.list;
          vm.filteredRecords = response.data.result.list;
          vm.interestList = response.data.result.interestList;
          vm.interestFilteredRecords = response.data.result.interestList;
          vm.records = response.data.result.list;
          vm.totalPages = Math.ceil(vm.records.length / vm.pageSize);
          vm.pageDetails = response.data.result.list;
          vm.updatePagination();
        },
        function (error) {
          vm.loader = false;
          if (error.data.message == "No Data Found") {
            vm.noDataFound = error.data.message;
          } else {
            handleErrorResponse(error);
            $state.go("bond_transfer-in_from_same_bank.inquiry");
          }
        }
      );
    }

    vm.totalInterestAmount = function () {
                return vm.interestFilteredRecords.reduce((sum, record) => sum + (record.interestAmt ? parseFloat(record.interestAmt) : 0), 0);
                };
                   vm.totalTdsAmount = function () {
                return vm.interestFilteredRecords.reduce((sum, record) => sum + (record.tdsAmt ? parseFloat(record.tdsAmt) : 0), 0);
                };
                                   vm.totalInterestPaidAmount = function () {
                return vm.interestFilteredRecords.reduce((sum, record) => sum + (record.interestPaidAmt ? parseFloat(record.interestPaidAmt) : 0), 0);
                };

    if ($state.current.name == "bond_transfer-in_from_same_bank.inquiry_detail") {
      vm.schemeCode = $stateParams.obj.schemeCode;
      vm.accTranFrom = $stateParams.obj.accTransBranchId;
      vm.blaNo = $stateParams.obj.blaNo;
      vm.subsId = $stateParams.obj.subsId;
      vm.branchName = $stateParams.obj.branchName;
      vm.schemeName = $stateParams.obj.schemeName;
      vm.panNo = $stateParams.obj.panNo;
      vm.holderName = $stateParams.obj.holderName;
      getDetails();
    }

    vm.currentPage = 1;
    vm.currentDate = new Date();
    vm.textableDatePicker = textableDatePicker;
    vm.autoslashing = autoslashing;

    function textableDatePicker(id, valueDate) {
      commonService.textableDatePicker(id, valueDate);
    }
    function autoslashing(id) {
      commonService.autoslashing(id);
    }

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
      vm.getDetails();
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

    vm.maxValue = function () {
      return Math.min(vm.currentPage * vm.pageSize, vm.filteredRecords.length);
    };

    function goPreviousPage() {
      vm.pageNo = parseInt(vm.pageNo) - 1;
      vm.getDetails();
    }

    function goNextPage() {
      vm.pageNo = parseInt(vm.pageNo) + 1;
      vm.getDetails();
    }

    function paginationControls() {
      vm.pages = [];
      vm.maxPages = parseInt(vm.pageDetails.maxPages);

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

    vm.updateInterestFilteredRecords = function () {
      const searchInterestText = vm.searchInterestText ? vm.searchInterestText.toLowerCase() : "";
      vm.interestFilteredRecords = vm.interestList.filter((record) => {
        return Object.values(record).some((value) => value !== null && value !== undefined && String(value).toLowerCase().includes(searchInterestText));
      });
    };

    vm.downloadReport = function () {
      if (vm.downloadType === "excel") {
        vm.downloadExcel();
      } else if (vm.downloadType === "csv") {
        vm.downloadTXT();
      }
      vm.downloadType = "";
    };

    vm.downloadExcel = function () {
      const originalPage = vm.currentPage;
      const originalPageSize = vm.pageSize;

      vm.pageSize = Math.max(vm.filteredRecords.length, vm.interestFilteredRecords.length);
      vm.currentPage = 1;

      setTimeout(function () {
        const wb = XLSX.utils.book_new();
        const sheetData = [];

        sheetData.push(["Bond Transfer-in of Bond Account within Same Bank - Inquiry"]);
        sheetData.push([]);

        sheetData.push(["Bank Name : INSPIRISY BANK LTD" || ""]);
        sheetData.push(["Sol ID: 101 / BRANCH OF THE BANK" || ""]);
        sheetData.push([]);

        const infoTable = document.getElementById("infoTable");
        if (infoTable) {
          const infoRows = infoTable.querySelectorAll("tr");
          infoRows.forEach((row) => {
            const cells = Array.from(row.querySelectorAll("th, td")).map((cell) => cell.innerText.trim());
            if (cells.length > 0) sheetData.push(cells);
          });
          sheetData.push([]);
        }

        const table1 = document.getElementById("myTable");
        if (table1) {
          const rows = table1.querySelectorAll("thead tr, tbody tr");
          rows.forEach((row) => {
            const cells = Array.from(row.querySelectorAll("th, td")).map((cell) => cell.innerText.trim());
            sheetData.push(cells);
          });
          sheetData.push([]);
        }

        if (vm.showDetails) {
          const table2 = document.getElementById("myTable2");
          if (table2) {
            sheetData.push(["Interest Paid Details"]);
            sheetData.push([]);
            const rows = table2.querySelectorAll("thead tr, tbody tr");
            rows.forEach((row) => {
              const cells = Array.from(row.querySelectorAll("th, td")).map((cell) => cell.innerText.trim());
              sheetData.push(cells);
            });
          }
        }

        const ws = XLSX.utils.aoa_to_sheet(sheetData);
        XLSX.utils.book_append_sheet(wb, ws, "Bond Transfer-in Report");
        XLSX.writeFile(wb, "Bond Transfer-in Report.xlsx");

        vm.pageSize = originalPageSize;
        vm.currentPage = originalPage;
        if (!$scope.$$phase) $scope.$apply();
      }, 0);
    };

    vm.downloadTXT = function () {
      const originalPage = vm.currentPage;
      const originalPageSize = vm.pageSize;

      vm.pageSize = Math.max(vm.filteredRecords.length, vm.interestFilteredRecords.length);
      vm.currentPage = 1;

      setTimeout(function () {
        let txt = "";

        txt += "Bond Transfer-in of Bond Account within Same Bank - Inquiry\n\n";

        txt += `Bank Name : INSPIRISY BANK LTD || ""}\n`;
        txt += `Sol ID: 101 / BRANCH OF THE BANK || ""}\n\n`;

        const infoTable = document.getElementById("infoTable");
        if (infoTable) {
          const rows = infoTable.querySelectorAll("tr");
          rows.forEach((row) => {
            const cols = Array.from(row.querySelectorAll("th, td"))
              .map((cell) => cell.innerText.trim())
              .filter((cell) => cell);
            if (cols.length === 2 && cols[1]) {

              txt += `${cols[0]}: ${cols[1]}\n`;
            }
          });
          txt += "\n";
        }

        function getFormattedTable(table) {
          let out = "";
          const rows = table.querySelectorAll("thead tr, tbody tr");
          const data = Array.from(rows).map((row) =>
            Array.from(row.children).flatMap((cell) => {
              const span = parseInt(cell.getAttribute("colspan") || "1", 10);
              const text = cell.innerText.trim();
              return span > 1 ? [text, ...Array(span - 1).fill("")] : [text];
            })
          );

          const colWidths = [];
          data.forEach((row) => {
            row.forEach((cell, i) => {
              colWidths[i] = Math.max(colWidths[i] || 0, cell.length);
            });
          });

          data.forEach((row) => {
            row.forEach((cell, i) => {
              out += cell.padEnd(colWidths[i] + 2, " ");
            });
            out += "\n";
          });

          return out;
        }

        const table1 = document.getElementById("myTable");
        if (table1) {
          txt += getFormattedTable(table1);
          txt += "\n";
        }

        if (vm.showDetails) {
          const table2 = document.getElementById("myTable2");
          if (table2) {
            txt += "Interest Paid Details\n\n";
            txt += getFormattedTable(table2);
          }
        }

        const blob = new Blob([txt], { type: "text/plain" });
        const link = document.createElement("a");
        link.download = "Bond Transfer-in Report.txt";
        link.href = window.URL.createObjectURL(blob);
        link.click();

        vm.pageSize = originalPageSize;
        vm.currentPage = originalPage;
        if (!$scope.$$phase) $scope.$apply();
      }, 0);
    };
  }
})();
