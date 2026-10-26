(function() {

	'use strict';

	angular.module('gbmUltraApp').controller(
			'rbibondReversalChangeAccNoController',
			rbibondReversalChangeAccNoController);

	rbibondReversalChangeAccNoController.$inject = [ '$scope', '$filter',
			'$state', '$stateParams', '$location', '$rootScope','rbibondReversalChangeAccNoService', 'commonService' ];

	function rbibondReversalChangeAccNoController($scope, $filter, $state,
			$stateParams, $location, $rootScope, rbibondReversalChangeAccNoService,
			commonService) {

		var vm = this;

        vm.currentPage = 1;
        vm.itemsPerPage = '5';
        vm.loginSolId = $rootScope.globals.currentUser.solId;
        vm.loginSolDesc = $rootScope.globals.currentUser.solDesc;
        vm.solDate = $rootScope.globals.currentUser.systemDate;
        vm.currentDate = new Date();
        vm.currentDate = $filter('date')(vm.currentDate, 'dd/MM/yyyy');
        vm.getInquiryDetails = getInquiryDetails;
        vm.getModuleName=getModuleName;
        vm.getBlaAccNo=getBlaAccNo;
        vm.printpage = printpage;
        vm.getSubscriptionId = getSubscriptionId;
        vm.submitAddForm = submitAddForm;
        vm.getVerifyDetails = getVerifyDetails;
        vm.getVerifyReversalId = getVerifyReversalId;
        vm.getDeleteReversalId = getDeleteReversalId;
        vm.submitVerifyForm = submitVerifyForm;
        vm.getDeleteDetails = getDeleteDetails;
        vm.submitDeleteForm = submitDeleteForm;
        vm.getBlaAccNoTo= getBlaAccNoTo;
        vm.getReversalId = getReversalId;
        vm.getAccTranSameNo = getAccTranSameNo;
        vm.convertNumberToWords = convertNumberToWords;
        vm.showDetailBtn = showDetailBtn;
        vm.showDetails = false;
        vm.commonYesNoMetaData = COMMON_YES_NO_METADATA;
        vm.showCancelConfirmation= showCancelConfirmation;

        if ($state.current.name == 'bond_subs_reversal_change_acc_no.inquiry_detail'){
                    if ($stateParams.obj) {
                         vm.schemeCode = $stateParams.obj.schemeCode;
                         vm.reversalId = $stateParams.obj.reversalId;
                         vm.schemeName = $stateParams.obj.schemeName;
                                         getInquiryDetails();

                    } else {
                        $state.go('bond_subs_reversal_change_acc_no.inquiry_detail');
                    }
                    }

 else if ($state.current.name == 'bond_subs_reversal_change_acc_no.verify_detail'){
                    if ($stateParams.obj) {
                         vm.schemeCode = $stateParams.obj.schemeCode;
                         vm.reversalId = $stateParams.obj.reversalId;
                         vm.schemeName = $stateParams.obj.schemeName;
                            getVerifyDetails();

                    } else {
                        $state.go('bond_subs_reversal_change_acc_no.verify_detail');
                    }
                    }

  else if ($state.current.name == 'bond_subs_reversal_change_acc_no.delete_detail'){
                      if ($stateParams.obj) {
                           vm.schemeCode = $stateParams.obj.schemeCode;
                           vm.reversalId = $stateParams.obj.reversalId;
                           vm.schemeName = $stateParams.obj.schemeName;
                                           getDeleteDetails();

                      } else {
                          $state.go('bond_subs_reversal_change_acc_no.delete_detail');
                      }
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
//                 'referenceId': vm.referenceId
             };
//             rbibondReversalChangeAccNoService.getRbiBondModuleNameDetails(requestData).then(function(response) {
//                     vm.loader = false;
//                     vm.schemeList = response.data.result;
//                     vm.moduleNameDetailsLabelValue = [];
//                     angular.forEach(vm.schemeList, function(item) {
//                         vm.moduleNameDetailsLabelValue.push({
//                             label: item.subCodeDescription,
//                             value: item.subCode
//                         })
//                     })
//                     angular.element('#schemeModel').modal('toggle');
//                 },
//                 function(error) {
//                     handleErrorResponse(error);
//                     vm.loader = false;
//                 });
rbibondReversalChangeAccNoService.getDetails(requestData)
                    .then(function(response) {
                        vm.loader= false;
                        vm.schemeList= response.data.result;
                        vm.moduleNameDetailsLabelValue = [];
                                             angular.forEach(vm.schemeList, function(item) {
                                                 vm.moduleNameDetailsLabelValue.push({
                                                     label: item.subCodeDescription,
                                                     value: item.subCode
                                                 })
                                             })
                                             angular.element('#schemeModel').modal('toggle');
                    }, function(error) {
                        handleErrorResponse(error);
                        vm.loader= false;
                    })
         }

        function printpage(){
                            window.print()
                          }

vm.indianNumberFormat=indianNumberFormat;
                function indianNumberFormat(value) {
                  if (isNaN(value)|| null == value) return value;
                  let numStr = value.toString();
                  let [whole, decimal] = numStr.split('.');
                  if (whole.length <= 3) {
                      return decimal ? whole + '.' + decimal : whole + '.00';
                    }
                  let lastThree = whole.slice(-3);  // last three digits
                  let otherNumbers = whole.slice(0, whole.length - 3);
                  if (otherNumbers !== '') {
                    lastThree = ',' + lastThree;
                  }
                  let formattedWhole = otherNumbers.replace(/\B(?=(\d{2})+(?!\d))/g, ',') + lastThree;
                if (decimal) {
                    return formattedWhole + '.' + decimal;
                  } else {
                    return formattedWhole+'.00';
                  }
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
                                        'flag'       : 'B',
                                        'schemeCode' : vm.schemeCode,
                                        'fromBlaNo'  : vm.blaAccNo,
                                    }
                                    console.log("Payload ",requestData);
                                    rbibondReversalChangeAccNoService.getDetails(requestData).then(function(response) {
                                        vm.loader = false;
                                        vm.blaAccNoList = response.data.result;
                                      //vm.tocSlabListEffctDate=response.data.result.effeFromDateList;

                                        angular.element('#blaAccNoModal').modal('toggle');
                                    }, function(error) {
                                        handleErrorResponse(error);
                                        vm.loader = false;
                                    });
                                }
        function getSubscriptionId() {
                                        vm.loader = true;
                                        var requestData = {
                                            'flag'      : 'S',
                                            'schemeCode': vm.schemeCode,
                                            'fromBlaNo' : vm.blaAccNo,
                                        }
                                        rbibondReversalChangeAccNoService.getDetails(requestData).then(function(response) {
                                            vm.loader = false;
                                            vm.subscriptionIdList = response.data.result;
                                          //vm.tocSlabListEffctDate=response.data.result.effeFromDateList;
                                            angular.element('#subScriptionIdModal').modal('toggle');
                                        }, function(error) {
                                            handleErrorResponse(error);
                                            vm.loader = false;
                                        });
                                     }
         function getBlaAccNoTo() {
                                             vm.loader = true;

                                             var requestData = {
                                                'flag'           : 'FB',
                                                'fromBlaNo'      : vm.blaAccNo,
                                                'schemeCode'     : vm.schemeCode,
                                                'subscriptionId' : vm.bondScbId,
                                                'toBlaNo'        : vm.blaAccNoTo,
                                             }
                                             rbibondReversalChangeAccNoService.getDetails(requestData).then(function(response) {
                                                 vm.loader = false;
                                                 vm.blaAccNoToList = response.data.result;
                                                 //                       vm.tocSlabListEffctDate=response.data.result.effeFromDateList;

                                                 angular.element('#blaAccNoToModal').modal('toggle');
                                             }, function(error) {
                                                 handleErrorResponse(error);
                                                 vm.loader = false;
                                             });
                                         }
function submitAddForm() {
//            Swal.fire({
//                 title: "Are you sure?",
//                 text: "Do you want to Really Add this record?",
//                 type: "warning",
//                 showCancelButton: true,
//                 cancelButtonClass: "btn-cancel",
//                 confirmButtonClass: "btn-primary",
//                 confirmButtonText: "Yes, Add it!",
//                 closeOnConfirm: false,
//                 showLoaderOnConfirm: true
//           },
//               function(){




                Swal.fire({
                  title: "Are you sure?",
                  text: "Do you want to Really Add this record?",
                  icon: "warning", // use "icon", not "type"
                  showCancelButton: true,
                  confirmButtonText: "Yes",
                  cancelButtonText: "Cancel",
                  customClass: {
                    confirmButton: "btn-primary",
                    cancelButton: "btn-cancel"
                  },
                  buttonsStyling: false
                }).then((result) => {

                       if (result.isConfirmed) {
                   var requestData = {
                       'schemeCode' : vm.schemeCode,
                       'fromBlaNo' : vm.blaAccNo,
                       'subscriptionId' : vm.bondScbId,
                       'subscriptionDate' : vm.subscriptionDate,
                       'subscriptionAmt' : vm.subscriptionAmt,
                       'toBlaNo' : vm.blaAccNoTo,
                       'remarks' : vm.remarks,
                   };
                   rbibondReversalChangeAccNoService.getAddSubmit(requestData).then(
                                               (response) => {
                                                   vm.loader = false;

                                                   vm.responseResult = response.data.result.reversalId;
                                                   var message = "Reversal Transaction  Added Successfully\nReversal ID: " +  vm.responseResult;
                                                   Swal.fire("Success", message, "success");
                                                   location.href = '#!/bond_subs_reversal_change_acc_no/action/inquiry';
                                               },
                                               function(error) {
                                                   vm.loader = false;
                                                   if(error.status =='400')
                                                   {
                                                       Swal.fire("Warning", error.message, "warning");
                                                   }else{
                                                       handleErrorResponse(error);
                                                   }
                                               });
                                               }

                   });
            }
 function getReversalId() {
                        vm.loader = true;

                        var requestData = {
                            'schemeCode': vm.schemeCode


                        }
                        rbibondReversalChangeAccNoService.getInquiry(requestData).then(function(response) {
                            vm.loader = false;
                            vm.reversalIdList = response.data.result;
                            //                       vm.tocSlabListEffctDate=response.data.result.effeFromDateList;

                            angular.element('#reversalIdModal').modal('toggle');
                        }, function(error) {
                            handleErrorResponse(error);
                            vm.loader = false;
                        });
                    }

    function getVerifyReversalId() {
                            vm.loader = true;

                            var requestData = {
                                'schemeCode': vm.schemeCode
                            }
                            rbibondReversalChangeAccNoService.getVerify(requestData).then(function(response) {
                                vm.loader = false;
                                vm.reversalIdList = response.data.result;
                                //                       vm.tocSlabListEffctDate=response.data.result.effeFromDateList;

                                angular.element('#reversalIdModal').modal('toggle');
                            }, function(error) {
                                handleErrorResponse(error);
                                vm.loader = false;
                            });
                        }

     function getDeleteReversalId() {
                                vm.loader = true;

                                var requestData = {
                                    'schemeCode': vm.schemeCode


                                }
                                rbibondReversalChangeAccNoService.getDelete(requestData).then(function(response) {
                                    vm.loader = false;
                                    vm.reversalIdList = response.data.result;
                                    //                       vm.tocSlabListEffctDate=response.data.result.effeFromDateList;

                                    angular.element('#reversalIdModal').modal('toggle');
                                }, function(error) {
                                    handleErrorResponse(error);
                                    vm.loader = false;
                                });
                            }

         function getInquiryDetails(){
                       vm.loader = true;
                                  var requestData = {
                                      'schemeCode':vm.schemeCode,
                                      'reversalId': vm.reversalId


                                  }

                                  rbibondReversalChangeAccNoService.getInquiry(requestData).then(function(response) {
                                      vm.loader = false;
                                      vm.auditInfo = response.data.result;

                                       vm.dataList =vm.auditInfo.cursorOut[0];

                              configDataTable();
                         }, function(error){
                             vm.loader = false;

            if (error.data.message == 'No Data Found') {
                    vm.noDataFound = error.data.message;
                } else {
                    handleErrorResponse(error);
                    $state.go('bond_subs_reversal_change_acc_no.inquiry')
                }

                         })
                     }


          function getVerifyDetails(){
                                vm.loader = true;
                                          var requestData = {
                                              'schemeCode':vm.schemeCode,
                                              'reversalId': vm.reversalId
                                          }

                                           rbibondReversalChangeAccNoService.getVerify(requestData).then(function(response) {
                                               vm.loader = false;
                                               vm.auditInfo = response.data.result;

                                                vm.verifyList =vm.auditInfo.cursorOut[0];

                                       configDataTable();
                                  }, function(error){
                                      vm.loader = false;

                     if (error.data.message == 'No Data Found') {
                             vm.noDataFound = error.data.message;
                         } else {
                             handleErrorResponse(error);
                             $state.go('bond_subs_reversal_change_acc_no.inquiry')
                         }
                                  })
                              }

    function submitVerifyForm() {
//                Swal.fire({
//                        title: "Are you sure?",
//                        text: "Do you want to Really Verify this record?",
//                        type: "warning",
//                        showCancelButton: true,
//                        cancelButtonClass: "btn-cancel",
//                        confirmButtonClass: "btn-primary",
//                        confirmButtonText: "Yes",
//                        closeOnConfirm: false
//                    },
//
//                    function() {

                    Swal.fire({
                      title: "Are you sure?",
                      text: "Do you want to Really Verify this record?",
                      icon: "warning", // use "icon", not "type"
                      showCancelButton: true,
                      confirmButtonText: "Yes",
                      cancelButtonText: "Cancel",
                      customClass: {
                        confirmButton: "btn-primary",
                        cancelButton: "btn-cancel"
                      },
                      buttonsStyling: false
                    }).then((result) => {

                           if (result.isConfirmed) {
                        vm.loader = true;
                        let requestData = {
                           'schemeCode': vm.schemeCode,
                           'reversalId': vm.reversalId,
                           'remarks'   : vm.verifyList.remarks,
                        }
                        rbibondReversalChangeAccNoService.getVerifySubmit(requestData).then(
                            (response) => {
                                vm.loader = false;
                                vm.responseResult = response.data.result.reversalId;
                              var message = "Reversal Transaction Verified  Successfully\nReversal ID: " +  vm.responseResult;
                                     Swal.fire("Success", message, "success");
                                     location.href = '#!/bond_subs_reversal_change_acc_no/action/inquiry';
                            },
                            function(error) {
                                vm.loader = false;
                                if(error.status =='400')
                                {
                                    Swal.fire("Warning", error.message, "warning");
                                }else{
                                    handleErrorResponse(error);
                                }

                            });

                            }
                    });

            }

  vm.rejectReason=function () {
      angular.element("#permissionMappingDelete").modal("toggle");
    }

vm.getRemarksList= function () {
                               vm.referenceId = '056';
                               var requestData = { 'referenceId': vm.referenceId };

                               rbibondReversalChangeAccNoService.getRemarksList(requestData).then(function(response) {
                                   vm.remarksList = response.data.result || []; // Ensure it's an array
                               }, function(error) {
                                   handleErrorResponse(error);
                               });
                           }

 function getDeleteDetails(){
                                vm.loader = true;
                                           var requestData = {
                                               'schemeCode':vm.schemeCode,
                                               'reversalId': vm.reversalId
                                           }

                                           rbibondReversalChangeAccNoService.getDelete(requestData).then(function(response) {
                                               vm.loader = false;
                                               vm.auditInfo = response.data.result;

                                               vm.verifyList =vm.auditInfo.cursorOut[0];

                                       configDataTable();
                                  }, function(error){
                                      vm.loader = false;

                     if (error.data.message == 'No Data Found') {
                             vm.noDataFound = error.data.message;
                         } else {
                             handleErrorResponse(error);
                             $state.go('bond_subs_reversal_change_acc_no.inquiry')
                         }

                                  })
                              }
     function submitDeleteForm() {
//                     Swal.fire({
//                             title: "Are you sure?",
//                             text: "Do you want to Really Reject this record?",
//                             type: "warning",
//                             showCancelButton: true,
//                             cancelButtonClass: "btn-cancel",
//                             confirmButtonClass: "btn-primary",
//                             confirmButtonText: "Yes",
//                             closeOnConfirm: false
//                         },
//
//                         function() {




               Swal.fire({
                 title: "Are you sure?",
                 text: "Do you want to Really Reject this record?",
                 icon: "warning", // use "icon", not "type"
                 showCancelButton: true,
                 confirmButtonText: "Yes",
                 cancelButtonText: "Cancel",
                 customClass: {
                   confirmButton: "btn-primary",
                   cancelButton: "btn-cancel"
                 },
                 buttonsStyling: false
               }).then((result) => {

                      if (result.isConfirmed) {
                             vm.loader = true;
                             let requestData = {
                                'schemeCode':vm.schemeCode,
                                'reversalId': vm.reversalId,
                                'remarks':vm.remarks
                             }
                             rbibondReversalChangeAccNoService.getDeleteSubmit(requestData).then(
                                 (response) => {
                                     vm.loader = false;
                                     vm.responseResult = response.data.result.reversalId;
                                   var message = "Reversal Transaction  Rejected  Successfully\nReversal ID: " +  vm.responseResult;
                                         Swal.fire("Success", message, "success");
                                          location.href = '#!/bond_subs_reversal_change_acc_no/action/inquiry';
                                 },
                                 function(error) {
                                     vm.loader = false;
                                     if(error.status =='400')
                                     {
                                         Swal.fire("Warning", error.message, "warning");
                                     }else{
                                         handleErrorResponse(error);
                                     }

                                 });
                                 }
                         });

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


     // Cancel Confirmation
             function showCancelConfirmation(state) {
//                 Swal.fire({
//                     title: "Confirm Cancel",
//                     text: "Do you really want to cancel?",
//                     type: "warning",
//                     showCancelButton: true,
//                     cancelButtonClass: "btn-cancel",
//                     confirmButtonClass: "btn-danger",
//                     confirmButtonText: "Yes",
//                     cancelButtonText: "No",
//                     closeOnConfirm: true
//                 },
//                 function() {



                    Swal.fire({
                      title: "Are you sure?",
                      text: "Do you Really Want to Cancel?",
                      icon: "warning", // use "icon", not "type"
                      showCancelButton: true,
                      confirmButtonText: "Yes",
                      cancelButtonText: "Cancel",
                      customClass: {
                        confirmButton: "btn-primary",
                        cancelButton: "btn-cancel"
                      },
                      buttonsStyling: false
                    }).then((result) => {

                           if (result.isConfirmed) {
                     $state.go(state);



                     }
                 });
              }


	}
})();