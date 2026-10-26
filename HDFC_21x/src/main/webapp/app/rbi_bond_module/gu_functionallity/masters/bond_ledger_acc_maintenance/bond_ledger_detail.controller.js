(function() {

    'use strict';

    angular.module('gbmUltraApp').controller('bondLedgerAccMaintenanceController',
        bondLedgerAccMaintenanceController);

    bondLedgerAccMaintenanceController.$inject = ['$scope', '$filter', '$state', '$rootScope',
        '$stateParams', '$location', '$timeout', 'bondLedgerAccMaintenanceService', 'commonService'
    ];

    function bondLedgerAccMaintenanceController($scope, $filter, $state, $rootScope, $stateParams,
        $location, $timeout, bondLedgerAccMaintenanceService, commonService) {

		var vm = this;
		vm.showCancelConfirmation = showCancelConfirmation;
		//vm.getScssCommonMetaData = getScssCommonMetaData;

		vm.scssOtherBanksDetails = [{}];
		vm.customerMasterDetails=[];
		vm.customerMasterJointDetails=[];
		vm.customerMasterFormDetails={};
		vm.getState=getState;
		vm.getClearAllData=getClearAllData;
		vm.getClearAllDatas=getClearAllDatas;
		vm.getSolDetails=getSolDetails;
		vm.getSchemeTypeListOperative=getSchemeTypeListOperative;
		vm.getSchemeTypeListOperativeJoint=getSchemeTypeListOperativeJoint;
		vm.commonYesNoMetaData = COMMON_YES_NO_METADATA;
		vm.panPattern = /[a-zA-Z]{5}[0-9]{4}[a-zA-Z]{1}/;
        vm.emailPattern = /[^@]+@[^@]+\.[a-zA-Z]{2,6}/;
        vm.optionToggled =optionToggled;
        vm.addRange = addRange;
        vm.checkAge = checkAge;
        vm.removeRange = removeRange;
        vm.titleChange = titleChange;
        vm.alert = alert;
        vm.getModeofHolding = getModeofHolding;
        vm.addcustomerMaster = addcustomerMaster;
        vm.getTdsApplicableRate = getTdsApplicableRate;
        vm.getModifyTdsApplicableRate = getModifyTdsApplicableRate;
        vm.getVerifyTdsApplicableRate = getVerifyTdsApplicableRate;
        vm.getCbsCustFstJoinDetails = getCbsCustFstJoinDetails;
        vm.getCbsCustScndJoinDetails = getCbsCustScndJoinDetails;
        vm.getBlaAccNoDetails = getBlaAccNoDetails;
        vm.getScssJointCustDetails = getScssJointCustDetails;
        vm.getCurrentFinancialYear = getCurrentFinancialYear;
        vm.getModifySelectDetails = getModifySelectDetails;
        vm.modifycustomerMaster = modifycustomerMaster;
        vm.getVerifySelectDetails = getVerifySelectDetails;
        vm.verifyCustomerMaster = verifyCustomerMaster;
        vm.deleteCustomerMaster = deleteCustomerMaster;
        vm.getInquirySelectDetails = getInquirySelectDetails;
        vm.depositDateValidation = depositDateValidation;
        vm.maturityDateValid = maturityDateValid;
        vm.maturityDateValidation = maturityDateValidation;
        vm.getValidations = getValidations;
        vm.accountHolderDateValidation = accountHolderDateValidation;
        vm.dateOfRetirementValid = dateOfRetirementValid;
        vm.getDisableTorchFlag = getDisableTorchFlag;
        vm.getTypeofHolding = getTypeofHolding;
        vm.getFormDetails = getFormDetails;
        vm.getKycDetails = getKycDetails;
        vm.getGuardianDetails = getGuardianDetails;
        vm.getGuardianDetailsMetaData = getGuardianDetailsMetaData;
        vm.getModuleName = getModuleName;
        vm.getModuleNameAdd = getModuleNameAdd;
        vm.formFifteenGValidation = formFifteenGValidation;
        vm.minAgeC=minAgeC;

        vm.getCbsCustFstJoinDetailsTwo = getCbsCustFstJoinDetailsTwo;
        vm.getBlaAccNoDetailsTwo = getBlaAccNoDetailsTwo;
        vm.getGuardianDetailsTwo = getGuardianDetailsTwo;
        vm.optionToggledTwo = optionToggledTwo;
        vm.formFifteenGValidationTwo = formFifteenGValidationTwo;

        vm.getCbsCustFstJoinDetailsThree = getCbsCustFstJoinDetailsThree;
        vm.getBlaAccNoDetailsThree = getBlaAccNoDetailsThree;
        vm.getGuardianDetailsThree = getGuardianDetailsThree;
        vm.optionToggledThree = optionToggledThree;
        vm.formFifteenGValidationThree = formFifteenGValidationThree;
        vm.getSchemeTypeListOperativeThree = getSchemeTypeListOperativeThree;
        vm.getSchemeTypeListOperativeTwo = getSchemeTypeListOperativeTwo;
        vm.convertLabelValue = convertLabelValue;
        vm.getCbsCustFstJoinDetailsModify = getCbsCustFstJoinDetailsModify;
        vm.getCbsCustFstJoinDetailsTwoModify = getCbsCustFstJoinDetailsTwoModify;
        vm.getCbsCustFstJoinDetailsThreeModify = getCbsCustFstJoinDetailsThreeModify;
        vm.getBlaAccNoDetailsModify = getBlaAccNoDetailsModify;
        vm.getBlaAccNoDetailsTwoModify = getBlaAccNoDetailsTwoModify;
        vm.getBlaAccNoDetailsThreeModify = getBlaAccNoDetailsThreeModify;
        vm.getGuardianDetailsModify = getGuardianDetailsModify;
        vm.getGuardianDetailsTwoModify = getGuardianDetailsTwoModify;
        vm.getGuardianDetailsThreeModify = getGuardianDetailsThreeModify;
        vm.formFifteenGValidationM = formFifteenGValidationM;
        vm.formFifteenGValidationTwoM = formFifteenGValidationTwoM;
        vm.formFifteenGValidationThreenM = formFifteenGValidationThreenM;
        vm.validateCount = validateCount;
        vm.orderArray = orderArray;
        vm.checkAge = checkAge;
        vm.dodRecVal = dodRecVal;
        vm.dodVal = dodVal;

        getCurrentFinancialYear();

        vm.loginSolDate = $rootScope.globals.currentUser.solDate;

        vm.genderListMetaData = [
            {label : "MALE",value : "M"},
            {label : "FEMALE",value : "F"},

        ];
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




        vm.supportingDocumentsMetaData = [
            {label : "No",value : 'FALSE'},
            {label : "Yes",value : "TRUE"},
        ];


		if($state.current.name == 'bond_ledger_acc_main.add_detail') {
		    if($stateParams.obj){
                vm.modeOfHolding = $stateParams.obj.modeOfHolding;
                vm.isNri = $stateParams.obj.isNri;
                vm.schemeCode = $stateParams.obj.schemeCode;
                vm.schemeName = $stateParams.obj.schemeName;
                vm.noOfJointAcc = parseInt($stateParams.obj.noOfJointAcc);
                if(vm.isNri == 'Y'){
                      Swal.fire("Error!", "NRI Cannot Open BOND LEDGER ACCOUNT", "error");
                       $state.go('bond_ledger_acc_main.add');
//                    if(vm.modeOfHolding == 'MH001' || vm.modeOfHolding == 'MH004' || vm.modeOfHolding == 'MH005'){
//
//                    }
                }
                getCurrentFinancialYear();
		        titleChange();
		        getTypeofHolding();
		        getFormDetails();
		        getKycDetails();
		        getGuardianDetailsMetaData();
		        getTdsApplicableRate();
		        getState();
		    }
		}else if($state.current.name == 'bond_ledger_acc_main.modify_detail') {
            if($stateParams.obj){
                vm.schemeCode = $stateParams.obj.schemeCode;
                vm.blaNo = $stateParams.obj.blaNo;
                vm.schemeName = $stateParams.obj.schemeName;
                getModifySelectDetails('select');
                getCurrentFinancialYear();
                getTypeofHolding();
                getFormDetails();
                getKycDetails();
                getGuardianDetailsMetaData();
                getTdsApplicableRate('M');
                getState();
            }
        }else if($state.current.name == 'bond_ledger_acc_main.verify_detail') {
             if($stateParams.obj){
                 vm.schemeCode = $stateParams.obj.schemeCode;
                 vm.blaNo = $stateParams.obj.blaNo;
                 vm.schemeName = $stateParams.obj.schemeName;
                 getVerifySelectDetails('select');
                 getCurrentFinancialYear();
                 getTypeofHolding();
                 getFormDetails();
                 getKycDetails();
                 getGuardianDetailsMetaData();
                 getModeofHolding();
                 getState();
             }
         }else if($state.current.name == 'bond_ledger_acc_main.delete_detail') {
               if($stateParams.obj){
                   vm.schemeCode = $stateParams.obj.schemeCode;
                   vm.blaNo = $stateParams.obj.blaNo;
                   vm.schemeName = $stateParams.obj.schemeName;
                   getVerifySelectDetails('select');
                   getCurrentFinancialYear();
                   getTypeofHolding();
                   getFormDetails();
                   getKycDetails();
                   getGuardianDetailsMetaData();
                   getModeofHolding();
                   getState();
               }
         }
         else if($state.current.name == 'bond_ledger_acc_main.list_detail') {
                if($stateParams.obj){
                    vm.blaNo = $stateParams.obj.blaNo;
                    vm.schemeCode = $stateParams.obj.schemeCode;
                    vm.schemeName = $stateParams.obj.schemeName;
                    getInquirySelectDetails('select');
                    getCurrentFinancialYear();
                    titleChange();
                    getTypeofHolding();
                    getFormDetails();
                    getKycDetails();
                    getGuardianDetailsMetaData();
                    getModeofHolding();
                    getState();
                }
          }

        function convertLabelValue(list){
            for(var item in list){
                list[item].value = list[item].SUBCODE;
                list[item].label = list[item].DESCRIPTION;
            }
            return list;
        }

        function formFifteenGValidation(value){
            if(vm.customerMasterDetails.bondHldDob){
                vm.customerMasterDetails.dateSubmission15g15htax = '';
                var year = parseInt($filter('date')(vm.customerMasterDetails.bondHldDob, "yyyy"));
                var cYear = parseInt($filter('date')(new Date(), "yyyy"));
                var age = cYear - year;
                            if(value == 'TF001'){
                                //15g
                                if(age > 60){
                                    Swal.fire("Error!", "Age of the holder is NOT BELOW 60 years", "error");
                                    vm.customerMasterDetails.form15g15htaxExeCerti = '';
                                }
                                vm.customerMasterDetails.tdsRateApplicable = '0';
                            }
                            if(value == 'TF002'){
                                //15h
                                if(age <= 60){
                                    Swal.fire("Error!", "Age of the holder is BELOW 60 years", "error");
                                    vm.customerMasterDetails.form15g15htaxExeCerti = '';
                                }
                                vm.customerMasterDetails.tdsRateApplicable = '0';
                            }
                            if(value == 'TF003'){
                                //tax excemption cert
                                vm.customerMasterDetails.tdsRateApplicable = '0';
                            }
                            if(value == 'NONE'){
                                    vm.customerMasterDetails.tdsRateApplicable = vm.tdsRateApplicableResponse;
                            }
            }else{
                 Swal.fire("Error!", "Please select a age", "error");
                  vm.customerMasterDetails.form15g15htaxExeCerti = '';
            }


        }

        function formFifteenGValidationM(value){
                    if(vm.customerMasterDetails.C013.oldValue){

                        var year = parseInt($filter('date')(vm.customerMasterDetails.C013.oldValue, "yyyy"));
                        var cYear = parseInt($filter('date')(new Date(), "yyyy"));
                        var age = cYear - year;
                                    if(value == 'TF001'){
                                        //15g
                                        if(age > 60){
                                            Swal.fire("Error!", "Age of the holder is NOT BELOW 60 years", "error");
                                            vm.customerMasterDetails.C038.oldValue = '';
                                        }
                                        vm.customerMasterDetails.C064.oldValue = '0';
                                    }
                                    if(value == 'TF002'){
                                        //15h
                                        if(age <= 60){
                                            Swal.fire("Error!", "Age of the holder is BELOW 60 years", "error");
                                            vm.customerMasterDetails.C038.oldValue = '';
                                        }
                                        vm.customerMasterDetails.C064.oldValue = '0';
                                    }
                                    if(value == 'TF003'){
                                        //tax excemption cert
                                        vm.customerMasterDetails.C064.oldValue = '0';
                                    }
                                    if(value == 'NONE'){
                                            try{
                                                vm.customerMasterDetails.C039.oldValue = '';
                                            }catch(e){}

                                            vm.customerMasterDetails.C064.oldValue = vm.tdsRateApplicableResponse;
                                    }
                    }else{
                         Swal.fire("Error!", "Please select a age", "error");
                          vm.customerMasterDetails.C038.oldValue = '';
                    }


                }

        /** Common MetaData **/
        /* request information
        */


      function alert(item){

                         for (let i = 0 ;i < vm.schemeList.length;i++){
                          if(vm.schemeList[i].subCode == item){
                                vm.schemeName = vm.schemeList[i].subCodeDescription;
                               //vm.cusName = vm.custList[i].cusName;
                          //  vm.cusAlphaCode = vm.custList[i].cusAlphaCode;
                           // vm.id = vm.custList[i].id;
                           }
                         }
                      }

        function validateCount(value) {
            try{
                var intee = parseInt(value);
                if(intee <= 3 && intee > 1){

                }else{
                    vm.noOfJointAcc = '';
                }
            }catch(error){
                vm.noOfJointAcc = '';
            }
        }


        function getModuleName() {
            vm.loader = true;
            vm.referenceId = 'TAX';
            var requestData = {
                'referenceId': vm.referenceId
            };
            bondLedgerAccMaintenanceService.getRbiBondModuleNameDetails(requestData).then(function (response) {
                vm.loader = false;
                vm.schemeList = response.data.result;
            }, function (error) {
                handleErrorResponse(error);
                vm.loader = false;
            });
        }


        function getModuleNameAdd() {
                    vm.loader = true;
                    vm.referenceId = 'ACTSCH';
                    var requestData = {
                        'referenceId': vm.referenceId
                    };
                    bondLedgerAccMaintenanceService.getRbiBondModuleNameDetails(requestData).then(function (response) {
                        vm.loader = false;
                        vm.schemeList = response.data.result;
                    }, function (error) {
                        handleErrorResponse(error);
                        $state.go('bond_ledger_acc_main.list');
                        vm.loader = false;
                    });
                }

        function getCurrentFinancialYear() {
          vm.fiscalyear = "";
          var today = new Date();
          if ((today.getMonth() + 1) <= 3) {
            vm.fiscalyear = (today.getFullYear() - 1) + "-" + today.getFullYear()
          } else {
            vm.fiscalyear = today.getFullYear() + "-" + (today.getFullYear() + 1)
          }
          vm.currentFinancialYear = vm.fiscalyear;
          vm.customerMasterDetails.finYear = vm.fiscalyear;
//          vm.customerMasterDetailsTwo.finYear = vm.fiscalyear;
//          vm.customerMasterDetailsThree.finYear = vm.fiscalyear;
          return vm.fiscalyear

        }
       function getModeofHolding() {
           var requestData = {
                 'referenceId': 'MODE_OF_HOLDING'
             }
           bondLedgerAccMaintenanceService.getReferenceData(requestData).then(function(response){
              vm.modeOfHoldingList = response.data.result;
            },function(error) {
               handleErrorResponse(error);
             });
        }
       function getTypeofHolding() {
           var requestData = {
                 'referenceId': 'HOLDER_TYPE'
             }
           bondLedgerAccMaintenanceService.getReferenceData(requestData).then(function(response){
              vm.holderTypeList = response.data.result;
            },function(error) {
               handleErrorResponse(error);
             });
        }
       function getFormDetails() {
           var requestData = {
                 'referenceId': 'FORM'
             }
           bondLedgerAccMaintenanceService.getReferenceData(requestData).then(function(response){
              vm.formList = response.data.result;
            },function(error) {
               handleErrorResponse(error);
             });
        }
       function getKycDetails() {
           var requestData = {
                 'referenceId': 'KYC_DOCS'
             }
           bondLedgerAccMaintenanceService.getReferenceData(requestData).then(function(response){
              vm.kycList = response.data.result;
            },function(error) {
               handleErrorResponse(error);
             });
        }
           function getGuardianDetailsMetaData() {
               var requestData = {
                     'referenceId': 'RELATION_WITH_MINOR'
                 }
               bondLedgerAccMaintenanceService.getReferenceData(requestData).then(function(response){
                  vm.guardianDetailsListMetaData = response.data.result;
                },function(error) {
                   handleErrorResponse(error);
                 });
            }
        /* Check box selection */
        function selectedChecklist() {
            angular.forEach(vm.serviceCodeDetailsList, function(item) {
                if (item.selected) {
                    vm.selectedCheckListArray.push(item.serCode);
                }
            });
            if(vm.selectedCheckListArray.length == '0'){
                Swal.fire('Please select atleast one request type')
            }else{
                getMonitorServiceReport();
            }
        }

        vm.displayRemarks = true;
        vm.reqField = true;

        function optionToggled(){

         vm.isAllSelected = [];
            angular.forEach(vm.kycList, function(item)
            {
                vm.isAllSelected.push(item.selected);
            });
            console.log(vm.isAllSelected);
            if(vm.isAllSelected[0] == true){
                vm.customerMasterDetails.birthCertificate = 'Yes'
            }
            if(vm.isAllSelected[1] == true){
                vm.customerMasterDetails.passport = 'Yes'
            }
            if(vm.isAllSelected[2] == true){
                vm.customerMasterDetails.seniorCitizenCard = 'Yes'
            }
             if(vm.isAllSelected[3] == true){
                vm.customerMasterDetails.voterIdCard = 'Yes'
            }
            if(vm.isAllSelected[4] == true){
                vm.customerMasterDetails.panCard = 'Yes'
            }
            if(vm.isAllSelected[5] == true){
                vm.customerMasterDetails.drivingLicence = 'Yes'
            }
            if(vm.isAllSelected[6] == true){
                vm.customerMasterDetails.rationCard = 'Yes'
            }
            if(vm.isAllSelected[7] == true){
                vm.customerMasterDetails.otherDocuments = 'Yes'
            }


//            if(vm.isAllSelected.indexOf(true) != -1){
//                vm.reqField = false;
//            }else{
//                vm.reqField = true;
//            }

        }

        function getState() {

                 vm.listType = 'STATES OF INDIA';
                 var requestData = {
                 referenceId:vm.listType
                 }
           bondLedgerAccMaintenanceService.getCommonMetaDatList(requestData).then(function(response){
              vm.stateListValues = response.data.result;
                vm.stateList = [];
                angular.forEach(vm.stateListValues, function(item){
                     vm.stateList.push({subCodeDescription: item.DESCRIPTION, subCode: item.SUBCODE})
                })
            },function(error) {
               handleErrorResponse(error);
             });
        }

        /* Get Sol Details */
        function getSolDetails(solId, scopeVariable, flag){
            vm.tempSolIDScopeVariable = scopeVariable;
            vm.searchSolId = '';
            vm.loader = true;
            commonService.getSolAllDetails(solId, function(response){
                if(response.success) {
                    vm.loader = false;
                    vm.solDetails = response.data;
                    if(flag == 'normal'){
                    angular.element('#solIDModal').modal('toggle');
                    }else{
                    angular.element('#solIDJointModal').modal('toggle');
                    }
                } else {
                    Swal.fire("Error!", response.message, "error");
                    vm.loader = false;
                }
            });
        }
        /* Get TDS Applicable Rate */
        function getTdsApplicableRate(value){
            vm.loader = true;
            var requestData = {
                'schemeCode' : vm.schemeCode,
            };

            bondLedgerAccMaintenanceService.getTdsApplecableRate(requestData).then( (response) => {
                vm.loader = false;

                vm.tdsRateApplicableResponse = response.data.result.tdsRateWithPan;

                if(value == 'M'){

                vm.customerMasterDetails.C064.oldValue = vm.tdsRateApplicableResponse;
                                try{
                                    vm.customerMasterDetailsTwo.C064.oldValue = vm.tdsRateApplicableResponse;
                                }catch(error){}
                                try{
                                    vm.customerMasterDetailsThree.C064.oldValue = vm.tdsRateApplicableResponse;
                                }catch(error){}


                }

                vm.customerMasterDetails.tdsRateApplicable = vm.tdsRateApplicableResponse;
                try{
                    vm.customerMasterDetailsTwo.tdsRateApplicable = vm.tdsRateApplicableResponse;
                }catch(error){}
                try{
                    vm.customerMasterDetailsThree.tdsRateApplicable = vm.tdsRateApplicableResponse;
                }catch(error){}

            }, function(error) {
               vm.loader = false;
               handleErrorResponse(error);
            });

        }
        function getModifyTdsApplicableRate(){
            vm.loader = true;
            bondLedgerAccMaintenanceService.getTdsApplecableRate().then(function(response){
                if(response) {
                    vm.getTdsApplicableList = response.data;
                    vm.count = Object.getOwnPropertyNames(vm.getTdsApplicableList).length;
                    if(vm.count > 0){
                        if(vm.getScssDetails.masterDetailsValueModel[0].C002 =='MH001' || vm.getScssDetails.masterDetailsValueModel[0].C002 =='MH002'){
                            if((vm.customerMasterDetails.C026 ==undefined && vm.customerMasterDetails.C027 =='Y') || (vm.customerMasterDetails.C026 =='' && vm.customerMasterDetails.C027 =='Y')){
                                vm.tdsRateApplicableVal = vm.getTdsApplicableList.result.tdsRateWithPan;
                            }else if(vm.customerMasterDetails.C026 !='' && vm.customerMasterDetails.C026 !=undefined){
                                vm.tdsRateApplicableVal = vm.getTdsApplicableList.result.tdsRateWithPan;
                            }else{
                                vm.tdsRateApplicableVal = vm.getTdsApplicableList.result.tdsRateWithoutPan;
                            }
                        }
                    }else{
                        vm.tdsRateApplicableVal ='0';
                    }
                    vm.loader = false;
                } else {
                    Swal.fire("Error!", response.message, "error");
                    vm.loader = false;
                }
            });
        }

        function getVerifyTdsApplicableRate(){
            vm.loader = true;
            bondLedgerAccMaintenanceService.getTdsApplecableRate().then(function(response){
                if(response) {
                    vm.getTdsApplicableList = response.data;
                    vm.propertyCheck = vm.getScssVerifyDetails.masterDetailsValueModel[0];
                    vm.count = Object.getOwnPropertyNames(vm.getTdsApplicableList).length;
                    if(vm.count > 0){
                        if(vm.getScssVerifyDetails.masterDetailsValueModel[0].C002.oldValue =='MH001' || vm.getScssVerifyDetails.masterDetailsValueModel[0].C002.oldValue =='MH002'){
                            if(vm.propertyCheck.hasOwnProperty('C026') == true){
                                if(vm.getScssVerifyDetails.masterDetailsValueModel[0].C026.oldValue !=undefined){
                                    vm.tdsRateApplicableVal = vm.getTdsApplicableList.result.tdsRateWithPan;
                                }else{
                                    vm.tdsRateApplicableVal = vm.getTdsApplicableList.result.tdsRateWithoutPan;
                                }
                            }else if(vm.propertyCheck.hasOwnProperty('C027') == true){
                                if(vm.getScssVerifyDetails.masterDetailsValueModel[0].C027.oldValue =='Y'){
                                    vm.tdsRateApplicableVal = vm.getTdsApplicableList.result.tdsRateWithPan;
                                }else{
                                    vm.tdsRateApplicableVal = vm.getTdsApplicableList.result.tdsRateWithoutPan;
                                }
                            }
                        }
                    }else{
                        vm.tdsRateApplicableVal ='0';
                    }
                    vm.loader = false;
                } else {
                    Swal.fire("Error!", response.message, "error");
                    vm.loader = false;
                }
            });
        }
        /* Get CBS Customer Id Details */
        function getCbsCustFstJoinDetails(){
            vm.loader = true;
              var requestData = {
                'cifId': vm.customerMasterDetails.cbsCustId,
                'operativeAccNo' : vm.customerMasterDetails.operativeAccNum,
                'operativeAccBranchId' : vm.customerMasterDetails.operativeAccBranchId
               };

          bondLedgerAccMaintenanceService.fetchCbsCustId(requestData).then( (response) => {
                    vm.loader = false;
                    vm.cbsDetail = response.data.result;

                    vm.customerMasterDetails.bondHldFirstName = vm.cbsDetail.firstName;
                    vm.customerMasterDetails.bondHldMidName = vm.cbsDetail.middleName;
                    vm.customerMasterDetails.bondHldLastName = vm.cbsDetail.lastName;
                    vm.customerMasterDetails.fatherName = vm.cbsDetail.fatherName;
                    vm.customerMasterDetails.motherName = vm.cbsDetail.motherName;
                    vm.customerMasterDetails.gender = vm.cbsDetail.gender;
                    vm.customerMasterDetails.bondHldDob = convertToDateObject(vm.cbsDetail.dateOfBirth);
                    vm.customerMasterDetails.isMinor = vm.cbsDetail.isMinor;
                    vm.customerMasterDetails.bondHldAddOne = vm.cbsDetail.addressOne;
                    vm.customerMasterDetails.bondHldAddTwo = vm.cbsDetail.addressTwo;
                    vm.customerMasterDetails.bondHldAddThree = vm.cbsDetail.addressThree;
                    vm.customerMasterDetails.bondHldCity = vm.cbsDetail.city;
                    vm.customerMasterDetails.bondHldPinCode = vm.cbsDetail.pinCode;
                    vm.customerMasterDetails.bondHolderPhoneNumber = vm.cbsDetail.phoneNumber;
                    vm.customerMasterDetails.bondHldMobNum = vm.cbsDetail.mobileNumber;
                    vm.customerMasterDetails.bondHldEmail = vm.cbsDetail.email;
                    vm.customerMasterDetails.operativeAccNum = vm.cbsDetail.operativeAccNo;
                    vm.customerMasterDetails.operativeAccBranchId = vm.cbsDetail.operativeBranchId;
                    vm.customerMasterDetails.panNo = vm.cbsDetail.pan;
                    vm.customerMasterDetails.aadhaarNo = vm.cbsDetail.aadhar;

                }, function(error) {
                   vm.loader = false;
                   handleErrorResponse(error);
                   vm.errmsgSingle = 'CBS Failure';
                   vm.customerMasterDetails.cbsCustId = '';
                });

//            bondLedgerAccMaintenanceService.fetchCbsCustId(requestData).then(function(response){
//                vm.loader = false;
//                if(response.data.result.requestStatus.error_flag == 'S') {
//
//                        vm.getCbsDetailsChck = response.data.status;
//                        vm.getCbsDetails = response.data.customerDetails;
//                    if(vm.getCbsDetailsChck !='N' && vm.getCbsDetails != null){
//                        vm.customerMasterDetails.C026 = vm.getCbsDetails.panNumber;
//                        vm.customerMasterDetails.C024 = vm.getCbsDetails.solId;
//                        vm.customerMasterDetails.C012 = vm.getCbsDetails.addressLine1;
//                        vm.customerMasterDetails.C013 = vm.getCbsDetails.addressLine2;
//                        vm.customerMasterDetails.C014 = vm.getCbsDetails.addressLine3;
//                        //vm.customerMasterDetails.C026 = vm.getCbsDetails.panNumber;
//                        vm.customerMasterDetails.C015 = vm.getCbsDetails.city;
//                        //vm.customerMasterDetails.C016 = vm.getCbsDetails.state;
//                        //vm.customerMasterDetails.C026 = vm.getCbsDetails.country;
//                        vm.customerMasterDetails.C017 = vm.getCbsDetails.zipCode;
//                        vm.customerMasterDetails.C018 = vm.getCbsDetails.phoneNumber;
//                        vm.customerMasterDetails.C019 = vm.getCbsDetails.email;
//                        vm.errmsgSingle ='';
//
//                        getTdsApplicableRate();
//                    }
//                    else{
//                        vm.errmsgSingle = 'No Data Found';
//                    }
//
//
//                    vm.loader = false;
//                } else {
//                    Swal.fire("Error!", response.message, "error");
//                    vm.loader = false;
//                }
//            });
        }


    function minAgeC(){
              var currentDate=new Date();
              var result = subtractYears(currentDate, 18);


    }

//const date = new Date('2024-06-15T17:37:59.000Z');
//const result2 = subtractYears(date, 2);
//         function calculateAge(birthday) { // birthday is a date
//                    var ageDifMs = Date.now() - birthday.getTime();
//                     vm.currentDate=new Date();
//
//                    var ageDate = new Date(ageDifMs); // miliseconds from epoch
//                    vm.apySubscriberRegistration.C016 = Math.abs(ageDate.getUTCFullYear() - 1970);
//
//                    if(vm.apySubscriberRegistration.C016 > vm.maxAge){
//                        var msg = "Age exceeded the maximum eligible age. (above " +vm.maxAge+ "Years)"
//                        Swal.fire("Warning!", msg, "warning");
//                        vm.apySubscriberRegistration.C016 = '';
//                        $state.go('apy_subscriber_registration.add');
//                    }else if(vm.apySubscriberRegistration.C016 < vm.minAge){
//                        var msg = "Age less than minimum eligible age.(below " +vm.minAge+ "Years)"
//                        Swal.fire("Warning!", msg, "warning");
//                        vm.apySubscriberRegistration.C016 = '';
//                        $state.go('apy_subscriber_registration.add');
//                    }
//               }

        /* Get CBS Customer Id Second Join Details */
        function getCbsCustScndJoinDetails(){
            vm.loader = true;
            vm.custId = vm.customerMasterJointDetails.cbsCustId;
            bondLedgerAccMaintenanceService.fetchCbsCustId(vm.custId).then(function(response){
                if(response) {
                    vm.getCbsScndJoinDetailsChck = response.data.status;
                    vm.getCbsScndJoinDetails = response.data.customerDetails;
                    if(vm.getCbsScndJoinDetailsChck != 'N' && vm.getCbsScndJoinDetails != null){
                        vm.customerMasterJointDetails.C026 = vm.getCbsScndJoinDetails.panNumber;
                        vm.customerMasterJointDetails.C024 = vm.getCbsScndJoinDetails.solId;
                        vm.customerMasterJointDetails.C012 = vm.getCbsScndJoinDetails.addressLine1;
                        vm.customerMasterJointDetails.C013 = vm.getCbsScndJoinDetails.addressLine2;
                        vm.customerMasterJointDetails.C014 = vm.getCbsScndJoinDetails.addressLine3;
                        //vm.customerMasterJointDetails.C026 = vm.getCbsScndJoinDetails.panNumber;
                        vm.customerMasterJointDetails.C015 = vm.getCbsScndJoinDetails.city;
                        vm.customerMasterJointDetails.C016 = vm.getCbsScndJoinDetails.state;
                        vm.customerMasterJointDetails.C017 = vm.getCbsScndJoinDetails.zipCode;
                        vm.customerMasterJointDetails.C018 = vm.getCbsScndJoinDetails.phoneNumber;
                        vm.customerMasterJointDetails.C019 = vm.getCbsScndJoinDetails.email;
                        vm.errmsgJoint ='';

                        getTdsApplicableRate();
                    }else{
                        vm.errmsgJoint = 'No Data Found';
                    }

                    vm.loader = false;
                } else {
                    Swal.fire("Error!", response.message, "error");
                    vm.loader = false;
                }
            });
        }
        /* Get bla acc Details */
        function getBlaAccNoDetails(flag){
        vm.loader = true;

        if(flag=='torch'){


                    var requestData = {
                        'schemeCode': vm.schemeCode,
                        'modeOfHolding': vm.modeOfHolding,
                    };
                      bondLedgerAccMaintenanceService.fetchBlaAccNoDetails(requestData).then( (response) => {
                                vm.loader = false;
                                vm.blaNoList = response.data.result;
                                if(vm.modeOfHolding == 'MH002' || vm.modeOfHolding == 'MH003'){
                                        angular.element('#blaJointModal').modal('toggle');
                                }else{
                                       angular.element('#blaModal').modal('toggle');
                                }
                            }, function(error) {
                               vm.loader = false;
                               handleErrorResponse(error);
                            });
        }
        else{
                  var requestData = {
                        'schemeCode': vm.schemeCode,
                        'modeOfHolding': vm.modeOfHolding,
                        'blaNum' : vm.customerMaster.blaAccNo,
                        'jointHolderNo': vm.jointHolderTypeCode,
                        };
                      bondLedgerAccMaintenanceService.fetchBlaAccNoDetails(requestData).then( (response) => {
                                vm.loader = false;
                                vm.blaAccDetails = response.data.result[0];
                                vm.customerMasterDetails.blaAccNo = vm.blaAccDetails.blaNo;
                               vm.customerMasterDetails.bondHldFirstName = vm.blaAccDetails.bondHolderFirstName;
                               vm.customerMasterDetails.bondHldMidName = vm.blaAccDetails.bondHolderMiddleName;
                               vm.customerMasterDetails.bondHldLastName = vm.blaAccDetails.bondHolderLastName;
                               vm.customerMasterDetails.fatherName = vm.blaAccDetails.fatherName;
                               vm.customerMasterDetails.motherName = vm.blaAccDetails.motherName;
                               vm.customerMasterDetails.gender = vm.blaAccDetails.gender;
                               vm.customerMasterDetails.bondHldDob = convertToDateObject(vm.blaAccDetails.dateOfBirth);
                               vm.customerMasterDetails.isMinor = vm.blaAccDetails.isMinor;
                               vm.customerMasterDetails.bondHldAddOne = vm.blaAccDetails.addressOne;
                               vm.customerMasterDetails.bondHldAddTwo = vm.blaAccDetails.addressTwo;
                               vm.customerMasterDetails.bondHldAddThree = vm.blaAccDetails.addressThree;
                               vm.customerMasterDetails.bondHldCity = vm.blaAccDetails.city;
                               vm.customerMasterDetails.bondHldPinCode = vm.blaAccDetails.pinCode;
                               vm.customerMasterDetails.bondHolderPhoneNumber = vm.blaAccDetails.phoneNumber;
                               vm.customerMasterDetails.bondHldMobNum = vm.blaAccDetails.mobileNumber;
                               vm.customerMasterDetails.bondHldEmail = vm.blaAccDetails.email;
                               vm.customerMasterDetails.operativeAccNum = vm.blaAccDetails.operativeAccNo;
                               vm.customerMasterDetails.operativeAccBranchId = vm.blaAccDetails.operativeBranchId;
                               vm.customerMasterDetails.panNo = vm.blaAccDetails.pan;

                            }, function(error) {
                               vm.loader = false;
                               handleErrorResponse(error);
                            });
        }






//            vm.loader = true;
//            vm.scsscustId = vm.customerMaster.scssCustId;
//            vm.holderNum = vm.holderNum='1';
//            var requestData = {
//            custId:vm.scsscustId,
//            holderFirstName:  vm.holderNum
//            }
//            bondLedgerAccMaintenanceService.fetchScssCustId(requestData).then(function(response){
//                if(response) {
//                    if(vm.scsscustId !='' && vm.holderNum ==1){
//                        vm.getScssDetailsChck = response.data.result;
//                        vm.getScssDetails = response.data.result[0];
//                        if(vm.getScssDetailsChck.length > 0){
//                            vm.customerMasterDetails.C026 = vm.getScssDetails.holderPanNumber;
//                            vm.customerMasterDetails.C024 = vm.getScssDetails.branchId;
//                            vm.customerMasterDetails.C012 = vm.getScssDetails.holderaddress1;
//                            vm.customerMasterDetails.C013 = vm.getScssDetails.holderaddress2;
//                            vm.customerMasterDetails.C014 = vm.getScssDetails.holderaddress3;
//                            //vm.customerMasterDetails.C026 = vm.getScssDetails.holderPanNumber;
//                            vm.customerMasterDetails.C015 = vm.getScssDetails.holderCity;
//                            vm.customerMasterDetails.C017 = vm.getScssDetails.holderPinCode;
//                            vm.customerMasterDetails.C018 = vm.getScssDetails.holderPhoneNo;
//                            vm.customerMasterDetails.C019 = vm.getScssDetails.holderEmail;
//
//                            vm.customerMasterDetails.C008 = vm.getScssDetails.holderFirstName;
//                            vm.customerMasterDetails.C009 = vm.getScssDetails.holderMiddleName;
//                            vm.customerMasterDetails.C010 = vm.getScssDetails.holderLastName;
//                            vm.customerMasterDetails.C027 = vm.getScssDetails.form49A;
//                            vm.customerMasterDetails.C028 = vm.getScssDetails.form60;
//                            vm.customerMasterDetails.C025 = vm.getScssDetails.holderOperativeAcctNo;
//                            vm.customerMasterDetails.C011 = vm.getScssDetails.holderType;
//                            vm.customerMasterDetails.C029 = vm.getScssDetails.holderAadharNo;
//                            //vm.customerMasterDetails.C003 = vm.getScssDetails.finYear;
//                            vm.customerMasterFormDetails.C004 = vm.getScssDetails.form15G15H;
//                            vm.customerMasterDetails.C020 = convertToDateObject(vm.getScssDetails.holderDateOfBirth);
//                            //vm.customerMasterDetails.C005 = convertToDateObject(vm.getScssDetails.dateOfSubmission);
//                            vm.customerMasterDetails.C022 = convertToDateObject(vm.getScssDetails.holderDateOfRetirement);
//                            vm.customerMasterDetails.C023 = convertToDateObject(vm.getScssDetails.holderDateOfTermBenefits);
//                            vm.customerMasterFormDetails.C005 = convertToDateObject(vm.getScssDetails.dateOfSubmission);
//                            vm.errorMessageSingle = '';
//
//                            getTdsApplicableRate();
//                        }else{
//                            vm.errorMessageSingle = 'No Data Found';
//                        }
//                   }
//
//
//                    vm.loader = false;
//                } else {
//                    Swal.fire("Error!", response.message, "error");
//                    vm.loader = false;
//                }
//            });
        }


        function getGuardianDetails(flag){
                vm.loader = true;

                if(flag == 'select'){
                    var requestData = {
                        'schemeCode' : vm.schemeCode,
                    };
                  bondLedgerAccMaintenanceService.fetchGuardianDetails(requestData).then( (response) => {
                            vm.loader = false;
                            vm.guardianDetailsList = response.data.result;
                            angular.element('#guardianModal').modal('toggle');
                        }, function(error) {
                           vm.loader = false;
                           handleErrorResponse(error);
                        });
                }else{
                    var requestData = {
                        'schemeCode' : vm.schemeCode,
                        'guardianId' : vm.customerMasterDetails.guardianId,
                    };
                  bondLedgerAccMaintenanceService.fetchGuardianDetails(requestData).then( (response) => {
                            vm.loader = false;
                            vm.guardianDetail = response.data.result[0];

                            vm.customerMasterDetails.guardianFirstName = vm.guardianDetail.guardianFirstName;
                            vm.customerMasterDetails.guardianMiddleName = vm.guardianDetail.guardianMiddleName;
                            vm.customerMasterDetails.guardianLastName = vm.guardianDetail.guardianLastName;
                            vm.customerMasterDetails.guardianDateOfBirth = convertToDateObject(vm.guardianDetail.dateOfBirth);
                            vm.customerMasterDetails.guardianCommunicationAddress = vm.guardianDetail.address;
                            vm.customerMasterDetails.guardianMobileNo = vm.guardianDetail.mobileNo;
                            vm.customerMasterDetails.guardianEmailId = vm.guardianDetail.email;
                        }, function(error) {
                           vm.loader = false;
                           handleErrorResponse(error);
                        });
                }




                }
        /* Get SCSS Joint Customer Id Details */
        function getScssJointCustDetails(){
            vm.loader = true;
                vm.scsscustId = vm.customerMasterJointDetails.scssCustId;
                vm.holderNum = vm.holderNum='2';
            bondLedgerAccMaintenanceService.fetchScssCustId(vm.scsscustId, vm.holderNum).then(function(response){
                if(response) {
                    if(vm.scsscustId !='' && vm.holderNum ==2){
                        vm.getScssJointDetailsChck = response.data.result;
                        vm.getScssJointDetails = response.data.result[0];
                        if(vm.getScssJointDetailsChck.length > 0){
                            vm.customerMasterJointDetails.C026 = vm.getScssJointDetails.holderPanNumber;
                            vm.customerMasterJointDetails.C024 = vm.getScssJointDetails.branchId;
                            vm.customerMasterJointDetails.C012 = vm.getScssJointDetails.holderaddress1;
                            vm.customerMasterJointDetails.C013 = vm.getScssJointDetails.holderaddress2;
                            vm.customerMasterJointDetails.C014 = vm.getScssJointDetails.holderaddress3;
                            //vm.customerMasterJointDetails.C026 = vm.getScssJointDetails.holderPanNumber;
                            vm.customerMasterJointDetails.C015 = vm.getScssJointDetails.holderCity;
                            vm.customerMasterJointDetails.C017 = vm.getScssJointDetails.holderPinCode;
                            vm.customerMasterJointDetails.C018 = vm.getScssJointDetails.holderPhoneNo;
                            vm.customerMasterJointDetails.C019 = vm.getScssJointDetails.holderEmail;

                            vm.customerMasterJointDetails.C008 = vm.getScssJointDetails.holderFirstName;
                            vm.customerMasterJointDetails.C009 = vm.getScssJointDetails.holderMiddleName;
                            vm.customerMasterJointDetails.C010 = vm.getScssJointDetails.holderLastName;
                            vm.customerMasterJointDetails.C027 = vm.getScssJointDetails.form49A;
                            vm.customerMasterJointDetails.C028 = vm.getScssJointDetails.form60;
                            vm.customerMasterJointDetails.C025 = vm.getScssJointDetails.holderOperativeAcctNo;
                            vm.customerMasterJointDetails.C011 = vm.getScssJointDetails.holderType;
                            vm.customerMasterJointDetails.C029 = vm.getScssJointDetails.holderAadharNo;
                            vm.customerMasterJointDetails.C003 = vm.getScssJointDetails.finYear;
                            vm.customerMasterJointDetails.C004 = vm.getScssJointDetails.form15G15H;
                            vm.customerMasterJointDetails.C020 = convertToDateObject(vm.getScssJointDetails.holderDateOfBirth);
                            vm.customerMasterFormDetails.C005 = convertToDateObject(vm.getScssJointDetails.dateOfSubmission);
                            vm.customerMasterJointDetails.C022 = convertToDateObject(vm.getScssJointDetails.holderDateOfRetirement);
                            vm.customerMasterJointDetails.C023 = convertToDateObject(vm.getScssJointDetails.holderDateOfTermBenefits);
                            vm.errorMessageJoint ='';

                            getTdsApplicableRate();
                        }else{
                             vm.errorMessageJoint = 'No Data Found';
                        }
                   }


                    vm.loader = false;
                } else {
                    Swal.fire("Error!", response.message, "error");
                    vm.loader = false;
                }
            });
        }

	/*Operative Acct Num*/
           function getSchemeTypeListOperative (acctNum, flag){
               vm.loader = true;
               vm.customerMasterDetails.operativeAccBranchId='';
               if(acctNum){

               commonService.getCustomAcctInqApi(acctNum.toUpperCase(), function (response) {
       					if (response.success) {
       						vm.accList = response.data;
       						vm.loader = false;
       						if (vm.accList.length > 0) {
       						if (vm.accList[0].status == 'S') {


                                   if(vm.accList[0].accountFreezeStatus) {
                                     vm.cbsErrorMessage = 'Account is Friezed';
                                     vm.customerMasterDetails.operativeAccNum='';
                                   }else{
                                       vm.customerMasterDetails.operativeAccBranchId = vm.accList[0].solID;
                                       vm.cbsErrorMessage = null;
                                   }
       							}
       							if (vm.accList[0].status == 'F') {
       								vm.cbsErrorMessage = 'Invalid  Account  Number';
       							}
       						}
       						else {
                                   vm.cbsErrorMessage = 'Invalid  Account  Number';
       						}
       					} else {
       						Swal.fire("Error!", response.message, "error");
       						vm.loader = false;
       						vm.customerMasterDetails.operativeAccNum='';
       					}
       				}, function (err) {
       					handleErrorResponse(err);
       					vm.loader = false;
       					vm.customerMasterDetails.operativeAccNum='';
       				});




       //        commonService.getSchemeTypeListAcc(acctNum.toUpperCase(), function(response){
       //            if(response.success) {
       //                vm.accList = response.data;
       //                vm.loader = false;
       //                if(vm.accList.length>0)
       //                {
       //                vm.customerMasterDetails.acctNum = vm.accList[0].forAccountId;
       //                vm.customerMasterDetails.C025 = vm.accList[0].forAccountId;
       //                vm.customerMasterDetails.acctName= vm.accList[0].accountName;
       //                vm.errorMessage ='';
       //                }else
       //                {
       //                vm.errorMessage = 'Account Number failed to validate';
       //                vm.customerMasterDetails.acctNum='';
       //                }
       //            } else {
       //                Swal.fire("Error!", response.message, "error");
       //                vm.loader = false;
       //                        }
       //        }, function(err) {
       //                 handleErrorResponse(err);
       //             vm.loader = false;
       //         });



//        commonService.getSchemeTypeListAcc(acctNum.toUpperCase(), function(response){
//            if(response.success) {
//                vm.accList = response.data;
//                vm.loader = false;
//                if(vm.accList.length>0)
//                {
//                vm.customerMasterDetails.acctNum = vm.accList[0].forAccountId;
//                vm.customerMasterDetails.C025 = vm.accList[0].forAccountId;
//                vm.customerMasterDetails.acctName= vm.accList[0].accountName;
//                vm.errorMessage ='';
//                }else
//                {
//                vm.errorMessage = 'Account Number failed to validate';
//                vm.customerMasterDetails.acctNum='';
//                }
//            } else {
//                Swal.fire("Error!", response.message, "error");
//                vm.loader = false;
//                        }
//        }, function(err) {
//                 handleErrorResponse(err);
//             vm.loader = false;
//         });


        }else{
             vm.loader = false;
         }
    }
    /*Operative Acct Num Joint*/
        function getSchemeTypeListOperativeJoint (solId, acctNum, flag){
            vm.loader = true;
            if(acctNum){
            commonService.getSchemeTypeListAcc(acctNum.toUpperCase(), function(response){
                if(response.success) {
                    vm.accList = response.data;
                    vm.loader = false;
                    if(vm.accList.length>0)
                    {
                    vm.customerMasterJointDetails.acctNum = vm.accList[0].forAccountId;
                    vm.customerMasterJointDetails.C025 = vm.accList[0].forAccountId;
                    vm.customerMasterJointDetails.acctName= vm.accList[0].accountName;
                    vm.errorMessageJointAcnt ='';
                    }else
                    {
                    vm.errorMessageJointAcnt = 'Account Number failed to validate';
                    vm.customerMasterJointDetails.acctNum='';
                    }
                } else {
                    Swal.fire("Error!", response.message, "error");
                    vm.loader = false;
                            }
            }, function(err) {
                     handleErrorResponse(err);
                 vm.loader = false;
             });
            }else{
                vm.loader = false;

            }
        }

            function addRange() {
                if (vm.scssOtherBanksDetails.length <= 14) {
                    vm.scssOtherBanksDetails.push({});
                    vm.disableDelete = true;
                }
            }
            function removeRange() {
                if (vm.scssOtherBanksDetails.length > 1) {
                    vm.lastRow = vm.scssOtherBanksDetails.length - 1;
                    vm.scssOtherBanksDetails.splice(vm.lastRow, 1);
                    if (vm.lastRow == 1) {
                        vm.disableDelete = false;
                    }
                } else {
                    vm.disableDelete = false;
                }
            }
            function titleChange(){
                if(vm.modeOfHolding == 'MH002' || vm.modeOfHolding == 'MH003'){
                    vm.titleText="FIRST JOINT ACCOUNT HOLDER DETAILS";

                }
                else {
                    vm.titleText="BOND HOLDER DETAILS";
                }
            }

            const convertToColumnValuesArrayTemp = (objectToBeConvert) => {
              if(typeof(objectToBeConvert) === "object") {
                 let resultArray = [];
                 for(let key of Object.keys(objectToBeConvert)) {
                    if(objectToBeConvert[key])
                        resultArray.push({colName : 'C003',value : objectToBeConvert[key].value})
                         if(objectToBeConvert[key].value == 'KD009'){
                          resultArray.push({colName : 'C004',value : vm.documentsDetailsList.C004});//Remarks
                         }
                 }
                  if(vm.modeOfHolding=='MH001')
                  { //Holder Number
                  resultArray.push({colName : 'C002',value : '1'});
                  }else{
                  resultArray.push({colName : 'C002',value : '2'});
                  }
                 return resultArray;
              } else {
                throw "Only objects can be converted"
              }
            }
             const convertToColumnValuesArrayTempModify = (objectToBeConvert) => {
              if(typeof(objectToBeConvert) === "object") {
                 let resultArray = [];
                 for(let key of Object.keys(objectToBeConvert)) {
                    if(objectToBeConvert[key])
                        resultArray.push({colName : 'C003',value : objectToBeConvert[key].value})
                        resultArray.push({colName : 'ID',value : objectToBeConvert[key].ID? objectToBeConvert[key].ID : ''})
                    if(objectToBeConvert[key].value == 'KD009'){
                    resultArray.push({colName : 'C004',value : vm.documentsDetailsList.C004});//Remarks
                    }
                 }

                  if(vm.getScssDetails.masterDetailsValueModel[0].C002 =='MH001')
                  { //Holder Number
                  resultArray.push({colName : 'C002',value : '1'});
                  }else{
                  resultArray.push({colName : 'C002',value : '2'});
                  }
                 return resultArray;
              } else {
                throw "Only objects can be converted"
              }
            }

            const convertToColumnValuesArrayList = (objectToBeConvert) => {
              if(typeof(objectToBeConvert) === "object") {
                 let resultArrayOther = [];
                 for(let key of Object.keys(objectToBeConvert)) {
                    if(objectToBeConvert[key])
                        resultArrayOther.push({colName : key,value : objectToBeConvert[key].oldValue})
                 }

                 return resultArrayOther;
              } else {
                throw "Only objects can be converted"
              }
            }


//function toObject(arr) {
//  var rv = {};
//  for (var i = 0; i < arr.length; ++i)
//    if (arr[i] !== undefined) rv[i] = arr[i];
//  return rv;
//}

            /* Add Submit */
            function addcustomerMaster() {
//                    Swal.fire({
//                            title: "Are you sure?",
//                            text: "Do you want to Really Add this record?",
//                            type: "warning",
//                            showCancelButton: true,
//                            cancelButtonClass: "btn-cancel",
//                            confirmButtonClass: "btn-primary",
//                            confirmButtonText: "Yes",
//                            closeOnConfirm: false
//                        },
//                        function() {



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

                        vm.resultArray = [];

                        vm.customerMasterDetails.bondHldDob = $filter('date')(vm.customerMasterDetails.bondHldDob, 'dd-MMM-yyyy');
                        vm.customerMasterDetails.dateSubmission15g15htax = $filter('date')(vm.customerMasterDetails.dateSubmission15g15htax, 'dd-MMM-yyyy');
                        vm.customerMasterDetails.guardianDateOfBirth = $filter('date')(vm.customerMasterDetails.guardianDateOfBirth, 'dd-MMM-yyyy');

                        try{
                            vm.customerMasterDetails.birthCertificate = vm.customerMasterDetails.birthCertificate ? 'TRUE' : 'FALSE';
                            vm.customerMasterDetails.passport = vm.customerMasterDetails.passport ? 'TRUE' : 'FALSE';
                            vm.customerMasterDetails.seniorCitizenCard = vm.customerMasterDetails.seniorCitizenCard ? 'TRUE' : 'FALSE';
                            vm.customerMasterDetails.voterIdCard = vm.customerMasterDetails.voterIdCard ? 'TRUE' : 'FALSE';
                            vm.customerMasterDetails.panCard = vm.customerMasterDetails.panCard ? 'TRUE' : 'FALSE';
                            vm.customerMasterDetails.drivingLicence = vm.customerMasterDetails.drivingLicence ? 'TRUE' : 'FALSE';
                            vm.customerMasterDetails.rationCard = vm.customerMasterDetails.rationCard ? 'TRUE' : 'FALSE';
                            vm.customerMasterDetails.otherDocuments = vm.customerMasterDetails.otherDocuments ? 'TRUE' : 'FALSE';
                        }catch(e){}

                        try{
                            vm.customerMasterDetailsTwo.birthCertificate = vm.customerMasterDetailsTwo.birthCertificate ? 'TRUE' : 'FALSE';
                            vm.customerMasterDetailsTwo.passport = vm.customerMasterDetailsTwo.passport ? 'TRUE' : 'FALSE';
                            vm.customerMasterDetailsTwo.seniorCitizenCard = vm.customerMasterDetailsTwo.seniorCitizenCard ? 'TRUE' : 'FALSE';
                            vm.customerMasterDetailsTwo.voterIdCard = vm.customerMasterDetailsTwo.voterIdCard ? 'TRUE' : 'FALSE';
                            vm.customerMasterDetailsTwo.panCard = vm.customerMasterDetailsTwo.panCard ? 'TRUE' : 'FALSE';
                            vm.customerMasterDetailsTwo.drivingLicence = vm.customerMasterDetailsTwo.drivingLicence ? 'TRUE' : 'FALSE';
                            vm.customerMasterDetailsTwo.rationCard = vm.customerMasterDetailsTwo.rationCard ? 'TRUE' : 'FALSE';
                            vm.customerMasterDetailsTwo.otherDocuments = vm.customerMasterDetailsTwo.otherDocuments ? 'TRUE' : 'FALSE';
                        }catch(e){}

                        try{
                            vm.customerMasterDetailsThree.birthCertificate = vm.customerMasterDetailsThree.birthCertificate ? 'TRUE' : 'FALSE';
                            vm.customerMasterDetailsThree.passport = vm.customerMasterDetailsThree.passport ? 'TRUE' : 'FALSE';
                            vm.customerMasterDetailsThree.seniorCitizenCard = vm.customerMasterDetailsThree.seniorCitizenCard ? 'TRUE' : 'FALSE';
                            vm.customerMasterDetailsThree.voterIdCard = vm.customerMasterDetailsThree.voterIdCard ? 'TRUE' : 'FALSE';
                            vm.customerMasterDetailsThree.panCard = vm.customerMasterDetailsThree.panCard ? 'TRUE' : 'FALSE';
                            vm.customerMasterDetailsThree.drivingLicence = vm.customerMasterDetailsThree.drivingLicence ? 'TRUE' : 'FALSE';
                            vm.customerMasterDetailsThree.rationCard = vm.customerMasterDetailsThree.rationCard ? 'TRUE' : 'FALSE';
                            vm.customerMasterDetailsThree.otherDocuments = vm.customerMasterDetailsThree.otherDocuments ? 'TRUE' : 'FALSE';
                        }catch(e){}

                         try {
                            vm.customerMasterDetailsTwo.bondHldDob = $filter('date')(vm.customerMasterDetailsTwo.bondHldDob, 'dd-MMM-yyyy');
                         }
                         catch(err) {
                         }
                          try {
                            vm.customerMasterDetailsTwo.dateSubmission15g15htax = $filter('date')(vm.customerMasterDetailsTwo.dateSubmission15g15htax, 'dd-MMM-yyyy');
                          }
                          catch(err) {
                          }
                           try {
                            vm.customerMasterDetailsTwo.guardianDateOfBirth = $filter('date')(vm.customerMasterDetailsTwo.guardianDateOfBirth, 'dd-MMM-yyyy');
                           }
                           catch(err) {
                           }

                         try {
                            vm.customerMasterDetailsThree.bondHldDob = $filter('date')(vm.customerMasterDetailsThree.bondHldDob, 'dd-MMM-yyyy');
                         }
                         catch(err) {
                         }
                          try {
                            vm.customerMasterDetailsThree.dateSubmission15g15htax = $filter('date')(vm.customerMasterDetailsThree.dateSubmission15g15htax, 'dd-MMM-yyyy');
                          }
                          catch(err) {
                          }
                           try {
                            vm.customerMasterDetailsThree.guardianDateOfBirth = $filter('date')(vm.customerMasterDetailsThree.guardianDateOfBirth, 'dd-MMM-yyyy');
                           }
                           catch(err) {
                           }

                        var objOne = Object.fromEntries(Object.entries(vm.customerMasterDetails));
                        objOne.jointHolderType = 'S';
                        if(vm.customerMasterDetailsTwo){
                             var objTwo = Object.fromEntries(Object.entries(vm.customerMasterDetailsTwo));
                             objOne.jointHolderType = '1';
                             objTwo.jointHolderType = '2';
                             vm.resultArray.push(objTwo);
                        }
                        if(vm.customerMasterDetailsThree){
                             var objThree = Object.fromEntries(Object.entries(vm.customerMasterDetailsThree));
                             objThree.jointHolderType = '3';
                             vm.resultArray.push(objThree);
                        }



                        vm.resultArray.push(objOne);

                            if(vm.modeOfHolding == 'MH002' || vm.modeOfHolding == 'MH003'){
                                vm.resultArray = orderArray(vm.resultArray);
                            }


                            var requestData = {
                                'dataList': vm.resultArray,
                                'schemeCode': vm.schemeCode,
                                'modeOfHolding': vm.modeOfHolding,
                                'isNri' : vm.isNri,
                            };
                            requestData.jointHolderNo = vm.noOfJointAcc;

                            bondLedgerAccMaintenanceService.addSubmit(requestData).then(
                                (response) => {
                                    vm.loader = false;
                                    vm.responseResult = response.data.result;
                                    var message = 'Bond Ledger Account Created Successfully';
                                    if(vm.modeOfHolding == 'MH001' || vm.modeOfHolding == 'MH004' || vm.modeOfHolding == 'MH005'){
                                        message = message + '\nBLA No: ' + vm.responseResult.singleBlaNo;
                                        if(vm.responseResult.singleGuardianId){
                                            message = message + '\nGuardian ID: ' + vm.responseResult.singleGuardianId;
                                        }
                                    }
                                    else{
                                        if(vm.responseResult.firstJointBlaNo){
                                            message = message + '\nFirst Joint BLA No: ' + vm.responseResult.firstJointBlaNo;
                                            if(vm.responseResult.firstGuardianId){
                                                message = message + '\nFirst Joint Guardian ID: ' + vm.responseResult.firstGuardianId;
                                            }
                                        }

                                        if(vm.responseResult.secondJointBlaNo){
                                            message = message + '\nSecond Joint BLA No: ' + vm.responseResult.secondJointBlaNo;
                                            if(vm.responseResult.secondGuardianId){
                                                message = message + '\nSecond Joint Guardian ID: ' + vm.responseResult.secondGuardianId;
                                            }
                                        }

                                          if(vm.responseResult.thirdJointBlaNo){
                                            message = message + '\nThird Joint BLA No: ' + vm.responseResult.thirdJointBlaNo;
                                            if(vm.responseResult.secondGuardianId){
                                                message = message + '\nThird Joint Guardian ID: ' + vm.responseResult.thirdGuardianId;
                                            }
                                        }

                                    }
                                    Swal.fire("Success!", message ,"success");
                                    location.href = '#!/bond_ledger_acc_main/action/inquiry';
                                }, function(error) {
                                   vm.loader = false;
                                   handleErrorResponse(error);
                                   $state.go('bond_ledger_acc_main.add')
                                });
                     }
                        });
            }

            function orderArray(list){
               //this function will come joint holder greater than or equal to 2
               vm.tempArray = [];
               vm.tempArray.push('0');
               vm.tempArray.push('1');
               if(list.length > 2){
                   vm.tempArray.push('2');
               }
                angular.forEach(list,function(item){

                    if(item.jointHolderType == '1'){
                        vm.tempArray.splice(0,'0', item);
                    }
                    if(item.jointHolderType == '2'){
                        vm.tempArray.splice(1,'1', item);
                    }
                    if(item.jointHolderType == '3'){
                        vm.tempArray.splice(2,'2', item);
                    }

                });

                var value = vm.tempArray.filter((value) => {
                    if(value == '0'){

                    }else
                    if(value == '1'){

                    }else
                     if(value == '2'){

                    }else{
                        return value;
                    }
                });

                return value;

            }

            //Modify Select
            function getModifySelectDetails(flag) {
                vm.loader = true;

                if(flag == 'torch'){

                    var requestData = {
                    'schemeCode' : vm.schemeCode,
                    };

                    bondLedgerAccMaintenanceService.modifySelect(requestData).then(
                        (response) => {
                            vm.loader = false;
                            vm.torchList = response.data.result;
                            angular.element('#blaModel').modal('toggle');
                        }, function(error) {
                           vm.loader = false;
                           handleErrorResponse(error);
                        });

                }else{

                        var requestData = {
                        'schemeCode' : vm.schemeCode,
                        'blaNum' : vm.blaNo,
                        };

                        bondLedgerAccMaintenanceService.modifySelect(requestData).then(
                            (response) => {
                                vm.loader = false;
                                vm.getDetails = response.data.result;

                                if(vm.getDetails.masterDetailsValueModel.length == 1){
                                    vm.titleText = 'BOND HOLDER DETAILS';
                               }else{
                                     vm.titleText = 'FIRST JOINT BOND HOLDER DETAILS';

                                      vm.tempArray = [];
                                      vm.tempArray.push('0');
                                      vm.tempArray.push('1');
                                      if(vm.getDetails.masterDetailsValueModel.length > 2){
                                          vm.tempArray.push('2');
                                      }

                                      angular.forEach(vm.getDetails.masterDetailsValueModel,function(item){

                                          if(item.C045.oldValue == '1'){
                                              vm.tempArray.splice(0,'0', item);
                                          }
                                          if(item.C045.oldValue == '2'){
                                              vm.tempArray.splice(1,'1', item);
                                          }
                                          if(item.C045.oldValue == '3'){
                                              vm.tempArray.splice(2,'2', item);
                                          }

                                      });

                                      var value = vm.tempArray.filter((value) => {
                                          if(value == '0'){

                                          }else
                                          if(value == '1'){

                                          }else
                                           if(value == '2'){

                                          }else{
                                              return value;
                                          }
                                      });
                                      vm.getDetails.masterDetailsValueModel = value;


                               }
                               vm.modeOfHolding = vm.getDetails.masterDetailsValueModel[0].C001.oldValue;

                               vm.customerMasterDetails = vm.getDetails.masterDetailsValueModel[0];
                               vm.customerMasterDetails.C013.oldValue = convertToDateObject(vm.customerMasterDetails.C013.oldValue);

                               if(vm.customerMasterDetails.C064){
                                    vm.customerMasterDetailsTdsRateCode = vm.customerMasterDetails.C064.oldValue;
                               }

                               if(vm.customerMasterDetails.C030){
                                   vm.customerMasterDetails.C030.oldValue = convertToDateObject(vm.customerMasterDetails.C030.oldValue);
                               }
                               if(vm.customerMasterDetails.C039){
                                     vm.customerMasterDetails.C039.oldValue = convertToDateObject(vm.customerMasterDetails.C039.oldValue);
                               }
                               if(vm.customerMasterDetails.C051){
                                    vm.customerMasterDetails.C051.oldValue = convertToDateObject(vm.customerMasterDetails.C051.oldValue);
                               }
                               if(vm.customerMasterDetails.C052){
                                     vm.customerMasterDetails.C052.oldValue = convertToDateObject(vm.customerMasterDetails.C052.oldValue);
                               }
                               if(vm.customerMasterDetails.C053){
                                     vm.customerMasterDetails.C053.oldValue = convertToDateObject(vm.customerMasterDetails.C053.oldValue);
                               }

                               if(vm.getDetails.masterDetailsValueModel.length >= 2){
                                    vm.customerMasterDetailsTwo = vm.getDetails.masterDetailsValueModel[1];
                                   vm.customerMasterDetailsTwo.C013.oldValue = convertToDateObject(vm.customerMasterDetailsTwo.C013.oldValue);
                                     if(vm.customerMasterDetailsTwo.C030){
                                          vm.customerMasterDetailsTwo.C030.oldValue = convertToDateObject(vm.customerMasterDetailsTwo.C030.oldValue);
                                      }
                                       if(vm.customerMasterDetailsTwo.C039){
                                            vm.customerMasterDetailsTwo.C039.oldValue = convertToDateObject(vm.customerMasterDetailsTwo.C039.oldValue);
                                      }
                                     if(vm.customerMasterDetailsTwo.C051){
                                          vm.customerMasterDetailsTwo.C051.oldValue = convertToDateObject(vm.customerMasterDetailsTwo.C051.oldValue);
                                     }
                                     if(vm.customerMasterDetailsTwo.C052){
                                           vm.customerMasterDetailsTwo.C052.oldValue = convertToDateObject(vm.customerMasterDetailsTwo.C052.oldValue);
                                     }
                                     if(vm.customerMasterDetailsTwo.C053){
                                           vm.customerMasterDetailsTwo.C053.oldValue = convertToDateObject(vm.customerMasterDetailsTwo.C053.oldValue);
                                     }

                                     if(vm.customerMasterDetailsTwo.C064.oldValue){
                                          vm.customerMasterDetailsTwoTdsRateCode = vm.customerMasterDetailsTwo.C064.oldValue;
                                     }

                               }
                               if(vm.getDetails.masterDetailsValueModel.length >= 3){
                                   vm.customerMasterDetailsThree = vm.getDetails.masterDetailsValueModel[2];
                                  vm.customerMasterDetailsThree.C013.oldValue = convertToDateObject(vm.customerMasterDetailsThree.C013.oldValue);
                                                 if(vm.customerMasterDetailsThree.C030){
                                                      vm.customerMasterDetailsThree.C030.oldValue = convertToDateObject(vm.customerMasterDetailsThree.C030.oldValue);
                                                  }
                                                   if(vm.customerMasterDetailsThree.C039){
                                                        vm.customerMasterDetailsThree.C039.oldValue = convertToDateObject(vm.customerMasterDetailsThree.C039.oldValue);
                                                  }
                                                 if(vm.customerMasterDetailsThree.C051){
                                                      vm.customerMasterDetailsThree.C051.oldValue = convertToDateObject(vm.customerMasterDetailsThree.C051.oldValue);
                                                 }
                                                 if(vm.customerMasterDetailsThree.C052){
                                                       vm.customerMasterDetailsThree.C052.oldValue = convertToDateObject(vm.customerMasterDetailsThree.C052.oldValue);
                                                 }
                                                 if(vm.customerMasterDetailsThree.C053){
                                                       vm.customerMasterDetailsThree.C053.oldValue = convertToDateObject(vm.customerMasterDetailsThree.C053.oldValue);
                                                 }

                                                   if(vm.customerMasterDetailsThree.C064.oldValue){
                                                          vm.customerMasterDetailsThreeTdsRateCode = vm.customerMasterDetailsThree.C064.oldValue;
                                                   }
                              }
								try{
									if(vm.customerMasterDetails.C038.oldValue == 'NONE'){
                                    vm.customerMasterDetails.C064.oldValue = vm.tdsRateApplicableResponse;
                                }
								}catch(e){}


                                try{
                                    if(vm.customerMasterDetailsTwo.C038.oldValue == 'NONE'){
                                        vm.customerMasterDetailsTwo.C064.oldValue = vm.tdsRateApplicableResponse;
                                    }
                                }catch(e){}

                                try{
                                    if(vm.customerMasterDetailsThree.C038.oldValue == 'NONE'){
                                        vm.customerMasterDetailsThree.C064.oldValue = vm.tdsRateApplicableResponse;
                                    }
                                }catch(e){}



                            try{
                                vm.customerMasterDetails.C055.oldValue = vm.customerMasterDetails.C055.oldValue == 'TRUE' ? true : false;
                                vm.customerMasterDetails.C056.oldValue = vm.customerMasterDetails.C056.oldValue == 'TRUE' ? true : false;
                                vm.customerMasterDetails.C057.oldValue = vm.customerMasterDetails.C057.oldValue == 'TRUE' ? true : false;
                                vm.customerMasterDetails.C058.oldValue = vm.customerMasterDetails.C058.oldValue == 'TRUE' ? true : false;
                                vm.customerMasterDetails.C059.oldValue = vm.customerMasterDetails.C059.oldValue == 'TRUE' ? true : false;
                                vm.customerMasterDetails.C060.oldValue = vm.customerMasterDetails.C060.oldValue == 'TRUE' ? true : false;
                                vm.customerMasterDetails.C061.oldValue = vm.customerMasterDetails.C061.oldValue == 'TRUE' ? true : false;
                                vm.customerMasterDetails.C062.oldValue = vm.customerMasterDetails.C062.oldValue == 'TRUE' ? true : false;
                            }catch(e){}

                            try{
                                vm.customerMasterDetailsTwo.C055.oldValue = vm.customerMasterDetailsTwo.C055.oldValue == 'TRUE' ? true : false;
                                vm.customerMasterDetailsTwo.C056.oldValue = vm.customerMasterDetailsTwo.C056.oldValue == 'TRUE' ? true : false;
                                vm.customerMasterDetailsTwo.C057.oldValue = vm.customerMasterDetailsTwo.C057.oldValue == 'TRUE' ? true : false;
                                vm.customerMasterDetailsTwo.C058.oldValue = vm.customerMasterDetailsTwo.C058.oldValue == 'TRUE' ? true : false;
                                vm.customerMasterDetailsTwo.C059.oldValue = vm.customerMasterDetailsTwo.C059.oldValue == 'TRUE' ? true : false;
                                vm.customerMasterDetailsTwo.C060.oldValue = vm.customerMasterDetailsTwo.C060.oldValue == 'TRUE' ? true : false;
                                vm.customerMasterDetailsTwo.C061.oldValue = vm.customerMasterDetailsTwo.C061.oldValue == 'TRUE' ? true : false;
                                vm.customerMasterDetailsTwo.C062.oldValue = vm.customerMasterDetailsTwo.C062.oldValue == 'TRUE' ? true : false;
                            }catch(e){}

                            try{
                                vm.customerMasterDetailsThree.C055.oldValue = vm.customerMasterDetailsThree.C055.oldValue == 'TRUE' ? true : false;
                                vm.customerMasterDetailsThree.C056.oldValue = vm.customerMasterDetailsThree.C056.oldValue == 'TRUE' ? true : false;
                                vm.customerMasterDetailsThree.C057.oldValue = vm.customerMasterDetailsThree.C057.oldValue == 'TRUE' ? true : false;
                                vm.customerMasterDetailsThree.C058.oldValue = vm.customerMasterDetailsThree.C058.oldValue == 'TRUE' ? true : false;
                                vm.customerMasterDetailsThree.C059.oldValue = vm.customerMasterDetailsThree.C059.oldValue == 'TRUE' ? true : false;
                                vm.customerMasterDetailsThree.C060.oldValue = vm.customerMasterDetailsThree.C060.oldValue == 'TRUE' ? true : false;
                                vm.customerMasterDetailsThree.C061.oldValue = vm.customerMasterDetailsThree.C061.oldValue == 'TRUE' ? true : false;
                                vm.customerMasterDetailsThree.C062.oldValue = vm.customerMasterDetailsThree.C062.oldValue == 'TRUE' ? true : false;
                            }catch(e){}


                            }, function(error) {
                               vm.loader = false;
                               handleErrorResponse(error);
                               $state.go('bond_ledger_acc_main.modify');
                            });
                }


            }


            /* Modify Submit */
            function modifycustomerMaster() {
//                    Swal.fire({
//                            title: "Are you sure?",
//                            text: "Do you want to Really Modify this record?",
//                            type: "warning",
//                            showCancelButton: true,
//                            cancelButtonClass: "btn-cancel",
//                            confirmButtonClass: "btn-primary",
//                            confirmButtonText: "Yes",
//                            closeOnConfirm: false
//                        },
//                        function() {



                      Swal.fire({
                        title: "Are you sure?",
                        text: "Do you want to Really Modify this record?",
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
                            vm.resultArray = [];


                            try{

                               if(vm.customerMasterDetails.C062.oldValue == false){

                               vm.customerMasterDetails.C041 ? vm.customerMasterDetails.C041.oldValue = '' : "";

                               }


                                vm.customerMasterDetails.C055.oldValue = vm.customerMasterDetails.C055.oldValue == true ? 'TRUE' : 'FALSE';
                                vm.customerMasterDetails.C056.oldValue = vm.customerMasterDetails.C056.oldValue == true ? 'TRUE' : 'FALSE';
                                vm.customerMasterDetails.C057.oldValue = vm.customerMasterDetails.C057.oldValue == true ? 'TRUE' : 'FALSE';
                                vm.customerMasterDetails.C058.oldValue = vm.customerMasterDetails.C058.oldValue == true ? 'TRUE' : 'FALSE';
                                vm.customerMasterDetails.C059.oldValue = vm.customerMasterDetails.C059.oldValue == true ? 'TRUE' : 'FALSE';
                                vm.customerMasterDetails.C060.oldValue = vm.customerMasterDetails.C060.oldValue == true ? 'TRUE' : 'FALSE';
                                vm.customerMasterDetails.C061.oldValue = vm.customerMasterDetails.C061.oldValue == true ? 'TRUE' : 'FALSE';
                                vm.customerMasterDetails.C062.oldValue = vm.customerMasterDetails.C062.oldValue == true ? 'TRUE' : 'FALSE';
                            }catch(e){}

                            try{
                               if(vm.customerMasterDetailsTwo.C062.oldValue == false){

                               vm.customerMasterDetailsTwo.C041 ? vm.customerMasterDetailsTwo.C041.oldValue = '' : "";

                               }
                                vm.customerMasterDetailsTwo.C055.oldValue = vm.customerMasterDetailsTwo.C055.oldValue == true ? 'TRUE' : 'FALSE';
                                vm.customerMasterDetailsTwo.C056.oldValue = vm.customerMasterDetailsTwo.C056.oldValue == true ? 'TRUE' : 'FALSE';
                                vm.customerMasterDetailsTwo.C057.oldValue = vm.customerMasterDetailsTwo.C057.oldValue == true ? 'TRUE' : 'FALSE';
                                vm.customerMasterDetailsTwo.C058.oldValue = vm.customerMasterDetailsTwo.C058.oldValue == true ? 'TRUE' : 'FALSE';
                                vm.customerMasterDetailsTwo.C059.oldValue = vm.customerMasterDetailsTwo.C059.oldValue == true ? 'TRUE' : 'FALSE';
                                vm.customerMasterDetailsTwo.C060.oldValue = vm.customerMasterDetailsTwo.C060.oldValue == true ? 'TRUE' : 'FALSE';
                                vm.customerMasterDetailsTwo.C061.oldValue = vm.customerMasterDetailsTwo.C061.oldValue == true ? 'TRUE' : 'FALSE';
                                vm.customerMasterDetailsTwo.C062.oldValue = vm.customerMasterDetailsTwo.C062.oldValue == true ? 'TRUE' : 'FALSE';
                            }catch(e){}

                            try{
                              if(vm.customerMasterDetailsThree.C062.oldValue == false){

                               vm.customerMasterDetailsThree.C041 ? vm.customerMasterDetailsThree.C041.oldValue = '' : "";

                               }
                                vm.customerMasterDetailsThree.C055.oldValue = vm.customerMasterDetailsThree.C055.oldValue == true ? 'TRUE' : 'FALSE';
                                vm.customerMasterDetailsThree.C056.oldValue = vm.customerMasterDetailsThree.C056.oldValue == true ? 'TRUE' : 'FALSE';
                                vm.customerMasterDetailsThree.C057.oldValue = vm.customerMasterDetailsThree.C057.oldValue == true ? 'TRUE' : 'FALSE';
                                vm.customerMasterDetailsThree.C058.oldValue = vm.customerMasterDetailsThree.C058.oldValue == true ? 'TRUE' : 'FALSE';
                                vm.customerMasterDetailsThree.C059.oldValue = vm.customerMasterDetailsThree.C059.oldValue == true ? 'TRUE' : 'FALSE';
                                vm.customerMasterDetailsThree.C060.oldValue = vm.customerMasterDetailsThree.C060.oldValue == true ? 'TRUE' : 'FALSE';
                                vm.customerMasterDetailsThree.C061.oldValue = vm.customerMasterDetailsThree.C061.oldValue == true ? 'TRUE' : 'FALSE';
                                vm.customerMasterDetailsThree.C062.oldValue = vm.customerMasterDetailsThree.C062.oldValue == true ? 'TRUE' : 'FALSE';
                            }catch(e){}



                            var oneObj = Object.fromEntries(Object.entries(vm.customerMasterDetails));
                            oneObj.C013.oldValue = $filter('date')(oneObj.C013.oldValue, "dd-MMM-yyyy");
                            if(oneObj.C030){
                                oneObj.C030.oldValue = $filter('date')(oneObj.C030.oldValue, "dd-MMM-yyyy");
                            }
                            if(oneObj.C039){
                                oneObj.C039.oldValue = $filter('date')(oneObj.C039.oldValue, "dd-MMM-yyyy");
                            }
                            if(oneObj.C051){
                                oneObj.C051.oldValue = $filter('date')(oneObj.C051.oldValue, "dd-MMM-yyyy");
                                oneObj.C052.oldValue = $filter('date')(oneObj.C052.oldValue, "dd-MMM-yyyy");
                            }
                            if(oneObj.C053){
                                oneObj.C053.oldValue = $filter('date')(oneObj.C053.oldValue, "dd-MMM-yyyy");
                            }
                            vm.resultArray.push(convertToColumnValuesArrayList(oneObj));

                            //second joint holder
                            if(vm.customerMasterDetailsTwo){
                                var twoObj = Object.fromEntries(Object.entries(vm.customerMasterDetailsTwo));
                                twoObj.C013.oldValue = $filter('date')(twoObj.C013.oldValue, "dd-MMM-yyyy");
                                if(twoObj.C030){
                                    twoObj.C030.oldValue = $filter('date')(twoObj.C030.oldValue, "dd-MMM-yyyy");
                                }
                                if(twoObj.C039){
                                    twoObj.C039.oldValue = $filter('date')(twoObj.C039.oldValue, "dd-MMM-yyyy");
                                }
                                if(twoObj.C051){
                                    twoObj.C051.oldValue = $filter('date')(twoObj.C051.oldValue, "dd-MMM-yyyy");
                                    twoObj.C052.oldValue = $filter('date')(twoObj.C052.oldValue, "dd-MMM-yyyy");
                                }
                                if(twoObj.C053){
                                    twoObj.C053.oldValue = $filter('date')(twoObj.C053.oldValue, "dd-MMM-yyyy");
                                }
                                vm.resultArray.push(convertToColumnValuesArrayList(twoObj));
                            }

                             //third joint holder
                            if(vm.customerMasterDetailsThree){
                                var threeObj = Object.fromEntries(Object.entries(vm.customerMasterDetailsThree));
                                threeObj.C013.oldValue = $filter('date')(threeObj.C013.oldValue, "dd-MMM-yyyy");
                                if(threeObj.C030){
                                    threeObj.C030.oldValue = $filter('date')(threeObj.C030.oldValue, "dd-MMM-yyyy");
                                }
                                if(threeObj.C039){
                                    threeObj.C039.oldValue = $filter('date')(threeObj.C039.oldValue, "dd-MMM-yyyy");
                                }
                                if(threeObj.C051){
                                        threeObj.C051.oldValue = $filter('date')(threeObj.C051.oldValue, "dd-MMM-yyyy");
                                        threeObj.C052.oldValue = $filter('date')(threeObj.C052.oldValue, "dd-MMM-yyyy");
                                    }
                                  if(threeObj.C053){
                                    threeObj.C053.oldValue = $filter('date')(threeObj.C053.oldValue, "dd-MMM-yyyy");
                                }
                                vm.resultArray.push(convertToColumnValuesArrayList(threeObj));
                            }



                            let requestData = {
                                'schemeCode':vm.schemeCode,
                                'columnValueDetailsList': vm.resultArray,
                            }
                            bondLedgerAccMaintenanceService.modifySubmit(requestData).then(
                                (response) => {
                                    vm.loader = false;
                                    Swal.fire("Success!","Bond Ledger Account Modified Successfully","success");
                                    location.href = '#!/bond_ledger_acc_main/action/inquiry';
                                }, function(error) {
                                   vm.loader = false;
                                   handleErrorResponse(error);
                                   $state.go('bond_ledger_acc_main.modify');
                                });
                 }
                        });
            }
            //Verify Select
            function getVerifySelectDetails(flag){
                vm.loader = true;

                if(flag == 'torch'){
                var requestData = {
                    'schemeCode' : vm.schemeCode,
                };
                    bondLedgerAccMaintenanceService.verifySelect(requestData).then(function(response){
                    vm.loader = false;
                    vm.torchList = response.data.result;
                    angular.element('#blaModel').modal('toggle');

                                    },function(error) {
                                    vm.loader = false;
                                        handleErrorResponse(error);
                                      });
                }else{
                            var requestData = {
                                'schemeCode' : vm.schemeCode,
                                'blaNum' : vm.blaNo

                            };
                                bondLedgerAccMaintenanceService.verifySelect(requestData).then(function(response){
                                vm.loader = false;
                                vm.getDetails = response.data.result;
                                  if(vm.getDetails.masterDetailsValueModel.length == 1){
                                        vm.titleText = 'BOND HOLDER DETAILS';
                                   }else{
                                         vm.titleText = 'FIRST JOINT BOND HOLDER DETAILS';

                                         vm.tempArray = [];
                                         vm.tempArray.push('0');
                                         vm.tempArray.push('1');
                                         if(vm.getDetails.masterDetailsValueModel.length > 2){
                                             vm.tempArray.push('2');
                                         }

                                         angular.forEach(vm.getDetails.masterDetailsValueModel,function(item){

                                             if(item.C045.oldValue == '1'){
                                                 vm.tempArray.splice(0,'0', item);
                                             }
                                             if(item.C045.oldValue == '2'){
                                                 vm.tempArray.splice(1,'1', item);
                                             }
                                             if(item.C045.oldValue == '3'){
                                                 vm.tempArray.splice(2,'2', item);
                                             }

                                         });

                                         var value = vm.tempArray.filter((value) => {
                                             if(value == '0'){

                                             }else
                                             if(value == '1'){

                                             }else
                                              if(value == '2'){

                                             }else{
                                                 return value;
                                             }
                                         });
                                         vm.getDetails.masterDetailsValueModel = value;
                                   }
                                },function(error) {
                                    vm.loader = false;
                                    handleErrorResponse(error);
                                    $state.go('bond_ledger_acc_main.list');
                                  });
                }

//                var requestData = {};
//                if(flag =='tourch'){
//                    requestData.custId = vm.custId;
//                    requestData.custIdFlag = 'Y';
//                }
//                else{
//                    requestData.custId = vm.custId;
//                    requestData.custIdFlag = '';
//                }

//                bondLedgerAccMaintenanceService.verifySelect(requestData).then(function(response){
//                        if(flag =='tourch'){
//                            vm.custIdList = response.data.result.custIdList;
//                            angular.element('#custIdModel').modal('toggle');
//                        }
//                        else{
//                             vm.getScssVerifyDetails = response.data.result;
//                             vm.propertyCheck = vm.getScssVerifyDetails.masterDetailsValueModel[0];
//                             if(vm.propertyCheck.hasOwnProperty('C021') == true){
//                                vm.fieldShow21 = true;
//                                if(vm.getScssVerifyDetails.masterDetailsValueModel[0].C021.newValue !=null){
//                                      vm.getScssVerifyDetails.masterDetailsValueModel[0].C021.newValue = new Date(vm.getScssVerifyDetails.masterDetailsValueModel[0].C021.newValue);
//                                }
//                             }
//                             if(vm.propertyCheck.hasOwnProperty('C036') == true){
//                                 vm.fieldShow36 = true;
//                                 if(vm.getScssVerifyDetails.masterDetailsValueModel[0].C036.newValue !=null){
//                                      vm.getScssVerifyDetails.masterDetailsValueModel[0].C036.newValue = new Date(vm.getScssVerifyDetails.masterDetailsValueModel[0].C036.newValue);
//                                 }
//                             }
//                             vm.getScssKycVerifyDetails = response.data.result.formDetailsValueModel;
//                             vm.getScssKycOtherVerifyDetails = response.data.result.formDetailsValueModel;
//                             vm.getScssOtherBankVerifyDetails = response.data.result.otherBanksDetailsValueModel;
//                                if(vm.getScssVerifyDetails.masterDetailsValueModel[0].C002.oldValue == 'MH002'){
//                                    vm.titleText="FIRST JOINT ACCOUNT HOLDER DETAILS";
//                                    vm.holdingStatus ="JOINT";
//                                }else{
//                                    vm.titleText="CUSTOMER MASTER DETAILS";
//                                    vm.holdingStatus ="SINGLE";
//                                }
//                                getVerifyTdsApplicableRate();
//                             }
//
//
//
//
//                        vm.loader = false;
//
//                },function(error) {
//                    handleErrorResponse(error);
//                    $state.go('bond_ledger_acc_main.list');
//                  });
            }
            /* Verify Submit */
            function verifyCustomerMaster() {
//                    Swal.fire({
//                            title: "Are you sure?",
//                            text: "Do you want to Really Verify this record?",
//                            type: "warning",
//                            showCancelButton: true,
//                            cancelButtonClass: "btn-cancel",
//                            confirmButtonClass: "btn-primary",
//                            confirmButtonText: "Yes",
//                            closeOnConfirm: false
//                        },
//                        function() {


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
                                'blaGroupId': vm.getDetails.blaGroupId,
                            }
                            bondLedgerAccMaintenanceService.verifySubmit(requestData).then(
                                (response) => {
                                    vm.loader = false;
                                    if(vm.getDetails.lastVerifiedBy != null){
                                        Swal.fire("Success!","Bond Ledger Account Verified Successfully","success");
                                    }else{
                                        var message = 'Bond Ledger Account Verified Successfully';
                                        vm.modeOfHolding = vm.getDetails.masterDetailsValueModel[0].C001.oldValue;
                                        if(vm.modeOfHolding == 'MH001' || vm.modeOfHolding == 'MH004' || vm.modeOfHolding == 'MH005'){
                                            message = message + '\nBLA No: ' + vm.getDetails.masterDetailsValueModel[0].C067.oldValue;
//                                            if(vm.getDetails.masterDetailsValueModel[0].C014.oldValue == 'Y'){
//                                                message = message + '\nGuardian ID: ' + vm.getDetails.masterDetailsValueModel[0].C025.oldValue;
//                                            }
                                        }
                                        else{
                                            if(vm.getDetails.masterDetailsValueModel[0]){
                                                message = message + '\nFirst Joint BLA No: ' + vm.getDetails.masterDetailsValueModel[0].C067.oldValue;
//                                                if(vm.getDetails.masterDetailsValueModel[0].C014.oldValue == 'Y'){
//                                                    message = message + '\nFirst Joint Guardian ID: ' + vm.getDetails.masterDetailsValueModel[0].C025.oldValue;
//                                                }
                                            }

                                            if(vm.getDetails.masterDetailsValueModel[1]){
                                                message = message + '\nSecond Joint BLA No: ' + vm.getDetails.masterDetailsValueModel[1].C067.oldValue;
//                                                if(vm.getDetails.masterDetailsValueModel[1].C014.oldValue == 'Y'){
//                                                    message = message + '\nSecond Joint Guardian ID: ' + vm.getDetails.masterDetailsValueModel[1].C025.oldValue;
//                                                }
                                            }

                                              if(vm.getDetails.masterDetailsValueModel[2]){
                                                message = message + '\nThird Joint BLA No: ' + vm.getDetails.masterDetailsValueModel[2].C067.oldValue;
//                                                if(vm.getDetails.masterDetailsValueModel[2].C014.oldValue == 'Y'){
//                                                    message = message + '\nThird Joint Guardian ID: ' + vm.getDetails.masterDetailsValueModel[2].C025.oldValue;
//                                                }
                                            }

                                        }
                                        Swal.fire("Success!", message ,"success");
                                    }

                                    location.href = '#!/bond_ledger_acc_main/action/inquiry';
                                }, function(error) {
                                   vm.loader = false;
                                   handleErrorResponse(error);
                                });
                                }

                        });
            }
            /* Delete Submit */
            function deleteCustomerMaster() {
//                    Swal.fire({
//                            title: "Are you sure?",
//                            text: "Do you want to Really Delete this record?",
//                            type: "warning",
//                            showCancelButton: true,
//                            cancelButtonClass: "btn-cancel",
//                            confirmButtonClass: "btn-primary",
//                            confirmButtonText: "Yes",
//                            closeOnConfirm: false
//                        },
//                        function() {



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

                            let requestData = {
                                'schemeCode': vm.schemeCode,
                                'blaGroupId': vm.getDetails.blaGroupId,
                            }
                            bondLedgerAccMaintenanceService.deleteSubmit(requestData).then(
                                (response) => {
                                    vm.loader = false;
                                    Swal.fire("Success!","Bond Ledger Account Deleted Successfully","success");
                                    location.href = '#!/bond_ledger_acc_main/action/inquiry';
                                }, function(error) {
                                   vm.loader = false;
                                   handleErrorResponse(error);
                                });
             }
                        });
            }
            //Inquiry Select
            function getInquirySelectDetails(flag){
                vm.loader = true;

                if(flag =='tourch'){
                    var requestData = {
                        'schemeCode': vm.schemeCode,
                    };
                     bondLedgerAccMaintenanceService.inquirySelect(requestData).then(function(response){
                      vm.loader = false;
                      vm.blaDataList = response.data.result;
                      angular.element('#blaModel').modal('toggle');
                    },function(error) {
                        vm.loader = false;
                        handleErrorResponse(error);
                      });
                }else{

                    var requestData = {
                        'schemeCode': vm.schemeCode,
                        'blaNum': vm.blaNo,
                    };
                     bondLedgerAccMaintenanceService.inquirySelect(requestData).then(function(response){

                       vm.getDetails = response.data.result;
                        vm.loader = false;

                       if(vm.getDetails.masterDetailsValueModel.length == 1){
                            vm.titleText = 'BOND HOLDER DETAILS';
                       }else{
                             vm.titleText = 'FIRST JOINT BOND HOLDER DETAILS';

                            vm.tempArray = [];
                            vm.tempArray.push('0');
                            vm.tempArray.push('1');
                            if(vm.getDetails.masterDetailsValueModel.length > 2){
                                vm.tempArray.push('2');
                            }

                            angular.forEach(vm.getDetails.masterDetailsValueModel,function(item){

                                if(item.C045.oldValue == '1'){
                                    vm.tempArray.splice(0,'0', item);
                                }
                                if(item.C045.oldValue == '2'){
                                    vm.tempArray.splice(1,'1', item);
                                }
                                if(item.C045.oldValue == '3'){
                                    vm.tempArray.splice(2,'2', item);
                                }

                            });

                            var value = vm.tempArray.filter((value) => {
                                if(value == '0'){

                                }else
                                if(value == '1'){

                                }else
                                 if(value == '2'){

                                }else{
                                    return value;
                                }
                            });
                                vm.getDetails.masterDetailsValueModel = value;

                       }








                    },function(error) {
                        vm.loader = false;
                        handleErrorResponse(error);
                        $state.go('bond_ledger_acc_main.list');
                      });
                }

//                bondLedgerAccMaintenanceService.inquirySelect(requestData).then(function(response){
//                        if(flag =='tourch'){
//                            vm.custIdList = response.data.result.custIdList;
//                            angular.element('#custIdModel').modal('toggle');
//                        }
//                        else{
//                             vm.getScssVerifyDetails = response.data.result;
//                             vm.propertyCheck = vm.getScssVerifyDetails.masterDetailsValueModel[0];
//                              if(vm.propertyCheck.hasOwnProperty('C021') == true){
//                                  vm.fieldShow21 = true;
//                                  if(vm.getScssVerifyDetails.masterDetailsValueModel[0].C021.newValue !=null){
//                                        vm.getScssVerifyDetails.masterDetailsValueModel[0].C021.newValue = new Date(vm.getScssVerifyDetails.masterDetailsValueModel[0].C021.newValue);
//                                  }
//                               }
//                               if(vm.propertyCheck.hasOwnProperty('C036') == true){
//                                   vm.fieldShow36 = true;
//                                   if(vm.getScssVerifyDetails.masterDetailsValueModel[0].C036.newValue !=null){
//                                        vm.getScssVerifyDetails.masterDetailsValueModel[0].C036.newValue = new Date(vm.getScssVerifyDetails.masterDetailsValueModel[0].C036.newValue);
//                                   }
//                               }
//                             vm.getScssKycVerifyDetails = response.data.result.formDetailsValueModel;
//                             vm.getScssKycOtherVerifyDetails = response.data.result.formDetailsValueModel;
//                             vm.getScssOtherBankVerifyDetails = response.data.result.otherBanksDetailsValueModel;
//                                if(vm.getScssVerifyDetails.masterDetailsValueModel[0].C002.oldValue == 'MH002'){
//                                    vm.titleText="FIRST JOINT ACCOUNT HOLDER DETAILS";
//                                    vm.holdingStatus ="JOINT";
//                                }else{
//                                    vm.titleText="CUSTOMER MASTER DETAILS";
//                                    vm.holdingStatus ="SINGLE";
//                                }
//                                getVerifyTdsApplicableRate();
//                             }
//
//                        vm.loader = false;
//
//                },function(error) {
//                    vm.loader = false;
//                    handleErrorResponse(error);
//                    $state.go('bond_ledger_acc_main.list');
//                  });
            }


             function getValidations(flag) {
//                vm.flagVal = flag;
//
//                bondLedgerAccMaintenanceService.accountTypeValid().then(function(response){
//                    if(response) {
//                        vm.basicValidations = response.data.result[0];
//                        vm.minAgeSenior = vm.basicValidations.minAgeSeniorCitizen;
//                        vm.minAgeVolRetire = vm.basicValidations.minAgeVolRetire;
//                        vm.minAgeDefPerson = vm.basicValidations.minAgeDefPerson;
//                        if(vm.flagVal == 'AT001'){
//                            vm.minAge = vm.minAgeSenior;
//                        }else if(vm.flagVal == 'AT002'){
//                            vm.minAge = vm.minAgeVolRetire;
//                        }else if(vm.flagVal == 'AT003'){
//                            vm.minAge = vm.minAgeDefPerson;
//                        }
//                        accountHolderDateValidation(vm.minAge);
//                    }else {
//                        Swal.fire("Error!", response.message, "error");
//                        vm.loader = false;
//                    }
//
//                 }, function(error) {
//                    handleErrorResponse(error);
//                  });
             }
//             vm.dobCalenderOptionsTwo  = {
//                                     //minDate: minDate,
//                                     maxDate: convertToDateObject(vm.loginSolDate),
//                                     showWeeks: false
//                                 };
//                                 vm.dobCalenderOptionsThree  = {
//                                                         //minDate: minDate,
//                                                         maxDate: convertToDateObject(vm.loginSolDate),
//                                                         showWeeks: false
//                                                     };
             function accountHolderDateValidation(minAge){
                    var solDate = vm.loginSolDate;
                    var maxYear = convertToDateObject(solDate);
                    maxYear.setFullYear(maxYear.getFullYear() - 18);
                    var maxDate = maxYear;
                    vm.dobCalenderOptions  = {
                        //minDate: minDate,
                        maxDate: maxDate,
                        showWeeks: false
                    };
                    vm.customerMasterDetails.C020 = '';
             }
             function depositDateValidation(){
                  vm.depositDate={
                        maxDate: convertToDateObject(vm.loginSolDate),
                        showWeeks: false
                  }

             }
              function maturityDateValid(tempMatDate){
                    vm.maturityDate={
                          minDate: tempMatDate,
                          showWeeks: false
                    }

              }
              function dateOfRetirementValid(){
                    vm.customerMasterDetails.C022 ={
                        minDate: convertToDateObject(vm.loginSolDate),
                        showWeeks: false
                    }

              }
              function maturityDateValidation() {
                angular.forEach(vm.scssOtherBanksDetails, function(item){
                    item.C006 = item.C006;
                    vm.maturityDate = {
                          minDate: item.C006,
                          showWeeks: false
                    }
                })

              }
              vm.retirDatePickerValidation = {
                  showWeeks : false,
                  maxDate : convertToDateObject(vm.loginSolDate)
              }
              // format date from dd/MM/yyyy to yyyy-MM-dd
               function formatDate(tempDate){
                       return $filter('date')(tempDate, "dd-MMM-yyyy");
               }

               function dodVal(){
                     vm.dodCalenderOptions={
                       maxDate: convertToDateObject(vm.loginSolDate),
                       showWeeks: false
                      }

                }

                 function dodRecVal(recDate){
                          vm.dodRecCalenderOptions={
                           minDate: recDate,
                           showWeeks: false
                                      }

                  }

             // Cancel Confirmation
             function showCancelConfirmation(state) {
//                 Swal.fire({
//                         title: "Confirm Cancel",
//                         text: "Do you really want to cancel?",
//                         type: "warning",
//                         showCancelButton: true,
//                         cancelButtonClass: "btn-cancel",
//                         confirmButtonClass: "btn-danger",
//                         confirmButtonText: "Yes",
//                         cancelButtonText: "No",
//                         closeOnConfirm: true
//                     },
//                     function() {

Swal.fire({
  title: "Are you sure?",
  text: "Do you really want to cancel?",
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

        function getDisableTorchFlag(customerId){
          if(customerId){
            if(customerId.length>=5){
                   vm.disableTorchFlag = false;
            }else{
                   vm.disableTorchFlag = true;
            }
          }else{
                   vm.disableTorchFlag = true;
          }
        }


        function getCbsCustFstJoinDetailsTwo(){
                    vm.loader = true;
                    var requestData = {
                        'cifId': vm.customerMasterDetailsTwo.cbsCustId,
                                               'operativeAccNo' : vm.customerMasterDetailsTwo.operativeAccNum,
                                               'operativeAccBranchId' : vm.customerMasterDetailsTwo.operativeAccBranchId
                                           };

                  bondLedgerAccMaintenanceService.fetchCbsCustId(requestData).then( (response) => {
                            vm.loader = false;
                            vm.cbsDetailTwo = response.data.result;

                            vm.customerMasterDetailsTwo.bondHldFirstName = vm.cbsDetailTwo.firstName;
                            vm.customerMasterDetailsTwo.bondHldMidName = vm.cbsDetailTwo.middleName;
                            vm.customerMasterDetailsTwo.bondHldLastName = vm.cbsDetailTwo.lastName;
                            vm.customerMasterDetailsTwo.fatherName = vm.cbsDetailTwo.fatherName;
                            vm.customerMasterDetailsTwo.motherName = vm.cbsDetailTwo.motherName;
                            vm.customerMasterDetailsTwo.gender = vm.cbsDetailTwo.gender;
                            vm.customerMasterDetailsTwo.bondHldDob = convertToDateObject(vm.cbsDetailTwo.dateOfBirth);
                            vm.customerMasterDetailsTwo.isMinor = vm.cbsDetailTwo.isMinor;
                            vm.customerMasterDetailsTwo.bondHldAddOne = vm.cbsDetailTwo.addressOne;
                            vm.customerMasterDetailsTwo.bondHldAddTwo = vm.cbsDetailTwo.addressTwo;
                            vm.customerMasterDetailsTwo.bondHldAddThree = vm.cbsDetailTwo.addressThree;
                            vm.customerMasterDetailsTwo.bondHldCity = vm.cbsDetailTwo.city;
                            vm.customerMasterDetailsTwo.bondHldPinCode = vm.cbsDetailTwo.pinCode;
                            vm.customerMasterDetailsTwo.bondHolderPhoneNumber = vm.cbsDetailTwo.phoneNumber;
                            vm.customerMasterDetailsTwo.bondHldMobNum = vm.cbsDetailTwo.mobileNumber;
                            vm.customerMasterDetailsTwo.bondHldEmail = vm.cbsDetailTwo.email;
                            vm.customerMasterDetailsTwo.operativeAccNum = vm.cbsDetailTwo.operativeAccNo;
                            vm.customerMasterDetailsTwo.operativeAccBranchId = vm.cbsDetailTwo.operativeBranchId;
                            vm.customerMasterDetailsTwo.panNo = vm.cbsDetailTwo.pan;
                            vm.customerMasterDetailsTwo.aadhaarNo = vm.cbsDetailTwo.aadhar;

                        }, function(error) {
                           vm.loader = false;
                           vm.errmsgSingle = 'CBS Failure';
                           handleErrorResponse(error);
                           vm.customerMasterDetailsTwo.cbsCustId = '';
                        });


                }

        function getBlaAccNoDetailsTwo(flag){
                 vm.loader = true;

                 if(flag=='torch'){


                             var requestData = {
                                 'schemeCode': vm.schemeCode,
                                 'modeOfHolding': vm.modeOfHolding,
                             };
                               bondLedgerAccMaintenanceService.fetchBlaAccNoDetails(requestData).then( (response) => {
                                         vm.loader = false;
                                         vm.blaNoListTwo = response.data.result;
                                         if(vm.modeOfHolding == 'MH002' || vm.modeOfHolding == 'MH003'){
                                                 angular.element('#blaJointModalTwo').modal('toggle');
                                         }else{
                                                angular.element('#blaModalTwo').modal('toggle');
                                         }
                                     }, function(error) {
                                        vm.loader = false;
                                        handleErrorResponse(error);
                                     });
                 }
                 else{
                           var requestData = {
                                 'schemeCode': vm.schemeCode,
                                 'modeOfHolding': vm.modeOfHolding,
                                 'blaNum' : vm.customerMasterTwo.blaAccNo,
                                 'jointHolderNo': vm.jointHolderTypeCodeTwo,
                                 };
                               bondLedgerAccMaintenanceService.fetchBlaAccNoDetails(requestData).then( (response) => {
                                         vm.loader = false;
                                         vm.blaAccDetailsTwo = response.data.result[0];
                                         vm.customerMasterDetailsTwo.blaAccNo = vm.blaAccDetailsTwo.blaNo;
                                        vm.customerMasterDetailsTwo.bondHldFirstName = vm.blaAccDetailsTwo.bondHolderFirstName;
                                        vm.customerMasterDetailsTwo.bondHldMidName = vm.blaAccDetailsTwo.bondHolderMiddleName;
                                        vm.customerMasterDetailsTwo.bondHldLastName = vm.blaAccDetailsTwo.bondHolderLastName;
                                        vm.customerMasterDetailsTwo.fatherName = vm.blaAccDetailsTwo.fatherName;
                                        vm.customerMasterDetailsTwo.motherName = vm.blaAccDetailsTwo.motherName;
                                        vm.customerMasterDetailsTwo.gender = vm.blaAccDetailsTwo.gender;
                                        vm.customerMasterDetailsTwo.bondHldDob = convertToDateObject(vm.blaAccDetailsTwo.dateOfBirth);
                                        vm.customerMasterDetailsTwo.isMinor = vm.blaAccDetailsTwo.isMinor;
                                        vm.customerMasterDetailsTwo.bondHldAddOne = vm.blaAccDetailsTwo.addressOne;
                                        vm.customerMasterDetailsTwo.bondHldAddTwo = vm.blaAccDetailsTwo.addressTwo;
                                        vm.customerMasterDetailsTwo.bondHldAddThree = vm.blaAccDetailsTwo.addressThree;
                                        vm.customerMasterDetailsTwo.bondHldCity = vm.blaAccDetailsTwo.city;
                                        vm.customerMasterDetailsTwo.bondHldPinCode = vm.blaAccDetailsTwo.pinCode;
                                        vm.customerMasterDetailsTwo.bondHolderPhoneNumber = vm.blaAccDetailsTwo.phoneNumber;
                                        vm.customerMasterDetailsTwo.bondHldMobNum = vm.blaAccDetailsTwo.mobileNumber;
                                        vm.customerMasterDetailsTwo.bondHldEmail = vm.blaAccDetailsTwo.email;
                                        vm.customerMasterDetailsTwo.operativeAccNum = vm.blaAccDetailsTwo.operativeAccNo;
                                        vm.customerMasterDetailsTwo.operativeAccBranchId = vm.blaAccDetailsTwo.operativeBranchId;
                                        vm.customerMasterDetailsTwo.panNo = vm.blaAccDetailsTwo.pan;

                                     }, function(error) {
                                        vm.loader = false;
                                        handleErrorResponse(error);
                                     });
                 }


                 }
                  function getClearAllData(x){
                  vm.customerMasterDetails.blaAccNo=null;
                            if(x ==1)
                            {
                            var z= Object.keys(vm.customerMasterDetails);
                            for(var i=0;i<z.length;i++)
                            {
                            vm.customerMasterDetails[z[i]]=''
                            }
                            }else  if(x ==2)
                            {
                           var z = Object.keys(vm.customerMasterDetailsTwo);
                             for(var i=0;i<z.length;i++)
                            {
                            vm.customerMasterDetailsTwo[z[i]]=''
                            }
                            }else if(x ==3)
                            {
                            var z =Object.keys(vm.customerMasterDetailsThree);
                              for(var i=0;i<z.length;i++)
                                                        {
                                                        vm.customerMasterDetailsTwo[z[i]]=''
                                                        }
                            }
                         }

             function getClearAllDatas(){
//                                         if(x ==1)
//                                         {
                                         var z= Object.keys(vm.customerMasterDetails);
                                         for(var i=0;i<z.length;i++)
                                         {
                                         vm.customerMasterDetails[z[i]]=''
                                         }
//                                         }
//                                         else  if(x ==2)
//                                         {
//                                        var z = Object.keys(vm.customerMasterDetailsTwo);
//                                          for(var i=0;i<z.length;i++)
//                                         {
//                                         vm.customerMasterDetailsTwo[z[i]]=''
//                                         }
//                                         }else if(x ==3)
//                                         {
//                                         var z =Object.keys(vm.customerMasterDetailsThree);
//                                           for(var i=0;i<z.length;i++)
//                                                                     {
//                                                                     vm.customerMasterDetailsTwo[z[i]]=''
//                                                                     }
//                                         }
                                      }

        function getGuardianDetailsTwo(flag){
                        vm.loader = true;

                        if(flag == 'select'){
                            var requestData = {
                                'schemeCode' : vm.schemeCode,
                            };
                          bondLedgerAccMaintenanceService.fetchGuardianDetails(requestData).then( (response) => {
                                    vm.loader = false;
                                    vm.guardianDetailsListTwo = response.data.result;
                                    angular.element('#guardianModalTwo').modal('toggle');
                                }, function(error) {
                                   vm.loader = false;
                                   handleErrorResponse(error);
                                });
                        }else{
                            var requestData = {
                                'schemeCode' : vm.schemeCode,
                                'guardianId' : vm.customerMasterDetailsTwo.guardianId,
                            };
                          bondLedgerAccMaintenanceService.fetchGuardianDetails(requestData).then( (response) => {
                                    vm.loader = false;
                                    vm.guardianDetailTwo = response.data.result[0];

                                    vm.customerMasterDetailsTwo.guardianFirstName = vm.guardianDetailTwo.guardianFirstName;
                                    vm.customerMasterDetailsTwo.guardianMiddleName = vm.guardianDetailTwo.guardianMiddleName;
                                    vm.customerMasterDetailsTwo.guardianLastName = vm.guardianDetailTwo.guardianLastName;
                                    vm.customerMasterDetailsTwo.guardianDateOfBirth = convertToDateObject(vm.guardianDetailTwo.dateOfBirth);
                                    vm.customerMasterDetailsTwo.guardianCommunicationAddress = vm.guardianDetailTwo.address;
                                    vm.customerMasterDetailsTwo.guardianMobileNo = vm.guardianDetailTwo.mobileNo;
                                    vm.customerMasterDetailsTwo.guardianEmailId = vm.guardianDetailTwo.email;
                                }, function(error) {
                                   vm.loader = false;
                                   handleErrorResponse(error);
                                });
                        }




                        }

        function optionToggledTwo(){

                 vm.isAllSelected = [];
                    angular.forEach(vm.kycList, function(item)
                    {
                        vm.isAllSelected.push(item.selected);
                    });


                }

        function formFifteenGValidationTwo(value){
                    if(vm.customerMasterDetailsTwo.bondHldDob){
                        vm.customerMasterDetailsTwo.dateSubmission15g15htax = '';
                        var year = parseInt($filter('date')(vm.customerMasterDetailsTwo.bondHldDob, "yyyy"));
                        var cYear = parseInt($filter('date')(new Date(), "yyyy"));
                        var age = cYear - year;
                                    if(value == 'TF001'){
                                        //15g
                                        if(age > 60){
                                            Swal.fire("Error!", "Age of the holder is NOT BELOW 60 years", "error");
                                            vm.customerMasterDetailsTwo.form15g15htaxExeCerti = '';
                                        }
                                        vm.customerMasterDetailsTwo.tdsRateApplicable = '0';
                                    }
                                    if(value == 'TF002'){
                                        //15h
                                        if(age <= 60){
                                            Swal.fire("Error!", "Age of the holder is BELOW 60 years", "error");
                                            vm.customerMasterDetailsTwo.form15g15htaxExeCerti = '';
                                        }
                                        vm.customerMasterDetailsTwo.tdsRateApplicable = '0';
                                    }
                                    if(value == 'TF003') {
                                        //tax excemption cert
                                        vm.customerMasterDetailsTwo.tdsRateApplicable = '0';
                                    }
                                    if(value == 'NONE'){
                                        vm.customerMasterDetailsTwo.tdsRateApplicable = vm.tdsRateApplicableResponse;
                                    }
                    }else{
                         Swal.fire("Error!", "Please select a age", "error");
                         vm.customerMasterDetailsTwo.form15g15htaxExeCerti = '';
                    }


                }

        function formFifteenGValidationTwoM(value){
                            if(vm.customerMasterDetailsTwo.C013.oldValue){

                                var year = parseInt($filter('date')(vm.customerMasterDetailsTwo.C013.oldValue, "yyyy"));
                                var cYear = parseInt($filter('date')(new Date(), "yyyy"));
                                var age = cYear - year;
                                            if(value == 'TF001'){
                                                //15g
                                                if(age > 60){
                                                    Swal.fire("Error!", "Age of the holder is NOT BELOW 60 years", "error");
                                                    vm.customerMasterDetailsTwo.C038.oldValue = '';
                                                }
                                                vm.customerMasterDetailsTwo.C064.oldValue = '0';
                                            }
                                            if(value == 'TF002'){
                                                //15h
                                                if(age <= 60){
                                                    Swal.fire("Error!", "Age of the holder is BELOW 60 years", "error");
                                                    vm.customerMasterDetailsTwo.C038.oldValue = '';
                                                }
                                                vm.customerMasterDetailsTwo.C064.oldValue = '0';
                                            }
                                            if(value == 'TF003'){
                                                //tax excemption cert
                                                vm.customerMasterDetailsTwo.C064.oldValue = '0';
                                            }
                                            if(value == 'NONE'){
                                                    try{
                                                         vm.customerMasterDetailsTwo.C039.oldValue = '';
                                                    }catch(e){}
                                                    vm.customerMasterDetailsTwo.C064.oldValue = vm.tdsRateApplicableResponse;
                                            }
                            }else{
                                 Swal.fire("Error!", "Please select a age", "error");
                                  vm.customerMasterDetailsTwo.C038.oldValue = '';
                            }


                        }


function getCbsCustFstJoinDetailsThree(){
            vm.loader = true;


            var requestData = {
                                        'cifId': vm.customerMasterDetailsThree.cbsCustId,
                                        'operativeAccNo' : vm.customerMasterDetailsThree.operativeAccNum,
                                        'operativeAccBranchId' : vm.customerMasterDetailsThree.operativeAccBranchId
                                    };

          bondLedgerAccMaintenanceService.fetchCbsCustId(requestData).then( (response) => {
                    vm.loader = false;
                    vm.cbsDetailThree = response.data.result;

                    vm.customerMasterDetailsThree.bondHldFirstName = vm.cbsDetailThree.firstName;
                    vm.customerMasterDetailsThree.bondHldMidName = vm.cbsDetailThree.middleName;
                    vm.customerMasterDetailsThree.bondHldLastName = vm.cbsDetailThree.lastName;
                    vm.customerMasterDetailsThree.fatherName = vm.cbsDetailThree.fatherName;
                    vm.customerMasterDetailsThree.motherName = vm.cbsDetailThree.motherName;
                    vm.customerMasterDetailsThree.gender = vm.cbsDetailThree.gender;
                    vm.customerMasterDetailsThree.bondHldDob = convertToDateObject(vm.cbsDetailThree.dateOfBirth);
                    vm.customerMasterDetailsThree.isMinor = vm.cbsDetailThree.isMinor;
                    vm.customerMasterDetailsThree.bondHldAddOne = vm.cbsDetailThree.addressOne;
                    vm.customerMasterDetailsThree.bondHldAddTwo = vm.cbsDetailThree.addressTwo;
                    vm.customerMasterDetailsThree.bondHldAddThree = vm.cbsDetailThree.addressThree;
                    vm.customerMasterDetailsThree.bondHldCity = vm.cbsDetailThree.city;
                    vm.customerMasterDetailsThree.bondHldPinCode = vm.cbsDetailThree.pinCode;
                    vm.customerMasterDetailsThree.bondHolderPhoneNumber = vm.cbsDetailThree.phoneNumber;
                    vm.customerMasterDetailsThree.bondHldMobNum = vm.cbsDetailThree.mobileNumber;
                    vm.customerMasterDetailsThree.bondHldEmail = vm.cbsDetailThree.email;
                    vm.customerMasterDetailsThree.operativeAccNum = vm.cbsDetailThree.operativeAccNo;
                    vm.customerMasterDetailsThree.operativeAccBranchId = vm.cbsDetailThree.operativeBranchId;
                    vm.customerMasterDetailsThree.panNo = vm.cbsDetailThree.pan;
                    vm.customerMasterDetailsThree.aadhaarNo = vm.cbsDetailThree.aadhar;

                }, function(error) {
                   vm.loader = false;
                   vm.errmsgSingle = 'CBS Failure';
                   handleErrorResponse(error);
                   vm.customerMasterDetailsThree.cbsCustId = '';
                });


        }

function getBlaAccNoDetailsThree(flag){
         vm.loader = true;

         if(flag=='torch'){


                     var requestData = {
                         'schemeCode': vm.schemeCode,
                         'modeOfHolding': vm.modeOfHolding,
                     };
                       bondLedgerAccMaintenanceService.fetchBlaAccNoDetails(requestData).then( (response) => {
                                 vm.loader = false;
                                 vm.blaNoListThree = response.data.result;
                                 if(vm.modeOfHolding == 'MH002' || vm.modeOfHolding == 'MH003'){
                                         angular.element('#blaJointModalThree').modal('toggle');
                                 }else{
                                        angular.element('#blaModal').modal('toggle');
                                 }
                             }, function(error) {
                                vm.loader = false;
                                handleErrorResponse(error);
                             });
         }
         else{
                   var requestData = {
                         'schemeCode': vm.schemeCode,
                         'modeOfHolding': vm.modeOfHolding,
                         'blaNum' : vm.customerMasterThree.blaAccNo,
                         'jointHolderNo': vm.jointHolderTypeCodeThree,
                         };

                       bondLedgerAccMaintenanceService.fetchBlaAccNoDetails(requestData).then( (response) => {
                                 vm.loader = false;
                                 vm.blaAccDetailsThree = response.data.result[0];
                                 vm.customerMasterDetailsThree.blaAccNo = vm.blaAccDetailsThree.blaNo;
                                vm.customerMasterDetailsThree.bondHldFirstName = vm.blaAccDetailsThree.bondHolderFirstName;
                                vm.customerMasterDetailsThree.bondHldMidName = vm.blaAccDetailsThree.bondHolderMiddleName;
                                vm.customerMasterDetailsThree.bondHldLastName = vm.blaAccDetailsThree.bondHolderLastName;
                                vm.customerMasterDetailsThree.fatherName = vm.blaAccDetailsThree.fatherName;
                                vm.customerMasterDetailsThree.motherName = vm.blaAccDetailsThree.motherName;
                                vm.customerMasterDetailsThree.gender = vm.blaAccDetailsThree.gender;
                                vm.customerMasterDetailsThree.bondHldDob = convertToDateObject(vm.blaAccDetailsThree.dateOfBirth);
                                vm.customerMasterDetailsThree.isMinor = vm.blaAccDetailsThree.isMinor;
                                vm.customerMasterDetailsThree.bondHldAddOne = vm.blaAccDetailsThree.addressOne;
                                vm.customerMasterDetailsThree.bondHldAddTwo = vm.blaAccDetailsThree.addressTwo;
                                vm.customerMasterDetailsThree.bondHldAddThree = vm.blaAccDetailsThree.addressThree;
                                vm.customerMasterDetailsThree.bondHldCity = vm.blaAccDetailsThree.city;
                                vm.customerMasterDetailsThree.bondHldPinCode = vm.blaAccDetailsThree.pinCode;
                                vm.customerMasterDetailsThree.bondHolderPhoneNumber = vm.blaAccDetailsThree.phoneNumber;
                                vm.customerMasterDetailsThree.bondHldMobNum = vm.blaAccDetailsThree.mobileNumber;
                                vm.customerMasterDetailsThree.bondHldEmail = vm.blaAccDetailsThree.email;
                                vm.customerMasterDetailsThree.operativeAccNum = vm.blaAccDetailsThree.operativeAccNo;
                                vm.customerMasterDetailsThree.operativeAccBranchId = vm.blaAccDetailsThree.operativeBranchId;
                                vm.customerMasterDetailsThree.panNo = vm.blaAccDetailsThree.pan;

                             }, function(error) {
                                vm.loader = false;
                                handleErrorResponse(error);
                             });
         }


         }

function getGuardianDetailsThree(flag){
                vm.loader = true;

                if(flag == 'select'){
                    var requestData = {
                        'schemeCode' : vm.schemeCode,
                    };
                  bondLedgerAccMaintenanceService.fetchGuardianDetails(requestData).then( (response) => {
                            vm.loader = false;
                            vm.guardianDetailsListThree = response.data.result;
                            angular.element('#guardianModalThree').modal('toggle');
                        }, function(error) {
                           vm.loader = false;
                           handleErrorResponse(error);
                        });
                }else{
                    var requestData = {
                        'schemeCode' : vm.schemeCode,
                        'guardianId' : vm.customerMasterDetailsThree.guardianId,
                    };
                  bondLedgerAccMaintenanceService.fetchGuardianDetails(requestData).then( (response) => {
                            vm.loader = false;
                            vm.guardianDetailThree = response.data.result[0];

                            vm.customerMasterDetailsThree.guardianFirstName = vm.guardianDetailThree.guardianFirstName;
                            vm.customerMasterDetailsThree.guardianMiddleName = vm.guardianDetailThree.guardianMiddleName;
                            vm.customerMasterDetailsThree.guardianLastName = vm.guardianDetailThree.guardianLastName;
                            vm.customerMasterDetailsThree.guardianDateOfBirth = convertToDateObject(vm.guardianDetailThree.dateOfBirth);
                            vm.customerMasterDetailsThree.guardianCommunicationAddress = vm.guardianDetailThree.address;
                            vm.customerMasterDetailsThree.guardianMobileNo = vm.guardianDetailThree.mobileNo;
                            vm.customerMasterDetailsThree.guardianEmailId = vm.guardianDetailThree.email;
                        }, function(error) {
                           vm.loader = false;
                           handleErrorResponse(error);
                        });
                }




                }

function optionToggledThree(){

         vm.isAllSelected = [];
            angular.forEach(vm.kycList, function(item)
            {
                vm.isAllSelected.push(item.selected);
            });


        }

        function formFifteenGValidationThree(value){
        debugger;
            if(vm.customerMasterDetailsThree.bondHldDob){
                vm.customerMasterDetailsThree.dateSubmission15g15htax = '';
                var year = parseInt($filter('date')(vm.customerMasterDetailsThree.bondHldDob, "yyyy"));
                var cYear = parseInt($filter('date')(new Date(), "yyyy"));
                var age = cYear - year;
                            if(value == 'TF001'){
                                //15g
                                if(age > 60){
                                    Swal.fire("Error!", "Age of the holder is NOT BELOW 60 years", "error");
                                    vm.customerMasterDetailsThree.form15g15htaxExeCerti = '';
                                }
                                vm.customerMasterDetailsThree.tdsRateApplicable = '0';
                            }
                            if(value == 'TF002'){
                                //15h
                                if(age <= 60){
                                    Swal.fire("Error!", "Age of the holder is BELOW 60 years", "error");
                                    vm.customerMasterDetailsThree.form15g15htaxExeCerti = '';
                                }
                                vm.customerMasterDetailsThree.tdsRateApplicable = '0';
                            }
                            if(value == 'TF003'){
                                //tax excemption cert
                                vm.customerMasterDetailsThree.tdsRateApplicable = '0';
                            }
                            if(value == 'NONE'){
                                vm.customerMasterDetailsThree.tdsRateApplicable = vm.tdsRateApplicableResponse;
                            }
            }else{
                 Swal.fire("Error!", "Please select a age", "error");
                 vm.customerMasterDetailsThree.form15g15htaxExeCerti = '';
            }


        }

        function formFifteenGValidationThreenM(value){
                            if(vm.customerMasterDetailsThree.C013.oldValue){

                                var year = parseInt($filter('date')(vm.customerMasterDetailsThree.C013.oldValue, "yyyy"));
                                var cYear = parseInt($filter('date')(new Date(), "yyyy"));
                                var age = cYear - year;
                                            if(value == 'TF001'){
                                                //15g
                                                if(age > 60){
                                                    Swal.fire("Error!", "Age of the holder is NOT BELOW 60 years", "error");
                                                    vm.customerMasterDetailsThree.C038.oldValue = '';
                                                }
                                                vm.customerMasterDetailsThree.C064.oldValue = '0';
                                            }
                                            if(value == 'TF002'){
                                                //15h
                                                if(age <= 60){
                                                    Swal.fire("Error!", "Age of the holder is BELOW 60 years", "error");
                                                    vm.customerMasterDetailsThree.C038.oldValue = '';
                                                }
                                                vm.customerMasterDetailsThree.C064.oldValue = '0';
                                            }
                                            if(value == 'TF003'){
                                                //tax excemption cert
                                                vm.customerMasterDetailsThree.C064.oldValue = '0';
                                            }
                                            if(value == 'NONE'){
                                                    try{
                                                        vm.customerMasterDetailsThree.C039.oldValue = '';
                                                    }catch(e){}

                                                    vm.customerMasterDetailsThree.C064.oldValue = vm.tdsRateApplicableResponse;
                                            }
                            }else{
                                 Swal.fire("Error!", "Please select a age", "error");
                                  vm.customerMasterDetailsThree.C038.oldValue = '';
                            }


                        }

        function getSchemeTypeListOperativeTwo (acctNum, flag){
                vm.loader = true;
                vm.customerMasterDetailsTwo.operativeAccBranchId='';

                if(acctNum){

                commonService.getCustomAcctInqApi(acctNum.toUpperCase(), function (response) {
        					if (response.success) {
        						vm.accList = response.data;
        						vm.loader = false;
        						if (vm.accList.length > 0) {
        						if (vm.accList[0].status == 'S') {


                                    if(vm.accList[0].accountFreezeStatus) {
                                      vm.cbsErrorMessageTwo = 'Account is Friezed';
                                    }else{
                                        vm.customerMasterDetailsTwo.operativeAccBranchId = vm.accList[0].solID;
                                        vm.cbsErrorMessageTwo = null;
                                    }


        							}
        							if (vm.accList[0].status == 'F') {
        								vm.cbsErrorMessageTwo = 'Invalid  Account  Number';
        							}
        						}
        						else {
                                    vm.cbsErrorMessageTwo = 'Invalid  Account  Number';
        						}
        					} else {
        						Swal.fire("Error!", response.message, "error");
        						vm.loader = false;
        					}
        				}, function (err) {
        					handleErrorResponse(err);
        					vm.loader = false;
        				});
                }else{
                     vm.loader = false;
                 }
            }

        function getSchemeTypeListOperativeThree (acctNum, flag){
                vm.loader = true;
                 vm.customerMasterDetailsThree.operativeAccBranchId='';
                 vm.cbsErrorMessageThree ='';
                if(acctNum){

                commonService.getCustomAcctInqApi(acctNum.toUpperCase(), function (response) {
        					if (response.success) {
        						vm.accList = response.data;
        						vm.loader = false;
        						if (vm.accList.length > 0) {
        						if (vm.accList[0].status == 'S') {


                                    if(vm.accList[0].accountFreezeStatus) {
                                      vm.cbsErrorMessageThree = 'Account is Friezed';
                                    }else{
                                        vm.customerMasterDetailsThree.operativeAccBranchId = vm.accList[0].solID;
                                        vm.cbsErrorMessageThree = null;
                                    }


        							}
        							if (vm.accList[0].status == 'F') {
        								vm.cbsErrorMessageThree= 'Invalid  Account  Number';
        							}
        						}
        						else {
                                    vm.cbsErrorMessageThree = 'Invalid  Account  Number';
        						}
        					} else {
        						Swal.fire("Error!", response.message, "error");
        						vm.loader = false;
        					}
        				}, function (err) {
        					handleErrorResponse(err);
        					vm.loader = false;
        				});

                }else{
                     vm.loader = false;
                 }
            }

        function getCbsCustFstJoinDetailsModify(){
                    vm.loader = true;

                    var requestData = {
                                                'cifId': vm.customerMasterDetails.C004.oldValue,
                                                'operativeAccNo' : vm.customerMasterDetails.C034.oldValue,
                                                'operativeAccBranchId' : vm.customerMasterDetails.C035.oldValue
                                            };

                  bondLedgerAccMaintenanceService.fetchCbsCustId(requestData).then( (response) => {
                            vm.loader = false;
                            vm.cbsDetail = response.data.result;

                            vm.customerMasterDetails.C006.oldValue = vm.cbsDetail.firstName;
                            vm.customerMasterDetails.C007.oldValue = vm.cbsDetail.middleName;
                            vm.customerMasterDetails.C008.oldValue = vm.cbsDetail.lastName;
                            vm.customerMasterDetails.C010.oldValue = vm.cbsDetail.fatherName;
                            vm.customerMasterDetails.C011.oldValue = vm.cbsDetail.motherName;
                            vm.customerMasterDetails.C012.oldValue = vm.cbsDetail.gender;
                            vm.customerMasterDetails.C013.oldValue = convertToDateObject(vm.cbsDetail.dateOfBirth);
                            vm.customerMasterDetails.C014.oldValue = vm.cbsDetail.isMinor;
                            vm.customerMasterDetails.C015.oldValue = vm.cbsDetail.addressOne;
                            vm.customerMasterDetails.C016.oldValue = vm.cbsDetail.addressTwo;
                            vm.customerMasterDetails.C017.oldValue = vm.cbsDetail.addressThree;
                            vm.customerMasterDetails.C018.oldValue = vm.cbsDetail.city;
                            vm.customerMasterDetails.C020.oldValue = vm.cbsDetail.pinCode;
                            vm.customerMasterDetails.C046.oldValue = vm.cbsDetail.phoneNumber;
                            vm.customerMasterDetails.C021.oldValue = vm.cbsDetail.mobileNumber;
                            vm.customerMasterDetails.C022.oldValue = vm.cbsDetail.email;
                            vm.customerMasterDetails.C034.oldValue = vm.cbsDetail.operativeAccNo;
                            vm.customerMasterDetails.C035.oldValue = vm.cbsDetail.operativeBranchId;
                            vm.customerMasterDetails.C036.oldValue = vm.cbsDetail.pan;
                            vm.customerMasterDetails.C037.oldValue = vm.cbsDetail.aadhar;
                             vm.customerMasterDetailsThree.C023.oldValue = vm.blaAccDetailsThree.hufRegPowerOfAttorney;
                            vm.customerMasterDetailsThree.C024.oldValue = vm.blaAccDetailsThree.powerOfAttorneyKartaName;

                        }, function(error) {
                           vm.loader = false;
                           vm.errmsgSingle = 'CBS Failure';
                           handleErrorResponse(error);
                           vm.customerMasterDetails.C004.oldValue = '';
                        });
                }

        function getCbsCustFstJoinDetailsTwoModify(){
                            vm.loader = true;


                             var requestData = {
                                                                            'cifId': vm.customerMasterDetailsTwo.C004.oldValue,
                                                                            'operativeAccNo' : vm.customerMasterDetailsTwo.C034.oldValue,
                                                                            'operativeAccBranchId' : vm.customerMasterDetailsTwo.C035.oldValue
                                                                        };

                          bondLedgerAccMaintenanceService.fetchCbsCustId(requestData).then( (response) => {
                                    vm.loader = false;
                                    vm.cbsDetailTwo = response.data.result;
                            vm.customerMasterDetailsTwo.C006.oldValue = vm.cbsDetailTwo.firstName;
                            vm.customerMasterDetailsTwo.C007.oldValue = vm.cbsDetailTwo.middleName;
                            vm.customerMasterDetailsTwo.C008.oldValue = vm.cbsDetailTwo.lastName;
                            vm.customerMasterDetailsTwo.C010.oldValue = vm.cbsDetailTwo.fatherName;
                            vm.customerMasterDetailsTwo.C011.oldValue = vm.cbsDetailTwo.motherName;
                            vm.customerMasterDetailsTwo.C012.oldValue = vm.cbsDetailTwo.gender;
                            vm.customerMasterDetailsTwo.C013.oldValue = convertToDateObject(vm.cbsDetailTwo.dateOfBirth);
                            vm.customerMasterDetailsTwo.C014.oldValue = vm.cbsDetailTwo.isMinor;
                            vm.customerMasterDetailsTwo.C015.oldValue = vm.cbsDetailTwo.addressOne;
                            vm.customerMasterDetailsTwo.C016.oldValue = vm.cbsDetailTwo.addressTwo;
                            vm.customerMasterDetailsTwo.C017.oldValue = vm.cbsDetailTwo.addressThree;
                            vm.customerMasterDetailsTwo.C018.oldValue = vm.cbsDetailTwo.city;
                            vm.customerMasterDetailsTwo.C020.oldValue = vm.cbsDetailTwo.pinCode;
                            vm.customerMasterDetailsTwo.C046.oldValue = vm.cbsDetailTwo.phoneNumber;
                            vm.customerMasterDetailsTwo.C021.oldValue = vm.cbsDetailTwo.mobileNumber;
                            vm.customerMasterDetailsTwo.C022.oldValue = vm.cbsDetailTwo.email;
                            vm.customerMasterDetailsTwo.C034.oldValue = vm.cbsDetailTwo.operativeAccNo;
                            vm.customerMasterDetailsTwo.C035.oldValue = vm.cbsDetailTwo.operativeBranchId;
                            vm.customerMasterDetailsTwo.C036.oldValue = vm.cbsDetailTwo.pan;
                            vm.customerMasterDetailsTwo.C037.oldValue = vm.cbsDetailTwo.aadhar;
                             vm.customerMasterDetailsThree.C023.oldValue = vm.blaAccDetailsThree.hufRegPowerOfAttorney;
                              vm.customerMasterDetailsThree.C024.oldValue = vm.blaAccDetailsThree.powerOfAttorneyKartaName;

                                }, function(error) {
                                   vm.loader = false;
                                   vm.errmsgSingle = 'CBS Failure';
                                   handleErrorResponse(error);
                                   vm.customerMasterDetailsTwo.C004.oldValue = '';
                                });


                        }

        function getCbsCustFstJoinDetailsThreeModify(){
                    vm.loader = true;


                     var requestData = {
                                                                                                'cifId': vm.customerMasterDetailsThree.C004.oldValue,
                                                                                                'operativeAccNo' : vm.customerMasterDetailsThree.C034.oldValue,
                                                                                                'operativeAccBranchId' : vm.customerMasterDetailsThree.C035.oldValue
                                                                                            };


                  bondLedgerAccMaintenanceService.fetchCbsCustId(requestData).then( (response) => {
                            vm.loader = false;
                            vm.cbsDetailThree = response.data.result;

                            vm.customerMasterDetailsThree.C006.oldValue = vm.cbsDetailThree.firstName;
                            vm.customerMasterDetailsThree.C007.oldValue = vm.cbsDetailThree.middleName;
                            vm.customerMasterDetailsThree.C008.oldValue = vm.cbsDetailThree.lastName;
                            vm.customerMasterDetailsThree.C010.oldValue = vm.cbsDetailThree.fatherName;
                            vm.customerMasterDetailsThree.C011.oldValue = vm.cbsDetailThree.motherName;
                            vm.customerMasterDetailsThree.C012.oldValue = vm.cbsDetailThree.gender;
                            vm.customerMasterDetailsThree.C013.oldValue = convertToDateObject(vm.cbsDetailThree.dateOfBirth);
                            vm.customerMasterDetailsThree.C014.oldValue = vm.cbsDetailThree.isMinor;
                            vm.customerMasterDetailsThree.C015.oldValue = vm.cbsDetailThree.addressOne;
                            vm.customerMasterDetailsThree.C016.oldValue = vm.cbsDetailThree.addressTwo;
                            vm.customerMasterDetailsThree.C017.oldValue = vm.cbsDetailThree.addressThree;
                            vm.customerMasterDetailsThree.C018.oldValue = vm.cbsDetailThree.city;
                            vm.customerMasterDetailsThree.C020.oldValue = vm.cbsDetailThree.pinCode;
                            vm.customerMasterDetailsThree.C046.oldValue = vm.cbsDetailThree.phoneNumber;
                            vm.customerMasterDetailsThree.C021.oldValue = vm.cbsDetailThree.mobileNumber;
                            vm.customerMasterDetailsThree.C022.oldValue = vm.cbsDetailThree.email;
                            vm.customerMasterDetailsThree.C034.oldValue = vm.cbsDetailThree.operativeAccNo;
                            vm.customerMasterDetailsThree.C035.oldValue = vm.cbsDetailThree.operativeBranchId;
                            vm.customerMasterDetailsThree.C036.oldValue = vm.cbsDetailThree.pan;
                            vm.customerMasterDetailsThree.C037.oldValue = vm.cbsDetailThree.aadhar;
                             vm.customerMasterDetailsThree.C023.oldValue = vm.blaAccDetailsThree.hufRegPowerOfAttorney;
                             vm.customerMasterDetailsThree.C024.oldValue = vm.blaAccDetailsThree.powerOfAttorneyKartaName;

                        }, function(error) {
                           vm.loader = false;
                           vm.errmsgSingle = 'CBS Failure';
                           handleErrorResponse(error);
                           vm.customerMasterDetailsThree.C004.oldValue = '';
                        });


                }



          function checkAge(item){

                    if(typeof(vm.customerMasterDetails.C013.oldValue) != 'undefined'){
                     var dob = new Date(vm.customerMasterDetails.C013.oldValue);
                     var month_diff = Date.now() - dob.getTime();
                     var age_dt = new Date(month_diff);
                     var year = age_dt.getUTCFullYear();

                     var age = Math.abs(year - 1970);

                     if(age >= 18){
                     Swal.fire("Not a Minor!");
                     vm.customerMasterDetails.C013.oldValue = "";
                     }
                   }

                   }

        function getBlaAccNoDetailsModify(flag){
                vm.loader = true;

                if(flag=='torch'){


                            var requestData = {
                                'schemeCode': vm.schemeCode,
                                'modeOfHolding': vm.modeOfHolding,
                            };
                              bondLedgerAccMaintenanceService.fetchBlaAccNoDetails(requestData).then( (response) => {
                                        vm.loader = false;
                                        vm.blaNoList = response.data.result;
                                        if(vm.modeOfHolding == 'MH002' || vm.modeOfHolding == 'MH003'){
                                                angular.element('#blaJointModal').modal('toggle');
                                        }else{
                                               angular.element('#blaModal').modal('toggle');
                                        }
                                    }, function(error) {
                                       vm.loader = false;
                                       handleErrorResponse(error);
                                    });
                }
                else{
                          var requestData = {
                                'schemeCode': vm.schemeCode,
                                'modeOfHolding': vm.modeOfHolding,
                                'blaNum' : vm.customerMasterDetails.C005.oldValue,
                                'jointHolderNo': '1',
                                };
                              bondLedgerAccMaintenanceService.fetchBlaAccNoDetails(requestData).then( (response) => {
                                        vm.loader = false;
                                        vm.blaAccDetails = response.data.result[0];
                                        vm.customerMasterDetails.C005.oldValue = vm.blaAccDetails.blaNo;
                                       vm.customerMasterDetails.C006.oldValue = vm.blaAccDetails.bondHolderFirstName;
                                       vm.customerMasterDetails.C007.oldValue = vm.blaAccDetails.bondHolderMiddleName;
                                       vm.customerMasterDetails.C008.oldValue = vm.blaAccDetails.bondHolderLastName;
                                       vm.customerMasterDetails.C010.oldValue = vm.blaAccDetails.fatherName;
                                       vm.customerMasterDetails.C011.oldValue = vm.blaAccDetails.motherName;
                                       vm.customerMasterDetails.C012.oldValue = vm.blaAccDetails.gender;
                                       vm.customerMasterDetails.C013.oldValue = convertToDateObject(vm.blaAccDetails.dateOfBirth);
                                       vm.customerMasterDetails.C014.oldValue = vm.blaAccDetails.isMinor;
                                       vm.customerMasterDetails.C015.oldValue = vm.blaAccDetails.addressOne;
                                       vm.customerMasterDetails.C016.oldValue = vm.blaAccDetails.addressTwo;
                                       vm.customerMasterDetails.C017.oldValue = vm.blaAccDetails.addressThree;
                                       vm.customerMasterDetails.C018.oldValue = vm.blaAccDetails.city;
                                       vm.customerMasterDetails.C020.oldValue = vm.blaAccDetails.pinCode;
                                       vm.customerMasterDetails.C046.oldValue = vm.blaAccDetails.phoneNumber;
                                       vm.customerMasterDetails.C021.oldValue = vm.blaAccDetails.mobileNumber;
                                       vm.customerMasterDetails.C022.oldValue = vm.blaAccDetails.email;
                                       vm.customerMasterDetails.C034.oldValue = vm.blaAccDetails.operativeAccNo;
                                       vm.customerMasterDetails.C035.oldValue = vm.blaAccDetails.operativeBranchId;
                                       vm.customerMasterDetails.C036.oldValue = vm.blaAccDetails.pan;
                                        vm.customerMasterDetailsThree.C023.oldValue = vm.blaAccDetailsThree.hufRegPowerOfAttorney;
                                         vm.customerMasterDetailsThree.C024.oldValue = vm.blaAccDetailsThree.powerOfAttorneyKartaName;

                                    }, function(error) {
                                       vm.loader = false;
                                       vm.customerMasterDetails.C005.oldValue='';
                                       handleErrorResponse(error);
                                    });
                }

                }

        function getBlaAccNoDetailsTwoModify(flag){
                         vm.loader = true;

                         if(flag=='torch'){


                                     var requestData = {
                                         'schemeCode': vm.schemeCode,
                                         'modeOfHolding': vm.modeOfHolding,
                                     };
                                       bondLedgerAccMaintenanceService.fetchBlaAccNoDetails(requestData).then( (response) => {
                                                 vm.loader = false;
                                                 vm.blaNoListTwo = response.data.result;
                                                 if(vm.modeOfHolding == 'MH002' || vm.modeOfHolding == 'MH003'){
                                                         angular.element('#blaJointModalTwo').modal('toggle');
                                                 }else{
                                                        angular.element('#blaModalTwo').modal('toggle');
                                                 }
                                             }, function(error) {
                                                vm.loader = false;
                                                handleErrorResponse(error);
                                             });
                         }
                         else{
                                   var requestData = {
                                         'schemeCode': vm.schemeCode,
                                         'modeOfHolding': vm.modeOfHolding,
                                         'blaNum' : vm.customerMasterDetailsTwo.C005.oldValue,
                                         'jointHolderNo': '2',
                                         };
                                       bondLedgerAccMaintenanceService.fetchBlaAccNoDetails(requestData).then( (response) => {
                                                 vm.loader = false;
                                                 vm.blaAccDetailsTwo = response.data.result[0];
                                        vm.customerMasterDetailsTwo.C005.oldValue = vm.blaAccDetailsTwo.blaNo;
                                       vm.customerMasterDetailsTwo.C006.oldValue = vm.blaAccDetailsTwo.bondHolderFirstName;
                                       vm.customerMasterDetailsTwo.C007.oldValue = vm.blaAccDetailsTwo.bondHolderMiddleName;
                                       vm.customerMasterDetailsTwo.C008.oldValue = vm.blaAccDetailsTwo.bondHolderLastName;
                                       vm.customerMasterDetailsTwo.C010.oldValue = vm.blaAccDetailsTwo.fatherName;
                                       vm.customerMasterDetailsTwo.C011.oldValue = vm.blaAccDetailsTwo.motherName;
                                       vm.customerMasterDetailsTwo.C012.oldValue = vm.blaAccDetailsTwo.gender;
                                       vm.customerMasterDetailsTwo.C013.oldValue = convertToDateObject(vm.blaAccDetailsTwo.dateOfBirth);
                                       vm.customerMasterDetailsTwo.C014.oldValue = vm.blaAccDetailsTwo.isMinor;
                                       vm.customerMasterDetailsTwo.C015.oldValue = vm.blaAccDetailsTwo.addressOne;
                                       vm.customerMasterDetailsTwo.C016.oldValue = vm.blaAccDetailsTwo.addressTwo;
                                       vm.customerMasterDetailsTwo.C017.oldValue = vm.blaAccDetailsTwo.addressThree;
                                       vm.customerMasterDetailsTwo.C018.oldValue = vm.blaAccDetailsTwo.city;
                                       vm.customerMasterDetailsTwo.C020.oldValue = vm.blaAccDetailsTwo.pinCode;
                                       vm.customerMasterDetailsTwo.C046.oldValue = vm.blaAccDetailsTwo.phoneNumber;
                                       vm.customerMasterDetailsTwo.C021.oldValue = vm.blaAccDetailsTwo.mobileNumber;
                                       vm.customerMasterDetailsTwo.C022.oldValue = vm.blaAccDetailsTwo.email;
                                       vm.customerMasterDetailsTwo.C034.oldValue = vm.blaAccDetailsTwo.operativeAccNo;
                                       vm.customerMasterDetailsTwo.C035.oldValue = vm.blaAccDetailsTwo.operativeBranchId;
                                       vm.customerMasterDetailsTwo.C036.oldValue = vm.blaAccDetailsTwo.pan;
                                        vm.customerMasterDetailsThree.C023.oldValue = vm.blaAccDetailsThree.hufRegPowerOfAttorney;
                                     vm.customerMasterDetailsThree.C024.oldValue = vm.blaAccDetailsThree.powerOfAttorneyKartaName;

                                             }, function(error) {
                                                vm.loader = false;
                                                vm.customerMasterDetailsTwo.C005.oldValue='';
                                                handleErrorResponse(error);
                                             });
                         }


                         }


        function getBlaAccNoDetailsThreeModify(flag){
                 vm.loader = true;

                 if(flag=='torch'){


                             var requestData = {
                                 'schemeCode': vm.schemeCode,
                                 'modeOfHolding': vm.modeOfHolding,
                             };
                               bondLedgerAccMaintenanceService.fetchBlaAccNoDetails(requestData).then( (response) => {
                                         vm.loader = false;
                                         vm.blaNoListThree = response.data.result;
                                         if(vm.modeOfHolding == 'MH002' || vm.modeOfHolding == 'MH003'){
                                                 angular.element('#blaJointModalThree').modal('toggle');
                                         }else{
                                                angular.element('#blaModal').modal('toggle');
                                         }
                                     }, function(error) {
                                        vm.loader = false;
                                        handleErrorResponse(error);
                                     });
                 }
                 else{
                           var requestData = {
                                 'schemeCode': vm.schemeCode,
                                 'modeOfHolding': vm.modeOfHolding,
                                 'blaNum' : vm.customerMasterDetailsThree.C005.oldValue,
                                 'jointHolderNo': '3',
                                 };
                               bondLedgerAccMaintenanceService.fetchBlaAccNoDetails(requestData).then( (response) => {
                                         vm.loader = false;
                                         vm.blaAccDetailsThree = response.data.result[0];
                          vm.customerMasterDetailsThree.C005.oldValue = vm.blaAccDetailsTwo.blaNo;
                                       vm.customerMasterDetailsThree.C006.oldValue = vm.blaAccDetailsThree.bondHolderFirstName;
                                       vm.customerMasterDetailsThree.C007.oldValue = vm.blaAccDetailsThree.bondHolderMiddleName;
                                       vm.customerMasterDetailsThree.C008.oldValue = vm.blaAccDetailsThree.bondHolderLastName;
                                       vm.customerMasterDetailsThree.C010.oldValue = vm.blaAccDetailsThree.fatherName;
                                       vm.customerMasterDetailsThree.C011.oldValue = vm.blaAccDetailsThree.motherName;
                                       vm.customerMasterDetailsThree.C012.oldValue = vm.blaAccDetailsThree.gender;
                                       vm.customerMasterDetailsThree.C013.oldValue = convertToDateObject(vm.blaAccDetailsThree.dateOfBirth);
                                       vm.customerMasterDetailsThree.C014.oldValue = vm.blaAccDetailsThree.isMinor;
                                       vm.customerMasterDetailsThree.C015.oldValue = vm.blaAccDetailsThree.addressOne;
                                       vm.customerMasterDetailsThree.C016.oldValue = vm.blaAccDetailsThree.addressTwo;
                                       vm.customerMasterDetailsThree.C017.oldValue = vm.blaAccDetailsThree.addressThree;
                                       vm.customerMasterDetailsThree.C018.oldValue = vm.blaAccDetailsThree.city;
                                       vm.customerMasterDetailsThree.C020.oldValue = vm.blaAccDetailsThree.pinCode;
                                       vm.customerMasterDetailsThree.C046.oldValue = vm.blaAccDetailsThree.phoneNumber;
                                       vm.customerMasterDetailsThree.C021.oldValue = vm.blaAccDetailsThree.mobileNumber;
                                       vm.customerMasterDetailsThree.C022.oldValue = vm.blaAccDetailsThree.email;
                                       vm.customerMasterDetailsThree.C034.oldValue = vm.blaAccDetailsThree.operativeAccNo;
                                       vm.customerMasterDetailsThree.C035.oldValue = vm.blaAccDetailsThree.operativeBranchId;
                                       vm.customerMasterDetailsThree.C036.oldValue = vm.blaAccDetailsThree.pan;
                                       vm.customerMasterDetailsThree.C023.oldValue = vm.blaAccDetailsThree.hufRegPowerOfAttorney;
                                        vm.customerMasterDetailsThree.C024.oldValue = vm.blaAccDetailsThree.powerOfAttorneyKartaName;



                                     }, function(error) {
                                        vm.loader = false;
                                        vm.customerMasterDetailsThree.C005.oldValue='';

                                        handleErrorResponse(error);
                                     });
                 }


                 }

        function getGuardianDetailsModify(flag){
                        vm.loader = true;

                        if(flag == 'select'){
                            var requestData = {
                                'schemeCode' : vm.schemeCode,
                            };
                          bondLedgerAccMaintenanceService.fetchGuardianDetails(requestData).then( (response) => {
                                    vm.loader = false;
                                    vm.guardianDetailsList = response.data.result;
                                    angular.element('#guardianModal').modal('toggle');
                                }, function(error) {
                                   vm.loader = false;
                                   handleErrorResponse(error);
                                });
                        }else{
                            var requestData = {
                                'schemeCode' : vm.schemeCode,
                                'guardianId' : vm.customerMasterDetails.C025.oldValue,
                            };
                          bondLedgerAccMaintenanceService.fetchGuardianDetails(requestData).then( (response) => {
                                    vm.loader = false;
                                    vm.guardianDetail = response.data.result[0];

                                    vm.customerMasterDetails.C026.oldValue = vm.guardianDetail.guardianFirstName;
                                    vm.customerMasterDetails.C027.oldValue = vm.guardianDetail.guardianMiddleName;
                                    vm.customerMasterDetails.C028.oldValue = vm.guardianDetail.guardianLastName;
                                    vm.customerMasterDetails.C030.oldValue = convertToDateObject(vm.guardianDetail.dateOfBirth);
                                    vm.customerMasterDetails.C031.oldValue = vm.guardianDetail.address;
                                    vm.customerMasterDetails.C032.oldValue = vm.guardianDetail.mobileNo;
                                    vm.customerMasterDetails.C033.oldValue = vm.guardianDetail.email;
                                }, function(error) {
                                   vm.loader = false;
                                   handleErrorResponse(error);
                                });
                        }




                        }

        function getGuardianDetailsTwoModify(flag){
                                vm.loader = true;

                                if(flag == 'select'){
                                    var requestData = {
                                        'schemeCode' : vm.schemeCode,
                                    };
                                  bondLedgerAccMaintenanceService.fetchGuardianDetails(requestData).then( (response) => {
                                            vm.loader = false;
                                            vm.guardianDetailsListTwo = response.data.result;
                                            angular.element('#guardianModalTwo').modal('toggle');
                                        }, function(error) {
                                           vm.loader = false;
                                           handleErrorResponse(error);
                                        });
                                }else{
                                    var requestData = {
                                        'schemeCode' : vm.schemeCode,
                                        'guardianId' : vm.customerMasterDetailsTwo.C025.oldValue,
                                    };
                                  bondLedgerAccMaintenanceService.fetchGuardianDetails(requestData).then( (response) => {
                                            vm.loader = false;
                                            vm.guardianDetailTwo = response.data.result[0];


                                    vm.customerMasterDetailsTwo.C026.oldValue = vm.guardianDetailTwo.guardianFirstName;
                                    vm.customerMasterDetailsTwo.C027.oldValue = vm.guardianDetailTwo.guardianMiddleName;
                                    vm.customerMasterDetailsTwo.C028.oldValue = vm.guardianDetailTwo.guardianLastName;
                                    vm.customerMasterDetailsTwo.C030.oldValue = convertToDateObject(vm.guardianDetailTwo.dateOfBirth);
                                    vm.customerMasterDetailsTwo.C031.oldValue = vm.guardianDetailTwo.address;
                                    vm.customerMasterDetailsTwo.C032.oldValue = vm.guardianDetailTwo.mobileNo;
                                    vm.customerMasterDetailsTwo.C033.oldValue = vm.guardianDetailTwo.email;
                                        }, function(error) {
                                           vm.loader = false;
                                           handleErrorResponse(error);
                                        });
                                }




                                }

        function getGuardianDetailsThreeModify(flag){
                        vm.loader = true;

                        if(flag == 'select'){
                            var requestData = {
                                'schemeCode' : vm.schemeCode,
                            };
                          bondLedgerAccMaintenanceService.fetchGuardianDetails(requestData).then( (response) => {
                                    vm.loader = false;
                                    vm.guardianDetailsListThree = response.data.result;
                                    angular.element('#guardianModalThree').modal('toggle');
                                }, function(error) {
                                   vm.loader = false;
                                   handleErrorResponse(error);
                                });
                        }else{
                            var requestData = {
                                'schemeCode' : vm.schemeCode,
                                'guardianId' : vm.customerMasterDetailsThree.C025.oldValue,
                            };
                          bondLedgerAccMaintenanceService.fetchGuardianDetails(requestData).then( (response) => {
                                    vm.loader = false;
                                    vm.guardianDetailThree = response.data.result[0];
                                    vm.customerMasterDetailsThree.C026.oldValue = vm.guardianDetailThree.guardianFirstName;
                                    vm.customerMasterDetailsThree.C027.oldValue = vm.guardianDetailThree.guardianMiddleName;
                                    vm.customerMasterDetailsThree.C028.oldValue = vm.guardianDetailThree.guardianLastName;
                                    vm.customerMasterDetailsThree.C030.oldValue = convertToDateObject(vm.guardianDetailThree.dateOfBirth);
                                    vm.customerMasterDetailsThree.C031.oldValue = vm.guardianDetailThree.address;
                                    vm.customerMasterDetailsThree.C032.oldValue = vm.guardianDetailThree.mobileNo;
                                    vm.customerMasterDetailsThree.C033.oldValue = vm.guardianDetailThree.email;
                                }, function(error) {
                                   vm.loader = false;
                                   handleErrorResponse(error);
                                });
                        }




                        }

                function checkAge(isMinor, dateOfBirth,mode,list){

                        if(typeof(dateOfBirth) != 'undefined'){
                         var dob = new Date(dateOfBirth);
                         var month_diff = Date.now() - dob.getTime();
                         var age_dt = new Date(month_diff);
                         var year = age_dt.getUTCFullYear();

                         var age = Math.abs(year - 1970);

                         if(age >= 18 && isMinor == 'Y'){
                         Swal.fire("Not a Minor!", "Please enter the correct DOB", "info");
                         if(mode=='add'){

                         if(list=='1'){vm.customerMasterDetails.bondHldDob = "";}
                         if(list=='2'){vm.customerMasterDetailsTwo.bondHldDob = "";}
                         if(list=='3'){vm.customerMasterDetailsThree.bondHldDob="";}
                         }
                        if (mode=='modify'){
                        if(list=='1'){vm.customerMasterDetails.C013.oldValue="";}
                        if(list=='2'){vm.customerMasterDetailsTwo.C013.oldValue="";}
                        if(list=='3'){vm.customerMasterDetailsThree.C013.oldValue="";}

                        }

                         }
                         else if(age < 18 && isMinor == 'N'){
                         Swal.fire("Not a Major!", "Please enter the correct DOB", "info");
                         if(mode=='add'){
                         if(list=='1'){vm.customerMasterDetails.bondHldDob = "";}
                         if(list=='2'){vm.customerMasterDetailsTwo.bondHldDob = "";}
                         if(list=='3'){vm.customerMasterDetailsThree.bondHldDob="";}
                         }
                        if (mode=='modify'){
                        if(list=='1'){vm.customerMasterDetails.C013.oldValue="";}
                        if(list=='2'){vm.customerMasterDetailsTwo.C013.oldValue="";}
                        if(list=='3'){vm.customerMasterDetailsThree.C013.oldValue="";}

                        }
                         }
                       }

                       }

        }
        })();