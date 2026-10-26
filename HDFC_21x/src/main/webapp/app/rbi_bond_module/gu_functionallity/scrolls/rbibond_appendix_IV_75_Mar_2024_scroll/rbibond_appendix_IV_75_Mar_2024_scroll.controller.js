(function () {
    "use strict";

    angular.module("gbmUltraApp").controller("AppendixIV75March2024Controller", AppendixIV75March2024Controller);
    AppendixIV75March2024Controller.$inject= ["$scope","$filter","$state","$stateParams","$location","$rootScope","AppendixIV75MarchService","commonService"];

    function AppendixIV75March2024Controller($scope, $filter, $state, $stateParams, $location, $rootScope, AppendixIV75MarchService, commonService) {
    var vm= this;

        vm.currentPage = 1;
        vm.itemsPerPage = '5';
        vm.loginSolId = $rootScope.globals.currentUser.solId;
        vm.loginSolDesc = $rootScope.globals.currentUser.solDesc;
        vm.bankName = $rootScope.globals.currentUser.bankName;
        vm.solDate = $rootScope.globals.currentUser.systemDate;
        vm.currentDate = new Date();
        vm.currentDate = $filter('date')(vm.currentDate, 'dd/MM/yyyy');
        vm.getAppendixDetails= getAppendixDetails;

// Pagination integration - starts
    vm.showNext = false;
    vm.showPrevious = false;
    $scope.sortReverse = false;
    $scope.sort = function (key) {
      $scope.sortKey = key;
      $scope.sortReverse = $scope.sortKey == key ? !$scope.sortReverse : $scope.sortReverse;
      $scope.sortReverse ? (vm.sortOrder = "DESC") : (vm.sortOrder = "ASC");
      vm.sortField = key;
      vm.getAppendixDetails();
    };
    vm.pageNo = 1;
    vm.filteredRecords = [];
    vm.pageSizeOptions = [5,10, 25, 50, 100];
    vm.pageSize = vm.pageSizeOptions[0];
    vm.currentPage = 1;
    vm.maxPagesToShow = 5;
    vm.searchText = "";
    vm.loading = true;
    vm.showExportOptions = false;

    vm.goPreviousPage = function () {
      vm.pageNo = parseInt(vm.pageNo) - 1;
      vm.getAppendixDetails();
    };

    vm.goNextPage = function () {
      vm.pageNo = parseInt(vm.pageNo) + 1;
      vm.getAppendixDetails();
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

    vm.maxValue = function () {
      return Math.min(vm.currentPage * vm.pageSize, vm.filteredRecords.length);
    };
// Pagination integration - ends

    if($state.current.name== "appendix_iv_75_mar_2024.inquiry_detail") {
        vm.schemeCode = $stateParams.obj.schemeCode;
//        vm.schemeName = $stateParams.obj.schemeName;
        vm.fromDate = $filter('date')($stateParams.obj.fromDate, "dd-MMM-yyyy");
        vm.toDate = $filter('date')($stateParams.obj.toDate, "dd-MMM-yyyy");
            getAppendixDetails();
    }
// Scheme Torch
     vm.getSchemeName = function () {
          vm.loader = true;
          var requestData = {
            schemeCode: "",
            flag: "S",
          };
          AppendixIV75MarchService.getAppendixDetails(requestData).then(
            function (response) {
              vm.loader = false;
              vm.schemeList = response.data.result.schemeList;
              console.log('vm.schemeList',vm.schemeList);
              angular.element("#schemeModel").modal("toggle");
            },
            function (error) {
              handleErrorResponse(error);
              vm.loader = false;
            }
          );
        };

//Input Submit Button
    function getAppendixDetails() {
        vm.loader= false;
        var requestData= {
            schemeCode: vm.schemeCode,
            fromDate  : vm.fromDate,
            toDate    : vm.toDate,
            flag      : "T",
//        pageNo : vm.pageNo,
//        pageSize:vm.pageSize,
//        searchValue:vm.searchText,
//        sortOrder:'',
//        sortField:''
        };

        AppendixIV75MarchService.getAppendixDetails(requestData).then(
           function (response) {
             vm.loader = false;
             vm.filteredRecords = response.data.result;
             vm.records = response.data.result;
             vm.partANonCumulativeList= response.data.result.partANonCumulativeList;
             vm.partACumulativeList= response.data.result.partACumulativeList;

             vm.mergedPartAList = [];
             var maxLength = Math.max(vm.partANonCumulativeList.length, vm.partACumulativeList.length);
             for (var i = 0; i < maxLength; i++) {
               vm.mergedPartAList.push({
               nonCumulative: vm.partANonCumulativeList[i] || {},
               cumulative: vm.partACumulativeList[i] || {}
               });
             }
             console.log('vm.dataList',vm.mergedPartAList);
           },
           function (error) {
             handleErrorResponse(error);
             vm.loader = false;
             $state.go('appendix_iv_75_mar_2024.inquiry')
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
           maxDate: new Date(),
           showWeeks: false
       };

          vm.textableDatePicker = textableDatePicker;
                  vm.autoslashing = autoslashing;
                  vm.textableDatePickerTo = textableDatePickerTo;


                   function textableDatePickerTo(id, valueDate,valueDateFrom) {
                                                              if (valueDate != null && valueDateFrom != null) {

                                                                    let lastDayOfMonth = new Date(valueDateFrom.getFullYear(), valueDateFrom.getMonth() + 1, 0);

                                                                    // Validation
                                                                    if (valueDate < valueDateFrom) {

                                                                        Swal.fire("warning","To Date should be greater than or equal to From Date", "warning");
                                                                        vm.toDate="";
                                                                        return;
                                                                    }

//                                                                    if (valueDate > lastDayOfMonth) {
//
//                                                                        Swal.fire("warning","To Date should not exceed month end of From Date", "warning");
//                                                                         vm.toDate="";
//                                                                        return;
//                                                                    }


                                                                }
                                                                 commonService.textableDatePicker(id, valueDate);
                                                               }

                                                    function textableDatePicker(id, valueDate) {
                                                      commonService.textableDatePicker(id, valueDate);
                                                    }
                                                    function autoslashing(id) {
                                                      commonService.autoslashing(id);
                                                    }
   };

    }
})();