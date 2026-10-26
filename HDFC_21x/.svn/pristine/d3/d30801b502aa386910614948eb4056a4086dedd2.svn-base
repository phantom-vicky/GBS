(function() {
    "use strict";

    angular.module("gbmUltraApp").controller("EfilingController", EfilingController);
    EfilingController.$inject= ["$scope", "$filter", "$state", "$stateParams", "$location", "$rootScope", "EfilingService", "commonService"];

    function EfilingController($scope, $filter, $state, $stateParams, $location, $rootScope, EfilingService, commonService) {
    var vm= this;
vm.getDownload=getDownload;

        vm.currentPage = 1;
        vm.itemsPerPage = '5';
        vm.loginSolId = $rootScope.globals.currentUser.solId;
        vm.loginSolDesc = $rootScope.globals.currentUser.solDesc;
        vm.bankName = $rootScope.globals.currentUser.bankName;
        vm.solDate = $rootScope.globals.currentUser.systemDate;
        vm.currentDate = new Date();
        vm.currentDate = $filter('date')(vm.currentDate, 'dd/MM/yyyy');

vm.getEfilingDetails= getEfilingDetails;
// Pagination integration - starts
    vm.showNext = false;
    vm.showPrevious = false;
    $scope.sortReverse = false;
    $scope.sort = function (key) {
      $scope.sortKey = key;
      $scope.sortReverse = $scope.sortKey == key ? !$scope.sortReverse : $scope.sortReverse;
      $scope.sortReverse ? (vm.sortOrder = "DESC") : (vm.sortOrder = "ASC");
      vm.sortField = key;
      vm.getEfilingDetails();
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


    vm.pageNo = 1;
    vm.pageSize = 5;

    vm.goPreviousPage = function () {
      vm.pageNo = parseInt(vm.pageNo) - 1;
      vm.getEfilingDetails();
    };

    vm.goNextPage = function () {
      vm.pageNo = parseInt(vm.pageNo) + 1;
      vm.getEfilingDetails();
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

     function configDataTable() {
                        vm.getTitleDescription = {
                            title: 'E-Filling',
                            solId: $rootScope.globals.currentUser.solId,
                            solDesc: $rootScope.globals.currentUser.solDesc
                        }
                        vm.dataTableOption = configDataTableOptions(vm.getTitleDescription);
                    }

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

    if($state.current.name== 'e_filling.inquiry_detail') {
//        vm.schemeCode = $stateParams.obj.schemeCode;
//        vm.schemeName = $stateParams.obj.schemeName;
        vm.fromDate = $stateParams.obj.fromDate;
        vm.toDate = $stateParams.obj.toDate;
         vm.getEfilingDetails();
    }

    vm.getSchemeName= function() {
        vm.loader= true;
        var requestData= {
            flag: 'S',
        };
        EfilingService.getEfillingDetails(requestData).then(
            function(response) {
                vm.loader= false;
                vm.schemeList= response.data.result.list;
                angular.element("#schemeModel").modal("toggle");
            },
            function(error) {
                handleErrorResponse(error);
                vm.loader= false;
            });
    };
  vm.pageSize = 5;
            vm.pageNo = 1;
    function getEfilingDetails() {
        vm.loader= true;
        var requestData= {
//            schemeCode  : vm.schemeCode,
            fromDate    : moment(vm.fromDate).format("DD-MMM-YYYY") || "",
            toDate      :  moment(vm.toDate).format("DD-MMM-YYYY") || "",
            flag        : 'D',
             pageNo : vm.pageNo,
                    pageSize:vm.pageSize,
                    search:vm.searchValue,
        };

        EfilingService.getEfillingDetails(requestData).then(
            function(response) {
            vm.loader= false;
                            vm.noDataFoundInSearch = false;
                  vm.noDataFound = null;


            vm.records = response.data.result.list;
                      vm.filteredRecords = response.data.result.list;
                      vm.totalPages = response.data.total;
                      vm.maxPages = Math.ceil(response.data.total / vm.pageSize);
                      vm.paginationControls();


            configDataTable();
            },
            function(error) {
                handleErrorResponse(error);
                vm.loader= false;

                if(vm.searchValue!= null && error.data.message == 'No Data Found'){
                vm.noDataFoundInSearch = true;
                }else if (error.data.message == 'No Data Found'){
                  vm.noDataFound = error.data.message;
                }
                 else {
                                   handleErrorResponse(error);
                                   $state.go('e_filling.inquiry')
                                }
vm.searchValue=null;

            });
    };


function getDownload() {
    vm.loader = true;
    var requestData = {
//         schemeCode  : vm.schemeCode,
                    fromDate    : moment(vm.fromDate).format("DD-MMM-YYYY") || "",
                    toDate      :  moment(vm.toDate).format("DD-MMM-YYYY") || "",
                    flag        : 'D',
        pageNo : 1,
        pageSize: vm.totalPages,
    };
    EfilingService.getEfillingDetailsDownload(requestData).then(
        function (response) {
            vm.loader = false;
            vm.filteredRecord = response.data.result;
            if (vm.selectedOption === "excel") {
              if (response.data.result.fileName) {
                 vm.fileData = response.data.result.fileData;
                 vm.fileName = response.data.result.fileName;
                 saveAs(getBlobFromBase64String(vm.fileData), vm.fileName)
                 Swal.fire("Downloaded", response.data.message, "success");
            }
          }

          vm.selectedOption = "";
            },function (error) {
           vm.loader = false;
           if ((error.data.message == "No Data Found") || (error.data.message == "NDF")) {
             vm.noDataFound = error.data.message;
           }else {
             handleErrorResponse(error);
             $state.go("e_filling.inquiry");
           }
          });
       }




     vm.textableDatePicker = textableDatePicker;
                                 vm.autoslashing = autoslashing;
                                 function textableDatePicker(id, valueDate) {
                                   commonService.textableDatePicker(id, valueDate);
                                 }
                                 function autoslashing(id) {
                                   commonService.autoslashing(id);
                                 }
//Date Validation
    vm.fromDateVal= function() {
        vm.fromDateOptions= {
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
    }
})();