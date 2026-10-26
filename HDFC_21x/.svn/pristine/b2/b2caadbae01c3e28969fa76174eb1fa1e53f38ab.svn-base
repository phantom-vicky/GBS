(function() {

    'use strict';

    angular.module('gbmUltraApp').controller(
        'rbiBondBodEodStatusController',
        rbiBondBodEodStatusController);

    rbiBondBodEodStatusController.$inject = ['$scope', '$filter',
        '$state', '$stateParams', '$location', '$rootScope', 'rbiBondBodEodStatusService', 'commonService'
    ];

    function rbiBondBodEodStatusController($scope, $filter, $state,
        $stateParams, $location, $rootScope, rbiBondBodEodStatusService,
        commonService) {

          var vm = this;

                vm.currentPage = 1;
                vm.itemsPerPage = '5';
                vm.moduleName = 'RBI BOND';
                vm.loginSolId = $rootScope.globals.currentUser.solId;
                vm.loginSolDesc = $rootScope.globals.currentUser.solDesc;
                vm.solDate = $rootScope.globals.currentUser.systemDate;
                vm.getdetails = getdetails;














        //    Pagination Integration
            vm.goPreviousPage = goPreviousPage;
            vm.goNextPage = goNextPage;
           // vm.paginationControls = paginationControls;
            vm.showNext = false;
            vm.showPrevious = false;
            $scope.sortReverse = false;
            $scope.sort = function (key) {
            $scope.sortKey = key;
            $scope.sortReverse =
            $scope.sortKey == key ? !$scope.sortReverse : $scope.sortReverse;
            $scope.sortReverse ? (vm.sortOrder = "DESC") : (vm.sortOrder = "ASC");
            vm.sortField = key;
            vm.getDetails();
            };



            vm.pageNo = 1;
            vm.pageData = null;
            //BASHA CODE START
            vm.records = [];
            vm.filteredRecords = [];
            vm.pageSizeOptions = [5,10, 25, 50, 100];
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
              vm.getDetails();
            }

            function goNextPage() {
              vm.pageNo = parseInt(vm.pageNo) + 1;
              vm.getDetails();
            }

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




            vm.setPage = function (page) {
              console.log("Page Number in setPage: " + page);
              if (page < 1) {
                vm.currentPage = 1;
              } else if (page > vm.totalPages) {
                vm.currentPage = vm.totalPages;
              } else {
                vm.currentPage = page;
              }
              console.log("vm.currentPage: " + vm.currentPage);
              vm.updatePagination();
            };

            vm.updatePagination = function () {
              try {
                var startPage, endPage;
                console.log("Inside updatePagination function...");

                if (vm.totalPages <= vm.maxPagesToShow) {
                  startPage = 1;
                  endPage = vm.totalPages;
                } else {
                  var maxPagesBeforeCurrentPage = Math.floor(vm.maxPagesToShow / 2);
                  var maxPagesAfterCurrentPage = Math.ceil(vm.maxPagesToShow / 2) - 1;

                  if (vm.currentPage <= maxPagesBeforeCurrentPage) {
                    startPage = 1;
                    endPage = vm.maxPagesToShow;
                  } else if (
                    vm.currentPage + maxPagesAfterCurrentPage >=
                    vm.totalPages
                  ) {
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
                console.log("vm.pages: " + vm.pages);
              } catch (error) {
                console.error("An error occurred in updatePagination:", error);
              }
            };

            vm.updatePageSize = function () {
              vm.totalPages = Math.ceil(vm.filteredRecords.length / vm.pageSize);
              console.log("totalPages: " + vm.totalPages);
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
                   const searchText = vm.searchText ? vm.searchText.toLowerCase() : '';
                   vm.filteredRecords = vm.model.filter(record => {
                       return Object.values(record).some(value =>
                           value !== null &&
                           value !== undefined &&
                           String(value).toLowerCase().includes(searchText)
                       );
                   });
                   vm.totalPages = Math.ceil(vm.filteredRecords.length / vm.pageSize);
                   vm.setPage(1);
               };


            if ($state.current.name == "rbibond_bod_eod_status_inquiry_dc.inquiry_details") {
                vm.schemeCode = $stateParams.obj.schemeCode;
                vm.schemeName = $stateParams.obj.schemeName;
                vm.schemeStatus = $stateParams.obj.schemeStatus;
                vm.branchId = $stateParams.obj.branchId;
                vm.levelName = $stateParams.obj.levelName;
                vm.levelId = $stateParams.obj.levelId;
                vm.statusInquiry = $stateParams.obj.statusInquiry;
                getdetails();
                }

                function formatDate(date) {
                    if (!date) return "";
                    const d = new Date(date);
                    const day = String(d.getDate()).padStart(2, "0");
                    const month = String(d.getMonth() + 1).padStart(2, "0"); // Months are 0-based
                    const year = d.getFullYear();
                    return `${day}/${month}/${year}`;
                }


        vm.downloadReport = function () {
            if (vm.downloadType === "excel") {
                vm.downloadExcel();
            }
            vm.downloadType = "";
        };

       vm.downloadExcel = function () {
           const additionalDetails = [
               ["RBI Bond BOD_EOD Status Inquiry"],
               ["Bank Name: INSPIRISY BANK LTD"],
               ["Sol ID: 101 / BRANCH OF THE BANK"],
               ...(vm.statusInquiry ? [["Status Inquiry: " +
                   (vm.statusInquiry === 'EP' ? 'EOD Pending' :
                    vm.statusInquiry === 'BC' ? 'BOD Completed' :
                    vm.statusInquiry === 'EC' ? 'EOD Completed' : vm.statusInquiry)]] : []),

               ["Bod / Eod Date: " + formatDate(vm.solDate)],
               ["Module Name: RBI BOND"],
               ...(vm.levelName ? [["Level Name: " + vm.levelName]] : []),
               ...(vm.branchId ? [["Level Branch Id: " + vm.branchId]] : []),
               [],
           ];

           var headers = [[
               "S.No.", "Branch ID", "Branch Description", "Level Name"
           ]];

           var rows = vm.filteredRecords.map(function (row, index) {
               return [
                   index + 1,
                   row.branchId,
                   row.branchDesc,
                   row.levelName,
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
           XLSX.writeFile(wb, "RBI Bond BOD_EOD Status Inquiry.xlsx");
       };


        vm.getlevelname =  function (){
         console.log("working");
         vm.loader = true;
         var requestData ={
         flag : "L",
         };
         rbiBondBodEodStatusService.getDetails(requestData)
         .then(function(response){
         vm.loader = false;
         vm.levelname = response.data.result.list;
         angular.element("#levelBranchModal").modal("toggle");
         },function (error){
         handleErrorResponse(error);
         vm.loader = false;
         });
         }

        vm.getLevelBranchTorch = function (){
         vm.loader = true;
         var requestData = {
         levelId: vm.levelId,
         flag : "LB",
         };
         rbiBondBodEodStatusService.getDetails(requestData)
         .then(function(response){
         vm.loader = false;
         vm.levelbranchid = response.data.result.list;
         angular.element("#branchModal").modal("toggle");
         },function(error){
         handleErrorResponse(error);
         vm.loader = false;
         });
         }

       function getdetails(){
        vm.loader = true;
        var requestData ={
        flag : "D",
        statusInquiry : vm.statusInquiry,
        levelName : vm.levelName,
        levelBranchId: vm.branchId,
        levelId: vm.levelId,
         pageNo: vm.pageNo,
        pageSize: vm.pageSize,
        searchValue: vm.searchText,
         sortOrder: vm.sortOrder,
         sortField: vm.sortField,
        };




        rbiBondBodEodStatusService.getDetails(requestData)
        .then(function (response){
        vm.loader = false;
        vm.model = response.data.result.list;
        vm.filteredRecords = response.data.result.list;
        vm.pageDetails = response.data.result.list;

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
                  if(
                    (error.data.message == "No Data Found") ||
                    (error.data.message == "NDF")
                  ) {
                    vm.noDataFound = error.data.message;
                  } else {
                    handleErrorResponse(error);
                    $state.go("rbibond_bod_eod_status_inquiry_dc.inquiry_details");
                  }
                });
        }
        }
 })();