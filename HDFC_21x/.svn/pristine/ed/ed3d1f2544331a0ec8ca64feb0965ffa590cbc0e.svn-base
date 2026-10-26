(function() {

	'use strict';

	angular.module('gbmUltraApp').controller(
			'netBankingTransactionforallholdersreportpdfController',
			netBankingTransactionforallholdersreportpdfController);

	netBankingTransactionforallholdersreportpdfController.$inject = [ '$scope', '$filter',
			'$state', '$stateParams', '$location', '$rootScope','netBankingTransactionforallholdersreportpdfService', 'commonService' ];

	function netBankingTransactionforallholdersreportpdfController($scope, $filter, $state,
			$stateParams, $location, $rootScope, netBankingTransactionforallholdersreportpdfService,
			commonService) {

		var vm = this;

        vm.currentPage = 1;
        vm.itemsPerPage = '5';
        vm.loginSolId = $rootScope.globals.currentUser.solId;
        vm.loginSolDesc = $rootScope.globals.currentUser.solDesc;
        vm.solDate = $rootScope.globals.currentUser.systemDate;
        vm.downloadPdfFile = downloadPdfFile;
        vm.validateInput =  validateInput;
        vm.printpage = printpage;

      //  vm.getModuleName=getModuleName;
        vm.getDetails   = getDetails;
       // vm.getPantorch  = getPantorch;
       // vm.getblaNoInq  = getblaNoInq;
       // vm.getsubscriptionIdInq =  getsubscriptionIdFInq;
        vm.downloadAllPdfFiles  =   downloadAllPdfFiles;


        vm.bankName=$rootScope.globals.currentUser.bankName;
         vm.subScriptionIdList = [];

         if ($state.current.name == 'Net_Banking_Transaction_for_all_holders_report_pdf.inquiry_details')
                {
                   if($stateParams.obj){
                   vm.schemeCode = $stateParams.obj.schemeCode;
                    vm.blaNo = $stateParams.obj.blaNo;
                   vm.schemeName = $stateParams.obj.schemeName;

                     vm.panNum = $stateParams.obj.panNum;
                       vm.bondHolderName = $stateParams.obj.bondHolderName;
                         vm.subscriptionId = $stateParams.obj.subscriptionId;
                           vm.fromDate = $stateParams.obj.fromDate;
                             vm.toDate = $stateParams.obj.toDate;






                  }
                 vm.getDetails('D');

                }





                function validateInput(event, id) {
                    const textBox = document.getElementById(id);

                    var charCode = event.which || event.keyCode;
                    var charTyped = String.fromCharCode(charCode);

                    if (textBox) {
                        const cursorPosition = textBox.selectionStart;
                        if (cursorPosition === 0 && charTyped === ' ') {
                            event.preventDefault();
                            return;
                        }
                    }

                    var currentValue = event.target.value + charTyped;

                    var pattern = /^[a-zA-Z\s']*$/;

                    if (!pattern.test(currentValue)) {
                        event.preventDefault();
                    }
                }





         function getDetails(flag){
                       vm.loader = true;
                       vm.date = $rootScope.formatDateToDDMMMYYYY(vm.date);
                                  var requestData = {


                                               'flag' : flag,
                                               'schemeCode': vm.schemeCode,
                                               'bondHolderName' : vm.bondHolderName,
                                               'panNum' : vm.panNum,
                                               'blaNo': vm.blaNo,
                                               'subscriptionId': vm.subscriptionId,
                                               'fromDate': $filter("date")(vm.fromDate, "dd-MMM-yyyy"),
                                               'toDate': $filter("date")(vm.toDate, "dd-MMM-yyyy"),


                                  			}

                                  netBankingTransactionforallholdersreportpdfService.getDetails(requestData).then(function(response) {
                                      vm.loader = false



                                   if(flag ==  'S'){

                                        vm.schemeList = response.data.result.torchValue;
                                      angular.element('#schemeModel').modal('toggle');
                                   }

                                   else if(flag ==  'P'){

                                   vm.PanTorch = response.data.result.torchValue;
                                  angular.element('#panModel').modal('toggle');

                                    }

                                     else if(flag ==  'B'){


                                       vm.blaDataList = response.data.result.torchValue;
                                  angular.element('#blaModel').modal('toggle');


                                  }


                                   else if(flag ==  'BR'){

                                     vm.subscriptionList = response.data.result.torchValue;
                                  angular.element('#subscriptionModel').modal('toggle');




                                    }


                                    else if(flag ==  'D'){

                                     vm.detailsList = response.data.result.torchValue;


                                    }




                         }, function(error){
                             vm.loader = false;
                             if(error.data.message == 'No Data Found' | error.data.message == 'NDF'){
                                   vm.noDataFound = error.data.message;
                             }else{
                               handleErrorResponse(error);
                               $state.go('Net_Banking_Transaction_for_all_holders_report_pdf.inquiry');
                             }

                         })
                     }


                                        	vm.dateToOptions = {

                                             				maxDate: vm.solDate
                                             			};

                                             				vm.dateToOptionss = {
                                                                     	minDate: vm.fromDate,
                                                                        maxDate: vm.solDate
                                                                 	};


//
//                                                     function getModuleName() {
//                                                                vm.loader = true;
//
//                                                                var requestData = {
//                                                                };
//                                                                netBankingTransactionforallholdersreportpdfService.getRbiBondModuleNameDetails(requestData).then(function (response) {
//                                                                    vm.loader = false;
//                                                                    vm.schemeList = response.data.result;
//                                                                    angular.element('#schemeModel').modal('toggle');
//                                                                }, function (error) {
//                                                                    handleErrorResponse(error);
//                                                                    vm.loader = false;
//                                                                });
//                                                            }







         function downloadAllPdfFiles() {
             angular.forEach(vm.detailsList, function(item) {
                 // Assuming each item in vm.detailsList has a subscriptionId
                 var subscriptionId = item.subscriptionId;

                 // Call the function to download the PDF file for each subscriptionId
                 downloadPdfFile(subscriptionId);
             });


              $state.go('Net_Banking_Transaction_for_all_holders_report_pdf.inquiry');
         }



vm.indianFormat = function (input) {
       if (!input) return "";

        // Convert input to a number
        var num = parseFloat(input);
        if (isNaN(num)) return input;
        // Format number in Indian style
      return num.toLocaleString('en-IN', {
        minimumFractionDigits: 2,
        maximumFractionDigits: 2
    });
        };

                 function downloadPdfFile(subscriptionId,bla){
                                       vm.loader = true;
                                       vm.date = $rootScope.formatDateToDDMMMYYYY(vm.date);
                                                  var requestData = {
                                                                 'flag' : 'Z',
                                                              'subscriptionId': subscriptionId,
                                                              'schemeCode': vm.schemeCode,
                                                                'blaNo': bla,
                                                                'branchName' :  vm.loginSolDesc,


                                                  			}

                                                  netBankingTransactionforallholdersreportpdfService.getDetails(requestData).then(function(response) {
                                                      vm.loader = false
                                                //   vm.detailsList = response.data.result;
                                                    vm.loader = false;
                                                                   vm.fileData = response.data.result.fileData;
                                                                   vm.fileName = response.data.result.fileName;
                                                                   saveAs(getBlobFromBase64String(vm.fileData), vm.fileName)
                                                                    $state.go('Net_Banking_Transaction_for_all_holders_report_pdf.inquiry_details');



                                         }, function(error){
                                             vm.loader = false;
                                             if(error.data.message == 'No Data Found' | error.data.message == 'NDF'){
                                                   vm.noDataFound = error.data.message;
                                             }else{
                                               handleErrorResponse(error);
                                               $state.go('Net_Banking_Transaction_for_all_holders_report_pdf.inquiry_details');
                                             }

                                         })
                                     }






 function printpage() {

            window.print();
            document.title = 'Government Business Suite'
        }















// function getPantorch() {
//      var requestData = {
//        schemeCode: vm.schemeCode,
//        bondHolderName: vm.bondHolderName,
//        panNum: vm.panNum,
//      };
//      netBankingTransactionforallholdersreportpdfService.panTorch(requestData).then(
//        function (response) {
//          vm.loader = false;
//          //                                                       vm.PanTorch = response.data.result;
//          vm.PanTorch = response.data.result.torchValue;
//          angular.element("#panModel").modal("toggle");
//        },
//        function (error) {
//          vm.loader = false;
//          handleErrorResponse(error);
//        }
//      );
//    }




//	 function getblaNoInq() {
//      vm.loader = true;
//
//      var requestData = {
//        schemeCode: vm.schemeCode,
//        schemeName: vm.schemeName,
//        panNum: vm.panNum,
//        bondHolderName: vm.bondHolderName,
//        blaNo: vm.blaNo,
//      };
//      netBankingTransactionforallholdersreportpdfService.getblaNoInq(requestData).then(
//        function (response) {
//          vm.loader = false;
//          vm.blaDataList = response.data.result.torchValue;
//          angular.element("#blaModel").modal("toggle");
//        },
//        function (error) {
//          vm.loader = false;
//          handleErrorResponse(error);
//        }
//      );
//    }





//	    function getsubscriptionIdInq() {
//      vm.loader = true;
//
//      var requestData = {
//        schemeCode: vm.schemeCode,
//        schemeName: vm.schemeName,
//        panNum: vm.panNum,
//        bondHolderName: vm.bondHolderName,
//        blaNo: vm.blaNo,
//        subscriptionId: vm.subscriptionId,
//      };
//      netBankingTransactionforallholdersreportpdfService.getsubscriptionIdInq(requestData).then(
//        function (response) {
//          vm.loader = false;
//          vm.subscriptionList = response.data.result.torchValue;
//
//          angular.element("#subscriptionModel").modal("toggle");
//        },
//        function (error) {
//          vm.loader = false;
//          handleErrorResponse(error);
//        }
//      );
//    }





	}
})();