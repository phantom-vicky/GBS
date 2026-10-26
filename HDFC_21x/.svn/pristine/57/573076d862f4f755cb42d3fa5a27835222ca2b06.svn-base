(function() {
    'use strict';

    angular.module('gbmUltraApp').controller('RoleRightReportController', RoleRightReportController);

    RoleRightReportController.$inject = ['$scope', '$filter', '$state', '$stateParams', '$location', '$rootScope', 'RoleRightReportService', 'commonService'];

    function RoleRightReportController($scope, $filter, $state, $stateParams, $location, $rootScope, RoleRightReportService, commonService) {
        var vm = this;
        vm.currentDate = new Date();
        vm.date = vm.currentDate;
        vm.commonYesNoMetaData = COMMON_YES_NO_METADATA;
        vm.dateValidation = dateValidation;
        vm.getRoleRightsReport = getRoleRightsReport;
        vm.records = [];
            vm.filteredRecords = [];
            vm.filteredRecord = [];
            vm.currentPage = 1;
            vm.maxPagesToShow = 5;
            vm.searchValue = vm.searchValue || "";
            vm.loading = true;
            vm.showExportOptions = false;
            vm.itemsPerPage = "5";
            vm.goPreviousPage = goPreviousPage;
            vm.goNextPage = goNextPage;
            vm.paginationControls = paginationControls;
            vm.showNext = false;
            vm.showPrevious = false;
            $scope.sortReverse = false;
            $scope.sort = function(key){
            $scope.sortKey = key;
            $scope.sortReverse = ($scope.sortKey == key) ? !$scope.sortReverse : $scope.sortReverse;
            $scope.sortReverse ? vm.sortOrder = 'DESC' : vm.sortOrder = 'ASC';
            vm.sortField = key;
            vm.getRoleRightsReport();
            }
            vm.pageSize = 5;
            vm.pageNo = 1;
            function goPreviousPage() {
             vm.pageNo = parseInt(vm.pageNo) - 1;
             vm.getRoleRightsReport();
             }
             function goNextPage() {
             vm.pageNo = parseInt(vm.pageNo) + 1;
             vm.getRoleRightsReport();
             }
             function paginationControls () {
             vm.pages = [];
             for(var i=1; i<= parseInt(vm.maxPages); i++){
                vm.pages.push(parseInt(i));
              }
             if(vm.pageNo != 1){
                 vm.showPrevious = true;
             }
             else{
                 vm.showPrevious = false;
             }

             if(vm.pageNo != vm.maxPages){
                  vm.showNext = true;
             }
             else{
                  vm.showNext = false;
             }
             }
        if ($state.current.name == "role_rights_report.inquiry_details") {
            vm.date = $stateParams.obj.date;
            getRoleRightsReport();
        }/*

       function configDataTable() {
           vm.getTitleDescription = {
               title: 'Role Rights Report',
               solId: $rootScope.globals.currentUser.solId,
               solDesc: $rootScope.globals.currentUser.solDesc
           };
           vm.dataTableOption = configDataTableOptions(vm.getTitleDescription);


           var formattedData = [];


           vm.dataTableOption.exportData = function() {
               formattedData = [];
               vm.getRoleRightsReport.forEach(function(x) {
                   var formattedRightsAssigned = formatRightsAssigned(x.rightsAssigned);
                   var rowArray = [
                       x.roleName,
                       x.roleDesc,
                       formattedRightsAssigned,
                       x.roleDescription,
                       x.rightsAssignedFlg,
                       x.roleStatus,
                       x.departmentCode,
                       x.typeAccess,
                       x.lastCheckerDate
                   ];
                   formattedData.push(rowArray);
               });
               return formattedData;
           };
       }*/

vm.getDownload=getDownload;
function getDownload() {
    vm.loader = true;
    var requestData = {
        date: $filter('date')(vm.date, "dd-MMM-yyyy"),
        pageNo : 1,
        pageSize: vm.total,
        searchValue:vm.searchValue
    };
        RoleRightReportService.getRoleRightsReportDownload(requestData).then(function(response) {
            vm.loader = false;
            vm.filteredRecord = response.data.result.roleRightsReportDTOList;
            if (vm.selectedOption === "excel") {
              if (response.data.result.fileName) {
                 vm.fileData = response.data.result.fileData;
                 vm.fileName = response.data.result.fileName;
                 saveAs(getBlobFromBase64String(vm.fileData), vm.fileName)
                 Swal.fire("Downloaded", response.data.message, "success");
            }
          } else if (vm.selectedOption === "csv") {
            vm.downloadCsv();
          }
          vm.selectedOption = "";
            },function (error) {
           vm.loader = false;
           if ((error.data.message == "No Data Found") || (error.data.message == "NDF")) {
             vm.noDataFound = error.data.message;
           }else {
             handleErrorResponse(error);
             $state.go('role_rights_report.inquiry');
           }
          });
       }
        function getRoleRightsReport() {
            vm.loader = true;
            if(null!=vm.searchValue){
                var requestData = {
                    date: $filter('date')(vm.date, "dd-MMM-yyyy"),
                    pageNo : vm.pageNo,
                    pageSize:vm.pageSize,
                    searchValue:vm.searchValue
                };
                }else{
                var requestData = {
                    date: $filter('date')(vm.date, "dd-MMM-yyyy"),
                    pageNo : vm.pageNo,
                    pageSize:vm.pageSize,
                    searchValue:vm.searchValue
                };
               }
            RoleRightReportService.getRoleRightsReport(requestData).then(function(response) {
              vm.loader = false;
              vm.filteredRecords = response.data.result.roleRightsReportDTOList;
              vm.total = response.data.total;
              vm.maxPages =  Math.ceil(response.data.total/vm.pageSize);
              if (vm.filteredRecords.length === 0) {
                  vm.noDataFoundInSearch = true;
              } else {
                  vm.noDataFoundInSearch = false;
              }
              vm.paginationControls();
            }, function(error) {
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
              } else {
                   handleErrorResponse(error);
                   $state.go('role_rights_report.inquiry');
              }
            });
        }

        function dateValidation() {
            vm.toDate = '';
            vm.dateOptions = {
                maxDate: vm.currentDate
            }
        }
    }
})();
