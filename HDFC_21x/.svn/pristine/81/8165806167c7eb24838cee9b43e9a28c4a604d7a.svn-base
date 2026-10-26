gbmUltraApp.service('instrumentDetailsService', function ($http) {
	// Return public API.
    return ({
    	addinstrumentDetails: addinstrumentDetails
    	
    });

    function addinstrumentDetails(data) {
        var parameter = JSON.stringify(data);

        return $http({
            method: "post",
            url: 'gbmultra/cbs/integration/master/instruments',
            params: {}, data: parameter
        });
    }
    
});