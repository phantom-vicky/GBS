gbmUltraApp.service('officeAccountPlaceHolderService', function ($http) {
	// Return public API.
    return ({
    	addofficeAccountPlaceHolder: addofficeAccountPlaceHolder
    	
    });

    function addofficeAccountPlaceHolder(data) {
        var parameter = JSON.stringify(data);

        return $http({
            method: "post",
            url: 'gbmultra/cbs/integration/master/officeaccountplaceholder',
            params: {}, data: parameter
        });
    }
    
});