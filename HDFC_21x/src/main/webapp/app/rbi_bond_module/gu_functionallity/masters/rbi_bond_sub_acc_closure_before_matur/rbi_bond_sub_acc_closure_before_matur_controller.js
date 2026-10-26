(function() {

    'use strict';

    angular.module('gbmUltraApp').controller('bondSubAccCloseBeforeMatur',
        bondSubAccCloseBeforeMatur);

    bondSubAccCloseBeforeMatur.$inject = ['$scope', '$filter', '$state', '$rootScope',
        '$stateParams', '$location', '$timeout', 'bondSubAccCloseBeforeMaturService', 'commonService'
    ];

    function bondSubAccCloseBeforeMatur($scope, $filter, $state, $rootScope, $stateParams,
        $location, $timeout, bondSubAccCloseBeforeMaturService, commonService) {

		var vm = this;
		vm.nomineeList = [];
		vm.moduleName = 'RBI Bond';
		vm.solDate = $rootScope.globals.currentUser.systemDate;

        vm.getModuleName = getModuleName;
        vm.getAddDetails = getAddDetails;
        vm.getVerifyDeleteDetails = getVerifyDeleteDetails;
        vm.getInquiryDetails = getInquiryDetails;
        vm.addClosureBefMatur = addClosureBefMatur;
        vm.verifyClosureBefMatur = verifyClosureBefMatur;
        vm.deleteClosureBefMatur = deleteClosureBefMatur;


        vm.solDate1 = $rootScope.globals.currentUser.solDate;


        vm.today=new Date();
        vm.loginSolDate = $rootScope.globals.currentUser.solDate;
		vm.loginSolId = $rootScope.globals.currentUser.solId;
		vm.commonYesNoMetaData = COMMON_YES_NO_METADATA;

        vm.yesNoMetaData = [
            {label : "Select",value : null},
            {label : "Yes",value : "Y"},
            {label : "No",value : 'N'}
        ];


          if($state.current.name == 'rbi_bond_sub_acc_closure_before_matur.add_detail') {
		            if($stateParams.obj){
                     vm.boSubsId = $stateParams.obj.boSubsId;
                     vm.blaNo = $stateParams.obj.blaNo;
                     vm.moduleId = $stateParams.obj.moduleId;
                    vm.schemeName = $stateParams.obj.schemeName;
                     vm.bondHolderName = $stateParams.obj.bondHolderName;
                     vm.getAddDetails('det');
		           }
		  }
		  else if($state.current.name == 'rbi_bond_sub_acc_closure_before_matur.verify_detail' || $state.current.name == 'rbi_bond_sub_acc_closure_before_matur.delete_detail') {
          		    if($stateParams.obj){
                      vm.boSubsId = $stateParams.obj.boSubsId;
                      vm.blaNo = $stateParams.obj.blaNo;
                      vm.moduleId = $stateParams.obj.moduleId;
                    vm.schemeName = $stateParams.obj.schemeName;
                      vm.getVerifyDeleteDetails('det');
                     }
         }else if($state.current.name == 'rbi_bond_sub_acc_closure_before_matur.inquiry_detail') {
                   	if($stateParams.obj){
                       vm.boSubsId = $stateParams.obj.boSubsId;
                       vm.blaNo = $stateParams.obj.blaNo;
                       vm.moduleId = $stateParams.obj.moduleId;
                        vm.schemeName = $stateParams.obj.schemeName;

                       vm.getInquiryDetails('det');
                   	}
         }


                  function getAddDetails(flag){
                              vm.loader = true;

                             if(flag == "bla"){

                              var requestData = {
                                  'flag' : flag,
                                  'moduleId' : vm.moduleId,
                                  'blaNo' : null,
                                  'boSubsId' : null,

                              };
                              bondSubAccCloseBeforeMaturService.getAddDetails(requestData).then(function (response) {
                                  vm.loader = false;
                                  vm.blaDataList = response.data.result;
                                  angular.element('#blaModel').modal('toggle');

                              }, function (error) {
                                  handleErrorResponse(error);
                                  vm.loader = false;
                              });
                             }

                             if(flag == "boSubsId"){

                                 var requestData = {
                                 'flag' : flag,
                                 'moduleId' : vm.moduleId,
                                 'blaNo' : vm.blaNo,
                                 'bondSubsId' : null
                                 };
                              bondSubAccCloseBeforeMaturService.getAddDetails(requestData).then(function (response) {
                                 vm.loader = false;
                                 vm.bondSubsList = response.data.result;
                                 angular.element('#bondSubsIdSearch').modal('toggle');
                                 }, function (error) {
                                   handleErrorResponse(error);
                                    vm.loader = false;
                              });
                            }

                            if(flag == "det"){

                                 var requestData = {
                                   'flag' : flag,
                                   'moduleId' : vm.moduleId,
                                   'blaNo' : vm.blaNo,
                                   'boSubsId' : vm.boSubsId
                                  };
                                 bondSubAccCloseBeforeMaturService.getAddDetails(requestData).then(function (response) {
                                 vm.loader = false;

                                 vm.blaDetails = response.data.result.blaDetails;
                                 vm.bondDetails = response.data.result.bondDetails;

                                 vm.bondDetails.subsDate = $rootScope.formatDateToDDMMMYYYY(convertToDateObject(vm.bondDetails.subsDate));
                                 vm.bondDetails.maturityDate = $rootScope.formatDateToDDMMMYYYY(convertToDateObject(vm.bondDetails.maturityDate));
                                 vm.bondDetails.intCalFromDate = $rootScope.formatDateToDDMMMYYYY(convertToDateObject(vm.bondDetails.intCalFromDate));
                                 vm.bondDetails.intCalToDate = $rootScope.formatDateToDDMMMYYYY(convertToDateObject(vm.bondDetails.intCalToDate));

                                  }, function (error) {
                                       handleErrorResponse(error);
                                       vm.loader = false;
                                       $state.go('rbi_bond_sub_acc_closure_before_matur.add')
                                     });
                            }

                  }


                  function getVerifyDeleteDetails(flag){
                             vm.loader = true;

                                                         if(flag == "bla"){

                                                          var requestData = {
                                                              'flag' : flag,
                                                              'moduleId' : vm.moduleId,
                                                              'blaNo' : null,
                                                              'boSubsId' : null

                                                          };
                                                          bondSubAccCloseBeforeMaturService.getVerifyDeleteDetails(requestData).then(function (response) {
                                                              vm.loader = false;
                                                              vm.blaDataList = response.data.result;
                                                              angular.element('#blaModel').modal('toggle');

                                                          }, function (error) {
                                                              handleErrorResponse(error);
                                                              vm.loader = false;
                                                          });
                                                         }

                                                         if(flag == "boSubsId"){

                                                             var requestData = {
                                                             'flag' : flag,
                                                             'moduleId' : vm.moduleId,
                                                             'blaNo' : vm.blaNo,
                                                             'bondSubsId' : null
                                                             };
                                                          bondSubAccCloseBeforeMaturService.getVerifyDeleteDetails(requestData).then(function (response) {
                                                             vm.loader = false;
                                                             vm.bondSubsList = response.data.result;
                                                             angular.element('#bondSubsIdSearch').modal('toggle');
                                                             }, function (error) {
                                                               handleErrorResponse(error);
                                                                vm.loader = false;
                                                          });
                                                        }

                                                        if(flag == "det"){

                                                             var requestData = {
                                                               'flag' : flag,
                                                               'moduleId' : vm.moduleId,
                                                               'blaNo' : vm.blaNo,
                                                               'boSubsId' : vm.boSubsId
                                                              };
                                                             bondSubAccCloseBeforeMaturService.getVerifyDeleteDetails(requestData).then(function (response) {
                                                             vm.loader = false;

                                                             vm.auditInfo = response.data.result.auditDetails;
                                                             vm.bondDetails = response.data.result.bondDetails;

                                                             vm.bondDetails.subsDate = $rootScope.formatDateToDDMMMYYYY(convertToDateObject(vm.bondDetails.subsDate));
                                                                                              vm.bondDetails.maturityDate = $rootScope.formatDateToDDMMMYYYY(convertToDateObject(vm.bondDetails.maturityDate));
                                                                                              vm.bondDetails.intCalFromDate = $rootScope.formatDateToDDMMMYYYY(convertToDateObject(vm.bondDetails.intCalFromDate));
                                                                                              vm.bondDetails.intCalToDate = $rootScope.formatDateToDDMMMYYYY(convertToDateObject(vm.bondDetails.intCalToDate));


                                                             if(vm.bondDetails.modeOfInterestPay=='MP001')
                                                             vm.bondDetails.modeOfInterestPay = 'CREDIT ACCOUNT';


                                                             else if(vm.bondDetails.modeOfInterestPay=='MP002')
                                                             vm.bondDetails.modeOfInterestPay = 'DD/PO';

                                                             else if(vm.bondDetails.modeOfInterestPay=='MP003')
                                                             vm.bondDetails.modeOfInterestPay = 'NEFT';


                                                              }, function (error) {
                                                                   handleErrorResponse(error);
                                                                   vm.loader = false;
                                                                   $state.go('rbi_bond_sub_acc_closure_before_matur.inquriy')
                                                                 });
                                                        }

                  }


                  function getInquiryDetails(flag){
                              vm.loader = true;

                             if(flag == "bla"){

                              var requestData = {
                                  'flag' : flag,
                                  'moduleId' : vm.moduleId,
                                  'blaNo' : null,
                                  'schemeName' : vm.schemeName,
                                  'boSubsId' : null

                              };
                              bondSubAccCloseBeforeMaturService.getInquiryDetails(requestData).then(function (response) {
                                  vm.loader = false;
                                  vm.blaDataList = response.data.result;
                                  angular.element('#blaModel').modal('toggle');

                              }, function (error) {
                                  handleErrorResponse(error);
                                  vm.loader = false;
                              });
                             }

                             if(flag == "boSubsId"){

                                 var requestData = {
                                 'flag' : flag,
                                 'moduleId' : vm.moduleId,
                                 'blaNo' : vm.blaNo,
                                 'bondSubsId' : null
                                 };
                              bondSubAccCloseBeforeMaturService.getInquiryDetails(requestData).then(function (response) {
                                 vm.loader = false;
                                 vm.bondSubsList = response.data.result;
                                 angular.element('#bondSubsIdSearch').modal('toggle');
                                 }, function (error) {
                                   handleErrorResponse(error);
                                    vm.loader = false;
                              });
                            }

                            if(flag == "det"){

                                 var requestData = {
                                   'flag' : flag,
                                   'moduleId' : vm.moduleId,
                                   'blaNo' : vm.blaNo,
                                   'boSubsId' : vm.boSubsId
                                  };
                                 bondSubAccCloseBeforeMaturService.getInquiryDetails(requestData).then(function (response) {
                                 vm.loader = false;

                                 vm.auditInfo = response.data.result.auditDetails;
                                 vm.bondDetails = response.data.result.bondDetails;
                                 
                                 vm.bondDetails.subsDate = $rootScope.formatDateToDDMMMYYYY(convertToDateObject(vm.bondDetails.subsDate));
                                                                  vm.bondDetails.maturityDate = $rootScope.formatDateToDDMMMYYYY(convertToDateObject(vm.bondDetails.maturityDate));
                                                                  vm.bondDetails.intCalFromDate = $rootScope.formatDateToDDMMMYYYY(convertToDateObject(vm.bondDetails.intCalFromDate));
                                                                  vm.bondDetails.intCalToDate = $rootScope.formatDateToDDMMMYYYY(convertToDateObject(vm.bondDetails.intCalToDate));


                                 if(vm.bondDetails.modeOfInterestPay=='MP001')
                                 vm.bondDetails.modeOfInterestPay = 'CREDIT ACCOUNT';


                                 else if(vm.bondDetails.modeOfInterestPay=='MP002')
                                 vm.bondDetails.modeOfInterestPay = 'DD/PO';

                                 else if(vm.bondDetails.modeOfInterestPay=='MP003')
                                 vm.bondDetails.modeOfInterestPay = 'NEFT';


                                  }, function (error) {
                                       handleErrorResponse(error);
                                       vm.loader = false;
                                       $state.go('rbi_bond_sub_acc_closure_before_matur.inquriy')
                                     });
                            }

                  }



          function addClosureBefMatur(){

//
//                         Swal.fire({
//                    			title: "Are you sure?",
//                   				text: "Do you want to Really Add this record?",
//                   				type: "warning",
//                   				showCancelButton: true,
//                   				cancelButtonClass: "btn-cancel",
//                   				confirmButtonClass: "btn-primary",
//                   				confirmButtonText: "Yes",
//                   				closeOnConfirm: false
//                   			}, function () {



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



 vm.loader = true;

 vm.bondDetails.modeOfHolding = vm.blaDetails.modeOfHolding;
 vm.bondDetails.blaNo = vm.blaNo;
 vm.bondDetails.holderType = vm.blaDetails.holderType;
 vm.bondDetails.holderTypeCode = vm.blaDetails.holderTypeCode;

 vm.bondDetails.firstJointBondHolderBLA = vm.blaDetails.firstJointBondHolderBLA;
 vm.bondDetails.firstJointBondHolderName = vm.blaDetails.firstJointBondHolderName;
 vm.bondDetails.secondJointBondHolderBLA = vm.blaDetails.secondJointBondHolderBLA;
 vm.bondDetails.secondJointBondHolderName = vm.blaDetails.secondJointBondHolderName;
 vm.bondDetails.bondHolderName = vm.blaDetails.bondHolderName;
 vm.bondDetails.isNri = vm.blaDetails.isNri;
 vm.bondDetails.closureType = 'BMN01';
 vm.bondDetails.moduleId = vm.moduleId;
 vm.bondDetails.modeOfHoldingCode = vm.blaDetails.modeOfHoldingCode;

 var requestData ={
  'bondDetails' : vm.bondDetails,
 }
 bondSubAccCloseBeforeMaturService.addClosureBefMatur(requestData).then(function (response){


 vm.loader = false;

 if(response.data.message =='SUCCESS'){

Swal.fire("Added!","Bond Account Closure on Maturity Added Successfully \n (GBM Tran Id : " + response.data.result + ")\n (Subscription Id : " + vm.boSubsId + ")" + ")\n (BLA No : " + vm.blaNo + ")", "success");
$state.go('rbi_bond_sub_acc_closure_before_matur.inquiry');
 }else{
 Swal.fire("Error!",resultset.msg, "error");
 }
}, function (error) {
          handleErrorResponse(error);
          vm.loader = false;
      });
}
})
}


          function deleteClosureBefMatur(){


//                         Swal.fire({
//                    			title: "Are you sure?",
//                   				text: "Do you want to Really Delete this record?",
//                   				type: "warning",
//                   				showCancelButton: true,
//                   				cancelButtonClass: "btn-cancel",
//                   				confirmButtonClass: "btn-primary",
//                   				confirmButtonText: "Yes",
//                   				closeOnConfirm: false
//                   			}, function () {



                          Swal.fire({
                            title: "Are you sure?",
                            text: "Do you want to Really Delete this record?",
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


 var requestData ={
  'moduleId' : vm.moduleId,
  'boSubsId' : vm.boSubsId
 }
 bondSubAccCloseBeforeMaturService.deleteClosureBefMatur(requestData).then(function (response){


 vm.loader = false;

 if(response.data.message =='SUCCESS'){

Swal.fire("DELETED!","Bond Account Closure on Maturity Deleted Successfully", "success");
$state.go('rbi_bond_sub_acc_closure_before_matur.inquiry');
 }else{
 Swal.fire("Error!",resultset.msg, "error");
 }
}, function (error) {
          handleErrorResponse(error);
          vm.loader = false;
      });
      }

})
}


 function verifyClosureBefMatur(){


//                         Swal.fire({
//                    			title: "Are you sure?",
//                   				text: "Do you want to Really Verify this record?",
//                   				type: "warning",
//                   				showCancelButton: true,
//                   				cancelButtonClass: "btn-cancel",
//                   				confirmButtonClass: "btn-primary",
//                   				confirmButtonText: "Yes",
//                   				closeOnConfirm: false
//                   			}, function () {






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


 var requestData ={
  'moduleId' : vm.moduleId,
  'boSubsId' : vm.boSubsId
 }
 bondSubAccCloseBeforeMaturService.verifyClosureBefMatur(requestData).then(function (response){


 vm.loader = false;

 if(response.data.message =='SUCCESS'){

Swal.fire("VERIFIED!","Bond Account Closure on Maturity Verified Successfully \n (GBM Tran Id : " + response.data.result.gbmTranId + ")\n (CBS Tran Id : " + response.data.result.cbsTranId + ")" + ")\n (Subscription Id : " + vm.boSubsId + ")" + ")\n (BLA No : " + vm.blaNo + ")", "success");
$state.go('rbi_bond_sub_acc_closure_before_matur.inquiry');
 }else{
 Swal.fire("Error!",resultset.msg, "error");
 }
}, function (error) {
          handleErrorResponse(error);
          vm.loader = false;
      });
}
})
}



        function getModuleName() {
                       vm.loader = true;
                       vm.referenceId = 'TAX';
                       var requestData = {
                           'referenceId': vm.referenceId
                       };
                       bondSubAccCloseBeforeMaturService.getRbiBondModuleNameDetails(requestData).then(function (response) {
                           vm.loader = false;
                           vm.schemeList = response.data.result;
                           vm.schemeName=response.data.result[0].subCodeDescription;

                       }, function (error) {
                           handleErrorResponse(error);
                           vm.loader = false;
                       });
                   }



       } })();