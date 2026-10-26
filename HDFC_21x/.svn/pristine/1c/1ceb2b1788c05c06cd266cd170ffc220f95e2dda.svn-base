(function() {

    'use strict';

    angular.module('gbmUltraApp').controller('bondInterestPayMisReportController',
        bondInterestPayMisReportController);

    bondInterestPayMisReportController.$inject = ['$scope', '$filter', '$state', '$rootScope',
        '$stateParams', '$location', '$timeout', 'bondInterestPayMisReportService', 'commonService'
    ];

    function bondInterestPayMisReportController($scope, $filter, $state, $rootScope, $stateParams,
        $location, $timeout, bondInterestPayMisReportService, commonService) {

		var vm = this;
		vm.moduleName = 'RBI Bond';
//		vm.moduleId = '0012';
		vm.solDate = $rootScope.globals.currentUser.systemDate;
		vm.subsDate = moment(vm.solDate).format("DD-MMM-YYYY");
		vm.solDate1 = $rootScope.globals.currentUser.solDate;
        vm.getModuleName = getModuleName;
        vm.getFinancialYear = getFinancialYear;
        vm.getBondInterestPayMisReportValues = getBondInterestPayMisReportValues;
        vm.loginSolDesc = $rootScope.globals.currentUser.solDesc
        vm.today=new Date();
        vm.loginSolDate = $rootScope.globals.currentUser.solDate;
		vm.loginSolId = $rootScope.globals.currentUser.solId;
		vm.commonYesNoMetaData = COMMON_YES_NO_METADATA;
		vm.sorting = sorting;
		vm.goNextPage =goNextPage;
		vm.goPreviousPage= goPreviousPage;
		vm.paginationControls= paginationControls;


         vm.pageSize = 5;
         vm.pageNo = 1;
         vm.financialYear = '';


        vm.yesNoMetaData = [
            {label : "Select",value : null},
            {label : "Yes",value : "Y"},
            {label : "No",value : 'N'}
        ];





          if($state.current.name == 'bond_interest_payment_mis_report_sol_wise.inquiry_details') {
		            if($stateParams.obj){
                    vm.paidMonth = $stateParams.obj.paidMonth;
                    vm.financialYear = $stateParams.obj.financialYear;
                    vm.schemeName = $stateParams.obj.schemeName;
                    vm.schemeCode = $stateParams.obj.schemeCode;

                    vm.getBondInterestPayMisReportValues();
		           }
		  }




       function sorting(key){
        vm.sortKey = key;
        vm.sortReverse = (vm.sortKey == key) ? !vm.sortReverse : vm.sortReverse;
        vm.sortReverse ? vm.sortOrder = 'DESC' : vm.sortOrder = 'ASC';
        vm.sortField = key;
        vm.getBondInterestPayMisReportValues();
        }


         function goPreviousPage() {

         vm.pageNo = parseInt(vm.pageNo) - 1;
         vm.getBondInterestPayMisReportValues();
         }

         function goNextPage() {

         vm.pageNo = parseInt(vm.pageNo) + 1;
         vm.getBondInterestPayMisReportValues();
         }


          function paginationControls () {

          vm.pages = [];
//          vm.maxPages = parseInt(vm.pageDetails.maxPages);

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

		  function getBondInterestPayMisReportValues(){
            vm.loader = true;
            var requestData={
            'financialYear' : vm.financialYear,
            'paidMonth' : vm.paidMonth ,
            'moduleId' : vm.schemeCode,
            'pageSize' : vm.pageSize,
            'pageNo' : vm.pageNo,
            'searchValue' : vm.searchValue,
            'sortOrder' : vm.sortOrder,
            'sortField' : vm.sortField,
            'downloadType' : vm.downloadType,
            'solDesc' :  vm.loginSolDesc,
            'schemeCode' : vm.schemeCode
            }
           bondInterestPayMisReportService.getBondInterestPayMisReportValues(requestData).then(function (response) {
               if(response.data.message != 'SUCCESS'){
               Swal.fire('Warning!', "No Record Found", 'warning');
                }
             else{

                if(vm.downloadType == 'excel' || vm.downloadType == 'pdf'){
                        vm.exportDataList = response.data.result;
                        vm.fileData = vm.exportDataList.fileData;
                        vm.fileName = vm.exportDataList.fileName;
                        vm.downloadType='';
                        saveAs(getBlobFromBase64String(vm.fileData), vm.fileName);
                        Swal.fire("Success!", vm.fileName+"\n Downloaded Successfully", "success");

                     }else{
                        vm.dataList = response.data.result;
                        vm.dataList = response.data.result.dataList;
                        vm.maxPages = response.data.result.maxPagesOut;
                        vm.totGrossIntAmt = response.data.result.totGrossIntAmt;
                        vm.totTdsAmt = response.data.result.totTdsAmt;
                        vm.totNetIntAmt =  response.data.result.totNetIntAmt;
                     }


             }

             vm.paginationControls();
             vm.loader = false;
             }, function (error) {
                 handleErrorResponse(error);
                 $state.go('bond_interest_payment_mis_report_sol_wise.inquiry')
                 vm.loader = false;
             });
		  }



        function getModuleName() {
                vm.loader = true;
                vm.referenceId = 'TAX';
                var requestData = {
                'moduleId' : vm.schemedesc,
                'referenceId': vm.referenceId
                };
               bondInterestPayMisReportService.getRbiBondModuleNameDetails(requestData).then(function (response) {
                   vm.loader = false;
                   vm.schemeList = response.data.result;
               }, function (error) {
                   handleErrorResponse(error);
                   vm.loader = false;
               });
         }



        function getFinancialYear(){
            var requestData = {
            'moduleId' : vm.schemeCode,
            'referenceId': vm.referenceId
            };

        bondInterestPayMisReportService.getFinancialYear(requestData).then(function (response){
        vm.financialyearData = response.data.result.cur_fin_year;
        vm.financialyearData1 = response.data.result.cur_fin_year_1;
        });
        }






       } })();