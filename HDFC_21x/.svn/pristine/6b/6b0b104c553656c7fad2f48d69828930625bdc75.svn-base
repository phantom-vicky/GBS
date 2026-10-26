(function () {

    'use strict';

    angular.module('gbmUltraApp').service(
        'rbiBondPanStatusUpdateForTdsService',
        rbiBondPanStatusUpdateForTdsService);
    rbiBondPanStatusUpdateForTdsService.$inject = ['$http'];
    function rbiBondPanStatusUpdateForTdsService($http) {
        var rbiBondPanStatusUpdateForTdsService = this;
        rbiBondPanStatusUpdateForTdsService.downloadSubmit = downloadSubmit;
        rbiBondPanStatusUpdateForTdsService.template = template;
        rbiBondPanStatusUpdateForTdsService.fileUploadSubmit = fileUploadSubmit;
        rbiBondPanStatusUpdateForTdsService.getDownloadModuleName = getDownloadModuleName;
        rbiBondPanStatusUpdateForTdsService.getUploadModuleName = getUploadModuleName;

        function getUploadModuleName(requestData) {
            return $http({
                method: 'POST',
                url: 'gbmultra/rbiBond/panStatus/upload/forTds/getUploadModuleName',
                data: requestData
            })
        }
        function getDownloadModuleName(requestData) {
            return $http({
                method: 'POST',
                url: 'gbmultra/rbiBond/panStatus/upload/forTds/download',
                data: requestData
            })
        }
        function fileUploadSubmit(file, requestData) {
            var fd = new FormData();
            fd.append('file', file);
            fd.append('dto', new Blob([JSON.stringify(requestData)], {
                type: "application/json"
            }));
            return $http.post('gbmultra/rbiBond/panStatus/upload/forTds/upload', fd, {
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
                url: 'gbmultra/rbiBond/panStatus/upload/forTds/template',
                data: requestData
            })
        }
        function downloadSubmit(requestData) {
            return $http({
                method: 'POST',
                url: 'gbmultra/rbiBond/panStatus/upload/forTds/download',
                data: requestData
            })
        }
    }

})();