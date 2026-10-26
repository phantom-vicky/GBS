(function() {

    'use strict';

    angular.module('gbmUltraApp').controller('bondSubAccCloseBeforeMaturDeath',
        bondSubAccCloseBeforeMaturDeath);

    bondSubAccCloseBeforeMaturDeath.$inject = ['$scope', '$filter', '$state', '$rootScope',
        '$stateParams', '$location', '$timeout', 'bondSubAccCloseBeforeMaturDeathService', 'commonService'
    ];

    function bondSubAccCloseBeforeMaturDeath($scope, $filter, $state, $rootScope, $stateParams,
        $location, $timeout, bondSubAccCloseBeforeMaturDeathService, commonService) {

		var vm = this;
		vm.nomineeList = [];
		vm.legalHeirDeatailList = [];
		vm.moduleName = 'RBI Bond';
//		vm.moduleId = '0012';
		vm.solDate = $rootScope.globals.currentUser.systemDate;
		vm.subsDate = moment(vm.solDate).format("DD-MMM-YYYY");
        vm.getModuleName = getModuleName;
        vm.getAddDetails = getAddDetails;
       vm.addClosureBefMaturDeath = addClosureBefMaturDeath;
        vm.verifyClosureBefMaturDeath = verifyClosureBefMaturDeath;
        vm.getGuardianDetailsMetaData=getGuardianDetailsMetaData;
        vm.convertLabelValue=convertLabelValue;

        vm.getVerifyDeleteDetails = getVerifyDeleteDetails;
        vm.getInquiryDetails = getInquiryDetails;
        vm.deleteClosureBefMaturDeath = deleteClosureBefMaturDeath;
        vm.creditAccAndBranch = creditAccAndBranch;
        vm.addRange = addRange;
        vm.removeRange =removeRange;
        vm.legalHeirPayBranchId = legalHeirPayBranchId;
        vm.totSharePercen =totSharePercen;
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


   function addRange() {
                      if (vm.legalHeirDeatailList.length>= 0) {
                          vm.legalHeirDeatailList.push({});
                          vm.enableDelete = true;

                          if (vm.legalHeirDeatailList.length>0){
                          vm.totalShareDisplay = true;
                          }
                          else{
                          vm.totalShareDisplay = false;
                          }
                      }
                  }

 function getGuardianDetailsMetaData() {
                           var requestData = {
                                 'referenceId': 'RELATION_WITH_MINOR'
                             }
                           bondSubAccCloseBeforeMaturDeathService.getReferenceData(requestData).then(function(response){
                              vm.guardianDetailsList = response.data.result;
                            },function(error) {
                               handleErrorResponse(error);
                             });
                        }

   function removeRange( index) {
                         $scope.$evalAsync(function(){
                         vm.legalHeirDeatailList.splice(index, 1);

                         vm.legalHeirDeatailList = vm.legalHeirDeatailList;

                         if(vm.legalHeirDeatailList.length > 0){vm.enableDelete = true;}
                           });

//                        if (vm.legalHeirDeatailList.length=0){vm.totalShareDisplay = false;}
                          }

   function totSharePercen(list,x){
 var sum =0;
   for (var i = 0; i < list.length; i++) {

        sum += list[i].sharePercentage;
       vm.totSharePercenValue = sum;
       }

      x.shareAmountSettled = vm.totalAmt*x.sharePercentage/100;

       if(vm.totSharePercenValue >100){
       vm.totSharePercenErr = "Total Share percentage Cannot be Greater Than 100";
       vm.totSharePercenValue=""
       }else if(vm.totSharePercenValue <100){
              vm.totSharePercenErr = "Total Share percentage Cannot be Lesser Than 100";
              vm.totSharePercenValue ="";
       }
       else{vm.totSharePercenErr ="";}

   }


          if($state.current.name == 'rbi_bond_sub_acc_closure_before_matur_death.add_detail') {
		            if($stateParams.obj){
                     vm.boSubsId = $stateParams.obj.boSubsId;
                     vm.blaNo = $stateParams.obj.blaNo;
                     vm.schemeName = $stateParams.obj.schemeName;
                     vm.moduleId=$stateParams.obj.moduleId;
                     vm.proceedsToBePaid = $stateParams.obj.proceedsToBePaid;
                     vm.penalityToBeRecovered =$stateParams.obj.penalityToBeRecovered;

                     if($stateParams.obj.proceedsToBePaid =='N'){
                     vm.proceedsToBePaidDetail = 'Nominees'
                     }
                     else{
                     vm.proceedsToBePaidDetail = 'Legal Heirs'
                     }

                     vm.getAddDetails('det');
                     vm.getGuardianDetailsMetaData();
		           }
		  }
		  else if($state.current.name == 'rbi_bond_sub_acc_closure_before_matur_death.verify_detail' || $state.current.name == 'rbi_bond_sub_acc_closure_before_matur_death.delete_detail') {
          		    if($stateParams.obj){
                      vm.boSubsId = $stateParams.obj.boSubsId;
                      vm.blaNo = $stateParams.obj.blaNo;
                      vm.schemeName = $stateParams.obj.schemeName;
                      vm.moduleId=$stateParams.obj.moduleId;
                      vm.getVerifyDeleteDetails('det');
                      vm.getGuardianDetailsMetaData();
                     }
         }else if($state.current.name == 'rbi_bond_sub_acc_closure_before_matur_death.inquiry_detail') {
                   	if($stateParams.obj){
                       vm.boSubsId = $stateParams.obj.boSubsId;
                       vm.blaNo = $stateParams.obj.blaNo;
                       vm.schemeName = $stateParams.obj.schemeName;
                        vm.moduleId=$stateParams.obj.moduleId;
                      vm.getInquiryDetails('det');
                         vm.getGuardianDetailsMetaData();

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
                              bondSubAccCloseBeforeMaturDeathService.getAddDetails(requestData).then(function (response) {
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
                              bondSubAccCloseBeforeMaturDeathService.getAddDetails(requestData).then(function (response) {
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
                                   'boSubsId' : vm.boSubsId,
                                   'proceedsToBePaid' : vm.proceedsToBePaid,
                                   'penalityToBeRecovered' : vm.penalityToBeRecovered,
                                  };
                                 bondSubAccCloseBeforeMaturDeathService.getAddDetails(requestData).then(function (response) {
                                 vm.loader = false;
                                vm.blaDetails = response.data.result.blaDetails;
                                vm.firstJointBondHolderName = response.data.result.blaDetails.bondHolderName;
                                vm.bondDetails = response.data.result.bondDetails;
                                vm.nomineeDetailsList = response.data.result.nomineeDetailsList;
                                vm.ddPoAccNum = response.data.result.ddPoAccNum;
                                 vm.totalAmt= vm.bondDetails.amtPaid;

                                 vm.bondDetails.dodBondHolder =$rootScope.formatDateToDDMMMYYYY(convertToDateObject(vm.bondDetails.dodBondHolder));
                                 vm.bondDetails.dateOfReceiptDod =$rootScope.formatDateToDDMMMYYYY(convertToDateObject(vm.bondDetails.dateOfReceiptDod));
                                 vm.bondDetails.subsDate = $rootScope.formatDateToDDMMMYYYY(convertToDateObject(vm.bondDetails.subsDate));
                                 vm.bondDetails.maturityDate = $rootScope.formatDateToDDMMMYYYY(convertToDateObject(vm.bondDetails.maturityDate));
//vm.bondDetails.intCalFromDate = $rootScope.formatDateToDDMMMYYYY(convertToDateObject(vm.bondDetails.intCalFromDate));
//vm.bondDetails.intCalToDate = $rootScope.formatDateToDDMMMYYYY(convertToDateObject(vm.bondDetails.intCalToDate));

                                  }, function (error) {
                                       handleErrorResponse(error);
                                       vm.loader = false;
                                       $state.go('rbi_bond_sub_acc_closure_before_matur_death.add')
                                     });
                            }

                  }

          function convertLabelValue(list){
                                 var resultList = [];
                                     for(var item in list){
                                         list[item].value = list[item].SUBCODE;
                                         list[item].label = list[item].DESCRIPTION;
                                         let sam = {};
                                         sam.label = list[item].label;
                                         sam.value = list[item].value;
                                         resultList.push(sam);
                                     }
                 //                    return list;
                                     return resultList;
                                 }

function legalHeirPayBranchId(x){
if (x.modeOfInterestPay =='MP001'){
if(x.accNum){
commonService.getCustomAcctInqApi(x.accNum, function (response) {
vm.accValidate = response.data;

                  if (vm.accValidate[0].status == 'F') {
                  vm.message = "INVALID ACCOUNT NUMBER";
                  x.accBranch = '';
                  }
                  else{
                  vm.message ="";
                  x.accBranch =vm.accValidate[0].solID;
                  }

                  });
                  }
                  }
                  else if(x.modeOfInterestPay =='MP002'){
                  vm.message = "";
                  x.accNum = vm.ddPoAccNum;

                  }
                  }



                  function creditAccAndBranch(x){

                  if (x.modeOfInterestPay =='MP001'){
//                  x.accNum = x.initalNomineeAccNo;
//                  x.accBranch = x.initalNomineeBranchId;

                   if(x.accNum){

                   commonService.getCustomAcctInqApi(x.accNum, function (response) {
                                    vm.accValidate = response.data;
                                    if(vm.accValidate[0].status == 'F'){
                                    vm.message = "INVALID ACCOUNT NUMBER";
                                    x.accBranch = '';
                                    }
                                    else{
                                    vm.message = "";
                                    x.accBranch =vm.accValidate[0].solID;

                                    }

                                    });
                    }
                  }

                  else if(x.modeOfInterestPay =='MP002'){
                  vm.message = "";
                  x.accNum = vm.ddPoAccNum;
                  }

                  }





// finacle call to get the branch id for ddpo acc num

function operativeAccNumValidation(accountNum){
if(accountNum){
commonService.getCustomAcctInqApi(accountNum, function (response) {
vm.message = "INVALID ACCOUNT NUMBER"
vm.accValidate = response.data;
vm.oprAccBranchId =vm.accValidate[0].solID;

if (vm.accValidate[0].status == 'F')
{
vm.creditBankAccountNoErrMsg = vm.message;
}
else
{
vm.creditBankAccountNoErrMsg = '';
}
}, function (err) {
handleErrorResponse(err);
vm.loader = false;
});
}

else
{
vm.creditBankAccountNoErrMsg = '';
}
}




          function addClosureBefMaturDeath(){

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
 vm.bondDetails.nameOfTheBondHolder = vm.blaDetails.nameOfTheBondHolder;
 vm.bondDetails.isNri = vm.blaDetails.isNri;
 vm.bondDetails.closureType = 'BMD02';
 vm.bondDetails.moduleId = vm.moduleId;
 vm.bondDetails.modeOfHoldingCode = vm.blaDetails.modeOfHoldingCode;
vm.bondDetails.dodBondHolder = vm.bondDetails.dodBondHolder;
vm.bondDetails.dateOfReceiptDod = vm.bondDetails.dateOfReceiptDod;
vm.bondDetails.proceedsToBePaid =vm.proceedsToBePaid;
vm.bondDetails.penalityToBeRecovered = vm.penalityToBeRecovered;
vm.bondDetails.bondHolderName = vm.firstJointBondHolderName;


 var requestData ={
  'bondDetails' : vm.bondDetails,
  'nomineeDetails' : vm.nomineeDetailsList,
  'legalHeirDeatails' : vm.legalHeirDeatailList,
 }
 bondSubAccCloseBeforeMaturDeathService.addClosureBefMaturDeath(requestData).then(function (response){


 vm.loader = false;

 if(response.data.message =='SUCCESS'){

 vm.gbmTranId = response.data.result;

Swal.fire("Added!","Bond Account Closure on Maturity Added Successfully"+"\n GBM TRAN ID : "+vm.gbmTranId, "success");
$state.go('rbi_bond_sub_acc_closure_before_matur_death.inquiry');
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




















                  function getVerifyDeleteDetails(flag){
                             vm.loader = true;

                                                         if(flag == "bla"){

                                                          var requestData = {
                                                              'flag' : flag,
                                                              'moduleId' : vm.moduleId,
                                                              'blaNo' : null,
                                                              'boSubsId' : null

                                                          };
                                                          bondSubAccCloseBeforeMaturDeathService.getVerifyDeleteDetails(requestData).then(function (response) {
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
                                                          bondSubAccCloseBeforeMaturDeathService.getVerifyDeleteDetails(requestData).then(function (response) {
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
                    bondSubAccCloseBeforeMaturDeathService.getVerifyDeleteDetails(requestData).then(function (response) {
                    vm.loader = false;
                    vm.blaDetails = response.data.result.blaDetails;
                    vm.bondDetails = response.data.result.bondDetails;
                    vm.proceedsToBePaid = response.data.result.bondDetails.proceedsToPaidCode,
                    //                                                             penalityToBeRecovered : vm.penalityToBeRecovered,

                    vm.totalAmt= vm.bondDetails.amtPaid;
                    if (vm.proceedsToBePaid =='N'){
                    vm.nomineeDetailsList = response.data.result.nomineeDetails;}
                    else{
                    vm.legalHeirDeatailList = response.data.result.legalHeirDeatails;
                    }

//                    vm.ddPoAccNum = response.data.result.ddPoAccNum;
                    vm.bondDetails.dodBondHolder =$rootScope.formatDateToDDMMMYYYY(convertToDateObject(vm.bondDetails.dodBondHolder));
                    vm.bondDetails.dateOfReceiptDod =$rootScope.formatDateToDDMMMYYYY(convertToDateObject(vm.bondDetails.dateOfReceiptDod));
                    vm.bondDetails.subsDate = $rootScope.formatDateToDDMMMYYYY(convertToDateObject(vm.bondDetails.subsDate));
                    vm.bondDetails.maturityDate = $rootScope.formatDateToDDMMMYYYY(convertToDateObject(vm.bondDetails.maturityDate));
                    vm.bondDetails.intCalFromDate = $rootScope.formatDateToDDMMMYYYY(convertToDateObject( vm.bondDetails.intCalFromDate));
                    vm.bondDetails.intCalToDate = $rootScope.formatDateToDDMMMYYYY(convertToDateObject(vm.bondDetails.intCalToDate));
                    vm.proceedsToBePaidDetail = response.data.result.bondDetails.proceedsToBePaid;
                    vm.auditInfo= response.data.result.auditDetails;
                    }, function (error) {
                    handleErrorResponse(error);
                    vm.loader = false;
                    $state.go('rbi_bond_sub_acc_closure_before_matur_death.verify')
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
                                  'boSubsId' : null

                              };
                              bondSubAccCloseBeforeMaturDeathService.getInquiryDetails(requestData).then(function (response) {
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
                              bondSubAccCloseBeforeMaturDeathService.getInquiryDetails(requestData).then(function (response) {
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
                                 bondSubAccCloseBeforeMaturDeathService.getInquiryDetails(requestData).then(function (response) {
                                               vm.loader = false;
                                                   vm.blaDetails = response.data.result.blaDetails;
                                                   vm.bondDetails = response.data.result.bondDetails;
                                                   vm.proceedsToBePaid = response.data.result.bondDetails.proceedsToPaidCode,
                                                   //                                                             penalityToBeRecovered : vm.penalityToBeRecovered,

                                                   vm.totalAmt= vm.bondDetails.amtPaid;
                                                   if (vm.proceedsToBePaid =='N'){
                                                   vm.nomineeDetailsList = response.data.result.nomineeDetails;}
                                                   else{
                                                   vm.legalHeirDeatailList = response.data.result.legalHeirDeatails;
                                                   }

                               //                    vm.ddPoAccNum = response.data.result.ddPoAccNum;
                                                   vm.bondDetails.dodBondHolder =$rootScope.formatDateToDDMMMYYYY(convertToDateObject(vm.bondDetails.dodBondHolder));
                                                   vm.bondDetails.dateOfReceiptDod =$rootScope.formatDateToDDMMMYYYY(convertToDateObject(vm.bondDetails.dateOfReceiptDod));
                                                   vm.bondDetails.subsDate = $rootScope.formatDateToDDMMMYYYY(convertToDateObject(vm.bondDetails.subsDate));
                                                   vm.bondDetails.maturityDate = $rootScope.formatDateToDDMMMYYYY(convertToDateObject(vm.bondDetails.maturityDate));
                                                   vm.bondDetails.intCalFromDate = $rootScope.formatDateToDDMMMYYYY(convertToDateObject( vm.bondDetails.intCalFromDate));
                                                   vm.bondDetails.intCalToDate = $rootScope.formatDateToDDMMMYYYY(convertToDateObject(vm.bondDetails.intCalToDate));
                                                   vm.proceedsToBePaidDetail = response.data.result.bondDetails.proceedsToBePaid;
                                                   vm.auditInfo= response.data.result.auditDetails;
                                  }, function (error) {
                                       handleErrorResponse(error);
                                       vm.loader = false;
                                       $state.go('rbi_bond_sub_acc_closure_before_matur_death.inquriy')
                                     });
                            }

                  }






          function deleteClosureBefMaturDeath(){


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
 bondSubAccCloseBeforeMaturDeathService.deleteClosureBefMaturDeath(requestData).then(function (response){


 vm.loader = false;

 if(response.data.message =='SUCCESS'){

Swal.fire("DELETED!","Bond Account Closure on Maturity Deleted Successfully", "success");
$state.go('rbi_bond_sub_acc_closure_before_matur_death.inquiry');
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


 function verifyClosureBefMaturDeath(){


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
 bondSubAccCloseBeforeMaturDeathService.verifyClosureBefMaturDeath(requestData).then(function (response){


 vm.loader = false;

 if(response.data.message =='SUCCESS'){

Swal.fire("VERIFIED!","Bond Account Closure on Maturity Verified Successfully", "success");
$state.go('rbi_bond_sub_acc_closure_before_matur_death.inquiry');
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
                       bondSubAccCloseBeforeMaturDeathService.getRbiBondModuleNameDetails(requestData).then(function (response) {
                           vm.loader = false;
                           vm.schemeList = response.data.result;
                           vm.schemeName=response.data.result[0].subCodeDescription;

                       }, function (error) {
                           handleErrorResponse(error);
                           vm.loader = false;
                       });
                   }

       } })();