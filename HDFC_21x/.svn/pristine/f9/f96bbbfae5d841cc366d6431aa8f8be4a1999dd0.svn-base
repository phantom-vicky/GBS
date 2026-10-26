(function () {
  "use strict";
  angular.module("gbmUltraApp").controller("dashBoardViewForPhysicalAndInetController", dashBoardViewForPhysicalAndInetController);

  dashBoardViewForPhysicalAndInetController.$inject = ["$scope", "$filter", "$state", "$rootScope", "$stateParams", "$location", "dashBoardViewForPhysicalAndInetService", "commonService"];

  function dashBoardViewForPhysicalAndInetController($scope, $filter, $state, $rootScope, $stateParams, $location, dashBoardViewForPhysicalAndInetService, commonService) {
    var vm = this;
    vm.commonYesNoMetaData = COMMON_YES_NO_METADATA;
    vm.currentDate = new Date();
    vm.getRemarksList = getRemarksList;
    vm.submitVerifyForm = submitVerifyForm;
    vm.verifySelect = verifySelect;
    vm.getModuleName = getModuleName;
    vm.fromToDateValidation = fromToDateValidation;
    vm.submitAddForm = submitAddForm;
    vm.getAddValidation = getAddValidation;
    vm.valueDateValidation = valueDateValidation;
    vm.download = download;
    vm.inquiry = inquiry;
    vm.deleteSubmit = deleteSubmit;
    vm.addDeleteSubmit = addDeleteSubmit;
    vm.rejectReason = rejectReason;
    vm.addRejectReason = addRejectReason;
    vm.validateInput = validateInput;
    vm.optionToggled = optionToggled;
    vm.selectedCheckBox = [];
    vm.selectedCheckBox["RRBNB"] = false;
    vm.selectedCheckBox["TBNB"] = false;
    vm.selectedCheckBox["RRBNB_BB"] = false;
    vm.selectedCheckBox["TBNB_BB"] = false;
    vm.selectedCheckBox["TAXBOND"] = false;
    vm.selectedCheckBox["RRBNB_SAKSHAM"] = false;
    vm.selectedCheckBox["TBNB_SAKSHAM"] = false;
    vm.selectedCheckBox["RRBNB_NOVOPAY"] = false;
    vm.selectedCheckBox["TBNB_NOVOPAY"] = false;
    vm.fileModeList = [
      {
        subCodeDescription: "Cash-in",
        subCode: "C",
      },
      {
        subCodeDescription: "Net Banking",
        subCode: "NB",
      },
    ];
    vm.netBankingFileTypeList = [
      {
        subCodeDescription: "RRBNB",
        subCode: "RRBNB",
      },{
        subCodeDescription: "RRBNB_BB",
        subCode: "RRBNB_BB",
      },{
        subCodeDescription: "RRBNB_SAKSHAM",
        subCode: "RRBNB_SAKSHAM",
      },{
        subCodeDescription: "RRBNB_NOVOPAY",
        subCode: "RRBNB_NOVOPAY",
      },
    ];
    vm.cashInFileTypeList = [
      {
        subCodeDescription: "TAXBOND",
        subCode: "TAXBOND",
      },
    ];
    if ($state.current.name == "dash_board_view_for_physical_and_inet.add_detail") {
      if ($stateParams.obj) {
        vm.fileType = $stateParams.obj.fileType;
        vm.liquidationDate = $stateParams.obj.liquidationDate;
        vm.valueDate = $stateParams.obj.valueDate;
        vm.fileMode = $stateParams.obj.fileMode;
        vm.schemeName = $stateParams.obj.schemeName;
        vm.subCode = $stateParams.obj.subCode;
        getAddValidation();
      } else {
        $state.go("dash_board_view_for_physical_and_inet.add_detail");
      }
    } else if ($state.current.name == "dash_board_view_for_physical_and_inet.verify_detail") {
      if ($stateParams.obj) {
        vm.fileType = $stateParams.obj.fileType;
        vm.liquidationDate = $stateParams.obj.liquidationDate;
        vm.fileMode = $stateParams.obj.fileMode;
        vm.schemeName = $stateParams.obj.schemeName;
        vm.subCode = $stateParams.obj.subCode;
        verifySelect();
      } else {
        $state.go("dash_board_view_for_physical_and_inet.verify_detail");
      }
    } else if ($state.current.name == "dash_board_view_for_physical_and_inet.inquiry_detail") {
      if ($stateParams.obj) {
        vm.fileType = $stateParams.obj.fileType;
        vm.liquidationDate = $stateParams.obj.liquidationDate;
        vm.fileMode = $stateParams.obj.fileMode;
        vm.schemeName = $stateParams.obj.schemeName;
        vm.subCode = $stateParams.obj.subCode;
        inquiry();
      } else {
        $state.go("dash_board_view_for_physical_and_inet.inquiry_detail");
      }
    }
    function rejectReason() {
      angular.element("#permissionMappingDelete").modal("toggle");
    }
    function addRejectReason() {
      angular.element("#permissionMappingAddDelete").modal("toggle");
    }
    function getRemarksList() {
      vm.referenceId = "056";
      var requestData = { referenceId: vm.referenceId };

      dashBoardViewForPhysicalAndInetService.getRemarksList(requestData).then(
        function (response) {
          vm.remarksList = response.data.result || []; // Ensure it's an array
        },
        function (error) {
          handleErrorResponse(error);
        }
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
    function formatDateToDDMMYY(date) {
      // Ensure the input is a Date object
      if (!(date instanceof Date)) {
        date = new Date(date);
      }
      let year = date.getFullYear().toString().slice(-2); // Get last 2 digits of the year
      let month = (date.getMonth() + 1).toString().padStart(2, "0"); // Months are zero-based
      let day = date.getDate().toString().padStart(2, "0"); // Get the day and pad with leading zero if necessary
      return day + month + year;
    }
    function formatDateToCustomString(date) {
      if (!(date instanceof Date)) {
        date = new Date(date);
      }
      // Get day, month, and year components
      const day = ("0" + date.getDate()).slice(-2); // Adding leading zero if needed
      const month = ("0" + (date.getMonth() + 1)).slice(-2); // Adding leading zero if needed (months are zero-indexed)
      const year = date.getFullYear();

      // Concatenate to get the desired format
      return day + month + year;
    }
   /* function getAddRejection() {
      vm.finalList = [];
      angular.forEach(vm.responseResult, function (item, index) {
        var fileName = document.getElementById("selectedItem" + index).getAttribute("name");
        if (item.fileName == "RRBNB" && fileName == "RRBNB" && vm.selectedCheckBox[fileName] == true) {
          vm.finalList.push(item);
        }
        if (item.fileName == "TBNB" && fileName == "TBNB" && vm.selectedCheckBox[fileName] == true) {
          vm.finalList.push(item);
        }
        if (item.fileName == "RRBNB_BB" && fileName == "RRBNB_BB" && vm.selectedCheckBox[fileName] == true) {
          vm.finalList.push(item);
        }
        if (item.fileName == "TBNB_BB" && fileName == "TBNB_BB" && vm.selectedCheckBox[fileName] == true) {
          vm.finalList.push(item);
        }
        if (item.fileName == "TAXBOND" && fileName == "TAXBOND" && vm.selectedCheckBox[fileName] == true) {
          vm.finalList.push(item);
        }
      });
      if (vm.finalList.length > 0) {
        vm.loader = true;
        vm.finalList1 = vm.finalList.map(function (item) {
          var cleanedItem = Object.assign({}, item); // Shallow copy the item
          delete cleanedItem.$$hashKey; // Delete the $$hashKey property
          return cleanedItem;
        });
        var requestData = {
          subCode: vm.subCode,
          fileMode: vm.fileMode,
          liquidationDate: $filter("date")(vm.liquidationDate, "dd-MMM-yyyy"),
          torchOut: vm.finalList1,
          rejectionRemarks: vm.remarks,
        };
        dashBoardViewForPhysicalAndInetService
          .getAddRejection(requestData)
          .then(function (response) {
            vm.loader = false;
            const backdrop = document.querySelector(".modal-backdrop");
            if (backdrop) {
              backdrop.parentNode.removeChild(backdrop);
              document.body.classList.remove("modal-open");
            }
            Swal.fire("Rejected!", "File Data Rejected Successfully", "success");
            $state.go("dash_board_view_for_physical_and_inet.inquiry");
          })
          .catch(function (error) {
            vm.loader = false;
            handleErrorResponse(error);
            const backdrop = document.querySelector(".modal-backdrop");
            if (backdrop) {
              backdrop.parentNode.removeChild(backdrop);
              document.body.classList.remove("modal-open");
            }
            $state.go("dash_board_view_for_physical_and_inet.add_detail");
          });
      } else {
        Swal.fire("Warning!", "Please Select Necessary Files", "warning");
        $state.go("dash_board_view_for_physical_and_inet.add_detail");
      }
    }*/
    function addDeleteSubmit() {
          vm.finalList = [];
          angular.forEach(vm.responseResult, function (item, index) {
            var fileName = document.getElementById("selectedItem" + index).getAttribute("name");
            if (item.fileName == "RRBNB" && fileName == "RRBNB" && vm.selectedCheckBox[fileName] == true) {
              vm.finalList.push(item);
            }
            if (item.fileName == "TBNB" && fileName == "TBNB" && vm.selectedCheckBox[fileName] == true) {
              vm.finalList.push(item);
            }
            if (item.fileName == "RRBNB_BB" && fileName == "RRBNB_BB" && vm.selectedCheckBox[fileName] == true) {
              vm.finalList.push(item);
            }
            if (item.fileName == "TBNB_BB" && fileName == "TBNB_BB" && vm.selectedCheckBox[fileName] == true) {
              vm.finalList.push(item);
            }
            if (item.fileName == "RRBNB_SAKSHAM" && fileName == "RRBNB_SAKSHAM" && vm.selectedCheckBox[fileName] == true) {
              vm.finalList.push(item);
            }
            if (item.fileName == "TBNB_SAKSHAM" && fileName == "TBNB_SAKSHAM" && vm.selectedCheckBox[fileName] == true) {
              vm.finalList.push(item);
            }
            if (item.fileName == "RRBNB_NOVOPAY" && fileName == "RRBNB_NOVOPAY" && vm.selectedCheckBox[fileName] == true) {
              vm.finalList.push(item);
            }
            if (item.fileName == "TBNB_NOVOPAY" && fileName == "TBNB_NOVOPAY" && vm.selectedCheckBox[fileName] == true) {
              vm.finalList.push(item);
            }
            if (item.fileName == "TAXBOND" && fileName == "TAXBOND" && vm.selectedCheckBox[fileName] == true) {
              vm.finalList.push(item);
            }
          });
          if (vm.finalList.length > 0) {
            vm.loader = true;
            vm.finalList1 = vm.finalList.map(function (item) {
              var cleanedItem = Object.assign({}, item); // Shallow copy the item
              delete cleanedItem.$$hashKey; // Delete the $$hashKey property
              return cleanedItem;
            });
            var requestData = {
              subCode: vm.subCode,
              fileMode: vm.fileMode,
              liquidationDate: $filter("date")(vm.liquidationDate, "dd-MMM-yyyy"),
              valueDate: $filter("date")(vm.valueDate, "dd-MMM-yyyy"),
              torchOut: vm.finalList1,
              rejectionRemarks: vm.remarks,
            };
            dashBoardViewForPhysicalAndInetService
              .addDeleteSubmit(requestData)
              .then(function (response) {
                vm.loader = false;
                const backdrop = document.querySelector(".modal-backdrop");
                if (backdrop) {
                  backdrop.parentNode.removeChild(backdrop);
                  document.body.classList.remove("modal-open");
                }
                Swal.fire("Rejected!", "Add File Data Rejected Successfully", "success");
                $state.go("dash_board_view_for_physical_and_inet.inquiry");
              })
              .catch(function (error) {
                vm.loader = false;
                handleErrorResponse(error);
                const backdrop = document.querySelector(".modal-backdrop");
                if (backdrop) {
                  backdrop.parentNode.removeChild(backdrop);
                  document.body.classList.remove("modal-open");
                }
                $state.go("dash_board_view_for_physical_and_inet.add_detail");
              });
          } else {
            Swal.fire("Warning!", "Please Select Necessary Files", "warning");
            $state.go("dash_board_view_for_physical_and_inet.add_detail");
          }
        }
    function deleteSubmit() {
      vm.finalList = [];
      angular.forEach(vm.responseResult, function (item, index) {
        var fileName = document.getElementById("selectedItem" + index).getAttribute("name");
        if (item.fileName == "RRBNB" && fileName == "RRBNB" && vm.selectedCheckBox[fileName] == true) {
          vm.finalList.push(item);
        }
        if (item.fileName == "TBNB" && fileName == "TBNB" && vm.selectedCheckBox[fileName] == true) {
          vm.finalList.push(item);
        }
        if (item.fileName == "RRBNB_BB" && fileName == "RRBNB_BB" && vm.selectedCheckBox[fileName] == true) {
          vm.finalList.push(item);
        }
        if (item.fileName == "TBNB_BB" && fileName == "TBNB_BB" && vm.selectedCheckBox[fileName] == true) {
          vm.finalList.push(item);
        }
        if (item.fileName == "RRBNB_SAKSHAM" && fileName == "RRBNB_SAKSHAM" && vm.selectedCheckBox[fileName] == true) {
          vm.finalList.push(item);
        }
        if (item.fileName == "TBNB_SAKSHAM" && fileName == "TBNB_SAKSHAM" && vm.selectedCheckBox[fileName] == true) {
          vm.finalList.push(item);
        }
        if (item.fileName == "RRBNB_NOVOPAY" && fileName == "RRBNB_NOVOPAY" && vm.selectedCheckBox[fileName] == true) {
          vm.finalList.push(item);
        }
        if (item.fileName == "TBNB_NOVOPAY" && fileName == "TBNB_NOVOPAY" && vm.selectedCheckBox[fileName] == true) {
          vm.finalList.push(item);
        }
        if (item.fileName == "TAXBOND" && fileName == "TAXBOND" && vm.selectedCheckBox[fileName] == true) {
          vm.finalList.push(item);
        }
      });
      if (vm.finalList.length > 0) {
        vm.loader = true;
        vm.finalList1 = vm.finalList.map(function (item) {
          var cleanedItem = Object.assign({}, item); // Shallow copy the item
          delete cleanedItem.$$hashKey; // Delete the $$hashKey property
          return cleanedItem;
        });
        var requestData = {
          subCode: vm.subCode,
          fileMode: vm.fileMode,
          liquidationDate: $filter("date")(vm.liquidationDate, "dd-MMM-yyyy"),
          valueDate: $filter("date")(vm.valueDate, "dd-MMM-yyyy"),
          torchOut: vm.finalList1,
          rejectionRemarks: vm.remarks,
        };
        dashBoardViewForPhysicalAndInetService
          .deleteSubmit(requestData)
          .then(function (response) {
            vm.loader = false;
            const backdrop = document.querySelector(".modal-backdrop");
            if (backdrop) {
              backdrop.parentNode.removeChild(backdrop);
              document.body.classList.remove("modal-open");
            }
            Swal.fire("Rejected!", "File Data Rejected Successfully", "success");
            $state.go("dash_board_view_for_physical_and_inet.inquiry");
          })
          .catch(function (error) {
            vm.loader = false;
            handleErrorResponse(error);
            const backdrop = document.querySelector(".modal-backdrop");
            if (backdrop) {
              backdrop.parentNode.removeChild(backdrop);
              document.body.classList.remove("modal-open");
            }
            $state.go("dash_board_view_for_physical_and_inet.verify_detail");
          });
      } else {
        Swal.fire("Warning!", "Please Select Necessary Files", "warning");
        $state.go("dash_board_view_for_physical_and_inet.verify_detail");
      }
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
           }else if(flag == 'RRBNB_NOVOPAY' ){
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
           }else if(flag == 'TAXBOND' ){
           if(vm.selectedCheckBox['TAXBOND'] == true)
           {
           vm.selectedCheckBox['TAXBOND']=true;
           }else  {
          vm.selectedCheckBox['TAXBOND']=false;
           }
         }
       }
       }
    function download(flag) {
      if (vm.responseResult[flag].fileName == "RRBNB" || vm.responseResult[flag].fileName == "TBNB") {
        const liquidationDateDDMMYY = formatDateToDDMMYY(vm.liquidationDate); // Example date
        saveAs(getBlobFromBase64String(vm.responseResult[flag].file), vm.responseResult[flag].fileName + liquidationDateDDMMYY + ".csv");
        Swal.fire("Success!", vm.responseResult[flag].fileName + liquidationDateDDMMYY + ".csv" + "\n Downloaded Successfully", "success");
      } else if (vm.responseResult[flag].fileName == "RRBNB_BB" || vm.responseResult[flag].fileName == "TBNB_BB") {
        const liquidationDateDDMMYYYY = formatDateToCustomString(vm.liquidationDate); // Example date
        saveAs(getBlobFromBase64String(vm.responseResult[flag].file), vm.responseResult[flag].fileName.slice(0, -2) + liquidationDateDDMMYYYY + "_BB.csv");
        Swal.fire("Success!", vm.responseResult[flag].fileName.slice(0, -2) + liquidationDateDDMMYYYY + "_BB.csv" + "\n Downloaded Successfully", "success");
      }else if (vm.responseResult[flag].fileName == "RRBNB_SAKSHAM" || vm.responseResult[flag].fileName == "TBNB_SAKSHAM") {
        const liquidationDateDDMMYYYY = formatDateToCustomString(vm.liquidationDate); // Example date
        saveAs(getBlobFromBase64String(vm.responseResult[flag].file), vm.responseResult[flag].fileName.slice(0, -7) + liquidationDateDDMMYYYY + "_Saksham.csv");
        Swal.fire("Success!", vm.responseResult[flag].fileName.slice(0, -7) + liquidationDateDDMMYYYY + "_Saksham.csv" + "\n Downloaded Successfully", "success");
      }else if (vm.responseResult[flag].fileName == "RRBNB_NOVOPAY" || vm.responseResult[flag].fileName == "TBNB_NOVOPAY") {
       const liquidationDateDDMMYYYY = formatDateToCustomString(vm.liquidationDate); // Example date
       saveAs(getBlobFromBase64String(vm.responseResult[flag].file), vm.responseResult[flag].fileName.slice(0, -7) + liquidationDateDDMMYYYY + "_NOVOPAY.csv");
       Swal.fire("Success!", vm.responseResult[flag].fileName.slice(0, -7) + liquidationDateDDMMYYYY + "_NOVOPAY.csv" + "\n Downloaded Successfully", "success");
      }else if (vm.responseResult[flag].fileName == "TAXBOND") {
        const liquidationDateDDMMYYYY = formatDateToCustomString(vm.liquidationDate); // Example date
        saveAs(getBlobFromBase64String(vm.responseResult[flag].file), vm.responseResult[flag].fileName + liquidationDateDDMMYYYY + ".txt");
        Swal.fire("Success!", vm.responseResult[flag].fileName + liquidationDateDDMMYYYY + ".txt" + "\n Downloaded Successfully", "success");
      }
    }
    function getAddValidation() {
      vm.loader = true;
      var requestData = {
        subCode: vm.subCode,
        fileMode: vm.fileMode,
        liquidationDate: $filter("date")(vm.liquidationDate, "dd-MMM-yyyy"),
        valueDate: $filter("date")(vm.valueDate, "dd-MMM-yyyy"),
      };
      dashBoardViewForPhysicalAndInetService.getAddValidation(requestData).then(
        function (response) {
          vm.loader = false;
          vm.responseResult = response.data.result.torchOut;
          vm.overAllCount = response.data.result.overAllCount;
          vm.overAllAmt = response.data.result.overAllAmt;
          $state.go("dash_board_view_for_physical_and_inet.add_detail");
        },
        function (error) {
          vm.loader = false;
          vm.responseResult = "";
          handleErrorResponse(error);
          $state.go("dash_board_view_for_physical_and_inet.add");
        }
      );
    }

    /* Verify Select */
    function verifySelect() {
      vm.loader = true;
      var requestData = {
        subCode: vm.subCode,
        fileMode: vm.fileMode,
        liquidationDate: $filter("date")(vm.liquidationDate, "dd-MMM-yyyy"),
      };
      dashBoardViewForPhysicalAndInetService.verifySelect(requestData).then(
        function (response) {
          vm.loader = false;
          vm.responseResult = response.data.result.torchOut;
          vm.details = response.data.result;
          vm.overAllAmt = response.data.result.overAllAmt;
          vm.overAllCount = response.data.result.overAllCount;
          vm.valueDate = response.data.result.valueDate;
          $state.go("dash_board_view_for_physical_and_inet.verify_detail");
        },
        function (error) {
          vm.loader = false;
          vm.responseResult = "";
          handleErrorResponse(error);
          $state.go("dash_board_view_for_physical_and_inet.verify");
        }
      );
    }

    function fromToDateValidation() {
      var sevenDaysBefore = moment(vm.currentDate).subtract(7, "days").toDate();
      // Set the minimum date to be 7 days before the current date
      vm.liquidationDateCalenderOptions = {
        //  minDate: sevenDaysBefore,
        // maxDate: vm.currentDate
      };
      // Set the minimum date for liquidationDate
      vm.liquidationDate = vm.currentDate;
    }
    //Get Module Name
    function getModuleName() {
      vm.referenceId = "RBI Bond";
      var requestData = {
        referenceId: vm.referenceId,
      };
      dashBoardViewForPhysicalAndInetService.getRbiBondModuleNameDetails(requestData).then(
        function (response) {
          vm.schemeNameList = response.data.result;
          angular.element("#levelNameModal").modal("toggle");
        },
        function (error) {
          handleErrorResponse(error);
        }
      );
    }
    function validateFiles(filesArray) {
      return filesArray.every((file) => file != null && file !== undefined && file !== "");
    }
    function validateFiles1(filesArray1) {
      return filesArray1.every((file) => file != null && file !== undefined && file !== "");
    }
    function validateFiles2(filesArray2) {
      return filesArray2.every((file) => file != null && file !== undefined && file !== "");
    }
    function validateFiles3(filesArray3) {
      return filesArray3.every((file) => file != null && file !== undefined && file !== "");
    }
    function validateFiles4(filesArray4) {
      return filesArray4.every((file) => file != null && file !== undefined && file !== "");
    }

    vm.indianNumberFormat=indianNumberFormat;
                    function indianNumberFormat(value) {
                      if (isNaN(value)) return value;
                      let numStr = value.toString();
                      let [whole, decimal] = numStr.split('.');
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
    /* Add Submit */
    function submitAddForm() {
      vm.finalList = [];
      angular.forEach(vm.responseResult, function (item, index) {
        var fileName = document.getElementById("selectedItem" + index).getAttribute("name");
        if (item.fileName == "RRBNB" && fileName == "RRBNB" && vm.selectedCheckBox[fileName] == true) {
          vm.finalList.push(item);
        }
        if (item.fileName == "TBNB" && fileName == "TBNB" && vm.selectedCheckBox[fileName] == true) {
          vm.finalList.push(item);
        }
        if (item.fileName == "RRBNB_BB" && fileName == "RRBNB_BB" && vm.selectedCheckBox[fileName] == true) {
          vm.finalList.push(item);
        }
        if (item.fileName == "TBNB_BB" && fileName == "TBNB_BB" && vm.selectedCheckBox[fileName] == true) {
          vm.finalList.push(item);
        }
        if (item.fileName == "RRBNB_SAKSHAM" && fileName == "RRBNB_SAKSHAM" && vm.selectedCheckBox[fileName] == true) {
          vm.finalList.push(item);
        }
        if (item.fileName == "TBNB_SAKSHAM" && fileName == "TBNB_SAKSHAM" && vm.selectedCheckBox[fileName] == true) {
          vm.finalList.push(item);
        }
        if (item.fileName == "RRBNB_NOVOPAY" && fileName == "RRBNB_NOVOPAY" && vm.selectedCheckBox[fileName] == true) {
          vm.finalList.push(item);
        }
        if (item.fileName == "TBNB_NOVOPAY" && fileName == "TBNB_NOVOPAY" && vm.selectedCheckBox[fileName] == true) {
          vm.finalList.push(item);
        }
        if (item.fileName == "TAXBOND" && fileName == "TAXBOND" && vm.selectedCheckBox[fileName] == true) {
          vm.finalList.push(item);
        }
      });

      var hasRRBNB = vm.finalList.some(i => i.fileName === "RRBNB");
      var hasTBNB = vm.finalList.some(i => i.fileName === "TBNB");

      var hasRRBNB_BB = vm.finalList.some(i => i.fileName === "RRBNB_BB");
      var hasTBNB_BB = vm.finalList.some(i => i.fileName === "TBNB_BB");

      var hasRRBNB_SAKSHAM = vm.finalList.some(i => i.fileName === "RRBNB_SAKSHAM");
      var hasTBNB_SAKSHAM = vm.finalList.some(i => i.fileName === "TBNB_SAKSHAM");

      var hasRRBNB_NOVOPAY = vm.finalList.some(i => i.fileName === "RRBNB_NOVOPAY");
      var hasTBNB_NOVOPAY = vm.finalList.some(i => i.fileName === "TBNB_NOVOPAY");

      var hasTAXBOND = vm.finalList.some(i => i.fileName === "TAXBOND");

  var RRBNBCount = vm.finalList.filter(i => i.fileName === "RRBNB")
      .reduce((sum, i) => sum + (Number(i.countOfRecord) || 0), 0);

  var TBNBCount = vm.finalList.filter(i => i.fileName === "TBNB")
      .reduce((sum, i) => sum + (Number(i.countOfRecord) || 0), 0);

  var RRBNBBBCount = vm.finalList.filter(i => i.fileName === "RRBNB_BB")
      .reduce((sum, i) => sum + (Number(i.countOfRecord) || 0), 0);

  var TBNBBBCount = vm.finalList.filter(i => i.fileName === "TBNB_BB")
      .reduce((sum, i) => sum + (Number(i.countOfRecord) || 0), 0);

  var RRBNBSakshamCount = vm.finalList.filter(i => i.fileName === "RRBNB_SAKSHAM")
      .reduce((sum, i) => sum + (Number(i.countOfRecord) || 0), 0);

  var TBNBSakshamCount = vm.finalList.filter(i => i.fileName === "TBNB_SAKSHAM")
      .reduce((sum, i) => sum + (Number(i.countOfRecord) || 0), 0);

  var RRBNBNOVOPAYCount = vm.finalList.filter(i => i.fileName === "RRBNB_NOVOPAY")
        .reduce((sum, i) => sum + (Number(i.countOfRecord) || 0), 0);

var TBNBNOVOPAYCount = vm.finalList.filter(i => i.fileName === "TBNB_NOVOPAY")
    .reduce((sum, i) => sum + (Number(i.countOfRecord) || 0), 0);

var RRBNBAmount = vm.finalList.filter(i => i.fileName === "RRBNB")
    .reduce((sum, i) => sum + (Number(i.totalAmt) || 0), 0);

var TBNBAmount = vm.finalList.filter(i => i.fileName === "TBNB")
    .reduce((sum, i) => sum + (Number(i.totalAmt) || 0), 0);

var RRBNBBBAmount = vm.finalList.filter(i => i.fileName === "RRBNB_BB")
    .reduce((sum, i) => sum + (Number(i.totalAmt) || 0), 0);

var TBNBBBAmount = vm.finalList.filter(i => i.fileName === "TBNB_BB")
    .reduce((sum, i) => sum + (Number(i.totalAmt) || 0), 0);

var RRBNBSakshamAmount = vm.finalList.filter(i => i.fileName === "RRBNB_SAKSHAM")
    .reduce((sum, i) => sum + (Number(i.totalAmt) || 0), 0);

var TBNBSakshamAmount = vm.finalList.filter(i => i.fileName === "TBNB_SAKSHAM")
    .reduce((sum, i) => sum + (Number(i.totalAmt) || 0), 0);

var RRBNBNOVOPAYAmount = vm.finalList.filter(i => i.fileName === "RRBNB_NOVOPAY")
    .reduce((sum, i) => sum + (Number(i.totalAmt) || 0), 0);

var TBNBNOVOPAYAmount = vm.finalList.filter(i => i.fileName === "TBNB_NOVOPAY")
    .reduce((sum, i) => sum + (Number(i.totalAmt) || 0), 0);

//  If any one side selected without pair
    if ((!hasRRBNB && hasTBNB) || (hasRRBNB && !hasTBNB)) {
    Swal.fire("Warning!", "RRBNB & TBNB must be selected together", "warning");
    return false;
    }
    if ((!hasRRBNB_BB && hasTBNB_BB) || (hasRRBNB_BB && !hasTBNB_BB)) {
    Swal.fire("Warning!", "RRBNB_BB & TBNB_BB must be selected together", "warning");
    return false;
    }
    if ((!hasRRBNB_SAKSHAM && hasTBNB_SAKSHAM) || (hasRRBNB_SAKSHAM && !hasTBNB_SAKSHAM)) {
    Swal.fire("Warning!", "RRBNB_SAKSHAM & TBNB_SAKSHAM must be selected together", "warning");
    return false;
    }
    if ((!hasRRBNB_NOVOPAY && hasTBNB_NOVOPAY) || (hasRRBNB_NOVOPAY && !hasTBNB_NOVOPAY)) {
    Swal.fire("Warning!", "RRBNB_NOVOPAY & TBNB_NOVOPAY must be selected together", "warning");
    return false;
    }
    //validation amount and count
    if (hasRRBNB && hasTBNB) {
        if(RRBNBCount !== TBNBCount){
        Swal.fire("Warning!", "Count mismatch between files RRBNB and TBNB", "warning");
        return false;
        }else if(RRBNBAmount !== TBNBAmount){
        Swal.fire("Warning!", "Amount mismatch for file RRBNB and TBNB", "warning");
        return false;
        }}
    if (hasRRBNB_BB && hasTBNB_BB) {
        if(RRBNBBBCount !== TBNBBBCount){
        Swal.fire("Warning!", "Count mismatch between files RRBNB_BB and TBNB_BB", "warning");
        return false;
        }else if(RRBNBBBAmount !== TBNBBBAmount){
        Swal.fire("Warning!", "Amount mismatch for file RRBNB_BB and TBNB_BB", "warning");
        return false;
        }}
    if (hasRRBNB_SAKSHAM && hasTBNB_SAKSHAM) {
        if(RRBNBSakshamCount !== TBNBSakshamCount){
        Swal.fire("Warning!", "Count mismatch between files RRBNB_SAKSHAM and TBNB_SAKSHAM", "warning");
        return false;
        }else if(RRBNBSakshamAmount !== TBNBSakshamAmount){
        Swal.fire("Warning!", "Amount mismatch for file RRBNB_SAKSHAM and TBNB_SAKSHAM", "warning");
        return false;
        }}
    if (hasRRBNB_NOVOPAY && hasTBNB_NOVOPAY) {
        if(RRBNBNOVOPAYCount !== TBNBNOVOPAYCount){
        Swal.fire("Warning!", "Count mismatch between files RRBNB_NOVOPAY and TBNB_NOVOPAY", "warning");
        return false;
        }else if(RRBNBNOVOPAYAmount !== TBNBNOVOPAYAmount){
        Swal.fire("Warning!", "Amount mismatch for file RRBNB_NOVOPAY and TBNB_NOVOPAY", "warning");
        return false;
        }}
      if (vm.finalList.length > 0) {
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
            vm.finalList1 = vm.finalList.map(function (item) {
              var cleanedItem = Object.assign({}, item); // Shallow copy the item
              delete cleanedItem.$$hashKey; // Delete the $$hashKey property
              return cleanedItem;
            });
            var requestData = {
              subCode: vm.subCode,
              fileMode: vm.fileMode,
              liquidationDate: $filter("date")(vm.liquidationDate, "dd-MMM-yyyy"),
              valueDate: $filter("date")(vm.valueDate, "dd-MMM-yyyy"),
              torchOut: vm.finalList1,
            };
            dashBoardViewForPhysicalAndInetService.submitAddForm(requestData).then(
              function (response) {
                vm.loader = false;
                vm.model = response.data.result;
                if (null != vm.model && vm.model.countOfRecord && vm.model.totalAmt) {
                  Swal.fire("Added!", "File Added Successfully" + vm.model.countOfRecord + "/n" + vm.model.totalAmt, "success");
                } else {
                  Swal.fire("Added!", "File Added Successfully", "success");
                }
                $state.go("dash_board_view_for_physical_and_inet.inquiry");
              },
              function (error) {
                vm.loader = false;
                handleErrorResponse(error);
                $state.go("dash_board_view_for_physical_and_inet.add_detail");
              }
            );
            }
          }
        );
      } else {
        Swal.fire("Warning!", "Select The Necessary Files", "warning");
        $state.go("dash_board_view_for_physical_and_inet.add_detail");
      }
    }

    /* Verify Submit */
    function submitVerifyForm() {
      vm.finalList = [];
      angular.forEach(vm.responseResult, function (item, index) {
        var fileName = document.getElementById("selectedItem" + index).getAttribute("name");
        if (item.fileName == "RRBNB" && fileName == "RRBNB" && vm.selectedCheckBox[fileName] == true) {
          vm.finalList.push(item);
        }
        if (item.fileName == "TBNB" && fileName == "TBNB" && vm.selectedCheckBox[fileName] == true) {
          vm.finalList.push(item);
        }
        if (item.fileName == "RRBNB_BB" && fileName == "RRBNB_BB" && vm.selectedCheckBox[fileName] == true) {
          vm.finalList.push(item);
        }
        if (item.fileName == "TBNB_BB" && fileName == "TBNB_BB" && vm.selectedCheckBox[fileName] == true) {
          vm.finalList.push(item);
        }
        if (item.fileName == "RRBNB_SAKSHAM" && fileName == "RRBNB_SAKSHAM" && vm.selectedCheckBox[fileName] == true) {
          vm.finalList.push(item);
        }
        if (item.fileName == "TBNB_SAKSHAM" && fileName == "TBNB_SAKSHAM" && vm.selectedCheckBox[fileName] == true) {
          vm.finalList.push(item);
        }
        if (item.fileName == "RRBNB_NOVOPAY" && fileName == "RRBNB_NOVOPAY" && vm.selectedCheckBox[fileName] == true) {
          vm.finalList.push(item);
        }
        if (item.fileName == "TBNB_NOVOPAY" && fileName == "TBNB_NOVOPAY" && vm.selectedCheckBox[fileName] == true) {
          vm.finalList.push(item);
        }
        if (item.fileName == "TAXBOND" && fileName == "TAXBOND" && vm.selectedCheckBox[fileName] == true) {
          vm.finalList.push(item);
        }
      });
      if (vm.finalList.length > 0) {
//        Swal.fire(
//          {
//            title: "Are you sure?",
//            text: "Do you want to Really Verify this record?",
//            type: "warning",
//            showCancelButton: true,
//            cancelButtonClass: "btn-cancel",
//            confirmButtonClass: "btn-primary",
//            confirmButtonText: "Yes",
//            closeOnConfirm: false,
//          },
//          function () {


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
            vm.finalList1 = vm.finalList.map(function (item) {
              var cleanedItem = Object.assign({}, item); // Shallow copy the item
              delete cleanedItem.$$hashKey; // Delete the $$hashKey property
              return cleanedItem;
            });
            var requestData = {
              subCode: vm.subCode,
              fileMode: vm.fileMode,
              liquidationDate: $filter("date")(vm.liquidationDate, "dd-MMM-yyyy"),
              torchOut: vm.finalList1,
            };
            dashBoardViewForPhysicalAndInetService.submitVerifyForm(requestData).then(
              function (response) {
                vm.loader = false;
                vm.responseResult = response.data.result;
                var message = "File Data Verified Successfully";
                Swal.fire("Success!", message, "success");
                $state.go("dash_board_view_for_physical_and_inet.inquiry");
              },
              function (error) {
                vm.loader = false;
                handleErrorResponse(error);
                $state.go("cash_file_upload_and_net_banking_file_upload_processing.verify_detail");
              }
            );
            }
          }
        );
      } else {
        Swal.fire("Warning!", "Please Select Necessary Files", "warning");
        $state.go("dash_board_view_for_physical_and_inet.verify_detail");
      }
    }
    function inquiry() {
      vm.loader = true;
      var requestData = {
        subCode: vm.subCode,
        liquidationDate: $filter("date")(vm.liquidationDate, "dd-MMM-yyyy"),
      };
      dashBoardViewForPhysicalAndInetService.inquiry(requestData).then(
        function (response) {
          vm.loader = false;
          vm.responseResult = response.data.result.torchOut;
          vm.overAllAmt = response.data.result.overAllAmt;
          vm.overAllCount = response.data.result.overAllCount;
          vm.valueDate = response.data.result.valueDate;
          $state.go("dash_board_view_for_physical_and_inet.inquiry_detail");
        },
        function (error) {
          vm.loader = false;
          vm.schemeName = "";
          handleErrorResponse(error);
          $state.go("dash_board_view_for_physical_and_inet.inquiry");
        }
      );
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

    //
    //setTimeout(function() {
    //    let element = document.getElementById('imgDrag');
    //    if (element) {
    //        element.onpointermove = function(event) {
    //           if(event.buttons){
    //                    this.style.left     = this.offsetLeft + event.movementX + 'px'
    //                    this.style.top      = this.offsetTop  + event.movementY + 'px'
    //                    this.style.position = 'absolute'
    //                    this.draggable      = false
    //                    this.setPointerCapture(event.pointerId)
    //                }
    //        };
    //    } else {
    //        console.error('Element with ID "some-element-id" not found.');
    //    }
    //}, 1000);
  }
})();
