(function() {

	'use strict';

	angular.module('gbmUltraApp').controller('scrollNumberMaintenanceController',
			scrollNumberMaintenanceController);



	scrollNumberMaintenanceController.$inject = [ '$scope', '$filter', '$state',
			'$stateParams', '$location', 'scrollNumberMaintenanceService', 'commonService','$rootScope','$timeout' ];

	function scrollNumberMaintenanceController($scope, $filter, $state, $stateParams,
			$location, scrollNumberMaintenanceService, commonService, $rootScope,$timeout) {
		var vm = this;
		vm.scrollNumberDetails = {};
		vm.showCancelConfirmation= showCancelConfirmation;
        vm.addScrollNumber = addScrollNumber;
        vm.modifyScrollNumber = modifyScrollNumber;
        vm.modify = modify;
        vm.verifyScrollNumber = verifyScrollNumber;
        vm.verify = verify;
        vm.inquiry = inquiry;



		vm.getAddFrequency                                        = getAddFrequency;

		vm.getVerifyActiveLevelname                                = getVerifyActiveLevelname;
		vm.getVerifyActiveBranch                                   = getVerifyActiveBranch;
		vm.getVerifyActiveScrollname                               = getVerifyActiveScrollname;

		vm.getEnquiryActiveLevelname                               = getEnquiryActiveLevelname;
		vm.getEnquiryActiveBranch                                  = getEnquiryActiveBranch;
		vm.getEnquiryActiveScrollname                              = getEnquiryActiveScrollname;
		vm.getActiveScrollname                                      = getActiveScrollname;
		vm.scrollNumberLimit                                        = scrollNumberLimit;
		vm.modifyScrollNumberLimit                                  = modifyScrollNumberLimit;
		vm.getModifyDetailsAndScrollNumber                          = getModifyDetailsAndScrollNumber;
		vm.getVerifyDetailsAndScrollNumber                          = getVerifyDetailsAndScrollNumber;
		vm.getEnquiryDetailsAndScrollNumber                         = getEnquiryDetailsAndScrollNumber;


		vm.modifyScrollNumberMaster = modifyScrollNumberMaster;

		vm.add = add;

		vm.changeLevel = changeLevel;
		vm.changeBranch = changeBranch;

        const LEVEL_TABLE_NAME = "T073";
        const LEVEL_MASTER = "T007";
        const SCROLL_NAME = "T044";


		vm.commonYesNoMetaData = COMMON_YES_NO_METADATA;
		vm.solDate = $rootScope.globals.currentUser.solDate;
        vm.systemDate = $rootScope.globals.currentUser.systemDate;
       if ($state.current.name == 'scroll_number_maintenance.verify'){
                //verifyScrollNumber('verifySelect');
       }else if ($state.current.name == 'scroll_number_maintenance.inquiry') {
                 // inquiry('inquiryTorch');
       }else if ($state.current.name == 'scroll_number_maintenance.modify') {
               //   modifyScrollNumber('modifySelect');
               }



			// Cancel Confirmation
             function showCancelConfirmation(state) {
      //            Swal.fire({
      //                title : "Confirm Cancel",
      //                text : "Do you really want to cancel?",
      //                type : "warning",
      //                showCancelButton : true,
      //                cancelButtonClass : "btn-cancel",
      //                confirmButtonClass : "btn-danger",
      //                confirmButtonText : "Yes",
      //                cancelButtonText : "No",
      //                closeOnConfirm : true
      //            }, function() {
      Swal.fire({
                                     title : "Confirm Cancel",
                                    text : "Do you really want to cancel?",
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

                }  });
              }

       function addScrollNumber(flag) {
            vm.loader = true;
            let requestData ={};
            requestData.stateTaxCode=vm.stateTaxCode;
            requestData.stateTaxName=vm.stateTaxName;
            requestData.levelId=vm.levelId;
            requestData.levelName=vm.levelName;
            requestData.branchId=vm.branchId;
            requestData.branchName=vm.branchName;
            requestData.scrollCode=vm.scrollCode;
            requestData.scrollName=vm.scrollName;
            requestData.majorHeadCode=vm.majorHeadCode;
            requestData.majorHeadName=vm.majorHeadName;
            requestData.frequency=vm.frequency;
            requestData.startSerialNo=vm.nextScrollNumber;
          scrollNumberMaintenanceService.insertScrollNumber(requestData).then(function(response){
             vm.loader=false;
            if(flag == 'stateTaxCode'){
              vm.stateCodeList = response.data.result.stateTaxIdTorch;
              angular.element('#stateTaxModel').modal('toggle');
            }
            else if(flag == 'levelId'){
              vm.levelIdList = response.data.result.levelIdTorch;
            }
            if(flag == 'branchId'){
              vm.branchIdList = response.data.result.branchIdTorch;
              angular.element('#searchLevelBranchModal').modal('toggle');
            }
            else if(flag == 'scrollName'){
              vm.scrollNameList = response.data.result.scrollNameTorch;
              angular.element('#scrollNameModel').modal('toggle');
            }
            else if(flag == 'majorHeadCode'){
              vm.majorHeadCodeList = response.data.result.majorHeadCodeTorch;
              angular.element('#majorHeadCodeModel').modal('toggle');
            }
            else if(flag == 'frequency'){
            vm.frequency = response.data.result.frequencyLabel;
            }
          } ,function(error) {
              handleErrorResponse(error);
              location.href = "#!/scroll_number_maintenance/action/inquiry"
              vm.loader = false;
            });

       }
       function add() {
//            Swal.fire({
//              title : "Are you sure?",
//              text : "Do you want to Really Add this record?",
//              type : "warning",
//              showCancelButton : true,
//              cancelButtonClass : "btn-cancel",
//              confirmButtonClass : "btn-primary",
//              confirmButtonText : "Yes",
//              closeOnConfirm : false
//              }
//            ,() => {




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
            vm.loader=true
            let requestData ={};
              requestData.stateTaxCode=vm.stateTaxCode;
              requestData.stateTaxName=vm.stateTaxName;
              requestData.levelId=vm.levelId;
              requestData.levelName=vm.levelName;
              requestData.branchId=vm.branchId;
              requestData.branchName=vm.branchName;
              requestData.scrollCode=vm.scrollCode;
              requestData.scrollName=vm.scrollName;
              requestData.majorHeadCode=vm.majorHeadCode;
              requestData.majorHeadName=vm.majorHeadName;
              requestData.frequency=vm.frequencyCode;
              requestData.nextScrollNumber=vm.nextScrollNumber;
            scrollNumberMaintenanceService.insertScrollNumber(requestData).then(
              function (response) {
                if(response.data.message = 'SUCCESS') {
                      Swal.fire("Added!","Data saved successfully","success");
                      location.href = "#!/scroll_number_maintenance/action/inquiry"
                      vm.loader=false;
                      }

              },function(error) {
                handleErrorResponse(error);
                vm.loader = false;
              }
            );
         }   })
       }
       function inquiry(flag) {
              vm.loader = true;
              let requestData ={};
              if(flag == 'inquiryTorch'){
              id='';
              }else if(flag == 'inquiryDetail'){
              var id = vm.id;
              }
            requestData = {
				'id': id
			}
            scrollNumberMaintenanceService.inquiry(requestData).then(function(response){
               vm.loader=false;
               vm.inquirySelect = response.data.result;
                if(flag == 'inquiryDetail'){
                   requestData.stateTaxName = vm.stateTaxName;
                   requestData.stateTaxCode = vm.stateTaxCode;
          scrollNumberMaintenanceService.insertScrollNumber(requestData).then(function(response){
                 vm.levelIdList = response.data.result.levelIdTorch;
                 angular.forEach(vm.levelIdList, function(item){
                        if(item.levelId == vm.levelId){
                           vm.levelName = item.levelName;
                        }
                   })
                   requestData.stateTaxName = vm.stateTaxName;
                   requestData.stateTaxCode = vm.stateTaxCode;
                   requestData.levelId = vm.levelId;
                   requestData.levelName = vm.levelName;
          scrollNumberMaintenanceService.insertScrollNumber(requestData).then(function(response){
                 vm.branchIdList = response.data.result.branchIdTorch;
                 angular.forEach(vm.branchIdList, function(item){
                       if(item.branchId == vm.branchId){
                           vm.branchName = item.branchName;
                       }
                   })

                  requestData.stateTaxName = vm.stateTaxName;
                  requestData.stateTaxCode = vm.stateTaxCode;
                  requestData.levelId = vm.levelId;
                  requestData.levelName = vm.levelName;
                  requestData.branchId = vm.branchId;
                  requestData.branchName = vm.branchName;
         scrollNumberMaintenanceService.insertScrollNumber(requestData).then(function(response){
                vm.scrollNameList = response.data.result.scrollNameTorch;
                angular.forEach(vm.scrollNameList, function(item){
                      if(item.scrollCode == vm.scrollCode){
                          vm.scrollName = item.scrollName;
                      }
                  })
                  requestData.stateTaxName = vm.stateTaxName;
                  requestData.stateTaxCode = vm.stateTaxCode;
                  requestData.levelId = vm.levelId;
                  requestData.levelName = vm.levelName;
                  requestData.branchId = vm.branchId;
                  requestData.branchName = vm.branchName;
                  requestData.scrollCode = vm.scrollCode;
                  requestData.scrollName = vm.scrollName;
         scrollNumberMaintenanceService.insertScrollNumber(requestData).then(function(response){
                vm.majorHeadNameList = response.data.result.majorHeadCodeTorch;
                angular.forEach(vm.majorHeadNameList, function(item){
                      if(item.majorHeadCode == vm.majorHeadCode){
                          vm.majorHeadName = item.majorHeadDescription;
                      }
                  })

                   requestData.stateTaxName = vm.stateTaxName;
                   requestData.stateTaxCode = vm.stateTaxCode;
                   requestData.levelId = vm.levelId;
                   requestData.levelName = vm.levelName;
                   requestData.branchId = vm.branchId;
                   requestData.branchName = vm.branchName;
                   requestData.scrollCode = vm.scrollCode;
                   requestData.scrollName = vm.scrollName;
                   requestData.majorHeadCode = vm.majorHeadCode;
                   requestData.majorHeadName = vm.majorHeadName;
        scrollNumberMaintenanceService.insertScrollNumber(requestData).then(function(response){
                   vm.frequency = response.data.result.frequencyLabel;
                angular.forEach(vm.frequency, function(item){
                     if(item.frequencyCode == vm.frequencyCode){
                         vm.frequencyDescription = item.frequencyDescription;
                     }
                  })
                  },function(error) {
                   handleErrorResponse(error);
                   location.href = "#!/scroll_number_maintenance/action/inquiry"
                   vm.loader = false;
                  });
                  },function(error) {
                    handleErrorResponse(error);
                    location.href = "#!/scroll_number_maintenance/action/inquiry"
                    vm.loader = false;
                  });
                  },function(error) {
                    handleErrorResponse(error);
                    location.href = "#!/scroll_number_maintenance/action/inquiry"
                    vm.loader = false;
                  });


                  } ,function(error) {
                       handleErrorResponse(error);
                       location.href = "#!/scroll_number_maintenance/action/inquiry"
                       vm.loader = false;
                     });


                   } ,function(error) {
                    handleErrorResponse(error);
                    location.href = "#!/scroll_number_maintenance/action/inquiry"
                    vm.loader = false;
                  });

               }
               else if(flag = 'inquiryTorch'){
               scrollNumberMaintenanceService.insertScrollNumber(requestData).then(function(response){
                   vm.stateCodeList = response.data.result.stateTaxIdTorch;
                   vm.inquirySelectList = [];
                   angular.forEach(vm.stateCodeList, function(item2){
                   angular.forEach(vm.inquirySelect, function(item1){
                     if(item1.stateTaxCode == item2.stateTaxCode){
                           vm.inquirySelectAdd = [];
                           var obj = {id: item1.id,stateTaxCode: item1.stateTaxCode,stateTaxDescription: item2.stateTaxDescription,stateCode: item1.stateCode,
                           scrollCode: item1.scrollCode,levelId: item1.levelId,branchId: item1.branchId,
                           majorHeadCode: item1.majorHeadCode,frequencyCode: item1.frequency,nextScrollNumber: item1.nextScrollNumber};
                           vm.inquirySelectList.push(obj);
                     }
                   })
                   })
                   vm.loader = false;
                   $timeout(function(){
                   angular.element('#stateTaxModel').modal('toggle');
                   },1000)

                    } ,function(error) {
                       handleErrorResponse(error);
                       location.href = "#!/scroll_number_maintenance/action/inquiry"
                       vm.loader = false;
                     });

               }
            } ,function(error) {
                handleErrorResponse(error);
                location.href = "#!/scroll_number_maintenance/action/inquiry"
                vm.loader = false;
            });

              }
       function modifyScrollNumber(flag) {
               vm.loader = true;
               let requestData ={};
               if(flag == 'modifySelect')
               {
               requestData.id=vm.id;
               requestData.stateTaxCode=vm.stateTaxCode;
               requestData.stateTaxName=vm.stateTaxName;
               requestData.levelId=vm.levelId;
               requestData.levelName=vm.levelName;
               requestData.branchId=vm.branchId;
               requestData.branchName=vm.branchName;
               requestData.scrollCode=vm.scrollCode;
               requestData.scrollName=vm.scrollName;
               requestData.majorHeadCode=vm.majorHeadCode;
               requestData.majorHeadName=vm.majorHeadName;
               requestData.frequency=vm.frequency;
               requestData.startSerialNo=vm.nextScrollNumber;
               }
               else if(flag == 'modifyDetail'){
               requestData.id='';
               requestData.stateTaxCode='';
               requestData.stateTaxName='';
               requestData.levelId='';
               requestData.levelName='';
               requestData.branchId='';
               requestData.branchName='';
               requestData.scrollCode='';
               requestData.scrollName='';
               requestData.majorHeadCode='';
               requestData.majorHeadName='';
               requestData.frequency='';
               requestData.startSerialNo='';
               }else if(flag == 'branchId'){
               requestData.id=vm.id;
               requestData.stateTaxCode=vm.stateTaxCode;
               requestData.stateTaxName=vm.stateTaxName;
               requestData.levelId=vm.levelId;
               requestData.levelName=vm.levelName;
               }else if(flag == 'scrollName'){
                requestData.id=vm.id;
                requestData.stateTaxCode=vm.stateTaxCode;
                requestData.stateTaxName=vm.stateTaxName;
                requestData.levelId=vm.levelId;
                requestData.levelName=vm.levelName;
                requestData.branchId=vm.branchId;
                requestData.branchName=vm.branchName;
               }else if(flag == 'majorHeadCode'){
                requestData.id=vm.id;
                requestData.stateTaxCode=vm.stateTaxCode;
                requestData.stateTaxName=vm.stateTaxName;
                requestData.levelId=vm.levelId;
                requestData.levelName=vm.levelName;
                requestData.branchId=vm.branchId;
                requestData.branchName=vm.branchName;
                requestData.scrollCode=vm.scrollCode;
                requestData.scrollName=vm.scrollName;
               }else if(flag == 'frequency'){
                requestData.id=vm.id;
                requestData.stateTaxCode=vm.stateTaxCode;
                requestData.stateTaxName=vm.stateTaxName;
                requestData.levelId=vm.levelId;
                requestData.levelName=vm.levelName;
                requestData.branchId=vm.branchId;
                requestData.branchName=vm.branchName;
                requestData.scrollCode=vm.scrollCode;
                requestData.scrollName=vm.scrollName;
                requestData.majorHeadCode=vm.majorHeadCode;
                requestData.majorHeadName=vm.majorHeadName;
               }
             scrollNumberMaintenanceService.modifyScrollNumber(requestData).then(function(response){
                vm.loader=false;
                vm.modifySelect = response.data.result;
                if(flag == 'modifySelect'){
                scrollNumberMaintenanceService.insertScrollNumber(requestData).then(function(response){
                    vm.stateCodeList = response.data.result.stateTaxIdTorch;
                    vm.modifySelectList = [];
                    angular.forEach(vm.stateCodeList, function(item2){
                    angular.forEach(vm.modifySelect, function(item1){
                      if(item1.stateTaxCode == item2.stateTaxCode){
                            vm.modifySelectAdd = [];
                            var obj = {id: item1.id,stateTaxCode: item1.stateTaxCode,stateTaxDescription: item2.stateTaxDescription,stateCode: item1.stateCode,
                            scrollCode: item1.scrollCode,levelId: item1.levelId,branchId: item1.branchId,
                            majorHeadCode: item1.majorHeadCode,frequencyCode: item1.frequency,nextScrollNumber: item1.nextScrollNumber};
                            vm.modifySelectList.push(obj);
                      }
                    })
                    })
                    $timeout(function(){
                    angular.element('#stateTaxModel').modal('toggle');
                    },1000)

                     } ,function(error) {
                        handleErrorResponse(error);
                        location.href = "#!/scroll_number_maintenance/action/inquiry"
                        vm.loader = false;
                      });

                }

                else if(flag == 'modifyDetail'){
                    requestData.stateTaxName = vm.stateTaxName;
                    requestData.stateTaxCode = vm.stateTaxCode;
           scrollNumberMaintenanceService.insertScrollNumber(requestData).then(function(response){
                  vm.levelIdList = response.data.result.levelIdTorch;
                  angular.forEach(vm.levelIdList, function(item){
                         if(item.levelId == vm.levelId){
                            vm.levelName = item.levelName;
                         }
                    })
                    requestData.stateTaxName = vm.stateTaxName;
                    requestData.stateTaxCode = vm.stateTaxCode;
                    requestData.levelId = vm.levelId;
                    requestData.levelName = vm.levelName;
           scrollNumberMaintenanceService.insertScrollNumber(requestData).then(function(response){
                  vm.branchIdList = response.data.result.branchIdTorch;
                  angular.forEach(vm.branchIdList, function(item){
                        if(item.branchId == vm.branchId){
                            vm.branchName = item.branchName;
                        }
                    })

                   requestData.stateTaxName = vm.stateTaxName;
                   requestData.stateTaxCode = vm.stateTaxCode;
                   requestData.levelId = vm.levelId;
                   requestData.levelName = vm.levelName;
                   requestData.branchId = vm.branchId;
                   requestData.branchName = vm.branchName;
          scrollNumberMaintenanceService.insertScrollNumber(requestData).then(function(response){
                 vm.scrollNameList = response.data.result.scrollNameTorch;
                 angular.forEach(vm.scrollNameList, function(item){
                       if(item.scrollCode == vm.scrollCode){
                           vm.scrollName = item.scrollName;
                       }
                   })
                   requestData.stateTaxName = vm.stateTaxName;
                   requestData.stateTaxCode = vm.stateTaxCode;
                   requestData.levelId = vm.levelId;
                   requestData.levelName = vm.levelName;
                   requestData.branchId = vm.branchId;
                   requestData.branchName = vm.branchName;
                   requestData.scrollCode = vm.scrollCode;
                   requestData.scrollName = vm.scrollName;
          scrollNumberMaintenanceService.insertScrollNumber(requestData).then(function(response){
                 vm.majorHeadNameList = response.data.result.majorHeadCodeTorch;
                 angular.forEach(vm.majorHeadNameList, function(item){
                       if(item.majorHeadCode == vm.majorHeadCode){
                           vm.majorHeadName = item.majorHeadDescription;
                       }
                   })

                    requestData.stateTaxName = vm.stateTaxName;
                    requestData.stateTaxCode = vm.stateTaxCode;
                    requestData.levelId = vm.levelId;
                    requestData.levelName = vm.levelName;
                    requestData.branchId = vm.branchId;
                    requestData.branchName = vm.branchName;
                    requestData.scrollCode = vm.scrollCode;
                    requestData.scrollName = vm.scrollName;
                    requestData.majorHeadCode = vm.majorHeadCode;
                    requestData.majorHeadName = vm.majorHeadName;
         scrollNumberMaintenanceService.insertScrollNumber(requestData).then(function(response){
                    vm.frequency = response.data.result.frequencyLabel;
                 angular.forEach(vm.frequency, function(item){
                      if(item.frequencyCode == vm.frequencyCode){
                          vm.frequencyDescription = item.frequencyDescription;
                      }
                   })
                   },function(error) {
                    handleErrorResponse(error);
                    location.href = "#!/scroll_number_maintenance/action/inquiry"
                    vm.loader = false;
                   });
                   },function(error) {
                     handleErrorResponse(error);
                     location.href = "#!/scroll_number_maintenance/action/inquiry"
                     vm.loader = false;
                   });
                   },function(error) {
                     handleErrorResponse(error);
                     location.href = "#!/scroll_number_maintenance/action/inquiry"
                     vm.loader = false;
                   });


                   } ,function(error) {
                        handleErrorResponse(error);
                        location.href = "#!/scroll_number_maintenance/action/inquiry"
                        vm.loader = false;
                      });


                    } ,function(error) {
                     handleErrorResponse(error);
                     location.href = "#!/scroll_number_maintenance/action/inquiry"
                     vm.loader = false;
                   });

                }
               else if(flag == 'stateTaxCode'){
               vm.stateCodeList = response.data.result.stateTaxIdTorch;
                 angular.element('#stateTaxModel').modal('toggle');
               }
               else if(flag == 'levelId'){
               vm.levelIdList = response.data.result.levelIdTorch;
               }
               else if(flag == 'branchId'){
               vm.branchIdList = response.data.result.branchIdTorch;
                 angular.element('#searchLevelBranchModal').modal('toggle');
               }
               else if(flag == 'scrollName'){
               vm.scrollNameList = response.data.result.scrollNameTorch;
                 angular.element('#scrollNameModel').modal('toggle');
               }
               else if(flag == 'majorHeadCode'){
               vm.majorHeadCodeList = response.data.result.majorHeadCodeTorch;
                angular.element('#majorHeadCodeModel').modal('toggle');
               }
               else if(flag == 'frequency'){
               vm.frequency = response.data.result.frequencyLabel;
               }
             } ,function(error) {
                 handleErrorResponse(error);
                 location.href = "#!/scroll_number_maintenance/action/inquiry"
                 vm.loader = false;
             });

       }
       function modify() {
              Swal.fire({
                title : "Are you sure?",
                text : "Do you want to Really Modify this record?",
                type : "warning",
                showCancelButton : true,
                cancelButtonClass : "btn-cancel",
                confirmButtonClass : "btn-primary",
                confirmButtonText : "Yes",
                closeOnConfirm : false
                }
              ,() => {
              vm.loader=true
              let requestData ={};
                requestData.id=vm.id;
                requestData.stateTaxCode=vm.stateTaxCode;
                requestData.stateTaxName=vm.stateTaxName;
                requestData.levelId=vm.levelId;
                requestData.levelName=vm.levelName;
                requestData.branchId=vm.branchId;
                requestData.branchName=vm.branchName;
                requestData.scrollCode=vm.scrollCode;
                requestData.scrollName=vm.scrollName;
                requestData.majorHeadCode=vm.majorHeadCode;
                requestData.majorHeadName=vm.majorHeadName;
                requestData.frequency=vm.frequencyCode;
                requestData.nextScrollNumber=vm.nextScrollNumber;
              scrollNumberMaintenanceService.modifyScrollNumber(requestData).then(
                function (response) {
                  if(response.data.message = 'SUCCESS') {
                        Swal.fire("Modified!","Data modified successfully","success");
                        location.href = "#!/scroll_number_maintenance/action/inquiry"
                        vm.loader=false;
                        }

                },function(error) {
                  handleErrorResponse(error);
                  vm.loader = false;
                }
              );
              })
         }

       function verifyScrollNumber(flag) {
                     vm.loader = true;
                     let requestData ={};
                    if(flag == 'verifyDetail'){
                    requestData.id = '';
                    requestData.verifyFlag = '';
                     }else {
                      requestData.id = '';
                      requestData.verifyFlag = '';
                      requestData.stateTaxCode=vm.stateTaxCode;
                      requestData.stateTaxName=vm.stateTaxName;
                      requestData.levelId=vm.levelId;
                      requestData.levelName=vm.levelName;
                      requestData.branchId=vm.branchId;
                      requestData.branchName=vm.branchName;
                      requestData.scrollCode=vm.scrollCode;
                      requestData.scrollName=vm.scrollName;
                      requestData.majorHeadCode=vm.majorHeadCode;
                      requestData.majorHeadName=vm.majorHeadName;
                      requestData.frequency=vm.frequency;
                      requestData.startSerialNo=vm.nextScrollNumber;
                     }
                   scrollNumberMaintenanceService.verifyScrollNumber(requestData).then(function(response){
                      vm.loader=false;
                      vm.verifySelect = response.data.result;
                      if(flag == 'verifySelect'){
                      scrollNumberMaintenanceService.insertScrollNumber(requestData).then(function(response){
                          vm.stateCodeList = response.data.result.stateTaxIdTorch;
                          vm.verifySelectList = [];
                          angular.forEach(vm.stateCodeList, function(item2){
                          angular.forEach(vm.verifySelect, function(item1){
                            if(item1.stateTaxCode == item2.stateTaxCode){
                                  var obj = {id: item1.id,stateTaxCode: item1.stateTaxCode,stateTaxDescription: item2.stateTaxDescription,stateCode: item1.stateCode,
                                  scrollCode: item1.scrollCode,levelId: item1.levelId,branchId: item1.branchId,
                                  majorHeadCode: item1.majorHeadCode,frequencyCode: item1.frequency,nextScrollNumber: item1.nextScrollNumber,
                                  createdBy : item1.createdBy,createdDate : item1.createdDate,lastModifiedBy : item1.lastModifiedBy,
                                  lastModifiedDate : item1.lastModifiedDate,verifiedBy : item1.verifiedBy, verifiedDate : item1.verifiedDate,
                                  isDeleted : item1.isDeleted, verifiedStatus : item1.verifiedStatus,isActive : item1.isActive};
                                  vm.verifySelectList.push(obj);
                            }
                          })
                          })
                          $timeout(function(){
                          angular.element('#stateTaxModel').modal('toggle');
                          },1000)
                           } ,function(error) {
                              handleErrorResponse(error);
                              location.href = "#!/scroll_number_maintenance/action/inquiry"
                              vm.loader = false;
                            });

                      }else if(flag == 'verifyDetail'){
                          requestData.stateTaxName = vm.stateTaxName;
                          requestData.stateTaxCode = vm.stateTaxCode;
                 scrollNumberMaintenanceService.insertScrollNumber(requestData).then(function(response){
                        vm.levelIdList = response.data.result.levelIdTorch;
                        angular.forEach(vm.levelIdList, function(item){
                               if(item.levelId == vm.levelId){
                                  vm.levelName = item.levelName;
                               }
                          })
                          requestData.stateTaxName = vm.stateTaxName;
                          requestData.stateTaxCode = vm.stateTaxCode;
                          requestData.levelId = vm.levelId;
                          requestData.levelName = vm.levelName;
                 scrollNumberMaintenanceService.insertScrollNumber(requestData).then(function(response){
                        vm.branchIdList = response.data.result.branchIdTorch;
                        angular.forEach(vm.branchIdList, function(item){
                              if(item.branchId == vm.branchId){
                                  vm.branchName = item.branchName;
                              }
                          })

                         requestData.stateTaxName = vm.stateTaxName;
                         requestData.stateTaxCode = vm.stateTaxCode;
                         requestData.levelId = vm.levelId;
                         requestData.levelName = vm.levelName;
                         requestData.branchId = vm.branchId;
                         requestData.branchName = vm.branchName;
                scrollNumberMaintenanceService.insertScrollNumber(requestData).then(function(response){
                       vm.scrollNameList = response.data.result.scrollNameTorch;
                       angular.forEach(vm.scrollNameList, function(item){
                             if(item.scrollCode == vm.scrollCode){
                                 vm.scrollName = item.scrollName;
                             }
                         })
                         requestData.stateTaxName = vm.stateTaxName;
                         requestData.stateTaxCode = vm.stateTaxCode;
                         requestData.levelId = vm.levelId;
                         requestData.levelName = vm.levelName;
                         requestData.branchId = vm.branchId;
                         requestData.branchName = vm.branchName;
                         requestData.scrollCode = vm.scrollCode;
                         requestData.scrollName = vm.scrollName;
                scrollNumberMaintenanceService.insertScrollNumber(requestData).then(function(response){
                       vm.majorHeadNameList = response.data.result.majorHeadCodeTorch;
                       angular.forEach(vm.majorHeadNameList, function(item){
                             if(item.majorHeadCode == vm.majorHeadCode){
                                 vm.majorHeadName = item.majorHeadDescription;
                             }
                         })

                          requestData.stateTaxName = vm.stateTaxName;
                          requestData.stateTaxCode = vm.stateTaxCode;
                          requestData.levelId = vm.levelId;
                          requestData.levelName = vm.levelName;
                          requestData.branchId = vm.branchId;
                          requestData.branchName = vm.branchName;
                          requestData.scrollCode = vm.scrollCode;
                          requestData.scrollName = vm.scrollName;
                          requestData.majorHeadCode = vm.majorHeadCode;
                          requestData.majorHeadName = vm.majorHeadName;
               scrollNumberMaintenanceService.insertScrollNumber(requestData).then(function(response){
                          vm.frequency = response.data.result.frequencyLabel;
                       angular.forEach(vm.frequency, function(item){
                            if(item.frequencyCode == vm.frequencyCode){
                                vm.frequencyDescription = item.frequencyDescription;
                            }
                         })
                         },function(error) {
                          handleErrorResponse(error);
                          location.href = "#!/scroll_number_maintenance/action/inquiry"
                          vm.loader = false;
                         });
                         },function(error) {
                           handleErrorResponse(error);
                           location.href = "#!/scroll_number_maintenance/action/inquiry"
                           vm.loader = false;
                         });
                         },function(error) {
                           handleErrorResponse(error);
                           location.href = "#!/scroll_number_maintenance/action/inquiry"
                           vm.loader = false;
                         });


                         } ,function(error) {
                              handleErrorResponse(error);
                              location.href = "#!/scroll_number_maintenance/action/inquiry"
                              vm.loader = false;
                            });


                          } ,function(error) {
                           handleErrorResponse(error);
                           location.href = "#!/scroll_number_maintenance/action/inquiry"
                           vm.loader = false;
                         });

                      }
                   } ,function(error) {
                       handleErrorResponse(error);
                       location.href = "#!/scroll_number_maintenance/action/inquiry"
                       vm.loader = false;
                   });

                     }
                function verify() {
                             Swal.fire({
                               title : "Are you sure?",
                               text : "Do you want to Really Verify this record?",
                               type : "warning",
                               showCancelButton : true,
                               cancelButtonClass : "btn-cancel",
                               confirmButtonClass : "btn-primary",
                               confirmButtonText : "Yes",
                               closeOnConfirm : false
                               }
                             ,() => {
                             vm.loader=true
                             let requestData ={};
                               requestData.id=vm.id;
                               vm.verifyFlag = 'Y';
                               requestData.verifyFlag=vm.verifyFlag;
                             scrollNumberMaintenanceService.verifyScrollNumber(requestData).then(
                               function (response) {
                                 if(response.data.message = 'SUCCESS') {
                                       Swal.fire("Verified!","Data verified successfully","success");
                                       location.href = "#!/scroll_number_maintenance/action/inquiry"
                                       vm.loader=false;
                                       }

                               },function(error) {
                                 handleErrorResponse(error);
                                 vm.loader = false;
                               }
                             );
                             })
                        }
          //Add Zero Before Scroll Number
          function scrollNumberLimit(nextScrollNumber) {
          if(nextScrollNumber){
                var vvres = nextScrollNumber;
                var scrollRes=vvres.padStart(5, '0'); // '0005'
                vm.nextScrollNumber=scrollRes;
                }
          }
          //Add Zero Before Scroll Number
            function modifyScrollNumberLimit(nextScrollNumber) {
             if(nextScrollNumber){
                  var vvres = nextScrollNumber;
                  var scrollRes=vvres.padStart(5, '0'); // '0005'
                  vm.nextScrollNumber.oldScrollSerNo=scrollRes;
                  }
            }

           //Get Add Frequency Detail
              function getAddFrequency(scrollCode,flag)
              {
                  var resVal=vm.getScrollnameListDetails;
                  //var vreeee=scrollCode;
                  //console.log(vreeee);
                  if(flag=='add'){
                    angular.forEach(vm.getScrollnameListDetails, function(item){
                        if(scrollCode == item.scrollCode){
                            vm.frequency = item.frequency;
                        }
                      })
                  }else if(flag=='modify'){

                      getModifyDetailsAndScrollNumber(scrollCode);
                      angular.forEach(vm.getScrollnameListDetails, function(item){
                        if(scrollCode == item.scrollCode){
                            vm.frequency = item.frequency;
                        }
                      })

                  }
                  else if(flag=='verify' || flag=='delete'){

                        getVerifyDetailsAndScrollNumber(scrollCode);
                        angular.forEach(vm.getScrollnameListDetails, function(item){
                          if(scrollCode == item.scrollCode){
                              vm.frequency = item.frequency;
                          }
                        })

                    }
                    else if(flag=='enquiry'){
                        getEnquiryDetailsAndScrollNumber(scrollCode);
                        angular.forEach(vm.getScrollnameListDetails, function(item){
                          if(scrollCode == item.scrollCode){
                              vm.frequency = item.frequency;
                          }
                        })
                    }
              }


          //Get Modify Scrollname Details
            function getActiveScrollname()
            {
                scrollNumberMaintenanceService.getModifyScrollNameDetails(vm.tsryCode,vm.levelId,vm.branchId).then(function(response){
                        vm.loader=false;
                        vm.getScrollnameList = response.data.result.list;
                   },function(error) {
                       handleErrorResponse(error);
                       vm.loader = false;
                     });
            }
          //Get Modify Details with Scroll Number
          function getModifyDetailsAndScrollNumber()
            {
                vm.loader = true;
//                let requestData ={
//                    'trsyCode': vm.tsryCode,
//                    'levelId': vm.levelId,
//                    'branchId': vm.branchId,
//                    'scrollCode': vm.scrollName
//                };

                scrollNumberMaintenanceService.getModifyDetails(vm.tsryCode,vm.levelId,vm.branchId,vm.scrollName).then(function(response){
                        vm.loader=false;
                        //vm.getScrollNumber = response.data.result;
                        vm.nextScrollNumber = response.data.result;

                   },function(error) {
                       handleErrorResponse(error);
                       vm.loader = false;
                     });
            }

           //Modify Submit
           function modifyScrollNumberMaster() {
               Swal.fire({
                  title : "Are you sure?",
                  text : "Do you want to Really Modify this record?",
                  type : "warning",
                  showCancelButton : true,
                  cancelButtonClass : "btn-cancel",
                  confirmButtonClass : "btn-primary",
                  confirmButtonText : "Yes",
                  closeOnConfirm : false
               },() => {
                  vm.loader=true
                  let requestData ={
                    'trsyCode' : vm.tsryCode,
                    'levelId' : vm.levelId,
                    'branchId' : vm.branchId,
                    'scrollCode' : vm.scrollName,
                    'startSerialNo' : vm.nextScrollNumber.oldScrollSerNo
                  };
                  scrollNumberMaintenanceService.modifyScrollNumber(requestData).then(
                   function (response) {
                        Swal.fire("Modified!","Data updated successfully","success");
                        location.href = "#!/pension_scroll_number_master/action/inquiry"
                        vm.loader=false;
                   },
                   function(error) {
                     handleErrorResponse(error);
                     vm.loader = false;
                   }
                  )

               })
           }

             //Get Verify Active Levelname
             function getVerifyActiveLevelname()
             {
                 scrollNumberMaintenanceService.getVerifyLevelnameDetails(vm.tsryCode).then(function(response){
                         vm.loader=false;
                         vm.getLevelname = response.data.result.list;

                    },function(error) {
                        handleErrorResponse(error);
                        vm.loader = false;
                      });
             }
             //Get Modify Branch Details
               function getVerifyActiveBranch()
               {
                   vm.activeVal='';
                   vm.loader = true;
                   scrollNumberMaintenanceService.getVerifyLevelBranchDetails(vm.tsryCode,vm.levelId).then(function(response){
                           vm.loader=false;
                           vm.getBranchList = response.data.result.list;
                           angular.element('#searchLevelBranchModal').modal('toggle');
                      },function(error) {
                          handleErrorResponse(error);
                          vm.loader = false;
                        });
               }
               //Get Modify Scrollname Details
                 function getVerifyActiveScrollname()
                 {
                     scrollNumberMaintenanceService.getVerifyScrollNameDetails(vm.tsryCode,vm.levelId,vm.branchId).then(function(response){
                             vm.loader=false;
                             vm.getScrollnameList = response.data.result.list;
                        },function(error) {
                            handleErrorResponse(error);
                            vm.loader = false;
                          });
                 }
           //Get Verify Details with Scroll Number
             function getVerifyDetailsAndScrollNumber()
               {
                   vm.loader = true;
//                   let requestData ={};
//                     requestData.trsyCode=vm.tsryCode;
//                     requestData.levelId=vm.levelId;
//                     requestData.branchId=vm.branchId;
//                     requestData.scrollCode=vm.scrollName;



                   scrollNumberMaintenanceService.getVerifyDetails(vm.tsryCode,vm.levelId,vm.branchId,vm.scrollName).then(function(response){
                           vm.loader=false;
                           vm.getSelectDetails = response.data.result;
                           //console.log(vm.getScrollNumber);

                      },function(error) {
                          handleErrorResponse(error);
                          vm.loader = false;
                        });
               }
                //Get Enquiry Active Levelname
       function getEnquiryActiveLevelname()
       {
           scrollNumberMaintenanceService.getEnquiryLevelnameDetails(vm.tsryCode).then(function(response){
                   vm.loader=false;
                   vm.getLevelname = response.data.result.list;

              },function(error) {
                  handleErrorResponse(error);
                  vm.loader = false;
                });
       }
     //Get Enquiry Branch Details
     function getEnquiryActiveBranch()
     {
         vm.acriveSearch='';
         vm.loader = true;
         scrollNumberMaintenanceService.getEnquiryLevelBranchDetails(vm.tsryCode,vm.levelId).then(function(response){
                 vm.loader=false;
                 vm.getBranchList = response.data.result.list;
                 angular.element('#searchLevelBranchModal').modal('toggle');
            },function(error) {
                handleErrorResponse(error);
                vm.loader = false;
              });
     }
     //Get Enquiry Scrollname Details
       function getEnquiryActiveScrollname()
       {
           scrollNumberMaintenanceService.getEnquiryScrollNameDetails(vm.tsryCode,vm.levelId,vm.branchId).then(function(response){
                   vm.loader=false;
                   vm.getScrollnameList = response.data.result.list;
              },function(error) {
                  handleErrorResponse(error);
                  vm.loader = false;
                });
       }
    //Get Enquiry Details
     function getEnquiryDetailsAndScrollNumber()
       {
           vm.loader = true;
           let requestData ={};
             requestData.trsyCode=vm.tsryCode;
             requestData.levelId=vm.levelId;
             requestData.branchId=vm.branchId;
             requestData.scrollCode=vm.scrollName;

           scrollNumberMaintenanceService.getEnquiryDetails(requestData).then(function(response){
                   vm.loader=false;
                   vm.getSelectDetails = response.data.result;

              },function(error) {
                  handleErrorResponse(error);
                  vm.loader = false;
                });
       }

       //Change the level
       function changeLevel(){
            vm.branchId = vm.branchDescription = '';
            vm.getSelectDetails='';vm.scrollName='';vm.frequency='';
       }

       vm.changeTreasury = function(){
             vm.branchId = vm.branchDescription = '';
             vm.getSelectDetails='';vm.scrollName='';vm.frequency='';
             vm.levelId = '';
       }

       function changeBranch(){
            vm.getSelectDetails='';vm.scrollName='';vm.frequency='';

       }


	}
})();

