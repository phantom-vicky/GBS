(function() {

	'use strict';

	angular.module('gbmUltraApp').controller(
			'rbibondTranSameBankController',
			rbibondTranSameBankController);

	rbibondTranSameBankController.$inject = [ '$scope', '$filter',
			'$state', '$stateParams', '$location', '$rootScope','rbibondTranSameBankService', 'commonService' ];

	function rbibondTranSameBankController($scope, $filter, $state,
			$stateParams, $location, $rootScope, rbibondTranSameBankService,
			commonService) {

		var vm = this;

        vm.currentPage = 1;
        vm.itemsPerPage = '5';
        vm.loginSolId = $rootScope.globals.currentUser.solId;
        vm.loginSolDesc = $rootScope.globals.currentUser.solDesc;
        vm.solDate = $rootScope.globals.currentUser.systemDate;
        vm.currentDate = new Date();
        vm.currentDate = $filter('date')(vm.currentDate, 'dd/MM/yyyy');
        vm.getTransSameBankDetail = getTransSameBankDetail;
        vm.getModuleName=getModuleName;
        vm.getBlaAccNo=getBlaAccNo;
        vm.printpage = printpage;
        vm.getSubscriptionId = getSubscriptionId;
        vm.getAccTranSameNo = getAccTranSameNo;
        vm.convertNumberToWords = convertNumberToWords;
        vm.showDetailBtn = showDetailBtn;
        vm.showDetails = false;

         if ($state.current.name == 'rbibond_transfer_same_bank_report.inquiry_details')
                {
                vm.schemeCode = $stateParams.obj.schemeCode;
                vm.accTranFrom = $stateParams.obj.accTransBranchId;
                vm.blaAccNo = $stateParams.obj.blaNo;
                vm.bondScbId = $stateParams.obj.bondSubscriptionId;
                vm.branchName = $stateParams.obj.branchName;
                             getTransSameBankDetail();

                }


        function configDataTable() {
                    vm.getTitleDescription = {
                        title: 'Bond Nomination Register',
                        solId: $rootScope.globals.currentUser.solId,
                        solDesc: $rootScope.globals.currentUser.solDesc
                    }
                    vm.dataTableOption = configDataTableOptions(vm.getTitleDescription);
                }


                function showDetailBtn(){
                    vm.showDetails = !vm.showDetails;
                }


 function getModuleName() {
            vm.loader = true;
            vm.referenceId = 'TAX';
            var requestData = {
                'referenceId': vm.referenceId
            };
            rbibondTranSameBankService.getRbiBondModuleNameDetails(requestData).then(function (response) {
                vm.loader = false;
                vm.schemeList = response.data.result;
            }, function (error) {
                handleErrorResponse(error);
                vm.loader = false;
            });
        }

        function printpage(){
                            window.print()
                          }


       function getAccTranSameNo() {
                                           vm.loader = true;

                                           var requestData = {
                                                'schemeCode':vm.schemeCode

                                           }
                                           rbibondTranSameBankService.getDetails(requestData).then(function(response) {
                                               vm.loader = false;
                                               vm.accTransList = response.data.result;
                                               angular.element('#branchIdModal').modal('toggle');
                                           }, function(error) {
                                               handleErrorResponse(error);
                                               vm.loader = false;
                                           });
                                       }
        function getBlaAccNo() {
                                    vm.loader = true;

                                    var requestData = {
                                        'schemeCode': vm.schemeCode,
                                         'accTransBranchId': vm.accTranFrom

                                    }
                                    rbibondTranSameBankService.getDetails(requestData).then(function(response) {
                                        vm.loader = false;
                                        vm.blaAccNoList = response.data.result;
                                        //                       vm.tocSlabListEffctDate=response.data.result.effeFromDateList;

                                        angular.element('#blaAccNoModal').modal('toggle');
                                    }, function(error) {
                                        handleErrorResponse(error);
                                        vm.loader = false;
                                    });
                                }
        function getSubscriptionId() {
                                                            vm.loader = true;

                                                            var requestData = {
                                                                'schemeCode': vm.schemeCode,
                                                                'accTransBranchId': vm.accTranFrom,
                                                                'blaNo' : vm.blaAccNo


                                                            }
                                                            rbibondTranSameBankService.getDetails(requestData).then(function(response) {
                                                                vm.loader = false;
                                                                vm.subscriptionIdList = response.data.result;
                                                                //                       vm.tocSlabListEffctDate=response.data.result.effeFromDateList;

                                                                angular.element('#subScriptionIdModal').modal('toggle');
                                                            }, function(error) {
                                                                handleErrorResponse(error);
                                                                vm.loader = false;
                                                            });
                                                        }
         function getTransSameBankDetail(){
                       vm.loader = true;
                                  var requestData = {
                                      'schemeCode':vm.schemeCode,
                                      'accTransBranchId': vm.accTranFrom,
                                      'blaNo' : vm.blaAccNo,
                                       'bondSubscriptionId' : vm.bondScbId

                                  }

                                  rbibondTranSameBankService.getDetails(requestData).then(function(response) {
                                      vm.loader = false;

                                       vm.model =response.data.result;

//                                        if(vm.model.torchOne[0].subscriptionAmt)
//                                                     {
//                                                   vm.modelAmount = convertNumberToWords(vm.model.torchOne[0].subscriptionAmt);
//                                                   }
//                                                   else{
//                                                         vm.modelAmount = convertNumberToWords(0);
//
//                                                   }

                              configDataTable();
                         }, function(error){
                             vm.loader = false;
                             //Swal.fire
//                            Swal.fire("No Data Found", msg, "success");
            if (error.data.message == 'No Data Found') {
                    vm.noDataFound = error.data.message;
                } else {
                    handleErrorResponse(error);
                    $state.go('rbibond_transfer_same_bank_report.inquiry')
                }

                         })
                     }
      function convertNumberToWords(subscriptionAmt) {
                                                                  var words = new Array();
                                                                  words[0] = 'Zero';
                                                                  words[1] = 'One';
                                                                  words[2] = 'Two';
                                                                  words[3] = 'Three';
                                                                  words[4] = 'Four';
                                                                  words[5] = 'Five';
                                                                  words[6] = 'Six';
                                                                  words[7] = 'Seven';
                                                                  words[8] = 'Eight';
                                                                  words[9] = 'Nine';
                                                                  words[10] = 'Ten';
                                                                  words[11] = 'Eleven';
                                                                  words[12] = 'Twelve';
                                                                  words[13] = 'Thirteen';
                                                                  words[14] = 'Fourteen';
                                                                  words[15] = 'Fifteen';
                                                                  words[16] = 'Sixteen';
                                                                  words[17] = 'Seventeen';
                                                                  words[18] = 'Eighteen';
                                                                  words[19] = 'Nineteen';
                                                                  words[20] = 'Twenty';
                                                                  words[30] = 'Thirty';
                                                                  words[40] = 'Forty';
                                                                  words[50] = 'Fifty';
                                                                  words[60] = 'Sixty';
                                                                  words[70] = 'Seventy';
                                                                  words[80] = 'Eighty';
                                                                  words[90] = 'Ninety';
                                                                  subscriptionAmt = subscriptionAmt.toString();
                                                                  var atemp = subscriptionAmt.split(".");
                                                                  var number = atemp[0].split(",").join("");
                                                                  var n_length = number.length;
                                                                  var words_string = "";
                                                                  var paisa_string = "";
                                                                  if(atemp.length>1){
                                                                      var paisa = atemp[1].split(",").join("");
                                                                      var paisa_length = paisa.length;
                                                                  } else {
                                                                     paisa_string += words[0] + " ";
                                                                  }

                                                                  if (n_length <= 9) {
                                                                      var n_array = new Array(0, 0, 0, 0, 0, 0, 0, 0, 0);
                                                                      var received_n_array = new Array();
                                                                      for (var i = 0; i < n_length; i++) {
                                                                          received_n_array[i] = number.substr(i, 1);
                                                                      }
                                                                      for (var i = 9 - n_length, j = 0; i < 9; i++, j++) {
                                                                          n_array[i] = received_n_array[j];
                                                                      }
                                                                      for (var i = 0, j = 1; i < 9; i++, j++) {
                                                                          if (i == 0 || i == 2 || i == 4 || i == 7) {
                                                                              if (n_array[i] == 1) {
                                                                                  n_array[j] = 10 + parseInt(n_array[j]);
                                                                                  n_array[i] = 0;
                                                                              }
                                                                          }
                                                                      }
                                                                      let value = "";
                                                                      for (var i = 0; i < 9; i++) {
                                                                          if (i == 0 || i == 2 || i == 4 || i == 7) {
                                                                              value = n_array[i] * 10;
                                                                          } else {
                                                                              value = n_array[i];
                                                                          }
                                                                          if (value != 0) {
                                                                              words_string += words[value] + " ";
                                                                          }  if (value == '0' && i == 8){
                                                                            words_string += words[value] + " ";
                                                                          }
                                                                          if ((i == 1 && value != 0) || (i == 0 && value != 0 && n_array[i + 1] == 0)) {
                                                                              words_string += "Crores ";
                                                                          }
                                                                          if ((i == 3 && value != 0) || (i == 2 && value != 0 && n_array[i + 1] == 0)) {
                                                                              words_string += "Lakhs ";
                                                                          }
                                                                          if ((i == 5 && value != 0) || (i == 4 && value != 0 && n_array[i + 1] == 0)) {
                                                                              words_string += "Thousand ";
                                                                          }
                                                                          if (i == 6 && value != 0 && (n_array[i + 1] != 0 && n_array[i + 2] != 0)) {
                                                                              words_string += "Hundred and ";
                                                                          } else if (i == 6 && value != 0) {
                                                                              words_string += "Hundred ";
                                                                          }
                                                                      }
                                                                      words_string = words_string.split("  ").join(" ");

                                                                  }
                                                                  if(paisa_length < 3){
                                                                  var n_array = new Array(0, 0, 0, 0, 0, 0, 0, 0, 0);
                                                                   var received_n_array = new Array();
                                                                      for (var i = 0; i < paisa_length; i++) {
                                                                          received_n_array[i] = paisa.substr(i, 1);
                                                                      }
                                                                      for (var i = 9 - paisa_length, j = 0; i < 9; i++, j++) {
                                                                          n_array[i] = received_n_array[j];
                                                                      }
                                                                      for (var i = 0, j = 1; i < 9; i++, j++) {
                                                                          if (i == 0 || i == 2 || i == 4 || i == 7) {
                                                                              if (n_array[i] == 1) {
                                                                                  n_array[j] = 10 + parseInt(n_array[j]);
                                                                                  n_array[i] = 0;
                                                                              }

                                                                          }
                                                                      }
                                                                      let value = "";
                                                                      for (var i = 0; i < 9; i++) {
                                                                          if (i == 0 || i == 2 || i == 4 || i == 7) {
                                                                              value = n_array[i] * 10;
                                                                          } else {
                                                                              value = n_array[i];
                                                                          }
                                                                          if (value != 0) {
                                                                              paisa_string += words[value] + " ";
                                                                          }
                                                                          else {
                                                                            paisa_string += words[value] + " ";
                                                                          }

                                                                      }
                                                                      paisa_string = paisa_string.split("  ").join(" ");
                                                                  }
                                                                      vm.netPaymentInWords = "Rupees  "+words_string+" ONLY";
                                                                     return vm.netPaymentInWords;
                                                              }

	}
})();