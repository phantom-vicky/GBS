(function() {

	'use strict';

	angular.module('gbmUltraApp').controller(
			'BondInterestController',
			BondInterestController);

	BondInterestController.$inject = [ '$scope', '$filter',
			'$state', '$stateParams', '$location', '$rootScope','BondInterestCalculatedService', 'commonService' ];

	function BondInterestController($scope, $filter, $state,
			$stateParams, $location, $rootScope, BondInterestCalculatedService,
			commonService) {

		var vm = this;

        vm.currentPage = 1;
        vm.itemsPerPage = '5';
        vm.loginSolId = $rootScope.globals.currentUser.solId;
        vm.loginSolDesc = $rootScope.globals.currentUser.solDesc;
        vm.solDate = $rootScope.globals.currentUser.systemDate;
        vm.getBondInterestCalculatedReport = getBondInterestCalculatedReport;
        vm.getModuleName=getModuleName;
        vm.printpage = printpage;
        vm.getBranchId=getBranchId;
         vm.alert=alert;
         vm.getbondInterestDetailsfile = getbondInterestDetailsfile;


       vm.goNextPage = goNextPage;
                         vm.paginationControls = paginationControls;
                         vm.showNext = false;
                         vm.showPrevious = false;
                         vm.goPreviousPage = goPreviousPage;
                          vm.goNextPage = goNextPage;

                         $scope.sortReverse = false;
                         $scope.sort = function(key){
                         $scope.sortKey = key;
                         $scope.sortReverse = ($scope.sortKey == key) ? !$scope.sortReverse : $scope.sortReverse;
                         $scope.sortReverse ? vm.sortOrder = 'DESC' : vm.sortOrder = 'ASC';
                         vm.sortField = key;
                         vm.getBondInterestCalculatedReport();
                         }

                         vm.pageSize = 5;
                         vm.pageNo = 1;
         if ($state.current.name == 'Bond_Interest_NOT_Calculated_Report.inquiry_details')
                {
                vm.branchId = $stateParams.obj.branchId;
                vm.interestPaid = $stateParams.obj.interestPaid;
                vm.financialYear = $stateParams.obj.financialYear;
                vm.schemeCode = $stateParams.obj.schemeCode;
                vm.schemeName = $stateParams.obj.schemeName;


                 getBondInterestCalculatedReport();

                }


                 function goPreviousPage() {

                                         vm.pageNo = vm.pageNo - 1;
                                         vm.getBondInterestCalculatedReport();
                                         }

                                         function goNextPage() {

                                         vm.pageNo = vm.pageNo + 1;
                                         vm.getBondInterestCalculatedReport();
                                         }



                         function paginationControls () {

                                 vm.pages = [];
                                 vm.maxPages = parseInt(vm.pageDetails.maxPages);

                                 for(var i=1; i<= parseInt(vm.pageDetails.maxPages); i++){
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

                             function getbondInterestDetailsfile() {
                                 vm.loader = true;

                                 var requestData = {
                                'schemeCode':vm.schemeCode,
                                 'schemeName':vm.schemeName,
                                 'schemeName':vm.schemeName,
                                'branchId':vm.branchId,
                                'interestPaid': vm.interestPaid,
                              'financialYear': vm.financialYear,
                              'pageSize' : vm.pageSize,
                                           'pageNo' : vm.pageNo,
                                             'searchValue' : vm.searchValue,
                                             'sortOrder' : vm.sortOrder,
                                      'sortField' : vm.sortField,
                                 'downloadType' : vm.downloadType,
                                 'isDownload' : "Y",
                                'branchname': vm.loginSolDesc
                                 };

                                 BondInterestCalculatedService.getBondInterestCalculatedDetails(requestData)
                                     .then(
                                         function(response) {


                                         vm.loader = false;
                                                       vm.bondInterestCalculateddetailsList = response.data.result;

                                                        if(response.data.message != 'SUCCESS'){
                                                          Swal.fire('Warning!', "No Record Found", 'warning');
                                                        }
                                                        else{
                                                         vm.fileData = vm.bondInterestCalculateddetailsList.fileData;
                                                         vm.fileName = vm.bondInterestCalculateddetailsList.fileName;
                                                         saveAs(getBlobFromBase64String(vm.fileData), vm.fileName);
                                                           Swal.fire("Success!", vm.fileName+"\n Downloaded Successfully", "success");
                                                        }


                                                    }, function (error) {

                                                        vm.loader = false;

                                                            handleErrorResponse(error);
                                                             Swal.fire("Error!", "Download unsuccessful", "error");

                                                    })

                                         }

        function configDataTable() {
                    vm.getTitleDescription = {
                        title: 'Bond Interest NOT Calculated Report',
                        solId: $rootScope.globals.currentUser.solId,
                        solDesc: $rootScope.globals.currentUser.solDesc
                    }
                    vm.dataTableOption = configDataTableOptions(vm.getTitleDescription);
                }



            function alert(item){

                                             for (let i = 0;i<vm.schemeList.length;i++){
                                              if(vm.schemeList[i].subCode == item){
                                                    vm.schemeName = vm.schemeList[i].subCodeDescription;

                                               }
                                             }
                                          }

 function printpage(){
                    window.print()
                  }
 function getModuleName() {
            vm.loader = true;
            vm.referenceId = 'TAX';
            var requestData = {
                'referenceId': vm.referenceId
            };
            BondInterestCalculatedService.getRbiBondModuleNameDetails(requestData).then(function (response) {
                vm.loader = false;
                vm.schemeList = response.data.result;
            }, function (error) {
                handleErrorResponse(error);
                vm.loader = false;
            });
        }



         function getBondInterestCalculatedReport(){
                       vm.loader = true;

                                  var requestData = {
                                                'schemeCode':vm.schemeCode,
                                  				'branchId':vm.branchId,
                                                'interestPaid': vm.interestPaid,
                                                   'financialYear': vm.financialYear,
                                                     'pageSize' : vm.pageSize,
                                                           'pageNo' : vm.pageNo,
                                                             'searchValue' : vm.searchValue,
                                                             'sortOrder' : vm.sortOrder,
                                                             'sortField' : vm.sortField



                                  			}

                                  BondInterestCalculatedService.getBondInterestCalculatedDetails(requestData).then(function(response) {
                                      vm.loader = false;

                                 vm.pageDetails =response.data.result;
                                 vm.bondInterestCalculatedList=response.data.result.data;

                                 vm.paginationControls();

                              configDataTable();
                         }, function(error){
                             vm.loader = false;
                             if(error.data.message == 'No Data Found' | error.data.message == 'NDF'){
                                   vm.noDataFound = error.data.message;
                             }else{
                               handleErrorResponse(error);
                               $state.go('Bond_Interest_NOT_Calculated_Report.inquiry_details');
                             }

                         })
                     }

                function getBranchId() {
                                       vm.loader = true;

                                       var requestData = {
                                            'schemeCode':vm.schemeCode

                                       }
                                       BondInterestCalculatedService.getDetails(requestData).then(function(response) {
                                           vm.loader = false;
                                           vm.branchIdList = response.data.result;
                                           angular.element('#branchIdModal').modal('toggle');
                                       }, function(error) {
                                           handleErrorResponse(error);
                                           vm.loader = false;
                                       });
                                   }








	}
})();