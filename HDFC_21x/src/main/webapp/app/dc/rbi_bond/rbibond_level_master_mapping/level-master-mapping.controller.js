(function() {

 'use strict';

 angular.module('gbmUltraApp').controller('rbiBondLevelMasterMappingController',
  rbiBondLevelMasterMappingController);

 rbiBondLevelMasterMappingController.$inject = ['$scope', '$filter', '$state',
  '$stateParams', '$location', 'rbiBondLevelMasterMappingService', 'commonService'
 ];




 function rbiBondLevelMasterMappingController($scope, $filter, $state, $stateParams,
  $location, rbiBondLevelMasterMappingService, commonService) {

  const TABLE_NAME = "T007";
  var vm = this;
  vm.commonYesNoMetaData = COMMON_YES_NO_METADATA;
  vm.submitAddForm = submitAddForm;
  vm.submitModifyForm = submitModifyForm;
  vm.verifySubmitForm = verifySubmitForm;
  vm.showCancelConfirmation = showCancelConfirmation;
  vm.fetchParentSolID = fetchParentSolID;
  vm.fetchParentLevelIDList = fetchParentLevelIDList;
  vm.fetchParentSolIDListModify = fetchParentSolIDListModify;
  vm.mappedSols = mappedSols;
  vm.deleteSubmit = deleteSubmit;
  vm.assignParentId = assignParentId;
  vm.assignParentIdInquiry = assignParentIdInquiry;
  vm.fetchSolDetails = fetchSolDetails;
  vm.fetchSolDetailsVerify = fetchSolDetailsVerify;
  vm.goBack = goBack;
  vm.clearValues = clearValues;
  vm.getModuleName = getModuleName;
  vm.getLevelNameDetails = getLevelNameDetails;
  vm.getBranchSolDetails = getBranchSolDetails;
  vm.getParentSolDetails = getParentSolDetails;
  setDualListOptions();
  vm.unmappedSol = [];
  vm.mappedSol = [];
  var leftValue = [];
  var rightValue = [];
  vm.levelMaster = {};
  vm.getTreasuryCodes = getTreasuryCodes;
  vm.getTreasuryCodesModify = getTreasuryCodesModify;
  vm.getTreasuryCodesVerify = getTreasuryCodesVerify;
  vm.inquiryLevelMasterMap = inquiryLevelMasterMap;
  vm.insertLevelMasterMap = insertLevelMasterMap;
  vm.treasuryCodesList = [];
  vm.parentIdList = [];
  vm.parentIdListForEnable = [];
  vm.fetchLevelID = fetchLevelID;
  vm.fetchParentID = fetchParentID;
  vm.fetchParentIDList = [];
  vm.fetchParentIDList = [];
  vm.fetchParentSolID = fetchParentSolID;
  vm.fetchparentSolIDList = [];
  vm.solDetailsList = [];
  vm.screenModule = 'RBI Bond';

  function goBack() {
   vm.levelMaster.treasuryName = '';
   vm.levelMaster.levelName = '';
   vm.levelMaster.parentName = '';
   vm.levelMaster.C003 = '';
   vm.levelMaster.C006 = '';
  }

  function assignParentId(levelId) {
   if (levelId == 1) {
    vm.levelMaster.parentName = 'None';
    vm.levelMaster.parentId = 'None';
    vm.levelMaster.C006 = 'None';
    if ($state.current.name == 'rbibond_level_master_mapping.add') {
     getBranchSolDetails();
    } else if ($state.current.name == 'rbibond_level_master_mapping.modify') {
     getTreasuryCodesModify();
    }
   }
  }

  function assignParentIdInquiry(levelId) {
   if (levelId == '1') {
    vm.levelMaster.parentName = 'None';
    vm.levelMaster.parentId = 'None';
    vm.levelMaster.C006 = 'None';
    if ($state.current.name == 'rbibond_level_master_mapping.inquiry') {
     fetchSolDetailsInquiry();
    } else if ($state.current.name == 'rbibond_level_master_mapping.modify') {
     getTreasuryCodesModify();
    } else if ($state.current.name == 'rbibond_level_master_mapping.verify') {
     getTreasuryCodesVerify();
    }
   }
  }


  function fetchSolDetails() {
   vm.loader = true;
   vm.unmappedSol = [];
   vm.mappedSol = [];
   if ($state.current.name == 'rbibond_level_master_mapping.add') {
    rbiBondLevelMasterMappingService.fetchSolDetails(vm.levelMaster.C001, vm.levelMaster.C003, vm.levelMaster.parentId, vm.levelMaster.C006).then(
     (response) => {
      vm.unmappedSol = response.data.result.unmappedSols;
      leftValue = angular.copy(vm.unmappedSol);
      rightValue = angular.copy(vm.mappedSol);
      vm.loader = false;
     }, handleErrorResponse);
   }
   if ($state.current.name == 'rbibond_level_master_mapping.modify') {
    rbiBondLevelMasterMappingService.fetchSolDetailsVerify(vm.levelMaster.C001, vm.levelMaster.C003, vm.levelMaster.parentId, vm.levelMaster.C006).then(
     (response) => {
      vm.unmappedSol = response.data.result.unmappedSols;
      leftValue = angular.copy(vm.unmappedSol);
      rightValue = angular.copy(vm.mappedSol);
      vm.loader = false;
     }, handleErrorResponse);
   }
  }

  function getLeftRightValue() {
   leftValue = angular.copy(vm.unmappedSol);
   rightValue = angular.copy(vm.mappedSol);
  }

  function inquiryLevelMasterMap(flag) {
   if (flag == 'level') {
    vm.levelMaster.parentName = '';
    vm.levelMaster.C006 = '';
    var requestData = {

    };
   } else if (flag == 'parentSol') {
    var requestData = {
     'levelId': vm.levelMaster.C003,
     'parentLevelId': vm.levelMaster.parentId,
     'parentBranchId': ''

    };
   } else {
    var requestData = {
     'levelId': vm.levelMaster.C003,
     'parentLevelId': vm.levelMaster.parentId,
     'parentBranchId': vm.levelMaster.C006

    };
   }
   vm.loader = true;
   rbiBondLevelMasterMappingService.inquiryLevelMasterMap(requestData).then(
    (response) => {
     vm.loader = false;
     if (response.data.result.levelsOut != 0) {
      angular.element('#levelIDSearcher').modal('toggle');
      vm.parentIdList = response.data.result.levelsOut;
     } else if (response.data.result.parentBranchesOut != 0) {
      angular.element('#parentSolIDSearcher').modal('toggle');
      vm.fetchparentSolIDList = response.data.result.parentBranchesOut;
     }
     vm.mapUnmapResult = response.data.result;
     vm.remarks= response.data.result.remarks;
    }, handleErrorResponse);
  }

  function insertLevelMasterMap(flag) {
   if (flag == 'treasury') {
    vm.levelMaster.C001 = '';
    vm.levelMaster.C003 = '';
    vm.levelMaster.parentId = '';
    vm.levelMaster.C006 = ''
   } else if (flag == 'level') {
    vm.levelMaster.C003 = '';
    vm.levelMaster.parentId = '';
    vm.levelMaster.C006 = ''
   } else if (flag == 'parentSol') {
    vm.levelMaster.C006 = ''
   }
   vm.loader = true;
   rbiBondLevelMasterMappingService.insertLevelMasterMap(vm.levelMaster.C001, vm.levelMaster.C003, vm.levelMaster.parentId, vm.levelMaster.C006).then(
    (response) => {
     vm.loader = false;
     if (response.data.result.trsyList != null) {
      angular.element('#treasurySearcher').modal('toggle');
      vm.treasuryCodesList = response.data.result.trsyList;
     } else if (response.data.result.levelDetails != null) {
      angular.element('#levelIDSearcher').modal('toggle');
      vm.parentIdList = response.data.result.levelDetails;
     } else if (response.data.result.parentSolIdList != null) {
      angular.element('#parentSolIDSearcher').modal('toggle');
      vm.fetchparentSolIDList = response.data.result.parentSolIdList;
     }
     vm.unmappedSol = response.data.result.unmappedSols;
     getLeftRightValue();
    }, handleErrorResponse);
  }


  function fetchSolDetailsVerify() {
   vm.loader = true;
   rbiBondLevelMasterMappingService.fetchSolDetailsVerify(vm.levelMaster.C001, vm.levelMaster.C003, vm.levelMaster.parentId, vm.levelMaster.C006).then(
    (response) => {
     vm.loader = false;
     vm.mapUnmapResult = response.data.result;
    }, handleErrorResponse);
  }

  function fetchSolDetailsInquiry() {
   vm.loader = true;
   var requestData = {
    'levelId': vm.levelMaster.C003,
    'parentLevelId': vm.levelMaster.parentId,
    'parentBranchId': vm.levelMaster.C006

   };
   rbiBondLevelMasterMappingService.inquiryLevelMasterMap(requestData).then(
    (response) => {
     vm.loader = false;
     vm.mapUnmapResult = response.data.result;
    }, handleErrorResponse);
  }

  function setDualListOptions() {
   vm.options = {
    leftContainerScrollEnd: function() {

    },
    rightContainerScrollEnd: function() {

    },
    leftContainerSearch: function(text) {
     vm.unmappedSol = $filter('filter')(leftValue, {
      'parentBranchId': text
     })

    },
    rightContainerSearch: function(text) {
     vm.mappedSol = $filter('filter')(rightValue, {
      'parentBranchId': text
     })
    },
    leftContainerLabel: 'Available Sols',
    rightContainerLabel: 'Selected Sols',
    onMoveRight: function() {
     getLeftRightValue();
    },
    onMoveLeft: function() {
     getLeftRightValue();
    }

   };
  }

  function getTreasuryCodes() {
   vm.loader = true;
   commonService.getActiveTreasuryList().then(function(response) {
    vm.loader = false;
    angular.element('#treasurySearcher').modal('toggle');
    vm.treasuryCodesList = response.data;
   }, handleErrorResponse);
  }


  function getTreasuryCodesModify(flag) {
   vm.loader = true;
   if (flag == 'levelId') {
    vm.levelMaster.parentName = '';
    vm.levelMaster.C006 = '';
    var requestData = {

    };
   } else if (flag == 'parentSol') {
    var requestData = {
     'levelId': vm.levelMaster.C003,
     'parentLevelId': vm.levelMaster.parentId,
     'parentBranchId': ''

    };
   } else {
    var requestData = {
     'levelId': vm.levelMaster.C003,
     'parentLevelId': vm.levelMaster.parentId,
     'parentBranchId': vm.levelMaster.C006

    };
   }
   rbiBondLevelMasterMappingService.getTreasuryCodesModify(requestData).then(
    (response) => {
     vm.loader = false;
     if (response.data.result.levelsOut != 0) {
      angular.element('#levelIDSearcher').modal('toggle');
      vm.parentIdList = response.data.result.levelsOut;
     } else if (response.data.result.parentBranchesOut != 0) {
      angular.element('#parentSolIDSearcher').modal('toggle');
      vm.fetchparentSolIDList = response.data.result.parentBranchesOut;
     }
     vm.unmappedSol = response.data.result.unmappedBranchesOut;
     vm.mappedSol = response.data.result.mappedBranchesOut;
    vm.remarks= response.data.result.remarks;
    vm.result = response.data.result;
     getLeftRightValue();
    }, handleErrorResponse);
  }

  function getTreasuryCodesVerify(flag) {
   vm.loader = true;
   if (flag == 'levelId') {
    vm.levelMaster.parentName = '';
    vm.levelMaster.C006 = '';
    var requestData = {

    };
   } else if (flag == 'parentSol') {
    var requestData = {
     'levelId': vm.levelMaster.C003,
     'parentLevelId': vm.levelMaster.parentId,
     'parentBranchId': ''

    };
   } else {
    var requestData = {
     'levelId': vm.levelMaster.C003,
     'parentLevelId': vm.levelMaster.parentId,
     'parentBranchId': vm.levelMaster.C006

    };
   }
   rbiBondLevelMasterMappingService.getTreasuryCodesVerify(requestData).then(
    (response) => {
     vm.loader = false;
     if (response.data.result.levelsOut != 0) {
      angular.element('#levelIDSearcher').modal('toggle');
      vm.parentIdList = response.data.result.levelsOut;
     } else if (response.data.result.parentBranchesOut != 0) {
      angular.element('#parentSolIDSearcher').modal('toggle');
      vm.fetchparentSolIDList = response.data.result.parentBranchesOut;
     }
     vm.mapUnmapResult = response.data.result;
    }, handleErrorResponse);
  }


  function fetchParentIDList() {
   vm.loader = true;

   var requestData = {
    table: TABLE_NAME,
    columnList: ["C002", "C003"]
   }
   commonService.genericTableSelect(requestData).then(
    (response) => {
     vm.loader = false;

     vm.parentIdListForEnable = response.data.result;
    }, handleErrorResponse);
  }


  function fetchLevelID(trsyCode) {
   vm.loader = true;
   vm.searchparentId = '';
   rbiBondLevelMasterMappingService.fetchLevelID(trsyCode).then(
    (response) => {
     vm.loader = false;
     angular.element('#levelIDSearcher').modal('toggle');
     vm.parentIdList = response.data.result.levelDetails;
    }, handleErrorResponse);
  }

  function fetchParentID(trsyCode) {
   vm.loader = true;
   rbiBondLevelMasterMappingService.fetchParentID(trsyCode).then(
    (response) => {
     vm.loader = false;
     angular.element('#parentIDSearcher').modal('toggle');
     vm.parentIdList = response.data.result.levelDetails;
    }, handleErrorResponse);
  }

  function fetchParentSolID(trsyCode, parentId) {
   vm.loader = true;
   rbiBondLevelMasterMappingService.fetchParentSolIDList(trsyCode, parentId).then(
    (response) => {
     vm.loader = false;
     angular.element('#parentSolIDSearcher').modal('toggle');
     vm.fetchparentSolIDList = response.data.result;
    }, handleErrorResponse);
  }


  function fetchParentSolIDListModify(trsyCode, levelId, parentLevelId) {
   vm.loader = true;
   rbiBondLevelMasterMappingService.fetchParentSolIDListModify(trsyCode, levelId, parentLevelId).then(
    (response) => {
     vm.loader = false;
     angular.element('#parentSolIDSearcher').modal('toggle');
     vm.fetchparentSolIDList = response.data.result;
    }, handleErrorResponse);
  }



  function mappedSols(trsyCode, levelId, parentLevelId, parentSolId) {
   fetchSolDetails();
   vm.loader = true;

   rbiBondLevelMasterMappingService.mappedSolIds(trsyCode, levelId, parentLevelId, parentSolId).then(
    (response) => {
     vm.loader = false;
     vm.mappedSol = response.data.result;
     var i;
     for (i = 0; i < vm.mappedSol.length; i++) {
      vm.mappedSol[i].C001 = vm.mappedSol[i].c001;
      vm.mappedSol[i].C002 = vm.mappedSol[i].c002;
     }
     rightValue = angular.copy(vm.mappedSol);
     console.log(rightValue);
     debugger;
    }, handleErrorResponse);
  }

  function fetchParentLevelIDList(trsyCode, levelId) {
   vm.loader = true;

   rbiBondLevelMasterMappingService.fetchParentLevelIDList(trsyCode, levelId).then(
    (response) => {
     vm.loader = false;
     angular.element('#parentLevelIdSearcher').modal('toggle');
     vm.fetchparentLevelIDListValues = response.data.result;
    }, handleErrorResponse);
  }


  // get sol ID
  function getSolId() {
   commonService.getSolId(function(response) {

    if (response.success) {
     vm.solId = response.data[0].solId;
     vm.solDescription = response.data[0].solDescription;
    } else {
     $scope.$emit('showAlert', {
      'type': 'false',
      'msg': response.message
     });
    }
   });
  }

  /* Add Submit */
  function submitAddForm() {
   if (vm.levelMaster.C003 == '1' && vm.mappedSol.length > 1) {
    let msg = "Only one sol is allowed to map for this level";
    Swal.fire("Warning!", msg, "warning");
   } else if (vm.mappedSol.length == 0) {
    let msg = "Map atleast one sol for this level";
    Swal.fire("Warning!", msg, "warning");
   } else {
//    Swal.fire({
//     title: "Are you sure?",
//     text: "Do you want to Really Add this record?",
//     type: "warning",
//     showCancelButton: true,
//     cancelButtonClass: "btn-cancel",
//     confirmButtonClass: "btn-primary",
//     confirmButtonText: "Yes",
//     closeOnConfirm: false
//    }, () => {






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
     vm.loader = true
     vm.levelMaster.C005 = [];
     angular.forEach(vm.mappedSol, function(value, key) {
      vm.levelMaster.C005.push(value.parentBranchId);
     });
     var postDatalist = {
      "levelId": vm.levelMaster.C003,
      "parentLevelId": vm.levelMaster.parentId,
      "parentBranchId": vm.levelMaster.C006,
      "branchesToAdd": vm.levelMaster.C005

     }
     rbiBondLevelMasterMappingService.levelMasterInsert(postDatalist).then(function(response) {
      vm.loader = false;
      Swal.fire("Mapped!", "Level Master Mapping Added Successfully", "success");
      location.href = '#!/rbibond_level_master_mapping/action/inquiry';
     }, handleErrorResponse);
   } });
   }
  }


  /* Modify Submit */
  function submitModifyForm() {
   if (vm.levelMaster.C003 == '1' && vm.mappedSol.length > 1) {
    let msg = "Only one sol is allowed to map for this level";
    Swal.fire("Warning!", msg, "warning");
   } else if (vm.mappedSol.length == 0) {
    let msg = "Map atleast one sol for this level";
    Swal.fire("Warning!", msg, "warning");
   } else {
//    Swal.fire({
//      title: "Are you sure?",
//      text: "Do you want to Really Modify this record?",
//      type: "warning",
//      showCancelButton: true,
//      cancelButtonClass: "btn-cancel",
//      confirmButtonClass: "btn-primary",
//      confirmButtonText: "Yes",
//      closeOnConfirm: false
//     },
//
//     function() {


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
      vm.loader = true
      vm.levelMaster.C005 = [];
      angular.forEach(vm.mappedSol, function(value, key) {
       vm.levelMaster.C005.push(value.parentBranchId);
      });
      var postDatalist = {
       "levelId": vm.levelMaster.C003,
       "parentLevelId": vm.levelMaster.parentId,
       "parentBranchId": vm.levelMaster.C006,
       "branchesToAdd": vm.levelMaster.C005

      }

      rbiBondLevelMasterMappingService.submitModifyForm(postDatalist).then(function(response) {
       vm.loader = false;
       Swal.fire("Modified!", "Level Master Mapping Modify Successfully", "success");
       location.href = '#!/rbibond_level_master_mapping/action/inquiry';
      }, handleErrorResponse);

    } }, handleErrorResponse);
   }

  }

  /*  Submit */
  function verifySubmitForm() {
//   Swal.fire({
//     title: "Are you sure?",
//     text: "Do you want to Really Verify this record?",
//     type: "warning",
//     showCancelButton: true,
//     cancelButtonClass: "btn-cancel",
//     confirmButtonClass: "btn-primary",
//     confirmButtonText: "Yes",
//     closeOnConfirm: false
//    },
//
//    function() {


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
     var postDatalist = {
      "levelId": vm.levelMaster.C003,
      "parentLevelId": vm.levelMaster.parentId,
      "parentBranchId": vm.levelMaster.C006,
      "branchesToAdd": vm.levelMaster.C005

     }

     rbiBondLevelMasterMappingService.verifySubmitForm(postDatalist).then(function(response) {
      vm.loader = false;
      Swal.fire("Verified!", "Level Master Mapping Verified Successfully", "success");
      location.href = '#!/rbibond_level_master_mapping/action/inquiry';
     }, function(error) {
      vm.loader = false;
      handleErrorResponse(error);
     });
   } });
  }

 function deleteSubmit() {
     function handleKeyPress(event) {
         var key = event.key;
         var input = event.target.value;

         if (key === ' ' && input.slice(-1) === ' ') {
             event.preventDefault();
         }

         if (!key.match(/^[a-zA-Z\s]$/) && key !== 'Backspace' && key!=='Enter') {
             event.preventDefault();
         }
     }

     document.addEventListener('keypress', handleKeyPress);

     Swal.fire({
         title: "Rejection Remarks",
         text: "",
         type: "input",
         inputPlaceholder: "Enter your remarks here",
         showCancelButton: true,
         cancelButtonClass: "btn-cancel",
         confirmButtonClass: "btn-primary",
         confirmButtonText: "Submit",
         animation: "slide-from-top",
         closeOnConfirm: false,
         showLoaderOnConfirm: true,
         inputValidator: function(value) {
             if (/^[a-zA-Z\s]*$/.test(value.trim())) {
                 if (/\s\s/.test(value)) {
                     return "Please enter only single spaces between words!";
                 }
                 document.removeEventListener('keypress', handleKeyPress);
                 return true;
             } else {
                 return "Please enter only alphabetic characters!";
             }
         }
     }, function(inputValue) {
         if (inputValue === false) {
             document.removeEventListener('keypress', handleKeyPress);
             return false;
         }

         if (inputValue === "") {
             Swal.fire.showInputError("Please enter the remarks!");
             return false;
         }

         vm.remarks = inputValue;

         var requestData = {
             levelId: vm.levelMaster.C003,
             parentLevelId: vm.levelMaster.parentId,
             parentBranchId: vm.levelMaster.C006,
             branchesToAdd: vm.levelMaster.C005,
             remarks: vm.remarks
         };

         rbiBondLevelMasterMappingService.deleteDetailsSubmit(requestData)
             .then(function(response) {
                 vm.loader = false;
                 if (response.data.message === 'SUCCESS') {
                     Swal.fire("Rejected!", "Level Master Mapping Rejected Successfully", "success");
                     location.href = '#!/rbibond_level_master_mapping/action/inquiry';
                 } else {
                     Swal.fire("Warning!", response.data.message, "error");
                     location.href = '#!/rbibond_level_master_mapping/action/inquiry';
                 }
             })
             .catch(function(error) {
                 vm.loader = false;
                 handleErrorResponse(error);
                 location.href = '#!/rbibond_level_master_mapping/action/inquiry';
             });
     });
 }



  // Cancel Confirmation
  function showCancelConfirmation(state) {
//   Swal.fire({
//     title: "Confirm Cancel",
//     text: "Do you really want to cancel?",
//     type: "warning",
//     showCancelButton: true,
//     cancelButtonClass: "btn-cancel",
//     confirmButtonClass: "btn-danger",
//     confirmButtonText: "Yes",
//     cancelButtonText: "No",
//     closeOnConfirm: true
//    },
//    function() {




     Swal.fire({
                    title: "Are you sure?",
                    text: "Do you Really Want To Cancel?",
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
   } });
  }



  // Handle error response
  function handleErrorResponse(response) {
   vm.loader = false;
   var msg = response.data.message ? response.data.message :
    "Server Error";
   Swal.fire("Error!", msg, "error");
   $state.go('rbibond_level_master_mapping.inquiry');

   clearValues();
  }


  function clearValues() {
   vm.levelMaster.C001 = '';
   vm.levelMaster.treasuryName = '';
   vm.levelMaster.levelName = vm.levelMaster.C003 = '';
   vm.levelMaster.parentName = vm.levelMaster.C006 = '';
  }

  //New Integration on 13/02/2020
  //Get Module Name
  function getModuleName() {
   vm.referenceId = 'EFRIGHT';
   var requestData = {
    'referenceId': vm.referenceId
   }
   rbiBondLevelMasterMappingService.getModuleDetails(requestData).then(function(response) {
    vm.moduleNameList = response.data.result;
   }, function(error) {
    handleErrorResponse(error);
   });
  }

  //Get Level Name Torch
  function getLevelNameDetails() {
   vm.loader = true;
   vm.levelMaster.parentName = '';
   vm.levelMaster.C006 = '';

   var requestData = {

   };
   rbiBondLevelMasterMappingService.getAddDetails(requestData).then(function(response) {
    vm.loader = false;
    vm.parentIdList = response.data.result.levelsOut;
    angular.element('#levelIDSearcher').modal('toggle');
   }, function(error) {
    handleErrorResponse(error);
    vm.loader = false;
   });
  }
  //Get Parent Sol Torch
  function getParentSolDetails() {
   vm.loader = true;
   var requestData = {
    'levelId': vm.levelMaster.C003,
    'parentLevelId': vm.levelMaster.parentId,
    'parentSolId': ''

   };
   rbiBondLevelMasterMappingService.getAddDetails(requestData).then(function(response) {
    vm.loader = false;
    angular.element('#parentSolIDSearcher').modal('toggle');
    vm.fetchparentSolIDList = response.data.result.parentBranchesOut;
   }, function(error) {
    handleErrorResponse(error);
    vm.loader = false;
   });
  }
  //Get Branch Sol Details
  function getBranchSolDetails() {
   vm.loader = true;
   vm.unmappedSol = [];
   vm.mappedSol = [];
   if (vm.levelMaster.C003 && vm.levelMaster.parentId && vm.levelMaster.C006) {
    var requestData = {
     'levelId': vm.levelMaster.C003,
     'parentLevelId': vm.levelMaster.parentId,
     'parentBranchId': vm.levelMaster.C006
    };
   } else if (vm.levelMaster.C003) {
    var requestData = {
     'levelId': vm.levelMaster.C003

    };
   }
   rbiBondLevelMasterMappingService.getAddDetails(requestData).then(function(response) {
    vm.loader = false;
    //angular.element('#parentSolIDSearcher').modal('toggle');
    //vm.fetchparentSolIDList = response.data.result.unmappedBranchesOut;
    vm.unmappedSol = response.data.result.unmappedBranchesOut;
    if (response.data.result.mappedSols) {
     vm.mappedSol = response.data.result.mappedSols;
    }
    getLeftRightValue();
   }, function(error) {
    handleErrorResponse(error);
    vm.loader = false;
   });
  }
  //Get Modify map unmap details
  function fetchModifyDetails() {
   vm.loader = true;
   var requestData = {
    'levelId': vm.levelMaster.C003,
    'parentLevelId': '',
    'parentBranchId': ''

   };
   rbiBondLevelMasterMappingService.getTreasuryCodesModify(requestData).then(
    (response) => {
     vm.loader = false;
     vm.unmappedSol = response.data.result.unmappedBranchesOut;
     if (response.data.result.mappedBranchesOut) {
      vm.mappedSol = response.data.result.mappedBranchesOut;
     }
     getLeftRightValue();
    }, handleErrorResponse);
  }
  //Get Verify map unmap details
  function fetchVerifyDetails() {
   vm.loader = true;
   var requestData = {
    'levelId': vm.levelMaster.C003,
    'parentLevelId': '',
    'parentBranchId': ''

   };
   rbiBondLevelMasterMappingService.inquiryLevelMasterMap(requestData).then(
    (response) => {
     vm.loader = false;
     vm.mapUnmapResult = response.data.result;
    }, handleErrorResponse);
  }




 }
})();