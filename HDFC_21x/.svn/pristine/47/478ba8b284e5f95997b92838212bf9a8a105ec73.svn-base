(function() {

    'use strict';

    angular
        .module('gbmUltraApp')
        .service('cbsBulkUploadService', cbsBulkUploadService);

    cbsBulkUploadService.$inject = ['$http'];

    function cbsBulkUploadService($http) {
    	
    	var cbsBulkUploadService = this;
    	cbsBulkUploadService.addcbsBulkUpload		= addcbsBulkUpload;
    	cbsBulkUploadService.getCBSExcelFileNames	= getCBSExcelFileNames;
    	cbsBulkUploadService.getCBSDataByFileName	= getCBSDataByFileName;
    	cbsBulkUploadService.verifyCBSDataByFileName= verifyCBSDataByFileName;
        cbsBulkUploadService.deleteCBSDataByFileName= deleteCBSDataByFileName;
        cbsBulkUploadService.downloadCBSDataByFileName= downloadCBSDataByFileName;
        cbsBulkUploadService.getRemarksList = getRemarksList;


        function addcbsBulkUpload(file, functionality) {
        	var fd = new FormData();
            fd.append('file', file);
            return $http.post('gbmultra/cbs/integration/bulkupload/'+functionality+'/save', fd, {
                transformRequest: angular.identity,
        	   	transformResponse: angular.identity,
        	   	headers: {'Content-Type': undefined,
        	   		'Custom-Content-Type': 'upload'}
            });
        }
        
        function getCBSExcelFileNames(functionality){
        	return $http({
                method: "post",
                url: 'gbmultra/cbs/integration/bulkupload/'+functionality+'/unverifiedfiles'
            });
        }
        
        function getCBSDataByFileName(functionality, fileName){
        	return $http({
                method: "post",
                url: 'gbmultra/cbs/integration/bulkupload/'+functionality+'/unverifiedrecords',
                data : {fileName : fileName}
            });
        }
        
        function verifyCBSDataByFileName(functionality, fileName){
        	return $http({
                method: "post",
                url: 'gbmultra/cbs/integration/bulkupload/'+functionality+'/verify',
                data : {fileName : fileName}
            });
        }
        
        function deleteCBSDataByFileName(functionality, fileName, remarks){
        	return $http({
                method: "post",
                url: 'gbmultra/cbs/integration/bulkupload/'+functionality,
                data : {fileName : fileName, remarks: remarks}
            });
        }

        function downloadCBSDataByFileName(functionality, fileName){
                        	return $http({
                                method: "post",
                                url: 'gbmultra/cbs/integration/bulkupload/fileExcelDownload',
                                data : {fileName : fileName, functionality: functionality}
                            });
                        }

       function getRemarksList(requestData){
                      return $http({
                           method:'POST',
                           url:'rbiBond/getRemarksList',
                           data:requestData
                      });
                   }

        
    }
    
})();