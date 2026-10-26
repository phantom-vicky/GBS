(function () {
  "use strict";

  angular.module("gbmUltraApp").controller("AppendixIV8Controller", AppendixIV8Controller);
  AppendixIV8Controller.$inject = ["$scope", "$filter", "$state", "$stateParams", "$location", "$rootScope", "AppendixIV8Service", "commonService"];

  function AppendixIV8Controller($scope, $filter, $state, $stateParams, $location, $rootScope, AppendixIV8Service, commonService) {
    var vm = this;

        vm.textableDatePicker=textableDatePicker;
        vm.autoslashing=autoslashing;
        vm.textableDatePickerTo=textableDatePickerTo;

        function textableDatePicker(id,valueDate){
         commonService.textableDatePicker(id,valueDate);
        }
        function autoslashing(id){
         commonService.autoslashing(id);
        }


                                                                        function textableDatePickerTo(id, valueDate,valueDateFrom) {
                                                                                                            if (valueDate != null && valueDateFrom != null) {

                                                                                                                  let lastDayOfMonth = new Date(valueDateFrom.getFullYear(), valueDateFrom.getMonth() + 1, 0);

                                                                                                                  // Validation
                                                                                                                  if (valueDate < valueDateFrom) {

                                                                                                                      Swal.fire("warning","To Date should be greater than or equal to From Date", "warning");
                                                                                                                      vm.toDate="";
                                                                                                                      return;
                                                                                                                  }

                                                                                                                  if (valueDate > lastDayOfMonth) {

                                                                                                                      Swal.fire("warning","To Date should not exceed month end of From Date", "warning");
                                                                                                                       vm.toDate="";
                                                                                                                      return;
                                                                                                                  }


                                                                                                              }
                                                                                                               commonService.textableDatePicker(id, valueDate);
                                                                                                             }

    vm.getSchemeName = function () {
      vm.loader = true;
      var requestData = {
        flag: "S",
      };
      AppendixIV8Service.getDetails(requestData).then(
        function (response) {
          vm.loader = false;
          vm.schemeList = response.data.result.schemeList;
          angular.element("#schemeModel").modal("toggle");
        },
        function (error) {
          handleErrorResponse(error);
          vm.loader = false;
        }
      );
    };

    // Date Validation
    vm.fromDateVal = function () {
      vm.fromDateOptions = {
        maxDate: new Date(),
        showWeeks: false,
      };
    };
    vm.toDateVal = function (fromDate) {
     // Convert to Date object (if fromDate is string)
                var from = new Date(fromDate);

                // Last day of the same month as fromDate
                var lastDayOfMonth = new Date(
                    from.getFullYear(),
                    from.getMonth() + 1,
                    0
                );


           vm.toDateOptions = {
             minDate: from,
             maxDate: lastDayOfMonth,
             showWeeks: false,
           };
    };

    vm.getDetails = function () {
      vm.loader = true;

      var requestData = {
        schemeCode: vm.schemeCode,
        fromDate: $filter("date")(vm.fromDate, "dd-MMM-yyyy"),
        toDate: $filter("date")(vm.toDate, "dd-MMM-yyyy"),
        flag: "D",
      };

      AppendixIV8Service.getDetails(requestData).then(
        function (response) {
          vm.loader = false;
          vm.partANonCumulativeList = response.data.result.partANonCumulativeList;
          vm.partACumulativeList = response.data.result.partACumulativeList;
          vm.partBNonCumulativeList = response.data.result.partBNonCumulativeList;
          vm.partBCumulativeList = response.data.result.partBCumulativeList;
          vm.partCNonCumulativeList = response.data.result.partCNonCumulativeList;
          vm.partCCumulativeList = response.data.result.partCCumulativeList;
          vm.partDNonCumulativeList = response.data.result.partDNonCumulativeList;
          vm.partDCumulativeList = response.data.result.partDCumulativeList;


                    vm.noDataFound =
                      vm.partACumulativeList.length === 0 &&
                      vm.partANonCumulativeList.length === 0 &&
                      vm.partBCumulativeList.length === 0 &&
                      vm.partBNonCumulativeList.length === 0 &&
                      vm.partCCumulativeList.length === 0 &&
                      vm.partCNonCumulativeList.length === 0 &&
                      vm.partDCumulativeList.length === 0 &&
                      vm.partDNonCumulativeList.length === 0;

          vm.partANonCumulativeTotalAmount= vm.partANonCumulativeList.reduce((sum, record) => sum + (record.aNcAmount ? parseFloat(record.aNcAmount) : 0), 0);
          vm.partACumulativeTotalAmount= vm.partACumulativeList.reduce((sum, record) => sum + (record.aCAmount ? parseFloat(record.aCAmount) : 0), 0);
          vm.partBNonCumulativeTotalAmount= vm.partBNonCumulativeList.reduce((sum, record) => sum + (record.bNcAmount ? parseFloat(record.bNcAmount) : 0), 0);
          vm.partBCumulativeTotalAmount= vm.partBCumulativeList.reduce((sum, record) => sum + (record.bCAmount ? parseFloat(record.bCAmount) : 0), 0);
          vm.partCNonCumulativeTotalAmount= vm.partCNonCumulativeList.reduce((sum, record) => sum + (record.cNcAmount ? parseFloat(record.cNcAmount) : 0), 0);
          vm.partCCumulativeTotalAmount= vm.partCCumulativeList.reduce((sum, record) => sum + (record.cCAmount ? parseFloat(record.cCAmount) : 0), 0);
          vm.partDNonCumulativeTotalAmount= vm.partDNonCumulativeList.reduce((sum, record) => sum + (record.dNcAmount ? parseFloat(record.dNcAmount) : 0), 0);
          vm.partDCumulativeTotalAmount= vm.partDCumulativeList.reduce((sum, record) => sum + (record.dCAmount ? parseFloat(record.dCAmount) : 0), 0);

          vm.nonCumulativeNetTotal=(vm.partANonCumulativeTotalAmount + vm.partBNonCumulativeTotalAmount)-
          (vm.partCNonCumulativeTotalAmount+vm.partDNonCumulativeTotalAmount);
          vm.cumulativeNetTotal= (vm.partACumulativeTotalAmount+vm.partBCumulativeTotalAmount)-
          (vm.partCCumulativeTotalAmount+vm.partDCumulativeTotalAmount);

          vm.mergedPartAList = [];

          var maxLength = Math.max(vm.partANonCumulativeList.length, vm.partACumulativeList.length);
          for (var i = 0; i < maxLength; i++) {
            vm.mergedPartAList.push({
              nonCumulative: vm.partANonCumulativeList[i] || {},
              cumulative: vm.partACumulativeList[i] || {}
            });
          }

                    vm.mergedPartBList = [];

                    var maxLength = Math.max(vm.partBNonCumulativeList.length, vm.partBCumulativeList.length);
                    for (var i = 0; i < maxLength; i++) {
                      vm.mergedPartBList.push({
                        nonCumulative: vm.partBNonCumulativeList[i] || {},
                        cumulative: vm.partBCumulativeList[i] || {}
                      });
                    }
                                        vm.mergedPartCList = [];

                    var maxLength = Math.max(vm.partCNonCumulativeList.length, vm.partCCumulativeList.length);
                    for (var i = 0; i < maxLength; i++) {
                      vm.mergedPartCList.push({
                        nonCumulative: vm.partCNonCumulativeList[i] || {},
                        cumulative: vm.partCCumulativeList[i] || {}
                      });
                    }
                                                   vm.mergedPartDList = [];

                    var maxLength = Math.max(vm.partDNonCumulativeList.length, vm.partDCumulativeList.length);
                    for (var i = 0; i < maxLength; i++) {
                      vm.mergedPartDList.push({
                        nonCumulative: vm.partDNonCumulativeList[i] || {},
                        cumulative: vm.partDCumulativeList[i] || {}
                      });
                    }
//          vm.filteredRecords = response.data.result.list;
//          vm.totalPages = Math.ceil(vm.model.length / vm.pageSize);
//          vm.pageDetails = response.data.result.list;
//          vm.updatePagination();
        },
        function (error) {
          vm.loader = false;
          if (error.data.message == "No Data Found" || error.data.message == "NDF") {
            vm.noDataFound = error.data.message;
          } else {
            handleErrorResponse(error);
            $state.go("Appendix_IV_8_Mar_2024.inquiry_details");
          }
        }
      );
    };



//
//    // Pagination integration
//    vm.showNext = false;
//    vm.showPrevious = false;
//    $scope.sortReverse = false;
//    $scope.sort = function (key) {
//      $scope.sortKey = key;
//      $scope.sortReverse = $scope.sortKey == key ? !$scope.sortReverse : $scope.sortReverse;
//      $scope.sortReverse ? (vm.sortOrder = "DESC") : (vm.sortOrder = "ASC");
//      vm.sortField = key;
//      vm.getDetails();
//    };
//    vm.pageNo = 1;
//    vm.filteredRecords = [];
//    vm.pageSizeOptions = [5, 10, 25, 50, 100];
//    vm.pageSize = vm.pageSizeOptions[0];
//    vm.currentPage = 1;
//    vm.maxPagesToShow = 5;
//    vm.searchText = "";
//    vm.loading = true;
//    vm.showExportOptions = false;
//
//    vm.goPreviousPage = function () {
//      vm.pageNo = parseInt(vm.pageNo) - 1;
//      vm.getDetails();
//    };
//
//    vm.goNextPage = function () {
//      vm.pageNo = parseInt(vm.pageNo) + 1;
//      vm.getDetails();
//    };
//
//    vm.paginationControls = function () {
//      vm.pages = [];
//      vm.maxPages = parseInt(vm.pageDetails.maxPages);
//      for (var i = 1; i <= parseInt(vm.pageDetails.maxPages); i++) {
//        vm.pages.push(parseInt(i));
//      }
//      if (vm.pageNo != 1) {
//        vm.showPrevious = true;
//      } else {
//        vm.showPrevious = false;
//      }
//      if (vm.pageNo != vm.maxPages) {
//        vm.showNext = true;
//      } else {
//        vm.showNext = false;
//      }
//    };
//
//    vm.setPage = function (page) {
//      if (page < 1) {
//        vm.currentPage = 1;
//      } else if (page > vm.totalPages) {
//        vm.currentPage = vm.totalPages;
//      } else {
//        vm.currentPage = page;
//      }
//      vm.updatePagination();
//    };
//
//    vm.updatePagination = function () {
//      try {
//        var startPage, endPage;
//        if (vm.totalPages <= vm.maxPagesToShow) {
//          startPage = 1;
//          endPage = vm.totalPages;
//        } else {
//          var maxPagesBeforeCurrentPage = Math.floor(vm.maxPagesToShow / 2);
//          var maxPagesAfterCurrentPage = Math.ceil(vm.maxPagesToShow / 2) - 1;
//
//          if (vm.currentPage <= maxPagesBeforeCurrentPage) {
//            startPage = 1;
//            endPage = vm.maxPagesToShow;
//          } else if (vm.currentPage + maxPagesAfterCurrentPage >= vm.totalPages) {
//            startPage = vm.totalPages - vm.maxPagesToShow + 1;
//            endPage = vm.totalPages;
//          } else {
//            startPage = vm.currentPage - maxPagesBeforeCurrentPage;
//            endPage = vm.currentPage + maxPagesAfterCurrentPage;
//          }
//        }
//
//        vm.pages = [];
//        for (var i = startPage; i <= endPage; i++) {
//          vm.pages.push(i);
//        }
//      } catch (error) {
//        console.error("An error occurred in updatePagination:", error);
//      }
//    };
//
//    vm.updatePageSize = function () {
//      vm.totalPages = Math.ceil(vm.filteredRecords.length / vm.pageSize);
//      vm.setPage(1);
//    };
//
//    vm.prevPage = function () {
//      vm.setPage(vm.currentPage - 1);
//    };
//
//    vm.nextPage = function () {
//      vm.setPage(vm.currentPage + 1);
//    };
//
//    vm.firstPage = function () {
//      vm.setPage(1);
//    };
//
//    vm.lastPage = function () {
//      vm.setPage(vm.totalPages);
//    };
//
//    vm.updateFilteredRecords = function () {
//      const searchText = vm.searchText ? vm.searchText.toLowerCase() : "";
//      vm.filteredRecords = vm.model.filter((record) => {
//        return Object.values(record).some((value) => value !== null && value !== undefined && String(value).toLowerCase().includes(searchText));
//      });
//      vm.totalPages = Math.ceil(vm.filteredRecords.length / vm.pageSize);
//      vm.setPage(1);
//    };

    if ($state.current.name == "Appendix_IV_8_Mar_2024.inquiry_details") {
      vm.schemeCode = $stateParams.obj.schemeCode;
      vm.fromDate = $stateParams.obj.fromDate;
      vm.toDate = $stateParams.obj.toDate;
      vm.schemeName = $stateParams.obj.schemeName;
      vm.getDetails();
    }

    vm.totalAmount = function () {
      return vm.filteredRecords.reduce((sum, record) => sum + (record.amount ? parseFloat(record.amount) : 0), 0);
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

          setTimeout(function () {
            const wb = XLSX.utils.book_new();
            const sheetData = [];

            const table1 = document.getElementById("myTable");
            if (table1) {
              const rows = table1.querySelectorAll("thead tr, tbody tr");
              rows.forEach((row) => {
                const cells = Array.from(row.querySelectorAll("th, td")).map((cell) => cell.innerText.trim());
                sheetData.push(cells);
              });
              sheetData.push([]);
            }
            const ws = XLSX.utils.aoa_to_sheet(sheetData);
            XLSX.utils.book_append_sheet(wb, ws, "Appendix IV 8% Mar 2024");
            XLSX.writeFile(wb, "Appendix IV 8% Mar 2024.xlsx");
            if (!$scope.$$phase) $scope.$apply();
          }, 0);
        };

            vm.downloadTXT = function () {
              setTimeout(function () {
                var table = document.getElementById("myTable1");
                if (!table) return;

                var rows = table.querySelectorAll("tr");
                var data = Array.from(rows).map((row) => {
                  return Array.from(row.children).flatMap((cell) => {
                    var span = parseInt(cell.getAttribute("colspan") || "1", 10);
                    var text = cell.innerText.trim();

                    if (span > 1) {
                      return [text, ...Array(span - 1).fill("")];
                    } else {
                      return [text];
                    }
                  });
                });

                var colWidths = [];
                data.forEach((row) => {
                  row.forEach((cell, i) => {
                    colWidths[i] = Math.max(colWidths[i] || 0, cell.length);
                  });
                });

                var txt = "";
                data.forEach((row) => {
                  row.forEach((cell, i) => {
                    txt += cell.padEnd(colWidths[i] + 2, " ");
                  });
                  txt += "\n";
                });

                var blob = new Blob([txt], { type: "text/plain" });
                var link = document.createElement("a");
                link.download = "Appendix_IV_8%_Mar_2024.txt";
                link.href = window.URL.createObjectURL(blob);
                link.click();
                if (!$scope.$$phase) {
                  $scope.$apply();
                }
              }, 0);
            };
  }
})();
