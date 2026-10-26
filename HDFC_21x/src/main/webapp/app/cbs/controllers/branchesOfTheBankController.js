angular.module('gbmUltraApp').controller('branchesOfTheBankController', function ($scope,$stateParams, $filter, $location, cbsService){
	
	$scope.recordsList = '';
	$scope.displayForm = true;
	$scope.viewby = '10';
	$scope.itemsPerPage = $scope.viewby;
	
	
	$scope.getStatus = function(){
		var url = $scope.baseurl+'/status';
		var parameter= '';
		
		cbsService.getMethod(url, parameter, function(response){
			if(response.success) {
				$scope.statusList = response.data;
			} else {
				$scope.$emit('showAlert', { 'type': 'false', 'msg': response.message });
			}
		});
	};
	
	
	
	$scope.initialize = function(){
		$scope.branchesOfTheBank={};
		$scope.branchesOfTheBank.solId = " ";
		$scope.branchesOfTheBank.branchStatus = "1";
		$scope.leftMenu = {};
		$scope.leftMenu.add = {display: true, href: '#!/branches_of_the_bank/add'};
		$scope.leftMenu.modify = {display: true, href: '#!/branches_of_the_bank/modify'};
		$scope.leftMenu.delete = {display: true, href: '#!/branches_of_the_bank/delete'};
		$scope.leftMenu.inquiry = {display: true, href: '#!/branches_of_the_bank/inquiry'};
		$scope.leftMenu.verify = {display: true, href: '#!/branches_of_the_bank/verify'};
		
		$scope.baseurl = "gbmultra/cbs/integration/master/bankbranch";
		
		$scope.getStatus();
		
		//to initialize pagination
		$scope.currentPage = 1;
		$scope.maxSize = 5; //Number of pager buttons to show
		
		// to initialize loader
		$scope.loader = false;
	}
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
	}
	
	$scope.generateList = function(response){
		var data = [];
		angular.forEach(response, function(value, item){
			var tempdata= {};
			tempdata.id = value.id;
			tempdata.solId = value.solId;
			tempdata.bankCode = value.bankCode;
			tempdata.branchCode = value.branchCode;
			tempdata.cityCode = value.cityCode;
			tempdata.stateCode = value.stateCode;
			tempdata.pinCode = value.pinCode;
			tempdata.panGirNumber = value.panGirNumber;
			tempdata.taxDednDetails = value.taxDednDetails;
			tempdata.branchStatusDescription = value.branchStatusDescription;
			tempdata.createdDate = $filter('date')(value.createdDate, 'dd/MM/yyyy');
			tempdata.verifiedFlag = value.verifiedFlag;
            data.push(tempdata);
        });
		$scope.baseRecordList = data;
		$scope.recordsList = $scope.baseRecordList;
		$scope.totalItems = $scope.recordsList.length;
	}
	
	$scope.generateData = function(){
		var data = {};
		angular.forEach($scope.branchesOfTheBank, function(value, item){
            data[item] = typeof(value) === 'string' ? value.toUpperCase() : value;
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
		var url = $scope.baseurl+'/getallrecordsinquiry';
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
/*	
//Get solId
	$scope.getSolID = function(){
		var url = $scope.baseurl+'/solIdandDesc';
		var parameter=$scope.branchesOfTheBank.solId;
		$scope.list = {};
		cbsService.getMethod(url,parameter, function(response){
			if(response.success) {
				$scope.list = response.data;
				$scope.initialize();
			} else {
				$scope.$emit('showAlert', { 'type': 'false', 'msg': response.message });
			}
		});
	}
	
	$scope.setSolID = function(r){
		$scope.branchesOfTheBank.solId = r.solId;
		$scope.branchesOfTheBank.solDesc = r.solDesc;
		$('#solIDModal').modal('hide');
	}
	*/
//Modify Record
	
	/*$scope.getRecord = function(id){
		var url = $scope.baseurl+'/'+id;
		var parameter= '';
		
		cbsService.getMethod(url, parameter, function(response){
			if(response.success) {
				$scope.branchesOfTheBank = {};
				$scope.branchesOfTheBank.solId=response.data.solId;
				$scope.branchesOfTheBank.solDesc=response.data.solDesc;
				$scope.branchesOfTheBank.bankCode=response.data.bankCode;
				$scope.branchesOfTheBank.branchCode=response.data.branchCode;
				$scope.branchesOfTheBank.micrCertreCode=response.data.micrCertreCode;
				$scope.branchesOfTheBank.micrBankCode=response.data.micrBankCode;
				$scope.branchesOfTheBank.micrBranchCode=response.data.micrBranchCode;
				$scope.branchesOfTheBank.addr1=response.data.addr1;
				$scope.branchesOfTheBank.addr2=response.data.addr2;
				$scope.branchesOfTheBank.stateCode=response.data.stateCode;
				$scope.branchesOfTheBank.pinCode=response.data.pinCode;
				$scope.branchesOfTheBank.cityCode=response.data.cityCode;
				$scope.branchesOfTheBank.panGirNumber=response.data.panGirNumber;
				$scope.branchesOfTheBank.taxDednDetails=response.data.taxDednDetails;
				$scope.branchesOfTheBank.tdsCircleName=response.data.tdsCircleName;
				$scope.branchesOfTheBank.divisionName=response.data.divisionName;
				$scope.branchesOfTheBank.circlOfficeName=response.data.circlOfficeName;
				$scope.branchesOfTheBank.uniformBrCode=response.data.uniformBrCode;
				$scope.branchesOfTheBank.additionalText1=response.data.additionalText1;
				$scope.branchesOfTheBank.additionalText2=response.data.additionalText2;
				$scope.branchesOfTheBank.additionalText3=response.data.additionalText3;
				$scope.branchesOfTheBank.id=response.data.id;
				$scope.recordsList = '';
			} else {
				$scope.$emit('showAlert', { 'type': 'false', 'msg': response.message });
			}
		});
	};*/
	
	$scope.getRecord = function(id){
		var url = $scope.baseurl+'/'+id;
		var parameter= '';
		
		cbsService.getMethod(url, parameter, function(response){
			if(response.success) {
				$scope.branchesOfTheBank = {};
				angular.forEach(response.data, function(value, item){
                   $scope.branchesOfTheBank[item] = value;
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
		data.verifiedFlag = false;
		
		cbsService.putMethod(url, data, function(response){
			if(response.success) {
				$scope.$emit('showAlert', { 'type': 'true', 'msg': response.data.message });
        		$scope.initialize();
        		$scope.displayForm = false;
				$scope.list();
			} else {
				if(response.data.error == undefined) {
					$scope.$emit('showAlert', { 'type': 'false', 'msg': response.data.error });
				} else {
					var result = "";
	        		angular.forEach(response.data.error, function(value, index) {
	          		result += (value);
	        		if(index != response.data.error.length - 1) {
	        			result = result + ","
	        		}
	        		$scope.$emit('showAlert', { 'type': 'false', 'msg':result });
	        		
	        		});
				}
			}
		});
	};
	
//Delete Resord
	$scope.deleteForm = function(id){
		$scope.initialize();
		$scope.displayForm = true;
		$scope.getRecord(id);
	};
	
	$scope.delete = function(id){
		var url = $scope.baseurl;
		var data = $scope.generateData();
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
	
//verified and unverified files
	
		$scope.verifyForm = function(id){
			$scope.initialize();
			$scope.displayForm = true;
			$scope.getRecord(id);
		};
		
		$scope.verify = function(id){
			var url = $scope.baseurl+'/verify';
			var data = $scope.generateData();
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
//Inquiry 
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