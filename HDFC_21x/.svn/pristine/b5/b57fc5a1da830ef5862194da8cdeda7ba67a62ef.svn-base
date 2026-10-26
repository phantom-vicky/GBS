angular.module('gbmUltraApp').service('cbsBulkUploadService', function ($http) {
	// Return public API.
    return ({
    	addcbsBulkUpload: addcbsBulkUpload,
    	getCBSExcelFileNames: getCBSExcelFileNames,
    	getCBSDataByFileName: getCBSDataByFileName,
    	verifyCBSDataByFileName: verifyCBSDataByFileName,
    	deleteCBSDataByFileName: deleteCBSDataByFileName
    });

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
    
    function deleteCBSDataByFileName(functionality, fileName){
    	return $http({
            method: "post",
            url: 'gbmultra/cbs/integration/bulkupload/'+functionality,
            data : {fileName : fileName}
        });
    }
    
    
});