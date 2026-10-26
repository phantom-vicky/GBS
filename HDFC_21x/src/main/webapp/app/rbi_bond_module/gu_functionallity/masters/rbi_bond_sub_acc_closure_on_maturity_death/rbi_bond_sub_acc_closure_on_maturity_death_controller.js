(function() {

    'use strict';

    angular.module('gbmUltraApp').controller('bondSubAccCloseOnMaturDeath',
        bondSubAccCloseOnMaturDeath);

    bondSubAccCloseOnMaturDeath.$inject = ['$scope', '$filter', '$state', '$rootScope',
        '$stateParams', '$location', '$timeout', 'bondSubAccCloseOnMaturDeathService', 'commonService'
    ];

    function bondSubAccCloseOnMaturDeath($scope, $filter, $state, $rootScope, $stateParams,
        $location, $timeout, bondSubAccCloseOnMaturDeathService, commonService) {

		var vm = this;
		vm.nomineeList = [];
		vm.legalHeirDeatailList = [];
vm.panPattern=/[a-zA-Z]{5}[0-9]{4}[a-zA-Z]{1}/;
		vm.legalHeirsDetailsList = [];
 vm.currentPage = 1;
        vm.itemsPerPage = '5';
		vm.moduleName = 'RBI Bond';
		vm.solDate = $rootScope.globals.currentUser.systemDate;
		vm.subsDate = moment(vm.solDate).format("DD-MMM-YYYY");
		vm.closureType = 'OM002';
		 vm.panValid = true;
        vm.calculateSum = calculateSum;
        vm.getModuleName = getModuleName;
        vm.getAddDetails = getAddDetails;
       vm.addClosureOnMaturDeath = addClosureOnMaturDeath;
        vm.getGuardianDetailsMetaData=getGuardianDetailsMetaData;
        vm.getAddValidation = getAddValidation;
        vm.getVerifyDeleteDetails = getVerifyDeleteDetails;
        vm.getInquiryDetails = getInquiryDetails;
        vm.deleteClosureBefMaturDeath = deleteClosureBefMaturDeath;
        vm.creditAccAndBranch = creditAccAndBranch;
        vm.addRange = addRange;
        vm.getTotalSharePer = getTotalSharePer;
            vm.getModuleNameVer = getModuleNameVer;
        vm.downloadFile = downloadFile;
        vm.removeRange =removeRange;
        vm.convertLabelValue=convertLabelValue;
        vm.legalHeirPayBranchId = legalHeirPayBranchId;
        vm.totSharePercen =totSharePercen;
        vm.solDate1 = $rootScope.globals.currentUser.solDate;
        vm.totalPer= '100';
        vm.inquiry = inquiry;
        vm.verifySubmit = verifySubmit;
        vm.deleteSubmit = deleteSubmit;
        vm.parseDateFromString = parseDateFromString;

//            vm.getModuleNameVer = getModuleNameVer;
           vm.getPanValidation = getPanValidation;
vm.proceedsToBePaidList = [
  {
    value: "L",
    label: "Legal Heirs"
  },
  {
    value: "N",
    label: "Nominee"
  }
];

        vm.uploadFile = uploadFile;
        vm.today=new Date();
        vm.loginSolDate = $rootScope.globals.currentUser.solDate;
		vm.loginSolId = $rootScope.globals.currentUser.solId;
		vm.commonYesNoMetaData = COMMON_YES_NO_METADATA;
        vm.getCbsCustDetails = getCbsCustDetails;
            vm.getRemarksList = getRemarksList;
            vm.rejectReason = rejectReason;
            vm.nomineeDetailsList = [];

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

 function getGuardianDetailsMetaData() {
                           var requestData = {
                                 'referenceId': 'RELATION_WITH_MINOR'
                             }
                           bondSubAccCloseOnMaturDeathService.getReferenceData(requestData).then(function(response){
                              vm.guardianDetailsList = response.data.result;
                              vm.guardianName= response.data.result.DESCRIPTION;
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


          if($state.current.name == 'rbi_bond_sub_acc_closure_on_matur_death.add_detail') {
		            if($stateParams.obj){
                     vm.boSubsId = $stateParams.obj.boSubsId;
                     vm.blaNo = $stateParams.obj.blaNo;
                     vm.schemeName = $stateParams.obj.schemeName;
                     vm.proceedsToBePaid = $stateParams.obj.proceedsToBePaid;
                     vm.schemeCode = $stateParams.obj.schemeCode;
                     vm.closureType = $stateParams.obj.closureType;
                     vm.nomineeDetailsList = $stateParams.obj.nomineeDetailsList;
                     vm.legalHeirsDetailsList = $stateParams.obj.legalHeirsDetailsList;
                     vm.panNumber =$stateParams.obj.panNumber;

                     if($stateParams.obj.proceedsToBePaid =='N'){
                     vm.proceedsToBePaidDetail = 'Nominees'
                     }
                     else{
                     vm.proceedsToBePaidDetail = 'Legal Heirs'
                     }

                     vm.getAddValidation();
                     vm.getGuardianDetailsMetaData();
		           }
		  }
		  else if($state.current.name == 'rbi_bond_sub_acc_closure_on_matur_death.verify_detail') {
          		    if($stateParams.obj){
                      vm.boSubsId = $stateParams.obj.boSubsId;
                      vm.blaNo = $stateParams.obj.blaNo;
                      vm.schemeCode = $stateParams.obj.schemeCode;
                      vm.schemeName = $stateParams.obj.schemeName;
                      vm.panNo = $stateParams.obj.panNo;
                      vm.closureType = $stateParams.obj.closureType;
                    vm.getModuleNameVer('D');
                         vm.getGuardianDetailsMetaData();
                     }
         }else if($state.current.name == 'rbi_bond_sub_acc_closure_on_matur_death.inquiry_detail') {
                   	if($stateParams.obj){
                       vm.boSubsId = $stateParams.obj.boSubsId;
                       vm.blaNo = $stateParams.obj.blaNo;
                       vm.schemeName = $stateParams.obj.schemeName;
                       vm.schemeCode = $stateParams.obj.schemeCode;
                        vm.closureType = $stateParams.obj.closureType;
                        vm.panNoName = $stateParams.obj.panNoName;
                        vm.name = $stateParams.obj.name;
                       vm.inquiry('D');
                       vm.getGuardianDetailsMetaData();
                   	}
         }



function getTotalSharePer() {
      vm.totSharePercenValue = 0;
      for (var i = 0; i < vm.nomineeDetailsList.length; i++) {
        if (typeof vm.nomineeDetailsList[i].sharePercentage != "undefined") {
          vm.totSharePercenValue = parseInt(vm.totSharePercenValue) + parseInt(vm.nomineeDetailsList[i].sharePercentage);
        } else {
          vm.totSharePercenValue = parseInt(vm.totSharePercenValue) + parseInt(0);
        }
      }

      if (vm.totSharePercenValue) {
        if (vm.totSharePercenValue != 100) {
          vm.shareTotalPerCutOffErrMsg = "Total Share Percentage should be 100";
          vm.totSharePercenValue = "";
        } else {
          vm.shareTotalPerCutOffErrMsg = "";
        }
      } else {
        vm.shareTotalPerCutOffErrMsg = "";
      }
    }

    vm.getLabelFromValuesArray1 = function(value, list) {
      var found = list.find(item => item.value === value);
      return found ? found.label : value; // Return label if found, else return value
    };
                  function getAddDetails(flag){
                              vm.loader = true;

                            if(flag == "scheme"){
                                                            var requestData = {
                                                                'flag' : flag,
                                                                'schemeCode' : null,
                                                                'panNo'  : null,
                                                                'blaNo' : null,
                                                                'boSubsId' : null,

                                                            };
                                                            bondSubAccCloseOnMaturDeathService.getAddDetails(requestData).then(function (response) {
                                                                vm.loader = false;
                                                                vm.schemeList = response.data.result;
                                                                angular.element('#schemeModel').modal('toggle');

                                                            }, function (error) {
                                                                handleErrorResponse(error);
                                                                vm.loader = false;
                                                            });
                                                           }
                                if(flag == "pan"){
                                                            var requestData = {
                                                                'flag' : flag,
                                                                'schemeCode' : vm.schemeCode,
                                                                'panNo'  : null,
                                                                'blaNo' : null,
                                                                'boSubsId' : null,

                                                            };
                                                            bondSubAccCloseOnMaturDeathService.getAddDetails(requestData).then(function (response) {
                                                                vm.loader = false;
                                                                vm.panNumberDetailList = response.data.result;
                                                                angular.element('#panNoModal').modal('toggle');

                                                            }, function (error) {
                                                                handleErrorResponse(error);
                                                                vm.loader = false;
                                                            });
                                                           }

                             if(flag == "bla"){
                              var requestData = {
                                  'flag' : flag,
                                  'schemeCode' : vm.schemeCode,
                                  'panNo'  : vm.panNumber,
                                  'blaNo' : null,
                                  'boSubsId' : null,

                              };
                              bondSubAccCloseOnMaturDeathService.getAddDetails(requestData).then(function (response) {
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
                                 'schemeCode' : vm.schemeCode,
                                 'panNo'  : vm.panNumber,
                                 'blaNo' : vm.blaNo,
                                 'boSubsId' : null
                                 };
                              bondSubAccCloseOnMaturDeathService.getAddDetails(requestData).then(function (response) {
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
                                   'schemeCode' : vm.schemeCode,
                                   'panNo'  : vm.panNumber,
                                   'blaNo' : vm.blaNo,
                                   'boSubsId' : vm.boSubsId,
                                   'proceedsToBePaid' : vm.proceedsToBePaid,
                                   'closureType' : 'OM002'
                                  };
/*
                                 if(vm.proceedsToBePaid == "L"){
                                 requestData.nomineeDetailList  = vm.legalHeirsDetailsList
                                 } else {
                                 requestData.nomineeDetailList = vm.nomineeDetailsList
                                 }*/
                                 bondSubAccCloseOnMaturDeathService.getAddDetails(requestData).then(function (response) {
                                 vm.loader = false;
                               vm.nomineeDetailsListt = response.data.result;

                               // Initialize the nomineeDetailsList if it's not already initialized


                                vm.nomineeDetailsList = [];

                               vm.nomineeDetailsListt.forEach(function(heir) {
                                   // Push the formatted data into requestData.vm.nomineeDetailsList
                                   vm.nomineeDetailsList.push({
                                       nomineeName: heir.nomineeName,
                                      dateOfBirthOfMinor :moment(heir.dateOfBirthOfMinor, "DD-MM-YYYY").toDate() // Formatting the date
                                   });
                               });


                                  }, function (error) {
                                       handleErrorResponse(error);
                                       vm.loader = false;
                                       vm.proceedsToBePaid='';
                                       $state.go('rbi_bond_sub_acc_closure_on_matur_death.add')
                                     });
                            }

                  }







                  function parseDateFromString(dateString) {
                      const dateParts = dateString.split('/');
                      if (dateParts.length === 3) {
                          const day = dateParts[0];
                          const month = dateParts[1] - 1; // Months are zero-indexed in JavaScript
                          const year = dateParts[2];

                          // Return a new Date object
                          return new Date(year, month, day);
                      }
                      return null; // Return null if the date format is invalid
                  }

                  function getRemarksList() {
                                    vm.referenceId = '056';
                                    var requestData = { 'referenceId': vm.referenceId };

                                    bondSubAccCloseOnMaturDeathService.getRemarksList(requestData).then(function(response) {
                                        vm.remarksList = response.data.result || []; // Ensure it's an array
                                    }, function(error) {
                                        handleErrorResponse(error);
                                    });
                              }
function calculateSum() {
      vm.total = 0;
      for (var i = 0; i < vm.nomineeDetailsList.length; i++) {
        vm.total = vm.total + parseFloat(vm.nomineeDetailsList[i].sharePercentage ? vm.nomineeDetailsList[i].sharePercentage : "0");
      }
    }
                  function getModuleNameVer(flag){
                                    vm.loader = true;
                                    var requestData = {};
                                    if(flag == 'S'){
                                        requestData.schemeCode = null;
                                        requestData.detailsOrSubmit='scheme';
                                        requestData.flag='S';
                                    } else if (flag == 'B'){
                                          requestData.schemeCode = vm.schemeCode;
                                          requestData.panNo = vm.panNo;
                                          requestData.detailsOrSubmit='scheme';
                                          requestData.flag='bla';

                                    } else if (flag == 'P'){
                                            requestData.schemeCode = vm.schemeCode;
                                            requestData.detailsOrSubmit='scheme';
                                            requestData.flag='pan';
                                      } else if (flag == 'BS'){
                                            requestData.schemeCode = vm.schemeCode;
                                          requestData.panNo = vm.panNo;
                                          requestData.blaNo = vm.blaNo;
                                        requestData.flag='boSubsId';

                                            requestData.detailsOrSubmit='scheme'
                                      } else if (flag == 'D'){
                                        requestData.flag='det';
                                          requestData.schemeCode = vm.schemeCode;
                                          requestData.detailsOrSubmit='scheme'
                                          requestData.closureType = 'OM002'
                                          requestData.blaNo = vm.blaNo != null ? vm.blaNo : null;
                                          requestData.panNo = vm.panNo != null ? vm.panNo : null;
                                          requestData.boSubsId = vm.boSubsId != null ? vm.boSubsId : null;
                                    }
                                bondSubAccCloseOnMaturDeathService.getVerifyDeleteDetails(requestData).then(function (response) {
                                      vm.loader = false;
                                      vm.schemeList = response.data.result;
                                       if(flag=='S')
                                           angular.element('#schemeModel').modal('toggle');
                                       else if (flag =='B'){
                                              angular.element('#blaModel').modal('toggle');
                                       }  else if (flag =='BS'){
                                           angular.element('#bondSubsIdSearch').modal('toggle');
                                    }
                                    else if (flag =='P'){
                                          angular.element('#panNoModal').modal('toggle');
                                   } else if (flag == 'D'){
                                    vm.loader = false;
                                 //                                vm.nomineeDetailsList = response.data.result;
                                                                 vm.blaDetails = response.data.result.blaDetailsList;
                                                                 vm.bondDetails = response.data.result.bondSubsList;
                                                                 vm.nomineeDetailsList = response.data.result.nomineeDetailList;

                                                                 calculateSum();
                                                                 vm.ddPoAccNum = response.data.result.ddPoAccNum;
                                                                  vm.totalAmt= vm.bondDetails.amtPaid;

                                                                  vm.bondDetails.dodBondHolder =$rootScope.formatDateToDDMMMYYYY(convertToDateObject(vm.bondDetails.dodBondHolder));
                                                                  vm.bondDetails.dateOfReceiptDod =$rootScope.formatDateToDDMMMYYYY(convertToDateObject(vm.bondDetails.dateOfReceiptDod));
                                                                  vm.bondDetails.subsDate = $rootScope.formatDateToDDMMMYYYY(convertToDateObject(vm.bondDetails.subsDate));
                                                                  vm.bondDetails.maturityDate = $rootScope.formatDateToDDMMMYYYY(convertToDateObject(vm.bondDetails.maturityDate));
                                 //vm.bondDetails.intCalFromDate = $rootScope.formatDateToDDMMMYYYY(convertToDateObject(vm.bondDetails.intCalFromDate));
                                 //vm.bondDetails.intCalToDate = $rootScope.formatDateToDDMMMYYYY(convertToDateObject(vm.bondDetails.intCalToDate));
                    vm.auditInfo= response.data.result;

                                       }
                                   }, function (error) {
                                       handleErrorResponse(error);
                                       $state.go('rbi_bond_sub_acc_closure_on_matur_death.inquiry');
                                       vm.loader = false;
                                });
                              }
       function getAddValidation(){
                              vm.loader = true;

/*if (!vm.legalHeirsDetailsList || vm.legalHeirsDetailsList.length === 0) {
        alert('No legal heirs added');
        return;
    }*/

                                 var requestData = {
                                   'schemeCode' : vm.schemeCode,
                                   'panNo'  : vm.panNumber,
                                   'blaNo' : vm.blaNo,
                                   'boSubsId' : vm.boSubsId,
                                   'proceedsToBePaid' : vm.proceedsToBePaid,
                                   'closureType' : 'OM002',
                                    'nomineeDetails': []

                                  };
 vm.legalHeirsDetailsList.forEach(function(heir) {
        requestData.nomineeDetails.push({
            nomineeName: heir.nomineeName,
            panNo: heir.panNo,
            dateOfBirthOfMinor: $filter("date")(heir.dateOfBirthOfMinor, "dd-MMM-yyyy") // assuming this is a date string
        });
    });


    vm.nomineeDetailsList.forEach(function(heir) {
            requestData.nomineeDetails.push({
                nomineeName: heir.nomineeName,
                panNo: heir.panNo,
                dateOfBirthOfMinor: $filter("date")(heir.dateOfBirthOfMinor, "dd-MMM-yyyy") // assuming this is a date string
            });
        });
                                /* if(vm.proceedsToBePaid == "L"){
                                 requestData.nomineeDetails  = vm.legalHeirsDetailsList
                                 } else {
                                 requestData.nomineeDetails = vm.nomineeDetailsList
                                 }*/
                                 bondSubAccCloseOnMaturDeathService.getAddValidation(requestData).then(function (response) {
                                 vm.loader = false;
//                                vm.nomineeDetailsList = response.data.result;
                                vm.blaDetails = response.data.result.blaDetailsList;
                                vm.bondDetails = response.data.result.bondSubsList;
                                vm.nomineeDetailsList = response.data.result.nomineeDetailList;
                                vm.legalHeirsDetailsList1 = response.data.result.nomineeDetailList;
                                if(vm.proceedsToBePaid == "L"){
                                vm.legalHeirsDetailsList1[0].sharePercentage = '100';
}
//                                vm.legalHeirsDetailsList1[0].sharePercentage

                                calculateSum();
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
                                       console.error('Error Status:', error.status);
                                       console.error('Error Data:', error.data);
                                       $state.go('rbi_bond_sub_acc_closure_on_matur_death.add')
                                     });


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
                                    vm.message = "Invalid Account Number";
                                    x.accBranch='';

//                                    Swal.fire("Warning!",vm.message, "warning");


                                    }
                                    else{
                                    vm.message = '';
                                    x.accBranch =vm.accValidate[0].solID;
                                    }

                                    }, function (err) {
                                                                                handleErrorResponse(err);
                                                                                vm.loader = false;
                                                                                });


                    }
                  }

                  else if(x.modeOfInterestPay =='MP002'){
                  vm.message = "";
                  x.accNum = vm.ddPoAccNum;
                  x.accBranch='';
                  }

                  }


// finacle call to get the branch id for ddpo acc num
 function getCbsCustDetails(accountNumber) {
      vm.loader = true;
      const accountNumberPattern = /^\d{10,15}$/;

      if (!accountNumberPattern.test(accountNumber)) {
        Swal.fire("Account number must be numeric and between 10 to 15 digits.");
        vm.loader = false;
                  vm.operativeAccBranchId=101;
                  x.accNum='';
        return;
      }

      bondSubAccCloseOnMaturDeathService.fetchCbsCustId(accountNumber).then(
        (response) => {
          vm.loader = false;
          vm.cbsDetail = response.data.result.responseString.customerDetailsDTO[0];


          vm.customerMasterDetails.operativeAccBranchId = vm.cbsDetail.operativeBranchId;
        },
        function (error) {
          vm.loader = false;
          vm.errmsgSingle = "CBS Failure";
          handleErrorResponse(error);
          vm.customerMasterDetailsTwo.cbsCustId = "";
        }
      );
    }

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




                                         function getModuleName() {
                                                    vm.loader = true;

                                                    var requestData = {
                                                    };
                                                    bondSubAccCloseOnMaturDeathService.getRbiBondModuleNameDetails(requestData).then(function (response) {
                                                        vm.loader = false;
                                                        vm.schemeList = response.data.result;
                                                        angular.element('#schemeModel').modal('toggle');
                                                    }, function (error) {
                                                        handleErrorResponse(error);
                                                        vm.loader = false;
                                                    });
                                                }


function rejectReason() {
             angular.element('#permissionMappingDelete').modal('toggle');
            }




          function addClosureOnMaturDeath(){


                         Swal.fire({
                    			title: "Are you sure?",
                   				text: "Do you want to Really Add this record?",
                   				type: "warning",
                   				showCancelButton: true,
                   				cancelButtonClass: "btn-cancel",
                   				confirmButtonClass: "btn-primary",
                   				confirmButtonText: "Yes",
                   				closeOnConfirm: false
                   			}, function () {



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
 vm.bondDetails.closureType = 'OM002';
 vm.bondDetails.schemeCode = vm.schemeCode;
 vm.bondDetails.modeOfHoldingCode = vm.blaDetails.modeOfHoldingCode;
vm.bondDetails.dodBondHolder = vm.bondDetails.dodBondHolder;
vm.bondDetails.dateOfReceiptDod = vm.bondDetails.dateOfReceiptDod;
vm.bondDetails.proceedsToBePaid =vm.proceedsToBePaid;
//vm.bondDetails.penalityToBeRecovered = vm.penalityToBeRecovered;
if(vm.proceedsToBePaid=='L'){
vm.legalHeirsDetailsList1[0].nomineeName=vm.legalHeirsDetailsList[0].nomineeName;
vm.legalHeirsDetailsList1[0].nomineeDOB=vm.legalHeirsDetailsList[0].dateOfBirthOfMinor;
vm.legalHeirsDetailsList1[0].panNo=vm.legalHeirsDetailsList[0].panNo;
vm.legalHeirsDetailsList1[0].shareAmountSettled=vm.bondDetails[0].totalAmtToBePaid;
}
 var requestData ={
'schemeCode' : vm.schemeCode,
    'panNo'  : vm.panNumber,
    'blaNo' : vm.blaNo,
    'boSubsId' : vm.boSubsId,
    'proceedsToBePaid' : vm.proceedsToBePaid,
    'closureType' : 'OM002',
    'closureRemarks' : vm.bondDetails.closureRemarks,
     'nomineeDetails': []
     }

     if(vm.proceedsToBePaid=='N'){
vm.nomineeDetailsList.forEach(function(heir) {
            requestData.nomineeDetails.push({
                nomineeName: heir.nomineeName,
                nomineeDOB: $filter("date")(heir.nomineeDOB, "dd-MMM-yyyy") ,
                panNo: heir.panNo,
                relationShip: heir.relationShip,
                sharePercentage: heir.sharePercentage,
                shareAmountSettled: heir.shareAmountSettled,
                modeOfInterestPay: heir.modeOfInterestPay,
                accNum: heir.accNum,
                remarks: heir.remarks
            });
        });
        } else {
        vm.legalHeirsDetailsList1.forEach(function(heir) {
                    requestData.nomineeDetails.push({
                        nomineeName: heir.nomineeName,
                        nomineeDOB: $filter("date")(heir.nomineeDOB, "dd-MMM-yyyy") ,
                        panNo: heir.panNo,
                        relationShip: heir.relationShip,
                        sharePercentage: heir.sharePercentage,
                        shareAmountSettled: heir.shareAmountSettled,
                        modeOfInterestPay: heir.modeOfInterestPay,
                        accNum: heir.accNum,
                        remarks: heir.remarks
                    });
                });
        }
   /* if(vm.proceedsToBePaid == 'N'){
  requestData.nomineeDetails = vm.nomineeDetailsList;
  } else {
  requestData.nomineeDetails = vm.legalHeirDeatailList;
  }*/

 bondSubAccCloseOnMaturDeathService.addClosureOnMaturDeath(requestData).then(function (response){


 vm.loader = false;

 if(response.data.message =='SUCCESS'){

 vm.gbmTranId = response.data.result;

Swal.fire("Added!","Bond Account Closure on Maturity Added Successfully", "success");
$state.go('rbi_bond_sub_acc_closure_on_matur_death.inquiry');
 }else{
 Swal.fire("Error!",resultset.msg, "error");
 }
}, function (error) {
          handleErrorResponse(error);
          vm.loader = false;
      });

})
}




















                  function getVerifyDeleteDetails(flag){
                             vm.loader = true;




                                                                      if(flag == "pan"){
                                                                                         var requestData = {
                                                                                             'flag' : flag,
                                                                                             'schemeCode' : vm.schemeCode,
                                                                                             'panNo'  : null,
                                                                                             'blaNo' : null,
                                                                                             'boSubsId' : null,

                                                                                         };
                                                                                         bondSubAccCloseOnMaturDeathService.getAddDetails(requestData).then(function (response) {
                                                                                             vm.loader = false;
                                                                                             vm.panNumberDetailList = response.data.result;
                                                                                             angular.element('#panNoModal').modal('toggle');

                                                                                         }, function (error) {
                                                                                             handleErrorResponse(error);
                                                                                             vm.loader = false;
                                                                                         });
                                                                                        }











                                                         if(flag == "bla"){

                                                          var requestData = {
                                                              'flag' : flag,
                                                              'schemeCode' : vm.schemeCode,
                                                              'panNo'  : vm.panNumber,
                                                              'blaNo' : null,
                                                              'boSubsId' : null

                                                          };
                                                          bondSubAccCloseOnMaturDeathService.getVerifyDeleteDetails(requestData).then(function (response) {
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
                                                             'schemeCode' : vm.schemeCode,
                                                             'panNo'  : vm.panNumber,
                                                             'blaNo' : vm.blaNo,
                                                             'boSubsId' : null
                                                             };
                                                          bondSubAccCloseOnMaturDeathService.getVerifyDeleteDetails(requestData).then(function (response) {
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
                        'schemeCode' : vm.schemeCode,
                        'panNo'  : vm.panNumber,
                        'blaNo' : vm.blaNo,
                        'boSubsId' : vm.boSubsId
                    };
                    bondSubAccCloseOnMaturDeathService.getVerifyDeleteDetails(requestData).then(function (response) {
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
                    vm.auditInfo= response.data.result;
                    }, function (error) {
                    handleErrorResponse(error);
                    vm.loader = false;
                    $state.go('rbi_bond_sub_acc_closure_on_matur_death.verify')
                    });
                    }

                  }

                  function getPanValidation() {
                                vm.loader = true;
                                var requestData = {};
                                 vm.list=[];
                                 vm.singleList=[];
                                   if(vm.proceedsToBePaid == 'N'){
                                  vm.nomineeDetailsList.forEach(item => {
                                          if (item.panNo) { // Ensure the panNo is not empty or undefined
                                              vm.list.push({  nomineeName: item.nomineeName,
                                                                        dateOfBirthOfMinor: $filter("date")(item.dateOfBirthOfMinor, "dd-MMM-yyyy"),
                                                                        panNo: item.panNo});
                                          }
                                      });
                                      }else{
                                  vm.singleList.push({panNo: vm.legalHeirsDetailsList[0].panNo,
                                  nomineeName: vm.legalHeirsDetailsList[0].nomineeName,
                                  dateOfBirthOfMinor: $filter("date")(vm.legalHeirsDetailsList[0].dateOfBirthOfMinor, "dd-MMM-yyyy")});
                                  }
                                 requestData.schemeCode = vm.schemeCode;
                                 requestData.proceedsToBePaid=vm.proceedsToBePaid;
                                 requestData.detailsOrSubmit='valid';

                                 if(vm.proceedsToBePaid == 'L'){
                                requestData.panNumList = vm.singleList;
                                } else{
                                requestData.panNumList = vm.list;
                                }
                                bondSubAccCloseOnMaturDeathService.getPanValidation(requestData).then(function (response) {
                                 vm.loader = false;
                                 vm.schemeList = response.data.result;
                                 vm.panValidationMessage = response.data.message;
                                 vm.panValid = false;
                                 if (response.data.message === 'SUCCESS') {
                                     vm.PanNo = response.data.result;
                                     Swal.fire("Success!", "PAN Validation Success", 'success');
                                 } else if (response.data.message === 'FAILURE') {
                                     let failureMessage = "PAN Validation Failed for the following PANs:\n";
                                     response.data.result.forEach(panStatus => {
                                         if (panStatus.status === 'FAILURE') {
                                             failureMessage += `- ${panStatus.pan}\n`;
                                         }
                                     });
                                     Swal.fire("Warning!", failureMessage, 'error');
                                 } }, function (error) {
                if(error.data.message.toLowerCase().startsWith('trigger')){
                    vm.panValid = false;
                    handleErrorResponse(error);
                    vm.loader = false;
                    }
                    else{
                    vm.loader = false;
                    vm.panValid = false;
                    handleErrorResponse(error);
                    }
                    });
                   }


                  function getInquiryDetails(flag){
                              vm.loader = true;

                                                                                         if(flag == "pan"){
                                                                                                                           var requestData = {
                                                                                                                               'flag' : flag,
                                                                                                                               'schemeCode' : vm.schemeCode,
                                                                                                                               'panNo'  : null,
                                                                                                                               'blaNo' : null,
                                                                                                                               'boSubsId' : null,

                                                                                                                           };
                                                                                                                       bondSubAccCloseOnMaturDeathService.getAddDetails(requestData).then(function (response) {
                                                                                                                           vm.loader = false;
                                                                                                                           vm.panNumberDetailList = response.data.result;
                                                                                                                           angular.element('#panNoModal').modal('toggle');

                                                                                                                       }, function (error) {
                                                                                                                           handleErrorResponse(error);
                                                                                                                           vm.loader = false;
                                                                                                                       });
                                                                                                                      }

                             if(flag == "bla"){

                              var requestData = {
                                  'flag' : flag,
                                  'schemeCode' : vm.schemeCode,
                                  'panNo'  : vm.panNumber,
                                  'blaNo' : null,
                                  'boSubsId' : null

                              };
                              bondSubAccCloseOnMaturDeathService.getInquiryDetails(requestData).then(function (response) {
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
                                 'schemeCode' : vm.schemeCode,
                                 'panNo'  : vm.panNumber,
                                 'blaNo' : vm.blaNo,
                                 'boSubsId' : null
                                 };
                              bondSubAccCloseOnMaturDeathService.getInquiryDetails(requestData).then(function (response) {
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
                                   'schemeCode' : vm.schemeCode,
                                   'panNo'  : vm.panNumber,
                                   'blaNo' : vm.blaNo,
                                   'boSubsId' : vm.boSubsId
                                  };
                                 bondSubAccCloseOnMaturDeathService.getInquiryDetails(requestData).then(function (response) {
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
                                       $state.go('rbi_bond_sub_acc_closure_on_matur_death.inquriy')
                                     });
                            }

                  }






          function deleteClosureBefMaturDeath(){


                         Swal.fire({
                    			title: "Are you sure?",
                   				text: "Do you want to Really Delete this record?",
                   				type: "warning",
                   				showCancelButton: true,
                   				cancelButtonClass: "btn-cancel",
                   				confirmButtonClass: "btn-primary",
                   				confirmButtonText: "Yes",
                   				closeOnConfirm: false
                   			}, function () {



                      vm.loader = true;


 var requestData ={
  'schemeCode' : vm.schemeCode,
  'boSubsId' : vm.boSubsId
 }
 bondSubAccCloseOnMaturDeathService.deleteClosureBefMaturDeath(requestData).then(function (response){


 vm.loader = false;

 if(response.data.message =='SUCCESS'){

Swal.fire("DELETED!","Bond Account Closure on Maturity Deleted Successfully.", "success");
$state.go('rbi_bond_sub_acc_closure_on_matur_death.inquiry');
 }else{
 Swal.fire("Error!",resultset.msg, "error");
 }
}, function (error) {
          handleErrorResponse(error);
          vm.loader = false;
      });

})
}


        function deleteSubmit(closureRemarks, remarks){
                            Swal.fire({
                                     title: "Are you sure?",
                                     text: "Do you want to Really Reject this record?",
                                     type: "warning",
                                     showCancelButton: true,
                                     cancelButtonClass: "btn-cancel",
                                     confirmButtonClass: "btn-primary",
                                     confirmButtonText: "Yes",
                                     closeOnConfirm: false,
                                   },
                              function () {
                                    vm.loader = true;
                                    var requestData = {};
                                    requestData.schemeCode = vm.schemeCode;
                                    requestData.detailsOrSubmit='reject'
                                    requestData.closureType='OM002'
                                     requestData.blaNo = vm.blaNo ;
                                     requestData.panNo = vm.panNo ;
                                     requestData.boSubsId = vm.boSubsId ;
                                                           requestData.remarks = remarks;
                                   bondSubAccCloseOnMaturDeathService.getVerifyDeleteDetails(requestData).then(function(response) {
                                       vm.loader = false;
                                       Swal.fire("Rejected!","Rejected Successfully","success");
                                        $state.go('rbi_bond_sub_acc_closure_on_matur_death.inquiry');
                                   }, function(error) {
                                       vm.loader = false;
                                       if(error.data.message == 'No Records Found' | error.data.message == 'NDF'){
                                         vm.noDataFound = error.data.message;
                                         Swal.fire("Warning!",vm.noDataFound,"warning");
                                       }
                                     else{
                                        handleErrorResponse(error);
                                        $state.go('rbi_bond_sub_acc_closure_on_matur_death.verify');
                                     }
                                   });
                               });
                    }
 function verifySubmit(closureRemarks){
                Swal.fire({
                        title: "Are you sure?",
                        text: "Do you want to Really Verify this record?",
                        type: "warning",
                        showCancelButton: true,
                        cancelButtonClass: "btn-cancel",
                        confirmButtonClass: "btn-primary",
                        confirmButtonText: "Yes",
                        closeOnConfirm: false
                    },
                    function() {
                        vm.loader = true;
                        var requestData = {};
                         requestData.schemeCode = vm.schemeCode;
                         requestData.detailsOrSubmit='verify'
                         requestData.closureType='OM002'
                           requestData.blaNo = vm.blaNo ;
                           requestData.panNo = vm.panNo ;
                           requestData.boSubsId = vm.boSubsId ;



//                         requestData.verifyArrayObject= constructJsonList(listOfRecords);
                         requestData.remarks = closureRemarks;
                        bondSubAccCloseOnMaturDeathService.getVerifyDeleteDetails(requestData).then(function(response) {
                            vm.loader = false;
                            Swal.fire("Verified!", "Verified Successfully","success");
                            $state.go('rbi_bond_sub_acc_closure_on_matur_death.inquiry');
                        }, function(error) {
                            vm.loader = false;
                            if(error.data.message == 'No Records Found' | error.data.message == 'NDF'){
                              vm.noDataFound = error.data.message;
                              Swal.fire("Warning!",vm.noDataFound,"warning");
                            }
                          else{
                          handleErrorResponse(error);
                          $state.go("rbi_bond_sub_acc_closure_on_matur_death.verify");
                          }
                        })
                });
            }

              function uploadFile(textFile){
                              vm.loader = true;
                              var fd = new FormData();
                              let formattedDate = $filter('date')(vm.paymentDate, "dd-MMM-yyyy");
                              fd.append('textFile', textFile);
                              fd.append('schemeCode',vm.schemeCode);
                              fd.append('paymentDate', formattedDate);

                         bondSubAccCloseOnMaturDeathService.uploadTextFile(fd).then(function (response) {
                                       vm.loader = false;
                                       vm.responseMessage = response.data.message;
                                       if(vm.responseMessage == 'SUCCESS'){
                                            Swal.fire("Success!"," Payment File Uploaded Successfully. ", "success");
                                       }
                                        }, function (response){
                                            Swal.fire("Error!", response.data.message, "error");
                                            $state.go('rbi_bond_sub_acc_closure_on_matur_death.inquiry');
                         });
                        }


                        function inquiry(flag) {
                                        vm.loader = true;
                                        var requestData = {};
                                        if(flag == 'S'){
                                            requestData.schemeCode = null;
                                        } else if (flag == 'B'){
                                            requestData.schemeCode = vm.schemeCode;
                                            requestData.flag = 'B';
                                            requestData.name = vm.name != null ? vm.name : null ;
                                            requestData.panNoName = vm.panNoName;
                        //                    requestData.closureType='BM001'
                                        }else if (flag == 'BS'){
                                                                                     requestData.schemeCode = vm.schemeCode;
                                                                                     requestData.flag = 'BS';
                                                                                     requestData.name = vm.name != null ? vm.name : null ;
                                                                                     requestData.panNoName = vm.panNoName;
                                                                                     requestData.blaNo = vm.blaNo;

                                                                                 }
                                        else if (flag == 'P'){
                                            requestData.schemeCode = vm.schemeCode;
                                            requestData.flag = 'P';
                                            requestData.name = vm.name != null ? vm.name : null ;
                                            requestData.panNoName = vm.panNoName != null ? vm.panNoName : null ;
                        //                    requestData.closureType='BM001'
                                        } else {
                                            requestData.schemeCode = vm.schemeCode;
                                            requestData.flag = 'D';
                                            requestData.name = vm.name ;
                                            requestData.panNoName = vm.panNoName;
                                            requestData.closureType = 'OM002';
                                            requestData.blaNo = vm.blaNo;
                                            requestData.boSubsId = vm.boSubsId;

                                        }
                                        bondSubAccCloseOnMaturDeathService.getInquiryDetails(requestData).then(function (response) {
                                            vm.loader = false;
                                            if(flag=='S'){
                                                vm.schemeList = response.data.result;
                                                angular.element('#schemeModel').modal('toggle');
                                            }else if (flag == 'B'){
                                                vm.blaDataList = response.data.result;
                                                angular.element('#blaModel').modal('toggle');
                                            }else if (flag == 'P'){
                                                vm.PanTorch = response.data.result;
                                                angular.element('#panModel').modal('toggle');
                                            }else if (flag == 'BS'){
                                                                                             vm.bondSubsList = response.data.result;
                                                                                             angular.element('#bondSubsIdSearch').modal('toggle');
                                                                                         }
                                                                                         else {
                                                   vm.loader = false;
                                                //                                vm.nomineeDetailsList = response.data.result;
                                                                                vm.blaDetails = response.data.result.blaDetailsList;
                                                                                vm.bondDetails = response.data.result.bondSubsList;
                                                                                vm.nomineeDetailsList = response.data.result.nomineeDetailList;

                                                                                calculateSum();
                                                                                vm.ddPoAccNum = response.data.result.ddPoAccNum;
                                                                                 vm.totalAmt= vm.bondDetails.amtPaid;

                                                                                 vm.bondDetails.dodBondHolder =$rootScope.formatDateToDDMMMYYYY(convertToDateObject(vm.bondDetails.dodBondHolder));
                                                                                 vm.bondDetails.dateOfReceiptDod =$rootScope.formatDateToDDMMMYYYY(convertToDateObject(vm.bondDetails.dateOfReceiptDod));
                                                                                 vm.bondDetails.subsDate = $rootScope.formatDateToDDMMMYYYY(convertToDateObject(vm.bondDetails.subsDate));
                                                                                 vm.bondDetails.maturityDate = $rootScope.formatDateToDDMMMYYYY(convertToDateObject(vm.bondDetails.maturityDate));
                                                //vm.bondDetails.intCalFromDate = $rootScope.formatDateToDDMMMYYYY(convertToDateObject(vm.bondDetails.intCalFromDate));
                                                //vm.bondDetails.intCalToDate = $rootScope.formatDateToDDMMMYYYY(convertToDateObject(vm.bondDetails.intCalToDate));
                    vm.auditInfo= response.data.result;

                                            }

                                        }, function (error) {
                                            handleErrorResponse(error);
                                            $state.go('rbi_bond_sub_acc_closure_on_matur_death.inquiry');
                                            vm.loader = false;
                                        });
                                    }
  function downloadFile(){
                 vm.loader = true;
                   var requestData = {};
                   requestData.schemeCode = vm.schemeCode;
                   requestData.paymentDate = $filter('date')(vm.paymentDate, "dd-MMM-yyyy");
                bondSubAccCloseOnMaturDeathService.downloadFile(requestData).then(function (response) {
                    vm.loader = false;
                    vm.message = response.data.message;
                    if(vm.message == 'SUCCESS'){
                        vm.fileName = response.data.result.fileName;
                        vm.fileData = response.data.result.fileData;
                        saveAs(getBlobFromBase64String(vm.fileData), vm.fileName);
                        Swal.fire("Success!", vm.fileName +"\n Downloaded Successfully", "success");
                    } else{
                        Swal.fire("Warning!",vm.message,"warning");
                    }
                    }, function (error) {
                      vm.loader = false;
                    if (error.data.message == "No Data Found" || error.data.message == "NDF") {
                      vm.noDataFound = error.data.message;
                      Swal.fire("Warning!", vm.noDataFound, "warning");
                        $state.go('rbi_bond_sub_acc_closure_on_matur_death.inquiry');
                      } else {
                        handleErrorResponse(error);
                        $state.go('rbi_bond_sub_acc_closure_on_matur_death.inquiry');
                        }
                     }
                );
            }









                                                                        vm.textableDatePicker=textableDatePicker;
                                                                                 vm.autoslashing=autoslashing;
                                                                                 vm.futureNotAllowed=futureNotAllowed;


                                                                         function textableDatePicker(id,valueDate)
                                                                         {
                                                                          commonService.textableDatePicker(id,valueDate);
                                                                         }


                                                                         function autoslashing(id)

                                                                         {
                                                                          commonService.autoslashing(id)
                                                                         ;
                                                                         }



                                                                         function futureNotAllowed(id,valueDate)
                                                                         {
                                                                          commonService.futureNotAllowed(id,valueDate);
                                                                         }



//        function getModuleName() {
//                       vm.loader = true;
//                       vm.referenceId = 'TAX';
//                       var requestData = {
//                           'referenceId': vm.referenceId
//                       };
//                       bondSubAccCloseOnMaturDeathService.getRbiBondModuleNameDetails(requestData).then(function (response) {
//                           vm.loader = false;
//                           vm.schemeList = response.data.result;
//                           vm.schemeName=response.data.result[0].subCodeDescription;
//                       }, function (error) {
//                           handleErrorResponse(error);
//                           vm.loader = false;
//                       });
//                   }

       } })();