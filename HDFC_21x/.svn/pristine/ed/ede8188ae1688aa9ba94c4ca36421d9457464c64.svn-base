(function() {

    'use strict';

    angular.module('gbmUltraApp').controller('preMaturityClosureMarkingController',
        preMaturityClosureMarkingController);

    preMaturityClosureMarkingController.$inject = ['$scope', '$filter', '$state', '$rootScope',
        '$stateParams', '$location', '$timeout', 'preMaturityClosureMarkingService', 'commonService'
    ];

    function preMaturityClosureMarkingController($scope, $filter, $state, $rootScope, $stateParams,
        $location, $timeout, preMaturityClosureMarkingService, commonService) {
	     var vm = this;
        vm.currentPage = 1;
        vm.itemsPerPage = '5';
        vm.getRemarksList = getRemarksList;
		vm.commonYesNoMetaData = COMMON_YES_NO_METADATA;
		vm.getAddPanTorchBlaNoTorchDetails = getAddPanTorchBlaNoTorchDetails;
		vm.getVerifyPanTorchBlaNoTorchDetails = getVerifyPanTorchBlaNoTorchDetails;
		vm.getModifyPanTorchBlaNoTorchDetails = getModifyPanTorchBlaNoTorchDetails;
		vm.getInquiryPanTorchBlaNoTorchDetails = getInquiryPanTorchBlaNoTorchDetails;
		vm.getAddDetails = getAddDetails;
		vm.getModifyDetails = getModifyDetails;
		vm.getVerifyDetails = getVerifyDetails;
		vm.getInquiryDetails = getInquiryDetails;
		vm.addSubmit = addSubmit;
		vm.verifySubmit = verifySubmit;
		vm.modifySubmit = modifySubmit;
		vm.deleteSubmit = deleteSubmit;
		vm.updateSelection=updateSelection;
		vm.rejectReason=rejectReason;
        vm.validateInput=validateInput;
        vm.loginSolDate = $rootScope.globals.currentUser.solDate;
        vm.panPattern=/[a-zA-Z]{5}[0-9]{4}[a-zA-Z]{1}/;
        if($state.current.name == 'pre_maturity_closure_marking.add_detail') {
		    if($stateParams.obj){
                vm.schemeCode = $stateParams.obj.schemeCode;
                vm.schemeName = $stateParams.obj.schemeName;
                vm.blaNo = $stateParams.obj.blaNo;
                vm.panNum = $stateParams.obj.panNum;
                vm.bondHolderName = $stateParams.obj.bondHolderName;
                getAddDetails();
		    }
		}else if($state.current.name == 'pre_maturity_closure_marking.modify_detail') {
              		    if($stateParams.obj){
                              vm.schemeCode = $stateParams.obj.schemeCode;
                              vm.schemeName = $stateParams.obj.schemeName;
                              vm.blaNo = $stateParams.obj.blaNo;
                              vm.panNum = $stateParams.obj.panNum;
                              vm.bondHolderName = $stateParams.obj.bondHolderName;
                              getModifyDetails();
              		    }
         }else if($state.current.name == 'pre_maturity_closure_marking.verify_detail') {
                            if($stateParams.obj){
                                   vm.schemeCode = $stateParams.obj.schemeCode;
                                   vm.schemeName = $stateParams.obj.schemeName;
                                   vm.blaNo = $stateParams.obj.blaNo;
                                   vm.panNum = $stateParams.obj.panNum;
                                   vm.bondHolderName = $stateParams.obj.bondHolderName;
                                   getVerifyDetails();
                                   		    }
          }else if($state.current.name == 'pre_maturity_closure_marking.inquiry_detail') {
                            if($stateParams.obj){
                                   vm.schemeCode = $stateParams.obj.schemeCode;
                                   vm.schemeName = $stateParams.obj.schemeName;
                                   vm.blaNo = $stateParams.obj.blaNo;
                                   vm.panNum = $stateParams.obj.panNum;
                                   vm.bondHolderName = $stateParams.obj.bondHolderName;
                                   getInquiryDetails();
                            }
                        }

             function getRemarksList() {
                              vm.referenceId = '056';
                              var requestData = { 'referenceId': vm.referenceId };

                              preMaturityClosureMarkingService.getRemarksList(requestData).then(function(response) {
                                  vm.remarksList = response.data.result || []; // Ensure it's an array
                              }, function(error) {
                                  handleErrorResponse(error);
                              });
                          }
                      vm.toggleAllSelection=toggleAllSelection;
           function toggleAllSelection() {
                    if (vm.checkAll) {
                    vm.responseResult.forEach(function(record) {
                    record.selected = true;
                    });
                     }
                     else {
                     vm.responseResult.forEach(function(record) {
                    record.selected = false;
                    });
                    }
                 }
          function updateSelection(){
                                  vm.selectedAccounts = [];
                                  vm.otherAccounts = [];
                                   if (Array.isArray(vm.responseResult)) {
                                  vm.responseResult.forEach(function(x) {
                                    if (x.selected) {
                                      vm.selectedAccounts.push(x.subsId);
                                      if(vm.responseResult.length===vm.selectedAccounts.length){
                                      vm.checkAll=true;
                                      }else{
                                      vm.checkAll=false;
                                      }
                                    } else {
                                      vm.otherAccounts.push(x.subsId);
                                      vm.checkAll=false;
                                    }
                                  });
                                   } else {
                                      console.warn("preMaturity is not defined or not an array.");
                                    }
                                };
           function getAddDetails(flg) {
                        vm.loader = true;
                        if(flg=='S'){
                        vm.schemeCode=null;
                         var requestData = {
                         'schemeCode':vm.schemeCode
                         };
                        }else{
                        var requestData = {
                        'schemeCode':vm.schemeCode,
                        'blaNo':vm.blaNo,
                        'panNo':vm.panNum,
                        'bondHolderName':vm.bondHolderName
                        };
                        }
                        preMaturityClosureMarkingService.getAddDetails(requestData).then(function (response) {
                            vm.loader = false;
                            if(flg=='S'){
                            vm.schemeList = response.data.result;
                            angular.element('#schemeModel').modal('toggle');
                            }else{
                             vm.responseResult=response.data.result.preMaturityList;
                             vm.totalAmt=response.data.result.totalAmt;
                            }
                        }, function (error) {
                           vm.loader = false;
                           if(error.data.message == 'No Records Found' | error.data.message == 'NDF'){
                           vm.noDataFound = error.data.message;
                           Swal.fire("Warning!",vm.noDataFound,"warning");
                            }
                          else{
                          handleErrorResponse(error);
                          $state.go("pre_maturity_closure_marking.inquiry");
                          }
                        });
         }
          function getAddPanTorchBlaNoTorchDetails(flg) {
               if(vm.panNum.length==10){
               if(!vm.panPattern.test(vm.panNum.toUpperCase())){
                   Swal.fire("Warning!","INVALID PAN NO", "warning");
                   return false;
               }
               }
                                 if(vm.schemeCode){
                                 vm.loader = true;
                                 if(flg==='P'){
                                 var requestData = {
                                 'schemeCode':vm.schemeCode,
                                 'blaNo':vm.blaNo,
                                 'panNo':vm.panNum,
                                 'bondHolderName':vm.bondHolderName,
                                 'flag':flg
                                 };
                                 }else{
                                 var requestData = {
                                  'schemeCode':vm.schemeCode,
                                  'blaNo':vm.blaNo,
                                  'panNo':vm.panNum,
                                  'bondHolderName':vm.bondHolderName,
                                  'flag':flg
                                  };
                                 }
                                 preMaturityClosureMarkingService.getAddPanTorchBlaNoTorchDetails(requestData).then(function (response) {
                                     vm.loader = false;
                                     if(flg==='P'){
                                     vm.PanTorch = response.data.result.torchValue;
                                     angular.element('#panModel').modal('toggle');
                                     }else{
                                     vm.blaDataList = response.data.result.torchValue;
                                     angular.element('#blaModel').modal('toggle');
                                     }
                                 }, function (error) {
                                    vm.loader = false;
                                    if(error.data.message == 'No Records Found' | error.data.message == 'NDF'){
                                    vm.noDataFound = error.data.message;
                                    Swal.fire("Warning!",vm.noDataFound,"warning");
                                     }
                                   else{
                                   handleErrorResponse(error);
                                   $state.go("pre_maturity_closure_marking.inquiry");
                                   }
                                 });
                                 }else{
                                  Swal.fire("Warning!","Kindly Select SchemeName", "warning");
                                  $state.go('pre_maturity_closure_marking.add');
                                 }
                  }
                   function getVerifyPanTorchBlaNoTorchDetails(flg) {
                       if(vm.panNum.length==10){
                           if(!vm.panPattern.test(vm.panNum.toUpperCase())){
                               Swal.fire("Warning!","INVALID PAN NO", "warning");
                               return false;
                           }}
                                                   if(vm.schemeCode){
                                                   vm.loader = true;
                                                   if(flg==='P'){
                                                   var requestData = {
                                                   'schemeCode':vm.schemeCode,
                                                   'blaNo':vm.blaNo,
                                                   'panNo':vm.panNum,
                                                   'bondHolderName':vm.bondHolderName,
                                                   'flag':'P'
                                                   };
                                                   }else{
                                                   var requestData = {
                                                    'schemeCode':vm.schemeCode,
                                                    'blaNo':vm.blaNo,
                                                    'panNo':vm.panNum,
                                                    'bondHolderName':vm.bondHolderName,
                                                    'flag':'B'
                                                    };
                                                   }
                                                   preMaturityClosureMarkingService.getVerifyPanTorchBlaNoTorchDetails(requestData).then(function (response) {
                                                       vm.loader = false;
                                                       if(flg==='P'){
                                                       vm.PanTorch = response.data.result.torchValue;
                                                       angular.element('#panModel').modal('toggle');
                                                       }else{
                                                       vm.blaDataList = response.data.result.torchValue;
                                                       angular.element('#blaModel').modal('toggle');
                                                       }
                                                   }, function (error) {
                                                      vm.loader = false;
                                                      if(error.data.message == 'No Records Found' | error.data.message == 'NDF'){
                                                      vm.noDataFound = error.data.message;
                                                      Swal.fire("Warning!",vm.noDataFound,"warning");
                                                       }
                                                     else{
                                                     handleErrorResponse(error);
                                                     $state.go("pre_maturity_closure_marking.inquiry");
                                                     }
                                                   });
                                                    }else{
                                                     Swal.fire("Warning!","Kindly Select SchemeName", "warning");
                                                     $state.go('pre_maturity_closure_marking.verify');
                                                    }
                                    }
                                    function getModifyPanTorchBlaNoTorchDetails(flg) {
                                        if(vm.panNum.length==10){
                                            if(!vm.panPattern.test(vm.panNum.toUpperCase())){
                                                Swal.fire("Warning!","INVALID PAN NO", "warning");
                                                return false;
                                            }}
                                                                      if(vm.schemeCode){
                                                                      vm.loader = true;
                                                                      if(flg==='P'){
                                                                      var requestData = {
                                                                      'schemeCode':vm.schemeCode,
                                                                      'blaNo':vm.blaNo,
                                                                      'panNo':vm.panNum,
                                                                      'bondHolderName':vm.bondHolderName,
                                                                      'flag':'P'
                                                                      };
                                                                      }else{
                                                                      var requestData = {
                                                                       'schemeCode':vm.schemeCode,
                                                                       'blaNo':vm.blaNo,
                                                                       'panNo':vm.panNum,
                                                                       'bondHolderName':vm.bondHolderName,
                                                                       'flag':'B'
                                                                       };
                                                                      }
                                                                      preMaturityClosureMarkingService.getModifyPanTorchBlaNoTorchDetails(requestData).then(function (response) {
                                                                          vm.loader = false;
                                                                          if(flg==='P'){
                                                                          vm.PanTorch = response.data.result.torchValue;
                                                                          angular.element('#panModel').modal('toggle');
                                                                          }else{
                                                                          vm.blaDataList = response.data.result.torchValue;
                                                                          angular.element('#blaModel').modal('toggle');
                                                                          }
                                                                      }, function (error) {
                                                                         vm.loader = false;
                                                                         if(error.data.message == 'No Records Found' | error.data.message == 'NDF'){
                                                                         vm.noDataFound = error.data.message;
                                                                         Swal.fire("Warning!",vm.noDataFound,"warning");
                                                                          }
                                                                        else{
                                                                        handleErrorResponse(error);
                                                                        $state.go("pre_maturity_closure_marking.inquiry");
                                                                        }
                                                                      });
                                                                       }else{
                                                                    Swal.fire("Warning!","Kindly Select SchemeName", "warning");
                                                                    $state.go('pre_maturity_closure_marking.modify');
                                                                   }
                                                       }
        function getInquiryPanTorchBlaNoTorchDetails(flg) {
            if(vm.panNum.length==10){
                if(!vm.panPattern.test(vm.panNum.toUpperCase())){
                    Swal.fire("Warning!","INVALID PAN NO", "warning");
                    return false;
                }}
                                        if(vm.schemeCode){
                                         vm.loader = true;
                                        if(flg==='P'){
                                        var requestData = {
                                        'schemeCode':vm.schemeCode,
                                        'blaNo':vm.blaNo,
                                        'panNo':vm.panNum,
                                        'bondHolderName':vm.bondHolderName,
                                        'flag':'P'
                                        };
                                        }else{
                                        var requestData = {
                                         'schemeCode':vm.schemeCode,
                                         'blaNo':vm.blaNo,
                                         'panNo':vm.panNum,
                                         'bondHolderName':vm.bondHolderName,
                                         'flag':'B'
                                         };
                                        }
                                        preMaturityClosureMarkingService.getInquiryPanTorchBlaNoTorchDetails(requestData).then(function (response) {
                                            vm.loader = false;
                                            if(flg==='P'){
                                            vm.PanTorch = response.data.result.torchValue;
                                            angular.element('#panModel').modal('toggle');
                                            }else{
                                            vm.blaDataList = response.data.result.torchValue;
                                            angular.element('#blaModel').modal('toggle');
                                            }
                                        }, function (error) {
                                           vm.loader = false;
                                           if(error.data.message == 'No Records Found' | error.data.message == 'NDF'){
                                           vm.noDataFound = error.data.message;
                                           Swal.fire("Warning!",vm.noDataFound,"warning");
                                            }
                                          else{
                                          handleErrorResponse(error);
                                          $state.go("pre_maturity_closure_marking.inquiry");
                                          }
                                        });
                                         }else{
                                      Swal.fire("Warning!","Kindly Select SchemeName", "warning");
                                      $state.go('pre_maturity_closure_marking.inquiry');
                                     }
                         }
                            function getModifyDetails(flg) {
                            vm.loader = true;
                             if(flg=='S'){
                             vm.schemeCode=null;
                                                     var requestData = {
                                                     'schemeCode':vm.schemeCode
                                                     };
                                                    }else{
                            var requestData = {
                            'schemeCode':vm.schemeCode,
                            'blaNo':vm.blaNo,
                            'panNo':vm.panNum,
                            'bondHolderName':vm.bondHolderName
                            };}
                            preMaturityClosureMarkingService.getModifyDetails(requestData).then(function (response) {
                                vm.loader = false;
                                if(flg=='S'){
                                vm.schemeList = response.data.result;
                                angular.element('#schemeModel').modal('toggle');
                                }else{
                                  vm.responseResult=response.data.result.preMaturityList;
                                 vm.totalAmt=response.data.result.totalAmt;
                                }
                            }, function (error) {
                               vm.loader = false;
                               if(error.data.message == 'No Records Found' | error.data.message == 'NDF'){
                               vm.noDataFound = error.data.message;
                               Swal.fire("Warning!",vm.noDataFound,"warning");
                                }
                              else{
                              handleErrorResponse(error);
                              $state.go("pre_maturity_closure_marking.inquiry");
                              }
                            });
                        }
 function getVerifyDetails(flg) {
                                    vm.loader = true;
                                     if(flg=='S'){
                                     vm.schemeCode=null;
                                                             var requestData = {
                                                             'schemeCode':vm.schemeCode
                                                             };
                                                            }else{
                                    var requestData = {
                                    'schemeCode':vm.schemeCode,
                                    'blaNo':vm.blaNo,
                                    'panNo':vm.panNum,
                                    'bondHolderName':vm.bondHolderName
                                    };}
                                    preMaturityClosureMarkingService.getVerifyDetails(requestData).then(function (response) {
                                        vm.loader = false;
                                        if(flg=='S'){
                                        vm.schemeList = response.data.result;
                                        angular.element('#schemeModel').modal('toggle');
                                        }else{
                                          vm.responseResult=response.data.result.preMaturityList;
                                         vm.totalAmt=response.data.result.totalAmt;
                                         }
                                    }, function (error) {
                                       vm.loader = false;
                                       if(error.data.message == 'No Records Found' | error.data.message == 'NDF'){
                                       vm.noDataFound = error.data.message;
                                       Swal.fire("Warning!",vm.noDataFound,"warning");
                                        }
                                      else{
                                      handleErrorResponse(error);
                                      $state.go("pre_maturity_closure_marking.inquiry");
                                      }
                                    });
                                }
 function getInquiryDetails(flg) {
                                    vm.loader = true;
                                     if(flg=='S'){
                                     vm.schemeCode=null;
                                                             var requestData = {
                                                             'schemeCode':vm.schemeCode
                                                             };
                                                            }else{
                                    var requestData = {
                                    'schemeCode':vm.schemeCode,
                                    'blaNo':vm.blaNo,
                                    'panNo':vm.panNum,
                                    'bondHolderName':vm.bondHolderName
                                    };}
                                    preMaturityClosureMarkingService.getInquiryDetails(requestData).then(function (response) {
                                        vm.loader = false;
                                        if(flg=='S'){
                                        vm.schemeList = response.data.result;
                                        angular.element('#schemeModel').modal('toggle');
                                      }else{
                                         vm.responseResult=response.data.result.preMaturityList;
                                         vm.totalAmt=response.data.result.totalAmt;
                                      }
                                    }, function (error) {
                                      vm.loader = false;
                                      if(error.data.message == 'No Records Found' | error.data.message == 'NDF'){
                                       vm.noDataFound = error.data.message;
                                       Swal.fire("Warning!",vm.noDataFound,"warning");
                                        }
                                      else{
                                      handleErrorResponse(error);
                                      $state.go("pre_maturity_closure_marking.inquiry");
                                      }
                                    });
                                }

function addSubmit() {
                        updateSelection();
                        if(vm.selectedAccounts.length>0){
//                        Swal.fire({
//                         title: "Are you sure?",
//                         text: "Do you want to Really Add this record?",
//                         type: "warning",
//                         showCancelButton: true,
//                         cancelButtonClass: "btn-cancel",
//                         confirmButtonClass: "btn-primary",
//                         confirmButtonText: "Yes, Add it!",
//                         closeOnConfirm: false,
//                         showLoaderOnConfirm: true
//                        },function(){



                      Swal.fire({
                        title: "Are you sure?",
                        text: "Do you want to Really Add this record?",
                        icon: "warning", // use "icon", not "type"
                        showCancelButton: true,
                        confirmButtonText: "Yes Add it!",
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
                        'schemeCode':vm.schemeCode,
                        'blaNo':vm.blaNo,
                        'panNo':vm.panNum,
                        'bondHolderName':vm.bondHolderName,
                        'listOfString':vm.selectedAccounts
                        };
                        preMaturityClosureMarkingService.addSubmit(requestData).then(function (response) {
                           vm.loader = false;
                           Swal.fire("Added!", "Marked Successfully","success");
                           $state.go("pre_maturity_closure_marking.inquiry");
                        }, function (error) {
                           vm.loader = false;
                           if(error.data.message == 'No Records Found' | error.data.message == 'NDF'){
                           vm.noDataFound = error.data.message;
                           Swal.fire("Warning!",vm.noDataFound,"warning");
                            }
                           else if(error.statusCode == '0' | error.status =='500'){
                               Swal.fire("Warning!",error.message,"warning");
                           }
                          else{
                          handleErrorResponse(error);
                          $state.go("pre_maturity_closure_marking.add");
                          }
                        });
                        }
                        });
                        }else{
                        Swal.fire("Warning!","Please Select Any One Of The Records", "warning");
                        $state.go('pre_maturity_closure_marking.add_detail');
                        }
                    }
                    function rejectReason() {
                      angular.element('#permissionMappingDelete').modal('toggle');
                      }
                        function validateInput(event,id){
                                const textBox = document.getElementById(id);
                                  var charCode = event.which || event.keyCode;
                                  var charTyped = String.fromCharCode(charCode);
                            if(textBox){
                             const cursorPosition = textBox.selectionStart;
                                if(cursorPosition == 0 && charTyped === ' '){
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
                   function deleteSubmit(){
//                    Swal.fire(
//                           {
//                             title: "Are you sure?",
//                             text: "Do you want to Really Reject this record?",
//                             type: "warning",
//                             showCancelButton: true,
//                             cancelButtonClass: "btn-cancel",
//                             confirmButtonClass: "btn-primary",
//                             confirmButtonText: "Yes",
//                             closeOnConfirm: false,
//                           },
//                           function () {


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
                           vm.selectedAccounts=[];
                           if(vm.responseResult.length>0){
                             vm.responseResult.forEach(function(x) {
                              vm.selectedAccounts.push(x.subsId);
                              });
                              }
                           var requestData = {
                           'schemeCode':vm.schemeCode,
                           'blaNo':vm.blaNo,
                           'panNo':vm.panNum,
                           'bondHolderName':vm.bondHolderName,
                           'listOfString':vm.selectedAccounts,
                           'rejectionRemarks':vm.remarks
                           };
                       preMaturityClosureMarkingService.deleteSubmit(requestData).then(function(response) {
                           vm.loader = false;
                           Swal.fire("Rejected!","Rejected Successfully","success");
                           $state.go("pre_maturity_closure_marking.inquiry");
                       }, function(error) {
                           vm.loader = false;
                           if(error.data.message == 'No Records Found' | error.data.message == 'NDF'){
                             vm.noDataFound = error.data.message;
                             Swal.fire("Warning!",vm.noDataFound,"warning");
                           }else if(error.statusCode == '0' | error.status =='500'){
                               Swal.fire("Warning!",error.message,"warning");
                           }
                         else{
                         handleErrorResponse(error);
                         $state.go("pre_maturity_closure_marking.verify");
                         }
                       })
                       }
                       })
                   }
                   function verifySubmit(){
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
                            vm.selectedAccounts=[];
                           if(vm.responseResult.length>0){
                             vm.responseResult.forEach(function(x) {
                              vm.selectedAccounts.push(x.subsId);
                              })
                              }
                           var requestData = {
                           'schemeCode':vm.schemeCode,
                           'blaNo':vm.blaNo,
                           'panNo':vm.panNum,
                           'bondHolderName':vm.bondHolderName,
                           'listOfString':vm.selectedAccounts
                           };
                            preMaturityClosureMarkingService.verifySubmit(requestData).then(function(response) {
                                vm.loader = false;
                                Swal.fire("Verified!", "Verified Successfully","success");
                                $state.go('pre_maturity_closure_marking.inquiry');
                            }, function(error) {
                                vm.loader = false;
                                if(error.data.message == 'No Records Found' | error.data.message == 'NDF'){
                                  vm.noDataFound = error.data.message;
                                  Swal.fire("Warning!",vm.noDataFound,"warning");
                                }else if(error.statusCode == '0' | error.status =='500'){
                                    Swal.fire("Warning!",error.message,"warning");
                                }
                              else{
                              handleErrorResponse(error);
                              $state.go("pre_maturity_closure_marking.verify");
                              }
                            })
                            }
                        });
                   }
                   function modifySubmit(){
                        updateSelection();
                        if(vm.selectedAccounts.length>0){
//                        Swal.fire({
//                         title: "Are you sure?",
//                         text: "Do you want to Really Modify this record?",
//                         type: "warning",
//                         showCancelButton: true,
//                         cancelButtonClass: "btn-cancel",
//                         confirmButtonClass: "btn-primary",
//                         confirmButtonText: "Yes,Modify it!",
//                         closeOnConfirm: false,
//                         showLoaderOnConfirm: true
//                        },function(){

                          Swal.fire({
                            title: "Are you sure?",
                            text: "Do you want to Really Modify this record?",
                            icon: "warning", // use "icon", not "type"
                            showCancelButton: true,
                            confirmButtonText: "Yes Modify it!",
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
                        'schemeCode':vm.schemeCode,
                        'blaNo':vm.blaNo,
                        'panNo':vm.panNum,
                        'bondHolderName':vm.bondHolderName,
                        'listOfString':vm.selectedAccounts
                        };
                        preMaturityClosureMarkingService.modifySubmit(requestData).then(function (response) {
                           vm.loader = false;
                           Swal.fire("Modify!", "Modified Successfully","success");
                           $state.go("pre_maturity_closure_marking.inquiry");
                        }, function (error) {
                           vm.loader = false;
                           if(error.data.message == 'No Records Found' | error.data.message == 'NDF'){
                           vm.noDataFound = error.data.message;
                           Swal.fire("Warning!",vm.noDataFound,"warning");
                            }else if(error.statusCode == '0' | error.status =='500'){
                               Swal.fire("Warning!",error.message,"warning");
                           }
                          else{
                          handleErrorResponse(error);
                          $state.go("pre_maturity_closure_marking.modify");
                          }
                        });
                        }
                        });
                        }else{
                        Swal.fire("Warning!","Please Select Any One Of The Records", "warning");
                        $state.go('pre_maturity_closure_marking.modify_detail');
                        }
                   }
                    }
        })();



