(function() {

    'use strict';

    angular.module('gbmUltraApp').controller(
        'cafuFileDownloadController',
        cafuFileDownloadController
    );

    cafuFileDownloadController.$inject = ['$scope', '$filter', '$state', '$stateParams', '$location', '$rootScope', 'cafuFileDownloadService', 'commonService'];

    function cafuFileDownloadController($scope, $filter, $state, $stateParams, $location, $rootScope, cafuFileDownloadService, commonService) {

        var vm = this;

        vm.currentPage = 1;
        vm.itemsPerPage = '5';
        vm.loginSolId = $rootScope.globals.currentUser.solId;
        vm.loginSolDesc = $rootScope.globals.currentUser.solDesc;
        vm.loginSolDate = $rootScope.globals.currentUser.solDate;
        vm.dateValidation = dateValidation;
        vm.solDate = $rootScope.globals.currentUser.systemDate;
        vm.bankName = $rootScope.globals.currentUser.bankName;
        vm.currentDate = new Date();
        vm.getModuleName = getModuleName;
        vm.downloadAgain = downloadAgain;
        vm.downloadSelectedFiles = downloadSelectedFiles;
        vm.textableDatePicker=textableDatePicker;
        vm.autoslashing=autoslashing;
        vm.futureNotAllowed=futureNotAllowed;

        function downloadSelectedFiles() {
            vm.loader = true;
            var requestData = {
                'subCode': vm.subCode,
                'fileType': vm.fileType,
                'fileMode': vm.fileMode,
                'liquidationDate': $filter('date')(vm.liquidationDate, 'dd-MMM-yyyy')
            };
            cafuFileDownloadService.downloadSelectedFiles(requestData).then(function(response) {
                vm.loader = false;
                if(response.data.message=='SUCCESS') {
                vm.downloadedList = response.data.result.file;
                vm.fileName=response.data.result.fileName;
                }
                if(null!=vm.downloadedList){
                var fileName = vm.fileName;
                saveAs(getBlobFromBase64StringCAFU(vm.downloadedList),fileName);
                Swal.fire("File Downloaded Successfully!", response.data.message, "success");
                }
          }, function(error) {
                vm.loader = false;
                if (error.data.message == 'File Already Downloaded') {
                 downloadAgain();
                }else {
                    handleErrorResponse1(error);
                    $state.go('cafu_file_download.inquiry');
                }
            });
        }










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



        function downloadAgain(){
                         Swal.fire({
                            title: "Are you sure?",
                            text: "File Already Downloaded Do you want to download again?",
                            type: "warning",
                            showCancelButton: true,
                            cancelButtonClass: "btn-cancel",
                            confirmButtonClass: "btn-primary",
                            confirmButtonText: "Yes",
                            closeOnConfirm: false
                            }). then((result) => {
                                     if (result.isConfirmed){
                                vm.loader= true;
                                var requestData = {
                                    'subCode': vm.subCode,
                                    'fileType': vm.fileType,
                                    'fileMode': vm.fileMode,
                                    'liquidationDate': $filter('date')(vm.liquidationDate, 'dd-MMM-yyyy')
                                };
                          cafuFileDownloadService.downloadSelectedFiles(requestData).then( (response) => {
                              vm.loader = false;
                              if(response.data.message=='SUCCESS') {
                              vm.downloadedList = response.data.result.file;
                               vm.fileName=response.data.result.fileName;
                              }
                              if(null!=vm.downloadedList){
                              var fileName = vm.fileName;
                              saveAs(getBlobFromBase64StringCAFU(vm.downloadedList),fileName);
                              Swal.fire("File Downloaded Successfully!", response.data.message, "success");
                              }
                                $state.go('cafu_file_download.inquiry');
                           },function(error) {
                             vm.loader = false;
                             handleErrorResponse(error);
                             $state.go('cafu_file_download.inquiry');
                           });
                           }
                         });
        }

          function handleErrorResponse1(e) {
                        var value=e.data;
                        var t = value.message ? value.message : "Server Error";
                        422 == e.status ? Swal.fire("Warning", t, "warning") : Swal.fire("Warning!", t, "warning")
                    }
        function getModuleName() {
            vm.loader = true;
            var requestData = {};
            cafuFileDownloadService.getModuleName(requestData).then(function(response) {
                vm.loader = false;
                vm.schemeList = response.data.result;
                angular.element('#schemeModel').modal('toggle');
            }, function(error) {
                handleErrorResponse(error);
                vm.loader = false;
            });
        }

        function dateValidation() {
            vm.dateToOptions = {
                minDate: vm.fromDate,
                maxDate: vm.currentDate
            };
            vm.dateOptions = {
                maxDate: vm.currentDate
            };
        }
    }
})();
