/*angular.module('gbmUltraApp').controller('branchesOfOtherBankController', function ($scope, $stateParams,$controller, cbsService){
	
	$controller('cbsCommonController', {$scope: $scope});*/

angular.module('gbmUltraApp').controller('branchesOfOtherBankController', function ($scope,$stateParams, $filter, $location, cbsService){
	
	$scope.recordsList = '';
	$scope.displayForm = true;
	
	
	// Initialize the page
	$scope.initialize = function(){
		
		$scope.baseurl = "gbmultra/cbs/integration/master/otherbankbranches";
		
		$scope.branchesOfOtherBank = {};
		$scope.branchesOfOtherBank.branchId = "";
		$scope.branchesOfOtherBank.branchCode = "";
		$scope.branchesOfOtherBank.bankCode = "";
		$scope.branchesOfOtherBank.micrCentreCode = "";
		$scope.branchesOfOtherBank.micrBankCode = "";
		$scope.branchesOfOtherBank.micrBranchCode = "";
		$scope.branchesOfOtherBank.branchName = "";
		$scope.branchesOfOtherBank.branchAddr1 = "";
		$scope.branchesOfOtherBank.branchAddr2 = "";
		$scope.branchesOfOtherBank.branchAddr3 = "";
		$scope.branchesOfOtherBank.branchCityCode = "";
		$scope.branchesOfOtherBank.branchStateCode = "";
		$scope.branchesOfOtherBank.branchPinCode = "";
		$scope.branchesOfOtherBank.additionalText1 = "";
		$scope.branchesOfOtherBank.additionalText2 = "";
		$scope.branchesOfOtherBank.additionalText3 = "";
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
		$scope.leftMenu.add = {display: true, href: '#!/branches_of_other_bank/add'};
		$scope.leftMenu.modify = {display: true, href: '#!/branches_of_other_bank/modify'};
		$scope.leftMenu.delete = {display: true, href: '#!/branches_of_other_bank/delete'};
		$scope.leftMenu.inquiry = {display: true, href: '#!/branches_of_other_bank/inquiry'};
		$scope.leftMenu.verify = {display: true, href: '#!/branches_of_other_bank/verify'};
		
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
	

	$scope.generateList = function(response){
		var data = [];
		angular.forEach(response, function(value, item){
			var tempdata= {};
			tempdata.branchId = value.branchId;
			tempdata.bankCode = value.bankCode;
			tempdata.branchCode = value.branchCode;
			tempdata.branchName = value.branchName;
			tempdata.branchCityCode = value.branchCityCode;
			tempdata.branchStateCode = value.branchStateCode;
			tempdata.branchPinCode = value.branchPinCode;
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
		angular.forEach($scope.branchesOfOtherBank, function(value, item){
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
				$scope.branchesOfOtherBank = {};
				angular.forEach(response.data, function(value, item){
                   $scope.branchesOfOtherBank[item] = value;
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
		data.branchId = $scope.branchesOfOtherBank.branchId;
		
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
		data.branchId = $scope.branchesOfOtherBank.branchId;
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
		data.branchId = $scope.branchesOfOtherBank.branchId;
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
				$scope.initialize();
				$scope.recordsList = '';
				$scope.displayForm = false;
				$scope.listUnverifiedRecords();
				break;
			case 'inquiry':
				$scope.requiredClass = '';
				$scope.displayForm = false;
				$scope.list();
				break;
			case 'verify':
				$scope.requiredClass = '';
				$scope.displayForm = false;
				$scope.listUnverifiedRecords();
				break;
		}
	};
	$scope.getPage();
	
	
});