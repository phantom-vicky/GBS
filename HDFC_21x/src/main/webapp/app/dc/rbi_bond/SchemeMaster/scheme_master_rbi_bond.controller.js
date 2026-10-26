(function() {

    'use strict';

    angular.module('gbmUltraApp').controller('schemeMasterRbiBondController',
       schemeMasterRbiBondController);

    schemeMasterRbiBondController.$inject = ['$scope', '$filter', '$state', '$rootScope',
        '$stateParams', '$location', 'schemeMasterRbiBondService', 'commonService'
    ];

    function schemeMasterRbiBondController($scope, $filter, $state, $rootScope, $stateParams,
        $location, schemeMasterRbiBondService, commonService) {

        var vm = this;
        vm.solId = $rootScope.globals.currentUser.solId;
        vm.username = $rootScope.globals.currentUser.solDesc;
        vm.commonYesNoMetaData = COMMON_YES_NO_METADATA;
        vm.currentDate = new Date();
        vm.inquiry = inquiry;
        vm.getRemarksList = getRemarksList;
        //Add Functions
        vm.submitAddForm = submitAddForm;
        vm.modifySelectDetails = modifySelectDetails;
        vm.verifySelectDetails = verifySelectDetails;
        vm.modifySubmit = modifySubmit;
        vm.verifySubmit = verifySubmit;
        vm.deleteSubmit = deleteSubmit;
        vm.rejectReason = rejectReason;
        vm.specialNotAllowed=specialNotAllowed;
         if ($state.current.name == 'scheme_master_rbi_bond.modify_detail'){
            if ($stateParams.obj) {
                vm.schemeName = $stateParams.obj.schemeName;
                modifySelectDetails();
            } else {
                $state.go('scheme_master_rbi_bond.modify');
            }
        }else if ($state.current.name == 'scheme_master_rbi_bond.verify_detail') {
            if ($stateParams.obj) {
                vm.schemeName = $stateParams.obj.schemeName;
                  verifySelectDetails();
            } else {
                $state.go('scheme_master_rbi_bond.verify');
            }
        }  else if ($state.current.name == 'scheme_master_rbi_bond.inquiry_detail') {
            if ($stateParams.obj) {
               vm.schemeName = $stateParams.obj.schemeName;
               inquiry();
            } else {
                $state.go('scheme_master_rbi_bond.inquiry');
            }
        }
        $('#schemeName').keyup(function(e) {
          var strg = this.value;
          var firstChar = strg.charAt(0);
          if ((firstChar == " ")||(firstChar == "&nbsp;")||(strg == "")) {
            this.value = this.value.replace(/\s/g,'');
          }
        });
        function specialNotAllowed(values){
        if(values=='?' || values=='.'){
        vm.schemeName='';
        }
        }

         function getRemarksList() {
                            vm.referenceId = '055';
                            var requestData = { 'referenceId': vm.referenceId };

                            schemeMasterRbiBondService.getRemarksList(requestData).then(function(response) {
                                vm.remarksList = response.data.result || []; // Ensure it's an array
                            }, function(error) {
                                handleErrorResponse(error);
                            });
                        }

        /* Add Submit */
        function submitAddForm() {
//            Swal.fire({
//                 title: "Are you sure?",
//                 text: "Do you want to  Add this record?",
//                 type: "warning",
//                 showCancelButton: true,
//                 cancelButtonClass: "btn-cancel",
//                 confirmButtonClass: "btn-primary",
//                 confirmButtonText: "Yes, Add it!",
//                 closeOnConfirm: false,
//                 showLoaderOnConfirm: true
//           },
//               function(){


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
                   var requestData = {
                       'solId' : vm.solId,
                       'userName' : vm.username,
                       'schemeName' : vm.schemeName.toUpperCase()
                   };
                   schemeMasterRbiBondService.submitAddForm(requestData).then(function (response){
                        Swal.fire("Added!", "Scheme Name Added Successfully", "success");
                       $state.go('scheme_master_rbi_bond.inquiry');
                   },function(error){
                       handleErrorResponse(error);
                        $state.go('scheme_master_rbi_bond.add');
                   })
                 }  });
            }

             function inquiry(flg){
                        vm.loader = true;
                        if(flg == 'T'){
                            var requestData = {'flg': 'T' };
                        }
                        else{
                         var requestData = {
                               'flg' : 'D',
                               'solId' : vm.solId,
                               'userName' : vm.username,
                               'schemeName':vm.schemeName
                            }
                        }
                        schemeMasterRbiBondService.inquiry(requestData).then(function(response){
                            vm.loader = false;
                            if(flg == 'T'){
                                vm.schemeNameList = response.data.result;
                                angular.element('#levelNameModal').modal('toggle');
                            }else{
                                vm.details = response.data.result;
                                vm.masterOldNewValueModel = response.data.result.masterOldNewValueModel;
                                if(vm.masterOldNewValueModel.C004.oldValue=='Y'){
                                vm.masterOldNewValueModel.C004.oldValue='ENABLE';
                                }else{
                                vm.masterOldNewValueModel.C004.oldValue='DISABLE';
                                }
                                 if(vm.masterOldNewValueModel.C004.newValue=='Y'){
                                  vm.masterOldNewValueModel.C004.newValue='ENABLE';
                                  }else{
                                  vm.masterOldNewValueModel.C004.newValue='DISABLE';
                                  }
                               $state.go('scheme_master_rbi_bond.inquiry_detail');
                            }
                        }, function(error){
                            vm.loader = false;
                            handleErrorResponse(error);
                        })
                    }
                      function verifySubmit() {
//                                                        Swal.fire({
//                                                                title: "Are you sure?",
//                                                                text: "Do you want to Verify this record ? ",
//                                                                type: "warning",
//                                                                showCancelButton: true,
//                                                                cancelButtonClass: "btn-cancel",
//                                                                confirmButtonClass: "btn-primary",
//                                                                confirmButtonText: "Yes!",
//                                                                closeOnConfirm: false,
//                                                                showLoaderOnConfirm: true
//                                                            },
//                                                            function() {





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
                    var requestData = {};
                    requestData.solId = vm.solId;
                    requestData.userName = vm.username;
                    requestData.schemeName = $stateParams.obj.schemeName;
                    requestData.status = $stateParams.obj.status;
                    requestData.pkColumnValue = vm.pkColumnValue;
                    schemeMasterRbiBondService.verifySubmit(requestData)
                        .then(
                            function(response) {
                                vm.loader = false;
                                Swal.fire("Verified!","Scheme Name Verified Successfully","success");
                                $state.go('scheme_master_rbi_bond.inquiry_detail');
                            }, function(error){
                                handleErrorResponse(error);
                                $state.go('scheme_master_rbi_bond.modify');
                            }
                        ); } });
                        }
function deleteSubmit() {
//    function handleKeyPress(event) {
//        var key = event.key;
//        var input = event.target.value;
//
//        if (key === ' ' && input.slice(-1) === ' ') {
//            event.preventDefault();
//        }
//
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
//        showLoaderOnConfirm: true,
//        inputValidator: function(value) {
//
//            if (/^[a-zA-Z\s]*$/.test(value.trim())) {
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
//        if (inputValue === false) {
//            document.removeEventListener('keypress', handleKeyPress);
//            return false;
//        }
//
//        if (inputValue === "") {
//            Swal.fire.showInputError("Please enter the remarks!");
//            return false;
//        }

        vm.loader = true;
        var requestData = {
            pkColumnValue: vm.pkColumnValue,
            remarks: vm.remarks
        };

        schemeMasterRbiBondService.deleteSubmit(requestData)
            .then(function(response) {
                vm.loader = false;
                Swal.fire("Rejected!", "Scheme Name Rejected Successfully", "success");
                $state.go('scheme_master_rbi_bond.inquiry_detail');
                const backdrop = document.querySelector('.modal-backdrop');
                if (backdrop) {
                  backdrop.parentNode.removeChild(backdrop);
                  document.body.classList.remove('modal-open');
                }
            })
            .catch(function(error) {
                vm.loader = false;
                handleErrorResponse(error);
                const backdrop = document.querySelector('.modal-backdrop');
                if (backdrop) {
                  backdrop.parentNode.removeChild(backdrop);
                  document.body.classList.remove('modal-open');
                }
                $state.go('scheme_master_rbi_bond.verify');
            });

}

  function rejectReason() {
      angular.element("#permissionMappingDelete").modal("toggle");
    }

function modifySelectDetails(flg){
                        vm.loader = true;
                        if(flg == 'T'){
                            var requestData = {'flg' : 'T'};
                        }
                        else{
                         var requestData = {
                               'flg' : 'D',
                               'solId' : vm.solId,
                               'userName' : vm.username,
                               'schemeName':vm.schemeName
                            }
                        }
                        schemeMasterRbiBondService.modifySelectDetails(requestData).then(function(response){
                            vm.loader = false;
                            if(flg == 'T'){
                                vm.schemeNameList = response.data.result;
                                angular.element('#levelNameModal').modal('toggle');
                            }else{
                                vm.details = response.data.result;
                                vm.masterOldNewValueModel = response.data.result.masterOldNewValueModel;
                                vm.pkColumnValue=response.data.result.pkColumnValue;
                               $state.go('scheme_master_rbi_bond.modify_detail');
                            }
                        }, function(error){
                            vm.loader = false;
                            handleErrorResponse(error);
                        })
                    }
function verifySelectDetails(flg){
                        vm.loader = true;
                        if(flg == 'T'){
                            var requestData = {'flg' : 'T'};
                        }
                        else{
                         var requestData = {
                                'flg' : 'D',
                               'solId' : vm.solId,
                               'userName' : vm.username,
                               'schemeName':vm.schemeName
                            }
                        }
                        schemeMasterRbiBondService.verifySelectDetails(requestData).then(function(response){
                            vm.loader = false;
                            if(flg == 'T'){
                                vm.schemeNameList = response.data.result;
                                angular.element('#levelNameModal').modal('toggle');
                            }else{
                                vm.details = response.data.result;
                                vm.masterOldNewValueModel = response.data.result.masterOldNewValueModel;
                                vm.pkColumnValue=response.data.result.pkColumnValue;
                                 if(vm.masterOldNewValueModel.C004.oldValue=='Y'){
                                  vm.masterOldNewValueModel.C004.oldValue='ENABLE';
                                  }else{
                                  vm.masterOldNewValueModel.C004.oldValue='DISABLE';
                                  }
                                  if(vm.masterOldNewValueModel.C004.newValue=='Y'){
                                  vm.masterOldNewValueModel.C004.newValue='ENABLE';
                                  }else{
                                  vm.masterOldNewValueModel.C004.newValue='DISABLE';
                                  }
                               $state.go('scheme_master_rbi_bond.verify_detail');
                            }
                        }, function(error){
                            vm.loader = false;
                            handleErrorResponse(error);
                        })
                    }

 function modifySubmit() {
//            Swal.fire({
//                    title: "Are you sure?",
//                    text: "Do you  want to Modify this record? ",
//                    type: "warning",
//                    showCancelButton: true,
//                    cancelButtonClass: "btn-cancel",
//                    confirmButtonClass: "btn-primary",
//                    confirmButtonText: "Yes!",
//                    closeOnConfirm: false,
//                    showLoaderOnConfirm: true
//                },
//                function() {



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
                    let request = {
                      'pkColumnValue':vm.pkColumnValue,
				      'columnValues': convertToColumnValuesArrayOldNew(vm.masterOldNewValueModel)
                    }
                    schemeMasterRbiBondService.modifySubmit(request)
                    .then(function(response) {
                            Swal.fire("Modified!", "Scheme Name Modified Successfully", "success");
                            location.href = '#!/scheme_master_rbi_bond/action/inquiry';
                        }, handleErrorResponse
                    );
               } });
        }
        function convertToColumnValuesArrayOldNew(objectToBeConvert) {
        			if (typeof (objectToBeConvert) === "object") {
        				let resultArray = [];
        				for (let key of Object.keys(objectToBeConvert)) {
        					if (objectToBeConvert[key])
        						resultArray.push({
        							colName: key,
        							value: objectToBeConvert[key].oldValue
        						})
        				}
        				return resultArray;
        			} else {
        				throw "Only objects can be converted"
        			}
        		}

function handleErrorResponse(response) {
            vm.loader = false;
            var msg = response.data.message ? response.data.message : "Server Error";
            Swal.fire("Error!", msg, "error");
            location.href = '#!/scheme_master_rbi_bond/action/inquiry';
        }
}
})();