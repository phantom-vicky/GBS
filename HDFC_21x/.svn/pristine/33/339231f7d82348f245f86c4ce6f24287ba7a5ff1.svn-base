(function () {
  "use strict";

  angular.module("gbmUltraApp").controller("AppendixIV65Controller", AppendixIV65Controller);
  AppendixIV65Controller.$inject = ["$scope", "$filter", "$state", "$stateParams", "$location", "$rootScope", "AppendixIV65Service", "commonService"];

  function AppendixIV65Controller($scope, $filter, $state, $stateParams, $location, $rootScope, AppendixIV65Service, commonService) {
    var vm = this;

    vm.getSchemeName = function () {
      vm.loader = true;
      var requestData = {
        schemeCode: "",
        flag: "S",
      };
      AppendixIV65Service.getDetails(requestData).then(
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
        flag: "T",
      };

      AppendixIV65Service.getDetails(requestData).then(
        function (response) {
          vm.loader = false;
          vm.model = response.data.result;
          vm.filteredRecords = response.data.result;







                   vm.partAallnonCumulativeList = response.data.result.partAallnonCumulativeList;

                   vm.partANSum =  vm.partAallnonCumulativeList[0].sum;
                   vm.partANCount =  vm.partAallnonCumulativeList[0].count;

                  vm.partAallCumulativeList = response.data.result.partAallCumulativeList;

                      vm.partACSum =  vm.partAallCumulativeList[0].cumSum;
                      vm.partACCount =  vm.partAallCumulativeList[0].cumCount;


                     vm.partBallNonCumulativeList = response.data.result.partBallNonCumulativeList;


                      vm.partBNSum =  vm.partBallNonCumulativeList[0].sum;
                       vm.partBNCount =  vm.partBallNonCumulativeList[0].count;


                      vm.partBallCumulativeList = response.data.result.partBallCumulativeList;

                       vm.partBCSum =  vm.partBallCumulativeList[0].cumSum;
                       vm.partBCCount =  vm.partBallCumulativeList[0].cumCount;


                      vm.partCallNonCumulativeList = response.data.result.partCallNonCumulativeList;

                        vm.partCNSum =  vm.partCallNonCumulativeList[0].sum;
                        vm.partCNCount =  vm.partCallNonCumulativeList[0].count;


                        vm.partCallCumulativeList = response.data.result.partCallCumulativeList;


                         vm.partCCSum =  vm.partCallCumulativeList[0].cumSum;
                          vm.partCCCount =  vm.partCallCumulativeList[0].cumCount;


                         vm.partDallNonCumulativeList = response.data.result.partDallNonCumulativeList;


                          vm.partDNSum =  vm.partDallNonCumulativeList[0].sum;
                           vm.partDNCount =  vm.partDallNonCumulativeList[0].count;


                           vm.partDallCumulativeList = response.data.result.partDallCumulativeList;


                            vm.partDCSum =  vm.partDallCumulativeList[0].cumSum;
                            vm.partDCCount =  vm.partDallCumulativeList[0].cumCount;





          vm.totalPages = Math.ceil(vm.model.length / vm.pageSize);
          vm.pageDetails = response.data.result;
          vm.updatePagination();
        },
        function (error) {
          vm.loader = false;
          if (error.data.message == "No Data Found" || error.data.message == "NDF") {
            vm.noDataFound = error.data.message;
          } else {
            handleErrorResponse(error);
            $state.go("Appendix_IV_65_Mar_2024.inquiry");
          }
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
    vm.pageSizeOptions = [5, 10, 25, 50, 100];
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

    if ($state.current.name == "Appendix_IV_65_Mar_2024.inquiry_details") {
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
                XLSX.utils.book_append_sheet(wb, ws, "AAppendix_IV_6.5%_Mar_2024");
                XLSX.writeFile(wb, "Appendix_IV_6.5%_Mar_2024.xlsx");

    //            vm.pageSize = originalPageSize;
    //            vm.currentPage = originalPage;
                if (!$scope.$$phase) $scope.$apply();
              }, 0);
            };




            vm.textableDatePicker = textableDatePicker;
            vm.autoslashing = autoslashing;
            vm.textableDatePickerTo = textableDatePickerTo;
                                             function textableDatePicker(id, valueDate) {
                                               commonService.textableDatePicker(id, valueDate);
                                             }
                                             function autoslashing(id) {
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

//    vm.downloadExcel = function () {
//
//      var tempTable = document.createElement("table");
//      tempTable.innerHTML = document.getElementById("myTable").innerHTML;
//
//
//      var rows = "";
//      vm.filteredRecords.forEach((x, i) => {
//        rows += `<tr>
//      <td>${i + 1}</td>
//      <td>${x.blaNumber || ""}</td>
//      <td>${x.amount || ""}</td>
//      <td>${x.casAdviseNumber || ""}/${x.casDate || ""}</td>
//      <td>${x.dueDate || ""}</td>
//      <td>${x.sch || ""}</td>
//      <td>${x.cumulativeBlaNumber || ""}</td>
//      <td>${x.cumulativeAmount || ""}</td>
//      <td>${x.cumulativeCasAdviseNumber || ""}</td>
//      <td>${x.cumulativeCasDat || ""}</td>
//      <td>${x.cumulativeDueDate || ""}</td>
//    </tr>`;
//      });
//
//
//      var tbodies = tempTable.querySelectorAll("tbody");
//      if (tbodies.length > 0) {
//        tbodies[0].innerHTML = rows + tbodies[0].innerHTML.split("</tr>")[vm.filteredRecords.length] || "";
//      }
//
//
//      tempTable.style.display = "none";
//      document.body.appendChild(tempTable);
//
//
//      var workbook = XLSX.utils.table_to_book(tempTable, { sheet: "DATA" });
//      XLSX.writeFile(workbook, "Appendix_IV_6.5%_Mar_2024.xlsx");
//
//
//      document.body.removeChild(tempTable);
//    };

    vm.downloadTXT = function () {
      var originalPage = vm.currentPage;
      var originalPageSize = vm.pageSize;

      vm.pageSize = vm.filteredRecords.length;
      vm.currentPage = 1;

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
        link.download = "Appendix_IV_6.5%_Mar_2024.txt";
        link.href = window.URL.createObjectURL(blob);
        link.click();

        vm.pageSize = originalPageSize;
        vm.currentPage = originalPage;

        if (!$scope.$$phase) {
          $scope.$apply();
        }
      }, 0);
    };


    vm.maxValue = function () {
      return Math.min(vm.currentPage * vm.pageSize, vm.filteredRecords.length);
    };
  }
})();
