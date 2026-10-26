(function () {
  "use strict";

  angular.module("gbmUltraApp").controller("rbibondKartaCoparcenersController", rbibondKartaCoparcenersController);

  rbibondKartaCoparcenersController.$inject = [
    "$scope",
    "$filter",
    "$state",
    "$rootScope",
    "$stateParams",
    "$location",
    "$timeout",
    "rbibondKartaCoparcenersService",
    "commonService",
  ];

  function rbibondKartaCoparcenersController(
    $scope,
    $filter,
    $state,
    $rootScope,
    $stateParams,
    $location,
    $timeout,
    rbibondKartaCoparcenersService,
    commonService
  ) {
    var vm = this;
    vm.moduleName = "RBI Bond";
    vm.addRange = addRange;
    vm.removeRange = removeRange;
    vm.getModuleName = getModuleName;
    vm.getPanTorchDetails = getPanTorchDetails;
    vm.getInquiryTorchDetails = getInquiryTorchDetails;
    vm.getVerDelTorchDetails = getVerDelTorchDetails;
    vm.validateInput = validateInput;
    vm.verifyDetails = verifyDetails;
    vm.deleteDetails = deleteDetails;
    vm.modifyDetails = modifyDetails;
    vm.getPanNum = getPanNum;
    vm.getModuleNameMod = getModuleNameMod;
    //        vm.panValidPattren = panValidPattren;
    vm.getModuleNameInq = getModuleNameInq;
    vm.rejectReason = rejectReason;
    // vm.panPattern = /[a-zA-Z]{5}[0-9]{4}[a-zA-Z]{1}/;

    vm.today = new Date();
    vm.loginSolDate = $rootScope.globals.currentUser.solDate;
    vm.loginSolId = $rootScope.globals.currentUser.solId;
    vm.commonYesNoMetaData = COMMON_YES_NO_METADATA;
    vm.getModifyDetailsBla = getModifyDetailsBla;
    vm.getTorchDetails = getTorchDetails;
    vm.getVerDelTorchDetails = getVerDelTorchDetails;
    vm.getGuardianDetailsMetaData=getGuardianDetailsMetaData
    vm.blaDataList = [];
    vm.panDataList = [];
    vm.kartaList = [];
    vm.kartaModList = [];
    vm.resultDelArray = [];
    vm.resultAddArray = [];
    vm.resultModifyArray = [];
    vm.initialKartaListState = [];
    vm.panNoList = [];
     getGuardianDetailsMetaData();
    vm.fromDatepicker = [];
    vm.getInquiryDetails = getInquiryDetails;
    vm.getVerDelDetails = getVerDelDetails;

    vm.getRemarksList = getRemarksList;

    if ($state.current.name == "karta_coparceners_details.modify_detail") {
      if ($stateParams.obj) {
        vm.schemeCode = $stateParams.obj.schemeCode;
        vm.subDesc = $stateParams.obj.subDesc;

        vm.blaNo = $stateParams.obj.blaNo;
        vm.modeOfHolding = $stateParams.obj.modeOfHolding;
        vm.nameOfTheBondHolder = $stateParams.obj.nameOfTheBondHolder;
        vm.panNo = $stateParams.obj.panNo;
        vm.getTorchDetails("submit");
      }
    } else if ($state.current.name == "karta_coparceners_details.verify_detail") {
      if ($stateParams.obj) {
        vm.schemeCode = $stateParams.obj.schemeCode;
        vm.subDesc = $stateParams.obj.subDesc;
        vm.blaNo = $stateParams.obj.blaNo;
        vm.modeOfHolding = $stateParams.obj.modeOfHolding;
        vm.nameOfTheBondHolder = $stateParams.obj.nameOfTheBondHolder;
        vm.getVerDelDetails();
      }
    } else if ($state.current.name == "karta_coparceners_details.delete_detail") {
      if ($stateParams.obj) {
        vm.schemeCode = $stateParams.obj.schemeCode;
        vm.blaNo = $stateParams.obj.blaNo;
        vm.modeOfHolding = $stateParams.obj.modeOfHolding;
        vm.nameOfTheBondHolder = $stateParams.obj.nameOfTheBondHolder;
        vm.getVerDelDetails("submit");
      }
    } else if ($state.current.name == "karta_coparceners_details.inquiry_detail") {
      if ($stateParams.obj) {
        vm.schemeCode = $stateParams.obj.schemeCode;
        vm.subDesc = $stateParams.obj.subDesc;
        vm.blaNo = $stateParams.obj.blaNo;
        vm.panNo = $stateParams.obj.panNo;
        vm.modeOfHolding = $stateParams.obj.modeOfHolding;
        vm.nameOfTheBondHolder = $stateParams.obj.nameOfTheBondHolder;
        vm.getInquiryDetails();
      }
    }
    function getRemarksList() {
      vm.referenceId = "056";
      var requestData = { referenceId: vm.referenceId };

      rbibondKartaCoparcenersService.getRemarksList(requestData).then(
        function (response) {
          vm.remarksList = response.data.result || []; // Ensure it's an array
        },
        function (error) {
          handleErrorResponse(error);
        }
      );
    }

    function getPanTorchDetails(name) {
      vm.loader = true;

      var requestData = {
        schemeCode: vm.schemeCode,
        nameOfTheBondHolder: vm.nameOfTheBondHolder,
        panNo: vm.panNo,
      };
      rbibondKartaCoparcenersService.getPanTorchDetails(requestData).then(
        function (response) {
          vm.loader = false;
          vm.panDataList = response.data.result;
          angular.element("#pan1").modal("toggle");
        },
        function (error) {
          handleErrorResponse(error);
          vm.loader = false;
        }
      );
    }

    function getModifyDetailsBla() {
      vm.loader = true;

      var requestData = {
      flag:'B',
        schemeCode: vm.schemeCode,
        panNo: vm.panNo,
        blaNo: vm.blaNo,
      };
      rbibondKartaCoparcenersService.getModifyBlaTorch(requestData).then(
        function (response) {
          vm.loader = false;
          vm.blaDataList = response.data.result;
          angular.element("#blaModel2").modal("toggle");
        },
        function (error) {
          handleErrorResponse(error);
          vm.loader = false;
        }
      );
    }

    function getPanNum() {
      vm.loader = true;

      var requestData = {
        schemeCode: vm.schemeCode,
        nameOfTheBondHolder: vm.nameOfTheBondHolder,
        panNo: vm.panNo,
      };
      rbibondKartaCoparcenersService.getPanNo(requestData).then(
        function (response) {
          vm.loader = false;
          vm.panNoList = response.data.result;

          //                       vm.tocSlabListEffctDate=response.data.result.effeFromDateList;

          angular.element("#panNoModal").modal("toggle");
        },
        function (error) {
          handleErrorResponse(error);
          vm.loader = false;
        }
      );
    }

    function getInquiryTorchDetails() {
      vm.loader = true;
      var requestData = {
         flag:'B',
        schemeCode: vm.schemeCode,
        panNo: vm.panNo,
        blaNo: vm.blaNo,
      };
      rbibondKartaCoparcenersService.getInquiryTorchDetails(requestData).then(
        function (response) {
          vm.loader = false;
          vm.blaDataList = response.data.result;
          angular.element("#blaModel").modal("toggle");
        },
        function (error) {
          handleErrorResponse(error);
          vm.loader = false;
        }
      );
    }

    function getVerDelTorchDetails() {
      vm.loader = true;
      var requestData = {
       flag:'B',
        schemeCode: vm.schemeCode,
      };
      rbibondKartaCoparcenersService.getVerDelDetails(requestData).then(
        function (response) {
          vm.loader = false;
          vm.blaDataList = response.data.result.torchValue;
          angular.element("#blaModel").modal("toggle");
        },
        function (error) {
          handleErrorResponse(error);
          vm.loader = false;
        }
      );
    }

    function getModuleName() {
      vm.loader = true;

      var requestData = {'flag':'S'};
      rbibondKartaCoparcenersService.getModifyTorchDetails(requestData).then(
        function (response) {
          vm.loader = false;
          vm.schemeList = response.data.result;
          angular.element("#schemeModel").modal("toggle");
        },
        function (error) {
          handleErrorResponse(error);
          vm.loader = false;
        }
      );
    }

    function getTorchDetails(flag) {
      vm.loader = true;

      if (flag == "submit") {
        var requestData = {
          flag : 'D',
          blaNo: vm.blaNo,
          panNo: vm.panNo,
          nameOfTheBondHolder: vm.nameOfTheBondHolder,
          schemeCode: vm.schemeCode,
        };
        rbibondKartaCoparcenersService.getModifyTorchDetails(requestData).then(
          function (response) {
            vm.loader = false;

            vm.nomDet = response.data.result;

            for (var i = 0; i < vm.nomDet.length; i++) {
              vm.kartaList.push(vm.nomDet[i]);
              // vm.kartaModList.push(vm.nomDet[i]);
              // vm.kartaModList = Object.freeze(vm.nomDet.slice());
            }

            if (vm.kartaList.length > 0) {
              vm.initialKartaListState = angular.copy(vm.kartaList);
              vm.enableDelete = true;

              angular.forEach(vm.kartaList, function (item, key) {
                let items = [];
                if (typeof item.coparcenersDob != "undefined") {
                  if (item.coparcenersDob != null) {
                    item.coparcenersDob = convertToDateObject(item.coparcenersDob);
                  } else {
                    items.coparcenersDob = null;
                  }
                } else {
                  items.coparcenersDob = null;
                }
                /*vm.initialKartaListState.push({
                                                                              key: key,
                                                                              item: angular.copy(item)
                                                                          });*/
              });
            }
          },
          function (error) {
            handleErrorResponse(error);
            $state.go("karta_coparceners_details.inquiry");
            vm.loader = false;
          }
        );
      }
    }

    function getInquiryDetails() {
      vm.loader = true;
      var requestData = {
        flag:'D',
        schemeCode: vm.schemeCode,
        nameOfTheBondHolder: vm.nameOfTheBondHolder,
        blaNo: vm.blaNo,
        panNo: vm.panNo,
      };
      vm.verificationDetails = "";
      rbibondKartaCoparcenersService.getInquiryDetails(requestData).then(
        function (response) {
          vm.loader = false;
          vm.kartaList = response.data.result.oldNewValueMap;
          vm.auditInfo = response.data.result;
          // vm.remarks = response.data.result.remarks;
          vm.verificationDetails = response.data.result.preOprType;
        },
        function (error) {
          handleErrorResponse(error);
          $state.go("karta_coparceners_details.inquiry");
          vm.loader = false;
        }
      );
    }

    function getModuleNameInq() {
      vm.loader = true;

      var requestData = {'flag':'S'};
      rbibondKartaCoparcenersService.getInquiryDetails(requestData).then(
        function (response) {
          vm.loader = false;
          vm.schemeList1 = response.data.result.torchValue;
          angular.element("#schemeModel1").modal("toggle");
        },
        function (error) {
          handleErrorResponse(error);
          vm.loader = false;
        }
      );
    }

    function getModuleNameMod() {
      vm.loader = true;

      var requestData = {'flag':'S'};
      rbibondKartaCoparcenersService.getVerDelDetails(requestData).then(
        function (response) {
          vm.loader = false;
          vm.schemeList1 = response.data.result.torchValue;
          angular.element("#schemeModel1").modal("toggle");
        },
        function (error) {
          handleErrorResponse(error);
          vm.loader = false;
        }
      );
    }

    function getVerDelDetails(flag) {
      vm.loader = true;

      var requestData = {
        flag:'D',
        blaNo: vm.blaNo,
        schemeCode: vm.schemeCode,
      };
      rbibondKartaCoparcenersService.getVerDelDetails(requestData).then(
        function (response) {
          vm.loader = false;

          vm.subDetail = response.data.result.subMap;
          vm.kartaList = response.data.result.oldNewValueMap;
          vm.auditInfo = response.data.result;
          vm.preOprType = response.data.result.preOprType;
        },
        function (error) {
          handleErrorResponse(error);
          vm.loader = false;
        }
      );
    }

    function verifyDetails() {
//      Swal.fire(
//        {
//          title: "Are you sure?",
//          text: "Do you want to Really Verify this record?",
//          type: "warning",
//          showCancelButton: true,
//          cancelButtonClass: "btn-cancel",
//          confirmButtonClass: "btn-primary",
//          confirmButtonText: "Yes",
//          closeOnConfirm: false,
//        },
//        function () {


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
          var requestData = {
            flag:'D',
            blaNo: vm.blaNo,
            schemeCode: vm.schemeCode,
          };
          rbibondKartaCoparcenersService.verifyDetails(requestData).then(
            function (response) {
              vm.loader = false;
              vm.model = response.data.result;
              Swal.fire("Verified!", "Coparceners Details of Karta Verified Successfully", "success");
              $state.go("karta_coparceners_details.inquiry");
            },
            function (error) {
              vm.loader = false;
              if(error.status =='400')
              {
                Swal.fire("Warning", error.message, "warning");
              }else{
                handleErrorResponse(error);
              }
              $state.go("karta_coparceners_details.verify");
            }
          );
       } }
      );
    }

    function validateInput(event, id) {
      const textBox = document.getElementById(id);

      var charCode = event.which || event.keyCode;
      var charTyped = String.fromCharCode(charCode);

      if (textBox) {
        const cursorPosition = textBox.selectionStart;
        if (cursorPosition == 0 && charTyped === " ") {
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

    function rejectReason() {
      angular.element("#permissionMappingDelete").modal("toggle");
    }

    function deleteDetails() {
      vm.loader = true;
      var requestData = {
        blaNo: vm.blaNo,
        schemeCode: vm.schemeCode,
        remarks: vm.remarks,
      };
      rbibondKartaCoparcenersService.deleteDetails(requestData).then(
        function (response) {
          if (response.data.message == "SUCCESS") {
            Swal.fire("Rejected!", "Coparceners Details of Karta Rejected Successfully", "success");
            $state.go("karta_coparceners_details.inquiry");
          } else {
            Swal.fire("WARNING!", response.data.message, "error");
            $state.go("karta_coparceners_details.verify");
          }
          const backdrop = document.querySelector(".modal-backdrop");
          if (backdrop) {
            backdrop.parentNode.removeChild(backdrop);
            document.body.classList.remove("modal-open");
          }
        },
        function (error) {
          vm.loader = false;
          if(error.status =='400')
          {
            Swal.fire("Warning", error.message, "warning");
          }else{
            handleErrorResponse(error);
          }
          const backdrop = document.querySelector(".modal-backdrop");
          if (backdrop) {
            backdrop.parentNode.removeChild(backdrop);
            document.body.classList.remove("modal-open");
          }
          location.href = "#!/karta_coparceners_details/action/inquiry";
        }
      );
    }

    vm.dateValidation = function (item) {
      if (!item.dateOptions) {
        item.dateOptions = {};
      }
      item.dateOptions.maxDate = new Date();
    };

    function addRange() {
      vm.kartaList.push({});
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


             vm.futureNotAllowed =futureNotAllowed;
                function futureNotAllowed(id,valueDate)
                {
                 commonService.futureNotAllowed(id,valueDate);
                }

    function removeRange(id, index) {
      $scope.$evalAsync(function () {
        vm.kartaList.splice(index, 1);
        vm.kartaList = vm.kartaList;
        if (id) vm.resultDelArray.push([{ colName: "Id", value: id }]);
      });
      if (vm.kartaList.length > 0) {
        vm.enableDelete = true;
      }
    }

    //function removeRange(id, index) {
    //    $scope.$evalAsync(function() {
    //        // Extract the item to be removed
    //        const removedItem = vm.kartaList.splice(index, 1)[0];
    //
    //        // If the removed item has an id, push it to the resultDelArray for deletion
    //        if (id) {
    //            vm.resultDelArray.push(convertToColumnValuesArrayList({
    //                colName: 'ID',
    //                value: id
    //            }));
    //        } else {
    //            // If there is no id, but the removed item has other properties, you can add them here
    //            if (removedItem) {
    //                vm.resultDelArray.push(convertToColumnValuesArrayList(removedItem));
    //            }
    //        }
    //
    //        // Update kartaList to trigger Angular's digest cycle
    //        vm.kartaList = [...vm.kartaList];
    //
    //        // Check if there are still items in the list, and adjust enableDelete accordingly
    //        vm.enableDelete = vm.kartaList.length > 0;
    //    });
    //}

    function modifyDetails() {
//      Swal.fire(
//        {
//          title: "Are you sure?",
//          text: "Do you want to Really Add/Modify this record?",
//          type: "warning",
//          showCancelButton: true,
//          cancelButtonClass: "btn-cancel",
//          confirmButtonClass: "btn-primary",
//          confirmButtonText: "Yes",
//          closeOnConfirm: false,
//        },
//        function () {


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
          vm.kartaDetailsAdd = [];
          angular.forEach(vm.kartaList, function (item, key) {
            let items = [];

            items.C004 = typeof item.coparcenersName === "undefined" ? null : item.coparcenersName;
            items.C005 = typeof item.coparcenersRelation === "undefined" ? null : item.coparcenersRelation;
            items.C006 = typeof item.coparcenersDob === "undefined" ? null : moment(item.coparcenersDob).format("DD-MMM-YYYY");
            //                                                        items.C004 = typeof vm.subDetail.modeOfHolding === "undefined" ? null : vm.subDetail.modeOfHolding;
            //                                                        items.C005 = typeof vm.subDetail.blaNo === "undefined" ? null : vm.subDetail.blaNo;
            //                                                        items.C006 = typeof vm.subDetail.schemeCode === "undefined" ? null : vm.subDetail.schemeCode;

            if (typeof item.id != "undefined") {
              items.id = item.id;
            } else {
              items.id = null;
            }

            vm.kartaDetailsAdd.push(items);
          });

          angular.forEach(vm.kartaDetailsAdd, function (item) {
            /*
                                   if(item.id){
                                   vm.resultArray = [];
                                   vm.resultModifyArray.push(convertToColumnValuesArrayList(item));
                                   }
                                   else{
                                   vm.resultArray1 = [];
                                   vm.resultAddArray.push(convertToColumnValuesArrayList(item));
                                    }*/
            console.log(vm.initialKartaListState);
            if (item.id) {
              let existingItem = vm.initialKartaListState.find(function (modifyItem) {
                return modifyItem.id === item.id;
              });
              console.log(existingItem.coparcenersName);
              console.log(item.C004);
              console.log(existingItem.coparcenersRelation);
              console.log(item.C005);
              console.log(moment(convertToDateObject(existingItem.coparcenersDob)).format("DD-MMM-YYYY"));
              console.log(item.C006);
              if (existingItem) {
                if (
                  existingItem.coparcenersName !== item.C004 ||
                  existingItem.coparcenersRelation !== item.C005 ||
                  moment(convertToDateObject(existingItem.coparcenersDob)).format("DD-MMM-YYYY") !== item.C006
                ) {
                  //  existingItem.C004 !== item.C004 ||
                  //  existingItem.C005 !== item.C005 ||
                  //  existingItem.C006 !== item.C006) {
                  vm.resultModifyArray.push(convertToColumnValuesArrayList(item));
                }
              }
              //new
              /*vm.resultModifyArray = [];

                                                      // Iterate through kartaList and compare with initial state
                                                      angular.forEach(vm.kartaList, function(item, key) {
                                                          // Compare current item with initial state
                                                          if (!angular.equals(item, vm.initialKartaListState[key])) {
                                                              // If item has been modified, add it to resultModifyArray
                                                              vm.resultModifyArray.push(item);
                                                          }
                                                      });*/
            } else {
              if (item.C004 != null || item.C005 != null || item.C006 != null) {
                console.log(item.C003);
                vm.resultAddArray.push(convertToColumnValuesArrayList(item));
              }
            }
          });
          vm.loader = true;
          var requestData = {
            schemeCode: vm.schemeCode,
            blaNo: vm.blaNo,
            kartaAddDtoList: vm.resultAddArray,
            kartaModifyDtoList: vm.resultModifyArray,
            kartaDelDtoList: vm.resultDelArray,
          };
          rbibondKartaCoparcenersService.modifyDetails(requestData).then(
            function (response) {
              vm.loader = false;
              vm.model = response.data.result;
              Swal.fire("Modified!", "Coparceners Details of Karta Modified Successfully", "success");
              $state.go("karta_coparceners_details.inquiry");
            },
            function (error) {
              vm.loader = false;
              if(error.status =='400')
              {
                Swal.fire("Warning", error.message, "warning");
              }else{
                handleErrorResponse(error);
              }
              $state.go("karta_coparceners_details.modify");
            }
          );
       } }
      );
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



    						  function getGuardianDetailsMetaData() {
          var requestData = {
            referenceId: "RELATION_WITH_MINOR",
          };
          rbibondKartaCoparcenersService.getReferenceData(requestData).then(
            function (response) {
              vm.guardianDetailsListMetaData = response.data.result;
            },
            function (error) {
              handleErrorResponse(error);
            }
          );
        }
vm.getGuardianRelationName = function (subCode) {
    if (!subCode || !vm.guardianDetailsListMetaData) {
        return '-';
    }
    var relation = vm.guardianDetailsListMetaData.find(function (item) {
        return item.SUBCODE === subCode;
    });
    return relation ? relation.DESCRIPTION : subCode;
};



    //                             function panValidPattren(pan){
    //
    //                                  if (!vm.panPattern.test(pan)) {
    //                                                   vm.showPanAlert = "Enter a valid PAN number in the format ABCDE1234F.";
    //
    //                                               } else {
    //                                                   vm.showPanAlert = '';
    //                                               }
    //
    //
    //                                 }
  }
})();