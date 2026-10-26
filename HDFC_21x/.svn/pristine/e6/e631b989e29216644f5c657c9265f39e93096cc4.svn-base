(function() {

	'use strict';

	angular.module('gbmUltraApp').controller('levelMasterController',
levelMasterController);



	levelMasterController.$inject = [ '$scope', '$filter', '$state',
			'$stateParams', '$location',  'commonService','commonLevelMasterService'];


	function levelMasterController($scope, $filter, $state, $stateParams,
			$location,commonService,commonLevelMasterService) {

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

    vm.getModuleName = getModuleName;
    vm.getLevelName = getLevelName;
    vm.getUnVerifyRecords = getUnVerifyRecords;
    vm.getInquiryDetails = getInquiryDetails;
    vm.addErrorMsg = addErrorMsg;
    vm.showField='hide';

    if($state.current.name == 'cbdt_level_master.add' || $state.current.name == 'cbdt_level_master.inquiry' || $state.current.name == 'cbdt_level_master.delete' || $state.current.name == 'cbdt_level_master.verify'){
        vm.screenModule = 'CBDT';
    } else if ($state.current.name == 'ppf_level_master.add' || $state.current.name == 'ppf_level_master.inquiry' || $state.current.name == 'ppf_level_master.delete' || $state.current.name == 'ppf_level_master.verify'){
          vm.screenModule = 'PPF';
    } else if ($state.current.name == 'ssa_level_master.add' || $state.current.name == 'ssa_level_master.inquiry' || $state.current.name == 'ssa_level_master.delete' || $state.current.name == 'ssa_level_master.verify'){
        vm.screenModule = 'SSA';
    }else if ($state.current.name == 'scss_level_master.add' || $state.current.name == 'scss_level_master.inquiry' || $state.current.name == 'scss_level_master.delete' || $state.current.name == 'scss_level_master.verify'){
        vm.screenModule = 'SCSS';
    }else if ($state.current.name == 'kvp_level_master.add' || $state.current.name == 'kvp_level_master.inquiry' || $state.current.name == 'kvp_level_master.delete' || $state.current.name == 'kvp_level_master.verify'){
        vm.screenModule = 'KVP';
    }
    else if ($state.current.name == 'icegate_level_master.add' || $state.current.name == 'icegate_level_master.inquiry' || $state.current.name == 'icegate_level_master.delete' || $state.current.name == 'icegate_level_master.verify'){
            vm.showField='show';
            vm.screenModule = 'ICEGATE';
            vm.moduleId='0011';
            vm.displayMsg='SUCCESS'
    }

    vm.goToPreviousState = function(){
        if(vm.screenModule == 'CBDT'){
            if($state.current.name == 'cbdt_level_master.add' || $state.current.name == 'cbdt_level_master.verify' || $state.current.name == 'cbdt_level_master.delete'){
                $state.go('cbdt_level_master.inquiry')
            }else if($state.current.name == 'cbdt_level_master.inquiry'){
                $state.go('dc_cbdt_menu')
            }
        } else if(vm.screenModule == 'PPF'){
              if($state.current.name == 'ppf_level_master.add' || $state.current.name == 'ppf_level_master.verify' || $state.current.name == 'ppf_level_master.delete'){
                  $state.go('ppf_level_master.inquiry')
              }else if($state.current.name == 'ppf_level_master.inquiry'){
                  $state.go('dc_ppf_menu')
              }
          }else if(vm.screenModule == 'SSA'){
             if($state.current.name == 'ssa_level_master.add' || $state.current.name == 'ssa_level_master.verify' || $state.current.name == 'ssa_level_master.delete'){
                 $state.go('ssa_level_master.inquiry')
             }else if($state.current.name == 'ssa_level_master.inquiry'){
                 $state.go('dc_ssa_menu')
             }
         }else if(vm.screenModule == 'SCSS'){
               if($state.current.name == 'scss_level_master.add' || $state.current.name == 'scss_level_master.verify' || $state.current.name == 'scss_level_master.delete'){
                   $state.go('scss_level_master.inquiry')
               }else if($state.current.name == 'scss_level_master.inquiry'){
                   $state.go('dc_scss_menu')
               }
           }else if(vm.screenModule == 'KVP'){
               if($state.current.name == 'kvp_level_master.add' || $state.current.name == 'kvp_level_master.verify' || $state.current.name == 'kvp_level_master.delete'){
                   $state.go('kvp_level_master.inquiry')
               }else if($state.current.name == 'kvp_level_master.inquiry'){
                   $state.go('dc_kvp_menu')
               }
           }
           else if(vm.screenModule == 'ICEGATE'){
               vm.showField='show';
               vm.moduleId='0011';
               vm.displayMsg='SUCCESS'
                         if($state.current.name == 'icegate_level_master.add' || $state.current.name == 'icegate_level_master.verify' || $state.current.name == 'icegate_level_master.delete'){
                             $state.go('icegate_level_master.inquiry')
                         }else if($state.current.name == 'icegate_level_master.inquiry'){
                             $state.go('dc_icegate_menu')
                         }
                     }
    }


    //Get Module Name
    function getModuleName() {
       vm.referenceId = 'MOD_FOR_INT';
       var requestData = {
             'referenceId': vm.referenceId
         }
       commonLevelMasterService.getModuleDetails(requestData).then(function(response){
          vm.moduleNameList = response.data.result;
          vm.moduleList = [];
          if(vm.screenModule == 'CBDT'){
            angular.forEach(vm.moduleNameList, function(item){
                if(item.subCode == '0400'){
                    vm.moduleList.push(item);
                }
            })
          } else if (vm.screenModule == 'PPF'){
                angular.forEach(vm.moduleNameList, function(item){
                    if(item.subCode == '0900'){
                        vm.moduleList.push(item);
                    }
                })
              }
              else if (vm.screenModule == 'SSA'){
                  angular.forEach(vm.moduleNameList, function(item){
                      if(item.subCode == '0600'){
                          vm.moduleList.push(item);
                      }
                  })
                }else if (vm.screenModule == 'SCSS'){
                   angular.forEach(vm.moduleNameList, function(item){
                       if(item.subCode == '0501'){
                           vm.moduleList.push(item);
                       }
                   })
                 }else if (vm.screenModule == 'KVP'){
                     angular.forEach(vm.moduleNameList, function(item){
                         if(item.subCode == '0502'){
                             vm.moduleList.push(item);
                         }
                     })
                   }
                   else if (vm.screenModule == 'ICEGATE'){
                                        angular.forEach(vm.moduleNameList, function(item){
                                            if(item.subCode == '0011'){
                                                vm.moduleList.push(item);
                                            }
                                        })
                                      }
         /* angular.forEach(vm.moduleNameList, function(item){
                if(item.subCode == "ALL"){
                    var i = vm.moduleNameList.indexOf(item);
                    if(i != -1) {
                    	vm.moduleNameList.splice(i, 1);
                    }
                }
                return vm.moduleNameList;
          })*/
        },function(error) {
           handleErrorResponse(error);
         });
    }
//    Get Level Name
    function getLevelName(){
    if(vm.screenModule == 'ICEGATE'){
     commonService.getICEGATEMetaDataList('HIERARCHY_LEVELS',
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
            var postData = {
    //            'trCode' : vm.levelMaster.treasury,
                'levelMasterSubDtoList' : vm.heirarchyLevel,
                'schemeCode' : vm.moduleId
            }
            commonLevelMasterService.addLevelMaster(postData).then(function(response)
            {
                vm.loader = false;
                vm.displayMsg = response.data.message;
                vm.showField='show';
            },function(error) {
                 handleErrorResponse(error);
                 vm.loader = false;
                 vm.showField = 'hide';
                 vm.moduleId ='';
                 if(vm.screenModule == 'ICEGATE'){
                                          $state.go('icegate_level_master.inquiry')
                                        }else if(vm.screenModule == 'SCSS'){
                                         $state.go('scss_level_master.inquiry');
                                        }
                                        else{
                                           $state.go('common_level_master.inquiry');
                                        }
               });
        }
    function addLevelMaster() {
        Swal.fire({
            title : "Are you sure?",
            text : "Do you want to Really Add this record?",
            type : "warning",
            showCancelButton : true,
            cancelButtonClass : "btn-cancel",
            confirmButtonClass : "btn-primary",
            confirmButtonText : "Yes",
            closeOnConfirm : false
        },() => {
           vm.loader = true;
        var postData = {
//            'trCode' : vm.levelMaster.treasury,
            'levelMasterSubDtoList' : vm.heirarchyLevel,
            'schemeCode' : vm.moduleId
        }
        commonLevelMasterService.addLevelMaster(postData).then(function(response)
        {
            vm.loader=false;
            Swal.fire("Added!","Level Master saved successfully","success");
            if(vm.screenModule == 'ICEGATE'){
              $state.go('icegate_level_master.inquiry')
            }else if(vm.screenModule == 'SCSS'){
                                                      $state.go('scss_level_master.inquiry');
                                                     }else{
               $state.go('common_level_master.inquiry');
            }
        }, function(error){
              vm.loader = false;
              handleErrorResponse(error);
              if(vm.screenModule == 'ICEGATE'){
                                       $state.go('icegate_level_master.inquiry')
                                     }else if(vm.screenModule == 'SCSS'){
                                                                               $state.go('scss_level_master.inquiry');
                                                                              }else{
                                        $state.go('common_level_master.inquiry');
                                     }
          });
       }) ;
    }
    //get unverified records
    function getUnVerifyRecords() {
       vm.loader = true;
       var requestData = {
            'schemeCode' : vm.moduleId
       }
       commonLevelMasterService.getUnVerifyRecords(requestData).then(function(response){
          vm.loader=false;
          vm.levelMasterDetails = response.data.result;
          vm.heirarchyLevel = response.data.result.levelMasterSubDtoList;
        },function(error){
           vm.loader = false;
           handleErrorResponse(error);
           vm.moduleId = '';
           vm.levelMasterDetails = '';
           vm.heirarchyLevel = '';
           if(vm.screenModule == 'ICEGATE'){
                                    $state.go('icegate_level_master.inquiry')
                                  }else if(vm.screenModule == 'SCSS'){
                                                                            $state.go('scss_level_master.inquiry');
                                                                           }else{
                                     $state.go('common_level_master.inquiry');
                                  }
       });
    }

     function verifySubmit() {
        Swal.fire({
            title : "Are you sure?",
            text : "Do you want to Really Verify this record?",
            type : "warning",
            showCancelButton : true,
            cancelButtonClass : "btn-cancel",
            confirmButtonClass : "btn-primary",
            confirmButtonText : "Yes",
            closeOnConfirm : false
        },() => {
        vm.loader= true;
        var postData = {
            'schemeCode' : vm.moduleId
        };
        commonLevelMasterService.verifyDetailsSubmit(postData).then(function(response)
        {
            vm.loader=false;
            Swal.fire("Verified!","Level Master verified successfully","success");
           if(vm.screenModule == 'ICEGATE'){
                         //$state.go('icegate_level_master.inquiry');
                         $state.go('dc_icegate_menu');
                       }else if(vm.screenModule == 'SCSS'){
                                                                 $state.go('scss_level_master.inquiry');
                                                                }else{
                          $state.go('common_level_master.inquiry');
                       }
        }, function(error){
              vm.loader = false;
              handleErrorResponse(error);
              vm.moduleId = '';
              if(vm.screenModule == 'ICEGATE'){
                                       $state.go('icegate_level_master.inquiry')
                                     }else if(vm.screenModule == 'SCSS'){
                                                                               $state.go('scss_level_master.inquiry');
                                                                              }else{
                                        $state.go('common_level_master.inquiry');
                                     }
          });
       }) ;
    }

     function deleteSubmit() {
        Swal.fire({
            title : "Are you sure?",
            text : "Do you want to Really Delete this record?",
            type : "warning",
            showCancelButton : true,
            cancelButtonClass : "btn-cancel",
            confirmButtonClass : "btn-primary",
            confirmButtonText : "Yes",
            closeOnConfirm : false
        },() => {
        vm.loader= true;
        var postData = {
            'schemeCode' : vm.moduleId
        };
        commonLevelMasterService.deleteDetailsSubmit(postData).then(function(response)
        {
            vm.loader=false;

            Swal.fire("Deleted!","Level Master deleted successfully","success");

           if(vm.screenModule == 'ICEGATE'){
               $state.go('dc_icegate_menu');
               //$state.go('icegate_level_master.inquiry');
                }else if(vm.screenModule == 'SCSS'){
                     $state.go('scss_level_master.inquiry');
                }
                else{
                 $state.go('common_level_master.inquiry');
                 }
        }, function(error){
              vm.loader = false;
              handleErrorResponse(error);
              vm.moduleId = '';
              if(vm.screenModule == 'ICEGATE'){
                                       $state.go('icegate_level_master.inquiry')
                                     }else if(vm.screenModule == 'SCSS'){
                                                                               $state.go('scss_level_master.inquiry');
                                                                              }else{
                                        $state.go('common_level_master.inquiry');
                                     }
          });
       }) ;
    }
    //get Inquiry records
    function getInquiryDetails() {
       vm.loader = true;
         var postData = {
           'schemeCode' : vm.moduleId
       };
       commonLevelMasterService.getInquiryRecords(postData).then(function(response){
          vm.loader=false;
          vm.heirarchyLevel = response.data.result.levelMasterSubDtoList;
          vm.levelMasterDetails = response.data.result;
        },function(error){
           vm.loader = false;
           handleErrorResponse(error);
           vm.moduleId = '';
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

 }

})();
