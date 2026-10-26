(function() {

     	'use strict';

     	angular.module('gbmUltraApp').controller(
     			'reportOnBranchWiseDeptWiseUsersController',
     			reportOnBranchWiseDeptWiseUsersController);

     	reportOnBranchWiseDeptWiseUsersController.$inject = [ '$scope', '$filter',
     			'$state', '$stateParams', '$location', '$rootScope','reportOnBranchWiseDeptWiseUsersService', 'commonService' ];

     	function reportOnBranchWiseDeptWiseUsersController($scope, $filter, $state,
     			$stateParams, $location, $rootScope, reportOnBranchWiseDeptWiseUsersService,
     			commonService) {

     		var vm = this;


             vm.currentPage = 1;
             vm.itemsPerPage = '5';
             vm.loginSolId = $rootScope.globals.currentUser.solId;
             vm.solDate = $rootScope.globals.currentUser.systemDate;
             vm.loginSolDesc = $rootScope.globals.currentUser.solDesc;

             vm.getReportDetails = getReportDetails;

             vm.currentDate = new Date();
             vm.solDate = $filter('date')(vm.solDate, 'dd/MM/yyyy');

              function configDataTable() {
                 vm.getTitleDescription = {
                     title: 'Report on Branch-wise / Dept.-Wise Users With Respective Roles & Privileges As of Date',
                     solId: $rootScope.globals.currentUser.solId,
                     solDesc: $rootScope.globals.currentUser.solDesc
                 }

                 vm.dataTableOption = configDataTableOptions(vm.getTitleDescription);
             }


              if ($state.current.name == 'report_on_branch_wise_dept_wise_users.inquiry_details') {

                             if($stateParams.obj){
                                vm.dcId = $stateParams.obj.dcId;
                                vm.dcDescription = $stateParams.obj.dcDescription;
                                vm.branchId = $stateParams.obj.branchId;
                                vm.branchDescription = $stateParams.obj.branchDescription;
                                vm.deptCode = $stateParams.obj.deptCode;
                                vm.userId = $stateParams.obj.userId;

                                getReportDetails('O');
                         }else {
                             $state.go('report_on_branch_wise_dept_wise_users.inquiry');
                         }
                     }


         function getReportDetails(flag){
           vm.flag = flag;
           vm.loader = true;
           if(vm.flag == 'DC'){
                var requestData = {};
                requestData.inputFlag = vm.flag;
            }else if(vm.flag == 'B'){
                var requestData = {};
                requestData.inputFlag = vm.flag;
            }else if(vm.flag == 'D'){
                var requestData = {};
                requestData.inputFlag = vm.flag;
                requestData.branchId = vm.branchId;
            }else if(vm.flag == 'U'){
                var requestData = {};
                requestData.inputFlag = vm.flag;
                requestData.branchId = vm.branchId;
                requestData.deptCode = vm.deptCode;
            }else if(vm.flag == 'O'){
                var requestData = {};
                requestData.inputFlag = vm.flag;
                requestData.branchId = vm.branchId;
                requestData.deptCode = vm.deptCode;
                requestData.userId = vm.userId;
            }
           reportOnBranchWiseDeptWiseUsersService.getReportDetails(requestData).then(function(response){
               vm.loader = false;
               if(vm.flag == 'DC'){
                   vm.dcId = response.data.result.dcId;
                   vm.dcDescription = response.data.result.dcDescription;
               }else if(vm.flag == 'B'){
                   vm.report = response.data.result;
                   angular.element('#branchSearcher').modal('toggle');
               }else if(vm.flag == 'D'){
                   vm.report = response.data.result;
                   angular.element('#deptSearcher').modal('toggle');
               }else if(vm.flag == 'U'){
                   vm.report = response.data.result;
                   angular.element('#userSearcher').modal('toggle');
               }else if(vm.flag == 'O'){
                   vm.report = response.data.result;
                   configDataTable();
               }
           },function(error){
               vm.loader = false;
               if(vm.flag == 'O'){
                 if(error.data.message == 'NO DATA FOUND' | error.data.message == 'NDF'){
                     vm.noDataFound = error.data.message;
                 }
                 else{
                     handleErrorResponse(error);
                     $state.go("report_on_branch_wise_dept_wise_users.inquiry");
                 }
               }else{
                     handleErrorResponse(error);
               }
           })
         }

     	}
     })();