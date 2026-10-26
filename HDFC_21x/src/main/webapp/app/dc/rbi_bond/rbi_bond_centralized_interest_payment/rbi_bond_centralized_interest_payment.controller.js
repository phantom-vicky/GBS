(function() {

	'use strict';

	angular.module('gbmUltraApp').controller(
			'rbiBondCenInterestPaymentRunController',
			rbiBondCenInterestPaymentRunController);

	rbiBondCenInterestPaymentRunController.$inject = [ '$scope', '$filter',
			'$state', '$stateParams', '$location', '$rootScope','rbiBondCenInterestPaymentRunService', 'commonService' ];

	function rbiBondCenInterestPaymentRunController($scope, $filter, $state,
			$stateParams, $location, $rootScope, rbiBondCenInterestPaymentRunService,
			commonService) {

		var vm = this;
		vm.moduleName = 'RBI Bond';
        vm.commonYesNoMetaData = COMMON_YES_NO_METADATA;
		vm.customerDetailsList = [];
		vm.accountDetailsList = [];
		vm.extensionDetailsList = [];
        vm.currentPage = 1;
        vm.itemsPerPage = '5';
        vm.loginSolId = $rootScope.globals.currentUser.solId;
        vm.loginSolDesc = $rootScope.globals.currentUser.solDesc;
        vm.solDate = $rootScope.globals.currentUser.systemDate;
        vm.getModuleName = getModuleName;
        vm.getTorchDetails = getTorchDetails;
//        vm.actualRunProcess = actualRunProcess;
        vm.optionToggled = optionToggled;
        vm.selectInputVal = selectInputVal;
        vm.selectedCount = 0
        vm.isAllSelected = [];
        vm.getUnverifiedBlaList = getUnverifiedBlaList;
        vm.getUnverifiedDSubsList = getUnverifiedDSubsList;
        vm.getUnverifiedDetailsPage = getUnverifiedDetailsPage;
        vm.isStatusFetchedAfterOneTime = false;
        vm.isTranCompleted = false;
        vm.getInterestPaymentBatchTransaction = getInterestPaymentBatchTransaction;
        vm.getPaymentStatus = getPaymentStatus;
        vm.getInputDetailsPage = getInputDetailsPage;
        vm.getInterestPaymentDetailsPage = getInterestPaymentDetailsPage;
      vm.getInterestPaymentProcess = getInterestPaymentProcess;

//        vm.getHalfYearInterestDetails = getHalfYearInterestDetails;
        getUnverifiedDetails();

         if ($state.current.name == 'rbi_bond_centralized_int_payment.inquiry_detail') {

                if($stateParams.obj){

                    vm.branchId = $stateParams.obj.branchId;
                    vm.blaNo = $stateParams.obj.blaNo;
                    vm.year = $stateParams.obj.year;
                    vm.month = $stateParams.obj.month;
                    vm.moduleId = $stateParams.obj.moduleId;

                        getUnverifiedDetails();
                        vm.getUnverifiedDetailsPage();

            }
        }else {
                         $state.go('rbi_bond_centralized_int_payment.inquiry');
                           getInputDetailsPage();

                     }
//
//        function getHalfYearInterestPaymentDetails(){
//            var requestData = {};
//
//            rbiBondCenInterestPaymentRunService.getValidateDetails(requestData).then(function(response){
//
//            },function(error){
//                handleErrorResponse(error);
//            })
//        }



        function getUnverifiedDetails(){
            vm.loader = true;
             var requestData = {
             'moduleId' : vm.moduleId
             };
             rbiBondCenInterestPaymentRunService.getIntPayUnverifiedRecords(requestData).then(function(response){
                vm.loader = false;
                vm.unverifiedBlaList = response.data.result.blaNumberTorchOut;
                vm.unverifiedSubsList = response.data.result.bondSubsIdTorchOut;

                if(vm.unverifiedBlaList.length >0 || vm.unverifiedSubsList.length >0){
                 vm.unverifiedFlag = "Y";
                }

            }, function(error){
                vm.loader = false;
                handleErrorResponse(error);
                location.href = '#!/rbi_bond_centralized_int_payment/action/inquiry';
            })
        }

        function getUnverifiedDetailsPage(){
        vm.unverifiedDetails = true;
        vm.unverifiedBla = false;
        vm.unverifiedSubs = false;
        }

        function getUnverifiedBlaList(){
        vm.unverifiedDetails = false;
        vm.unverifiedBla = true;
        vm.unverifiedSubs = false;
        }

        function getUnverifiedDSubsList(){
        vm.unverifiedDetails = false;
        vm.unverifiedBla = false;
        vm.unverifiedSubs = true;
        }

          function getInputDetailsPage() {
                    vm.inputDetails = true;
                    vm.interestPaymentDetails = false;
                    vm.isStatusFetchedAfterOneTime = false;
                    vm.isTranCompleted = false;
                }

                function getInterestPaymentDetailsPage() {
                    vm.inputDetails = false;
                    vm.interestPaymentDetails = true;
                    vm.transactionList = []
                }



		   function getModuleName() {
                    vm.loader = true;
                    vm.referenceId = 'TAX';
                    var requestData = {
                        'referenceId': vm.referenceId
                    };
                    rbiBondCenInterestPaymentRunService.getRbiBondModuleNameDetails(requestData).then(function (response) {
                        vm.loader = false;
                        vm.schemeList = response.data.result;
                    }, function (error) {
                        handleErrorResponse(error);
                        vm.loader = false;
                    });
                }

                   function getTorchDetails(flag){
                    vm.loader = true;


                   if(flag == "sol"){

                    var requestData = {
                        'moduleId' : vm.moduleId,
                        'year' : vm.data.year,
                        'interestForHalfYear' : vm.data.interestForHalfYear,
                        'operationType' : flag
                    };
                    rbiBondCenInterestPaymentRunService.getTorchDetails(requestData).then(function (response) {
                        vm.loader = false;
                        vm.branchIdList = response.data.result.branchIdTorchOut;
                        angular.element('#branchSearcher').modal('toggle');

                    }, function (error) {
                        handleErrorResponse(error);
                        vm.loader = false;
                    });
                   }

                   if(flag == "blaNo"){

                       var requestData = {
                       'moduleId' : vm.moduleId,
                       'branchId' : vm.branchId,
                       'year' : vm.data.year,
                       'interestForHalfYear' : vm.data.interestForHalfYear,
                       'operationType' : flag

                       };
                    rbiBondCenInterestPaymentRunService.getTorchDetails(requestData).then(function (response) {
                       vm.loader = false;
                       vm.blaDataList = response.data.result.blaNumberTorchOut;
                       angular.element('#blaModel').modal('toggle');

                       }, function (error) {
                         handleErrorResponse(error);
                          vm.loader = false;
                    });
                  }


                  if(flag == "date"){

                    var requestData = {
                      'moduleId' : vm.moduleId,
                      'operationType' : flag
                    };
                 rbiBondCenInterestPaymentRunService.getTorchDetails(requestData).then(function (response) {
                 vm.loader = false;
                 vm.data = response.data.result;

                  }, function (error) {
                     handleErrorResponse(error);
                     vm.loader = false;
                     });
                 }

                 if(flag == "bondSubsId"){

                  var requestData = {
                 'moduleId' : vm.moduleId,
                 'branchId' : vm.branchId,
                 'blaNo' : vm.blaNo,
                 'year' : vm.data.year,
                 'interestForHalfYear' : vm.data.interestForHalfYear,
                 'operationType' : flag

                   };
                  rbiBondCenInterestPaymentRunService.getTorchDetails(requestData).then(function (response) {
                    vm.loader = false;
                    vm.bondSubsList = response.data.result.bondSubsIdTorchOut;

                    if(vm.bondSubsList){ angular.forEach(vm.bondSubsList, function(item) {
                     item.accountOpenDate = new Date(item.accountOpenDate);
                    })

                    }

                    angular.element('#bondSubsIdSearch').modal('toggle');
                  }, function (error) {
                     handleErrorResponse(error);
                     vm.loader = false;
                  });
              }

        }

                        	function selectInputVal() {
                        	 if (vm.selectedCount == '0' && vm.isAllSelected == '') {
                        		Swal.fire("Please Select At least One Bond Subscription Id")
                        		}
                        	 }

                   function optionToggled(flag) {
                       vm.isAllSelected = [];
                       var bondSubsId = "";
                       vm.selectedCount = 0;

                          if (flag == "ALL") {

                             if(vm.selectedAllItem){
                             angular.forEach(vm.bondSubsList, function (item, index) {
                                 vm.selectedCount = vm.selectedCount + 1;
                                 item.selected = true;
                                 vm.isAllSelected.push(item.selected)
                                 if(index == 0){bondSubsId =  item.bondSubsId;}
                                 else{bondSubsId = bondSubsId + "," + item.bondSubsId;}
                                  });
                                 vm.bondSubsId = bondSubsId;
                             }
                             else{
                             angular.forEach(vm.bondSubsList, function (item) {
                             item.selected = false;
                             });
                             vm.bondSubsId = null;
                             }

				         } else {

                        		angular.forEach(vm.bondSubsList, function (item) {
                        					vm.isAllSelected.push(item.selected)
                        					 if (item.selected) {
                        						vm.selectedCount = vm.selectedCount + 1;
                        						if(bondSubsId==""){
                        						bondSubsId =  item.bondSubsId;
                        						}else{
                        						bondSubsId = bondSubsId + "," + item.bondSubsId;
                        						}
                        					}
                        					vm.bondSubsId = bondSubsId;
                        				});

                        				if (vm.bondSubsList.length == vm.selectedCount) {
                        					vm.selectedAllItem = true;
                        				} else {
                        					vm.selectedAllItem = false;
                        				}
                                }
                        		}

//                        		        function actualRunProcess(){
//
//
//                                                 if(vm.unverifiedFlag == 'Y'){
//                                                 Swal.fire({
//                                                    title : "Are you sure?",
//                                                    text : "Some Records are Unverified, Which May Affect the Interest Payment. Please Verify the Accounts Before Proceeding to the Actual Interest Payment",
//                                                    type : "warning",
//                                                    showCancelButton : true,
//                                                    cancelButtonClass : "btn-cancel",
//                                                    confirmButtonClass : "btn-primary",
//                                                    confirmButtonText : "Yes",
//                                                    closeOnConfirm : false
//                                                },
//                                                function() {
//                                                vm.loader = true;
//                                                var requestData = {
//
//                                                     'moduleId' : vm.moduleId,
//                                                     'branchId' : vm.branchId,
//                                                     'valueFlag':vm.valueFlag,
//                                                     'operationType' : 'bondSubsId',
//                                                     'blaNo' : vm.blaNo,
//                                                     'interestForHalfYear' : 'JANUARY',
//                                                     'year' : '2023',
//
//
//                                                };
//                                                     Swal.fire.close();
//                                                     rbiBondCenInterestPaymentRunService.actualRunProcess(requestData).then(function(response){
//                                                     vm.loader = false;
//                                                     Swal.fire("Success!", "Interest Calculation Done Successfully" , "success");
//                                                     location.href = '#!/rbi_bond_centralized_int_payment/action/inquiry';
//                                                    }, function(error){
//                                                        vm.loader = false;
//                                                        handleErrorResponse(error);
//                                                    });
//                                                });
//                                                 } else{
//
//
//                                                var requestData = {
//                                                 'moduleId' : vm.moduleId,
//                                                                      'branchId' : vm.branchId,
//                                                                      'valueFlag':vm.valueFlag,
//                                                                      'operationType' : 'bondSubsId',
//                                                                      'blaNo' : vm.blaNo,
//                                                                      'interestForHalfYear' : 'JANUARY',
//                                                                      'year' : '2023',
//                                                                      'valueFlag' : vm.valueFlag
//                                                };
//
//                                                    rbiBondCenInterestPaymentRunService.getValidateDetails(requestData).then(function(response){
//                                                     Swal.fire("Success!", "Interest Calculation Done Successfully" , "success");
//                                                     location.href = '#!/rbi_bond_centralized_int_payment/action/inquiry';
//                                                    }, function(error){
//                                                        handleErrorResponse(error);
//                                                    });
//                                                }
//
//                                        }

        function getPaymentStatus() {
             var requestData = {
                        'moduleId' : vm.moduleId,
                        'branchId' : vm.branchId,
                        'centralizedFlag' : 'Y',
                        'processId' : vm.processId
                            };
            rbiBondCenInterestPaymentRunService.getInterestPaymentStatus(requestData).then(
                function(response) {
                    if (response.data.result) {
/*                        console.log(response.data.result)*/
                        $scope.$evalAsync(function() {
                            vm.transactionList = response.data.result.paymentStatusOut;
                            window.scrollTo(0, document.body.scrollHeight);
                        })
                        if (response.data.result.completedFlag == 'N') {
                            setTimeout(function() {
                                getPaymentStatus();
                            }, 2000);
                        } else {
                            if (vm.isStatusFetchedAfterOneTime) {
                                vm.isStatusFetchedAfterOneTime = false;
                                vm.isTranCompleted = true;
                                return;
                            } else {
                                vm.isStatusFetchedAfterOneTime = true;
                                setTimeout(function() {
                                    getPaymentStatus();
                                }, 2000);
                            }
                        }
                    }

                },
                function(error) {
                    vm.isTranCompleted = true;
                    handleErrorResponse(error);
                    getInputDetailsPage();
                }
            )
        }

        function getInterestPaymentBatchTransaction() {
                    vm.loader = true;

               var requestData = {
                    'moduleId' : vm.moduleId,
                    'centralizedFlag' : 'Y',
                    'branchId' : vm.branchId,
                    //  'valueFlag':vm.valueFlag,
                    //  'operationType' : 'bondSubsId',
                    'blaNo' : vm.blaNo,
                    'interestForHalfYear' : vm.data.interestForHalfYear,
                    'year' : vm.data.year,
                              };
                    rbiBondCenInterestPaymentRunService.getInterestPaymentBatchTransaction(requestData).then(function(response) {
                        vm.loader = false;
                        if (response.data.result && response.data.result.paymentProcessId) {
                            vm.processId = response.data.result.paymentProcessId;
                            console.log(vm.processId)
                            getPaymentStatus();
                        } else {
                            console.error("Invalid Payment Process Id")
                        }
                    }, function(error) {
                        vm.loader = false;
                        handleErrorResponse(error);
                        getInputDetailsPage();
                    })
                }

        function getInterestPaymentProcess(){

       let isSwal.fireClosed = false;

//        for testing purpose

//                 if(vm.unverifiedFlag == 'Y'){
                 if(vm.unverifiedFlag == 'Y'){

//                 Swal.fire({
//                    title : "Warning!",
//                    text : "Some Records are Unverified, Which May Affect the Interest Payment.Please Verify the Accounts Before Proceeding to Actual Interest Payment",
//                    type : "warning",
//                    showCancelButton : true,
//                    cancelButtonClass : "btn-cancel",
//                    confirmButtonClass : "btn-primary",
//                    confirmButtonText : "OK",
//                    closeOnConfirm : true
//                },
//                function() {


              Swal.fire({
                title: "Warning?",
                text: "Some Records are Unverified, Which May Affect the Interest Payment.Please Verify the Accounts Before Proceeding to Actual Interest Payment?",
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
               $state.go('dc_rbi_bond_menu')
//       #saranya:  as per function team  no payment should be done if unverifed records found
             }   });
                 } else{
                 Swal.fire({
                                     title : "Are you sure?",
                                     text : "Do you want to proceed with payment?",
                                     type : "warning",
                                     showCancelButton : true,
                                     cancelButtonClass : "btn-cancel",
                                     confirmButtonClass : "btn-primary",
                                     confirmButtonText : "OK",
                                     closeOnConfirm : true
                                 },function(){

// $state.go('');
 Swal.fire.close();
 vm.loader=false;
  vm.getInterestPaymentDetailsPage();
  vm.getInterestPaymentBatchTransaction();

                                 });
                            }


        }


	}
})();