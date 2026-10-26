(function() {

	'use strict';

	angular.module('gbmUltraApp').controller(
			'rbibondHUFDeathMarkingMenuController',
			rbibondHUFDeathMarkingMenuController);

	rbibondHUFDeathMarkingMenuController.$inject = [ '$scope', '$filter',
			'$state', '$stateParams', '$location', '$rootScope','rbibondHUFDeathMarkingMenuService', 'commonService' ];

	function rbibondHUFDeathMarkingMenuController($scope, $filter, $state,
			$stateParams, $location, $rootScope, rbibondHUFDeathMarkingMenuService,
			commonService) {

		var vm = this;
vm.textableDatePicker=textableDatePicker;
vm.autoslashing=autoslashing;

function textableDatePicker(id,valueDate)
{
 commonService.textableDatePicker(id,valueDate);
 const inputDate = new Date(valueDate);
     const today = new Date();
  if (id === 'dodDate') {
         const fromDate = new Date(vm.latestSubIdDate);
         if (inputDate < fromDate) {
             vm.dodDate = null;
             Swal.fire("Warning!", "Bond Holder's Deceased Date must be greater than the Subscription Date", "warning");
             return false;
         }else if(inputDate > today){
             vm.dodDate = null;
              Swal.fire("Warning!", "Bond Holder's Deceased Date must be less than Current Date", "warning");
              return false;
         }else if (null!=vm.certDate&&inputDate > vm.certDate) {
              vm.certDate = null;
              Swal.fire("Warning!", "Date of Receipt must be greater than Date Of Death", "warning");
              return false;
          }
         }
   if (id === 'certDate') {
         const dodDate = new Date(vm.dodDate);
        if (inputDate < dodDate) {
            vm.certDate = null;
            Swal.fire("Warning!", "Date of Receipt must be greater than Date Of Death", "warning");
            return false;
        }else if(inputDate > today){
          vm.certDate = null;
           Swal.fire("Warning!", "Date of Receipt must be less than Current Date", "warning");
           return false;
      }
    }
    return true;
}
function autoslashing(id)
{
 commonService.autoslashing(id);
}



        vm.currentPage = 1;
        vm.itemsPerPage = '5';
        vm.getRemarksList = getRemarksList;
        vm.loginSolId = $rootScope.globals.currentUser.solId;
        vm.loginSolDesc = $rootScope.globals.currentUser.solDesc;
        vm.solDate = $rootScope.globals.currentUser.systemDate;
        vm.currentDate = new Date();
        //vm.currentDate = $filter('date')(vm.currentDate, 'dd/MM/yyyy');
       // vm.getModuleName=getModuleName;
         vm.printpage = printpage;
         vm.remarks=null;
        vm.dateValidationMark = dateValidationMark;

        vm.getAddPanNumberDtls=getAddPanNumberDtls;
             vm.getCoParcenerDtls =getCoParcenerDtls;
        vm.getVerDelPanNumberDtls=getVerDelPanNumberDtls;
        vm.getInqPanNumberDtls=getInqPanNumberDtls;
         vm.submitAddForm = submitAddForm;
         vm.showCancelConfirmation=showCancelConfirmation;
          vm.getAddDetails=getAddDetails;
          vm.getVerifyDetails=getVerifyDetails;
          vm.getVerifySubmit=getVerifySubmit;
          vm.getDeleteSubmit=getDeleteSubmit;
          vm.getInquiryDetails = getInquiryDetails;
        vm.solDate = $rootScope.globals.currentUser.systemDate;
        vm.rejectReason = rejectReason;
        vm.validateInput = validateInput;
        vm.dateValidation=dateValidation;
        vm.panPattern= /[a-zA-Z]{5}[0-9]{4}[a-zA-Z]{1}/;
        vm.addList= {};
        vm.addResList= [];
        vm.latestSubIdDate='';



     if($state.current.name == 'huf_death_marking_menu.add_detail') {
     		    if($stateParams.obj){
                     vm.panNumber = $stateParams.obj.panNo;
                     vm.schemeCode = $stateParams.obj.schemeCode;
                      vm.bondHolderName=$stateParams.obj.bondHolderName;
                        vm.power=$stateParams.obj.power;
                     if (!vm.panPattern.test(vm.panNumber)) {
                         Swal.fire("Please enter a valid PAN number");
                         $state.go('death_marking_for_bla.add');
                         vm.loader = false;
                       }
                      vm.getAddDetails();
     		    }
               }


     else if($state.current.name == 'huf_death_marking_menu.verify_detail') {
              		    if($stateParams.obj){
                              vm.panNumber = $stateParams.obj.panNo;
                              vm.schemeCode = $stateParams.obj.schemeCode;
                              vm.panId=$stateParams.obj.panId;
                                vm.power=$stateParams.obj.power;
                           if (!vm.panPattern.test(vm.panNumber)) {
                             Swal.fire("Please enter a valid PAN number");
                             $state.go('huf_death_marking_menu.verify');
                             vm.loader = false;
                           }
                              vm.getVerifyDetails();

              		    }
                        }

     else   if ($state.current.name == 'huf_death_marking_menu.inquiry_detail'){
                    if ($stateParams.obj) {
                        vm.panNumber = $stateParams.obj.panNumber;
                        vm.schemeCode = $stateParams.obj.schemeCode;
                        vm.power= $stateParams.obj.power;
                        if (!vm.panPattern.test(vm.panNumber)) {
                        Swal.fire("Please enter a valid PAN number");
                        $state.go('huf_death_marking_menu.inquiry');
                        vm.loader = false;
                      }
                        vm.getInquiryDetails();
                 }
             }



    function getRemarksList() {
                    vm.referenceId = '056';
                    var requestData = { 'referenceId': vm.referenceId };

                    rbibondHUFDeathMarkingMenuService.getRemarksList(requestData).then(function(response) {
                        vm.remarksList = response.data.result || []; // Ensure it's an array
                    }, function(error) {
                        handleErrorResponse(error);
                    });
                }



        function configDataTable() {
                    vm.getTitleDescription = {
                        title: 'Deceased Marking for BLA Register',
                        solId: $rootScope.globals.currentUser.solId,
                        solDesc: $rootScope.globals.currentUser.solDesc
                    }
                    vm.dataTableOption = configDataTableOptions(vm.getTitleDescription);
                }



        function printpage(){
                            window.print()
                          }

function getAddPanNumberDtls() {
    if (!vm.panNumber || vm.panNumber.trim() === '') {
        Swal.fire("Please enter PAN number");
        return false;
    }
    if (vm.panNumber.charAt(3).toUpperCase() !== 'H') {
        vm.loader = false;
        handleErrorResponse({
            data: {
                message: 'HUF PAN No only allowed'
            }
        });
        vm.panNumber="";
        return;
    }

    vm.loader = true;

    var requestData = {
        'panNo': vm.panNumber,
        'schemeCode': vm.schemeCode
    };

    rbibondHUFDeathMarkingMenuService.getAddPanNumberDtls(requestData).then(function (response) {
        vm.loader = false;


        vm.panNumberDetailList = response.data.result;


        angular.element('#panNoModal').modal('toggle');
    }, function (error) {
        vm.loader = false;
        handleErrorResponse(error);
        $state.go('huf_death_marking_menu.inquiry');
    });
}




function getCoParcenerDtls() {

    vm.loader = true;

    var requestData = {
        'panNo': vm.panNumber,
    };

    rbibondHUFDeathMarkingMenuService.getCoParcenerDtls(requestData).then(function (response) {
        vm.loader = false;
        vm.coParcenerDtlsList = response.data.result;
            angular.element('#coParcenerModal').modal('toggle');
    }, function (error) {
        vm.loader = false;
        handleErrorResponse(error);
        $state.go('huf_death_marking_menu.add');
    });
}

vm.selectCoParcener = function(item){

    vm.newKartaName = item.coParcenerName;

    // Optional
   vm.relationshipWithKarta = item.relationship;
  vm.coParcenerDob = $filter('date')(new Date(item.dob), 'dd-MM-yyyy');
};
vm.formatDate = function(date) {
    if (!date) return '';
    return moment(date).format('DD-MM-YYYY');
};







       function getVerDelPanNumberDtls() {

                                                   vm.loader = true;

                                                   var requestData = {
                                                       'panNo': vm.panNumber,
                                                       'schemeCode': vm.schemeCode,


                                                   }
                                                   rbibondHUFDeathMarkingMenuService.getVerDelPanNumberDtls(requestData).then(function(response) {
                                                       vm.loader = false;
                                                       vm.panNumberDetailList = response.data.result;
                                                        angular.element('#panNoModal').modal('toggle');
                                                   }, function(error) {
                                                       handleErrorResponse(error);
                                                       //vm.loader = false;
                                                       $state.go('huf_death_marking_menu.inquiry');
                                                   });


                                               }



       function getInqPanNumberDtls() {
                                        if(!vm.panNumber || vm.panNumber.trim() === '') {
                                             				Swal.fire("Please enter PAN number");
                                             				return false;
                                             			}

                             			   if (vm.panNumber.charAt(3).toUpperCase() !== 'H') {
                                                vm.loader = false;
                                                handleErrorResponse({
                                                    data: {
                                                        message: 'HUF PAN No only allowed'
                                                    }
                                                });
                                                vm.panNumber="";
                                                return;
                                            }
                                                            vm.loader = true;

                                                                 var requestData = {
                                                                     'panNo': vm.panNumber,
                                                                     'schemeCode': vm.schemeCode,

                                                                 }
                                                                 rbibondHUFDeathMarkingMenuService.getInqPanNumberDtls(requestData).then(function(response) {
                                                                     vm.loader = false;
                                                                     vm.panNumberDetailList = response.data.result;
                                                                      angular.element('#panNoModal').modal('toggle');
                                                                 }, function(error) {
                                                                     handleErrorResponse(error);
                                                                     vm.loader = false;
                                                                 });


                                                             }


     function submitAddForm(){

                              Swal.fire({
                                title: "Are you sure?",
                                text: "Do you want to Really Add/Modify this record?",
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
      vm.power = typeof vm.power === "undefined" ? null :vm.power;
      vm.panNumber = typeof vm.panNumber === "undefined" ? null :vm.panNumber;
      vm.dodDate = typeof vm.dodDate === "undefined" ? null : moment(vm.dodDate).format("DD-MMM-YYYY");
      vm.certDate = typeof vm.certDate === "undefined" ? null : moment(vm.certDate).format("DD-MMM-YYYY");
      vm.dateFrom = typeof vm.dateFrom === "undefined" || !moment(vm.dateFrom).isValid() ? null : moment(vm.dateFrom).format("DD-MMM-YYYY");
      vm.remarks = typeof vm.remarks === "undefined" ? null :vm.remarks;
       vm.newKartaName = typeof vm.newKartaName === "undefined" ? null :vm.newKartaName;
      vm.relationshipWithKarta = typeof vm.relationshipWithKarta === "undefined" ? null :vm.relationshipWithKarta;
     // vm.coParcenerDob = typeof vm.coParcenerDob === "undefined" || !moment(vm.coParcenerDob).isValid() ? null : moment(vm.coParcenerDob).format("DD-MMM-YYYY");
vm.coParcenerDob = moment(vm.coParcenerDob, "DD-MMM-YYYY").format("DD-MMM-YYYY");


            vm.loader = true;
            var requestData = {
                   'bondHolderName':vm.power,
                   'panNo':vm.panNumber,
                   'dodDate':vm.dodDate,
                   'certDate':vm.certDate,
                   'markingDate':vm.dateFrom,
                   'remarks':vm.remarks,
                   'newKartaName': vm.newKartaName,
                   'relationshipWithKarta': vm.relationshipWithKarta,
                   'coParcenerDob':vm.coParcenerDob

            };
            rbibondHUFDeathMarkingMenuService.getAddSubmit(requestData).then(
                        (response) => {
                            vm.loader = false;
vm.kcrnNo=response.data.result;
                              var message=response.data.message;

                           Swal.fire(
                               "Added!",
                               "HUF Death Marked Successfully " + vm.kcrnNo,
                               "success"
                           );
                            location.href = '#!/huf_death_marking_menu/action/inquiry';
                        },
                        function(error) {
                            vm.loader = false;
                            handleErrorResponse(error);
                              const errorMessage = err && err.data && err.data.message
                                    ? err.data.message
                                    : "An unexpected error occurred.";
                            if(null!=errorMessage && errorMessage.startsWith("Invalid")){
                            Swal.fire("Warning!", errorMessage , "warning");
                            $state.go("huf_death_marking_menu.inquiry");
                            }else{
                              handleErrorResponse(error);
                              $state.go("huf_death_marking_menu.inquiry");
                              }

                        });
                        }

});

                     }


vm.clearCoParcener = function () {
    vm.newKartaName = "";
    vm.newKartaPan = "";
    vm.coParcenerDob = "";
    vm.newKartaId = "";
};


     function getVerifySubmit(){


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

                           var requestData = {
                          'panId': vm.panId,
                           };
                             rbibondHUFDeathMarkingMenuService.getVerifySubmit(requestData).then(
                                                         (response) => {
                                                             vm.loader = false;

                                                            var message=response.data.message;

                                                           Swal.fire("Verified!","HUF Death Marked Verified Successfully "+ vm.addList.kcrnNo,"success");
                                                             location.href = '#!/huf_death_marking_menu/action/inquiry';
                                                         },
                                                         function(error) {
                                                             vm.loader = false;
                                                             handleErrorResponse(error);

                                                         });
                                                         }

                             });
          }

function validateInput(event, id) {
    const textBox = document.getElementById(id);

    var charCode = event.which || event.keyCode;
    var charTyped = String.fromCharCode(charCode);

    if (textBox) {
        const cursorPosition = textBox.selectionStart;
        if (cursorPosition === 0 && charTyped === ' ') {
            event.preventDefault();
            return;
        }
    }

    var currentValue = event.target.value + charTyped;

    var pattern = /^[a-zA-Z0-9\s']*$/;

    if (!pattern.test(currentValue)) {
        event.preventDefault();
    }
}


    function rejectReason() {
                 angular.element('#permissionMappingDelete').modal('toggle');
                 }





   function getDeleteSubmit(){




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



                                   var requestData = {
                                              'panId': vm.panId,
                                             'rejectRemarks': vm.rejectionRemarks
                                         };

                                     rbibondHUFDeathMarkingMenuService.getDeleteSubmit(requestData).then(
                                                                       (response) => {
                                                                           vm.loader = false;

                                                                          var message=response.data.message;

                                                                         Swal.fire("Rejected!","Rejected Successfully","success");
                                                                           location.href = '#!/huf_death_marking_menu/action/inquiry';
                                                                       },
                                                                       function(error) {
                                                                           vm.loader = false;
                                                                           handleErrorResponse(error);

                                                                       });

                                         }  });
                        }





            // Cancel Confirmation
                    function showCancelConfirmation(state) {

                      Swal.fire({
                        title: "Are you sure?",
                        text: "Do you Really Want to Cancel?",
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


          function getAddDetails(){

                                 vm.loader = true;
                                            var requestData = {
                                                'panNo':vm.panNumber,
                                                 'schemeCode':vm.schemeCode
                                             }

                                            rbibondHUFDeathMarkingMenuService.getAdd(requestData).then(function(response) {
                                                vm.loader = false;
                                                vm.addList = response.data.result[0];
                                                vm.latestSubIdDate= vm.addList.latestSubIdDate;
                                                  vm.latestSubIdDate = moment(vm.latestSubIdDate, "DD-MM-YYYY").toDate(); // For
                                                vm.addResList = response.data.result;
                                                dateValidation('dod');
                                dateValidationMark('Marking');
                                        configDataTable();
                                   }, function(error){
                                       vm.loader = false;
                                       handleErrorResponse(error);
                           if (error.data.message == 'No Data Found') {
                              vm.noDataFound = error.data.message;
                          } else {
                              handleErrorResponse(error);
                              $state.go('huf_death_marking_menu.inquiry')
                          }
                         })
                         }

        function getVerifyDetails(){

             vm.loader = true;
                        var requestData = {
                            'panNo':vm.panNumber,
                            'schemeCode': vm.schemeCode,
                            'panId':vm.panId,
                         }

                        rbibondHUFDeathMarkingMenuService.getVerify(requestData).then(function(response) {
                            vm.loader = false;
                           vm.addList = response.data.result;
                           vm.addResList = response.data.result.holderDetails;
                            vm.remarks=vm.addList.remarks;
                            vm.coParcenerDob=response.data.result.coParcenerDob;
                             vm.relationshipWithKarta=response.data.result.relationshipWithKarta;
                              vm.newKartaName=response.data.result.newKartaName;
                    configDataTable();
               }, function(error){
                   vm.loader = false;
  const errorMessage = err && err.data && err.data.message
                                                            ? err.data.message
                                                            : "An unexpected error occurred.";

                                                   if(null!=errorMessage && errorMessage.startsWith("Invalid")){
                                                 handleErrorResponse(error);
                                                    Swal.fire("Warning!", errorMessage , "warning");
                                                    $state.go("huf_death_marking_menu.inquiry");
                                                    }
      if (error.data.message == 'No Data Found') {
          vm.noDataFound = error.data.message;
          handleErrorResponse(error);
      } else {

         handleErrorResponse(error);
         $state.go('huf_death_marking_menu.inquiry')

      }

               })
           }

         function getInquiryDetails(){
                 vm.loader = true;
                                var requestData = {
                                    'panNo':vm.panNumber,
                                    'schemeCode': vm.schemeCode,
                                 }

                                rbibondHUFDeathMarkingMenuService.getInquiry(requestData).then(function(response) {
                                    vm.loader = false;
                                     vm.addList = response.data.result;
                                      vm.addResList = response.data.result.holderDetails;
                                      vm.remarks=vm.addList.remarks;
                                      vm.coParcenerDob=response.data.result.coParcenerDob;
                                      vm.relationshipWithKarta=response.data.result.relationshipWithKarta;
                                      vm.newKartaName=response.data.result.newKartaName;

                            configDataTable();
                       }, function(error){
                           vm.loader = false;
                            const errorMessage = err && err.data && err.data.message
                            ? err.data.message
                            : "An unexpected error occurred.";
                    if(null!=errorMessage && errorMessage.startsWith("Invalid")){
                    Swal.fire("Warning!", errorMessage , "warning");
                    handleErrorResponse(error);
                    $state.go("huf_death_marking_menu.inquiry");
                    }
              if (error.data.message == 'No Data Found') {
                  vm.noDataFound = error.data.message;
                  handleErrorResponse(error);
              } else {

               handleErrorResponse(error);
               $state.go('huf_death_marking_menu.inquiry')

              }

                       })
                   }




  vm.currentDate = new Date();
         function dateValidation(flag) {

                    if(flag=='dod'){
                    vm.certDate='';
                     vm.dateFrom='';
                    }

                     vm.dateToOptions = {

                         minDate:vm.latestSubIdDate,
                          maxDate:  vm.currentDate,

                    };
                    vm.dateOptions = {
                        minDate:vm.dodDate,
                        maxDate:  vm.currentDate,
                     }

                 }
  function dateValidationMark(flag) {

                    vm.dateMarkOptions = {
                        minDate:vm.solDate,
                        maxDate:vm.solDate,
                     }
               vm.dateFrom = vm.solDate;
                 }









          const convertToColumnValuesArrayList = (objectToBeConvert) => {
                                         if (typeof objectToBeConvert === "object") {
                                             let resultArrayOther = [];
                                             for (let key of Object.keys(objectToBeConvert)) {
                                                 // Check for null or undefined, not just truthiness
                                                 if (objectToBeConvert[key] !== undefined) {
                                                     resultArrayOther.push({ colName: key, value: objectToBeConvert[key] });
                                                 }
                                             }
                                             return resultArrayOther;
                                         } else {
                                             throw "Only objects can be converted";
                                         }
                                     };









	}
})();