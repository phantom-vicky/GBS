(function() {

    'use strict';

    angular.module('gbmUltraApp').controller('rbiBondPanStatusUpdateForTdsController',
        rbiBondPanStatusUpdateForTdsController);
    rbiBondPanStatusUpdateForTdsController.$inject = ['$scope', '$filter', '$state', '$rootScope',
        '$stateParams', '$location', '$timeout', 'rbiBondPanStatusUpdateForTdsService', 'commonService'
    ];
    function rbiBondPanStatusUpdateForTdsController($scope, $filter, $state, $rootScope, $stateParams,
        $location, $timeout, rbiBondPanStatusUpdateForTdsService, commonService) {
		var vm = this;
		vm.moduleName="RBI BOND";
		vm.fileUploadSubmit=fileUploadSubmit;
		vm.downloadSubmit=downloadSubmit;
		vm.template=template;
        vm.solDate = new Date($rootScope.globals.currentUser.solDate);

		vm.errorMessage = "Please upload a .xlsx file.";
		vm.handleFiles = function(input) {
        if(vm.fileType){
        if (vm.files && (vm.files.type!== "application/vnd.ms-excel")) {
           vm.invalidFile = true;
            document.getElementById('fileInput').value = ''; // Clear the file input
           }else{
            vm.invalidFile = false;
           }
           }else{
           document.getElementById('fileInput').value = ''; // Clear the file input
           Swal.fire("Warning!","Kindly Select FileType", "warning");
           $state.go('rbi_bond_pan_status_update_for_tax.upload');
           }
   $scope.$apply(); // Ensure scope is updated in AngularJS
};
		function fileUploadSubmit(){
                Swal.fire({
                  title: "Are you sure?",
                  text: "Do you want to Really Upload this record?",
                  icon: "warning", // use "icon", not "type"
                  showCancelButton: true,
                  confirmButtonText: "Yes,Add it!",
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
                       'schemeCode' : vm.schemeCode,
                       'schemeName' : vm.schemeName,
                       'type' : vm.type,
                       'fileType' : vm.fileType
                   };
                   rbiBondPanStatusUpdateForTdsService.fileUploadSubmit(vm.files,requestData).then(function (response){
                       vm.loader = false;
                       var value=JSON.parse(response.data);
                       Swal.fire("Uploaded!", "File Upload Successfully","success");
                       $state.go('rbi_bond_pan_status_update_for_tax.download');
                   },function(error){
                   vm.loader = false;
                  if(error.data.message == 'No Records Found' | error.data.message == 'NDF'){
                  vm.noDataFound = error.data.message;
                  Swal.fire("Warning!",vm.noDataFound,"warning");
                   } else{
                   handleErrorResponse1(error);
                   $state.go('rbi_bond_pan_status_update_for_tax.upload');
                 }
              })
              }
             });
            }
                    function handleErrorResponse1(e) {
                        if (typeof e.data === 'string') {
                            var value = JSON.parse(e.data);
                            var t = value.message ? value.message : "Server Error";
                            422 == e.status ? Swal.fire("Warning", t, "warning") : Swal.fire("Warning!", t, "warning")
                        }else if(typeof e.data === 'object' && e.data !== null){
                            var value = e.data;
                            var t = value.message ? value.message : "Server Error";
                            422 == e.status ? Swal.fire("Warning", t, "warning") : Swal.fire("Warning!", t, "warning")
                        }
                                       }
                   vm.getUploadModuleName=getUploadModuleName;
                   function getUploadModuleName(){
                   		        vm.loader = true;
                                   vm.referenceId = 'RBI Bond';
                                   var requestData = {
                                       'referenceId': vm.referenceId
                                   }
                                   rbiBondPanStatusUpdateForTdsService.getUploadModuleName(requestData).then(function(response) {
                                       vm.loader = false;
                                       vm.schemeNameList = response.data.result;
                                       angular.element('#levelNameModal').modal('toggle');
                                   }, function(error) {
                                       vm.loader = false;
                                       handleErrorResponse(error);
                                   });
                   }

                   vm.getDownloadModuleName=getDownloadModuleName;
function getDownloadModuleName(){
		        vm.loader = true;
                vm.referenceId = 'RBI Bond';
                var requestData = {
                    'referenceId': vm.referenceId
                }
                rbiBondPanStatusUpdateForTdsService.getDownloadModuleName(requestData).then(function(response) {
                    vm.loader = false;
                    vm.schemeNameList = response.data.result;
                    angular.element('#levelNameModal').modal('toggle');
                }, function(error) {
                vm.loader = false;
                    handleErrorResponse(error);
                });
}
		function downloadSubmit(){
		vm.loader = true;
		var requestData = {
           'schemeCode' : vm.schemeCode,
           'schemeName' : vm.schemeName,
           'fileType' : vm.fileType,
           'fromDate': $filter('date')(vm.fromDate, 'dd-MMM-yyyy')
       };
         rbiBondPanStatusUpdateForTdsService.downloadSubmit(requestData).then(function(response){
                   vm.loader = false;
                    if(response.data.result.fileName) {
                   vm.fileData = response.data.result.file;
                   vm.fileName = response.data.result.fileName;
                   saveAs(getBlobFromBase64String(vm.fileData), vm.fileName)
                   }
                   Swal.fire("Downloaded", response.data.message, "success");
                   }, function(error) {
                   vm.loader = false;
                  if(error.data.message == 'No Records Found' | error.data.message == 'No PAN To Download'| error.data.message == 'NDF'){
                   vm.noDataFound = error.data.message;
                   Swal.fire("Warning!",vm.noDataFound,"warning");
                  }
                  else{
                  handleErrorResponse(error);
                  $state.go("rbi_bond_pan_status_update_for_tax.download");
                  }
           })
		}
		function template(){
        		if(vm.fileType){
        		vm.loader = true;
        		var requestData = {
                   'schemeCode' : vm.schemeCode,
                   'schemeName' : vm.schemeName,
                   'fileType' : vm.fileType,
                   'fromDate': $filter('date')(vm.fromDate, 'dd-MMM-yyyy')
               };
                 rbiBondPanStatusUpdateForTdsService.template(requestData).then(function(response){
                   vm.loader = false;
                   vm.verifiedFileData = response.data.result.fileData;
                   vm.verifiedFileName = response.data.result.fileName;
                   saveAs(getBlobFromBase64String(vm.verifiedFileData), vm.verifiedFileName)
                   Swal.fire("Downloaded", response.data.message, "success");
                        }, function(error) {
                         vm.loader = false;
                      if(error.data.message == 'No Records Found' | error.data.message == 'NDF'){
                       vm.noDataFound = error.data.message;
                       Swal.fire("Warning!",vm.noDataFound,"warning");
                        }
                      else{
                      handleErrorResponse(error);
                      $state.go("rbi_bond_pan_status_update_for_tax.upload");
                      }
                     })
                     }else{
                     Swal.fire("Warning!","Kindly Select FileType", "warning");
                     $state.go('rbi_bond_pan_status_update_for_tax.upload');
                     }
        		}
    vm.fileTypeList=[
            {
                "subCodeDescription": "Redemption",
                "subCode": "R001"
            },
            {
                "subCodeDescription": "Interest",
                "subCode": "I001"
            }
        ];
        vm.fromDateValidation=fromDateValidation;
     function fromDateValidation() {
               vm.fromDateCalenderOptions = {
                  minDate: vm.fromDate,
                  maxDate:  $filter('date')(vm.solDate, 'dd/MM/yyyy')
              };
            }
       } })();