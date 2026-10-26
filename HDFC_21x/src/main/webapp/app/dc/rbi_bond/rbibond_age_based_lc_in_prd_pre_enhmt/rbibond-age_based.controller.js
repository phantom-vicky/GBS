


(function () {

	'use strict';

	angular.module('gbmUltraApp').controller('rbiBondAgeBasedController',
		rbiBondAgeBasedController);

	rbiBondAgeBasedController.$inject = ['$scope', '$filter', '$state', '$rootScope',
		'$stateParams', '$location', '$timeout', 'rbiBondAgeBasedService', 'commonService'
	];



	function rbiBondAgeBasedController($scope, $filter, $state, $rootScope, $stateParams,
		$location, $timeout, rbiBondAgeBasedService, commonService) {

		var vm = this;
		vm.commonYesNoMetaData = COMMON_YES_NO_METADATA;
		vm.solDate = $rootScope.globals.currentUser.systemDate;

		//vm.getSchemeName = getSchemeName;
		// vm.moduleNameBefore = "Floating Rate Savings Taxable Bonds, 2020";
        vm.getRemarksList = getRemarksList;
		vm.checkRecordExisted = checkRecordExisted;
		vm.addAgeBasedCreation = addAgeBasedCreation;
		vm.validatePoolAcct = validatePoolAcct;
		vm.modifyAgeBasedCreation = modifyAgeBasedCreation;
		vm.verifyAgeBasedCreation = verifyAgeBasedCreation;
		vm.deleteAgeBasedCreation = deleteAgeBasedCreation;
		vm.getModifySelect = getModifySelect;
		vm.getInquiryDetails = getInquiryDetails;
		vm.tranAmountValidation = tranAmountValidation;
		vm.getUnverifiedRecords = getUnverifiedRecords;
		vm.getEffectFromDate = getEffectFromDate;
		vm.chooseDescription = chooseDescription;
  		vm.rejectReason = rejectReason;

//		vm.schemeTorchFunction = schemeTorchFunction;
        vm.timePattern=  /(?:[01]\d|2[0-3]):(?:[0-5]\d):(?:[0-5]\d)/;
        vm.numVal=numVal;
//		getModuleName();
        vm.addRange = addRange;
        vm.removeRange = removeRange;
        vm.alert = alert;
		vm.showCancelConfirmation = showCancelConfirmation;
		 vm.addAgeBasedList = [{}];

		 vm.addAgeBasedListListData = [[{}]];
//        vm.resultAddArray = [];
//        vm.resultModifyArray = [];
		  vm.currentMemberIndex =0;
		  vm.addRangeForAdd = addRangeForAdd;
		  vm.removeRangeForAdd = removeRangeForAdd;
vm.validateInput=validateInput;
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



vm.minValueOne=minValueOne;
    function minValueOne(event,entity,index) {
               let value = parseInt(event.target.value);
               // Common minimum value validation
               if (value === 0 || isNaN(value)) {
                   Swal.fire("Error!", "Minimum value is 1", "error");
                   vm.addAgeBasedList[index][entity] = '';
                   return;
               }
               if (entity === 'subscriberAgeBracketFrom') {
                  let subscriberAgeBracketFrom = parseInt(vm.addAgeBasedObj[index]['subscriberAgeBracketFrom']);
                  let subscriberAgeBracketTo = parseInt(vm.addAgeBasedObj[index]['subscriberAgeBracketTo']);
                  if (null!=subscriberAgeBracketFrom&&null!=subscriberAgeBracketTo&& subscriberAgeBracketFrom >= subscriberAgeBracketFrom) {
                     Swal.fire("Warning!", "Subscriber’s Age Bracket From must be less than To", "warning");
                      vm.addAgeBasedList[index]['subscriberAgeBracketFrom'] = '';
                      return;
                  }
              }
               if (entity === 'subscriberAgeBracketTo') {
                   let subscriberAgeBracketFrom = parseInt(vm.addAgeBasedObj[index]['subscriberAgeBracketFrom']);
                   let subscriberAgeBracketTo = parseInt(vm.addAgeBasedObj[index]['subscriberAgeBracketTo']);
                   if (null!=subscriberAgeBracketFrom&&null!=subscriberAgeBracketTo&& subscriberAgeBracketTo <= subscriberAgeBracketFrom) {
                     Swal.fire("Warning!", "Subscriber’s Age Bracket To must be greater than From", "warning");
                       vm.addAgeBasedList[index]['subscriberAgeBracketTo'] = '';
                       return;
                   }
               }
        }

        vm.minValueOneM=minValueOneM;
        function minValueOneM(event,entity,index) {
            let value = parseInt(event.target.value);
               // Common minimum value validation
               if (value === 0 || isNaN(value)) {
                   Swal.fire("Error!", "Minimum value is 1", "error");
                   vm.addAgeBasedObj[index][entity]['oldValue'] = '';
                   return;
               }
               if (entity === 'C005') {
                  let c005Val = parseInt(vm.addAgeBasedObj[index]['C005']['oldValue']);
                  let c006Val = parseInt(vm.addAgeBasedObj[index]['C006']['oldValue']);
                  if (null!=c005Val&&null!=c006Val&& c005Val >= c006Val) {
                      Swal.fire("Warning!", "Subscriber’s Age Bracket From must be less than To", "warning");
                      vm.addAgeBasedObj[index]['C005']['oldValue'] = '';
                      return;
                  }
              }
               // Additional check: If entity is C006, validate C005 first
               if (entity === 'C006') {
                   let c005Val = parseInt(vm.addAgeBasedObj[index]['C005']['oldValue']);
                   let c006Val = parseInt(vm.addAgeBasedObj[index]['C006']['oldValue']);
                   if (null!=c005Val&&null!=c006Val&& c006Val <= c005Val) {
                       Swal.fire("Warning!", "Subscriber’s Age Bracket To must be greater than From", "warning");
                       vm.addAgeBasedObj[index]['C006']['oldValue'] = '';
                       return;
                   }
               }
            }
          function addRangeForAdd() {
              if (vm.addAgeBasedList.length <=9) {
                  vm.addAgeBasedList.push({});
                  vm.disableDelete = true;
              }
          }
 function rejectReason() {
              angular.element('#permissionMappingDelete').modal('toggle');
              }

              function getRemarksList() {
                            vm.referenceId = '055';
                            var requestData = { 'referenceId': vm.referenceId };
                            rbiBondAgeBasedService.getRemarksList(requestData).then(function (response) {
                              vm.remarksList = response.data.result || []; // Ensure it's an array
                            }, function (error) {
                              handleErrorResponse(error);
                            });
                          }

          function removeRangeForAdd() {
              if (vm.addAgeBasedList.length > 1) {
                  vm.lastRow = vm.addAgeBasedList.length - 1;
                  vm.addAgeBasedList.splice(vm.lastRow, 1);
                  if (vm.lastRow == 1) {
                      vm.disableDelete = false;
                  }
              } else {
                  vm.disableDelete = false;
              }

          }

//                          function chooseDescription(item){
//
//                           for (let i = 0 ;i < vm.schemeList.length;i++){
//                            if(vm.schemeList[i].subCode == item){
//                                  vm.subCodeDescription = vm.schemeList[i].subCodeDescription;
//                                 //vm.cusName = vm.custList[i].cusName;
//                            //  vm.cusAlphaCode = vm.custList[i].cusAlphaCode;
//                             // vm.id = vm.custList[i].id;
//                             }
//                           }
//                        }
function chooseDescription(item) {
    for (let i = 0; i > vm.schemeList.length; i++) {
        if (vm.schemeList[i].subCode == item) {
            vm.subCodeDescription = vm.schemeList[i].subCodeDescription;

        }
    }
}

        if ($state.current.name == 'rbibond_age_based.add_detail') {
            if ($stateParams.obj) {
                vm.schemeCode = $stateParams.obj.schemeCode;
                vm.effFromDate = $stateParams.obj.effFromDate;
                vm.schemeName = $stateParams.obj.schemeName;
                vm.subCodeDescription = $stateParams.obj.subCodeDescription;
            }else{
                $state.go('rbibond_age_based.add');
            }
        }

        else if ($state.current.name == 'rbibond_age_based.verify_detail') {

            if ($stateParams.obj) {
			vm.versionNo = $stateParams.obj.versionNo;
			vm.effectiveFromDate = $stateParams.obj.effectiveFromDate;
			vm.schemeCode = $stateParams.obj.schemeCode;
//			vm.schemeName = $stateParams.obj.schemeName;
			vm.subCodeDescription = $stateParams.obj.subCodeDescription;
			  if (vm.schemeCode) {
                    vm.getUnverifiedRecords('main');
                }
            }
        }

        else if ($state.current.name == 'rbibond_age_based.delete_detail') {

            if ($stateParams.obj) {
			vm.versionNo = $stateParams.obj.versionNo;
			vm.effFromDate = $stateParams.obj.effFromDate;
			vm.schemeCode = $stateParams.obj.schemeCode;
//			vm.schemeName = $stateParams.obj.schemeName;
			vm.subCodeDescription = $stateParams.obj.subCodeDescription;
			    if (vm.schemeCode) {
                    vm.getUnverifiedRecords('main');
                }
            }
        }

		else if ($state.current.name == 'rbibond_age_based.inquiry_detail') {
			vm.versionNo = $stateParams.obj.versionNo;
			vm.effFromDate = $stateParams.obj.effFromDate;
			vm.schemeCode = $stateParams.obj.schemeCode;
//			vm.schemeName = $stateParams.obj.schemeName;
//			console.log('scheme Name'+vm.schemeName);
            vm.subCodeDescription = $stateParams.obj.subCodeDescription;
			if (vm.versionNo) {
				getInquiryDetails();
			}
		}

		else if ($state.current.name == 'rbibond_age_based.modify_details') {
        			vm.effFromDate = $stateParams.obj.effFromDate;
        			vm.schemeCode = $stateParams.obj.schemeCode;
        			vm.schemeName = $stateParams.obj.schemeName;
        			vm.versionNo = $stateParams.obj.versionNo;
        			vm.effFromDateOld = $stateParams.obj.effFromDateOld;
        			vm.subCodeDescription = $stateParams.obj.subCodeDescription;
        			if (vm.effFromDate) {
        				getModifySelect();
        			}
        		}





//
//        function addRange() {
//
//            if (vm.addAgeBasedObj.length <=50) {
//                vm.addAgeBasedObj.push({});
//                vm.disableDelete = true;
//            }
//
//        }


//        function removeRange() {
//            if (vm.addAgeBasedObj.length > 1) {
//                vm.lastRow = vm.addAgeBasedObj.length - 1;
//                vm.addAgeBasedObj.splice(vm.lastRow, 1);
//                if (vm.lastRow == 1) {
//                    vm.disableDelete = false;
//                }
//            } else {
//                vm.disableDelete = false;
//            }
//
//        }
//function removeRange(index) {
//    vm.addAgeBasedObj.splice(index, 1); // Remove the item at the specified index from the array
//    if (vm.addAgeBasedObj.length === 0) { // Check if the array is empty after removal
//        vm.disableDelete = true; // Disable the delete button if there are no more rows
//    }
//}

////
//        function getEffectFromDate() {
//			vm.loader = true;
//			var requestData = {}
//                            rbiBondAgeBasedService.getModifySelect(requestData).then(function(response) {
//                                vm.loader = false;
//                                        vm.schemeList = response.data.result;
//                                        vm.schemeList.forEach(function(item) {
//                                                    vm.effFromDate = convertToDateObject(item.effectiveFromDate);
//                                                });
//
//
//				vm.effFromDateOptionsM = {
//					minDate: vm.effFromDate,
//					showWeeks: false
//				}
//			}, function (error) {
//				vm.loader = false;
//				handleErrorResponse(error)
//			});
//		}
function getEffectFromDate() {
    console.log("Selected scheme name:", vm.schemeName);
    vm.loader = true;
    var requestData = {};
    rbiBondAgeBasedService.getModifySelect(requestData).then(function(response) {
        vm.loader = false;
        vm.effFromDate = null;
        vm.schemeList.forEach(function(item) {
            if (item.subCodeDescription === vm.subCodeDescription) {
                vm.effFromDate = convertToDateObject(item.effectiveFromDate);
                vm.versionNo = item.versionNo;
                console.log("Effective from date for selected scheme:", vm.effFromDate);
            }
        });

        vm.effFromDateOptions = {
            minDate: vm.effFromDate,
            showWeeks: false
        };

        //$scope.$apply();
    }, function (error) {
        vm.loader = false;
        handleErrorResponse(error);
    });
}


//     vm.effFromDateOptions = {
//     			showWeeks: false
//     		}

       function tranAmountValidation(amt){
       if(amt<1){
        vm.cbdtSchemeParameter.C030 = '';
               vm.errorMsgForTranAmountValidation = true;
           }else {
               vm.errorMsgForTranAmountValidation = false;
           }
       }


               vm.dateOptions = {
                   showWeeks : false,
                   minDate : $rootScope.globals.currentUser.systemDate
               }

		//Get Module Name
//		function getModuleName() {
//			vm.loader = true;
//			vm.referenceId = 'TAX';
//			var requestData = {
//				'referenceId': vm.referenceId
//			};
//			rbiBondAgeBasedService.getRbiBondModuleNameDetails(requestData).then(function (response) {
//				vm.loader = false;
//				vm.schemeList = response.data.result;
//				//vm.schemeName = vm.schemeList[0].subCodeDescription;
//			}, function (error) {
//				handleErrorResponse(error);
//				vm.loader = false;
//			});
//		}



		function addAgeBasedCreation() {
            vm.effectiveFromDateStr = $filter('date')(vm.effFromDate, 'dd-MMM-yyyy');
            console.log(vm.addAgeBasedList);
            vm.finalList1 = vm.addAgeBasedList.map(function(item) {
                                        var cleanedItem = Object.assign({}, item);  // Shallow copy the item
                                        delete cleanedItem.$$hashKey;  // Delete the $$hashKey property
                                        return cleanedItem;
                                      });
			var requestData = {
				'values': vm.finalList1,
				 'schemeCode' :vm.schemeCode,
				 'effectiveFromDate' :vm.effectiveFromDateStr
			};
//			Swal.fire({
//					title: "Are you sure?",
//					text: "Do you want to Really Add this record?",
//					type: "warning",
//					showCancelButton: true,
//					cancelButtonClass: "btn-cancel",
//					confirmButtonClass: "btn-primary",
//					confirmButtonText: "Yes",
//					closeOnConfirm: false
//				},
//
//				function () {



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
					rbiBondAgeBasedService.addAgeBasedCreation(requestData).then(function (response) {
						vm.loader = false;
						Swal.fire("Added!", "Age Based Lock-in Period Details created Successfully", "success");
						$state.go('rbibond_age_based.inquiry')
					}, function (error) {
						vm.loader = false;
						handleErrorResponse(error);
					})
			}	});
		}

		function getModifySelect(flag) {

         vm.loader = true;


                        var requestData;
                        if (flag == 'Y') {
                            requestData = {'flag':'S'};

                            rbiBondAgeBasedService.getModifySelect(requestData).then(function(response) {
                                vm.loader = false;
                                vm.schemeList = response.data.result;
                                angular.element('#branchIdSearcher').modal('toggle');
                            }, function(error) {
                                handleErrorResponse(error);
                                vm.loader = false;
                                $state.go('rbibond_age_based.inquiry');
                            });
                        }else{
        			           vm.versionNo = $stateParams.obj.versionNo;
			                   vm.effFromDateFilter = $rootScope.formatDateToDDMMMYYYY(vm.effFromDate);
                    		    var requestData = {
                    		        'flag':'D',
                    				'effectiveFromDate': $filter('date')(vm.effFromDate, 'dd-MMM-yyyy'),
                    				'schemeCode': vm.schemeCode
                    			};
                    			rbiBondAgeBasedService.getModifySelect(requestData).then(function (response) {

                    				vm.loader = false;
                    				vm.auditInfo = response.data.result;
                    				vm.cbdtId = response.data.result.pkColumnValue;
                    				vm.addAgeBasedObj = response.data.result.oldNewValueList;
                    				vm.rejectionRemarks = response.data.result.rejectionRemarks;
//                    				vm.effFromDateOld = response.data.result.oldNewValueList[0].C003.oldValue;


var dateString = response.data.result.oldNewValueList[0].C003.oldValue;

// Split the date string into day, month, and year
var parts = dateString.split("/");
var day = parts[0];
var month = parts[1];
var year = parts[2];

// Convert month number to month abbreviation
var months = [
    "Jan", "Feb", "Mar", "Apr", "May", "Jun",
    "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"
];
var monthAbbreviation = months[parseInt(month) - 1].toLowerCase(); // Convert to lowercase
monthAbbreviation = monthAbbreviation.charAt(0).toUpperCase() + monthAbbreviation.slice(1); // Capitalize first letter

// Form the new date string in "dd-Mmm-yyyy" format
var formattedDate = `${day}-${monthAbbreviation}-${year}`;

// Assign the formatted date to vm.effFromDateOld
vm.effFromDateOld = formattedDate;



                                    angular.forEach(vm.addAgeBasedObj, function(item) {
                                    item.C007.oldValue = parseInt(item.C007.oldValue);
                                    item.C006.oldValue = parseInt(item.C006.oldValue);
                                    item.C005.oldValue = parseInt(item.C005.oldValue);
                                    });


                                    var currentStr = $filter('date')(vm.effFromDate , 'dd-MMM-yyyy');
                                    var dbStr = $filter('date')(vm.effFromDateOld , 'dd-MMM-yyyy');
                                    if (currentStr !== dbStr) {
                                        vm.showAddButton = true;
                                        } else {
                                        vm.showAddButton = false;
                                        }
                    				if (currentStr == dbStr) {
//                    					Swal.fire({
//                    						title: "Record already exists for the effective date",
//                    						text: "Do you wish to continue?",
//                    						type: "warning",
//                    						showCancelButton: true,
//                    						cancelButtonClass: "btn-cancel",
//                    						confirmButtonClass: "btn-danger",
//                    						confirmButtonText: "Yes",
//                    						cancelButtonText: "No",
//                    						closeOnConfirm: true
//                    					}, function (inputValue) {
//                    						if (!inputValue) {


                          Swal.fire({
                                            	title: "Record already exists for the effective date",
                                                               						text: "Do you wish to continue?",
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

                                                  if (!result.isConfirmed) {


                    							$state.go('rbibond_age_based.modify');
                    						}
                    					});
                    				}
                                        vm.effFromDateOptions = {
                    					minDate: vm.effFromDate,
                    					showWeeks: false
                    				}

                    			}, function (error) {
                    				vm.loader = false;
                    				handleErrorResponse(error);
                    				$state.go('rbibond_age_based.inquiry');
                    			});


		}
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

		  // format date from dd/MM/yyyy to yyyy-MM-dd
         function formatDate(tempDate){
                 return $filter('date')(tempDate, "dd-MMM-yyyy");
         }
function removeRange(ID, index) {
    if (!vm.deletedRows) {
        vm.deletedRows = [];
    }

    vm.item = vm.addAgeBasedObj.splice(index, 1)[0];

    if (vm.addAgeBasedObj.length === 0) {
        vm.disableDelete = true;
    }

    let deletionData = {};

    if (ID) {
        deletionData['ID'] = vm.item.ID.oldValue;
    }

    deletionData['C008'] = 'Y';


    vm.deletedRows.push(deletionData);
    vm.resultDelArray.push([deletionData]);
}

//function removeRange(ID, index) {
//    if (!vm.deletedRows) {
//        vm.deletedRows = [];
//    }
//
//    vm.item = vm.addAgeBasedObj.splice(index, 1)[0];
//
//    if (vm.addAgeBasedObj.length === 0) {
//        vm.disableDelete = true;
//    }
//
//    let deletionData = {};
//
//    if (ID) {
//        deletionData['ID'] = ID; // Use the passed ID instead of vm.item.ID.oldValue
//    }
//
//    deletionData['C008'] = 'Y';
//
//    vm.deletedRows.push(deletionData);
//    vm.resultDelArray.push([deletionData]);
//}

//function removeRange(ID, index) {
//    if (!vm.deletedRows) {
//        vm.deletedRows = [];
//    }
//
//    const item = vm.addAgeBasedObj.splice(index, 1)[0]; // Extracting the item from the array directly
//
//    if (vm.addAgeBasedObj.length === 0) {
//        vm.disableDelete = true;
//    }
//
//    let deletionData = [];
//
//    if (ID) {
//        deletionData.push({
//            colName: 'ID',
//            value: ID
//        });
//    }
//
//    deletionData.push({
//        colName: 'C008',
//        value: 'Y'
//    });
//
//    vm.deletedRows.push(deletionData);
//    vm.resultDelArray.push(deletionData);
//}


function addRange() {

    if (vm.addAgeBasedObj.length <= 9) {
        vm.addAgeBasedObj.push({});
        vm.addAgeBasedObj1=[];
        vm.disableDelete = true;
   vm.resultArray1 = [];
    }
}


function modifyAgeBasedCreation() {
//    Swal.fire({
//        title: "Are you sure?",
//        text: "Do you want to Really Modify this record?",
//        type: "warning",
//        showCancelButton: true,
//        cancelButtonClass: "btn-cancel",
//        confirmButtonClass: "btn-primary",
//        confirmButtonText: "Yes",
//        closeOnConfirm: false
//    },
//    function(yesFlg) {
//        if (yesFlg) {


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
            vm.resultAddArray = [];
            vm.resultDelArray = []; // Reset resultDelArray

            angular.forEach(vm.addAgeBasedObj, function(item) {
                if (item.C004) {
                    item.C004.oldValue = formatDate(convertToDateObject(item.C004.oldValue));
                }
                if (item.C001) {
                    item.C001.oldValue = vm.schemeCode;
                }
                if (item.C003) {
                    item.C003.oldValue = $filter('date')(vm.effFromDate, "dd-MMM-yyyy");
                }

                if (item.ID == null || item.ID == undefined) {
                    let newItem = Object.assign({}, item);
                    newItem.colName = "$$HASHKEY";
                    vm.resultAddArray.push(convertToColumnValuesArrayList(newItem));
                } else {
                    vm.resultArray.push(convertToColumnValuesArrayList(item));
                }
            });

            // Construct deletion data similar to columnValueDetailsList
            angular.forEach(vm.deletedRows, function(deletedItem) {
                let deletionData = [];
                angular.forEach(deletedItem, function(value, key) {
                    deletionData.push({ colName: key, value: value });
                });
                vm.resultDelArray.push(deletionData);
            });

            let requestData = {
                'schemeCode': vm.schemeCode,
                'columnValueDetailsList': vm.resultArray,
                'columnValueDetailsAddList': vm.resultAddArray,
                'columnValueDetailsDelList': vm.resultDelArray
            };

            rbiBondAgeBasedService.modifyAgeBasedCreation(requestData).then(
                (response) => {
                    vm.loader = false;
                    Swal.fire("Modified!", "Age Based Lock-in Period Details Modified Successfully", "success");
                    location.href = '#!/rbibond_age_based/action/inquiry';
                },
                function(error) {
                    vm.loader = false;
                    handleErrorResponse(error);
                    $state.go('rbibond_age_based.modify');
                }
            );
        } else {
            $state.go('rbibond_age_based.modify');
        }
    });
}


		function checkRecordExisted(schemeCode) {
		 vm.loader = true;
            var requestData;
		    if (schemeCode == 'Y') {
                requestData = {'flag':'S'};

                rbiBondAgeBasedService.checkRecordExisted(requestData).then(function(response) {
                    vm.loader = false;
                    vm.schemeList = response.data.result;
                    angular.element('#branchIdSearcher').modal('toggle');
                }, function(error) {
                    handleErrorResponse(error);
                    vm.loader = false;
                    $state.go('rbibond_age_based.inquiry');
                });
            }else{
			var reqDt = {
			'flag':'D',
			'schemeCode': vm.schemeCode,
			'subCodeDescription':vm.subCodeDescription
			};


			rbiBondAgeBasedService.checkRecordExisted(reqDt).then(function (response) {
				vm.loader = false;
//            vm.schemeList = response.data.result;
				chooseDescription(vm.schemeCode);


			}, function (error) {
			vm.schemeCode = '';
				vm.loader = false;
				handleErrorResponse(error);
				$state.go('rbibond_age_based.add');
			});
		}
}

		function schemeTorchFunction(schemeCode){
		vm.loader = true;
						var reqDateTwo = {
        				    'schemeCode': schemeCode
        				};
                        rbiBondAgeBasedService.getInterestRateCodeTorch(reqDateTwo).then(function (responseOne) {
                            vm.loader = false;
                            vm.interstRateCodeList = responseOne.data.result;
                        }, function (errorOne) {
                            vm.schemeCode = '';
                            vm.loader = false;
                            handleErrorResponse(errorOne);
                           // $state.go('rbibond_age_based.inquiry');
                        });
		}


		function getUnverifiedRecords(mode) {
			vm.loader = true;
          var requestData;
                        if (mode == 'Y') {
                            requestData = {};

                            rbiBondAgeBasedService.getUnverifiedRecords(requestData).then(function(response) {
                                vm.loader = false;
                                vm.schemeList = response.data.result;
                                angular.element('#branchIdSearcher').modal('toggle');
                            }, function(error) {
                                handleErrorResponse(error);
                                vm.loader = false;
                                $state.go('rbibond_age_based.inquiry');
                            });
                        }


			else{
			if(mode=='versionNo'){
			    var requestData = {
                			    'schemeCode' :vm.schemeCode
                			};

                    rbiBondAgeBasedService.getUnverifiedRecords(requestData).then(function (response) {
                    vm.loader = false;
                    vm.versionNoList = response.data.result;
                    vm.versionNo = vm.versionNoList[0].versionNo;
                    vm.effFromDate = vm.versionNoList[0].effectiveFromDate;

                    //angular.element('#versionNoModal').modal('toggle');

                }, function (error) {
                    vm.loader = false;
                    handleErrorResponse(error);
                    $state.go('rbibond_age_based.inquiry');
                });
			}if(mode=='main'){
                var requestData = {
                        'versionNo': vm.versionNo,
                        'schemeCode' : vm.schemeCode,
                        'effectiveFromDate' : vm.effectiveFromDate,
                        'subCodeDescription' : vm.subCodeDescription,
                            };

                    rbiBondAgeBasedService.getUnverifiedRecords(requestData).then(function (response) {
                    vm.loader = false;
              vm.auditInfo = response.data.result;
             vm.cbdtId = response.data.result.pkColumnValue;
             vm.verificationDetails = response.data.result.previousOperationType;
             vm.ageBasedParameter = response.data.result.oldNewValueList;
              vm.rejectionRemarks = response.data.result.rejectionRemarks;

                }, function (error) {
                    vm.loader = false;
                    handleErrorResponse(error);
                    if($state.current.name == 'rbibond_age_based.verify_detail'){
                           $state.go('rbibond_age_based.verify')
                    }else if($state.current.name == 'rbibond_age_based.delete_detail'){
                           $state.go('rbibond_age_based.delete')
                    }

                });
			}

		}
		}

		                function alert(item){

                         for (let i = 0 ;i < vm.schemeList.length;i++){
                          if(vm.schemeList[i].subCode == item){
                                vm.subCodeDescription = vm.schemeList[i].subCodeDescription;
                               //vm.cusName = vm.custList[i].cusName;
                          //  vm.cusAlphaCode = vm.custList[i].cusAlphaCode;
                           // vm.id = vm.custList[i].id;
                           }
                         }
                      }

                   function getInquiryDetails(flag) {
                        vm.loader = true;
                        var requestData;
                        if (flag == 'Y') {
                            requestData = {'flag':'S'};

                            rbiBondAgeBasedService.inquiryAfeBasedLockInPeriod(requestData).then(function(response) {
                                vm.loader = false;
                                vm.schemeList = response.data.result;
                                angular.element('#branchIdSearcher').modal('toggle');
                            }, function(error) {
                                handleErrorResponse(error);
                                vm.loader = false;
                                $state.go('rbibond_age_based.inquiry');
                            });
                        }


			else{
			if(flag=='versionNo'){
                    var requestData = {
                       'flag':'V',
                           'schemeCode' : vm.schemeCode
                    };
                    rbiBondAgeBasedService.inquiryAfeBasedLockInPeriod(requestData).then(function (response) {
                        vm.loader = false;
                        vm.versionNoList = response.data.result;
                        angular.element('#versionNoModal').modal('toggle');
                    }, function (error) {
                        handleErrorResponse(error);
                        vm.loader = false;
                    });

			} else {
			var requestData = {

			   'flag':'D',
				'versionNo': vm.versionNo,
                'schemeCode' : vm.schemeCode,
                'effectiveFromDate' : vm.effFromDate,
			};
			rbiBondAgeBasedService.inquiryAfeBasedLockInPeriod(requestData).then(function (response) {
				vm.loader = false;
				if (flag == 'versionNo') {
					vm.versionNoList = response.data.result;
					angular.element('#versionNoModal').modal('toggle');
				} else {
					vm.auditInfo = response.data.result;
					vm.cbdtId = response.data.result.pkColumnValue;
					vm.verificationDetails = response.data.result.previousOperationType;
					vm.ageBasedParameter = response.data.result.oldNewValueList;
					vm.rejectionRemarks = response.data.result.rejectionRemarks;
				}
			}, function (error) {
				handleErrorResponse(error);
				if (error)
					$state.go('rbibond_age_based.inquiry')
				vm.loader = false;
			})

			}

          }
		}

		function verifyAgeBasedCreation() {
//			Swal.fire({
//					title: "Are you sure?",
//					text: "Do you want to Really Verify this record?",
//					type: "warning",
//					showCancelButton: true,
//					cancelButtonClass: "btn-cancel",
//					confirmButtonClass: "btn-primary",
//					confirmButtonText: "Yes",
//					closeOnConfirm: false
//				},
//
//				function () {


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
					vm.loader = true
					var requestData = {
                        "schemeCode" : vm.schemeCode,
                        "versionNo": vm.versionNo,
					};
					rbiBondAgeBasedService.verifyAgeBasedCreation(requestData).then(function (response) {
						vm.loader = false;
						Swal.fire("Success", 'Age Based Lock-in Period Details Verified Successfully', "success");
						$state.go('rbibond_age_based.inquiry')
					}, function (error) {
						vm.loader = false;
						handleErrorResponse(error);
						$state.go('rbibond_age_based.inquiry')
					})
			}	})
		}

//		function deleteAgeBasedCreation() {
//		    Swal.fire({
//                title: "Remarks",
//                text: "",
//                type: "input",
//                inputPlaceholder: "Enter your remarks here",
//                showCancelButton: true,
//                cancelButtonClass: "btn-cancel",
//                confirmButtonClass: "btn-primary",
//                confirmButtonText: "Submit",
//                animation: "slide-from-top",
//                closeOnConfirm: false,
//            },
//            function(inputValue) {
//                if (inputValue === false) return false;
//
//                if (inputValue === "") {
//                    Swal.fire.showInputError("Please enter the remarks!");
//                    return false;
//                }
//
//
//                vm.rejectionRemarks = inputValue;
//
//
//					var requestData = {
//						  "schemeCode" : vm.schemeCode,
//                         "versionNo": vm.versionNo,
//                         "rejectionRemarks": vm.rejectionRemarks,
//					}
//					rbiBondAgeBasedService.deleteAgeBasedCreation(requestData).then(function (response) {
//						vm.loader = false;
//						Swal.fire("Success", 'Age Based Lock-in Period Details Deleted Successfully', "success");
//						$state.go('rbibond_age_based.inquiry')
//					}, function (error) {
//						vm.loader = false;
//						handleErrorResponse(error);
//						$state.go('rbibond_age_based.inquiry')
//					})
//				})
//		}

//	function deleteAgeBasedCreation() {
//
//
//   function handleKeyPress(event) {
//         var key = event.key;
//         var input = event.target.value;
//
//         if (key === ' ' && input.slice(-1) === ' ') {
//             event.preventDefault();
//         }
//
//         if (!key.match(/^[a-zA-Z\s]$/) && key !== 'Backspace' && key!=='Enter') {
//             event.preventDefault();
//         }
//     }
//
//     document.addEventListener('keypress', handleKeyPress);
//
//		    Swal.fire({
//                title: "Rejection Remarks",
//                text: "",
//                type: "input",
//                inputPlaceholder: "Enter your Rejection Remarks here",
//                showCancelButton: true,
//                cancelButtonClass: "btn-cancel",
//                confirmButtonClass: "btn-primary",
//                confirmButtonText: "Submit",
//                animation: "slide-from-top",
//                closeOnConfirm: false,
//                inputValidator: function(value) {
//
//
//                   if (/^[a-zA-Z\s]*$/.test(value.trim())) {
//                           if (/\s\s/.test(value)) {
//                               return "Please enter only single spaces between words!";
//                           }
//                           document.removeEventListener('keypress', handleKeyPress);
//                           return true;
//                       } else {
//                           return "Please enter only alphabetic characters!";
//                       }
//                   }
//               }, function(inputValue) {
//                   if (inputValue === false) {
//                       document.removeEventListener('keypress', handleKeyPress);
//                       return false;
//                   }
//
//                   if (inputValue === "") {
//                       Swal.fire.showInputError("Please enter the Rejection Remarks!");
//                       return false;
//                   }
//
//
//
//                vm.rejectionRemarks = inputValue;
//
//
//					var requestData = {
//						  "schemeCode" : vm.schemeCode,
//                         "versionNo": vm.versionNo,
//                         "rejectionRemarks": vm.rejectionRemarks,
//					}
//					rbiBondAgeBasedService.deleteAgeBasedCreation(requestData).then(function (response) {
//						vm.loader = false;
//						Swal.fire("Success", 'Rejected Successfully', "success");
//						$state.go('rbibond_age_based.inquiry')
//					}, function (error) {
//						vm.loader = false;
//						handleErrorResponse(error);
//						$state.go('rbibond_age_based.inquiry')
//					})
//				})
//		}

function deleteAgeBasedCreation() {

                   vm.loader = true;

//    document.addEventListener('keypress', handleKeyPress);


        var requestData = {
            "schemeCode": vm.schemeCode,
            "versionNo": vm.versionNo,
            "rejectionRemarks": vm.remarks,
        };

        rbiBondAgeBasedService.deleteAgeBasedCreation(requestData).then(function(response) {
            vm.loader = false;
            Swal.fire("Success", 'Rejected Successfully', "success");
            $state.go('rbibond_age_based.inquiry');
                const backdrop = document.querySelector('.modal-backdrop');
                    if (backdrop) {
                      backdrop.parentNode.removeChild(backdrop);
                      document.body.classList.remove('modal-open');
                    }
        }, function(error) {
            vm.loader = false;
            handleErrorResponse(error);
            $state.go('rbibond_age_based.inquiry');
        });

}

		/* Account Number Validation */
		function validatePoolAcct(accountNum, flag, errorFlag, mode) {
			if (accountNum) {
				vm.accountNum = accountNum.toUpperCase();
				if (flag == 'Y') {
					commonService.accountNumValidate(vm.accountNum).then(function (response) {
						var message = response.data.reason;
						if (response.data.status == 'F') {
                            if (errorFlag == 'bondReceivableAcc') {
                                vm.isBondReceivableAccErrMsg = message;
                                if (mode == 'add') {
                                    vm.cbdtSchemeParameter.C062 = '';
                                } else {
                                    vm.cbdtSchemeParameter.C062.oldValue = '';
                                }``
                            }
							if (errorFlag == 'ddTransferAct') {
								vm.ddTransferActErrMsg = message;
								if (mode == 'add') {
									vm.cbdtSchemeParameter.C046 = '';
								} else {
									vm.cbdtSchemeParameter.C046.oldValue = '';
								}``
							}
							if (errorFlag == 'cbdtOTCClearingPoll') {
								vm.cbdtOTCClearingPollErrMsg = message;
								if (mode == 'add') {
									vm.cbdtSchemeParameter.C052 = '';
								} else {
									vm.cbdtSchemeParameter.C052.oldValue = '';
								}
							}
							if (errorFlag == 'cbdtOTCTransferAct') {
								vm.cbdtOTCTransferActErrMsg = message;
								if (mode == 'add') {
									vm.cbdtSchemeParameter.C050 = '';
								} else {
									vm.cbdtSchemeParameter.C050.oldValue = '';
								}
							}
							if (errorFlag == 'cbdtOTCPoolCentAct') {
								vm.cbdtOTCPoolCentActErrMsg = message;
								if (mode == 'add') {
									vm.cbdtSchemeParameter.C048 = '';
								} else {
									vm.cbdtSchemeParameter.C048.oldValue = '';
								}
							}
							if (errorFlag == 'cbdtOTCCashAct') {
								vm.cbdtOTCCashActErrMsg = message;

								if (mode == 'add') {
									vm.cbdtSchemeParameter.C038 = '';
								} else {
									vm.cbdtSchemeParameter.C038.oldValue = '';
								}
							}
							if (errorFlag == 'cbdtOTCSuspAct') {
								vm.cbdtOTCSuspActErrMsg = message;
								if (mode == 'add') {
									vm.cbdtSchemeParameter.C040 = '';
								} else {
									vm.cbdtSchemeParameter.C040.oldValue = '';
								}
							}
							if (errorFlag == 'cbdtOTCCollAct') {
								vm.cbdtOTCCollActErrMsg = message;
								if (mode == 'add') {
									vm.cbdtSchemeParameter.C054 = '';
								} else {
									vm.cbdtSchemeParameter.C054.oldValue = '';
								}
							}
							if (errorFlag == 'cbdtINETParkAct') {
								vm.cbdtINETParkActErrMsg = message;
								if (mode == 'add') {
									vm.cbdtSchemeParameter.C042 = '';
								} else {
									vm.cbdtSchemeParameter.C042.oldValue = '';
								}
							}
							if (errorFlag == 'cbdtCardsSusAct') {
								vm.cbdtCardsSusActErrMsg = message;
								if (mode == 'add') {
									vm.cbdtSchemeParameter.C056 = '';
								} else {
									vm.cbdtSchemeParameter.C056.oldValue = '';
								}
							}
							if (errorFlag == 'cbdtCardsParkAct') {
								vm.cbdtCardsParkActErrMsg = message;
								if (mode == 'add') {
									vm.cbdtSchemeParameter.C060 = '';
								} else {
									vm.cbdtSchemeParameter.C060.oldValue = '';
								}
							}
							if (errorFlag == 'cbdtConsolAct') {
								vm.cbdtConsolActErrMsg = message;
								if (mode == 'add') {
									vm.cbdtSchemeParameter.C044 = '';
								} else {
									vm.cbdtSchemeParameter.C044.oldValue = '';
								}
							}
							if (errorFlag == 'linkCellConslidationAccount') {
                            								vm.linkCellConslidationAccountErrMsg = message;
                            								if (mode == 'add') {
                            									vm.cbdtSchemeParameter.C057 = '';
                            								} else {
                            									vm.cbdtSchemeParameter.C057.oldValue = '';
                            								}
                            							}
						}
						else {
						if (errorFlag == 'bondReceivableAcc') {
                                vm.isBondReceivableAccErrMsg = '';
                            }
							if (errorFlag == 'ddTransferAct') {
								vm.ddTransferActErrMsg = '';
							}
							if (errorFlag == 'cbdtOTCClearingPoll') {
								vm.cbdtOTCClearingPollErrMsg = '';

							}
							if (errorFlag == 'cbdtOTCTransferAct') {
								vm.cbdtOTCTransferActErrMsg = '';
							}
							if (errorFlag == 'cbdtOTCPoolCentAct') {
								vm.cbdtOTCPoolCentActErrMsg = '';
							}
							if (errorFlag == 'cbdtOTCCashAct') {
								vm.cbdtOTCCashActErrMsg = '';
							}
							if (errorFlag == 'cbdtOTCSuspAct') {
								vm.cbdtOTCSuspActErrMsg = '';
							}
							if (errorFlag == 'cbdtOTCCollAct') {
								vm.cbdtOTCCollActErrMsg = '';
							}
							if (errorFlag == 'cbdtINETParkAct') {
								vm.cbdtINETParkActErrMsg = '';
							}
							if (errorFlag == 'cbdtCardsSusAct') {
								vm.cbdtCardsSusActErrMsg = '';
							}
							if (errorFlag == 'cbdtCardsParkAct') {
								vm.cbdtCardsParkActErrMsg = '';
							}
							if (errorFlag == 'cbdtConsolAct') {
								vm.cbdtConsolActErrMsg = '';
							}
						}
					}, function (error) {
						handleErrorResponse(error);
					})
				}
				else {
				    vm.cbdtSchemeParameter.C010 = 'Y';
				    vm.cbdtSchemeParameter.C011 = '';
					commonService.placeHolderAccNumValidate(vm.accountNum).then(function (response) {
						var message = response.data.reason;
						if (response.data.status == 'F') {

							if (errorFlag == 'ddTransferAct') {
								vm.ddTransferActErrMsg = response.data.reason;
								if (mode == 'add') {
									vm.cbdtSchemeParameter.C011 = '';
								} else {
									vm.cbdtSchemeParameter.C011.oldValue = '';
								}
							}
							if (errorFlag == 'cbdtOTCClearingPoll') {
								vm.cbdtOTCClearingPollErrMsg = message;
								if (mode == 'add') {
									vm.cbdtSchemeParameter.C036 = '';
								} else {
									vm.cbdtSchemeParameter.C036.oldValue = '';
								}
							}
							if (errorFlag == 'cbdtOTCTransferAct') {
								vm.cbdtOTCTransferActErrMsg = message;
								if (mode == 'add') {
									vm.cbdtSchemeParameter.C034 = '';
								} else {
									vm.cbdtSchemeParameter.C034.oldValue = '';
								}
							}
							if (errorFlag == 'cbdtOTCPoolCentAct') {
								vm.cbdtOTCPoolCentActErrMsg = message;
								if (mode == 'add') {
									vm.cbdtSchemeParameter.C032 = '';
								} else {
									vm.cbdtSchemeParameter.C032.oldValue = '';
								}
							}
							if (errorFlag == 'cbdtOTCCashAct') {
								vm.cbdtOTCCashActErrMsg = response.data.reason;
								if (mode == 'add') {
									vm.cbdtSchemeParameter.C011 = '';
								} else {
									vm.cbdtSchemeParameter.C011.oldValue = '';
								}
							}
							if (errorFlag == 'cbdtOTCSuspAct') {
								vm.cbdtOTCSuspActErrMsg = response.data.reason;
								if (mode == 'add') {
									vm.cbdtSchemeParameter.C015 = '';
								} else {
									vm.cbdtSchemeParameter.C015.oldValue = '';
								}
							}
							if (errorFlag == 'cbdtOTCCollAct') {
								vm.cbdtOTCCollActErrMsg = response.data.reason;
								if (mode == 'add') {
									vm.cbdtSchemeParameter.C017 = '';
								} else {
									vm.cbdtSchemeParameter.C017.oldValue = '';
								}
							}
							if (errorFlag == 'cbdtINETParkAct') {
								vm.cbdtINETParkActErrMsg = response.data.reason;
								if (mode == 'add') {
									vm.cbdtSchemeParameter.C019 = '';
								} else {
									vm.cbdtSchemeParameter.C019.oldValue = '';
								}
							}
							if (errorFlag == 'cbdtCardsSusAct') {
								vm.cbdtCardsSusActErrMsg = response.data.reason;
								if (mode == 'add') {
									vm.cbdtSchemeParameter.C021 = '';
								} else {
									vm.cbdtSchemeParameter.C021.oldValue = '';
								}
							}
							if (errorFlag == 'cbdtCardsParkAct') {
								vm.cbdtCardsParkActErrMsg = response.data.reason;
								if (mode == 'add') {
									vm.cbdtSchemeParameter.C023 = '';
								} else {
									vm.cbdtSchemeParameter.C023.oldValue = '';
								}
							}
							if (errorFlag == 'cbdtConsolAct') {
								vm.cbdtConsolActErrMsg = response.data.reason;
								if (mode == 'add') {
									vm.cbdtSchemeParameter.C025 = '';
								} else {
									vm.cbdtSchemeParameter.C025.oldValue = '';
								}
							}
						} else {
							if (errorFlag == 'ddTransferAct') {
								vm.ddTransferActErrMsg = '';
							}
							if (errorFlag == 'cbdtOTCClearingPoll') {
								vm.cbdtOTCClearingPollErrMsg = '';
							}
							if (errorFlag == 'cbdtOTCPoolCentAct') {
								vm.cbdtOTCPoolCentActErrMsg = '';
							}
							if (errorFlag == 'cbdtOTCTransferAct') {
								vm.cbdtOTCTransferActErrMsg = '';
							}
							if (errorFlag == 'cbdtOTCCashAct') {
								vm.cbdtOTCCashActErrMsg = '';
							}
							if (errorFlag == 'cbdtOTCSuspAct') {
								vm.cbdtOTCSuspActErrMsg = '';
							}
							if (errorFlag == 'cbdtOTCCollAct') {
								vm.cbdtOTCCollActErrMsg = '';
							}
							if (errorFlag == 'cbdtINETParkAct') {
								vm.cbdtINETParkActErrMsg = '';
							}
							if (errorFlag == 'cbdtCardsSusAct') {
								vm.cbdtCardsSusActErrMsg = '';
							}
							if (errorFlag == 'cbdtCardsParkAct') {
								vm.cbdtCardsParkActErrMsg = '';
							}
							if (errorFlag == 'cbdtConsolAct') {
								vm.cbdtConsolActErrMsg = '';
							}
						}
					}, function (error) {
						handleErrorResponse(error);
					});
				}
			}
		}

		function numVal(flag, mode, value){
                    		if(typeof value === 'undefined'){
                    		if(flag=='cbs'){
                    		  if(mode=='add'){
                    		  vm.cbdtSchemeParameter.C006='';
                    		   }
                    		   else{
                    		  vm.cbdtSchemeParameter.C006.oldValue='';
                    		   }
                    		}
                    		else if(flag=='rcbs')
                    		{
                    		if(mode=='add'){
                    		vm.cbdtSchemeParameter.C007='';
                    		}
                    		else{
                    		vm.cbdtSchemeParameter.C007.oldValue='';
                    		}
                    		}
                    		else if(flag=='cris')
                                                		{
                                                		if(mode=='add'){
                                                		vm.cbdtSchemeParameter.C008='';
                                                		}
                                                		else{
                                                		vm.cbdtSchemeParameter.C008.oldValue='';
                                                		}
                                                		}
                                                		else if(flag=='crisInterval')
                                                                            		{
                                                                            		if(mode=='add'){
                                                                            		vm.cbdtSchemeParameter.C009='';
                                                                            		}
                                                                            		else{
                                                                            		vm.cbdtSchemeParameter.C009.oldValue='';
                                                                            		}
                                                                            		}
                    		}
                    		}


		// Cancel Confirmation
		function showCancelConfirmation(state) {
//			Swal.fire({
//					title: "Confirm Cancel",
//					text: "Do you really want to cancel?",
//					type: "warning",
//					showCancelButton: true,
//					cancelButtonClass: "btn-cancel",
//					confirmButtonClass: "btn-danger",
//					confirmButtonText: "Yes",
//					cancelButtonText: "No",
//					closeOnConfirm: true
//				},
//				function () {


              Swal.fire({
                                 title: "Are you sure?",
                                 text: "Do you Really want to Cancel?",
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
			}	});
		}

		const convertToColumnValuesArrayList = (objectToBeConvert) => {
                  if(typeof(objectToBeConvert) === "object") {
                     let resultArray = [];
                     for(let key of Object.keys(objectToBeConvert)) {
                        if(objectToBeConvert[key])
                            resultArray.push({colName : key,value : objectToBeConvert[key].oldValue})
                     }

                     return resultArray;
                  } else {
                    throw "Only objects can be converted"
                  }
                }

	}
})();