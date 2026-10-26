(function () {

    'use strict';

    angular.module('gbmUltraApp').service(
        'bondNsdlPanValidationRedemptionOnMaturityService',
        bondNsdlPanValidationRedemptionOnMaturityService);
    bondNsdlPanValidationRedemptionOnMaturityService.$inject = ['$http'];
    function bondNsdlPanValidationRedemptionOnMaturityService($http) {
        var bondNsdlPanValidationRedemptionOnMaturityService = this;
        bondNsdlPanValidationRedemptionOnMaturityService.getRemarksList = getRemarksList;
        bondNsdlPanValidationRedemptionOnMaturityService.getRbiBondModuleNameDetails = getRbiBondModuleNameDetails;
        bondNsdlPanValidationRedemptionOnMaturityService.deleteSubmit = deleteSubmit;
        bondNsdlPanValidationRedemptionOnMaturityService.submitVerifyForm = submitVerifyForm;
        bondNsdlPanValidationRedemptionOnMaturityService.getInquiryDetails = getInquiryDetails;
        bondNsdlPanValidationRedemptionOnMaturityService.downloadSubmit = downloadSubmit;
        bondNsdlPanValidationRedemptionOnMaturityService.template = template;
        bondNsdlPanValidationRedemptionOnMaturityService.fileUploadSubmit = fileUploadSubmit;
        bondNsdlPanValidationRedemptionOnMaturityService.getDownloadModuleName = getDownloadModuleName;
        bondNsdlPanValidationRedemptionOnMaturityService.getUploadModuleName = getUploadModuleName;
        bondNsdlPanValidationRedemptionOnMaturityService.getInquiryModuleName = getInquiryModuleName;
        bondNsdlPanValidationRedemptionOnMaturityService.getVerifyModuleName = getVerifyModuleName;
        bondNsdlPanValidationRedemptionOnMaturityService.apiPanTriggerSubmit = apiPanTriggerSubmit;
        bondNsdlPanValidationRedemptionOnMaturityService.verifyDownload = verifyDownload;
        bondNsdlPanValidationRedemptionOnMaturityService.verifyApiPanTriggerSubmit = verifyApiPanTriggerSubmit;

        function getRbiBondModuleNameDetails(requestData) {
            return $http({
                method: 'POST',
                url: 'gbmultra/rbiBond/nsdlPan/validation/redemptionOnMaturity/api',
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
                url: 'gbmultra/rbiBond/nsdlPan/validation/redemptionOnMaturity/verifyDownload',
                data: requestData
            })
        }
        function apiPanTriggerSubmit(requestData) {
            return $http({
                method: 'POST',
                url: 'gbmultra/rbiBond/nsdlPan/validation/redemptionOnMaturity/api',
                data: requestData
            })
        }
         function verifyApiPanTriggerSubmit(requestData) {
            return $http({
                method: 'POST',
                url: 'gbmultra/rbiBond/nsdlPan/validation/redemptionOnMaturity/verifyApi',
                data: requestData
            })
        }
        function getVerifyModuleName(requestData) {
            return $http({
                method: 'POST',
                url: 'gbmultra/rbiBond/nsdlPan/validation/redemptionOnMaturity/verify',
                data: requestData
            })
        }
        function getInquiryModuleName(requestData) {
            return $http({
                method: 'POST',
                url: 'gbmultra/rbiBond/nsdlPan/validation/redemptionOnMaturity/inquiry',
                data: requestData
            })
        }
        function getUploadModuleName(requestData) {
            return $http({
                method: 'POST',
                url: 'gbmultra/rbiBond/nsdlPan/validation/redemptionOnMaturity/getUploadModuleName',
                data: requestData
            })
        }
        function getDownloadModuleName(requestData) {
            return $http({
                method: 'POST',
                url: 'gbmultra/rbiBond/nsdlPan/validation/redemptionOnMaturity/download',
                data: requestData
            })
        }
        function fileUploadSubmit(file, requestData) {
            var fd = new FormData();
            fd.append('file', file);
            fd.append('dto', new Blob([JSON.stringify(requestData)], {
                type: "application/json"
            }));
            return $http.post('gbmultra/rbiBond/nsdlPan/validation/redemptionOnMaturity/upload', fd, {
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
                url: 'gbmultra/rbiBond/nsdlPan/validation/redemptionOnMaturity/template',
                data: requestData
            })
        }
        function downloadSubmit(requestData) {
            return $http({
                method: 'POST',
                url: 'gbmultra/rbiBond/nsdlPan/validation/redemptionOnMaturity/download',
                data: requestData
            })
        }
        function deleteSubmit(requestData) {
            return $http({
                method: 'POST',
                url: 'gbmultra/rbiBond/nsdlPan/validation/redemptionOnMaturity/delete',
                data: requestData
            })
        }
        function submitVerifyForm(requestData) {
            return $http({
                method: 'POST',
                url: 'gbmultra/rbiBond/nsdlPan/validation/redemptionOnMaturity/verify',
                data: requestData
            })
        }
        function getInquiryDetails(requestData) {
            return $http({
                method: 'POST',
                url: 'gbmultra/rbiBond/nsdlPan/validation/redemptionOnMaturity/inquiry',
                data: requestData
            })
        }

    }

})();