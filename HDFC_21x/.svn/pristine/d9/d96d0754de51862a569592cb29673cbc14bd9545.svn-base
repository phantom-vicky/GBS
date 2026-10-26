(function() {

     	'use strict';

     	angular.module('gbmUltraApp').controller(
     			'PopulationReportController',
     			PopulationReportController);

     	PopulationReportController.$inject = [ '$scope', '$filter',
     			'$state', '$stateParams', '$location', '$rootScope','PopulationReportService', 'commonService' ];

     	function PopulationReportController($scope, $filter, $state,
     			$stateParams, $location, $rootScope, PopulationReportService,
     			commonService) {

     		        var vm = this;
     		        vm.currentDate = new Date();
                    vm.date = vm.currentDate;
                    console.log("currentDate-"+vm.date);
                    vm.dateValidation = dateValidation;
                    vm.getPopulationData = getPopulationData;

                     vm.filteredRecords = [];
                        vm.currentPage = 1;
                        vm.maxPagesToShow = 5;
                        vm.searchText = "";
                        vm.loading = true;
                        vm.showExportOptions = false;
                        vm.itemsPerPage = "5";
                        vm.showNext = false;
                        vm.showPrevious = false;


                   vm.pageNo = 1;
                   vm.pageSize = 5;

                     vm.goPreviousPage = function () {
                       vm.pageNo = parseInt(vm.pageNo) - 1;
                       vm.getPopulationData();
                     };

                     vm.goNextPage = function () {
                       vm.pageNo = parseInt(vm.pageNo) + 1;
                       vm.getPopulationData();
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


                    if($state.current.name == "population_report.inquiry_details"){
                    vm.date = $stateParams.obj.date
                    getPopulationData();
                    }






                   function getPopulationData() {
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
                                PopulationReportService.getPopulationData(requestData).then(function(response) {
                                  vm.loader = false;
                                  vm.populationreportData = response.data.result.populationDTOList;
                                  vm.total = response.data.total;
                                  vm.maxPages =  Math.ceil(response.data.total/vm.pageSize);
                                  if (vm.populationreportData.length === 0) {
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
                                       $state.go('population_report.inquiry');
                                  }
                                });
                            }



       vm.getDownload=getDownload;
       function getDownload() {
           vm.loader = true;
           var requestData = {
               date: $filter('date')(vm.date, "dd-MMM-yyyy"),
               pageNo : 1,
               pageSize: vm.total,
               searchValue:vm.searchValue
           };
               PopulationReportService.getPopulationReportDownload(requestData).then(function(response) {
                   vm.loader = false;
                   vm.filteredRecord = response.data.result.populationDTOList;
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
                    $state.go('population_report.inquiry');
                  }
                 });
              }

                    function dateValidation() {
                                 vm.toDate = '';
                                 vm.dateOptions = {
                                   maxDate:vm.currentDate
                                  }
                    }


     	}
     })();