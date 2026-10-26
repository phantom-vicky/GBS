(function() {

    'use strict';

    angular.module('gbmUltraApp').controller(
        'accessControlMatrixReportController',
        accessControlMatrixReportController);

    accessControlMatrixReportController.$inject = ['$scope', '$filter',
        '$state', '$stateParams', '$location', '$rootScope', 'accessControlMatrixReportService', 'commonService'
    ];

    function accessControlMatrixReportController($scope, $filter, $state,
        $stateParams, $location, $rootScope, accessControlMatrixReportService,
        commonService) {

        var vm = this;
		vm.currentPage = 1;
		vm.itemsPerPage = '5';
        vm.getroleName = getroleName;
        vm.searchroleName = '';
        vm.searchBranchId = '';
//        vm.printpage = printpage;
        vm.loginSolId = $rootScope.globals.currentUser.solId;
        vm.loginSolDesc = $rootScope.globals.currentUser.solDesc;
  		vm.solDate = $rootScope.globals.currentUser.systemDate;
        vm.solDate = $filter('date')(vm.solDate, 'dd/MM/yyyy');
        //vm.getBankName = getBankName;
        vm.getAccessControlMatrixReport = getAccessControlMatrixReport;
        vm.dataTableOption = DATA_TABLE_OPTION;
        vm.dateValidation  = dateValidation;
        vm.branchId = vm.loginSolId;
        vm.branchDesc = vm.loginSolDesc;
        vm.bankName=$rootScope.globals.currentUser.bankName;


        if ($state.current.name == 'Access_Control_Matrix_Report.inquiry_details') {

           vm.branchName = $stateParams.obj.branchName,
           vm.branchId = $stateParams.obj.branchId,
           vm.roleName = $stateParams.obj.roleName,
           vm.loginSolId = $rootScope.globals.currentUser.solId;
           vm.loginSolDesc = $rootScope.globals.currentUser.solDesc;
                getAccessControlMatrixReport();
        }


                          function configDataTable() {
                            vm.getTitleDescription = {
                                title: 'Access Control Matrix Report',
                                bankName:vm.bankName,
                                solId: $rootScope.globals.currentUser.solId,
                                solDesc: $rootScope.globals.currentUser.solDesc,
                            }
                            vm.dataTableOption = configDataTableOptions(vm.getTitleDescription);
                        }



//        function accessControlMatrixReport() {
//            vm.loader = true;
//            var requestData = {
//                'BankName': $stateParams.obj.branchName,
//                'BranchId': $stateParams.obj.branchId,
//                'roleName': $stateParams.obj.roleName,
//            };
//            accessControlMatrixReportService.getDetails(requestData).then(function(response) {
//                vm.loader = false;
//                vm.detailResponse = response.data.result;
//                console.log(response.data.result);
//
//            }, function(error) {
//                vm.loader = false;
//                console.log(error);
//            });
//
//        }




        function getAccessControlMatrixReport(){
                    vm.loader = true;
                    var requestData = {
                                   "branchId":  vm.branchId,
                                   "branchName":vm.branchName,
                                   "roleName":vm.roleName,
                                   'roleFlag': "N"
                                 }
                    accessControlMatrixReportService.getDetails(requestData).then(function(response){
                        vm.loader = false;
                        vm.detailsList = response.data.result.detailList;
                        angular.forEach( vm.detailsList,function(item){
//                        vm.slNo=item.sl_no;
                        vm.roles=item.ROLES;
                        vm.permissions=item.PERMISSION;
                        vm.createdByCreatedDate=item.CREATED_BYCREATED_DATE;
                        vm.modifiedByModifiedDate=item.MODIFIED_BYMODIFIED_DATE;
                        vm.verifiedByVerifiedDate=item.VERIFIED_BYVERIFIED_DATE;

                         })
                        configDataTable();
                    }, function(error){
                          vm.loader = false;
                          if(error.data.message == 'No Data Found' | error.data.message == 'No Records Available for the Given Input'){
                              vm.noDataFound = error.data.message;
                          }
                          else{
                          handleErrorResponse(error);
                          $state.go("Access_Control_Matrix_Report.inquiry");
                          }
                       })
                    }
		function dateValidation() {
                       vm.toDate = '';

                       vm.dateToOptions = {
                            minDate: vm.fromDate,
                       };
                       vm.dateOptions = {
                        }
                     }





        function getBankName()  {
               commonService.getBankName().then(
                (response) => {
                  vm.bankName=response.data.result.bankName;
                })
        }

        function getroleName() {
            vm.loader = true;
            vm.searchroleName = '';
            var requestData = {
                            "branchId" :  vm.searchBranchId,
                            "branchName" : vm.branchName,
                            'roleFlag': "Y"

                        }
            accessControlMatrixReportService.getDetails(requestData).then(function(response) {
                vm.loader = false;
                vm.roleNameList = response.data.result.roleList;
                angular.element('#roleNameSearcherPopUp').modal('toggle');
            }, function(error) {
                vm.loader = false;
                handleErrorResponse(error);
            });
        }




    }


})();