 angular.module('gbmUltraApp').controller('userDetailsController', function ($scope,$stateParams, $filter, $location, cbsService){
	
	
	$scope.recordsList = '';
	$scope.displayForm = true;
	
	
	

	// Initialize the page
	$scope.initialize = function(){
		
		$scope.userDetails = {};
		$scope.userDetails.gbmUserDetailsId = "";
		$scope.userDetails.userId = "";
		$scope.userDetails.userWorkClass = "";
		$scope.userDetails.solId = "";
		$scope.userDetails.additionalText1 = "";
		$scope.userDetails.additionalText2 = "";
		$scope.userDetails.additionalText3 = "";
		$scope.userDetails.userSolTenor = "";
		$scope.userDetails.userMaxInactiveTime = "";
		$scope.userDetails.userAccountExpiryDate = "";
	    $scope.userDetails.userPasswordExpiryDate = "";
		$scope.userDetails.userApplicationName = "";
		$scope.userDetails.newUserFlag = "true";
		$scope.userDetails.defaultTermId = "";
		$scope.userDetails.virtualFlag = "";
	
		//to initialize pagination
		$scope.viewby = '10';
		$scope.currentPage = 1;
		$scope.itemsPerPage = $scope.viewby;
		$scope.maxSize = 5; //Number of pager buttons to show
		
		// to initialize loader
		$scope.loader = false;
		
		$scope.displayFormTable = false;
		$scope.baseurl = "gbmultra/cbs/integration/master/userdetails";
		
		// to initialize left menu
		$scope.leftMenu = {};
		$scope.leftMenu.add = {display: true, href: '#!/user_details/add'};
		$scope.leftMenu.modify = {display: true, href: '#!/user_details/modify'};
		$scope.leftMenu.delete = {display: true, href: '#!/user_details/delete'};
		$scope.leftMenu.inquiry = {display: true, href: '#!/user_details/inquiry'};
		$scope.leftMenu.verify = {display: true, href: '#!/user_details/verify'};
		
	$("#userPasswordExpiryDate").on("dp.change", function() {
	        $scope.userDetails.userPasswordExpiryDate = $("#userPasswordExpiryDate").val();
	    });
	
	$("#userAccountExpiryDate").on("dp.change", function() {
	        $scope.userDetails.userAccountExpiryDate = $("#userAccountExpiryDate").val();
	    });
		 
		 
		 $('#userAccountExpiryDate').datetimepicker({
		    	format: 'DD/MM/YYYY',
		    	sideBySide:true,
		    	showClose: true,
		        showClear: true,
		       // minDate:new Date()
		    });
		 
		 $('#userPasswordExpiryDate').datetimepicker({
		    	format: 'DD/MM/YYYY',
		    	sideBySide:true,
		    	showClose: true,
		        showClear: true,
		        //minDate:new Date()
		    });
		
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
	}

	$scope.generateList = function(response){
		var data = [];
		angular.forEach(response, function(value, item){
			var tempdata= {};
			tempdata.gbmUserDetailsId = value.gbmUserDetailsId;
			tempdata.userId = value.userId;
			tempdata.userWorkClass = value.userWorkClass;
			tempdata.solId = value.solId;
			tempdata.userApplicationName = value.userApplicationName;
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
		angular.forEach($scope.userDetails, function(value, item){
            data[item] = value;
            
            if(item=='userAccountExpiryDate'){
            	var userAccountExpiryDate=$scope.userDetails.userAccountExpiryDate.split("/");
            	data[item]=userAccountExpiryDate[2] + '-' + userAccountExpiryDate[1] + '-' +  userAccountExpiryDate[0];
            }
            
            if(item=='userPasswordExpiryDate'){
            	var userPasswordExpiryDate=$scope.userDetails.userPasswordExpiryDate.split("/");
            	data[item]=userPasswordExpiryDate[2] + '-' + userPasswordExpiryDate[1] + '-' +  userPasswordExpiryDate[0];
            }
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
	}
	
	
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
	}
	
	
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
	}
	
	
	$scope.getRecord = function(gbmUserDetailsId){
		var url = $scope.baseurl+'/'+gbmUserDetailsId;
		var parameter= '';
		
		cbsService.getMethod(url, parameter ,function(response){
			if(response.success){
				
				$scope.userDetails = {};
				angular.forEach(response.data, function(value, item){
					if(item == 'userAccountExpiryDate' || item=='userPasswordExpiryDate'){
		                   $scope.userDetails[item] = $filter('date')(value, 'dd/MM/yyyy');
					}
					else if(item == 'newUserFlag' || item=='virtualFlag'){
							if(value != null){
								$scope.userDetails[item] = value.toString();
							} else {
								$scope.userDetails[item] = '';
							}
					}
					else
					{
						$scope.userDetails[item] = value;	
					}
                });
				$scope.recordsList = '';
				$scope.baseRecordList = '';
				} else {
					$scope.$emit('showAlert', { 'type': 'false', 'msg': response.message });
				}
			});
	};
	
	$scope.modifyForm = function(gbmUserDetailsId){
		$scope.initialize();
		$scope.displayForm = true;
		$scope.getRecord(gbmUserDetailsId);
	}
	
	$scope.modify = function(gbmUserDetailsId){
		var url = $scope.baseurl;
		var data = $scope.generateData();
		data.gbmUserDetailsId = $scope.userDetails.gbmUserDetailsId;
		
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
	}
	
	$scope.deleteForm = function(gbmUserDetailsId){
		$scope.initialize();
		$scope.displayForm = true;
		$scope.getRecord(gbmUserDetailsId);
	}
	
	$scope.delete = function(gbmUserDetailsId){
		var url = $scope.baseurl;
		var data = $scope.generateData();
		data.gbmUserDetailsId = $scope.userDetails.gbmUserDetailsId;
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
	}
	
	$scope.inquiryForm = function(gbmUserDetailsId){
		$scope.initialize();
		$scope.displayForm = true;
		$scope.getRecord(gbmUserDetailsId);
	}
	
	$scope.inquiry = function(gbmUserDetailsId){
		$scope.initialize();
		$scope.displayForm = false;
		$scope.list();
	}
	
	$scope.verifyForm = function(gbmUserDetailsId){
		$scope.initialize();
		$scope.displayForm = true;
		$scope.getRecord(gbmUserDetailsId);
	}
	
	$scope.verify = function(gbmUserDetailsId){
		var url = $scope.baseurl+'/verify';
		var data = $scope.generateData();
		data.gbmUserDetailsId = $scope.userDetails.gbmUserDetailsId;
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
	}
	
	
	
	
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
	}
	$scope.getPage();
	
	});