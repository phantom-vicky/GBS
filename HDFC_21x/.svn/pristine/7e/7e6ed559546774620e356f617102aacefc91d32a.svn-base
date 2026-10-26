(function() {

    'use strict';

    angular.module('gbmUltraApp').controller('rbibondAckReceiptController',
        rbibondAckReceiptController);

    rbibondAckReceiptController.$inject = ['$scope', '$filter', '$state', '$rootScope',
        '$stateParams', '$location', '$timeout', 'rbiBondAckReceiptService', 'commonService'
    ];

    function rbibondAckReceiptController($scope, $filter, $state, $rootScope, $stateParams,
        $location, $timeout, rbiBondAckReceiptService, commonService) {

		var vm = this;
        vm.getCustomerIdForInquiry=getCustomerIdForInquiry;
        vm.getAddAckDetails=getAddAckDetails;
        vm.getModuleName=getModuleName;
        vm.getBrokerId=getBrokerId;
        vm.validateInstNum = validateInstNum;
        vm.convertNumberToWords = convertNumberToWords;
        vm.currentDate = new Date();
        vm.currentDate = $filter('date')(vm.currentDate, 'dd/MM/yyyy');

        vm.getTransCommonMetaData = getTransCommonMetaData;

         vm.solDate = $rootScope.globals.currentUser.solId;
         vm.bankName = $rootScope.globals.currentUser.bankName;
          vm.loginSolDesc = $rootScope.globals.currentUser.solDesc;
        vm.printpage = printpage;
        vm.tranTypeRefId = "TRANSACTION_TYPE";
vm.textableDatePicker=textableDatePicker;
vm.autoslashing=autoslashing;

function textableDatePicker(id,valueDate)
{
 commonService.textableDatePicker(id,valueDate);
}
function autoslashing(id)
{
 commonService.autoslashing(id);
}




if ($state.current.name == 'rbi_bond_ack_receipt.add_detail'){
            if ($stateParams.obj) {
                 vm.bondAckRec = $stateParams.obj.bondAckRec;
                  getAddAckDetails()

            } else {
                $state.go('rbi_bond_ack_receipt.add_detail');
            }
            }
         else if ($state.current.name == 'rbi_bond_ack_receipt.inquiry_detail'){
            if ($stateParams.obj) {
                 vm.applicationNo = $stateParams.obj.applicationNo;
                 vm.schemeCode= $stateParams.obj.schemeCode;
                 vm.getCustomerIdForInquiry();

            } else {
                $state.go('rbi_bond_ack_receipt.inquiry_detail');
            }
            }


              vm.transactionModeData = [
                        {label : "Cash",value : "C"},
                        {label : "Transfer",value : "T"},
                        {label : "Clearing",value : "CL"},

                    ];

            function getAddAckDetails() {


                             var requestData = vm.bondAckRec;
                             requestData.delDate=vm.bondAckRec.delDate;


                       rbiBondAckReceiptService.getAddDetails(requestData).then(function(response){
                              vm.bondAckAddList = response.data.result;
                             vm.bondAckRec.delDate = moment(vm.bondAckRec.delDate).format("DD/MM/YYYY")
                                 vm.bondAckRec.bondHolderName=vm.bondAckAddList.bondHolderName.charAt(0).toUpperCase()+vm.bondAckAddList.bondHolderName.substring(1).toLowerCase();

                              if(vm.bondAckRec.amount){
                              vm.bondAckAddListAmount=convertNumberToWords(vm.bondAckRec.amount)
                               }else{
                               vm.bondAckAddListAmount = convertNumberToWords(0);
                               }
//             angular.element('#customerIdModalInquiry').modal('toggle');
                        },function(error) {
                           handleErrorResponse(error);
                           location.href = "#!/rbi_bond_ack_receipt/add"
                                             vm.loader = false;
                         });
                    }
                    function convertNumberToWords(amount) {
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
                                                            amount = amount.toString();
                                                            var atemp = amount.split(".");
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



        function getTransCommonMetaData(){
            var requestData = {
                'referenceId': 'RBI_TRAN_MODES'
            }

                       rbiBondAckReceiptService.getReferenceData(requestData).then(function(response){
                          vm.tranDetailList = response.data.result;

                        },function(error) {
                           handleErrorResponse(error);
                         });




        }
        vm.validateAccNumCbsM=validateAccNumCbsM;
    function validateAccNumCbsM(acctNum){

                                if(acctNum){
                                vm.loader = true;
                                commonService.getCustomAcctInqApi(acctNum.toUpperCase(), function (response) {
                        					if (response.success) {
                        						vm.accList = response.data;
                        						vm.loader = false;
                        						if (vm.accList.length > 0) {
                        						if (vm.accList[0].status == 'S') {


                                                    if(vm.accList[0].accountFreezeStatus) {
                                                      vm.cbsErrorMessage = 'Account is Freezed';
                                                      vm.bondAckRec.debitAccNo='';
                                                    }else{
                                                        vm.cbsErrorMessage = '';
                                                        vm.bondAckRec.branchId = vm.accList[0].solID;
                                                    }


                        							}
                        							if (vm.accList[0].status == 'F') {
                        							vm.bondAckRec.debitAccNo='';
                        							vm.bondAckRec.branchId = '';
                        								vm.cbsErrorMessage = 'Invalid Account Number';
                        								}
                        						}
                        						else {
                                                    vm.cbsErrorMessage = 'Invalid Account Number';

                        						}
                        					} else {
                        				vm.bondAckRec.debitAccNo='';
                        						Swal.fire("Error!", response.message, "error");
                        						vm.loader = false;
                        					}
                        				}, function (err) {
                        				   vm.bondAckRec.debitAccNo='';
                        				    vm.loader = false;
                        					handleErrorResponse(err);
                        				});

                                }
                            }


        function getCustomerIdForInquiry() {


                 var requestData = {
"schemeCode" :vm.schemeCode,
"applicationNo" :vm.applicationNo,
                 }
           rbiBondAckReceiptService.getCustomerIdForInquiry(requestData).then(function(response){
              vm.customerIdInquiryList = response.data.result;
//               vm.customerIdInquiryList[0].delDate = moment(vm.customerIdInquiryList[0].delDate).format("DD/MM/YYYY")

              if(vm.customerIdInquiryList[0].amount)
              {
            vm.customerIdInquiryListAmount = convertNumberToWords(vm.customerIdInquiryList[0].amount);
            }
            else{
                  vm.customerIdInquiryListAmount = convertNumberToWords(0);

            }

 angular.element('#customerIdModalInquiry').modal('toggle');
            },function(error) {
               handleErrorResponse(error);
             });
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

            rbiBondAckReceiptService.getRbiBondModuleNameDetails(requestData).then(function (response) {
                vm.loader = false;
                vm.schemeListMetaData = response.data.result;

            }, function (error) {
                handleErrorResponse(error);
                vm.loader = false;
            });
        }
       function getBrokerId()
       {

        vm.loader = true;
        var requestData = {}
        rbiBondAckReceiptService.getBrokerId(requestData).then(function(response){
            vm.loader = false;
            angular.element('#brokerIdModel').modal('toggle');
            vm.brokerIdList = response.data.result;
        }, function(error){
            vm.loader = false;
            handleErrorResponse(error);
        })

       }


       function validateInstNum(insNum,debAcc) {
       			console.log(vm.bondAckRec.InsNo);
       					if(insNum.length >= 6){
       					vm.errorMsgForInstrumentNumber = false;
       					} else {
                               var zeros = "0".repeat(6);
                               vm.bondAckRec.InsNo= (zeros + vm.bondAckRec.InsNo).slice(-1 * 6)
       					}
       			if (vm.bondAckRec.transactionMode == 'T' || vm.bondAckRec.transactionMode == 'C') {
       				commonService.validateInstNum(insNum,debAcc, function (response) {
       					if (response.success) {
       						if (response.data.instrumentStatus != 'S') {
//       							vm.bondAckRec.InsNo = '';

       							vm.errorMsgForInstrumentNumber = response.data.instrumentDescription;
       						} else {
       							vm.errorMsgForInstrumentNumber = '';
       						}
       					} else {
       						$scope.$emit('showAlert', {
       							'type': 'false',
       							'msg': response.message
       						});
       					}
       				});
       			}
       		}

        }
        })();