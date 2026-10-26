(function() {

	'use strict';

	angular.module('gbmUltraApp').controller('rbiBondLevelMasterController',
rbiBondLevelMasterController);



	rbiBondLevelMasterController.$inject = [ '$scope', '$filter', '$state',
			'$stateParams', '$location',  'commonService','rbiBondLevelMasterService'];



	function rbiBondLevelMasterController($scope, $filter, $state, $stateParams,
			$location,commonService,rbiBondLevelMasterService) {

    var vm = this;

     vm.levelNameMap = {
         "LINK CELL" : "LC",
         "CPPC" : "CPPC",
         "NODAL BRANCH" : "NB",
         "LINK BRANCH" : "LB",
         "PAY BRANCH" : "PB",
         "TERMINAL BRANCH":"TB",
         "POP" : "POP",
         "POP-SP":"POP-SP",
         "E-FPB" : "E-FPB"
     }
    vm.commonYesNoMetaData = COMMON_YES_NO_METADATA;
    vm.getLevelRow = getLevelRow;
    vm.setParentLevel = setParentLevel;
    vm.addLevelMaster = addLevelMaster;
    vm.deleteSubmit = deleteSubmit;
    vm.verifySubmit = verifySubmit;
 //   vm.getschemeName = getschemeName;
    vm.getLevelName = getLevelName;
    vm.getUnVerifyRecords = getUnVerifyRecords;
    vm.getInquiryDetails = getInquiryDetails;
    vm.addErrorMsg = addErrorMsg;
    vm.addValidationLevelMaster = addValidationLevelMaster;
    vm.showField='hide';
    vm.screenModule = 'RBI Bond';
    vm.readable = true;





     if ($state.current.name == 'rbi_bond_level_master.add' || $state.current.name == 'rbi_bond_level_master.inquiry' || $state.current.name == 'rbi_bond_level_master.delete' || $state.current.name == 'rbi_bond_level_master.verify'){
                 vm.showField='show';
                 vm.screenModule = 'RBI Bond';
                vm.displayMsg='SUCCESS'
         }

    vm.goToPreviousState = function(){
                 if(vm.screenModule == 'RBI Bond'){
                          vm.showField='show';

                          vm.displayMsg='SUCCESS'
                                    if($state.current.name == 'rbi_bond_level_master.add' || $state.current.name == 'rbi_bond_level_master.verify' || $state.current.name == 'rbi_bond_level_master.delete'){
                                        $state.go('rbi_bond_level_master.inquiry')
                                    }else if($state.current.name == 'rbi_bond_level_master.inquiry'){
                                        $state.go('dc_rbi_bond_menu')
                                    }
                                }
    }



    //Get Module Name
//    function getModuleName() {
//       vm.referenceId = 'MOD_FOR_INT';
//       var requestData = {
//             'referenceId': vm.referenceId
//         };
//
//       rbiBondLevelMasterService.getModuleDetails(requestData).then(function(response){
//          vm.moduleNameList = response.data.result;
//          vm.moduleList = [];
//
//          if(vm.screenModule == 'CBDT'){
//            angular.forEach(vm.moduleNameList, function(item){
//                if(item.subCode == '0400'){
//                    vm.moduleList.push(item);
//                }
//            })
//          } else if (vm.screenModule == 'PPF'){
//                angular.forEach(vm.moduleNameList, function(item){
//                    if(item.subCode == '0900'){
//                        vm.moduleList.push(item);
//                    }
//                })
//              }
//              else if (vm.screenModule == 'SSA'){
//                  angular.forEach(vm.moduleNameList, function(item){
//                      if(item.subCode == '0600'){
//                          vm.moduleList.push(item);
//                      }
//                  })
//                }else if (vm.screenModule == 'SCSS'){
//                   angular.forEach(vm.moduleNameList, function(item){
//                       if(item.subCode == '0501'){
//                           vm.moduleList.push(item);
//                       }
//                   })
//                 }else if (vm.screenModule == 'KVP'){
//                     angular.forEach(vm.moduleNameList, function(item){
//                         if(item.subCode == '0502'){
//                             vm.moduleList.push(item);
//                         }
//                     })
//                   }
//                   else if (vm.screenModule == 'ICEGATE'){
//                                        angular.forEach(vm.moduleNameList, function(item){
//                                            if(item.subCode == '0011'){
//                                                vm.moduleList.push(item);
//                                            }
//                                        })
//                                      }
//                   else if (vm.screenModule == 'RBI Bond') {
//                                      angular.forEach(vm.moduleNameList, function(item){
//                                      vm.moduleList.push(item);
////                                          if(item.subCode == '0012'){
////                                              vm.moduleList.push(item);
////                                          }
//                                      })
//                                    }
//         /* angular.forEach(vm.moduleNameList, function(item){
//                if(item.subCode == "ALL"){
//                    var i = vm.moduleNameList.indexOf(item);
//                    if(i != -1) {
//                    	vm.moduleNameList.splice(i, 1);
//                    }
//                }
//                return vm.moduleNameList;
//          })*/
//        },function(error) {
//           handleErrorResponse(error);
//         });
//    }
//    Get Level Name
    function getLevelName() {

           if(vm.screenModule == 'RBI Bond'){
            commonService.getRbiBondMetaDataList('HIERARCHY_LEVELS',
                 (result) => {
                   vm.levelNameList = result;
                   //console.log(vm.pensionerStatusList);
                   },
                 function(error) {
                   handleErrorResponse(error);
                 }
              );
              }
       else {
       commonService.getCBDTMetaDataList('HIERARCHY_LEVELS',
                 (result) => {
                   vm.levelNameList = result;
                   //console.log(vm.pensionerStatusList);
                   },
                 function(error) {
                   handleErrorResponse(error);
                 }
              );
         }
    }
    getLevelName();
    function getLevelRow(countRow) {

        if(countRow && countRow <= 5 && countRow>=1)
        {
            vm.levelCutOffErrMsg='';
            vm.heirarchyLevel = [];
            for(var i=1; i<=countRow; i++){
                vm.heirarchyLevel.push({});
            }
            return vm.heirarchyLevel;
        }else{
          vm.levelCutOffErrMsg = 'No of Levels should not be less than 1 or greater than 5';
          vm.levelMaster.level='';
          vm.heirarchyLevel = '';
        }

    }

    function setParentLevel(index){
        if(index == 0){
            for (var i=0; i<=vm.heirarchyLevel.length; i++){
                vm.heirarchyLevel[0].parentLevelName = 'None';
                vm.heirarchyLevel[0].parentLevelId = null;
                if(vm.heirarchyLevel[index+1].parentLevelName || vm.heirarchyLevel[index+1].parentLevelName == undefined){
                    vm.heirarchyLevel[index+1].parentLevelName = vm.heirarchyLevel[index].levelName;
                }
            }
        }else{
            for (var i=index; i<vm.heirarchyLevel.length; i++){
                 if(index != vm.heirarchyLevel.length-1){
                    if(vm.heirarchyLevel[index+1].parentLevelName || vm.heirarchyLevel[index+1].parentLevelName == undefined){
                        vm.heirarchyLevel[index+1].parentLevelName = vm.heirarchyLevel[index].levelName;
                    }else {
                        vm.heirarchyLevel[index].parentLevelName = vm.heirarchyLevel[index-1].levelName;
                    }
                 }else {
                    vm.heirarchyLevel[index].parentLevelName = vm.heirarchyLevel[index-1].levelName;
                 }
            }
        }
    }

    function addErrorMsg() {






                    //vm.showField='show';
                    vm.loader = true;
                     vm.heirarchyLevel1 = vm.heirarchyLevel.map(function(item) {
                     var cleanedItem = Object.assign({}, item);  // Shallow copy the item
                     delete cleanedItem.$$hashKey;  // Delete the $$hashKey property
                     return cleanedItem;
                   });
                    var postData = {
            //            'trCode' : vm.levelMaster.treasury,
                        'levelMasterSubDtoList' : vm.heirarchyLevel1,

                    }
                    rbiBondLevelMasterService.addLevelMaster(postData).then(function(response)
                    {
                        vm.loader = false;
                        vm.displayMsg = response.data.message;
                        vm.showField='show';
                    },function(error) {
                         handleErrorResponse(error);
                         vm.loader = false;
                         vm.showField = 'hide';

                         $state.go('rbi_bond_level_master.inquiry');
                       });




        }

//    function getschemeName() {
//                    vm.loader = true;
//                    vm.searchschemeName = '';
//                    var requestData = {
//
//                    }
//                    stMpDateWiseSummaryReportService.getDetails(requestData).then(function(response) {
//                        vm.loader = false;
//                        vm.schemeNameList = response.data.result.branch;
//                        //vm.branchIdList = response.data.result.mpStDateWiseSummaryDTO2;
//                        angular.element('#schemeNameSearcherPopUp').modal('toggle');
//                    }, function(error) {
//                        vm.loader = false;
//                        handleErrorResponse(error);
//                    });
//
//    }
    function addLevelMaster(value) {


        if(value == 'finalSubmit'){
//                    Swal.fire({
//                        title : "Are you sure?",
//                        text : "Do you want to Really Add this record?",
//                        type : "warning",
//                        showCancelButton : true,
//                        cancelButtonClass : "btn-cancel",
//                        confirmButtonClass : "btn-primary",
//                        confirmButtonText : "Yes",
//                        closeOnConfirm : false
//                    },() => {
//                       vm.loader = true;
//                        vm.heirarchyLevel1 = vm.heirarchyLevel.map(function(item) {
//                                            var cleanedItem = Object.assign({}, item);  // Shallow copy the item
//                                            delete cleanedItem.$$hashKey;  // Delete the $$hashKey property
//                                            return cleanedItem;
//                                          });
//                    var postData = {
//            //            'trCode' : vm.levelMaster.treasury,
//                        'levelMasterSubDtoList' : vm.heirarchyLevel1,
//
//                    }
//                    rbiBondLevelMasterService.addLevelMaster(postData).then(function(response)
//                    {
//                        vm.loader=false;
//                        Swal.fire("Added!","Added Successfully","success");
//                        $state.go('rbi_bond_level_master.inquiry');
//
//                    }, function(error){
//                          vm.loader = false;
//                          handleErrorResponse(error);
//                          $state.go('rbi_bond_level_master.inquiry');
//                      });
//                   }) ;



Swal.fire({
    title: "Are you sure?",
    text: "Do you really want to add this record?",
    icon: "warning", // use 'icon' instead of 'type'
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

        vm.heirarchyLevel1 = vm.heirarchyLevel.map(function(item) {
            var cleanedItem = Object.assign({}, item); // Shallow copy the item
            delete cleanedItem.$$hashKey; // Remove Angular's internal key
            return cleanedItem;
        });

        var postData = {
            // 'trCode': vm.levelMaster.treasury,
            'levelMasterSubDtoList': vm.heirarchyLevel1
        };

        rbiBondLevelMasterService.addLevelMaster(postData).then(function(response) {
            vm.loader = false;
            Swal.fire("Added!", "Added Successfully", "success");
            $state.go('rbi_bond_level_master.inquiry');
        }, function(error) {
            vm.loader = false;
            handleErrorResponse(error);
            $state.go('rbi_bond_level_master.inquiry');
        });
    }
});

        }







    }


    function addValidationLevelMaster() {

                    //vm.showField='show';
                    vm.loader = true;
                    var postData = {
            //            'trCode' : vm.levelMaster.treasury,
//                        'levelMasterSubDtoList' : vm.heirarchyLevel,

                    }
                    rbiBondLevelMasterService.addValidation(postData).then(function(response)
                    {
                        vm.loader = false;
                        vm.displayMsg = response.data.message;
                        vm.showField='show';
                    },function(error) {
                         handleErrorResponse(error);
                         vm.loader = false;
                         vm.showField = 'hide';

                         $state.go('rbi_bond_level_master.inquiry');
                       });


        }
    //get unverified records
    function getUnVerifyRecords() {


       vm.loader = true;
       var requestData = {

       }
       rbiBondLevelMasterService.getUnVerifyRecords(requestData).then(function(response){
          vm.loader=false;
          vm.readable = false;
          vm.levelMasterDetails = response.data.result;
          vm.heirarchyLevel = response.data.result.levelMasterSubDtoList;
        },function(error){
           vm.loader = false;
           vm.moduleId="";
           handleErrorResponse(error);
           vm.levelMasterDetails = '';
           vm.heirarchyLevel = '';
           $state.go('rbi_bond_level_master.inquiry');
       });



    }

     function verifySubmit() {
//        Swal.fire({
//            title : "Are you sure?",
//            text : "Do you want to Really Verify this record?",
//            type : "warning",
//            showCancelButton : true,
//            cancelButtonClass : "btn-cancel",
//            confirmButtonClass : "btn-primary",
//            confirmButtonText : "Yes",
//            closeOnConfirm : false
//        },() => {

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
        vm.loader= true;
        var postData = {

        };
        rbiBondLevelMasterService.verifyDetailsSubmit(postData).then(function(response)
        {
            vm.loader=false;
            Swal.fire("Verified!","Verified Successfully","success");
                 $state.go('rbi_bond_level_master.inquiry');



        }, function(error){
              vm.loader = false;
              handleErrorResponse(error);

              $state.go('rbi_bond_level_master.inquiry');
          });
          }
       }) ;
    }

  vm.rejectReason=function () {
      angular.element("#permissionMappingDelete").modal("toggle");
    }

vm.getRemarksList= function () {
                               vm.referenceId = '055';
                               var requestData = { 'referenceId': vm.referenceId };

                               rbiBondLevelMasterService.getRemarksList(requestData).then(function(response) {
                                   vm.remarksList = response.data.result || []; // Ensure it's an array
                               }, function(error) {
                                   handleErrorResponse(error);
                               });
                           }

function deleteSubmit() {

                                   var requestData = {
                                       remarks: vm.remarks
                                   };

                                   // Call service to delete and handle response
                                   rbiBondLevelMasterService.deleteDetailsSubmit(requestData)
                                       .then(function(response) {
                                           if (response.data.message === 'SUCCESS') {
                                               Swal.fire("Rejected!", "Rejected Successfully", "success");
                                               $state.go('rbi_bond_level_master.inquiry');
                                           } else {
                                               vm.loader = false;
                                               Swal.fire("Warning!", response.data.message, "error");
                                           }
                                       })
                                       .catch(function(error) {
                                           handleErrorResponse(error);
                                       });

                           }

//function deleteSubmit() {
//    function handleKeyPress(event) {
//        var key = event.key;
//        var input = event.target.value;
//
//        // Prevent entering more than one consecutive space
//        if (key === ' ' && input.slice(-1) === ' ') {
//            event.preventDefault();
//        }
//
//        // Allow only alphabetic characters, single spaces, and backspace
//        if (!key.match(/^[a-zA-Z\s]$/) && key !== 'Backspace' && key!=='Enter') {
//            event.preventDefault();
//        }
//    }
//
//    document.addEventListener('keypress', handleKeyPress);
//
//    Swal.fire({
//        title: "Rejection Remarks",
//        text: "",
//        type: "input",
//        inputPlaceholder: "Enter your remarks here",
//        showCancelButton: true,
//        cancelButtonClass: "btn-cancel",
//        confirmButtonClass: "btn-primary",
//        confirmButtonText: "Submit",
//        animation: "slide-from-top",
//        closeOnConfirm: false,
//        inputValidator: function(value) {
//            // Validate input
//            if (/^[a-zA-Z\s]*$/.test(value.trim())) {
//                // Additional check for consecutive spaces
//                if (/\s\s/.test(value)) {
//                    return "Please enter only single spaces between words!";
//                }
//                document.removeEventListener('keypress', handleKeyPress);
//                return true;
//            } else {
//                return "Please enter only alphabetic characters!";
//            }
//        }
//    }, function(inputValue) {
//        // Handle user input
//        if (inputValue === false) {
//            document.removeEventListener('keypress', handleKeyPress);
//            return false;
//        }
//
//        if (inputValue === "") {
//            Swal.fire.showInputError("Please enter the remarks!");
//            return false;
//        }
//
//        vm.remarks = inputValue;
//
//        var requestData = {
//            remarks: vm.remarks
//        };
//
//        // Call service to delete and handle response
//        rbiBondLevelMasterService.deleteDetailsSubmit(requestData)
//            .then(function(response) {
//                if (response.data.message === 'SUCCESS') {
//                    Swal.fire("Rejected!", "Rejected Successfully", "success");
//                    $state.go('rbi_bond_level_master.inquiry');
//                } else {
//                    vm.loader = false;
//                    Swal.fire("Warning!", response.data.message, "error");
//                }
//            })
//            .catch(function(error) {
//                handleErrorResponse(error);
//            });
//    });
//}

    //get Inquiry records
    function getInquiryDetails() {


                       vm.loader = true;
                         var postData = {

                       };
                       rbiBondLevelMasterService.getInquiryRecords(postData).then(function(response){
                          vm.loader=false;
                          vm.readable = false;
                          vm.heirarchyLevel = response.data.result.levelMasterSubDtoList;
                          vm.levelMasterDetails = response.data.result;
                        },function(error){
                           vm.loader = false;
                           vm.moduleId="";
                           handleErrorResponse(error);
                            $state.go('rbi_bond_level_master.inquiry');
                           vm.levelMasterDetails = '';
                           vm.heirarchyLevel = '';
                       });

    }


    // Cancel Confirmation
    function showCancelConfirmation(state) {
        Swal.fire({
            title : "Confirm Cancel",
            text : "Do you really want to cancel?",
            type : "warning",
            showCancelButton : true,
            cancelButtonClass : "btn-cancel",
            confirmButtonClass : "btn-danger",
            confirmButtonText : "Yes",
            cancelButtonText : "No",
            closeOnConfirm : true
        }, function() {
            $state.go(state);

        });
    }

        vm.makeOptionsInVisible = function(indexInHierarchy) {
         for(let i=0;i<vm.heirarchyLevel.length;i++) {
            if(indexInHierarchy==i)
                 continue;

            for(let item of vm.levelNameList) {
               if(vm.heirarchyLevel[i].levelName != item.label && vm.heirarchyLevel[indexInHierarchy].levelName == item.label) {
                   angular.element("#" + item.value + i).hide();
               } else {
                   let flag  = true;
                   for(let levelItem of vm.heirarchyLevel) {
                     if(item.label == levelItem.levelName) {
                       flag = false;
                       break;
                     }
                   }

                   if(flag)
                        angular.element("#" + item.value + i).show();
               }
            }
         }
    }

      /* vm.makeOptionsInVisible1 = function() {
             for(let i=0;i<vm.heirarchyLevel.length;i++) {

                for(let item of vm.levelNameList) {
                   if(vm.heirarchyLevel[i].levelName == item.label) {
                       angular.element("#" + item.value + i).hide();
                   }
                }
             }
        }*/

        vm.makeOptionsInVisible1 = function() {

        setTimeout(function(){
               for(let i=0;i<vm.heirarchyLevel.length;i++) {

                   for(let item of vm.levelNameList) {
                         let flag  = false;
                         for(let levelItem of vm.heirarchyLevel) {
                           if(item.label == levelItem.levelName) {
                            angular.element("#" + item.value + i).hide();
                            flag = true
                             break;
                           }
                         }

                         if(flag)
                              angular.element("#" + item.value + i).hide();

                   }
                }
        },1000);

       }

       function sleep(ms) {
         return new Promise(resolve => setTimeout(resolve, ms));
       }

 }

})();
