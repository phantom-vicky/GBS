(function() {

    'use strict';

    angular.module('gbmUltraApp').controller('cashInFileUploadAndNetBankingFileUploadController',
        cashInFileUploadAndNetBankingFileUploadController);

    cashInFileUploadAndNetBankingFileUploadController.$inject = ['$scope', '$filter', '$state', '$rootScope',
        '$stateParams', '$location', 'cashInFileUploadAndNetBankingFileUploadService','commonService'
    ];

    function cashInFileUploadAndNetBankingFileUploadController($scope, $filter, $state, $rootScope, $stateParams,
        $location, cashInFileUploadAndNetBankingFileUploadService, commonService) {

        var vm = this;
        vm.commonYesNoMetaData = COMMON_YES_NO_METADATA;
        vm.currentDate = new Date();
        vm.uploadSubmit=uploadSubmit;
        vm.getRemarksList = getRemarksList;
        vm.submitVerifyForm=submitVerifyForm;
        vm.deleteSubmit=deleteSubmit;
        vm.fromToDateValidation = fromToDateValidation;
        vm.verifySelect = verifySelect;
        vm.download = download;
        //Add Functions
        vm.submitAddForm = submitAddForm;
        vm.getModuleName = getModuleName;
        vm.popupScreen=popupScreen;
        vm.inquiry=inquiry;
        vm.optionToggled  = optionToggled;
        vm.rejectReason=rejectReason;
        vm.validateInput=validateInput;
        vm.valueDateValidation=valueDateValidation;
        vm.selectedCheckBox=[];
        vm.selectedCheckBox[0]=false;
        vm.selectedCheckBox[1]=false;
        vm.selectedCheckBox[2]=false;
        vm.selectedCheckBox[3]=false;
        vm.selectedCheckBox[4]=false;
        vm.selectedCheckBox[5]=false;
        vm.selectedCheckBox[6]=false;
        vm.selectedCheckBox[7]=false;
        vm.selectedCheckBox[8]=false;

           function getRemarksList() {
                            vm.referenceId = '056';
                            var requestData = { 'referenceId': vm.referenceId };

                            cashInFileUploadAndNetBankingFileUploadService.getRemarksList(requestData).then(function(response) {
                                vm.remarksList = response.data.result || []; // Ensure it's an array
                            }, function(error) {
                                handleErrorResponse(error);
                            });
                        }

         function optionToggled(flag) {
                                       vm.isAllSelected=[];
                                       if (flag == 'ALL') {
                                       if(vm.selectedAllItem == true)
                                       {
                                       vm.selectedCheckBox['RRBNB']=true;
                                       vm.selectedCheckBox['TBNB']=true;
                                       vm.selectedCheckBox['RRBNB_BB']=true;
                                       vm.selectedCheckBox['TBNB_BB']=true;
                                       vm.selectedCheckBox['RRBNB_SAKSHAM']=true;
                                       vm.selectedCheckBox['TBNB_SAKSHAM']=true;
                                       vm.selectedCheckBox['RRBNB_NOVOPAY']=true;
                                       vm.selectedCheckBox['TBNB_NOVOPAY']=true;
                                       vm.selectedCheckBox['TAXBOND']=true;
                                       }else  {
                                       vm.selectedCheckBox['RRBNB']=false;
                                       vm.selectedCheckBox['TBNB']=false;
                                       vm.selectedCheckBox['RRBNB_BB']=false;
                                       vm.selectedCheckBox['TBNB_BB']=false;
                                       vm.selectedCheckBox['RRBNB_SAKSHAM']=false;
                                       vm.selectedCheckBox['TBNB_SAKSHAM']=false;
                                       vm.selectedCheckBox['RRBNB_NOVOPAY']=false;
                                       vm.selectedCheckBox['TBNB_NOVOPAY']=false;
                                       vm.selectedCheckBox['TAXBOND']=false;
                                       }
                                       }
                                       else{
                                       if(flag == 'RRBNB' )
                                       {
                                       if(vm.selectedCheckBox['RRBNB']==true)
                                       {
                                       vm.selectedCheckBox['RRBNB']=true;
                                       vm.selectedCheckBox['TBNB']=true;
                                       } else  {
                                         vm.selectedCheckBox['RRBNB']=false;
                                         vm.selectedCheckBox['TBNB']=false;
                                       }
                                       }else if(flag == 'TBNB' )
                                       {
                                       if(vm.selectedCheckBox['TBNB']==true)
                                       {
                                         vm.selectedCheckBox['RRBNB']=true;
                                         vm.selectedCheckBox['TBNB']=true;
                                       } else  {
                                         vm.selectedCheckBox['RRBNB']=false;
                                         vm.selectedCheckBox['TBNB']=false;
                                       }
                                       }else if(flag == 'RRBNB_BB' )
                                       {
                                       if(vm.selectedCheckBox['RRBNB_BB']== true)
                                       {
                                      vm.selectedCheckBox['RRBNB_BB']=true;
                                      vm.selectedCheckBox['TBNB_BB']=true;
                                       } else  {
                                       vm.selectedCheckBox['RRBNB_BB']=false;
                                       vm.selectedCheckBox['TBNB_BB']=false;
                                       }
                                       }else if(flag == 'TBNB_BB' )
                                       {
                                       if(vm.selectedCheckBox['TBNB_BB'] == true)
                                       {
                                        vm.selectedCheckBox['RRBNB_BB']=true;
                                        vm.selectedCheckBox['TBNB_BB']=true;
                                       } else  {
                                        vm.selectedCheckBox['RRBNB_BB']=false;
                                        vm.selectedCheckBox['TBNB_BB']=false;
                                       }
                                       }else if(flag == 'RRBNB_SAKSHAM' )
                                       {
                                       if(vm.selectedCheckBox['RRBNB_SAKSHAM']== true)
                                       {
                                      vm.selectedCheckBox['RRBNB_SAKSHAM']=true;
                                      vm.selectedCheckBox['TBNB_SAKSHAM']=true;
                                       } else  {
                                       vm.selectedCheckBox['RRBNB_SAKSHAM']=false;
                                       vm.selectedCheckBox['TBNB_SAKSHAM']=false;
                                       }
                                       }else if(flag == 'TBNB_SAKSHAM' )
                                       {
                                       if(vm.selectedCheckBox['TBNB_SAKSHAM'] == true)
                                       {
                                        vm.selectedCheckBox['RRBNB_SAKSHAM']=true;
                                        vm.selectedCheckBox['TBNB_SAKSHAM']=true;
                                       } else  {
                                        vm.selectedCheckBox['RRBNB_SAKSHAM']=false;
                                        vm.selectedCheckBox['TBNB_SAKSHAM']=false;
                                       }
                                       }else if(flag == 'RRBNB_NOVOPAY' )
                                       {
                                       if(vm.selectedCheckBox['RRBNB_NOVOPAY']== true)
                                       {
                                      vm.selectedCheckBox['RRBNB_NOVOPAY']=true;
                                      vm.selectedCheckBox['TBNB_NOVOPAY']=true;
                                       } else  {
                                       vm.selectedCheckBox['RRBNB_NOVOPAY']=false;
                                       vm.selectedCheckBox['TBNB_NOVOPAY']=false;
                                       }
                                       }else if(flag == 'TBNB_NOVOPAY' )
                                       {
                                       if(vm.selectedCheckBox['TBNB_NOVOPAY'] == true)
                                       {
                                        vm.selectedCheckBox['RRBNB_NOVOPAY']=true;
                                        vm.selectedCheckBox['TBNB_NOVOPAY']=true;
                                       } else  {
                                        vm.selectedCheckBox['RRBNB_NOVOPAY']=false;
                                        vm.selectedCheckBox['TBNB_NOVOPAY']=false;
                                       }
                                       }
                                       else if(flag == 'TAXBOND' ){
                                       if(vm.selectedCheckBox['TAXBOND'] == true)
                                       {
                                       vm.selectedCheckBox['TAXBOND']=true;
                                       }else  {
                                      vm.selectedCheckBox['TAXBOND']=false;
                                       }
                                     }
                                   }
                                   }
        function download(flag){
        if(vm.responseResult[flag].fileName=='RRBNB' || vm.responseResult[flag].fileName=='TBNB'){
            const liquidationDateDDMMYY = formatDateToDDMMYY(vm.liquidationDate); // Example date
            saveAs(getBlobFromBase64String(vm.responseResult[flag].file),vm.responseResult[flag].fileName+liquidationDateDDMMYY+'.csv');
            Swal.fire("Success!", vm.responseResult[flag].fileName+liquidationDateDDMMYY+".csv"+"\n Downloaded Successfully", "success");
            }else if(vm.responseResult[flag].fileName=='RRBNB_BB' || vm.responseResult[flag].fileName=='TBNB_BB'){
            const liquidationDateDDMMYYYY = formatDateToCustomString(vm.liquidationDate); // Example date
            saveAs(getBlobFromBase64String(vm.responseResult[flag].file),vm.responseResult[flag].fileName.slice(0, -2)+liquidationDateDDMMYYYY+'_BB.csv');
            Swal.fire("Success!", vm.responseResult[flag].fileName.slice(0, -2)+liquidationDateDDMMYYYY+"_BB.csv"+"\n Downloaded Successfully", "success");
            }else if(vm.responseResult[flag].fileName=='RRBNB_SAKSHAM' || vm.responseResult[flag].fileName=='TBNB_SAKSHAM'){
             const liquidationDateDDMMYYYY = formatDateToCustomString(vm.liquidationDate); // Example date
             saveAs(getBlobFromBase64String(vm.responseResult[flag].file),vm.responseResult[flag].fileName.slice(0, -7)+liquidationDateDDMMYYYY+'_Saksham.csv');
             Swal.fire("Success!", vm.responseResult[flag].fileName.slice(0, -7)+liquidationDateDDMMYYYY+"_Saksham.csv"+"\n Downloaded Successfully", "success");
         }else if(vm.responseResult[flag].fileName=='RRBNB_NOVOPAY' || vm.responseResult[flag].fileName=='TBNB_NOVOPAY'){
           const liquidationDateDDMMYYYY = formatDateToCustomString(vm.liquidationDate); // Example date
           saveAs(getBlobFromBase64String(vm.responseResult[flag].file),vm.responseResult[flag].fileName.slice(0, -7)+liquidationDateDDMMYYYY+'_NOVOPAY.csv');
           Swal.fire("Success!", vm.responseResult[flag].fileName.slice(0, -7)+liquidationDateDDMMYYYY+"_NOVOPAY.csv"+"\n Downloaded Successfully", "success");
           }else if(vm.responseResult[flag].fileName=='TAXBOND'){
            const liquidationDateDDMMYYYY = formatDateToCustomString(vm.liquidationDate); // Example date
            saveAs(getBlobFromBase64String(vm.responseResult[flag].file),vm.responseResult[flag].fileName+liquidationDateDDMMYYYY+'.txt');
            Swal.fire("Success!", vm.responseResult[flag].fileName+liquidationDateDDMMYYYY+".txt"+"\n Downloaded Successfully", "success");
            }
        }
        //Get Module Name
        function getModuleName() {
                vm.referenceId = 'RBI Bond';
                var requestData = {
                    'referenceId': vm.referenceId
                }
                cashInFileUploadAndNetBankingFileUploadService.getRbiBondModuleNameDetails(requestData).then(function(response) {
                    vm.schemeNameList = response.data.result;
                    angular.element('#levelNameModal').modal('toggle');
                }, function(error) {
                    handleErrorResponse(error);
                });
            }
function formatDateToDDMMYY(date) {
    // Ensure the input is a Date object
    if (!(date instanceof Date)) {
        date = new Date(date);
    }
    let year = date.getFullYear().toString().slice(-2); // Get last 2 digits of the year
    let month = (date.getMonth() + 1).toString().padStart(2, '0'); // Months are zero-based
    let day = date.getDate().toString().padStart(2, '0'); // Get the day and pad with leading zero if necessary
    return day + month + year;
}
function formatDateToCustomString(date) {
if (!(date instanceof Date)) {
        date = new Date(date);
    }
    // Get day, month, and year components
    const day = ('0' + date.getDate()).slice(-2); // Adding leading zero if needed
    const month = ('0' + (date.getMonth() + 1)).slice(-2); // Adding leading zero if needed (months are zero-indexed)
    const year = date.getFullYear();

    // Concatenate to get the desired format
    return day + month + year;
}
       // JavaScript functions for file upload modal
       function popupScreen() {

           // Ensure vm.fileMode is set correctly before displaying modal
           if (vm.fileMode) {
               document.getElementById('fileUploadModal').style.display = "block";
           }
           // Close modal function
           vm.closeModal = function() {
               document.getElementById('fileUploadModal').style.display = "none";
                       $('#fileInput1').val('');
                       $('#fileInput2').val('');
                       $('#fileInput3').val('');
                       $('#fileInput4').val('');
                       $('#fileInput5').val('');
                       $('#fileInput6').val('');
                       $('#fileInput7').val('');
                       $('#fileInput8').val('');
                       $('#fileInput9').val('');
                       vm.files1='';
                       vm.files2='';
                       vm.files3='';
                       vm.files4='';
                       vm.files5='';
                       vm.files6='';
                       vm.files7='';
                       vm.files8='';
                       vm.files9='';
           };
           vm.invalidFile1 = false;
           vm.invalidFile2 = false;
           vm.invalidFile3 = false;
           vm.invalidFile4 = false;
           vm.invalidFile5 = false;
           vm.invalidFile6 = false;
           vm.invalidFile7 = false;
           vm.invalidFile8 = false;
           vm.invalidFile9 = false;
           vm.invalidNameFile1 = false;
           vm.invalidNameFile2 = false;
           vm.invalidNameFile3 = false;
           vm.invalidNameFile4 = false;
           vm.invalidNameFile5 = false;
           vm.invalidNameFile6 = false;
           vm.invalidNameFile7 = false;
           vm.invalidNameFile8 = false;
           vm.invalidNameFile9 = false;
           vm.errorMessage = "Please upload a .csv file.";
           vm.errorMessageName = "missmatch in file name";
           vm.errorMessage1 = "Please upload a .txt file.";
           // Handle file selection
           vm.handleFiles1 = function(input) {
                  const liquidationDateDDMMYYYY = formatDateToCustomString(vm.liquidationDate); // Example date
              for (var i = 0; i < input.files.length; i++) {
                    if (input && input.files[i].type !== "text/csv") {
                       vm.invalidFile1 = true;
                       vm.invalidNameFile1 = false;
                       $('#fileInput1').val('');
                       }else if(input && input.files[i].name !== "RRBNB_"+liquidationDateDDMMYYYY+"_BB.csv"){
                       vm.invalidNameFile1 = true;
                       vm.invalidFile1 = false;
                       $('#fileInput1').val('');
                       }
                       else {
                        vm.invalidFile1 = false;
                        vm.invalidNameFile1 = false;
                        vm.commonError=false;
                       }
                 }
               $scope.$apply(); // Ensure scope is updated in AngularJS
           };
           vm.handleFiles2 = function(input) {
                  const liquidationDateDDMMYYYY = formatDateToCustomString(vm.liquidationDate); // Example date
                         for (var i = 0; i < input.files.length; i++) {
                               if (input && input.files[i].type !== "text/csv") {
                                  vm.invalidFile2 = true;
                                  vm.invalidNameFile2 = false;
                                  $('#fileInput2').val('');
                                  }else if(input && input.files[i].name !== "TBNB_"+liquidationDateDDMMYYYY+"_BB.csv"){
                                  vm.invalidNameFile2 = true;
                                  vm.invalidFile2 = false;
                                  $('#fileInput2').val('');
                                  }
                                  else {
                                   vm.invalidFile2 = false;
                                   vm.invalidNameFile2 = false;
                                  vm.commonError=false;
                                  }
                            }
                          $scope.$apply(); // Ensure scope is updated in AngularJS
                      };
                      vm.handleFiles3 = function(input) {
                             const liquidationDateDDMMYY = formatDateToDDMMYY(vm.liquidationDate); // Example date
                                    for (var i = 0; i < input.files.length; i++) {
                                          if (input && input.files[i].type !== "text/csv") {
                                             vm.invalidFile3 = true;
                                             vm.invalidNameFile3 = false;
                                             $('#fileInput3').val('');
                                             }else if(input && input.files[i].name !== "RRBNB"+liquidationDateDDMMYY+".csv"){
                                             vm.invalidNameFile3 = true;
                                             vm.invalidFile3 = false;
                                             $('#fileInput3').val('');
                                             }
                                             else {
                                              vm.invalidFile3 = false;
                                              vm.invalidNameFile3 = false;
                                              vm.commonError=false;
                                             }
                                       }
                                     $scope.$apply(); // Ensure scope is updated in AngularJS
                                 };
                                 vm.handleFiles4 = function(input) {
                                        const liquidationDateDDMMYY = formatDateToDDMMYY(vm.liquidationDate); // Example date
                                               for (var i = 0; i < input.files.length; i++) {
                                                     if (input && input.files[i].type !== "text/csv") {
                                                        vm.invalidFile4 = true;
                                                        vm.invalidNameFile4 = false;
                                                        $('#fileInput4').val('');
                                                        }else if(input && input.files[i].name !== "TBNB"+liquidationDateDDMMYY+".csv"){
                                                        vm.invalidNameFile4 = true;
                                                        vm.invalidFile4 = false;
                                                        $('#fileInput4').val('');
                                                        }
                                                        else {
                                                         vm.invalidFile4 = false;
                                                         vm.invalidNameFile4 = false;
                                                         vm.commonError=false;
                                                        }
                                                  }
                                                $scope.$apply(); // Ensure scope is updated in AngularJS
                                            };
                                            vm.handleFiles5 = function(input) {
                                                              const liquidationDateDDMMYYYY = formatDateToCustomString(vm.liquidationDate); // Example date
                                                          for (var i = 0; i < input.files.length; i++) {
                                                                if (input && input.files[i].type !== "text/plain") {
                                                                   vm.invalidFile5 = true;
                                                                   vm.invalidNameFile5 = false;
                                                                   $('#fileInput5').val('');
                                                                   }else if(input && input.files[i].name !== "TAXBOND"+liquidationDateDDMMYYYY+".txt"){
                                                                   vm.invalidNameFile5 = true;
                                                                   vm.invalidFile5 = false;
                                                                   $('#fileInput5').val('');
                                                                   }
                                                                   else {
                                                                    vm.invalidFile5 = false;
                                                                    vm.invalidNameFile5 = false;
                                                                    vm.commonError=false;
                                                                   }
                                                             }
                                                           $scope.$apply(); // Ensure scope is updated in AngularJS
                                                       };
                                           vm.handleFiles6 = function(input) {
                                            const liquidationDateDDMMYYYY = formatDateToCustomString(vm.liquidationDate); // Example date
                                               for (var i = 0; i < input.files.length; i++) {
                                                     if (input && input.files[i].type !== "text/csv") {
                                                        vm.invalidFile6 = true;
                                                        vm.invalidNameFile6 = false;
                                                        $('#fileInput6').val('');
                                                        }else if(input && input.files[i].name !== "RRBNB_"+liquidationDateDDMMYYYY+"_Saksham.csv"){
                                                        vm.invalidNameFile6 = true;
                                                        vm.invalidFile6 = false;
                                                        $('#fileInput6').val('');
                                                        }
                                                        else {
                                                         vm.invalidFile6 = false;
                                                         vm.invalidNameFile6 = false;
                                                         vm.commonError=false;
                                                        }
                                                  }
                                                $scope.$apply(); // Ensure scope is updated in AngularJS
                                            };
                                            vm.handleFiles7 = function(input) {
                                            const liquidationDateDDMMYYYY = formatDateToCustomString(vm.liquidationDate); // Example date
                                                          for (var i = 0; i < input.files.length; i++) {
                                                                if (input && input.files[i].type !== "text/csv") {
                                                                   vm.invalidFile7 = true;
                                                                   vm.invalidNameFile7 = false;
                                                                   $('#fileInput7').val('');
                                                                   }else if(input && input.files[i].name !== "TBNB_"+liquidationDateDDMMYYYY+"_Saksham.csv"){
                                                                   vm.invalidNameFile7 = true;
                                                                   vm.invalidFile7 = false;
                                                                   $('#fileInput7').val('');
                                                                   }
                                                                   else {
                                                                    vm.invalidFile7 = false;
                                                                    vm.invalidNameFile7 = false;
                                                                    vm.commonError=false;
                                                                   }
                                                             }
                                                           $scope.$apply(); // Ensure scope is updated in AngularJS
                                                       };
                                                       vm.handleFiles8 = function(input) {
                                               const liquidationDateDDMMYYYY = formatDateToCustomString(vm.liquidationDate); // Example date
                                                  for (var i = 0; i < input.files.length; i++) {
                                                        if (input && input.files[i].type !== "text/csv") {
                                                           vm.invalidFile8 = true;
                                                           vm.invalidNameFile8 = false;
                                                           $('#fileInput8').val('');
                                                           }else if(input && input.files[i].name !== "RRBNB_"+liquidationDateDDMMYYYY+"_NOVOPAY.csv"){
                                                           vm.invalidNameFile8 = true;
                                                           vm.invalidFile8 = false;
                                                           $('#fileInput8').val('');
                                                           }
                                                           else {
                                                            vm.invalidFile8 = false;
                                                            vm.invalidNameFile8 = false;
                                                            vm.commonError=false;
                                                           }
                                                     }
                                                   $scope.$apply(); // Ensure scope is updated in AngularJS
                                               };
                                               vm.handleFiles9 = function(input) {
                                               const liquidationDateDDMMYYYY = formatDateToCustomString(vm.liquidationDate); // Example date
                                                             for (var i = 0; i < input.files.length; i++) {
                                                                   if (input && input.files[i].type !== "text/csv") {
                                                                      vm.invalidFile9 = true;
                                                                      vm.invalidNameFile9 = false;
                                                                      $('#fileInput9').val('');
                                                                      }else if(input && input.files[i].name !== "TBNB_"+liquidationDateDDMMYYYY+"_NOVOPAY.csv"){
                                                                      vm.invalidNameFile9 = true;
                                                                      vm.invalidFile9 = false;
                                                                      $('#fileInput9').val('');
                                                                      }
                                                                      else {
                                                                       vm.invalidFile9 = false;
                                                                       vm.invalidNameFile9 = false;
                                                                       vm.commonError=false;
                                                                      }
                                                                }
                                                              $scope.$apply(); // Ensure scope is updated in AngularJS
                                                          };
       }

       function uploadSubmit() {
           vm.commonError = false;
           vm.commonErrorMessage = "Proportional files are missing";
           const pair1 = !!(vm.files1 && vm.files2);
           const pair2 = !!(vm.files3 && vm.files4);
           const pair3 = !!(vm.files6 && vm.files7);
           const pair4 = !!(vm.files8 && vm.files9);
           const invalidPair =
               (vm.files1 && !vm.files2) || (!vm.files1 && vm.files2) ||
               (vm.files3 && !vm.files4) || (!vm.files3 && vm.files4) ||
               (vm.files6 && !vm.files7) || (!vm.files6 && vm.files7) ||
               (vm.files8 && !vm.files9) || (!vm.files8 && vm.files9);
           const hasValidFiles = pair1 || pair2 || pair3 || pair4 || vm.file5;
           if (!invalidPair && hasValidFiles) {
               vm.commonError = false;
               document.getElementById('fileUploadModal').style.display = "none";
           } else {
               vm.commonError = true;
               document.getElementById('fileUploadModal').style.display = "block";
           }
       }

       function validateFiles(filesArray) {
           return filesArray.every(file => file != null && file !== undefined && file !== "");
       }
       function validateFiles1(filesArray1) {
           return filesArray1.every(file => file != null && file !== undefined && file !== "");
       }
       function validateFiles2(filesArray2) {
         return filesArray2.every(file => file != null && file !== undefined && file !== "");
       }
       function validateFiles3(filesArray3) {
         return filesArray3.every(file => file != null && file !== undefined && file !== "");
       }
       function validateFiles4(filesArray4) {
        return filesArray4.every(file => file != null && file !== undefined && file !== "");
      }
        /* Add Submit */
        function submitAddForm() {
        // Example usage in your view model or component
        var filesArray = [vm.files1,vm.files2];
        var filesArray1 = [vm.files3, vm.files4];
        var filesArray2 = [vm.files5];
        var filesArray3 = [vm.files6, vm.files7];
        var filesArray4 = [vm.files8, vm.files9];
        if(validateFiles(filesArray) || validateFiles1(filesArray1) || validateFiles2(filesArray2) || validateFiles3(filesArray3) || validateFiles4(filesArray4)){
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
                   var requestData = {
                       'subCode' : vm.subCode,
                       'fileMode' : vm.fileMode,
                       'liquidationDate': $filter('date')(vm.liquidationDate,'dd-MMM-yyyy'),
                       'valueDate':$filter('date')(vm.valueDate,'dd-MMM-yyyy')
                   };
                   cashInFileUploadAndNetBankingFileUploadService.submitAddForm(vm.files1,vm.files2,vm.files3,vm.files4,vm.files5,vm.files6,vm.files7,vm.files8,vm.files9,requestData).then(function (response){
                       vm.loader = false;
                       //vm.model = response.data.result;
                       var value=JSON.parse(response.data);
                      if(null!=value.result && value.result.countOfRecord && value.result.totalAmt && value.result.fileName1){
                      if(vm.files5){
                       Swal.fire("Added!", "Cash In File Added Successfully \n (FileName: "+value.result.fileName1+") \n (CountOfRecord: "+value.result.countOfRecord+") \n (TotalAmt: "+indianNumberFormat(value.result.totalAmt) +")","success");
                      }else{
                       Swal.fire("Added!", "Inet Banking File Added Successfully \n (FileName: "+value.result.fileName1+") \n (CountOfRecord: "+value.result.countOfRecord+") \n (TotalAmt: "+indianNumberFormat(value.result.totalAmt)+")","success");
                      }
                      }else if(vm.files5){
                       Swal.fire("Added!", "Cash In File Added Successfully","success");
                      }
                      else{
                       Swal.fire("Added!", "Inet Banking File Added Successfully","success");
                      }
                       $state.go('cash_file_upload_and_net_banking_file_upload_processing.inquiry');
                   },function(error){
                       vm.loader = false;
                       handleErrorResponse1(error);
                        $state.go('cash_file_upload_and_net_banking_file_upload_processing.add');
                   })
                  } });
                   }else{
                    Swal.fire("Warning!","Upload The Necessary Files", "warning");
                    $state.go('cash_file_upload_and_net_banking_file_upload_processing.add');
                  }
            }

      /* Verify Submit */
                function submitVerifyForm() {
                vm.finalList=[];
                      angular.forEach(vm.responseResult, function(item,index){
                             var fileName=document.getElementById("selectedItem"+index).getAttribute("name");
                             if(item.fileName == 'RRBNB' && fileName == 'RRBNB' && vm.selectedCheckBox[fileName]==true){
                              vm.finalList.push(item);
                             }
                             if(item.fileName == 'TBNB'&& fileName == 'TBNB' && vm.selectedCheckBox[fileName]==true){
                              vm.finalList.push(item);
                             }
                             if(item.fileName == 'RRBNB_BB' && fileName == 'RRBNB_BB' && vm.selectedCheckBox[fileName]==true){
                              vm.finalList.push(item);
                             }
                             if(item.fileName == 'TBNB_BB' && fileName == 'TBNB_BB' &&vm.selectedCheckBox[fileName]==true){
                              vm.finalList.push(item);
                             }
                             if(item.fileName == 'RRBNB_SAKSHAM' && fileName == 'RRBNB_SAKSHAM' && vm.selectedCheckBox[fileName]==true){
                              vm.finalList.push(item);
                              }
                             if(item.fileName == 'TBNB_SAKSHAM' && fileName == 'TBNB_SAKSHAM' &&vm.selectedCheckBox[fileName]==true){
                             vm.finalList.push(item);
                              }
                            if(item.fileName == 'RRBNB_NOVOPAY' && fileName == 'RRBNB_NOVOPAY' && vm.selectedCheckBox[fileName]==true){
                            vm.finalList.push(item);
                            }
                           if(item.fileName == 'TBNB_NOVOPAY' && fileName == 'TBNB_NOVOPAY' &&vm.selectedCheckBox[fileName]==true){
                           vm.finalList.push(item);
                            }
                             if(item.fileName == 'TAXBOND'&& fileName == 'TAXBOND' && vm.selectedCheckBox[fileName]==true){
                              vm.finalList.push(item);
                             }
                        })
              if(vm.finalList.length>0){
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
vm.finalList1 = vm.finalList.map(function(item) {
  var cleanedItem = Object.assign({}, item);  // Shallow copy the item
  delete cleanedItem.$$hashKey;  // Delete the $$hashKey property
  return cleanedItem;
});
                            var requestData = {
                                'subCode' : vm.subCode,
                                'fileMode' : vm.fileMode,
                                'liquidationDate': $filter('date')(vm.liquidationDate,'dd-MMM-yyyy'),
                                'torchOut':vm.finalList1
                            };
                            cashInFileUploadAndNetBankingFileUploadService.submitVerifyForm(requestData).then(function(response) {
                                vm.loader = false;
                                vm.responseResult = response.data.result;
                               if(vm.finalList.length==2){
                                Swal.fire("Verified!", "Inet Banking File Verified Successfully","success");
                               }else if(vm.finalList.length==4){
                                Swal.fire("Verified!", "Inet Banking File Verified Successfully","success");
                               }else if(vm.finalList.length==6){
                                Swal.fire("Verified!", "Inet Banking File Verified Successfully","success");
                               }else if(vm.finalList.length==8){
                                Swal.fire("Verified!", "Inet Banking File Verified Successfully","success");
                               }else{
                                Swal.fire("Verified!", "Cash In File Verified Successfully","success");
                               }
                                $state.go('cash_file_upload_and_net_banking_file_upload_processing.inquiry')
                            }, function(error) {
                                vm.loader = false;
                                handleErrorResponse(error);
                                $state.go('cash_file_upload_and_net_banking_file_upload_processing.verify');
                            })
                            }
                        });
                        }else{
                         Swal.fire("Warning!","Please Select Necessary Files", "warning");
                         $state.go('cash_file_upload_and_net_banking_file_upload_processing.verify');
                        }
                }
                 function handleErrorResponse1(e) {
                        var value=JSON.parse(e.data);
                        var t = value.message ? value.message : "Server Error";
                        422 == e.status ? Swal.fire("Warning", t, "warning") : Swal.fire("Warning!", t, "warning")
                    }
  function inquiry() {
                            vm.loader = true;
                            var requestData = {
                                'subCode' : vm.subCode,
                                'liquidationDate': $filter('date')(vm.liquidationDate,'dd-MMM-yyyy'),
                            };
                            cashInFileUploadAndNetBankingFileUploadService.inquiry(requestData).then(function(response) {
                                vm.loader = false;
                                vm.responseResult = response.data.result.torchOut;
                                vm.overAllAmt = response.data.result.overAllAmt;
                                vm.overAllCount = response.data.result.overAllCount;
                                vm.valueDate = response.data.result.valueDate;
                                $state.go('cash_file_upload_and_net_banking_file_upload_processing.inquiry')
                            }, function(error) {
                                vm.loader = false;
                                vm.schemeName='';
                                handleErrorResponse(error);
                                $state.go('cash_file_upload_and_net_banking_file_upload_processing.inquiry');
                            })

                }

     /* Verify Select */
                function verifySelect() {
                            vm.loader = true;
                            var requestData = {
                                'subCode' : vm.subCode,
                                'fileMode' : vm.fileMode,
                                'fileType' : vm.fileType,
                                'liquidationDate': $filter('date')(vm.liquidationDate,'dd-MMM-yyyy'),
                            };
                            cashInFileUploadAndNetBankingFileUploadService.verifySelect(requestData).then(function(response) {
                                vm.loader = false;
                                vm.responseResult = response.data.result.torchOut;
                                vm.details = response.data.result;
                                vm.overAllAmt = response.data.result.overAllAmt;
                                vm.overAllCount = response.data.result.overAllCount;
                                vm.valueDate = response.data.result.valueDate;
                                $state.go('cash_file_upload_and_net_banking_file_upload_processing.verify')
                            }, function(error) {
                                vm.loader = false;
                                vm.responseResult='';
                                vm.schemeName='';
                                handleErrorResponse(error);
                                $state.go('cash_file_upload_and_net_banking_file_upload_processing.verify');
                            })
                }
                vm.indianNumberFormat=indianNumberFormat;
                function indianNumberFormat(value) {
                  if (isNaN(value)) return value;
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
function rejectReason() {
  angular.element('#permissionMappingDelete').modal('toggle');
  }


    function validateInput(event,id){

          const textBox = document.getElementById(id);

            var charCode = event.which || event.keyCode;
              var charTyped = String.fromCharCode(charCode);

      if(textBox)
      {
       const cursorPosition = textBox.selectionStart;
          if(cursorPosition == 0 && charTyped === ' ')
          {
                  event.preventDefault();
                  return;
          }
          }
            var currentValue = event.target.value + charTyped;
              var pattern = /^[a-zA-Z\s]*'?([a-zA-Z\s]*'?)*$/;



              if (!pattern.test(currentValue)) {
                  event.preventDefault();
              }
          }

function deleteSubmit() {
                vm.finalList=[];
angular.forEach(vm.responseResult, function(item,index){
                 var fileName=document.getElementById("selectedItem"+index).getAttribute("name");
                 if(item.fileName == 'RRBNB' && fileName == 'RRBNB' && vm.selectedCheckBox[fileName]==true){
                  vm.finalList.push(item);
                 }
                 if(item.fileName == 'TBNB'&& fileName == 'TBNB' && vm.selectedCheckBox[fileName]==true){
                  vm.finalList.push(item);
                 }
                 if(item.fileName == 'RRBNB_BB' && fileName == 'RRBNB_BB' && vm.selectedCheckBox[fileName]==true){
                  vm.finalList.push(item);
                 }
                 if(item.fileName == 'TBNB_BB' && fileName == 'TBNB_BB' &&vm.selectedCheckBox[fileName]==true){
                  vm.finalList.push(item);
                 }
                 if(item.fileName == 'RRBNB_SAKSHAM' && fileName == 'RRBNB_SAKSHAM' && vm.selectedCheckBox[fileName]==true){
                  vm.finalList.push(item);
                 }
                 if(item.fileName == 'TBNB_SAKSHAM' && fileName == 'TBNB_SAKSHAM' &&vm.selectedCheckBox[fileName]==true){
                  vm.finalList.push(item);
                 }
                 if(item.fileName == 'RRBNB_NOVOPAY' && fileName == 'RRBNB_NOVOPAY' && vm.selectedCheckBox[fileName]==true){
                   vm.finalList.push(item);
                  }
                  if(item.fileName == 'TBNB_NOVOPAY' && fileName == 'TBNB_NOVOPAY' &&vm.selectedCheckBox[fileName]==true){
                   vm.finalList.push(item);
                  }
                 if(item.fileName == 'TAXBOND'&& fileName == 'TAXBOND' && vm.selectedCheckBox[fileName]==true){
                  vm.finalList.push(item);
                 }
          })
    if(vm.finalList.length>0){
        vm.loader = true;
        vm.finalList1 = vm.finalList.map(function(item) {
          var cleanedItem = Object.assign({}, item);  // Shallow copy the item
          delete cleanedItem.$$hashKey;  // Delete the $$hashKey property
          return cleanedItem;
        });
        var requestData = {
             'subCode' : vm.subCode,
             'fileMode' : vm.fileMode,
             'liquidationDate': $filter('date')(vm.liquidationDate,'dd-MMM-yyyy'),
             'torchOut':vm.finalList1,
             'rejectionRemarks':vm.remarks
        };
        cashInFileUploadAndNetBankingFileUploadService.deleteSubmit(requestData)
            .then(function(response) {
                vm.loader = false;
                   const backdrop = document.querySelector('.modal-backdrop');
                if (backdrop) {
                  backdrop.parentNode.removeChild(backdrop);
                  document.body.classList.remove('modal-open');
                }
                Swal.fire("Rejected!", "File Data Rejected Successfully", "success");
                $state.go('cash_file_upload_and_net_banking_file_upload_processing.inquiry');
            })
            .catch(function(error) {
                vm.loader = false;
                handleErrorResponse(error);
                    const backdrop = document.querySelector('.modal-backdrop');
                    if (backdrop) {
                    backdrop.parentNode.removeChild(backdrop);
                    document.body.classList.remove('modal-open');
                   }
                $state.go('cash_file_upload_and_net_banking_file_upload_processing.verify');
            });
    }else{
       Swal.fire("Warning!","Please Select Necessary Files", "warning");
       $state.go('cash_file_upload_and_net_banking_file_upload_processing.verify');
    }
}
                                 /*function valueDateValidation() {
                                   var currentDate = moment(vm.currentDate); // Get the current date as a Moment object
                                   var sevenDaysBefore = currentDate.clone().subtract(7, 'days').toDate(); // Date 7 days before
                                   var sevenDaysAfter = currentDate.clone().add(7, 'days').toDate(); // Date 7 days after
                                   // Set the date range options for the calendar
                                   vm.valueDateCalenderOptions = {
                                       minDate: sevenDaysBefore, // Minimum selectable date
                                       maxDate: sevenDaysAfter // Maximum selectable date
                                   };
                                   // Optionally, you can set the default liquidationDate if needed
                                   vm.valueDate = currentDate.toDate(); // Set default date to current date
                               }*/
                               function valueDateValidation() {
                                   // Use system date
                                   var systemDate = moment(vm.currentDate); // or moment() if vm.currentDate not needed
                                   // Allow any past date, restrict future dates
                                   vm.valueDateCalenderOptions = {
                                       minDate: null,                  // no minimum restriction (any past date allowed)
                                       maxDate: systemDate.toDate()    // only allow till system date
                                   };
                                   // Default value = system date
                                   vm.valueDate = systemDate.toDate();
                               }
       function fromToDateValidation() {
            var sevenDaysBefore = moment(vm.currentDate).subtract(7, 'days').toDate();
                   // Set the minimum date to be 7 days before the current date
                   vm.liquidationDateCalenderOptions = {
                       //minDate: sevenDaysBefore,
                       //maxDate: vm.currentDate
                   };
                   // Set the minimum date for liquidationDate
                   vm.liquidationDate = vm.currentDate;
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
         commonService.autoslashing(id);

        }



        function futureNotAllowed(id,valueDate)
        {
         commonService.futureNotAllowed(id,valueDate);
        }
    }
})();