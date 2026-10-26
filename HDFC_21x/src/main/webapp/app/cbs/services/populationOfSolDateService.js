gbmUltraApp.service('populationOfSolDateService', function ($http) {
	// Return public API.
    return ({
    	getCBSExcelFileNames: getCBSExcelFileNames
    });

   
    function getCBSExcelFileNames(functionality){
    	return $http({
            method: "post",
            url: 'gbmultra/cbs/integration/bulkupload/'+functionality+'/unverifiedfiles'
        });
    }
    
   
});