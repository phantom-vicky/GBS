(function () {
  "use strict";

  angular.module("gbmUltraApp").controller("brokerageInvoiceConfirmationController", brokerageInvoiceConfirmationController);

  brokerageInvoiceConfirmationController.$inject = [
    "$scope",
    "$filter",
    "$state",
    "$stateParams",
    "$location",
    "$rootScope",
    "brokerageInvoiceConfirmationService",
    "commonService",
  ];

  function brokerageInvoiceConfirmationController(
    $scope,
    $filter,
    $state,
    $stateParams,
    $location,
    $rootScope,
    brokerageInvoiceConfirmationService,
    commonService
  ) {
    var vm = this;
    vm.brokerAgencyCodeTorch = brokerAgencyCodeTorch;
    vm.currentPage = 1;
    vm.itemsPerPage = "5";
    vm.loginSolId = $rootScope.globals.currentUser.solId;
    vm.loginSolDesc = $rootScope.globals.currentUser.solDesc;
    vm.solDate = $rootScope.globals.currentUser.systemDate;
    //        vm.uploadFile = uploadFile;
    vm.bankName = $rootScope.globals.currentUser.bankName;
    vm.getRemarksList = getRemarksList;
    vm.validateFile = validateFile;
    vm.currentDate = new Date();
    vm.currentDate = $filter("date")(vm.currentDate, "dd/MM/yyyy");
    vm.getModuleName = getModuleName;
    vm.getInvoiceConfirmationDetails = getInvoiceConfirmationDetails;
    vm.getInvoiceConfirmationVerify = getInvoiceConfirmationVerify;
    vm.getInvoiceConfirmationInquiry = getInvoiceConfirmationInquiry;
    vm.dateValidation1 = dateValidation1;
    vm.fromToDateValidation = fromToDateValidation;
    vm.dateValidation = dateValidation;
    vm.submitAddForm = submitAddForm;
    vm.rejectReason = rejectReason;
    vm.alert = alert;
    //         vm.handleFile = handleFile;
    vm.addSubmit = addSubmit;
    vm.submitDeleteForm=submitDeleteForm;
    vm.getDeleteSubmit = getDeleteSubmit;
    vm.verifySubmit = verifySubmit;
    vm.paymentConfirmation = paymentConfirmation;

    if ($state.current.name == "rbibond_brokerage_invoice_confirmation.add_details") {
      vm.schemeCode = $stateParams.obj.schemeCode;
      vm.subscriptionFromDate = $stateParams.obj.subscriptionFromDate;
      vm.subscriptionToDate = $stateParams.obj.subscriptionToDate;
      vm.invoiceNumber = $stateParams.obj.invoiceNumber;
      vm.brokerAgencyCode = $stateParams.obj.brokerAgencyCode;
      vm.schemeName = $stateParams.obj.schemeName;
      getInvoiceConfirmationDetails("submit");
    }

    if ($state.current.name == "rbibond_brokerage_invoice_confirmation.inquiry_details") {
      vm.schemeCode = $stateParams.obj.schemeCode;
      vm.subscriptionFromDate = $stateParams.obj.subscriptionFromDate;
      vm.subscriptionToDate = $stateParams.obj.subscriptionToDate;
      vm.invoiceNumber = $stateParams.obj.invoiceNumber;
      vm.brokerAgencyCode = $stateParams.obj.brokerAgencyCode;
      vm.schemeName = $stateParams.obj.schemeName;
      getInvoiceConfirmationInquiry("submit");
    }
    if ($state.current.name == "rbibond_brokerage_invoice_confirmation.verify_details") {
      vm.schemeCode = $stateParams.obj.schemeCode;
      vm.invoiceNumber = $stateParams.obj.invoiceNumber;
      vm.brokerAgencyCode = $stateParams.obj.brokerAgencyCode;
      vm.schemeName = $stateParams.obj.schemeName;
      getInvoiceConfirmationVerify("submit");
    }

    function configDataTable() {
      vm.getTitleDescription = {
        title: "Brokerage Invocie Confirmation",
        solId: $rootScope.globals.currentUser.solId,
        solDesc: $rootScope.globals.currentUser.solDesc,
      };
      vm.dataTableOption = configDataTableOptions(vm.getTitleDescription);
    }

    vm.toggleAll = function (selectAll) {
      angular.forEach(vm.brokerageInvoiceVerifyDetials, function (item) {
        item.selected = selectAll;
      });
    };
    vm.toggleAllAdd = function (selectAll) {
          angular.forEach(vm.brokerageInvocieConfirmationDetials, function (item) {
            item.selected = selectAll;
          });
        };

    vm.isAnyCheckboxSelected = function () {
      return vm.brokerageInvocieConfirmationDetials.some(function (item) {
        return item.selected;
      });
    };
    vm.isAnyCheckboxSelectedVer = function () {
          return vm.brokerageInvoiceVerifyDetials.some(function (item) {
            return item.selected;
          });
        };

    function getModuleName() {
      vm.loader = true;
      vm.referenceId = "TAX";
      var requestData = {
        referenceId: vm.referenceId,
      };
      brokerageInvoiceConfirmationService.getRbiBondModuleNameDetails(requestData).then(
        function (response) {
          vm.loader = false;
          vm.schemeList = response.data.result;
        },
        function (error) {
          vm.loader = false;
          handleErrorResponse(error);
        }
      );
    }

    function alert(item) {
      for (let i = 0; i < vm.schemeList.length; i++) {
        if (vm.schemeList[i].subCode == item) {
          vm.schemeName = vm.schemeList[i].subCodeDescription;
        }
      }
    }

    function brokerAgencyCodeTorch(flag) {
      vm.loader = true;
      var requestData = {
        schemeCode: vm.schemeCode,
        flag:flag
      };
      brokerageInvoiceConfirmationService.getBrokerAgencyCode(requestData).then(
        function (response) {
          vm.loader = false;
          vm.agencyList = response.data.result.agencyOuts;
          angular.element("#agencyCodeModal").modal("toggle");
        },
        function (error) {
          vm.loader = false;
          handleErrorResponse(error);
        }
      );
    }

    function getInvoiceConfirmationDetails(flag) {
      vm.loader = true;
      if (flag == "submit") {
        var requestData = {
          schemeCode: vm.schemeCode,
          subFromDate: vm.subscriptionFromDate ? moment(vm.subscriptionFromDate).format("DD-MMM-YYYY") : "",
          subToDate: vm.subscriptionToDate ? moment(vm.subscriptionToDate).format("DD-MMM-YYYY") : "",
          invoiceNumber: vm.invoiceNumber,
          agencyCode: vm.brokerAgencyCode,
        };
      }
      if (flag == "torch") var requestData = {};

      brokerageInvoiceConfirmationService.getInvoiceConfirmationDetails(requestData).then(
        function (response) {
          vm.loader = false;
          if (flag == "submit") {
            vm.brokerageInvocieConfirmationDetials = response.data.result;
            configDataTable();
          } else if (flag == "torch") {
            vm.schemeNameList = response.data.result;
            angular.element("#schemeNameSearcherPopUp").modal("toggle");
          }
        },
        function (error) {
          vm.loader = false;
          if ((error.data.message == "No Records Found") | (error.data.message == "NDF")) {
            vm.noDataFound = error.data.message;
          } else {
            handleErrorResponse(error);
          }
        }
      );
    }

    vm.getSelectedRows = function () {
      return vm.brokerageInvocieConfirmationDetials.filter(function (item) {
        return item.selected;
      });
    };

    function getInvoiceConfirmationVerify(flag) {
        vm.loader = true;
        var requestData = {};

        if (flag == "submit") {
            requestData = {
                schemeCode: vm.schemeCode,
                invoiceNumber: vm.invoiceNumber,
                agencyCode: vm.brokerAgencyCode,
            };
        }

        brokerageInvoiceConfirmationService.getInvoiceConfirmationVerify(requestData).then(
            function (response) {
                vm.loader = false;
                vm.noDataFound = false; // Reset noDataFound
                if (flag == "submit") {
                    if (response.data.result && response.data.result.length > 0) {
                        vm.brokerageInvoiceVerifyDetials = response.data.result;
                        configDataTable();
                    } else {
                        vm.noDataFound = true; // No data in response
                        vm.brokerageInvoiceVerifyDetials = []; // Clear any existing data
                    }
                } else if (flag == "torch") {
                    vm.schemeNameList = response.data.result;
                    angular.element("#schemeNameSearcherPopUp").modal("toggle");
                }
            },
            function (error) {
                vm.loader = false;
                vm.brokerageInvoiceVerifyDetials = []; // Clear data in case of error
                if ((error.data.message == "No Records Found") || (error.data.message == "NDF")) {
                    vm.noDataFound = true; // Explicitly set noDataFound to true
                } else {
                    handleErrorResponse(error);
                }
            }
        );
    }


    vm.getSelectedVerifyRows = function () {
      return vm.brokerageInvoiceVerifyDetials.filter(function (item) {
        return item.selected;
      });
    };

    function getInvoiceConfirmationInquiry(flag) {

      vm.loader = true;
      if (flag == "submit") {
        var requestData = {
          schemeCode: vm.schemeCode,
          subFromDate: vm.subscriptionFromDate ? moment(vm.subscriptionFromDate).format("DD-MMM-YYYY") : "",
          subToDate: vm.subscriptionToDate ? moment(vm.subscriptionToDate).format("DD-MMM-YYYY") : "",
          invoiceNumber: vm.invoiceNumber,
          agencyCode: vm.brokerAgencyCode,
        };
      }
      if (flag == "torch") var requestData = {};

      brokerageInvoiceConfirmationService.getInvoiceConfirmationInquiry(requestData).then(
        function (response) {
          vm.loader = false;
          if (flag == "submit") {
            vm.brokerageInvocieInquiryDetials = response.data.result;
            configDataTable();
          } else if (flag == "torch") {
            vm.schemeNameList = response.data.result;
            angular.element("#schemeNameSearcherPopUp").modal("toggle");
          }
        },
        function (error) {
          vm.loader = false;
          if ((error.data.message == "No Records Found") | (error.data.message == "NDF")) {
            vm.noDataFound = error.data.message;
          } else {
            handleErrorResponse(error);
          }
        }
      );
    }

    vm.getSelectedRows = function () {
      return vm.brokerageInvocieConfirmationDetials.filter(function (item) {
        return item.selected;
      });
    };

    vm.getSelectedRows = function () {
          return vm.brokerageInvocieConfirmationDetials.filter(function (item) {
            return item.selected;
          });
        };

    function rejectReason() {
      angular.element("#permissionMappingDelete").modal("toggle");
    }
    //    function getAddRejection()
    //    {

    //    }

    function addSubmit() {
      //    $scope.x.invRecieveDate = $filter('date')($scope.x.invRecieveDate, 'dd/MM/yyyy');
      //    $scope.x.invRaisedOnDate = $filter('date')($scope.x.invRaisedOnDate, 'dd/MM/yyyy');

//      Swal.fire(
//        {
//          title: "Are you sure?",
//          text: "Do you want to Really Add this record?",
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
          var selectedRows = vm.getSelectedRows();
          if (selectedRows.length > 0) {
            selectedRows.forEach((row) => {
              delete row.selected;
              delete row.$$hashKey;
            });
            var requestData = {};
            requestData.schemeCode = vm.schemeCode;
            requestData.brokerageInvoiceConfirmationResponseDTOList = selectedRows;

            // Loop through each item in the list to format the dates/ recieve date should be higher
            requestData.brokerageInvoiceConfirmationResponseDTOList.forEach(function(item) {
                item.invRaisedOnDate = $filter("date")(item.invRaisedOnDate, "dd-MMM-yyyy");
                item.invRecieveDate = $filter("date")(item.invRecieveDate, "dd-MMM-yyyy");
                    item.invoiceDate = $filter("date")(new Date(item.invoiceDate.split('/').reverse().join('-')), "dd-MMM-yyyy");
;
            });

            brokerageInvoiceConfirmationService.invoiceVerify(requestData).then(
              function (response) {
                vm.loader = false;
                Swal.fire("Success!", "Brokerage Invoice confirmation Added Successfully", "success");
                $state.go("rbibond_brokerage_invoice_confirmation.inquiry");
              },
              function (error) {
                vm.loader = false;
                handleErrorResponse(error);
                $state.go("rbibond_brokerage_invoice_confirmation.add");
              }
            );
          }
          }
        }
      );
    }

    //verify submit
    function verifySubmit() {
          //    $scope.x.invRecieveDate = $filter('date')($scope.x.invRecieveDate, 'dd/MM/yyyy');
          //    $scope.x.invRaisedOnDate = $filter('date')($scope.x.invRaisedOnDate, 'dd/MM/yyyy');

//          Swal.fire(
//            {
//              title: "Are you sure?",
//              text: "Do you want to Really Verify this record?",
//              type: "warning",
//              showCancelButton: true,
//              cancelButtonClass: "btn-cancel",
//              confirmButtonClass: "btn-primary",
//              confirmButtonText: "Yes",
//              closeOnConfirm: false,
//            },
//            function () {


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
              var selectedRows = vm.getSelectedVerifyRows();
              if (selectedRows.length > 0) {
                selectedRows.forEach((row) => {
                  delete row.selected;
                  delete row.$$hashKey;
                });
                var requestData = {};
                requestData.schemeCode = vm.schemeCode;
                requestData.brokerageInvoiceConfirmationResponseDTOList = selectedRows;

                brokerageInvoiceConfirmationService.verifySubmit(requestData).then(
                  function (response) {
                    vm.loader = false;
                    Swal.fire("Success!", "Brokerage Invoice confirmation Verified Successfully", "success");
                    $state.go("rbibond_brokerage_invoice_confirmation.inquiry");
                  },
                  function (error) {
                    vm.loader = false;
                    handleErrorResponse(error);
                    $state.go("rbibond_brokerage_invoice_confirmation.verify");
                  }
                );
              }
              }
            }
          );
        }


    function submitDeleteForm() {
      var selectedRows = vm.getSelectedRows();

      if (selectedRows.length > 0) {
        // Remove unnecessary properties from each selected row
        selectedRows.forEach((row) => {
          delete row.selected;
          delete row.$$hashKey;
        });
        vm.loader = true;
        var requestData = {
          schemeCode:vm.schemeCode,
          brokerageInvoiceConfirmationResponseDTOList: selectedRows,
          remarks: vm.remarks,
        };
        brokerageInvoiceConfirmationService.submitDeleteForm(requestData).then(
          function (response) {
            if (response.data.message == "SUCCESS") {
              Swal.fire("Rejected!", "Brokerage Invoice Confirmation Rejected Successfully", "success");
              $state.go("rbibond_brokerage_invoice_confirmation.inquiry");
            } else {
              Swal.fire("WARNING!", response.data.message, "error");
              $state.go("rbibond_brokerage_invoice_confirmation.verify");
            }
            const backdrop = document.querySelector(".modal-backdrop");
            if (backdrop) {
              backdrop.parentNode.removeChild(backdrop);
              document.body.classList.remove("modal-open");
            }
          },
          function (error) {
            vm.loader = false;
            handleErrorResponse(error);
            const backdrop = document.querySelector(".modal-backdrop");
            if (backdrop) {
              backdrop.parentNode.removeChild(backdrop);
              document.body.classList.remove("modal-open");
            }
            location.href = "#!/rbibond_brokerage_invoice_confirmation/inquiry";
          }
        );
      }
    }

    function getDeleteSubmit() {
    console.log("getDeleteSubmit working");
      var selectedRows = vm.getSelectedVerifyRows();

            if (selectedRows.length > 0) {
              // Remove unnecessary properties from each selected row
              selectedRows.forEach((row) => {
                delete row.selected;
                delete row.$$hashKey;
              });
              vm.loader = true;
              var requestData = {
                schemeCode:vm.schemeCode,
                brokerageInvoiceConfirmationResponseDTOList: selectedRows,
                remarks: vm.remarks,
              };
              brokerageInvoiceConfirmationService.getDeleteSubmit(requestData).then(
                function (response) {
                console.log("success");
                  if (response.data.message == "SUCCESS") {
                  console.log("inif");
                    Swal.fire("Rejected!", "Brokerage Invoice Confirmation Rejected Successfully", "success");
                    $state.go("rbibond_brokerage_invoice_confirmation.inquiry");
                  } else {
                  console.log("inelse");
                    Swal.fire("WARNING!", response.data.message, "error");
                    $state.go("rbibond_brokerage_invoice_confirmation.verify");
                  }
                  const backdrop = document.querySelector(".modal-backdrop");
                  if (backdrop) {
                  console.log("inbackdrop");
                    backdrop.parentNode.removeChild(backdrop);
                    document.body.classList.remove("modal-open");
                  }
                },
                function (error) {
                console.log("errorr");
                  vm.loader = false;
                  handleErrorResponse(error);
                  const backdrop = document.querySelector(".modal-backdrop");
                  if (backdrop) {
                    backdrop.parentNode.removeChild(backdrop);
                    document.body.classList.remove("modal-open");
                  }
                  location.href = "#!/rbibond_brokerage_invoice_confirmation/inquiry";
                }
              );
            }
    }


    function dateValidation() {
      vm.toDate = "";
      vm.dateToOptions = {
        minDate: vm.subscriptionFromDate,
        maxDate: vm.solDate,
      };
      vm.dateOptions = {
        maxDate: vm.solDate,
      };
    }

    /*function uploadFile(){

              			var $el = angular.element("input[type='file']");
              			Swal.fire({
              				  title: "Are you sure?",
              				  text: "Are you sure want to upload this file?",
              				  type: "warning",
              				  showCancelButton: true,
              				  cancelButtonClass: "btn-cancel",
              				  confirmButtonClass: "btn-primary",
              				  confirmButtonText: "Yes!",
              				  closeOnConfirm: false,
              				  showLoaderOnConfirm: true
              				},
              				function(){
              				    vm.loader = true;
              					brokerageInvoiceConfirmationService.save(vm.file)
              			        .then(
              			                function (response) {
              			                   vm.loader = false;
              		                		var json = JSON.parse(response.data);
              		            		    Swal.fire("Uploaded!", "File Uploaded Successfully", "success");
              		            		    vm.file = "";
              		                		clearFileInput($el);
              		                		location.href = '#!/state_district_pin/action/inquiry';

              			                }, function (error){
              			                   vm.loader = false;
              			                   var json = JSON.parse(error.data);
              			                   Swal.fire("Error!", json.message, "error");
              		                       clearFileInput($el);
              			                });
              				});

              		}
*/
    // Function to validate the uploaded file
    function validateFile() {
      let file = document.getElementById("file").files[0];

      if (!file) {
        document.getElementById("message").innerText = "No file uploaded.";
        vm.stDtPin.file = "";
        vm.file = "";
        return false;
      }

      let reader = new FileReader();
      reader.onload = function (e) {
        let data = new Uint8Array(e.target.result);

        // Parse the XLSX file
        let workbook = XLSX.read(data, { type: "array" });
        let firstSheet = workbook.Sheets[workbook.SheetNames[0]];

        // Convert sheet to JSON
        let rows = XLSX.utils.sheet_to_json(firstSheet);

        // Check if the file is empty or doesn't have data
        if (rows.length === 0) {
          document.getElementById("message").innerText = "The file is empty.";
          document.getElementById("file").value = "";
          vm.stDtPin.file = "";

          return;
        }

        // Validation: compare the file format
        let expectedHeaders = [
          "VBMS Reference Number",
          "Payment Ref",
          "Paid On",
          "Invoice Receive Date",
          "Invoice Raised on",
          "VBMS Maker",
          "VBMS Checker",
        ]; // replace with actual expected columns
        let actualHeaders = Object.keys(rows[0]);

        // Check if actual headers contain the expected headers
        let valid = expectedHeaders.every((header) => actualHeaders.includes(header));

        if (!valid) {
          document.getElementById("message").innerText = "Invalid file format. Please upload a valid template.";
          document.getElementById("file").value = "";
          vm.stDtPin.file = "";

          return;
        }

        // If valid, clear the message
        document.getElementById("message").innerText = "";
      };
      reader.onerror = function (ex) {
        console.log(ex);
        const messageElement = document.getElementById("message");
        if (messageElement) {
          messageElement.innerText = "Error reading the file.";
        }
      };
      // Use readAsArrayBuffer for better performance with larger files
      reader.readAsArrayBuffer(file);
    }
    function submitAddForm() {
      // Example usage in your view model or component
      var filesArray = [vm.file];

//      Swal.fire(
//        {
//          title: "Are you sure?",
//          text: "Do you want to Really Add this record?",
//          type: "warning",
//          showCancelButton: true,
//          cancelButtonClass: "btn-cancel",
//          confirmButtonClass: "btn-primary",
//          confirmButtonText: "Yes, Add it!",
//          closeOnConfirm: false,
//          showLoaderOnConfirm: true,
//        },
//        function () {


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

          const formData = new FormData();
          formData.append("file", vm.file); // Append the selected file
          formData.append(
            "brokerageInvoiceConfirmationDTO",
            new Blob(
              [
                JSON.stringify({
                  schemeCode: vm.schemeCode,
                }),
              ],
              { type: "application/json" }
            )
          );
          brokerageInvoiceConfirmationService.submitAddForm(formData).then(
            function (response) {
              vm.loader = false;
              //vm.model = response.data.result;

              Swal.fire("Uploaded!", "Invoice Confirmation verified Successfully", "success");

              $state.go("rbibond_brokerage_invoice_confirmation.inquiry");
            },
            function (error) {
              handleErrorResponse(error);
              $state.go("rbibond_brokerage_invoice_confirmation.add");
            }
          );
          }
        }
      );
    }

    function getRemarksList() {
      console.log("Working");
      vm.referenceId = "056";
      var requestData = { referenceId: vm.referenceId };

      brokerageInvoiceConfirmationService.getRemarksList(requestData).then(
        function (response) {
          vm.remarksList = response.data.result || [];
        },
        function (error) {
          handleErrorResponse(error);
        }
      );
    }


    function fromToDateValidation() {
    //  vm.subscriptionToDate = "";
   vm.dateFromOptions = {
                maxDate: vm.solDate
            };
            vm.dateToOptions = {
              minDate: vm.fromDate,  maxDate: vm.solDate
            };
    }

    function dateValidation1() {
      vm.toDate = "";

      vm.dateToOptions = {
        minDate: vm.fromDate,
        maxDate: vm.today,
      };
      vm.dateOptions = {
        maxDate: vm.today,
      };
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

         function paymentConfirmation() {
               vm.loader = true;
               brokerageInvoiceConfirmationService.paymentConfirmation().then(function (response) {
                   vm.loader = false;
                   var fileName = response.data.result.fileName;
                   var fileData = response.data.result.fileData;
                   var byteCharacters = atob(fileData);
                   var byteNumbers = new Array(byteCharacters.length);
                   for (var i = 0; i < byteCharacters.length; i++) {
                       byteNumbers[i] = byteCharacters.charCodeAt(i);
                   }
                   var byteArray = new Uint8Array(byteNumbers);
                   var blob = new Blob([byteArray], { type: "application/vnd.ms-excel" });
                   var blobUrl = window.URL.createObjectURL(blob);
                   var link = document.createElement("a");
                   link.href = blobUrl;
                   link.download = fileName;
                   document.body.appendChild(link);
                   link.click();
                   document.body.removeChild(link);
                   setTimeout(function () {
                       window.URL.revokeObjectURL(blobUrl);
                   }, 100);

                   Swal.fire("Downloaded", "Downloaded successfully.", "success");

               }, function (error) {
                   vm.loader = false;
                   handleErrorResponse(error);
               });
           }


        function futureNotAllowed(id,valueDate)
        {
         commonService.futureNotAllowed(id,valueDate);
        }
  }
})();
