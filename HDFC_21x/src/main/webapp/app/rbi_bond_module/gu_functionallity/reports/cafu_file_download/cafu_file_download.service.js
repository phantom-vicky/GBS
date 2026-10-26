(function() {

    'use strict';

    angular.module('gbmUltraApp')
        .service('cafuFileDownloadService', cafuFileDownloadService);

    cafuFileDownloadService.$inject = ['$http'];

    function cafuFileDownloadService($http) {

        var cafuFileDownloadService = this;


        cafuFileDownloadService.downloadSelectedFiles = downloadSelectedFiles;
        cafuFileDownloadService.getModuleName = getModuleName;



        function downloadSelectedFiles(requestData) {
            return $http({
                method: 'POST',
                url: 'gbmultra/rbiBond/CafuFileDownload/getCafuFileDownload',
                data: requestData
            })
        }
         function getModuleName(requestData) {
                    return $http({
                        method: 'POST',
                        url: 'gbmultra/rbiBond/CafuFileDownload/getCafuFile',
                        data: requestData
                    })
                }


    }

})();