(function () {

    'use strict';

    angular.module('gbmUltraApp').service(
        'bondNsdlPanValidationService',
        bondNsdlPanValidationService);
    bondNsdlPanValidationService.$inject = ['$http'];
    function bondNsdlPanValidationService($http) {
        var bondNsdlPanValidationService = this;
        bondNsdlPanValidationService.getRemarksList = getRemarksList;
        bondNsdlPanValidationService.getRbiBondModuleNameDetails = getRbiBondModuleNameDetails;
        bondNsdlPanValidationService.getHalfYearList = getHalfYearList;
        bondNsdlPanValidationService.deleteSubmit = deleteSubmit;
        bondNsdlPanValidationService.submitVerifyForm = submitVerifyForm;
        bondNsdlPanValidationService.getInquiryDetails = getInquiryDetails;
        bondNsdlPanValidationService.downloadSubmit = downloadSubmit;
        bondNsdlPanValidationService.template = template;
        bondNsdlPanValidationService.fileUploadSubmit = fileUploadSubmit;
        bondNsdlPanValidationService.getDownloadModuleName = getDownloadModuleName;
        bondNsdlPanValidationService.getUploadModuleName = getUploadModuleName;
        bondNsdlPanValidationService.getInquiryModuleName = getInquiryModuleName;
        bondNsdlPanValidationService.getVerifyModuleName = getVerifyModuleName;
        bondNsdlPanValidationService.apiPanTriggerSubmit = apiPanTriggerSubmit;
        bondNsdlPanValidationService.verifyDownload = verifyDownload;
        bondNsdlPanValidationService.verifyApiPanTriggerSubmit = verifyApiPanTriggerSubmit;

        function getRbiBondModuleNameDetails(requestData) {
            return $http({
                method: 'POST',
                url: 'gbmultra/rbiBond/nsdlPan/validation/api',
                data: requestData
            })
        }

         function getRemarksList(requestData){
                                 return $http({
                                      method:'POST',
                                      url:'rbiBond/getRemarksList',
                                      data:requestData
                                 })
                    }

        function verifyDownload(requestData) {
            return $http({
                method: 'POST',
                url: 'gbmultra/rbiBond/nsdlPan/validation/verifyDownload',
                data: requestData
            })
        }
        function apiPanTriggerSubmit(requestData) {
            return $http({
                method: 'POST',
                url: 'gbmultra/rbiBond/nsdlPan/validation/api',
                data: requestData
            })
        }
        function verifyApiPanTriggerSubmit(requestData) {
            return $http({
                method: 'POST',
                url: 'gbmultra/rbiBond/nsdlPan/validation/verifyApi',
                data: requestData
            })
        }
        function getVerifyModuleName(requestData) {
            return $http({
                method: 'POST',
                url: 'gbmultra/rbiBond/nsdlPan/validation/verify',
                data: requestData
            })
        }
        function getInquiryModuleName(requestData) {
            return $http({
                method: 'POST',
                url: 'gbmultra/rbiBond/nsdlPan/validation/inquiry',
                data: requestData
            })
        }
        function getUploadModuleName(requestData) {
            return $http({
                method: 'POST',
                url: 'gbmultra/rbiBond/nsdlPan/validation/getUploadModuleName',
                data: requestData
            })
        }
        function getDownloadModuleName(requestData) {
            return $http({
                method: 'POST',
                url: 'gbmultra/rbiBond/nsdlPan/validation/download',
                data: requestData
            })
        }

        function getHalfYearList(requestData) {
            return $http({
                method: 'POST',
                url: 'gbmultra/rbiBond/nsdlPan/validation/api',
                data: requestData
            })
        }
        function fileUploadSubmit(file, requestData) {
            var fd = new FormData();
            fd.append('file', file);
            fd.append('dto', new Blob([JSON.stringify(requestData)], {
                type: "application/json"
            }));
            return $http.post('gbmultra/rbiBond/nsdlPan/validation/upload', fd, {
                transformRequest: angular.identity,
                transformResponse: angular.identity,
                headers: {
                    'Content-Type': undefined,
                    'Custom-Content-Type': 'upload'
                }
            });
        }
        function template(requestData) {
            return $http({
                method: 'POST',
                url: 'gbmultra/rbiBond/nsdlPan/validation/template',
                data: requestData
            })
        }
        function downloadSubmit(requestData) {
            return $http({
                method: 'POST',
                url: 'gbmultra/rbiBond/nsdlPan/validation/download',
                data: requestData
            })
        }
        function deleteSubmit(requestData) {
            return $http({
                method: 'POST',
                url: 'gbmultra/rbiBond/nsdlPan/validation/delete',
                data: requestData
            })
        }
        function submitVerifyForm(requestData) {
            return $http({
                method: 'POST',
                url: 'gbmultra/rbiBond/nsdlPan/validation/verify',
                data: requestData
            })
        }
        function getInquiryDetails(requestData) {
            return $http({
                method: 'POST',
                url: 'gbmultra/rbiBond/nsdlPan/validation/inquiry',
                data: requestData
            })
        }

    }

})();