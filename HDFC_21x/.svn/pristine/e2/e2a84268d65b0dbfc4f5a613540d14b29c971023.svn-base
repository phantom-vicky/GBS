angular.module('gbmUltraApp').controller('instrumentDetailsController', function ($scope,$stateParams, $filter, $location,cbsService){
	
	//$controller('cbsCommonController', {$scope: $scope});
	
	$scope.recordsList = '';
	$scope.displayForm = true;
	$scope.totalItems = 0;
	$scope.viewby = '10';
	$scope.itemsPerPage = $scope.viewby;
	
	$scope.getInstrumentTypes = function(){
		var url = $scope.baseurl+'/instrumenttypes';
		var parameter= '';
		
		cbsService.getMethod(url, parameter, function(response){
			if(response.success) {
				$scope.typeOfInstrumentList = response.data;
			} else {
				$scope.$emit('showAlert', { 'type': 'false', 'msg': response.message });
			}
		});
	};
	
	// Initialize the page
	$scope.initialize = function(){
		
		$scope.baseurl = "gbmultra/cbs/integration/master/instruments";
		
		$scope.instrumentDetails = {};
		$scope.instrumentDetails.instrumentId = "";
		$scope.instrumentDetails.drCrIndicator = "";
		$scope.instrumentDetails.instrumentType = "";
		$scope.instrumentDetails.schemeCode = "";
		$scope.instrumentDetails.additionalText1 = "";
		$scope.instrumentDetails.additionalText2 = "";
		$scope.instrumentDetails.additionalText3 = "";
		$scope.displayFormTable = false;
		$scope.typeOfInstrumentList = '';
		$scope.getInstrumentTypes();
		
		//to initialize pagination
		$scope.currentPage = 1;
		$scope.maxSize = 5; //Number of pager buttons to show
		
		// to initialize loader
		$scope.loader = false;
		// to initialize left menu
		$scope.leftMenu = {};
		$scope.leftMenu.add = {display: true, href: '#!/instrument_details/add'};
		$scope.leftMenu.modify = {display: true, href: '#!/instrument_details/modify'};
		$scope.leftMenu.delete = {display: true, href: '#!/instrument_details/delete'};
		$scope.leftMenu.inquiry = {display: true, href: '#!/instrument_details/inquiry'};
		$scope.leftMenu.verify = {display: true, href: '#!/instrument_details/verify'};
		
	};
	
	$scope.initialize();
	
	$scope.searchInquiryFn = function(searchKeyword){
		$scope.searchKeyword = searchKeyword;
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
	
	$scope.generateList = function(response){
		var data = [];
		angular.forEach(response, function(value, item){
			var tempdata= {};
			tempdata.instrumentId = value.instrumentId;
			tempdata.drCrIndicator = value.drCrIndicator;
			tempdata.instrumentType = value.instrumentType;
			tempdata.schemeCode = value.schemeCode;
			tempdata.additionalText1 = value.additionalText1;
			tempdata.additionalText2 = value.additionalText2;
			tempdata.additionalText3 = value.additionalText3;
			tempdata.createdDate = $filter('date')(value.createdDate, 'dd/MM/yyyy');
			tempdata.verified = value.verified;
            data.push(tempdata);
        });
		$scope.baseRecordList = data;
		$scope.recordsList = $scope.baseRecordList;
		$scope.totalItems = $scope.recordsList.length;
	}
	
	$scope.generateData = function(){
		var data = {};
		angular.forEach($scope.instrumentDetails, function(value, item){
            data[item] = value;
        });
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
		var url = $scope.baseurl+'/list';
		var parameter= '';
		$scope.loader = true;
		cbsService.getMethod(url, parameter, function(response){
			if(response.success) {
				$scope.generateList(response.data);
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
				$scope.generateList(response.data);
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
				$scope.generateList(response.data);
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
				$scope.instrumentDetails = {};
				angular.forEach(response.data, function(value, item){
                   $scope.instrumentDetails[item] = value;
                });
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
		data.instrumentId = $scope.instrumentDetails.instrumentId;
		
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
		data.instrumentId = $scope.instrumentDetails.instrumentId;
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
		data.instrumentId = $scope.instrumentDetails.instrumentId;
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