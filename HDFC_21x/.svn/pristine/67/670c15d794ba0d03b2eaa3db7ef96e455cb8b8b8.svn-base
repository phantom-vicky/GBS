gbmUltraApp.service('maintenanceOfCalendarEntriesService', function ($http) {
	// Return public API.
    return ({
    	addmaintenanceOfCalendarEntries: addmaintenanceOfCalendarEntries
    });

    function addmaintenanceOfCalendarEntries(data) {
        var parameter = JSON.stringify(data);

        return $http({
            method: "post",
            url: 'gbmultra/cbs/integration/master/calendarentries',
            params: {}, data: parameter
        });
    }
    
});