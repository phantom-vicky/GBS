(function() {

	'use strict';

	angular.module('gbmUltraApp').controller(
			'adminActivityReportController',
			adminActivityReportController);

	adminActivityReportController.$inject = [ '$scope', '$filter',
			'$state', '$stateParams', '$location', '$rootScope','adminActivityReportService', 'commonService' ];

	function adminActivityReportController($scope, $filter, $state,
			$stateParams, $location, $rootScope, adminActivityReportService,
			commonService) {

		var vm = this;
		vm.dateValidation = dateValidation;
		vm.getDetails = getDetails;
        vm.currentDate = new Date();
         vm.filteredRecords = [];
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
            vm.getDetails();
            }
            vm.pageSize = 5;
            vm.pageNo = 1;
            function goPreviousPage() {
             vm.pageNo = parseInt(vm.pageNo) - 1;
             vm.getDetails();
             }
             function goNextPage() {
             vm.pageNo = parseInt(vm.pageNo) + 1;
             vm.getDetails();
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

        if ($state.current.name == 'admin_activity_report.inquiry_details') {
            if($stateParams.obj){
                    vm.fromDate = $filter('date')($stateParams.obj.fromDate, 'dd-MMM-yyyy');
                    vm.toDate = $filter('date')($stateParams.obj.toDate, 'dd-MMM-yyyy');
                    if(vm.fromDate && vm.toDate){
                        getDetails();
                    }else{
                        $state.go('admin_activity_report.inquiry')
                    }
            }else{
                $state.go('admin_activity_report.inquiry')
            }

        }
vm.getDownload=getDownload;
function getDownload() {
    vm.loader = true;
    var requestData = {
        fromDate : vm.fromDate,
        toDate : vm.toDate,
        pageNo : 1,
        pageSize: vm.total,
        searchValue:vm.searchValue
    };
        adminActivityReportService.adminActivityReportDownload(requestData).then(function(response) {
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
             $state.go('admin_activity_report.inquiry');
           }
          });
       }
        function getDetails() {
            vm.loader = true;
             if(null!=vm.searchValue){
            var requestData = {
                fromDate : vm.fromDate,
                toDate : vm.toDate,
                pageNo : vm.pageNo,
                pageSize:vm.pageSize,
                searchValue:vm.searchValue
            };
            }else{
            var requestData = {
                fromDate : vm.fromDate,
                toDate : vm.toDate,
                pageNo : vm.pageNo,
                pageSize:vm.pageSize,
                searchValue:vm.searchValue
            };
           }
            adminActivityReportService.getDetails(requestData).then(function(response) {
                     vm.loader = false;
                      vm.filteredRecords = response.data.result;
                      vm.total = response.data.total;
                      vm.maxPages =  Math.ceil(response.data.total/vm.pageSize);
                      if (vm.filteredRecords.length === 0) {
                          vm.noDataFoundInSearch = true;
                      } else {
                          vm.noDataFoundInSearch = false;
                      }
                      vm.paginationControls();
                    }, function(error){
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
                           $state.go('admin_activity_report.inquiry');
                      }
                 });
        }
        function dateValidation() {
                         vm.toDate = '';
                         vm.dateToOptions = {
                             minDate: vm.fromDate,
                             maxDate: vm.currentDate,
                             //maxDate: (vm.currentDate < moment(vm.fromDate).endOf('month').toDate())?(vm.currentDate) : (moment(vm.fromDate).add(1, 'M').toDate()),
                         };
                          vm.dateOptions = {
                             maxDate: vm.currentDate,
                         }
        }
	}
})();