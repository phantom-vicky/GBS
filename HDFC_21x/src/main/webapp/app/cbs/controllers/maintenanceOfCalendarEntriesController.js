angular.module('gbmUltraApp').controller('maintenanceOfCalendarEntriesController', function ($scope,$stateParams, $filter, $location, cbsService){
	
	$scope.recordsList = '';
	$scope.displayForm = true;
	
	
	
	// Initialize the page
	$scope.initialize = function(){
		
		$scope.baseurl = "gbmultra/cbs/integration/master/calendarentries";
		
		$scope.maintenanceOfCalendarEntries = {};
		$scope.maintenanceOfCalendarEntries.id = "";
		$scope.maintenanceOfCalendarEntries.calendarType = "";
		$scope.maintenanceOfCalendarEntries.calendarCode = "";
		$scope.maintenanceOfCalendarEntries.monthYear = "";
		$scope.maintenanceOfCalendarEntries.holidayString = "";
	/*	$scope.maintenanceOfCalendarEntries.status = "";*/
		$scope.maintenanceOfCalendarEntries.additionalText1 = "";
		$scope.maintenanceOfCalendarEntries.additionalText2 = "";
		$scope.maintenanceOfCalendarEntries.additionalText3 = "";
		$scope.maintenanceOfCalendarEntries.month = "";
		$scope.maintenanceOfCalendarEntries.year = "";

		var year = new Date().getFullYear();
	    var range = [];
	    range.push(year);
	    for (var i = 1; i < 10; i++) {
	        range.push(year + i);
	    }
	    $scope.maintenanceOfCalendarEntries.years = range;
	    
		
		$scope.displayFormTable = false;
		
		//to initialize pagination
		$scope.viewby = '10';
		$scope.currentPage = 1;
		$scope.itemsPerPage = $scope.viewby;
		$scope.maxSize = 5; //Number of pager buttons to show
		
		// to initialize loader
		$scope.loader = false;
		
		// to initialize left menu
		$scope.leftMenu = {};
		$scope.leftMenu.add = {display: true, href: '#!/maintenance_of_calendar_entries/add'};
		$scope.leftMenu.modify = {display: true, href: '#!/maintenance_of_calendar_entries/modify'};
		$scope.leftMenu.delete = {display: true, href: '#!/maintenance_of_calendar_entries/delete'};
		$scope.leftMenu.inquiry = {display: true, href: '#!/maintenance_of_calendar_entries/inquiry'};
		$scope.leftMenu.verify = {display: true, href: '#!/maintenance_of_calendar_entries/verify'};
		
	};
$scope.initialize();
	
	$scope.searchInquiryFn = function(searchKeyword){
		$scope.recordsList = $filter('filter')($scope.baseRecordList, searchKeyword);
		$scope.currentPage = 1;
		$scope.totalItems = $scope.recordsList.length;
		$scope.initialize();
	}
	
	$scope.setItemsPerPage = function(itemsPerPage){
		$scope.currentPage = 1;
	}
	
	$scope.cancelOperation = function(action){
		if(action == 'delete' || action == 'modify' || action == 'verify' ){
			$scope.getPage();
		} else if(action == 'add' || action == 'inquiry'){
			location.href = "#!/";
		}
	}
	
	$scope.action = $stateParams.operation;
	
	if($scope.action == '' || typeof($scope.action) == 'undefined'){
		$scope.action = 'add';
	}
	
	$scope.submitForm = function(data){
		if($scope.action == 'add'){
			$scope.add();
		} else if($scope.action == 'modify'){
			$scope.modify();
		}
	};
	
	$scope.generateData = function(){
		var data = {};
		angular.forEach($scope.maintenanceOfCalendarEntries, function(value, item){
            data[item] = value;
        });
		data.monthYear = $scope.maintenanceOfCalendarEntries.month + $scope.maintenanceOfCalendarEntries.year;
		return data;
	};
	
	// Add data to database
	$scope.add = function(){
		var url = $scope.baseurl;
		var data = $scope.generateData();
		
		cbsService.postMethod(url, data, function(response){
			if(response.success) {
        		$scope.$emit('showAlert', { 'type': 'true', 'msg': response.data.message });
        		$scope.initialize();
			} else {
				$scope.$emit('showAlert', { 'type': 'false', 'msg': response.message });
			}
		});
	};
	
	$scope.list = function(){
		var url = $scope.baseurl+'/getallrecords';
		var parameter= '';
		$scope.loader = true;
		cbsService.getMethod(url, parameter, function(response){
			if(response.success) {
				$scope.baseRecordList = response.data;
				$scope.recordsList = $scope.baseRecordList;
				$scope.totalItems = $scope.recordsList.length;
				$scope.initialize();
			} else {
				$scope.$emit('showAlert', { 'type': 'false', 'msg': response.message });
			}
		});
	};
	
	
	$scope.listUnverifiedRecords = function(){
		var url = $scope.baseurl+'/unverifiedrecords';
		var parameter= '';
		$scope.loader = true;
		cbsService.getMethod(url, parameter, function(response){
			if(response.success) {
				$scope.baseRecordList = response.data;
				$scope.recordsList = $scope.baseRecordList;
				$scope.totalItems = $scope.recordsList.length;
				$scope.initialize();
				$scope.displayForm = false;
			} else {
				$scope.$emit('showAlert', { 'type': 'false', 'msg': response.message });
			}
		});
	};
	
	$scope.listVerifiedRecords = function(){
		var url = $scope.baseurl;
		var parameter= '';
		$scope.loader = true;
		cbsService.getMethod(url, parameter, function(response){
			if(response.success) {
				$scope.baseRecordList = response.data;
				$scope.recordsList = $scope.baseRecordList;
				$scope.totalItems = $scope.recordsList.length;
				$scope.initialize();
				$scope.displayForm = false;
			} else {
				$scope.$emit('showAlert', { 'type': 'false', 'msg': response.message });
			}
		});
	};
	
	$scope.getRecord = function(id){
		var url = $scope.baseurl+'/'+id;
		var parameter= '';
		
		cbsService.getMethod(url, parameter, function(response){
			if(response.success) {
				$scope.maintenanceOfCalendarEntries = {};
				angular.forEach(response.data, function(value, item){
                   $scope.maintenanceOfCalendarEntries[item] = value;
                });
				var year = new Date().getFullYear();
			    var range = [];
			    range.push(year);
			    for (var i = 1; i < 10; i++) {
			        range.push(year + i);
			    }
			    $scope.maintenanceOfCalendarEntries.years = range;
			    
				$scope.maintenanceOfCalendarEntries.month = response.data.monthYear.slice(0, 2);
				$scope.maintenanceOfCalendarEntries.year = response.data.monthYear.slice(2, 6);
				$scope.recordsList = '';
				$scope.baseRecordList = '';
			} else {
				$scope.$emit('showAlert', { 'type': 'false', 'msg': response.message });
			}
		});
	};
	
	$scope.modifyForm = function(id){
		$scope.initialize();
		$scope.displayForm = true;
		$scope.getRecord(id);
	};
	
	$scope.modify = function(){
		var url = $scope.baseurl;
		var data = $scope.generateData();
		data.id = $scope.maintenanceOfCalendarEntries.id;
		
		cbsService.putMethod(url, data, function(response){
			if(response.success) {
				$scope.$emit('showAlert', { 'type': 'true', 'msg': response.data.message });
        		$scope.initialize();
        		$scope.displayForm = false;
				$scope.list();
			} else {
				$scope.$emit('showAlert', { 'type': 'false', 'msg': response.message });
			}
		});
	};
	
	$scope.deleteForm = function(id){
		$scope.initialize();
		$scope.displayForm = true;
		$scope.getRecord(id);
	};
	
	$scope.delete = function(id){
		var url = $scope.baseurl;
		var data = $scope.generateData();
		data.id = $scope.maintenanceOfCalendarEntries.id;
		var result = confirm("Want to delete?");
		if (result) {
			cbsService.deleteMethod(url, data, function(response){
				if(response.success) {
					$scope.$emit('showAlert', { 'type': 'true', 'msg': response.data.message });
	        		$scope.initialize();
	        		$scope.displayForm = false;
	        		$scope.listUnverifiedRecords();
				} else {
					$scope.$emit('showAlert', { 'type': 'false', 'msg': response.message });
				}
			});
		}
	};

	$scope.inquiryForm = function(id){
		$scope.initialize();
		$scope.displayForm = true;
		$scope.getRecord(id);
	};
	
	$scope.inquiry = function(id){
		$scope.initialize();
		$scope.displayForm = false;
		$scope.list();
	};

	$scope.verifyForm = function(id){
		$scope.initialize();
		$scope.displayForm = true;
		$scope.getRecord(id);
	};
	
	$scope.verify = function(id){
		var url = $scope.baseurl+'/verify';
		var data = $scope.generateData();
		data.id = $scope.maintenanceOfCalendarEntries.id;
		var result = confirm("Want to verify?");
		if (result) {
			cbsService.postMethod(url, data, function(response){
				if(response.success) {
					$scope.$emit('showAlert', { 'type': 'true', 'msg': response.data.message });
	        		$scope.initialize();
	        		$scope.displayForm = false;
					$scope.listUnverifiedRecords();
				} else {
					$scope.$emit('showAlert', { 'type': 'false', 'msg': response.message });
				}
			});
		}
	};
	
	$scope.getPage = function(){
		$scope.baseRecordList = '';
		$scope.recordsList = '';
		$scope.initialize();
		switch($scope.action){
			case 'add':
				$scope.requiredClass = 'required';
				$scope.displayForm = true;
				break;
			case 'modify':
				$scope.requiredClass = 'required';
				$scope.displayForm = false;
				$scope.list();
				break;
			case 'delete':
			case 'verify':
				$scope.requiredClass = '';
				$scope.displayForm = false;
				$scope.listUnverifiedRecords();
				break;
			case 'inquiry':
				$scope.requiredClass = '';
				$scope.displayForm = false;
				$scope.list();
				break;
			
		}
	};
	$scope.getPage();
	
	
});